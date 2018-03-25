package com.doublegaurd.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public final class HibernateUtil {
	private static SessionFactory SESSIONFACTORY;
	@SuppressWarnings("unused")
	private static final String CLASS_NAME = HibernateUtil.class.getName();
	private static ServiceRegistry serviceRegistry;
	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			
			final Configuration configuration = new Configuration();
			configuration.configure("config/hibernate.cfg.xml");
			
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			
			SESSIONFACTORY = configuration.buildSessionFactory(serviceRegistry);
			
		} catch (HibernateException ex) {
			ex.printStackTrace();
			//throw new RunTimeException(ex);
		}
	}

	private HibernateUtil() {

	}

	public static SessionFactory getSessionFactory() {
		return SESSIONFACTORY;
	}
}