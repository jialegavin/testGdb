package com.njyb.gbdbase.model.datasearch.wkl;
import java.io.Serializable;

@SuppressWarnings("serial")
public class UkraineImportModel implements Serializable {

	/**
	 * 
	 */
	private Integer ukraineImportId;

	/**
	 * 日期(Дата)
	 */
	private String date;

	/**
	 * 报关单号（DECLARATION_NUMBER）
	 */
	private String declarationNo;

	/**
	 * 
	 */
	private String exporterId;

	/**
	 * 出口商,发件人（Найменування відправника）
	 */
	private String exporter;

	/**
	 * 出口商地址(Адреса відправника)
	 */
	private String exporterAddress;

	/**
	 * 收件人的企业代码(Код за ЄДРПОУ одержувача)
	 */
	private String importerId;

	/**
	 * 进口商(Найменування одержувача)
	 */
	private String importer;

	/**
	 * 进口商地址(Адреса одержувача)
	 */
	private String importerAddress;

	/**
	 * 海关编码(Код УКТЗЕД)
	 */
	private String hscode;

	/**
	 * 产品描述(Опис-товару)
	 */
	private String goodsDesc;

	/**
	 * 净重(Вага нетто)
	 */
	private Double netWeight;

	/**
	 * 货物成本(Фактурна вартість)
	 */
	private Double cost;

	/**
	 * 发票总额(загальна фактурна вартість)
	 */
	private Double cifValue;

	/**
	 * 货币名称/代码(код валюти)
	 */
	private String currencyType;
	
	/**
	 * 汇率(Курс валюти)
	 */
	private Double exchangeRate;

	/**
	 * 担保人的企业代码(Код за ЄДРПОУ гаранта)
	 */
	private String guaranteeId;

	/**
	 * 担保人名称(Найменування гаранта)
	 */
	private String guarantee;

	/**
	 * 担保人地址(Адреса гаранта)
	 */
	private String guaranteeAddress;

	/**
	 * 银行账号编号
	 */
	private String bankAccountNo;

	/**
	 * 分行地址代码
	 */
	private String bankBranchLocCode;

	/**
	 * 银行代码
	 */
	private String bankCode;

	/**
	 * 银行名称
	 */
	private String bankName;

	/**
	 * 海关编号(Номер ВМД (митниця))
	 */
	private String customsOfficeCode;

	public Integer getUkraineImportId() {
		return ukraineImportId;
	}

	public void setUkraineImportId(Integer ukraineImportId) {
		this.ukraineImportId = ukraineImportId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDeclarationNo() {
		return declarationNo;
	}

	public void setDeclarationNo(String declarationNo) {
		this.declarationNo = declarationNo;
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

	public String getExporterAddress() {
		return exporterAddress;
	}

	public void setExporterAddress(String exporterAddress) {
		this.exporterAddress = exporterAddress;
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

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getCifValue() {
		return cifValue;
	}

	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public String getGuaranteeAddress() {
		return guaranteeAddress;
	}

	public void setGuaranteeAddress(String guaranteeAddress) {
		this.guaranteeAddress = guaranteeAddress;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankBranchLocCode() {
		return bankBranchLocCode;
	}

	public void setBankBranchLocCode(String bankBranchLocCode) {
		this.bankBranchLocCode = bankBranchLocCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCustomsOfficeCode() {
		return customsOfficeCode;
	}

	public void setCustomsOfficeCode(String customsOfficeCode) {
		this.customsOfficeCode = customsOfficeCode;
	}
}


