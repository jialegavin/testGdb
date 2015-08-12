package com.njyb.gbdbase.service.common.componet.impl;

import java.util.List;

import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;


/**
 * 查询业务所要依赖的组件
 * @author 贾红平
 *
 */
public interface ISearchDataComponent {
	/**
	 * 获取返回id集合
	 * @param model
	 * @return
	 */
	public List<Integer> getListKeyId(SearchCommonParamModel model);
	/**
	 * 获取返回任意object对象的集合
	 * @param model
	 * @return
	 */
	public List<Object> getListObj(SearchCommonParamModel model);
}
