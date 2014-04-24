package com.googlecode.goodsamples.quartz;

import static org.mockito.Mockito.*;

import org.apache.commons.logging.Log;
import org.junit.Test;
import org.quartz.Job;

public class HelloJobTest {
	Job helloJob = new HelloJob();
	
	@Test
	public void helloJobShouldSayHello() throws Exception {
		Log log = mock(Log.class);
		((HelloJob)helloJob).log = log;
		
		helloJob.execute(null);
		
		verify(log).info(HelloJob.MESSAGE);
	}
}
