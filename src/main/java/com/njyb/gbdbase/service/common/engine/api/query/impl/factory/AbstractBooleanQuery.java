package com.njyb.gbdbase.service.common.engine.api.query.impl.factory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanQuery;

/**
 * 定义抽象query
 * @author 贾红平
 *
 */
public abstract class AbstractBooleanQuery {
	/**
	 * 子类公用属性
	 */
	public BooleanQuery bq=new BooleanQuery();
	/**
	 * 返回一个具体的查询对象
	 * @param fs:查询参数
	 * @param vs:参数对应的值
	 * @param request:封装请求参数对象
	 * @return 查询对象
	 */
	public abstract BooleanQuery getBooleanQuery(List<String>fs,List<String>vs,String country,HttpServletRequest request);
}
