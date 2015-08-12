package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njyb.auth.service.impl.cmp.IUserRightAllDBService;
import com.njyb.gbdbas.util.ArrayUtil;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.SearchRightLibraryModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 权库数据处理类
 * 	
 * @author WangBo 2015年4月14日 MarketAnalysisReportData.java
 */
@Service
public class RightLibraryConstructionData implements
		IRightLibraryConstructionData {

	// log 日志
	private static final Logger log = Logger
			.getLogger(RightLibraryConstructionData.class);
	
	// 权库配置Map
	private Map<String, Object> configMap = null;

	@Autowired
	private IQueryDataService queryDataService;

	@Autowired
	private IDataSearchService dataSearchService;
	
	@Autowired
	private IUserRightAllDBService userRightAllDBService;

	@Resource
	private SearchRightLibraryModel searchRightLibraryModel;
	

	/**
	 * 报告类型和字段的映射
	 * 
	 * @param reportName
	 *            : 报告类型
	 * @return resultReportName : 返回的报告类型 字符串
	 */
	public String getReportName(String reportName) {
		if (configMap.containsKey(reportName)) {
			reportName = configMap.get(reportName).toString();
		}
		return reportName;
	}

	/**
	 * 构造条件
	 * 
	 * @param request
	 *            : 请求 {HttpServletRequest}
	 * @param key
	 *            : key {List}
	 * @param value
	 *            : value {List}
	 * @param countryName
	 *            : 国家名称 {?,?,?,?}
	 * @param sortKey
	 *            : 排序字段
	 * @param reportName
	 *            : 报告类型
	 * @param isFlag
	 *            : 标示符
	 * @return List<AllDBModel>
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<DataReportSumModel> getMarketAnalysisReportList(
			HttpServletRequest request, List<String> key, List<String> value,
			String [] countrys, String sortKey, String reportName,
			boolean isFlag) throws Exception {
		List<DataReportSumModel> queryDataList = Lists.newLinkedList();
		configMap = searchRightLibraryModel.getPropertiesMap();
		List<String> doKey = null;
		List<String> doValue = null;
		// 构造条件
		for (String country : countrys) {
			// 复制条件,用于循环构造添加,保证是前台传入的齐全参数
			doKey = Lists.newArrayList(key);
			doValue = Lists.newArrayList(value);
			String doReportName = reportName;
			if (configMap.containsKey(country)) {
				// 获取当前国家的配置value
				String conditionToStr = String.valueOf(configMap.get(country));
				String[] conditionArray = conditionToStr.split(";");
				Map<String, String> keyValueMap = ArrayUtil
						.getReportNameList(conditionArray[0]);
				Map<String, String> reportNameMap = ArrayUtil
						.getReportNameList(conditionArray[1]);
				// 构造个个国家的报告类型
				if (reportNameMap.containsKey(doReportName)) {
					// 国家拥有报告类型,否则不在耗费循环构造条件
					doReportName = !Strings.isNullOrEmpty(reportNameMap
							.get(doReportName)) ? reportNameMap
							.get(doReportName) : "";
					// 构造个个国家的条件
					for (String keys : key) {
						int index = doKey.indexOf(keys);
						// 删除个个国家无用的条件
						if (!keyValueMap.containsKey(keys)) {
							doKey.remove(index);
							doValue.remove(index);
						}
						// 如果kyes 为 tread_type(贸易类型) 则不处理
						if (!keys.equals(RightLibraryConstant.TRADETYPE)) {
						}
						// 权限控制 对于中国 日期 特殊处理
//						userRightAllDBService.getValue(request, doKey, doValue, country, keys, index);
						// 如果存在 日期配置标示符,则对日期处理
						if (keyValueMap.containsKey(RightLibraryConstant.DATECONFIG)) {
							if (keys.equals(RightLibraryConstant.DATE)) {
								String dateSplit = doValue.get(index);
								String date = getDateFormat(dateSplit);
								doValue.set(index, date);
							}
						}
					}
					if (doKey.size() > 1) {
						log.info("汇总数据 : + 国家: " + country + " key :   " + doKey.toString() + "  value : "
								+ doValue.toString());
						country = InitCountryCENameUtil.queryCountryEnName(country);
						doReportName = getReportName(doReportName);
						String[] feilds = ArrayUtil.getStringArray(doKey);
						String[] values = ArrayUtil.getStringArray(doValue);
						List<DataReportSumModel> singleQueryDataList = queryDataService.queryDataByParams(request, feilds, values, country, doReportName,isFlag);
						setDataReportByCountry(singleQueryDataList, country);
						queryDataList.addAll(singleQueryDataList);
					}
				}
			}
		}
		return queryDataList;
	}
	
	/**
	 * 添加国家名称
	 */
	private void setDataReportByCountry(List<DataReportSumModel> singleQueryDataList,String country){
		for (DataReportSumModel dataReportSumModel : singleQueryDataList) {
			dataReportSumModel.setCountry(InitCountryCENameUtil.queryCountryCnName(country));
		}
	}
	
	/**
	 * 权库自有的方法<br>
	 * 对日期格式化
	 * 
	 * @param dateSplit
	 *            : 日期
	 * @return
	 */
	private static String getDateFormat(String dateSplit) {
		String[] dateArray = dateSplit.split(",");
		String startDate = DataUtil.parseDate(
				DataUtil.parseDate(dateArray[0], 3), 17);
		String endDate = DataUtil.parseDate(
				DataUtil.parseDate(dateArray[1], 3), 17);
		return startDate + "," + endDate;
	}
	
	/**
	 * 交易记录,竞争对手,同环比
	 * 构造条件
	 */
	@SuppressWarnings("static-access")
	@Override
	public Map<String,Map<String,Object>> constantParamByCountry(List<String> key,
			List<String> value, String countryName) {
		IdentityHashMap<String,Map<String,Object>> paramTotalMap = Maps.newIdentityHashMap();
		String[] countrys = countryName.split(",");
		configMap = searchRightLibraryModel.getPropertiesMap();
		List<String> doKey = null;
		List<String> doValue = null;
		// 构造条件
		for (String country : countrys) {
			Map<String,Object> paramMap = Maps.newHashMap();
			// 复制条件,用于循环构造添加,保证是前台传入的齐全参数
			doKey = Lists.newArrayList(key);
			doValue = Lists.newArrayList(value);
			if (configMap.containsKey(country)) {
				// 获取当前国家的配置value
				String conditionToStr = String.valueOf(configMap.get(country));
				String[] conditionArray = conditionToStr.split(";");
				Map<String, String> keyValueMap = ArrayUtil
						.getReportNameList(conditionArray[0]);
				// 构造个个国家的条件
				for (String keys : key) {
					int index = doKey.indexOf(keys);
					// 删除个个国家无用的条件
					if (!keyValueMap.containsKey(keys)) {
						doKey.remove(index);
						doValue.remove(index);
					}
					// 如果存在 日期配置标示符,则对日期处理
					if (keyValueMap.containsKey(RightLibraryConstant.DATECONFIG)) {
						if (keys.equals(RightLibraryConstant.DATE)) {
							String dateSplit = doValue.get(index);
							String date = getDateFormat(dateSplit);
							doValue.set(index, date);
						}
					}
				}
			}
			if (doKey.size() > 1) {		//如果条件超过1个,则查询
				getParamTotalMap(doKey, doValue, country, paramMap,  paramTotalMap);
				System.out.println(paramTotalMap);
			}
		}
		
		return paramTotalMap;
	}
	
	/**
	 * 交易记录,竞争对手,同环比
	 * 构造条件
	 */
	@SuppressWarnings("static-access")
	@Override
	public IdentityHashMap<String,Map<String,Object>> constantDownLoad(List<String> key,
			List<String> value, String countryName) {
		IdentityHashMap<String,Map<String,Object>> paramTotalMap = Maps.newIdentityHashMap();
		IdentityHashMap<String,Map<String,Object>> temp_paramTotalMap = Maps.newIdentityHashMap();
		String[] countrys = countryName.split(",");
		configMap = searchRightLibraryModel.getPropertiesMap();
		List<String> doKey = null;
		List<String> doValue = null;
		// 构造条件
		for (String country : countrys) {
			Map<String,Object> paramMap = Maps.newHashMap();
			// 复制条件,用于循环构造添加,保证是前台传入的齐全参数
			doKey = Lists.newArrayList(key);
			doValue = Lists.newArrayList(value);
			if (configMap.containsKey(country)) {
				// 获取当前国家的配置value
				String conditionToStr = String.valueOf(configMap.get(country));
				String[] conditionArray = conditionToStr.split(";");
				Map<String, String> keyValueMap = ArrayUtil
						.getReportNameList(conditionArray[0]);
				// 构造个个国家的条件
				for (String keys : key) {
					int index = doKey.indexOf(keys);
					// 删除个个国家无用的条件
					if (!keyValueMap.containsKey(keys)) {
						doKey.remove(index);
						doValue.remove(index);
					}
					// 如果存在 日期配置标示符,则对日期处理
					if (keyValueMap.containsKey(RightLibraryConstant.DATECONFIG)) {
						if (keys.equals(RightLibraryConstant.DATE)) {
							String dateSplit = doValue.get(index);
							String date = getDateFormat(dateSplit);
							doValue.set(index, date);
						}
					}
				}
			}
			if (doKey.size() > 1) {		//如果条件超过1个,则查询
				getParamTotalMap(doKey, doValue, country, paramMap, paramTotalMap);
				temp_paramTotalMap.putAll(getParamsByHscode(paramTotalMap));
			}
		}
		return temp_paramTotalMap;
	}
	
	/**
	 * 根据HsCode的个数生成条件Map
	 * @param paramMap : 条件Map
	 * @return IdentityHashMap
	 */
	@SuppressWarnings("unchecked")
	private IdentityHashMap<String,Map<String,Object>> getParamsByHscode(IdentityHashMap<String,Map<String,Object>> paramMap){
		IdentityHashMap<String,Map<String,Object>> resultMap = new IdentityHashMap<String, Map<String,Object>>();
		for (Entry<String, Map<String, Object>> entyry : paramMap.entrySet()) {
			String country = entyry.getKey();										//获取key
			List<String> newKey = (List<String>) entyry.getValue().get("key");		//获取List Key
			List<String> newValue = (List<String>) entyry.getValue().get("value");  //获取List value
			if (newKey.contains(RightLibraryConstant.HSCODE)) {		// 获得海关编码
				int index = newKey.indexOf(RightLibraryConstant.HSCODE);
				String hsCodeValue = newValue.get(index);
				if (hsCodeValue.contains(",")) {		// 如果海关编码多个
					String [] hsCodeArray = hsCodeValue.split(",");
					for (String hscode : hsCodeArray) {
						Map<String,Object> keyMap = new HashMap<String, Object>();
						List<String> tempValue = new ArrayList<String>(newValue);
						tempValue.set(index, hscode);		// 设置当前的hscode
						keyMap.put("key", newKey);
						keyMap.put("value", tempValue);
						resultMap.put(new String(country), keyMap);
					}
				} else {
					return paramMap;			// 针对不是正式用户,并且不是多个海关编码,直接返回当前的map条件
				}
			} else {
				return paramMap;				// 如果不含有海关编码,直接返回当前的map对象
			}
		}
		return resultMap;
	}
	
	/**
	 * 贸易情报{贸易类型}特殊处理
	 * @param doKey
	 * @param doValue
	 * @param country
	 * @param paramMap
	 * @param paramTotalMap
	 * @return
	 */
	private IdentityHashMap<String,Map<String,Object>> getParamTotalMap(List<String> doKey,List<String> doValue,String country,Map<String,Object> paramMap,IdentityHashMap<String,Map<String,Object>> paramTotalMap){
		if (country.equals("中国") || country.equals("英国") && doKey.contains(RightLibraryConstant.TRADETYPE)) {		//如果Keys 为 tread_type(贸易类型) 则处理成 数组
			int index = doKey.indexOf(RightLibraryConstant.TRADETYPE);
			String [] treadTypeArray = getTreadType(doValue.get(index));
			for (int i = 0; i < treadTypeArray.length; i++) {
				Map<String,Object> paramMaps = Maps.newHashMap();
				doValue.set(index, treadTypeArray[i]);
				paramMaps.put("key", doKey);
				List<String> listValue = Lists.newArrayList(doValue);
				paramMaps.put("value", listValue);
				paramTotalMap.put(new String(country), paramMaps);
			}
		} else {
			paramMap.put("key", doKey);
			paramMap.put("value", doValue);
			paramTotalMap.put(country, paramMap);
		}
		return paramTotalMap;
	}
	
	/**
	 * 贸易类型处理
	 * @param treadType
	 * @return
	 */
	private String[] getTreadType(String treadType) {
		String [] treadTypeArray = null;
		if (treadType.indexOf(",") > -1) {		//如果存在,则处理成数组
			treadTypeArray = treadType.split(",");
		} else {
			treadTypeArray = new String[]{treadType};
		}
		return treadTypeArray;
	}
}