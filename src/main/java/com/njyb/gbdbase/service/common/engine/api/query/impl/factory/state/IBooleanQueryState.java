package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.state;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanQuery;

/**
 * 抽象状态接口
 * @author 贾红平
 *
 */
public interface IBooleanQueryState {
	/**
	 * 定义抽象查询对象
	 * @param fs:参数
	 * @param vs:参数值
	 * @param country:国家简称
	 * @param falg:进口商文本框是否为空 yes:代表进口商文本框没有填写值 no:代表进口商文本框有填写值
	 * @return
	 */
	public abstract BooleanQuery getBooleanQueryByState(String[]fs,String[]vs,String country,HttpServletRequest request);
}
