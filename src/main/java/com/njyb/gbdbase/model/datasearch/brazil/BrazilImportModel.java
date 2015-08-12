package com.njyb.gbdbase.model.datasearch.brazil;
import java.io.Serializable;

@SuppressWarnings("serial")
public class BrazilImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer brazilImportId;

	/**
	 * 进货日期(月/年)
	 */
	private String date;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * 产品描述
	 */
	private String goodsDesc;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 进口商(DEPARTMENT)
	 */
	private String importer;

	/**
	 * 目的地(DESTINATIO:借用字段)
	 */
	private String destCountry;

	/**
	 * 单位类型
	 */
	private String unitType;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * FOB单价
	 */
	private Double fobUnit;

	/**
	 * FOB美元总价
	 */
	private Double fobValue;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 自治区
	 */
	private String municCity;
	
	private Double num = 1.0;

	public Integer getBrazilImportId() {
		return brazilImportId;
	}

	public void setBrazilImportId(Integer brazilImportId) {
		this.brazilImportId = brazilImportId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
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

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getFobUnit() {
		return fobUnit;
	}

	public void setFobUnit(Double fobUnit) {
		this.fobUnit = fobUnit;
	}

	public Double getFobValue() {
		return fobValue;
	}

	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getMunicCity() {
		return municCity;
	}

	public void setMunicCity(String municCity) {
		this.municCity = municCity;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}

