package com.googlecode.goodsamples.zookeeper.masterelection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;
import org.junit.Test;

import com.googlecode.goodsamples.zookeeper.AbstractZooKeeperTest;

/**
 * <p>
 * ZooKeeper must be running to execute this test.
 * </p>
 */
public final class MasterElectionTest extends AbstractZooKeeperTest {
	final static Integer ROUND_ONE = 1;
	final static Integer ROUND_TWO = 2;
	final static Integer ROUND_THREE = 3;
	final static String ROOT = "/master";
	ExecutorService service = Executors.newFixedThreadPool(10);

	@Test
	public void masterShouldBeElectedByItsPriotity() throws KeeperException,
			InterruptedException, IOException {
		MasterCandidate first = createCandidateBy(9999);
		MasterCandidate second = createCandidateBy(5555);

		first.waitUntil(ROUND_ONE);
		second.waitUntil(ROUND_ONE);

		assertThat(first.isMaster(), is(false));
		assertThat(second.isMaster(), is(true));

		clear(first, second);
	}

	@Test
	public void nextMasterShouldBeElectedByItsPriority_IfCurrentMasterIsRetired()
			throws KeeperException, InterruptedException, IOException {
		MasterCandidate first = createCandidateBy(0);
		MasterCandidate second = createCandidateBy(999);

		first.waitUntil(ROUND_ONE);
		second.waitUntil(ROUND_ONE);

		assertThat(first.isMaster(), is(true));

		first.retire();
		second.waitUntil(ROUND_TWO);

		assertThat(first.isMaster(), is(false));
		assertThat(second.isMaster(), is(true));

		clear(first, second);
	}

	@Test
	public void highPriotiryCandidateShouldBeElected_EvenThoughThrereIsCurrentMasters()
			throws KeeperException, InterruptedException, IOException {
		MasterCandidate first = createCandidateBy(0);
		MasterCandidate second = createCandidateBy(999);

		first.waitUntil(ROUND_ONE);
		second.waitUntil(ROUND_ONE);

		first.retire();
		second.waitUntil(ROUND_TWO);

		first = createCandidateBy(1);

		first.waitUntil(ROUND_ONE);
		second.waitUntil(ROUND_THREE);

		assertThat(first.isMaster(), is(true));
		assertThat(second.isMaster(), is(false));

		clear(first, second);
	}

	@Test
	public void theHighestPriorityIsElected() {
		List<String> candidates = new ArrayList<String>();
		candidates.add("c_001");
		candidates.add("c_002");
		candidates.add("c_010");

		MasterCandidate sut = new MasterCandidate();

		String result = sut.elect(candidates);
		assertThat(result, is("c_001"));
	}

	private MasterCandidate createCandidateBy(Integer priority)
			throws IOException, KeeperException, InterruptedException {
		MasterCandidate result = new MasterCandidate(HOST, ROOT, priority);
		service.execute(result);
		return result;
	}

	private void clear(MasterCandidate... candidates)
			throws InterruptedException {
		for (MasterCandidate each : candidates) {
			each.close();
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.SECONDS);
	}
}
