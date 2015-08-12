package com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.sort.DataGridSortUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.RatioModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.IListChangeMapFormat;
import com.njyb.gbdbase.service.common.componet.report.componet.father.AbstractReportCmp;
import com.njyb.gbdbase.service.common.componet.report.sumary.IReportDataSummary;
import com.njyb.gbdbase.service.common.componet.report.trend.IMonthYearTrendCmp;
import com.njyb.gbdbase.service.common.componet.report.trend.impl.strategy.ITrendStrategy;
import com.njyb.gbdbase.service.common.engine.util.NjypCompartor;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engine.util.ReportDateUtil;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;

/**
 * 月度同比的算法
 * 
 * @author 贾红平
 *
 */
@SuppressWarnings("unused")
@Component
public class MonthTongBiTrendStrategy extends AbstractReportCmp implements ITrendStrategy {
	/**
	 * 实现同环比的具体方法
	 */
	@Override
	@SuppressWarnings({ "static-access", "unchecked" })
	public synchronized <T> List<T> getRatioListModel(ReportCommonParamModel model, String value, String oper,String reportType) {
		//调用父类方法 进行参数初始化
		initParam(model);
		try {
			// 获取月度同比的时间
			secondStart = ReportDateUtil.getChangeDate("year", start, -1,dateFormatType(model, propertiesModel.getReportFieldMap()));
			secondEnd = ReportDateUtil.getChangeDate("year", end, -1,dateFormatType(model, propertiesModel.getReportFieldMap()));
			//获取要计算的月度同比集合
			Map<String, List<T>>map=super.getMonthTrendMap(model, value, reportType, oper);
			List<DataReportSumModel>firstList=(List<DataReportSumModel>) map.get("first");
			List<DataReportSumModel>secondList=(List<DataReportSumModel>) map.get("second");
			// 原始集合和环比集合都不为空 就执行环比计算
			if (firstList != null && secondList != null) {
				// 传入两个集合 计算具体的比率
				List<RatioModel> ratioList = super.getRatioList(firstList,secondList,"myoy");
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
