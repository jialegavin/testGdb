package com.njyb.gbdbase.model.datasearch.honduras;
import java.io.Serializable;

@SuppressWarnings("serial")
public class HondurasImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer hondurasImportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * 产品描述
	 */
	private String goodsDesc;

	/**
	 * CIF美元总价
	 */
	private Double cifValue;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * CIF美元单价
	 */
	private Double cifUnit;

	private Double num = 1.0;

	public Integer getHondurasImportId() {
		return hondurasImportId;
	}

	public void setHondurasImportId(Integer hondurasImportId) {
		this.hondurasImportId = hondurasImportId;
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

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getCifUnit() {
		return cifUnit;
	}

	public void setCifUnit(Double cifUnit) {
		this.cifUnit = cifUnit;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}
