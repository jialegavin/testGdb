package com.njyb.gbdbas.util;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.njyb.gbdbase.service.common.engine.util.EnglishCountryConstant;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;

/**
 * 初始化数据检索需要用到的初始化数据信息
 * @author 贾红平
 *
 */
public class InitCountryCENameUtil {
	/*声明一个map存储国家信息*/
	private static final BiMap<String,String> countryMap; 
	/*声明一个map存储报告类型对应的中文名称*/
	private static final BiMap<String,String> reportChinaTypeMap; 
	/*声明一个map存储英文字段对应的中文字段的简称*/
	private static final BiMap<String,String> reportChinaFieldMap; 
	//add english name map
	private static final BiMap<String, String> countryEnMap;
	// add English Type map
	private static final BiMap<String, String> reportEnTypeMap;
	/*初始化map数据*/
	 static {
		 /*初始化map*/
	    countryMap =HashBiMap.create(); 
	    reportChinaTypeMap=HashBiMap.create();
	    reportChinaFieldMap=HashBiMap.create();
	    countryEnMap =HashBiMap.create();  //add english name map
	    reportEnTypeMap = HashBiMap.create(); 
	    /*初始化国家数据信息*/
	    countryMap.put("阿根廷进口",LuceneConstant.ARGENTINA_IMPORT_STRING);
		countryMap.put("阿根廷出口",LuceneConstant.ARGENTINA_EXPORT_STRING);
		countryMap.put("巴西进口",LuceneConstant.BRAZIL_IMPORT_STRING);
		countryMap.put("智利进口",LuceneConstant.CHILE_IMPORT_STRING);
		countryMap.put("智利出口",LuceneConstant.CHILE_EXPORT_STRING);
		countryMap.put("哥伦比亚进口",LuceneConstant.COLOM_IMPORT_STRING);
		countryMap.put("哥伦比亚出口",LuceneConstant.COLOM_EXPORT_STRING);
		countryMap.put("哥斯达黎加进口",LuceneConstant.COSTARICA_IMPORT_STRING);
		countryMap.put("哥斯达黎加出口",LuceneConstant.COSTARICA_EXPORT_STRING);
		countryMap.put("厄瓜多尔进口",LuceneConstant.ECUADOR_IMPORT_STRING);
		countryMap.put("厄瓜多尔出口",LuceneConstant.ECUADOR_EXPORT_STRING);
		countryMap.put("危地马拉进口",LuceneConstant.GUATEMALA_IMPORT_STRING);
		countryMap.put("危地马拉出口",LuceneConstant.GUATEMALA_EXPORT_STRING);
		countryMap.put("洪都拉斯进口",LuceneConstant.HONDURAS_IMPORT_STRING);
		countryMap.put("洪都拉斯出口",LuceneConstant.HONDURAS_EXPORT_STRING);
		countryMap.put("印度进口",LuceneConstant.INDIA_IMPORT_STRING);
		countryMap.put("印度出口",LuceneConstant.INDIA_EXPORT_STRING);
		countryMap.put("韩国",LuceneConstant.KOREA_STRING);
		countryMap.put("墨西哥进口",LuceneConstant.MEXICON_IMPORT_STRING);
		countryMap.put("尼加拉瓜进口",LuceneConstant.NICARAGUA_IMPORT_STRING);
		countryMap.put("尼加拉瓜出口",LuceneConstant.NICARAGUA_EXPORT_STRING);
		countryMap.put("巴基斯坦进口",LuceneConstant.PAKISTAN_IMPORT_STRING);
		countryMap.put("巴拿马进口",LuceneConstant.PANAMA_IMPORT_STRING);
		countryMap.put("巴拿马出口",LuceneConstant.PANAMA_EXPORT_STRING);
		countryMap.put("巴拉圭进口",LuceneConstant.PARAGUAY_IMPORT_STRING);
		countryMap.put("巴拉圭出口",LuceneConstant.PARAGUAY_EXPORT_STRING);
		countryMap.put("秘鲁进口",LuceneConstant.PERU_IMPORT_STRING);
		countryMap.put("秘鲁出口",LuceneConstant.PERU_EXPORT_STRING);
		countryMap.put("俄罗斯进口",LuceneConstant.RUSSIAN_IMPORT_STRING);
		countryMap.put("俄罗斯出口",LuceneConstant.RUSSIAN_EXPORT_STRING);
		countryMap.put("萨尔瓦多进口",LuceneConstant.SALVATORE_IMPROT_STRING);
		countryMap.put("萨尔瓦多出口",LuceneConstant.SALVATORE_EXPORT_STRING);
		countryMap.put("英国进口",LuceneConstant.UK_IMPORT_STRING);
		countryMap.put("乌克兰进口",LuceneConstant.UKRAINE_IMPORT_STRING);
		countryMap.put("乌拉圭进口",LuceneConstant.URUGUAY_IMPORT_STRING);
		countryMap.put("乌拉圭出口",LuceneConstant.URUGUAY_EXPORT_STRING);
		countryMap.put("美国进口",LuceneConstant.USA_IMPORT_STRING);
		countryMap.put("委内瑞拉进口",LuceneConstant.VENEZUELAID_IMPORT_STRING);
		countryMap.put("委内瑞拉出口",LuceneConstant.VENEZUELAID_EXPORT_STRING);
		countryMap.put("越南进口",LuceneConstant.VIETNAM_IMPORT_STRING);
		countryMap.put("越南出口",LuceneConstant.VIETNAM_EXPORT_STRING);
		countryMap.put("中国",LuceneConstant.CHINA_EIGHT_STRING);
		countryMap.put("玻利维亚进口",LuceneConstant.BOLIVIA_IMPORT_STRING);
		
		
		/* initial country information*/
	    	countryEnMap.put("阿根廷进口",EnglishCountryConstant.ARGENTINA_IMPORT_STRING);
			countryEnMap.put("阿根廷出口",EnglishCountryConstant.ARGENTINA_EXPORT_STRING);
			countryEnMap.put("巴西进口",EnglishCountryConstant.BRAZIL_IMPORT_STRING);
			countryEnMap.put("智利进口",EnglishCountryConstant.CHILE_IMPORT_STRING);
			countryEnMap.put("智利出口",EnglishCountryConstant.CHILE_EXPORT_STRING);
			countryEnMap.put("哥伦比亚进口",EnglishCountryConstant.COLOM_IMPORT_STRING);
			countryEnMap.put("哥伦比亚出口",EnglishCountryConstant.COLOM_EXPORT_STRING);
			countryEnMap.put("哥斯达黎加进口",EnglishCountryConstant.COSTARICA_IMPORT_STRING);
			countryEnMap.put("哥斯达黎加出口",EnglishCountryConstant.COSTARICA_EXPORT_STRING);
			countryEnMap.put("厄瓜多尔进口",EnglishCountryConstant.ECUADOR_IMPORT_STRING);
			countryEnMap.put("厄瓜多尔出口",EnglishCountryConstant.ECUADOR_EXPORT_STRING);
			countryEnMap.put("危地马拉进口",EnglishCountryConstant.GUATEMALA_IMPORT_STRING);
			countryEnMap.put("危地马拉出口",EnglishCountryConstant.GUATEMALA_EXPORT_STRING);
			countryEnMap.put("洪都拉斯进口",EnglishCountryConstant.HONDURAS_IMPORT_STRING);
			countryEnMap.put("洪都拉斯出口",EnglishCountryConstant.HONDURAS_EXPORT_STRING);
			countryEnMap.put("印度进口",EnglishCountryConstant.INDIA_IMPORT_STRING);
			countryEnMap.put("印度出口",EnglishCountryConstant.INDIA_EXPORT_STRING);
			countryEnMap.put("韩国",EnglishCountryConstant.KOREA_STRING);
			countryEnMap.put("墨西哥进口",EnglishCountryConstant.MEXICON_IMPORT_STRING);
			countryEnMap.put("尼加拉瓜进口",EnglishCountryConstant.NICARAGUA_IMPORT_STRING);
			countryEnMap.put("尼加拉瓜出口",EnglishCountryConstant.NICARAGUA_EXPORT_STRING);
			countryEnMap.put("巴基斯坦进口",EnglishCountryConstant.PAKISTAN_IMPORT_STRING);
			countryEnMap.put("巴拿马进口",EnglishCountryConstant.PANAMA_IMPORT_STRING);
			countryEnMap.put("巴拿马出口",EnglishCountryConstant.PANAMA_EXPORT_STRING);
			countryEnMap.put("巴拉圭进口",EnglishCountryConstant.PARAGUAY_IMPORT_STRING);
			countryEnMap.put("巴拉圭出口",EnglishCountryConstant.PARAGUAY_EXPORT_STRING);
			countryEnMap.put("秘鲁进口",EnglishCountryConstant.PERU_IMPORT_STRING);
			countryEnMap.put("秘鲁出口",EnglishCountryConstant.PERU_EXPORT_STRING);
			countryEnMap.put("俄罗斯进口",EnglishCountryConstant.RUSSIAN_IMPORT_STRING);
			countryEnMap.put("俄罗斯出口",EnglishCountryConstant.RUSSIAN_EXPORT_STRING);
			countryEnMap.put("萨尔瓦多进口",EnglishCountryConstant.SALVATORE_IMPROT_STRING);
			countryEnMap.put("萨尔瓦多出口",EnglishCountryConstant.SALVATORE_EXPORT_STRING);
			countryEnMap.put("英国进口",EnglishCountryConstant.UK_IMPORT_STRING);
			countryEnMap.put("乌克兰进口",EnglishCountryConstant.UKRAINE_IMPORT_STRING);
			countryEnMap.put("乌拉圭进口",EnglishCountryConstant.URUGUAY_IMPORT_STRING);
			countryEnMap.put("乌拉圭出口",EnglishCountryConstant.URUGUAY_EXPORT_STRING);
			countryEnMap.put("美国进口",EnglishCountryConstant.USA_IMPORT_STRING);
			countryEnMap.put("委内瑞拉进口",EnglishCountryConstant.VENEZUELAID_IMPORT_STRING);
			countryEnMap.put("委内瑞拉出口",EnglishCountryConstant.VENEZUELAID_EXPORT_STRING);
			countryEnMap.put("越南进口",EnglishCountryConstant.VIETNAM_IMPORT_STRING);
			countryEnMap.put("越南出口",EnglishCountryConstant.VIETNAM_EXPORT_STRING);
			countryEnMap.put("中国",EnglishCountryConstant.CHINA_EIGHT_STRING);
			countryEnMap.put("玻利维亚进口",EnglishCountryConstant.BOLIVIA_IMPORT_STRING);
		
		/*初始化报告对应的中文描述信息*/
		reportChinaTypeMap.put(DataSearchConstantUtil.HSCODE, DataSearchConstantUtil.HSCODE_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.GOODSDESC, DataSearchConstantUtil.GOODSDESC_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.QS, DataSearchConstantUtil.QS_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.JKS, DataSearchConstantUtil.JKS_TEN_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.CKS, DataSearchConstantUtil.CKS_TEN_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.QYG, DataSearchConstantUtil.QYG_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.DDG, DataSearchConstantUtil.DDG_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.YCG, DataSearchConstantUtil.YCG_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.MDG, DataSearchConstantUtil.MDG_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.TZR, DataSearchConstantUtil.TZR_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.ZZS, DataSearchConstantUtil.ZZS_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.HGCS, DataSearchConstantUtil.HGCS_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.ZG_JKS, DataSearchConstantUtil.ZG_JKS_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.ZG_PORT, DataSearchConstantUtil.ZG_PORT_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.ZG_SFHD, DataSearchConstantUtil.ZG_SFHD_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.YOY,DataSearchConstantUtil.YOY_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.MYOY,DataSearchConstantUtil.MYOY_ZH);
		reportChinaTypeMap.put(DataSearchConstantUtil.MOM,DataSearchConstantUtil.MOM_ZH);
		
		
		/*初始化报告对应的中文描述信息*/
		reportEnTypeMap.put(DataSearchConstantUtil.HSCODE, DataSearchConstantUtil.HSCODE_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.GOODSDESC, DataSearchConstantUtil.GOODSDESC_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.QS, DataSearchConstantUtil.QS_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.JKS, DataSearchConstantUtil.JKS_TEN_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.CKS, DataSearchConstantUtil.CKS_TEN_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.QYG, DataSearchConstantUtil.QYG_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.DDG, DataSearchConstantUtil.DDG_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.YCG, DataSearchConstantUtil.YCG_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.MDG, DataSearchConstantUtil.MDG_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.TZR, DataSearchConstantUtil.TZR_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.ZZS, DataSearchConstantUtil.ZZS_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.HGCS, DataSearchConstantUtil.HGCS_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.ZG_JKS, DataSearchConstantUtil.ZG_JKS_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.ZG_PORT, DataSearchConstantUtil.ZG_PORT_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.ZG_SFHD, DataSearchConstantUtil.ZG_SFHD_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.YOY,DataSearchConstantUtil.YOY_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.MYOY,DataSearchConstantUtil.MYOY_EN);
		reportEnTypeMap.put(DataSearchConstantUtil.MOM,DataSearchConstantUtil.MOM_EN);
		
		
		
		
		/*初始化英文字段对应的中文字段信息*/
		reportChinaFieldMap.put(DataSearchConstantUtil.HSCODE_LUCENE, "海关编码");
		reportChinaFieldMap.put(DataSearchConstantUtil.GOOD_DESC_LUCENE, "产品描述");
		reportChinaFieldMap.put(DataSearchConstantUtil.CIF_VALUE_LUCENE, "cif价格");
		reportChinaFieldMap.put(DataSearchConstantUtil.G_WEIGHT_LUCENE, "毛重");
		reportChinaFieldMap.put(DataSearchConstantUtil.DATE_LUCENE, "日期");
		reportChinaFieldMap.put(DataSearchConstantUtil.FOB_VALUE_LUCENE, "fob价格");
		reportChinaFieldMap.put(DataSearchConstantUtil.QUANTITY_LUCENE, "数量");
		reportChinaFieldMap.put(DataSearchConstantUtil.IMPORTER_LUCENE, "进口商");
		reportChinaFieldMap.put(DataSearchConstantUtil.ORIGIN_COUNTRY_LUCENE, "原产国");
		reportChinaFieldMap.put(DataSearchConstantUtil.EXPORTER_LUCENE, "出口商");
		reportChinaFieldMap.put(DataSearchConstantUtil.START_PORT_LUCENE, "起运港");
		reportChinaFieldMap.put(DataSearchConstantUtil.END_PORT_LUCENE, "抵达港");
		reportChinaFieldMap.put(DataSearchConstantUtil.DEST_COUNTRY_LUCENE, "目的国");
		reportChinaFieldMap.put(DataSearchConstantUtil.N_WEIGHT_LUCENE, "净重");
		reportChinaFieldMap.put(DataSearchConstantUtil.BL_NUMBER_LUCENE, "提单号");
		reportChinaFieldMap.put(DataSearchConstantUtil.PACKAGES_LUCENE, "包装数");
		reportChinaFieldMap.put(DataSearchConstantUtil.NOTIFIER_LUCENE, "通知人");
		reportChinaFieldMap.put(DataSearchConstantUtil.TRADE_TYPE_LUCENE, "贸易类型");
		reportChinaFieldMap.put(DataSearchConstantUtil.CUSTOMS_LUCENE, "海关城市");
		reportChinaFieldMap.put(DataSearchConstantUtil.MANUFACTURE, "制造商");
		// WangBo 为 英国,中国,添加 公司名称
		reportChinaFieldMap.put(DataSearchConstantUtil.COMPANYNAME, "公司名称");
		// WangBo 收发货地
		reportChinaFieldMap.put(DataSearchConstantUtil.PRODUCTPLACE, "收发货地");
	 }
	 
	 
	 /*
	  * get country english name
	  */
	 public static String queryCountryEnglishName(String chinaName){
		 return countryEnMap.get(chinaName);
	 }
	/*
	 * 获取国家的英文名称
	 */
	 public static String queryCountryEnName(String chinaName) {
	   return countryMap.get(chinaName);
	 }
	 /*
	  * 获取国家的中文名称
	  */
	 public static String queryCountryCnName(String englishName) {
		  return countryMap.inverse().get(englishName);
	 }
	 /*
	  * 获取报告类型对应的中文描述信息
	  */
	 public static String getReportTypeZhName(String reportType) {
		 return reportChinaTypeMap.get(reportType);
	 }
	 /*
	  * Get report type corresponding English Information
	  */
	 public static String getReportTypeEnName(String reportType) {
		 return reportEnTypeMap.get(reportType);
	 }
		
	/*
	 * 获取英文字段对应的中文字段
	 */
	public static String queryFieldZhName(String field) {
		return reportChinaFieldMap.get(field);
	}
	public static void main(String[] args) {
		System.out.println(InitCountryCENameUtil.queryCountryEnName("巴拿马进口"));
	}
}
