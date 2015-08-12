package com.njyb.gbdbase.model.sellcenter;
/**
 * 用户订单模型
 * @author chenhu
 * 2015年5月7日
 */
public class UserOrderModel {
   //订单ID
	private int orderId;
	//用户信息Id
	private int rid;
	//商品名称
	private String orderName;
	//订单总价
	private double orderSummary;
	//用户Id
	private int userId;
	//订单数量
	private int orderNum;
	//订单号
	private String orderNo;
	//订单状态
	private String orderStatus;
	//付款方式
	private String payType;
	//是否开发票
	private String invoiceType;
	//发票信息
	private String invoice;
	//付款人
	private String  payPepole;
	//订单时间
	private String orderTime;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public double getOrderSummary() {
		return orderSummary;
	}
	public void setOrderSummary(double orderSummary) {
		this.orderSummary = orderSummary;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getPayPepole() {
		return payPepole;
	}
	public void setPayPepole(String payPepole) {
		this.payPepole = payPepole;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
}
