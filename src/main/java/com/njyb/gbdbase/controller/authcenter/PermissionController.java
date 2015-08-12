package com.njyb.gbdbase.controller.authcenter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.auth.service.impl.cmp.IOrderCountCmp;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.dao.admincenter.IAuthorityDao;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.admincenter.UserCountModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.authcenter.AuthCenterModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.service.authcenter.IPermissionService;


/**
 * 验证中心
 * 登录模块涉及到的一些方法
 * @author honghao
 * 2015-03-27
 */

@Controller
@RequestMapping("/permission")
public class PermissionController  extends PublicCommonController  {
	
	@Autowired
	private IPermissionService perService;
	@Resource
	private  AuthCenterModel authCenterModel;
	@Resource
	private AuthorityFieldModel authorityFieldModel;
	@Autowired
	private IOrderCountCmp orderCountCmp;
	
	//添加日志
	public static final Logger log=Logger.getLogger(PermissionController.class);
	/**
	 *查询用户是否具有该查询条件的权限
	 * 返回：
	 * 1、当前用户是按次用户，可以查询
	 * 2、当前用户是正式用户，满足查询权限
	 * 3、当前用户是正式用户，但是不具备查询的权限
	 * 4、未获取该用户
	 * @param request
	 * @param response
	 * @param conditionModel 条件查询模型
	 */
	@RequestMapping(value="/checkHaveSearchRight")
	  public void checkHaveSearchRight(HttpServletRequest request,HttpServletResponse response,ConditionRightModel conditionModel ){
		try{
			log.info("*****验证中心（查询用户是否具有该查询条件的权限）******");
			//获取当前用户  
			UserModel user=(UserModel)request.getSession().getAttribute("user");
		    //验证用户是否具有查询条件的权限
	        String result=perService.checkHaveSearchRight(user,conditionModel );
	        JSONObject json=new JSONObject();
	        json.put("result", result);
	        response.getWriter().write(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		log.info("*****验证中心（查询用户是否具有该查询条件的权限）******\n");
	  }
	
	/**
	 * 判断当前用户是否具有查看详情的权限
	 * 1.按次用户，次数未超，可以用
	 * 2.正式用户，不需要验证，可以用
	 * 4.没有该用户
	 * 5.按次用户，次数超了，不可以用
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/checkHaveDetailRight")
	public void checkHaveDetailRight(HttpServletRequest request,HttpServletResponse response){
		try{
			log.info("*****验证中心（判断当前用户是否具有查看明细的权限）*****");
			//获取当前用户  
			UserModel user=(UserModel)request.getSession().getAttribute("user");
		    //验证用户是否具有查看明细的权限
	        String result=perService.checkHaveDetailRight(user );
	        JSONObject json=new JSONObject();
	        json.put("result", result);
	        response.getWriter().write(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		log.info("*****验证中心（判断当前用户是否具有查看明细的权限）*****\n");
	}
	
	/**
	 * 获取当前登录用户所有权限信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/queryUserRightMessage")
	public void queryUserRightMessage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		//切换数据库
		List<ConditionRightModel> userRightList = perService.getConditionRight(userModel.getUserId());
		for (ConditionRightModel rightModel : userRightList) {			// 对中国从新赋值时间
			if (rightModel.getStartTime().split("-").length ==2 || rightModel.getEndTime().split("-").length == 2) {
				rightModel.setStartTime(rightModel.getStartTime() + "-01");
				rightModel.setEndTime(rightModel.getEndTime() + "-30");
			} else {
				// 国家+进出口类型
				rightModel.setByCountry(rightModel.getByCountry() + rightModel.getiExportType());
			}
		}
		JSONObject object = new JSONObject();
		object.put("key", userRightList);
		object.put("user", userModel);
		object.put("nowDate", DataUtil.parseDate(new Date(), 3));
		response.getWriter().println(object.toString());
	}
	
	/**
	 * 个人中心模块查询用户权限
	 * @param request : 请求
	 * @param response : 响应
	 * @param userModel :用户模型
	 * @throws Exception 
	 */
	@RequestMapping(value="/QueryUserRight")
	public String queryUserRight(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		/**
		  * 权限控制
		  */
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = response.getWriter();
//		if(MainAuthoriy.isVisitModule(request, response, ((UserModel)request.getSession().getAttribute("user")).getUserDesc(), authorityFieldModel.getAuthorityFieldMap(), jsonObject)){
			UserModel userModel = (UserModel) request.getSession().getAttribute("user");
			List<ConditionRightModel> userRightList = perService.queryUserRight(userModel);
			if(userRightList != null)
			{
				jsonObject.put("total",userRightList.size());
			}
			jsonObject.put("rows",userRightList);
//		}else{
//			jsonObject.put("total",0);
//			jsonObject.put("rows","");
//		}
		out.write(jsonObject.toString());
		return null;
	}
	
	/**
	 * 按次用户查看权限
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryUserCountRight")
	public String queryUserCountRight(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = response.getWriter();
		//获取用户
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userModel.getUserId());
		paramMap.put("type", userModel.getUserDesc());
		UserCountModel userCountModel = orderCountCmp.queryUserCountModel(paramMap);
		List<UserCountModel> userRightList = new LinkedList<UserCountModel>();
		if(userCountModel != null)
		{
			userRightList.add(userCountModel);
			jsonObject.put("total",userRightList.size());
		}
		jsonObject.put("rows",userRightList);
		out.write(jsonObject.toString());
		return null;
	}
	
	/**
	 * 判断当前用户下是否有使用该账号的权限
	 * @param request : 请求
	 * @param response : 响应
	 * @param module 模块名
	 * @throws Exception 
	 */
	@RequestMapping(value="/queryRightByUser")
	public String queryRightByUser(HttpServletRequest request,HttpServletResponse response,String module) throws Exception
	{
		// 获取map对象
		Map map = authCenterModel.getAuthCenterMap();
		//判断是否有权限
		String status = perService.getRightByUser(request,map,module);
		return status;
	}
		
}
