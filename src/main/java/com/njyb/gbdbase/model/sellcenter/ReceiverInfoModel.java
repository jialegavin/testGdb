package com.njyb.gbdbase.model.sellcenter;
/**
 * 接收人信息
 * @author chenhu
 * 2015年5月7日
 */
public class ReceiverInfoModel {
	//信息ID
  private int rid;
  //公司名称
  private String company;
  //用户Id
  private int userId;
  //收货人
  private String consignee;
  //国家
  private String country;
  //邮政编码
  private String  postalNum;
  //省份
  private String province;
  //手机
  private String phone;
  //地址
  private String address;
  //操作标志
  private boolean operFlag;
  //是否是默认地址
  private boolean status;
  //城市
  private String city;
  
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalNum() {
		return postalNum;
	}
	public void setPostalNum(String postalNum) {
		this.postalNum = postalNum;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean getOperFlag() {
		return operFlag;
	}
	public void setOperFlag(boolean operFlag) {
		this.operFlag = operFlag;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
