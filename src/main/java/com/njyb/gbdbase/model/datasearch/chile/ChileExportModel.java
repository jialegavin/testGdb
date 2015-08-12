package com.njyb.gbdbase.model.datasearch.chile;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ChileExportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer chileExportId;

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
	 * 编号
	 */
	private String controlId;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 出口商编号
	 */
	private String exporterId;

	/**
	 * 校验码
	 */
	private String verifier;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * HS章注
	 */
	private String hsDesc;

	/**
	 * 产品
	 */
	private String product;

	/**
	 * 规格型号
	 */
	private String varietey;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 产品描述(DESC)
	 */
	private String goodsDesc;

	/**
	 * 目的国
	 */
	private String destCountry;

	/**
	 * 运输类型
	 */
	private String transType;

	/**
	 * 跨国公司
	 */
	private String transCorp;

	/**
	 * 船名
	 */
	private String shipName;

	/**
	 * 负载类型(TYPE_OF_LOAD)
	 */
	private String loadType;

	/**
	 * 起运港
	 */
	private String startPort;

	/**
	 * 抵达港
	 */
	private String endPort;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * fob总价(FOB)
	 */
	private Double fobValue;

	/**
	 * 运输价
	 */
	private Double freight;

	/**
	 * 保险价
	 */
	private Double insurance;

	/**
	 * cif总价
	 */
	private Double cifValue;

	/**
	 * fob单价
	 */
	private Double fobUnit;

	/**
	 * 包装类型
	 */
	private String packageType;

	/**
	 * 出口地区
	 */
	private String exporterRegion;

	/**
	 * 包装数量
	 */
	private Double packages;

	/**
	 * 包装描述(PACKAGES_DESC)
	 */
	private String packageDesc;

	/**
	 * 跨国公司所在国
	 */
	private String transCorpCountry;

	/**
	 * 销售状态
	 */
	private String saleCondition;

	/**
	 * 经济活动中心
	 */
	private String economicZone;

	/**
	 * 出口商的主产业
	 */
	private String exporterEconomicKey;

	/**
	 * 运输文档编号
	 */
	private String transDocNumber;

	/**
	 * 运输文档日期
	 */
	private String transDocDate;

	/**
	 * 轮班号
	 */
	private String voyageNumber;

	/**
	 * 成交方式
	 */
	private String incoterms;

	/**
	 * 支付方式
	 */
	private String payment;

	/**
	 * 日期
	 */
	private String date;
	private Double num = 1.0;
	public Integer getChileExportId() {
		return chileExportId;
	}
	public void setChileExportId(Integer chileExportId) {
		this.chileExportId = chileExportId;
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
	public String getControlId() {
		return controlId;
	}
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	public String getCustoms() {
		return customs;
	}
	public void setCustoms(String customs) {
		this.customs = customs;
	}
	public String getExporterId() {
		return exporterId;
	}
	public void setExporterId(String exporterId) {
		this.exporterId = exporterId;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
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
	public String getVarietey() {
		return varietey;
	}
	public void setVarietey(String varietey) {
		this.varietey = varietey;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
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
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getLoadType() {
		return loadType;
	}
	public void setLoadType(String loadType) {
		this.loadType = loadType;
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
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
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
	public Double getFobUnit() {
		return fobUnit;
	}
	public void setFobUnit(Double fobUnit) {
		this.fobUnit = fobUnit;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getExporterRegion() {
		return exporterRegion;
	}
	public void setExporterRegion(String exporterRegion) {
		this.exporterRegion = exporterRegion;
	}
	public Double getPackages() {
		return packages;
	}
	public void setPackages(Double packages) {
		this.packages = packages;
	}
	public String getPackageDesc() {
		return packageDesc;
	}
	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
	}
	public String getTransCorpCountry() {
		return transCorpCountry;
	}
	public void setTransCorpCountry(String transCorpCountry) {
		this.transCorpCountry = transCorpCountry;
	}
	public String getSaleCondition() {
		return saleCondition;
	}
	public void setSaleCondition(String saleCondition) {
		this.saleCondition = saleCondition;
	}
	public String getEconomicZone() {
		return economicZone;
	}
	public void setEconomicZone(String economicZone) {
		this.economicZone = economicZone;
	}
	public String getExporterEconomicKey() {
		return exporterEconomicKey;
	}
	public void setExporterEconomicKey(String exporterEconomicKey) {
		this.exporterEconomicKey = exporterEconomicKey;
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
	public String getVoyageNumber() {
		return voyageNumber;
	}
	public void setVoyageNumber(String voyageNumber) {
		this.voyageNumber = voyageNumber;
	}
	public String getIncoterms() {
		return incoterms;
	}
	public void setIncoterms(String incoterms) {
		this.incoterms = incoterms;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
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

