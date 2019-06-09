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
		//in hibernate use name of the class in sql query statement
		//instead of table name
		List users = session.createQuery("from User where name = :name")
				.setParameter("name", name).list();
		return users;
	}
	
	public void update(User user) {
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
	}
	
	public void close() {
		session.close();
		factory.close();
	}
}
