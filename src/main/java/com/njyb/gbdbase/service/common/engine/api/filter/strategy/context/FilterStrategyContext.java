package com.njyb.gbdbase.service.common.engine.api.filter.strategy.context;

import java.util.Map;

import org.apache.lucene.search.Filter;

import com.njyb.gbdbase.service.common.engine.api.filter.strategy.AbstractFilterStrategy;
import com.njyb.gbdbase.service.common.engine.api.filter.strategy.impl.DateStrategy;
import com.njyb.gbdbase.service.common.engine.api.filter.strategy.impl.HscodeStrategy;
import com.njyb.gbdbase.service.common.engine.api.filter.strategy.impl.NumberStrategy;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 选择策略上下文对象
 * @author 贾红平
 *
 */
public class FilterStrategyContext {
	/**
	 * 根据类型 创建具体策略对象
	 * @param type
	 * @return
	 */
	private static AbstractFilterStrategy getFilterStrategy(String type){
		AbstractFilterStrategy strategy=null;
		switch (ParamEnumUtil.getEnum(type)) {
		case hscode:
			strategy=new HscodeStrategy();
			break;
		case date:
			strategy=new DateStrategy();
			break;
		case number:
			strategy=new NumberStrategy();
			break;
		default:
			break;
		}
		return strategy; 
	}
	/**
	 * 调用策略对象具体的策略方法
	 * @param filter
	 * @param map
	 * @param o
	 * @param type
	 * @return
	 */
	public static Filter getFilter(Filter filter, Map map, Object o,String type){
		return getFilterStrategy(type).getFilter(filter, map, o);
	}
	
}
