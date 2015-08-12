package com.njyb.gbdbase.service.common.engine.api.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engine.api.filter.strategy.context.FilterStrategyContext;
import com.njyb.gbdbase.service.common.engine.util.LuceneAnaylazeyUtil;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 封装lucene查询经常用到的范围查询区间
 * 
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public  class LuceneFilter {
	private Map mp = null;

	/**
	 * 获取用户传递过来的参数 找到哪些参数是通过过滤器进行过滤 加快查询速度 过滤器的字段类型:日期 数量 价格 重量 字符串前缀
	 * 
	 * @param fields
	 *            --new string[]{'customcode','productname','unitprice'}
	 * @param values
	 *            --new value[]{'0409*','tianran','1.0,5.0'};Long
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Filter> getFilter(HttpServletRequest request,
			String[] fields, String[] values, String countyName,@SuppressWarnings("rawtypes") Map propertiesMap) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter filter = null;
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		//特殊国家需要把海关编码加到前缀过滤器中
		String filterStr =null;
		if (LuceneAnaylazeyUtil.falg(countyName)) {
			filterStr=ParamEnumUtil.hscode.toString()+","+propertiesMap.get("commonFilterStr").toString();
		}
		else{
			filterStr = propertiesMap.get("commonFilterStr").toString();
		}
		
		// 获取过滤的字符串 从属性配置文件中
		String[] fs =filterStr.split(",");
				
		// 添加到map对象中
		for (int i = 0; i < fs.length; i++) {
			for (int j = 0; j < fields.length; j++) {
				if (fs[i].toLowerCase().equals(fields[j])) {
					map.put(fs[i], values[j]);
				}
			}
		}
		// 获取每一个具体过滤的对象
		for (Object o : map.keySet()) {
			// 调用策略封装获取具体类型处理的filter
			filter = FilterStrategyContext
					.getFilter(filter, map, o, getType(o));
			filters.add(filter);
		}
		return filters;
	}

	/**
	 * 过滤日期类型
	 * 
	 * @param map
	 * @param o
	 * @return
	 */
	private static String getType(Object o) {
		if (o.equals(ParamEnumUtil.hscode.toString())) {
			return "hscode";
		}
		if (o.equals(ParamEnumUtil.date.toString())) {
			return "date";
		} else {
			return "number";
		}
	}

}
