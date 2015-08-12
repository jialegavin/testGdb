package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.notempty;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;

import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.IBooleanQueryState;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.CommonQuery;
import com.njyb.gbdbase.service.common.engine.util.LuceneAnaylazeyUtil;
/**
 * 针对进口商的状态是:任意类型的
 * @author 贾红平
 *
 */
public class NEmptyImporterValueIsAnyQuery  extends CommonQuery implements IBooleanQueryState{
	/**
	 * 针对进口商的状态是:任意类型的
	 */
	@Override
	public BooleanQuery getBooleanQueryByState(String[] fs, String[] vs,
			String country,HttpServletRequest request) {
		 for (int i = 0; i < fs.length; i++) {
			 bq.add(LuceneAnaylazeyUtil.getTokenInfoByParam(fs[i],
						vs[i], country,request), Occur.MUST);
		}
		return bq;
	}

}
