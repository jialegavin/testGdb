package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;
/**
 * 导出各国报表汇总excel相关字段
 * @author XL
 *
 */
@Component
public class ExportResultFieldModel implements Serializable{
	private static Map exportResultFieldMap = null;
	
	public static Map getExportResultFieldMap() {
		return exportResultFieldMap;
	}

	public static void setExportResultFieldMap(Map exportResultFieldMap) {
		ExportResultFieldModel.exportResultFieldMap = exportResultFieldMap;
	}

}
