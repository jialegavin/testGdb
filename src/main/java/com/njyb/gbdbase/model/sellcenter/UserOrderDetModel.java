package com.njyb.gbdbase.model.sellcenter;
/**
 * 用户订单明细模型
 * @author chenhu
 * 2015年5月7日
 */
public class UserOrderDetModel {
   //订单明细Id
	private int detId;
	//订单Id
	private String orderNo;
	//价格 
	private double price;
	//次数
	private String times;
	//时间范围
	private String tlimit;
	//进出口类型
	private String ioType;
	//购买国家
	private String country;
	//产品描述
	private String prodesc;
	//海关编码
	private String hscode;
	//定制类型
	private String byType;
	public int getDetId() {
		return detId;
	}
	public void setDetId(int detId) {
		this.detId = detId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getTlimit() {
		return tlimit;
	}
	public void setTlimit(String tlimit) {
		this.tlimit = tlimit;
	}
	public String getIoType() {
		return ioType;
	}
	public void setIoType(String ioType) {
		this.ioType = ioType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProdesc() {
		return prodesc;
	}
	public void setProdesc(String prodesc) {
		this.prodesc = prodesc;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public String getByType() {
		return byType;
	}
	public void setByType(String byType) {
		this.byType = byType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
}
