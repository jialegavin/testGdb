package com.njyb.gbdbase.model.alldb.projectAnalyze;

import java.io.Serializable;
/**
 * 产品标签<br>
 * 买家实体类
 * @author WangBo
 * 2015年4月27日
 * BuyerModel.java
 */
public class BuyerModel implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private int id;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 产品描述
	 */
	private String goodsDescription;
	/**
	 * 海关编码
	 */
	private String hscode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGoodsDescription() {
		return goodsDescription;
	}
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}