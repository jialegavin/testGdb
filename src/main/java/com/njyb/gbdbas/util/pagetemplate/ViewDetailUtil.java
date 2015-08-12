
package com.njyb.gbdbas.util.pagetemplate;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;

/**
 * 动态生成各国家查看详情页面的模板工具类
 * @author honghao
 * @date 	2015-03-17
 * @version 标准版
 */
public class ViewDetailUtil
{
	//log记录日志
    private static final Logger log = Logger.getLogger(ViewDetailUtil.class);
	//查看详情标题id
	private static final String  view_detail_titleId = "<p align='\"center\"' style='\"font-size: 18px;color: #ED5564;margin-top: 0px;color: #1369c0;font-weight:bold;\"' id=''{";
	//查看详情标题名称
	private static final String  view_detail_titleName = "}''>{";
	//p结束标签
	private static final String  p_end = "}\n</p>";
	//hr水平线
	private static final String  hr = "\n<hr style='\"border: 1px solid #cccccc;width: 100%;\"'/>";
	//查看详情标题表格头部样式
	private static final String  title_table_head = "<table style='\"width:100%;padding-bottom: 4px;\"'>\n<tr width='\"100%\"'>\n<td width='\"100%\"'>";
	//导出pdf按钮
	private static final String  export_pdf = "\n<a href='\"javascript:void(0)\"' onclick='\"detailExportPdf();\"' id='\"ch_exportpdf_button\"' class='\"exportPdf_cls\"'><font id='\"name_exportPDF\"' style=\"padding-left: 30px;\">导 出</font></a>";
	//打印按钮
	private static final String  print = "\n<a href='\"javascript:void(0)\"' onclick='\"detailPrintData();\"' id='\"ch_print_button\"' class='\"print_cls\"'><font id='\"name_exportPrint\"' style=\"padding-left: 30px;\">打 印</font></a>";
	//海关编码或者提单号
	private static final String  hscode_blnumber_zhnameid= "\n<span id=''{";
	private static final String  hscode_blnumber_zhnamevalue = "}'' class='\"btn_css\"' style='\"padding-left: 200px;\"'>{";
	private static final String  hscode_blnumber_fieldvalue	= "}\n</span>\n<span style='\"color: #ED5564;font-size: 16px;\"'>{";
	private static final String  hscode_blnumber_end = "}\n</span>";
	//翻译
	private static final String translate_div_head = "<div id='\"info\"' style='\"float: left;margin-top: -10px\"'  onclick='\"show(''language'')\"' > ";
	//翻译中文名对应标签id
	private static final String translate_zhname_id = "<span style='\"font-size: 16px;color: #ED5564;\"' id=''{";
	//翻译中文名对应value
	private static final String translate_zhname_value = "}''>{";
	//翻译中文名结束标签
	private static final String translate_zhname_end ="}</span>&nbsp;";
	//下三角图片
	private static final String translate_sanjiao = "\n<a href='\"javascript:void(0)\"'><img src='\"/gbdbas/static/img/datasearch/combo_arrow.png\"' /></a>&nbsp;&nbsp;";
	//上三角
	private static final String translate_shangsanjiao = "\n<div id='\"language\"' onmouseover='\"show(''language'')\"' onmouseout='\"hide(''language'')\"'><div id='\"img\"'>\n<img src='\"/gbdbas/static/img/datasearch/viewdetail/shangsanjiao.png\"' width='\"111\"' height='\"16\"' />\n</div>\n<div id='\"wenzi\"'>";
	//翻译下拉列表选择语言
	private static final String translate_select_language= "\n<a href='\"#\"'  onclick='\"translation(''chinese'');\"'><p id='\"name_chinese\"' onmousemove='\"Hover(''name_chinese'')\"' onmouseout='\"notHover(''name_chinese'')\"'>中文</p></a>"
																				          +"\n<a href='\"#\"'  onclick='\"translation(''english'');\"'><p id='\"name_english\"' onmousemove='\"Hover(''name_english'')\"' onmouseout='\"notHover(''name_english'')\"'>英文</p></a>"
																				          +"\n<a href='\"#\"'  onclick='\"translation(''russian'');\"'><p id='\"name_russian\"' onmousemove='\"Hover(''name_russian'')\"' onmouseout='\"notHover(''name_russian'')\"'>俄文</p></a>"
																				          +"\n<a href='\"#\"'  onclick='\"translation(''spain'');\"'><p id='\"name_spain\"' onmousemove='\"Hover(''name_spain'')\"' onmouseout='\"notHover(''name_spain'')\"'>西班牙文</p></a>";
	//div结束标签
	private static final String div_end = "\n</div>";
	//tr结束标签
	private static final String tr_end = "\n</tr>";
	//table结束标签
	private static final String table_end = "\n</table>";
	//div的head前面部分
	private static final String  div_head_front = "<div style=''height:38px;margin-left:-15px;padding-left:15px;padding-left:15px;font-size:16px;font-weight:normal;border-bottom: 2px solid #e5e9ed;background-color: #f5faf9;''><div  class=''easyui-panel'' id=''{";
	//div的样式
	private static final String  div_style = "}'' style=''overflow: auto; padding-top: 10px;padding-left: 20px;color:#1369c0;font-weight: bold;''>{";
	//div的head后面部分
	private static final String  div_head_behind = "}</div></div>" ;
	//table的head内容
	private static final String  table_head = "\n<table id=''search'' style=''margin-top: 0px; width: 100%'' cellspacing=''1''>";
	//第一段td的前面部分	
	private static final String  first_td_front= "\n<td align=''center'' valign=''middle'' id=''{";
	//第一段td的后面部分	
	private static final String  first_td_behind= "}'' style=''color: #434A52;width:100px;height:30px;font-size: 12px;font-weight: normal;''>{";
	//第二段td占一行的前面部分
	private static final String  second_td_front_colspan1= "\n<td  align=''center'' valign=''middle'' id=''{";
	//第二段td占三行的前面部分
	private static final String  second_td_front_colspan3= "\n<td colspan=''3''  align=''center'' valign=''middle'' id=''{";
	//第二段td后面部分
	private static final String  second_td_behind= "}'' style=''color: #333333;width:280px;font-size: 14px;font-weight: normal;''>{";
	//td结束标签
	private static final String td_end="}</td>";
	//td结束标签2
	private static final String td_end2="</td>";
	//添加百度搜索前面标签部分
	private static final String baidu_front = "&nbsp;&nbsp;<a href=\"http://www.baidu.com/s?ie=utf-8&wd=";
	//添加百度搜索后面标签部分
	private static final String baidu_behind = "&cl=3\" target=\"_blank\"><img title=\"More details in Google\" alt=\"More details in Google\" style=\"width: 20px;border: 0px;\" src=\"/gbdbas/static/img/datasearch/google-g-logo-vector.png\" ></a>";
	//添加客户信息前面标签部分
	private static final String addcustomer_front = "&nbsp;&nbsp;<a href=\"#\" onclick=\"showCustomerName('";
	//添加客户信息后面标签部分
	private static final String addcustomer_behind  = "')\"><img title=\"Add Customer\" alt=\"Add Customer\" style=\"border: 0px;\" src=\"/gbdbas/static/img/datasearch/viewdetail/addcustomer.png\" ></a>";
	//添加竞争对手信息前面标签部分
	private static final String addcompany_front = "&nbsp;&nbsp;<a href=\"#\" onclick=\"showCompanyName('";
	//添加竞争对手信息后面标签部分
	private static final String addcompany_behind  = "')\"><img title=\"Add Competitor\" alt=\"Add Competitor\" style=\"border: 0px;\" src=\"/gbdbas/static/img/datasearch/viewdetail/addcompare.png\" ></a>";
	
	/**
	 * 动态生成国家查看详情的页面
	 * @return String 查询条件对应的html代码
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static <T>String fmtHtml(Map map,T model,HttpServletRequest request) 
	{
		log.info("进入方法fmtHtml()"); 
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		new LoadPropertiesUtil().init(request,IConstantUtil.VIEWDETAIL);
		//获取所有的列名中文名
		String[] fieldNameTotal=LoadPropertiesUtil.getValue((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.FIELDNAME).split(";");
		//获取所有列名对应的字段
		String[] fieldValueTotal=LoadPropertiesUtil.getValue((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.FIELDVALUE).split(";");
		//获取该国家的关键字(海关编码或者提单号)
		String keyword=LoadPropertiesUtil.getValue((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.KEYWORD);
		String [] formatFields = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.SEARCH_FORMATPRECISION)).toString().split(",");
		//参数列表
		List<String> paramList = new ArrayList<String>();
		
		try {
			//html中的下标
			int index = 0;
			//拼接查看详情头部的样式
			buf.append(view_detail_titleId).append(index++).append(view_detail_titleName).append(index++).append(p_end).append(hr)
		       .append(title_table_head).append(export_pdf).append(print).append(hscode_blnumber_zhnameid).append(index++)
		       .append(hscode_blnumber_zhnamevalue).append(index++).append(hscode_blnumber_fieldvalue).append(index++)
		       .append(hscode_blnumber_end).append(translate_div_head).append(translate_zhname_id).append(index++).append(translate_zhname_value)
		       .append(index++).append(translate_zhname_end).append(translate_sanjiao).append(translate_shangsanjiao).append(translate_select_language)
		       .append(div_end).append(td_end2).append(tr_end).append(table_end);
			paramList.add("name_"+(String)request.getSession().getAttribute("country")+"_data");
			paramList.add((String)request.getSession().getAttribute("countryZhName")+"数据");
			if(DataSearchConstantUtil.HSCODE_LUCENE.equals(keyword))
			{
				paramList.add("name_hscode");
				paramList.add("海关编码：");
				paramList.add(BeanUtils.getProperty(model,"hscode"));
			}else if(DataSearchConstantUtil.BL_NUMBER_LUCENE.equals(keyword))
			{
				paramList.add("name_blnumber");
				paramList.add("提单号：");
				paramList.add(BeanUtils.getProperty(model,"blNumber"));
			}
			paramList.add("name_translate");
			paramList.add("翻译");
			
			for(int i=0;i<fieldNameTotal.length;i++)
			{
				//单个div的title名
				String fieldTitle = fieldNameTotal[i].split(":")[0];
				//单个div的所有的列名
				String[] fieldName = fieldNameTotal[i].split(":")[1].split(",");
				//单个div的title名对应的id
				String fieldTitleId = fieldValueTotal[i].split(":")[0];
				//单个div的所有的列名对应的字段
				String[] fieldValue = fieldValueTotal[i].split(":")[1].split(",");
				
				buf.append(div_head_front).append(index++).append(div_style).append(index++).append(div_head_behind).append(table_head);
				paramList.add("name_"+fieldTitleId);
				paramList.add(fieldTitle);
				
				for(int j=0;j<fieldName.length;j++)
				{
					if(j%2==0)
					{
						buf.append("\n<tr>");
						if(j != fieldName.length-1)
						{
							//显示左边的列
							buf.append(first_td_front).append(index++).append(first_td_behind).append(index++).append(td_end);
							buf.append(second_td_front_colspan1).append(index++).append(second_td_behind).append(index++).append(td_end);
						}
						else
						{
							//显示最后一项占三行的列
							buf.append(first_td_front).append(index++).append(first_td_behind).append(index++).append(td_end);
							buf.append(second_td_front_colspan3).append(index++).append(second_td_behind).append(index++).append(td_end);
							buf.append("\n</tr>");
						}
					}else
					{
						//显示右边的列
						buf.append(first_td_front).append(index++).append(first_td_behind).append(index++).append(td_end);
						buf.append(second_td_front_colspan1).append(index++).append(second_td_behind).append(index++).append(td_end);
						buf.append("\n</tr>");
					}
					paramList.add("name_"+fieldValue[j]);
					paramList.add(fieldName[j]);
					paramList.add("value_"+fieldValue[j]);
					//反射获取当前字段的值
					String value = BeanUtils.getProperty(model, fieldValue[j]);
					// 获取属性类型
					String type = PropertyUtils.getPropertyType(model, fieldValue[j]).getName();
					if (type.equals("java.lang.String")) 
					{
						value = null != BeanUtils.getProperty(model, fieldValue[j])?BeanUtils.getProperty(model, fieldValue[j]):"N/A";
					}
 					if(Arrays.asList(formatFields).contains(fieldValue[j]))
					{
 						 value = null !=BeanUtils.getProperty(model, fieldValue[j])?BeanUtils.getProperty(model, fieldValue[j]):"0";
						 DecimalFormat df=new DecimalFormat("###.00");
						 value = df.format(Double.parseDouble(value));
					}
					//拼接百度添加竞争对手客户的值
					String newValue = "";
					//如果当前字段未进口商则需要添加百度搜索的功能和添加我的客户的功能
					if("importer".equals(fieldValue[j]) && value != "N/A")
					{
						newValue = value.concat(baidu_front).concat(value).concat(baidu_behind)
								.concat(addcustomer_front).concat(value.replaceAll("['\"<>#&]", "")).concat(addcustomer_behind);
						paramList.add(newValue);
					}
					else if("exporter".equals(fieldValue[j]) && value != "N/A")
					{
						newValue = value.concat(baidu_front).concat(value).concat(baidu_behind)
								.concat(addcompany_front).concat(value.replaceAll("['\"<>#&]", "")).concat(addcompany_behind);
						paramList.add(newValue);
					}
					else
					{
						paramList.add(value);
					}
				}
				buf.append("\n</table>\n</div>");
			}
		}catch (Exception e) {
				log.debug(e.getMessage());
		}
		buf.append("\n</div>");
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		return html;
	}
	
	
	/**
	 * 动态生成国家查看详情的页面
	 * @param country : 国家
	 * @param request : 请求
	 * @param model : 国家模型
	 * @return String 查询条件对应的html代码
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static <T>String fmtHtmlByCountry(T model,HttpServletRequest request,String country) 
	{
		log.info("进入方法fmtHtml()"); 
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		new LoadPropertiesUtil().init(request,IConstantUtil.VIEWDETAIL);
		//获取所有的列名中文名
		String[] fieldNameTotal=LoadPropertiesUtil.getValue(country + DataSearchConstantUtil.FIELDNAME).split(";");
		//获取所有列名对应的字段
		String[] fieldValueTotal=LoadPropertiesUtil.getValue(country + DataSearchConstantUtil.FIELDVALUE).split(";");
		//获取该国家的关键字(海关编码或者提单号)
		String keyword=LoadPropertiesUtil.getValue(country + DataSearchConstantUtil.KEYWORD);
		//参数列表
		List<String> paramList = new ArrayList<String>();
		try {
			//html中的下标
			int index = 0;
			//拼接查看详情头部的样式
			buf.append(view_detail_titleId).append(index++).append(view_detail_titleName).append(index++).append(p_end).append(hr)
		       .append(title_table_head).append(export_pdf).append(print).append(hscode_blnumber_zhnameid).append(index++)
		       .append(hscode_blnumber_zhnamevalue).append(index++).append(hscode_blnumber_fieldvalue).append(index++)
		       .append(hscode_blnumber_end).append(translate_div_head).append(translate_zhname_id).append(index++).append(translate_zhname_value)
		       .append(index++).append(translate_zhname_end).append(translate_sanjiao).append(translate_shangsanjiao).append(translate_select_language)
		       .append(div_end).append(td_end2).append(tr_end).append(table_end);
			paramList.add("name_" + country + "_data");
			paramList.add(InitCountryCENameUtil.queryCountryCnName(country)+"数据");
			if(DataSearchConstantUtil.HSCODE_LUCENE.equals(keyword))
			{
				paramList.add("name_hscode");
				paramList.add("海关编码：");
				paramList.add(BeanUtils.getProperty(model,"hscode"));
			}else if(DataSearchConstantUtil.BL_NUMBER_LUCENE.equals(keyword))
			{
				paramList.add("name_blnumber");
				paramList.add("提单号：");
				paramList.add(BeanUtils.getProperty(model,"blNumber"));
			}
			paramList.add("name_translate");
			paramList.add("翻译");
			
			for(int i=0;i<fieldNameTotal.length;i++)
			{
				//单个div的title名
				String fieldTitle = fieldNameTotal[i].split(":")[0];
				//单个div的所有的列名
				String[] fieldName = fieldNameTotal[i].split(":")[1].split(",");
				//单个div的title名对应的id
				String fieldTitleId = fieldValueTotal[i].split(":")[0];
				//单个div的所有的列名对应的字段
				String[] fieldValue = fieldValueTotal[i].split(":")[1].split(",");
				
				buf.append(div_head_front).append(index++).append(div_style).append(index++).append(div_head_behind).append(table_head);
				paramList.add("name_"+fieldTitleId);
				paramList.add(fieldTitle);
				
				for(int j=0;j<fieldName.length;j++)
				{
					if(j%2==0)
					{
						buf.append("\n<tr>");
						if(j != fieldName.length-1)
						{
							//显示左边的列
							buf.append(first_td_front).append(index++).append(first_td_behind).append(index++).append(td_end);
							buf.append(second_td_front_colspan1).append(index++).append(second_td_behind).append(index++).append(td_end);
						}
						else
						{
							//显示最后一项占三行的列
							buf.append(first_td_front).append(index++).append(first_td_behind).append(index++).append(td_end);
							buf.append(second_td_front_colspan3).append(index++).append(second_td_behind).append(index++).append(td_end);
							buf.append("\n</tr>");
						}
					}else
					{
						//显示右边的列
						buf.append(first_td_front).append(index++).append(first_td_behind).append(index++).append(td_end);
						buf.append(second_td_front_colspan1).append(index++).append(second_td_behind).append(index++).append(td_end);
						buf.append("\n</tr>");
					}
					paramList.add("name_"+fieldValue[j]);
					paramList.add(fieldName[j]);
					paramList.add("value_"+fieldValue[j]);
					
					//反射获取当前字段的值
					String value = BeanUtils.getProperty(model, fieldValue[j]);
					// 获取属性类型
					String type = PropertyUtils.getPropertyType(model, fieldValue[j]).getName();
					if (type.equals("java.lang.String")) 
					{
						value = null != BeanUtils.getProperty(model, fieldValue[j])?BeanUtils.getProperty(model, fieldValue[j]):"N/A";
					}
					//拼接百度添加竞争对手客户的值
					String newValue = "";
					//如果当前字段未进口商则需要添加百度搜索的功能和添加我的客户的功能
					if("importer".equals(fieldValue[j]) && value != "N/A")
					{
						newValue = value.concat(baidu_front).concat(value).concat(baidu_behind)
								.concat(addcustomer_front).concat(value).concat(addcustomer_behind);
						paramList.add(newValue);
					}
					else if("exporter".equals(fieldValue[j]) && value != "N/A")
					{
						newValue = value.concat(baidu_front).concat(value).concat(baidu_behind)
								.concat(addcompany_front).concat(value).concat(addcompany_behind);
						paramList.add(newValue);
					}
					else
					{
						paramList.add(value);
					}
				}
				buf.append("\n</table>\n</div>");
			}
		}catch (Exception e) {
				log.debug(e.getMessage());
		}
		buf.append("\n</div>");
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		return html;
	}

}