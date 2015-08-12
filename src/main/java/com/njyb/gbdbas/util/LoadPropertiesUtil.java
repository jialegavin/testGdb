package com.njyb.gbdbas.util;

import java.io.BufferedInputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
/**
 * 属性读取配置文件中属性的值
 * @author jiahp
 *
 */
@Component
public class LoadPropertiesUtil {
	private static Properties pro;
	private static FileInputStream fis;
	private static String path;
	private static String url;
	public void init(HttpServletRequest request,String param){
		Class objClass = this.getClass(); 
		String strRealPath  = objClass.getClassLoader().getResource("").getFile(); 
		try {
			strRealPath = URLDecoder.decode(strRealPath, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		File objFile = new File(strRealPath); 
		strRealPath = objFile.getParent(); 
		pro = new Properties();
		path = request.getSession().getServletContext().getInitParameter(param);
		String urls=strRealPath+"/classes/"+path;
		url = strRealPath+"/classes/"+path;
		try {
			fis=new FileInputStream(urls);
			pro.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 通过名称--获取对应属性的值
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String value = pro.getProperty(key);
		return value;
	}
	
	/**
	 * 通过Key获取properties里的value
	 * @param name 参过来的key值 
	 * @return string []
	 * @author 章华才
	 */
	public static String [] getProp(String name){
		String str = pro.getProperty(name);
		if(str == null || "".equals(str))
		{
			return null;
		}else{
			return str.split(",");
		}
	}
	
	
	/**
	 * 设置属性的值 根据指定属性的名称
	 * @param key
	 * @param value
	 */
	public static void setValue(String key, String value) {
		pro.setProperty(key, value);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(url));
			pro.store(fos, key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
