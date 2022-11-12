package com.dev;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.dao.LoginDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		try {
			LoginDAO loginDAO = new LoginDAO();
			UserWrapper uw = loginDAO.checkLogin(uname, pass);
			System.out.println(uw.isUserPresent());
			if(uw.isUserPresent()) {
				HttpSession session = request.getSession();
				session.setAttribute("uid", uw.getUserId());
				session.setAttribute("uname", uname);
				session.setAttribute("pass", pass);
				int[] noteIdList = loginDAO.getNoteIdList(uw.getUserId());
				session.setAttribute("noteIdList", noteIdList);
				response.sendRedirect("AllNotes.jsp");
			}
			else {
				response.sendRedirect("Login.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
