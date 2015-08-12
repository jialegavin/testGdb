
package com.njyb.gbdbas.util.pagetemplate;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.njyb.gbdbas.util.DataSearchConstantUtil;

/**
 * 动态生成各国家查询条件模板工具类
 * @author honghao
 * @date 	2015-04-01
 * @version 标准版
 */
public class QueryConditionUtil
{
	//log记录日志
	private static final Logger log = Logger.getLogger(QueryConditionUtil.class);
	//第一列文本框样式
	private static final String first_rows_div_front="\n<div class=''firstfield''>";
	//第二列文本框样式
	private static final String second_rows_div_front ="\n<div class=''secondfield''>";
	//第三列文本框样式
	private static final String third_rows_div_front ="\n<div class=''thirdfield''>";
	//第四列文本框样式
	private static final String forth_rows_div_front ="\n<div class=''forthfield''>";
	//div结束标签
	private static final String div_behind ="\n</div>";
	//中文字体样式
	private static final String font_front = "\n<font class=''fieldname''>{";
	//font结束标签
	private static final String font_behind = "}</font>";
	//文本框id名
	private static final String input_id = "\n<input id=''{";
	//单个文本框name名
	private static final String input_singleinput_name  = "}'' class=''inpText'' name=''{";
	//单个文本框placehoder值
	private static final String input_singleinput_placeholder = "}'' placeholder=''{";
	//单个文本框选择国家value值
	private static final String input_singleinput_value = "}'' value = ''{";
	//单个文本框选择国家onclick样式
	private static final String input_singleinput_onclick = "}'' onclick='\"changeCountry()\"' readonly='\"readonly\"'/>";
	//海关编码单个文本框不可编辑
	private static final String input_singleinput_hscode_disabled = "}'' readonly='\"readonly\"'/>";
	//单个文本框结束标签
	private static final String input_singleinput_behind = "}''/>";
	//区间文本框name名
	private static final String input_doubleinput_name = "}'' class=''doubleinpText'' name=''{";
	//区间文本框结束标签(不是日期)
	private static final String input_doubleinput_nodate_behind = "}''/>";
	//第一段日期文本框开始标签
	private static final String input_doubleinput_firstdate_value = "}'' value=''";
	private static final String input_doubleinput_firstdate_behind = "''  onclick='\"WdatePicker({lang:''zh-cn'',dateFmt:''";
	//第一段日期文本框结束标签(两种日期格式)
	private static final String input_doubleinput_firstdate_formatDate_first = "'',quickSel:[''2010-01-01'',''2011-01-01'',''2012-01-01'',''2013-01-01'',''2014-01-01''],readOnly:true})\"'/>";
	private static final String input_doubleinput_firstdate_formatDate_second = "'',quickSel:[''01/2010'',''01/2011'',''01/2012'',''01/2013'',''01/2014''],readOnly:true})\"'/>";
	//第二段日期文本框开始标签
	private static final String input_doubleinput_seconddate_value = "}'' value=''";
	private static final String input_doubleinput_seconddate_behind = "'' onclick='\"WdatePicker({lang:''zh-cn'',dateFmt:''";
	//第二段日期文本框结束标签(两种日期格式)
	private static final String input_doubleinput_seconddate_formatDate_first = "'',quickSel:[''2010-12-31'',''2011-12-31'',''2012-12-31'',''2013-12-31'',''2014-12-31''],readOnly:true})\"'/>";
	private static final String input_doubleinput_seconddate_formatDate_second = "'',quickSel:[''12/2010'',''12/2011'',''12/2012'',''12/2013'',''12/2014''],readOnly:true})\"'/>";
	//区间文本框之间的横杠
	private static final String heng_gang = "\n<font class=''henggang''>-</font>";
	//select下拉框id名
	private static final String select_id= "\n<select  class=''easyui-combobox''  id=''{";
	//select下拉框name名
    private static final String select_name = "}''  name = ''{";
    //select下拉框结束标签
    private static final String select_behind = "}''></select>";
    //进出口类型单选按钮1(进口)开始样式
    private static final String radio_import_front= "<input id=''import'' checked=''checked'' style = ''width:40px'' type=''radio'' name=''{";
    //进出口类型单选按钮1(进口)结束样式 ---I have change the text of import and export, not know if it will change the other program.
    private static final String radio_import_behind= "}'' value=''I'' /><font class=''radiofieldname''>Import</font>&nbsp;";
    //进出口类型单选按钮1(出口)开始样式
    private static final String radio_export_front= "<input id=''import'' style = ''width:40px'' type=''radio'' name=''{";
    //进出口类型单选按钮1(出口)结束样式
    private static final String radio_export_behind= "}'' value=''E'' /><font class=''radiofieldname''>Export</font>";
    //翻译的按钮固定在左下角文本框中
    //翻译的样式1
    private static final String div_translate_firstcows = "\n<div class='translatefirstcows'>";
    //翻译的样式2
    private static final String div_translate_secondcows = "\n<div class='translatesecondcows'>";
    //翻译的样式3
    private static final String div_translate_thirdcows = "\n<div class='translatethirdcows'>";
    //翻译的样式4
    private static final String div_translate_forthcows = "\n<div class='translateforthcows'>";
    //添加海关编码按钮开始样式
    private static final String add_hscode_front = "<div class='addhscode'><img align=''center'' style=''width: 19px; height: 19px;'' src='"+DataSearchConstantUtil.ADD_HSCODE_PATH+"'  onclick='\"javascript:addhscode('{";
    //添加海关编码按钮结束样式
    private static final String add_hscode_behind = "}',''addHscodeDialog'');\"'/></div>";
    //翻译的图片按钮
    private static final String div_translate_img = "<img align='center' src='"+DataSearchConstantUtil.TRANSBUTTON_PATH+"'  onclick='\"javascript:translateValue('{";
    //div结束标签
    private static final String div_translate_behind = "}');\"'/></div>";
    
	/**
	 * 动态生成各国家查询条件模板
	 * @return String 查询条件对应的html代码
	 * @param country 国家英文名
	 * @param countryEnName 国家中文名---change to english name
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static <T>String fmtHtml(String country,Map map,String countryEnName) 
	{
		
		//log.info("进入QueryConditionUtil类的fmtHtml()方法");
		//拼凑出待生成的html
		StringBuffer buf = new StringBuffer();
		//获取搜索条件的中文名
	//	String [] fieldZhName = map.get(country+DataSearchConstantUtil.CONDITIONZHNAME).toString().split(",");
		//get search english name
		String [] fieldZhName = map.get(country+DataSearchConstantUtil.CONDITIONENNAME).toString().split(",");
		//获取搜索条件字段id名
		String [] fieldId = map.get(country+DataSearchConstantUtil.CONDITIONFIELDID).toString().split(",");
		//获取需要添加翻译的字段id
		String [] fieldNeedTrans = map.get(country+DataSearchConstantUtil.TRANS).toString().split(",");
		//获取需要添加文本框描述的字段id
		String [] fieldNeedDesc = map.get(country+DataSearchConstantUtil.DESC).toString().split(",");
		//获取当前国家的日期格式
		String dateFormatter = map.get(country+DataSearchConstantUtil.DATEFORMATTER).toString();
		//获取当前国家初始日期
		String[] dateValue = map.get(country+DataSearchConstantUtil.DATEVALUE).toString().split(",");
		//翻译的样式位置
		String transStyle = "";
		//html中的下标
		int index = 0;
		//参数列表
		List<String> paramList = new ArrayList<String>();
		for(int i=0;i<fieldId.length;i++)
		{
			//第一列查询条件
			if(i%4==0)
			{
				buf.append(first_rows_div_front);
				if(i==4)
				{
					transStyle = div_translate_firstcows;
				}else if(i == 8)
				{
					transStyle = div_translate_secondcows ;
				}
				}
			//第二列查询条件
			else if(i%4 ==1)
			{
				buf.append(second_rows_div_front);
				if(i==5)
				{
					transStyle = div_translate_thirdcows;
				}else if(i == 9)
				{
					transStyle = div_translate_forthcows;
				}
			}
			//第三列查询条件
			else if(i%4 ==2)
			{
				buf.append(third_rows_div_front);
			}
			//第四列查询条件
			else if(i%4 ==3)
			{
				buf.append(forth_rows_div_front);
			}
			
			buf.append(font_front).append(index++).append(font_behind);
			paramList.add(fieldZhName[i]);
			//如果是原产国查询字段，需要select下拉列表展示
			if(DataSearchConstantUtil.ORIGIN_COUNTRY_LUCENE.equals(fieldId[i]))
			{
				buf.append(select_id).append(index++).append(select_name).append(index++).append(select_behind);
				paramList.add("c_"+fieldId[i]);
				paramList.add("c_"+fieldId[i]);
				buf.append(div_behind);
			}
			//如果是进出口类型字段，需要单选按钮展示
			else if(DataSearchConstantUtil.TRADE_TYPE_LUCENE.equals(fieldId[i]))
			{
				buf.append(radio_import_front).append(index++).append(radio_import_behind).append(radio_export_front).append(index++).append(radio_export_behind);
				paramList.add("c_"+fieldId[i]);
				paramList.add("c_"+fieldId[i]);
				buf.append(div_behind);
			}
			//如果是其他的字段，需要input文本框展示
			else
			{
				buf.append(input_id).append(index++);
				paramList.add("c_"+fieldId[i].split("/")[0]);
				//如果是单个文本框字段
				if(fieldId[i].split("/").length == 1)
				{
					buf.append(input_singleinput_name).append(index++);
					paramList.add("c_"+fieldId[i]);
					//判断该字段需要加文本框描述
					if(Arrays.asList(fieldNeedDesc).contains(fieldId[i]))
					{
						buf.append(input_singleinput_placeholder).append(index++);
						//海关编码文本框描述
						if("hscode".equals(fieldId[i]))
						{
							buf.append(input_singleinput_hscode_disabled);
							paramList.add(DataSearchConstantUtil.HSCODEDESC);
							buf.append(add_hscode_front).append(index++).append(add_hscode_behind);
							paramList.add("\'"+"c_"+fieldId[i]+"\'");
						}
						//提单号文本框添加描述
						else if("billnumber".equals(fieldId[i]))
						{
							buf.append(input_singleinput_behind);
							paramList.add(DataSearchConstantUtil.BILLLADINGDESC);
						}
						//其他文本框需要添加
						else
						{
							buf.append(input_singleinput_behind);
							paramList.add(DataSearchConstantUtil.OTHERDESC);
						}
					}else
					{
						//选择国家添加onclick时间以及显示文本框value值
						if("country".equals(fieldId[i]))
						{	
							buf.append(input_singleinput_value).append(index++).append(input_singleinput_onclick);
							paramList.add(countryEnName);
						}else
						{
							buf.append(input_singleinput_behind);
						}
					}
					
					//判断该字段需要加翻译按钮
					if(Arrays.asList(fieldNeedTrans).contains(fieldId[i]))
					{
						buf.append(transStyle).append(div_translate_img).append(index++).append(div_translate_behind);
						paramList.add("\'"+"c_"+fieldId[i]+"\'");
					}
				}
				//如果该字段是区间文本框字段
				else
				{
					buf.append(input_doubleinput_name).append(index++);
					paramList.add("c_"+fieldId[i].split("/")[0]);
					//如果该字段为日期字段的话
					if(fieldId[i].split("/")[0].contains("startdate") && fieldId[i].split("/")[1].contains("enddate"))
					{
						String quickSelDateFirst = "";
						String quickSelDateSecond = "";
						//根据日期格式选用不同的快速选择日期格式
						if("yyyy-MM-dd".equals(dateFormatter))
						{
							quickSelDateFirst = input_doubleinput_firstdate_formatDate_first;
							quickSelDateSecond = input_doubleinput_seconddate_formatDate_first;
						}
						else
						{
							quickSelDateFirst = input_doubleinput_firstdate_formatDate_second;
							quickSelDateSecond = input_doubleinput_seconddate_formatDate_second;
						}
						buf.append(input_doubleinput_firstdate_value).append(dateValue[0]).append(input_doubleinput_firstdate_behind)
						.append(dateFormatter).append(quickSelDateFirst).append(heng_gang).append(input_id).append(index++)
						.append(input_doubleinput_name).append(index++).append(input_doubleinput_seconddate_value).append(dateValue[1])
						.append(input_doubleinput_seconddate_behind).append(dateFormatter).append(quickSelDateSecond);
					}
					else
					{
						buf.append(input_doubleinput_nodate_behind).append(heng_gang).append(input_id).append(index++).append(input_doubleinput_name).append(index++).append(input_doubleinput_nodate_behind);
					}
					paramList.add("c_"+fieldId[i].split("/")[1]);
					paramList.add("c_"+fieldId[i].split("/")[1]);
				}
				buf.append(div_behind);
			}
		}
		//生成对应的前端HTML语句
		String html = MessageFormat.format(buf.toString(), paramList.toArray());
		//log.info("生成的前段html语句为:"+html);
		return html;
	}
}