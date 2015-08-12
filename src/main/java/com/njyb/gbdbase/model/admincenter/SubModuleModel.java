package com.njyb.gbdbase.model.admincenter;

import java.io.Serializable;

/**
 * 子模块权限的model
 * @author 章华才
 */
public class SubModuleModel implements Serializable{

	
	/**
	 * 子模块Id
	 */
	private Integer submid;
	/**
	 * 主模块Id
	 */
	private int mainid;
	/**
	 * 子模块的名称
	 */
	private String subname;
	/**
	 * 访问权限路径
	 */
	private String urlname;
	/**
	 * 访问权限方法名称
	 */
	private String mentodname;
	/**
	 * 权限说明
	 */
	private String hasright;
	/**
	 * 登录用户名称
	 */
	private String loginame;
	public Integer getSubmid() {
		return submid;
	}
	public void setSubmid(Integer submid) {
		this.submid = submid;
	}
	public int getMainid() {
		return mainid;
	}
	public void setMainid(int mainid) {
		this.mainid = mainid;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public String getUrlname() {
		return urlname;
	}
	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
	public String getMentodname() {
		return mentodname;
	}
	public void setMentodname(String mentodname) {
		this.mentodname = mentodname;
	}
	public String getHasright() {
		return hasright;
	}
	public void setHasright(String hasright) {
		this.hasright = hasright;
	}
	public String getLoginame() {
		return loginame;
	}
	public void setLoginame(String loginame) {
		this.loginame = loginame;
	}
	
}
