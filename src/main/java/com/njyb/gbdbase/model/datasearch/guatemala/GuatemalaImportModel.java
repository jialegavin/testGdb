package com.njyb.gbdbase.model.datasearch.guatemala;
import java.io.Serializable;

@SuppressWarnings("serial")
public class GuatemalaImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer guatemalaImportId;

	/**
	 * 抵达时间(arrival_date)
	 */
	private String date;

	/**
	 * 进口商(consigneename)
	 */
	private String importer;

	/**
	 * 进口商地址(consignee_addr)
	 */
	private String importerAddr;

	/**
	 * 出口商(shippername)
	 */
	private String exporter;

	/**
	 * 出口商地址(shipper_addr)
	 */
	private String exporterAddr;

	/**
	 * 通知方(notifyname)
	 */
	private String notifier;

	/**
	 * 通知人地址(notify_addr)
	 */
	private String notifierAddr;

	/**
	 * 提单号(bill_of_lading)
	 */
	private String blNumber;

	/**
	 * 船次(voyaga_number)
	 */
	private String voyageNumber;

	/**
	 * 承运人(carrier)
	 */
	private String carrier;

	/**
	 * 起运港(departure_port)
	 */
	private String startPort;

	/**
	 * 抵达港(arrival_port)
	 */
	private String endPort;

	/**
	 * 原产国(origin_country)
	 */
	private String originCountry;

	/**
	 * 目的国(destination_country)
	 */
	private String destCountry;

	/**
	 * 船名(vessel_name)
	 */
	private String vesselName;

	/**
	 * 船旗国(vessel_country)
	 */
	private String vesselCountry;

	/**
	 * 国际海事组织代码(imo_code)
	 */
	private String imoCode;

	/**
	 * 数量(quantity)
	 */
	private Double quantity;

	/**
	 * 
	 */
	private String goodsDesc;
	private Double num = 1.0;
	public Integer getGuatemalaImportId() {
		return guatemalaImportId;
	}
	public void setGuatemalaImportId(Integer guatemalaImportId) {
		this.guatemalaImportId = guatemalaImportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getVoyageNumber() {
		return voyageNumber;
	}
	public void setVoyageNumber(String voyageNumber) {
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

