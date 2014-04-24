package com.googlecode.goodsamples.spring;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.inject.Provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:prototypeContext.xml", "classpath:dependencyContext.xml"})
public class PrototypeInjectionTest {
	@Autowired
	Prototype prototypeOne;
	@Autowired
	Prototype prototypeTwo;
	@Autowired
	Provider<Prototype> prototypeProvider;

	@Test
	public void prototypeIsNotSingletone() {
		assertThat(prototypeOne, is(not(sameInstance(prototypeTwo))));
	}

	@Test
	public void newPrototypeShouldBePickedOutFromProvider() {
		Prototype a = prototypeProvider.get();
		Prototype b = prototypeProvider.get();

		assertThat(a, is(not(sameInstance(b))));
	}
}
