package com.controler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.Constants;

/**
 * Servlet implementation class NewEmpServlet
 */
public class NewEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewEmpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		register(request);
		response.sendRedirect("empmessage.jsp");
	}

	public void register(HttpServletRequest request) {
		String name = request.getParameter("fullName");
		String email = request.getParameter("email");
		String salary = request.getParameter("salary");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		Connection conConnection = null; // database connection
		Statement stStatement = null; // Statement object
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conConnection = DriverManager
					.getConnection(Constants.DB_URL,
							"root", "root");
			stStatement = conConnection.createStatement();
			stStatement.executeUpdate("Insert into User(name, email, salary, userName, password) values('"+name+"','"+email+"','"+salary+"','"+userName+"','"+password+"')");
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
	}

}
