package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveNotesFromDB {
	
	public String[] retrieveNotes(int noteId) throws SQLException, ClassNotFoundException {
		
		String sql = "select Notes, NoteName from notes where NoteId = ?";
		String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
		String username = "root";
		String pass = "password";
		String notes = "";
		String name = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, pass);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, noteId);	
			ResultSet rs = st.executeQuery();
			rs.next();
			notes = rs.getString("Notes");
			name = rs.getString("NoteName");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Notes : "+notes);
		return new String[]{notes, name};
	}

}
