package com.njyb.gbdbase.service.common.engine.builder.impl;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engine.builder.ISearchBuilder;
import com.njyb.gbdbase.service.common.engine.builder.componet.impl.SearchFilter;
import com.njyb.gbdbase.service.common.engine.builder.componet.impl.SearchQuery;
import com.njyb.gbdbase.service.common.engine.builder.componet.impl.Searcher;
/**
 * 实现返回结果是list的生成器所需的零部件
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public class MapResultDataBuilder implements ISearchBuilder {
	@Resource
	private  SearchPropertiesModel propertiesMap;
	/**
	 * 创建filter部件
	 */
	@Override
	public Filter getFilter(HttpServletRequest request, String[] fields,
			String[] values, String country) {
 		return new SearchFilter().getFilter(request, fields, values, country,propertiesMap.getPropertiesMap());
	}
	/**
	 * 创建query部件
	 */
	@Override
	public Query getQuery(HttpServletRequest request, String[] fields,
			String[] values, String country) {
 		return new SearchQuery().getQuery(request, fields, values, country);
	}
	/**
	 * 创建searcher部件
	 */
	@Override
	public IndexSearcher getSearcher(String countryName, String indexpath,
			String[] fields, String[] values) {
 		return new Searcher().getSearcher(countryName, indexpath, fields, values);
	}

	

}
