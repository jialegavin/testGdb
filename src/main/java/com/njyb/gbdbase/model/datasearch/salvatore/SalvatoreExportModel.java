package com.njyb.gbdbase.model.datasearch.salvatore;
import java.io.Serializable;

@SuppressWarnings("serial")
public class SalvatoreExportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer salvatoreExportId;

	/**
	 * 离开时间
	 */
	private String date;

	/**
	 * 出口商
	 */
	private String exporter;

	/**
	 * 出口商地址
	 */
	private String exporterAddr;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 进口商地址
	 */
	private String importerAddr;

	/**
	 * 通知人
	 */
	private String notifier;

	/**
	 * 通知人地址
	 */
	private String notifierAddr;

	/**
	 * 提单号
	 */
	private String blNumber;

	/**
	 * 船次
	 */
	private Double voyageNumber;

	/**
	 * 承运人
	 */
	private String carrier;

	/**
	 * 起运港
	 */
	private String startPort;

	/**
	 * 抵达港
	 */
	private String endPort;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 目的国
	 */
	private String destCountry;

	/**
	 * 船名
	 */
	private String vesselName;

	/**
	 * 船旗国,轮船所在国
	 */
	private String vesselCountry;

	/**
	 * 国际海事组织代码
	 */
	private String imoCode;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 
	 */
	private String goodsDesc;
	private Double num = 1.0;
	public Integer getSalvatoreExportId() {
		return salvatoreExportId;
	}
	public void setSalvatoreExportId(Integer salvatoreExportId) {
		this.salvatoreExportId = salvatoreExportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
	}
	public String getExporterAddr() {
		return exporterAddr;
	}
	public void setExporterAddr(String exporterAddr) {
		this.exporterAddr = exporterAddr;
	}
	public String getImporter() {
		return importer;
	}
	public void setImporter(String importer) {
		this.importer = importer;
	}
	public String getImporterAddr() {
		return importerAddr;
	}
	public void setImporterAddr(String importerAddr) {
		this.importerAddr = importerAddr;
	}
	public String getNotifier() {
		return notifier;
	}
	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}
	public String getNotifierAddr() {
		return notifierAddr;
	}
	public void setNotifierAddr(String notifierAddr) {
		this.notifierAddr = notifierAddr;
	}
	public String getBlNumber() {
		return blNumber;
	}
	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}
	public Double getVoyageNumber() {
		return voyageNumber;
	}
	public void setVoyageNumber(Double voyageNumber) {
		this.voyageNumber = voyageNumber;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
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
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getVesselCountry() {
		return vesselCountry;
	}
	public void setVesselCountry(String vesselCountry) {
		this.vesselCountry = vesselCountry;
	}
	public String getImoCode() {
		return imoCode;
	}
	public void setImoCode(String imoCode) {
		this.imoCode = imoCode;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

