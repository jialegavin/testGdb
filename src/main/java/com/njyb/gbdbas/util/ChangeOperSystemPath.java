package com.njyb.gbdbas.util;

import java.io.File;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class ChangeOperSystemPath {
	/**
	 * 判断操作系统
	 * @return true  windows操作系统  false linux
	 */
      public static boolean JudgeSystem(){
    	  Properties prop = System.getProperties();
    	  String os= prop.getProperty("os.name");
    	  if(os.startsWith("win")||os.startsWith("Win")){
    		  return true;
    	  }else{
    		  return false;
    	  }
      }
      /**
       * 切换linux 和windows 的缓存路径
       * @return
       */
      public static String getCachePath(){
    	  //if(JudgeSystem()){
    			 // return "src/main/resources/config/cache/ehcache.xml";
	    	  File nowFile = new File(ResolveXMLUtil.class.getClassLoader().getResource("").getFile());
				String nowPath = new File(nowFile.getParent()).getParent();			
	    	  if(tomOrJetty(nowPath)) return nowPath+"/WEB-INF/classes/config/cache/ehcache.xml";
	    	  else return "src/main/resources/config/cache/ehcache.xml";
    	  //}
    	  //else{
    		//  return "//usr//apache-tomcat-6.0.37//webapps//gbdbas//WEB-INF//classes//config//cache//ehcache.xml";
    	  //}
      }
      /**
       * 切换linux 和windows 的菜单路径
       * @return
       */
      public static String getMenuPath(){
    	 // if(JudgeSystem()){
    		  //window根据获取项目路径
    			File nowFile = new File(ResolveXMLUtil.class.getClassLoader().getResource("").getFile());
    			String nowPath = new File(nowFile.getParent()).getParent();
			 // return nowPath+"/src/main/resources/config/menu/menu.xml";
    			if(tomOrJetty(nowPath)) return nowPath+"/WEB-INF/classes/config/menu/menu.xml";
    			else return nowPath+"/src/main/resources/config/menu/menu.xml";
		  //}else{
			 //linux获取绝对路径
			//  return "//usr//apache-tomcat-6.0.37//webapps//gbdbas//WEB-INF//classes//config//menu//menu.xml";
		  //}
      }
      /**
       * 切换linux 和windows 的log4j路径
       * @return
       */
      public static String getLog4jPath(){
    	  //if(JudgeSystem()){
    		  //window根据获取项目路径
    			File nowFile = new File(ResolveXMLUtil.class.getClassLoader().getResource("").getFile());
    			String nowPath = new File(nowFile.getParent()).getParent();
			 // return nowPath+"/src/main/resources/config/log4j/log4j.properties";
    			if(tomOrJetty(nowPath)) return nowPath+"/WEB-INF/classes/config/log4j/log4j.properties";
    			else return nowPath+"/src/main/resources/config/log4j/log4j.properties";
		 // }else{
			 //linux获取绝对路径
			//  return "//usr//apache-tomcat-6.0.37//webapps//gbdbas//WEB-INF//classes//config//log4j//log4j.properties";
		 // }
      }
      public static void main(String[] args) {
		System.out.println( getMenuPath());
	}
      public static boolean tomOrJetty(String nowPath){
    	  File file=new File(nowPath+"/src");
    	  if(file.exists()) return false;
    	  else return true;
      }
}
