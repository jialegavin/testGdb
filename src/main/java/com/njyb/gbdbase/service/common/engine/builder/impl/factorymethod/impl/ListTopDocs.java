package com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.impl;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.ITopDocs;
import com.njyb.gbdbase.service.common.engine.util.LucenePageUtil;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
/**
 * 生成返回list的topcosc
 * @author jiahp
 *
 */
public class ListTopDocs implements ITopDocs {
	/**
	 * 生成返回list的topcosc
	 */
	@Override
	public TopDocs getTopDocs(IndexSearcher searcher, Query query,
			Filter filter, String module, PageBeanUtil page) {
		TopDocs td=null;
		try {
			ScoreDoc sc= LucenePageUtil.getLastScoreDoc(page.getPageIndex(),page.getPageSize(), query, searcher, filter);					
			if (searcher!=null) {
//				if (module.equals(ParamEnumUtil.search.name().toString())) {
//					td=searcher.search(query, filter, Integer.MAX_VALUE);
//				}
//				else{
					td = searcher.searchAfter(sc, query, filter, page.getPageSize());								
//				}
			}
			else{
				td=null;
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
 		return td;
	}

}
