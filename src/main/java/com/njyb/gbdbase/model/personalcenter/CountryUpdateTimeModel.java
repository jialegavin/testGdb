package com.njyb.gbdbase.model.personalcenter;

import java.io.Serializable;

/**
 * 各国数据更新时间实体bean
 * @author honghao 由于业务变更,重写 wangB
 */
public class CountryUpdateTimeModel  implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键编号
	private int id;
	// 国家中文名称
	private String chCountryName;
	// 国家英文名称
	private String ehCountryName;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChCountryName() {
		return chCountryName;
	}
	public void setChCountryName(String chCountryName) {
		this.chCountryName = chCountryName;
	}
	public String getEhCountryName() {
		return ehCountryName;
	}
	public void setEhCountryName(String ehCountryName) {
		this.ehCountryName = ehCountryName;
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
}