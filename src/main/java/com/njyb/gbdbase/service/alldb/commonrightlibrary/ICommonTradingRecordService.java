package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;

/**
 * 交易记录 共用服务接口<br>
 * 适用于<br>
 * 我的产品<br>
 * 我的竞争对手<br>
 * 我的客户信息<br>
 * @author WangBo 2015年4月17日 
 * ICommonTradingRecordService.java
 */
public interface ICommonTradingRecordService<T> {
	
	/**
	 * 交易记录共用接口<br>
	 * 我的产品<br>
	 * 我的竞争对手<br>
	 * 我的客户信息<br>
	 * @param paramMap : 条件Map
	 * @return List<T>
	 */
	List<AllDBModel> queryTradingRecordData(Map<String,Object> paramMap,PageBeanUtil pageBeanUtil);
	
	/**
	 * 交易记录共用接口<br>
	 * 我的产品<br>
	 * 我的竞争对手<br>
	 * 我的客户信息<br>
	 * @param paramMap : 条件Map
	 * @param pageBeanUtil : 分页Bean
	 * @return Map<String,List<T>>
	 */
//	Map<String,Object> queryTradingRecordDataMap(Map<String,Object> paramMap,PageBeanUtil pageBeanUtil);
}