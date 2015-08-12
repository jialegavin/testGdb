package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 生成贸易情报深度挖取页面工具类
 * @author honghao
 * @date 	2015-05-11
 * @version 标准版
 */ 
public class DepthDiggingUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(DepthDiggingUtil.class);
	//div报告 id名
	private static final String div_reportid="\n<div style='\"padding-top:4px;\"'  id=''{";
	//div 图标id名
	private static final String div_chartid="\n<div style='\"padding-top:4px;\"'  id=''{";
	//十大进口商出口商标题样式
	private static final String div_jks_cks_head="\n<div style='\"margin:0;padding:4px 0 0 0px;border-bottom: 1px solid #1369C0;background-color:#1369C0;font-size:16px;color:#FFFFFF;width:866px;height:28px;text-align:left\"'>";
	//十大进口商出口商标题文字样式
	private static final String span_jks_cks_head ="\n<span style=''font-size:16px;color:#FFFFFF;''>{";
	//span结束标签
	private static final String span_behind = "}\n</span>";
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
	//2列数据表格的样式
	private static final String th_two_Field = "}'' Width='\"430\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//3列数据表格的样式
	private static final String th_three_Field = "}'' Width='\"288\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//4列数据表格的样式
	private static final String th_four_Field = "}'' Width='\"216\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//5列数据表格的样式
	private static final String th_five_Field = "}'' Width='\"173\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//6列数据表格的样式
	private static final String th_six_Field = "}'' Width='\"144\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//列名称开始样式
	private static final String font_head = "\n<font style='\"font-size: 14px;font-weight: bold;\"'>{";
	//红色文字展示样式
	private static final String font_red_head = "\n<font style='\"font-size: 14px;font-weight: bold;color:red\"'>{";
	//其他列名称结束样式
	private static final String font_behind = "}\n</font>";
	//th结束标签
	private static final String th_behind = "\n</th>";
	
	/**
	 * 生成贸易情报深度挖取页面
	 * @param country
	 * @param resultFieldMap
	 * @param selectedFieldZhName:深度挖取分析的字段中文名
	 * @param selectedFieldValue:深度挖取分析的字段值
	 * @param selectedReportType:深度挖取当前的报告类型
	 * @return
	 */
	public static <T>String fmtHtml(String country,Map resultFieldMap,String selectedFieldZhName,String selectedFieldValue,String selectedReportType) 
	{
		log.info("进入DepthDiggingUtil类的fmtHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//参数列表
		List<String> paramList = new ArrayList<String>();
		//html中的下标
		int index = 0;
		//获取产品交易趋势报告配置内容
		String [] reportType = resultFieldMap.get(country+DataSearchConstantUtil.DEPTHDIGGING_REPORTTYPE).toString().split(",");
		
		buf.append(font_red_head).append(index++).append(font_behind);
		paramList.add(selectedFieldZhName+":"+selectedFieldValue);
		for(int i =0;i<reportType.length;i++)
		{
			//不钻取自身的报告类型
			if(!selectedReportType.equals(reportType[i]))
			{
				//字段id
				String [] fieldId = resultFieldMap.get(country+"_"+reportType[i]+DataSearchConstantUtil.FIELDID).toString().split(",");
				//字段中文名
				String [] fieldName = resultFieldMap.get(country+"_"+reportType[i]+DataSearchConstantUtil.FIELDNAME).toString().split(",");
				buf.append(div_reportid).append(index++).append(div_head_behind).append(div_chartid).append(index++)
				.append(div_head_behind).append(div_behind).append(div_chartid).append(index++).append(div_head_behind)
				.append(div_behind);
				paramList.add(reportType[i]+"_depthdigging_div");
				paramList.add(reportType[i]+"_depthdigging_mixchart");
				paramList.add(reportType[i]+"_depthdigging_piechart");
				if(DataSearchConstantUtil.JKS.equals(reportType[i]))
				{
					buf.append(div_jks_cks_head).append(span_jks_cks_head).append(index++).append(span_behind).append(div_behind);
					paramList.add(DataSearchConstantUtil.JKS_ZH);
				}else if(DataSearchConstantUtil.CKS.equals(reportType[i]))
				{
					buf.append(div_jks_cks_head).append(span_jks_cks_head).append(index++).append(span_behind).append(div_behind);
					paramList.add(DataSearchConstantUtil.CKS_ZH);
				}
				buf.append(table_id).append(index++).append(table_head_behind).append(thead_head).append(tr_head);
				paramList.add(reportType[i]+"_depthdigging_table");
				for(int j=0;j<fieldId.length;j++)
				{
					//排除配置文件中隐藏列和报表分析
					if(!"hideColumn".equals(fieldId[j]) && !"function".equals(fieldId[j]))
					{
						buf.append(th_head).append(index++);
						//2列数据表格样式除去报表分析，和隐藏列
						if(fieldId.length-2 ==2)
						{
							buf.append(th_two_Field);
						}
						//3列数据表格样式除去报表分析，和隐藏列
						if(fieldId.length-2 ==3)
						{
							buf.append(th_three_Field);
						}
						//4列数据表格样式
						else if(fieldId.length-2 ==4)
						{
							buf.append(th_four_Field);
						}
						//5列数据表格样式
						else if(fieldId.length-2 ==5)
						{
							buf.append(th_five_Field);
						}
						//5列
						else if(fieldId.length-2 ==6)
						{
							buf.append(th_six_Field);
						}
						buf.append(font_head).append(index++).append(font_behind).append(th_behind);
						paramList.add(fieldId[j]);
						paramList.add(fieldName[j]);
					}
				}
				buf.append(tr_behind).append(thead_behind).append(table_behind).append(div_behind);
			}
		}
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}
}