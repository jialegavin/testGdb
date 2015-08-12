package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 动态生成各国家搜索结果字段列的数据表格工具类
 * @author honghao
 * @date 	2015-04-13
 * @version 标准版
 */ 
public class ResultFieldUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(ResultFieldUtil.class);
	//table头部样式
	private static final String table_head="<table id='\"resultFieldTable\"' style='\"height:855px;width:1210px;\"'"+ ">";
	//table头部样式
	private static final String favorites_table_head="<table id='\"resultFieldTable\"' style='\"height:300px;width:1000px;\"'"+ ">";
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
	//鼠标移动上去显示对应的title
	private static final String th_formatTitle = "}'' Width='\"161\"' style='\"height: 30px\"' align='\"center\"' formatter='\"formatTitle\"'>";
	//鼠标移动上去显示对应的百度搜索的列
	private static final String th_formatSearch = "}'' Width='\"161\"' style='\"height: 30px\"' align='\"left\"' formatter='\"formatSearch\"'>";
	//其余的列的样式
	private static final String th_normalField = "}'' Width='\"161\"' style='\"height: 30px\"' align='\"center\"' formatter='\"formatOtherSearch\"' sortable='\"true\"'>";
	
	//针对显示字段小于7的国家处理宽度问题
	//鼠标移动上去显示对应的title
	private static final String thw_formatTitle = "}'' Width='\"187\"' style='\"height: 30px\"' align='\"center\"' formatter='\"formatTitle\"'>";
	//鼠标移动上去显示对应的百度搜索的列
	private static final String thw_formatSearch = "}'' Width='\"187\"' style='\"height: 30px\"' align='\"left\"' formatter='\"formatSearch\"'>";
	//其余的列的样式
	private static final String thw_normalField = "}'' Width='\"187\"' style='\"height: 30px\"' align='\"center\"' formatter='\"formatOtherSearch\"'  sortable='\"true\"'>";
		
	
	//查看详情列
	private static final String th_viewdetail = "\n<th data-options='\"field:''viewdetail'',width:80,formatter:viewdetail\"' align='\"center\"'>";
	//选择字段列名称开始样式
	private static final String selectfiled_font_head = "\n<a onclick='\"openSelectFieldDialog()\"'><font style='\"font-size: 14px;font-weight: bold;\"'>{";
	//选择字段列名称结束样式
	private static final String selectfiled_font_behind = "}</font></a>";
	//其他列名称开始样式
	private static final String font_head = "\n<font style='\"font-size: 14px;font-weight: bold;\"'>{";
	//其他列名称结束样式
	private static final String font_behind = "}</font>";
	//th结束标签
	private static final String th_behind = "\n</th>";
	
	/**
	 * 动态生成各国家搜索结果字段列的数据表格
	 * @param country
	 * @param map
	 * @param selectFieldId 页面选择的字段id
	 * @param selectFieldName 页面选择的字段名
	 * @return
	 */
	public static <T>String fmtHtml(String country,Map map,String selectFieldId,String selectFieldName,String intoType) 
	{
		
		//log.info("进入ResultFieldUtil类的fmtHtml()方法");187
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//查询结果展示字段中文名
	//	String [] displayZhName = map.get(country+DataSearchConstantUtil.RESULTDISPLAYZHNAME).toString().split(",");
		// query show english
		String [] displayEnName = map.get(country+DataSearchConstantUtil.RESULTDISPLAYENNAME).toString().split(",");
		//查询结果展示字段id名
		String [] displayFieldId = map.get(country+DataSearchConstantUtil.RESULTDISPLAYFIELD).toString().split(",");
		//需要鼠标移动上去显示对应的title的列的id名
		String [] formatTitle = map.get(country+DataSearchConstantUtil.FORMATTITLE).toString().split(",");
		//需要鼠标移动上去显示对应的百度搜索的列的id名
		String [] formatSearch = map.get(country+DataSearchConstantUtil.FORMATSEARCH).toString().split(",");
		//html中的下标
		int index = 0;
		//参数列表
		List<String> paramList = new ArrayList<String>();
	
		if(!"".equals(intoType) && intoType.equals("myFavorites"))
		{
			buf.append(favorites_table_head).append(thead_head).append(tr_head);
		}
		else
		{
			buf.append(table_head).append(thead_head).append(tr_head);
		}
		
		//前台页面选择字段选中的列
		if(selectFieldId !=null && selectFieldId.trim() !="")
		{
			displayFieldId = selectFieldId.split(",");
			//displayZhName = selectFieldName.split(",");
			displayEnName = selectFieldName.split(",");
			if(!Arrays.asList(displayFieldId).contains(DataSearchConstantUtil.VIEWDETAIL))
			{
				displayFieldId = (DataSearchConstantUtil.VIEWDETAIL+","+selectFieldId).split(",");
				//displayZhName = (DataSearchConstantUtil.VIEWDETAIL_ZH+","+selectFieldName).split(",");
				displayEnName = (DataSearchConstantUtil.VIEWDETAIL_ZH+","+selectFieldName).split(",");
			}
		}
		//拼出显示列的html语句
		for(int i=0;i<displayFieldId.length;i++)
		{
			//鼠标悬浮可以查看详细的列
			if(Arrays.asList(formatTitle).contains(displayFieldId[i]))
			{
				buf.append(th_head).append(index++).append((displayFieldId.length-1)<7?thw_formatTitle:th_formatTitle);
				paramList.add(displayFieldId[i]);
			}
			//添加百度搜索的列
			else if(Arrays.asList(formatSearch).contains(displayFieldId[i]))
			{
				buf.append(th_head).append(index++).append((displayFieldId.length-1)<7?thw_formatSearch:th_formatSearch);
				paramList.add(displayFieldId[i]);
			}else if(DataSearchConstantUtil.VIEWDETAIL.equals(displayFieldId[i]))
			{
				buf.append(th_viewdetail);
			}else
			{
				buf.append(th_head).append(index++).append((displayFieldId.length-1)<7?thw_normalField:th_normalField);
				paramList.add(displayFieldId[i]);
			}
			if(DataSearchConstantUtil.VIEWDETAIL.equals(displayFieldId[i]))
			{
				buf.append(selectfiled_font_head).append(index++).append(selectfiled_font_behind).append(th_behind);
			}else
			{
				buf.append(font_head).append(index++).append(font_behind).append(th_behind);
			}
			//paramList.add(displayZhName[i]);
			paramList.add(displayEnName[i]);
		}
		
		buf.append(tr_behind).append(thead_behind).append(table_behind);
		
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}

}