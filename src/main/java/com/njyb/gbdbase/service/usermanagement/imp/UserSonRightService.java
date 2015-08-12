package com.njyb.gbdbase.service.usermanagement.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.njyb.gbdbase.dao.datasearch.export.IUserDownloadRightDao;
import com.njyb.gbdbase.dao.usermanagement.IRightDao;
import com.njyb.gbdbase.dao.usermanagement.IViewDao;
import com.njyb.gbdbase.model.datasearch.export.UserDownloadRightModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.model.usermanagement.ViewModel;
import com.njyb.gbdbase.service.usermanagement.IUserSonRightService;

@Service
public class UserSonRightService implements IUserSonRightService {
	@Autowired
    private IRightDao rightDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IViewDao viewDao;
	@Autowired
	private IUserDownloadRightDao dwnDao;
	//log记录日志
	private static final Logger log = Logger.getLogger(UserSonRightService.class);
	@Override
	public List<ConditionRightModel> queryRightsByUidCN(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		List<ConditionRightModel> list=new ArrayList<ConditionRightModel>();
		try
		{
			 //根据用户ID查询用户在平台中购买的权限列表
			if(query!=null&&query.getUserId()!=0&&query.getCountryName()!=null)
			{
				//查询用户权限列表
				list=rightDao.queryRightsByUidCN(query);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
	   
		return list;
	}

	@Override
	public List<Map<String,String>> queryAllCountries(String lang) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		List<Map<String,String>> viewList=new ArrayList<Map<String,String>>();
		try
		{
			ViewModel view=new ViewModel();
			view.setType("counData");
			view.setLanguage(lang);
			viewList=viewDao.queryViewByTL(view);
		}catch(Exception e)
		{ 
			e.printStackTrace();
			log.debug(e.toString());
		}
		return viewList;
	}

	@Override
	public List<String> queryCountriesByUid(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		List<String> countryList=new ArrayList<String>();
		try
		{
			if(query!=null&&query.getUserId()!=0)
			{
				countryList=rightDao.queryRigthCountryByUserId(query.getUserId());
			}
			
		}catch(Exception e)
		{ 
			e.printStackTrace();
			log.debug(e.toString());
		}
		return countryList;
	}
	@Override
	public int queryCountCountryRightByUserID(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		int num=0;
		try{
			if(query!=null&&query.getUserId()!=0&&query.getCountryName()!=null)
			{
				//查询用户权限列表
				num=rightDao.queryCountCountryRightByUserID(query);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return num;
	}
	@Override
	public boolean addRights(List<Map<String, String>> list,int userId) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		boolean flag=false;
		try{
			if((list!=null)&&(list.size()>0))
			{
				//组装用户权限类
				for(Map map:list)
				{
					map.put("userId", userId);
					addUserConditionRight(map);
				}
				
			}
			flag=true;
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return flag;
	}
	@Override
	public boolean addUserConditionRight(Map rightMap) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		 boolean flag=false;  
		try{
			  rightDao.addRight(rightMap);
			  flag=true;
		  }catch(Exception e)
		  {
			  e.printStackTrace();
			  log.debug(e.toString());
		  }
		return flag;
	}

	@Override
	public List<ConditionRightModel> queryRightsForPaging(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		List<ConditionRightModel> list=new ArrayList<ConditionRightModel>();
		try{
			if(query!=null&&!("".equals(query.getParam()))&&query.getUserId()!=0)
			{
				list=rightDao.queryConditionRightsForPaging(query);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return list;
	}

	@Override
	public int queryRightNum(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		int num=0;
		try{
			if(query!=null&&!("".equals(query.getParam()))&&query.getUserId()!=0)
			{
				num=rightDao.queryRightNum(query);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return num;
	}

	@Override
	public List<UserDownloadRightModel> queryDownLoadNum(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		List<UserDownloadRightModel> list=new ArrayList<UserDownloadRightModel>();
		try{
			if(query!=null&&query.getUserId()!=0&&query.getRelaId()!=0)
			{
				UserDownloadRightModel sonDownModel=dwnDao.queryUserDownloadRight(query.getUserId());
				UserDownloadRightModel fatherDownModel=dwnDao.queryUserDownloadRight(query.getRelaId());

				if(sonDownModel!=null)
				{
					list.add(sonDownModel);
				}
				if(fatherDownModel!=null)
				{
					list.add(fatherDownModel);
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
	public boolean updateDownNum(UserDownloadRightModel downRightModel) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		boolean flag=false;
		try{
			if(downRightModel!=null&&downRightModel.getUserId()!=0&&downRightModel.getTotalNum()!=0)
			{
				String sql="update user_download_right set totalnum="
						+ ""+downRightModel.getTotalNum()+"\n where userId="+downRightModel.getUserId();
				dwnDao.updateDownloadRecord(sql);
			}
			flag=true;
		}catch(Exception e)
		{
			 log.debug(e.toString());
			 e.printStackTrace();
		}
		return flag;
	}
}
