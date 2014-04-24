package com.googlecode.goodsamples.zookeeper;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Zookeeper must be running to execute this test. 
 *  </p>
 */

public class BasicOperationTest extends AbstractZooKeeperTest {
	final static String ROOT = "/test";
	final static Object mutex = new Object();

	private boolean notified = false;

	@Test
	public void createNode() throws KeeperException, InterruptedException {
		String actualName = createSequencialNode(ROOT + "/" + "z_");
		Stat result = zooKeeper.exists(actualName, false);

		assertThat(result, is(notNullValue()));
		assertThat(result.getVersion(), is(INITIAL_VERSION));
	}

	@Test
	public void removeNode() throws KeeperException, InterruptedException {
		String actualName = createSequencialNode(ROOT + "/" + "z_");
		zooKeeper.delete(actualName, 0);

		assertThat(zooKeeper.exists(actualName, false), is(nullValue()));
	}

	@Test
	public void watchNode() throws KeeperException, InterruptedException {
		String actualName = createSequencialNode(ROOT + "/" + "w_");
		zooKeeper.exists(actualName, new WatchTestWatcher());

		synchronized (mutex) {
			zooKeeper.delete(actualName, 0);
			mutex.wait();
		}

		assertThat(notified, is(true));
	}

	private class WatchTestWatcher implements Watcher {
		@Override
		public void process(WatchedEvent event) {
			notified = true;
			synchronized (mutex) {
				mutex.notify();
			}			
		}
	}
	
	private String createSequencialNode(String prefix) throws KeeperException,
			InterruptedException {
		String actualName = zooKeeper.create(prefix, NO_DATA, NO_ACL,
				CreateMode.EPHEMERAL_SEQUENTIAL);
		return actualName;
	}
	
	@Before
	public void assertThatThereIsRootPath() throws KeeperException, InterruptedException {
		if (zooKeeper.exists(ROOT, false) == null) {
			zooKeeper.create(ROOT, NO_DATA, Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
		}		
	}
}
