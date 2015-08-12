package com.njyb.gbdbas.util;

import java.util.ArrayList;
import java.util.List;

import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;

/**
 * 存放所有国家的名称工具类
 * @author XL
 * @date 2015-04-16
 * @version 标准版
 */
public class GetAllCountryNameUtil {

	/**
	 * 存放国家英文名称集合
	 * @return List<String>
	 */
	public static List<String> getAllCountryName()
	{
		List<String> countryList = new ArrayList<String>();
		countryList.add(LuceneConstant.ARGENTINA_IMPORT_STRING);
		countryList.add(LuceneConstant.ARGENTINA_EXPORT_STRING);
		countryList.add(LuceneConstant.BRAZIL_IMPORT_STRING);
		countryList.add(LuceneConstant.CHILE_IMPORT_STRING);
		countryList.add(LuceneConstant.CHILE_EXPORT_STRING);
		countryList.add(LuceneConstant.COLOM_IMPORT_STRING);
		countryList.add(LuceneConstant.COLOM_EXPORT_STRING);
		countryList.add(LuceneConstant.COSTARICA_IMPORT_STRING);
		countryList.add(LuceneConstant.COSTARICA_EXPORT_STRING);
		countryList.add(LuceneConstant.ECUADOR_IMPORT_STRING);
		countryList.add(LuceneConstant.ECUADOR_EXPORT_STRING);
		countryList.add(LuceneConstant.GUATEMALA_IMPORT_STRING);
		countryList.add(LuceneConstant.GUATEMALA_EXPORT_STRING);
		countryList.add(LuceneConstant.HONDURAS_IMPORT_STRING);
		countryList.add(LuceneConstant.HONDURAS_EXPORT_STRING);
		countryList.add(LuceneConstant.INDIA_IMPORT_STRING);
		countryList.add(LuceneConstant.INDIA_EXPORT_STRING);
		countryList.add(LuceneConstant.KOREA_STRING);
		countryList.add(LuceneConstant.MEXICON_IMPORT_STRING);
		countryList.add(LuceneConstant.NICARAGUA_IMPORT_STRING);
		countryList.add(LuceneConstant.NICARAGUA_EXPORT_STRING);
		countryList.add(LuceneConstant.PAKISTAN_IMPORT_STRING);
		countryList.add(LuceneConstant.PANAMA_IMPORT_STRING);
		countryList.add(LuceneConstant.PANAMA_EXPORT_STRING);
		countryList.add(LuceneConstant.PARAGUAY_IMPORT_STRING);
		countryList.add(LuceneConstant.PARAGUAY_EXPORT_STRING);
		countryList.add(LuceneConstant.PERU_IMPORT_STRING);
		countryList.add(LuceneConstant.PERU_EXPORT_STRING);
		countryList.add(LuceneConstant.RUSSIAN_IMPORT_STRING);
		countryList.add(LuceneConstant.RUSSIAN_EXPORT_STRING);
		countryList.add(LuceneConstant.SALVATORE_IMPROT_STRING);
		countryList.add(LuceneConstant.SALVATORE_EXPORT_STRING);
		countryList.add(LuceneConstant.UK_IMPORT_STRING);
		countryList.add(LuceneConstant.UKRAINE_IMPORT_STRING);
		countryList.add(LuceneConstant.URUGUAY_IMPORT_STRING);
		countryList.add(LuceneConstant.URUGUAY_EXPORT_STRING);
		countryList.add(LuceneConstant.USA_IMPORT_STRING);
		countryList.add(LuceneConstant.VENEZUELAID_IMPORT_STRING);
		countryList.add(LuceneConstant.VENEZUELAID_EXPORT_STRING);
		countryList.add(LuceneConstant.VIETNAM_IMPORT_STRING);
		countryList.add(LuceneConstant.VIETNAM_EXPORT_STRING);
		countryList.add(LuceneConstant.CHINA_EIGHT_STRING);
		return countryList;
	}
}
