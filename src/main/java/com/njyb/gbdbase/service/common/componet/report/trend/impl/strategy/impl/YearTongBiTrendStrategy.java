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
 * 年度同比的算法
 * @author 贾红平
 *
 */
@SuppressWarnings("unused")
@Component
public class YearTongBiTrendStrategy extends AbstractReportCmp implements ITrendStrategy {
	/**
	 * 实现同环比的具体方法
	 */
	@Override
	@SuppressWarnings({ "static-access", "unchecked" })
	public synchronized <T> List<T> getRatioListModel(ReportCommonParamModel model, String value, String oper,String reportType) {
		//调用父类方法 进行参数初始化
		initParam(model);
		try {
			// 获取年度同比的集合
			secondStart = ReportDateUtil.getChangeDate("year", start, -1,dateFormatType(model, propertiesModel.getReportFieldMap()));
			secondEnd = ReportDateUtil.getChangeDate("year", end, -1,dateFormatType(model, propertiesModel.getReportFieldMap()));
			Map<String, List<T>>map=super.getMonthTrendMap(model, value, reportType, oper);
			List<DataReportSumModel>firstList=(List<DataReportSumModel>) map.get("first");
			List<DataReportSumModel>secondList=(List<DataReportSumModel>) map.get("second");
			// 原始集合和环比集合都不为空 就执行环比计算
			if (firstList != null && secondList != null) {
				List<DataReportSumModel>ls=new ArrayList<DataReportSumModel>();
				//添加今年的值
				if (firstList!=null && firstList.size()>0) {
					DataReportSumModel firstData=firstList.get(0);
					firstData.setDate("This Year"+value);
					ls.add(firstData);
				}
				//添加去年的值
				if (secondList!=null &&  secondList.size()>0) {
					DataReportSumModel secondData=secondList.get(0);
					secondData.setDate("Last Year"+value);
					ls.add(secondData);
				}
				return (List<T>) ls;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
