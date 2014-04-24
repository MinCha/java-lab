package com.googlecode.goodsamples.hibernatejpa;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MessageMappingUsingJPATest {
	private static Log log = LogFactory.getLog(MessageMappingUsingJPATest.class);
	
	Session session;
	private Transaction tx;
	
	@Before
	public void prepare() {
		session = JPASessionFactory.getSession();
		log.info("Opened a new session.");
		tx = session.beginTransaction();
		log.info("Began a new transaction.");
	}
	
	@After
	public void cleanResources() {
		tx.rollback();
		log.info("Rollbacked the transaction.");
		session.close();
		log.info("Closed the session.");
		JPASessionFactory.shutdown();
	}
	
	private MessageUsingJPA createMessage() {
		return new MessageUsingJPA("Test" + System.currentTimeMillis());
	}
	
	@Test
	public void messageShouldBeStoredThenLoaded() {
		Long id = (Long)session.save(createMessage());
		
		assertThat(id, is(notNullValue()));
		assertThat(session.get(MessageUsingJPA.class, id), is(notNullValue()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void nextMessageShouldBeSavedWithOriginalMessage() {
		MessageUsingJPA message = createMessage();
		message.setNextMessage(createMessage()); // Cascade Feature
		session.save(message);
		
		List<MessageUsingJPA> messages = session.createQuery("from MessageUsingJPA m order by m.id asc").list();
		
		assertThat(messages.size(), is(2));
	}
}
