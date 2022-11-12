package com.dev.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteNoteServlet
 */
@WebServlet("/DeleteNoteServlet")
public class DeleteNoteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		int noteId = Integer.parseInt(request.getParameter("delBtn"));
		String sql = "Delete from notes where UserId = ? and NoteId = ?";
		String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
		String username = "root";
		String pass = "password";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, pass);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, (int)session.getAttribute("uid"));
			st.setInt(2, noteId);
			int rs = st.executeUpdate();
			if(rs > 0)
				System.out.println("Sucessfully deleted");
			else
				System.out.println("Deletion failed!!");
			sql = "Select NoteId from notes where UserId = "+(int)session.getAttribute("uid");
			response.sendRedirect("AllNotes.jsp");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
