package com.njyb.gbdbase.model.datasearch.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;
/**
 * 存储查询配置文件里面所有的参数信息
 * @author 贾红平
 *
 */
@Component
public class ReportPropertiesModel implements Serializable{
	/**
	 * 保证序列化版本一致性
	 */
	private static final long serialVersionUID = 1L;
	private static Map reportFieldMap = null;

	public static Map getReportFieldMap() {
		return reportFieldMap;
	}

	public static void setReportFieldMap(Map reportFieldMap) {
		ReportPropertiesModel.reportFieldMap = reportFieldMap;
	}
	 
}
