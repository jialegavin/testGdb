package com.njyb.gbdbase.service.alldb.projectAnalyze;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.usermanagement.MixDataModel;

/**
 * 同环比
 * @author WangBo
 * 2015年5月15日
 * IWithSequential.java
 */
public interface IWithSequentialService {

	/**
	 * 获取同环比数据
	 * @param paramMap
	 * @return
	 */
	public <T> Map<String, List<T>> queryWithSequentiallMap(Map<String,Object> paramMap);
	
	/**
	 * 生成图表model
	 * @param <T>
	 * @param list : list
	 * @return
	 */
	public <T> MixDataModel getMixDataModel(List<T> paramList);
	
}