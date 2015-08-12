package com.njyb.auth.service.impl.cmp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;

/**
 * 全库对用户权限控制接口
 * @author WangBo
 * 2015年6月28日
 * IUserRightAllDBService.java
 */
public interface IUserRightAllDBService {
	
	/**
	 * 动态更新全库用户查询model
	 * @param rightSearchModel
	 * @return
	 */
	public RightLibrarySearchModel ControlRightSearchModel(HttpServletRequest request,RightLibrarySearchModel rightSearchModel);
	
	/**
	 * 动态更新日期开始时间和结束时间
	 * @param request
	 * @param key
	 * @param value
	 * @param country
	 * @param keys
	 * @param index
	 * @return
	 */
	public List<String> getValue(HttpServletRequest request,List<String> key,List<String> value,String country,String keys,int index);
}