package com.njyb.gbdbase.service.alldb.competitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.ECacheContrastUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.StringUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.alldb.competitor.ICompetitorAndCustomerInfoDao;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.ComAndCusInfoResultModel;
import com.njyb.gbdbase.model.alldb.competitor.QueryCompetitorQueryModel;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IDataConvertDBModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IQueryDataService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IRightLibraryConstructionData;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 竞争对手
 * 
 * @author WangBo 2015年4月7日 CompetitorService.java
 */
@Service
public class CompetitorService implements ICompetitorService {

	// log记录日志
	private static final Logger log = Logger.getLogger(CompetitorService.class);

	// 竞争对手&&客户信息查询信息
	@Autowired
	private ICompetitorAndCustomerInfoDao competitorDao;

	// 市场分析 服务接口
	@Autowired
	private IMarketAnalysisReportService marketAnalysisReportService;
	
	// 构造条件模块
	@Autowired
	private IRightLibraryConstructionData rightLibraryConstructionData;
	
	// 数据查询接口
	@Autowired
	private IQueryDataService queryDataService;
	
	// lucene的调用接口
	@Autowired
	private  ISearchEngineService searcherEngineService;
	
	// 数据库共用搜索接口
	@Autowired
	private IDataSearchService dataSearchService;
	
	// 数据转换接口
	@Autowired
	private IDataConvertDBModel dataConvertDBModel;
	

	/**
	 * 竞争对手查询
	 */
	@Override
	public List<ComAndCusInfoResultModel> queryCompetitorResult(
			Map<String, Object> paramMap) {
		List<ComAndCusInfoResultModel> resultList = Lists
				.newArrayList();
		if (!paramMap.isEmpty()) {
			PageBeanUtil pageBean = (PageBeanUtil) paramMap.get("pageUtil");
			QueryCompetitorQueryModel queryCompetitorQueryModel = (QueryCompetitorQueryModel) paramMap
					.get("queryModel");
			if (null != queryCompetitorQueryModel && null != pageBean) {
				paramMap.put("companyName",StringUtil.getKillNull(queryCompetitorQueryModel.getCompanyName()));
				paramMap.put("param", IConstantUtil.COMPETITOR);
				paramMap.remove("pageUtil");
				paramMap.remove("queryModel");
				//切换数据库
				resultList = competitorDao.queryCompetitorResult(paramMap);
			}
		}
		return resultList;
	}

	/**
	 * 删除竞争对手
	 */
	@Override
	public int deleteCompetitorAndCustomerInfo(
			ComAndCusInfoResultModel competitorAndCustomerInfoResultModel) {
		int result = 0;
		if (null != competitorAndCustomerInfoResultModel) {
			String id = competitorAndCustomerInfoResultModel.getId();
			Map<String, Object> paramMap = Maps.newHashMap();
			paramMap.put("idList", id);
			//切换数据库
			competitorDao.deleteCompetitorAndCustomerInfo(paramMap);
			result = (int) paramMap.get("flag");
		}
		return result;
	}

	/**
	 * 添加功能
	 */
	@Override
	public int addCompetitorAndCustomerInfo(
			ComAndCusInfoResultModel competitorAndCustomerInfoResultModel) {
		int result = 0;
		if (null != competitorAndCustomerInfoResultModel) {
			if (null == competitorAndCustomerInfoResultModel.getAddTime()){
				competitorAndCustomerInfoResultModel.setAddTime(new Date());
			}
			//切换数据库
			result = competitorDao
					.addCompetitor(competitorAndCustomerInfoResultModel);
		}
		return result;
	}

	/**
	 * 修改竞争对手
	 */
	@Override
	public int updateCompetitorAndCustomerInfo(
			ComAndCusInfoResultModel competitorAndCustomerInfoResultModel) {
		int result = 0;
		if (null != competitorAndCustomerInfoResultModel) {
			//切换数据库
			result = competitorDao
					.updateCompetitor(competitorAndCustomerInfoResultModel);
		}
		return result;
	}

	/**
	 * 市场分析
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataReportSumModel> queryMarketAnalysisData(Map<String, Object> paramMap) {
		// 获取Map的数据 {key}
		HttpServletRequest request = (HttpServletRequest) paramMap
				.get("request");
		RightLibrarySearchModel queryModel = (RightLibrarySearchModel) paramMap
				.get("queryModel");
		List<String> key = (List<String>) paramMap.get("key");
		List<String> value = (List<String>) paramMap.get("value");
		String countryName = queryModel.getCountrySelect();
		String sortKey = Strings.isNullOrEmpty(queryModel.getOldName()) ? "weight"
				: queryModel.getOldName();
		List<DataReportSumModel> allDBList = marketAnalysisReportService
				.queryReportDateByType(request, queryModel, key, value,
						countryName, sortKey);
		return allDBList;
	}

	/**
	 * 交易记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AllDBModel> queryTradingRecordData(Map<String, Object> paramMap,PageBeanUtil pageBean) {
		List<AllDBModel> allDBList = Lists.newArrayList();
		HttpServletRequest request = (HttpServletRequest) paramMap
				.get("request");
		RightLibrarySearchModel queryModel = (RightLibrarySearchModel) paramMap
				.get("queryModel");
		List<String> key = (List<String>) paramMap.get("key");
		List<String> value = (List<String>) paramMap.get("value");
		String countryName = queryModel.getCountrySelect();
		String elementKey = key.toString() + value.toString() + countryName;
		// 读取缓存
		if (null != ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, elementKey)) {
			return (List<AllDBModel>) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, elementKey);
		}
		try {
			Map<String,Map<String,Object>> resultMap = rightLibraryConstructionData.constantParamByCountry(key, value, countryName);	//鑾峰彇鍒版瀯閫犲ソ鐨勬潯浠�lucene鏌ヨ
			allDBList = queryDataService.queryAllDBData(ParamEnumUtil.search.toString(), request, pageBean, resultMap);					//鑾峰緱鏁版嵁
			// 存入缓存
			if (null != allDBList) {
				ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, elementKey ,allDBList);
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return allDBList;
	}
}