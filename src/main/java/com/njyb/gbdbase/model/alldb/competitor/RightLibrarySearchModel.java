package com.njyb.gbdbase.model.alldb.competitor;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.BusinessType;

/**
 * 市场分析报告查询Model {共用}
 * @author WangBo
 * 2015年4月13日
 * MarketAnalysisReportSearchModel.java
 */
public class RightLibrarySearchModel  implements Serializable {
	private static final long serialVersionUID = 1L;
	//产品描述
	private String goodsdescription;
	//海关编码
	private String hscode;
	//开始时间
	private String beginDateFlex;
	//结束时间
	private String endDateFlex;
	//报表的类型
	private BusinessType flexType;
	//选择的国家
	private String countrySelect;
	//排序的字段
	private String oldName;
	//国家名称
	private String countryName;
	//贸易类型
	private String tradeType;
	//公司名称
	private String companyName;
	//进口商
	private String importer;
	//出口商
	private String exporter;
	//同环比类型
	private String widthType;
	private String sort;
	
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
	public String getGoodsdescription() {
		return goodsdescription;
	}
	public void setGoodsdescription(String goodsdescription) {
		this.goodsdescription = goodsdescription;
	}
	public String getBeginDateFlex() {
		return beginDateFlex;
	}
	public void setBeginDateFlex(String beginDateFlex) {
		this.beginDateFlex = beginDateFlex;
	}
	
	public String getEndDateFlex() {
		return endDateFlex;
	}
	public void setEndDateFlex(String endDateFlex) {
		this.endDateFlex = endDateFlex;
	}
	public BusinessType getFlexType() {
		return flexType;
	}
	public void setFlexType(BusinessType flexType) {
		this.flexType = flexType;
	}
	public String getCountrySelect() {
		return countrySelect;
	}
	public void setCountrySelect(String countrySelect) {
		this.countrySelect = countrySelect;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getImporter() {
		return importer;
	}
	public void setImporter(String importer) {
		this.importer = importer;
	}
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getWidthType() {
		return widthType;
	}
	public void setWidthType(String widthType) {
		this.widthType = widthType;
	}
}