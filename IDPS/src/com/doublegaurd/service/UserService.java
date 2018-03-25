package com.doublegaurd.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.doublegaurd.bean.UserBean;
import com.doublegaurd.util.Emailer;
import com.doublegaurd.util.HibernateUtil;

public class UserService {

	public boolean validateLogin(String userName, String password) {
		boolean flag = false;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from UserBean where userName='"
					+ userName + "' and password='" + password + "'");
			List<UserBean> loginFormList = query.list();
			if (null != loginFormList && !loginFormList.isEmpty()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}
	
	public UserBean getUser(String userName) {
		UserBean user = null;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from UserBean where userName='"
					+ userName +  "'");
			List<UserBean> loginFormList = query.list();
			if (null != loginFormList && !loginFormList.isEmpty()) {
				user = loginFormList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	public boolean saveUser(UserBean user) {
		boolean flag = false;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();
			session.save(user);
			// Committing the changes
			transaction.commit();
			flag = true;
			String toUser[] = new String[1];
			toUser[0] = "doublegaurdautoreply@gmail.com";
			String messageBody = getMessageBody(user);
			Emailer.sendEmail(toUser, "New User registered", messageBody);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	private String getMessageBody(UserBean user) {
		String message = "Dear Admin, <br> Below user registered to the system <br><br> Name : "
				+ user.getFirstName()
				+ " "
				+ user.getLastName()
				+ "<br> email : "
				+ user.getEmail()
				+ " Address : "
				+ user.getAddress();
		return message;
	}

	public UserBean getUserDetails(String email) {
		UserBean user = null;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from UserBean where email='"
					+ email + "'");
			List<UserBean> userList = query.list();
			user = userList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
}
