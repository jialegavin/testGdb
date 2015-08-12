package com.njyb.gbdbase.model.datasearch.ecuador;
import java.io.Serializable;

@SuppressWarnings("serial")
public class EcuadorExportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer ecuadorExportId;

	/**
	 * 日期(REG_DATE)
	 */
	private String date;

	/**
	 * 出口类型(TYPE_OF_EXPORT)
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
	 * 出口商编号
	 */
	private String exporterId;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 目的国
	 */
	private String destCountry;

	/**
	 * 抵达港(PORT)
	 */
	private String endPort;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * HS章注
	 */
	private String hsDesc;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

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
	 * FOB金额(FOB)
	 */
	private Double fobValue;

	/**
	 * 进口商，收货人（CONSIGNEE）
	 */
	private String importer;

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

	private Double num = 1.0;

	public Integer getEcuadorExportId() {
		return ecuadorExportId;
	}

	public void setEcuadorExportId(Integer ecuadorExportId) {
		this.ecuadorExportId = ecuadorExportId;
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

	public Double getFobValue() {
		return fobValue;
	}

	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
	}

	public String getImporter() {
		return importer;
	}

	public void setImporter(String importer) {
		this.importer = importer;
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

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}
