package com.njyb.gbdbase.service.common.engine.api.filter.strategy.impl;

import java.util.Map;

import org.apache.lucene.search.Filter;

import com.njyb.gbdbase.service.common.engine.api.filter.strategy.AbstractFilterStrategy;
/**
 * 针对数值类型的策略
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public class NumberStrategy extends AbstractFilterStrategy {
	@Override
	public Filter getFilter(Filter filter, Map map, Object o) {
		String value = map.get(o).toString();
		String[] val = value.split(",");
		f =getDoubleFieldFilter(o.toString(),
				Double.parseDouble(val[0]), Double.parseDouble(val[1]));
		return f;
	}

}
