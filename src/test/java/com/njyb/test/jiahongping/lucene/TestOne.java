package com.njyb.test.jiahongping.lucene;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaImportModel;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
public class TestOne {
	//定义需要参数的值
	static ApplicationContext context=null;
	String path="config\\core\\applicationContext.xml";
	String[]fields=new String[]{"hscode","date","trade_type"};
	String[]values=new String[]{"842199","2013-01,2013-12","I"};
	PageBeanUtil page=new PageBeanUtil(500);
	String countryName=LuceneConstant.CHINA_EIGHT_STRING;
	ISearchEngineService service=null;
	@Test
	public void testListId(){
		List<Integer>ls=service.getListKey(new SearchCommonParamModel(fields, values, countryName, "search", null, page));
		System.out.println("ls:"+ls.size());
	}

	@Before
	public void initContext(){
		context=new ClassPathXmlApplicationContext(path);
		service=context.getBean(ISearchEngineService.class);
		
	}
	@After
	public void destoryContext(){
		System.out.println("spring is great!");
	}
	
}
