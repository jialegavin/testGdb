package com.njyb.gbdbase.model.datasearch.costarica;
import java.io.Serializable;

@SuppressWarnings("serial")
public class CostaricaImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer costaricaImportId;

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
	 * 报关单号
	 */
	private String declarationNumber;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 船号
	 */
	private String shippingNumber;

	/**
	 * 贸易类型
	 */
	private String tradeType;

	/**
	 * 进口商编号
	 */
	private String importerId;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 进口商地址
	 */
	private String importerAddress;

	/**
	 * 发票总金额
	 */
	private Double totalInv;

	/**
	 * CIF金额
	 */
	private Double totalCif;

	/**
	 * 毛重(报关单中的毛重求和)
	 */
	private Double totalGWeight;

	/**
	 * 净重(报关单中的净重求和)
	 */
	private Double totalNWeight;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 汇率(EXCHANGE_RATE)
	 */
	private String exchangeRate;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 序列号
	 */
	private String serialNumber;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 包装数量
	 */
	private String packages;

	/**
	 * 包装类型(TYPE_OF_PACKAGE)
	 */
	private String packageType;

	/**
	 * 品牌(商标)
	 */
	private String brand;

	/**
	 * 此项CIF价(CIF)
	 */
	private Double cifValue;

	/**
	 * 此项毛重(ITEM_G_WEIGHT)
	 */
	private Double grossWeight;

	/**
	 * 此项净重(ITEM_N_WEIGHT)
	 */
	private Double netWeight;

	private Double num = 1.0;

	public Integer getCostaricaImportId() {
		return costaricaImportId;
	}

	public void setCostaricaImportId(Integer costaricaImportId) {
		this.costaricaImportId = costaricaImportId;
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

	public String getDeclarationNumber() {
		return declarationNumber;
	}

	public void setDeclarationNumber(String declarationNumber) {
		this.declarationNumber = declarationNumber;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getShippingNumber() {
		return shippingNumber;
	}

	public void setShippingNumber(String shippingNumber) {
		this.shippingNumber = shippingNumber;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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

	public Double getTotalInv() {
		return totalInv;
	}

	public void setTotalInv(Double totalInv) {
		this.totalInv = totalInv;
	}

	public Double getTotalCif() {
		return totalCif;
	}

	public void setTotalCif(Double totalCif) {
		this.totalCif = totalCif;
	}

	public Double getTotalGWeight() {
		return totalGWeight;
	}

	public void setTotalGWeight(Double totalGWeight) {
		this.totalGWeight = totalGWeight;
	}

	public Double getTotalNWeight() {
		return totalNWeight;
	}

	public void setTotalNWeight(Double totalNWeight) {
		this.totalNWeight = totalNWeight;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Double getCifValue() {
		return cifValue;
	}

	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
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

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}
