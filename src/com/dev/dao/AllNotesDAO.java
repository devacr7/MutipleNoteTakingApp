package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dev.NotesWrapper;

public class AllNotesDAO {
	
	public List<NotesWrapper> getUserNotes(int userId) {
		
		String sql = "select * from notes where UserId = ?";
		String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
		String username = "root";
		String pass = "password";
		String notes = "";
		List<NotesWrapper> notesList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, pass);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				
				NotesWrapper nw = new NotesWrapper();
				nw.setUserId(userId);
				nw.setNotesId(rs.getInt("NoteId"));
				nw.setNoteName(rs.getString("NoteName"));
				nw.setNotes(rs.getString("Notes"));
				notesList.add(nw);
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notesList;
	}

}
