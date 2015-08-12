package com.njyb.gbdbase.controller.personalcenter.userlog;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.login.UserLoginlogModel;
import com.njyb.gbdbase.service.admincenter.IUserManageService;
import com.njyb.gbdbase.service.personalcenter.userlog.IUserLogService;

/**
 * 用户日志Controller
 * @author WangBo
 * 2015年3月26日
 * UserLogController.java
 */
@Controller
@RequestMapping(value="/userLog")
public class UserLogController extends PublicCommonController{
	
	// 日志
	private static final Logger log = Logger
			.getLogger(UserLogController.class);
	
	//用户日志Service
	@Autowired
	private IUserLogService userLogService;
	
	//账户管理Service
	@Autowired
	private IUserManageService userManageService;
	
	/**
	 * 查询用户日志根据用户Id
	 * @param request
	 * @param response
	 * @param userModel
	 */
	@RequestMapping(value="/QueryUserLogByUserModel")
	public void queryUserLog(HttpServletRequest request,HttpServletResponse response){
		UserModel userModel = (UserModel)request.getSession().getAttribute("user");
		List<UserLoginlogModel> userLogList = userLogService.queryUserLogModelByUserModel(userModel);
		//实例化分页工具
		PageBeanUtil beanUtil = userLogService.getPageBeanUtil(request,"10");
		//获取分页集合
		List<UserLoginlogModel> pageList = new LinkedList<UserLoginlogModel>();
		int startIndex = userLogService.getStartIndex(beanUtil);
		int endIndex = userLogService.getEndIndex(beanUtil, userLogList, startIndex);
		for(int i=startIndex;i<endIndex;i++)
		{
			pageList.add(userLogList.get(i));
		}
		 //json展示给前台
		JSONObject jsonObject = new JSONObject();
		if(userLogList !=null)
		{
			jsonObject.put("total", userLogList.size());
		}
		jsonObject.put("rows", pageList);
		try {
			response.getWriter().println(jsonObject.toString());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * 根据用户名查询登录日志
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryUserLogByUserName")
	public void queryUserLogByUserName(HttpServletRequest request,HttpServletResponse response,UserLoginlogModel userLoginlogModel){
		//根据名称查询UserModel
		PageBeanUtil pageBeanUtil = this.getPageBeanToWorked(request);
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("userId", userLoginlogModel.getUserId());
		paramMap.put("beginDate", userLoginlogModel.getBeginDate());
		paramMap.put("endDate", userLoginlogModel.getEndDate());
		List<UserLoginlogModel> userLogList = userLogService.queryLogResultByLoginModel(paramMap);
		
		//实例化分页工具
		PageBeanUtil beanUtil = userLogService.getPageBeanUtil(request,"10");
		//获取分页集合
		List<UserLoginlogModel> pageList = new LinkedList<UserLoginlogModel>();
		int startIndex = userLogService.getStartIndex(beanUtil);
		int endIndex = userLogService.getEndIndex(beanUtil, userLogList, startIndex);
		for(int i=startIndex;i<endIndex;i++)
		{
			pageList.add(userLogList.get(i));
		}
		
		 //json展示给前台
		JSONObject jsonObject = new JSONObject();
		if(userLogList !=null)
		{
			jsonObject.put("total", userLogList.size());
		}
		jsonObject.put("rows", pageList);
		try {
			response.getWriter().println(jsonObject.toString());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * 查询当前账户和所有子账号
	 * @param request
	 * @param response
	 * @param userLoginlogModel
	 */
	@RequestMapping(value="/queryAllUserModel")
	public void queryAllUserModel(HttpServletRequest request,HttpServletResponse response,
			UserLoginlogModel userLoginlogModel){
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		List<UserModel> userList = userManageService.queryAllSons(userModel.getUserId());
		userList.add(0, userModel);
		JSONArray json = JSONArray.fromObject(userList);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}
}