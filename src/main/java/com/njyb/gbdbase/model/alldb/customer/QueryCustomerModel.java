package com.njyb.gbdbase.model.alldb.customer;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.QueryCompetitorAndCustomerInfoModel;

/**
 * 客户信息查询Model
 * 
 * @author WangBo 2015年4月8日 QueryCustomerModel.java
 */
public class QueryCustomerModel extends QueryCompetitorAndCustomerInfoModel
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 客户名称
	private String customerName;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}