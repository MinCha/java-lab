package com.googlecode.goodsamples.zookeeper.masterelection;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class MasterCandidate implements Watcher, Runnable {
	private static final Log LOG = LogFactory.getLog(MasterCandidate.class);
	private static final byte[] NO_DATA = new byte[0];
	static final String PREFIX = "c_";

	private Object mutex = new Object();
	private String targetPath;
	private ZooKeeper zooKeeper;
	private boolean master = false;
	private Integer round = 1;
	private Integer priority;
	private Boolean running = true;

	MasterCandidate() {
	}

	/**
	 * Connects to ZooKeeper then itself registers to ZooKeeper as an candidate.
	 */
	public MasterCandidate(String host, String root, Integer priority)
			throws IOException, KeeperException, InterruptedException {
		this.zooKeeper = new ZooKeeper(host, 3000, this);
		this.targetPath = root;
		this.priority = priority;

		zooKeeper.create(root + "/" + PREFIX + priority, NO_DATA,
				Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
	}

	@Override
	public void run() {
		while (running) {
			synchronized (mutex) {
				try {
					List<String> candidates = zooKeeper.getChildren(targetPath,
							true);
					Collections.sort(candidates);

					if (canBeMaster(elect(candidates))) {
						master = true;
						LOG.info(priority + " has just elected among "
								+ candidates);
					} else {
						master = false;
						LOG.info(priority + " is watching for among "
								+ candidates);
					}

					round++;
					mutex.wait();
				} catch (Exception e) {
					throw new RuntimeException(e.toString(), e);
				}
			}
		}
	}

	public boolean isMaster() {
		return master;
	}

	@Override
	public void process(WatchedEvent event) {
		if (event.getType() == EventType.None) {
			return;
		}

		if (event.getType() == EventType.NodeChildrenChanged) {
			synchronized (mutex) {
				mutex.notify();
			}
		}
	}

	public void retire() throws InterruptedException {
		LOG.info(priority + " has just retired");
		master = false;
		close();
	}

	public void close() {
		stopThread();
		closeZookeeper();
	}

	private boolean isNotEndedRound(int i) {
		return round == i;
	}

	String elect(List<String> candidates) {
		String result = candidates.get(0);
		Integer lowestPriority = Integer.parseInt(result.split("_")[1]);

		for (String each : candidates) {
			Integer eachPriority = Integer.parseInt(each.split("_")[1]);
			if (eachPriority < lowestPriority) {
				result = each;
				lowestPriority = eachPriority;
			}
		}

		return result;
	}

	private void closeZookeeper() {
		try {
			zooKeeper.close();
		} catch (Exception e) {
		}
	}

	private void stopThread() {
		running = false;
		synchronized (mutex) {
			mutex.notify();
		}
	}

	private boolean canBeMaster(String elected) {
		return elected.equals(PREFIX + priority);
	}

	public void waitUntil(Integer round) throws InterruptedException {
		while (isNotEndedRound(round)) {
			Thread.sleep(10);
		}
	}
}
