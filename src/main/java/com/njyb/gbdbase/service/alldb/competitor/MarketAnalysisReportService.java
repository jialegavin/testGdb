package com.njyb.gbdbase.service.alldb.competitor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.ECacheContrastUtil;
import com.njyb.gbdbas.util.StringUtil;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IDataConvertDBModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IRightLibraryConstructionData;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;

/**
 * 市场分析
 * 
 * @author WangBo 2015年4月14日 MarketAnalysisReportService.java
 */
@Service
public class MarketAnalysisReportService implements
		IMarketAnalysisReportService {
	
	// log日志
	private static final Logger log = Logger.getLogger(MarketAnalysisReportService.class);
	
	// 构造条件模块
	@Autowired
	private IRightLibraryConstructionData rightLibraryConstructionData;
	
	// 数据转换接口
	@Autowired
	private IDataConvertDBModel dataConvertDBModel;

	/**
	 * 设置查询市场分析报告model中的值构造查询条件
	 */
	@Override
	public Map<String, Object> setMarketAnalysisReportFields(
			HttpServletRequest request, RightLibrarySearchModel model) {
		// 获取model的值 并且构造成索引
		Map<String, Object> paramMap = Maps.newHashMap();
		// 存放数据
		List<String> fieldKey = Lists.newArrayList();
		List<String> fieldValue = Lists.newArrayList();
		if (!StringUtil.isEmpty(model.getBeginDateFlex())
				&& !StringUtil.isEmpty(model.getEndDateFlex())) {
			fieldKey.add(RightLibraryConstant.DATE);
			fieldValue.add(model.getBeginDateFlex() + ","
					+ model.getEndDateFlex());
		}
		if (!StringUtil.isEmpty(model.getGoodsdescription())) {
			fieldKey.add(RightLibraryConstant.GOODS_DESC);
			fieldValue.add(ReportHelpUtil.handleSybmol(model.getGoodsdescription()));
		}
		if (!StringUtil.isEmpty(model.getHscode())) {
			fieldKey.add(RightLibraryConstant.HSCODE);
			fieldValue.add(model.getHscode());
		}
		if (!StringUtil.isEmpty(model.getTradeType())) {
			fieldKey.add(RightLibraryConstant.TRADETYPE);
			fieldValue.add(model.getTradeType());
		}
		if (!StringUtil.isEmpty(model.getCompanyName())
				&& !StringUtil.isEmpty(model.getCompanyName())) {
			fieldKey.add(RightLibraryConstant.COMPANY_NAME);
			fieldValue.add(ReportHelpUtil.handleSybmol(model.getCompanyName()));
		}
		if (!StringUtil.isEmpty(model.getImporter())
				&& !StringUtil.isEmpty(model.getImporter())) {
			fieldKey.add(RightLibraryConstant.IMPORTER);
			fieldValue.add(ReportHelpUtil.handleSybmol(model.getImporter()));
		}
		if (!StringUtil.isEmpty(model.getExporter())
				&& !StringUtil.isEmpty(model.getExporter())) {
			fieldKey.add(RightLibraryConstant.EXPORTER);
			fieldValue.add(ReportHelpUtil.handleSybmol(model.getExporter()));
		}
		paramMap.put("key", fieldKey);
		paramMap.put("value", fieldValue);
		return paramMap;
	}

	/**
	 * 市场分析报告所有报告方法汇总
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataReportSumModel> queryReportDateByType(HttpServletRequest request,
			RightLibrarySearchModel model, List<String> key,
			List<String> value, String countryName, String sortKey) {
		List<DataReportSumModel> pakistanlist = Lists.newArrayList();
		// 构造条件
		String flexType = model.getFlexType().toString();
		String[] countrys = StringUtil.getSplitStr(countryName);
		String cacheKey = flexType + model.getBeginDateFlex() + model.getEndDateFlex() + countryName + key.toString() + value.toString();
		System.out.println(cacheKey);
		// 读取缓存
		if (null != ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, cacheKey)){
			return (List<DataReportSumModel>) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, cacheKey);
		}
		try {
			pakistanlist = rightLibraryConstructionData.getMarketAnalysisReportList(request, key, value, countrys, sortKey, flexType, true);
			if (null != pakistanlist && !pakistanlist.isEmpty()) {		//如果不为空或者不为null
				pakistanlist = dataConvertDBModel.convertDataByCountryAndReportType(pakistanlist, countrys, flexType);
				// 存入缓存
				if (null != pakistanlist && !pakistanlist.isEmpty()) {
					ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE,cacheKey,pakistanlist);
				}
			}
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
		}
		return pakistanlist;
	}
}