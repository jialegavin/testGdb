package com.njyb.gbdbase.service.common.engines.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.componet.impl.ISearchDataComponent;
import com.njyb.gbdbase.service.common.engines.AbstractEngineService;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;

/**
 * 查询实现类
 * 
 * @author 贾红平
 */
@Service
@SuppressWarnings("static-access")
public class SearchEngineService extends AbstractEngineService implements ISearchEngineService {
	// 注入查询组件
	@Autowired
	private ISearchDataComponent dataComponent;
	@Autowired
	private SearchPropertiesModel propertiesMap;
	// 引入logger
	private static Logger log = Logger.getLogger(SearchEngineService.class);

	// 返回集合(整形)
	@Override
	public List<Integer> getListKey(SearchCommonParamModel model) {
		log.info("进入数据检索业务逻辑方法...");
		return dataComponent.getListKeyId(filterStrByCountry(model,propertiesMap.getPropertiesMap()));
	}
	
}
