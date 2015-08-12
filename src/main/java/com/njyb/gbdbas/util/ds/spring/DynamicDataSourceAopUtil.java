package com.njyb.gbdbas.util.ds.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

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
    @Pointcut("execution (* com.njyb.gbdbase.service.impl..*.*(..))")  
    public void serviceExecution(){}  
    
    /**
     * 拦截时间 
     * @param jp
     */
    @Before("serviceExecution()")  
    public void setDynamicDataSource(JoinPoint jp) {  
    	Object[]objs=jp.getArgs();
    	//默认第一个参数 代表当前业务类需要的数据源的类型的简称
    	String dsName=objs[0].toString();
    	if (dsName.equals("userDs")) {
			DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		}
    	else if (dsName.equals("dbDs")) {
			DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_DS);
		}
    	else{
			DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
    	}
    }  
}  