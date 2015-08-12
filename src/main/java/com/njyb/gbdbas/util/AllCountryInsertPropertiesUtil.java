package com.njyb.gbdbas.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.allCountrySelectField.AllCountrySelectFieldModel;
import com.njyb.gbdbase.model.datasearch.common.ConditionFieldModel;

import net.sf.json.JSONArray;

/**
 * 所有国家原产国字段配置
 * @author 章华才
 */
@Component
public class AllCountryInsertPropertiesUtil {
	
	@Autowired
	private ConditionFieldModel conditionFieldModel;
	
	private JSONArray jsonArray = null;
	
	public JSONArray getArray(String countryName,String fieldName){
		//危地马拉-尼加拉瓜-萨尔瓦多
		//获取字段数值
		Set<String> value = getField(countryName,getCountryName(countryName));
		if(value == null)
		{
			return null;
		}
		List<AllCountrySelectFieldModel> list = new ArrayList<AllCountrySelectFieldModel>();
		for (String s : value) {
			AllCountrySelectFieldModel model = new AllCountrySelectFieldModel();
			if(s != null && !"".equals(s))
			{
				//过虑数字
				if(testParttern(s))
				{
					continue;
				}
				model.setFieldValue(s);
			}
			list.add(model);
		}
		jsonArray = JSONArray.fromObject(list);
		//将jsonArray加入到缓存当中
		return jsonArray;
	}
	
	
	/**
	 * 获取所有国家存在原产国字段
	 * @param countryName
	 * @return
	 */
	public String [] getCountryName(String countryName){
		String names = conditionFieldModel.getConditionFieldMap().get(countryName + "_select").toString();
		if(names == null || "".equals(names))
		{
			return null;
		}
		String [] type = names.split(",");
		return type;
	}
	
	
	/**
	 * 正则表达式验证是否存在数值
	 * @param num
	 */
	public boolean testParttern(String num){   
	        //表达式的功能：验证必须为数字（整数或小数）   
			String pattern = "[0-9]+(.[0-9]+)?";   
			//对()的用法总结：将()中的表达式作为一个整体进行处理，必须满足他的整体结构才可以。   
			//(.[0-9]+)? ：表示()中的整体出现一次或一次也不出现   
			Pattern p = Pattern.compile(pattern);   
			Matcher m = p.matcher(num);   
			boolean b = m.matches(); 
			return b;
	}   
	
	/**
	 * 获取字段
	 * @param countryName
	 * @param util
	 * @return
	 */
	public Set<String> getField(String country, String[] countryName){
		StringBuffer sb = new StringBuffer();
		String [] value = null;
		Set<String> set =new HashSet<String>();
		if(countryName == null)
		{
			return null;
		}
		for (int i = 0; i < countryName.length; i++) {
			sb.append(conditionFieldModel.getConditionFieldMap().get(country + "_" + countryName[i]).toString());
		}
		value = sb.toString().split("~");
		//去重复
		for (int i = 0; i < value.length; i++) {
			set.add(value[i]);
		}
		return set;
	}
}
