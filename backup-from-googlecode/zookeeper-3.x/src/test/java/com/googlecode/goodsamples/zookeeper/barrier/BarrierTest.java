package com.googlecode.goodsamples.zookeeper.barrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;

/**
 * <p>
 * Zookeeper must be running to execute this test.
 * </p>
 */
public class BarrierTest {
	private static final Log LOG = LogFactory.getLog(BarrierTest.class);
	private static final Integer PARTICIPANT_COUNT = 3;
	private static final String HOST = "127.0.0.1:2181";

	@Test
	public void barrierShouldBeRunWithoutProblems() throws Exception {
		ExecutorService executorService = runBarrierClientsAsMuchAsExpectedCount();
		executorService.shutdown();
		executorService.awaitTermination(3, TimeUnit.SECONDS);
	}

	@Test
	@Ignore
	public void barrierShouldBeRunWithoutProblems_When_ItIsRepeatlyRun()
			throws Exception {
		final Integer repeatCount = 30;
		for (int i = 0; i < repeatCount; i++) {
			barrierShouldBeRunWithoutProblems();
		}
	}

	private ExecutorService runBarrierClientsAsMuchAsExpectedCount() {
		ExecutorService executorService = Executors
				.newFixedThreadPool(PARTICIPANT_COUNT);
		for (int i = 0; i < PARTICIPANT_COUNT; i++) {
			executorService.execute(new BarrierClient());
		}
		return executorService;
	}

	private class BarrierClient implements Runnable {
		@Override
		public void run() {
			try {
				Barrier barrier = new Barrier(HOST, PARTICIPANT_COUNT);
				barrier.ensureAllParticipantsAreReady();
				barrier.ensureAllParticipantsStartTheirJobs();
				barrier.leave();
				LOG.info("End of TID : " + Thread.currentThread().getId());
			} catch (Exception e) {
				throw new RuntimeException(e.toString(), e);
			}
		}
	}
}