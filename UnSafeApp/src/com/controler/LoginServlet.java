package com.controler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constants.Constants;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("uname");
		String password=request.getParameter("password");
		String empType = request.getParameter("empType");
		if(empType.equals("User")) {
			if(validateUser(userName, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("uname", userName);
				session.setAttribute("userType", empType);
				response.sendRedirect("home2.jsp");
			} else {
				
				response.sendRedirect("loginFailed.jsp");
			}
		}
		else{
			if(userName.equals("admin") && password.equals("admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("uname", userName);
				session.setAttribute("userType", empType);
				response.sendRedirect("home.jsp");
			} else {
				
				response.sendRedirect("loginFailed.jsp");
			}
		}
		
		
	}
	
	public boolean validateUser(String uName, String password) {
		boolean flag= false;
		Connection conConnection = null; // database connection
		Statement stStatement = null; // Statement object
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conConnection = DriverManager
					.getConnection(Constants.DB_URL,
							"root", "root");
			stStatement = conConnection.createStatement();
			String query="Select * from User where userName='"+uName+"' and password='"+password+"'";
			System.out.println(query);
			ResultSet rs = stStatement.executeQuery(query);
			if(rs.next()){
				flag=true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(stStatement!=null){
					stStatement.close();
				}
				if(conConnection!=null) {
					conConnection.close();
				}
			} catch (Exception e) {
			}
		}
		return flag;
	}

}
