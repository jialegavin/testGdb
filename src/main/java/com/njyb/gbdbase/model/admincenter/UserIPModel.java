package com.njyb.gbdbase.model.admincenter;

public class UserIPModel {
	// 用户登录编号
	private int userId;
	// 用户登录名称
	private String loginName;
	// 用户真实姓名
	private String userName;
	//ip的id
	private int ipid;
	//绑定的IP地址
	private String ipaddress;
	//是否拦截
	private String ischeck;
	//ip所属地
	private String ipTerritory;
	public String getIpTerritory() {
		return ipTerritory;
	}
	public void setIpTerritory(String ipTerritory) {
		this.ipTerritory = ipTerritory;
	}
	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIpid() {
		return ipid;
	}
	public void setIpid(int ipid) {
		this.ipid = ipid;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getIscheck() {
		return ischeck;
	}
	public void setIscheck(int check) {
		if(check==0){
			this.ischeck = "是";
		}else{
			this.ischeck = "否";
		}
	}
}
