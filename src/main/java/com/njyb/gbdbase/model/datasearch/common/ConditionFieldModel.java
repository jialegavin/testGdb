package com.njyb.gbdbase.model.datasearch.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;
/**
 * 动态生成各国家查询条件模板工具类
 * @author honghao
 *
 */
@Component
public class ConditionFieldModel implements Serializable{
	private static Map conditionFieldMap = null;
	
	
	public static Map getConditionFieldMap() 
	{
		return conditionFieldMap;
	}
	
	/**
	 * 设置conditionFieldMap中属性的值
	 * @param map
	 * @throws IOException
	 */
	public static void setConditionFieldMap(Map map) 
	{
		conditionFieldMap = map;
	}
	
}
