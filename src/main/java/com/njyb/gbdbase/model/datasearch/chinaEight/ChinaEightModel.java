package com.njyb.gbdbase.model.datasearch.chinaEight;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ChinaEightModel implements Serializable {

	/**
	 *  
	 */
	private Integer chinaEightId;

	/**
	 * 进出口类型
	 */
	private String tradeType;

	/**
	 * 海关编码
	 */
	private String hscode;

	/**
	 * 商品名称(productname)
	 */
	private String goodsDesc;

	/**
	 * 时间：月度(monthly)
	 */
	private String date;

	/**
	 * 企业编码(companycode)
	 */
	private String companyCode;

	/**
	 * 企业名称(companyname)
	 */
	private String companyName;

	/**
	 * 收发货地/生产地(productplace)
	 */
	private String productPlace;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 金额(money,借用CIF_VALUE名称，翻译为金额)
	 */
	private Double cifValue;

	/**
	 * 单价(借用CIF_UNIT名称，翻译为单价：unitprice)
	 */
	private Double cifUnit;

	/**
	 * 产销国(productsalcountry)
	 */
	private String originCountry;

	/**
	 * 中转国(transitcountry)
	 */
	private String transitcountry;

	/**
	 * 报关口岸(customsports)
	 */
	private String startPort;

	/**
	 * 贸易方式(trade)
	 */
	private String tradeMethod;

	/**
	 * 运输方式(transportmode)
	 */
	private String transType;

	/**
	 * 计量单位(measureunits)
	 */
	private String measureunits;

	/**
	 * 电话(phone)
	 */
	private String phone;

	/**
	 * 传真(fax)
	 */
	private String fax;

	/**
	 * 邮编(zip)
	 */
	private String zip;

	/**
	 * 电子邮件(email)
	 */
	private String email;

	/**
	 * 联系人(contact)
	 */
	private String contact;

	/**
	 * 公司性质(companyproperty)
	 */
	private String companyProperty;

	/**
	 * 地址(address)
	 */
	private String address;

	private Double num = 1.0;

	public Integer getChinaEightId() {
		return chinaEightId;
	}

	public void setChinaEightId(Integer chinaEightId) {
		this.chinaEightId = chinaEightId;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProductPlace() {
		return productPlace;
	}

	public void setProductPlace(String productPlace) {
		this.productPlace = productPlace;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
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

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getTransitcountry() {
		return transitcountry;
	}

	public void setTransitcountry(String transitcountry) {
		this.transitcountry = transitcountry;
	}

	public String getStartPort() {
		return startPort;
	}

	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}

	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getMeasureunits() {
		return measureunits;
	}

	public void setMeasureunits(String measureunits) {
		this.measureunits = measureunits;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCompanyProperty() {
		return companyProperty;
	}

	public void setCompanyProperty(String companyProperty) {
		this.companyProperty = companyProperty;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}

