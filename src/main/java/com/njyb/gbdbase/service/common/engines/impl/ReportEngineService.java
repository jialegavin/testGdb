package com.njyb.gbdbase.service.common.engines.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.componet.report.build.IBuildReportData;
import com.njyb.gbdbase.service.common.engines.AbstractEngineService;
import com.njyb.gbdbase.service.common.engines.IReportEngineService;
/**
 * 报表业务的实现接口
 * @author 贾红平
 *
 */
@Service
@SuppressWarnings("static-access")
public class ReportEngineService extends AbstractEngineService implements IReportEngineService {

	@Autowired
	private IBuildReportData buildReportData;
	@Autowired
	private SearchPropertiesModel propertiesMap;
	/**
	 * 返回map的数据集合
	 */
	public  Map<String, List<DataReportSumModel>> builderMapList(ReportCommonParamModel  searchCommonParamModel,boolean isShowAll){
		return buildReportData.builderMapList(filterStrByCountry(searchCommonParamModel, propertiesMap.getPropertiesMap()), isShowAll);
	}
	/**
	 * 返回json数据的数据集合
	 */
	public  JSONArray buildJsonObject(ReportCommonParamModel  commonParamModel,boolean isShowAll){
		return null;
	}


}
