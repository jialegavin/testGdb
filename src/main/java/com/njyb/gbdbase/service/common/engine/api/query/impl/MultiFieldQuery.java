package com.njyb.gbdbase.service.common.engine.api.query.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Query;

import com.njyb.gbdbase.service.common.engine.api.query.ISearchQuery;
/**
 * 空实现
 * @author 贾红平
 *
 */
public class MultiFieldQuery implements ISearchQuery {
	@Override
	public Query getQuery(String[] filterField, String[] values, String country,HttpServletRequest request) {
		return null;
	}

}
