package com.njyb.gbdbase.service.datasearch.export.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 报表中的导出业务接口层
 * @author XL
 * @date 2015-04-30
 * @version 标准版
 */
public interface IExportDataService {
	
	/**
	 * 导出当前报告汇总明细
	 * @param reportType 报告类型如：qs
	 * @param country 国家
	 * @param comparValue 汇总的值
	 * @param map
	 * @param request
	 * @param response
	 */
	 void exportSummaryDetail(String reportType, String country, String comparValue, Map map, HttpServletRequest request, HttpServletResponse response);
	 
	 /**
	 * 根据国家进行报表汇总生成excel
	 * @param country 国家名称
	 * @param map 获取配置文件内的字段的集合
	 * @param request
	 * @param response
	 */
	<T> void exportSummaryReportByCountry(String country, Map map,HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 报表总计
	 * @param c 实体类
	 * @param summaryList 汇总集合
	 * @param commonField 公用属性字段
	 * @param commonSummaryRatio 公用的汇总比率字段 
	 * @param request
	 * @return T
	 */
	<T> T statistics(Class<DataReportSumModel> c,List<T> summaryList,String[] commonField,String[] commonSummaryRatio,HttpServletRequest request);
	
	/**
	 * @param summaryList 需要计算比率的汇总后的集合(针对金额数量重量计算)
	 * @param fields 金额,数量,重量对应的字段
	 * @param ratioFields  金额,数量,重量对应的比率字段
	 * @auther honghao
	 * @return
	 */
	<T> void getDataPercent(List<T> summaryList, String[] fields,String[] ratioFields) throws Exception;
	
	/**
	 * 重新组装带钻取字段的数组
	 * @param srcArry 原数组
	 * @param addStr 新添加的内容
	 * @return String[]
	 */
	String[] getNewArray(String[] srcArry, String addStr);
}
