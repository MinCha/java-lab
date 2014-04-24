package com.googlecode.goodsamples.quartz;

import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class HelloJobLaunchTest {
	@Test
	public void schedulerShouldBeRunByJobConfiguration() throws Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		
		/*
		 If you want to run your job from java, use following code snippet.
		 
		JobDetail jobDetail = new JobDetail("SimpleHelloJob", null, HelloJob.class);
		Trigger trigger = new SimpleTrigger("rightNowTrigger", null);
		scheduler.scheduleJob(jobDetail, trigger);
		*/
		
		scheduler.shutdown();		
	}
}
