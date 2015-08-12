package com.njyb.gbdbase.model.datasearch.panama;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PanamaExportModel implements Serializable {

	/**
	 * 主键id
	 */
	private Integer panamaExportId;

	/**
	 * 出口商编号
	 */
	private String exporterId;

	/**
	 * 出口商
	 */
	private String exporter;

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
	 * 起运港(PORT)
	 */
	private String startPort;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 报关单号
	 */
	private Double declarationNumber;

	/**
	 * 目的国
	 */
	private String destCountry;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 件数(包装数量)
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
	 * FOB总价
	 */
	private Double fobValue;

	/**
	 * 日期
	 */
	private String date;

	private Double num = 1.0;

	public Integer getPanamaExportId() {
		return panamaExportId;
	}

	public void setPanamaExportId(Integer panamaExportId) {
		this.panamaExportId = panamaExportId;
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

	public String getStartPort() {
		return startPort;
	}

	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Double getDeclarationNumber() {
		return declarationNumber;
	}

	public void setDeclarationNumber(Double declarationNumber) {
		this.declarationNumber = declarationNumber;
	}

	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
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
