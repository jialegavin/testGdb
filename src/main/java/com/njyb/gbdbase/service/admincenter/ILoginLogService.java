package com.njyb.gbdbase.service.admincenter;

import java.util.List;

import com.njyb.gbdbase.model.login.UserLoginlogModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;

/**
 * 登录日志管理
 * @author hu
 */
public interface ILoginLogService {
	/**
	 * 分页查询用户登录日志列表
	 * @param qury
	 * @return
	 */
	List<UserLoginlogModel> queryUserLogModelForPaging(QueryModel query);
	/**
	 * 根据用户ID分页查询用户登录日志
	 * @param query
	 * @return
	 */
	List<UserLoginlogModel> queryUserLogModelByUserIdForPaging(QueryModel query);
	/**
	 * 根据用户的ID查询登录日志的数量
	 * @param query
	 * @return
	 */
	int queryCountUserLogModelByUserId(QueryModel query);
	/**
	 * 查询全部登录日志的数量
	 * @param query
	 * @return
	 */
	int queryCountUserLogModel(QueryModel query);
	
}
