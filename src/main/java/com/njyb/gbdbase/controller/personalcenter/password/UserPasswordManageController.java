package com.njyb.gbdbase.controller.personalcenter.password;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.MD5Util;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.personalcenter.password.EmailModel;
import com.njyb.gbdbase.model.personalcenter.password.PasswordModel;
import com.njyb.gbdbase.service.login.ILoginService;
import com.njyb.gbdbase.service.personalcenter.password.IUserPasswordManageService;

/**
 * 用户密码 修改与找回
 * 
 * @author WangBo
 */
@Controller
@RequestMapping(value = "/UserPassword")
public class UserPasswordManageController extends PublicCommonController{
	
	// 日志
	private static final Logger log = Logger.getLogger(UserPasswordManageController.class);

	// 用户密码 Service
	@Autowired
	private IUserPasswordManageService userPasswordManageService;
	@Autowired
	private ILoginService iLoginService;

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 *             : IO异常
	 * @return {0 : 密码修改成功 1 : 原密码错误 2 : 密码为空}
	 */
	@RequestMapping(value = "/updateUserByPassword")
	public void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		UserModel model = (UserModel) session.getAttribute("user");
		PasswordModel pwd = new PasswordModel();
		pwd.setOldPassword((null == request.getParameter("userPassword") ? ""
				: request.getParameter("userPassword")));
		pwd.setNewPassword(((null == request.getParameter("newPassword") ? ""
				: request.getParameter("newPassword"))));
		// 验证密码
		int resultNum = userPasswordManageService.checkPassword(pwd, model);
		if (resultNum == 0) {
			model.setLoginPassword(pwd.getNewPassword());
			if (1 == userPasswordManageService.updatePassword(model)) {		//如果密码修改成功,session 删除此user
				session.removeAttribute("user");
			}
		}
		JSONObject json = new JSONObject();
		json.put("resultNum", resultNum);
		response.getWriter().println(json.toString());
	}
	

	/**
	 * 找回密码 暂时废弃
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findPasswordByUsername")
	public void findPasswordByUsername(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		UserModel userModel = new UserModel();
		userModel.setEmail(email);
		UserModel sessionUserModel = (UserModel) request.getSession().getAttribute("user");
		// 首先从session获取usermodel,如果有邮箱,则不去数据库查询,如果没有,获取数据库最新的userModel,如果没有邮箱,则提示没有这个邮箱
		// 1检查邮箱合法性,2 找回密码一天不能超过3次
		int result = userPasswordManageService.checkUserModel(userModel, sessionUserModel);
		if (0 == result) {
			// 生成连接.包含(用户名,密钥key,过期时间)保证一次连接只能点一次,并且半个小时后失效
			userPasswordManageService.sendEmail(sessionUserModel,request);
		} else {
			try {
				response.getWriter().println("result");
			} catch (IOException e) {
				log.debug(e.getMessage());
			}
		}
	}
	
	/**
	 * 验证邮件连接
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkEmailCheckUrl")
	public String checkEmailCheckUrl(HttpServletRequest request,
			HttpServletResponse response,EmailModel emailModel){
		Map<String,Object> map = Maps.newHashMap();
		map.put("sId", emailModel.getsId());
		if (userPasswordManageService.queryEmailByParam(map)) {
			//跳转修改页面
			return "login/findpassword/resetpwd";
		} else {
			//跳转上一级菜单
			return "login/findpassword/emailvalidate";
		}
	}
	
	/**
	 * 邮件邮件过来的连接修改密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(HttpServletRequest request,
			HttpServletResponse response){
		//获取用户
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		//获取页面需要修改的密码
		String password = request.getParameter("password");
		user.setLoginPassword(MD5Util.encodeByMD5(password));
		if (userPasswordManageService.updateEmailByUser(user)){
			//将修改的用户重新放入seesion
			request.getSession().setAttribute("user", user);
			//修改成功
			return "login/findpassword/resetpwdsuccess";
		} else {
			//修改失败
			return "login/findpassword/resetpwd";
		}
	}
	
	/**
	 * 页面验证用户账户是否存在
	 * 返回值0：表示账户不存在 1：验证码不正确 2：表示身份验证成功
	 * @author XL
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/isUserExit", method = RequestMethod.POST)
	public String confirmAccount(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserModel model, String veritycode)throws Exception 
	{
		String result = userPasswordManageService.exsit(model.getLoginName(), veritycode, request);
		response.getWriter().write(result);
		return null;
	}
	
	/**
	 * 发送验证码
	 * flag:0发送邮件失败 1：发送邮件成功
	 * @author XL
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String sendEmail(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String flag = "0";
		//获取用户
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		//判断用户是否为空
		if(null != userModel)
		{
			//判断用户邮箱是否为空
			if(!Strings.isNullOrEmpty(userModel.getEmail()))
			{
				//发送邮件
				userPasswordManageService.sendEmailTemplate(userModel,request);
				flag="1";
			}
		}
		response.getWriter().write(flag);
		return null;
	}
	
	/**
	 * 修改密码后直接登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login")
	public String directLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return iLoginService.jugeUserRole(request,response);
	}
}