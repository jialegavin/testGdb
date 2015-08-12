package com.njyb.gbdbase.model.paypal;


import java.io.Serializable;
/**
 * Used to manage the user charge information
 * @author hy_ho_000
 *
 */

public class UserChargeModel implements Serializable{
	private String username;
	private String userip;
	private String userservice;
	private String date;
	private String paymentID;
	public void setUsername(String username){
		this.username=username;
	}
	public void setUserip(String userip){
		this.userip=userip;
	}
	public void setUsermode(String model){
		this.userservice=model;
	}
	public void setDate(String date){
		this.date=date;
	}
	public void setPaymentID(String id){
		this.paymentID=id;
	}
	public String getUsername(){
		return username;
	}
	public String getUserip(){
		return userip;
	}
	public String getUsermode(){
		return userservice;
	}
	public String getPaymentId(){
		return paymentID;
	}


}

