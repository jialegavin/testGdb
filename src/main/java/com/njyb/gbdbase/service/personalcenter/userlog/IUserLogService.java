package com.njyb.gbdbase.service.personalcenter.userlog;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.login.UserLoginlogModel;

/**
 * 用户日志Service
 * @author WangBo
 * 2015年3月26日
 * IUserLogService.java
 */
public interface IUserLogService {
	
	/**
	 * 根据用户Id查询用户日志
	 */
	public List<UserLoginlogModel> queryUserLogModelByUserModel(UserModel userModel);
	
	/**
	 * 根据名称查询UserModel
	 * @param loginName : 用户名
	 * @return UserModel
	 */
	UserModel queryUserModelByLoginName(String loginName);
	
	/**
	 * 根据UserLoginlogModel 查询出相关数据
	 * @param paramMap : 参数Map
	 * @return List<UserLoginlogModel>
	 */
	List<UserLoginlogModel> queryLogResultByLoginModel(Map<String,Object> paramMap);
	
	 /**
	 * 根据条件获取总条数
	 * @param userModel
	 * @return
	 */
	int queryUserLogCountByParam(UserModel userModel);
	
	/**
	 * 数据分页
	 * @param request
	 * @return page
	 * @auther honghao
	 */
	public PageBeanUtil getPageBeanUtil(HttpServletRequest request,String num);
	
	/**
	 * 分页起始位置id
	 * @param beanUtil
	 * @return
	 *	@auther honghao
	 */
	public int getStartIndex(PageBeanUtil beanUtil);
	
	/**
	 * 分页结束位置id
	 * @param beanUtil
	 * @param list
	 * @param start
	 * @return
	 *	@auther honghao
	 */
	public int getEndIndex(PageBeanUtil beanUtil,List<?> list,int start);
}
