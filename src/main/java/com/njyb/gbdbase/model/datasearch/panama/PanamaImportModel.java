package com.njyb.gbdbase.model.datasearch.panama;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PanamaImportModel implements Serializable {

	/**
	 * 主键id
	 */
	private Integer panamaImportId;

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
	 * 进口商编号
	 */
	private String importerId;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * 产品描述
	 */
	private String goodsDesc;

	/**
	 * 海关中心
	 */
	private String customsZone;

	/**
	 * 抵达港(PORT)
	 */
	private String endPort;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 报关单号
	 */
	private String declarationNumber;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 包装数量
	 */
	private Double packages;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * fob总价
	 */
	private Double fobValue;

	/**
	 * cif总价
	 */
	private Double cifValue;

	/**
	 * 运输费用
	 */
	private Double freight;

	/**
	 * 保险费用
	 */
	private Double insurance;

	/**
	 * 日期
	 */
	private String date;
	private Double num = 1.0;
	public Integer getPanamaImportId() {
		return panamaImportId;
	}
	public void setPanamaImportId(Integer panamaImportId) {
		this.panamaImportId = panamaImportId;
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
	public String getImporterId() {
		return importerId;
	}
	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}
	public String getImporter() {
		return importer;
	}
	public void setImporter(String importer) {
		this.importer = importer;
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
	public String getCustomsZone() {
		return customsZone;
	}
	public void setCustomsZone(String customsZone) {
		this.customsZone = customsZone;
	}
	
	public String getEndPort() {
		return endPort;
	}
	public void setEndPort(String endPort) {
		this.endPort = endPort;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getDeclarationNumber() {
		return declarationNumber;
	}
	public void setDeclarationNumber(String declarationNumber) {
		this.declarationNumber = declarationNumber;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public Double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getPackages() {
		return packages;
	}
	public void setPackages(Double packages) {
		this.packages = packages;
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
	public Double getCifValue() {
		return cifValue;
	}
	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
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

