package com.njyb.gbdbase.controller.admincenter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.admincenter.UserSystemLogModel;
import com.njyb.gbdbase.model.login.UserLoginlogModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.ISearchLogService;
import com.njyb.gbdbase.service.personalcenter.userlog.IUserLogService;

/**
 * 后台管理（用户日志管理）
 * @author chenhu
 * 2015年4月3日
 */
@Controller
@RequestMapping(value="/userLogInfo")
public class UserSystemLogController extends PublicCommonController{
	@Autowired
	private ISearchLogService searchService;
	//用户日志Service
	@Autowired
	private IUserLogService userLogService;
	
	private static final Logger log=Logger.getLogger(UserSystemLogController.class);
	/**
	 * 用户查询日志列表
	 * @param request
	 * @param response
	 * @param query
	 */
	@RequestMapping(value="queryUserSearchLog")
     public void queryUserSearchLog(HttpServletRequest request,HttpServletResponse response,QueryModel query)
     {
		try{
		    //获取分页的页数和页面大小
			PageBeanUtil pageUtil=this.getPageBean(request);
			query.setCurPage(pageUtil.getPageIndex());
			query.setPageSize(pageUtil.getPageSize());
			List<UserSystemLogModel> logList=searchService.querySystemLogListByUserIdForPaging(query);
		    int count=searchService.querySysLogCountByUserId(query);
			JSONObject json= this.getJsonObject(count, logList);
			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
     }
	/**
	 * 根据用户名查询登录日志
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryUserLogById")
	public void queryUserLogByUserName(HttpServletRequest request,HttpServletResponse response,UserModel user){
		
		List<UserLoginlogModel> userLogList = userLogService.queryUserLogModelByUserModel(user);
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
}
