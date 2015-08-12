package com.njyb.gbdbase.model.datasearch.korea;
import java.io.Serializable;

@SuppressWarnings("serial")
public class KoreaModel implements Serializable {

	/**
	 * 主键id
	 */
	private Integer koreaId;

	/**
	 * 海关编码
	 */
	private String hscode;
	
	/**
	 * 产品描述
	 */
	private String goodsDesc;

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 年
	 */
	private String year;

	/**
	 * 月
	 */
	private String month;

	/**
	 * 贸易类型
	 */
	private String tradeType;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 首席执行官
	 */
	private String ceo;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 电子邮件
	 */
	private String email;

	/**
	 * 月度
	 */
	private String date;
	private Double num = 1.0;
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public Integer getKoreaId() {
		return koreaId;
	}
	public void setKoreaId(Integer koreaId) {
		this.koreaId = koreaId;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}

