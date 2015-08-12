package com.njyb.gbdbase.service.common.intercontinental;


public class GetParse
{
   private GetParse(){}
   
   private static  IParseDaoXml parse;
   
   public static IParseDaoXml getInstance()
   {
	     if(parse==null)
	     {
	    	 parse = new ParseDaoXmlImpl();
	     }
		 return    parse;
   }
}
