package com.njyb.gbdbase.model.login;

import java.io.Serializable;

import com.njyb.gbdbase.model.personalcenter.CommonTotalModel;

/**
 * 用户登录日志表实体类
 * @author honghao
 * <br>WangBo 2015-03-27 添加了2个属性
 * 2015-03-10
 */
public class UserLoginlogModel extends CommonTotalModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//登录日志的用户真实id
	private int userId;
	//登录日志的用户名
	private String loginName;
	//登录日志的时间
	private String loginTime;
	//登录日志的用户ip
	private String ipAddress;
	//开始时间
	private String beginDate;
	//结束时间
	private String endDate;
	
	//WangBo 添加到
	private int id;					//Id
	
	private String IpAttribution;	//IP归属地

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

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpAttribution() {
		return IpAttribution;
	}

	public void setIpAttribution(String ipAttribution) {
		IpAttribution = ipAttribution;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
