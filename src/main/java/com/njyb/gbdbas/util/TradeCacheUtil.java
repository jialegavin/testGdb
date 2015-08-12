package com.njyb.gbdbas.util;

import com.njyb.gbdbase.model.datasearch.common.MapModel;

/**
 * 贸易情报缓存工具类
 * @author 贾红平
 *
 */
public class TradeCacheUtil {
	/**
	 * 获取查询参数将中文查询参数和英文查询参数放入缓存
	 * @param keyValue
	 * @param regx
	 * @auther honghao 
	 * @return 中文参数列表
	 */
	public static String getQueryParam(String key,String value){
		//获取最近一次的查询条件
	   	 String [] queryKey =  key.split(";");
	   	 String [] queryValue = value.split(";");
	   	 //英文参数列表
	   	 StringBuffer enQueryParam = new StringBuffer();
	   	 //中文参数列表
	   	 StringBuffer zhQueryParam = new StringBuffer();
	   	 for(int i=0;i<queryKey.length;i++)
	   	 {
	   		enQueryParam.append(queryKey[i]).append(":").append(queryValue[i]).append(";");
	   		zhQueryParam.append(InitCountryCENameUtil.queryFieldZhName(queryKey[i])).append(":").append(queryValue[i]).append(";");
	   	 }
	   	 MapModel.map.put("enQueryParam", enQueryParam);
	   	 MapModel.map.put("zhQueryParam", zhQueryParam);
	   	 return zhQueryParam.toString();
	}
	
	
	
}
