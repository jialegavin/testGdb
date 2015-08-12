package com.njyb.gbdbase.model.admincenter;

import java.io.Serializable;

/**
 * 用户购买的id  Model
 * @author WangBo
 * 2015年6月26日
 * UserCountBuyerModel.java
 */
public class UserCountBuyerModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String countryEn;		// 英文名称
	
	private String countryCn;		// 中文名称
	
	private int countryId;			// 国家id

	public String getCountryEn() {
		return countryEn;
	}

	public void setCountryEn(String countryEn) {
		this.countryEn = countryEn;
	}

	public String getCountryCn() {
		return countryCn;
	}

	public void setCountryCn(String countryCn) {
		this.countryCn = countryCn;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
}