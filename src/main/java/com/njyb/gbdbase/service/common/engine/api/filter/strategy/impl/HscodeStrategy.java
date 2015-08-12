package com.njyb.gbdbase.service.common.engine.api.filter.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.queries.ChainedFilter;
import org.apache.lucene.search.Filter;

import com.njyb.gbdbase.service.common.engine.api.filter.strategy.AbstractFilterStrategy;
/**
 *针对海关编码的策略
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public class HscodeStrategy extends AbstractFilterStrategy {
	@Override
	public Filter getFilter(Filter filter, Map map, Object o) {
		List<Filter> ls = new ArrayList<Filter>();
		String value = map.get(o).toString();
		String[] vs = value.split(",");
		// 多个海关编码
		if (vs.length >= 2) {
			for (int i = 0; i < vs.length; i++) {
				f =getFieldRangeFilter(o.toString(),
						vs[i]);
				ls.add(f);
			}
			Filter[] ff = new Filter[ls.size()];
			for (int i = 0; i < ls.size(); i++) {
				ff[i] = ls.get(i);
			}
			filter = new ChainedFilter(ff, ChainedFilter.OR);
		}
		// 一个海关编码
		else if (vs.length == 1) {
			filter =getFieldRangeFilter(o.toString(),
					value);
		}

	return filter;
	}

}
