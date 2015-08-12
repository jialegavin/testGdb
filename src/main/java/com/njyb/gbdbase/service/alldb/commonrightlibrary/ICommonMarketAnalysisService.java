package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.List;
import java.util.Map;

/**
 * 市场分析 共用服务接口<br>
 * 适用于<br>
 * 我的产品<br>
 * 我的竞争对手<br>
 * 我的客户信息<br>
 * @author WangBo
 * 2015年4月13日
 * @param <T>
 * ICommonMarketAnalysisService.java
 */
public interface ICommonMarketAnalysisService<T> {
	
	/**
	 * 市场分析共用接口<br>
	 * 我的产品<br>
	 * 我的竞争对手<br>
	 * 我的客户信息<br>
	 * @param paramMap : 条件Map
	 * @return
	 */
	List<T> queryMarketAnalysisData(Map<String,Object> paramMap);
}