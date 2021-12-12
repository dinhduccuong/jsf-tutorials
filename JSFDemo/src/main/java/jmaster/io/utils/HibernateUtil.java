package jmaster.io.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactoryObj();

	// Create The SessionFactory Object From Standard (Hibernate.cfg.xml)
	// Configuration File
	public static SessionFactory buildSessionFactoryObj() {
		Configuration configuration = new Configuration()
				.addResource("hibernate.cfg.xml")
				.configure();
		
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());

		return configuration.buildSessionFactory(builder.build());
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}