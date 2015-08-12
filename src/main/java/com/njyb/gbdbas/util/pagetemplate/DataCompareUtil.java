package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 生成贸易情报数据对比页面工具类
 * @author honghao
 * @date 	2015-05-14
 * @version 标准版
 */ 
public class DataCompareUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(DataCompareUtil.class);
	//div 图标id名
	private static final String div_chartid="\n<div style='\"padding-top:4px;\"'  id=''{";
	//div 头部其他样式
	private static final String div_head_behind="}''>";
	//div结束标签
	private static final String div_behind="\n</div>";
	//table头部id名
	private static final String table_id="\n<table id=''{";
	//table头部其他样式
	private static final String table_head_behind = "}''  style='\"width:866px;overflow:hidden;\"' >";
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
	//4列数据表格的样式
	private static final String th_four_Field = "}'' Width='\"220\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//4列报表分析功能大全(包含查看明细，导出报表，同环比报表)
	private static final String th_four_functionField = "}'' Width='\"203\"' style='\"height: 30px\"' align='\"center\"' formatter='\"compareFunctionDaQuan\"'>";
	
	//5列数据表格的样式
	private static final String th_five_Field = "}'' Width='\"173\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//5列报表分析功能大全(包含查看明细，导出报表，同环比报表)
	private static final String th_five_functionField = "}'' Width='\"173\"' style='\"height: 30px\"' align='\"center\"' formatter='\"compareFunctionDaQuan\"'>";
	
	
	//6列数据表格的样式
	private static final String th_six_Field = "}'' Width='\"144\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//6列报表分析功能大全(包含查看明细，导出报表，同环比报表)
	private static final String th_six_functionField = "}'' Width='\"144\"' style='\"height: 30px\"' align='\"center\"' formatter='\"compareFunctionDaQuan\"'>";
	//7列数据表格的样式
	private static final String th_seven_Field = "}'' Width='\"123\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//7列报表分析功能大全(包含查看明细，导出报表，同环比报表)
	private static final String th_seven_functionField = "}'' Width='\"126\"' style='\"height: 30px\"' align='\"center\"' formatter='\"compareFunctionDaQuan\"'>";
	//8列数据表格的样式
	private static final String th_eight_Field = "}'' Width='\"108\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//8列报表分析功能大全(包含查看明细，导出报表，同环比报表)
	private static final String th_eight_functionField = "}'' Width='\"108\"' style='\"height: 30px\"' align='\"center\"' formatter='\"compareFunctionDaQuan\"'>";
	//列名称开始样式
	private static final String font_head = "\n<font style='\"font-size: 14px;font-weight: bold;\"'>{";
	//其他列名称结束样式
	private static final String font_behind = "}\n</font>";
	//th结束标签
	private static final String th_behind = "\n</th>";
	
	/**
	 * 生成贸易情报数据对比页面
	 * @param country
	 * @param resultFieldMap
	 * @param reportType:选择对比所在的报告类型
	 * @return
	 */
	public static <T>String fmtHtml(String country,Map resultFieldMap, String reportType) 
	{
		log.info("进入DepthDiggingUtil类的fmtHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//参数列表
		List<String> paramList = new ArrayList<String>();
		//html中的下标
		int index = 0;
		//该报告类型下的字段id
		String [] fieldId = resultFieldMap.get(country+"_"+reportType+DataSearchConstantUtil.FIELDID).toString().split(",");
		//该报告类型下的字段名称--change to english version
		//String [] fieldName = resultFieldMap.get(country+"_"+reportType+DataSearchConstantUtil.FIELDNAME).toString().split(",");
		  String [] fieldName = resultFieldMap.get(country+"_"+reportType+DataSearchConstantUtil.FIELDNAMEEN).toString().split(",");
		
		buf.append(div_chartid).append(index++).append(div_head_behind).append(div_behind).append(div_chartid)
		.append(index++).append(div_head_behind).append(div_behind).append(table_id).append(index++)
		.append(table_head_behind).append(thead_head).append(tr_head);
		paramList.add(reportType+"_datacompare_mixchart");
		paramList.add(reportType+"_datacompare_piechart");
		paramList.add(reportType+"_datacompare_table");
		
		for(int j=0;j<fieldName.length;j++)
		{
			buf.append(th_head).append(index++);
			if("Hide Columns".equals(fieldName[j]))
			//if("隐藏列".equals(fieldName[j]))
			{
				buf.append(th_hide_Field);
			}else
			{
				
				
				//不包含隐藏列如果数据表格中的列为5列
				if(fieldName.length-1 ==4)
				{
					if("Statement Analysis".equals(fieldName[j]))
					//if("报表分析".equals(fieldName[j]))
					{
						buf.append(th_four_functionField);
					}
					//比率字段不需要排序
					else if("percentage".equals(fieldId[j]))
					{
						buf.append(th_four_Field.replaceAll("sortable='\"true\"'", ""));
					}else
					{
						buf.append(th_four_Field);
					}
				}
				//不包含隐藏列如果数据表格中的列为5列
				else if(fieldName.length-1 ==5)
				{
					if("Statement Analysis".equals(fieldName[j]))
					//if("报表分析".equals(fieldName[j]))
					{
						buf.append(th_five_functionField);
					}
					//比率字段不需要排序
					else if("percentage".equals(fieldId[j]))
					{
						buf.append(th_five_Field.replaceAll("sortable='\"true\"'", ""));
					}else
					{
						buf.append(th_five_Field);
					}
				}
				//不包含隐藏列如果数据表格中的列为6列
				else if(fieldName.length-1 ==6)
				{
					if("Statement Analysis".equals(fieldName[j]))
					//if("报表分析".equals(fieldName[j]))
					{
						buf.append(th_six_functionField);
					}
					//比率字段不需要排序
					else if("percentage".equals(fieldId[j]))
					{
						buf.append(th_six_Field.replaceAll("sortable='\"true\"'", ""));
					}else
					{
						buf.append(th_six_Field);
					}
				}
				//不包含隐藏列如果数据表格中的列为7列
				else if(fieldName.length-1 ==7)
				{
					if("Statement Analysis".equals(fieldName[j]))
					//if("报表分析".equals(fieldName[j]))
					{
						buf.append(th_seven_functionField);
					}
					//比率字段不需要排序
					else if("percentage".equals(fieldId[j]))
					{
						buf.append(th_seven_Field.replaceAll("sortable='\"true\"'", ""));
					}else
					{
						buf.append(th_seven_Field);
					}
				}
				//不包含隐藏列如果数据表格中的列为8列
				else if(fieldName.length-1 ==8)
				{
					if("Statement Analysis".equals(fieldName[j]))
					//if("报表分析".equals(fieldName[j]))
					{
						buf.append(th_eight_functionField);
					}
					//比率字段不需要排序
					else if("percentage".equals(fieldId[j]))
					{
						buf.append(th_eight_Field.replaceAll("sortable='\"true\"'", ""));
					}else
					{
						buf.append(th_eight_Field);
					}
				}
			}
			buf.append(font_head).append(index++).append(font_behind).append(th_behind);
			paramList.add(fieldId[j]);
			paramList.add(fieldName[j]);
		}
		buf.append(tr_behind).append(thead_behind).append(table_behind);
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		log.info("生成的前段html语句为:"+html);
		return html;
	}
}