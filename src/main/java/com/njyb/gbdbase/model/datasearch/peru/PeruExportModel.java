package com.njyb.gbdbase.model.datasearch.peru;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PeruExportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer peruExportId;

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
	 * 编号
	 */
	private String controlId;

	/**
	 * 
	 */
	private String serie;

	/**
	 * 海关城市
	 */
	private String customs;

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
	 * 出口商所在城市
	 */
	private String exporterCity;

	/**
	 * 出口商所在国家/州
	 */
	private String exporterState;

	/**
	 * 出口商所在区(县)
	 */
	private String exporterDistrict;

	/**
	 * 进口商电话
	 */
	private String exporterTel;

	/**
	 * 进口商传真
	 */
	private String exporterFax;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * HS章注（HS_CODE_DESC）
	 */
	private String hsDesc;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 
	 */
	private String desc1;

	/**
	 * 
	 */
	private String desc2;

	/**
	 * 
	 */
	private String desc3;

	/**
	 * 
	 */
	private String desc4;

	/**
	 * FOB金额(FOB)
	 */
	private Double fobValue;

	/**
	 * 净重
	 */
	private Double netWeight;

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
	 * FOB单价
	 */
	private Double fobUnit;

	/**
	 * 物理数量
	 */
	private Double physicalQuantity;

	/**
	 * 物理数量单位(UNIT_OF_PHYSICAL_QUANTITY)
	 */
	private String physicalQuantityUnit;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 银行
	 */
	private String bank;

	/**
	 * 目的国
	 */
	private String destCountry;

	/**
	 * 目的港（DEST_PORT）
	 */
	private String endPort;

	/**
	 * 海关代理
	 */
	private String customsAgent;

	/**
	 * 运输公司
	 */
	private String transCorp;

	/**
	 * 船名
	 */
	private String shipName;

	/**
	 * 货物状态
	 */
	private String merchandiseState;

	private Double num = 1.0;

	public Integer getPeruExportId() {
		return peruExportId;
	}

	public void setPeruExportId(Integer peruExportId) {
		this.peruExportId = peruExportId;
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

	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
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

	public String getExporterState() {
		return exporterState;
	}

	public void setExporterState(String exporterState) {
		this.exporterState = exporterState;
	}

	public String getExporterDistrict() {
		return exporterDistrict;
	}

	public void setExporterDistrict(String exporterDistrict) {
		this.exporterDistrict = exporterDistrict;
	}

	public String getExporterTel() {
		return exporterTel;
	}

	public void setExporterTel(String exporterTel) {
		this.exporterTel = exporterTel;
	}

	public String getExporterFax() {
		return exporterFax;
	}

	public void setExporterFax(String exporterFax) {
		this.exporterFax = exporterFax;
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

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public String getDesc4() {
		return desc4;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}

	public Double getFobValue() {
		return fobValue;
	}

	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
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

	public Double getFobUnit() {
		return fobUnit;
	}

	public void setFobUnit(Double fobUnit) {
		this.fobUnit = fobUnit;
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

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
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

	public String getCustomsAgent() {
		return customsAgent;
	}

	public void setCustomsAgent(String customsAgent) {
		this.customsAgent = customsAgent;
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

	public String getMerchandiseState() {
		return merchandiseState;
	}

	public void setMerchandiseState(String merchandiseState) {
		this.merchandiseState = merchandiseState;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}
