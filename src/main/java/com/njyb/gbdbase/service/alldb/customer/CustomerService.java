package com.njyb.gbdbase.service.alldb.customer;

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
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.alldb.customer.QueryCustomerModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IDataConvertDBModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IQueryDataService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IRightLibraryConstructionData;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;
import com.njyb.gbdbase.service.alldb.competitor.IMarketAnalysisReportService;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 客户信息<br>
 * 
 * @author WangBo 2015年4月8日 CustomerService.java
 */
@Service
public class CustomerService implements ICustomerService {

	// log记录日志
	private static final Logger log = Logger.getLogger(CustomerService.class);
	
	// 用户信息Dao层
	@Autowired
	private ICompetitorAndCustomerInfoDao competitorAndCustomerInfoDao;
	
	// 市场分析报表
	@Autowired
	private IMarketAnalysisReportService marketAnalysisReportService;
	
	// 构造条件模块
	@Autowired
	private IRightLibraryConstructionData rightLibraryConstructionData;
		
	// lucene的调用接口
	@Autowired
	private  ISearchEngineService searcherEngineService;
	
	// 数据库共用搜索接口
	@Autowired
	private IDataSearchService dataSearchService;
	
	// 数据转换接口
	@Autowired
	private IDataConvertDBModel dataConvertDBModel;
	
	// 数据查询接口
	@Autowired
	private IQueryDataService queryDataService;

	/**
	 * 查询用户信息
	 */
	@Override
	public List<ComAndCusInfoResultModel> queryCompetitorResult(
			Map<String, Object> paramMap) {
		List<ComAndCusInfoResultModel> resultList = Lists.newArrayList();
		if (!paramMap.isEmpty()) {
			PageBeanUtil pageBean = (PageBeanUtil) paramMap.get("pageBean");
			QueryCustomerModel queryCompetitorQueryModel = (QueryCustomerModel) paramMap
					.get("queryModel");
			if (null != queryCompetitorQueryModel && null != pageBean) {
				paramMap.put("companyName",
						StringUtil.getKillNull(queryCompetitorQueryModel.getCompanyName()));
				paramMap.put("param", IConstantUtil.CUSTOMER);
				paramMap.remove("pageBean");
				paramMap.remove("queryModel");
				//切换数据库
				resultList = competitorAndCustomerInfoDao.queryCompetitorResult(paramMap);
			}
		}
		return resultList;
	}

	/**
	 * 删除用户信息
	 */
	@Override
	public int deleteCompetitorAndCustomerInfo(
			ComAndCusInfoResultModel competitorAndCustomerInfoResultModel) {
		int reuslt = 0;
		if (null != competitorAndCustomerInfoResultModel) {
			String id = competitorAndCustomerInfoResultModel.getId();
			Map<String, Object> paramMap = Maps.newHashMap();
			paramMap.put("idList", id);
			//切换数据库
			
			competitorAndCustomerInfoDao
					.deleteCompetitorAndCustomerInfo(paramMap);
			reuslt = (int) paramMap.get("flag");
		}
		return reuslt;
	}

	/**
	 * 添加客户信息
	 */
	@Override
	public int addCompetitorAndCustomerInfo(
			ComAndCusInfoResultModel competitorAndCustomerInfoResultModel) {
		int result = 0;
		if (null != competitorAndCustomerInfoResultModel) {
			competitorAndCustomerInfoResultModel
					.setUserType(IConstantUtil.CUSTOMER);
			if (null != competitorAndCustomerInfoResultModel){
				competitorAndCustomerInfoResultModel.setAddTime(new Date());
			}
			//切换数据库
			
			result = competitorAndCustomerInfoDao
					.addCustomerInfo(competitorAndCustomerInfoResultModel);
		}
		return result;
	}

	/**
	 * 修改客户信息
	 */
	@Override
	public int updateCompetitorAndCustomerInfo(
			ComAndCusInfoResultModel competitorAndCustomerInfoResultModel) {
		int result = 0;
		if (null != competitorAndCustomerInfoResultModel) {
			competitorAndCustomerInfoResultModel
					.setUserType(IConstantUtil.CUSTOMER);
			//切换数据库
			
			result = competitorAndCustomerInfoDao
					.updateCustomerInfo(competitorAndCustomerInfoResultModel);
		}
		return result;
	}

	/**
	 * 市场分析
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataReportSumModel> queryMarketAnalysisData(Map<String, Object> paramMap) {
		List<DataReportSumModel> allDBList = Lists.newArrayList();
		HttpServletRequest request = (HttpServletRequest) paramMap
				.get("request");
		RightLibrarySearchModel queryModel = (RightLibrarySearchModel) paramMap
				.get("queryModel");
		List<String> key = (List<String>) paramMap.get("key");
		List<String> value = (List<String>) paramMap.get("value");
		String countryName = queryModel.getCountrySelect();
		String sortKey = Strings.isNullOrEmpty(queryModel.getOldName()) ? "weight"
				: queryModel.getOldName();
		try {
			allDBList = marketAnalysisReportService.queryReportDateByType(request, queryModel, key, value, countryName, sortKey);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return allDBList;
	}

	/**
	 * 交易记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AllDBModel> queryTradingRecordData(Map<String, Object> paramMap,PageBeanUtil pageBean) {
		List<AllDBModel> allDBList = Lists.newArrayList();
		if (!paramMap.isEmpty() && null != paramMap) {
			HttpServletRequest request = (HttpServletRequest) paramMap
					.get("request");
			RightLibrarySearchModel queryModel = (RightLibrarySearchModel) paramMap
					.get("queryModel");
			List<String> key = (List<String>) paramMap.get("key");
			List<String> value = (List<String>) paramMap.get("value");
			String elementKey = key.toString() + value.toString() + queryModel.getCountryName() + queryModel.getCountrySelect();
			// 读取缓存
			if (null != ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, elementKey)) {
				return (List<AllDBModel>) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, elementKey);
			}
			Map<String,Map<String,Object>> resultMap = rightLibraryConstructionData.constantParamByCountry(key, value, queryModel.getCountrySelect());	//鑾峰彇鍒版瀯閫犲ソ鐨勬潯浠�lucene鏌ヨ
			allDBList = queryDataService.queryAllDBData(ParamEnumUtil.search.toString(), request, pageBean, resultMap);									//鑾峰緱鏁版嵁
			// 存入缓存
			if (null != allDBList) {
				ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, elementKey ,allDBList);
			}
		}
		return allDBList;
	}
}