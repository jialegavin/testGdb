package com.njyb.gbdbase.service.usermanagement.imp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.MD5Util;
import com.njyb.gbdbas.util.StringUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.njyb.gbdbase.dao.datasearch.export.IUserDownloadRightDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.datasearch.export.UserDownloadRightModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.IUserManageService;
import com.njyb.gbdbase.service.usermanagement.IUserSonBaseOperService;
/**
 * 
 * @author chenhu
 * 2015年5月20日
 */
@Service
public class UserSonBaseOperService implements IUserSonBaseOperService {
	
	@Autowired
	private IUserDao  userDao;
	@Autowired
	private IUserManageService userManageService;
	@Autowired
	private IUserDownloadRightDao  downLoadRightDao;
    	
	private static final Logger log=Logger.getLogger(UserSonBaseOperService.class);
	@Override
	public List<UserModel> queryUserSonsByLoginNameForPaging(QueryModel queryModel) 
	{
		
	  List<UserModel> list=new  ArrayList<UserModel>();
	  try{
		  if((queryModel!=null)&&(queryModel.getPageSize()!=0)&&(queryModel.getCurPage()!=0))
			 {
				if(!(StringUtil.isNone(queryModel.getLoginName())))
				{   
					UserModel userModel=new UserModel();
					userModel=queryUserByLoginName(queryModel);
					if(userModel!=null&&(userModel.getUserId()!=0))
					{
						queryModel.setWhereSql(" relaid="+userModel.getUserId());
					     list=userDao.queryUsersForPaging(queryModel);
					}
			   }
			}
	  }catch(Exception e)
	  {
		  log.debug(e.toString());
	  }
		 return list;
	}
	@Override
	public List<UserModel> queryUserSonsByIdForPaging(QueryModel queryModel) {
		
		List<UserModel> list=new  ArrayList<UserModel>();
		  try{
			  if((queryModel!=null)&&(queryModel.getPageSize()!=0)&&(queryModel.getCurPage()!=0)&&queryModel.getUserId()!=0)
				 {
							queryModel.setWhereSql(" relaid="+queryModel.getUserId());
						     list=userDao.queryUsersForPaging(queryModel);
				}
		  }catch(Exception e)
		  {
			  log.debug(e.toString());
		  }
			 return list;
	}
	@Override
	public int queryUserSonNumById(QueryModel queryModel) {
		
		int num=0;
		try{
			if(queryModel!=null&&queryModel.getUserId()!=0)
			{
				queryModel.setWhereSql(" relaid="+queryModel.getUserId());
				num=userDao.queryUserCountBySql(queryModel);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return num;
	}
	@Override
	public UserModel queryUserByLoginName(QueryModel queryModel) {
		
		UserModel user=new UserModel();
		try{
			if((queryModel!=null)&&!("".equals(queryModel.getLoginName())))
			{
				queryModel.setWhereSql(" loginName='"+queryModel.getLoginName()+"'");
		        user=userDao.queryUserBySql(queryModel);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
        return user;
	}
	
	@Override
	public String getIpAddress(HttpServletRequest request) {
		
		 String ip = request.getHeader("x-forwarded-for"); 
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		    { 
		        ip = request.getHeader("Proxy-Client-IP"); 
		    } 
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		    { 
		        ip = request.getHeader("WL-Proxy-Client-IP"); 
		    } 
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		    { 
		    	//获取真实用户访问的ip地址
		    	ip = request.getRemoteAddr(); 
		    } 
		    return ip;
	}
    
	@Override
	public int  checkAbleCreateSon(UserModel userModel) 
	{
		
		//是否可以创建子用户的标志
		int flag=0;
		try{
			if((userModel!=null)&&!(StringUtil.isNone(userModel.getLoginName())))
			{
				QueryModel query=new QueryModel();
				query.setLoginName(userModel.getLoginName());
				UserModel user=queryUserByLoginName(query);
				query.setUserId(user.getUserId());
				//获取用户层数
				int floor=userManageService.queryFloorById(query);
				if(floor<=IConstantUtil.SONFLOOR)
				{
					if(user.getOpenService())
					{
						if(user.getMainId()!=0)
						{
							query.setWhereSql(" userId="+user.getMainId());
							//获取主用户
							UserModel mainUser=userDao.queryUserBySql(query);
							if((mainUser.getSonAccountNum()!=mainUser.getSonAccountTotal()))
							{
								flag=1;
							}
							else
							{
								flag=2;
							}
						}
						else
						{
							if((user.getSonAccountNum()!=user.getSonAccountTotal()))
							{
								flag=1;
							}
							else
							{
								flag=2;
							}
						}
					}
					else
					{
						flag=3;
					}
				}
				else
				{
					flag=4;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return flag;
	}    
	
	@Override
	public String ckeckInCondtionForAddSon(UserModel user) {
		StringBuffer sb=new StringBuffer();
		if(user!=null)
		{
			//判断用户名是否重复
			if(queryEmailNum(user)!=0)
			{
				//返回Email重复信息
				sb.append(IConstantUtil.ISEMAILREPEAT);
				return sb.toString();
			}
			if( queryLoginNameNum(user)!=0)
			{
				//返回用户名重复信息
				sb.append(IConstantUtil.ISLOGINNAMEREPEAT);
			}
			//判断邮箱是否重复
		}
		return sb.append("Y").toString();
	}	
	
	/**
	 * 全库查询Email是否重复
	 * @param email
	 * @return
	 */
	private int queryEmailNum(UserModel user)
	{
		
		int emailNum=0;
		try{
			if(user.getEmail()!=null&&!("".equals(user.getEmail())))
			{
				
				QueryModel query=new QueryModel();
				query.setWhereSql( " email='"+user.getEmail()+"'");
				
				//查询当前用户的Email在系统中是否重复
				emailNum=userDao.queryUserCountBySql(query);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return emailNum;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int queryLoginNameNum(UserModel user)
	{
		
		int loginNameNum=0;
		try{
			if(user.getLoginName()!=null&&!("".equals(user.getLoginName())))
			{
				QueryModel query=new QueryModel();
				query.setWhereSql( " loginname="+user.getLoginName());
				//查询当前用户的Email在系统中是否重复
				loginNameNum=userDao.queryUserCountBySql(query);
			}
		}catch(Exception e)
		{
			log.debug(e.toString());
		}
		return loginNameNum;
	}
	
	@Override
	public boolean addUserSon(UserModel son, UserModel user) 
	{
		
		boolean flag=false;
		try{
			if((checkUser(user))&&(checkUser(son)))
			{
				try 
				{
					//添加主账号的id
					if(user.getMainId()==0)
					{
						son.setMainId(user.getUserId());
					}
					else
					{
						son.setMainId(user.getMainId());
					}
				
					//添加父账户的id
					son.setRelaId(user.getUserId());
					//密码
					String passwd=MD5Util.getInstance().generateEncrypte(son.getLoginPassword());
					son.setLoginPassword(passwd);
					//用户描述
					son.setUserDesc(IConstantUtil.DEFAULTDESC);
					//初始化子账户数量
					son.setSonAccountTotal(IConstantUtil.DEFUATSONS);
					//是否激活
					son.setIsActivated(IConstantUtil.USERACTIVE);
					//初始化开通子账户的权限
					son.setOpenService(IConstantUtil.OPENSERVICE);
					//初始化账户锁定状态
					son.setIsLocked(IConstantUtil.LOCKED);
					//初始化登录次数
					son.setLoginCount(IConstantUtil.LOGINTIME);
					//初始化用户是否可用
					son.setIsDisable(IConstantUtil.ISDISABLE);
					//添加新的用户
					userDao.addNewUser(son);
					//查询新增子用户的ID根据用户名
					QueryModel query=new QueryModel();
					query.setWhereSql(" loginName='"+son.getLoginName()+"'");
					UserModel newUser=userDao.queryUserBySql(query);
					//更新因添加子用户而引起的父用户的变化
					updateFather(user,true);
					//添加下载条数
					UserDownloadRightModel userRight=new UserDownloadRightModel();
					userRight.setPdfNum(IConstantUtil.DEFAULTPDF);
					userRight.setTotalNum(IConstantUtil.DEFAULTTOATLNUM);
					userRight.setExcelNum(IConstantUtil.DEFAULTEXELNUM);
					userRight.setUserId(newUser.getUserId());
					downLoadRightDao.insertDownloadRecord(userRight);
					flag=true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e)
		{
			log.debug(e.toString());
		}
	  return flag;
		
	}
	@Override
	public boolean updateUserInfo(UserModel user) 
	{
		boolean flag=false;
        try{
        	if(user!=null)
    		{
    			userDao.updateUserInfo(user);
    			flag=true;
    		}
        }catch(Exception e)
        {
        	log.debug(e.toString());
        }
		return flag;
	}
	
	@Override
	public boolean updateUserActive(UserModel user)
	{
		//返回标志
		boolean flag=false;
		try
		{
			// 判断用户模型是非空
			if(checkUser(user))
			{
				QueryModel  query=new QueryModel();
				query.setUpdateSql(" isactivated="+user.getIsActivated()+"\n");
				query.setWhereSql(" loginname="+user.getLoginName()+"\n");
				//根据用户登录名称激活用户
				userDao.updateUserBySql(query);
				flag=true;
			}
		}catch(Exception e)
		{
			log.debug(e.toString());
		}
		return flag;
	}
	
	@Override
	public boolean updateOpenSubService(UserModel user)
	{
		//返回标准
		boolean flag=false;
		try{
			if(checkUser(user))
			{
				QueryModel  query=new QueryModel();
				query.setUpdateSql(" openservice="+user.getOpenService()+"\n");
				query.setWhereSql(" loginname="+user.getLoginName()+"\n");
				//根据用户登录名称激活用户
				userDao.updateUserBySql(query);
			}
		}catch(Exception e)
		{
			log.debug(e.toString());
		}
		return flag;
	}
	@Override
	public boolean deleteUsers(String[] ids) {
		boolean flag=false;
		try{
			if(ids!=null&&ids.length>0)
			{  
				
			   for(String i:ids)
			   {
				   int userId=Integer.parseInt(i);
				   //根据用户ID查询用
				   UserModel user=userDao.queryUserByID(userId);
				   flag=deleteUser(user);
			   }
			}
	   }catch(Exception e)
	   {
		   e.printStackTrace();
		   log.debug(e.toString());
	   }
		return flag;
	}
	@Override
	public boolean deleteUser(UserModel user)
	{
		boolean flag=false;
			try
			{
				//根据登录名获取UserModel
				if(user!=null&&user.getUserId()!=0)
				{
					int userId=user.getUserId();
					//判断该用户是否有子用户
					int sonNum=user.getSonAccountNum();
					if((sonNum!=0)&&(IConstantUtil.ISDELETESONS))
					{
						//获取该用户下的所有子用户
						List<UserModel> userList=userManageService.queryAllSons(userId);
						if(userList!=null&&userList.size()>0)
						{
							for(UserModel userModel:userList)
							{
								 updateFather(userModel,false);
								userDao.deleteUserAllMesseByUserID(userModel.getUserId());
							   
							}
						}
					}
					//删除与当前用户关联的全部数据
					userDao.deleteUserAllMesseByUserID(userId);
				}
				
				flag=updateFather(user,false);
			}catch(Exception e)
			{
				flag=false;
				log.debug(e.toString());
				e.printStackTrace();
			}
		
		return flag;
	}

	@Override
	public boolean checkUser(UserModel user) 
	{
		boolean flag=false;
		//检查用户 满足数据库条件
	     if(user!=null)
	     {
	    	 if((user.getLoginName()!=null)&&(user.getLoginPassword()!=null))
	    	 {
	    		 flag=true;
	    	 } 
	     }
		return flag;
	}
	/**
	 * 修改因增删引起的上级用户的变化
	 * @param user
	 * @param operflag 操作类型：false 删除，true 增加
	 * @return
	 */
	private boolean updateFather(UserModel user,boolean operflag)
	{
		boolean flag=false;
		UserModel faUser=new UserModel();
		try{
			if(operflag&&user!=null){
			  	//执行添加的用户如果不是主用户，则修改当主用户的子账户数量
				if(user.getMainId()!=0)
				{
					faUser=userDao.queryUserByID(user.getMainId());
					faUser=updateUserSonNum(faUser,operflag);
					userDao.updateUserSonNum(faUser);
				}
				//修改父用户的子账户数量
				user=updateUserSonNum(user,operflag);
				userDao.updateUserSonNum(user);
			}
			else
			{    
				   //判断该用户是否是主用户，如果是则不做任何处理
				   if(user.getMainId()!=0){
					   //获取上一级用户
					   faUser=userDao.queryUserByID(user.getRelaId());
					   //如果父节点已经被删除了
					   if(faUser==null){
                           //则直接更新主账户的数量						   
						   UserModel userMain=userDao.queryUserByID(user.getMainId());
						   userMain=updateUserSonNum(userMain,operflag);
						   userDao.updateUserSonNum(userMain);
					   }else{
						   //执行删除的上级用户不是主用户，则修改当主用户的子账户数量
						   if(faUser.getMainId()!=0)
						   {
							   UserModel userMain=userDao.queryUserByID(user.getMainId());
							   userMain=updateUserSonNum(userMain,operflag);
							   userDao.updateUserSonNum(userMain);
						   }
						   //修改父用户的子账户数量
						   faUser=updateUserSonNum(faUser,operflag);
						   userDao.updateUserSonNum(faUser);
					   }
				   }
		}
			
			flag=true;
		}catch(Exception e)
		{
			flag=false;
			e.printStackTrace();
			log.debug(e);
		}
		return flag;
	}

	 /**
	  * 修改账户的子账户数量
	  * @param user
	  * @param operflag
	  * @return
	  */
	  private UserModel updateUserSonNum(UserModel user,boolean operflag)
	  {
		  int sonNum=0;
		  if(operflag)
		  {
			  sonNum=user.getSonAccountNum()+1;
		  }
		  else
		  {
			  sonNum=user.getSonAccountNum()-1;
		  }
			user.setSonAccountNum(sonNum);
			return user;
	  }
}
