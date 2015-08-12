//package com.njyb.test.jiahongping.auth;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.njyb.auth.IHelloService;
//import com.njyb.auth.ITestService;
//
//public class AuthTest {	
//	public static void main(String[] args) {
//		ApplicationContext context=null;
//		String path="config\\core\\applicationContext.xml";
//		context=new ClassPathXmlApplicationContext(path);
//		IHelloService service=(IHelloService) context.getBean(IHelloService.class);
//		service.helloA();
//		service.helloB();
//		System.exit(0);
//	}
//}
