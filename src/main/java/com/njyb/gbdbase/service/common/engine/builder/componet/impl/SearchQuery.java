package com.njyb.gbdbase.service.common.engine.builder.componet.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Query;

import com.njyb.gbdbase.service.common.engine.api.query.staticfactory.SearchQueryFactory;
import com.njyb.gbdbase.service.common.engine.builder.componet.AbstractQuery;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
/**
 * 实现抽象query的实例
 * @author jiahp
 *
 */
public class SearchQuery extends AbstractQuery {
	@Override
	public Query getQuery(HttpServletRequest request, String[] fields,
			String[] values,String country) {
			//获取query对象
			return SearchQueryFactory.getQuery(fields, values, country, request,ParamEnumUtil.condition.toString());
	}

}
