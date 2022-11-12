package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.UserWrapper;

public class LoginDAO {

	
	public UserWrapper checkLogin(String uname, String password) throws ClassNotFoundException, SQLException {
		
		String sql = "select * from user where Username = ? and Password = ?";
		String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
		String username = "root";
		String pass = "password";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, pass);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, uname);
		st.setString(2, password);	
		ResultSet rs = st.executeQuery();
		return buildWrapper(rs);
	}
	
	public UserWrapper buildWrapper(ResultSet rs) throws SQLException {
		
		UserWrapper uw = new UserWrapper();
		if(rs.next()) {
			uw.setUserId(rs.getInt("UserId"));
			uw.setUserName(rs.getString("UserName"));
			uw.setPassword(rs.getString("Password"));
			uw.setUserPresent(true);
		}
		else {
			uw.setUserPresent(false);
		}
		
		return uw;
	}
	
	public int[] getNoteIdList(int uid) throws SQLException, ClassNotFoundException {
		
		String sql = "select NoteId from notes where UserId = ?";
		String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
		String username = "root";
		String pass = "password";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, pass);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, uid);	
		ResultSet rs = st.executeQuery();
		
		List<Integer> noteIdList = new ArrayList<>();
		
		while(rs.next()) {
			noteIdList.add(rs.getInt("NoteId"));
		}
		
		int idArr[] = new int[noteIdList.size()];
		for(int i=0;i<noteIdList.size();i++) {
			idArr[i] = noteIdList.get(i);
		}
		return idArr;
	}

	
public String[] getNoteNameList(int uid) throws SQLException, ClassNotFoundException {
		
		String sql = "select NoteName from notes where UserId = ?";
		String url = "jdbc:mysql://localhost:3306/multiplenotetaking";
		String username = "root";
		String pass = "password";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, pass);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, uid);	
		ResultSet rs = st.executeQuery();
		
		List<String> noteNameList = new ArrayList<>();
		
		while(rs.next()) {
			noteNameList.add(rs.getString("NoteName"));
		}
		
		String nameArr[] = new String[noteNameList.size()];
		for(int i=0;i<noteNameList.size();i++) {
			nameArr[i] = noteNameList.get(i);
		}
		return nameArr;
	}
}


