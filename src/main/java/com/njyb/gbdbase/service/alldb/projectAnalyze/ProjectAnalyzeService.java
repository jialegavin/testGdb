package com.njyb.gbdbase.service.alldb.projectAnalyze;

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
import com.njyb.gbdbas.util.ArrayUtil;
import com.njyb.gbdbas.util.ECacheContrastUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.StringUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.alldb.projectanalyze.IProjectAnalyzeDao;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.alldb.projectAnalyze.BuyerModel;
import com.njyb.gbdbase.model.alldb.projectAnalyze.ProjectAnalyzeModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.service.alldb.buyerresource.IBuyerResourceService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IDataConvertDBModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IQueryDataService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IRightLibraryConstructionData;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;
import com.njyb.gbdbase.service.alldb.competitor.IMarketAnalysisReportService;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 产品分析 {权库分析}
 * 
 * @author WangBo 2015年4月20日 ProjectAnalyzeService.java
 */
@Service
public class ProjectAnalyzeService implements IProjectAnalyzeService {

	// 产品分析
	@Autowired
	private IProjectAnalyzeDao projectAnalyzeDao;

	// 市场分析报表
	@Autowired
	private IMarketAnalysisReportService marketAnalysisReportService;
	
	// 买家资源库
	@Autowired
	private IBuyerResourceService buyerResourceService;
	
	// 公用搜索业务接口层
	@Autowired
	private IDataSearchService dataSearchService;
	
	// 构造条件模块
	@Autowired
	private IRightLibraryConstructionData rightLibraryConstructionData;
	
	// lucene的调用接口
	@Autowired
	private ISearchEngineService searcherEngineService;
	
	// 数据转换接口
	@Autowired
	private IDataConvertDBModel dataConvertDBModel;
	
	// 数据查询接口
	@Autowired
	private IQueryDataService queryDataService;
	
	// 日志
	private static final Logger log = Logger.getLogger(ProjectAnalyzeService.class);

	/**
	 * 添加产品标签
	 */
	@Override
	public int addProjectAnalyze(ProjectAnalyzeModel projectAnalyzeModel) {
		//切换数据库
		
		if (null != projectAnalyzeModel) {
			projectAnalyzeModel.setAddTime(new Date());
		}
		int result = projectAnalyzeDao.addProjectAnalyze(projectAnalyzeModel);
		return result;
	}

	/**
	 * 删除产品标签
	 */
	@Override
	public int deleteProjectAnalyze(ProjectAnalyzeModel projectAnalyzeModel) {
		int result = 0;
		if (null != projectAnalyzeModel) {
			String id = projectAnalyzeModel.getDeleteId();
			Map<String, Object> paramMap = Maps.newHashMap();
			paramMap.put("idList", id);
			projectAnalyzeDao.deleteProjectAnalyze(paramMap);
			result = (int) paramMap.get("flag");
		}
		return result;
	}

	/**
	 * 查询 产品标签{全库分析}
	 */
	@Override
	public List<ProjectAnalyzeModel> queryProjectAnalyzeModelByParam(
			Map<String, Object> paramMap) {
		List<ProjectAnalyzeModel> queryList = Lists.newArrayList();
		if (null != paramMap && !paramMap.isEmpty()) {
			ProjectAnalyzeModel projectAnalyzeModel = (ProjectAnalyzeModel) paramMap
					.get("queryModel");
			paramMap.put("hscode", projectAnalyzeModel.getHscode());
			paramMap.remove("queryModel");
			queryList = projectAnalyzeDao.queryProjectAnalyzeModelByParam(paramMap);
		}
		return queryList;
	}

	/**
	 * 修改 产品标签
	 */
	@Override
	public int updateProjectAnalyze(ProjectAnalyzeModel projectAnalyzeModel) {
		int result = 0;
		if (null != projectAnalyzeModel) {
			result = projectAnalyzeDao
					.updateProjectAnalyze(projectAnalyzeModel);
		}
		return result;
	}

	/**
	 * 产品标签<br>
	 * 市场分析
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataReportSumModel> queryMarketAnalysisData(Map<String, Object> paramMap) {
		List<DataReportSumModel> resultList = Lists.newArrayList();
		if (null != paramMap) {
			try {
				HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
				RightLibrarySearchModel queryModel = (RightLibrarySearchModel) paramMap.get("queryModel");
				List<String> key = (List<String>)paramMap.get("key");
				List<String> value = (List<String>)paramMap.get("value");
				String countryName = "";
				String sortKey = "";
				if (null != queryModel) {
					countryName = Strings.isNullOrEmpty(queryModel.getCountrySelect()) ? "" : queryModel.getCountrySelect();
					sortKey = Strings.isNullOrEmpty(queryModel.getOldName()) ? "" : queryModel.getOldName();
				}
				resultList = marketAnalysisReportService.queryReportDateByType(request, queryModel, key, value, countryName, sortKey);
			} catch (Exception e) {
				e.printStackTrace();
				log.debug(e.getMessage());
			}
		}
		return resultList;
	}

	/**
	 * 产品标签<br>
	 * 交易记录
	 */
	@Override
	public List<AllDBModel> queryTradingRecordData(Map<String, Object> paramMap,PageBeanUtil pageBean) {
//		List<AllDBModel> resultList = Lists.newArrayList();
//		if (null != paramMap) {
//			try {
//				HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
//				RightLibrarySearchModel queryModel = (RightLibrarySearchModel) paramMap.get("queryModel");
//				List<String> key = (List<String>)paramMap.get("key");
//				List<String> value = (List<String>)paramMap.get("value");
//				// 读取缓存中的数据
//				String eCacheKey = queryModel.getCountrySelect() + key.toString() + value.toString();
//				if (null != ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey)) {
//					return (List<AllDBModel>) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey);
//				}
//				//构造条件
//				Map<String,Map<String,Object>> resultMap = rightLibraryConstructionData.constantParamByCountry(key, value, queryModel.getCountrySelect());
//				resultList = queryDataService.queryAllDBData(ParamEnumUtil.search.toString(), request, pageBean, resultMap);									//获得数据
//				// 放入缓存
//				if (null != resultList && !resultList.isEmpty()) {
//					ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey, resultList);
//				}
//			} catch (Exception e) {
//				log.debug(e.getMessage());
//			}
//		}
		return null;
	}

	/**
	 * 买家资源库
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BuyerModel> queryBuyerList(Map<String, Object> paramMap) {
		List<BuyerModel> resultList = Lists.newArrayList();
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		PageBeanUtil page = (PageBeanUtil) paramMap.get("pageBean");
		RightLibrarySearchModel queryModel = (RightLibrarySearchModel)paramMap.get("queryModel");
		List<String> key = (List<String>)paramMap.get("key");
		List<String> value = (List<String>)paramMap.get("value");
		String countryName = queryModel.getCountrySelect();
		// 读取缓存中的数据
		String eCacheKey = countryName + key.toString() + value.toString();
		if (null != ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey)) {
			return (List<BuyerModel>) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey);
		}
		// 国家
		if (null != queryModel) {
			// 如果为"" 是全部国家
			if (Strings.isNullOrEmpty(countryName) || countryName.equals("全部")) {
				countryName = "";
			}
		}
		String [] fields = ArrayUtil.getStringArray(key);	// key
		String [] values = ArrayUtil.getStringArray(value);	// value
		String module = ParamEnumUtil.buyer.toString();		//买家资源库 常量
		page.setPageSize(Integer.MAX_VALUE);
		//获取数据
		resultList = buyerResourceService.queryBuyerModelList(request, fields, values, countryName, module, page);
		resultList = queryBuyerList(request, resultList, countryName);
		if (null != resultList && !resultList.isEmpty()) {
			ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey, resultList);
		}
		return resultList;
	}
	
	/**
	 * 根据权限中的国家查询出相应的数据
	 * @param request : 请求
	 * @param buyerList : 买家资源库
	 * @param countryName : 国家名称
	 * @return
	 */
	private List<BuyerModel> queryBuyerList(HttpServletRequest request,List<BuyerModel> buyerList,String countryName) {
		List<ConditionRightModel> list = (List<ConditionRightModel>) request.getSession().getAttribute("authorityInfo");
		List<BuyerModel> queryBuyList = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(countryName)) {
			queryBuyList = buyerResourceService.queryBuyerModelByCountry(countryName, buyerList);		//根据国家筛选
		} else {
			for (ConditionRightModel model : list) {
				List<BuyerModel> temp_buyerList = buyerResourceService.queryBuyerModelByCountry(model.getByCountry(), buyerList);
				queryBuyList.addAll(temp_buyerList);
			}
		}
		return queryBuyList;
	}
	
	/**
	 * 竞争对手
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<DataReportSumModel> queryCompetitorByParam(Map<String, Object> paramMap) {
		// 将key和value放入竞争对手缓存
		List<DataReportSumModel> compList = Lists.newArrayList();
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		RightLibrarySearchModel queryModel = (RightLibrarySearchModel)paramMap.get("queryModel");
		List<String> key = (List<String>)paramMap.get("key");
		List<String> value = (List<String>)paramMap.get("value");
		String countryName = queryModel.getCountrySelect();
		String [] country = StringUtil.getSplitStr(countryName);
		// 读取缓存
		String eCacheKey = key.toString() + value.toString() + countryName;
		if (null != ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey)) {
			return (List<DataReportSumModel>) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey);
		}
		// 构造条件
//		Map<String,Map<String,Object>> resultMap = rightLibraryConstructionData.constantParamByCountry(key, value, countryName);
		try {
			compList = rightLibraryConstructionData.getMarketAnalysisReportList(request, key, value, country, "", queryModel.getFlexType().toString(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		queryDataService.queryDataByParams(request,key,value,"",true);
//		objectList = queryDataService.queryAllDBData(ParamEnumUtil.search.toString(), request, pageBean, resultMap);									//获得数据
		compList = filterCompanyName(compList);
		// 将查询结果放入权库缓存中
		if (null != compList && !compList.isEmpty()){
			ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, eCacheKey, compList);
		}
		return compList;
	}
	
	/**
	 * 过滤出口商和进口商
	 * @param dataReportList
	 * @return
	 */
	private List<DataReportSumModel> filterCompanyName(List<DataReportSumModel> dataReportList) {
 		List<DataReportSumModel> newList = Lists.newArrayList();
		for (DataReportSumModel dataReportSumModel : dataReportList) {
			if (!Strings.isNullOrEmpty(dataReportSumModel.getExporter()) && !dataReportSumModel.getExporter().trim().equals("N/A".trim())) {
				newList.add(dataReportSumModel);
			} else if (!Strings.isNullOrEmpty(dataReportSumModel.getImporter())){
				newList.add(dataReportSumModel);
			}
		}
		return newList;
	}
}