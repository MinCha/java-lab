package com.googlecode.goodsamples.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class HibernateSessionFactory {
	private static Log log = LogFactory.getLog(HibernateSessionFactory.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
        	Configuration cfg = new Configuration().configure();
        	cfg.addResource("com/googlecode/goodsamples/hibernate/Message.hbm.xml");
            return cfg.buildSessionFactory();
        }
        catch (Throwable ex) {
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
