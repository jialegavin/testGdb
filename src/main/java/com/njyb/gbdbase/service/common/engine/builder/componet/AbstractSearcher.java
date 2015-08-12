package com.njyb.gbdbase.service.common.engine.builder.componet;
import org.apache.lucene.search.IndexSearcher;

/**
 * 构建查询核心对象searcher的部件
 * @author jiahp
 *
 */
public abstract class AbstractSearcher {
	public abstract IndexSearcher getSearcher(String countryName,String indexpath,String[] fields, String[] values);
}
