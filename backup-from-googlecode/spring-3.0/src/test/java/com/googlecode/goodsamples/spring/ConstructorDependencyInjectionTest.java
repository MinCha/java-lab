package com.googlecode.goodsamples.spring;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dependencyContext.xml"})
public class ConstructorDependencyInjectionTest {
	@Autowired
	private Composite sut;

	@Test
	public void dependencyShouldBeInjected_Through_Constructor() {
		assertThat(sut.one(), is(not(sut.two())));
	}
}
