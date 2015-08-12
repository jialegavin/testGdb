package com.njyb.gbdbase.service.common.componet.report.trend.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.service.common.componet.report.componet.father.AbstractReportCmp;
import com.njyb.gbdbase.service.common.componet.report.trend.IMonthYearTrendCmp;
import com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.impl.context.TrendStrategyContext;
/**
 * 实现月度同环比的具体组件
 * @author 贾红平
 *
 */
@Component
public class MonthYearTrendCmp extends AbstractReportCmp implements IMonthYearTrendCmp{
	/**
	 * 实现同环比的具体方法
	 */
	@Override
	public synchronized <T> List<T> getMomRatioModel(ReportCommonParamModel model,String value,String oper,String reportType) {
		return TrendStrategyContext.getRatioListModel(model, value, oper, reportType);
	}
}
