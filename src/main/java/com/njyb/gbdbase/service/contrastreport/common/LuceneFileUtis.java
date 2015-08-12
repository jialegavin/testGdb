package com.njyb.gbdbase.service.contrastreport.common;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
/**
 * 公共类
 * @author 章华才
 */
public class LuceneFileUtis {
	
	
	public static String [] newFields;
	public static String [] newValues;
	
	public static String [] secondFields;
	public static String [] secondValues;
	
	public static String newCountryName;
	public static String secondeCountryName;
	
	public static String newAdd;
	public static String secondAdd;

	public static String newIe;
	public static String secondeIe;
	/**
	 * 根据国家名称 返回对应国家的字段
	 * @return 章华才
	 */
	public static String getFilterKeyName(String countryName,String type) {
		String freeTime=type + "_";
		// 阿根廷进口
		if (countryName.equals(LuceneConstant.ARGENTINA_IMPORT_STRING)) {
			return freeTime + LuceneConstant.ARGENTINA_IMPORT_STRING;
		}
//		// 阿根廷出口
		if (countryName.equals(LuceneConstant.ARGENTINA_EXPORT_STRING)) {
			return freeTime+LuceneConstant.ARGENTINA_EXPORT_STRING;
		}
		// 巴西进口
		if (countryName.equals(LuceneConstant.BRAZIL_IMPORT_STRING)) {
			return freeTime + LuceneConstant.BRAZIL_IMPORT_STRING;
		}
		//智利进口
		if (countryName.equals(LuceneConstant.CHILE_IMPORT_STRING)) {
			return freeTime + LuceneConstant.CHILE_IMPORT_STRING;
		}
		//智利出口
		if (countryName.equals(LuceneConstant.CHILE_EXPORT_STRING)) {
			return freeTime + LuceneConstant.CHILE_EXPORT_STRING;
		}
		//中国
		if (countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)) {
			return freeTime + LuceneConstant.CHINA_EIGHT_STRING;
		}
		//哥伦比亚进口
		if (countryName.equals(LuceneConstant.COLOM_IMPORT_STRING)) {
			return freeTime + LuceneConstant.COLOM_IMPORT_STRING;
		}
		//哥伦比亚出口
		if(countryName.equals(LuceneConstant.COLOM_EXPORT_STRING)){
			return freeTime + LuceneConstant.COLOM_EXPORT_STRING;
		}
		//哥斯达黎加进口
		if(countryName.equals(LuceneConstant.COSTARICA_IMPORT_STRING)){
			return freeTime + LuceneConstant.COSTARICA_IMPORT_STRING;
		}
		//哥斯达黎加出口
		if(countryName.equals(LuceneConstant.COSTARICA_EXPORT_STRING)){
			return freeTime + LuceneConstant.COSTARICA_EXPORT_STRING;
		}
		//厄瓜多尔进口
		if(countryName.equals(LuceneConstant.ECUADOR_IMPORT_STRING)){
			return freeTime + LuceneConstant.ECUADOR_IMPORT_STRING;
		}
		//厄瓜多尔出口
		if(countryName.equals(LuceneConstant.ECUADOR_EXPORT_STRING)){
			return freeTime + LuceneConstant.ECUADOR_EXPORT_STRING;
		}
		//危地马拉进口
		if(countryName.equals(LuceneConstant.GUATEMALA_IMPORT_STRING)){
			return freeTime + LuceneConstant.GUATEMALA_IMPORT_STRING;
		}
		//危地马拉出口
		if(countryName.equals(LuceneConstant.GUATEMALA_EXPORT_STRING)){
			return freeTime + LuceneConstant.GUATEMALA_EXPORT_STRING;
		}
		//洪都拉斯进口
		if(countryName.equals(LuceneConstant.HONDURAS_IMPORT_STRING)){
			return freeTime + LuceneConstant.HONDURAS_IMPORT_STRING;
		}
		//洪都拉斯出口
		if(countryName.equals(LuceneConstant.HONDURAS_EXPORT_STRING)){
			return freeTime + LuceneConstant.HONDURAS_EXPORT_STRING;
		}
		//印度进口
		if(countryName.equals(LuceneConstant.INDIA_IMPORT_STRING)){
			return freeTime + LuceneConstant.INDIA_IMPORT_STRING;
		}
		//韩国
		if(countryName.equals(LuceneConstant.KOREA_STRING)){
			return freeTime + LuceneConstant.KOREA_STRING;
		}
		//墨西哥进口
		if(countryName.equals(LuceneConstant.MEXICON_IMPORT_STRING)){
			return freeTime + LuceneConstant.MEXICON_IMPORT_STRING;
		}
		//尼加拉瓜进口
		if(countryName.equals(LuceneConstant.NICARAGUA_IMPORT_STRING)){
			return freeTime + LuceneConstant.NICARAGUA_IMPORT_STRING;
		}
		//尼加拉瓜出口
		if(countryName.equals(LuceneConstant.NICARAGUA_EXPORT_STRING)){
			return freeTime + LuceneConstant.NICARAGUA_EXPORT_STRING;
		}
		//巴基斯坦
		if(countryName.equals(LuceneConstant.PAKISTAN_IMPORT_STRING)){
			return freeTime + LuceneConstant.PAKISTAN_IMPORT_STRING;
		}
		//巴拿马进口
		if(countryName.equals(LuceneConstant.PANAMA_IMPORT_STRING)){
			return freeTime + LuceneConstant.PANAMA_IMPORT_STRING;
		}
		//巴拿马出口
		if(countryName.equals(LuceneConstant.PANAMA_EXPORT_STRING)){
			return freeTime + LuceneConstant.PANAMA_EXPORT_STRING;
		}
		//巴拉圭进口
		if(countryName.equals(LuceneConstant.PARAGUAY_IMPORT_STRING)){
			return freeTime + LuceneConstant.PARAGUAY_IMPORT_STRING;
		}
		//巴拉圭出口
		if(countryName.equals(LuceneConstant.PARAGUAY_EXPORT_STRING)){
			return freeTime + LuceneConstant.PARAGUAY_EXPORT_STRING;
		}
		//秘鲁进口
		if(countryName.equals(LuceneConstant.PERU_IMPORT_STRING)){
			return freeTime + LuceneConstant.PERU_IMPORT_STRING;
		}
		//秘鲁出口
		if(countryName.equals(LuceneConstant.PERU_EXPORT_STRING)){
			return freeTime + LuceneConstant.PERU_EXPORT_STRING;
		}
		//萨尔瓦多进口
		if(countryName.equals(LuceneConstant.SALVATORE_IMPROT_STRING)){
			return freeTime + LuceneConstant.SALVATORE_IMPROT_STRING;
		}
		//萨尔瓦多出口
		if(countryName.equals(LuceneConstant.SALVATORE_EXPORT_STRING)){
			return freeTime + LuceneConstant.SALVATORE_EXPORT_STRING;
		}
		//英国
		if(countryName.equals(LuceneConstant.UK_IMPORT_STRING)){
			return freeTime + LuceneConstant.UK_IMPORT_STRING;
		}
		//乌克兰进口
		if(countryName.equals(LuceneConstant.UKRAINE_IMPORT_STRING)){
			return freeTime + LuceneConstant.UKRAINE_IMPORT_STRING;
		}
		//乌克兰出口
		if(countryName.equals(LuceneConstant.UKRAINE_IMPORT_STRING)){
			return freeTime + LuceneConstant.UKRAINE_IMPORT_STRING;
		}
		//乌拉圭进口
		if(countryName.equals(LuceneConstant.URUGUAY_IMPORT_STRING)){
			return freeTime + LuceneConstant.URUGUAY_IMPORT_STRING;
		}
		//乌拉圭出口
		if(countryName.equals(LuceneConstant.URUGUAY_EXPORT_STRING)){
			return freeTime + LuceneConstant.URUGUAY_EXPORT_STRING;
		}
		
		//美国进口
		if(countryName.equals(LuceneConstant.USA_IMPORT_STRING)){
			return freeTime + LuceneConstant.USA_IMPORT_STRING;
		}
		//委内瑞拉进口
		if(countryName.equals(LuceneConstant.VENEZUELAID_IMPORT_STRING)){
			return freeTime + LuceneConstant.VENEZUELAID_IMPORT_STRING;
		}
		//委内瑞拉出口
		if(countryName.equals(LuceneConstant.VENEZUELAID_EXPORT_STRING)){
			return freeTime + LuceneConstant.VENEZUELAID_EXPORT_STRING;
		}
		//越南出口
		if(countryName.equals(LuceneConstant.VIETNAM_IMPORT_STRING)){
			return freeTime + LuceneConstant.VIETNAM_IMPORT_STRING;
		}
		//越南出口
		if(countryName.equals(LuceneConstant.VIETNAM_EXPORT_STRING)){
			return freeTime + LuceneConstant.VIETNAM_EXPORT_STRING;
		}
		return null;
	}
	
	
	/**
	 * 获取排序的字段
	 * @param sortKey
	 * @return
	 */
	public static  StringBuffer getSortName(String sortKey) {
		StringBuffer methodName = new StringBuffer();
		methodName.append("get");
		methodName.append(sortKey.substring(0, 1).toUpperCase());
		methodName.append(sortKey.substring(1));
		return methodName;
	}
	//根据指定字段--对集合进行降序排序
	@SuppressWarnings("all")
	public static <T> void executeSort(List<T>ls,Comparator comp){
		Collections.sort(ls,comp);
	}

	
	/**
	 * 判断量个数组是否相等
	 * @param bt1
	 * @param bt2
	 * @return
	 */
	public static boolean judgeEqual(String[] bt1, String[] bt2) {
		if(bt1 != null && bt2 != null)
		{
			if(bt1.length!=bt2.length){
				return false;
			}
			int length = bt1.length;
			boolean b = true;
			for (int i = 0; i < length; i++) {
				if (!bt1[i].trim().equals(bt2[i].trim())) {
					b = false;
					
				}
			}
			return b;
		}
		return false;
		/* Arrays.sort(bt1); 
		 Arrays.sort(bt2); 
		 if (Arrays.equals(bt1, bt2)) { 
			 return true;
		 } else
		 { 
			 return false;
		 }*/
	}
	
	
	/**
	 * 判断量个数组是否相等
	 * @param bt1
	 * @param bt2
	 * @return
	 */
	public static boolean judgeEqualBySort(String[] bt1, String[] bt2) {
		 Arrays.sort(bt1); 
		 Arrays.sort(bt2); 
		 if (Arrays.equals(bt1, bt2)) { 
			 return true;
		 } else
		 { 
			 return false;
		 }
	}
	/**
	 * 判断量个数组是否相等
	 * @param bt1
	 * @param bt2
	 * @return
	 */
	public static boolean judgeField(String newCountryName,String countryName) {
		if(newCountryName.equals(countryName)){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据字段名称匹配数据库中的字段
	 * @param strName
	 * @return chileImportId
	 */
	 public static String processField( String field ) {
		 //主键编号
		 if(field.contains("ImportId"))
		 {
			 int i=field.indexOf("Import");
			 int j=field.indexOf("Id");
			 String name=field.substring(0,i)+"_"+field.substring(i,j)+"_"+field.substring(j,field.length());
			 return name;
		 }
		 else if(field.contains("ExportId"))
		 {
			 int i=field.indexOf("Export");
			 int j=field.indexOf("Id");
			 String name=field.substring(0,i)+"_"+field.substring(i,j)+"_"+field.substring(j,field.length());
			 return name;
		 }
		 //产品描述
		 else if(field.contains("goodsDesc"))
		 {
			 int i=field.indexOf("Desc");
			 String name=field.substring(0,i)+"_"+field.substring(i,field.length());
			 return name;
		 }
		 //G重量
		 else if(field.contains("grossWeight"))
		 {
			 int i=field.indexOf("Weight");
			 String name="G_"+field.substring(i,field.length());
			 return name;
		 }
		 //N重量
		 else if(field.contains("netWeight"))
		 {
			 int i=field.indexOf("Weight");
			 String name="N_"+field.substring(i,field.length());
			 return name;
		 }
		 //cif重量
		 else if(field.contains("cifValue"))
		 {
			 int i=field.indexOf("Value");
			 String name=field.substring(0,i)+"_"+field.substring(i,field.length());
			 return name;
		 }
		 //fob重量
		 else if(field.contains("fobValue"))
		 {
			 int i=field.indexOf("Value");
			 String name=field.substring(0,i)+"_"+field.substring(i,field.length());
			 return name;
		 }
		 return null;
	 }
	 
	 
	 	/**
		 * 获取索引目录
		 * @author jiahp
		 * @param map
		 * @param indexPath
		 * @return
		 */
		public static File getFile(Map map, String indexPath) {
			File file = new File((IConstantUtil.COMMON_INDEXPATH.toString() + map
					.get(indexPath).toString()).toString());
			return file;
		}
}
