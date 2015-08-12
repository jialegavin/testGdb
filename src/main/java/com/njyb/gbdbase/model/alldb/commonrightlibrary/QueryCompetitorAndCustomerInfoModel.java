package com.njyb.gbdbase.model.alldb.commonrightlibrary;

import java.io.Serializable;

/**
 * 竞争对手<br>
 * 客户信息<br>
 * 共用的查询条件Model
 * @author WangBo
 * 2015年4月7日
 * QueryCompetitorAndCustomerInfoModel.java
 */
public abstract class QueryCompetitorAndCustomerInfoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//国家名称
	private String countryName;
	
	private String companyName;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}