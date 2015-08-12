package com.njyb.auth.util;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
/**
 * 获取当前登录用的实际权限
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public class GetUserActualAuthUtil {
	/*
	 * 根据当前用户登录的实际角色分配实际权限
	 */
	public static Set<String> getAllActions(String roleName,HttpSession session) {
		Set<String> actions = new HashSet<String>();
		//获取注解的所有方法
		Map<String, Set<String>> allAuths = (Map<String, Set<String>>) session
				.getServletContext().getAttribute("rights");
		//获取当前登录用户所能访问的实际权限
		if (roleName.equals("user_count")) {
			actions.addAll(allAuths.get("visit_count"));			
		}
		else if (roleName.equals("user_vip")) {
			actions.addAll(allAuths.get("visit_vip"));
		}
		return actions;
	} 
}
