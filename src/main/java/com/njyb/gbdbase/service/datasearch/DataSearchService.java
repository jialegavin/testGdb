package com.njyb.gbdbase.service.datasearch;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.beanutils.BeanUtils;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.BuildTradeChartUtil;
import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.GetAllCountryNameUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.model.datasearch.common.ConditionModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.KeyValueModel;
import com.njyb.gbdbase.model.datasearch.common.MapModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.usermanagement.MixDataModel;
import com.njyb.gbdbase.model.usermanagement.PieDataModel;
import com.njyb.gbdbase.service.common.componet.report.build.RequestMap;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;

/**
 * 公用搜索业务接口实现层
 * 
 * @author 洪浩/贾红平
 * @date 2015-04-10
 * @version 标准版/修订版
 * @param <T>
 */
@Service
@SuppressWarnings("all")
public class DataSearchService<T> extends CommonSearchService implements
		IDataSearchService {
	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IDataSearchService#getDataById(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getDataById(String country, List<Integer> idList) {
		// 切换数据库
		//DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_DS);
		List<T> list = new ArrayList<T>();
		SqlModel sqlModel = new SqlModel();
		sqlModel.setSql(getMyBatiesSql(idList));
		if (country.equals(DataSearchConstantUtil.ARGENTINA_IMPORT)) {
			list = (List<T>) IArgentinaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.ARGENTINA_EXPORT)) {
			list = (List<T>) IArgentinaExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.BRAZIL_IMPORT)) {
			list = (List<T>) IBrazilImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.CHILE_EXPORT)) {
			list = (List<T>) IChileExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.CHILE_IMPORT)) {
			list = (List<T>) IChileImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.CHINA_EIGHT)) {
			list = (List<T>) IChinaEightDao.queryByPrimaryAll(sqlModel);
			if(list!=null){
				for(T t:list){
					try {
						String orgdate=BeanUtils.getProperty(t, "date");
					    if (orgdate.contains("/")) {
					    	String ndate=orgdate.substring(3,orgdate.length())+orgdate.substring(0,2);
							BeanUtils.setProperty(t, "date", ndate);
						}
					    else{
					    	BeanUtils.setProperty(t, "date", orgdate);
					    }
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else if (country.equals(DataSearchConstantUtil.COLOMBIA_EXPORT)) {
			list = (List<T>) IColombiaExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.COLOMBIA_IMPORT)) {
			list = (List<T>) IColombiaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.COSTARICA_EXPORT)) {
			list = (List<T>) ICostaricaExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.COSTARICA_IMPORT)) {
			list = (List<T>) ICostaricaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.ECUADOR_EXPORT)) {
			list = (List<T>) IEcuadorExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.ECUADOR_IMPORT)) {
			list = (List<T>) IEcuadorImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.GUATEMALA_EXPORT)) {
			list = (List<T>) IGuatemalaExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.GUATEMALA_IMPORT)) {
			list = (List<T>) IGuatemalaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.HONDURAS_IMPORT)) {
			list = (List<T>) IHondurasImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.INDIA_IMPORT)) {
			list = (List<T>) IIndiaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.KOREA)) {
			list = (List<T>) IKoreaDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.MEXICO_IMPORT)) {
			list = (List<T>) IMexicoImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.NICARAGUA_EXPORT)) {
			list = (List<T>) INicaraguaExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.NICARAGUA_IMPORT)) {
			list = (List<T>) INicaraguaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.PAKISTAN_IMPORT)) {
			list = (List<T>) IPakistanImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.PANAMA_EXPORT)) {
			list = (List<T>) IPanamaExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.PANAMA_IMPORT)) {
			list = (List<T>) IPanamaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.PERU_EXPORT)) {
			list = (List<T>) IPeruExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.PERU_IMPORT)) {
			list = (List<T>) IPeruImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.SALVATORE_EXPORT)) {
			list = (List<T>) ISalvatoreExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.SALVATORE_IMPORT)) {
			list = (List<T>) ISalvatoreImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.UK_IMPORT)) {
			list = (List<T>) IUkImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.URUGUAY_EXPORT)) {
			list = (List<T>) IUruguayExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.URUGUAY_IMPORT)) {
			list = (List<T>) IUruguayImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.VENEZUELA_EXPORT)) {
			list = (List<T>) IVenezuelaExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.VENEZUELA_IMPORT)) {
			list = (List<T>) IVenezuelaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.VIETNAM_EXPORT)) {
			list = (List<T>) IVietnamExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.VIETNAM_IMPORT)) {
			list = (List<T>) IVietnamImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.PARAGUAY_EXPORT)) {
			list = (List<T>) IParaguayExportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.PARAGUAY_IMPORT)) {
			list = (List<T>) IParaguayImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.USA_IMPORT)) {
			list = (List<T>) IUsaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.UKRAINE_IMPORT)) {
			list = (List<T>) ukraineImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.RUSSIA_IMPORT)) {
			list = (List<T>) russiaImportDao.queryByPrimaryAll(sqlModel);
		} else if (country.equals(DataSearchConstantUtil.RUSSIA_EXPORT)) {
			list = (List<T>) russiaExportDao.queryByPrimaryAll(sqlModel);
		} else if(country.equals(DataSearchConstantUtil.BOLIVIA_IMPORT)){
			list = (List<T>) boliviaImportDao.queryByPrimaryAll(sqlModel);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IDataSearchService#getMyBatiesSql(java.util.List)
	 */
	public String getMyBatiesSql(List<Integer> idList) {
		if (idList == null || idList.size() == 0) {
			idList.add(-1);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (Integer i : idList) {
			sb.append(i + ",");
		}
		return sb.substring(0, sb.lastIndexOf(",")) + ")";
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IDataSearchService#querySearchDetailFields(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Set<String> querySearchDetailFields(HttpServletRequest request) {
		// 初始化查看明细配置文件
		new LoadPropertiesUtil().init(request, IConstantUtil.VIEWDETAIL);
		// 获取所有国家英文名称
		List<String> countryList = GetAllCountryNameUtil.getAllCountryName();
		// 拼接所有国家的查看详情id
		StringBuffer str = new StringBuffer();
		for (String country : countryList) {
			// 组装获取查看详情id的key值
			String key = country + "_fieldvalue";
			// 获取value
			String value = LoadPropertiesUtil.getValue(key);
			if (!"".equals(value) && null != value) {
				// 将所有分号冒号替换成逗号
				String newValue = value.trim().replaceAll("[:;]", ",");
				// 拼接字符串
				str.append(newValue);
			}
		}
		// 分割成数组
		String[] allIds = str.toString().split(",");
		// 数组转list集合
		List<String> list = Arrays.asList(allIds);
		// 去重
		Set<String> hset = new HashSet<String>(list);
		return hset;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IDataSearchService#solveCountryHscode(java.lang.Object, java.lang.String)
	 */
	public <T> String solveCountryHscode(T model, String country) {
		String hscodeOrblNumber = null;
		try {
			if (country.equals(LuceneConstant.SALVATORE_IMPROT_STRING)
					|| country.equals(LuceneConstant.SALVATORE_EXPORT_STRING)
					|| country.equals(LuceneConstant.NICARAGUA_IMPORT_STRING)
					|| country.equals(LuceneConstant.NICARAGUA_EXPORT_STRING)
					|| country.equals(LuceneConstant.GUATEMALA_IMPORT_STRING)
					|| country.equals(LuceneConstant.GUATEMALA_EXPORT_STRING)
					|| country.equals(LuceneConstant.USA_IMPORT_STRING)
					|| country.equals(LuceneConstant.PAKISTAN_IMPORT_STRING)) {
				hscodeOrblNumber = BeanUtils.getProperty(model, "blNumber");
			} else {
				hscodeOrblNumber = BeanUtils.getProperty(model, "hscode");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return hscodeOrblNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IReportService#getReportList(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public List<DataReportSumModel> getReportList(HttpServletRequest request,
			String tableId, String type, String queryKey, String queryValue,
			int loadType) throws Exception {
		String cacheRepType = "";
		if (!Strings.isNullOrEmpty(tableId)) {
			 String [] typeArray = tableId.split("_");
			 //针对中国，英国，韩国 特殊进口商国家处理/zg_jks
			 if (typeArray.length<=2){
				 request.setAttribute("rep_type", typeArray[0]);
				 cacheRepType = typeArray[0];
			 }else{
				 request.setAttribute("rep_type", typeArray[0] + "_" + typeArray[1]);
				 cacheRepType = typeArray[0] + "_" + typeArray[1];
			 }
		 }
		
		// 获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		String lastQueryKey = KeyValueModel.getLastQueryKey();
		String lastQueryValue = KeyValueModel.getLastQueryValue();
		String lastCountry = KeyValueModel.getLastCountry();
		Map<String, List<DataReportSumModel>> reportMap = new HashMap<String, List<DataReportSumModel>>();
		// 缓存对象
		Cache ehCache = CreateEncache.getEacheInstance().getCache(
				"dataSearchCache");
		// 判断是否满足过滤条件
		boolean isTrue = ReportHelpUtil.newInstanceReportUtil().isInitSearch(
				lastCountry, lastQueryKey.split(";"),
				lastQueryValue.split(";"), country, queryKey.split(";"),
				queryValue.split(";"));
		
		try {
			// 懒加载
			if (loadType == DataSearchConstantUtil.LAZY_LOAD) {
				 if(queryKey.equals(lastQueryKey) &&
					 queryValue.equals(lastQueryValue)
					 && ehCache.get(country+queryKey+queryValue+type+cacheRepType) != null &&
					 country.equals(lastCountry))
					 {
					 	 /*直接从缓存中获取*/
						 ehCache.acquireReadLockOnKey(country+queryKey+queryValue+type);
						 Element element = ehCache.get(country+queryKey+queryValue+type+cacheRepType);
						 ehCache.releaseReadLockOnKey(country+queryKey+queryValue+type);
						 reportMap = (Map<String, List<DataReportSumModel>>)element.getObjectValue();
				 }else
				 {
					 KeyValueModel.setLastQueryKey(queryKey);
					 KeyValueModel.setLastQueryValue(queryValue);
					 KeyValueModel.setLastCountry(country);
					 
					// 如果满足就不保存上一次查询条件,不满足的话就保存本次查询条件
					if (isTrue == false) {
						ConditionModel.setLastTimeCountry(country);
						ConditionModel.setLastTimeFields(queryKey); 
						ConditionModel.setLastTimeValues(queryValue);
					}
					 //存放type
					 RequestMap.getRequestMap().setRequest(request);
					 //获取报表汇总结果集map
					 reportMap = reportEngineService.builderMapList(new
					 ReportCommonParamModel(queryKey.split(";"),
					 queryValue.split(";"), country, "report", request,type),false);
					 Log.info("key : "+ queryKey + "value : "+ queryValue + "国家 : " + country + "type : " + type + "结果集个数 : " + reportMap.size());
					 System.out.println("key : "+ queryKey + "value : "+ queryValue + "国家 : " + country + "type : " + type + "结果集个数 : " + reportMap.size());
					 //将查询结果放入缓存中
					 ehCache.acquireWriteLockOnKey(country+queryKey+queryValue+type);
					 ehCache.put(new
					 Element(country+queryKey+queryValue+type+cacheRepType,reportMap));
					 ehCache.releaseWriteLockOnKey(country+queryKey+queryValue+type);
				 }
				
				// 获取报表汇总结果集map
				/*reportMap = reportEngineService.builderMapList(
						new ReportCommonParamModel(queryKey.split(";"), queryValue 
								.split(";"), country, "report", request, type),
						false);*/
			}
			// 全部加载
			else if (loadType == DataSearchConstantUtil.ALL_LOAD) {
				 if(queryKey.equals(lastQueryKey) &&
					 queryValue.equals(lastQueryValue)
					 && ehCache.get(country+queryValue+queryKey+type+cacheRepType) != null &&
					 country.equals(lastCountry))
					 {
					 ehCache.acquireReadLockOnKey(country+queryValue+queryKey+type+cacheRepType);
					 Element element = ehCache.get(country+queryValue+queryKey+type+cacheRepType);
					 ehCache.releaseReadLockOnKey(country+queryValue+queryKey+type+cacheRepType);
					 reportMap = (Map<String, List<DataReportSumModel>>)element.getObjectValue();
				 }else
				 {
					 KeyValueModel.setLastQueryKey(queryKey);
					 KeyValueModel.setLastQueryValue(queryValue);
					 KeyValueModel.setLastCountry(country);
					 
					// 如果满足就不保存上一次查询条件,不满足的话就保存本次查询条件
					if (isTrue == false) {
						ConditionModel.setLastTimeCountry(country);
						ConditionModel.setLastTimeFields(queryKey); 
						ConditionModel.setLastTimeValues(queryValue);
					}
					 //获取报表汇总结果集map
					 reportMap = reportEngineService.builderMapList(new
					 ReportCommonParamModel(queryKey.split(";"),
					 queryValue.split(";"), country, "report", request,type),true);
					 //将查询结果放入缓存中
					 ehCache.acquireWriteLockOnKey(country+queryValue+queryKey+type+cacheRepType);
					 ehCache.put(new
					 Element(country+queryValue+queryKey+type+cacheRepType,reportMap));
					 ehCache.releaseWriteLockOnKey(country+queryValue+queryKey+type+cacheRepType);
				 }
				
				// 获取报表汇总结果集map
				/*reportMap = reportEngineService.builderMapList(
						new ReportCommonParamModel(queryKey.split(";"), queryValue
								.split(";"), country, "report", request, type),
						true);*/
			}
		} catch (Exception e) {
			Log.debug(e);
			Log.info(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<DataReportSumModel> reportList = reportMap.get(tableId.replaceAll(
				"_table", ""));
		return reportList;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IReportService#getDeepReportList(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<DataReportSumModel> getDeepReportList(
			HttpServletRequest request, String value, String orginalType,
			String drillType) throws Exception {
		// 获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		// 报表钻取获取某一报告类型汇总结果集map
		List<DataReportSumModel> reportList = reportDrillService
				.drillReportSumList(value, orginalType, country, drillType);
		return reportList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IReportService#getDataCompareReportList(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)
	 */
	public List<DataReportSumModel> getDataCompareReportList(
			HttpServletRequest request, String hideColumn, String selectedValue)
			throws Exception {
		Map<String, List<DataReportSumModel>> reportMap = new HashMap<String, List<DataReportSumModel>>();
		// 获取查询条件
		String queryKey = MapModel.getMap().get("queryKey").toString();
		String queryValue = MapModel.getMap().get("queryValue").toString();
		// 获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");

		// 缓存对象
		Cache ehCache = CreateEncache.getEacheInstance().getCache(
				"dataSearchCache");
		 ehCache.acquireReadLockOnKey(country+queryKey+queryValue+hideColumn.split("罟")[0]+hideColumn.split("罟")[1]);
		 Element element =
		 ehCache.get(country+queryKey+queryValue+hideColumn.split("罟")[0]+hideColumn.split("罟")[1]);
		 ehCache.releaseReadLockOnKey(country+queryKey+queryValue+hideColumn.split("罟")[0]+hideColumn.split("罟")[1]);
		 reportMap = (Map<String, List<DataReportSumModel>>)
		 element.getObjectValue();

		// 获取报表汇总结果集map
		List<DataReportSumModel> reportList = reportMap.get(hideColumn
				.split("罟")[1]);

		List<DataReportSumModel> selectedReportList = new LinkedList<DataReportSumModel>();
		// 筛选集合，产生用户选中待对比的数据集合
		for (DataReportSumModel model : reportList) {
			String reportValue = BeanUtils.getProperty(model,
					hideColumn.split("罟")[3]);
			// 下拉框选择的字段值 和 被对比的字段值
			if (Arrays.asList(selectedValue.split("罟")).contains(reportValue)
					|| hideColumn.split("罟")[2].contains(reportValue)) {
				selectedReportList.add(model);
			}
		}
		return selectedReportList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IDataSearchService#getHyperlinkList(javax.servlet.http.HttpServletRequest, java.util.List, java.lang.String, java.util.Map, java.lang.String)
	 */
	public List<DataReportSumModel> getHyperlinkList(
			HttpServletRequest request, List<DataReportSumModel> pageList,
			String columField, Map map, String reportType) throws Exception {
		if (!"date".equals(columField)) {
			for (DataReportSumModel model : pageList) {
				String columnValue = BeanUtils.getProperty(model, columField);
				String title = columnValue + "(关联报告分析)";
				// 设置列值超链接展示
				String linkColumnValue = "<a href='javascript:void(0)' title='"
						+ title
						+ "' style='color: black;text-decoration:none' onmouseover=\"this.style.color='red';this.style.textDecoration='underline'\" onMouseout=\"this.style.color='black';this.style.textDecoration='none'\" onclick='deepReport(\""
						+ columField + "罟" + columnValue + "罟" + reportType
						+ "\")'>" + columnValue + "</a>";
				BeanUtils.setProperty(model, columField, linkColumnValue);
			}
		}
		return pageList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IDataSearchService#setHideColumnValue(javax.servlet.http.HttpServletRequest, java.util.List, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<DataReportSumModel> setHideColumnValue(
			HttpServletRequest request, List<DataReportSumModel> pageList,
			String columField, String tableId, String type) throws Exception {
		// 获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		for (DataReportSumModel model : pageList) {
			String columnValue = BeanUtils.getProperty(model, columField);
			String hideValue = type + "罟" + tableId.replaceAll("_table", "")
					+ "罟" + columnValue + "罟" + columField;
			BeanUtils.setProperty(model, "hideColumn", hideValue);
		}
		return pageList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IReportCacheService#getCacheValue(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	public List<DataReportSumModel> getCacheValue(HttpServletRequest request,
			String data) throws Exception {
		Map<String, List<DataReportSumModel>> reportMap = new HashMap<String, List<DataReportSumModel>>();
		// 获取查询条件
		String queryKey = (String) MapModel.getMap().get("queryKey");
		String queryValue = (String) MapModel.getMap().get("queryValue");
		// 获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		// 缓存对象
		Cache ehCache = CreateEncache.getEacheInstance().getCache(
				"dataSearchCache");
		String firstParam = data.split("罟")[0];
		String secondeParam = data.split("罟")[1];
		 ehCache.acquireReadLockOnKey(country+queryKey+queryValue+firstParam+secondeParam);
		 Element element = ehCache.get(country+queryKey+queryValue+firstParam+secondeParam);
		 ehCache.releaseReadLockOnKey(country+queryKey+queryValue+firstParam+secondeParam);
		 reportMap = (Map<String, List<DataReportSumModel>>)
		 element.getObjectValue();
		 
		 if(reportMap == null){
		// 获取报表汇总结果集map
		reportMap = reportEngineService.builderMapList(
				new ReportCommonParamModel(queryKey.split(";"), queryValue
						.split(";"), country, "report", request, firstParam),
				false);
		}
		List<DataReportSumModel> reportList = reportMap.get(secondeParam);
		if (reportList!=null) {
			for (DataReportSumModel model : reportList) {
				BeanUtils.setProperty(model, "hideColumn",
						BeanUtils.getProperty(model, data.split("罟")[3]));
			}
		}
		return reportList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IBuildChartService#getPieAndMixChartData(javax.servlet.http.HttpServletRequest, java.util.List, java.lang.String, java.util.Map, java.lang.String, int)
	 */
	public <T> Map getPieAndMixChartData(HttpServletRequest request,
			List<T> listAll, String columField, Map map, String reportType,
			int functionType,String DynamicSortValue) throws Exception {
		return BuildTradeChartUtil.buildPieAndMixChartData(request, listAll,
				columField, map, reportType, functionType,DynamicSortValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IReportCacheService#getDataSummaryCacheValue(javax.servlet.http.HttpServletRequest, java.util.Map, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DataReportSumModel> getDataSummaryCacheValue(
			HttpServletRequest request, Map reportFieldMap, String country,
			String reportType) {
		// 获取查询条件
		String queryKey = (String) MapModel.getMap().get("queryKey");
		String queryValue = (String) MapModel.getMap().get("queryValue");
		String lastQueryKey = KeyValueModel.getLastQueryKey();
		String lastQueryValue = KeyValueModel.getLastQueryValue();
		String lastCountry = KeyValueModel.getLastCountry();
		Map<String, List<DataReportSumModel>> reportMap = new HashMap<String, List<DataReportSumModel>>();
		// 获取产品交易趋势报告配置内容
		String cpTrade = reportFieldMap.get(
				country + DataSearchConstantUtil._CP_TRADE).toString();
		// 获取国别报表配置内容
		String cpCountry = reportFieldMap.get(
				country + DataSearchConstantUtil._CP_COUNTRY).toString();
		// 获取港口报表配置内容
		String cpPort = reportFieldMap.get(
				country + DataSearchConstantUtil._CP_PORT).toString();
		// 汇总集合
		List<DataReportSumModel> reportList = null;
		// 缓存对象
		Cache ehCache = CreateEncache.getEacheInstance().getCache(
				"dataSearchCache");
		String tabModule = "";
		// 判断该报告类型属于哪个选项卡模块
		if (cpTrade.indexOf(reportType) != -1) {
			tabModule = DataSearchConstantUtil.CP_TRADE;

		} else if (cpCountry.indexOf(reportType) != -1) {
			tabModule = DataSearchConstantUtil.CP_COUNTRY;
		} else if (cpPort.indexOf(reportType) != -1) {
			tabModule = DataSearchConstantUtil.CP_PORT;
		}
		if (queryKey.equals(lastQueryKey) && queryValue.equals(lastQueryValue)
				&& ehCache.get(country + tabModule) != null
				&& country.equals(lastCountry)) {
			// 从缓存中拿数据
			ehCache.acquireReadLockOnKey(country + tabModule);
			Element element = ehCache.get(country + tabModule);
			ehCache.releaseReadLockOnKey(country + tabModule);
			reportMap = (Map<String, List<DataReportSumModel>>) element
					.getValue();
		} else {
			// 查询出lucene中数据库主键id
			reportMap = reportEngineService
					.builderMapList(
							new ReportCommonParamModel(queryKey.split(";"),
									queryValue.split(";"), country, "report",
									request, tabModule), true);
			// 将查询结果放入缓存中
			ehCache.acquireWriteLockOnKey(country + tabModule);
			ehCache.put(new Element(country + tabModule, reportMap));
			ehCache.releaseWriteLockOnKey(country + tabModule);
			KeyValueModel.setLastQueryKey(queryKey);
			KeyValueModel.setLastQueryValue(queryValue);
			KeyValueModel.setLastCountry(country);
		}
		reportList = reportMap.get(reportType);
		return reportList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.datasearch.IReportTrendService#getThbReportList(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String, java.lang.String)
	 */
	public <T> List<T> getThbReportList(HttpServletRequest request,
			String singleReport, String reportType, String value)
			throws Exception {
		// 获取当前的国家
		String country = (String) request.getSession().getAttribute("country");
		// 查询条件
		String fieldKey = (String) MapModel.getMap().get("queryKey");
		String fieldValue = (String) MapModel.getMap().get("queryValue");
		// 获取开始结束时间
		String startDate = "";
		String endDate = "";
		String[] keyArray = fieldKey.split(";");
		String[] valueArray = fieldValue.split(";");
		for (int i = 0; i < keyArray.length; i++) {
			if (DataSearchConstantUtil.DATE_LUCENE.equals(keyArray[i])) {
				startDate = valueArray[i].split(",")[0];
				endDate = valueArray[i].split(",")[1];
			}
		}
		Map<String, List<T>> thbReportMap = new HashMap<String, List<T>>();
		// 缓存对象
		Cache ehCache = CreateEncache.getEacheInstance().getCache(
				"dataSearchCache");
		if (ehCache.get(country + fieldKey + fieldValue + reportType + value
				+ singleReport) != null) {
			ehCache.acquireReadLockOnKey(country + fieldKey + fieldValue
					+ reportType + value + singleReport);
			Element element = ehCache.get(country + fieldKey + fieldValue
					+ reportType + value + singleReport);
			ehCache.releaseReadLockOnKey(country + fieldKey + fieldValue
					+ reportType + value + singleReport);
			thbReportMap = (Map<String, List<T>>) element.getValue();
		} else {
			// 获取同环比集合
			ReportCommonParamModel model = new ReportCommonParamModel(
					fieldKey.split(";"), fieldValue.split(";"), country,
					ParamEnumUtil.report.name(), request, reportType,
					startDate, endDate);
			thbReportMap = monthYearTrendService.getRatioModelMap(model,
					reportType, value, singleReport);
			// 将查询结果放入缓存中
			ehCache.acquireWriteLockOnKey(country + fieldKey + fieldValue
					+ reportType + value + singleReport);
			ehCache.put(new Element(country + fieldKey + fieldValue
					+ reportType + value + singleReport, thbReportMap));
			ehCache.releaseWriteLockOnKey(country + fieldKey + fieldValue
					+ reportType + value + singleReport);
		}
		return (List<T>) thbReportMap.get(singleReport);
	}

}
