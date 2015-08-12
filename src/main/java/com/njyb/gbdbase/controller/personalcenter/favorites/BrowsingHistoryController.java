package com.njyb.gbdbase.controller.personalcenter.favorites;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.personalcenter.favorites.HistoryAndFavoritesModel;
import com.njyb.gbdbase.service.personalcenter.favorites.IBrowsingHistoryService;

/**
 * 浏览记录 控制层
 * @author WangBo 
 * 2015-3-16
 */
@Controller
@RequestMapping(value="/browsingHistory")
public class BrowsingHistoryController extends PublicCommonController{
	
	/**
	 * 浏览记录 Service
	 */
	@Autowired
	private IBrowsingHistoryService browsingHistoryService;
	
	
	/**
	 * 点击查看国家的海关数据
	 * @param request
	 * @param response
	 */
	public void queryFavoritesDetailsByQueryValue(HttpServletRequest request,
			HttpServletResponse response){
		// TODO 为实现
//		String queryValue = request.getParameter("queryValue");
	}
	
	/**
	 * 插入浏览记录
	 * @param request
	 * @param response
	 * @param historyRecModel : 收藏夹Bean
	 * @throws IOException 
	 */
	@RequestMapping(value="/addbrowsingHistory")
	public Object insertFavorItesHistory(HttpServletRequest request,
			HttpServletResponse response,HistoryAndFavoritesModel favoritesModel) throws IOException{
		String country = String.valueOf(request.getSession().getAttribute("country"));
		UserModel userModel = null == request.getSession().getAttribute("user") ? new UserModel() : (UserModel)request.getSession().getAttribute("user");
		// 国家
		favoritesModel.setCountry(country);
		// 用户Id
		favoritesModel.setUserId(userModel.getUserId());
		int resultNum = browsingHistoryService.addFavorites(favoritesModel);
		JSONObject json = new JSONObject();
		json.put("resultNum", resultNum);
		response.getWriter().println(json.toString());
		return null;
	}
	
}