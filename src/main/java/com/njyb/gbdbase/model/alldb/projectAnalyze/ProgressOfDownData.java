package com.njyb.gbdbase.model.alldb.projectAnalyze;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * 权库下载进度条model
 * 
 * @author WangBo 2015年5月14日 ProgressOfDownData.java
 */
public class ProgressOfDownData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static ProgressOfDownData pro = new ProgressOfDownData();
	
	private ProgressOfDownData(){
	}
	
	public static ProgressOfDownData getProgressOfDownData(){
		if (null == pro) {
			pro = new ProgressOfDownData();
		}
		return pro;
	}
	
	private String country;		// 全库下载
	
	private int proIndex;		// 进度条的值   针对 贸易情报
	
	public void getRequestOfCountry(HttpServletRequest request){
		this.country = (String) request.getAttribute("country");
	}
	
	public int getProIndex() {
		return proIndex;
	}

	public void setProIndex(int proIndex) {
		this.proIndex = proIndex;
	}

	public String getCountry(){
		return country;
	}
	
	public static ProgressOfDownData getPro() {
		return pro;
	}

	public static void setPro(ProgressOfDownData pro) {
		ProgressOfDownData.pro = pro;
	}

	/**
	 * 贸易情报的进度条值
	 * @param request
	 */
	public void getDataSearchPro(HttpServletRequest request) {
		int totalPro = Integer.valueOf(request.getAttribute("totalPro").toString());
		int singleTemp = Integer.valueOf(request.getAttribute("singleTemp").toString());
		if (singleTemp == -1) {			//-1 代表结束
			setProIndex(100);
		} else {
			setProIndex((100 / totalPro) * singleTemp);
		}
	}
}