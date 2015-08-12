package com.njyb.gbdbase.model.datasearch.ecuador;
import java.io.Serializable;

@SuppressWarnings("serial")
public class EcuadorImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer ecuadorImportId;

	/**
	 * 抵达时间
	 */
	private String arrivalDate;

	/**
	 * 注册时间
	 */
	private String date;

	/**
	 * 贸易类型
	 */
	private String tradeType;

	/**
	 * 会签号
	 */
	private String refNo;

	/**
	 * 报关单号
	 */
	private String declarationNumber;

	/**
	 * 项号
	 */
	private String itemNumber;

	/**
	 * 进口商编号
	 */
	private String importerId;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 销售国
	 */
	private String salesCountry;

	/**
	 * 起运国(SHIPPING_COUNTRY)
	 */
	private String startCountry;

	/**
	 * 起运港（PORT）
	 */
	private String startPort;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 海关编码（HS_CODE）
	 */
	private String hscode;

	/**
	 * HS章注
	 */
	private String hsDesc;

	/**
	 * 产品描述（PRODUCT_DESC）
	 */
	private String goodsDesc;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 货物状态
	 */
	private String goodsCondition;

	/**
	 * 货柜量
	 */
	private String container;

	/**
	 * 件数(PACKAGES)
	 */
	private Double packages;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * 从价税
	 */
	private Double adValorem;

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

	/**
	 * 出口商(SHIPPER)
	 */
	private String exporter;

	/**
	 * 海关代理
	 */
	private String customsAgent;

	/**
	 * 运输代理
	 */
	private String transAgent;

	/**
	 * 运输公司
	 */
	private String transCorp;

	/**
	 * 船名
	 */
	private String shipName;

	/**
	 * 提单号
	 */
	private String blNumber;

	/**
	 * 
	 */
	private String emb;

	/**
	 * 仓储地
	 */
	private String deposit;

	private Double num = 1.0;

	public Integer getEcuadorImportId() {
		return ecuadorImportId;
	}

	public void setEcuadorImportId(Integer ecuadorImportId) {
		this.ecuadorImportId = ecuadorImportId;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getDeclarationNumber() {
		return declarationNumber;
	}

	public void setDeclarationNumber(String declarationNumber) {
		this.declarationNumber = declarationNumber;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
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

	public String getStartCountry() {
		return startCountry;
	}

	public void setStartCountry(String startCountry) {
		this.startCountry = startCountry;
	}

	public String getStartPort() {
		return startPort;
	}

	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
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

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGoodsCondition() {
		return goodsCondition;
	}

	public void setGoodsCondition(String goodsCondition) {
		this.goodsCondition = goodsCondition;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public Double getPackages() {
		return packages;
	}

	public void setPackages(Double packages) {
		this.packages = packages;
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

	public Double getAdValorem() {
		return adValorem;
	}

	public void setAdValorem(Double adValorem) {
		this.adValorem = adValorem;
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

	public String getExporter() {
		return exporter;
	}

	public void setExporter(String exporter) {
		this.exporter = exporter;
	}

	public String getCustomsAgent() {
		return customsAgent;
	}

	public void setCustomsAgent(String customsAgent) {
		this.customsAgent = customsAgent;
	}

	public String getTransAgent() {
		return transAgent;
	}

	public void setTransAgent(String transAgent) {
		this.transAgent = transAgent;
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

	public String getBlNumber() {
		return blNumber;
	}

	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}

	public String getEmb() {
		return emb;
	}

	public void setEmb(String emb) {
		this.emb = emb;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}
