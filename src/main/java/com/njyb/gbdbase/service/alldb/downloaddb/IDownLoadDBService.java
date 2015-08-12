package com.njyb.gbdbase.service.alldb.downloaddb;

import java.util.Map;

/**
 * 下载全库Service
 * @author WangBo
 * 2015年5月6日
 * IDownLoadDBService.java
 */
public interface IDownLoadDBService {

	/**
	 * 根据查询条件下载全库
	 * @param paramMap
	 * @return
	 */
	<T> Map<String, Map<String, Object>> downLoadDBByParams(Map<String,Object> paramMap);
}