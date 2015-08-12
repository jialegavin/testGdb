package com.njyb.gbdbase.model.datasearch.usa;
import java.io.Serializable;

@SuppressWarnings("serial")
public class UsaImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer usaImportId;

	/**
	 * 提单号
	 */
	private String blNumber;

	/**
	 * 运输编号
	 */
	private String transCode;

	/**
	 * 
	 */
	private String extend1;

	/**
	 * 
	 */
	private String extend2;

	/**
	 * 承运人代码
	 */
	private String carrierCode;

	/**
	 * 舶船IMO编号
	 */
	private String shipImo;

	/**
	 * 船舶名称
	 */
	private String shipName;

	/**
	 * 船舶运输编号
	 */
	private String shipTransCode;

	/**
	 * 日期（TRANS_DATE）
	 */
	private String date;

	/**
	 * 货载数量（件数TRANS_PACKAGES）
	 */
	private Double packages;

	/**
	 * 数量单位
	 */
	private String packagesUnit;

	/**
	 * 运输重量(TRANS_WEIGHT)
	 */
	private Double grossWeight;

	/**
	 * 重量单位
	 */
	private String weightUnit;

	/**
	 * 运输体积
	 */
	private String transVolume;

	/**
	 * 装货港(TRANS_PORTLOADING)
	 */
	private String startPort;

	/**
	 * 集装箱编号
	 */
	private String containerNo;

	/**
	 * 包裹编号
	 */
	private String packageNo;

	/**
	 * 流水号
	 */
	private String flowNo;

	/**
	 * 唛头
	 */
	private String contMarks;

	/**
	 * 次序号
	 */
	private String productOrder;

	/**
	 * 产品名称(PRODCUT_NAME)
	 */
	private String goodsDesc;

	/**a
	 * 产品数量
	 */
	private Double productQuantity;

	/**
	 * 产品重量
	 */
	private String productWeight;

	/**
	 * 出口商公司名称(SENDER_COMPANY_NAME)
	 */
	private String exporter;

	/**
	 * 出口商地址(SENDER_ADDRESS)
	 */
	private String exporterAddress;

	/**
	 * 出口商联系方式(SENDER_CONTACT)
	 */
	private String exporterContact;

	/**
	 * 出口商所在国(SENDER_COUNTRY)
	 */
	private String exporterCountry;

	/**
	 * 进口商公司名称(RECIPIENT_COMPANY_NAME)
	 */
	private String importer;

	/**
	 * 进口商地址(RECIPIENT_ADDRESS)
	 */
	private String importerAddress;

	/**
	 * 进口商联系方式(RECIPIENT_CONTACT)
	 */
	private String importerContact;

	/**
	 * 进口商所在国(RECIPIENT_COUNTRY)
	 */
	private String importerCountry;

	/**
	 * 通知方公司名称(NOTIFY_COMPANY_NAME)
	 */
	private String notifier;

	/**
	 * 通知方地址(NOTIFY_ADDRESS)
	 */
	private String notifierAddress;

	/**
	 * 通知方联系方式(NOTIFY_CONTACT)
	 */
	private String notifierContact;

	/**
	 * 通知方所在国(NOTIFY_COUNTRY)
	 */
	private String notifierCountry;

	public Integer getUsaImportId() {
		return usaImportId;
	}

	public void setUsaImportId(Integer usaImportId) {
		this.usaImportId = usaImportId;
	}

	public String getBlNumber() {
		return blNumber;
	}

	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getShipImo() {
		return shipImo;
	}

	public void setShipImo(String shipImo) {
		this.shipImo = shipImo;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipTransCode() {
		return shipTransCode;
	}

	public void setShipTransCode(String shipTransCode) {
		this.shipTransCode = shipTransCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPackages() {
		return packages;
	}

	public void setPackages(Double packages) {
		this.packages = packages;
	}

	public String getPackagesUnit() {
		return packagesUnit;
	}

	public void setPackagesUnit(String packagesUnit) {
		this.packagesUnit = packagesUnit;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getTransVolume() {
		return transVolume;
	}

	public void setTransVolume(String transVolume) {
		this.transVolume = transVolume;
	}

	public String getStartPort() {
		return startPort;
	}

	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}

	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getContMarks() {
		return contMarks;
	}

	public void setContMarks(String contMarks) {
		this.contMarks = contMarks;
	}

	public String getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(String productOrder) {
		this.productOrder = productOrder;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Double getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Double productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
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

	public String getExporterContact() {
		return exporterContact;
	}

	public void setExporterContact(String exporterContact) {
		this.exporterContact = exporterContact;
	}

	public String getExporterCountry() {
		return exporterCountry;
	}

	public void setExporterCountry(String exporterCountry) {
		this.exporterCountry = exporterCountry;
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

	public String getImporterContact() {
		return importerContact;
	}

	public void setImporterContact(String importerContact) {
		this.importerContact = importerContact;
	}

	public String getImporterCountry() {
		return importerCountry;
	}

	public void setImporterCountry(String importerCountry) {
		this.importerCountry = importerCountry;
	}

	public String getNotifier() {
		return notifier;
	}

	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}

	public String getNotifierAddress() {
		return notifierAddress;
	}

	public void setNotifierAddress(String notifierAddress) {
		this.notifierAddress = notifierAddress;
	}

	public String getNotifierContact() {
		return notifierContact;
	}

	public void setNotifierContact(String notifierContact) {
		this.notifierContact = notifierContact;
	}

	public String getNotifierCountry() {
		return notifierCountry;
	}

	public void setNotifierCountry(String notifierCountry) {
		this.notifierCountry = notifierCountry;
	}
}


