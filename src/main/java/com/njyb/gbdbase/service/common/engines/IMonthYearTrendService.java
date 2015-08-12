package com.njyb.gbdbase.service.common.engines;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.RatioModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
/**
 * 月度环比 月度同比 年度同比 业务接口
 * @author 贾红平
 * @version 标准版
 *
 */
public interface IMonthYearTrendService {
	/**
	 * 报告汇总单一对象的月度环比 月度同比 年度同比
	 * @param model 报表查询参数
	 * @param currentReportType 当前对象所属的报告类型 比如 jks,cks...
	 * @param currentValue 当前要做趋势分析对象的值
	 * @param trendType   当前对象要做哪些趋势分析的综合字符串 如"mom,myoy,yoy"或者"mom,myoy"等
	 * @return
	 */
	public <T> Map<String, List<T>>getRatioModelMap(ReportCommonParamModel model,String currentReportType,String currentValue,String trendType);
}
