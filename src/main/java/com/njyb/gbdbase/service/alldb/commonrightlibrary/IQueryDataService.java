package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 权库查询数据接口
 * @author WangBo
 * 2015年5月28日
 * IQueryDataService.java
 */
public interface IQueryDataService {

	/**
	 * 汇总数据  市场分析
	 * @param request
	 * @param key
	 * @param value
	 * @param countrys
	 * @param sortKey
	 * @param reportName
	 * @param isFlag
	 * @return
	 */
	List<DataReportSumModel> queryDataByParams(HttpServletRequest request, String[] key, String[] value,
			String countrys,String reportName,boolean isFlag);
	
	/**
	 * 查询数据  交易记录
	 * @param feilds
	 * @param values
	 * @param country
	 * @param module
	 * @param request
	 * @param pageBean
	 * @return
	 */
	List<AllDBModel> queryAllDBData(String module, HttpServletRequest request, PageBeanUtil pageBean,Map<String,Map<String,Object>> resultMap);
}
