package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;

import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.AbstractBooleanQuery;
import com.njyb.gbdbase.service.common.engine.util.LuceneAnaylazeyUtil;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 针对中国八位返回具体的查询对象
 * 
 * @author 贾红平
 *
 */
public class ChinaBooleanQuery extends AbstractBooleanQuery {
	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.common.engine.api.query.impl.factory.AbstractBooleanQuery#getBooleanQuery(java.util.List, java.util.List, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public BooleanQuery getBooleanQuery(List<String> fs, List<String> vs,
			String country,HttpServletRequest request) {
		// 只有一个元素 证明 只包含进出口类型
		if (country.equals(ParamEnumUtil.china.toString())) {
			if (fs.size() == 1) {
				int lv = vs.get(0).length();
				if (lv == 2 || lv == 1) {
					bq.add(new TermQuery(new Term(ParamEnumUtil.trade_type.toString(), vs.get(0))),
							Occur.MUST);
				}
				if (lv == 5 || lv == 2) {
					bq.add(new TermQuery(new Term(ParamEnumUtil.trade_type.toString(), vs.get(0).split(
							",")[0])), Occur.SHOULD);
				}
				fs.remove(0);
				vs.remove(0);
			}
			int a = fs.size();
			// 多个元素
			if (a > 1) {
				for (int i = a - 1; i >= 0; i--) {
					if (fs.get(i).equals(ParamEnumUtil.trade_type.toString())) {
						if (vs.get(i).length() == 5 || vs.get(i).length() == 2) {
							bq.add(new TermQuery(new Term(fs.get(i), vs.get(i))),
									Occur.MUST);
							fs.remove(i);
							vs.remove(i);
						} else {
							bq.add(new TermQuery(new Term(fs.get(i), vs.get(i))),
									Occur.MUST);
							fs.remove(i);
							vs.remove(i);
						}
						continue;

					}
					// 针对报关口岸
					if (fs.get(i).equals(ParamEnumUtil.start_port.toString())) {
						bq.add(new TermQuery(new Term(fs.get(i), vs.get(i))),
								Occur.MUST);
						fs.remove(i);
						vs.remove(i);
						continue;
					}

				}
			}
			// 重新遍历集合--先对用户输入的词进行分词 然后再动态生成查询对象
			if (fs.size() > 0) {
				for (int k = 0; k < fs.size(); k++) {
					bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam(fs.get(k),
							vs.get(k), country,request), Occur.MUST);
				}
			}
		}
		return bq;
	}

}
