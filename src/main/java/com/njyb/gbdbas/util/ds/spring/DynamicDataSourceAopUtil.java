package com.njyb.gbdbas.util.ds.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.njyb.gbdbas.loginFilter.CheckSessionFilter;

/**
 * 实现数据源切换 通过service 用aop
 * @author 贾红平
 *
 */
@Aspect  
public class DynamicDataSourceAopUtil {
	/**
	 * 执行所有业务层的实现类 调用该方法
	 */
    @Pointcut("execution (* com.njyb.gbdbase.service..*.*(..)) || execution( * com.njyb.auth.service.impl.*.*(..))")
	public void serviceExecution(){}   
    
    
    /**
     * 拦截时间 
     * @param jp
     */
    @Before("serviceExecution()")  
    public void setDynamicDataSource(JoinPoint jp) {  
    	String domainName=null;
    	synchronized (this) {
    		domainName=CheckSessionFilter.domainMap.get("domain").toString();
    		System.out.println(domainName);
		}
    	//默认第一个参数 代表当前业务类需要的数据源的类型的简称
    	String implClass=jp.getTarget().toString();
    	if (implClass.contains("DataSearchService")) {
    		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_DS);
		}
    	else{
    		//设置南通用户基本信息数据源
    		if (getNtDbName(domainName)) {
    			DBContextUtil.setDbTypeName(DBContextUtil.NT_DATA_SOURCE_USER);
			}
    		//设置英蓓用户基本信息数据源
    		else if (getYbDbName(domainName)) {
    			DBContextUtil.setDbTypeName(DBContextUtil.YB_DATA_SOURCE_USER);
			}
    		//赋予默认值
    		else if(domainName.contains("localhost")){
				DBContextUtil.setDbTypeName(DBContextUtil.NT_DATA_SOURCE_USER);
			}
    	}
    }  
    
    /**
     * 判断属于南通的一类域名
     * @param domain
     * @return
     */
    private static boolean getNtDbName(String domain){
    	return domain.contains("trade-easy");
    }
     
    /**
     * 判断属于英蓓的一类域名
     * @param domain
     * @return
     */
    private static boolean getYbDbName(String domain){
    	return domain.contains("ybdb.net");
    }
    
}  