package com.njyb.gbdbas.loginFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.njyb.gbdbas.util.UserCookieUtil;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.service.login.ILoginService;
import com.njyb.gbdbase.service.login.LoginService;

/**
 * 当用户点击自动登陆的时候（对于此次无效，当用户再次登陆的时候，取出cookie的值
 * 如果session不为null时将cookie的值和session的值进行比较如果两者相同的话直接
 * 跳转到主页，如果session为空则将cookie里的值显示到页面上。如果session有值，
 * cookie没值不做处理
 * ，用户点击登陆页面
 * @author chenhu
 * 2015年5月25日
 */
public class   AutoLoginFilter implements Filter{
	private ILoginService loginService=new LoginService() ;
	//cookie的名称
	private final static String cookieDomainName = "www.njyb.net"; 
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		String url = request.getRequestURI();
		//如果
		if(url.indexOf("/login.jsp") >-1){
			//首先判断session里是否有用户
	        HttpSession session=request.getSession(true);
	        UserModel user=(UserModel)session.getAttribute("user");
			//1、获取当前主站的cookie值
			Cookie[] cookies = request.getCookies();
			String cookieValue = null;
	        //下面是找到本项目的cookie
			if(cookies!=null){
					for (int i = 0; i < cookies.length; i++) {
						if(cookieDomainName.equals(cookies[i].getName())){
						cookieValue = cookies[i].getValue();
						break;
					}
				}
			}
			 //2、判断cookie是否为空，如果不是，则翻译cookie
			if(cookieValue!=null){
				// 先得到的CookieValue进行Base64解码
				String cookieValueAfterDecode = new String(Base64.decodeBase64(cookieValue),"utf-8");
				// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆,清理cookie跳转到登陆页面
				String cookieValues[] = cookieValueAfterDecode.split(":");
				if(cookieValues.length==4){
					long validTimeInCookie = new Long(cookieValues[1]);
					if (validTimeInCookie > System.currentTimeMillis()) {
						String userName=cookieValues[0];
						if(userName!=null&&user!=null){
							//如果cookie里的用户和session用户一至则直接判断跳转
							if(userName.equals(user.getLoginName())){
								loginService.jugeUserRole(request, response);
							}
						}else if(user==null){
							request.setAttribute("userName", userName);
						}
					}
			     }else{
			    	//如果所有判断都失败，则清理cookie并跳转到登陆页面
			 		UserCookieUtil.clearCookie(response);
			     }
			}
		}
		chain.doFilter(request, response);
 	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	
}
