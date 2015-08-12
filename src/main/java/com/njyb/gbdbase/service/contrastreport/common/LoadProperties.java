package com.njyb.gbdbase.service.contrastreport.common;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 读取properties文件
 * @author 章华才
 * 2015-03-31
 */
public class LoadProperties {

	//私有化
	private LoadProperties(){};
	
	private static Properties properties;
	
	//加载prope文件
	private static Properties getProperties(){
		File file = new File("src/main/resources/config/search/contrastre.properties");
		Properties p = null;
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(file));
			p  =   new  Properties(); 
		   try {
			 p.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 * 通过Key获取properties里的value
	 * @param name 参过来的key值 
	 * @return string []
	 * @author 章华才
	 */
	public static String [] getProp(String name){
		properties = getProperties();
		if(name == null || "".equals(name))
		{
			return null;
		}
		String str = properties.getProperty(name);
		if(str != null && !str.equals(""))
		{
			return str.split(",");
		}
		return null;
	}
	
	
	/**
	 * 通过Key获取properties里的value
	 * @param name 参过来的key值 
	 * @return string []
	 * @author 章华才
	 */
	public static String getPropert(String name){
		properties = getProperties();
		if(name == null || "".equals(name))
		{
			return null;
		}
		String str = properties.getProperty(name);
		return str;
	}
	
	/**
	 * 获取值
	 * @param name
	 * @return
	 */
	public static Object getValue(String name){
		properties = getProperties();
		return properties.get(name);
	}
	
}
