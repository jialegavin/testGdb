package com.njyb.gbdbase.service.alldb.projectAnalyze;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.ArrayUtil;
import com.njyb.gbdbas.util.ECacheContrastUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.SearchRightLibraryModel;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.usermanagement.MixDataModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IRightLibraryConstructionData;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.IMonthYearTrendService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 同环比接口
 * @author WangBo 
 * 2015年5月15日
 * WithSequentialService.java
 */
@Service
public class WithSequentialService implements IWithSequentialService {

	// 同环比
	@Autowired
	private IMonthYearTrendService monthYearTrendService;
	
	// 过滤条件接口	
	@Autowired
	private IRightLibraryConstructionData rightLibraryConstructionData;

	// 公用搜索业务接口层
	@Autowired
	private IDataSearchService dataSearchService;
	
	// 权库配置文件
	@Resource
	private SearchRightLibraryModel searchRightLibraryModel;
	
	// 权库参数处理核心接口
	@Autowired
	private IRightLibraryConstructionData libraryConstructionData;

	/**
	 * 同环比
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Map<String, List<T>> queryWithSequentiallMap(
			Map<String, Object> paramMap) {
		Map<String, List<T>> resultMap = Maps.newHashMap();
		if (null != paramMap) {
			RightLibrarySearchModel queryModel = (RightLibrarySearchModel) paramMap.get("queryModel");
			HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
			List<String> feilds = (List<String>) paramMap.get("key");
			List<String> values = (List<String>) paramMap.get("value");
			String reportType = queryModel.getFlexType().toString();
			String cacheKey = queryModel.getWidthType() + feilds.toString() + values.toString() + queryModel.getCountrySelect();
			// 判断缓存中是否存在key
			if (null != ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, cacheKey)) {
				return (Map<String, List<T>>) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, cacheKey);
			}
			ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, cacheKey, cacheKey);
			Map<String,Map<String,Object>> resultWithMap = rightLibraryConstructionData.constantParamByCountry(feilds, values, queryModel.getCountrySelect());
			for (Entry<String, Map<String, Object>> entyry : resultWithMap.entrySet()) {
				String country = InitCountryCENameUtil.queryCountryEnName(entyry.getKey());				//获取英文名称
				List<String> newKey = (List<String>) entyry.getValue().get("key");					//获取Key
				List<String> newValue = (List<String>) entyry.getValue().get("value");  			//获取value
				String [] key = ArrayUtil.getStringArray(newKey);									//得到数组
				String [] value = ArrayUtil.getStringArray(newValue);
				// TODO Thread
				ReportCommonParamModel reportModel = new ReportCommonParamModel(key, value, country, ParamEnumUtil.report.toString(), request, reportType);
				//读取配置文件
				String reportName = libraryConstructionData.getReportName(libraryConstructionData.getReportName(queryModel.getFlexType().toString()));
				// 注意: 权库只有月度同比和环比
				resultMap = monthYearTrendService.getRatioModelMap(reportModel, reportName,"",queryModel.getWidthType());		//同环比
				if (null != resultMap && !resultMap.isEmpty()) {		// 放入缓存
					ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, cacheKey, resultMap);
				}
			}
		}
		return resultMap;
	}
	
	/**
	 * 生成图表model
	 * @param <T>
	 */
	@Override
	public <T> MixDataModel getMixDataModel(List<T> paramList) {
		Object [] data = ArrayUtil.getObjArray(paramList);
		MixDataModel mixDataModel = new MixDataModel();
		mixDataModel.setData(data);
		mixDataModel.setType("bar");
		mixDataModel.setName("市场趋势同环比报告");
		mixDataModel.setyIndex(0);
		return mixDataModel;
	}
}