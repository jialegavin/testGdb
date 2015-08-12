package com.njyb.gbdbase.service.alldb.downloaddb;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.ArrayUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.export.ExportDataUtil;
import com.njyb.gbdbas.util.export.QueryReportFieldByCountryUtil;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.alldb.projectAnalyze.ProgressOfDownData;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.IRightLibraryConstructionData;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;
/**
 * 下载全库
 * @author WangBo
 * 2015年5月7日
 * DownLoadDBService.java
 */
@Service
public class DownLoadDBService implements IDownLoadDBService {
	
	//log记录日志
	private static final Logger log = Logger.getLogger(DownLoadDBService.class);

	// 权库处理构造核心模块
	@Autowired
	private IRightLibraryConstructionData rightLibraryConstructionData;
	
	// 公用搜索业务接口
	@Autowired
	private IDataSearchService dataSearchService;
	
	// lucene的调用接口
	@Autowired
	private ISearchEngineService searchEngineService;
	
	/**
	 * 根据查询条件下载全库
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Map<String,Map<String,Object>> downLoadDBByParams(Map<String, Object> paramMap) {
		Map<String,Map<String,Object>> dataResultMap = Maps.newHashMap();
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		try {
			RightLibrarySearchModel queryModel = (RightLibrarySearchModel) paramMap.get("queryModel");
			String countryName = queryModel.getCountrySelect();						//获取国家名称
			PageBeanUtil pageBean = (PageBeanUtil) paramMap.get("pageBean");		// 获取分页对象
			pageBean.setPageSize(Integer.MAX_VALUE);								// 手动赋值为最大
//			String language = request.getSession().getAttribute("language").toString();
			List<String> key = (List<String>) paramMap.get("key");
			List<String> value = (List<String>) paramMap.get("value");
			Map<String,Map<String,Object>> resultMap = rightLibraryConstructionData.constantParamByCountry(key, value, countryName);
			for (Entry<String, Map<String, Object>> entyry : resultMap.entrySet()) {
				request.setAttribute("country", entyry.getKey());
				ProgressOfDownData.getProgressOfDownData().getRequestOfCountry(request);									//加载进度条
				String country = InitCountryCENameUtil.queryCountryEnName(entyry.getKey());	//获取英文名称
				List<String> newKey = (List<String>) entyry.getValue().get("key");		//获取Key
				List<String> newValue = (List<String>) entyry.getValue().get("value");  //获取value
				String [] fileds = ArrayUtil.getStringArray(newKey);					//得到数组key
				String [] values = ArrayUtil.getStringArray(newValue);					//得到数组value
				// TODO Thread
				List<Integer> idList = searchEngineService.getListKey(new SearchCommonParamModel(fileds, values, country, ParamEnumUtil.search.toString(), request, pageBean));
				List<T> objList = dataSearchService.getDataById(country, idList);
				String [] remark = QueryReportFieldByCountryUtil.queryCommonRemarksByCountry(request, country);					//备注
				String [] colomName = QueryReportFieldByCountryUtil.queryCommonPropertyNameByCountry(request, country);			//列名
				List<String[]> stringList = ExportDataUtil.getList(objList, colomName);
				if (null != stringList && !stringList.isEmpty()) {
					Map<String,Object> dataMap = Maps.newHashMap();
					dataMap.put("columnName", remark);
					dataMap.put("dataList", stringList);
					dataMap.put("sheetName", InitCountryCENameUtil.queryCountryCnName(country));
					dataResultMap.put(country, dataMap);
				}
			}
			request.setAttribute("country", "end");
			ProgressOfDownData.getProgressOfDownData().getRequestOfCountry(request);
		} catch (Exception e) {
			request.setAttribute("country", "end");
			ProgressOfDownData.getProgressOfDownData().getRequestOfCountry(request);
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return dataResultMap;
	}
}