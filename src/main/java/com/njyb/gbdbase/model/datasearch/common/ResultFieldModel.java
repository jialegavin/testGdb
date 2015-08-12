package com.njyb.gbdbase.model.datasearch.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;
/**
 * 动态生成各国家搜索结果字段列的数据表格
 * @author honghao
 *
 */
@Component
public class ResultFieldModel implements Serializable{
	private static Map resultFieldMap = null;
	
	public static Map getResultFieldMap()
	{
		return resultFieldMap;
	}
	
	/**
	 * 设置resultFieldMap中属性的值
	 * @param map
	 * @throws IOException
	 */
	public static void setResultFieldMap(Map resultFieldMap) 
	{
		ResultFieldModel.resultFieldMap = resultFieldMap;
	}
}
