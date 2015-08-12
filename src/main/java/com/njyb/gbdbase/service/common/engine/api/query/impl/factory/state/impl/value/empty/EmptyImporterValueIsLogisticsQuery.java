package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.empty;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;

import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.IBooleanQueryState;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.CommonQuery;
import com.njyb.gbdbase.service.common.engine.util.LuceneAnaylazeyUtil;
/**
 * 针对进口商的状态是:可以是物流但是不能是空值
 * @author 贾红平
 *
 */
public class EmptyImporterValueIsLogisticsQuery extends CommonQuery implements IBooleanQueryState {
	/**
	 * 针对进口商的状态是:可以是物流但是不能是空值
	 */
	@Override
	public BooleanQuery getBooleanQueryByState(String[] fs, String[] vs,
			String country,HttpServletRequest request) {
		for (int k = 0; k < fs.length; k++) {
			if (vs[k].equals("N/A")) {
					bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam("importer",
							"N/A", country,request), Occur.MUST_NOT);
				}
				else {
					bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam(fs[k],
							vs[k], country,request), Occur.MUST);
				}
		}
		return bq;
	}

}
