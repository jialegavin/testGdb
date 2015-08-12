package com.njyb.gbdbase.service.personalcenter.password;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.personalcenter.password.PasswordCommon;

/**
 * 用户密码Service
 * @author WangBo
 */
public interface IUserPasswordManageService {
	
	/**
	 * 用户密码效验
	 * @param passwordCommon : 接口
	 * @param userModel : 用户模型
	 * @return {0 : 密码修改成功  1 : 原密码错误  2 : 密码为空} 
	 */
	public int checkPassword(PasswordCommon passwordCommon,UserModel userModel);
	
	/**
	 * 1.验证邮箱是否属于用户<br>
	 * 2.一天不能超过3次<br>
	 * @param userModel : 用户模型
	 * @return 0 : 验证通过  1 : 违法邮箱  2 : 一天找回密码超过了3次邮箱  3 : 系统没有次邮箱
	 */
	public int checkUserModel(UserModel userModel,UserModel sessionUserModel);
	
	/**
	 * 根据名称查找是否存在对象
	 * @param userId : 用户ID
	 * @return Is not null Or  Is null {EmailModel}
	 */
	public PasswordCommon queryEmailModelByUserId(int userId);
	
	/**
	 * 根据用户id查询出邮箱,生成密钥<br>
	 * 发送邮件
	 * @param userMdoel : 用户
	 * @return true : 发送成功. false : 发送失败
	 */
	public boolean sendEmail(UserModel userMdoel,HttpServletRequest request);
	
	/**
	 * 修改密码
	 * @param userModel : 用户
	 * @return 1 : 成功  2 : 失败
	 */
	public int updatePassword(UserModel userModel);
	
	/**
	 * 判断邮件的连接地址的sId是否在半个小时之内,如果在:生效,如果没有:失效
	 * @param param : Map参数
	 * @return true : 生效 , false : 失效
	 */
	public boolean queryEmailByParam(Map<String,Object> param);
	
	/**
	 * 根据邮件连接修改密码
	 * @param userModel
	 * @return
	 */
	public boolean updateEmailByUser(UserModel userModel);
	
	/**
	 * 判断账户是否存在
	 * @author XL
	 * @param loginName 用户名
	 * @param veritycode 验证码
	 * @param request
	 * @return String
	 */
	String exsit(String loginName,String veritycode,HttpServletRequest request);
	
	/**
	 * 组织模板
	 * @author XL
	 * @param userMdoel
	 * @param request
	 */
	void sendEmailTemplate(UserModel userMdoel,HttpServletRequest request);
}