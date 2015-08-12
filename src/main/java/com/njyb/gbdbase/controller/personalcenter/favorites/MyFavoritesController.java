package com.njyb.gbdbase.controller.personalcenter.favorites;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.datasearch.common.MapModel;
import com.njyb.gbdbase.model.personalcenter.favorites.HistoryAndFavoritesModel;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;
import com.njyb.gbdbase.service.personalcenter.favorites.IMyFavoritesService;

/**
 * 我的收藏
 * @author WangBo 
 * @date 2015年4月1日 
 * @since MyFavoritesController.java
 */
@Controller
@RequestMapping(value = "/myFavorites")
public class MyFavoritesController extends PublicCommonController {

	// 我的收藏夹
	@Autowired
	private IMyFavoritesService myFavoritesService;
	
	// 公用搜索业务接口层
	@Autowired
	private IDataSearchService dataSearchService;

	/**
	 * 查询我的收藏数据
	 * 
	 * @param request
	 *            : 请求
	 * @param response
	 *            : 响应
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryMyFavorites")
	public void queryMyFavorites(HttpServletRequest request,
			HttpServletResponse response) throws IOException 
	{
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		//获取数据
		List<HistoryAndFavoritesModel> myFavoritesList = myFavoritesService.queryFavoritesResult(userModel.getUserId());
		int total = 0 == myFavoritesList.size() ? 0 :myFavoritesList.get(0).getTotal();
		JSONObject json = this.getJsonObject(total, myFavoritesList);
		response.getWriter().println(json.toString());
	}
	
	/**
	 * 根据查询参数查询 我的收藏记录信息
	 * @param request
	 * @param response
	 * @param obj
	 * @throws IOException 
	 */
	@RequestMapping(value="/queryMyFavorItesByParams")
	public void queryMyFavorItesByParams(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Map<String,Object> paramsMap = Maps.newHashMap();
		PageBeanUtil pageBean = this.getPageBeanToWorked(request);
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		paramsMap.put("hscode", request.getParameter("hscode"));
		paramsMap.put("country", InitCountryCENameUtil.queryCountryEnName(request.getParameter("country")));
		paramsMap.put("beginDate", request.getParameter("beginDate"));
		paramsMap.put("endDate", request.getParameter("endDate"));
		paramsMap.put("index", pageBean.getPageIndex());
		paramsMap.put("size", pageBean.getPageSize());
		paramsMap.put("userId", userModel.getUserId());
		List<HistoryAndFavoritesModel> resultList = myFavoritesService.queryFavoritesByParams(paramsMap);
		JSONObject object = this.getJsonObject(resultList.size() == 0 ? 0 : resultList.size(), resultList);
		response.getWriter().println(object.toString());
	}

	/**
	 * 保存我的收藏夹<br>
	 * @param request
	 * @param response
	 * @return 1 : 保存成功 2 : 保存失败
	 * @throws Exception
	 * @author honghao
	 */
	@RequestMapping(value = "/addMyFavorites")
	public void addMyFavorites(HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		//英文查询条件
		String enQueryParam =MapModel.getMap().get("enQueryParam").toString();
		//中文查询条件
		String zhQueryParam =MapModel.getMap().get("zhQueryParam").toString();
		//当前保存的时间
		String saveTime = DataUtil.parseDate(new Date(), 0);
		//获取当前用户
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		//获取当前的国家
		String country = (String) request.getSession().getAttribute("country");
		//向user_collection表中插入一条收藏记录
		HistoryAndFavoritesModel model = new HistoryAndFavoritesModel("",userModel.getUserId(),zhQueryParam,enQueryParam,country,userModel.getLoginName(),"","",saveTime);
		int result = myFavoritesService.addFavorites(model);
		response.getWriter().println(result);
	}
	
	/**
	 * 根据id删除表数据
	 * @param request
	 * @param response
	 * @param id
	 * @throws IOException 
	 */
	@RequestMapping(value="/delMyFavorites")
	public void delMyFavorites(HttpServletRequest request,HttpServletResponse response,String id) throws IOException{
		int result = myFavoritesService.delFavorites(id);
		JSONObject json = new JSONObject();
		json.put("result", result);
		response.getWriter().println(json.toString());
	}
}