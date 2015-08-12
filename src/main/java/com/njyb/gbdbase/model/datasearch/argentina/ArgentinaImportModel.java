package com.njyb.gbdbase.model.datasearch.argentina;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ArgentinaImportModel implements Serializable {

	/**
	 * 主键
	 */
	private Integer argentinaImportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 日
	 */
	private String day;

	/**
	 * 月
	 */
	private String month;

	/**
	 * 年
	 */
	private String year;

	/**
	 * 进口id
	 */
	private String importId;

	/**
	 * 类型
	 */
	private String operType;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 进口商编号
	 */
	private String importerId;

	/**
	 * 销售国
	 */
	private String salesCountry;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 起运港(ORIGIN_PORT)
	 */
	private String startPort;

	/**
	 * 成交方式
	 */
	private String incoterms;

	/**
	 * 件数,包装数量
	 */
	private Double packages;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * 当前项FOB价
	 */
	private Double fobValue;

	/**
	 * 当前项运费
	 */
	private Double freight;

	/**
	 * 当前项保险价
	 */
	private Double insurance;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 当前项CIF价
	 */
	private Double cifValue;

	/**
	 * 商标
	 */
	private String brand;

	/**
	 * 型号
	 */
	private String attributes;

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

	public Integer getArgentinaImportId() {
		return argentinaImportId;
	}

	public void setArgentinaImportId(Integer argentinaImportId) {
		this.argentinaImportId = argentinaImportId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImportId() {
		return importId;
	}

	public void setImportId(String importId) {
		this.importId = importId;
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

	public String getImporter() {
		return importer;
	}

	public void setImporter(String importer) {
		this.importer = importer;
	}

	public String getImporterId() {
		return importerId;
	}

	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}

	public String getSalesCountry() {
		return salesCountry;
	}

	public void setSalesCountry(String salesCountry) {
		this.salesCountry = salesCountry;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getStartPort() {
		return startPort;
	}

	public void setStartPort(String startPort) {
		this.startPort = startPort;
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

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
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

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
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


}