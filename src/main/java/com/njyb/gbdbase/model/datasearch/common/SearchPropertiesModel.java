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
@SuppressWarnings({ "rawtypes", "serial" })
public class SearchPropertiesModel implements Serializable{
	
	private static Map propertiesMap = null;
	
	public static Map getPropertiesMap() 
	{
		return propertiesMap;
	}
	/**
	 * 设置map中属性的值
	 * @param map
	 * @throws IOException
	 */
	public static void setPropertiesMap(Map map) throws IOException
	{
		propertiesMap = map;
	}
}
