//package com.njyb.test.jiahongping.lucene;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.experimental.theories.suppliers.TestedOn;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.njyb.gbdbas.util.PageBeanUtil;
//import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
//import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
//import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
//import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaImportModel;
//import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
//import com.njyb.gbdbase.service.common.engines.IMultiHscodeMarkService;
//import com.njyb.gbdbase.service.common.engines.IReportDetailService;
//import com.njyb.gbdbase.service.common.engines.IReportEngineService;
//import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
//public class TestFour {
//	//定义需要参数的值
//	static ApplicationContext context=null;
//	String path="config\\core\\applicationContext.xml";;
//	String[]fields=new String[]{"hscode","date"};
//	String[]values=new String[]{"842199","2014-01-01,2015-01-28"};
//	PageBeanUtil page=new PageBeanUtil(100);
//	String countryName=LuceneConstant.ARGENTINA_IMPORT_STRING;
//	IReportEngineService build=null;
//	IReportDetailService detailService=null;
//	IMultiHscodeMarkService markService=null;
//	//String type="cp_trade";
//	//String type="jckscompare";
//	String type="jks";
//	ReportCommonParamModel common=new ReportCommonParamModel(fields, values, countryName, "report", null, type);
//	@Test
//	public void testListData(){
//		long start=System.currentTimeMillis();
//		Map<String, List<DataReportSumModel>>map=build.builderMapList(common,true);
//		
//		int i=0;
//		int count=0;
////		for(Object o:map.keySet()){
////			List<DataReportSumModel>ls=map.get(o);
////			i=ls.size();
////			if(o.equals("jks")){
////				for(DataReportSumModel data:ls){
////					count=count+data.getTradeCount();
////					System.out.println("进口商:"+data.getImporter()+":金额:"+data.getTradeMoney()+":"+":次数:"+data.getTradeCount());
////				}
////			}
////		}
//		long end=System.currentTimeMillis();
//		System.out.println("耗时:"+(end-start)/1000+"s"+",记录数是:"+i);
//		System.out.println("实际查询记录数是:"+count);
//		System.out.println("=================================获取多海关编码备注");
//		List<DataReportSumModel>hscodeMap=markService.getMultiHscodeMarkInfo("MANN HUMMEL ARGENTINA SA X", LuceneConstant.ARGENTINA_IMPORT_STRING, "842199,230910","jks");
//		for(DataReportSumModel data:hscodeMap){
//			 
//				System.out.println(data.getHscode()+"<<:"+data.getTradeMoney()+":"+data.getTradeWeight()+":"+data.getTradeQuantity()+":"+data.getTradeCount());
//			 
//		}
//	}
//	
//	@Before
//	public void initContext(){
//		context=new ClassPathXmlApplicationContext(path);
//		build=context.getBean(IReportEngineService.class);
//		detailService=context.getBean(IReportDetailService.class);
//		markService=context.getBean(IMultiHscodeMarkService.class);
//	}
//	@After
//	public void destoryContext(){
//		System.out.println("spring is great!");
//	}
//	@Test
//	public void testHello(){
//	}
//}
