package com.njyb.gbdbase.dao.usermanagement;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.model.usermanagement.ViewModel;

/**
 * 用户权限dao
 * @author chenhu
 * 2015年3月26日
 */
@MyBatisReposity
public interface IRightDao {
	/**
	 * 根据查询模板查询权限模板列表
	 * @param query
	 * @return
	 */
      public List<ConditionRightModel> queryRightsByUidCN(QueryModel query);
     /**
      * 分页查询用户的权限
      * @param query
      * @return
      */
	 public List<ConditionRightModel> queryConditionRightsForPaging(QueryModel query);
	 /**
	  * 判断子用户的购买的国家时间范围是否超过
	  * @param countyRight
	  */
	 public void queryCoutryCountByTimeLimit(ConditionRightModel countyRight);
	 /**
	  * 查询所有的国家
	  * @param viewName 试图名称
	  * @return
	  */
	public List<ViewModel> queryAllCountries();
	/**
	 * 根据用户Id查询该用户拥有多少个国家权限
	 * @return
	 */
	public List<String> queryRigthCountryByUserId(int userId);
	/**
	 * 根据用户ID和国家代码查询该用户的国家权限数量
	 * @param query
	 * @return
	 */
	public int queryCountCountryRightByUserID(QueryModel query);
	/**
	 * 添加权限
	 * @param countyRight
	 */
    public void addRight(Map rightMap);
    /**
     * 根据用户ID和权限类别统计权限数量
     * @param query
     * @return
     */
	public int queryRightNum(QueryModel query);
	/**
	 * 判断该用户是否具有该查询条件
	 * @param condition
	 */
	public int checkHaveSearchRight(ConditionRightModel condition);  
	
}
