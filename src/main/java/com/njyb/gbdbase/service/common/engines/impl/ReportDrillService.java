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
import com.njyb.gbdbase.service.common.engines.IReportDetailService;
import com.njyb.gbdbase.service.common.engines.IReportDrillService;
/**
 * 报表深度挖取
 * @author jiahp
 *
 */
@Service
public class ReportDrillService extends ReportSumaryCmp implements IReportDrillService {
	//注入明细组件
	@Autowired
	private IReportDetailService detailService;
	//转换map组件
	@Autowired
	private IListChangeMapFormat changeMapFormat;
	//注入配置文件信息
	@Autowired
	private ReportPropertiesModel report;
	//根据报告类型获取具体的报告汇总集合 type:jks,qyg:map<jks,list>
	@SuppressWarnings("static-access")
	@Override
	public   List<DataReportSumModel>drillReportSumList(String value,String orginalType,String country,String drillType){
		List<DataReportSumModel>list=null;
		//新增流失报告的钻取 后期考虑使用配置文件能减少判断代码
		if (ParamEnumUtil.companycompare_first.name().contains(orginalType)
				||ParamEnumUtil.companycompare_second.name().contains(orginalType)
				||ParamEnumUtil.jkscompare_first.name().contains(orginalType)
				||ParamEnumUtil.jkscompare_second.name().contains(orginalType)
				||ParamEnumUtil.ckscompare_first.name().contains(orginalType)
				||ParamEnumUtil.ckscompare_second.name().contains(orginalType)) {
			list=detailService.builderSingleList(value,orginalType,country);
		}
		//给多个报告汇总用的钻取
		else{
			list=detailService.buildReportSumList(value, orginalType, country);
		}
		//转map：mapper 
		Map<String, List<DataReportSumModel>>map=changeMapFormat.changeListForMap(list, drillType, report.getReportFieldMap(), true);
		if(drillType.startsWith("drill")){
			drillType = drillType.split("&")[1];
		}
		//map转集合:reduce
		List<DataReportSumModel>datalist=super.getDataReportSumModels(map, drillType, country, report.getReportFieldMap());
		return datalist;
	}
}
