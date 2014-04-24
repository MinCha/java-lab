package com.googlecode.goodsample.rabbitmq.raw;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class QueueIntegrationTest extends AbstractConnectionFactory {
	@Test
	public void canConnect() throws IOException {
		Connection conn = null;
		try {
			conn = createConnectionFactory().newConnection();
			conn.createChannel();

			assertThat(conn.isOpen(), is(true));
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Test
	public void canCreateQueue() throws IOException {
		Connection conn = null;
		try {
			conn = createConnectionFactory().newConnection();
			Channel channel = conn.createChannel();
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("x-ha-policy", "all");
			channel.queueDeclare("hello", true, false, false, args);
			channel.queueDelete("hello");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Test
	public void canPublishMessage() throws IOException {
		Connection conn = null;
		try {
			conn = createConnectionFactory().newConnection();
			Channel channel = conn.createChannel();

			byte[] message = "Hello, Rabbit!".getBytes();
			channel.basicPublish("", "hello",
					MessageProperties.PERSISTENT_TEXT_PLAIN, message);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
