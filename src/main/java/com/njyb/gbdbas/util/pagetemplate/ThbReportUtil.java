package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 生成同环比报表页面工具类
 * @author honghao
 * @date 	2015-05-15
 * @version 标准版
 */ 
public class ThbReportUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(ThbReportUtil.class);
	//div 图标id名
	private static final String div_id="\n<div id=''{";
	//div 图标id名
	private static final String div_chartid="\n<div style='\"padding-top:4px;\"'  id=''{";
	//div 头部其他样式
	private static final String div_head_behind="}''>";
	//div结束标签
	private static final String div_behind="\n</div>";
	//table头部id名
	private static final String table_id="\n<table id=''{";
	//table头部其他样式
	private static final String table_head_behind = "}'' style='\"width:866px;overflow:hidden;\"' >";
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
	private static final String th_two_Field = "}'' Width='\"432\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//3列数据表格的样式
	private static final String th_three_Field = "}'' Width='\"288\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//4列数据表格的样式
	private static final String th_four_Field = "}'' Width='\"216\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
	//5列数据表格的样式
	private static final String th_five_Field = "}'' Width='\"173\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
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
	 * have changed ZHNAME --> ENNAME
	 */
	public static <T>String fmtHtml(HttpServletRequest request,String country,Map resultFieldMap) 
	{
		log.info("进入ThbReportUtil类的fmtHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//参数列表
		List<String> paramList = new ArrayList<String>();
		//html中的下标
		int index = 0;
		//同环比报告类型
		String [] thbReport = resultFieldMap.get(country+DataSearchConstantUtil.THB_SHOWTYPE).toString().split(",");
		for(String type : thbReport)
		{
			buf.append(div_id).append(index++).append(div_head_behind).append(div_chartid).append(index++).append(div_head_behind).append(div_behind).append(div_chartid)
			.append(index++).append(div_head_behind).append(div_behind).append(table_id).append(index++)
			.append(table_head_behind).append(thead_head).append(tr_head);
			paramList.add(type+"_thb_div");
			paramList.add(type+"_thb_mixchart");
			paramList.add(type+"_thb_piechart");
			paramList.add(type+"_thb_table");
			String[] thbFieldId = new String[]{};
			String[] thbFieldName = new String[]{};
			//月度环比月度同比需要展示的数据表格字段
			if(DataSearchConstantUtil.MOM.equals(type) || DataSearchConstantUtil.MYOY.equals(type))
			{
				thbFieldId = resultFieldMap.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MOM_MYOY_FIELDID)).toString().split(",");
				thbFieldName = resultFieldMap.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MOM_MYOY_FIELDENNAME)).toString().split(",");
			}
			//年度同比需要展示的数据表格字段
			else if(DataSearchConstantUtil.YOY.equals(type))
			{
				thbFieldId = resultFieldMap.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.YOY_FIELDID)).toString().split(",");
				thbFieldName = resultFieldMap.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.YOY_FIELDENNAME)).toString().split(",");
			}
			for(int i =0;i<thbFieldName.length;i++)
			{
				buf.append(th_head).append(index++);
				//数据表格中的列为2列
				if(thbFieldName.length ==2)
				{
					buf.append(th_two_Field);
				}
				//数据表格中的列为3列
				if(thbFieldName.length ==3)
				{
					buf.append(th_three_Field);
				}
				//数据表格中的列为4列
				else if(thbFieldName.length ==4)
				{
					buf.append(th_four_Field);
				}
				//数据表格中的列为5列
				else if(thbFieldName.length ==5)
				{
					buf.append(th_five_Field);
				}
				buf.append(font_head).append(index++).append(font_behind).append(th_behind);
				paramList.add(thbFieldId[i]);
				paramList.add(thbFieldName[i]);
			}
			buf.append(tr_behind).append(thead_behind).append(table_behind).append(div_behind);
		}
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}
}