package com.njyb.gbdbase.model.datasearch.vietnam;
import java.io.Serializable;

@SuppressWarnings("serial")
public class VietnamImportModel implements Serializable {

	/**
	 * 
	 */
	private Integer vietnamImportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 海关编码(hscode)
	 */
	private String hscode;

	/**
	 * 进口商(importer)
	 */
	private String importer;

	/**
	 * 出口商(exporter)
	 */
	private String exporter;

	/**
	 * 产品描述(goods_desp)
	 */
	private String goodsDesc;

	/**
	 * 成交方式(incoterms)
	 */
	private String incoterms;

	/**
	 * 数量(quantity)
	 */
	private Double quantity;

	/**
	 * 数量单位(unit_of_quantity)
	 */
	private String quantityUnit;

	/**
	 * cif单价（统计）
	 */
	private Double cifUnit;

	/**
	 * cif单价(cif_unit)
	 */
	private Double cifUnitOrigin;

	/**
	 * cif总价(cif)
	 */
	private Double cifValue;

	/**
	 * 货币类型(currency)
	 */
	private String currency;

	/**
	 * cif美元价(us_cif)
	 */
	private Double usCif;

	/**
	 * 支付方式(payment)
	 */
	private String payment;

	/**
	 * 税费(tax)
	 */
	private Double tax;

	/**
	 * 抵达港(port_name)
	 */
	private String endPort;

	/**
	 * 原产国(origin_country)
	 */
	private String originCountry;


	private Double num = 1.0;


	public Integer getVietnamImportId() {
		return vietnamImportId;
	}


	public void setVietnamImportId(Integer vietnamImportId) {
		this.vietnamImportId = vietnamImportId;
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


	public String getGoodsDesc() {
		return goodsDesc;
	}


	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}


	public String getIncoterms() {
		return incoterms;
	}


	public void setIncoterms(String incoterms) {
		this.incoterms = incoterms;
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


	public Double getCifUnit() {
		return cifUnit;
	}


	public void setCifUnit(Double cifUnit) {
		this.cifUnit = cifUnit;
	}


	public Double getCifUnitOrigin() {
		return cifUnitOrigin;
	}


	public void setCifUnitOrigin(Double cifUnitOrigin) {
		this.cifUnitOrigin = cifUnitOrigin;
	}


	public Double getCifValue() {
		return cifValue;
	}


	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public Double getUsCif() {
		return usCif;
	}


	public void setUsCif(Double usCif) {
		this.usCif = usCif;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public Double getTax() {
		return tax;
	}


	public void setTax(Double tax) {
		this.tax = tax;
	}


	public String getEndPort() {
		return endPort;
	}


	public void setEndPort(String endPort) {
		this.endPort = endPort;
	}


	public String getOriginCountry() {
		return originCountry;
	}


	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}


	public Double getNum() {
		return num;
	}


	public void setNum(Double num) {
		this.num = num;
	}
}