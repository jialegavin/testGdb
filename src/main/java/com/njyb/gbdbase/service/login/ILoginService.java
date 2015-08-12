package com.njyb.gbdbase.service.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njyb.gbdbase.model.admincenter.UserModel;

/**
 * 用户登录模块的业务接口
 * @author honghao
 * 2015-03-06
 */
public interface ILoginService {
	
	/**
	 * 改变用户的成功登录状态
	 * @param user 用户实体bean
	 * @param request
	 */
	public void changeUser(UserModel user,HttpServletRequest request) throws Exception;

	/**
	 * 获取客户单用户访问的真实ip地址
	 * @param request
	 * @retrun String 用户实际登录的ip地址
	 * @throws Exception
	 */
	public String getIpAddress(HttpServletRequest request) throws Exception;
	
	/**
	 * 登录模块的总方法
	 * 其他表示用户登录被锁定
	 * LOGIN_SUCCESS:表示用户成功登录
	 * PASSWD_INCORRENT:表示密码不正确
	 * USER_NOTEXIST:表示用户不存在
	 * VERIFICATION_INCORRENT:表示用户验证码不正确
	 * ELSEWHERE_LOGIN:表示该用户已在其他地方登录
	 * USER_LOCKED:该用户以被禁用
	 * INACTIVE:表示未激活
	 * @param regCode 校验码
	 * @param rpassword 是否记住密码
	 * @return String 返回的各种校验码
	 */
	public String checkedUserLogin(String doMainName,UserModel userModel,String regCode,String rPassword,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	/**
	 *用户是否激活 
	 *@param loginName 登录名
	 *@return Integer 
	 */
	public Integer userIsactivated(String loginName);
	
	
	/**
	 * 判断用户是否选中了“记住密码”，如果选中则放到Cookie总
	 * @param rPassword
	 * @param userModel
	 * @param response
	 * @throws Exception 
	 */
	public void rePassword(String rPassword,UserModel userModel,HttpServletResponse response) throws Exception;
	
	/**
	 * 获取当前的时间 
	 * @return String
	 */
	public String getDateStr();
	
	/**
	 * 根据用户名查看用户表记录
	 * @return String
	 */
	public UserModel queryUserByName(String loginName);
	
	/**
	 *用户登录错误的情况下的改变用户状态
	 *@param loginName 登录名
	 *@return Integer
	 */
	public void userLoginerror(UserModel user);
	
	/**
	 * 同一个账号同时登陆的地方不超过3个
	 * @throws Exception 
	 */
	public boolean limitLoginTimes(UserModel user,HttpServletRequest request)throws Exception;
	
	/**
	 * 判断用户的角色,如果用户为管理员账户，跳转到后台管理页面
	 * 如果用户为普通用户或者试用用户，跳转到国家查询页面
	 * @throws IOException 
	 */
	public String jugeUserRole(HttpServletRequest request,HttpServletResponse response) throws IOException;
	/**
	 * 注册用户
	 * 返回：1:注册成功
	 *      2:验证码错误
	 *      3:邮箱错误
	 * @param user(用户)
	 * @param code(验证码)
	 * @param request
	 * @param response
	 * @return
	 */
	String regestUser(UserModel user,String code,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 根据用信息发送激活邮件
	 * @param userMdoel
	 * @param request
	 */
	boolean sendActiveEmail(UserModel userMdoel,HttpServletRequest request);
    /**
     * 根据用户反馈的信息激活用户
     * @param emailModel
     * @return
     */
	public String activeUser(String rid, String loginName,HttpServletRequest request,HttpServletResponse response);
}