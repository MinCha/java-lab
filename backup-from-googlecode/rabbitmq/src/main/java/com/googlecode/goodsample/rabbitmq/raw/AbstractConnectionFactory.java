package com.googlecode.goodsample.rabbitmq.raw;

import com.rabbitmq.client.ConnectionFactory;

public abstract class AbstractConnectionFactory {
	final String userId = "guest";
	final String pw = "guest";
	final int port = 5672;
	final String host = "10.64.135.189";
	final String virtualHost = "/";

	ConnectionFactory createConnectionFactory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(userId);
		factory.setPassword(pw);
		factory.setHost(host);
		factory.setPort(port);
		factory.setVirtualHost(virtualHost);
		return factory;
	}
}
