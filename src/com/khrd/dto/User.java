package com.khrd.dto;

public class User {
	String uId;
	String uName;
	String uPassword;
	
	public User() {}
	
	public User(String uId, String uName, String uPassword) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uPassword = uPassword;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uPassword=" + uPassword + "]";
	}	
}
