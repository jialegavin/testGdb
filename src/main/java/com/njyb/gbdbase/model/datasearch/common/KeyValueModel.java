package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/**
 * 保存用户上一次的查询条件
 * @author honghao
 *
 */
@Component
public class KeyValueModel implements Serializable{
	public  static String lastQueryKey="";
	public  static String lastQueryValue="";
	public  static String lastCountry="";
	/*private static KeyValueModel km=null;
	public static synchronized KeyValueModel createInstance(){
		if (km==null) {
			km=new KeyValueModel();
		}
		return km;
	}*/
	public  static String getLastQueryKey() {
		return lastQueryKey;
	}
	public static void setLastQueryKey(String lastQueryKey) {
		KeyValueModel.lastQueryKey = lastQueryKey;
	}
	public static String getLastQueryValue() {
		return lastQueryValue;
	}
	public static void setLastQueryValue(String lastQueryValue) {
		KeyValueModel.lastQueryValue = lastQueryValue;
	}
	public static String getLastCountry() {
		return lastCountry;
	}
	public static void setLastCountry(String lastCountry) {
		KeyValueModel.lastCountry = lastCountry;
	}
	
}
