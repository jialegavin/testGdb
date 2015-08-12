package com.njyb.gbdbas.cache;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.ChangeOperSystemPath;

/**
 * 创建一个缓存实例
 * 缓存多字段匹配查询对象
 * 缓存多条件查询对象
 * @author 贾红平
 * 
 */
public final class CreateEncache {
	/*使用日志*/
	private static final Logger log = Logger.getLogger(CreateEncache.class);
	/*读取缓存配置文件的路径*/
	private static final String XMLPATH=ChangeOperSystemPath.getCachePath();

//	private static final String XMLPATH="/data/ehcache.xml";
	//private static final String XMLPATH="src/main/resources/config/cache/ehcache.xml";
	
	
	/*声明同步锁*/
	private static ReentrantLock lock=new ReentrantLock();
	/*使用并发map存储已经创建好的缓存对象*/
	private static Map<String, Cache> cacheMap=new ConcurrentHashMap<String, Cache>();
	/**
	 * 使用单例模式创建该工具类
	 */
	private CreateEncache(){}
	
	static class Eache{
		public static CreateEncache cache=new CreateEncache();
	}
	/*使用内部类来解决异步和耗时两个问题*/
	public static CreateEncache getEacheInstance(){
		 return Eache.cache;
	}
	
	/**
	 * 创建缓存实例
	 * @param cacheName 缓存管理器的名称
	 * @param key 保存缓存的名称
	 * @return
	 */
	public  Cache getCache(String cacheMangerName){
		try {
			lock.lock();
			return createCache(XMLPATH,cacheMangerName);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
		return null;
	}
	
	/**
	 * 获取缓存的方法:先从map中获取,如果没有的话再创建..然后保存到map中...
	 * @param xmlPath 保存缓存信息的配置文件的路径
	 * @param cacheMangerName 缓存名称
	 * @return
	 * @throws Exception
	 */
	private static Cache createCache(String xmlPath,String cacheMangerName) throws Exception{
		//创建一个缓存管理器
		CacheManager cm=CacheManager.create(xmlPath);
		Cache cache=cacheMap.get(cacheMangerName);
		if (cache==null) {
			//通过缓存管理器获取一个缓存对象
			cache=cm.getCache(cacheMangerName);
			//把已经创建好的缓存对象存放到map中
			cacheMap.put(cacheMangerName, cache);
		}
		return cache;
	}
	
	/**
	 * 根据key值获取缓存的数据
	 * @return Element元素
	 * @auther 洪皓
	 */
	@Deprecated
	public  static Element getCacheValue(Cache cache,String key)
	{
		Element element = cache.get(key);
		log.info("得到缓存中的对象数"+cache.getSize()+"\n"+"缓存对象占用内存的大小"+cache.getMemoryStoreSize()
				+"\n"+"缓存读取的命中次数"+cache.getStatistics().getCacheHits()+"\n"+"缓存读取的错失次数"+cache.getStatistics().getCacheMisses());
		return element;
	}
	public static void main(String[] args) {
	}
}
