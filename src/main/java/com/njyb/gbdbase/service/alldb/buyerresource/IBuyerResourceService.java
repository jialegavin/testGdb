package com.njyb.gbdbase.service.alldb.buyerresource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.alldb.projectAnalyze.BuyerModel;

/**
 * 买家资源库
 * @author WangBo
 * 2015年4月28日
 * IbuyerResouceService.java
 */
public interface IBuyerResourceService {
	
	/**
	 * 查询买家资源库
	 * @param request : 请求
	 * @param fields : key 
	 * @param values : value
	 * @param countryName : 国家
	 * @param module : 枚举
	 * @param page : 分页
	 * @return
	 */
	List<BuyerModel> queryBuyerModelList(HttpServletRequest request,String[] fields, String[] values, String countryName,String module, PageBeanUtil page);
	
	/**
	 * 根据国家集合筛选国家
	 * @param countryName
	 * @return
	 */
	List<BuyerModel> queryBuyerModelByCountry(String countryName,List<BuyerModel> buyerList);
}