package com.dev;

public class UserWrapper {

	private int userId;
	private String userName;
	private String password;
	private boolean isUserPresent;


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isUserPresent() {
		return isUserPresent;
	}


	public void setUserPresent(boolean isUserPresent) {
		this.isUserPresent = isUserPresent;
	}
	
	
}
