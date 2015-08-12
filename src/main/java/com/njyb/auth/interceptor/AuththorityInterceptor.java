package com.njyb.auth.interceptor;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.njyb.gbdbase.model.admincenter.UserModel;
/**
 * 用户权限拦截器验证
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public class AuththorityInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		HandlerMethod hm = (HandlerMethod)handler;
//		//开发者需要自己完善
//		UserModel user=null;
//		//User user = (User)session.getAttribute("loginUser");
//		if(user==null) {
//			//重定向到登录页面
//			/*response.sendRedirect(request.getContextPath()+"/login");*/
//		} else {
//				//后期具体能访问的权限:实际就是controller里面的具体方法
//				String userRight="";//保存在用户登录的controller 存放的用户的权限
//				Set<String> actions = (Set<String>)session.getAttribute(userRight);
//				//检查当前用户登录时候需要进行权限验证的方法 
//				String aname = hm.getBean().getClass().getName()+"."+hm.getMethod().getName();
//				//需要进行权限验证的方法是否包含在该用户实际能访问的权限访问之内
//				if(!actions.contains(aname)) throw new RuntimeException("对不起您,没有相应的权限访问该功能!");
//		}
		
		return super.preHandle(request, response, handler);
	}
}
