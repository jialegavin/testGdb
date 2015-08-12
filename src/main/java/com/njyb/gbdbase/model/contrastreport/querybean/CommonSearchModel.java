package com.njyb.gbdbase.model.contrastreport.querybean;

public class CommonSearchModel {

	//海关编码
	private String hscode;
	//产品描述
	private String goodDesc;
	//第一段开始时间
	private String beginDate;
	//第一段结束时间
	private String endDate;
	//第二段开始时间
	private String beginAddDate;
	//第二段结束时间
	private String endAddDate;
	//日期
	private String firstDate;
	
	//针对中国-韩国-英国
	private String tradeType;
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public String getGoodDesc() {
		return goodDesc;
	}
	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBeginAddDate() {
		return beginAddDate;
	}
	public void setBeginAddDate(String beginAddDate) {
		this.beginAddDate = beginAddDate;
	}
	public String getEndAddDate() {
		return endAddDate;
	}
	public void setEndAddDate(String endAddDate) {
		this.endAddDate = endAddDate;
	}
	public String getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}
	
	
}
