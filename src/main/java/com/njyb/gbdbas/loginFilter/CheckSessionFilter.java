package com.njyb.gbdbas.loginFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.njyb.gbdbase.model.admincenter.UserModel;

/**
 * 检查session,是否有效,如果有效,则继续,无效跳转Login.jsp
 * 
 * @author WangBo 2015年5月20日 CheckSessionInterceptor.java
 */
@Repository
public class CheckSessionFilter implements Filter {

	private static final Logger log = Logger.getLogger(CheckSessionFilter.class);  
	
	@Override()
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * 验证session是否失效或者已登录
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			// 强转为 httpServletRequest 容器的线程请求
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			HttpSession session = servletRequest.getSession();
			// 获取用户请求的URI
			String path = servletRequest.getRequestURI();
			UserModel userModel = (UserModel) session.getAttribute("user");
			if (path.indexOf("/login.jsp") > -1) {		// 如果是登录页面,无需过滤
				chain.doFilter(servletRequest, servletResponse);
				return;
			} else if (path.indexOf("/register.jsp") > -1) {		// 如果是注册页面,无需过滤
				chain.doFilter(servletRequest, servletResponse);
				return;
			} else if(path.indexOf("/findpwd.jsp") > -1) { //如果是找回密码页面，无需过滤
				chain.doFilter(servletRequest, servletResponse);
				return;
			} else if(path.indexOf("/index.jsp")>-1){   //if it is index page, do not need filter. 
				chain.doFilter(servletRequest, servletResponse);
				return;      
			} else if(path.indexOf("/pricing.jsp")>-1){   //if it is pricing page, do not need filter. 
				chain.doFilter(servletRequest, servletResponse);
				return;            
			} else if(path.indexOf("/research.jsp")>-1){   //if it is research page, do not need filter. 
				chain.doFilter(servletRequest, servletResponse);
				return;      
			} else if(path.indexOf("/buyers.jsp")>-1){   //if it is buyers page, do not need filter. 
				chain.doFilter(servletRequest, servletResponse);
				return;      
			} else if(path.indexOf("/sellers.jsp")>-1){   //if it is sellers page, do not need filter. 
				chain.doFilter(servletRequest, servletResponse);
				return;      
			}else if(path.endsWith("gbdbas")||path.endsWith("gbdbas/")){
				chain.doFilter(servletRequest, servletResponse);
				return; 
			}
			// 如果用户为null,则跳转到login.jsp
			if (null == userModel) {
				// 跳转login.jsp
				servletResponse.sendRedirect(servletRequest.getContextPath() + "/view/newhomepage/login.jsp");
			} else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			log.info(e);
			log.debug(e);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}