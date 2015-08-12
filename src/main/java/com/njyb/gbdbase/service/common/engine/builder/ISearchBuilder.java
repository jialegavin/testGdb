package com.njyb.gbdbase.service.common.engine.builder;

import javax.servlet.http.HttpServletRequest;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
/**
 * 定义查询对象的生成器接口
 * @author jiahp
 *
 */
public interface ISearchBuilder {
	/**
	 * 构建核心查询对象
	 * @param countryName　国家名称
	 * @param indexpath　　索引路径
	 * @param fields	　　封装请求参数的名称
	 * @param values		封装请求参数的值
	 * @param map			保存索引基本信息
	 * @param type			是单海关编码single　还是多海关编码multi
	 * @return
	 */
	public abstract IndexSearcher getSearcher(String countryName,String indexpath,String[] fields, String[] values);
	/**
	 * 构建核心过滤器对象
	 * @param request
	 * @param fields
	 * @param values
	 * @param country
	 * @return
	 */
	public abstract Filter getFilter(HttpServletRequest request, String[] fields,
			String[] values,String country);
	/**
	 * 构建核心query对象
	 * @param request
	 * @param fields
	 * @param values
	 * @param country
	 * @return
	 */
	public abstract Query getQuery(HttpServletRequest request,String[] fields, String[] values,String country);
}
