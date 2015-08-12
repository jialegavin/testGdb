package com.njyb.gbdbase.service.common.engine.builder.impl.director;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.service.common.engine.api.searcher.LuceneSearch;
import com.njyb.gbdbase.service.common.engine.builder.ISearchBuilder;
import com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.factory.TopDocsFactory;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 指导如何去让生成器去生成不同的对象
 * 
 * @author jiahp
 * 
 */
public class ResultDataDirector {
	// 注入构造器接口
	private ISearchBuilder buidler;

	public ResultDataDirector(ISearchBuilder buidler) {
		this.buidler = buidler;
	}

	/**
	 * 生成器的指导对象通过调用工厂获取具体的返回结果类型
	 * @param request 封装请求对象
	 * @param indexPath  具体的索引路径 如 e:/lucene/usa_index
	 * @param fields      查询参数的名称
	 * @param values     查询参数的值
	 * @param page      分页模型对象
	 * @param keyPath   具体主键id 如 usaid ukid...
	 * @param countryName 国家名称
	 * @param module 模块名称简称 具体解释如下
	 * search 产品检索
	 * report 报表检索
	 * drill  报表深度挖取
	 * detailinfo  报表汇总数据对应的详情信息
	 * alldb 全库报表分析
	 * buyers 买家资源库   
	 * @param file 索引文件目录
	 * @return
	 */
	public Map getData(HttpServletRequest request, String indexPath,
			String[] fields, String[] values, PageBeanUtil page,
			String keyId, String countryName,
			String module, File file) {
		IndexSearcher searcher = null;
		Filter filter = null;
		try {
			if (module.equals(ParamEnumUtil.buyer.toString())) {
				searcher = LuceneSearch.getMultiFieldSearcher(file,countryName);
				filter = null;
			} else {
				searcher = buidler.getSearcher(countryName, indexPath, fields,values);
				filter = buidler.getFilter(request, fields, values, countryName);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Query query = buidler.getQuery(request, fields, values, countryName);
		return TopDocsFactory.getObject(searcher, query, filter, module, page, keyId);
	}

}
