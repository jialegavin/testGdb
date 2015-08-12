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

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbase.model.admincenter.UserModel;

/**
 * 根据当前项目状态 using:代表项目是发布状态,所以消费客户可以正常使用平台所有功能,但是开发团队本身不能访问项目
 * test:代表项目是测试状态,所以开发团队可以测试平台所有功能,但是消费用户只能访问友好提示页面
 * 
 * @author jiahp
 *
 */
public class ProjectStatuFilter implements Filter {
	public String statu=""; 
	String admin="";
	String userNamePre="";
	String conNamePre="";
	String username="";
	UserModel user=null;
	String tryuser="";
	public void destroy() {
		
	}
	
	public void initFilterParam(HttpServletRequest httpreq,HttpServletResponse response){
		new LoadPropertiesUtil().init(httpreq, IConstantUtil.FILTERFIELD);
		// 获取用户信息
		user = (UserModel) httpreq.getSession().getAttribute("user");
		// 获取项目发布状态
		statu = LoadPropertiesUtil.getValue("project_falg");
		admin = LoadPropertiesUtil.getValue("admin_pre");
		// 获取用户名前缀:dev_代表是开发或者测试人员账号,con_代表是正常消费用户
		userNamePre = LoadPropertiesUtil.getValue("user_pre");
		conNamePre = LoadPropertiesUtil.getValue("con_pre");
		//试用用户
		tryuser = LoadPropertiesUtil.getValue("tryuser");

		if(user!=null){
			// 获取登录姓名==包含前缀
			username = user.getLoginName();
		}

	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpreq = (HttpServletRequest) request;
		HttpServletResponse httpresp = (HttpServletResponse) response;
		initFilterParam(httpreq, httpresp);
		String path = httpreq.getServletPath();
		if (path.endsWith("/login.jsp")||path.endsWith("/user_frendly.jsp")
				||path.endsWith("/develop_frendly.jsp") || path.endsWith("/register.jsp")
				||path.endsWith("/findpwd.jsp")) {
			chain.doFilter(request, response);
			return;
		}

		else {
			// 加载配置文件
			if (user != null) {
				String uname = user.getUserDesc();
				// 管理员
				if (uname.equals(admin)) {
					chain.doFilter(request, response);
					return;
				}
				// 代表当前项目状态是出于发布状态==消费用户可以访问平台 ==试用用户+开发者用户无权访问平台
				if (statu.equals("using")) {

					//试用用户+消费用户 可访问
					if (user.getUserDesc().equals(tryuser) || user.getUserDesc().equals("正式用户")
							|| user.getUserDesc().equals("按次用户") || user.getUserDesc().equals("超级用户")) {	
						chain.doFilter(request, response);
						return;
						
					}
//					//开发用户
//					else if (user.getUserDesc().equals("超级用户")) {
//						httpresp.sendRedirect(httpreq.getContextPath()+ "/view/common/friendly/develop_frendly.jsp");
//						return;
//					} 
//					//匿名用户
//					else {
//						httpresp.sendRedirect(httpreq.getContextPath()+ "/view/common/friendly/user_frendly.jsp");
//						return;
//					}
				}

				// 代表当前项目状态是测试状态
				if (statu.equals("test")) {
					// 访问的用户是输入消费用户
					if (user.getUserDesc().equals("超级用户")) {
						chain.doFilter(request, response);
						return;
					} 
					else {
						httpresp.sendRedirect(httpreq.getContextPath()+ "/view/common/friendly/user_frendly.jsp");
						return;
					}
				}

			}
			if (user == null) {
				httpresp.sendRedirect(httpreq.getContextPath()
						+ "/view/login/infobase/login.jsp");
				return;
			}
		}

	}
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
