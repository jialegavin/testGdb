package com.njyb.gbdbase.model.datasearch.costarica;
import java.io.Serializable;

@SuppressWarnings("serial")
public class CostaricaExportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer costaricaExportId;

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
	 * 船舶编号
	 */
	private String shippingNumber;

	/**
	 * 贸易类型
	 */
	private String tradeType;

	/**
	 * 出口商编号
	 */
	private String exporterId;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 出口商地址
	 */
	private String exporterAddress;

	/**
	 * 发票总金额
	 */
	private Double totalInv;

	/**
	 * CIF金额（报关单号下的CIF项求和:）
	 */
	private Double totalCif;

	/**
	 * 毛重（报关单号下的毛重项求和）
	 */
	private Double totalGWeight;

	/**
	 * 净重（报关单号下的净重项求和）
	 */
	private Double totalNWeight;

	/**
	 * 运输类型
	 */
	private String transType;

	/**
	 * 汇率
	 */
	private String exchangeRate;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 包装数量(PACKAGES)
	 */
	private Double packages;

	/**
	 * 包装类型(TYPE_OF_PACKAGE)
	 */
	private String packageType;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 此项CIF价(CIF)
	 */
	private Double cifValue;

	/**
	 * 此项毛重(G_WEIGHT)
	 */
	private Double grossWeight;

	/**
	 * 此项净重(N_WEIGHT)
	 */
	private Double netWeight;

	/**
	 * 日期
	 */
	private String date;
	private Double num = 1.0;
	public Integer getCostaricaExportId() {
		return costaricaExportId;
	}
	public void setCostaricaExportId(Integer costaricaExportId) {
		this.costaricaExportId = costaricaExportId;
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
	public Double getPackages() {
		return packages;
	}
	public void setPackages(Double packages) {
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

