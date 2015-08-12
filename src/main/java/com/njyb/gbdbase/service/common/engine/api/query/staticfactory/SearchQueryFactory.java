package com.njyb.gbdbase.service.common.engine.api.query.staticfactory;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Query;

import com.njyb.gbdbase.service.common.engine.api.query.ISearchQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.MultiConditionQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.MultiFieldQuery;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 选择query的具体实现
 * @author 贾红平
 *
 */
public class SearchQueryFactory {
	/**
	 * 选择具体的query实现
	 * @param type
	 * @return
	 */
	private static ISearchQuery createSearchQueryInstance(String type){
		ISearchQuery query=null;
		if (type.equals(ParamEnumUtil.field.toString())) {
			query=new MultiFieldQuery();
		}
		if (type.equals(ParamEnumUtil.condition.toString())) {
			query=new MultiConditionQuery();
		}
		return query;
	}
	/**
	 * 调用query对象具体的查询query对象
	 * @param filterField
	 * @param values
	 * @param country
	 * @param request
	 * @param type
	 * @return
	 */
	public static Query getQuery(String[] filterField, String[] values,String country,HttpServletRequest request,String type) {
		return createSearchQueryInstance(type).getQuery(filterField, values, country, request);
	}
}
