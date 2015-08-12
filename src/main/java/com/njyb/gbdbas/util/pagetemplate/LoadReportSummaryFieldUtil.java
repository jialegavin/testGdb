package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 动态生成各国报表汇总字段工具类
 * @author XL
 * @date 2015-05-13
 * @version 标准版
 */ 
public class LoadReportSummaryFieldUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(LoadReportSummaryFieldUtil.class);
	//table头部id名
	private static final String table_id="\n<table id=''{";
	//table头部其他样式
	private static final String table_head_behind = "}''  style='\"width:1138px;height:380px;overflow:hidden;\"' >";
	//table结束标签
	private static final String table_behind="\n</table>";
	//thread开始标签
	private static final String thead_head="\n<thead>";
	//thread结束标签	
	private static final String thead_behind="\n</thead>";
	//tr开始标签
	private static final String tr_head = "\n<tr>";
	//tr结束标签
	private static final String tr_behind = "\n</tr>";
	//th开始标签
	private static final String th_head = "\n<th field=''{";
	//隐藏列的样式
	private static final String th_hide_Field = "}'' style='\"height: 30px\"' hidden='\"true\"' align='\"center\"'>";
	//2列数据表格的样式
	private static final String th_Two_Field = "}'' Width='\"565\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
		
	//3列数据表格的样式
	private static final String th_Three_Field = "}'' Width='\"375\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	
	//4列数据表格的样式
	private static final String th_Four_Field = "}'' Width='\"282\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//5列数据表格的样式
	private static final String th_Five_Field = "}'' Width='\"227\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//6列数据表格的样式
	private static final String th_Six_Field = "}'' Width='\"201\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//7列数据表格的样式
	private static final String th_Seven_Field = "}'' Width='\"172\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//8列数据表格的样式
	private static final String th_Eight_Field = "}'' Width='\"140\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//列名称开始样式
	private static final String font_head = "\n<font style='\"font-size: 14px;font-weight: bold;\"'>{";
	//其他列名称结束样式
	private static final String font_behind = "}\n</font>";
	//th结束标签
	private static final String th_behind = "\n</th>";
	
	/**
	 * 动态生成加载报表汇总字段
	 * @param country
	 * @param reportType
	 * @param resultFieldMap
	 * @return
	 */
	public static <T>String fmtLoadDataHtml(String country, String reportType, Map resultFieldMap) 
	{
		log.info("进入LoadReportSummaryFieldUtil类的fmtLoadDataHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//html中的下标
		int index = 0;
		//参数列表
		List<String> paramList = new ArrayList<String>();
		buf.append(table_id).append(index++).append(table_head_behind).append(thead_head).append(tr_head);
		paramList.add(reportType+"_LoadDataTable");
		//Change to FIELDNAMEEN
		String [] oldFieldname = resultFieldMap.get(country+"_"+reportType+DataSearchConstantUtil.FIELDNAMEEN).toString().split(",");
		String [] oldFieldid = resultFieldMap.get(country+"_"+reportType+DataSearchConstantUtil.FIELDID).toString().split(",");
		List<String> fieldname = new LinkedList<String>();
		List<String> fieldid = new LinkedList<String>();
		for(String str : oldFieldname)
		{
			//changed from 比率->Ratio, 报表分析->Statment Analysis
			if(!str.contains("Ratio")&&!str.contains("Statement Analysis"))
			{
				fieldname.add(str);
			}
		}
		for(String str : oldFieldid)
		{
			if(!str.contains("Percentage")&&!str.contains("function"))
			{
				fieldid.add(str);
			}
		}
		for(int j=0;j<fieldname.size() ; j++)
		{
			buf.append(th_head).append(index++);
			//changed from 隐藏咧-》Hide Columns
			if("Hide Columns".equals(fieldname.get(j)))
			{
				buf.append(th_hide_Field);
			}else
			{
				//不包含隐藏列如果数据表格中的列为3列
				if(fieldname.size()-1 ==2)
				{
					buf.append(th_Two_Field);
				}
				//不包含隐藏列如果数据表格中的列为3列
				if(fieldname.size()-1 ==3)
				{
					buf.append(th_Three_Field);
				}
				//不包含隐藏列如果数据表格中的列为4列
				if(fieldname.size()-1 ==4)
				{
					buf.append(th_Four_Field);
				}
				//不包含隐藏列如果数据表格中的列为5列
				if(fieldname.size()-1 ==5)
				{
					buf.append(th_Five_Field);
				}
				//不包含隐藏列如果数据表格中的列为6列
				else if(fieldname.size()-1 ==6)
				{
					buf.append(th_Six_Field);
				}
				//不包含隐藏列如果数据表格中的列为7列
				else if(fieldname.size()-1 ==7)
				{
					buf.append(th_Seven_Field);
				}
				//不包含隐藏列如果数据表格中的列为8列
				else if(fieldname.size()-1 ==8)
				{
					buf.append(th_Eight_Field);
				}
			}
			buf.append(font_head).append(index++).append(font_behind).append(th_behind);
			paramList.add(fieldid.get(j));
			paramList.add(fieldname.get(j));
		}
		buf.append(tr_behind).append(thead_behind).append(table_behind);
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}	
}