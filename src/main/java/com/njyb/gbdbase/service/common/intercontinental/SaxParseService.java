package com.njyb.gbdbase.service.common.intercontinental;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 洲际解析实现类
 * @author Administrator
 *
 */
public class SaxParseService extends DefaultHandler 
{

	 private String currentTag = null;//作用是记录解析时的上一个节点名称  
	 
	 private List<Country> countrys = null;  
	 
	 private Country contry = null; 
	 
	 public String  getCountrys(String bizname)
	 {  
            for (Country country : countrys) 
            {
			      if(country!=null&&bizname!=null&&bizname.equals(country.getBizname()))
			      {
                       return country.getCountryname()==null?"":country.getCountryname();
			      }	
			}
	        return null;  
	 }  
	
	@Override
	public void startDocument() throws SAXException 
	{
		countrys = new ArrayList<Country>();
		super.startDocument();
	}


	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		if("biz".equals(qName))
		{  
			contry = new Country();  
			contry.setBizname(attributes.getValue(0));  
        }  
		currentTag = qName;//将正在解析的节点名称赋给preTag 
	}

	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException 
	{
		if("biz".equals(qName))
		{  
			countrys.add(contry);  
			contry = null;  
        } 
		currentTag = null;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException 
	{
		if(currentTag!=null)
		{  
            String content = new String(ch,start,length);  
            if("country".equals(currentTag))
            {  
            	if(contry!=null)
            	{
            	   contry.setCountryname(content);
            	}
            }
        }  
	}
}
