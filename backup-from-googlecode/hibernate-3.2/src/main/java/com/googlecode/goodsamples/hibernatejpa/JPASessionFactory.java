package com.googlecode.goodsamples.hibernatejpa;

import javax.persistence.Entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.goodsamples.common.ClassScanner;

public class JPASessionFactory {
	private static Log log = LogFactory.getLog(JPASessionFactory.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			cfg = (AnnotationConfiguration) cfg.configure();

			for (Class<?> clasz : ClassScanner.getClassesAnnotated(
					"com.googlecode.goodsamples", Entity.class)) {
				cfg.addAnnotatedClass(clasz);
			}
			return cfg.buildSessionFactory();
		} catch (Throwable ex) {
			log.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		sessionFactory.close();
	}
}
