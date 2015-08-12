package com.njyb.test.jiahongping;

public class UserBean {
	public UserBean(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	private String username;
	private String pwd;
	
	public UserBean(){}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
