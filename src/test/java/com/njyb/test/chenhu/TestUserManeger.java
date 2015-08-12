package com.njyb.test.chenhu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njyb.gbdbase.service.admincenter.IUserManageService;


public class TestUserManeger{
	
	private IUserManageService userService;
	
	@Before
    public void before(){                                                                    
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:/config/core/applicationContext.xml"
                ,"classpath*:/config/mybatis/mybatis.cfg.xml"});
        userService = (IUserManageService) context.getBean("userManageService");
    }
     
    @Test
    public void addUser(){
    	System.out.println(userService.queryAllSons(888).toString());
    }
}
