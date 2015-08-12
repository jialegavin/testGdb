package com.njyb.gbdbase.model.personalcenter.password;

import java.io.Serializable;
import java.util.Date;
/**
 * 密码找回
 * @author WangBo
 */
public class EmailModel implements PasswordCommon, Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;
	
	private String sId;
	
	private Date sendTime;
	
	private int emailCount;
	
	private int emailId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getEmailCount() {
		return emailCount;
	}

	public void setEmailCount(int emailCount) {
		this.emailCount = emailCount;
	}

	public int getEmailId() {
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}
	
}