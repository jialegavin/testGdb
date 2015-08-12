package com.njyb.gbdbase.service.usermanagement;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.export.UserDownloadRightModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.model.usermanagement.ViewModel;


/**
 * 用户权限操作
 * 备注：除非删除该用户时删除该用的全部权限，没有删除用户部分的功能
 * @author hu
 */
public interface IUserSonRightService {
	/**
	 * 查询用户的条件查询权限
	 * 根据当查询的用户Id和查询国家
	 * 获取账户的权限和时间范围
	 * @param Id
	 * @return
	 */
      List<ConditionRightModel> queryRightsByUidCN(QueryModel query);
      /**
       * 添加用户条件查询权限
       * 一次添加一条权限
       * @param right
       * @return
       */
      boolean addUserConditionRight(Map rightMap);
      /**
       * 查询系统拥有的国家列表
       * @return
       */
      List<Map<String,String>> queryAllCountries(String lang);
      /**
       * 根据用户ID查该用户拥有的国家列表
       * @param query
       * @return
       */
      List<String> queryCountriesByUid(QueryModel query);
      /**
       * 根据国家和用户ID查询该用户拥有的权限数量
       * @param query
       * @return
       */
	 int queryCountCountryRightByUserID(QueryModel query);
	 /**
	  * 根据权限列表添加用户权限
	  * @param list
	  * @return
	  */
	 boolean addRights(List<Map<String, String>> list,int uid);
	 /**
	  * 根据权限类型和用户ID分页查询用户列表
	  * @param query
	  * @return
	  */
	List<ConditionRightModel> queryRightsForPaging(QueryModel query);
	/**
	 * 根据权限类型和用户ID获取用户权限的数量
	 * @param query
	 * @return
	 */
	int queryRightNum(QueryModel query);
	/**
	 * 根据用户ID查询用户的下载数量
	 * @param query
	 * @return
	 */
	List<UserDownloadRightModel> queryDownLoadNum(QueryModel query);
	/**
	 * 修改下载数量
	 * @param downRightModel
	 * @return
	 */
	boolean updateDownNum(UserDownloadRightModel downRightModel);

      
}
