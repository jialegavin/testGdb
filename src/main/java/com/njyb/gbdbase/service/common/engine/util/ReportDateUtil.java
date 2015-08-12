package com.njyb.gbdbase.service.common.engine.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.njyb.gbdbas.util.IConstantUtil;

/**
 * 报表有关的日期格式需要处理的工具类
 * @author 贾红平
 *
 */
public class ReportDateUtil {
	/**
	 * 日期加减
	 * @author honghao
	 * @date   2013/12/5
	 * @param type      year(年 )           month(月)                quarter(季度)
	 * @param value 1加一年 -1 减一年                     1加一个月 -1减一个月        3加一个季度   -3减一个季度
	 * @return
	 */
	public static String getChangeDate(String type,String date,int value,String dateType)throws Exception
	{
		SimpleDateFormat formate = new SimpleDateFormat(dateType);
		//mm/yyyy
		Date fdate  = formate.parse(date);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fdate);
		if(null!=type&&type.equals(IConstantUtil.YEAR))
		{ 
			//例如用户输入6月30号时，计算上月环比时需要取5月31号
			if(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == fdate.getDate())
			{
				calendar.add(1,value);
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
			}else
			{
				calendar.add(1,value);
			}
		}else if(null!=type&&type.equals(IConstantUtil.MONTH))
		{
			//例如用户输入6月30号时，计算上月环比时需要取5月31号
			if(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == fdate.getDate())
			{
				calendar.add(2,value);
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
			}
			else
			{
				calendar.add(2,value);
			}
		}else if(null!=type&&type.equals(IConstantUtil.QUAETER))
		{
			calendar.add(2,value);
		}else if(null!=type&&type.equals(IConstantUtil.DAY))
		{
			calendar.add(Calendar.DAY_OF_MONTH, value);
		}
		String str = formate.format(calendar.getTime());
		return str;
	}
}
