package com.njyb.gbdbase.model.personalcenter.alipay;

import java.io.Serializable;
/**
 * 支付备案
 * @author WangBo
 * 2015年5月14日
 * AliPalyFilingModel.java
 */
public class AliPalyFilingModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;						//id
	
	private String out_trade_no;		//商户订单号
	
	private String trade_no;			//支付宝交易号
	
	private String trade_status;		//交易状态
	
	private boolean verify_result;		//计算得出通知验证结果
	
	private int userId;					//用户Id
	
	private String aliPalyTime;			//支付时间
	
	private String payType;				//支付类型 1.支付宝 2.贝宝 3.网银

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public boolean isVerify_result() {
		return verify_result;
	}

	public void setVerify_result(boolean verify_result) {
		this.verify_result = verify_result;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAliPalyTime() {
		return aliPalyTime;
	}

	public void setAliPalyTime(String aliPalyTime) {
		this.aliPalyTime = aliPalyTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}