package com.njyb.gbdbase.service.common.componet.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.pagetemplate.TabGenerateUtil;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaImportModel;
import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engine.builder.impl.ListResultDataBuilder;
import com.njyb.gbdbase.service.common.engine.builder.impl.MapResultDataBuilder;
import com.njyb.gbdbase.service.common.engine.builder.impl.director.ResultDataDirector;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.contrastreport.common.LuceneFileUtis;

/**
 * lucene数据检索结果返回
 * 
 * @author 贾红平
 *
 */
@SuppressWarnings("rawtypes")
public class CommonLuceneComponent {
	//log记录日志
//	private static final Logger log = Logger.getLogger(CommonLuceneComponent.class);
	@Resource
	private SearchPropertiesModel searchPropertiesModel;
	private String indexPath;
	private String keyId;

	// 报表汇总分析 买家资源库 全库检索 钻取
	@SuppressWarnings({ "static-access" })
	private Map getResultMap(HttpServletRequest request, String[] fields,
			String[] values, String countryName, String module,
			PageBeanUtil page) {
		// 获取配置文件相关信息
		initParam(countryName);
		// 获取生成结果对象
		Map mp = null;
		// 买家资源库
		if (module.equals(ParamEnumUtil.buyer.toString())) {
			mp = getDirector(module).getData(request,indexPath,fields,values,page,null,countryName,module,new File((IConstantUtil.COMMON_INDEXPATH.toString()+indexPath)));
		}
		// 全库检索+汇总明细+报表汇总模块
		else {
			mp = getDirector(module).getData(request, indexPath, fields,values, null, null, countryName, module, null);
		}
		return mp;

	}

	/**
	 * 返回检索结果的document
	 * 
	 * @param request
	 *            封装请求参数
	 * @param fields
	 *            请求参数名称
	 * @param values
	 *            请求参数值
	 * @param page
	 *            请求的分页信息
	 * @param countryName
	 *            国家名称
	 * @param module
	 *            模块简称 详情见ParamEnumUtil
	 * @return list
	 */
	protected List<Document> getDocList(HttpServletRequest request,String[] fields, String[] values, String countryName,String module, PageBeanUtil page) {

		
		try {
			Map mp = getResultMap(request, fields, values, countryName, module,
					page);
			IndexSearcher searcher = (IndexSearcher) mp.get("searcher");
			TopDocs td = (TopDocs) mp.get("td");
			ScoreDoc[] sc = td.scoreDocs;
			List<Document> dlist = new LinkedList<Document>();
			for (ScoreDoc s : sc) {
				try {
					dlist.add(searcher.doc(s.doc));
				} catch (IOException e) {
					System.out.println("输入错误打印信息：" + e.getMessage());
				}
			}
			return dlist;
		} catch (Exception e) {
			System.out.println("输入错误打印信息：" + e.getMessage());
		}
		return null;
	}

	

	/**
	 * 根据模块 返回不同的构建对象
	 * 
	 * @param moudle
	 * @return
	 */
	private ResultDataDirector getDirector(String moudle) {
		if (moudle.equals(ParamEnumUtil.search.toString())
				|| moudle.equals(ParamEnumUtil.detail.toString())) {
			return new ResultDataDirector(new ListResultDataBuilder());
		} else {
			return new ResultDataDirector(new MapResultDataBuilder());
		}
	}

	/**
	 * 初始化参数值
	 * 
	 * @param coutry
	 */
	@SuppressWarnings("static-access")
	private void initParam(String coutry) {
		if (coutry.equals(LuceneConstant.DATA_RESOUCE)) {
			// 获取配置文件相关信息
			indexPath = searchPropertiesModel.getPropertiesMap().get(coutry)
					.toString().split(",")[0];
			
		}
		else{
			// 获取配置文件相关信息
			indexPath = searchPropertiesModel.getPropertiesMap().get(coutry)
					.toString().split(",")[0];
			keyId = searchPropertiesModel.getPropertiesMap().get(coutry).toString()
					.split(",")[1];
		}
	}

	

	/**
	 * 产品检索和明细
	 * 
	 * @param request
	 * @param fields
	 * @param values
	 * @param page
	 * @param countryName
	 * @param module
	 * @return
	 */
	protected List<Integer> getListKey(HttpServletRequest request,
			String[] fields, String[] values, PageBeanUtil page,
			String countryName, String module) {
		// 获取配置文件相关信息
		initParam(countryName);
		// 获取生成结果对象
		Map mp = getDirector(module).getData(request, indexPath, fields,values, page, keyId, countryName, module, null);
		if (mp==null) {
			return new LinkedList<Integer>();
		}
		else{
			return (List<Integer>) mp.get("search_list");
		}
 
	}

}
