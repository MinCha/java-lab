package com.googlecode.goodsamples.zookeeper;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.junit.After;
import org.junit.Before;

public class AbstractZooKeeperTest {
	protected static final String HOST = "127.0.0.1:2181";
	protected static ZooKeeper zooKeeper;
	protected static final byte[] NO_DATA = new byte[0];
	protected static final Integer INITIAL_VERSION = 0;
	protected static final ArrayList<ACL> NO_ACL = Ids.OPEN_ACL_UNSAFE;

	public AbstractZooKeeperTest() {
		super();
	}

	@Before
	public void connectToZooKeeper() throws IOException, KeeperException,
			InterruptedException {
		zooKeeper = new ZooKeeper(HOST, 3000, new DefaultWatcher());
	}

	@After
	public void disconnectFromZooKeeper() throws InterruptedException {
		zooKeeper.close();
	}

	protected void createWhenThereIsNoNode(String path, CreateMode createMode)
			throws KeeperException, InterruptedException {
		if (zooKeeper.exists(path, false) == null) {
			zooKeeper.create(path, NO_DATA, Ids.OPEN_ACL_UNSAFE, createMode);
		}
	}

	protected class DefaultWatcher implements Watcher {
		@Override
		public void process(WatchedEvent event) {
		}
	}
}