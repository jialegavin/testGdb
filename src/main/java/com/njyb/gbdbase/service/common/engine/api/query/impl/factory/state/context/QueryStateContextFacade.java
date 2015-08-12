package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.context;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanQuery;
/**
 * 封装状态上下文对象的api
 * @author 贾红平
 *
 */
public final class QueryStateContextFacade {
	/**
	 * 返回用户需要的查询对象
	 * @param ks:参数
	 * @param vs:参数值
	 * @param country:国家简称
	 * @param falg:值状态
	 * @param state:持有状态
	 * @return
	 */
	public static BooleanQuery getBooleanQueryByImporterState(String[] ks,
			String[] vs, String country, String falg, String state,HttpServletRequest request) {
		return QueryStateContext.getBooleanQueryByImporterState(ks, vs, country, falg, state,request);
	}
}
