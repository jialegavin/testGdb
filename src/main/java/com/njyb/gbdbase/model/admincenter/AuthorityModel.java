package com.njyb.gbdbase.model.admincenter;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限model类
 * @author 章华才
 */
public class AuthorityModel implements Serializable{

	  /**
	   * 权限Id
	   */
	  private Integer rid;  
	  /**
	   * 用户Id
	   */
	  private int userid;
	  /**
	   * 购买国家hscode
	   */
	  private String byhscode;
	  /**
	   * 购买国家产品描述
	   */
	  private String byproduct_desc;
	  /**
	   * 购买国家
	   */
	  private String bycountry;
	  /**
	   * 数据检索起始时间
	   */
	  private String starttime;
	  /**
	   * 数据检索截止时间
	   */
	  private String endtime;
	  /**
	   * 购买国家的进口/出口数据
	   */
	  private String iexporttype;
	  /**
	   * 
	   */
	  private String rightType;
	  /**
	   * 是否打开历史数据（中国）
	   */
	  private int openHistoryData;
	  
	  
	  /**
	   * 历史开始时间
	   */
	  private String historyStartTime;
	  
	  
	  /**
	   * 历史结束时间
	   */
	  private String historyEndTime;
	  
	public String getHistoryEndTime() {
		return historyEndTime;
	}
	public void setHistoryEndTime(String historyEndTime) {
		this.historyEndTime = historyEndTime;
	}
	public String getHistoryStartTime() {
		return historyStartTime;
	}
	public void setHistoryStartTime(String historyStartTime) {
		this.historyStartTime = historyStartTime;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getByhscode() {
		return byhscode;
	}
	public void setByhscode(String byhscode) {
		this.byhscode = byhscode;
	}
	public String getByproduct_desc() {
		return byproduct_desc;
	}
	public void setByproduct_desc(String byproduct_desc) {
		this.byproduct_desc = byproduct_desc;
	}
	public String getBycountry() {
		return bycountry;
	}
	public void setBycountry(String bycountry) {
		this.bycountry = bycountry;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getIexporttype() {
		return iexporttype;
	}
	public void setIexporttype(String iexporttype) {
		this.iexporttype = iexporttype;
	}
	public String getRightType() {
		return rightType;
	}
	public void setRightType(String rightType) {
		this.rightType = rightType;
	}
	public int getOpenHistoryData() {
		return openHistoryData;
	}
	public void setOpenHistoryData(int openHistoryData) {
		this.openHistoryData = openHistoryData;
	}
	  
	  
	  
	
}
