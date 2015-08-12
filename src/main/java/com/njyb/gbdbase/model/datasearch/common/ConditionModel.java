package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/**
 * 保存用户上一次的查询条件
 * @author 贾红平
 *
 */
@SuppressWarnings("serial")
@Component
public class ConditionModel implements Serializable{
	//上一次查询字段名称
	public  static String lastTimeFields="";
	//上一次查询字段对应的值
	public  static String lastTimeValues="";
	//上一次查询的国家
	public  static String lastTimeCountry="";
	//报表查询参数对象
	public static ReportCommonParamModel reportModel;
	
	public static ReportCommonParamModel getReportModel() {
		return reportModel;
	}
	public static void setReportModel(ReportCommonParamModel reportModel) {
		ConditionModel.reportModel = reportModel;
	}
	public static String getLastTimeFields() {
		return lastTimeFields;
	}
	public static void setLastTimeFields(String lastTimeFields) {
		ConditionModel.lastTimeFields = lastTimeFields;
	}
	public static String getLastTimeValues() {
		return lastTimeValues;
	}
	public static void setLastTimeValues(String lastTimeValues) {
		ConditionModel.lastTimeValues = lastTimeValues;
	}
	public static String getLastTimeCountry() {
		return lastTimeCountry;
	}
	public static void setLastTimeCountry(String lastTimeCountry) {
		ConditionModel.lastTimeCountry = lastTimeCountry;
	}
	
	
	
}
