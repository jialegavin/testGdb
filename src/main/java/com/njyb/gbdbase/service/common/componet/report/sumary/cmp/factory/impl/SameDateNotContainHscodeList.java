package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.ConditionModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.KeyValueModel;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.AbstractCacheListCmp;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;
/**
 * 两次查询中 不是同时包含海关编码
 * 本次查询的条件和值和上一次也不是一一对应
 * @author jiahp
 *
 */
public class SameDateNotContainHscodeList extends AbstractCacheListCmp implements IBuildReportDataListByCondition {
	@SuppressWarnings({ "static-access" })
	@Override
	public List<DataReportSumModel> getFilterListModel(String[] fields,
			String[] values, String country, @SuppressWarnings("rawtypes") Map map,String oper) {
		String[]oldFields=ConditionModel.getLastTimeFields().split(";");
		String[]oldValues=ConditionModel.getLastTimeValues().split(";");
		//上一次的查询条件和对应的值都包含在本次查询中 可以对其它多余的字段直接进行过滤
		if (ReportHelpUtil.newInstanceReportUtil().isAllTrue(oldFields, oldValues, fields, values)) {
			//获取本次查询比上一次多的查询条件
			return ReportHelpUtil.getFilterList(oldFields, oldValues, fields, values,getListFromCache());
		}
		//直接重新查询
		else{
			//初始化数据 并且放到缓存中
			return initListAndSaveCache(fields, values,country, map,"对不起,因为两次查询没有同时包含海关编码,且上次查询的条件和值没有包含在本次中,所以只能重新检索数据");
		}
	}

}
