package com.njyb.gbdbase.controller.usermanagement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.njyb.gbdbase.model.datasearch.export.UserDownloadRightModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.IUserManageService;
import com.njyb.gbdbase.service.usermanagement.IUserSonRightService;
/**
 * 用户权限表
 * @author chenhu
 * 2015年4月13日
 */
@Controller
@RequestMapping("/userSonRight")
public class UserSonRightController extends PublicCommonController {
	/**
	 * 引人log    
	 */
	private static final Logger log=Logger.getLogger(UserSonRightController.class);  
	@Autowired
	private IUserSonRightService sonRight;
	@Autowired
	private IUserManageService userManage;
	/**
	 * 查询系统拥有的国家数据列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryAllCountries")
    public void queryAllCountries(HttpServletRequest request,HttpServletResponse response)
    {
		
		String lang="ch";
    	 try{
    		 List<Map<String,String>> country=sonRight.queryAllCountries(lang);
    		 JSONObject json = new JSONObject();
    		 json.put("country", country);
    		 log.info(json.toString());
    		 response.getWriter().write(json.toString());
    	 }catch(Exception e)
    	 {
    		 e.printStackTrace();
    		 log.debug(e.toString());
    	 }
    }
	/**
	 * 根据用户ID查询用户拥有的国家列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryCountriesByUid")
    public void queryCountriesByUid(HttpServletRequest request,HttpServletResponse response,QueryModel query)
    {
		try{
			 List<String> country=sonRight.queryCountriesByUid(query);
    		 JSONObject json = new JSONObject();
    		 json.put("country", country);
    		 log.info(json.toString());
    		 response.getWriter().write(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
    }
	/**
	 * 根据用户ID和国家名称查询 用户权限列表
	 * @param request
	 * @param response
	 * @param query
	 */
	@RequestMapping(value="/queryRightsByUidCN")
    public void queryRightsByUidCN(HttpServletRequest request,HttpServletResponse response,QueryModel query)
    {
		try{
			 List<ConditionRightModel> countryList=sonRight.queryRightsByUidCN(query);
    		//获取全部子用户权限的数量
 		    int count=sonRight.queryCountCountryRightByUserID(query);
 		    JSONObject json=this.getJsonObject(count, countryList);
 			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
    }
	/**
	 * 为子用户授予权限
	 * 拥有权限的试用用户将自动转换成普通用户
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/addRights")
    public void addRights(HttpServletRequest request,HttpServletResponse response)
    {
		try{
			UserModel user=(UserModel)request.getSession().getAttribute("user");
			JSONObject json=JSONObject.fromObject(request.getParameter("data"));
			//获取前台的用户权限列表
            List<Map<String,String>> rights=(List<Map<String, String>>) json.getJSONArray("rights");
            //获取用户的ID
            String uid=json.getString("uid");
            int userId=Integer.parseInt(uid);
            //修改用户的身份
            userManage.updateUserDesc(userId,user.getUserDesc());
            //为用户授权
           boolean flag=sonRight.addRights(rights, userId);
           json.put("flag", flag);
           response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
    }
	/**
	 * 根据用户ID和权限类别分页查询用户权限列表
	 * @param request
	 * @param response
	 * @param query
	 */
	@RequestMapping(value="/queryCountryRightsForPagingById")
    public void queryCountryRightsForPagingById(HttpServletRequest request,HttpServletResponse response,QueryModel query)
    {
		try{
			PageBeanUtil pageUtil=this.getPageBean(request);
			query.setCurPage(pageUtil.getPageIndex());
			query.setPageSize(pageUtil.getPageSize());
			 List<ConditionRightModel> rightList=sonRight.queryRightsForPaging(query);
    		//获取全部子用户权限的数量
 		    int count=sonRight.queryRightNum(query);
			//获取全部子用户的数量
		    JSONObject json=this.getJsonObject(count, rightList);
			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
    }
	/**
	 * 根据用户ID和权限类别分页查询用户hscode或者产品描述列表
	 * @param request
	 * @param response
	 * @param query
	 */
	@RequestMapping(value="/queryCodeRightsForPagingById")
    public void queryCodeRightsForPagingById(HttpServletRequest request,HttpServletResponse response,QueryModel query)
    {
		try{
			PageBeanUtil pageUtil=this.getPageBean(request);
			query.setCurPage(pageUtil.getPageIndex());
			query.setPageSize(pageUtil.getPageSize());
			 List<ConditionRightModel> rightList=sonRight.queryRightsForPaging(query);
    		//获取全部子用户权限的数量
 		    int count=sonRight.queryRightNum(query);
			//获取全部子用户的数量
		    JSONObject json=this.getJsonObject(count, rightList);
			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
    }
	/**
	 * 查询下载数量
	 * @param request
	 * @param response
	 * @param query
	 */
	@RequestMapping(value="/queryDownLoadNum")
    public void queryDownLoadNum(HttpServletRequest request,HttpServletResponse response,QueryModel query)
    {
		try{
			List<UserDownloadRightModel> list=sonRight.queryDownLoadNum(query);
			JSONObject json=new JSONObject();
			json.put("downRight", list);
			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
			try {
				response.getWriter().println("2");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
	/**
	 * 修改下载条数
	 * @param request
	 * @param response
	 * @param query
	 */
	@RequestMapping(value="/updateDownNum")
    public void updateDownNum(HttpServletRequest request,HttpServletResponse response,UserDownloadRightModel downRightModel)
    {
		try{
			 boolean flag=sonRight.updateDownNum(downRightModel);
			JSONObject json=new JSONObject();
			json.put("flag", flag);
			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
    }
}
