package com.njyb.auth.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.njyb.auth.service.CommonAuthService;
import com.njyb.auth.service.impl.cmp.IOrderCountCmp;
import com.njyb.gbdbase.model.admincenter.UserCountModel;
import com.njyb.gbdbase.model.admincenter.UserModel;

/**
 * 按次用户拦截器
 * 
 * @author WangBo 2015年6月25日 UserCountInterceptor.java
 */
public class UserCountInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IOrderCountCmp orderCountCmp;

	/**
	 * 在请求action之前,调用<br>
	 * 返回状态码: 1. 用户次数不足 2. 用户时间过期, 3. 当前用户无权限访问
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		UserModel userModel = (UserModel) session.getAttribute("user");
		JSONObject json = getJSONRequest(request, userModel);		// 获取按次用户信息
		if (null == json) {
			json = new JSONObject();
			Map<String, Object> paramMap = ImmutableMap.of("userId",(Object) userModel.getUserId(), "type",CommonAuthService.COUNT_USER,"look_price",CommonAuthService.LOOK_PRICE);
			UserCountModel userCountModel = orderCountCmp.queryUserCountModel(paramMap);
			if (null != userCountModel) {
				if (userCountModel.getResidueNum() > 0) {
					if (!Strings.isNullOrEmpty(userModel.getUserDesc())) {
						if (userModel.getUserDesc().equals(CommonAuthService.COUNT_USER)) {
							return true;
						} else {
							json.put("userRight", 3);
						}
					}
				} else {
					json.put("userRight", 1);
				}
			} else {
				return true;
			}
		}
		response.getWriter().println(json.toString());
		return false;
	}
	
	/**
	 * 获取按次用户 购买的 详细信息
	 * @param request : 请求
	 * @param userModel : 用户Model
	 * @return JSONObject
	 */
	private JSONObject getJSONRequest(HttpServletRequest request,UserModel userModel) {
		String tempFlag = request.getParameter("flag");
		Map<String,Object> paramMap = Maps.newHashMap();
		JSONObject json = null;
		UserCountModel userCountModel = null;
		if (!Strings.isNullOrEmpty(tempFlag)) {
			paramMap.put("look_price", CommonAuthService.LOOK_PRICE);
			paramMap.put("type", userModel.getUserDesc());
			paramMap.put("userId", userModel.getUserId());
			userCountModel = orderCountCmp.queryUserCountModel(paramMap);
			json = new JSONObject();
			json.put("countModel", userCountModel);
			json.put("user", userModel);
		}
		return json;
	}

	/**
	 * 扣费  按次用户
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		UserModel userModel = (UserModel) session.getAttribute("user");
		// 如果是按次用户,则扣费,否则不扣费
		if (userModel.getUserDesc().equals(CommonAuthService.COUNT_USER)) {
			int id = Integer.valueOf(request.getParameter("id"));		// 库中的id
			String country = "";		//国家
			// myFavorites 不为空则为收藏夹功能
			if (!Strings.isNullOrEmpty(request.getParameter("myFavorites"))) {
				country = request.getParameter("country");
			} else {
				// 获取当前的国家名称
				country = (String) request.getSession().getAttribute("country");
			}
			if (userModel.getUserDesc().equals(CommonAuthService.COUNT_USER)) {				// 按次用户
				Map<String, Object> paramMap = Maps.newHashMap();
				paramMap.put("temp_userid", userModel.getUserId());
				paramMap.put("country_en", country);
				paramMap.put("countryid", id);
				paramMap.put("look_money", 10.00);
				orderCountCmp.userCountBuUserId(paramMap);
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}