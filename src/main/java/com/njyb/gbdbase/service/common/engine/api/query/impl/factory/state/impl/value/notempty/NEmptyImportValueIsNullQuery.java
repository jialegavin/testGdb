package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.notempty;

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
 * 针对进口商的状态是:可以是空值 但是不能是物流
 * 
 * @author 贾红平
 *
 */
@Component
@SuppressWarnings("all")
public class NEmptyImportValueIsNullQuery extends CommonQuery implements
		IBooleanQueryState {
	@Resource
	private SearchPropertiesModel propertiesMap;
	/**
	 * 针对进口商的状态是:可以是空值 但是不能是物流
	 */
	@Override
	public BooleanQuery getBooleanQueryByState(String[] fs, String[] vs,
			String country, HttpServletRequest request) {
		for (int i = 0; i < fs.length; i++) {
			bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam("importer",
					"nodetailinformation", country, request), Occur.MUST);
			bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam("importer", propertiesMap
					.getPropertiesMap().get("logists").toString(), country,
					request), Occur.MUST);
			if (fs[i].equals("importer")) {
				bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam(fs[i], vs[i],
						country, request), Occur.SHOULD);

			} else {
				bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam(fs[i], vs[i],
						country, request), Occur.MUST);
			}
		}
		return bq;
	}

}
