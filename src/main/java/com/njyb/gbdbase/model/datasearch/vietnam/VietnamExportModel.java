package com.njyb.gbdbase.model.datasearch.vietnam;
import java.io.Serializable;

@SuppressWarnings("serial")
public class VietnamExportModel implements Serializable {

	/**
	 * 
	 */
	private Integer vietnamExportId;

	/**
	 * 日期（REG_DATE）
	 */
	private String date;

	/**
	 * 起运港(PORT_NAME)
	 */
	private String startPort;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * FOB单价
	 */
	private Double fobUnit;

	/**
	 * FOB金额（FOB）
	 */
	private Double fobValue;

	/**
	 * 币种
	 */
	private String currency;

	/**
	 * 成交方式
	 */
	private String incoterms;

	/**
	 * 支付方式
	 */
	private String payment;

	/**
	 * FOB美元价
	 */
	private Double usFob;

	/**
	 * 税收
	 */
	private Double tax;
	private Double num = 1.0;
	public Integer getVietnamExportId() {
		return vietnamExportId;
	}
	public void setVietnamExportId(Integer vietnamExportId) {
		this.vietnamExportId = vietnamExportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartPort() {
		return startPort;
	}
	public void setStartPort(String startPort) {
		this.startPort = startPort;
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
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIncoterms() {
		return incoterms;
	}
	public void setIncoterms(String incoterms) {
		this.incoterms = incoterms;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Double getUsFob() {
		return usFob;
	}
	public void setUsFob(Double usFob) {
		this.usFob = usFob;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

