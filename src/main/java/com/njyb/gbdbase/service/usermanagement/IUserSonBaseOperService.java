package com.njyb.gbdbase.service.usermanagement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.admincenter.UserIPModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;


/**
 * 子账户的基本操作（增删改查）
 * @author chenhu
 * 2015年4月27日
 */
public interface IUserSonBaseOperService {
	/**
	 * 根据用户名分页查询子用户列表
	 * @param queryModel
	 * @return
	 */
    List<UserModel> queryUserSonsByLoginNameForPaging(QueryModel queryModel);
    /**
     * 根据用户UserId分页查询子用户列表
     * @param queryModel
     * @return
     */
    List<UserModel> queryUserSonsByIdForPaging(QueryModel queryModel);
    /**
     * 根据用户Id查询用户的子用户数量
     * @param queryModel
     * @return
     */
    int queryUserSonNumById(QueryModel queryModel);
    /**
     * 根据用模型查询当前用户的全部信息
     * @param useId
     * @return
     */
     UserModel queryUserByLoginName(QueryModel queryModel);
     /**
  	 * 查询用登录的IP
  	 * @param request
  	 * @return
  	 */
 	String getIpAddress(HttpServletRequest request);

 	/**
 	 * 是否能创建子账户
 	 * 1.成功
 	 * 2.子帐号已经用完
 	 * 3.未开通子帐号的权限
 	 * 4.已超出3层不允许开通子帐号
 	 * @param user
 	 * @return
 	 */
    int checkAbleCreateSon(UserModel user);
    /**
     * 判断用户输入的条件是否满足创建子账户条件
     * @param user
     * @return
     */
    String ckeckInCondtionForAddSon(UserModel user);
 	 /**
     * 添加子账户
     * @param son
     * @param user
     * @return
     */
    boolean addUserSon(UserModel son, UserModel user);
    /**
     *更新用户信息
     * @param user
     * @return
     */
    boolean updateUserInfo(UserModel user);
    /**
     * 激活用户
     * @param user
     * @return
     */
    boolean updateUserActive(UserModel user);
    /**
   	 *修改用户创建子用户权限
   	 * @param userModel
   	 * @return
   	 */
   	boolean updateOpenSubService(UserModel user);

    /**
     * 根据用户ID删除一个用户
     * @param userId
     * @return
     */
    boolean  deleteUser(UserModel user);
    /**
     * 根据多个用户ID数组级联删除用户
     * @param ids
     * @return
     */
    boolean  deleteUsers(String[] ids);
	/**
 	 * 对用检查：符合数据库非空原则
 	 * @param user
 	 * @return
 	 */
 	boolean checkUser(UserModel user);

}
