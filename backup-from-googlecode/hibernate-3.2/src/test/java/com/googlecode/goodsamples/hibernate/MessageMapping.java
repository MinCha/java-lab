package com.googlecode.goodsamples.hibernate;

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

public class MessageMapping {
	private static Log log = LogFactory.getLog(MessageMapping.class);
	
	Session session;
	private Transaction tx;
	
	@Before
	public void prepare() {
		session = HibernateSessionFactory.getSession();
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
		HibernateSessionFactory.shutdown();
	}

	private Message createMessage() {
		return new Message("Test" + System.currentTimeMillis());
	}
	
	@Test
	public void messageShouldBeStoredThenLoaded() {
		Long id = (Long)session.save(createMessage());
		
		assertThat(id, is(notNullValue()));
		assertThat(session.get(Message.class, id), is(notNullValue()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void nextMessageShouldBeSavedWithOriginalMessage() {
		Message message = createMessage();
		message.setNextMessage(createMessage()); // Cascade Feature
		session.save(message);
		
		List<Message> messages = session.createQuery("from Message m order by m.id asc").list();
		
		assertThat(messages.size(), is(2));
	}
}
