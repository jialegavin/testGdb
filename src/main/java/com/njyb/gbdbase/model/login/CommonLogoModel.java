package com.njyb.gbdbase.model.login;

/**
 * 通用用户登录模型对象
 * @author XL
 *
 */
public class CommonLogoModel {
	//域名
	private String domain;
	//logo路径
	private String logoUrl;
	//登录后的logo路径
	private String intoLogoUrl;
	//电子邮箱
	private String companyEmail;
	//电话
	private String tel;
	//公司名称
	private String comanyName;
	//跳转路径
	private String forwardJsp;
	//备案号
	private String recordNumber;
	//客服中心图片
	private String customImgUrl;
	//邮箱密码
	private String emailPwd;
	//传真
	private String fax;
	//地址
	private String address;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getComanyName() {
		return comanyName;
	}
	public void setComanyName(String comanyName) {
		this.comanyName = comanyName;
	}
	public String getForwardJsp() {
		return forwardJsp;
	}
	public void setForwardJsp(String forwardJsp) {
		this.forwardJsp = forwardJsp;
	}
	public String getIntoLogoUrl() {
		return intoLogoUrl;
	}
	public void setIntoLogoUrl(String intoLogoUrl) {
		this.intoLogoUrl = intoLogoUrl;
	}
	public String getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String getCustomImgUrl() {
		return customImgUrl;
	}
	public void setCustomImgUrl(String customImgUrl) {
		this.customImgUrl = customImgUrl;
	}
	public String getEmailPwd() {
		return emailPwd;
	}
	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
