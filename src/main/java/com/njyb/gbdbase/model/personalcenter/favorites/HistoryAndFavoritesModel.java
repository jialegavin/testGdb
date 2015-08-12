package com.njyb.gbdbase.model.personalcenter.favorites;

import java.io.Serializable;

import com.njyb.gbdbase.model.personalcenter.CommonTotalModel;

/**
 * 历史查询记录 && 我的收藏<br>
 * 共用MODEL
 * @author WangBo 
 * @date 2015年4月1日 FavoritesModel.java
 */
public class HistoryAndFavoritesModel extends CommonTotalModel implements Serializable {

	private static final long serialVersionUID = 1L;
	// 查询的时间
	private String querytime;
	// 主键id
	private int id;
	// 对于的用户的id
	private int userId;
	// 给用户看的查询条件
	private String queryKey;
	// 后台识别的查询条件
	private String queryValue;
	// 所属的国家
	private String country;
	// 英文国家
	private String enCountry;
	// 保存条件名
	private String name;
	// 给用户看的查询条件
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 用户保存收藏时间
	private String saveTime;
	// 用户删除Id
	private String delId;
	
	public HistoryAndFavoritesModel(String querytime, int userId,
			String queryKey, String queryValue, String country, String name,
			String startTime, String endTime, String saveTime) {
		super();
		this.querytime = querytime;
		this.userId = userId;
		this.queryKey = queryKey;
		this.queryValue = queryValue;
		this.country = country;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.saveTime = saveTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HistoryAndFavoritesModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HistoryAndFavoritesModel(String queryKey, String queryValue) {
		super();
		this.queryKey = queryKey;
		this.queryValue = queryValue;
	}

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

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getQuerytime() {
		return querytime;
	}

	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSaveTime() {
		return saveTime;
	}

	public String getEnCountry() {
		return enCountry;
	}

	public void setEnCountry(String enCountry) {
		this.enCountry = enCountry;
	}

	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
}