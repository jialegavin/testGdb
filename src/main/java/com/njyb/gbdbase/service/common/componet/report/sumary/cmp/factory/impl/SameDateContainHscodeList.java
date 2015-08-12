package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.ConditionModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.AbstractCacheListCmp;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;
/**
 * 日期相同且两次都包含海关编码
 * 两次海关编码一致
 * 本次查询的海关编码长度比上一次查询的长
 * 本次查询的海关编码长度比上一次查询的短
 * @author jiahp
 *
 */
public class SameDateContainHscodeList extends AbstractCacheListCmp implements IBuildReportDataListByCondition {
	@SuppressWarnings("rawtypes")
	@Override
	public List<DataReportSumModel> getFilterListModel(String[] fields,
			String[] values, String country, Map map,String oper) {
		if (oper.equals("samelast")) {
			return filterSameHscode(fields, values, country, map);
		}
		else if (oper.equals("innerlast")) {
			return filterInnerLastHscode(fields, values, country, map);
		}
		else if (oper.equals("outterlast")) {
			return filterOuterLastHscode(fields, values, country, map);
		}
		return null;
	}
	//两次海关编码一致
	@SuppressWarnings({ "unused", "static-access", "unchecked" })
	private  List<DataReportSumModel> filterSameHscode(String[] fields,
			String[] values, String country, @SuppressWarnings("rawtypes") Map map){
		//获取上一次用户查询条件
		String oldCountry=ConditionModel.getLastTimeCountry();
		String[]oldFields=ConditionModel.getLastTimeFields().split(";");
		String[]oldValues=ConditionModel.getLastTimeValues().split(";");
		//上一次查询的字段和值都包含在本次 所有可以进行多余字段的过滤
		if (ReportHelpUtil.newInstanceReportUtil().isAllTrue(oldFields, oldValues, fields, values)) {
				return ReportHelpUtil.getFilterList(oldFields, oldValues, fields, values,getListFromCache());				
		}
		//如果有一个不包含在本次查询 需要重新检索数据
		else{
			//初始化数据 并且放到缓存中
			return  initListAndSaveCache(fields, values,country, map,"对不起,因为上一次查询的条件以及值没有包含在本次查询中,所以只能重新检索数据");
		}
	}
	//本次查询的海关编码比上一次查询的海关编码长
	@SuppressWarnings({ "static-access", "rawtypes", "unused" })
	private  List<DataReportSumModel> filterInnerLastHscode(String[] fields,
			String[] values, String country, Map map){
		//获取上一次用户查询条件
		String oldCountry=ConditionModel.getLastTimeCountry();
		String[]oldFields=ConditionModel.getLastTimeFields().split(";");
		String[]oldValues=ConditionModel.getLastTimeValues().split(";");
		String newhscode=ReportHelpUtil.newInstanceReportUtil().getHscode(fields, values);
		//上一次查询的字段和值都包含在本次 所有可以进行多余字段的过滤
		if (ReportHelpUtil.newInstanceReportUtil().isAllTrue(oldFields, oldValues, fields, values)) {
			List<DataReportSumModel>hhlist=new LinkedList<>();
			//先根据hscode过滤一遍
			for(DataReportSumModel data:getListFromCache()){
				if (data.getHscode().startsWith(newhscode)) {
					hhlist.add(data);
				}
			}
			//直接对多余字段进行过滤
			return ReportHelpUtil.getFilterList(oldFields, oldValues, fields, values,hhlist);
		}
		//只要有一个不包含在本次查询中 直接重新检索数据
		else{
			//初始化数据 并且放到缓存中
			return  initListAndSaveCache(fields, values,country, map,"对不起,因为上一次查询的条件以及值没有包含在本次查询中,所以只能重新检索数据");
		}
	}
	//本次查询的海关编码比上一次查询的海关编码短
	@SuppressWarnings("unused")
	private  List<DataReportSumModel> filterOuterLastHscode(String[] fields,
			String[] values, String country, @SuppressWarnings("rawtypes") Map map){
		//初始化数据 并且放到缓存中
		return  initListAndSaveCache(fields, values,country, map,"对不起,因为本次检索的海关编码不在上一次检索的范围之内,所以只能重新检索数据");
	}
}
