package com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbas.util.sort.DataGridSortUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.RatioModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.service.common.componet.report.componet.father.AbstractReportCmp;
import com.njyb.gbdbase.service.common.componet.report.sumary.IReportDataSummary;
import com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.ITrendStrategy;
import com.njyb.gbdbase.service.common.engine.util.NjypCompartor;
import com.njyb.gbdbase.service.common.engine.util.ReportDateUtil;

/**
 * 月度环比的算法
 * 
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
@Component
public class MonthHuanBiTrendStrategy extends AbstractReportCmp implements ITrendStrategy {
	@Autowired
	private IReportDataSummary dataSummary;
	/**
	 * 实现同环比的具体方法
	 */
	@Override
	@SuppressWarnings({ "static-access", "unchecked" })
	public synchronized <T> List<T> getRatioListModel(ReportCommonParamModel model, String value, String oper,String reportType) {
		//调用父类方法 进行参数初始化
		initParam(model);
		try {
			// 获取月度环比的时间段
			secondStart = ReportDateUtil.getChangeDate("month", start, -1,dateFormatType(model, propertiesModel.getReportFieldMap()));
			secondEnd = ReportDateUtil.getChangeDate("month", end, -1,dateFormatType(model, propertiesModel.getReportFieldMap()));
			//获取要计算的月度环比集合
			Map<String, List<T>>map=super.getMonthTrendMap(model, value, reportType, oper);
			List<DataReportSumModel>firstList=(List<DataReportSumModel>) map.get("first");
			List<DataReportSumModel>secondList=(List<DataReportSumModel>) map.get("second");
			// 原始集合和环比集合都不为空 就执行环比计算
			if (firstList != null && secondList != null) {
				// 传入两个集合 计算具体的比率
				List<RatioModel> ratioList = super.getRatioList(firstList,secondList,"mom");
				// 对集合根据日期进行排序
				DataGridSortUtil.executeSort(ratioList, new NjypCompartor("date"));
				return (List<T>) ratioList;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
