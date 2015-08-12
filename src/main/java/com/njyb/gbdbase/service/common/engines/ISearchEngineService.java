package com.njyb.gbdbase.service.common.engines;

import java.util.List;

import org.w3c.dom.views.AbstractView;

import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;

/**
 * lucene的调用接口
 * @version 第一次创建
 * @author 贾红平
 *
 */
public interface ISearchEngineService {
	/**
	 * 返回整形集合
	 * @param model 封装请求参数
	 * @return list(Integer)
	 */
	public  List<Integer> getListKey(SearchCommonParamModel model);
	
}
