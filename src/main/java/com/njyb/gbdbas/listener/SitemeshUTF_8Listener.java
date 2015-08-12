package com.njyb.gbdbas.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 页面装饰器对应的监听器
 * @author 贾红平
 *
 */
public class SitemeshUTF_8Listener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		Properties prop = System.getProperties();
		prop.put("file.encoding", "utf-8");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
	
	}

}
