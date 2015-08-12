package com.njyb.gbdbase.service.common.engine.api.searcher;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.SysContentUtil;
import com.njyb.gbdbas.util.pagetemplate.TabGenerateUtil;
import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;

/**
 *封装Lucene查询的核心对象 indexsearch
 *@author 贾红平
 *
 */
@SuppressWarnings("all")
public final class LuceneSearch {
	//log记录日志
	private static final Logger log = Logger.getLogger(LuceneSearch.class);
	// 索引目录
	private static Directory directory = null;
	// 索引解析器
	private static DirectoryReader reader = null;
	// 查询对象
	private static IndexSearcher searcher = null;
	
	private static String newCountry = null;
	private static File[] newFiles = null;
	private static File newFile = null;
	/**
	 * 从内存中加载索引索引文件 jhp
	 * 
	 * @return
	 */
	public static Directory getDirectory(File file) {
		Directory directory = null;
		try {
			// 从磁盘中获取索引文件
			directory = FSDirectory.open(file);
			// 把磁盘中的索引文件加载到内存中 提高读取性能
			// Directory ramDirectory=new RAMDirectory(directory,
			// IOContext.READ);
			// return ramDirectory;
			return directory;
		} catch (IOException e) {
			System.out.println("输入错误打印信息：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 创建查询directoryreader对象 jhp 通过单例模式获取reader对象 及时cpu创建资源
	 * 
	 */
	public static DirectoryReader getDirectoryReader(Directory directory) {
		DirectoryReader reader = null;
		try {
			if (reader == null) {
				reader = DirectoryReader.open(directory);
			} else {
				DirectoryReader tr = DirectoryReader.openIfChanged(reader);
				if (tr != null) {
					reader.close();
					reader = tr;
				}
			}
		} catch (Exception e) {
			System.out.println("输入错误打印信息：" + e.getMessage());
		}
		return reader;
	}

	/**
	 * 获取多索引目录查询对象
	 */
	public static IndexSearcher getMultiConditonSearcher(File[] files,String country)
			throws Exception {
		//这里的缓存需要考虑多个国家的不同缓存机制 需要完善
		// 获取缓存实例
//		Cache cache = CreateEncache.getEacheInstance().getCache("searcherMultiCache");
//		Element element = cache.get("multiCondition");
//		Object o =null;
//		if (element!=null) {
//			o= element.getObjectValue();
//		}
//		// 判断缓存中是否有 有的话就从缓存去 否则重新创建一个
//		if (o == null || o.equals("")|| !newCountry.equals(country)
//				|| isValiteFiles(newFiles, files)) {
//			int index = 0;
//			DirectoryReader[] readers = new DirectoryReader[files.length];
//			for (File f : files) {
//				readers[index++] = getDirectoryReader(getDirectory(f));
//			}
//			element = new Element("multiCondition", new IndexSearcher(
//					new MultiReader(readers)));
//			cache.put(element);
//			newCountry=country;
//			newFiles=files;
//		}
		int index = 0;
		DirectoryReader[] readers = null;;
		try {
			readers = new DirectoryReader[files.length];
			for (File f : files) {
				readers[index++] = getDirectoryReader(getDirectory(f));
			}
			return new IndexSearcher(
					new MultiReader(readers));
		} catch (Exception e) {
			System.out.println("输入错误打印信息：" + e.getMessage());
		}
		return null;
		//return (IndexSearcher) element.getObjectValue();
	}
	
	/**
	 * 判断文件夹是否一致
	 * @param newFiles 新文件夹[]
	 * @param files 文件夹[]
	 * @return boolean
	 * @author 章华才
	 */
	public static boolean isValiteFiles(File[] newFiles,File[] files){
		if(files.equals(newFiles)){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断文件夹是否一致
	 * @param newFiles 新文件夹[]
	 * @param files 文件夹[]
	 * @return boolean
	 * @author 章华才
	 */
	public static boolean isValiteFile(File newFiles,File files){
		if(files.equals(newFiles)){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取多字段查询对象
	 * 
	 * @param directory
	 * @return
	 */
	public static IndexSearcher getMultiFieldSearcher(File file,String country)
			throws Exception {
		// 获取缓存实例
//		Cache cache =CreateEncache.getEacheInstance().getCache("searcherCache");
//		Element element = cache.get("searcher");
//		Object o = null;
//		if(element != null)
//		{
//			o = element.getObjectValue();
//		}
//		// 判断缓存中是否有 有的话就从缓存去 否则重新创建一个
//		if (o == null || o.equals("") || newCountry.equals(country) || isValiteFile(newFile, file)) {
//			element = new Element("searcher", new IndexSearcher(
//					getDirectoryReader(getDirectory(file))));
//			newCountry = country;
//			newFile = file;
//			cache.put(element);
//		}
		return new IndexSearcher(
				getDirectoryReader(getDirectory(file)));

	}
}
