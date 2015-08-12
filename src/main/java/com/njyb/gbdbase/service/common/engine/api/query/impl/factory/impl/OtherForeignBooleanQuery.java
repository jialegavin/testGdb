package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;

import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.AbstractBooleanQuery;
import com.njyb.gbdbase.service.common.engine.util.LuceneAnaylazeyUtil;
/**
 * 其它所有国外国家返回一个通用的查询对象
 * @author 贾红平
 *
 */
public class OtherForeignBooleanQuery extends AbstractBooleanQuery {
	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.common.engine.api.query.impl.factory.AbstractBooleanQuery#getBooleanQuery(java.util.List, java.util.List, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public BooleanQuery getBooleanQuery(List<String>fs,List<String>vs,String country,HttpServletRequest request) {
		for (int k = 0; k < fs.size(); k++) {
			bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam(fs.get(k),
					vs.get(k), country,request), Occur.MUST);
		}
		return bq;
	}

}
