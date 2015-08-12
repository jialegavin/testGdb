package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.context;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanQuery;

import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.IBooleanQueryState;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.empty.EmptyImportValueIsNullQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.empty.EmptyImporterValueIsAnyQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.empty.EmptyImporterValueIsLogisticsQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.empty.EmptyImporterValueIsNotNullAndLogisticsQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.notempty.NEmptyImportValueIsNullQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.notempty.NEmptyImporterValueIsAnyQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.notempty.NEmptyImporterValueIsLogisticsQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.impl.value.notempty.NEmptyImporterValueIsNotNullAndLogisticsQuery;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 管理状态上下文的容器对象
 * 
 * @author 贾红平
 *
 */
public class QueryStateContext {
	 

	/**
	 * 针对不同的状态 执行不同的行为 返回不同的对象
	 * 
	 * @param ks
	 * @param vs
	 * @param country
	 * @param falg
	 *            进口商文本框是否有值:yes 代表有 no:代表无没有
	 * @param state
	 *            代表用户选择多条件的状态
	 * @return
	 */
	public static BooleanQuery getBooleanQueryByImporterState(String[] ks,
			String[] vs, String country, String falg, String state,HttpServletRequest request) {
		IBooleanQueryState stateQuery = null;
		// 代表进口商文本框有值
		if (falg.equals(ParamEnumUtil.yes.toString())) {
			if (state.equals(ParamEnumUtil.imp_not_null.toString())) {
				stateQuery=new NEmptyImporterValueIsLogisticsQuery();
			} else if (state.equals(ParamEnumUtil.imp_not_wl.toString())) {
				stateQuery=new NEmptyImportValueIsNullQuery();
			} else if (state.equals(ParamEnumUtil.imp_not_wul_null.toString())) {
				stateQuery=new NEmptyImporterValueIsNotNullAndLogisticsQuery();
			} else {
				stateQuery=new NEmptyImporterValueIsAnyQuery();
			}
		}
		// 代表进口商文本框没有值
		if (falg.equals(ParamEnumUtil.no.toString())) {
			if (state.equals(ParamEnumUtil.imp_not_null.toString())) {
				stateQuery=new EmptyImporterValueIsLogisticsQuery();
			} else if (state.equals(ParamEnumUtil.imp_not_wl.toString())) {
				stateQuery=new EmptyImportValueIsNullQuery();
			} else if (state.equals(ParamEnumUtil.imp_not_wul_null.toString())) {
				stateQuery=new EmptyImporterValueIsNotNullAndLogisticsQuery();
			} else {
				stateQuery=new EmptyImporterValueIsAnyQuery();
			}
		}
		return stateQuery.getBooleanQueryByState(ks, vs, country,request);
	}
}
