package com.njyb.gbdbase.controller.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbas.util.ChangeOperSystemPath;
import com.njyb.gbdbas.util.CheckedCodeUtil;
import com.njyb.gbdbas.util.GetDoMainNameUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbas.util.UserCookieUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.personalcenter.password.EmailModel;
import com.njyb.gbdbase.service.login.ILoginService;

/**
 * 登录模块涉及到的一些方法
 * @author honghao
 * 2015-03-04
 */
@Controller
public class LoginController {
	
	@Autowired
	private ILoginService iLoginService;
	
	// 验证码的工具类
	private CheckedCodeUtil checkedCodeUtil = new CheckedCodeUtil();
	
	//log记录日志
	private static final Logger log = Logger.getLogger(LoginController.class);
	
	/**
	 * 判断页面跳转，如果用户为管理员账户，跳转到后台管理页面
	 * 如果用户为普通用户或者试用用户，跳转到国家查询页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="pageJump")
	public String pageJump(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return iLoginService.jugeUserRole(request,response);
	}

	/**
	 * ajax返回一张验证码的图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getCheckCode")
	public Object getCheckCode(HttpServletRequest request,HttpServletResponse response) {
		try 
		{
			int width = 120;
			int height = 30;
			int length = 4;
			int flag = 0;
			String fontName = "隶体";
			int fontStyle = 5;
			int fontSize = 20;
			int rotate = 0;
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			// 设置不缓存图片
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			// 禁止服务器端缓存
			response.setDateHeader("Expires", 0);
			// 指定生成的相应图片
			// 默认是text/html(输出文档的MIMI类型)
			response.setContentType("image/jpeg"); 
			Random random = new Random();
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_BGR);
			Graphics2D g = image.createGraphics();
			g.setColor(checkedCodeUtil.getRandomColor(200, 250));
			g.fillRect(0, 0, width, height);
			g.setColor(Color.yellow);
			for (int i = 0; i < 10; i++) 
			{
				int k = random.nextInt(width - 1);
				int j = random.nextInt(height - 1);
				g.drawLine(k, j, k + 1, j + 1);
				g.drawLine(k, j, k + 1, j);
				g.drawLine(k, j, k, j);
			}
			g.setColor(Color.red);
			for (int i = 0; i < 10; i++) 
			{
				int k = random.nextInt(width - 1);
				int j = random.nextInt(height - 1);
				g.drawLine(k, j, k + 1, j + 1);
				g.drawLine(k, j, k + 1, j);
				g.drawLine(k, j, k, j);
			}
			g.setColor(Color.green);
			for (int i = 0; i < 10; i++) 
			{
				int k = random.nextInt(width - 1);
				int j = random.nextInt(height - 1);
				g.drawLine(k, j, k + 1, j + 1);
				g.drawLine(k, j, k + 1, j);
				g.drawLine(k, j, k, j);
			}
			g.setColor(checkedCodeUtil.getRandomColor(10, 200));
			// 定义字体样式
			Font myFont = new Font(fontName, fontStyle, fontSize);
			// 设置字体
			g.setFont(myFont);
			String sessionstr = checkedCodeUtil.drawRandomString(length, g,flag);
			session.setAttribute("randCode", sessionstr);
			g.dispose();
			ImageIO.write(image, "JPEG", response.getOutputStream());
		}catch (Exception e) 
		{
			
		}
		return null;
	}
	
	/**
	 * 用户登录的控制器
	 * @param request
	 * @param response
	 * @param regCode 用户验证码是否输入正确
	 * @param rPassword 判断用户是否记住密码
	 * @param userModel 用户的实体类
	 * @return string 返回的状态码
	 * @throws Exception 
	 */
	@RequestMapping(value = "/userLogin")
	public String userLoginInfo(HttpServletRequest request,HttpServletResponse response, String regCode, String rPassword,UserModel userModel) throws Exception {
		new LoadPropertiesUtil().init(request,IConstantUtil.FILTERFIELD);
		//读取log4j配置文件
		Log4jConfigurer.initLogging(ChangeOperSystemPath.getLog4jPath());
		//切换数据库
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		//获取免费试用用户名
		String loginName =LoadPropertiesUtil.getValue("testUser").toString();
		//获取免费试用用户密码
		String password =LoadPropertiesUtil.getValue("testPwd").toString();
		
		//免费试用用户登录
		if(!StringUtils.isEmpty(userModel.getLoginName()))
		{
			if(loginName.equals(userModel.getLoginName()))
			{
				userModel.setLoginPassword(password);
			}
		}
		//获取当前的域名
		String doMainName = GetDoMainNameUtil.getDoMain(request);
		String s = iLoginService.checkedUserLogin(doMainName,userModel,regCode,rPassword,request,response);
		log.info("进入方法/userLogin，当前的登录状态码为:"+s);
//		response.setContentType("text/plain;charset=utf-8");
//		response.getWriter().write(s);
		String callback= request.getParameter("callback");
		if (null!=callback) {
			response.setContentType("text/javascript; charset=UTF-8");
			 response.getWriter().print(String.format(("%s('"+s+"')"), callback));
		}else{
			response.setContentType("text/plain;charset=utf-8");
			response.getWriter().write(s);
		}
		return null;
	}
	/**
	 * 注册
	 * 1.成功
	 * 2.失败!验证码错误
	 * 3.失败!创建失败
	 * @param request
	 * @param response
	 * @param regCode
	 * @param userModel
	 * @return
	 */
	@RequestMapping(value = "/userRegest")
	public void userRegest(HttpServletRequest request,HttpServletResponse response, String checkCode,UserModel user){
		response.setContentType("text/plain;charset=utf-8");
		log.info("enter userRegest");
		  String result="1";
		  result= iLoginService.regestUser(user, checkCode, request, response);
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
             log.debug(e.toString());
			e.printStackTrace();
		}
	}
	/**
	 * Email激活用户并跳转到主页面
	 * @param request
	 * @param response
	 * @param emailModel
	 */
	@RequestMapping(value="/emailToIndex")
	public String emailToIndex(HttpServletRequest request,HttpServletResponse response,String sId,String loginName){
		  //初始化配置文件
		  new LoadPropertiesUtil().init(request,IConstantUtil.FILTERFIELD);
		  return  iLoginService.activeUser(sId,loginName,request,response);
	}
	/**
	 * 重新发送emai邮件
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/reSendEmail")
	public void reSendEmail(HttpServletRequest request,HttpServletResponse response){
		//再次发送邮件
		 boolean  flag =false;
		//获取缓存中的用户模型
		UserModel userMdoel=(UserModel)request.getSession().getAttribute("user");
		//发送邮件
		flag= iLoginService.sendActiveEmail( userMdoel, request);
		try {
		     JSONObject json=new JSONObject();
		     json.put("flag", flag);
		     json.put("email", userMdoel.getEmail());
			response.getWriter().write(json.toString());
		} catch (IOException e) {
           log.debug(e.toString());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * XL用户注销的功能
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/userOff")
	public String userlogOff(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//移除session内的用户
		request.getSession().removeAttribute("user");
		UserCookieUtil.clearCookie(response);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		//跳转登录页面
		response.getWriter().print("<script>alert('Welcome back!');location.href='"+request.getContextPath()+"/view/newhomepage/login.jsp';</script>");
		return null;
	}
}
