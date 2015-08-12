package com.njyb.gbdbas.util.pagetemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;

/**
 * 动态生成各国家查看详情模板工具类
 * @author honghao
 * @date 	2015-04-22
 * @version 标准版
 */
public class ExportViewDetailPdfUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(ExportViewDetailPdfUtil.class);
	//html模板开头部分
	private static final String  html_head = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\""
																		+ "\n\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
																		+ "\n<html xmlns=\"http://www.w3.org/1999/xhtml\">"
																		+ "\n<head>"
																		+ "\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"
																		+ "\n<title>查看详情导出pdf模板</title>"
																		+ "\n<style type=\"text/css\">"
																		+ "\n#top'{'"
																		+ "\nposition:relative;"
																		+ "\nwidth:650px;"
																		+ "\nheight:50px;"
																		+ "\nmargin:20px auto;"
																		+ "\n'}'"
																		+ "\n#logo'{'"
																		+ "\nposition: absolute;"
																		+ "\ntop: 0;"
																		+ "\n'}'"
																		+ "\n#content'{'"
																		+ "\nposition:relative;"
																		+ "\nwidth:650px;"
																		+ "\nmargin:20px auto;"
																		+ "\n'}'"
																		+ "\n#content #title'{'"
																		+ "\nposition:relative;"
																		+ "\nmargin-left:50px;"
																		+ "\n'}'"
																		+ "\n#content #title #t1'{'"
																		+ "\nfloat:left;"
																		+ "\nborder-left:thin solid #EC5565;"
																		+ "\nborder-top:thin solid #EC5565;"
																		+ "\nborder-right:thin solid #EC5565;"
																		+ "\nborder-top-left-radius:4px;"
																		+ "\nborder-top-right-radius:4px;"
																		+ "\nheight:35px;"
																		+ "\nwidth:15%;"
																		+ "\npadding-left:28px;"
																		+ "\npadding-top:12px;"
																		+ "\n'}'"
																		+ "\n#content #title #t2'{'"
																		+ "\nfloat:left;"
																		+ "\nborder-bottom:thin solid #EC5565;"
																		+ "\nheight:47px;"
																		+ "\nwidth:80%;"
																		+ "\n'}'"
																		+ "\n#content .infoTable1'{'"
																		+ "\nfloat:left;"
																		+ "\nwidth:100%;"
																		+ "\n'}'"
																		+ "\n#content .infoTable1 p'{'"
																		+ "\nbackground-color:#F5F9FA;"
																		+ "\nborder-bottom:2px solid #E4E8EB;"
																		+ "\nheight:35px;"
																		+ "\nmargin-left:80px;"
																		+ "\npadding-left:20px;"
																		+ "\npadding-top:10px;"
																		+ "\ncolor:#EC5565;"
																		+ "\n'}'"
																		+ "\n#content .infoTable1 .tableDiv'{'"
																		+ "\nmargin-left:120px;"
																		+ "\nmargin-top:30px;"
																		+ "\n'}'"
																		+ "\n#content .infoTable1 .tableDiv td'{'"
																		+ "\nwidth:130px;"
																		+ "\n'}'"
																		+ "\n#content .statement'{'"
																		+ "\nfloat:left;"
																		+ "\nmargin-left:80px;"
																		+ "\nmargin-top:10px;"
																		+ "\ncolor:#EC5565;"
																		+ "\n'}'"
																		+ "\n</style>"
																		+ "\n</head>"
																		+ "\n<body style=\"font-family:SimSun;font-weight: bold;width: 650px;height:900px;\">"
																		+ "\n<div id=\"top\">"
																		+ "\n<div id=\"logo\"> <img style=\"width:250px;\" src=\""+"http://localhost:8080/gbdbas/static/img/login/infobase_logo.gif"+"\"/></div>"
																		+ "\n</div>"
																		+"\n<div id=\"content\">"
																		+ "\n<div id=\"title\">"
																		+ "\n<p id=\"t1\">数据表格</p>"
																		+ "\n<p id=\"t2\"></p>"
																		+ "\n</div>";
	//html模板结尾部分
	private static final String  html_end = "\n<div class=\"statement\">"
																	+ "\n<p>该内容由"+"南通趣易信息技术有限公司"+"提供，<br/>仅供有关参考和研究使用，请在下载24小时内删除，不得复制传播或者利用于其<br/>他商业用途。如果我们提供的信息涉及到损害贵公司的权益，请来电或者发邮件<br/>告知。</p>"
																	+ "\n<p>联系电话："+"400-1816-008"+"</p>"
																	+ "\n<p>电子邮箱："+"TradeEasy@163.com"+"</p>"
																	+ "\n</div>"
																	+ "\n</div>"
																	+ "\n</body>"
																	+ "\n</html> ";
			
	
	
	//外层div的head前面部分
	private static final String  div_head_begin = "<div class=\"infoTable1\">";
	//p标签的标题信息
	private static final String  p_head_begin = "\n<p>{";
	//p标签的标题后半部分
	private static final String  p_head_end = "}</p>";
	//内层div的前半部分
	private static final String secondDiv_head_begin = "\n<div class=\"tableDiv\">";
	//table的head内容
	private static final String  table_head = "\n<table border=\"0\" style=\"table-layout:fixed; word-break:break-strict;\">";
	//第一段td的前面部分	
	private static final String  first_td_behind= "\n<td style=\"color:#333\">{";
	//第二段td占三行的前面部分
	private static final String  second_td_front_colspan3= "\n<td colspan=\"3\" style=\"color:#999\">{";
	//第二段td前面部分
	private static final String  second_td_behind= "<td style=\"color:#999\">{";
	//td结束标签
	private static final String td_end="}</td>";

	/**
	 * 动态生成国家查看详情模板
	 * @param model 国家查看详情的数据
	 * @param request
	 * @return
	 */
	public static <T>String fmtHtml(T model,HttpServletRequest request) 
	{
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		new LoadPropertiesUtil().init(request,IConstantUtil.VIEWDETAIL);
		//获取所有的列名中文名
		String[] fieldNameTotal=LoadPropertiesUtil.getValue((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.FIELDNAME).split(";");
		//获取所有列名对应的字段
		String[] fieldValueTotal=LoadPropertiesUtil.getValue((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.FIELDVALUE).split(";");
		//参数列表
		List<String> paramList = new ArrayList<String>();
		try {
			//html中的下标
			int index = 0;
			buf.append(html_head);
			for(int i=0;i<fieldNameTotal.length;i++)
			{
				//单个div的title名
				String fieldTitle = fieldNameTotal[i].split(":")[0];
				//单个div的所有的列名
				String[] fieldName = fieldNameTotal[i].split(":")[1].split(",");
				//单个div的所有的列名对应的字段
				String[] fieldValue = fieldValueTotal[i].split(":")[1].split(",");
				buf.append(div_head_begin).append(p_head_begin).append(index++).append(p_head_end).append(secondDiv_head_begin).append(table_head);
				paramList.add(fieldTitle);
				for(int j=0;j<fieldName.length;j++)
				{
					if(j%2==0)
					{
						buf.append("\n<tr>");
						if(j != fieldName.length-1)
						{
							//显示左边的列
							buf.append(first_td_behind).append(index++).append(td_end);
							buf.append(second_td_behind).append(index++).append(td_end);
						}
						else
						{
							//显示最后一项占三行的列
							buf.append(first_td_behind).append(index++).append(td_end);
							buf.append(second_td_front_colspan3).append(index++).append(td_end);
							buf.append("\n</tr>");
						}
					}else
					{
						//显示右边的列
						buf.append(first_td_behind).append(index++).append(td_end);
						buf.append(second_td_behind).append(index++).append(td_end);
						buf.append("\n</tr>");
					}
					paramList.add(fieldName[j]);
					//反射获取当前字段的值
					String value = null != BeanUtils.getProperty(model, fieldValue[j])?BeanUtils.getProperty(model, fieldValue[j]):"N/A";
					paramList.add(value.contains("&")?value.replaceAll("&*", ""):value);
				}
				buf.append("\n</table>\n</div>\n</div>");
			}
			buf.append(html_end);
		}catch (Exception e) {
				log.debug(e.getMessage());
		}
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		return html;
	}
}