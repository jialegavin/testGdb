package com.njyb.test.jiahongping.lucene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.RatioModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engines.IMonthYearTrendService;
import com.njyb.gbdbase.service.common.engines.IMultiHscodeMarkService;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;
import com.njyb.gbdbase.service.common.engines.IReportDrillService;
import com.njyb.gbdbase.service.common.engines.IReportEngineService;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;

public class SumaryTest {
	@SuppressWarnings("unchecked")
	public static <T> void main(String[] args) {
		String path="config\\core\\applicationContext.xml";;
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext(path);
		String[]fields=new String[]{"date","hscode"};
		String[]values=new String[]{"2013-01-01,2013-12-31","842199"};
		String countryName=LuceneConstant.PARAGUAY_IMPORT_STRING;
		//数据检索接口
		ISearchEngineService searchEngineService=context.getBean(ISearchEngineService.class);
		//数据报表接口
		IReportEngineService reportEngineService=context.getBean(IReportEngineService.class);
		//测试报表多海关编码备注接口
		IMultiHscodeMarkService multiHscodeMarkService=context.getBean(IMultiHscodeMarkService.class);
		//数据同环比接口
		IMonthYearTrendService monthYearTrendService=context.getBean(IMonthYearTrendService.class);
		//测试报表汇总的详情接口
		IReportDetailService detailService=context.getBean(IReportDetailService.class);
		//测试报表汇总对应的深度挖取接口
		IReportDrillService  reportDrillService=context.getBean(IReportDrillService.class);
		//设置分页信息
		PageBeanUtil page=new PageBeanUtil(500);
		//测试智力进口
	//	List<Integer>list=searchEngineService.getListKey(new SearchCommonParamModel(fields, values, countryName, "search", null, page));
		//System.out.println("ls:"+list.size());
		//测试报表汇总
		String type="cp_trade";
		ReportCommonParamModel common=new ReportCommonParamModel(fields, values, countryName, "report", null, type);
		Map<String, List<DataReportSumModel>>map=reportEngineService.builderMapList(common, true);
		System.out.println("===========================测试报表相关的所有功能能:汇总 备注 详情 钻取 趋势===============================");
		for(Object o:map.keySet()){
			List<DataReportSumModel>ls=map.get(o);
			if(o.equals("jks")){
				for(DataReportSumModel data:ls){
					if(data.getImporter().equals("COMERCIAL KAUFMANN S A")){
						System.err.println("====================COMERCIAL KAUFMANN S A 汇总信息:=============");
						System.out.println("进口商:"+data.getImporter()+":金额:"+data.getTradeMoney()+":次数:"+data.getTradeCount());						
					}
				}
			}
		}
		//测试报表汇总详情
		/*进口商:COMERCIAL KAUFMANN S A:金额:5121727.449999997::次数:1009*/
		/*进口商:PM CHILE SPA:金额:9545.5::次数:6*/
		/*date:201307:金额:6379493.81999999::次数:1153*/
		/*List<DataReportSumModel>detailList=detailService.buildReportSumList("201307", "qs",countryName);*/
//		List<DataReportSumModel>detailList=detailService.builderSingleList("COMERCIAL KAUFMANN S A", "jks",countryName);
//		System.err.println("==================COMERCIAL KAUFMANN S A 交易明细记录条数为:"+detailList.size()+"条===============显示前10条");
//		int i=0;
//		for(DataReportSumModel data:detailList){
//			if (i<10) {
//				//System.out.println(data.getImporter()+":"+data.getTradeMoney());				
//			}
//			i++;
//		}
		//遍历钻取结果
//		Map<String, List<DataReportSumModel>>drillMap=new HashMap<String, List<DataReportSumModel>>();
//		for(String typename:"drill&ycg,drill&qyg,drill&hgcs".split(",")){
//			List<DataReportSumModel>drillList=reportDrillService.drillReportSumList("COMERCIAL KAUFMANN S A", "jks", countryName,typename);
//			drillMap.put(typename, drillList);
//		}
//		System.err.println("=========================COMERCIAL KAUFMANN S A 深度挖取分析:=======================");
//		for(String str:drillMap.keySet()){
//			if (str.equals("drill&ycg")) {
//				System.err.println("COMERCIAL KAUFMANN S A交易的原产国:"+drillMap.get(str).size()+"条");
//				for(DataReportSumModel data:drillMap.get(str)){
//				//	System.out.println(data.getOrigin_country()+":"+data.getTradeMoney()+":"+data.getTradeCount());
//				}
//			}
//			if (str.equals("drill&qyg")) {
//				System.err.println("COMERCIAL KAUFMANN S A交易的起运港:"+drillMap.get(str).size()+"条");
//				for(DataReportSumModel data:drillMap.get(str)){
//				//	System.out.println(data.getStart_port()+":"+data.getTradeMoney()+":"+data.getTradeCount());
//				}
//			}
//			if (str.equals("drill&hgcs")) {
//				System.err.println("COMERCIAL KAUFMANN S A交易的海关城市:"+drillMap.get(str).size()+"条");
//				for(DataReportSumModel data:drillMap.get(str)){
//				//	System.out.println(data.getCustoms()+":"+data.getTradeMoney()+":"+data.getTradeCount());
//				}
//			}
//		}
		
//		List<DataReportSumModel>drillDetaiList=detailService.builderSingleList("METROPOLITANA", "drill&hgcs",countryName);
//		System.err.println("=======COMERCIAL KAUFMANN S A交易的海关城市 METROPOLITANA的交易明细:"+drillDetaiList.size()+"条===============显示前10条记录");
//		int k=0;
//		for(DataReportSumModel data:drillDetaiList){
//			if (k<10) {
//				//System.out.println(data.getCustoms()+":"+data.getImporter()+":"+data.getTradeMoney());				
//			}
//			k++;
//		}
		
//		System.err.println("====================测试多海关编码查询对应的备注信息=========================");
//		List<DataReportSumModel>remakrList=multiHscodeMarkService.getMultiHscodeMarkInfo("COMERCIAL KAUFMANN S A", countryName, "jks");
//		for(@SuppressWarnings("unused") DataReportSumModel data:remakrList){
//			//System.out.println(data.getHscode()+":"+data.getTradeMoney()+":"+data.getTradeWeight()+":"+data.getTradeQuantity()+":"+data.getTradeCount());
//		}
		System.err.println("===================测试非纯月份的月度同环比==================");
		Map<String, List<T>>m=monthYearTrendService.getRatioModelMap(common,"jks","YHAGUY REPUESTOS SA","mom");
		if (m!=null) {
			for(String str:m.keySet()){
				if (str.equals("mom")) {
					System.out.println("COMERCIAL KAUFMANN S A 月度环比信息:");
					for(RatioModel ratio:(List<RatioModel>)m.get(str)){
						System.out.println(ratio.getDate()+":"+ratio.getMoney()+":"+ratio.getMoneyRatio()+":"+ratio.getCount()+":"+ratio.getCountRatio());
					}
				}
				if (m.equals("myoy")) {
					System.out.println("COMERCIAL KAUFMANN S A 月度同比信息:");
					for(RatioModel ratio:(List<RatioModel>)m.get(str)){
						System.out.println(ratio.getDate()+":"+ratio.getMoney()+":"+ratio.getMoneyRatio()+":"+ratio.getCount()+":"+ratio.getCountRatio());
					}
				}
				if (m.equals("myoy")) {
					System.out.println("COMERCIAL KAUFMANN S A 年度同比信息:");
					for(DataReportSumModel ratio:(List<DataReportSumModel>)m.get(str)){
						System.out.println(ratio.getTradeMoney()+":"+ratio.getTradeCount());
					}
				}
			}
		}
	}
}
