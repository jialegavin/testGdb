package com.njyb.gbdbase.service.common.componet.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
/**
 * 查询组件接口的具体实现
 * @author 贾红平
 *
 */
/**
 * 查询组件接口的具体实现
 * @author 贾红平
 *
 */
@Component
public class SearchDataComponent  extends CommonLuceneComponent implements ISearchDataComponent{
	@Override
	public List<Integer> getListKeyId(SearchCommonParamModel model) {
		return super.getListKey(model.getRequest(), model.getFeilds(), model.getValues(), model.getPage(), model.getCountry(), model.getModule());
	}
	@Deprecated
	@Override
	public List<Object> getListObj(SearchCommonParamModel model) {
		return null;
	}

}
