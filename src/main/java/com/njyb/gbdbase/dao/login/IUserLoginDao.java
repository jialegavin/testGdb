package com.njyb.gbdbase.dao.login;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.login.UserLoginlogModel;

/**
 * 用户登录日志的dao层
 * @author honghao
 * 2015-03-10
 */

@MyBatisReposity
public interface IUserLoginDao {
	
	/*
	 * 写入日志的方法
	 * @param Model
	 */
	void insertLoginLog(UserLoginlogModel  logModel);
	
	/*
	 * 查询所有的用户登录日志
	 */
	List<UserLoginlogModel> getAllUserLoginLog(Map map);
	
	/*
	 * 根据姓名查询用户登录日志
	 */
	List<UserLoginlogModel> getLogByLoginName(Map map);
	
	/*
	 * 获得所有的条数
	 */
	int findLoginLogCount();
	
	/**
	 * 根据条件获取总条数
	 * @param map : 条件Map
	 * @return
	 */
	int queryUserLogCountByParam(Map<String,Object> map);
	
	//WangBo 添加的2个方法   2015-03-27
	/**
	 * 根据用户Id查询用户日志<br>
	 * 调用此方法时,请把userId封装成UserModel对象<br>
	 * 如果start和end{参数}为0,则查询全部
	 * @param userId : 用户Id
	 * @return List<UserLogModel>
	 */
	List<UserLoginlogModel> queryUserLogResultByUserIdAndPage(int userId);
	
	/**
	 * 插入用户模型
	 * @param userLogModel : 用户模型
	 * @return 1 : 保存成功 , 0 : 保存失败
	 */
	int addUserLog(UserLoginlogModel userLogModel);
    /**
     * 根据用户ID查询用户数量
     * @param userId
     * @return
     */
	int queryCountUserLogByUserId(int userId);
	
}
