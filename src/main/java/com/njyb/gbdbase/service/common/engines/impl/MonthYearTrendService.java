package com.njyb.gbdbase.service.common.engines.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.service.common.componet.report.trend.IMonthYearTrendCmp;
import com.njyb.gbdbase.service.common.engines.IMonthYearTrendService;
/**
 * 月度同环比 年度同比的业务的实现类
 * @author 贾红平
 *
 */
@Service
public class MonthYearTrendService implements IMonthYearTrendService {
	@Autowired
	private IMonthYearTrendCmp monthYearTrendCmp;
	/**
	 * 具体实现方法
	 */
	@Override
	public <T> Map<String, List<T>>getRatioModelMap(ReportCommonParamModel model,String currentReportType,String currentValue,String trendType){
		Map<String, List<T>> map=new HashMap<String, List<T>>();
		if (trendType.contains(",")) {
			for(String str:trendType.split(",")){
				List<T> t=monthYearTrendCmp.getMomRatioModel(model, currentValue, str, currentReportType);
				map.put(str, t);
			}
		}
		else{
			List<T> t=monthYearTrendCmp.getMomRatioModel(model, currentValue, trendType, currentReportType);
			map.put(trendType, t);
		}
		return map;
	}
}
