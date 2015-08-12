package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.njyb.gbdbas.util.ArrayUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.service.common.engines.IReportEngineService;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 汇总数据
 * 
 * @author WangBo 2015年5月28日 QueryDataService.java
 */
@Service
public class QueryDataService implements IQueryDataService{
	
	// log 日志
	private static final Logger log = Logger.getLogger(QueryDataService.class);
	
	@Autowired
	private IReportEngineService reportEngineService;

	// lucene的调用接口
	@Autowired
	private ISearchEngineService searcherEngineService;

	// 数据库共用搜索接口
	@Autowired
	private IDataSearchService dataSearchService;

	// 数据转换接口
	@Autowired
	private IDataConvertDBModel dataConvertDBModel;
	
	//容器集合
//	private List<DataReportSumModel> queryDataList = Lists.newArrayList();

	/**
	 * 汇总数据
	 */
	@Override
	public synchronized List<DataReportSumModel> queryDataByParams(
			HttpServletRequest request, String[] feilds, String[] values,
			String country, String reportName, boolean isFlag) {
		List<DataReportSumModel> queryDataList = Lists.newLinkedList();
		ReportCommonParamModel searchModel = new ReportCommonParamModel(feilds,
				values, country, "report", request, reportName);
		Map<String, List<DataReportSumModel>> resultMap = reportEngineService
				.builderMapList(searchModel, isFlag);
		buildAllDBList(reportName, resultMap, queryDataList); // 汇总数据
		return queryDataList;
	}

	/**
	 * 构建数据List<br>
	 * 市场分析
	 * 
	 * @param key
	 * @param resultDataMap
	 * @return
	 */
	private List<DataReportSumModel> buildAllDBList(String key,
			Map<String, List<DataReportSumModel>> resultDataMap,
			List<DataReportSumModel> queryDataList) {
		if (resultDataMap.containsKey(key)) { // 非空判断
			queryDataList.addAll(resultDataMap.get(key));
		}
		return queryDataList;
	}

	/**
	 * 查询数据 交易记录,竞争对手
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<AllDBModel> queryAllDBData(String module,
			HttpServletRequest request, PageBeanUtil pageBean,
			Map<String, Map<String, Object>> resultMap) {
		pageBean.setPageSize(Integer.MAX_VALUE);
		List<AllDBModel> allDBList = Lists.newLinkedList();
		for (Entry<String, Map<String, Object>> entyry : resultMap.entrySet()) {
			String country = InitCountryCENameUtil.queryCountryEnName(entyry
					.getKey()); // 获取英文名称
			List<String> newKey = (List<String>) entyry.getValue().get("key"); 		// 获取Key
			List<String> newValue = (List<String>) entyry.getValue().get("value");  // 获取value
			log.info("全库查询id : 国家 : " + country + " key : " +newKey.toString()+ " value : " + newValue.toString());
			String[] fileds = ArrayUtil.getStringArray(newKey); 					// 得到数组
			String[] values = ArrayUtil.getStringArray(newValue);
			// TODO Thread
			List<Integer> idList = searcherEngineService.getListKey(new SearchCommonParamModel(fileds, values,
					country, module, request,
					pageBean));
			allDBList.addAll(dataConvertDBModel.convertAllDBModel(country,idList));
		}
		return allDBList;
	}
}