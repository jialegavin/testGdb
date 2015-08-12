package com.njyb.gbdbase.model.datasearch.chile;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ChileImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer chileImportId;

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
	 * 校验码
	 */
	private String verifier;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * HS章注
	 */
	private String hsDesc;

	/**
	 * 产品名称
	 */
	private String product;

	/**
	 * 品牌(商标)
	 */
	private String brand;

	/**
	 * 规格型号
	 */
	private String varietey;

	/**
	 * 产品描述(DESC)
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
	 * 起运港
	 */
	private String startPort;

	/**
	 * 抵达港
	 */
	private String endPort;

	/**
	 * 运输公司
	 */
	private String transCorp;

	/**
	 * 负载类型(TYPE_OF_LOAD)
	 */
	private String loadType;

	/**
	 * 包装类型(TYPE_OF_PACKAGE)
	 */
	private String packageType;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 成交方式
	 */
	private String incoterms;

	/**
	 * 税费
	 */
	private String tax;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * FOB金额（FOB）
	 */
	private Double fobValue;

	/**
	 * 运费
	 */
	private Double freight;

	/**
	 * 保费
	 */
	private Double insurance;

	/**
	 * CIF总价
	 */
	private Double cifValue;

	/**
	 * CIF单价
	 */
	private Double cifUnit;

	/**
	 * FOB单价
	 */
	private Double fobUnit;

	/**
	 * 船旗国
	 */
	private String transCorpCountry;

	/**
	 * 税费(美元)
	 */
	private Double usTax;

	/**
	 * 件数
	 */
	private String packages;

	/**
	 * 经济贸易区
	 */
	private String economicZone;

	/**
	 * 进口商的主产业
	 */
	private String importerEconomicKey;

	/**
	 * 关单号
	 */
	private String manifestNumber;

	/**
	 * 到岸日期
	 */
	private String manifestDate;

	/**
	 * 运输文档编号
	 */
	private String transDocNumber;

	/**
	 * 运输文档日期
	 */
	private String transDocDate;
	private Double num = 1.0;
	public Integer getChileImportId() {
		return chileImportId;
	}
	public void setChileImportId(Integer chileImportId) {
		this.chileImportId = chileImportId;
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
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
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
	public String getHsDesc() {
		return hsDesc;
	}
	public void setHsDesc(String hsDesc) {
		this.hsDesc = hsDesc;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getVarietey() {
		return varietey;
	}
	public void setVarietey(String varietey) {
		this.varietey = varietey;
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
	public String getStartPort() {
		return startPort;
	}
	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}
	public String getEndPort() {
		return endPort;
	}
	public void setEndPort(String endPort) {
		this.endPort = endPort;
	}
	public String getTransCorp() {
		return transCorp;
	}
	public void setTransCorp(String transCorp) {
		this.transCorp = transCorp;
	}
	public String getLoadType() {
		return loadType;
	}
	public void setLoadType(String loadType) {
		this.loadType = loadType;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getIncoterms() {
		return incoterms;
	}
	public void setIncoterms(String incoterms) {
		this.incoterms = incoterms;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
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
	public Double getCifUnit() {
		return cifUnit;
	}
	public void setCifUnit(Double cifUnit) {
		this.cifUnit = cifUnit;
	}
	public Double getFobUnit() {
		return fobUnit;
	}
	public void setFobUnit(Double fobUnit) {
		this.fobUnit = fobUnit;
	}
	public String getTransCorpCountry() {
		return transCorpCountry;
	}
	public void setTransCorpCountry(String transCorpCountry) {
		this.transCorpCountry = transCorpCountry;
	}
	public Double getUsTax() {
		return usTax;
	}
	public void setUsTax(Double usTax) {
		this.usTax = usTax;
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	public String getEconomicZone() {
		return economicZone;
	}
	public void setEconomicZone(String economicZone) {
		this.economicZone = economicZone;
	}
	public String getImporterEconomicKey() {
		return importerEconomicKey;
	}
	public void setImporterEconomicKey(String importerEconomicKey) {
		this.importerEconomicKey = importerEconomicKey;
	}
	public String getManifestNumber() {
		return manifestNumber;
	}
	public void setManifestNumber(String manifestNumber) {
		this.manifestNumber = manifestNumber;
	}
	public String getManifestDate() {
		return manifestDate;
	}
	public void setManifestDate(String manifestDate) {
		this.manifestDate = manifestDate;
	}
	public String getTransDocNumber() {
		return transDocNumber;
	}
	public void setTransDocNumber(String transDocNumber) {
		this.transDocNumber = transDocNumber;
	}
	public String getTransDocDate() {
		return transDocDate;
	}
	public void setTransDocDate(String transDocDate) {
		this.transDocDate = transDocDate;
		
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

