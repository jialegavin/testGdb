package com.njyb.gbdbase.model.datasearch.mexico;
import java.io.Serializable;

@SuppressWarnings("serial")
public class MexicoImportModel implements Serializable {

	/**
	 * 主键id
	 */
	private Integer mexicoImportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * 产品描述
	 */
	private String goodsDesc;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 计量单位
	 */
	private String measureUnit;

	/**
	 * fob美元总价
	 */
	private Double fobValue;

	/**
	 * fob美元单价
	 */
	private Double fobUnit;

	private Double num = 1.0;

	public Integer getMexicoImportId() {
		return mexicoImportId;
	}

	public void setMexicoImportId(Integer mexicoImportId) {
		this.mexicoImportId = mexicoImportId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public Double getFobValue() {
		return fobValue;
	}

	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
	}

	public Double getFobUnit() {
		return fobUnit;
	}

	public void setFobUnit(Double fobUnit) {
		this.fobUnit = fobUnit;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}
