package com.dev;

import java.io.IOException;
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

/**
 * Servlet Filter implementation class EmptyRecordSaveFilter
 */
@WebFilter("/saveNotes")
public class EmptyRecordSaveFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Empty notes filter entry!!!");
		HttpServletRequest servReq = (HttpServletRequest)request;
		HttpServletResponse servRes = (HttpServletResponse)response;
		HttpSession session = servReq.getSession();
		String notes = request.getParameter("notes");
		String noteName = request.getParameter("noteName");
		System.out.println("size-->"+notes+"<--");
		if(noteName.equals("")) {
			session.setAttribute("ErrorMsg", "Please provide a Note name!!!");
			servRes.sendRedirect("Alert.jsp");
		}
		else {
			System.out.println("chained to saveNotes");
			chain.doFilter(request, response);
		}
	}

}
