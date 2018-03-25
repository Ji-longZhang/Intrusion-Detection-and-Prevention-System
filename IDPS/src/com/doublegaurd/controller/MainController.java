package com.doublegaurd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doublegaurd.bean.AppRolePrivilegesBean;
import com.doublegaurd.bean.ApplicationsBean;
import com.doublegaurd.bean.PrivilegesBean;
import com.doublegaurd.bean.UserBean;
import com.doublegaurd.bean.UserRoleBean;
import com.doublegaurd.service.ApplicationService;
import com.doublegaurd.service.UserService;
import com.doublegaurd.util.Emailer;

/**
 * Servlet implementation class for Servlet: MainController
 * 
 */
public class MainController extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	private void action(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		final String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if ("doLogin".equals(action)) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			UserService userService = new UserService();
			if (userService.validateLogin(userName, password)) {
				session.setAttribute("userName", userName);
				response.sendRedirect("home.jsp");
			} else {
				session.setAttribute("message", "Invalid User Name or password");
				response.sendRedirect("index.jsp");
			}
		} else if ("doRegister".equals(action)) {

			UserBean user = new UserBean();
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setEmail(request.getParameter("email"));
			user.setAddress(request.getParameter("address"));
			user.setBirthDate(request.getParameter("birthDate"));
			user.setMobile(request.getParameter("mobile"));
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			
			UserService userService = new UserService();
			if (userService.saveUser(user)) {
				session.setAttribute("message",
						"Register successful.. Please Login");
				response.sendRedirect("index.jsp");
			} else {
				session.setAttribute("message",
						"Error occured while register, try again later");
				response.sendRedirect("register.jsp");
			}
		} else if ("doforGetPwd".equals(action)) {
			String email = request.getParameter("emailId");
			UserService userService = new UserService();
			UserBean user = userService.getUserDetails(email);
			if (user == null) {
				session.setAttribute("message", "Invalid email..");
				response.sendRedirect("forgetpassword.jsp");
			} else {

				String[] toUser = new String[] { email };
				String subject = "Double Guard password recovery";
				String messageBody = "Dear User, <br> Your password details are as below: <br> <b> User Name : </b>"
						+ user.getUserName()
						+ " </br> <b>Password : </b>"
						+ user.getPassword();
				Emailer.sendEmail(toUser, subject, messageBody);
				
				session.setAttribute("message", "Password details sent on registered email");
				response.sendRedirect("forgetpassword.jsp");

			}

		} else if (action.equals("addapplication")) {

			ApplicationsBean application = new ApplicationsBean();
			application.setApplicationName(request
					.getParameter("applicationName"));
			application.setServerIpAddress(request
					.getParameter("serverIpAddress"));
			application.setServerPort(request.getParameter("serverPort"));
			application.setErrorPage(request.getParameter("errorPage"));
			application.setWelcomePage(request.getParameter("welcomePage"));
			String userName = (String) session.getAttribute("userName");
			application.setUserName(userName);
			ApplicationService applicationService = new ApplicationService();
			if (applicationService.addApplication(application)) {
				session.setAttribute("message",
						"Application added successfully..");
				response.sendRedirect("message.jsp");
			} else {
				session.setAttribute("message",
						"Error occured while adding application, try again later");
				response.sendRedirect("addapplication.jsp");
			}
			
		} else if (action.equals("addapplicationUser")) {
			UserRoleBean userrole = new UserRoleBean();
			userrole.setApplicationId(Integer.parseInt(request
					.getParameter("appName")));
			userrole.setUserRole(request.getParameter("userRole"));

			ApplicationService appService = new ApplicationService();
			if (appService.addAppUserRole(userrole)) {
				session.setAttribute("message",
						"Application User Roles added successfully..");
				response.sendRedirect("message.jsp");
			} else {
				session.setAttribute("message",
						"Error occured while adding application user role, try again later");
				response.sendRedirect("addappusers.jsp");
			}

		} else if (action.equals("addapplicationPrivileges")) {
			PrivilegesBean privilegesBean = new PrivilegesBean();
			privilegesBean.setPrivilegeName(request
					.getParameter("privilegeName"));
			privilegesBean
					.setPrivilegeURL(request.getParameter("privilegeURL"));
			privilegesBean.setApplicationId(Integer.valueOf(request
					.getParameter("appName")));
			ApplicationService appService = new ApplicationService();
			if (appService.addAppPrivileges(privilegesBean)) {
				session.setAttribute("message",
						"Application privileges added successfully..");
				response.sendRedirect("mapprivilegestousers.jsp");
			} else {
				session.setAttribute("message",
						"Error occured while adding application privileges, try again later");
				response.sendRedirect("mapprivilegestousers.jsp");
			}
		} else if (action.equals("addRoleToPrivileges")) {
			AppRolePrivilegesBean appRolePrivilegesBean = new AppRolePrivilegesBean();
			ApplicationsBean application = new ApplicationsBean();
			application
					.setAppId(Integer.valueOf(request.getParameter("appId")));

			appRolePrivilegesBean.setApplication(application);

			PrivilegesBean privilegesBean = new PrivilegesBean();
			privilegesBean.setPrivilegeId(Integer.valueOf(request
					.getParameter("privilegeName")));
			appRolePrivilegesBean.setPrivileges(privilegesBean);

			UserRoleBean userRole = new UserRoleBean();
			userRole.setUesrRoleId(Integer.valueOf(request
					.getParameter("userRoles")));
			appRolePrivilegesBean.setUserRole(userRole);
			ApplicationService appService = new ApplicationService();
			if (appService.addRoleToPrivileges(appRolePrivilegesBean)) {
				session.setAttribute("message",
						"Application Role assigned to privileges successfully..");
				response.sendRedirect("message.jsp");
			} else {
				session.setAttribute("message",
						"Error occured while adding role to privileges, try again later");
				response.sendRedirect("mapprivilegestousers.jsp");
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
}