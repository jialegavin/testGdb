package com.njyb.test.chenhu;

import org.junit.Test;

/**
 * 测试递归算法
 * @author chenhu
 * 2015年3月27日
 */
public class TestReCu {
	private static int i=1;
	/**
	 * 测试递归算法
	 * @return
	 */
   public static int getR(int num){
	  
	   i*=num;
	   if(num>1)
	   {
		   --num;
		   getR(num);
	   }
	   return i;
   }
  @Test
   public void setI()
   {
	 String s = TestReCu.class.getClassLoader().getResource("")+"";
	 System.out.println(s);
   }
}
