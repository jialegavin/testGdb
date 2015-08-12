package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanQuery;

import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.AbstractBooleanQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state.context.QueryStateContextFacade;
/**
 * 针对美国返回一个具体的查询对象
 * @author 贾红平
 *
 */
public class UsaBooleanQuery extends AbstractBooleanQuery {
	/*
	 * (non-Javadoc)
	 * @see com.njyb.gbdbase.service.common.engine.api.query.impl.factory.AbstractBooleanQuery#getBooleanQuery(java.util.List, java.util.List, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public BooleanQuery getBooleanQuery(List<String>fs,List<String>vs,String country,HttpServletRequest request) {
		//美国比较特殊 主要是针对进口商这块 
		//1 进口商可以为空 可以为物流
		//2 进口商可以为空 但是不可以为物流
		//3 进口商可以是物流 但是不可以是空值
		//4 进口商既不能为空也不能为物流
		//进口商文本框是否为空 yes or no
		/*String importerIsEmty=request.getParameter("falg");*/
		/*临时使用*/
		String importerIsEmty="no";
		//1 进口商可以为空 可以为物流
		//imp_other,
		//2 进口商可以为空 但是不可以为物流
		//imp_not_wl,
		//3 进口商可以是物流 但是不可以是空值
		//imp_not_null,
		//4 进口商既不能为空也不能为物流
		//imp_not_wul_null,
		//代表进口商文本框输入框有填写值
		/*String importerState=request.getParameter("state");*/
		String importerState="imp_other";
		return QueryStateContextFacade.getBooleanQueryByImporterState(fs.toArray(new String[fs.size()]), vs.toArray(new String[vs.size()]), country, importerIsEmty, importerState,request);
	}

}
