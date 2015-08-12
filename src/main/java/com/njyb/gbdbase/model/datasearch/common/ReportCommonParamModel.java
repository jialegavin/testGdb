package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.PageBeanUtil;
/**
 * 封装报表需要的参数
 * @author 贾红平
 *
 */
public class ReportCommonParamModel extends CommonParamModel {
	//报告大类分类类型 具体参见pramenumutil
	private String reportType;
	private String date;
	private String startDate;
	private String endDate;
	
	public ReportCommonParamModel(String[] feilds, String[] values,
			String country, String module, HttpServletRequest request,
			String reportType, String startDate, String endDate) {
		super(feilds, values, country, module, request);
		this.reportType = reportType;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

//	public String getDate() {
//		return date;
//	}
//
//	public void setDate(String date) {
//		this.date = date;
//	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public ReportCommonParamModel(String[] feilds, String[] values,
			String country, String module, HttpServletRequest request,
			String reportType) {
		super(feilds, values, country, module, request);
		this.reportType = reportType;
	}
}
