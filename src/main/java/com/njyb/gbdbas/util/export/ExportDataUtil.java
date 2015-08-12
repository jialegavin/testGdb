package com.njyb.gbdbas.util.export;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * @Description 将集合转成字符串工具类
 * @author XL
 * @date 2015-02-27
 * @version 标准版
 */
public class ExportDataUtil {

	//log记录日志
	private static final Logger log = Logger.getLogger(ExportDataUtil.class);
	
	/**
	 * 集合转数据
	 * 
	 * @param <T>
	 * @param list
	 *            原始集合
	 * @param fields
	 *            属性字段
	 * @return
	 * @throws Exception
	 */
	public static <T> List<String[]> getList(List<T> list, String... fields)
	{
		List<String[]> data = new LinkedList<String[]>();
		for (T c : list) 
		{
			String[] arr = new String[fields.length];
			for (int i = 0; i < fields.length; i++) 
			{
				try 
				{
					if(c != null){
						arr[i] = BeanUtils.getProperty(c, fields[i]);
					}
				} 
				catch 
				(IllegalAccessException | InvocationTargetException
						| NoSuchMethodException e) 
				{
					log.debug(e.getMessage());
				}
			}
			// 将数组放入list集合
			data.add(arr);
		} 
		return data;
	}
	
	/**
	 * 根据指定字段--对集合进行降序排序
	 * @param ls 集合
	 * @param property 排序字段
	 */
	@SuppressWarnings("unchecked")
	public static <T> void executeSort(List<T>ls,String property)
	{
		Collections.sort(ls,new CommonCompartor(property));
	}
	
	/**
	 * @param String 待处理的数值字符串
	 * @honghao
	 */
	public static String fmtMicrometer(String text)  
    {  
        DecimalFormat df = null;  
        if(text.indexOf(".") > 0)  
        {  
            if(text.length() - text.indexOf(".")-1 == 0)  
            {  
                df = new DecimalFormat("###,##0.");  
            }else if(text.length() - text.indexOf(".")-1 == 1)  
            {  
                df = new DecimalFormat("###,##0.0");  
            }else  
            {  
                df = new DecimalFormat("###,##0.00");  
            }  
        }else   
        {  
            df = new DecimalFormat("###,##0");  
        }  
        double number = 0.0;  
        try {  
             number = Double.parseDouble(text);  
        } catch (Exception e) {  
            number = 0.0;  
        }  
        return df.format(number);  
    }
}
