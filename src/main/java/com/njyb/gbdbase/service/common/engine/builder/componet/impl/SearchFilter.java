package com.njyb.gbdbase.service.common.engine.builder.componet.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Filter;

import com.njyb.gbdbase.service.common.engine.api.filter.facade.LuceneFilterFacade;
import com.njyb.gbdbase.service.common.engine.builder.componet.AbstractFilter;
/**
 * 实现抽象filter的实例
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public class SearchFilter extends AbstractFilter {
	 /**
	  * 实现抽象filter的实例
	  */
	@Override
	public Filter getFilter(HttpServletRequest request, String[] fields,
			String[] values, String country, Map propertiesMap) {
		//获取filter对象
		return LuceneFilterFacade.getFilter(request, fields, values, country,propertiesMap);
	}

}
