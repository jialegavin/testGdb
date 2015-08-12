package com.njyb.gbdbas.util.ds.spring;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 让spring通过用户配置的数据源类型 动态切换数据源
 * @author 贾红平
 *
 */
public class DefintineDynamicDataSourceUtil extends AbstractRoutingDataSource{  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DBContextUtil.getDbTypeName();  
    }  
}