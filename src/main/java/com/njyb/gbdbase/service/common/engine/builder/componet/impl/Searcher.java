package com.njyb.gbdbase.service.common.engine.builder.componet.impl;

import org.apache.lucene.search.IndexSearcher;
import org.springframework.beans.factory.annotation.Autowired;

import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engine.builder.componet.AbstractSearcher;
import com.njyb.gbdbase.service.common.engine.builder.componet.state.context.IndexSearchStateContext;
/**
 * 实现抽象的searcher的实例
 * @author jiahp
 *
 */
public class Searcher extends AbstractSearcher {
	@Autowired
	private SearchPropertiesModel propertiesMap;
	/**
	 * 实现searcher的实例
	 */
	@Override
	public IndexSearcher getSearcher(String countryName, String indexpath,
			String[] fields, String[] values) {
		//通过状态者模式获取indexsearcher
		//两种方式构建索引目录:一种是根据年月  一种是根据整个文件所用目录
		return IndexSearchStateContext.requestIndexSearcher(countryName, indexpath, fields, values,propertiesMap);
	}

}
