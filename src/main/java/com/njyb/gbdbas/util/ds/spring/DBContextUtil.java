package com.njyb.gbdbas.util.ds.spring;
/**
 * 使用一个线程上下文来保存多种数据源的不同标致
 * @author 贾红平
 *
 */
public class DBContextUtil{
	/**
	 * 用户相关数据源
	 */
    public static final String DATA_SOURCE_USER = "userDs"; 
    /**
     * 数据相关数据源
     */
    public static final String DATA_SOURCE_DS = "dbDs";  
    /**
     * 防止多线程访问 出现数据混乱 使用线程变量共享
     */
    private static final ThreadLocal<String> contextUtil = new ThreadLocal<String>();  
    /**
     * 设置数据源标致类型
     * @param dbType
     */
    public static void setDbTypeName(String dbType) {  
        contextUtil.set(dbType);  
    }  
    /**
     * 获取数据标致类型
     * @return
     */
    public static String getDbTypeName() {  
        return contextUtil.get();  
    }  
    /**
     * 清除数据源
     */
    public static void clearDbTypeName() {  
        contextUtil.remove();  
    }  
}  