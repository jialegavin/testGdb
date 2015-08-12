package com.njyb.gbdbas.util.ds.java;
/**
 * 通过线程上下文获加载
 *@author 贾红平
 *
 */
@SuppressWarnings("all")
public class ThreadContextUtil {
	/**
	 * 保证变量 共享
	 */
	private static ThreadLocal thread = new ThreadLocal();   
    public static void setDName(String name) {   
        thread.set(name);   
    }   
    public static String getDName() {   
        return (String)thread.get();   
    }   
}
