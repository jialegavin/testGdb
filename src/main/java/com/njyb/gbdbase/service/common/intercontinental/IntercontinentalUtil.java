package com.njyb.gbdbase.service.common.intercontinental;

import java.util.HashMap;
import java.util.Map;

import com.njyb.gbdbas.util.SysContentUtil;

public class IntercontinentalUtil 
{    
	
   public static final String path="com/njyb/gbdbase/service/common/intercontinental/Intercontinental.xml";
   public static final String PATH_EN="com/njyb/gbdbase/service/common/intercontinental/Intercontinental_en.xml";
	
   public synchronized static Map<String,String> createMap() {
//	   String language = SysContentUtil.getSession().getAttribute("language").toString();
	   String language = "message_en_US";
	   String finalPath=("message_en_US".equalsIgnoreCase(language))?PATH_EN:path;
	    IParseDaoXml parsexml =  GetParse.getInstance();
		Map<String, String> map = new HashMap<String, String>();
		map.put("非洲",parsexml.getDaoXml(finalPath,"afraica"));
		map.put("亚洲",parsexml.getDaoXml(finalPath,"asia"));
		map.put("欧洲",parsexml.getDaoXml(finalPath,"europe"));
		map.put("北美洲",parsexml.getDaoXml(finalPath,"north_america"));
		map.put("大洋洲",parsexml.getDaoXml(finalPath,"oceania"));
		map.put("南美洲",parsexml.getDaoXml(finalPath,"south_america"));
	    return map;
   }
   
   public synchronized static Map<String,String> getCountryList() 
   {
	    IParseDaoXml parsexml =  GetParse.getInstance();
		Map<String, String> map = new HashMap<String, String>();
		map.put("countrylist",parsexml.getDaoXml(path,"countryslist"));
		map.put("en_countryList",parsexml.getDaoXml(path,"countryslisten"));
	    return map;
   }
   
}
