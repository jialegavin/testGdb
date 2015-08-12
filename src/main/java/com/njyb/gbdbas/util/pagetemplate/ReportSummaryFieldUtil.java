package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.annotations.Logging;
import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbase.model.datasearch.common.MapModel;

/**
 * 动态生成各国报表汇总字段工具类
 * @author honghao
 * @date 	2015-05-04
 * @version 标准版
 */ 
public class ReportSummaryFieldUtil
{
	//log记录日志
//	private static final Logger log = Logger.getLogger(ReportSummaryFieldUtil.class);
	//div id名
	private static final String div_id="\n<div style='\"padding-top:50px;\"' id=''{";
	//div报告 id名
	private static final String div_reportid="\n<div style='\"padding-top:4px;\"'  id=''{";
	//div 图标id名
	private static final String div_chartid="\n<div style='\"padding-top:4px;\"'  id=''{";
	//div 头部其他样式
	private static final String div_head_behind="}''>";
	//div结束标签
	private static final String div_behind="\n</div>";
	//table头部id名
	private static final String table_id="\n<table id=''{";	
	//table头部其他样式
	private static final String table_head_behind = "}''  style='\"width:1210px;overflow:hidden;\"' >";
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
	
	//4列其他报告报表分析功能大全(包含查看明细，导出报表，同环比报表，对比中心)
	private static final String th_Four_functionField = "}'' Width='\"300\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionDaQuan\"'>";
	//4列交易趋势报表分析功能大全(包含查看明细，导出报表，对比中心)
	private static final String th_Four_qs_functionField = "}'' Width='\"300\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionQsDaQuan\"'>";
	//4列多海关编码报表分析功能大全(包含查看明细，导出报表，多海关编码备注，对比中心)
	private static final String th_Four_mutihscode_functionField = "}'' Width='\"300\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionMutiHscodeDaQuan\"'>";
	//4列数据表格的样式
	private static final String th_Four_Field = "}'' Width='\"300\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	
	
	//5列其他报告报表分析功能大全(包含查看明细，导出报表，同环比报表，对比中心)
	private static final String th_Five_functionField = "}'' Width='\"230\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionDaQuan\"'>";
	//5列交易趋势报表分析功能大全(包含查看明细，导出报表，对比中心)
	private static final String th_Five_qs_functionField = "}'' Width='\"230\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionQsDaQuan\"'>";
	//5列多海关编码报表分析功能大全(包含查看明细，导出报表，多海关编码备注，对比中心)
	private static final String th_Five_mutihscode_functionField = "}'' Width='\"230\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionMutiHscodeDaQuan\"'>";
	//5列数据表格的样式
	private static final String th_Five_Field = "}'' Width='\"227\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
		
	//6列其他报告报表分析功能大全(包含查看明细，导出报表，同环比报表，对比中心)
	private static final String th_Six_functionField = "}'' Width='\"204\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionDaQuan\"'>";
	//6列交易趋势报表分析功能大全(包含查看明细，导出报表，对比中心)
	private static final String th_Six_qs_functionField = "}'' Width='\"204\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionQsDaQuan\"'>";
	//6列多海关编码报表分析功能大全(包含查看明细，导出报表，多海关编码备注，对比中心)
	private static final String th_Six_mutihscode_functionField = "}'' Width='\"204\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionMutiHscodeDaQuan\"'>";
	//6列数据表格的样式
	private static final String th_Six_Field = "}'' Width='\"201\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//7列其他报告报表分析功能大全(包含查看明细，导出报表，同环比报表，对比中心)
	private static final String th_Seven_functionField = "}'' Width='\"177\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionDaQuan\"'>";
	//7列交易趋势报表分析功能大全(包含查看明细，导出报表，对比中心)
	private static final String th_Seven_qs_functionField = "}'' Width='\"177\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionQsDaQuan\"'>";
	//7列多海关编码报表分析功能大全(包含查看明细，导出报表，多海关编码备注，对比中心)
	private static final String th_Seven_mutihscode_functionField = "}'' Width='\"177\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionMutiHscodeDaQuan\"'>";
	//7列数据表格的样式
	private static final String th_Seven_Field = "}'' Width='\"172\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//8列其他报告报表分析功能大全(包含查看明细，导出报表，同环比报表，对比中心)
	private static final String th_Eight_functionField = "}'' Width='\"143\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionDaQuan\"'>";
	//8列交易趋势报表分析功能大全(包含查看明细，导出报表，对比中心)
	private static final String th_Eight_qs_functionField = "}'' Width='\"143\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionQsDaQuan\"'>";
	//8列多海关编码报表分析功能大全(包含查看明细，导出报表，多海关编码备注，对比中心)
	private static final String th_Eight_mutihscode_functionField = "}'' Width='\"143\"' style='\"height: 30px\"' align='\"center\"' formatter='\"functionMutiHscodeDaQuan\"'>";
	//8列数据表格的样式
	private static final String th_Eight_Field = "}'' Width='\"140\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//列名称开始样式
	private static final String font_head = "\n<font style='\"font-size: 14px;font-weight: bold;\"'>{";
	//其他列名称结束样式
	private static final String font_behind = "}\n</font>";
	//th结束标签
	private static final String th_behind = "\n</th>";
	//加载更多div开始样式
	private static final String load_more_div_id = "\n<div style = '\"padding-top:1px\"' id=''{";
	//加载更多div开始样式
	private static final String load_more_div_before = "\n<div class=''loadMoreDiv_cls''><a href=''javascript:void(0)'' onclick='\"	queryMore('{";		
    //加载更多onclick事件
	private static final String load_more_onclick_param = "}','{";
	//加载更多div结束样式
    private static final String load_more_div_end = "}');\"'>"+DataSearchConstantUtil.LOAD_MORE+"</a></div>";
//    //交易趋势同环比按钮
//	private static final String thb_a_head = "\n<div  style='\"padding-top: 57px;padding-left:800px;z-index:1000;position: absolute;\"'> <a class='\"trendThb_cls\"' onclick='\"trendThbAnalysis('{";
//	 //交易趋势同环比onclick参数
//	private static final String thb_a_onclick_param = "}');\"' title=''"+DataSearchConstantUtil.THB_TITLE+"''></a></div>";

	/**
	 * 动态生成交易趋势报表汇总字段
	 * @param country
	 * @param map
	 * @return
	 */
	public static <T>String fmtCpTradeHtml(String country,Map reportFieldMap,Map resultFieldMap) 
	{
		//log.info("进入ReportSummaryFieldUtil类的fmtCpTradeHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//获取产品交易趋势报告配置内容
		String cpTrade = reportFieldMap.get(country+DataSearchConstantUtil._CP_TRADE).toString();
		String [] cpTradeArray = new String []{};
		if(cpTrade != null && !"".equals(cpTrade.trim()))
		{
			cpTradeArray = cpTrade.split(",");
		}
		
		//html中的下标
		int index = 0;
		//参数列表
		List<String> paramList = new ArrayList<String>();
		buf.append(div_id).append(index++).append(div_head_behind);
		paramList.add("cp_trade_div");
		for(int i=0;i<cpTradeArray.length;i++)
		{
			//单海关编码交易趋势报告展示同环比按钮
			if(DataSearchConstantUtil.QS .equals(cpTradeArray[i]) && !isMutiHscode())
			{
//				buf.append(thb_a_head).append(index++).append(thb_a_onclick_param);
//				paramList.add("'"+DataSearchConstantUtil.QS+"'");
			}
			
			buf.append(div_reportid).append(index++).append(div_head_behind).append(div_chartid).append(index++).append(div_head_behind)
			.append(div_behind).append(div_chartid).append(index++).append(div_head_behind).append(div_behind).append(table_id).append(index++)
			.append(table_head_behind).append(thead_head).append(tr_head);
			paramList.add(cpTradeArray[i]+"_div");
			paramList.add(cpTradeArray[i]+"_mixchart");
			paramList.add(cpTradeArray[i]+"_piechart");
			paramList.add(cpTradeArray[i]+"_table");
			//Change DataSearchConstantUtil.FIELDNAME---->.FIELDNAMEEN
			String [] fieldname = resultFieldMap.get(country+"_"+cpTradeArray[i]+DataSearchConstantUtil.FIELDNAMEEN).toString().split(",");
			String [] fieldid = resultFieldMap.get(country+"_"+cpTradeArray[i]+DataSearchConstantUtil.FIELDID).toString().split(",");
			for(int j=0;j<fieldname.length ; j++)
			{
				buf.append(th_head).append(index++);
				//Below do some language change issue 
				//if("隐藏列".equals(fieldname[j]))
				if("Hide Columns".equals(fieldname[j]))
				{
					buf.append(th_hide_Field);
				}else
				{
					//不包含隐藏列如果数据表格中的列为5列
					if(fieldname.length-1 ==5)
					{
					//	//if("报表分析".equals(fieldname[j]))!
						if("Statement Analysis".equals(fieldname[j]))
							
						{
							if(isMutiHscode())
							{
								buf.append(th_Five_mutihscode_functionField);
							}
							else if(DataSearchConstantUtil.QS .equals(cpTradeArray[i]))
							{
								buf.append(th_Five_qs_functionField);
							}else
							{
								buf.append(th_Five_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Five_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Five_Field);
						}
					}
					//4列
					else if(fieldname.length-1 ==4){
						
						//if("报表分析".equals(fieldname[j]))
						if("Statement Analysis".equals(fieldname[j]))
							
						{
							if(isMutiHscode())
							{
								buf.append(th_Four_mutihscode_functionField);
							}
							else if(DataSearchConstantUtil.QS .equals(cpTradeArray[i]))
							{
								buf.append(th_Four_qs_functionField);
							}else
							{
								buf.append(th_Four_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Four_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Four_Field);
						}
						
					}
					//不包含隐藏列如果数据表格中的列为6列
					else if(fieldname.length-1 ==6)
					{
			//			if("报表分析".equals(fieldname[j]))
						if("Statement Analysis".equals(fieldname[j]))
							
						{
							if(isMutiHscode())
							{
								buf.append(th_Six_mutihscode_functionField);
							}
							else if(DataSearchConstantUtil.QS .equals(cpTradeArray[i]))
							{
								buf.append(th_Six_qs_functionField);
							}else
							{
								buf.append(th_Six_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Six_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Six_Field);
						}
					}
					//不包含隐藏列如果数据表格中的列为7列
					else if(fieldname.length-1 ==7)
					{
						//if("报表分析".equals(fieldname[j]))
						if("Statement Analysis".equals(fieldname[j]))
							
						{
							if(isMutiHscode())
							{
								buf.append(th_Seven_mutihscode_functionField);
							}else if(DataSearchConstantUtil.QS .equals(cpTradeArray[i]))
							{
								buf.append(th_Seven_qs_functionField);
							}else
							{
								buf.append(th_Seven_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Seven_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Seven_Field);
						}
					}
					//不包含隐藏列如果数据表格中的列为8列
					else if(fieldname.length-1 ==8)
					{
						//if("报表分析".equals(fieldname[j]))
						if("Statement Analysis".equals(fieldname[j]))

						{
							if(isMutiHscode())
							{
								buf.append(th_Eight_mutihscode_functionField);
							}else if(DataSearchConstantUtil.QS .equals(cpTradeArray[i]))
							{
								buf.append(th_Eight_qs_functionField);
							}else
							{
								buf.append(th_Eight_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Eight_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Eight_Field);
						}
					}
				}
				buf.append(font_head).append(index++).append(font_behind).append(th_behind);
				paramList.add(fieldid[j]);
				paramList.add(fieldname[j]);
			}
			buf.append(tr_behind).append(thead_behind).append(table_behind).append(load_more_div_id).append(index++)
			.append(div_head_behind).append(load_more_div_before).append(index++).append(load_more_onclick_param).append(index++)
			.append(load_more_onclick_param).append(index++).append(load_more_div_end).append(div_behind).append(div_behind);
			paramList.add(cpTradeArray[i]+"_loadMore");
			paramList.add("'"+cpTradeArray[i]+"'");
			paramList.add("'"+"cp_trade"+"'");
			paramList.add("'"+fieldname[1]+"'");
		}
		buf.append(div_behind);
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
	//	log.info("生成的前段html语句为:"+html);
		return html;
	}
	
	/**
	 * 动态生成国别报表汇总字段
	 * @param country
	 * @param map
	 * @return
	 */
	public static <T>String fmtCpCountryHtml(String country,Map reportFieldMap,Map resultFieldMap) 
	{
		//log.info("进入ReportSummaryFieldUtil类的fmtCpCountryHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//获取国别报表配置内容
		String cpCountry = reportFieldMap.get(country+DataSearchConstantUtil._CP_COUNTRY).toString();
		String [] cpCountryArray = new String []{};
		if(cpCountry != null && !"".equals(cpCountry.trim()))
		{
			cpCountryArray = cpCountry.split(",");
		}
		
		//html中的下标
		int index = 0;
		//参数列表
		List<String> paramList = new ArrayList<String>();
		buf.append(div_id).append(index++).append(div_head_behind);
		paramList.add("cp_country_div");
		for(int i=0;i<cpCountryArray.length;i++)
		{
			buf.append(div_reportid).append(index++).append(div_head_behind).append(div_chartid).append(index++).append(div_head_behind)
			.append(div_behind).append(div_chartid).append(index++).append(div_head_behind).append(div_behind).append(table_id)
			.append(index++).append(table_head_behind).append(thead_head).append(tr_head);
			paramList.add(cpCountryArray[i]+"_div");
			paramList.add(cpCountryArray[i]+"_mixchart");
			paramList.add(cpCountryArray[i]+"_piechart");
			paramList.add(cpCountryArray[i]+"_table");
			//Change DataSearchConstantUtil.FIELDNAME---->.FIELDNAMEEN
			String [] fieldname = resultFieldMap.get(country+"_"+cpCountryArray[i]+DataSearchConstantUtil.FIELDNAMEEN).toString().split(",");
			String [] fieldid = resultFieldMap.get(country+"_"+cpCountryArray[i]+DataSearchConstantUtil.FIELDID).toString().split(",");
			for(int j=0;j<fieldname.length ; j++)
			{
				buf.append(th_head).append(index++);
			//	if("隐藏列".equals(fieldname[j]))
				if("Hide Columns".equals(fieldname[j]))
					
				{
					buf.append(th_hide_Field);
				}else
				{
					//不包含隐藏列如果数据表格中的列为5列
					if(fieldname.length-1 ==5)
					{
						 //if("报表分析".equals(fieldname[j]))
						if("Statement Analysis".equals(fieldname[j]))
						{
							if(isMutiHscode())
							{
								buf.append(th_Five_mutihscode_functionField);
							}else
							{
								buf.append(th_Five_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Five_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Five_Field);
						}
					}
					//不包含隐藏列如果数据表格中的列为6列
					else if(fieldname.length-1 ==6)
					{
						//if("报表分析".equals(fieldname[j]))!
						if("Statement Analysis".equals(fieldname[j]))

						{
							if(isMutiHscode())
							{
								buf.append(th_Six_mutihscode_functionField);
							}else
							{
								buf.append(th_Six_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Six_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Six_Field);
						}
					}
					//不包含隐藏列如果数据表格中的列为7列
					else if(fieldname.length-1 ==7)
					{
						//if("报表分析".equals(fieldname[j]))!
						if("Statement Analysis".equals(fieldname[j]))

						{
							if(isMutiHscode())
							{
								buf.append(th_Seven_mutihscode_functionField);
							}else
							{
								buf.append(th_Seven_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Seven_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Seven_Field);
						}
					}
					//不包含隐藏列如果数据表格中的列为8列
					else if(fieldname.length-1 ==8)
					{
						 //if("报表分析".equals(fieldname[j]))!
						if("Statement Analysis".equals(fieldname[j]))

						{
							if(isMutiHscode())
							{
								buf.append(th_Eight_mutihscode_functionField);
							}else
							{
								buf.append(th_Eight_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Eight_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Eight_Field);
						}
					}
				}
				buf.append(font_head).append(index++).append(font_behind).append(th_behind);
				paramList.add(fieldid[j]);
				paramList.add(fieldname[j]);
			}
			buf.append(tr_behind).append(thead_behind).append(table_behind).append(load_more_div_id).append(index++)
			.append(div_head_behind).append(load_more_div_before).append(index++).append(load_more_onclick_param).append(index++)
			.append(load_more_onclick_param).append(index++).append(load_more_div_end).append(div_behind).append(div_behind);
			paramList.add(cpCountryArray[i]+"_loadMore");
			paramList.add("'"+cpCountryArray[i]+"'");
			paramList.add("'"+"cp_country"+"'");
			paramList.add("'"+fieldname[1]+"'");
		}
		buf.append(div_behind);
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}
	
	/**
	 * 动态生成港口报表汇总字段
	 * @param country
	 * @param map
	 * @return
	 */
	public static <T>String fmtCpPortHtml(String country,Map reportFieldMap,Map resultFieldMap) 
	{
		//log.info("进入ReportSummaryFieldUtil类的fmtCpPortHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//获取港口报表配置内容
		String cpPort = reportFieldMap.get(country+DataSearchConstantUtil._CP_PORT).toString();
		String [] cpPortArray = new String []{};
		if(cpPort != null && !"".equals(cpPort.trim()))
		{
			cpPortArray = cpPort.split(",");
		}
		
		//html中的下标
		int index = 0;
		//参数列表
		List<String> paramList = new ArrayList<String>();
		buf.append(div_id).append(index++).append(div_head_behind);
		paramList.add("cp_port_div");
		for(int i=0;i<cpPortArray.length;i++)
		{
			buf.append(div_reportid).append(index++).append(div_head_behind).append(div_chartid).append(index++).append(div_head_behind)
			.append(div_behind).append(div_chartid).append(index++).append(div_head_behind).append(div_behind).append(table_id)
			.append(index++).append(table_head_behind).append(thead_head).append(tr_head);
			paramList.add(cpPortArray[i]+"_div");
			paramList.add(cpPortArray[i]+"_mixchart");
			paramList.add(cpPortArray[i]+"_piechart");
			paramList.add(cpPortArray[i]+"_table");
			//Change DataSearchConstantUtil.FIELDNAME---->.FIELDNAMEEN
			String [] fieldname = resultFieldMap.get(country+"_"+cpPortArray[i]+DataSearchConstantUtil.FIELDNAMEEN).toString().split(",");
			String [] fieldid = resultFieldMap.get(country+"_"+cpPortArray[i]+DataSearchConstantUtil.FIELDID).toString().split(",");
			for(int j=0;j<fieldname.length ; j++)
			{
				buf.append(th_head).append(index++);
				//if("隐藏列".equals(fieldname[j]))
				if("Hide Columns".equals(fieldname[j]))
					
				{
					buf.append(th_hide_Field);
				}else
				{
					//不包含隐藏列如果数据表格中的列为5列
					if(fieldname.length-1 ==5)
					{
						//if("报表分析".equals(fieldname[j]))!
						if("Statement Analysis".equals(fieldname[j]))

						{
							if(isMutiHscode())
							{
								buf.append(th_Five_mutihscode_functionField);
							}else
							{
								buf.append(th_Five_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Five_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Five_Field);
						}
					}
					//不包含隐藏列如果数据表格中的列为6列
					else if(fieldname.length-1 ==6)
					{
						//if("报表分析".equals(fieldname[j]))!
						if("Statement Analysis".equals(fieldname[j]))

						{
							if(isMutiHscode())
							{
								buf.append(th_Six_mutihscode_functionField);
							}else
							{
								buf.append(th_Six_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Six_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Six_Field);
						}
					}
					//不包含隐藏列如果数据表格中的列为7列
					else if(fieldname.length-1 ==7)
					{
						//if("报表分析".equals(fieldname[j]))!
						if("Statement Analysis".equals(fieldname[j]))

						{
							 if(isMutiHscode())
							{
								buf.append(th_Seven_mutihscode_functionField);
							}else
							{
								buf.append(th_Seven_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Seven_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Seven_Field);
						}
					}
					//不包含隐藏列如果数据表格中的列为8列
					else if(fieldname.length-1 ==8)
					{
						//if("报表分析".equals(fieldname[j]))!
						if("Statement Analysis".equals(fieldname[j]))

						{
							if(isMutiHscode())
							{
								buf.append(th_Eight_mutihscode_functionField);
							}else
							{
								buf.append(th_Eight_functionField);
							}
						}
						//比率字段不需要排序
						else if("percentage".equals(fieldid[j]))
						{
							buf.append(th_Eight_Field.replaceAll("sortable='\"true\"'", ""));
						}else
						{
							buf.append(th_Eight_Field);
						}
					}
				}
				buf.append(font_head).append(index++).append(font_behind).append(th_behind);
				paramList.add(fieldid[j]);
				paramList.add(fieldname[j]);
			}
			buf.append(tr_behind).append(thead_behind).append(table_behind).append(load_more_div_id).append(index++)
			.append(div_head_behind).append(load_more_div_before).append(index++).append(load_more_onclick_param).append(index++)
			.append(load_more_onclick_param).append(index++).append(load_more_div_end).append(div_behind).append(div_behind);
			paramList.add(cpPortArray[i]+"_loadMore");
			paramList.add("'"+cpPortArray[i]+"'");
			paramList.add("'"+"cp_port"+"'");
			paramList.add("'"+fieldname[1]+"'");
		}
		buf.append(div_behind);
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}
	
	/**
	 * 获取需要生成数据表格的table的id和图标所在div的id
	 * @param type
	 * @param reportFieldMap
	 * @return Map
	 */
	public static Map getTableAndChartId(String type,String country,Map reportFieldMap)
	{
		Map map = new HashMap();
		//获取产品交易趋势报告配置内容
		String cpTrade = reportFieldMap.get(country+DataSearchConstantUtil._CP_TRADE).toString();
		//获取国别报表配置内容
		String cpCountry = reportFieldMap.get(country+DataSearchConstantUtil._CP_COUNTRY).toString();
		//获取港口报表配置内容
		String cpPort = reportFieldMap.get(country+DataSearchConstantUtil._CP_PORT).toString();
		
		//交易趋势报告数组
		String [] cpTradeArray = new String []{};
		if(cpTrade != null && !"".equals(cpTrade.trim()))
		{
			cpTradeArray = cpTrade.split(",");
		}
		//国别报表数组
		String [] cpCountryArray = new String []{};
		if(cpCountry != null && !"".equals(cpCountry.trim()))
		{
			cpCountryArray = cpCountry.split(",");
		}
		//港口报表数组
		String [] cpPortArray = new String []{};
		if(cpPort != null && !"".equals(cpPort.trim()))
		{
			cpPortArray = cpPort.split(",");
		}
		
		StringBuffer tableId = new StringBuffer();
		StringBuffer chartDivId = new StringBuffer();
		if(DataSearchConstantUtil.CP_TRADE.equals(type))
		{
			for(int i=0;i<cpTradeArray.length;i++)
			{
				tableId.append(cpTradeArray[i]+"_table"+",");
				chartDivId.append(cpTradeArray[i]+"_reportchart"+",");
			}
		}else if(DataSearchConstantUtil.CP_PORT.equals(type))
		{
			for(int i=0;i<cpPortArray.length;i++)
			{
				tableId.append(cpPortArray[i]+"_table"+",");
				chartDivId.append(cpPortArray[i]+"_reportchart"+",");
			}
		}else if(DataSearchConstantUtil.CP_COUNTRY.equals(type))
		{
			for(int i=0;i<cpCountryArray.length;i++)
			{
				tableId.append(cpCountryArray[i]+"_table"+",");
				chartDivId.append(cpCountryArray[i]+"_reportchart"+",");
			}
		}
		map.put("tableId", tableId.toString());
		map.put("chartDivId", chartDivId.toString());
		return map;
	}
	
	/**
	 * 判断是否为多海关编码
	 * @return
	 */
    private static boolean isMutiHscode()
    {
    	String queryKey = (String) MapModel.getMap().get("queryKey");
    	String queryValue = (String) MapModel.getMap().get("queryValue");
    	String[] KeyArray = queryKey.split(";");
    	String[] valueArray = queryValue.split(";");
    	for(int i=0;i<KeyArray.length;i++)
    	{
    		if(DataSearchConstantUtil.HSCODE_LUCENE.equals(KeyArray[i]))
    		{
    			if(valueArray[i].split(",").length>1)
    			{
    				return true;
    			}
    		}
    	}
        return false;
    }
}