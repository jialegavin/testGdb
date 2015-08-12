package com.njyb.gbdbase.service.common.engine.api.filter.strategy.impl;

import java.util.Map;

import org.apache.lucene.search.Filter;

import com.njyb.gbdbase.service.common.engine.api.filter.strategy.AbstractFilterStrategy;
/**
 * 处理针对日期类型的策略
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public class DateStrategy extends AbstractFilterStrategy {
	@Override
	public Filter getFilter(Filter filter, Map map, Object o) {	
		String value = map.get(o).toString();
		String[] val = value.split(",");
		f =getRightTimeFilter(o.toString(),
				val[0], val[1]);
		return f;
	}

}
