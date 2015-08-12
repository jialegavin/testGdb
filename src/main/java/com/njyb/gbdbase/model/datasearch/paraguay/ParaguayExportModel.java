package com.njyb.gbdbase.model.datasearch.paraguay;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ParaguayExportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer paraguayExportId;

	/**
	 * 日期(REG_DATE)
	 */
	private String date;

	/**
	 * 出口商编号
	 */
	private String exporterId;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 进口商(BUYER)
	 */
	private String importer;

	/**
	 * 目的国
	 */
	private String destCountry;

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
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * FOB总价(FOB)
	 */
	private Double fobValue;

	/**
	 * 运输价
	 */
	private Double freight;

	/**
	 * 保险价
	 */
	private Double insurance;

	/**
	 * CIF总价（CIF）
	 */
	private Double cifValue;

	/**
	 * FOB单价（统计值）
	 */
	private Double fobUnit;

	/**
	 * FOB单价(原始数据)
	 */
	private Double fobUnitOrigin;

	/**
	 * 商标
	 */
	private String brand;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 运输类型
	 */
	private String transType;

	/**
	 * 运输公司
	 */
	private String transCorp;

	/**
	 * 运输国家
	 */
	private String transCountry;

	/**
	 * 编号
	 */
	private String orderList;
	private Double num = 1.0;
	public Integer getParaguayExportId() {
		return paraguayExportId;
	}
	public void setParaguayExportId(Integer paraguayExportId) {
		this.paraguayExportId = paraguayExportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExporterId() {
		return exporterId;
	}
	public void setExporterId(String exporterId) {
		this.exporterId = exporterId;
	}
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
	}
	public String getImporter() {
		return importer;
	}
	public void setImporter(String importer) {
		this.importer = importer;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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
	public String getOrderList() {
		return orderList;
	}
	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

