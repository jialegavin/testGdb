package com.njyb.gbdbase.service.common.engine.api.filter.strategy;

import java.util.Map;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.PrefixFilter;

import com.njyb.gbdbas.util.DataUtil;
/**
 *选择策略接口
 * @author 贾红平
 *
 */
public abstract class AbstractFilterStrategy {
	/**
	 * 子类可以使用
	 */
	protected Filter f=null;
	/**
	 * 针对不同策略 获取不同的filter
	 * @param filter Lucene 自身filter
	 * @param map  map集合
	 * @param o object
	 * @return fitler
	 */
	public abstract Filter getFilter(Filter filter, Map map, Object o);
	
	/**
	 * 过滤产品单价 重量 数量 件数等范围区间查询
	 */
	protected static Filter getDoubleFieldFilter(String key, double min, double max) {
		return NumericRangeFilter.newDoubleRange(key, min, max, true, true);
	}

	/**
	 * 过滤用户权限的有效时间 日期格式不同
	 */
	protected static Filter getRightTimeFilter2(String key, String startTime,
			String endTime) {
		return NumericRangeFilter.newLongRange(key,
				DataUtil.getDate(startTime), DataUtil.getDate(endTime), true,
				true);

	}
	/**
	 * 过滤用户权限的有效时间 日期格式不同
	 */
	protected static Filter getRightTimeFilter1(String key, String startTime,
			String endTime) {
		return NumericRangeFilter.newLongRange(key, DataUtil
				.getDateByOperator(startTime), DataUtil
				.getDateByOperator(endTime), true, true);

	}
	
	/**
	 * 获取过滤器查询对象
	 * 
	 * @param filterField
	 *            --要过滤的字段
	 * @param keyWord
	 *            --查询关键字
	 * @return
	 */
	protected static Filter getFieldRangeFilter(String key, String value) {
		Filter filter = null;
		if (key != null && !key.equals("")) {
			filter = new PrefixFilter(new Term(key, value));
		}
		return filter;

	}
	/**
	 * 生成统一查询日期范围的方法
	 * @param key 查询字段
	 * @param startTime 开始时间
	 * @param endTime   结束时间
	 * @return
	 */
	protected static Filter getRightTimeFilter(String key, String startTime,
			String endTime) {
		if (startTime.contains("-")) {
			return NumericRangeFilter.newLongRange(key, DataUtil
					.getDateByOperator(startTime), DataUtil
					.getDateByOperator(endTime), true, true);
		} else {
			return NumericRangeFilter.newLongRange(key, DataUtil
					.getDate(startTime), DataUtil.getDate(endTime), true, true);
		}

	}
}
