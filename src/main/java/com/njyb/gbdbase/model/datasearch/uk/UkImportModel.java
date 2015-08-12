package com.njyb.gbdbase.model.datasearch.uk;
import java.io.Serializable;

@SuppressWarnings("serial")
public class UkImportModel implements Serializable {

	/**
	 * 
	 */
	private Integer ukImportId;

	/**
	 * 公司名称(进口商)
	 */
	private String companyName;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * 产品描述
	 */
	private String goodsDesc;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 邮政编码
	 */
	private String postCode;

	/**
	 * 日期(monthly)
	 */
	private String date;
	private Double num = 1.0;
	public Integer getUkImportId() {
		return ukImportId;
	}
	public void setUkImportId(Integer ukImportId) {
		this.ukImportId = ukImportId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

