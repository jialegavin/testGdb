package com.njyb.gbdbase.controller.admincenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.njyb.auth.service.impl.GrantAuthCommonService;
import com.njyb.auth.service.impl.UserGrantAuthService;
import com.njyb.auth.service.intface.IGrantAuthCommonService;
import com.njyb.auth.service.intface.IUserGrantAuthService;
import com.njyb.auth.util.GetDoMainName;
import com.njyb.gbdbas.loginFilter.CheckSessionFilter;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.ToJsonUtil;
import com.njyb.gbdbase.model.admincenter.UserConsumingRecordsModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
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
	 @Autowired
	 private IUserGrantAuthService UserGrantAuthService;
	 @Autowired
	 private IGrantAuthCommonService grantAuthCommonService;
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
	 
	 /**
	  * 管理员操作添加用户消费记录
	  * @param request
	  * @param response
	  * @param userModel
	  * @param userConsumingRecordsModel
	  * @param flag
	  * @return
	  */
	 @RequestMapping(value = "/rightUserOper")
	 public @ResponseBody String rightUserOper(HttpServletRequest request,HttpServletResponse response, UserModel userModel,UserConsumingRecordsModel userConsumingRecordsModel,String addUserRightType) {
			//重新设置数据源 
		 String domain = GetDoMainName.getDoMain(request);
		 synchronized (this) {
			  //重新设置域名
			  CheckSessionFilter.domainMap.put("domain", domain);
		 }
		 System.out.println("展示权限页面---domain" + GetDoMainName.getDoMain(request));
		 String jsonStr = null;
		if(addUserRightType.equals("a")) {
			jsonStr = userMangerService.addRightUser(userModel, userConsumingRecordsModel);
		} else {
			jsonStr = userMangerService.addConsumingRecordsByUser(userModel, userConsumingRecordsModel);
		}
		return jsonStr;
	 }
	 
	 /**
	  * 用户授权
	  * @param request
	  * @param response
	  * @param jsonArray
	  * @param auth_type
	  * @param loginName
	  * @return
	  * @throws JSONException
	  */
	 @RequestMapping(value = "/addRightForUser")
	 public @ResponseBody String addRightForUser(HttpServletRequest request,HttpServletResponse response,String jsonArray,String auth_type,String loginName,String buyMoney) throws JSONException{
		 //重新设置数据源 
		 String domain = GetDoMainName.getDoMain(request);
		 synchronized (this) {
			 //重新设置域名
			 CheckSessionFilter.domainMap.put("domain", domain);
		 }
		 System.out.println("用户授权---domain" + domain);
		 Map<String,String> paramMap = new HashMap<String, String>();
		 paramMap.put("jsonArray", jsonArray);
		 paramMap.put("loginName", loginName);
		 paramMap.put("buyMoney", buyMoney);
		 if(auth_type.equals(IConstantUtil.AUTH_HSCODE)){
			 UserGrantAuthService.userGrantAuthByHscode(request, paramMap);
		 }else if(auth_type.equals(IConstantUtil.AUTH_DESC)){
			 UserGrantAuthService.userGrantAuthByDesc(request, paramMap);
		 }else if(auth_type.equals(IConstantUtil.AUTH_COUNTRY)){
			 UserGrantAuthService.userGrantAuthByCountry(request, paramMap);
		 }else{
			 UserGrantAuthService.userGrantAuthByCount(request, paramMap);
		 }
		 JSONObject js = new JSONObject();
		 js.put("message", "授权成功！");
		 return js.toString();
	 }
	 
	 /**
	  * 展示权限页面
	  * @param request
	  * @param response
	  * @param auth_type
	  * @throws ServletException
	  * @throws IOException
	  */
	 @RequestMapping(value = "/showCountryInfo")
	 public void showCountryInfo(HttpServletRequest request,HttpServletResponse response,String auth_type) throws ServletException, IOException{
		//重新设置数据源 
		 String domain = GetDoMainName.getDoMain(request);
		 synchronized (this) {
			  //重新设置域名
			  CheckSessionFilter.domainMap.put("domain", domain);
		 }
		 System.out.println("展示权限页面---domain" + domain);
		 //根据用户选择的定制方式，返回对应的集合默认为hscode
		 //页面所展示的内容
		 List<ConditionRightModel> list = null;
		 if(auth_type.equals(IConstantUtil.AUTH_HSCODE)){
			 list = grantAuthCommonService.getCountryDefaultInfo(auth_type, request);
		 }else if(auth_type.equals(IConstantUtil.AUTH_DESC)){
			 list = grantAuthCommonService.getCountryDefaultInfo(auth_type, request);
		 }else if(auth_type.equals(IConstantUtil.AUTH_COUNTRY)){
			 list = grantAuthCommonService.getCountryDefaultInfo(auth_type, request);
		 }
		 Map<String, List<ConditionRightModel>> map = new HashMap<String, List<ConditionRightModel>>();
		 map.put(auth_type, list);
		 request.getSession().setAttribute("rightMap", map);
	 }
	 
}
