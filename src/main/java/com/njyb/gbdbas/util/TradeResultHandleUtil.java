package com.njyb.gbdbas.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;

/**
 * 贸易情报数据结果相关辅助处理的工具类
 * @author 贾红平
 *
 */
public class TradeResultHandleUtil {
	/**
	 * 列表中查询必要条件值红色展示
	 * @param keyValue
	 * @param regx
	 * @auther honghao 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @return
	 */
	public static <T> List<T> fieldConRedOnResult(String queryKey,String queryValue,List<T> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		List<T> newList = new ArrayList<T>();
		newList = getNewList(list,newList);
		//查询条件构成的数组
		String[] keyArray = queryKey.split(";");
		String[] keyValue = queryValue.split(";");
		
		//查询必要条件需要在列表中红色展示
		List<String> redDisplayField = new LinkedList<String>(); 
		List<String> redDisplayValue = new LinkedList<String>(); 
		for(int i =0;i<keyArray.length;i++)
		{
			//海关编码
			if(keyArray[i].equals(DataSearchConstantUtil.HSCODE_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.HSCODE_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//产品描述
			else if(keyArray[i].equals(DataSearchConstantUtil.GOOD_DESC_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.GOOD_DESC_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//采购商
			else if(keyArray[i].equals(DataSearchConstantUtil.IMPORTER_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.IMPORTER_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//原产国
			else if(keyArray[i].equals(DataSearchConstantUtil.ORIGIN_COUNTRY_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.ORIGIN_COUNTRY_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//出口商
			else if(keyArray[i].equals(DataSearchConstantUtil.EXPORTER_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.EXPORTER_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//起运港
			else if(keyArray[i].equals(DataSearchConstantUtil.START_PORT_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.START_PORT_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//抵达港
			else if(keyArray[i].equals(DataSearchConstantUtil.END_PORT_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.END_PORT_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//目的国
			else if(keyArray[i].equals(DataSearchConstantUtil.DEST_COUNTRY_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.DEST_COUNTRY_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//提单号
			else if(keyArray[i].equals(DataSearchConstantUtil.BL_NUMBER_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.BL_NUMBER_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			//通知人
			else if(keyArray[i].equals(DataSearchConstantUtil.NOTIFIER_LUCENE))
			{
				redDisplayField.add(DataSearchConstantUtil.NOTIFIER_MODEL);
				redDisplayValue.add(keyValue[i]);
			}
			
		}
		//列表中字段变成红色
		for(T t:newList)
		{
			for(int i=0;i<redDisplayField.size();i++)
			{
				String value = redDisplayValue.get(i);
				//多海关编码需要对每一个海关编码红色显示
				if(value !=null && value.split(",").length>1)
				{
					for(int j=0;j<value.split(",").length;j++)
					{
						BeanUtils.setProperty(t, redDisplayField.get(i),oneConRedOnResult(BeanUtils.getProperty(t, redDisplayField.get(i)),value.split(",")[j]));
					}
				}else
				{
					BeanUtils.setProperty(t, redDisplayField.get(i),oneConRedOnResult(BeanUtils.getProperty(t, redDisplayField.get(i)),redDisplayValue.get(i)));
				}
			}
		}
		return newList;
	}
	
	/**
	 * 列表中某一个查询条件值红色展示
	 * @param keyValue
	 * @param regx
	 * @auther honghao 
	 * @return
	 */
	protected static String oneConRedOnResult(String keyValue,String regx){
		String wordReg = "(?i)"+regx;
		StringBuffer sb = new StringBuffer();  
		if(keyValue==null)
		{
			keyValue="";
		}
		Matcher matcher = Pattern.compile(wordReg).matcher(keyValue);  
	    while(matcher.find())
	    {
	    	matcher.appendReplacement(sb, "<font color='#1369c0'>"+matcher.group()+"</font>");
	    }  
	    matcher.appendTail(sb);  
	    return sb.toString();
	}
	
	/**
	 * 各个国家备份新对象集合
	 *@auther honghao
	 * @return
	 */
	public static <T>   List<T> getNewList(List<T> listSrc,List<T> listDec) {
		try {
				if(listSrc != null && listSrc.size()>0){
					Class clazz = listSrc.get(0).getClass();
					Object objSrc;   
					Object objDec;
					Field[] fields = clazz.getDeclaredFields();
					for(int i=0;i<listSrc.size();i++){
						objSrc = listSrc.get(i);  
						objDec = clazz.newInstance();
						for(int j=0;j<fields.length;j++){    
							String fieldName = fields[j].getName();
							Class fieldType = fields[j].getType();
							if(fieldName.equalsIgnoreCase("serialVersionUID")){
								continue;  
							}
							try {
								Method method = clazz.getDeclaredMethod(getMethodName("get",fieldName));
								Object objValue = method.invoke(objSrc);
								if(fieldType.getName().contains("Date")&&method.getReturnType().getName().contains("String")){
									if (objValue!=null&& objValue.toString().length() != 0) {
										objValue = DataUtil.parseDate(objValue.toString(), 3);//yyyy-MM-dd转为日期
								 }
								}
								method = clazz.getDeclaredMethod(getMethodName("set", fieldName),new Class[]{fieldType});
								method.invoke(objDec,objValue);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						listDec.add((T)objDec);
					}
				}else{
					return  listSrc;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return listDec;
	  }
	
	/**
	 * 根据属性
	 */
	public static String getMethodName(String setOrGet,String field){
		return setOrGet+field.toUpperCase().charAt(0)+field.substring(1);
	}
	
	 /**
	  * 处理list中double类型的精度
	  * @param request
	  * @param list
	  * @param map
	  * @param type
	  * @return
	  * @throws Exception
	  */
   public static <T> List<T> processPrecision(HttpServletRequest request,List<T> list,Map map,String type,int wei)throws Exception
   {
   	String[] fields = new String[]{};
   	if(list != null)
   	{
	    	DecimalFormat df = null;
	    	if(wei ==1)
	    	{
	    		df = new DecimalFormat("###.0");
	    	}else if(wei ==2)
	    	{
	    		df = new DecimalFormat("###.00");
	    	}
	    	if(DataSearchConstantUtil.SEARCH.equals(type))
	    	{
	    		fields = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.SEARCH_FORMATPRECISION)).toString().split(",");
	    	}else if(DataSearchConstantUtil.REPORT.equals(type))
	    	{
	    		fields = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.REPORT_FORMATPRECISION)).toString().split(",");
	    	}else if(DataSearchConstantUtil.MOM.equals(type) || DataSearchConstantUtil.MYOY.equals(type))
	    	{
	    		fields = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MOM_MYOY_FORMATPRECISION)).toString().split(",");
	    	}else if(DataSearchConstantUtil.YOY.equals(type))
	    	{
	    		fields = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.YOY_FORMATPRECISION)).toString().split(",");
	    	}
	    	
	    	for(String s : fields)
	    	{
	    		//如果该国家没有精度处理的字段，s就是等于null/“” 就直接还回不在走下去，否则会报错
	    		//java.lang.NoSuchMethodException: Unknown property '' on class 'class com.njyb.gbdbase.model.datasearch.uk.UkImportModel'
	    		if(s == null || s.isEmpty()){
	    			return list;
	    		}
	    		for(T t :list)
	    		{
	    			String fieldValue = BeanUtils.getProperty(t, s)!=null?BeanUtils.getProperty(t, s):"0";
	    			 BeanUtils.setProperty(t, s,df.format(Double.parseDouble(fieldValue)));
	    		}
	    	}
   	}
   	return list;
   }
   
   /**
    * 处理特殊字符
    * String key  原查询条件key值
    * String value 原查询条件value值
    * @auther honghao 
    *  @return value 处理过特殊字符的value
    */
   public static String  processSpecialChara (HttpServletRequest request,String key,String value) throws Exception
   {
   	String [] processField = {"goods_desc","importer","origin_country","exporter","start_port","end_port","dest_country","notifier"};
   	String [] keyArray = key.split(";");
   	String [] valueArray = value.split(";");
   	StringBuffer stringBuffer = new StringBuffer();
   	for(int i=0;i< keyArray.length ;i++)
   	{
   		if(Arrays.asList(processField).contains(keyArray[i]))
   		{
   			String resultValue = ReportHelpUtil.handleSybmol(valueArray[i]);
   			stringBuffer.append(resultValue).append(";");
   		}else
   		{
   			stringBuffer.append(valueArray[i]).append(";");
   		}
   	}
   	return stringBuffer.toString();
   }
}
