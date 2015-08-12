package com.njyb.gbdbase.model.personalcenter.password;

import java.io.Serializable;

/**
 * 用户密码 Bean
 * @author WangBo
 *
 */
public class PasswordModel implements PasswordCommon,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//原密码
	private String oldPassword;
	
	//新密码
	private String newPassword;
	
	//是否更新
	private boolean isUpdate;
	
	//是否召回
	private boolean isFind;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public boolean isFind() {
		return isFind;
	}

	public void setFind(boolean isFind) {
		this.isFind = isFind;
	}
}