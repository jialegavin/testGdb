
package com.njyb.gbdbase.service.common.engine.api.query;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Query;

/**
 * Lucene查询api所需要的query
 * @author 贾红平
 *
 */
public interface ISearchQuery {
	/**
	 * 创建多条件查询对象
	 * 
	 * @param filterField
	 *            --查询条件 fields --new
	 *            string[]{'customcode','productname','unitprice'}
	 * @param values
	 *            --new value[]{'0409*','tianran','1.0,5.0'};
	 *            @param country:国家中文简称
	 * @return
	 * @throws  
	 */
	public abstract Query getQuery(String[] filterField, String[] values,String country,HttpServletRequest request);
}
