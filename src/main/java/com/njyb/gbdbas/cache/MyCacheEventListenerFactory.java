package com.njyb.gbdbas.cache;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;
/**
 * 监控缓存中元素的监听器工厂
 * @author 贾红平
 *
 */
public class MyCacheEventListenerFactory extends CacheEventListenerFactory {
	/**
	 * 实现自定义的监听器
	 */
   @Override
   public CacheEventListener createCacheEventListener(Properties properties) {
      return new MyCacheElementEventListener();
   }
 
}