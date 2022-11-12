package com.dev.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sql = "Insert into dev.note_taking (Username, Password, Notes) values(?, ?, ?)";
		String url = "jdbc:mysql://localhost:3306/dev";
		String username = "root";
		String pass = "password";
		HttpSession session = request.getSession();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, pass);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, String.valueOf(request.getParameter("signupName")));
			st.setString(2, String.valueOf(request.getParameter("signupPass")));
			st.setString(3, "");
			int rs = st.executeUpdate();
			
			if(rs > 0) {
				System.out.println("User created successfully");
				response.sendRedirect("CreateAccountMsg.jsp");
			}
			else
				System.out.println("User creation failed!!");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
