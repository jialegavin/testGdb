package com.njyb.gbdbase.model.admincenter;

/**
 * 用户系统日志
 * @author chenhu
 * 2015年3月27日
 */
public class UserSystemLogModel {
	/**
	 * 系统日志
	 */
   private int logId;
   /**
    * 日志时间
    */
   private String logTime;
   /**
    * 日志行为
    */
   private String country;
   /**
    * 用户名
    */
   private String userName;
   /**
    * 登录名
    */
   private String loginName;
   /**
    * 执行参数列表
    */
   private String parameter;
   /**
    * 方法描述
    */
   private String logDesc;
   /**
    * 用户ID
    */
   private int    userId;
   public int getLogId() {
	return logId;
}
public void setLogId(int logId) {
	this.logId = logId;
}
public String getLogTime() {
	return logTime;
}
public void setLogTime(String logTime) {
	this.logTime = logTime;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getParameter() {
	return parameter;
}
public void setParameter(String parameter) {
	this.parameter = parameter;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getLogDesc() {
	return logDesc;
}
public void setLogDesc(String logDesc) {
	this.logDesc = logDesc;
}
}
