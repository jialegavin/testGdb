package com.njyb.gbdbase.model.personalcenter;

import java.io.Serializable;

/**
 * 历史查询记录表
 * @author jcy
 *
 */
public class HistoryRecModel implements Serializable{
	//历史查询记录表的id
	private int id;
	//对于的用户的id
	private int userId;
	//查询的时间
	private String querytime;
	//给用户看的查询条件
	private String querykey;
	//后台识别的查询条件
	private String queryvalue;
	//开始查询的时间
	private String queryStartTime;
	//查询结束时间
	private String queryEndTime;
	//所属的国家
	private String country;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getQuerytime() {
		return querytime;
	}
	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}
	public String getQuerykey() {
		return querykey;
	}
	public void setQuerykey(String querykey) {
		this.querykey = querykey;
	}
	public String getQueryvalue() {
		return queryvalue;
	}
	public void setQueryvalue(String queryvalue) {
		this.queryvalue = queryvalue;
	}
	public String getQueryStartTime() {
		return queryStartTime;
	}
	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}
	public String getQueryEndTime() {
		return queryEndTime;
	}
	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
