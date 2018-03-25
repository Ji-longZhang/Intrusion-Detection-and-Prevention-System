package com.doublegaurd.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.doublegaurd.bean.AppRolePrivilegesBean;
import com.doublegaurd.bean.ApplicationsBean;
import com.doublegaurd.bean.AttackBean;
import com.doublegaurd.bean.PrivilegesBean;
import com.doublegaurd.bean.UserRoleBean;
import com.doublegaurd.util.APPConstants;
import com.doublegaurd.util.HibernateUtil;

public class ApplicationService {
	public boolean addApplication(ApplicationsBean application) {
		boolean flag = false;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();
			session.save(application);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}
	
	public boolean addAppUserRole(UserRoleBean userrole) {
		boolean flag = false;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();
			session.save(userrole);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}


	public ApplicationsBean getApplicationDetails(String appId) {
		List<ApplicationsBean> appList = new ArrayList<ApplicationsBean>();
		ApplicationsBean app = null;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("from ApplicationsBean where appId="
							+ appId );
			appList = query.list();
			if(null != appList && !appList.isEmpty()){
				app = appList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return app;
	}
	
	
	
	public List<ApplicationsBean> getUserApplications(String userName) {
		List<ApplicationsBean> appList = new ArrayList<ApplicationsBean>();
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("from ApplicationsBean where userName='"
							+ userName + "'");
			appList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return appList;
	}
	
	public List<ApplicationsBean> getApplications() {
		List<ApplicationsBean> appList = new ArrayList<ApplicationsBean>();
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("from ApplicationsBean");
			appList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return appList;
	}
	
	
	public List<PrivilegesBean> getUserApplicationPrivileges(String appId) {
		List<PrivilegesBean> appPrvgList = new ArrayList<PrivilegesBean>();
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("from PrivilegesBean where applicationId="
							+ appId );
			appPrvgList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return appPrvgList;
	}
	
	public List<UserRoleBean> getUserApplicationUserRoles(String appId) {
		List<UserRoleBean> appUserList = new ArrayList<UserRoleBean>();
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("from UserRoleBean where applicationId="
							+ appId );
			appUserList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return appUserList;
	}

	public boolean addAppPrivileges(PrivilegesBean privilegesBean) {
		boolean flag = false;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();
			privilegesBean.setMappedURL("");
			session.save(privilegesBean);
			//transaction.commit();
			
			System.out.println(privilegesBean.getPrivilegeId());
			String mappedUrl="http://"+APPConstants.SERVER_IP_ADDRESS+":8080/IDPS/RequestProcessor?action="+privilegesBean.getPrivilegeId();
			
			privilegesBean.setMappedURL(mappedUrl);
			session.saveOrUpdate(privilegesBean);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	public boolean addRoleToPrivileges(
			AppRolePrivilegesBean appRolePrivilegesBean) {
		boolean flag = false;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();
			session.save(appRolePrivilegesBean);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	public PrivilegesBean getUserAppPrivileges(String privilegeId) {
		List<PrivilegesBean> appPrvgList = new ArrayList<PrivilegesBean>();
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("from PrivilegesBean where privilegeId="
							+ privilegeId );
			appPrvgList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if(appPrvgList.isEmpty())
				return null;
		else
		return appPrvgList.get(0);
	}

	public boolean addAttackInfo(AttackBean attack) {
		boolean flag = false;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();
			session.save(attack);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
		
	}
	
	public List<AttackBean> getAttacks(String userName) {
		List<AttackBean> attackList = null;
		final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		final Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("from AttackBean where application.userName='"
							+ userName +"'");
			attackList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return attackList;
	}
}
