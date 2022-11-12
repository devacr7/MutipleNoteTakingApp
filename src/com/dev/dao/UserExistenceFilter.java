package com.dev.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserExistenceFilter
 */
//@WebFilter("/signup")
public class UserExistenceFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, NullPointerException {
		
		HttpServletRequest servReq = (HttpServletRequest)request;
		HttpServletResponse servRes = (HttpServletResponse)response;
		HttpSession session = servReq.getSession();
		String uname = "";
		String password = "";
		if(session.getAttribute("requestType").equals("signup")) {
			uname = servReq.getParameter("signupName");
			password = servReq.getParameter("signupPass");
		}
		else if(session.getAttribute("requestType").equals("login")) {
			uname = servReq.getParameter("uname");
			password = servReq.getParameter("pass");
		}
		String sql = "select * from user where Username = ?";
		String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
		String username = "root";
		String pass = "password";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, pass);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uname);
			ResultSet rs = st.executeQuery();

			if(!rs.next()) {
				if(session.getAttribute("requestType").equals("login")) {
					session.setAttribute("ErrorMsg", "No User with this Username found!!!");
					servRes.sendRedirect("Alert.jsp");
				}
				else if(session.getAttribute("requestType").equals("signup")) {
					// pass the request along the filter chain
					chain.doFilter(request, response);
				}
			}
			else {
				if(session.getAttribute("requestType").equals("login")) {
					if(!rs.getString("Password").equals(password)) {
						session.setAttribute("ErrorMsg", "Wrong password!!!");
						servRes.sendRedirect("Alert.jsp");
					}
					else {
						// pass the request along the filter chain
						chain.doFilter(request, response);
					}
				}
				else if(session.getAttribute("requestType").equals("signup")) {
					session.setAttribute("ErrorMsg", "User already exists!!!");
					servRes.sendRedirect("Alert.jsp");
				}
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
