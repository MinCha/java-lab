package com.googlecode.goodsamples.zookeeper.barrier;

import static org.apache.zookeeper.CreateMode.EPHEMERAL;
import static org.apache.zookeeper.CreateMode.PERSISTENT;

import java.lang.management.ManagementFactory;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class Barrier implements Watcher {
	private static final Log LOG = LogFactory.getLog(Barrier.class);
	private static final String ROOT = "/barrier";
	private static final String PARTICIPANTS = ROOT + "/participants";
	private static final String JOB = ROOT + "/jobs";
	private static final byte[] NO_DATA = new byte[0];

	private Object sharedMutex = new Object();
	private String name = uniqueId();
	private Integer participantCount;
	private ZooKeeper zooKeeper;
	
	public Barrier(String host, Integer participantCount) throws Exception {
		this.participantCount = participantCount;
		this.zooKeeper = new ZooKeeper(host, 3000, this);
		
		createWhenThereIsNoNode(ROOT, PERSISTENT);
		createWhenThereIsNoNode(PARTICIPANTS, EPHEMERAL);
		createWhenThereIsNoNode(JOB, EPHEMERAL);
	}

	public boolean ensureAllParticipantsAreReady() throws KeeperException,
			InterruptedException {
		createWhenThereIsNoNode(PARTICIPANTS + "/" + name, EPHEMERAL);
		LOG.info("Created PID : " + uniqueId());

		while (true) {
			synchronized (sharedMutex) {
				List<String> children = zooKeeper.getChildren(PARTICIPANTS,
						true);
				if (children.size() == participantCount) {
					return true;
				} else {
					waitUntilThereIsEventNotificationFromZooKeeper();
				}
			}
		}
	}

	public void ensureAllParticipantsStartTheirJobs() throws KeeperException,
			InterruptedException {
		createWhenThereIsNoNode(JOB + "/" + name, EPHEMERAL);
		while (true) {
			synchronized (sharedMutex) {
				List<String> children = zooKeeper.getChildren(JOB, true);
				if (children.size() == participantCount) {
					return;
				} else {
					waitUntilThereIsEventNotificationFromZooKeeper();
				}
			}
		}
	}

	public boolean leave() throws KeeperException, InterruptedException {
		final int anyVersion = -1;
		zooKeeper.delete(PARTICIPANTS + "/" + name, anyVersion);
		LOG.info("Removed PID : " + uniqueId());
		while (true) {
			synchronized (sharedMutex) {
				List<String> children = zooKeeper.getChildren(PARTICIPANTS,
						true);
				if (children.isEmpty()) {
					zooKeeper.close();
					return true;
				} else {
					waitUntilThereIsEventNotificationFromZooKeeper();
				}
			}
		}
	}

	private String uniqueId() {
		return ManagementFactory.getRuntimeMXBean().getName()
				+ Thread.currentThread().getId();
	}

	private void createWhenThereIsNoNode(String path, CreateMode createMode)
			throws KeeperException, InterruptedException {
		if (zooKeeper.exists(path, false) == null) {
			zooKeeper.create(path, NO_DATA, Ids.OPEN_ACL_UNSAFE, createMode);
		}
	}

	@Override
	public void process(WatchedEvent event) {
		wakeUpWatingThread();
	}

	private void wakeUpWatingThread() {
		synchronized (sharedMutex) {
			sharedMutex.notify();
		}
	}

	private void waitUntilThereIsEventNotificationFromZooKeeper()
			throws InterruptedException {
		sharedMutex.wait();
	}
}