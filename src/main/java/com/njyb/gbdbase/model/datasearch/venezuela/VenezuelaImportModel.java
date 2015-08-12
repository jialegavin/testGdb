package com.njyb.gbdbase.model.datasearch.venezuela;
import java.io.Serializable;

@SuppressWarnings("serial")
public class VenezuelaImportModel implements Serializable {

	/**
	 * 主键id
	 */
	private Integer venezuelaImportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 年
	 */
	private String year;

	/**
	 * 月
	 */
	private String month;

	/**
	 * 
	 */
	private String record;

	/**
	 * 提单号(BL)
	 */
	private String blNumber;

	/**
	 * HS章
	 */
	private String chapter;

	/**
	 * HS章注(HS_CODE_DESC)
	 */
	private String hsDesc;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 产品描述（HS_CODE_DESC）
	 */
	private String goodsDesc;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 海关城市(CUSTOM)
	 */
	private String customs;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 起运港(EMBARQ_PORT:装货港)
	 */
	private String startPort;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 销售国
	 */
	private String salesCountry;

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
	 * FOB美元价
	 */
	private Double fobValue;

	/**
	 * CIF玻利瓦尔价
	 */
	private Double boCif;

	/**
	 * CIF美元价
	 */
	private Double cifValue;
	private Double num = 1.0;
	public Integer getVenezuelaImportId() {
		return venezuelaImportId;
	}
	public void setVenezuelaImportId(Integer venezuelaImportId) {
		this.venezuelaImportId = venezuelaImportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getBlNumber() {
		return blNumber;
	}
	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
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
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getImporter() {
		return importer;
	}
	public void setImporter(String importer) {
		this.importer = importer;
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
	public String getStartPort() {
		return startPort;
	}
	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getSalesCountry() {
		return salesCountry;
	}
	public void setSalesCountry(String salesCountry) {
		this.salesCountry = salesCountry;
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
	public Double getBoCif() {
		return boCif;
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
	public void setBoCif(Double boCif) {
		this.boCif = boCif;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

