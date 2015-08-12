package com.njyb.gbdbase.service.common.componet.report.trend;

import java.util.List;

import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
/**
 * 计算月度同环比返回的集合比例模型
 * @author 贾红平
 *
 */
public interface IMonthYearTrendCmp {
	/**
	 * 返回一个比率集合对象
	 * @param model 查询参数
	 * @param value 查询对象的值
	 * @param oper  当前操作 是 mom:月度环比 myoy:月度同比 yoy:年度同比
	 * @param reportType 报告类型:需要客户端传过来
	 * @return
	 */
	public abstract <T> List<T> getMomRatioModel(ReportCommonParamModel model,
			String value, String oper, String reportType);

}