package com.njyb.gbdbase.service.admincenter.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.MD5Util;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.IUserManageService;

/**
 * 管理服务
 * @author chenhu
 * 2015年3月27日
 */
@Service
public class UserManageService implements IUserManageService {

	@Autowired
	private  IUserDao userDao;
	private static final Logger log=Logger.getLogger(UserManageService.class);
	@Override
	public List<UserModel>  queryAllSons(int uId) {
		List<UserModel> list=new ArrayList<UserModel>();
		try{
			
			 list=querySonUseRecun(uId);
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return list;
	}
	/**
	 * 根据用ID递归查询所有的自用户的ID
	 * @param uId
	 * @return
	 */
	private List<UserModel>  querySonUseRecun(int uId)
	{
	
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		 List<UserModel> list=new  ArrayList<UserModel>();
		try{
			List<UserModel> users=new ArrayList<UserModel>();
			QueryModel query=new QueryModel();
			query.setWhereSql(" and relaid="+uId+"\n");
			//查询单个用户下的所有子用户
			users=userDao.queryUsersBySql(query);
			if(users!=null&&users.size()>0)
			{
				 for(UserModel user:users)
				 {
					 //添加子用户ID
					 list.add(user);
					 if(user.getSonAccountNum()>0)
					 {
						 //递归查询自用户的子用户
						 list.addAll(queryAllSons(user.getUserId()));
					 }
				 }
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return list;
	}
	@Override
	public List<UserModel> queryAllUsersByDescForPaging(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		List<UserModel> list=new ArrayList<UserModel>();
       try{
    	   if((query!=null)&&(query.getCurPage()!=0)&&(query.getPageSize()!=0)&&("".equals(query.getUserDesc())))
    	   {
	    	   list=userDao.queryUsersByDesc(query);
	           
    	   }
       }catch(Exception e)
       {
    	   e.printStackTrace();
    	   log.debug(e.toString());
       }
		return list;
	}
	@Override
	public int queryUserCountByDesc(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		  int count=0;
	       try{
	    	   if((query!=null)&&(query.getCurPage()!=0)&&(query.getPageSize()!=0)&&("".equals(query.getUserDesc())))
	    	   {
		    	   count=userDao.queryUserCountByDesc(query.getUserDesc());
	    	   }
	       }catch(Exception e)
	       {
	    	   e.printStackTrace();
	    	   log.debug(e.toString());
	       }
			return count;
	}
	
    /**
     * 返回用户树的JSON格式
     * @return
     */
	@Override
	public String queryUserTree() {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
	    //一级树根
	    JSONArray root=new JSONArray();
	    try{
	    	JSONObject title=new JSONObject();
			title.put("text", "用户组");
			JSONArray rootChild=new JSONArray();
			//1.划分用户类别
			 List<String> userDesc=userDao.queryDiffUserByDesc();
			if(userDesc!=null&&userDesc.size()>0)
			{
			   for(String desc:userDesc)	
			   {
				   //用户的类别，添加到不同的树级结构中
				   JSONObject userTree=new JSONObject();
				   if(desc!=null&&!("".equals(desc)))
				   {
					   //添加二级树
					   userTree.put("id", "userType");
					   userTree.put("text", desc);
					   //根据用户描述获取不同用户的列表
					   List<UserModel> list=new ArrayList<UserModel>();
					   QueryModel query=new QueryModel();
					   query.setUserDesc(desc);
					   //查询mainid为空的用户
					   list=userDao.queryUsersByDesc(query);
					   //添加三级树
					   JSONArray diffUserTree=new JSONArray();
					   //添加不同用户数的用树
					   if(!("管理员用户".equals(desc.trim()))&&!("试用用户".equals(desc.trim())))
					   {
						   //正式用户存在
						   for(UserModel user:list)
						   {
							   //正式用户可能存在子用户，因此要递归查询子用户的的全部内容
							   JSONObject userLeaf=new JSONObject();
							   userLeaf.put("id", user.getUserId());
							   userLeaf.put("text", user.getLoginName());
							    if(user.getSonAccountNum()>0)
							    {
							    	userLeaf.put("children",getSonLeaf(user.getUserId()));
							    }
							   diffUserTree.add(userLeaf);
						   }
					   }
					   else{
						   for(UserModel user:list)
						   {
							   //非正式用户如：管理员、试用用户不存在子节点
							   JSONObject userLeaf=new JSONObject();
							   userLeaf.put("id", user.getUserId());
							   userLeaf.put("text", user.getLoginName());
							   diffUserTree.add(userLeaf);
						   }
						   
					   }
					   userTree.accumulate("children", diffUserTree);
					   rootChild.add(userTree);
				   }
			   }
			}
			title.put("children", rootChild);
			root.add(title);
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.debug(e);
	    }
		return root.toString();
	}

	/**
	 * 递归查询自用的树
	 * @param uId
	 * @return
	 */
	 private JSONArray getSonLeaf(int uId){
		 DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		  JSONArray farther=new JSONArray();
		  try{
			  QueryModel query=new QueryModel();
			   query.setWhereSql(" and relaid="+uId+"\n");
			   //根据用户ID获取当前用户的所有子用户
			   List<UserModel> list=userDao.queryUsersBySql(query);
			   if(list!=null&&list.size()>0)
			   {
			      for(UserModel user:list)
			      {
			    	 JSONObject son=new JSONObject();
			    	 son.put("id", user.getUserId());
			    	 son.put("text", user.getLoginName());
			    	 //递归添加该用户的子类
			    	 if(user.getSonAccountNum()>0)
			    	 {
			    	   son.put("children", getSonLeaf(user.getUserId()));
			    	 }
			    	 farther.add(son);
			      }
			   }
		  }catch(Exception e){
			  e.printStackTrace();
			  log.debug(e);
		  }
		  return farther;
	}

	@Override
	public UserModel queryUserById(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		UserModel user=new UserModel();
		try{
			if(query!=null&&query.getUserId()!=0)
			{
			  query.setWhereSql("  userId="+query.getUserId()+"\n");
			  user= userDao.queryUserBySql(query);
			}
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 log.debug(e.toString());
		 }
		return user;
	}
	@Override
	public int queryFloorById(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		int floorNum=0;
		try{
			if(query!=null&&query.getUserId()!=0)
			{
				//1.获取该用户
				UserModel user=userDao.queryUserByID(query.getUserId());
				//2.判断是否是主用户
				if(user!=null&&user.getMainId()==0)
				{
					floorNum= 1;
				}
				else
				{
					//递归查询该用户的层次
					floorNum= queryFloRC(query);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
			
		}
		return floorNum;
	}
	/**
	 * 递归查用户的层数
	 * @return
	 */
	private int queryFloRC(QueryModel query)
	{
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		int floor=1;
		try{
			QueryModel queryModel=new QueryModel();
			
	         //查询当前用户的上级用户
			UserModel user=userDao.queryUserByID(query.getUserId());
			//判断上级目录是否是根级目录
			if(user.getRelaId()!=0)
			{
				queryModel.setUserId(user.getRelaId());
				floor+=queryFloRC(queryModel);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
		}
		return floor;
	}
	@Override
	public String addNewUser(UserModel user) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		//0-添加失败 ， 1-添加成功
		String operFlag="0";
		try{
			if(user!=null)
			{
				log.info("添加新用户-输入一个用户模型");
				//添加主账号的id
				user.setMainId(0);
				//添加父账户的id
				user.setRelaId(0);
				//设置默认初始密码
				user.setLoginPassword(MD5Util.getInstance().generateEncrypte("njyb123456"));
				//初始化子账户数量
				user.setSonAccountTotal(IConstantUtil.DEFUATSONS);
				//是否激活
				user.setIsActivated(IConstantUtil.USERACTIVE);
				//初始化开通子账户的权限
				user.setOpenService(IConstantUtil.OPENSERVICE);
				//初始化账户锁定状态
				user.setIsLocked(IConstantUtil.LOCKED);
				//初始化登录次数
				user.setLoginCount(IConstantUtil.LOGINTIME);
				//初始化用户是否可用
				user.setIsDisable(IConstantUtil.ISDISABLE);
				//添加新的用户
				userDao.addNewUser(user);
				operFlag="1";
			}
			
		}catch(Exception e){
			log.debug(e.toString());
			e.printStackTrace();
		}
		return operFlag;
	}
	@Override
	public String upUser(UserModel user) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		String operFlag="0";
		try{
			log.info("修改用户-输入:登录名="+user.getLoginName());
			String loginPasswd="";
			//修改用户密码
			if((user.getLoginPassword()!=null)&&("".equals(user.getLoginPassword())))
			{
				QueryModel query=new QueryModel();
				query.setUserId(user.getUserId());
				loginPasswd=this.queryUserById(query).getLoginPassword();
			}
			else
			{
				loginPasswd=MD5Util.getInstance().generateEncrypte(user.getLoginPassword());
			}
			user.setLoginPassword(loginPasswd);
			userDao.updateUserInfo(user);
			operFlag="1";
		}catch(Exception e)
		{
			log.debug(e.toString());
			e.printStackTrace();
		}
		return operFlag;
	}
	@Override
	public String upUserSonCount(UserModel user) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		String operFlag="0";
		try{
			if(user!=null&&user.getUserId()!=0)
			{
				userDao.updateUserSonCount(user);
				operFlag="1";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return operFlag;
	}
	@Override
	public String updateUserFiedStatus(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		//用户表中的状态字段返回值
		String flag="";
		try{
			if(query!=null&&query.getParam()!=null&&!("".equals(query.getParam()))&&query.getUserId()!=0)
			{
				  //组装修改sql
				query.setUpdateSql(query.getParam()+"=(CASE  WHEN "+query.getParam()+" = 0 THEN 1	ELSE 0 	END) \n");
				query.setWhereSql("  userId="+query.getUserId()+"\n");
				//更新要修改的字段
				userDao.updateUserBySql(query);
				query.setWhereSql("select "+query.getParam()+" from sys_user WHERE USERID="+query.getUserId());
				//查询修改后的字段
				flag=userDao.queryUserFieldBySql(query);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return flag;
	}
	@Override
	public void updateUserDesc(int userId, String userDesc) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		 try{
			 if((userId!=0)&&!("".equals(userDesc))){
				 QueryModel query=new QueryModel();
				 query.setUserDesc(userDesc);
				 query.setUserId(userId);
				 userDao.updateUserDescById(query);
			 }
	      
		 }
		catch(Exception e)
		 {
			e.toString();
			log.debug(e.toString());
		 }
	}
	@Override
	public boolean upUserInfo(UserModel user) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		 try{
			 if((user!=null)&&(user.getUserId()!=0)){
				 userDao.upUserInfo(user);
				 return true;
			 }
		 }
		catch(Exception e)
		 {
			e.toString();
			log.debug(e.toString());
		 }
		 return false;
	}
}