package com.njyb.gbdbase.model.admincenter;

import java.io.Serializable;

/**
 * 用户消费记录实体类
 * @author XL
 * @createTime 2015-06-24
 *
 */
public class UserConsumingRecordsModel implements Serializable{
	private static final long serialVersionUID = 1L;
	//主键id
	private int id;
	//用户id
	private int userId;
	//消费总金额
	private double totalmoney;
	//创建日期
	private String createtime;
	//备注
	private String remarks;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
