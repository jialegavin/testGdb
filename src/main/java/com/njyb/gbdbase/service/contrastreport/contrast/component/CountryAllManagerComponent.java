package com.njyb.gbdbase.service.contrastreport.contrast.component;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.beanutils.BeanUtils;
import org.jfree.util.Log;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.datasearch.brazil.BrazilImportModel;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaExportModel;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaImportModel;
import com.njyb.gbdbase.model.datasearch.chile.ChileExportModel;
import com.njyb.gbdbase.model.datasearch.chile.ChileImportModel;
import com.njyb.gbdbase.model.datasearch.colombia.ColombiaExportModel;
import com.njyb.gbdbase.model.datasearch.colombia.ColombiaImportModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ResultFieldModel;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.contrastreport.querybean.CommonSearchModel;
import com.njyb.gbdbase.model.datasearch.chinaEight.ChinaEightModel;
import com.njyb.gbdbase.model.datasearch.costarica.CostaricaExportModel;
import com.njyb.gbdbase.model.datasearch.costarica.CostaricaImportModel;
import com.njyb.gbdbase.model.datasearch.ecuador.EcuadorExportModel;
import com.njyb.gbdbase.model.datasearch.ecuador.EcuadorImportModel;
import com.njyb.gbdbase.model.datasearch.guatemala.GuatemalaExportModel;
import com.njyb.gbdbase.model.datasearch.guatemala.GuatemalaImportModel;
import com.njyb.gbdbase.model.datasearch.india.IndiaImportModel;
import com.njyb.gbdbase.model.datasearch.korea.KoreaModel;
import com.njyb.gbdbase.model.datasearch.mexico.MexicoImportModel;
import com.njyb.gbdbase.model.datasearch.nicaragua.NicaraguaExportModel;
import com.njyb.gbdbase.model.datasearch.nicaragua.NicaraguaImportModel;
import com.njyb.gbdbase.model.datasearch.pakistan.PakistanImportModel;
import com.njyb.gbdbase.model.datasearch.panama.PanamaExportModel;
import com.njyb.gbdbase.model.datasearch.panama.PanamaImportModel;
import com.njyb.gbdbase.model.datasearch.paraguay.ParaguayExportModel;
import com.njyb.gbdbase.model.datasearch.paraguay.ParaguayImportModel;
import com.njyb.gbdbase.model.datasearch.peru.PeruExportModel;
import com.njyb.gbdbase.model.datasearch.peru.PeruImportModel;
import com.njyb.gbdbase.model.datasearch.salvatore.SalvatoreExportModel;
import com.njyb.gbdbase.model.datasearch.salvatore.SalvatoreImportModel;
import com.njyb.gbdbase.model.datasearch.uk.UkImportModel;
import com.njyb.gbdbase.model.datasearch.uruguay.UruguayExportModel;
import com.njyb.gbdbase.model.datasearch.uruguay.UruguayImportModel;
import com.njyb.gbdbase.model.datasearch.venezuela.VenezuelaExportModel;
import com.njyb.gbdbase.model.datasearch.venezuela.VenezuelaImportModel;
import com.njyb.gbdbase.model.datasearch.vietnam.VietnamExportModel;
import com.njyb.gbdbase.model.datasearch.vietnam.VietnamImportModel;
import com.njyb.gbdbase.model.usermanagement.MixDataModel;
import com.njyb.gbdbase.model.usermanagement.PieDataModel;
import com.njyb.gbdbase.service.alldb.convertdata.IndiaIConvertModel;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.contrastreport.common.LuceneFileUtis;

/**
 * 所以查询国家的公用的方法
 * 
 * @author 章华才 2015-3-19
 */
@Component
@Scope("prototype")
public class CountryAllManagerComponent {

	/**
	 * 存储第一段时间内的汇总集合：做一个临时缓存
	 * 
	 * @return map
	 */
	private static Map<String, Object> firstMap = new HashMap<String, Object>();
	/**
	 * 存储第二段时间内的汇总集合：做一个临时缓存
	 * 
	 * @return map
	 */
	private static Map<String, Object> secondMap = new HashMap<String, Object>();

	/**
	 * 获取查询字段
	 * 
	 * @param map
	 * @return String []
	 */
	@SuppressWarnings("all")
	public String[] findByFieldKey(Map<String, List<String>> map) {
		if (map == null) {
			return null;
		}
		synchronized (this) {
			List<String> fieldKey = (List<String>) map.get("fieldKey");
			String[] key = new String[fieldKey.size()];
			for (int i = 0; i < fieldKey.size(); i++) {
				key[i] = fieldKey.get(i);
			}
			return key;
		}
	}

	/**
	 * 获取查询字段value
	 * 
	 * @param map
	 * @return String []
	 */
	@SuppressWarnings("all")
	public String[] findByFieldValue(Map<String, List<String>> map) {
		if (map == null) {
			return null;
		}
		synchronized (this) {
			List<String> fieldValue = (List<String>) map.get("fieldValue");
			String[] value = new String[fieldValue.size()];
			for (int i = 0; i < fieldValue.size(); i++) {
				value[i] = fieldValue.get(i);
			}
			return value;
		}
	}

	/**
	 * 数据分页
	 * 
	 * @param request
	 * @return page
	 */
	public PageBeanUtil getPageBeanUtil(HttpServletRequest request, String num) {
		int pageIndex = Integer
				.valueOf((null == request.getParameter("page")) ? "1"
						: (request.getParameter("page")));
		int pageSize = Integer
				.valueOf((null == request.getParameter("rows")) ? (num)
						: (request.getParameter("rows")));
		PageBeanUtil beanUtil = new PageBeanUtil();
		beanUtil.setPageIndex(pageIndex);
		beanUtil.setPageSize(pageSize);
		return beanUtil;
	}

	/**
	 * 设置查询参数
	 * 
	 * @param request
	 *            请求参数
	 * @param countryName
	 *            国家名称
	 * @param bean
	 *            查询bean
	 * @return Map
	 */
	@SuppressWarnings("all")
	public Map<String, List<String>> setParameters(HttpServletRequest request,
			String countryName, CommonSearchModel bean) {
		// 初始化配置文件
		new LoadPropertiesUtil().init(request, "contrastreCenter");
		List<String> fieldKey = new ArrayList<String>();
		List<String> fieldValue = new ArrayList<String>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String[] fields = null;
		if (countryName.equals("china") || countryName.equals("korea")) {
			fields = LoadPropertiesUtil.getProp("query_china");
		} else {
			fields = LoadPropertiesUtil.getProp("query_common");
		}
		if (fields == null) {
			return null;
		}
		for (String str : fields) {
			str = str.trim();
			if (str.equals("hscode") && bean.getHscode() != null
					&& !bean.getHscode().equals("")
					&& !"此国家没有海关编码！".equals(bean.getHscode())) {
				fieldKey.add(str);
				fieldValue.add(bean.getHscode());
			} else if (str.equals("date") && bean.getFirstDate() != null
					&& !bean.getFirstDate().equals("")) {
				fieldKey.add(str);
				fieldValue.add(bean.getFirstDate());

			} else if (str.equals("goods_desc") && bean.getGoodDesc() != null
					&& !bean.getGoodDesc().equals("")
					&& !"".equals(bean.getGoodDesc())) {
				fieldKey.add(str);
				fieldValue.add(bean.getGoodDesc());

			} else if (str.equals("trade_type") && bean.getTradeType() != null
					&& !bean.getTradeType().equals("")) {
				fieldKey.add(str);
				fieldValue.add(bean.getTradeType());

			}
		}
		map.put("fieldKey", fieldKey);
		map.put("fieldValue", fieldValue);
		return map;
	}

	/**
	 * 字符串首字母大写
	 * 
	 * @param src
	 * @return String
	 */
	public String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}

	/**
	 * 对比新增进口商
	 * 
	 * @param firstList
	 *            第一段时间查询的集合
	 * @param secondList
	 *            第二段时间查询的集合
	 * @param reportType
	 *            类型
	 * @return T
	 */
	@SuppressWarnings("all")
	public <T> List<T> addImportAndExport(List<T> firstList,
			List<T> secondList, String reportType, String countryName) {
		// 非空判断
		if (firstList != null && firstList.size() > 0 && secondList != null
				&& secondList.size() > 0) {
			cacheData(firstList, secondList, reportType, countryName, null);
			// 新增数据后的新集合
			List<T> newList = new ArrayList<T>();
			try {

				for (String s : secondMap.keySet()) {

					// 取出第二段时间内的进出口商到第一段时间内的Map集合当中去找，
					// 如果有就是没有新增，如果没有就表示新增的进出口商
					if (firstMap.get(s) == null) {

						// 将新增出来进出口商的加入到新集合当中
						newList.add((T) secondMap.get(s));

					}
				}

				return newList;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	/**
	 * 对比流失进出口
	 * 
	 * @param firstList
	 *            第一段时间集合
	 * @param secondList
	 *            第二段时间集合
	 * @param reportType
	 *            类型
	 * @return T
	 */
	@SuppressWarnings("all")
	public <T> List<T> outImportAndExport(List<T> firstList,
			List<T> secondList, String reportType) {
		List<T> newList = new ArrayList<T>();
		// 非空判断
		if (firstList != null && firstList.size() > 0 && secondList != null
				&& secondList.size() > 0) {

			try {

				for (String s : firstMap.keySet()) {

					if (secondMap.get(s) == null) {
						newList.add((T) firstMap.get(s));
					}
				}

				return newList;

			} catch (Exception e) {
				// System.out.println(e.getMessage());
			}
		}

		return null;
	}

	/**
	 * 保持进出口商
	 * 
	 * @param firstList
	 *            第一段查询集合
	 * @param secondList
	 *            第二段查询集合
	 * @param type
	 *            类型
	 * @return T
	 */
	@SuppressWarnings("all")
	public <T> Map<String, T> KeepImportAndExport(List<T> firstList,
			List<T> secondList, String type) {

		List<T> list1 = new ArrayList<T>();
		List<T> list2 = new ArrayList<T>();

		Map<String, DataReportSumModel> map1 = new HashMap<String, DataReportSumModel>();
		Map<String, DataReportSumModel> map2 = new HashMap<String, DataReportSumModel>();

		Map<String, T> map = new HashMap<String, T>();
		// 非空判断
		if (firstList != null && firstList.size() > 0 && secondList != null
				&& secondList.size() > 0) {
			try {

				// 循环集合拿到同时存在的两个集合
				for (String s : firstMap.keySet()) {

					if (secondMap.get(s) != null) {
						list1.add((T) firstMap.get(s));
						list2.add((T) secondMap.get(s));
					}
				}
				map.put("data1", (T) list1);
				map.put("data2", (T) list2);
			} catch (Exception e) {
				// System.out.println(e.getMessage());
			}
		}

		return map;
	}

	/**
	 * 临时缓存数据
	 * 
	 * @param firstList
	 *            第一段时间集合
	 * @param secondList
	 *            第二段时间集合
	 * @param type
	 *            类型
	 */
	public <T> void cacheData(List<T> firstList, List<T> secondList,
			String type, String countryName, String[] values) {
		firstMap.clear();
		secondMap.clear();
		try {
			for (T t : firstList) {

				// 针对中国-韩国特殊国家处理
				if (countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)
						|| countryName.equals(LuceneConstant.KOREA_STRING)
						|| countryName.equals(LuceneConstant.UK_IMPORT_STRING)) {
					// 获取进口商名称
					String str = BeanUtils.getProperty(t, "company_name");
					// 格式金额和重量，数量
					firstMap.put(str, t);
				} else {
					if (type.equals("importer")) {
						// 获取进口商名称
						String str = BeanUtils.getProperty(t, "importer");
						// 格式金额和重量，数量
						firstMap.put(str, t);
					}

					else if (type.equals("exporter")) {
						// 获取出口商名称
						String str = BeanUtils.getProperty(t, "exporter");
						firstMap.put(str, t);
					}

				}

			}
			for (T t : secondList) {

				// 针对中国-韩国特殊国家处理
				if (countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)
						|| countryName.equals(LuceneConstant.KOREA_STRING)
						|| countryName.equals(LuceneConstant.UK_IMPORT_STRING)) {
					// 获取进口商名称
					String str = BeanUtils.getProperty(t, "company_name");
					// 格式金额和重量，数量
					secondMap.put(str, t);
				} else {
					if (type.equals("importer")) {
						// 获取进口商名称
						String str = BeanUtils.getProperty(t, "importer");
						secondMap.put(str, t);
					}

					else if (type.equals("exporter")) {
						// 获取出口商名称
						String str = BeanUtils.getProperty(t, "exporter");
						secondMap.put(str, t);
					}
				}
			}
			// System.out.println(firstMap.size() + "：放到map");
			// System.out.println(secondMap.size() + "：放到map");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 根据国家名称返回国家进口/出口
	 * 
	 * @param countryName
	 * @return
	 */
	public String judgeByCountryName(String imexType) {

		if (imexType.equals("import")) {
			return "importer";
		} else if (imexType.equals("export")) {
			return "exporter";
		} else {
			return "importer";
		}
	}

	/**
	 * 判断时间大小
	 * 
	 * @param firstTime
	 *            第一段时间
	 * @param endTime
	 *            第二段时间
	 * @return boolean
	 */
	public boolean dateDuiBi(String firstTime, String endTime) {
		SimpleDateFormat dateFormat = null;
		// 判断日期格式
		if (isValidDate(firstTime) && isValidDate(endTime)) {
			dateFormat = new SimpleDateFormat("yyyyMMdd");
		} else {
			dateFormat = new SimpleDateFormat("yyyyMM");
		}
		Date d = null;
		Date e = null;
		try {
			d = dateFormat.parse(firstTime);
			e = dateFormat.parse(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return e.before(d);
	}

	/**
	 * 正则表达式判断日期格式
	 * 
	 * @param sDate
	 *            日期
	 * @return boolean
	 * @author 章华才
	 */
	private boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}\\d{2}\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	
	public static String dateFormat(String date,String countryName){
		// 巴西-中国
		if (countryName.equals(LuceneConstant.BRAZIL_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)
				|| countryName.equals(LuceneConstant.VENEZUELAID_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.VENEZUELAID_EXPORT_STRING)
				|| countryName.equals(LuceneConstant.UK_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.MEXICON_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.KOREA_STRING)
				|| countryName.equals(LuceneConstant.HONDURAS_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.HONDURAS_EXPORT_STRING)) {
			
			return date.split("-")[0] +"-"+ date.split("-")[1];
		}
		return date;
	}
	
	/**
	 * 根据国家名称处理相应国家的日期格式-针对对比中心
	 * 
	 * @param model
	 * @param countryName
	 */
	public void isValidateByCountry(CommonSearchModel model,
			String countryName, String isFirst) {

		String date1 = null;
		String date2 = null;
		if (isFirst.equals("1")) {
			date1 = model.getBeginDate();
			date2 = model.getEndDate();
		} else {
			date1 = model.getBeginAddDate();
			date2 = model.getEndAddDate();
		}
		// 巴西-中国
		if (countryName.equals(LuceneConstant.BRAZIL_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)
				|| countryName.equals(LuceneConstant.VENEZUELAID_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.VENEZUELAID_EXPORT_STRING)
				|| countryName.equals(LuceneConstant.UK_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.MEXICON_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.KOREA_STRING)
				|| countryName.equals(LuceneConstant.HONDURAS_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.HONDURAS_EXPORT_STRING)) {
			date1 = date1.substring(0, 4) + "-" + date1.substring(5, 7);
			date2 = date2.substring(0, 4) + "-" + date2.substring(5, 7);

			if (isFirst.equals("1")) {
				model.setBeginDate(date1);
				model.setEndDate(date2);
			} else {
				model.setBeginAddDate(date1);
				model.setEndAddDate(date2);
			}
		}
	}

	/**
	 * 根据国家名称处理相应国家的日期格式
	 * 
	 * @param model
	 * @param countryName
	 */
	public String formartDateByCountry(String rq, String countryName) {
		Log.info(rq);
		String[] str = null;
		String[] rqs = null;
		StringBuffer sb = new StringBuffer();
		// 巴西-中国
		if (countryName.equals(LuceneConstant.BRAZIL_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)
				|| countryName.equals(LuceneConstant.VENEZUELAID_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.VENEZUELAID_EXPORT_STRING)
				|| countryName.equals(LuceneConstant.UK_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.MEXICON_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.KOREA_STRING)
				|| countryName.equals(LuceneConstant.HONDURAS_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.HONDURAS_EXPORT_STRING)) {
			String[] paramArray = rq.split(";");
			String dateParams = paramArray[1];
			String[] date = dateParams.split(",");
			// 使用正则表达式 判断 日期格式 WangBo 修改 针对收藏夹
			if (isValidDate1(date[0]) && isValidDate1(date[1])) {
				date[0] = date[0].substring(0, date[0].lastIndexOf("-"));
				date[1] = date[1].substring(0, date[1].lastIndexOf("-"));
				String returnStr = paramArray[0] + ";" + date[0] + ","
						+ date[1];
				return returnStr;
			}
			// 针对贸易情报
			if (rq.contains("/")) {
				str = rq.split(";");
				for (int i = 0; i < str.length; i++) {
					// 判断是否是日期
					if (str[i].contains("/")) {
						String[] time = str[i].split(",");
						if (isValidation(time[0])) {
							time[0] = time[0].substring(3, 7) + "-"
									+ time[0].substring(0, 2);
							time[1] = time[1].substring(3, 7) + "-"
									+ time[1].substring(0, 2);
							str[i] = time[0] + "," + time[1];
						}
						//加上同步块
						synchronized (this) {
							// 获取要查询的库里面的数据检索日期
							if (AuthorityFieldModel.getAuthorityFieldMap().get("july100") != null) {
								String[] t = str[i].split(",");
								t[1] = AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString();
								str[i] = t[0] + "," + t[1];
							}else if(AuthorityFieldModel.getAuthorityFieldMap().get("newTime")!=null){
								//页面填写的日期01/2012
								if(AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().contains("/")){
									time[0] = AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().split(",")[0].split("/")[1]+"-"
											  + AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().split(",")[0].split("/")[0];
								}
								//这个是数据库日期2012-01
								else{
									time[0] = AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().split(",")[0].split("-")[0]+"-"
											  + AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().split(",")[0].split("-")[1];
								}
								time[1] = AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().split(",")[1].split("-")[0] + "-"
										+ AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().split(",")[1].split("-")[1];
								str[i] = time[0] + "," + time[1];
							}
						}
					}
					sb.append(str[i] + ";");
				}
				return sb.toString();
			}
		}else{
			str = rq.split(";");
			// 获取要查询的库里面的数据检索日期
			if (AuthorityFieldModel.getAuthorityFieldMap().get("newTime") != null) {
				
				for (int i = 0; i < str.length; i++) {
					// 判断是否是日期
					if (str[i].contains("-")) {
						String[] time = str[i].split(",");
						//加上同步块
						synchronized (this) {
							if (isValidation2(time[0])) {
								time[0] = AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().split(",")[0];
								time[1] = AuthorityFieldModel.getAuthorityFieldMap().get("newTime").toString().split(",")[1];
								str[i] = time[0] + "," + time[1];
							}	
						}
					}
					sb.append(str[i] + ";");
				}
				return sb.toString();
			}
		}
		return rq;
		
	}

	/**
	 * 日期判断 wangbo
	 * 
	 * @param sDate
	 * @return
	 */
	private boolean isValidDate1(String sDate) {
		String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(sDate);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			return false;
		}
		return true;
	}

	/**
	 * 判断日期为格式为：MM/YYYY的格式
	 * 
	 * @param date
	 * @return boolean
	 */
	public static boolean isValidation(String date) {
		String eL = "[0-9]{2}/[0-9]{4}";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			return false;
		}
		return true;
	}
	/**
	 * 判断日期为格式为：MM/YYYY的格式
	 * 
	 * @param date
	 * @return boolean
	 */
	public static boolean isValidation2(String date) {
		String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			return false;
		}
		return true;
	}

	/**
	 * 分页数据
	 * 
	 * @param list
	 *            汇总后集合
	 * @param beanUtil
	 * @return T
	 */
	public <T> List<T> pageList(List<T> list, PageBeanUtil beanUtil) {
		List<T> jqueryList = new ArrayList<T>();
		// 通过循环分页数据：例如：0-100,100-200,200-300......
		for (int i = getStart(beanUtil, list); i < getEnd(); i++) {
			jqueryList.add(list.get(i));
		}
		return jqueryList;
	}

	/**
	 * 设置分页起始页
	 * 
	 * @param beanUtil
	 * @param list
	 *            集合
	 * @return int
	 */
	@SuppressWarnings("all")
	public int getStart(PageBeanUtil beanUtil, List<?> list) {
		int start = (beanUtil.getPageIndex() - 1) * beanUtil.getPageSize();
		int end = beanUtil.getPageIndex() * beanUtil.getPageSize();

		if (list == null || list.size() == 0) {
			end = 0;
		}

		else if (list.size() >= start && list.size() <= end) {
			end = list.size();
		}

		this.setEnd(end);
		return start;

	}

	/**
	 * 分页结束页
	 * 
	 * @return
	 */
	public int end;

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * 排序
	 * 
	 * @param list
	 *            排序集合
	 * @param Type
	 *            排序类型 desc/asc
	 * @param fieldvalue
	 *            排序字段
	 * @param fieldtype
	 *            字段类型
	 * @return T
	 */
	public <T> List<T> orderListDescOrAsc(List<T> list, String Type,
			final String fieldvalue, final String fieldtype) {
		// 非空验证
		if (list != null && list.size() > 0) {
			if (Type.equalsIgnoreCase("desc")) {
				Collections.sort(list, new Comparator<T>() {
					public int compare(T arg0, T arg1) {

						if (fieldtype.equalsIgnoreCase("java.lang.Double")
								|| fieldtype.equalsIgnoreCase("double")) {
							Double arg0value = 0d;
							Double arg1value = 0d;
							try {
								arg0value = Double.valueOf(BeanUtils
										.getProperty(arg0, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg0,
												fieldvalue));
								arg1value = Double.valueOf(BeanUtils
										.getProperty(arg1, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg1,
												fieldvalue));
							} catch (Exception e) {
								e.printStackTrace();
							}
							return arg1value.compareTo(arg0value);
						} else if (fieldtype
								.equalsIgnoreCase("java.lang.Integer")
								|| fieldtype.equalsIgnoreCase("int")) {
							Integer arg0value = 0;
							Integer arg1value = 0;
							try {
								arg0value = Integer.valueOf(BeanUtils
										.getProperty(arg0, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg0,
												fieldvalue));
								arg1value = Integer.valueOf(BeanUtils
										.getProperty(arg1, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg1,
												fieldvalue));
							} catch (Exception e) {
								e.printStackTrace();
							}
							return arg1value.compareTo(arg0value);
						} else if (fieldtype
								.equalsIgnoreCase("java.lang.String")
								|| fieldtype.equalsIgnoreCase("int")) {
							Integer arg0value = 0;
							Integer arg1value = 0;
							try {
								arg0value = Integer.valueOf(BeanUtils
										.getProperty(arg0, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg0,
												fieldvalue));
								arg1value = Integer.valueOf(BeanUtils
										.getProperty(arg1, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg1,
												fieldvalue));
							} catch (Exception e) {
								e.printStackTrace();
							}
							return arg1value.compareTo(arg0value);
						} else if (fieldtype.equalsIgnoreCase("java.sql.Date")
								|| fieldtype.equalsIgnoreCase("date")) {
							Date arg0value = null;
							Date arg1value = null;
							try {
								String dateformat1 = BeanUtils.getProperty(
										arg0, fieldvalue);
								String dateformat2 = BeanUtils.getProperty(
										arg1, fieldvalue);
								if (dateformat1.length() == 6) {
									arg0value = DataUtil.parseDate(dateformat1,
											16);
								}
								if (dateformat1.length() == 8) {
									arg0value = DataUtil.parseDate(dateformat1,
											10);
								}
								if (dateformat2.length() == 6) {
									arg1value = DataUtil.parseDate(dateformat2,
											16);
								}
								if (dateformat2.length() == 8) {
									arg1value = DataUtil.parseDate(dateformat2,
											10);
								}
								if (dateformat1.length() == 10
										&& dateformat1.indexOf("-") != -1) {
									arg0value = DataUtil.parseDate(dateformat1,
											3);
								}
								if (dateformat2.length() == 10
										&& dateformat2.indexOf("-") != -1) {
									arg1value = DataUtil.parseDate(dateformat2,
											3);
								}
								// arg0value =
								// java.sql.Date.valueOf(BeanUtils.getProperty(arg0,fieldvalue)==null?"0":BeanUtils.getProperty(arg0,fieldvalue));
								// arg1value =
								// java.sql.Date.valueOf(BeanUtils.getProperty(arg1,fieldvalue)==null?"0":BeanUtils.getProperty(arg1,fieldvalue));
							} catch (Exception e) {
								e.printStackTrace();
							}
							return arg1value.compareTo(arg0value);
						}

						return 1;
					}
				});
			} else {
				Collections.sort(list, new Comparator<T>() {
					public int compare(T arg0, T arg1) {
						if (fieldtype.equalsIgnoreCase("java.lang.Double")
								|| fieldtype.equalsIgnoreCase("double")) {
							Double arg0value = 0d;
							Double arg1value = 0d;
							try {
								arg0value = Double.valueOf(BeanUtils
										.getProperty(arg0, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg0,
												fieldvalue));
								arg1value = Double.valueOf(BeanUtils
										.getProperty(arg1, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg1,
												fieldvalue));
							} catch (Exception e) {
								e.printStackTrace();
							}
							return arg0value.compareTo(arg1value);
						} else if (fieldtype
								.equalsIgnoreCase("java.lang.Integer")
								|| fieldtype.equalsIgnoreCase("int")) {
							Integer arg0value = 0;
							Integer arg1value = 0;
							try {
								arg0value = Integer.valueOf(BeanUtils
										.getProperty(arg0, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg0,
												fieldvalue));
								arg1value = Integer.valueOf(BeanUtils
										.getProperty(arg1, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg1,
												fieldvalue));
							} catch (Exception e) {
								e.printStackTrace();
							}
							return arg0value.compareTo(arg1value);
						} else if (fieldtype
								.equalsIgnoreCase("java.lang.String")
								|| fieldtype.equalsIgnoreCase("int")) {
							Integer arg0value = 0;
							Integer arg1value = 0;
							try {
								arg0value = Integer.valueOf(BeanUtils
										.getProperty(arg0, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg0,
												fieldvalue));
								arg1value = Integer.valueOf(BeanUtils
										.getProperty(arg1, fieldvalue) == null ? "0"
										: BeanUtils.getProperty(arg1,
												fieldvalue));
							} catch (Exception e) {
								e.printStackTrace();
							}
							return arg0value.compareTo(arg1value);
						} else if (fieldtype.equalsIgnoreCase("java.sql.Date")
								|| fieldtype.equalsIgnoreCase("date")) {
							Date arg0value = null;
							Date arg1value = null;
							try {
								String dateformat1 = BeanUtils.getProperty(
										arg0, fieldvalue);
								String dateformat2 = BeanUtils.getProperty(
										arg1, fieldvalue);
								if (dateformat1.length() == 6) {
									arg0value = DataUtil.parseDate(dateformat1,
											16);
								}
								if (dateformat1.length() == 7
										&& dateformat1.indexOf("-") != -1) {
									arg0value = DataUtil.parseDate(dateformat1,
											17);
								}
								if (dateformat1.length() == 7
										&& dateformat1.indexOf("/") != -1) {
									arg0value = DataUtil.parseDate(dateformat1,
											15);
								}
								if (dateformat1.length() == 8) {
									arg0value = DataUtil.parseDate(dateformat1,
											10);
								}
								if (dateformat2.length() == 6) {
									arg1value = DataUtil.parseDate(dateformat2,
											16);
								}
								if (dateformat2.length() == 7
										&& dateformat2.indexOf("-") != -1) {
									arg1value = DataUtil.parseDate(dateformat2,
											17);
								}
								if (dateformat2.length() == 7
										&& dateformat2.indexOf("/") != -1) {
									arg1value = DataUtil.parseDate(dateformat2,
											15);
								}
								if (dateformat2.length() == 8) {
									arg1value = DataUtil.parseDate(dateformat2,
											10);
								}
								if (dateformat1.length() == 10
										&& dateformat1.indexOf("-") != -1) {
									arg0value = DataUtil.parseDate(dateformat1,
											3);
								}
								if (dateformat2.length() == 10
										&& dateformat2.indexOf("-") != -1) {
									arg1value = DataUtil.parseDate(dateformat2,
											3);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							return arg0value.compareTo(arg1value);
						}

						return 1;
					}
				});
			}
			return list;
		}

		return null;

	}

	/**
	 * 获取一年内每个月新增流失进/出口商
	 * 
	 * @param list
	 *            汇总后集合
	 * @return T
	 */
	public <T> Map<String, T> getCount(List<T> list, String addOrOut,
			String imexType) {
		// 存放柱状图集合
		List<T> histogramList = new ArrayList<T>();
		// 存放饼图集合
		List<T> pieList = new ArrayList<T>();
		// 存放饼图和柱状图的map总集合
		Map<String, T> map = new HashMap<String, T>();
		try {
			// 定义一个map来存放每个元素出现的次数 String 用来保存年-月 interger 用来保存个数
			Map<String, Integer> elementsCount = new HashMap<String, Integer>();
			if (list != null && list.size() > 0) {
				// 循环判断
				for (T t : list) {

					// 获取日期
					String date = BeanUtils.getProperty(t, "date");
					// 转化为年-月格式显示
					date = date.substring(0, 4) + "-" + date.substring(4, 6);
					// 将日期带到Map中去寻找
					Integer i = elementsCount.get(date);

					if (i == null) {
						elementsCount.put(date, 1);
					} else {
						elementsCount.put(date, i + 1);

					}
				}
				// 存放要排序的日期
				String[] str = new String[elementsCount.size()];
				// 存放每个月对应的增加数量
				Integer[] value = new Integer[elementsCount.size()];
				int i = 0;
				// 输出结果
				for (String key : elementsCount.keySet()) {
					str[i] = key;
					value[i] = elementsCount.get(key);
					PieDataModel m = new PieDataModel();
					m.setValue(elementsCount.get(key));
					m.setName(key);
					pieList.add((T) m);
					i++;
					// System.out.println(key+"出现了 " +elementsCount.get(key)
					// +"个");
				}
				// 处理日期排序
				Arrays.sort(str);
				for (int j = 0; j < str.length; j++) {
					str[j] = str[j];
					value[j] = elementsCount.get(str[j]);
				}
				String ieText = null;

				if (imexType.equals("import") && addOrOut.equals("add")) {
					ieText = "新增采购商个数";
				} else if (imexType.equals("import") && addOrOut.equals("out")) {
					ieText = "流失采购商个数";
				} else if (imexType.equals("export") && addOrOut.equals("out")) {
					ieText = "流失供应商个数";
				} else if (imexType.equals("export") && addOrOut.equals("add")) {
					ieText = "新增供应商个数";
				}
				MixDataModel model1 = new MixDataModel("x", "月份", 0, str, 0);
				MixDataModel model2 = new MixDataModel("bar", ieText, 1, value,
						0);
				histogramList.add((T) model1);
				histogramList.add((T) model2);
				map.put("barChart", (T) histogramList);
				map.put("pieChart", (T) pieList);
			}
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		}
		return map;
	}

	/**
	 * 设置查询缓存字段
	 * 
	 * @param fieldKey
	 *            查询的字段
	 * @param fieldValue
	 *            查询的值
	 * @param countryName
	 *            国家
	 * @param isAdd
	 *            是否是新增/流失
	 * @author 章华才
	 */
	public void setCacheField(String[] fieldKey, String[] fieldValue,
			String countryName, String isFirst, String ie) {
		if (isFirst.equals("first")) {
			LuceneFileUtis.newFields = fieldKey;
			LuceneFileUtis.newValues = fieldValue;
			LuceneFileUtis.newCountryName = countryName;
			LuceneFileUtis.newIe = ie;
		} else {
			LuceneFileUtis.secondFields = fieldKey;
			LuceneFileUtis.secondValues = fieldValue;
			LuceneFileUtis.secondeCountryName = countryName;
			LuceneFileUtis.secondeIe = ie;
		}

	}

	/**
	 * 判断查询条件是否一致
	 * 
	 * @param fields
	 *            字段名称
	 * @param values
	 *            字段对应值
	 * @return boolean
	 */
	@SuppressWarnings("all")
	public boolean getFields(String[] fields, String[] values,
			String countryName, String isFirst, String ie) {
		LuceneFileUtis lucene = new LuceneFileUtis();
		if (isFirst.equals("first")) {
			if (lucene.judgeEqual(LuceneFileUtis.newFields, fields)
					&& lucene.judgeEqual(LuceneFileUtis.newValues, values)
					&& lucene.judgeField(LuceneFileUtis.newCountryName,
							countryName)
					&& lucene.judgeField(LuceneFileUtis.newIe, ie)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (lucene.judgeEqual(LuceneFileUtis.secondFields, fields)
					&& lucene.judgeEqual(LuceneFileUtis.secondValues, values)
					&& lucene.judgeField(LuceneFileUtis.secondeCountryName,
							countryName)
					&& lucene.judgeField(LuceneFileUtis.secondeIe, ie)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 获取每个国家默认的排序字段
	 * 
	 * @param countryName
	 * @return
	 */
	public String getSortName(HttpServletRequest request, String countryName) {
		// 初始化配置文件
		new LoadPropertiesUtil().init(request, "contrastreCenter");
		String str = (String) LoadPropertiesUtil
				.getValue(countryName + "_sort");
		str = str.trim();
		return str;
	}

	/**
	 * 根据国家名称返回国家的导出显示字段
	 * 
	 * @param countryName
	 * @return
	 */
	public Map<String, String[]> getFieldsByCountryName(
			HttpServletRequest request, String countryName, String imexType) {
		// 初始化配置文件
		new LoadPropertiesUtil().init(request, "contrastreCenter");
		Map<String, String[]> map = new HashMap<String, String[]>();
		// 判断有进出口商
		if (imexType != null && countryName.contains("_")) {
			countryName = countryName.split("_")[0] + "_" + imexType;
		}
		// 将中文的国家转化为英文国家
		String[] field = LoadPropertiesUtil.getProp("query_" + countryName
				+ "_en");
		;
		String[] strName = LoadPropertiesUtil.getProp("query_" + countryName
				+ "_ch");
		;
		map.put("field", field);
		map.put("strName", strName);
		return map;
	}

	/**
	 * 复制集合
	 * 
	 * @param <E>
	 * @param source
	 * @param destinationClass
	 * @return
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass)
			throws IllegalAccessException, InvocationTargetException,
			InstantiationException {
		if (source == null) {
			return null;
		}
		if (source.size() == 0)
			return Collections.emptyList();
		List<E> res = new ArrayList<E>(source.size());
		for (Object o : source) {
			E e = destinationClass.newInstance();
			BeanUtils.copyProperties(e, o);
			res.add(e);
		}
		return res;
	}

	/**
	 * 根据国家的进出口商类型拿到相应的查询类型
	 * 
	 * @param countryName
	 * @return
	 */
	public String getImportOrExportType(String countryName, String isFirst,
			String type) {
		// 等于中国-英国
		if (countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)
				|| countryName.equals(LuceneConstant.UK_IMPORT_STRING)
				|| countryName.equals(LuceneConstant.KOREA_STRING)) {
			if (isFirst.equals("first")) {
				return ParamEnumUtil.companycompare_first.name();
			} else {
				return ParamEnumUtil.companycompare_second.name();
			}
		}
		// 其他国家
		else {
			if ("import".contains(type)) {
				if (isFirst.equals("first")) {
					return ParamEnumUtil.jkscompare_first.name();
				} else {
					return ParamEnumUtil.jkscompare_second.name();
				}
			} else {
				if (isFirst.equals("first")) {
					return ParamEnumUtil.ckscompare_first.name();
				} else {
					return ParamEnumUtil.ckscompare_second.name();
				}
			}
		}
	}

	/**
	 * 将集合放入到map当中方便以后调用
	 * 
	 * @param lists
	 *            新增流失后的汇总集合
	 * @return
	 */
	public Map<String, DataReportSumModel> getDataReportModelMap(
			List<DataReportSumModel> lists, String countryName, String type,
			String imexport) {
		Map<String, DataReportSumModel> map = new HashMap<String, DataReportSumModel>();
		for (DataReportSumModel d : lists) {
			// 如果是有原产国/进口商/目的国.....
			if (type == null) {

				if (countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)
						|| countryName.equals(LuceneConstant.KOREA_STRING)
						|| countryName.equals(LuceneConstant.UK_IMPORT_STRING)) {
					map.put(d.getCompany_name(), d);
				} else if (countryName.indexOf("import") != -1) {
					if (imexport != null && imexport.equals("import")) {
						map.put(d.getImporter(), d);
					} else if (imexport != null && imexport.equals("export")) {
						map.put(d.getExporter(), d);
					} else {
						map.put(d.getImporter(), d);
					}
				} else if (countryName.indexOf("export") != -1) {
					if (imexport != null && imexport.equals("import")) {
						map.put(d.getImporter(), d);
					} else if (imexport != null && imexport.equals("export")) {
						map.put(d.getExporter(), d);
					} else {
						map.put(d.getExporter(), d);
					}
				}

			} else {

				// 对比显示
				if (type.equals("ycg")) {
					type = "origin_country";
				} else if (type.equals("mdg")) {
					type = "dest_country";
				} else if (type.equals("goods_desc")) {
					type = "goodsDesc";
				} else if (type.equals("hs_code")) {
					type = "hscode";
				} else if (type.equals("data")) {
					type = "date";
				} else if (type.equals("cks")) {
					type = "exporter";
				} else if (type.equals("jks")) {
					type = "importer";
				}

				try {
					map.put(BeanUtils.getProperty(d, type), d);
				} catch (IllegalAccessException | InvocationTargetException
						| NoSuchMethodException e) {
					e.printStackTrace();
				}
			}

		}
		return map;
	}

	/**
	 * 数据对比需要的混搭图
	 * 
	 * @param <T>
	 * @param ls
	 * @return
	 * @throws Exception
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unused")
	public <T> Map<String, T> getLineInfo(HttpServletRequest request,
			List<DataReportSumModel> ls, String countryName, String type,
			String imexport, String DynamicSortValue)
			throws IllegalAccessException, InvocationTargetException, Exception {
		// 初始化配置文件
		new LoadPropertiesUtil().init(request, "contrastreCenter");
		String[] country = countryName.split("_");
		// 格式化数字类型
		DecimalFormat df1 = new DecimalFormat("0.00");
		Map<String, T> m = new HashMap<String, T>();
		// 获取混搭图显示字段
		String[] mixChartField = LoadPropertiesUtil.getProp(countryName
				+ "_chartField");
		// 获取混搭图显示中文名称
		String[] mixChartName = LoadPropertiesUtil.getProp(countryName
				+ "_chartName");
		// 获取饼图显示的字段
		String pieField = LoadPropertiesUtil
				.getValue(countryName + "_pieField");
		// 获取饼图显示的中文名称
		String pieName = LoadPropertiesUtil.getValue(countryName + "_pieName");
		// 获取阿根廷显示图表类型
		String[] chartType = LoadPropertiesUtil.getProp(countryName
				+ "_chartType");
		// 获取要除的数量字段
		String quantityField = LoadPropertiesUtil.getValue(countryName
				+ "_quantityField");
		// 获取国家需要混搭图隐藏的列
		String[] displayNum = LoadPropertiesUtil.getProp(countryName
				+ "_displayField");
		// 存放进口商和出口商
		String[] importOrExport = new String[ls.size()];
		// 存放混搭图集合
		List<MixDataModel> charlist = new ArrayList<MixDataModel>();
		// 存放饼图集合
		List<PieDataModel> pielist = new ArrayList<PieDataModel>();
		// 获取哪些国家默认显示混搭图中文显示名称：如：金额 - 重量
		String ch_name = LoadPropertiesUtil.getValue(countryName
				+ "_showChart_name");
		// 拿到map集合
		Map<String, DataReportSumModel> map = getDataReportModelMap(ls,
				countryName, type, imexport);
		int i = 0;
		// 绑定饼图
		for (String s : map.keySet()) {
			// PieDataModel pieDataModel = new PieDataModel(pieName,
			// BeanUtils.getProperty(map.get(s), pieField));
			importOrExport[i] = s;
			// pielist.add(pieDataModel);
			i++;
		}
		// 绑定混搭图
		MixDataModel ie = new MixDataModel("x", "进出口商", 0, importOrExport, 0);
		charlist.add(ie);
		for (int j = 0; j < mixChartField.length; j++) {

			Object[] chartData = new Object[ls.size()];
			int k = 0;
			for (String str : map.keySet()) {
				// 等于金额 并且存在数量 == 金额/数量
				if (mixChartField[j].equals("tradeMoney")
						&& quantityField != null && chartType[j].equals("line")
						&& DynamicSortValue == null) {
					double money = Double.valueOf(BeanUtils.getProperty(
							map.get(str), mixChartField[j]));
					double quantity = Double.valueOf(BeanUtils.getProperty(
							map.get(str), quantityField));
					chartData[k] = df1.format(money / quantity);
				} else {
					double obj = Double.valueOf(BeanUtils.getProperty(
							map.get(str), mixChartField[j]));
					chartData[k] = df1.format(obj);
				}
				k++;
			}
			MixDataModel lineChart = null;
			if (mixChartField[j].equals("tradeMoney") && quantityField != null
					&& chartType[j].equals("line")) {
				lineChart = new MixDataModel(chartType[j], mixChartName[j], 0,
						chartData, 0);
			} else {
				if (j < 1) {
					lineChart = new MixDataModel(chartType[j], mixChartName[j],
							0, chartData, 0);
				} else if (j >= 1) {
					lineChart = new MixDataModel(chartType[j], mixChartName[j],
							1, chartData, 1);
				}

			}
			charlist.add(lineChart);
		}
		m.put("showChart", (T) charlist);
		// m.put("showPie", (T) pielist);
		m.put("ch_name", (T) ch_name.split(","));
		return m;
	}

	/**
	 * 根据国家名称返回对应的明细字段
	 * 
	 * @param countryName
	 *            国家名称
	 * @return
	 */
	public Map<String, String[]> detailShowField(String countryName) {

		Map<String, String[]> detailMap = new HashMap<String, String[]>();
		String[] detailName = ((String) ResultFieldModel.getResultFieldMap()
				.get(countryName + "_detail_fieldzhname")).split(",");
		String[] detailId = ((String) ResultFieldModel.getResultFieldMap().get(
				countryName + "_detail_fieldid")).split(",");
		detailMap.put("name", detailName);
		detailMap.put("Id", detailId);
		return detailMap;
	}

	/**
	 * 创建一个缓存将数据放入到缓存中
	 * 
	 * @param listAll
	 *            查询的结果集合
	 * @param key
	 *            缓存的key
	 * @param value
	 *            缓存名称
	 */
	public void createCacheSetList(List<DataReportSumModel> listAll,
			String key, String value) {
		// value : reportContrastreCache
		// key : reportListAll
		Cache ehCache = null;
		try {
			// 将查询结果放入缓存中
			ehCache = CreateEncache.getEacheInstance().getCache(value);
			ehCache.acquireWriteLockOnKey(key);
			Element element = new Element(key, listAll);
			ehCache.put(element);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ehCache.releaseWriteLockOnKey(key);
		}
	}

	/**
	 * 
	 * 获取一个缓存数据
	 * 
	 * @param key
	 *            缓存的key
	 * @param value
	 *            缓存名称
	 * @return
	 */
	public List<DataReportSumModel> getCacheList(String key, String value) {
		// 创建一个缓存
		Cache cache = CreateEncache.getEacheInstance().getCache(value);
		// 从缓存中拿到相应的element
		Element element = cache.get(key);
		List<DataReportSumModel> rmp = null;
		if (element != null) {
			// 从element中拿到相应的数据
			rmp = (List<DataReportSumModel>) element.getObjectValue();
		}
		return rmp;
	}

	/**
	 * 求出日期
	 * 
	 * @param ls
	 * @param isAdd
	 * @return
	 * @throws Exception
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public List<DataReportSumModel> getList(List<DataReportSumModel> ls,
			String isAdd) throws IllegalAccessException,
			InvocationTargetException, Exception {
		// 复制集合
		List<DataReportSumModel> newList = copyTo(ls, DataReportSumModel.class);
		if (newList != null) {
			for (DataReportSumModel list : newList) {
				if (isAdd.equals("add")) {
					list.setDate(list.getDate().split(",")[0]);
				} else {
					list.setDate(list.getDate().split(",")[1]);
				}
			}
		}
		return newList;
	}

	/**
	 * 默认显示前十条数据
	 * 
	 * @param list
	 * @return
	 */
	public List<DataReportSumModel> defaultShowTen(
			List<DataReportSumModel> originList) {
		// 默认取出List前十条：参数1 起始下标 参数2 结束下标
		List<DataReportSumModel> listNow = new ArrayList<DataReportSumModel>();
		for (int i = 0; i < originList.size(); i++) {
			if (i == 9) {
				break;
			} else {
				listNow.add(originList.get(i));
			}
		}
		return listNow;
		// 默认取出List前十条：参数1 起始下标 参数2 结束下标
		// if(originList!=null){
		// if(originList.size()>=10){
		// return originList.subList(0, 10);
		// }else{
		// return originList.subList(0, originList.size());
		// }
		// }
		// return originList;
	}

	/**
	 * 将对比中心的查询参数封装为 hscode;goods_desc;....
	 * 
	 * @param request
	 * @param field
	 * @param values
	 * @param model
	 * @param sortKey
	 * @param countryName
	 * @param isAdd
	 * @param imexType
	 * @return
	 */
	public String[] queryKeyOrValue(HttpServletRequest request,
			CommonSearchModel model, String countryName) {
		// 针对对比中心
		model.setTradeType(request.getParameter("tradeType"));
		model.setHscode(request.getParameter("hscode"));
		model.setGoodDesc(request.getParameter("goodDesc"));
		model.setFirstDate(request.getParameter(""));
		model.setBeginDate(request.getParameter("beginDate"));
		model.setEndDate(request.getParameter("endDate"));
		model.setBeginAddDate(request.getParameter("beginAddDate"));
		model.setEndAddDate(request.getParameter("endAddDate"));

		// 将中文的国家转化为英文
		countryName = InitCountryCENameUtil.queryCountryEnName(countryName);
		// 处理日期格式
		isValidateByCountry(model, countryName, "2");
		// 设置第一段查询时间
		model.setFirstDate(model.getBeginAddDate() + ","
				+ model.getEndAddDate());
		// 设置第一次查询条件字段
		Map map = setParameters(request, countryName, model);
		if (map == null) {
			return null;
		}
		String[] fieldKey = findByFieldKey(map);
		String[] fieldValue = findByFieldValue(map);
		String[] query = new String[2];
		StringBuilder queryKey = new StringBuilder();
		StringBuilder queryValue = new StringBuilder();
		for (int i = 0; i < fieldKey.length; i++) {
			queryKey.append(fieldKey[i] + ";");
			queryValue.append(fieldValue[i] + ";");
		}
		query[0] = queryKey.toString();
		query[1] = queryValue.toString();
		return query;
	}

	
	/**
	 * 算出两段时间内相差的月份
	 * @param start 起始时间
	 * @param end   结束时间
	 * @return
	 */
	public String[] getAllMonths(String start, String end) {
		String splitSign = "-";
		String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))";
		if (!start.matches(regex) || !end.matches(regex))
			return new String[0];
		List<String> list = new ArrayList<String>();
		if (start.compareTo(end) > 0) {
			String temp = start;
			start = end;
			end = temp;
		}
		String temp = start;
		while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
			list.add(temp);
			String[] arr = temp.split(splitSign);
			int year = Integer.valueOf(arr[0]);
			int month = Integer.valueOf(arr[1]) + 1;
			if (month > 12) {
				month = 1;
				year++;
			}
			if (month < 10) {
				temp = year + splitSign + "0" + month;
			} else {
				temp = year + splitSign + month;
			}
		}
		int size = list.size();
		String[] result = new String[size];
		for (int i = 0; i < size; i++) {
			result[i] = list.get(i);
		}
		return result;

	}

	public Date getDate(String date){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try {
			return f.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 求出要过滤要排除的日期
	 * 
	 * @param datas
	 *            【0】 放第一段日期 datas【1】 放第二段时间 datas【2】放第三段时间也就是查询时间
	 * @return datestr
	 */
	public String excludDate(String date1,String date2,String date3) {
		  //非空验证
		  if(date1!=null && date2!=null && date3!=null){
			String[] result1 = null;
			String[] result2 = null;
			String[] result3 = null;
			StringBuilder sb = new StringBuilder();
			// 中国现在时间
			String[] date1str = date1.split(",");
			// 中国历史时间
			String[] date2str = date2.split(",");
			//中国查询时间
			String[] date3str = date3.split(",");
			//以下是历史时间判断
			if((getDate(date3str[0]).getTime() < getDate(date2str[0]).getTime()) && 
					getDate(date3str[1]).getTime() <= getDate(date2str[1]).getTime()){
				//开始时间小于历史时间并且结束日期大于历史结束日期
				if((getDate(date3str[0]).getTime()<getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()){
					result1 = getAllMonths(date3str[0], date2str[0]);
					result2 = getAllMonths(date2str[1], date3str[1]);
				}
				//开始时间小于历史时间并且结束日期小于历史结束日期 (不考虑)
				else if((getDate(date3str[0]).getTime()<getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) <= getDate(date2str[1]).getTime()){
					result1 = getAllMonths(date3str[0], date2str[0]);
	//				result2 = getAllMonths(date2str[1], date3str[1]);
				}
				//开始时间大于历史时间并且结束日期大于历史结束日期
				else if((getDate(date3str[0]).getTime()>getDate(date2str[0]).getTime())
						 && (getDate(date3str[1]).getTime()>getDate(date2str[1]).getTime())
						){
					result2 = getAllMonths(date2str[1], date3str[1]);
				}
				//开始时间大于历史时间并且结束日期小于历史结束日期 (不考虑)
				else if((getDate(date3str[0]).getTime()>getDate(date2str[0]).getTime())
						 && (getDate(date3str[0]).getTime()<getDate(date2str[0]).getTime())
						){
	//				result2 = getAllMonths(date2str[1], date3str[1]);
				}
			}
			//以下是现在时间判断
			else if((getDate(date3str[0]).getTime() > getDate(date2str[1]).getTime())){
				//开始时间大于历史结束时间并且开始日期小于现在开始时间并且结束日期小于现在结束日期
				if((getDate(date3str[0]).getTime()>getDate(date2str[1]).getTime())
					&& (getDate(date3str[0]).getTime()<getDate(date1str[0]).getTime())
					&& (getDate(date3str[1]).getTime()<getDate(date1str[1]).getTime())){
					result1 = getAllMonths(date3str[0], date1str[0]);
//					result2 = getAllMonths(date1str[1], date3str[1]);
				}
				//开始时间大于历史结束时间并且开始日期小于现在开始时间并且结束日期大于现在结束日期
				else if((getDate(date3str[0]).getTime()>getDate(date2str[1]).getTime())
						&& (getDate(date3str[0]).getTime()<getDate(date1str[0]).getTime())
						&& (getDate(date3str[1]).getTime()>getDate(date1str[1]).getTime())){
					result1 = getAllMonths(date3str[0], date1str[0]);
					result2 = getAllMonths(date1str[1], date3str[1]);
				}
				//开始时间大于历史结束时间并且开始日期小于现在开始时间并且结束日期大于现在结束日期
				else if((getDate(date3str[0]).getTime()>getDate(date2str[1]).getTime())
						&& (getDate(date3str[0]).getTime()<getDate(date1str[0]).getTime())
						&& (getDate(date3str[1]).getTime()<=getDate(date1str[1]).getTime())){
					result1 = getAllMonths(date3str[0], date1str[0]);
				}
				//开始时间大于历史结束时间并且开始日期小于现在开始时间并且结束日期大于现在结束日期（不考虑）
				else if((getDate(date3str[0]).getTime()>getDate(date2str[1]).getTime())
						&& (getDate(date3str[0]).getTime()>getDate(date1str[0]).getTime())
						&& (getDate(date3str[1]).getTime()<getDate(date1str[1]).getTime())){
					
				}
				//开始时间大于历史结束时间并且开始日期小于现在开始时间并且结束日期大于现在结束日期（不考虑）
				else if((getDate(date3str[0]).getTime()>getDate(date2str[1]).getTime())
						&& (getDate(date3str[0]).getTime()>getDate(date1str[0]).getTime())
						&& (getDate(date3str[1]).getTime()>getDate(date1str[1]).getTime())){
					
					 result2 = getAllMonths(date1str[1], date3str[1]);
				}
			}
			//历史+现在时间
			else{
				//开始时间大于历史时间并且结束日期大于历史时间并且结束日期小于现在开始日期
				if((getDate(date3str[0]).getTime()<getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()
						&& (getDate(date3str[1]).getTime()) < getDate(date1str[0]).getTime()){
					result1 = getAllMonths(date3str[0], date2str[0]);
					result2 = getAllMonths(date2str[1], date3str[1]);
				}
				//开始时间大于历史时间并且结束日期大于历史时间并且结束日期小于现在开始日期
				else if((getDate(date3str[0]).getTime()<getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()
						&& (getDate(date3str[1]).getTime()) > getDate(date1str[0]).getTime()
						&& (getDate(date3str[1]).getTime()) < getDate(date1str[1]).getTime()){
					
					result1 = getAllMonths(date3str[0], date2str[0]);
					result2 = getAllMonths(date2str[1], date1str[0]);
				}
				//开始时间大于历史时间并且结束日期大于历史时间并且结束日期小于现在开始日期
				else if((getDate(date3str[0]).getTime()>getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()
						&& (getDate(date3str[1]).getTime()) > getDate(date1str[0]).getTime()
						&& (getDate(date3str[1]).getTime()) <= getDate(date1str[1]).getTime()){
					
					result1 = getAllMonths(date2str[1], date1str[0]);
				}
				//开始时间大于历史时间并且结束日期大于历史时间并且结束日期小于现在开始日期
				else if((getDate(date3str[0]).getTime()>getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()
						&& (getDate(date3str[1]).getTime()) < getDate(date1str[0]).getTime()){
					
					result1 = getAllMonths(date2str[1], date1str[0]);
				}
				//开始时间大于历史时间并且结束日期大于历史时间并且结束日期小于现在开始日期
				else if((getDate(date3str[0]).getTime()>getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()
						&& (getDate(date3str[1]).getTime()) > getDate(date1str[0]).getTime()
						&& (getDate(date3str[1]).getTime()) > getDate(date1str[1]).getTime()){
					
					result1 = getAllMonths(date2str[1], date1str[0]);
					result2 = getAllMonths(date1str[1], date3str[1]);
				}
				//开始时间大于历史时间并且结束日期大于历史时间并且结束日期小于现在开始日期
				else if((getDate(date3str[0]).getTime()<getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()
						&& (getDate(date3str[1]).getTime()) > getDate(date1str[0]).getTime()
						&& (getDate(date3str[1]).getTime()) > getDate(date1str[1]).getTime()){
					
					result1 = getAllMonths(date3str[0], date2str[0]);
					result2 = getAllMonths(date2str[1], date1str[0]);
					result3 = getAllMonths(date1str[1], date3str[1]);
				}
				
				//开始时间大于历史时间并且结束日期大于历史时间并且结束日期小于现在开始日期
				else if((getDate(date3str[0]).getTime()<getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()
						&& (getDate(date3str[1]).getTime()) > getDate(date1str[0]).getTime()
						&& (getDate(date3str[1]).getTime()) <= getDate(date1str[1]).getTime()){
					
					result1 = getAllMonths(date3str[0], date2str[0]);
					result2 = getAllMonths(date2str[1], date1str[0]);
				}
				//开始时间大于历史时间并且结束日期大于历史时间并且结束日期小于现在开始日期
				else if((getDate(date3str[0]).getTime() == getDate(date2str[0]).getTime())
						&& (getDate(date3str[1]).getTime()) > getDate(date2str[1]).getTime()
						&& (getDate(date3str[1]).getTime()) < getDate(date1str[0]).getTime()){
					
					result1 = getAllMonths(date3str[0], date2str[0]);
					result2 = getAllMonths(date2str[1], date1str[0]);
				}
			}
			
			
			
			if(result1!=null){
				for (String s : result1) {
					sb.append(s+",");
				}
				
			}
			if(result2!=null){
				
				for (String s : result2) {
					sb.append(s+",");
				}
			}
			
			if(result3!=null){
				
				for (String s : result3) {
					sb.append(s+",");
				}
			}
			StringBuilder st = new StringBuilder();
			String d = date1 + "," + date2;
			if(sb!=null || !sb.toString().isEmpty()){
				String[] datestr = sb.toString().split(","); 
				for (String s : datestr) {
					if(!d.contains(s)){
						st.append(s+",");
					}
				}
			}
			
		   	
		   return st.toString();
		  }
		  return null;
	}
	
	
	/**
	 * 求出两段日期所有月份
	 * @param dateStr
	 * @return
	 */
	public List<String> dateList(List<String> dateStr){
		String[] resultDate = null;
		List<String> listDate = new ArrayList<String>();
		for (String s : dateStr) {
			resultDate=getAllMonths(s.split(",")[0],s.split(",")[1]);
			for (String str : resultDate) {
				listDate.add(str);
			}
		}
		return listDate;
	}
	
	/**
	 * 求出只能查询的日期
	 * @param originList
	 * @return
	 */
	public List<String> RemoveDuplicate(List<String> originList)
	{
	      //java里hashSet是一种不包含重复元素的集合
	      HashSet<String> hashSet = new HashSet<String>();
	      List<String> newList = new ArrayList<String>();
	      
	      for(Iterator iter = originList.iterator();iter.hasNext();)
	     {
	    	  String element = (String)iter.next();
	         if(!hashSet.add(element))
	         {
	             newList.add(element);
	        }
	     }
	     originList.clear();
	     originList.addAll(newList);
	     return originList;
	}
	
	/**
	 * 过滤所有能查询的日期
	 * @return
	 */
	public String filterNotDate(List<String> dateDBStr,List<String> dateQueryStr){
		StringBuilder sb = new StringBuilder();
		List<String> setList = new ArrayList<String>();
		setList.addAll(dateQueryStr);
		setList.addAll(dateDBStr);
		List<String> listWithoutDup = RemoveDuplicate(setList);
		if(listWithoutDup!=null && listWithoutDup.size()>0){
			for (String string : listWithoutDup) {
				sb.append(string+",");
			}
			return sb.toString();
		}else{
			return null;
		}
		
	}
}
