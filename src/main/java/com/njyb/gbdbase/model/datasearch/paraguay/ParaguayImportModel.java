package com.njyb.gbdbase.model.datasearch.paraguay;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ParaguayImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer paraguayImportId;

	/**
	 * 通关日期(REG_DATE)
	 */
	private String date;

	/**
	 * 进口商编码
	 */
	private String importerId;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 出口商(SALER)
	 */
	private String exporter;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位
	 */
	private String unitOfQuantity;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 品牌(商标)
	 */
	private String brand;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * FOB金额(FOB)
	 */
	private Double fobValue;

	/**
	 * 运费
	 */
	private Double freight;

	/**
	 * 保险费
	 */
	private Double insurance;

	/**
	 * CIF金额(CIF)
	 */
	private Double cifValue;

	/**
	 * 
	 */
	private Double fobUnit;

	/**
	 * FOB单价
	 */
	private Double fobUnitOrigin;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 运输公司
	 */
	private String transCorp;

	/**
	 * 运输公司所在国
	 */
	private String transCountry;

	/**
	 * 销售国（SALES_COUNTRY）
	 */
	private String salesCountry;

	/**
	 * 编号
	 */
	private String orderList;

	/**
	 * 提单号
	 */
	private String blNumber;
	private Double num = 1.0;
	public Integer getParaguayImportId() {
		return paraguayImportId;
	}
	public void setParaguayImportId(Integer paraguayImportId) {
		this.paraguayImportId = paraguayImportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getUnitOfQuantity() {
		return unitOfQuantity;
	}
	public void setUnitOfQuantity(String unitOfQuantity) {
		this.unitOfQuantity = unitOfQuantity;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
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
	public Double getCifValue() {
		return cifValue;
	}
	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
	}
	public Double getFobUnit() {
		return fobUnit;
	}
	public void setFobUnit(Double fobUnit) {
		this.fobUnit = fobUnit;
	}
	public Double getFobUnitOrigin() {
		return fobUnitOrigin;
	}
	public void setFobUnitOrigin(Double fobUnitOrigin) {
		this.fobUnitOrigin = fobUnitOrigin;
	}
	public String getCustoms() {
		return customs;
	}
	public void setCustoms(String customs) {
		this.customs = customs;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransCorp() {
		return transCorp;
	}
	public void setTransCorp(String transCorp) {
		this.transCorp = transCorp;
	}
	public String getTransCountry() {
		return transCountry;
	}
	public void setTransCountry(String transCountry) {
		this.transCountry = transCountry;
	}
	public String getSalesCountry() {
		return salesCountry;
	}
	public void setSalesCountry(String salesCountry) {
		this.salesCountry = salesCountry;
	}
	public String getOrderList() {
		return orderList;
	}
	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}
	public String getBlNumber() {
		return blNumber;
	}
	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

