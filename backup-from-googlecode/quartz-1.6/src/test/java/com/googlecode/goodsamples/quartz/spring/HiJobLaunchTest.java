package com.googlecode.goodsamples.quartz.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class HiJobLaunchTest {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job hiJob;

	@Test
	public void jobShouldBeRunThoughSpring() throws Exception {
	}
}
