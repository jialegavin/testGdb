package com.njyb.gbdbase.service.common.engine.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.document.Document;

import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.contrastreport.common.LuceneFileUtis;

/**
 * 报表汇总需要用到相关辅助工具类
 * 
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public class ReportHelpUtil {
	/**
	 * 禁止实例化
	 */
	private ReportHelpUtil() {
		
	}

	/**
	 * 单例模式
	 * 
	 * @author 贾红平
	 *
	 */
	static class HelpUtil {
		static ReportHelpUtil util = new ReportHelpUtil();
	}

	public static ReportHelpUtil newInstanceReportUtil() {
		return HelpUtil.util;
	}

	/**
	 * 字符串转换为double类型
	 * 
	 * @param str
	 * @return
	 */
	public double parase(String str) {
		return str == null ? 0.0 : Double.parseDouble(str);
	}

	/**
	 * 根据报告类型 通过反射动态赋值
	 * 
	 * @param doc
	 * @param type
	 * @param data
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void setFirstColumnValueByRef(Document doc, String type,
			DataReportSumModel data, Map map) {
		String fieldName = map.get(type).toString();
		String fieldValue = doc.get(fieldName);
		try {
			BeanUtils.setProperty(data, fieldName, fieldValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把日期格式进行特殊处理 统一转换成yyyymm格式
	 * 
	 * @param str
	 * @return
	 */
	public static String handleDateStr(String str) {
		if (str.length() > 6) {
			str = str.substring(0, 6);
		}
		return str;
	}

	/**
	 * 根据报告类型 动态配置报表第一列字段显示的值
	 * 
	 * @param value
	 * @param type
	 * @param data
	 * @return
	 */
	@SuppressWarnings("incomplete-switch")
	public synchronized void setColumnValueByType(String value, String type,
			DataReportSumModel data) {
		switch (ParamEnumUtil.getEnum(type)) {
		case jks:
			data.setImporter(value);
			break;
		case goodsDesc:
			data.setGoodsDesc(value);
			break;
		case hsCode:
			data.setHscode(value);
			break;
		case jkscompare_first:
			data.setImporter(value);
			break;
		case jkscompare_second:
			data.setImporter(value);
			break;
		case companycompare_first:
			data.setCompany_name(value);
			break;
		case companycompare_second:
			data.setCompany_name(value);
			break;
		case zg_jks:
			data.setCompany_name(value);
			break;
		case zg_sfhd:
			data.setProduct_place(value);
			break;
		case zg_port:
			data.setStart_port(value);
			break;
		case cks:
			data.setExporter(value);
			break;
		case ckscompare_first:
			data.setExporter(value);
			break;
		case ckscompare_second:
			data.setExporter(value);
			break;
		case qyg:
			data.setStart_port(value);
			break;
		case ddg:
			data.setEnd_port(value);
			break;
		case ycg:
			data.setOrigin_country(value);
			break;
		case mdg:
			data.setDest_country(value);
			break;
		case hgcs:
			data.setCustoms(value);
			break;
		case qs:
			data.setDate(value);
			break;
		case tzr:
			data.setNotifier(value);
			break;
		case zzs:
			data.setManufacture(value);
			break;
		case multi_hscode:
			data.setHscode(value);
			break;
		}
	}

	/**
	 * 根据报告类型 动态配置报表第一列字段显示的值
	 * 
	 * @param value
	 * @param type
	 * @param data
	 * @return
	 */
	public void setColumnValueByRef(String value, String type,
			DataReportSumModel data, Map map) {
		try {
			BeanUtils.setProperty(data, map.get(type).toString(), value);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取要创建的线程个数
	 * 
	 * @param sum
	 * @return
	 */
	public static int getDocNum(int sum, int pagesize) {
		return sum % pagesize == 0 ? sum / pagesize : sum / pagesize + 1;
	}

	/**
	 * 获取查询日期
	 * 
	 * @param fs
	 * @param vs
	 * @return
	 */
	public static String getDate(String[] fs, String[] vs) {
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].equals("date")) {
				String date = vs[i];
				return date;
			}
		}
		return null;
	}

	/**
	 * 获取海关编码的值
	 * 
	 * @param fs
	 * @param vs
	 * @return
	 */
	public static String getHscode(String[] fs, String[] vs) {
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].equals("hscode")) {
				String hscode = vs[i];
				return hscode;
			}
		}
		return null;
	}

	// 上一次查询的条件包含在本次查询中
	public static boolean isSameField(String[] nfs, String[] fs) {
		List<String> s1 = Arrays.asList(nfs);
		List<String> ss = new ArrayList<>();
		// 过滤相同
		for (String s : fs) {
			if (s1.contains(s)) {
				ss.add(s);
			}
		}
		if (nfs.length == ss.size()) {
			return true;
		} else {
			return false;
		}

	}

	// 上一次查询的值包含再本次之中
	public static boolean isSameValue(String[] nfs, String[] nvs, String[] fs,
			String[] vs) {
		List<String> s1 = Arrays.asList(nfs);
		Map<String, String> map = new HashMap<>();
		String[] newfs = null;
		String[] newvs = null;
		List<String> fields = new ArrayList<>();
		List<String> values = new ArrayList<>();

		// 转换成map
		for (int i = 0; i < fs.length; i++) {
			map.put(fs[i], vs[i]);
		}

		List<String> ds = new ArrayList<>();

		for (String s : fs) {
			if (!s1.contains(s)) {
				ds.add(s);
			}
		}
		// 排除不同
		for (String str : ds) {
			map.remove(str);
		}

		for (Object o : map.keySet()) {
			fields.add(o.toString());
			values.add(map.get(o.toString()));
		}
		newfs = fields.toArray(new String[fields.size()]);
		newvs = values.toArray(new String[values.size()]);
		
		if (LuceneFileUtis.judgeEqualBySort(nfs, newfs)
				&& LuceneFileUtis.judgeEqualBySort(nvs, newvs)) {
			return true;
		}
		return false;
	}

	// 判断上一次查询的字段以及值是否包含再本次中
	public static boolean isAllTrue(String[] nfs, String[] nvs, String[] fs,
			String[] vs) {
		if (isSameField(nfs, fs) && isSameValue(nfs, nvs, fs, vs)) {
			return true;
		}
		return false;
	}

	// 获取两次查询中不一样条件以及对应的值
	public static Map<String, String> getParamterMap(String[] of, String[] ov,
			String[] nf, String[] nv) {
		// 把查询参数组装成map格式
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < nf.length; i++) {
			map.put(nf[i], nv[i]);
		}
		// 获取两次查询条件中不一样的查询条件
		List<String> os = Arrays.asList(of);
		List<String> ns = new ArrayList<>();
		for (String str : nf) {
			if (!os.contains(str)) {
				ns.add(str);
			}
		}
		// 获取不一样的查询条件对应的值
		for (String s : of) {
			map.remove(s);
		}
		return map;
	}

	// 根据指定字段 进行集合过滤
	public static List<DataReportSumModel> getFilterList(String[] of,
			String[] ov, String[] nf, String[] nv,
			List<DataReportSumModel> nlist) {
		System.out.println("恭喜您,不需要重复查询,系统正在智能帮您筛选数据!");
		List<DataReportSumModel> filterList = new LinkedList<DataReportSumModel>();
		// 获取本次查询比上一次多的查询条件
		Map<String, String> map = getParamterMap(of, ov, nf, nv);
		for (DataReportSumModel data : nlist) {
			//所有条件都满足才添加到空集合中
			if (!judegeContainFalse(map, data).contains("false")) {
				filterList.add(data);
			}
		}
		return filterList;
	}
	//判断所有过滤条件是否都满足
	private static String judegeContainFalse(Map map, DataReportSumModel data) {
		StringBuffer sb = new StringBuffer();
		String value = null;
		// 进口商
		if (map.containsKey("importer")) {
			value = map.get("importer").toString();
			if (data.getImporter().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//进出口企业:中国  韩国 英国
		if (map.containsKey("company_name")) {
			value = map.get("company_name").toString();
			if (data.getCompany_name().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//进出口口岸
		if (map.containsKey("port")) {
			value = map.get("port").toString();
			if (data.getPort().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//产品收发货地:中国
		if (map.containsKey("product_place")) {
			value = map.get("product_place").toString();
			if (data.getProduct_place().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 出口商
		if (map.containsKey("exporter")) {
			value = map.get("exporter").toString();
			if (data.getExporter().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 起运港
		if (map.containsKey("start_port")) {
			value = map.get("start_port").toString();
			if (data.getStart_port().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 抵达港
		if (map.containsKey("end_port")) {
			value = map.get("end_port").toString();
			if (data.getEnd_port().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 原产国
		if (map.containsKey("origin_country")) {
			value = map.get("origin_country").toString();
			if (data.getOrigin_country().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 目的国
		if (map.containsKey("dest_country")) {
			value = map.get("dest_country").toString();
			if (data.getDest_country().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 通知人
		if (map.containsKey("notifier")) {
			value = map.get("notifier").toString();
			if (data.getNotifier().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 制造商
		if (map.containsKey("manufacture")) {
			value = map.get("manufacture").toString();
			if (data.getManufacture().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 海关城市
		if (map.containsKey("customs")) {
			value = map.get("customs").toString();
			if (data.getCustoms().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//毛重
		if (map.containsKey("g_weight")) {
			String[] vs = map.get("g_weight").toString().split(",");
			if (data.getTradeWeight() > Integer.parseInt(vs[0])
					&& data.getTradeWeight() < Integer.parseInt(vs[1])) {
				sb.append("true");
			} else {
				sb.append("false");
			}

		}
		//净重
		if (map.containsKey("n_weight")) {
			String[] vs = map.get("n_weight").toString().split(",");
			if (data.getTradeWeight() > Integer.parseInt(vs[0])
					&& data.getTradeWeight() < Integer.parseInt(vs[1])) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//CIF金额
		if (map.containsKey("cif_value")) {
			String[] vs = map.get("cif_value").toString().split(",");
			if (data.getTradeMoney() > Integer.parseInt(vs[0])
					&& data.getTradeMoney() < Integer.parseInt(vs[1])) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//FOB金额
		if (map.containsKey("fob_value")) {
			String[] vs = map.get("fob_value").toString().split(",");
			if (data.getTradeMoney() > Integer.parseInt(vs[0])
					&& data.getTradeMoney() < Integer.parseInt(vs[1])) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//数量
		if (map.containsKey("quantity")) {
			String[] vs = map.get("quantity").toString().split(",");
			if (data.getTradeQuantity() > Integer.parseInt(vs[0])
					&& data.getTradeQuantity() < Integer.parseInt(vs[1])) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//件数
		if (map.containsKey("packages")) {
			String[] vs = map.get("packages").toString().split(",");
			if (data.getTradePackage() > Integer.parseInt(vs[0])
					&& data.getTradePackage() < Integer.parseInt(vs[1])) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//卢布金额
		if (map.containsKey("inr_value")) {
			String[] vs = map.get("inr_value").toString().split(",");
			if (data.getTradeMoney() > Integer.parseInt(vs[0])
					&& data.getTradeMoney() < Integer.parseInt(vs[1])) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		// 产品描述
		if (map.containsKey("goods_desc")) {
			value = map.get("goods_desc").toString();
			if (data.getGoodsDesc().contains(value)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		//中国单价过滤
		if (map.containsKey("cif_unit")) {
			String[] vs = map.get("cif_unit").toString().split(",");
			if (data.getTradeMoney() > Integer.parseInt(vs[0])
					&& data.getTradeMoney() < Integer.parseInt(vs[1])) {
				sb.append("true");
			} else {
				sb.append("false");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据报告类型实例化缓存的key和name
	 * @param type
	 * @return map
	 */
	public Map<String, String> getCacheStr(String type){
		Map<String, String>param=new HashMap<String, String>();
		if(type.startsWith(ParamEnumUtil.ckscompare_first.name())|| type.startsWith(ParamEnumUtil.ckscompare_second.name())
				|| type.startsWith(ParamEnumUtil.companycompare_first.name()) || type.startsWith(ParamEnumUtil.companycompare_second.name())
					||type.startsWith(ParamEnumUtil.jkscompare_first.name()) || type.startsWith(ParamEnumUtil.jkscompare_second.name())
					){
			param.put("cache_key", "addoffMap");
			param.put("cache_name", "addoffReportMap");
		}
		else{
			param.put("cache_key", "reportMap");
			param.put("cache_name", "reportSumMap");
		}
		return param;
	}
	/**
	 * 去除字符串中特殊的标点符号
	 * @param s
	 * @return
	 */
	public static String handleSybmol(String s) {
		String regEx = "[`~!^&*()+=|{}':;',\\[\\].<>/?~！\'\\\\￥%……&*\"（）——+|{}【】'；：”“’。，、？\r\t\n\f\b]";
		if(s!=null && !s.trim().equals("")){
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		s = m.replaceAll(" ");
		s=s.replaceAll(" +", " ").trim();
		return s.toUpperCase();
		}else{
			return null;
		}
	}
	
	/**
	 * 一下情况需要重新检索数据并且放入到缓存中
	 * @param country
	 * @param fields
	 * @param values
	 * @return
	 */
	@SuppressWarnings("static-access")
	public boolean isInitSearch(String oldCountry,String[] oldFields,String[]oldValues,String country,String[]fields,String[]values){
		//两次查询国家不一致
		if (!oldCountry.equals(country)) {
			return  false;
		}
		//两次查询条件不一致且本次查询条件的长度等于上一次查询条件的长度
		if (!LuceneFileUtis.judgeEqual(oldFields, fields)&&oldFields.length==fields.length) {
			return  false;
		}
		//两次查询条件一致但是值不一致
		if (LuceneFileUtis.judgeEqual(oldFields, fields)&&!LuceneFileUtis.judgeEqual(oldValues, values)) {
			return  false;
		}
		//本次查询条件的个数比上一次查询的条件个数少
		if (!LuceneFileUtis.judgeEqual(oldFields, fields)&&oldFields.length>fields.length) {
			return  false;
		}
		//两次查询条件不一致 且 本次查询的字段个数比上一次多 且两次查询的日期的值不同
		if (!LuceneFileUtis.judgeEqual(oldFields, fields)&&oldFields.length<fields.length
				&&!ReportHelpUtil.newInstanceReportUtil().getDate(oldFields, oldValues)
				.equals(ReportHelpUtil.newInstanceReportUtil().getDate(fields, values))) {
			return  false;
		}
		return true;
	}
	
	/**
	 * 拼接查询缓存key 
	 * @param key
	 * @param value
	 * @return 
	 * 章华才
	 */
	public static String queryKeyOrValue(String[] key,String [] value){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < key.length; i++) {
			sb.append(key[i] + ";" + value[i]+";");
		}
		return sb.toString();
	}
}
