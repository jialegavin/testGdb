package com.njyb.gbdbas.util.pagetemplate;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 选择字段工具类
 * @author honghao
 * @date 	2015-04-21
 * @version 标准版
 */ 
public class SelectFieldUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(SelectFieldUtil.class);
	
	//table的head前面部分
	private static final String  table_head = "<table>";
	//table的结束标签
    private static final String  table_end = "</table>";
	//tr开始标签
	private static final String  tr_head = "<tr>";
	//tr结束标签
	private static final String  tr_end = "</tr>";
	//td：checkBox  checked
	private static final String  td_checkbox_checked =  "<td><input type='\"checkbox\"' name = '\"selectField\"'  checked='\"checked\"' value=''{";
	//td：checkBox  unchecked
	private static final String  td_checkbox_unchecked  = "<td><input type='\"checkbox\"' name = '\"selectField\"' '\"selectField\"' value=''{";
	
	//td：checkBox  unchecked
	private static final String  td_checkbox_id  = "}'' id=''{";
	
    //td：checkBox结束部分
	private static final String  td_checkbox_behind = "}''></input></td>";
	//td字段文字描述开始部分
	private static final String  td_wenzi_front =  "<td>{";
	//td字段文字描述结束部分
	private static final String  td_wenzi_behind =  "}</td>";
	
	/**
	 * 动态生成各国选择字段对话框内容
	 * @param country
	 * @param map
	 * @param selectFieldId 页面选择的字段id
	 * @param selectFieldName 页面选择的字段名
	 * @return
	 */
	public static <T>String fmtHtml(String country,Map map,String selectFieldId,String selectFieldName) 
	{
		log.info("进入SelectFieldUtil类的fmtHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//查询结果展示字段中文名
		//String [] displayZhName = map.get(country+DataSearchConstantUtil.RESULTDISPLAYZHNAME).toString().split(",");
		//query result english name
		String [] displayEnName = map.get(country+DataSearchConstantUtil.RESULTDISPLAYENNAME).toString().split(",");
		
		//查询结果展示字段id名
		String [] displayFieldId = map.get(country+DataSearchConstantUtil.RESULTDISPLAYFIELD).toString().split(",");
		String [] hideZhName = new String[]{};
		if (map.containsKey(country+DataSearchConstantUtil.RESULTHIDEENNAME)) {
			//查询结果隐藏字段中文名---change from hideZhName to hideEnName
			hideZhName = map.get(country+DataSearchConstantUtil.RESULTHIDEENNAME).toString().split(",");
		}
		String [] hideFieldId = new String[]{};
		if (map.containsKey(country+DataSearchConstantUtil.RESULTHIDEFIELD)) {
			//查询结果隐藏字段中文名
			hideFieldId = map.get(country+DataSearchConstantUtil.RESULTHIDEFIELD).toString().split(",");
		}
		//该国家所有的列
		String[] allFieldId = (String[]) ArrayUtils.addAll(displayFieldId, hideFieldId);
	    //该国家所有的列
		String[] allFieldName = (String[]) ArrayUtils.addAll(displayEnName, hideZhName);
		//html中的下标
		int index = 0;
		//参数列表
		List<String> paramList = new ArrayList<String>();
	
		buf.append(table_head);
		
		//前台页面选择字段选中的列
		if(selectFieldId !=null && selectFieldId.trim() !="")
		{
			displayFieldId = selectFieldId.split(",");
			displayEnName = selectFieldName.split(",");
		}

		for(int i=0;i<allFieldId.length;i++)
		{
			//拼出选中的checkbox列
			if(Arrays.asList(displayFieldId).contains(allFieldId[i]))
			{
				buf.append(tr_head);
				buf.append(td_checkbox_checked).append(index++).append(td_checkbox_id).append(index++).append(td_checkbox_behind);
				buf.append(td_wenzi_front).append(index++).append(td_wenzi_behind);
				buf.append(tr_end);
				paramList.add(allFieldName[i]);
				paramList.add(allFieldId[i]);
				paramList.add(allFieldName[i]);
			}
			//拼出不选中的checkbox列
			else{
				//判断隐藏字段是否为空或为0
				if(allFieldId[i].length() != 0){
					buf.append(tr_head);
					buf.append(td_checkbox_unchecked).append(index++).append(td_checkbox_id).append(index++).append(td_checkbox_behind);
					buf.append(td_wenzi_front).append(index++).append(td_wenzi_behind);
					buf.append(tr_end);
					paramList.add(allFieldName[i]);
					paramList.add(allFieldId[i]);
					paramList.add(allFieldName[i]);
				}
			}
		}
		
		buf.append(table_end);
		
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}
}