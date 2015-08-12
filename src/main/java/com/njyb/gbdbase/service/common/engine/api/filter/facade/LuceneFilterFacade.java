package com.njyb.gbdbase.service.common.engine.api.filter.facade;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queries.ChainedFilter;
import org.apache.lucene.search.CachingWrapperFilter;
import org.apache.lucene.search.Filter;

import com.njyb.gbdbase.service.common.engine.api.filter.LuceneFilter;

/**
 * 封装lucene
 * 
 * @author jiahp
 * 
 */
@SuppressWarnings("all")
public  class LuceneFilterFacade {
	/**
	 * fields=new String[]{"uname","date"} values=new String[]{"jiahp","11,12"}
	 * 
	 * @param fields
	 * @param values
	 * @return
	 */
	public static Filter getFilter(HttpServletRequest request,String[] fields, String[] values,String countryName,Map propertiesMap) {
		List<Filter>fs=LuceneFilter.getFilter(request,fields, values,countryName,propertiesMap);
		return getFilter(fs);
	}
	/**
	 * 获取过滤器
	 * @param fs
	 * @return
	 */
	private static Filter getFilter(List<Filter>fs) {
		CachingWrapperFilter cwf=null;
		if (fs.size()>0) {
			//集合转换成数组
			Filter[]filters=fs.toArray(new Filter[fs.size()]);
			Filter filter = new ChainedFilter(filters,ChainedFilter.AND);
			//缓存过滤查询条件--提高查询性能
			cwf=new CachingWrapperFilter(filter);
		}
		return cwf;
	}
	
}
