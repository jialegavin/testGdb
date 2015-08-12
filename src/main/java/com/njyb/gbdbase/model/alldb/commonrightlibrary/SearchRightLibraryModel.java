package com.njyb.gbdbase.model.alldb.commonrightlibrary;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Component;
/**
 * 权库构造查询条件所有的参数信息
 * @author WangBo
 * 2015年4月13日
 * SearchRightLibraryModel.java
 */
@Component
public class SearchRightLibraryModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//配置Map
	private static Map<String,Object> propertiesMap = null;
	
	/**
	 * 获取配置的Map
	 * @return
	 */
	public static Map<String,Object> getPropertiesMap() {
		return propertiesMap;
	}

	/**
	 * 设置map中属性的值
	 * 
	 * @param map
	 * @throws IOException
	 */
	public static void setPropertiesMap(Map<String,Object> map) throws IOException {
		propertiesMap = map;
	}
}
