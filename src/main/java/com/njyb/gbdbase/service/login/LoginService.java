package com.njyb.gbdbase.service.login;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.AddressUtils;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.GetDoMainNameUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbas.util.MD5Util;
import com.njyb.gbdbas.util.ResolveXMLUtil;
import com.njyb.gbdbas.util.UserCookieUtil;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.njyb.gbdbase.dao.login.IUserLoginDao;
import com.njyb.gbdbase.dao.personalcenter.password.IUserPasswordDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.login.UserLoginlogModel;
import com.njyb.gbdbase.model.personalcenter.email.MailModel;
import com.njyb.gbdbase.model.personalcenter.password.EmailModel;
import com.njyb.gbdbase.model.personalcenter.password.PasswordCommon;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.personalcenter.email.IEmailService;

/**
 * 用户登录模块的业务实现类
 * @author honghao
 * 2015-03-06
 */

@Service
public class LoginService implements ILoginService {
	@Autowired
	private IUserDao iLoginDao;
	@Autowired
	private IUserLoginDao iUserLoginDao;
	@Autowired
	private IEmailService iEamilService;
	@Autowired
	private IUserPasswordDao iUserPasswordDao;
	
	private static final Logger log=Logger.getLogger(LoginService.class);
	/**
	 * 判断用户的角色,如果用户为管理员账户，跳转到后台管理页面
	 * 如果用户为普通用户或者试用用户，跳转到国家查询页面
	 * @throws IOException 
	 * 
	 */
	public String jugeUserRole(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		//如果是管理员账号则跳转到后台管理页面
		if(IConstantUtil.ADMIN_USER.equals(user.getUserDesc()))
		{
			//解析后台页面
			ResolveXMLUtil xmlUtil=new ResolveXMLUtil("config\\menu\\menu.xml");
			//根据用户权限获取后台显示页面
			String menusStr=xmlUtil.getJsonString("admin");
			request.getSession().setAttribute("menusStr", menusStr);
			//跳转到后台管理页面
			response.getWriter().println("<script type='text/javascript'>location.href='/gbdbas/view/admincenter/menu.jsp'</script>");
		}
		response.getWriter().println("<script type='text/javascript'>location.href='/gbdbas/view/datasearch/datasearch.jsp'</script>");
		return null;
	}
	
	/**
	 * 改变用户的成功登录状态
	 * @param user 用户实体bean
	 * @param request
	 */
	public void changeUser(UserModel user,HttpServletRequest request) throws Exception
	{
		//将当前登录成功的用户存放在session中
		request.getSession().setAttribute("user", user);
		
		UserModel uModel = new UserModel();
		uModel.setSql("UPDATE sys_user SET endlogintime = '"+this.getDateStr()+"' WHERE userid = "+user.getUserId());
		iLoginDao.updateUserModel(uModel);
		
		//插入一条用户登录日志
		UserLoginlogModel logModel = new UserLoginlogModel();
		logModel.setLoginName(user.getLoginName());
		logModel.setLoginTime(this.getDateStr());
		logModel.setIpAddress(getIpAddress(request));
		logModel.setUserId(user.getUserId());
		//IP归属地
		if (null != logModel.getIpAddress() && !StringUtils.isEmpty(logModel.getIpAddress())) {
			String ipAddress = AddressUtils.getAddress(logModel.getIpAddress());	//获取IP归属地
			logModel.setIpAttribution(ipAddress);
		}
		iUserLoginDao.insertLoginLog(logModel);
	}
	
	/**
	 * 获取客户单用户访问的真实ip地址
	 * @param request
	 * @retrun String 用户实际登录的ip地址
	 * @throws Exception
	 */
	public String getIpAddress(HttpServletRequest request) throws Exception
	{
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
	    { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
	    { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
	    { 
	    	//获取真实用户访问的ip地址
	    	ip = request.getRemoteAddr(); 
	    } 
	    return ip;
	}
	
	/**
	 * 登录模块的总方法
	 * 其他表示用户登录被锁定
	 * LOGIN_SUCCESS:表示用户成功登录
	 * PASSWD_INCORRENT:表示密码不正确
	 * USER_NOTEXIST:表示用户不存在
	 * VERIFICATION_INCORRENT:表示用户验证码不正确
	 * ELSEWHERE_LOGIN_TIMES:同一个账号同时登陆的地方不超过3个
	 * USER_LOCKED:该用户暂时被锁定
	 * INACTIVE:表示未激活
	 * USER_DISABLE:该用户被禁用
	 * @param regCode 校验码
	 * @param rpassword 是否记住密码
	 * @return String 返回的各种校验码
	 */
	public String checkedUserLogin(String doMainName,UserModel userModel,String regCode,String rPassword,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取当前用户选择的语言
		String language = (String) request.getSession().getAttribute("language");
		//趣易未加国际化，默认语言为中文
		if(language==null)
		{
			language="message_zh_CN";
			request.getSession().setAttribute("language", language);
		}
		
		//获取测试用户名
		new LoadPropertiesUtil().init(request,IConstantUtil.FILTERFIELD);
		String loginName =LoadPropertiesUtil.getValue("testUser").toString();
		//如果用户不是免费试用连接进来的话需要校验验证码
		if(!loginName.equals(userModel.getLoginName()))
		{
			String regCodeNew = (String) request.getSession().getAttribute("randCode");
			//判断是否激活
			Integer count=this.userIsactivated(userModel.getLoginName());
			if(count==0)
			{
				return IConstantUtil.INACTIVE;
			}
			//判断验证码输入是否不对
			if(regCodeNew == null || !regCodeNew.equals(regCode))
			{
				return IConstantUtil.VERIFICATION_INCORRENT;
			}
		}
		
        //拼装条件sql语句
		QueryModel query=new QueryModel();
		query.setWhereSql(" loginName='"+userModel.getLoginName()+"'\n" );
		//根据用户名向数据库查记录
		UserModel user = iLoginDao.queryUserBySql(query);
		if(user != null)
		{
			//账号已经被禁用
			if(user.getIsDisable())
			{
				return IConstantUtil.USER_DISABLE;
			}
			//同一个账号同时登陆的地方不超过3个
			if(!limitLoginTimes(user,request))
			{
				return IConstantUtil.ELSEWHERE_LOGIN_TIMES;
			}
			if(user.getLoginPassword().equals(MD5Util.getInstance().generateEncrypte(userModel.getLoginPassword())))
			{
				changeUser(user,request);
				request.getSession().setAttribute("doMainName", doMainName);
				//如果用户点击自动登录 则将用户名密码保存到cookie里
				 if("y".equals(rPassword)){
					 UserCookieUtil.createInstance().saveCookie(user, response);
				 }else
				 {
					 UserCookieUtil.clearCookie(response);
				 }
				//表示用户成功登录
				return IConstantUtil.LOGIN_SUCCESS;
			}else
			{
				userLoginerror(user);
				//账号是否已被锁定
				if(user.getIsLocked())
				{
					return IConstantUtil.USER_LOCKED;
				}
				//表示密码不正确
				return IConstantUtil.PASSWD_INCORRENT;
			}
		}else
		{
			//表示用户不存在
			return IConstantUtil.USER_NOTEXIST;
		}	
	}
	
	/**
	 * 根据用户名查询数据表中的记录
	 * @param loginName 登录名
	 * @auther honghao
	 */
	public UserModel queryUserByName(String loginName) 
	{
		UserModel user=new UserModel();
		if((loginName!=null)&&!("".equals(loginName)))
		{
			   //拼装条件sql语句
			QueryModel query=new QueryModel();
			query.setWhereSql(" loginName="+loginName+"\n" );
			 user = iLoginDao.queryUserBySql(query);
		}
		return user;
	}
	
	/**
	 *用户登录错误的情况下的改变用户状态
	 *@param loginName 登录名
	 *@return Integer
	 */
	public void userLoginerror(UserModel user)
	{
		//密码输入错误次数超过5次 15分钟之后才可以重新使用登录功能
		UserModel uModel = new UserModel();
		if(user.getLoginCount()+1< IConstantUtil.PASSWD_ENTER_TIMES)
		{
			uModel.setSql("UPDATE sys_user SET logincount = logincount + 1, endlogintime = '"+this.getDateStr()+"' WHERE userid = "+user.getUserId());
		}else
		{
			if(getMinutes(user)<IConstantUtil.LOCKED_TIME)
			{
				uModel.setSql("UPDATE sys_user SET isLocked=1 ,logincount=0 WHERE userid = "+user.getUserId());
			}else
			{
				uModel.setSql("UPDATE sys_user SET isLocked=0,logincount=1 WHERE userid = "+user.getUserId());
			}
		}
		this.iLoginDao.updateUserModel(uModel);
	}
	
	/**
	 *获取两段时间相差的分钟数 
	 *@param user
	 *@return int 
	 */
	
	public int getMinutes(UserModel user)
	{
		Calendar endLoginTime = DataUtil.parseDate(user.getEndLoginTime());
		Calendar now = Calendar.getInstance();
		long timeDiff = now.getTimeInMillis()-endLoginTime.getTimeInMillis();
		//当前时间减去用户最后登录的时间所得的分钟差
		int minutes = (int)timeDiff/1000/60;
		return minutes;
	}
	
	/**
	 *用户是否激活 
	 *@param loginName 登录名
	 *@return Integer
	 */
	@Override
	public Integer userIsactivated(String loginName){
		Integer count=-1;
	  if(loginName!=null){
		  UserModel user=new UserModel();
		  user.setLoginName(loginName);
		//拼装条件sql语句
		QueryModel query=new QueryModel();
		query.setWhereSql(" loginName='"+loginName +"' and isactivated = 1" );
        count=iLoginDao.queryUserCountBySql(query);
        if(count==null){
        	return 0;
        }
	  }
	  return count;
	}
	
	/**
	 * 同一个账号同时登陆的地方不超过3个
	 * @throws Exception 
	 */
	public boolean limitLoginTimes(UserModel user,HttpServletRequest request)throws Exception{
		Map map=new HashMap();
		map.put("loginName", user.getLoginName());
		//根据用户的登陆名称查询所有的登陆结果集
		List<UserLoginlogModel> list =iUserLoginDao.getLogByLoginName(map);

		//将最近一个小时内该用户的登陆地址输出
		Set<String> ipAddrSet = new HashSet<String>();
		for(UserLoginlogModel model : list)
		{
			Calendar endLoginTime = DataUtil.parseDate(model.getLoginTime());
			Calendar now = Calendar.getInstance();
			long timeDiff = now.getTimeInMillis()-endLoginTime.getTimeInMillis();
			//当前时间减去用户最后登录的时间所得的分钟差
			int minutes = (int)timeDiff/1000/60;
			if(minutes < IConstantUtil.LOGIN_TIME_INTERVAL)
			{
				ipAddrSet.add(model.getIpAddress());
			}
		}
		//当一个小时候用户登入的ip地址数目超过3次则不给用户登录
		if(ipAddrSet.size()>= IConstantUtil.LOGIN_TIMES)
		{
			return false;
		}
		return true;
	}
	

	/**
	 * 判断用户是否选中了“记住密码”，如果选中则放到Cookie总
	 * @param rPassword
	 * @param userModel
	 * @param response
	 * @throws Exception 
	 */
	public void rePassword(String rPassword,UserModel userModel,HttpServletResponse response) throws Exception{
		if ("forever".equals(rPassword)) {
			// 创建用户名Cookie
			Cookie nameCookie = new Cookie("userName",URLEncoder.encode(userModel.getLoginName(),"UTF-8"));
			// 创建密码Cookie
			Cookie passwordCookie = new Cookie("userPassword", userModel.getLoginPassword());
			// 设置cookie的存活时间为30分钟
			nameCookie.setMaxAge(7 * 24 * 60 * 60);
			passwordCookie.setMaxAge(7 * 24 * 60 * 60);
			nameCookie.setPath("/");
			passwordCookie.setPath("/");
			response.addCookie(nameCookie);
			response.addCookie(passwordCookie);
		}else{
			Cookie nameCookie = new Cookie("userName", null);
			// 创建密码Cookie
			Cookie passwordCookie = 
				new Cookie("userPassword", null);
			nameCookie.setPath("/");
			passwordCookie.setPath("/");
			nameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
			response.addCookie(nameCookie);
			response.addCookie(passwordCookie);
		}
	}
	
	/**
	 * 获取当前的时间 
	 * @return String
	 */
	public String getDateStr(){
		Date date = new Date();
		String timer =  date.getYear()+1900+"-"+(date.getMonth()+1)+"-"+date.getDate()
			+"  "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		return timer;
	}
	/**
	 * 获取一年后的时间 
	 * @return String
	 */
	public String getNextDateStr(){
		Date date = new Date();
		String timer =  date.getYear()+1901+"-"+(date.getMonth()+1)+"-"+date.getDate()
			+"  "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		return timer;
	}


	@Override
	public String regestUser(UserModel user, String code, HttpServletRequest request, HttpServletResponse response) {
		String flag="3";
		try{
				log.info("注册新用户");
				String chechCode=(String)request.getSession().getAttribute("randCode");
				//当验证码为空或者验证码不正确时直接返回2
					//if((chechCode==null)||("".equals(code))||!(chechCode.equals(code))){
						//return "2";
					//}
				    user.setLoginName(user.getEmail());
					//添加用户
					if( user!=null && (user.getEmail()!=null) && (user.getLoginPassword()!=null )){
						//添加用户
						user.setUserDesc(IConstantUtil.TRIAL_USER);
						String passwd=MD5Util.getInstance().generateEncrypte(user.getLoginPassword());
						//密码加密
						user.setLoginPassword(passwd);
						//设置注册时间
						user.setRegistertime(this.getDateStr());
						//设置起始时间
						user.setBeginTime(this.getDateStr());
						//设置结束时间  默认一年
					    user.setEndTime(this.getNextDateStr());
					    
				
					    
					    //Find whether the current user has been register
					    QueryModel query=new QueryModel();
						query.setWhereSql(" loginName='"+user.getEmail()+"'");
						UserModel userModel=iLoginDao.queryUserBySql(query);
					    int userNum = iLoginDao.checkLoginName(userModel);
					    if(userNum!=0){
					    	
					    	if(!userModel.getIsActivated()){
					    		return "4";
					    	}
					    	return "3";
					    }
					    
					    
					    // if this user has been registered and has been activated , then add this user to database
					    else {
					    	iLoginDao.addNewUser(user);
					    }
					  
						
						//发送邮件
						if(this.sendActiveEmail(user,request)){
							//如果发送成功，则返回1
							return "1";
						}
						
					}
			}catch(Exception e)
			{
				e.printStackTrace();
				Log.debug(e.toString());
			}
		
		return flag;
	}
	@Override
	public boolean sendActiveEmail(UserModel userMdoel, HttpServletRequest request) {
		      // 判断用户是否注册成功
				//if (userMdoel.getIsActivated()) {
					//return false;
				//}
				//获取上次的email
				EmailModel emailModel = new EmailModel();
				emailModel.setEmailCount(emailModel.getEmailCount() + 1);
				emailModel.setSendTime(new Date());
				
				String key = MD5Util.encodeByMD5(emailModel.getSendTime() + UUID.randomUUID().toString() );
			
				emailModel.setsId(key);
				iUserPasswordDao.addEmailModelByModel(emailModel);		//添加
				//拼接可变部分
				
				StringBuilder sb = new StringBuilder("http://" + request.getServerName() + ":")
				.append(request.getServerPort())
				.append(request.getContextPath())
				.append("/emailToIndex")
				.append("?sId=" + key + "&loginName=" + userMdoel.getEmail());
				//.append("?sId=" + key + "&loginName=" + userMdoel.getLoginName());
				//获取主域名
				String contextUrl = GetDoMainNameUtil.getContextDoMain(request);
				//填充模板里的变量
				Map<String,Object> emailParamMap = Maps.newHashMap();
				emailParamMap.put("updateUrl", contextUrl);
				emailParamMap.put("username", userMdoel.getEmail());
				//emailParamMap.put("username", userMdoel.getLoginName());
				emailParamMap.put("url", sb.toString());
				//邮件model
				MailModel mailModel = new MailModel();
				//邮件主题
				mailModel.setSubject("激活用户");
				//邮件地址
				mailModel.setToAddreress(userMdoel.getEmail()); // TODO userMdoel.getEmail();
				
				//发件人
				mailModel.setFromAddress("noreply@inforvellor.com");
				
				//发送邮件
				if(!iEamilService.sendAttachMail(mailModel, emailParamMap, "activeUser.html")){
					  //如果发送失败，则删除数据库中的用户，并删除保存在数据库中的信息提示用户邮箱错误
					iLoginDao.deleteUserAllMesseByUserID(userMdoel.getUserId());
					iUserPasswordDao.deleteEmailBySid(key);
					return false;
				}
				return true;
	}

	@Override
	public String activeUser(String rid, String loginName,HttpServletRequest request,HttpServletResponse response) {
	       Map map=new HashMap();
	       try{
	    	   //根据email 的sid查询是否存在该用户
	    	   if(rid!=null&&(loginName!=null)){
	    		    //首先判断该用户是否存在，根据登录名查询用户
	    		   QueryModel query=new QueryModel();
	    		   query.setWhereSql(" loginName='"+loginName+"'");
	    		   UserModel user=iLoginDao.queryUserBySql(query);
	    		   if(user!=null){
	    			   //根据rid查询EmailModel
	    			   map.put("sId", rid);
	    			   EmailModel emailModel=(EmailModel)iUserPasswordDao.queryEmailByParam(map);
	    			   if(emailModel!=null){
	    				   //判断发送时间是否超时，如果超时则重新发送，
	    				   //如果没有超时，则进行下一步验证
	    				   if(checkIsOverTime(emailModel.getSendTime())){
	    					   //激活用户
	    					   user.setIsActivated(true);
	    					   query.setUpdateSql("ISACTIVATED=1,endlogintime = '"+this.getDateStr()+"'");
	    					   iLoginDao.updateUserBySql(query);
	    					   //跳转到主页面
	    					   request.getSession().setAttribute("user", user);
	    						response.getWriter().println("<script type='text/javascript'>location.href='/gbdbas/view/login/register/activeSuccess.jsp'</script>");
	    					//   this.jugeUserRole(request, response);
	    				   }else
	    				   {
	    					   //跳转到重新发页面
	    					   response.getWriter().println("<script type='text/javascript'>"
	    							   + "location.href='/gbdbas/view/login/register/reActive.jsp'</script>");
	    				   }
	    			   }
	    		   }else{
	    			   //如果当前没有发送该email说明非法注册 跳转到非法页面
	    			   response.getWriter().println("<script type='text/javascript'>"
	    					   + "location.href='/gbdbas/error/400.jsp'</script>");
	    		   }
	    		  }
	       }catch(Exception e){
	    	   log.debug(e.toString());
	    	   e.printStackTrace();
	       }
		  return null;
	} 
	/**
	 * 判断邮件发送是否超时
	 * @param starTime 邮件发送开始时间
	 * @return
	 */
	private boolean checkIsOverTime(Date starTime){
	      //那发送时间跟当前时间进行对比判断是否超过48小时 
		  //如果超过则返回false 超过则返回true
		boolean result=false;
		try
		{
			if(starTime!=null){
				long emailTime=starTime.getTime();
				long nowTime=new Date().getTime();
		        if((nowTime-emailTime)<48*60*60*1000){
		        	result=true;
		        }
			}
		}catch(Exception e){
			log.debug(e.toString());
			e.printStackTrace();
		}
		return result;
	}
}
