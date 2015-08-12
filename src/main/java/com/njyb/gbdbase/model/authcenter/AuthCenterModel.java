package com.njyb.gbdbase.model.authcenter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 不同用户各模块各功能权限配置中心实体类
 * @author honghao
 */
@Component
public class AuthCenterModel implements Serializable{
	private static Map authCenterMap = null;
	
	
	public static Map getAuthCenterMap() 
	{
		return authCenterMap;
	}
	
	/**
	 * 设置authCenterMap中属性的值
	 * @param map
	 * @throws IOException
	 */
	public static void setAuthCenterMap(Map map) 
	{
		authCenterMap = map;
	}
	
}
