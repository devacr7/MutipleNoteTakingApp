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
 * Servlet implementation class SaveNotes
 */
@WebServlet("/saveNotes")
public class SaveNotes extends HttpServlet {
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("notes", request.getParameter("notes"));
		session.setAttribute("noteName", request.getParameter("noteName"));
		if(!(boolean)session.getAttribute("isNewNote") == true) {
			String sql = "update notes set Notes = ?, NoteName = ? where UserId = ? and NoteId = ?;";
			String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
			String username = "root";
			String pass = "password";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, pass);
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, request.getParameter("notes"));
				st.setString(2, request.getParameter("noteName"));
				st.setString(3, String.valueOf(session.getAttribute("uid")));
				st.setString(4, String.valueOf(session.getAttribute("currentNoteId")));
				int rs = st.executeUpdate();
				System.out.println(String.valueOf(session.getAttribute("currentNoteId")));
				response.sendRedirect("NoteTaking.jsp");
				if(rs > 0)
					System.out.println("Sucessfully updated");
				else
					System.out.println("Updation failed!!");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			String sql = "Insert into notes (UserId, NoteName, Notes) values(?, ?, ?)";
			String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
			String username = "root";
			String pass = "password";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, pass);
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, (int)session.getAttribute("uid"));
				st.setString(2, request.getParameter("noteName"));
				st.setString(3, request.getParameter("notes"));
				int rs = st.executeUpdate();
				System.out.println(String.valueOf(session.getAttribute("currentNoteId")));
				if(rs > 0)
					System.out.println("Sucessfully Inserted");
				else
					System.out.println("Insertion failed!!");
				sql = "Select NoteId from notes where UserId = "+(int)session.getAttribute("uid");
				PreparedStatement st1 = con.prepareStatement(sql);
				ResultSet rs1 = st1.executeQuery();
				List<Integer> noteIdList = new ArrayList<>();
				while(rs1.next()) {
					
					noteIdList.add(rs1.getInt("NoteId"));
				}
				session.setAttribute("currentNoteId", noteIdList.get(noteIdList.size()-1));
				session.setAttribute("isNewNote", false);
				response.sendRedirect("NoteTaking.jsp");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
