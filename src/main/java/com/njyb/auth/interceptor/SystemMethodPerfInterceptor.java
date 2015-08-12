package com.njyb.auth.interceptor;

import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
/**
 * 监控系统所有的业务方法:主要是现实方法的开始和结束执行时间
 * 从而方便后期找出性能瓶颈
 * @author jiahp
 *
 */
public class SystemMethodPerfInterceptor implements MethodInterceptor,InitializingBean {
    @Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	Logger logger = Logger.getLogger(SystemMethodPerfInterceptor.class);
    private static ConcurrentHashMap<String, MethodModel> methodMap = new ConcurrentHashMap<String, MethodModel>();
    //频率
    private static long statLogFrequency = 10;
    
    private static long methodWarningThreshold = 1000;
    
    /*
     * 方法执行
     */
    @Override
    public Object invoke(MethodInvocation method) throws Throwable {
    	long start = System.currentTimeMillis();
        try {
            return method.proceed();
        }
        finally {
            updateStats(method.getMethod().getName(),(System.currentTimeMillis() - start));
        }
    }
    
    /*
     * 更新状态信息
     */
    private void updateStats(String methodName, long elapsedTime) {
    	MethodModel stats = methodMap.get(methodName);
        if(stats == null) {
            stats = new MethodModel(methodName);
            methodMap.put(methodName,stats);
        }
        stats.count++;
        stats.totalTime += elapsedTime;
        if(elapsedTime > stats.maxTime) {
            stats.maxTime = elapsedTime;
        }
        
        if(elapsedTime > methodWarningThreshold) {
            logger.warn("方法性能出现警告: " + methodName + "(), cnt = " + stats.count + ", lastTime = " + elapsedTime + ", maxTime = " + stats.maxTime);
        }
        
        if(stats.count % statLogFrequency == 0) {
            long avgTime = stats.totalTime / stats.count;
            long runningAvg = (stats.totalTime-stats.lastTotalTime) / statLogFrequency;
            logger.debug("方法正常执行: " + methodName + "(), cnt = " + stats.count + ", lastTime = " + elapsedTime + ", avgTime = " + avgTime + ", runningAvg = " + runningAvg + ", maxTime = " + stats.maxTime);
            
             stats.lastTotalTime = stats.totalTime;   
        }
    }
    /*
     * 封装方法要显示的基本信息
     */
    class MethodModel {
        public String methodName;
        public long count;
        public long totalTime;
        public long lastTotalTime;
        public long maxTime;
        
        public MethodModel(String methodName) {
            this.methodName = methodName;
        }
    }

	 
}