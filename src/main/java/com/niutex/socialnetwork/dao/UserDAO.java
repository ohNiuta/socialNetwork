package com.niutex.socialnetwork.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.niutex.socialnetwork.model.User;

public class UserDAO {
	
	private SessionFactory factory;
	private Session session;
	
	public UserDAO() {
		factory = new Configuration().configure().buildSessionFactory();
		session = factory.openSession();
	}
	
	public void insertUser(User user) {
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
	
	public List<User> findUserByName(String name) {
		List<User> users = session.
				createQuery("from User where name = :name").
				setParameter("name", name).
				list();
		return users;
	}
	
	public void close() {
		session.close();
		factory.close();
	}
}
