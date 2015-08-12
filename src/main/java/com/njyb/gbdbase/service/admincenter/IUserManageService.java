package com.njyb.gbdbase.service.admincenter;

import java.util.List;

import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;

/**
 * 用户后台管理
 * @author hu
 */
public interface IUserManageService {
	/**
	 * 根据当前用户的Id获取全部子用户ID
	 * @param uId
	 * @return
	 */
	 List<UserModel> queryAllSons(int uId);
	/**
	 * 根据查询条件分页查询全部用户
	 * @param query
	 * @return
	 */
	 List<UserModel> queryAllUsersByDescForPaging(QueryModel query);
	 /**
	  * 根据用户描述查询该类用户在数据库中的全部数量
	  * @param query
	  * @return
	  */
	 int queryUserCountByDesc(QueryModel query);
	 /**
	  * 获取用户树
	  * @return
	  */
	 String queryUserTree();
	 /**
	  * 根据查询模型获单个用户的全部信息
	  * @param query
	  * @return
	  */
	 UserModel queryUserById(QueryModel query); 
	 /**
	  * 根据用户ID获取用户层数
	  * @param query
	  * @return
	  */
	 int queryFloorById(QueryModel query);
	 /**
	  * 添加新用户
	  * @param user
	  * @return
	  */
	 String addNewUser(UserModel user);
	 /**
	  * 根据用户ID修改用户
	  * @param user
	  * @return
	  */
	String upUser(UserModel user);
	/**
	 * 根据用户ID修改用的子用户数量
	 * @param user
	 * @return
	 */
	String upUserSonCount(UserModel user);
	/**
	 * 根据用户ID修改部分字段状态并返回该字段的最新状态
	 * @param query
	 * @return
	 */
	String updateUserFiedStatus(QueryModel query);
	/**
	 * 根据用户Id修改用户身份
	 * @param userId
	 * @param userDesc
	 */
	void updateUserDesc(int  userId,String userDesc);
	/**
	 * 根据用的ID修改用户的详细信息
	 * @param user
	 * @return
	 */
	boolean upUserInfo(UserModel user);
	
}
