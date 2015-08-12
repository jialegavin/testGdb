package com.njyb.gbdbase.service.common.componet.report.sumary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.lucene.document.Document;
import org.springframework.stereotype.Component;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.ConditionModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.impl.CommonLuceneComponent;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.CreateFactory;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.AbstractCacheListCmp;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;
import com.njyb.gbdbase.service.contrastreport.common.LuceneFileUtis;

/**
 * 文档对象转换成集合的具体实现类
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
@Component
public class ReportDataSummary extends AbstractCacheListCmp implements IReportDataSummary {
	/**
	 * 给javabean对象的属性赋值
	 * 
	 */
	@SuppressWarnings({"static-access"})
	@Override
	public List<DataReportSumModel> getSumListModel(String[] fields, String[] values, String country,@SuppressWarnings("rawtypes") Map map) {
		List<DataReportSumModel> lists = new ArrayList<DataReportSumModel>();
		//启用集合过滤功能 得到新的集合
		if (map.get("useFilter").toString().equals("no")) {
			List<DataReportSumModel>list=null;
			//获取上一次用户查询条件
			String oldCountry=ConditionModel.getLastTimeCountry();
			String[]oldFields=ConditionModel.getLastTimeFields().split(";");
			String[]oldValues=ConditionModel.getLastTimeValues().split(";");
			//用户两次检索的国家是同一个
			if (oldCountry.equals(country)) {
				//用户本次检索的条件以及对应的值和上一次检索的条件以及对应的值
				if (LuceneFileUtis.judgeEqual(oldFields, fields)&&LuceneFileUtis.judgeEqual(oldValues, values)) {
					lists =  CreateFactory.createListFactory(ParamEnumUtil.same.name()).getFilterListModel(fields, values, country, map, null);
				}
				//用户两次查询的条件不一致
				if (!LuceneFileUtis.judgeEqual(oldFields, fields)){
					//条件不一致:本次查询的字段数量比上一次多
					if (oldFields.length<fields.length) {
						String olddate=ReportHelpUtil.newInstanceReportUtil().getDate(oldFields, oldValues);
						String newdate=ReportHelpUtil.newInstanceReportUtil().getDate(fields, values);
						//多:两次查询的日期的值是一致的 
						if (olddate.equalsIgnoreCase(newdate)) {
							//多:一致:两次查询都包含海关编码
							if (Arrays.asList(oldFields).contains("hscode")&&Arrays.asList(fields).contains("hscode")) {
								String oldhscode=ReportHelpUtil.newInstanceReportUtil().getHscode(oldFields, oldValues);
								String newhscode=ReportHelpUtil.newInstanceReportUtil().getHscode(fields, values);
								return CreateFactory.createListFactory(ParamEnumUtil.containhscode.name()).getFilterListModel(fields, values, country, map, getFalg(oldhscode, newhscode));
							}
							//多:一致:两次查询没有同时包含海关编码
							if (!Arrays.asList(oldFields).contains("hscode")||!Arrays.asList(fields).contains("hscode")) {
								lists = CreateFactory.createListFactory(ParamEnumUtil.nocontainhscode.name()).getFilterListModel(fields, values, country, map, null);
							}			
						}
					}
				}
			}
			//各种情况导致 需要重新检索数据
			if (!ReportHelpUtil.newInstanceReportUtil().isInitSearch(oldCountry,oldFields,oldValues,country, fields, values)) {
				lists = CreateFactory.createListFactory(ParamEnumUtil.init.name()).getFilterListModel(fields, values, country, map,null);
			}
		}
		//直接通过检索索引文档 得到新的集合
		if (map.get("useFilter").toString().equals("yes")) {
			lists =  CreateFactory.createListFactory(ParamEnumUtil.init.name()).getFilterListModel(fields, values, country, map,null);
		}
//		//list转化 方便删除
//		CopyOnWriteArrayList arr = new CopyOnWriteArrayList(lists);
//		
//		//排除月份
//		if(map.get("datestr")!=null){
//			StringBuilder sb = new StringBuilder();
//			String datestr = (String)map.get("datestr").toString();
//			if(datestr!=null){
//				String[] dt = datestr.split(",");
//				for (String s : dt) {
//					sb.append(s.split("-")[0]+s.split("-")[1]+",");
//				}
//				datestr = sb.toString();
//			}
//			Iterator it = arr.iterator();
//			while (it.hasNext()) {
//				DataReportSumModel d = (DataReportSumModel) it.next();
//				if(datestr.contains(d.getDate())){
//					arr.remove(d);
//				}
//			}
//		}
		return lists;
	}
	
	
	/**
	 * 返回一个字符串
	 * @param oldhs
	 * @param nhs
	 * @return
	 */
	private String getFalg(String oldhs,String nhs){
		//两次查询的海关编码一致
		if (oldhs.equals(nhs)) {
			return "samelast";
		}
		//本次查询的海关编码在上一次查询范围之内
		if (oldhs.length()<nhs.length()) {
			return "innerlast";
		}
		//本次查询的海关编码超出上一次查询的海关编码范围之内
		if (oldhs.length()>nhs.length()) {
			return "outterlast";
		}
		return null;
	}
	
	
	
}
