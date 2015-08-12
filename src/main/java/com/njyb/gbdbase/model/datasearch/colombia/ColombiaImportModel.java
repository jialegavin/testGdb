package com.njyb.gbdbase.model.datasearch.colombia;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ColombiaImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer colombiaImportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 日
	 */
	private String day;

	/**
	 * 月
	 */
	private String month;

	/**
	 * 年
	 */
	private String year;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 编号
	 */
	private String controlId;

	/**
	 * 进口商编号
	 */
	private String importerId;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 进口商地址(小范围)
	 */
	private String importerAddress;

	/**
	 * 进口商电话
	 */
	private String importerTel;

	/**
	 * 进口商地址(大范围：省)
	 */
	private String importerDepartment;

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
	 * 支付方式
	 */
	private String payment;

	/**
	 * 运输公司
	 */
	private String transCorp;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * 税费
	 */
	private Double tax;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 出口商地址
	 */
	private String exporterAddress;

	/**
	 * 出口商所在城市
	 */
	private String exporterCity;

	/**
	 * 出口商所在国家
	 */
	private String exporterCountry;

	/**
	 * 出口商联系方式
	 */
	private String exporterContact;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * FOB金额(FOB)
	 */
	private Double fobValue;

	/**
	 * 运费
	 */
	private Double freight;

	/**
	 * 保险费
	 */
	private Double insurance;

	/**
	 * CIF金额(CIF)
	 */
	private Double cifValue;

	private Double num = 1.0;

	public Integer getColombiaImportId() {
		return colombiaImportId;
	}

	public void setColombiaImportId(Integer colombiaImportId) {
		this.colombiaImportId = colombiaImportId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
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

	public String getImporterAddress() {
		return importerAddress;
	}

	public void setImporterAddress(String importerAddress) {
		this.importerAddress = importerAddress;
	}

	public String getImporterTel() {
		return importerTel;
	}

	public void setImporterTel(String importerTel) {
		this.importerTel = importerTel;
	}

	public String getImporterDepartment() {
		return importerDepartment;
	}

	public void setImporterDepartment(String importerDepartment) {
		this.importerDepartment = importerDepartment;
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTransCorp() {
		return transCorp;
	}

	public void setTransCorp(String transCorp) {
		this.transCorp = transCorp;
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

	public String getExporter() {
		return exporter;
	}

	public void setExporter(String exporter) {
		this.exporter = exporter;
	}

	public String getExporterAddress() {
		return exporterAddress;
	}

	public void setExporterAddress(String exporterAddress) {
		this.exporterAddress = exporterAddress;
	}

	public String getExporterCity() {
		return exporterCity;
	}

	public void setExporterCity(String exporterCity) {
		this.exporterCity = exporterCity;
	}

	public String getExporterCountry() {
		return exporterCountry;
	}

	public void setExporterCountry(String exporterCountry) {
		this.exporterCountry = exporterCountry;
	}

	public String getExporterContact() {
		return exporterContact;
	}

	public void setExporterContact(String exporterContact) {
		this.exporterContact = exporterContact;
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

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
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


