package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 动态生成各国家搜索结果选项卡工具类
 * @author honghao
 * @date 	2015-05-04
 * @version 标准版
 */ 
public class TabGenerateUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(TabGenerateUtil.class);
	//ul开始标签
	private static final String ul_start = "<ul>";
	//li标签id名
	private static final String li_id = "\n<li id=''{";
	//li标签onclick事件
	private static final String li_onclick_param1 = "}'' name=''resultTab'' onclick='\"setTab(''tab'','{";
	//li标签onclick事件
	private static final String li_onclick_param2 = "}','{";
	//li标签搜索结果方法
	private static final String li_searchResult = "}');searchResultTab();\"' style='\"background-image: url(''/gbdbas/static/img/datasearch/tabout.png'');width:128px;\"'  class=''hover''>";
	//li标签产品趋势报告方法
	private static final String li_cp_trade = "}');cp_trade_report();\"' style='\"background-image: url(''/gbdbas/static/img/datasearch/tabbbefore.png'');width:128px; \"'>";
	//li标签港口趋势报告方法
	private static final String li_cp_port = "}');cp_port_report();\"' style='\"background-image: url(''/gbdbas/static/img/datasearch/tabbbefore.png'');width:128px; \"'>";
	//li标签国别趋势报告方法
	private static final String li_cp_country = "}');cp_country_report();\"' style='\"background-image: url(''/gbdbas/static/img/datasearch/tabbbefore.png'');width:128px; \"'>";
	//a标签id名
	private static final String a_id = "\n<a id=''{";
	//a标签style样式
	private static final String a_style = "}'' style=''color: #1369c0''>{";
	//a标签nostyle样式
	private static final String a_nostyle = "}''>{";
	//a标签结束标签
	private static final String a_end = "}</a>";
	//li标签结束标签
	private static final String li_end = "\n</li>";
	//ul标签结束标签
	private static final String ul_end  = "\n</ul>";
	
	/**
	 * 动态生成各国家搜索结果选项卡
	 * @param country
	 * @param map
	 * @return
	 */
	public static <T>String fmtHtml(String country,Map map) 
	{
		
		//log.info("进入TabGenerateUtil类的fmtHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//获取产品交易趋势报告配置内容
		String cpTrade = map.get(country+DataSearchConstantUtil._CP_TRADE).toString();
		//获取国别报表配置内容
		String cpCountry = map.get(country+DataSearchConstantUtil._CP_COUNTRY).toString();
		//获取港口报表配置内容
		String cpPort = map.get(country+DataSearchConstantUtil._CP_PORT).toString();
		
		int tabNum = 1;
		if(cpTrade != null && !"".equals(cpTrade.trim()))
		{
			tabNum++;
		}
		if(cpCountry != null && !"".equals(cpCountry.trim()))
		{
			tabNum++;
		}
		if(cpPort != null && !"".equals(cpPort.trim()))
		{
			tabNum++;
		}
		//选项卡的id数
		int tabIdNum = 1;
		//html中的下标
		int index = 0;
		//参数列表
		List<String> paramList = new ArrayList<String>();
		buf.append(ul_start).append(li_id).append(index++).append(li_onclick_param1).append(index++).append(li_onclick_param2)
		.append(index++).append(li_searchResult).append(a_id).append(index++).append(a_style).append(index++).append(a_end).append(li_end);
		paramList.add("tab"+tabIdNum);
		paramList.add(String.valueOf(tabIdNum));
		paramList.add(String.valueOf(tabNum));
		paramList.add("tab_a"+tabIdNum);
		paramList.add(DataSearchConstantUtil.SEARCH_RESULT);
		tabIdNum = 3;
		//国别报表标签页
		if(cpCountry != null && !"".equals(cpCountry.trim()))
		{
			buf.append(li_id).append(index++).append(li_onclick_param1).append(index++).append(li_onclick_param2)
			.append(index++).append(li_cp_country).append(a_id).append(index++).append(a_nostyle).append(index++).append(a_end).append(li_end);
			paramList.add("tab"+tabIdNum);
			paramList.add(String.valueOf(tabIdNum));
			paramList.add(String.valueOf(tabNum));
			paramList.add("tab_a"+tabIdNum);
			paramList.add(DataSearchConstantUtil.COUNTRY_REPORT);
			tabIdNum++;
		}
		//港口报表标签页
		if(cpPort != null && !"".equals(cpPort.trim()))
		{
			buf.append(li_id).append(index++).append(li_onclick_param1).append(index++).append(li_onclick_param2)
			.append(index++).append(li_cp_port).append(a_id).append(index++).append(a_nostyle).append(index++).append(a_end).append(li_end);
			paramList.add("tab"+tabIdNum);
			paramList.add(String.valueOf(tabIdNum));
			paramList.add(String.valueOf(tabNum));
			paramList.add("tab_a"+tabIdNum);
			paramList.add(DataSearchConstantUtil.PORT_REPORT);
			tabIdNum++;
		}
		//产品趋势分析报表标签页
		if(cpTrade != null && !"".equals(cpTrade.trim()))
		{
			buf.append(li_id).append(index++).append(li_onclick_param1).append(index++).append(li_onclick_param2)
			.append(index++).append(li_cp_trade).append(a_id).append(index++).append(a_nostyle).append(index++).append(a_end).append(li_end);
			paramList.add("tab"+2);
			paramList.add(String.valueOf(2));
			paramList.add(String.valueOf(tabNum));
			paramList.add("tab_a"+2);
			paramList.add(DataSearchConstantUtil.PRODUCT_TREAD_REPORT);
			tabIdNum++;
		}
		buf.append(ul_end);
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;

	}
}