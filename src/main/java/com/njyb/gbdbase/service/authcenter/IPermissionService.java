package com.njyb.gbdbase.service.authcenter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;

/**
 * 用户权限验证服务
 * @author chenhu
 * 2015年4月27日
 */
public interface IPermissionService {
    /**
     * 判断该用户是否具有当前查询条件(暂时未做国际化)
     * @param user  当前用户
     * @param conditionModel 
     * @return 
     */
	String checkHaveSearchRight(UserModel user,	ConditionRightModel conditionModel);
    /**
     * 判断该用户是否具有查看明细的权限
     * @param user
     * @return
     */
	String checkHaveDetailRight(UserModel user);
	
	/**
	 * 根据用户Id查询用户权限
	 */
	public List<ConditionRightModel> queryUserRight(UserModel userModel) ;
	
	/**
	 * 添加用户权限 用于在定制时调用此方法,添加用户的权限
	 */
	public int insertUserRight(ConditionRightModel userRightModel);
	
	/**
	 * 判断用户是否有该模块权限
	 * @param module
	 * @return String 返回判断结果
	 */
	public String getRightByUser(HttpServletRequest request,Map map,String module) throws Exception;
}
