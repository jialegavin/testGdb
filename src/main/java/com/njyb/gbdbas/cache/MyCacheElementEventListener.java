package com.njyb.gbdbas.cache;
import org.apache.log4j.Logger;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
/**
 * 自定义监控缓存中存放元素的相关操作
 * @author 贾红平
 *
 */
public class MyCacheElementEventListener implements CacheEventListener {
	
	private static final Logger log = Logger.getLogger(MyCacheElementEventListener.class);
		
   /**
    * 移除一个元素
    */
   @Override
   public void notifyElementRemoved(Ehcache cache, Element element)
         throws CacheException {
	   	log.info(element.getObjectKey()+"元素,被从缓存中移除");
   }
   /**
    * 存放一个元素
    */
   @Override
   public void notifyElementPut(Ehcache cache, Element element)
         throws CacheException {
	   log.info("向缓存存放了一个新的元素:"+element.getObjectKey()+",包含对象数量是:"+cache.getSize()+",对象占有内存大小是:"+cache.getMemoryStoreSize());
   }
   /**
    * 更新一个元素
    */
   @Override
   public void notifyElementUpdated(Ehcache cache, Element element)
         throws CacheException {
	   log.info("缓存中"+element.getObjectKey()+"元素被更新了");
   }
   /**
    * 一个元素失效
    */
   @Override
   public void notifyElementExpired(Ehcache cache, Element element) {
	   log.info(element.getObjectKey()+"元素已经失效,"+"失效时间是:"+element.getExpirationTime());
	   /*从缓存中移除已经失效的元素*/
	   cache.remove(element.getObjectKey());
   }
   /**
    * 清除一个元素
    */
   @Override
   public void notifyElementEvicted(Ehcache cache, Element element) {
	   log.info("从缓存中清除了一个元素是:"+element.getObjectKey());
   }
   /**
    * 
    */
   @Override
   public void notifyRemoveAll(Ehcache cache) {
     log.info("完了所有元素都被清除了...");
   }
   /**
    * 释放资源
    */
   @Override
   public void dispose() {
 
   }
   
   public Object clone() throws CloneNotSupportedException {
      throw new CloneNotSupportedException();
   }
 
}