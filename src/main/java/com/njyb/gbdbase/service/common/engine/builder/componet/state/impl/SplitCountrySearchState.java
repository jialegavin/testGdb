package com.njyb.gbdbase.service.common.engine.builder.componet.state.impl;

import java.io.File;

import org.apache.lucene.search.IndexSearcher;

import com.google.common.base.Strings;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbase.service.common.engine.api.searcher.LuceneSearch;
import com.njyb.gbdbase.service.common.engine.builder.componet.state.ISearcherState;
import com.njyb.gbdbase.service.common.engine.util.CountryAnaylazerUtil;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 返回美国对应的indexsearcher
 * 
 * @author jiahp
 * 
 */
public class SplitCountrySearchState implements ISearcherState {
	/**
	 * 返回美国对应的indexsearcher
	 */
	@Override
	public IndexSearcher handleIndexSearcher(String country,String indexpath, String[] fields,
			String[] values) {
		String[] str = null;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals(ParamEnumUtil.date.toString())) {
				str = values[i].split(",");
			}
		}
		String cpath=IConstantUtil.COMMON_INDEXPATH + "/"+indexpath;
		// WangBo SplitCountrySearchState.java 33L,Add method checkYearByStr(param1,param2)
		File[] files = CountryAnaylazerUtil.getIndexFileByTime(
				cpath, str[0], str[1],checkYearByStr(str[0], str[1]));
		// 获取searcher实例对象
		try {
			return LuceneSearch.getMultiConditonSearcher(files,country);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 验证日期是否合法,<br>
	 * 2种情况, 1 : yyyy-MM-dd  2 : mm/yyyy <br>
	 * @param beginDateParam : param1
	 * @param endDateParam : param2
	 * 2015年4月30日
	 * @author WangBo
	 * @return true || false
	 */
	private boolean checkYearByStr(String beginDateParam,String endDateParam){
		boolean result = false;
		int startNum = 0;
		int endNum = 0;
		if (!Strings.isNullOrEmpty(beginDateParam) && !Strings.isNullOrEmpty(endDateParam)) {
			if (beginDateParam.indexOf("/") != -1 && endDateParam.indexOf("/") != -1) {
				startNum = Integer.valueOf(beginDateParam.substring(beginDateParam.indexOf("/")+1,beginDateParam.length()));
				endNum = Integer.valueOf(endDateParam.substring(endDateParam.indexOf("/")+1,endDateParam.length()));
				result = startNum == endNum ? true : false;
			} else {
				result = Integer.parseInt(beginDateParam.substring(0, 4)) == Integer
						.parseInt(endDateParam.substring(0, 4)) ? true : false;
			}
		}
		return result;
	}
	
}
