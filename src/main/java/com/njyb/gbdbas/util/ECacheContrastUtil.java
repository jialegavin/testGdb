package com.njyb.gbdbas.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.njyb.gbdbas.cache.CreateEncache;
/**
 * Ecache查询条件对比工具
 * @author WangBo
 * 2015年5月8日
 * ECacheContrastUtil.java
 */
public class ECacheContrastUtil implements Serializable {

	//log记录日志
	private static final Logger log = Logger.getLogger(ECacheContrastUtil.class);
	
	private static final long serialVersionUID = 1L;
	
	private ECacheContrastUtil(){
		
	}

	/**
	 * 验证当前查询条件是否和上一次查询条件一致
	 * @param key : key
	 * @param value : value
	 * @param countrySelect : 国家
	 * @param cacheKey : cache Key
	 * @param elementKey : element Key
	 * @return true : 相同  , false : 不相同
	 */
	public static boolean checkParamIsEqual(List<String> key,List<String> value,String [] countrySelect,String cacheKey,String elementKey) {
		Cache ehCache = CreateEncache.getEacheInstance().getCache(cacheKey);
		Element element = CreateEncache.getCacheValue(ehCache, elementKey);
		if (null == element) {
			return false;
		}
		Map<String,Object> paramsMap = (Map<String,Object>) element.getValue();
		List<String> keys = (List<String>) paramsMap.get("key");
		List<String> values = (List<String>) paramsMap.get("value");
		// 判断国家是否相同
		String [] country = (String[]) paramsMap.get("country");
		if (country.length == countrySelect.length) {
			for (int i = 0; i < country.length; i++) {
				if (!country[i].equals(countrySelect[i])) {
					return false;
				}
			}
		} else {
			return false;
		}
		// 判断缓存中的条件是否和当前条件相同
		if (key.size() == keys.size() && value.size() == values.size()) {
			for (String strKey : keys) {
				if (!key.contains(strKey)) {
					return false;
				}
			}
			for (String strValue : values) {
				if (!value.contains(strValue)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * 存入缓存
	 * @param cacheKey
	 * @param elementKey
	 * @param t
	 */
	public static <T> void addCacheByParams(String cacheKey,String elementKey,T t) {
		@SuppressWarnings("static-access")
		Cache ehCache = CreateEncache.getEacheInstance().getCache(cacheKey);
		ehCache.acquireWriteLockOnKey(elementKey);
		ehCache.put(new Element(elementKey,t));
		ehCache.releaseWriteLockOnKey(elementKey);  
	}
	
	/**
	 * 读取缓存
	 * @param cacheKey : cache Key
	 * @param elementKey : element Key
	 * @return
	 */
	public static Object getCacheResultByParams(String cacheKey,String elementKey) {
		try {
			Cache ehCache = CreateEncache.getEacheInstance().getCache(cacheKey);
			ehCache.acquireReadLockOnKey(elementKey);
			Element element = ehCache.get(elementKey);
			ehCache.releaseReadLockOnKey(elementKey);
			if (null != element) {
				return element.getValue();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		} catch (CacheException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return null;
	}
}
