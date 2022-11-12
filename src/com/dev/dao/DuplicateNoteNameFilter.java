package com.dev.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.UserWrapper;

/**
 * Servlet Filter implementation class DuplicateNoteNameFilter
 */
@WebFilter("/saveNotes")
public class DuplicateNoteNameFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			HttpServletRequest servReq = (HttpServletRequest)request;
			HttpServletResponse servRes = (HttpServletResponse)response;
			HttpSession session = servReq.getSession();
			String uname = (String)session.getAttribute("uname");
			String pass = (String)session.getAttribute("pass");
			LoginDAO loginDAO = new LoginDAO();
			UserWrapper uw = loginDAO.checkLogin(uname, pass);
			String[] noteNameList = loginDAO.getNoteNameList(uw.getUserId());
			session.setAttribute("noteNameList", noteNameList);
			String currNoteName = request.getParameter("noteName");
			boolean isDuplicate = false;
			System.out.println ("dup list "+Arrays.toString(noteNameList));
			System.out.println("currNoteName "+currNoteName);
			for(String str : noteNameList) {
				if(str.equalsIgnoreCase(currNoteName)) {
					isDuplicate = true;
				}
			}
			
			//Retrieving current note details from DB.
			String[] notes = new String[2];
			String storedNotes = "";
			String storedName = "";
			if(!(boolean)session.getAttribute("isNewNote") == true) {
				int noteId = (int)session.getAttribute("currentNoteId");
				session.setAttribute("currentNoteId", noteId);
				//Retrieving data form DB to show the notes in all scenarions.
				RetrieveNotesFromDB notesDB = new RetrieveNotesFromDB();
				notes = notesDB.retrieveNotes(noteId);
				storedNotes = notes[0];
				storedName = notes[1];
			}
			if(isDuplicate && ((boolean)session.getAttribute("isNewNote") == true || ((boolean)session.getAttribute("isNewNote") == false && (!request.getParameter("noteName").equals(storedName))))) {
				session.setAttribute("ErrorMsg", "Note Name already exists!!!");
				servRes.sendRedirect("Alert.jsp");
			}
			else {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
