package com.dev.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteNotes
 */
@WebServlet("/deleteNotes")
public class DeleteNotes extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sql = "update notes set Notes = '' where UserId = ? and NoteId = ?;";
		String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
		String username = "root";
		String pass = "password";
		HttpSession session = request.getSession();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, pass);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, String.valueOf(session.getAttribute("uid")));
			st.setString(2, String.valueOf(session.getAttribute("currentNoteId")));
			int rs = st.executeUpdate();
			response.sendRedirect("NoteTaking.jsp");
			if(rs > 0)
				System.out.println("Sucessfully deleted");
			else
				System.out.println("Deletion failed!!");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
