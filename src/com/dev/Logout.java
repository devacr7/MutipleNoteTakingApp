package com.dev;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("uid");
		session.removeAttribute("uname");
		session.removeAttribute("pass");
		session.removeAttribute("currentNoteId");
		session.removeAttribute("notesList");
		session.removeAttribute("isNewNote");
		session.removeAttribute("noteIdList");
		session.removeAttribute("noteNameList");
		//		System.out.println(session.getAttribute("uname"));
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}


}
