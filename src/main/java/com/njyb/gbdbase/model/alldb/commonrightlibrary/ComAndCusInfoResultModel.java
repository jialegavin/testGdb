package com.njyb.gbdbase.model.alldb.commonrightlibrary;

import java.io.Serializable;
import java.util.Date;

import com.njyb.gbdbase.model.personalcenter.CommonTotalModel;

/**
 * 竞争对手<br>
 * 客户信息<br>
 * 共用model
 * @author WangBo 2015年4月7日 CompetitorAndCustomerInfoResultModel.java
 */
public class ComAndCusInfoResultModel extends CommonTotalModel implements Serializable {

	private static final long serialVersionUID = 1L;

	// 客户编号
	private Integer collectionId;
	// 客户名称(公司名称)
	private String companyName;
	// 备选名称
	private String alternativeName;
	// 公司地址
	private String address;
	// 公司邮箱
	private String mailBox;
	// 公司联系人
	private String contact;
	// 公司传真号码
	private String fax;
	// 客户类型（我的客户/我的竞争对手）
	private String userType;
	// 用户编号
	private Integer userId;
	// 客户等级（大客户/小客户）
	// 联系人号码
	private String userGrade;
	private String tel;
	// 国家
	private String country;
	// 备选电子邮件
	private String alternativeEmail;
	// 网址
	private String website;
	// 跟踪状态
	private String trackingStatus;
	// 联系次数
	private Integer contactNum;
	// 备注
	private String remark;
	// 邮编
	private String zip;
	// 数据来源
	private String datasource;
	// 客户价值
	private String customerValue;
	// 最后联系
	private String finallyContact;
	// 收货人代码
	private String consigneecode;
	// 结算人代码
	private String settlementcode;
	// 发货人代码
	private String consignorcode;
	// 制造商
	private String manufacturers;
	// 插入时间
	private Date addTime;
	// 不可操作 修改,删除
	private int isOperate;
	//条件ID 用在删除 竞争对手 和 我的客户信息
	private String id;
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAlternativeName() {
		return alternativeName;
	}
	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMailBox() {
		return mailBox;
	}
	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAlternativeEmail() {
		return alternativeEmail;
	}
	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTrackingStatus() {
		return trackingStatus;
	}
	public void setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
	}
	public Integer getContactNum() {
		return contactNum;
	}
	public void setContactNum(Integer contactNum) {
		this.contactNum = contactNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public String getCustomerValue() {
		return customerValue;
	}
	public void setCustomerValue(String customerValue) {
		this.customerValue = customerValue;
	}
	public String getFinallyContact() {
		return finallyContact;
	}
	public void setFinallyContact(String finallyContact) {
		this.finallyContact = finallyContact;
	}
	public String getConsigneecode() {
		return consigneecode;
	}
	public void setConsigneecode(String consigneecode) {
		this.consigneecode = consigneecode;
	}
	public String getSettlementcode() {
		return settlementcode;
	}
	public void setSettlementcode(String settlementcode) {
		this.settlementcode = settlementcode;
	}
	public String getConsignorcode() {
		return consignorcode;
	}
	public void setConsignorcode(String consignorcode) {
		this.consignorcode = consignorcode;
	}
	public String getManufacturers() {
		return manufacturers;
	}
	public void setManufacturers(String manufacturers) {
		this.manufacturers = manufacturers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getIsOperate() {
		return isOperate;
	}
	public void setIsOperate(int isOperate) {
		this.isOperate = isOperate;
	}
}