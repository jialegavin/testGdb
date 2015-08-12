package com.njyb.gbdbase.model.personalcenter.alipay;

/**
 * 支付宝<br>
 * 支付参数封装Model
 * @author WangBo
 * 2015年3月30日
 * AliPalyEntity.java
 */
public class AliPalyEntity {
	//支付类型
	private String payment_type;	//必填
	//服务器异步通知页面路径
	private String notify_url;			
	//页面跳转同步通知页面路径
	private String return_url;
	//卖家支付宝帐户
	private String seller_email;//必填
	//商户订单号
	private String out_trade_no;
	//订单名称
	private String subject;	//必填
	//付款金额
	private String total_fee;	//必填
	//订单描述
	private String body;
	//商品展示地址
	private String show_url;
	//防钓鱼时间戳
	private String anti_phishing_key;
	//客户端的IP地址
	private String exter_invoke_ip;
	//非局域网的外网IP地址，如：221.0.0.1
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String paymentType) {
		payment_type = paymentType;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String returnUrl) {
		return_url = returnUrl;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String sellerEmail) {
		seller_email = sellerEmail;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String totalFee) {
		total_fee = totalFee;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String showUrl) {
		show_url = showUrl;
	}
	public String getAnti_phishing_key() {
		return anti_phishing_key;
	}
	public void setAnti_phishing_key(String antiPhishingKey) {
		anti_phishing_key = antiPhishingKey;
	}
	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}
	public void setExter_invoke_ip(String exterInvokeIp) {
		exter_invoke_ip = exterInvokeIp;
	}
}