package com.njyb.gbdbas.util.export;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;

/**
 * 获取导出报表excel所需的字段工具类
 * @author XL
 * @date 2015-03-27
 * @version 标准版
 */
public class QueryReportFieldByCountryUtil {

	/**
	 * 查询配置文件
	 * @param request
	 */
	private static void initLoadProperties(HttpServletRequest request)
	{
		new LoadPropertiesUtil().init(request,IConstantUtil.EXPORTINFO);
	}
	
//	/**
//	 * 根据国家获取公用的汇总属性字段
//	 * @param request
//	 * @param countryName 国家名称
//	 * @return String[]
//	 */
//	public static String[] queryCommonSummaryField(HttpServletRequest request,String countryName)
//	{
//		initLoadProperties(request);
//		//组装KEY值
//		String key = getFieldKeyName(countryName) + "_commonSummaryField";
//		//获取汇总属性字段
//		String[] commonSummaryField = LoadPropertiesUtil.getValue(key).split(",");
//		return commonSummaryField;
//	}
//	
//	/**
//	 * 根据国家获取公用的汇总利率字段
//	 * @param request
//	 * @param countryName 国家名称
//	 * @return String[]
//	 */
//	public static String[] queryCommonSummaryRatio(HttpServletRequest request,String countryName)
//	{
//		initLoadProperties(request);
//		//组装KEY值
//		String key = getFieldKeyName(countryName) + "_commonSummaryRatio";
//		//获取公用的汇总利率字段
//		String[] commonSummaryRatio = LoadPropertiesUtil.getValue(key).split(",");
//		return commonSummaryRatio;
//		
//	}
	
	/**
	 * 根据国家名称下的属性字段获取公用的显示列名
	 * 例如：数量(千克), 数量占比,金额(美元)-排序, 美元占比↓, 平均单价(美元)
	 * @param request
	 * @param countryName 国家名称
	 * @param commonField 公用字段包括：汇总属性字段及汇总利率字段
	 * @return String[]
	 */
//	public static String[] queryCommonColumnName(HttpServletRequest request,String countryName,String[] commonField)
//	{
//		initLoadProperties(request);
//		//组装KEY值
//		String key = getFieldKeyName(countryName) + "_commonColumnName";
//		//获取Value值
//		String[] value = LoadPropertiesUtil.getValue(key).split(",");
//		//以键值对的形式存储字段及显示的列名
//		Map<String,String> map = new HashMap<String,String>();
//		for(int i = 0; i < value.length; i++)
//		{
//			String[] str = value[i].split(":");
//			map.put(str[0], str[1]);
//		}
//		//存放列名数组
//		String[] commonColumnName = new String[commonField.length];
//		//根据传入的属性字段查找列名
//		for(int i = 0; i < commonField.length; i++)
//		{
//			commonColumnName[i ]= map.get(commonField[i]);
//		}
//		return commonColumnName;
//	}
	
	/**
	 * 根据国家名称下的报告类型字段获取第二层钻取报告类型字段
	 * @param request
	 * @param countryName 国家名称
	 * @param reportType 报告类型属性字段
	 * @return String
	 */
//	public static String queryDrillField(HttpServletRequest request,String countryName,String reportType)
//	{
//		initLoadProperties(request);
//		//组装KEY值
//		String key = getFieldKeyName(countryName) + "_drillField";
//		//获取Value值
//		String[] value = LoadPropertiesUtil.getValue(key).split(",");
//		//以键值对的形式存储字段及显示的列名
//		Map<String,String> map = new HashMap<String,String>();
//		for(int i = 0; i < value.length; i++)
//		{
//			String[] str = value[i].split(":");
//			map.put(str[0], str[1]);
//		}
//		//获取钻取字段
//		String drillField = map.get(reportType);
//		return drillField;
//	}
//	
	/**
	 * 根据国家名称下的报告类型字段获取对应的报告类型中文列名
	 * @param request
	 * @param countryName 国家名称
	 * @param reportType 报告类型属性字段
	 * @return String
	 */
//	public static String queryReportTypeColumnName(HttpServletRequest request,String countryName,String reportType)
//	{
//		initLoadProperties(request);
//		//组装KEY值
//		String key = getFieldKeyName(countryName) + "_reportTypeColumnName";
//		//获取Value值
//		String[] value = LoadPropertiesUtil.getValue(key).split(",");
//		//以键值对的形式存储字段及显示的列名
//		Map<String,String> map = new HashMap<String,String>();
//		for(int i = 0; i < value.length; i++)
//		{
//			String[] str = value[i].split(":");
//			map.put(str[0], str[1]);
//		}
//		//报告类型中文显示列名
//		String reportTypeColumnName = map.get(reportType);
//		return reportTypeColumnName;
//		
//	}
	
	/**
	 * 根据报告类型字段获取第二次钻取字段的中文名称
	 * @param request
	 * @param countryName 国家名称
	 * @param reportType 报告类型属性字段
	 * @return String
	 */
//	public static String queryDrillFieldColumnName(HttpServletRequest request,String countryName,String reportType)
//	{
//		initLoadProperties(request);
//		//组装KEY值
//		String key = getFieldKeyName(countryName) + "_drillFieldColumnName";
//		//获取Value值
//		String[] value = LoadPropertiesUtil.getValue(key).split(",");
//		//以键值对的形式存储字段及显示的列名
//		Map<String,String> map = new HashMap<String,String>();
//		for(int i = 0; i < value.length; i++)
//		{
//			String[] str = value[i].split(":");
//			map.put(str[0], str[1]);
//		}
//		//根据报告类型属性字段获取第二层钻取字段的中文列名
//		String drillFieldColumnName = map.get(reportType);
//		return drillFieldColumnName;
//	}
	
//	/**
//	 * 查询中文类名及对应的属性字段
//	 * 如：企业  数量(千克) 数量占比  金额(美元) 美元占比↓ 平均单价(美元)
//	 * @param request
//	 * @param countryName
//	 * @param reportType
//	 * @return Map<String,String[]>
//	 */
//	public static Map<String,String[]> queryShowColumnName(HttpServletRequest request,String countryName,String reportType){
//		//获取第二层钻取字段
//		String drillFiled = queryDrillField(request, countryName, reportType);
//		
//		//获取公用属性字段
//		String[] commonField = queryCommonSummaryField(request, countryName);
//		
//		//获取公用计算比率属性字段
//		String[] commonSummaryRatio = queryCommonSummaryRatio(request, countryName);
//		
//		//存放组装的公用属性字段如：cifValue,summoneyPercentage,grossWeight,grossWeightPercentage...
//		List<String> fieldlist = new ArrayList<String>();
//		for(int i = 0; i < commonField.length; i++){
//			fieldlist.add(commonField[i]);
//			fieldlist.add(commonSummaryRatio[i]);
//		}
//		//转换成数组
//		String[] fieldArray = fieldlist.toArray(new String[fieldlist.size()]);
//		//获取公用属性字段包括比率属性字段的中文名称
//		String[] commonColumnName = queryCommonColumnName(request, countryName, fieldArray);
//		//存储中文类名及对应的属性字段的集合
//		Map<String,String[]> map = new HashMap<String, String[]>();
//		//获取报表类型汇总字段中文列名
//		String reportChinaName = queryReportTypeColumnName(request, countryName, reportType);
//		//判断是否有第二层钻取
//		if(null == drillFiled)
//		{
//			String[] showChinaColumn = new String[commonColumnName.length+1]; 
//			String[] showCommonField = new String[fieldArray.length+1]; 
//			//组装excel中显示的中文列名
//			showChinaColumn[0] = reportChinaName;
//			System.arraycopy(commonColumnName, 0, showChinaColumn, 1, commonColumnName.length);
//			//组装excel中显示的属性字段
//			showCommonField[0] = reportType;
//			System.arraycopy(fieldArray, 0,showCommonField, 1, fieldArray.length);
//			map.put("showChinaColumn", showChinaColumn);
//			map.put("showCommonField", showCommonField);
//		}
//		else
//		{
//			// 如果获取钻取字段不为空，则组装显示在excel中的列
//			String[] showChinaColumn = new String[commonColumnName.length+2]; 
//			String[] showCommonField = new String[fieldArray.length+2]; 
//			//根据报表类型获取钻取字段中文列名
//			String drillColumnName = queryDrillFieldColumnName(request, countryName, reportType);
//			//组装excel中显示的中文列名
//			showChinaColumn[0] = reportChinaName;
//			showChinaColumn[1] = drillColumnName;
//			System.arraycopy(commonColumnName, 0, showChinaColumn, 2, commonColumnName.length);
//			//组装excel中显示的属性字段
//			showCommonField[0] = reportType;
//			showCommonField[1] = drillFiled;
//			System.arraycopy(fieldArray, 0,showCommonField, 2, fieldArray.length);
//			map.put("showChinaColumn", showChinaColumn);
//			map.put("showCommonField", showCommonField);
//		}
//		return map;
//	}
	
	/**
	 * 根据国家名称获取属性key
	 * @param countryName 国家名称
	 * @return String
	 */
	private static String getFieldKeyName(String countryName)
	{
		// 阿根廷进口
		if (countryName.equals(LuceneConstant.ARGENTINA_IMPORT_STRING))
		{
			return LuceneConstant.ARGENTINA_IMPORT_STRING;
		}
		// 阿根廷出口
		if (countryName.equals(LuceneConstant.ARGENTINA_EXPORT_STRING))
		{
			return LuceneConstant.ARGENTINA_EXPORT_STRING;
		}
		// 巴西进口
		if (countryName.equals(LuceneConstant.BRAZIL_IMPORT_STRING))
		{
			return LuceneConstant.BRAZIL_IMPORT_STRING;
		}
		// 智利进口
		if (countryName.equals(LuceneConstant.CHILE_IMPORT_STRING))
		{
			return LuceneConstant.CHILE_IMPORT_STRING;
		}
		// 智利出口
		if (countryName.equals(LuceneConstant.CHILE_EXPORT_STRING))
		{
			return LuceneConstant.CHILE_EXPORT_STRING;
		}
		//哥伦比亚进口
		if (countryName.equals(LuceneConstant.COLOM_IMPORT_STRING))
		{
			return LuceneConstant.COLOM_IMPORT_STRING;
		}
		//哥伦比亚出口
		if (countryName.equals(LuceneConstant.COLOM_EXPORT_STRING))
		{
			return LuceneConstant.COLOM_EXPORT_STRING;
		}
		//哥斯达黎加进口
		if (countryName.equals(LuceneConstant.COSTARICA_IMPORT_STRING))
		{
			return LuceneConstant.COSTARICA_IMPORT_STRING;
		}
		//哥斯达黎加出口
		if (countryName.equals(LuceneConstant.COSTARICA_EXPORT_STRING))
		{
			return LuceneConstant.COSTARICA_EXPORT_STRING;
		}
		//厄瓜多尔进口
		if (countryName.equals(LuceneConstant.ECUADOR_IMPORT_STRING))
		{
			return LuceneConstant.ECUADOR_IMPORT_STRING;
		}
		//厄瓜多尔出口
		if (countryName.equals(LuceneConstant.ECUADOR_EXPORT_STRING))
		{
			return LuceneConstant.ECUADOR_EXPORT_STRING;
		}
		//危地拉马进口
		if (countryName.equals(LuceneConstant.GUATEMALA_IMPORT_STRING))
		{
			return LuceneConstant.GUATEMALA_IMPORT_STRING;
		}
		//危地拉马出口
		if (countryName.equals(LuceneConstant.GUATEMALA_EXPORT_STRING))
		{
			return LuceneConstant.GUATEMALA_EXPORT_STRING;
		}
		//洪都拉斯进口
		if (countryName.equals(LuceneConstant.HONDURAS_IMPORT_STRING))
		{
			return LuceneConstant.HONDURAS_IMPORT_STRING;
		}
		//洪都拉斯出口
		if (countryName.equals(LuceneConstant.HONDURAS_EXPORT_STRING))
		{
			return LuceneConstant.HONDURAS_EXPORT_STRING;
		}
		//印度进口
		if (countryName.equals(LuceneConstant.INDIA_IMPORT_STRING))
		{
			return LuceneConstant.INDIA_IMPORT_STRING;
		}
		//印度出口
		if (countryName.equals(LuceneConstant.INDIA_EXPORT_STRING))
		{
			return LuceneConstant.INDIA_EXPORT_STRING;
		}
		//韩国
		if (countryName.equals(LuceneConstant.KOREA_STRING))
		{
			return LuceneConstant.KOREA_STRING;
		}
		//墨西哥进口
		if (countryName.equals(LuceneConstant.MEXICON_IMPORT_STRING))
		{
			return LuceneConstant.MEXICON_IMPORT_STRING;
		}
		//尼加拉瓜进口
		if (countryName.equals(LuceneConstant.NICARAGUA_IMPORT_STRING))
		{
			return LuceneConstant.NICARAGUA_IMPORT_STRING;
		}
		//尼加拉瓜出口
		if (countryName.equals(LuceneConstant.NICARAGUA_EXPORT_STRING))
		{
			return LuceneConstant.NICARAGUA_EXPORT_STRING;
		}
		//巴基斯坦
		if (countryName.equals(LuceneConstant.PAKISTAN_IMPORT_STRING))
		{
			return LuceneConstant.PAKISTAN_IMPORT_STRING;
		}
		//巴拿马进口
		if (countryName.equals(LuceneConstant.PANAMA_IMPORT_STRING))
		{
			return LuceneConstant.PANAMA_IMPORT_STRING;
		}
		//巴拿马出口
		if (countryName.equals(LuceneConstant.PANAMA_EXPORT_STRING))
		{
			return LuceneConstant.PANAMA_EXPORT_STRING;
		}
		//巴拉圭进口
		if (countryName.equals(LuceneConstant.PARAGUAY_IMPORT_STRING))
		{
			return LuceneConstant.PARAGUAY_IMPORT_STRING;
		}
		//巴拉圭出口
		if (countryName.equals(LuceneConstant.PARAGUAY_EXPORT_STRING))
		{
			return LuceneConstant.PARAGUAY_EXPORT_STRING;
		}
		//秘鲁进口
		if (countryName.equals(LuceneConstant.PERU_IMPORT_STRING))
		{
			return LuceneConstant.PERU_IMPORT_STRING;
		}
		//秘鲁出口
		if (countryName.equals(LuceneConstant.PERU_EXPORT_STRING))
		{
			return LuceneConstant.PERU_EXPORT_STRING;
		}
		//俄罗斯进口
		if (countryName.equals(LuceneConstant.RUSSIAN_IMPORT_STRING))
		{
			return LuceneConstant.RUSSIAN_IMPORT_STRING;
		}
		//俄罗斯出口
		if (countryName.equals(LuceneConstant.RUSSIAN_EXPORT_STRING))
		{
			return LuceneConstant.RUSSIAN_EXPORT_STRING;
		}
		//萨尔瓦多进口
		if (countryName.equals(LuceneConstant.SALVATORE_IMPROT_STRING))
		{
			return LuceneConstant.SALVATORE_IMPROT_STRING;
		}
		//萨尔瓦多出口
		if (countryName.equals(LuceneConstant.SALVATORE_EXPORT_STRING))
		{
			return LuceneConstant.SALVATORE_EXPORT_STRING;
		}
		//英国
		if (countryName.equals(LuceneConstant.UK_IMPORT_STRING))
		{
			return LuceneConstant.UK_IMPORT_STRING;
		}
		//乌克兰进口
		if (countryName.equals(LuceneConstant.UKRAINE_IMPORT_STRING))
		{
			return LuceneConstant.UKRAINE_IMPORT_STRING;
		}
		//乌拉圭进口
		if (countryName.equals(LuceneConstant.URUGUAY_IMPORT_STRING))
		{
			return LuceneConstant.URUGUAY_IMPORT_STRING;
		}
		//乌拉圭出口
		if (countryName.equals(LuceneConstant.URUGUAY_EXPORT_STRING))
		{
			return LuceneConstant.URUGUAY_EXPORT_STRING;
		}
		//美国
		if (countryName.equals(LuceneConstant.USA_IMPORT_STRING))
		{
			return LuceneConstant.USA_IMPORT_STRING;
		}
		//委内瑞拉进口
		if (countryName.equals(LuceneConstant.VENEZUELAID_IMPORT_STRING))
		{
			return LuceneConstant.VENEZUELAID_IMPORT_STRING;
		}
		//委内瑞拉出口
		if (countryName.equals(LuceneConstant.VENEZUELAID_EXPORT_STRING))
		{
			return LuceneConstant.VENEZUELAID_EXPORT_STRING;
		}
		//越南进口
		if (countryName.equals(LuceneConstant.VIETNAM_IMPORT_STRING))
		{
			return LuceneConstant.VIETNAM_IMPORT_STRING;
		}
		//越南出口
		if (countryName.equals(LuceneConstant.VIETNAM_EXPORT_STRING))
		{
			return LuceneConstant.VIETNAM_EXPORT_STRING;
		}
		//中国八位
		if (countryName.equals(LuceneConstant.CHINA_EIGHT_STRING))
		{
			return LuceneConstant.CHINA_EIGHT_STRING;
		}
		//玻利维亚
		if (countryName.equals(LuceneConstant.BOLIVIA_IMPORT_STRING))
		{
			return LuceneConstant.BOLIVIA_IMPORT_STRING;
		}
		return null;
	}
	
	/**
	 * 根据国家获取当前国家实体类的属性的备注
	 * @param request
	 * @param countryName 国家名称
	 * @return String[]
	 */
	public static String[] queryCommonRemarksByCountry(HttpServletRequest request,String countryName)
	{
		initLoadProperties(request);
		//组装KEY值
		String key = getFieldKeyName(countryName) + "_remarks";
		String[] remarks = LoadPropertiesUtil.getValue(key).split(",");
		return remarks;
	}
	
	/**
	 * 根据国家获取当前国家实体类的所有属性名称不包括主键
	 * @param request
	 * @param countryName
	 * @return String[]
	 */
	public static String[] queryCommonPropertyNameByCountry(HttpServletRequest request,String countryName)
	{
		initLoadProperties(request);
		//组装KEY值
		String key = getFieldKeyName(countryName) + "_propertyName";
		String[] propertys = LoadPropertiesUtil.getValue(key).split(",");
		return propertys;
	}

}
