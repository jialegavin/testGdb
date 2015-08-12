package com.njyb.gbdbase.model.datasearch.india;
import java.io.Serializable;

@SuppressWarnings("serial")
public class  IndiaImportModel implements Serializable {

	/**
	 * 
	 */
	private Integer indiaImportId;

	/**
	 * 日期(REG_DATE)
	 */
	private String date;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * 进口商编号
	 */
	private String importerId;

	/**
	 * 进口商(IMPORTER_NAME)
	 */
	private String importer;

	/**
	 * 进口商地址1(IMPORTER_ADDRESS_ONE)
	 */
	private String importerAddressOne;

	/**
	 * 进口商地址2(IMPORTER_ADDRESS_TWO)
	 */
	private String importerAddressTwo;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 邮编
	 */
	private String postal;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 电子邮件
	 */
	private String email;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位(UNIT_OF_QUANTITY)
	 */
	private String quantityUnit;

	/**
	 * 卢布价(计算添加)
	 */
	private Double inrValue;

	/**
	 * 卢布单价(UNIT_PRICE_INV)
	 */
	private Double inrUnit;

	/**
	 * 美元单价(UNIT_PRICE_USD)
	 */
	private Double usdUnit;

	/**
	 * 欧元单价(UNIT_PRICE_EURO)
	 */
	private Double euroUnit;

	/**
	 * 海关代理
	 */
	private String customAgent;

	/**
	 * 出口商(SUPPLIER_NAME)
	 */
	private String exporter;

	/**
	 * 出口商地址(SUPPLIER_ADDRESS)
	 */
	private String exporterAddress;

	/**
	 * 出口商所在国(SUPPLIER_COUNTRY)
	 */
	private String originCountry;

	/**
	 * 起运港(ORIGIN_PORT)
	 */
	private String startPort;

	/**
	 * 联系人1(CONTACT_1)
	 */
	private String contactOne;

	/**
	 * 联系人2(CONTACT_2)
	 */
	private String contactTwo;

	/**
	 * 银行
	 */
	private String bank;
	private Double num = 1.0;
	public Integer getIndiaImportId() {
		return indiaImportId;
	}
	public void setIndiaImportId(Integer indiaImportId) {
		this.indiaImportId = indiaImportId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
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
	public String getImporterAddressOne() {
		return importerAddressOne;
	}
	public void setImporterAddressOne(String importerAddressOne) {
		this.importerAddressOne = importerAddressOne;
	}
	public String getImporterAddressTwo() {
		return importerAddressTwo;
	}
	public void setImporterAddressTwo(String importerAddressTwo) {
		this.importerAddressTwo = importerAddressTwo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
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
	public Double getInrValue() {
		return inrValue;
	}
	public void setInrValue(Double inrValue) {
		this.inrValue = inrValue;
	}
	public Double getInrUnit() {
		return inrUnit;
	}
	public void setInrUnit(Double inrUnit) {
		this.inrUnit = inrUnit;
	}
	public Double getUsdUnit() {
		return usdUnit;
	}
	public void setUsdUnit(Double usdUnit) {
		this.usdUnit = usdUnit;
	}
	public Double getEuroUnit() {
		return euroUnit;
	}
	public void setEuroUnit(Double euroUnit) {
		this.euroUnit = euroUnit;
	}
	public String getCustomAgent() {
		return customAgent;
	}
	public void setCustomAgent(String customAgent) {
		this.customAgent = customAgent;
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
	
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getStartPort() {
		return startPort;
	}
	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}
	public String getContactOne() {
		return contactOne;
	}
	public void setContactOne(String contactOne) {
		this.contactOne = contactOne;
	}
	public String getContactTwo() {
		return contactTwo;
	}
	public void setContactTwo(String contactTwo) {
		this.contactTwo = contactTwo;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

