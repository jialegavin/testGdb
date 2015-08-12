package com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;

import com.njyb.gbdbas.util.PageBeanUtil;
/**
 * 定义产品接口 
 * @author jiahp
 *
 */
public interface ITopDocs {
	/**
	 * 通过工厂创建一个具体的topdocs
	 * @param searcher 部件
	 * @param query   部件
	 * @param filter   部件
	 * @param module   模块名称
	 * @param page   分页模型
	 * @return
	 */
	TopDocs getTopDocs(IndexSearcher searcher,Query query,Filter filter,String module,PageBeanUtil page);
}
