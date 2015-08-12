package com.njyb.gbdbase.model.contrastreport.customercenter;

import java.util.Date;


/**
 * 客服中心
 * @author 章华才
 *
 */
public class CustomerCenterModel {

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 用户电话
	 */
	private String tel;
	
	/**
	 * 用户留言
	 */
	private String centent;

	/**
	 * 时间
	 */
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCentent() {
		return centent;
	}

	public void setCentent(String centent) {
		this.centent = centent;
	}
}
