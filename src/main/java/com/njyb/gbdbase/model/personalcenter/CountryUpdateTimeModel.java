package com.njyb.gbdbase.model.personalcenter;

import java.io.Serializable;

/**
 * 各国数据更新时间实体bean
 * 
 * @author honghao
 * 
 */
public class CountryUpdateTimeModel  implements Serializable {
	//主键编号
	private int id;
	//国家名称
	private String countryName;
	//数据最新更新时间
	private String updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
