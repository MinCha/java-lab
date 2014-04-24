package com.googlecode.goodsamples.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {
	public static final Object MESSAGE = "Hello";
	public Log log = LogFactory.getLog(HelloJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info(MESSAGE);
	}
}
