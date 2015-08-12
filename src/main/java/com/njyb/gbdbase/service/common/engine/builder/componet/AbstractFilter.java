package com.njyb.gbdbase.service.common.engine.builder.componet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Filter;

/**
 * 抽象filter
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public abstract class AbstractFilter {
	/**
	 * 获取查询过滤的filter
	 * @param request 封装请求参数对象
	 * @param fields  封装查询参数
	 * @param values  封装查询参数对应的值
	 * @param country 查询国家名称
	 * @return
	 */
	public abstract Filter getFilter(HttpServletRequest request, String[] fields,
			String[] values,String country,Map propertiesMap);
}
