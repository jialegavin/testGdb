package com.njyb.gbdbase.service.common.intercontinental;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class ParseDaoXmlImpl implements IParseDaoXml 
{

	@Override
	public String  getDaoXml(String resourcename,String bizname)
	{ 
		SaxParseService handler = null; 
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();  
			SAXParser parser = factory.newSAXParser();  
			handler = new SaxParseService();
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream(resourcename);
			parser.parse(stream, handler);
		} catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler.getCountrys(bizname);
	}

}
