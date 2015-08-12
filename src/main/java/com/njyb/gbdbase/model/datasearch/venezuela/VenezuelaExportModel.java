package com.njyb.gbdbase.model.datasearch.venezuela;
import java.io.Serializable;

@SuppressWarnings("serial")
public class VenezuelaExportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer venezuelaExportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 年
	 */
	private Double year;

	/**
	 * 月
	 */
	private Double month;

	/**
	 * 编号
	 */
	private Double record;

	/**
	 * 提单号(BL)
	 */
	private Double blNumber;

	/**
	 * HS章
	 */
	private Double chapter;

	/**
	 * HS章注（CHAPTER_DESC）
	 */
	private String hsDesc;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 支付方式
	 */
	private String payment;

	/**
	 * 产品描述(HS_CODE_DESC)
	 */
	private String goodsDesc;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 海关城市(CUSTOM)
	 */
	private String customs;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 目的国
	 */
	private String destCountry;

	/**
	 * 抵达港（DEST_PORT）
	 */
	private String endPort;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * FOB玻利瓦尔价
	 */
	private Double boFob;

	/**
	 * FOB美元价(US_FOB)
	 */
	private Double fobValue;
	private Double num = 1.0;
	public Integer getVenezuelaExportId() {
		return venezuelaExportId;
	}
	public void setVenezuelaExportId(Integer venezuelaExportId) {
		this.venezuelaExportId = venezuelaExportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getYear() {
		return year;
	}
	public void setYear(Double year) {
		this.year = year;
	}
	public Double getMonth() {
		return month;
	}
	public void setMonth(Double month) {
		this.month = month;
	}
	public Double getRecord() {
		return record;
	}
	public void setRecord(Double record) {
		this.record = record;
	}
	public Double getBlNumber() {
		return blNumber;
	}
	public void setBlNumber(Double blNumber) {
		this.blNumber = blNumber;
	}
	public Double getChapter() {
		return chapter;
	}
	public void setChapter(Double chapter) {
		this.chapter = chapter;
	}
	public String getHsDesc() {
		return hsDesc;
	}
	public void setHsDesc(String hsDesc) {
		this.hsDesc = hsDesc;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
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
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	public String getEndPort() {
		return endPort;
	}
	public void setEndPort(String endPort) {
		this.endPort = endPort;
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
	public Double getBoFob() {
		return boFob;
	}
	public void setBoFob(Double boFob) {
		this.boFob = boFob;
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

