package com.dev;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AllNotes
 */
@WebServlet("/setNoteId")
public class AllNotes extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noteId = Integer.parseInt(request.getParameter("notesBtn"));
		HttpSession session = request.getSession();
		session.setAttribute("isNewNote", false);
		session.setAttribute("currentNoteId", noteId);
		response.sendRedirect("NoteTaking.jsp");
	}

}
