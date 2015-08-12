package com.njyb.gbdbas.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取域名
 * @author XL
 */
public class GetDoMainNameUtil {
	
	/**
	 * 获取域名（如：http://f0rb.iteye.com）
	 * @param request
	 * @return
	 */
	public static String getDoMain(HttpServletRequest request){
		StringBuffer url = request.getRequestURL();  
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString(); 
		return tempContextUrl;
	}
	
	/***
	 * 获取带部署环境上下文的域名，如： http://www.iteye.com/admin/ 
	 * @param request
	 * @return
	 */
	public static String getContextDoMain(HttpServletRequest request){
	    StringBuffer url = request.getRequestURL();   
	    String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).append("/").toString();
		return tempContextUrl;
	}
}
