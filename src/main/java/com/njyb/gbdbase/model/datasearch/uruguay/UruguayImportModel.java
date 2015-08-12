package com.njyb.gbdbase.model.datasearch.uruguay;
import java.io.Serializable;

@SuppressWarnings("serial")
public class UruguayImportModel implements Serializable {

	/**
	 * 
	 */
	private Integer uruguayImportId;

	/**
	 * 日期(REG_DATE)
	 */
	private String date;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 进口商编号
	 */
	private String importerId;

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
	 * 销售国
	 */
	private String salesCountry;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 运输公司
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
	 * 税费
	 */
	private Double tax;

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
	 * 物理数量单位(UNIT_OF_PHYSICAL_QUANTITY)
	 */
	private String physicalQuantityUnit;

	/**
	 * 保险费
	 */
	private Double insurance;

	/**
	 * 保费币种
	 */
	private String insuranceCurrency;

	/**
	 * 运费
	 */
	private Double freight;

	/**
	 * 运费币种
	 */
	private String freightCurrency;

	/**
	 * CIF金额（CIF）
	 */
	private Double cifValue;


	private Double num = 1.0;


	public Integer getUruguayImportId() {
		return uruguayImportId;
	}


	public void setUruguayImportId(Integer uruguayImportId) {
		this.uruguayImportId = uruguayImportId;
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


	public String getImporter() {
		return importer;
	}


	public void setImporter(String importer) {
		this.importer = importer;
	}


	public String getImporterId() {
		return importerId;
	}


	public void setImporterId(String importerId) {
		this.importerId = importerId;
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


	public String getSalesCountry() {
		return salesCountry;
	}


	public void setSalesCountry(String salesCountry) {
		this.salesCountry = salesCountry;
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


	public Double getTax() {
		return tax;
	}


	public void setTax(Double tax) {
		this.tax = tax;
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


	public Double getInsurance() {
		return insurance;
	}


	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}


	public String getInsuranceCurrency() {
		return insuranceCurrency;
	}


	public void setInsuranceCurrency(String insuranceCurrency) {
		this.insuranceCurrency = insuranceCurrency;
	}


	public Double getFreight() {
		return freight;
	}


	public void setFreight(Double freight) {
		this.freight = freight;
	}


	public String getFreightCurrency() {
		return freightCurrency;
	}


	public void setFreightCurrency(String freightCurrency) {
		this.freightCurrency = freightCurrency;
	}


	public Double getCifValue() {
		return cifValue;
	}


	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
	}


	public Double getNum() {
		return num;
	}


	public void setNum(Double num) {
		this.num = num;
	}
	}