package com.njyb.gbdbase.model.datasearch.bolivia;

import java.io.Serializable;

public class BoliviaImportModel implements Serializable  {
	/**
	 * 主键编号
	 */
	private Integer boliviaImportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 政体
	 */
	private String regime;

	/**
	 * 注册(Registration)
	 */
	private String registration;

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
	 * 章注(Product HS)
	 */
	private String hscodeDesc;

	/**
	 * 出口商(Provider)
	 */
	private String exporter;

	/**
	 * 原产国(Country of Origin)
	 */
	private String originCountry;

	/**
	 * 收购国(Country of Purchase)
	 */
	private String purchaseCountry;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 报关单号(Customs Broker ID)
	 */
	private String brokerId;

	/**
	 * 报关员(Customs Broker)
	 */
	private String broker;

	/**
	 * 途径
	 */
	private String channel;

	/**
	 * 协议
	 */
	private String agreement;

	/**
	 * 原始修改案(Original Amendment)
	 */
	private String amendent;

	/**
	 * 项号
	 */
	private String item;

	/**
	 * 税收(Total Taxes Value Bs)
	 */
	private String tax;

	/**
	 * 净重(Net Weight)
	 */
	private Double netWeight;

	/**
	 * 产品描述(Commercial Description)
	 */
	private String goodsDesc;

	/**
	 * FOB美元总价(FOB Value US$)
	 */
	private Double fobValue;

	/**
	 * CIF美元总价(CIF Value US$)
	 */
	private Double cifValue;

	/**
	 * CIF的玻利瓦尔价(CIF Value Bs)
	 */
	private Double cifBob;

	/**
	 * 汇率(Exchange Rate)
	 */
	private Double rate;

	/**
	 * 次数
	 */
	private Double num = 1.0;
	
	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Integer getBoliviaImportId() {
		return boliviaImportId;
	}

	public void setBoliviaImportId(Integer boliviaImportId) {
		this.boliviaImportId = boliviaImportId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
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

	public String getHscodeDesc() {
		return hscodeDesc;
	}

	public void setHscodeDesc(String hscodeDesc) {
		this.hscodeDesc = hscodeDesc;
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

	public String getPurchaseCountry() {
		return purchaseCountry;
	}

	public void setPurchaseCountry(String purchaseCountry) {
		this.purchaseCountry = purchaseCountry;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getAmendent() {
		return amendent;
	}

	public void setAmendent(String amendent) {
		this.amendent = amendent;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
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

	public Double getCifBob() {
		return cifBob;
	}

	public void setCifBob(Double cifBob) {
		this.cifBob = cifBob;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}
