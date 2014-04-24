package com.googlecode.goodsamples.spring;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.googlecode.goodsamples.spring.support.Dependency;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dependencyContext.xml" })
public class DependencyInjectionTest {
	@Autowired
	private Dependency dependency;

	@Test
	public void dependencyShouldBeInjected() {
		assertThat(dependency, is(notNullValue()));
		assertThat(dependency.getOneDataSource(), is(notNullValue()));
		assertThat(dependency.getTwoDataSource(), is(notNullValue()));
	}
}
