package com.njyb.gbdbase.service.common.engine.builder.componet.state.impl;

import java.io.File;

import org.apache.lucene.search.IndexSearcher;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbase.service.common.engine.api.searcher.LuceneSearch;
import com.njyb.gbdbase.service.common.engine.builder.componet.state.ISearcherState;

/**
 * 返回除美国之外的其它国家没有海关编码的 indexsearcher
 * 
 * @author jiahp
 * 
 */
public class AllCountryState implements ISearcherState {
	/**
	 * 返回除美国之外的其它国家没有海关编码的 indexsearcher
	 */
	@Override
	public IndexSearcher handleIndexSearcher(String country,String indexpath, String[] fields,
			String[] values) {
		File file = new File((IConstantUtil.COMMON_INDEXPATH + "/" + indexpath));
		try {
			return LuceneSearch.getMultiFieldSearcher(file,country);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
