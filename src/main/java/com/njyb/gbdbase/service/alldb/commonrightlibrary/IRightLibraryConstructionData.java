package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 权库处理构造核心模块
 * @author WangBo
 * 2015年4月29日
 * IRightLibraryConstructionData.java
 */
public interface IRightLibraryConstructionData {
	
//	/**
//	 * 多国市场分析报告汇总--进口<br>
//	 * 出口<br>
//	 * 采购国<br>
//	 * 交易总价<br>
//	 * 交易总量<br>
//	 * 交易次数<br>
//	 * 原产国<br>
//	 * @param request
//	 *            : 请求
//	 * @param fields
//	 *            : key
//	 * @param values
//	 *            : value
//	 * @param countryName
//	 *            : 国家名称
//	 * @param sortKey
//	 *            : 排序字段{预留}
//	 * @param reportName
//	 *            : 报告类型
//	 * @param isflag
//	 *            : 未知
//	 * @return List<AllDBModel>
//	 */
//	public List<DataReportSumModel> queryMarketAnalysisReport(
//			HttpServletRequest request, List<String> key, List<String> value,
//			String countryName, String sortKey, String reportName,
//			boolean isflag);
	
	/**
	 * 构造条件方法  (市场分析)
	 * @param request
	 * @param key
	 * @param value
	 * @param countryName
	 * @param sortKey
	 * @param reportName
	 * @param isFlag
	 * @return
	 * @throws Exception
	 */
	public List<DataReportSumModel> getMarketAnalysisReportList(
			HttpServletRequest request, List<String> key, List<String> value,
			String [] countryName, String sortKey, String reportName,
			boolean isFlag) throws Exception;
	
	/**
	 * 交易记录,竞争对手<br>
	 * 构造条件
	 * @param key : Key
	 * @param value : value
	 * @param countryName : 国家名称
	 * @return
	 */
	public Map<String,Map<String,Object>> constantParamByCountry( List<String> key, List<String> value,
			String countryName);
	
	/**
	 * 报告类型和字段的映射
	 * 
	 * @param reportName
	 *            : 报告类型
	 * @return resultReportName : 返回的报告类型 字符串
	 */
	public String getReportName(String reportName);

	/**
	 * 全库下载 构造函数
	 * @param key
	 * @param value
	 * @param countryName
	 * @return
	 */
	IdentityHashMap<String, Map<String, Object>> constantDownLoad(List<String> key,
			List<String> value, String countryName);
}
