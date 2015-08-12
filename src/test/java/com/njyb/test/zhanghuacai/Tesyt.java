package com.njyb.test.zhanghuacai;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Tesyt {
		/**
	    * 正则表达式判断日期格式
	    * @param sDate 日期
	    * @return boolean
	    * @author 章华才
	    */
	   private void isValidDate(String sDate) {
//			Pattern p = Pattern.compile(eL);
//			Matcher m = p.matcher(sDate);
//			boolean dateFlag = m.matches();
//			if (!dateFlag) {
//				System.out.println("格式错误");
//			}
//			System.out.println("格式正确");　preg_replace('/"|\'/', '', $str) 
			
		   String s3 = "a'b\"c<d>e#f&g";
		 //替换所有非法字符
		 //结果：  a*b*c*d*e*f*g     
//		 System.out.println(s3.replaceAll("['\"<>#&]", ""));
		  
		 //替换所有非 非法字符
		 //结果：  *'*"*<*>*#*&*       
//		 System.out.println(s3.replaceAll("[^'\"<>#&]", "*"));
		 
		 //匹配和高亮显示 
		 StringBuffer sb = new StringBuffer();
		 Pattern p = Pattern.compile("zhanghuacai");
		 Matcher m = p.matcher("huacai");
		 //判断是否找到
		 while(m.find()){
			 
		 }
		   
		   
	}
	   
	   @Test
	  public void test(){
		   isValidDate("2015/08");
	  }
}
