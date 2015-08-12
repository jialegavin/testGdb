package com.njyb.test.jiahongping;

public class PersonBean {
	private String username;
	private String pwd;
	private String like;
	public PersonBean(String username, String pwd, String like) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.like = like;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
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
	public PersonBean(){}
	public static void main(String[] args) {
		
	}
}
