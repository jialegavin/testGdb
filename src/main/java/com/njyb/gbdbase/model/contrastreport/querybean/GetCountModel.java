package com.njyb.gbdbase.model.contrastreport.querybean;

import java.io.Serializable;

/**
 * 统计各国国家每月新增流失进出口商个数
 * @author 章华才
 * 2015-03-30
 */
@SuppressWarnings("serial")
public class GetCountModel implements Serializable{

	private String dateName;
	private Integer dateCount;
	
	public GetCountModel(String dateName, Integer dateCount) {
		super();
		this.dateName = dateName;
		this.dateCount = dateCount;
	}
	public String getDateName() {
		return dateName;
	}
	public void setDateName(String dateName) {
		this.dateName = dateName;
	}
	public Integer getDateCount() {
		return dateCount;
	}
	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}
}
