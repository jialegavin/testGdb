package com.njyb.test.jiahongping.lucene;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njyb.gbdbase.service.ITestService;

public class SpringAopTest {
	public static void main(String[] args) {
		String path="config\\core\\applicationContext.xml";
		ApplicationContext context=new ClassPathXmlApplicationContext(path);
		ITestService service=context.getBean(ITestService.class);
		service.test("jiahp","gongfu");
		System.exit(0);
	}
}
