package com.njyb.gbdbase.service.personalcenter.password;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.GetDoMainNameUtil;
import com.njyb.gbdbas.util.MD5Util;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.njyb.gbdbase.dao.personalcenter.password.IUserPasswordDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.personalcenter.email.MailModel;
import com.njyb.gbdbase.model.personalcenter.password.EmailModel;
import com.njyb.gbdbase.model.personalcenter.password.PasswordCommon;
import com.njyb.gbdbase.model.personalcenter.password.PasswordModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.personalcenter.email.IEmailService;
import com.njyb.gbdbase.service.usermanagement.IUserSonBaseOperService;

/**
 * 用户密码 找回 和 修改
 * 
 * @author WangBo
 */
@Service
public class UserPasswordManageService implements IUserPasswordManageService {

	// 日志
	private static final Logger log = Logger
			.getLogger(UserPasswordManageService.class);

	// 用户登录DAO
	@Autowired
	private IUserDao iLoginDao;

	// 用户密码DAO
	@Autowired
	private IUserPasswordDao iUserPasswordDao;

	// 邮件发送接口
	@Autowired
	private IEmailService iEamilService;

	// 用户Dao
	@Autowired
	private IUserDao userDao;
	@Autowired
	IUserSonBaseOperService  userSonBaseOperService; 

	/**
	 * 用户密码效验
	 * 
	 * @param passwordCommon
	 *            : 接口
	 * @param userModel
	 *            : 用户模型
	 * @return {0 : 原密码,输入密码一致  1 : 原密码,输入密码不一致  2 : 密码为空}
	 */
	@Override
	public int checkPassword(PasswordCommon passwordCommon, UserModel userModel) {
		int resultNum = 0;
		if (null != passwordCommon && null != userModel) {
			PasswordModel passwordModel = (PasswordModel) passwordCommon;
			String oldPassword = MD5Util.encodeByMD5(
					passwordModel.getOldPassword()).trim();
			String userLoginPassword = userModel.getLoginPassword().trim();
			// 如果用户密码和输入的原密码一致
			if (userLoginPassword.equals(oldPassword)) {
				resultNum = 0;
			} else {
				resultNum = 1;
			}
		} else {
			resultNum = 2;
		}
		return resultNum;
	}

	/**
	 * 验证邮箱<br>
	 * 1.验证邮箱是否属于用户<br>
	 * 2.一天不能超过3次<br>
	 * 
	 * @param userModel
	 *            : 用户模型
	 * @return 0 : 验证通过 1 : 违法邮箱 2 : 一天找回密码超过了3次邮箱 3 : 系统没有此邮箱
	 */
	@Override
	public int checkUserModel(UserModel userModel, UserModel sessionUserModel) {
		int result = 0;
		// 非空判断
		if (null != userModel && null != sessionUserModel) {
			// 如果邮箱和session的邮箱不一致,则为 违法邮箱
			if (!userModel.getEmail().equals(sessionUserModel.getEmail())) {
				return 1;
			}
			// 去数据库查询最新的数据
			QueryModel queryModel = new QueryModel();
			queryModel.setWhereSql(" EMAIL = '" + userModel.getEmail() + "'");
			// 如果系统中没有此邮箱
			if (userDao.queryUserCountBySql(queryModel) == 0) {
				return 3;
			}
			EmailModel emailModel = (EmailModel) queryEmailModelByUserId(sessionUserModel
					.getUserId());
			// 如果Email表中没有这个用户,则添加到表中
			if (0 == emailModel.getUserId()) {
				emailModel.setEmailCount(0); // 初始化为0
				emailModel.setSendTime(new Date());
				emailModel.setUserId(sessionUserModel.getUserId());
				iUserPasswordDao.addEmailModelByModel(emailModel);
				result = 0;
			} else {
				// 一天是否找回密码超过3次
				if (result == 0) {
					try {
						if (DataUtil.countTime(emailModel.getSendTime()) < 24 * 60) {
							if (emailModel.getEmailCount() >= 3) {
								return 2;
							}
						}
					} catch (ParseException e) {
						log.debug(e.getMessage());
					}
				}
			}
		}
		return result;
	}

	/**
	 * 根据用户id查询出邮箱,生成密钥
	 */
	@Override
	public boolean sendEmail(UserModel userMdoel,HttpServletRequest request) {
		// 如果用户id为空
		if (userMdoel.getUserId() == 0) {
			return false;
		}
		EmailModel emailModel = (EmailModel) queryEmailModelByUserId(userMdoel
				.getUserId());
		String key = MD5Util.encodeByMD5(emailModel.getSendTime() + UUID.randomUUID().toString() + emailModel.getUserId());
		emailModel.setEmailCount(emailModel.getEmailCount() + 1);
		emailModel.setsId(key);
		emailModel.setSendTime(new Date());
		iUserPasswordDao.addEmailModelByModel(emailModel);		//添加
		MailModel mailModel = new MailModel();
		//拼接连接
		StringBuilder sb = new StringBuilder("http://" + request.getServerName() + ":")
		.append(request.getServerPort())
		.append(request.getContextPath())
		.append("/UserPassword/emailCheckUrl")
		.append("?sId=" + key + "&loginName=" + userMdoel.getLoginName());
		String contextUrl = GetDoMainNameUtil.getContextDoMain(request);
		Map<String,Object> emailParamMap = Maps.newHashMap();
		emailParamMap.put("updateUrl", contextUrl);
		emailParamMap.put("username", userMdoel.getLoginName());
		emailParamMap.put("url", sb.toString());
		emailParamMap.put("companyName", "江苏省南通趣易信息技术有限公司");
		mailModel.setSubject(sb.toString());
		mailModel.setToAddreress(userMdoel.getEmail());
	//	mailModel.setFromAddress("TradeEasy@163.com");
		mailModel.setFromAddress("noreply@inforvellor.com");
		//发送邮件
		iEamilService.sendAttachMail(mailModel, emailParamMap, "findPwdTemplate.html");
		return true;
	}
	
	/**
	 * 根据名称查找是否存在对象
	 */
	@Override
	public PasswordCommon queryEmailModelByUserId(int userId) {
		PasswordCommon passwordCommon = iUserPasswordDao
				.queryEmailModelByUserId(userId);
		return null == passwordCommon ? new EmailModel() : passwordCommon;
	}

	/**
	 * 修改密码
	 */
	@Override
	public int updatePassword(UserModel userModel) {
		int resultNum = 0; 
		if (null != userModel) {
			String password = MD5Util.encodeByMD5(userModel.getLoginPassword());
			userModel.setLoginPassword(password);
			resultNum = iLoginDao.updatePassword(userModel);
		}
		return resultNum;
	}
	
	/**
	 * 根据参数获取EmailModel对象
	 */
	@Override
	public boolean queryEmailByParam(Map<String, Object> param) {
		boolean result = false;
		if (null != param) {
			//根据验证码查询是否存在
			EmailModel emailModel = (EmailModel) iUserPasswordDao.queryEmailByParam(param);
			if (null != emailModel) {
				//如果在半个小时之内
				long emailTime = emailModel.getSendTime().getTime();
				long nowTime = new Date().getTime();
				if (nowTime - emailTime < 60 * 60 * 1000) {
					result = true;
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据邮件连接修改密码
	 */
	@Override
	public boolean updateEmailByUser(UserModel userModel) {
		if (null != userModel) {
			QueryModel query = new QueryModel();
			//UPDATE SYS_USER SET ${updateSql} WHERE ${whereSql}
			query.setUpdateSql(" loginPassword = '" + userModel.getLoginPassword() + "'");
			query.setWhereSql(" userId = " + userModel.getUserId());
			iLoginDao.updateUserBySql(query);
		}
		return true;
	}

	/**
	 * 判断用户名是否存在
	 */
	@Override
	public String exsit(String loginName,String veritycode,HttpServletRequest request) {
		if (loginName != null && !"".equals(loginName)) {
			QueryModel queryModel = new QueryModel();
			queryModel.setLoginName(loginName);
			//根据用户名查询账户
			UserModel  userModel = userSonBaseOperService.queryUserByLoginName(queryModel);
			//如果账户存在就判断验证码
			if (null != userModel) {
		//		String chechCode=(String)request.getSession().getAttribute("randCode");
				//当验证码为空或者验证码不正确时直接返回1
		//		if((chechCode==null)||("".equals(veritycode))||!(chechCode.equals(veritycode))){
		//			 return "1";
		//		}
		//		else
		//		{
					//将获取的用户放入session
					request.getSession().setAttribute("user", userModel);
					//所有验证正确直接返回2
					 return "2";
		//		}
			}else{
				 //用户账户不存在直接返回0
				return "0";
			}
		}
		//用户账户不存在直接返回0
		return "0";
	}

	
	/**
	 * 组织模板
	 */
	@Override
	public void sendEmailTemplate(UserModel userMdoel,HttpServletRequest request) {
		Map<String,Object> emailParamMap = Maps.newHashMap();
		int randCode = (int)((Math.random()*9+1)*100000);
		// 保存验证码
		EmailModel emailModel = new EmailModel();
		emailModel.setUserId(userMdoel.getUserId());
		emailModel.setSendTime(new Date());
		emailModel.setsId(String.valueOf(randCode));
		iUserPasswordDao.addEmailModelByModel(emailModel);
		// 生成模板
		emailParamMap.put("validateCode", randCode);
		emailParamMap.put("companyName", "Inforvellor.Inc");
		emailParamMap.put("sendTime", DataUtil.parseDate(new Date(),7));
		MailModel mailModel = new MailModel();
		mailModel.setSubject("Accounting--Password Retrieve");
		mailModel.setToAddreress(userMdoel.getEmail());		
		mailModel.setFromAddress("noreply@inforvellor.com");
		//发送邮件
		iEamilService.sendAttachMail(mailModel, emailParamMap, "findPwdTemplate.html");
	}
}
