package com.njyb.gbdbase.model.datasearch.uruguay;
import java.io.Serializable;

@SuppressWarnings("serial")
public class UruguayExportModel implements Serializable {

	/**
	 * 
	 */
	private Integer uruguayExportId;

	/**
	 * 日期(REG_DATE)
	 */
	private String date;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 出口商编号
	 */
	private String exporterId;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 目的国
	 */
	private String destCountry;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 *  运输公司
	 */
	private String transCorp;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * 物理数量
	 */
	private Double physicalQuantity;

	/**
	 *  物理数量单位(UNIT_OF_PHYSICAL_QUANTITY)
	 */
	private String physicalQuantityUnit;

	/**
	 * FOB金额（FOB)
	 */
	private Double fobValue;
	private Double num = 1.0;
	public Integer getUruguayExportId() {
		return uruguayExportId;
	}
	public void setUruguayExportId(Integer uruguayExportId) {
		this.uruguayExportId = uruguayExportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCustoms() {
		return customs;
	}
	public void setCustoms(String customs) {
		this.customs = customs;
	}
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
	}
	public String getExporterId() {
		return exporterId;
	}
	public void setExporterId(String exporterId) {
		this.exporterId = exporterId;
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
	public String getTransCorp() {
		return transCorp;
	}
	public void setTransCorp(String transCorp) {
		this.transCorp = transCorp;
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
	public Double getPhysicalQuantity() {
		return physicalQuantity;
	}
	public void setPhysicalQuantity(Double physicalQuantity) {
		this.physicalQuantity = physicalQuantity;
	}
	public String getPhysicalQuantityUnit() {
		return physicalQuantityUnit;
	}
	public void setPhysicalQuantityUnit(String physicalQuantityUnit) {
		this.physicalQuantityUnit = physicalQuantityUnit;
	}
	public Double getFobValue() {
		return fobValue;
	}
	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

