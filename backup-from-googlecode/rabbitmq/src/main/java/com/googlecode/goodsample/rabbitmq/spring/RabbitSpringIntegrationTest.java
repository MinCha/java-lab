package com.googlecode.goodsample.rabbitmq.spring;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RabbitSpringIntegrationTest {
	@Autowired
	private AmqpTemplate sut;

	@Test
	public void produceAndConsumeWithString() {
		final String queueName = "hello.string";
		String hello = "hello";

		sut.convertAndSend(queueName, hello);
		String result = (String) sut.receiveAndConvert(queueName);

		assertThat(result, is(hello));
	}

	@Test
	public void produceAndConsumeWithObject() {
		final String queueName = "hello.object";
		Pick pick = new Pick("Icearrows", "my photo");

		sut.convertAndSend(queueName, pick);
		Pick result = (Pick) sut.receiveAndConvert(queueName);

		assertThat(result, is(pick));
	}
}
