package com.njyb.gbdbase.controller.usermanagement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.IUserManageService;
import com.njyb.gbdbase.service.usermanagement.IUserSonBaseOperService;
/**
 * 子用户的Controll层
 * @author chenhu
 * 2015年4月23日
 */
@Controller
@RequestMapping("/userSon")
public class UserSonController  extends PublicCommonController{
	
	@Autowired
	private IUserSonBaseOperService userSonBaseOpernService;
	@Autowired
	private IUserManageService userManagerService;
	private static final Logger log=Logger.getLogger(UserSonController.class);
    /**
     * 添加用户
     * @param request
     * @param response
     * @param userMode
     * @return
     */
	@RequestMapping(value = "/addUserSon",method = RequestMethod.POST)
	public void  addUserSon(HttpServletRequest request,HttpServletResponse response,UserModel userModel)
	{
		//添加结果
		boolean flag=false;
		try{
			//获取用户(当前使用用户)
			UserModel sessionUser = (UserModel)request.getSession().getAttribute("user");
			//添加父用户数据模型
			QueryModel query=new QueryModel();
			query.setLoginName(sessionUser.getLoginName());
			UserModel user=userSonBaseOpernService.queryUserByLoginName(query);
			//添加子用户
			flag=userSonBaseOpernService.addUserSon(userModel, user);
			JSONObject json = new JSONObject();
			json.put("flag", flag);
			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 *判断子用户名是否重复
	 * @param request
	 * @param response
	 * @param userModel
	 * @return
	 */
	@RequestMapping(value = "/checkUserSonName" ,method = RequestMethod.POST)
	@ResponseBody
	public boolean  checkUserSonName(HttpServletRequest request,HttpServletResponse response,String loginName)
	{
			boolean  flag=false;
			//根据登录名称获取该账户的子账户的数量
			QueryModel query=new QueryModel();
			query.setLoginName(loginName);
			UserModel user=userSonBaseOpernService.queryUserByLoginName(query);
			if(user==null)
			{
				flag=true;
			}
			return flag;
	}
	/**
	 * 检查该用户是否具备开通子帐号的权限
	 * @param request
	 * @param response
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/chechUserSonRight" ,method = RequestMethod.POST)
	public String  chechUserSonRight(HttpServletRequest request,HttpServletResponse response,UserModel user)
	{
		try{
			int flag=userSonBaseOpernService.checkAbleCreateSon(user);
			JSONObject json = new JSONObject();
			json.put("flag", flag);
			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 分页查询子用户
	 * @param request
	 * @param response
	 * @param userModel
	 * @return
	 */
	@RequestMapping(value = "/queryUserSons" ,method = RequestMethod.POST)
	public void  queryUserSons(HttpServletRequest request,HttpServletResponse response,QueryModel query)
	{
		try{
		    //获取分页的页数和页面大小
			PageBeanUtil pageUtil=this.getPageBean(request);
			query.setCurPage(pageUtil.getPageIndex());
			query.setPageSize(pageUtil.getPageSize());
			//分页查询子用户列表
			List<UserModel> logList=userSonBaseOpernService.queryUserSonsByIdForPaging(query);
			//获取全部子用户的数量
		    int count=userSonBaseOpernService.queryUserSonNumById(query);
		    JSONObject json=this.getJsonObject(count, logList);
			response.getWriter().println(json.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	/**
	 * 级联删除多个用户
	 * 删除用户时，把跟该用户相关的所有信息全部删除
	 * @param request
	 * @param response
	 * @param query
	 */
	@RequestMapping(value="/deleteUserSons",method=RequestMethod.POST)
	public void deleteUserSonsCascade(HttpServletRequest request,HttpServletResponse response)
	{
	    try{
	    	boolean flag=false;
            String[] userIds = request.getParameterValues("ids[]");
            flag=userSonBaseOpernService.deleteUsers(userIds);
            JSONObject json = new JSONObject();
			json.put("flag", flag);
			response.getWriter().println(json.toString());
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	log.debug(e.toString());;
	    }
	}
	/**
	 * 级联删除用户
	 * 删除用户时，把跟该用户相关的所有信息全部删除
	 * @param request
	 * @param response
	 * @param query
	 */
	@RequestMapping(value="/deleteUserSon",method=RequestMethod.POST)
	public void deleteUserSonCascade(HttpServletRequest request,HttpServletResponse response,QueryModel query)
	{
	    try{
	    	boolean flag=false;
            UserModel user=userManagerService.queryUserById(query);
            flag=userSonBaseOpernService.deleteUser(user);
            JSONObject json = new JSONObject();
			json.put("flag", flag);
			response.getWriter().println(json.toString());
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	log.debug(e.toString());;
	    }
	}
	
}
