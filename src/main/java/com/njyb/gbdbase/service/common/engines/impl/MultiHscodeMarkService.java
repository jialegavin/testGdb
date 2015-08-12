package com.njyb.gbdbase.service.common.engines.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.service.common.componet.report.ReportSumaryCmp;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.IListChangeMapFormat;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.IMultiHscodeMarkService;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;
/**
 * 获取多海关编码备注信息
 * @author 贾红平
 *
 */
@Service
public class MultiHscodeMarkService extends ReportSumaryCmp implements
		IMultiHscodeMarkService {
	@Autowired
	private IListChangeMapFormat mapFormat;
	@Autowired
	private IReportDetailService detailService;
	@Autowired
	ReportPropertiesModel report;
	/**
	 * 获取多海关编码备注的详细信息
	 */
	@SuppressWarnings("static-access")
	@Override
	public List<DataReportSumModel> getMultiHscodeMarkInfo(String value, String country,String reportType) {
		 List<DataReportSumModel>list=detailService.buildReportSumList(value, reportType, country);
		 Map<String, List<DataReportSumModel>>map=mapFormat.changeListForMap(list, ParamEnumUtil.multi_hscode.name(), report.getReportFieldMap(), true);
		 List<DataReportSumModel>hslist=super.getDataReportSumModels(map, ParamEnumUtil.multi_hscode.name(), country, report.getReportFieldMap());
		 return hslist;
	}

}
