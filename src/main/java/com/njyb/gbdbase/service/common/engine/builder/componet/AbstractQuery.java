package com.njyb.gbdbase.service.common.engine.builder.componet;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Query;

/**
 * 构建抽象query的部件
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public abstract class AbstractQuery {
	/**
	 * 获取查询query
	 * @param request 封装请求参数对象
	 * @param fields  封装查询参数
	 * @param values  封装查询参数对应的值
	 * @param country 查询国家名称
	 * @return
	 */
	public abstract Query getQuery(HttpServletRequest request,String[] fields, String[] values,String country);
}
