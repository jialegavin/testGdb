package com.njyb.gbdbase.service.common.engine.util;

import java.io.IOException;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
/**
 * 
 * @author jiahp
 * 封装lucene分页模型对象
 *
 */
public class LucenePageUtil {
	/**
	 * 分页辅助方法
	 * 
	 * @return 目标页上一页的最后一条文档
	 * @throws IOException
	 */
	public static ScoreDoc getLastScoreDoc(int pageIndex, int pageSize,
			Query query, IndexSearcher searcher, Filter filter)
			throws IOException {
		if (pageIndex == 1)
			return null;
		// 取出上一页的数量
		int num = pageSize * (pageIndex - 1);
		TopDocs tds = searcher.search(query, filter, num);
		// 根据页码和分页大小返回最后一个scoreDoc
		return tds.scoreDocs[num - 1];
	}
}
