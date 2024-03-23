package com.demo.Movies_Database_Managemenet_System;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {
		try {
			return new
					Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Director.class).addAnnotatedClass(MovieType.class).addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class).addAnnotatedClass(User.class).addAnnotatedClass(Login.class).buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}

