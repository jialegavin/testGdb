package com.njyb.test.jiahongping;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class Test2 {
	public static  void main(String[] args) throws Exception {
		List<PersonBean>ps=new ArrayList<PersonBean>();
		for (int i = 0; i < 20; i++) {
			PersonBean bean=new PersonBean();
			bean.setUsername("jiahp"+i);
			bean.setPwd("abcd"+i);
			ps.add(bean);
		}
		
		 //打印基本信息
//	    for(PersonBean bean:ps){
//	    	 
//	    	System.out.println(bean.getUsername()+":"+bean.getPwd());
//	    }
		//反射组装新属性
	    List<Object> ls=new ArrayList<Object>();
	    for (int i = 0; i <ps.size(); i++) {
	    	Object obj=UserBean.class.newInstance();
	    	BeanUtils.setProperty(obj, "username",BeanUtils.getProperty(ps.get(i), "username"));
	    	BeanUtils.setProperty(obj, "pwd",  BeanUtils.getProperty(ps.get(i), "pwd"));
	    	ls.add(obj);
		}
	    
	    System.out.println(ls.size());
	    
	    //打印基本信息
	    for(Object o:ls){
	    	UserBean bean=(UserBean)o;
	    	System.out.println(bean.getUsername()+":"+bean.getPwd());
	    }
	}
}
