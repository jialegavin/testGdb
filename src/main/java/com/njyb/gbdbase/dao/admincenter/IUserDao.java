package com.njyb.gbdbase.dao.admincenter;

import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.model.paypal.UserChargeModel;


/**
 * 用户的Dao
 * @author chenhu
 * 2015年3月25日
 *
 * 2015.07.22 Gavin has added several function to operate user charging
 */
@MyBatisReposity
public interface IUserDao {

	/**
	 * change user charing status
	 * @param beginTime
	 * @param endTime
	 * @param userDesc
	 * @param loginname
	 */
	void updateUserTime(String loginName,String beginTime, String endTime);
	/**
	 *
	 * @param loginName
	 * @return
	 */
	String getEndTime(String loginName);
	/**
	 * add user time for user service
	 * @param loginName
	 * @param endTime
	 */

	void addUserTime(String loginName, String endTime);
	/**
	 * Used to save charge information of user
	 * @param loginName
	 * @param date
	 * @param ip
	 * @param model
	 */
	void saveUserCharge(UserChargeModel charemodel);
	/**
	 * Used to check if LoginName exits
	 * @param userModel
	 * @return
	 */
	int checkLoginName(UserModel userModel);




	/**
	 * 根据sql查询用户Model列表
	 * @param userId
	 */
	List<UserModel> queryUsersBySql(QueryModel queryModel);
	/**
	 * 根据Sql查询单个用户
	 * @param userName
	 * @return
	 */
	UserModel queryUserBySql(QueryModel queryModel);
    /**
     * 根据Sql查询部分字段在数据库中的数量
     * @param loginName
     * @return
     */
	Integer queryUserCountBySql(QueryModel queryModel);
	/**
	 * 分页查询子用户
	 * @param map
	 * @return
	 */
	List<UserModel> queryUsersForPaging(QueryModel queryModel);
	/**
	 * 根据用户描述分页查询所有用户列表
	 * @param query
	 * @return
	 */
	List<UserModel> queryUsersByDesc(QueryModel query);
	/**
	 * 根据用户描述获取该类型用户数据中的总数量
	 * @param query
	 * @return
	 */
	int queryUserCountByDesc(String userdesc);

	/**
	 * 根据用户名修改用户的激活状态
	 * @param loginName
	 */
	void updateUserBySql(QueryModel query);
	/**
	 * 修改用户的登录状态
	 */
	void updateUserModel(UserModel model);
	/**
	 * XL
	 * 修改用戶密碼
	 * @param model : 用户
	 */
	int updatePassword(UserModel model);
	/**
	 * 修改子用户账户信息
	 * @param userSonModel
	 * @return
	 */
	int updateUserInfo(UserModel userModel);
	/**
	 * 修改用户开通子账户权限
	 * @param userSonModel
	 * @return
	 */
	int updateIsOpenSubService(UserModel userSonModel);

	/**
	 * 添加新账户
	 * @param model
	 */
	void addNewUser(UserModel model);

	/**
	 * 根据用户ID删除当前用户的全部信息
	 * @param userid
	 */
	void deleteUserAllMesseByUserID(int userId);
	/**
	 * 获取不同的用户类别
	 * @return
	 */
	List<String> queryDiffUserByDesc();
	/**
	 * 根据用户ID获取用户
	 * @param queryModel
	 * @return
	 */
	UserModel queryUserByID(int id);
	/**
	 * 根据用户ID修改用户拥有的子账户数量
	 * @param user
	 */
	void updateUserSonCount(UserModel user);
	/**
	 * 查询用户的部分状态字段
	 * @param query
	 * @return
	 */
	String queryUserFieldBySql(QueryModel query);
	/**
	 * 根据用户ID修改用户创建的子账户的数量
	 * @param user
	 */
	void updateUserSonNum(UserModel user);
	/**
	 * 根据用户ID修改用户的身份
	 * @param userId
	 * @param userDesc
	 */
	void updateUserDescById(QueryModel query);
	/**
	 * 根据用的ID修改用户的详细信息
	 * @param user
	 */
	void upUserInfo(UserModel user);

}
