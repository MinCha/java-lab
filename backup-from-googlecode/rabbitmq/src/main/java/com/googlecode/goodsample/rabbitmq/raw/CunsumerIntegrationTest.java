package com.googlecode.goodsample.rabbitmq.raw;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.Connection;

public class CunsumerIntegrationTest extends AbstractConnectionFactory {
	@Test
	public void canConsume() throws IOException {
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
}
