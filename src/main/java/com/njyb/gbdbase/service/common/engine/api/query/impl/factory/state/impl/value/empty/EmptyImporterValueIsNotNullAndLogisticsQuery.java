package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.empty;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.IBooleanQueryState;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.CommonQuery;
import com.njyb.gbdbase.service.common.engine.util.LuceneAnaylazeyUtil;
/**
 * 针对进口商的状态是:既不能是空值 也不能是物流
 * @author 贾红平
 *
 */
@Component
public class EmptyImporterValueIsNotNullAndLogisticsQuery extends CommonQuery implements IBooleanQueryState {
	@Resource
	private SearchPropertiesModel model;
	/**
	 * 针对进口商的状态是:既不能是空值 也不能是物流
	 */
	@Override
	public BooleanQuery getBooleanQueryByState(String[] fs, String[] vs,
			String country,HttpServletRequest request) {
	    for (int i = 0; i < fs.length; i++) {
	    	bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam("importer","N/A", country,request), Occur.MUST_NOT);
			bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam("importer",model.getPropertiesMap().get("logists").toString(), country,request), Occur.MUST_NOT);
			bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam(fs[i],vs[i], country,request), Occur.MUST);
		}
		return bq;
	}
}
