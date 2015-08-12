package com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.impl.context;

import java.util.List;

import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.ITrendStrategy;
import com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.impl.MonthHuanBiTrendStrategy;
import com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.impl.MonthTongBiTrendStrategy;
import com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.impl.YearTongBiTrendStrategy;

/**
 * 同环比算法策略选择的上下文
 * @author 贾红平
 *
 */
public class TrendStrategyContext {
	private static ITrendStrategy strategy;
	/*选择具体算法的策略实例对象*/
	private static ITrendStrategy geTrendStrategyInstance(String param){
		switch (param) {
			case "mom":
				strategy=new MonthHuanBiTrendStrategy();
				break;
			case "myoy":
				strategy=new MonthTongBiTrendStrategy();
				break;
			case "yoy":
				strategy=new YearTongBiTrendStrategy();
				break;
			default:
				break;
			}
		return strategy;
	}
	/*获取具体的比例集合*/
	public static  <T> List<T> getRatioListModel(ReportCommonParamModel model, String value, String oper,String reportType) {
		return geTrendStrategyInstance(oper).getRatioListModel(model, value, oper, reportType);
	}

}
