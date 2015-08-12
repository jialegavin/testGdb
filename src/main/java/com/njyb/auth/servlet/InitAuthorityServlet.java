package com.njyb.auth.servlet;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.njyb.auth.util.InitAuthorityByRoleUtil;

/**
 * 初始化用户角色对应的权限
 * @author jiahp
 *
 */
public class InitAuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//web上下文对象
	private static WebApplicationContext webApplicationContext;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//初始化spring的工厂
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//初始化权限信息
		Map<String,Set<String>> authList =InitAuthorityByRoleUtil.initAuthority("com.njyb.gbdbase.controller.datasearch.search");
		this.getServletContext().setAttribute("rights", authList);
		System.out.println("------------------------正在初始化系统的所有权限:"+authList+"-----------------------------");
	}
	
	public static WebApplicationContext getWc() {
		return webApplicationContext;
	}

}
