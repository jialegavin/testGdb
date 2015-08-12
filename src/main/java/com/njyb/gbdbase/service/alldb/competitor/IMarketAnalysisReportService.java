package com.njyb.gbdbase.service.alldb.competitor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
/**
 * 市场分析报表 服务
 * @author WangBo
 * 2015年4月14日
 * IMarketAnalysisReportService.java
 */
public interface IMarketAnalysisReportService {
	
	/**
	 * 设置查询市场分析报告model中的值构造查询条件
	 */
    Map<String,Object> setMarketAnalysisReportFields(HttpServletRequest request,
			RightLibrarySearchModel model); 
	
    /**
	 * 市场分析报告所有报告方法汇总
	 * @param model : 条件model
	 * @param request : 请求
	 * @param key : key 
	 * @param values : value
	 * @param countryName : 国家名称
	 * @param sortKey : [预留]<br>排序字段
	 * @return List<AllDBModel> {已处理好的ALlDBModel 容器}
	 */
    List<DataReportSumModel> queryReportDateByType(HttpServletRequest request, RightLibrarySearchModel model, List<String> key, List<String> value,String countryName, String sortKey);
}