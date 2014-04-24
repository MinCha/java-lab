package com.googlecode.goodsample.rabbitmq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-message-listener.xml" })
public class RabbitSpringListenerIntegrationTest {
	@Autowired
	private AmqpTemplate sut;

	@Test
	public void infinitePublishAndConsume() throws InterruptedException {
		int i = 0;
		while (true) {
			sut.convertAndSend("hello.object", new Pick("min", "cha"));
			i++;
			sut.convertAndSend("hello.string", "hello min");
			i++;
			System.out.println(i + " fired");
			Thread.sleep(300);
		}
	}

	@Test
	public void infiniteConsume() throws InterruptedException {
		while (true) {
			Thread.sleep(300);
		}
	}
}