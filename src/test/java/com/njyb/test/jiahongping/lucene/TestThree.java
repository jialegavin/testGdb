package com.njyb.test.jiahongping.lucene;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaImportModel;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engines.IReportCompareService;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;
import com.njyb.gbdbase.service.common.engines.IReportEngineService;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
public class TestThree {
	//定义需要参数的值
	static ApplicationContext context=null;
	String path="config\\core\\applicationContext.xml";;
	String[]fields=new String[]{"hscode","date"};
	String[]values=new String[]{"842199","2014-01-01,2015-01-28"};
	PageBeanUtil page=new PageBeanUtil(100);
	String countryName=LuceneConstant.ARGENTINA_IMPORT_STRING;
	IReportCompareService reportCompareService=null;
	//String type="cp_trade";
	String type="jckscompare";
	//String type="jks";
	ReportCommonParamModel common=new ReportCommonParamModel(fields, values, countryName, "report", null, type);
	@Test
	public void testListData(){
		long start=System.currentTimeMillis();
		List<DataReportSumModel>dlist=reportCompareService.builderSingleList(common, true);
		for(DataReportSumModel data:dlist){
			System.out.println(data.getImporter()+":"+data.getOrigin_country()+":"+data.getDate()+":"+data.getHscode()+":"+data.getGoodsDesc()+":"+data.getStart_port());
		}
	}
	
	@Before
	public void initContext(){
		context=new ClassPathXmlApplicationContext(path);
		reportCompareService=context.getBean(IReportCompareService.class);
	}
	@After
	public void destoryContext(){
		System.out.println("spring is great!");
	}
	@Test
	public void testHello(){
	}
}
