package com.njyb.gbdbase.model.datasearch.argentina;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ArgentinaExportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer argentinaExportId;

	/**
	 * 日
	 */
	private Integer day;

	/**
	 * 月
	 */
	private Integer month;

	/**
	 * 年
	 */
	private Integer year;

	/**
	 * 出口商编号
	 */
	private String exportId;

	/**
	 * 类型
	 */
	private String operType;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 目的国
	 */
	private String destCountry;

	/**
	 * 运输类型
	 */
	private String transType;

	/**
	 * 成交方式(国际贸易术语)
	 */
	private String incoterms;

	/**
	 * 包装数量
	 */
	private Double packages;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * 项目fob价(ITEM_FOB)
	 */
	private Double fobValue;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * 产品描述
	 */
	private String goodsDesc;

	/**
	 * 项目CIF价(ITEM_CIF)
	 */
	private Double cifValue;

	/**
	 * 商标
	 */
	private String brand;

	/**
	 * 规格
	 */
	private String attributes;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 次数
	 */
	private Double num = 1.0;
	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Integer getArgentinaExportId() {
		return argentinaExportId;
	}

	public void setArgentinaExportId(Integer argentinaExportId) {
		this.argentinaExportId = argentinaExportId;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getExportId() {
		return exportId;
	}

	public void setExportId(String exportId) {
		this.exportId = exportId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getIncoterms() {
		return incoterms;
	}

	public void setIncoterms(String incoterms) {
		this.incoterms = incoterms;
	}

	public Double getPackages() {
		return packages;
	}

	public void setPackages(Double packages) {
		this.packages = packages;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public Double getFobValue() {
		return fobValue;
	}

	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
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

	public Double getCifValue() {
		return cifValue;
	}

	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}