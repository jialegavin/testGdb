package com.njyb.gbdbase.controller.admincenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbas.util.ToJsonUtil;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.IUserManageService;

/**
 * 后台管理（系统用户管理）
 * @author chenhu
 * 2015年4月1日
 */
@Controller
@RequestMapping("/userManager")
public class UserManagerController {
	 @Autowired
	 private  IUserManageService userMangerService;
	 private static final Logger log=Logger.getLogger(UserManagerController.class);
	 /**
	  * 获取用户树
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/queryUserTree")
	 public void  queryUserTree(HttpServletRequest request,HttpServletResponse response){
		 try {
			   String treeDatajson="";
				 treeDatajson=userMangerService.queryUserTree();
				 response.setContentType("text/plain;charset=utf-8");
				 response.getWriter().write(treeDatajson);
			} catch (Exception e) {
				e.printStackTrace();
				log.debug(e.toString());
			}
	 }
	 /**
	  * 根据用户ID获取单个用户
	  * @param request
	  * @param response
	  * @param query
	  */
	 @RequestMapping(value = "/queryUserById")
	 public void  queryUserById(HttpServletRequest request,HttpServletResponse response,QueryModel query)
	 {
		 try{
			 UserModel user=new UserModel();
			  user= userMangerService.queryUserById(query);
			 String json= ToJsonUtil.bean2json(user);
			 response.setContentType("text/plain;charset=utf-8");
			 response.getWriter().write(json);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
	 }
	 /**
	  * 添加用户
	  * @param request
	  * @param response
	  * @param user
	  */
	 @RequestMapping(value = "/addNewUser")
	 public void  addNewUser(HttpServletRequest request,HttpServletResponse response,UserModel user)
	 {
		 try{
			 JSONObject json=new JSONObject();
			 String result=userMangerService.addNewUser(user);
			 json.put("result", result);
			 response.setContentType("text/plain;charset=utf-8");
			 response.getWriter().write(json.toString());
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
	 }
	 /**
	  * 根据用户的登录名称修改用户
	  * @param request
	  * @param response
	  * @param user
	  */
	 @RequestMapping(value = "/upUser")
	 public void  upUser(HttpServletRequest request,HttpServletResponse response,UserModel user)
	 {
		 try{
			 JSONObject json=new JSONObject();
			 String result=userMangerService.upUser(user);
			 json.put("flag", result);
			 response.setContentType("text/plain;charset=utf-8");
			 response.getWriter().write(json.toString());
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
	 }
	 /**
	  * 根据用户的ID 修改用户的详细信息
	  * @param request
	  * @param response
	  * @param user
	  */
	 @RequestMapping("/upUserInfo")
	 public void upUserInfo(HttpServletRequest request,HttpServletResponse response,UserModel user){
		 try{
			 JSONObject json=new JSONObject();
			 boolean  flag=userMangerService.upUserInfo(user);
			 json.put("flag", flag);
			 response.setContentType("text/plain;charset=utf-8");
			 response.getWriter().write(json.toString());
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
	 }
	 /**
	  * 动态修改用户的子用户数量
	  * @param request
	  * @param response
	  * @param user
	  */
	 @RequestMapping(value = "/updateUseSonCount")
	 public void  updateUseSonCount(HttpServletRequest request,HttpServletResponse response,UserModel user)
	 {
		 try{
			 JSONObject json=new JSONObject();
			 String result=userMangerService.upUserSonCount(user);
			 json.put("result", result);
			 response.setContentType("text/plain;charset=utf-8");
			 response.getWriter().write(json.toString());
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
	 }
	 /**
	  * 修改用户部分字段的值
	  * @param request
	  * @param response
	  * @param query
	  */
	 @RequestMapping(value = "/updateFiledStatus")
	 public void  updateFiledStatus(HttpServletRequest request,HttpServletResponse response,QueryModel query)
	 {
		 try{
			 JSONObject json=new JSONObject();
			 String result=userMangerService.updateUserFiedStatus(query);
			 json.put("result", result);
			 response.setContentType("text/plain;charset=utf-8");
			 response.getWriter().write(json.toString());
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
	 }
	 /**
	  * 获取当前用户
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/querySessionUser")
	 public void  querySessionUser(HttpServletRequest request,HttpServletResponse response)
	 {
		 try{
			 UserModel user=(UserModel)request.getSession().getAttribute("user");
			 String json="";
			 if(user!=null){
				  json= ToJsonUtil.bean2json(user);
			 }
			 response.setContentType("text/plain;charset=utf-8");
			 response.getWriter().write(json.toString());
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
	 }
	 /**
	  * 因用户变更，变更缓存内的用户信息
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/upSessionUser")
	 public void  upSessionUser(HttpServletRequest request,HttpServletResponse response,QueryModel query)
	 {
		 try{
			 UserModel user= userMangerService.queryUserById(query);
			 request.getSession().setAttribute("user", user);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
	 }
	 
}
