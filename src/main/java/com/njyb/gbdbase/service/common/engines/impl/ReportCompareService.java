package com.njyb.gbdbase.service.common.engines.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.componet.report.build.IBuildReportData;
import com.njyb.gbdbase.service.common.engines.AbstractEngineService;
import com.njyb.gbdbase.service.common.engines.IReportCompareService;
/**
 * 报表新增流失分析接口
 * @author 贾红平
 *
 */
@Service
public class ReportCompareService extends AbstractEngineService implements IReportCompareService {
	@Autowired
	private IBuildReportData buildReportData;
	@Autowired 
	private SearchPropertiesModel searchPropertiesModel;
	@SuppressWarnings("static-access")
	@Override
	public List<DataReportSumModel> builderSingleList(
			ReportCommonParamModel searchCommonParamModel, boolean isShowAll) {
		 	return buildReportData.builderSingleList(filterStrByCountry(searchCommonParamModel,searchPropertiesModel.getPropertiesMap()), isShowAll);
	}
}
