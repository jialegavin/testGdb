package com.njyb.gbdbase.model.usermanagement;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户权限模板
 * @author chenhu
 * 2015年3月26日
 */
public class ConditionRightModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 权限主键
	 */
	private int rid;
	/**
	 * 用户ID
	 */
	private int userId;
	/**
	 * 用户购买的hscode
	 */
	private String byHsCode;
	/**
	 * 用户购买的产品描述
	 */
	private String byProductDesc;
	/**
	 * 用户购买的国家
	 */
	private String byCountry;
	

	/**
	 * 购买的权限起始时间
	 */
	private String startTime;
	/**
	 * 购买的结束时间
	 */
	private String endTime;
	/**
	 * 进出口类型
	 */
	private String iExportType;
	/**
	 * 权限类型
	 */
	private String rightType;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getByHsCode() {
		return byHsCode;
	}
	public void setByHsCode(String byHsCode) {
		this.byHsCode = byHsCode;
	}
	public String getByProductDesc() {
		return byProductDesc;
	}
	public void setByProductDesc(String byProductDesc) {
		this.byProductDesc = byProductDesc;
	}
	public String getByCountry() {
		return byCountry;
	}
	public void setByCountry(String byCountry) {
		this.byCountry = byCountry;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getiExportType() {
		return iExportType;
	}
	public void setiExportType(String iExportType) {
		this.iExportType = iExportType;
	}
	public String getRightType() {
		return rightType;
	}
	public void setRightType(String rightType) {
		this.rightType = rightType;
	}

}
