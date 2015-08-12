package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 生成多海关编码备注页面工具类
 * @author honghao
 * @date 	2015-05-14
 * @version 标准版
 */ 
public class MutiHscodeUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(MutiHscodeUtil.class);
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
	private static final String th_two_Field = "}'' Width='\"433\"' style='\"height: 30px\"' align='\"center\"'  sortable='\"true\"'>";
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
	//其他列名称结束样式
	private static final String font_behind = "}\n</font>";
	//th结束标签
	private static final String th_behind = "\n</th>";
	
	/**
	 * 生成多海关编码备注页面
	 * @param country
	 * @param resultFieldMap
	 * @param reportType:选择对比所在的报告类型
	 * @return
	 */
	public static <T>String fmtHtml(String country,Map resultFieldMap, String reportType) 
	{
		log.info("进入MutiHscodeUtil类的fmtHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//参数列表
		List<String> paramList = new ArrayList<String>();
		//html中的下标
		int index = 0;
		//该报告类型下的字段id
		String [] fieldId = resultFieldMap.get(country+DataSearchConstantUtil.MUTI_HSCODE_FIELDID).toString().split(",");
		//该报告类型下的字段名称
		String [] fieldName = resultFieldMap.get(country+DataSearchConstantUtil.MUTI_HSCODE_FIELDZHNAME).toString().split(",");
	
		buf.append(table_id).append(index++).append(table_head_behind).append(thead_head).append(tr_head);
		paramList.add(reportType+"_mutihscode_table");
		
		for(int j=0;j<fieldName.length;j++)
		{
			buf.append(th_head).append(index++);
			//2列数据表格样式
			if(fieldName.length ==2)
			{
				buf.append(th_two_Field);
			}
			//3列数据表格样式
			if(fieldName.length ==3)
			{
				buf.append(th_three_Field);
			}
			//4列数据表格样式
			else if(fieldName.length ==4)
			{
				buf.append(th_four_Field);
			}
			//5列数据表格样式
			else if(fieldName.length ==5)
			{
				buf.append(th_five_Field);
			}
			//6列数据表格样式
			else if(fieldName.length ==6)
			{
				buf.append(th_six_Field);
			}
			buf.append(font_head).append(index++).append(font_behind).append(th_behind);
			paramList.add(fieldId[j]);
			paramList.add(fieldName[j]);
		}
		buf.append(tr_behind).append(thead_behind).append(table_behind);
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}
}