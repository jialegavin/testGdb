package com.njyb.gbdbas.util.export;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * 日期排序
 * @author XL
 *
 * @param <T>
 * @date 2015-04-02
 * @version 标准版
 */
public class DateComparator<T> implements Comparator<T> {

	//log记录日志
	private static final Logger log = Logger.getLogger(DateComparator.class);
	
	@Override
	public int compare(T o1, T o2) 
	{
		boolean isBefore=false; 
		try 
		{
			String firstDate = BeanUtils.getProperty(o1, "date");
			String secondDate = BeanUtils.getProperty(o2, "date");
			DateFormat df = null;
			if(firstDate.contains("/"))
			{
				df = new SimpleDateFormat("mm/yyyy");
			}
			else if(firstDate.contains("-"))
			{
				df = new SimpleDateFormat("yyyy-MM-dd");
			}
			else
			{
				df = new SimpleDateFormat("yyyyMM");
			}
			isBefore = df.parse(firstDate).before(df.parse(secondDate));
		} 
		catch (Exception e) 
		{
			log.debug(e.getMessage());
		}  
		int rntValue=0; 
		if(isBefore)
		{
			rntValue=-1; 
		}
		else
		{
			rntValue=1;
		}
		return rntValue;
	}

}
