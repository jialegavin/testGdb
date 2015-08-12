package com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy;

import java.util.List;

import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
/**
 * 定义一个趋势算法的策略接口
 * @author 贾红平
 *
 */
public interface ITrendStrategy {
	/**
	 * 返回一个比率集合对象
	 * @param model 查询参数
	 * @param value 查询对象的值
	 * @param oper  当前操作 是 mom:月度环比 myoy:月度同比 yoy:年度同比
	 * @param reportType 报告类型:需要客户端传过来
	 * @return
	 */
	public abstract <T> List<T> getRatioListModel(ReportCommonParamModel model,
			String value, String oper, String reportType);

}