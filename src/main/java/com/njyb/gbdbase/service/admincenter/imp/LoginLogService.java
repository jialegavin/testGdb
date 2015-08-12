package com.njyb.gbdbase.service.admincenter.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.login.IUserLoginDao;
import com.njyb.gbdbase.model.login.UserLoginlogModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.ILoginLogService;

/**
 * 登陆日志查询
 * @author chenhu
 * 2015年3月27日
 */
@Service
public class LoginLogService implements ILoginLogService {

	@Autowired
	private IUserLoginDao userLogDao;
	
	private static final Logger log=Logger.getLogger(LoginLogService.class);
	@Override
	public List<UserLoginlogModel> queryUserLogModelForPaging(QueryModel qury) {
		List<UserLoginlogModel>  list=new ArrayList<UserLoginlogModel>();
		try{
			if((qury!=null)&&(qury.getCurPage()!=0)&&(qury.getPageSize()!=0))
			{
				 Map map=new HashMap();
				 map.put("curpage", qury.getStartnum());
				 map.put("pagesize", qury.getPageSize());
				list =userLogDao.getAllUserLoginLog(map);
			}
			
		}catch(Exception e)
		{
			log.debug(e.toString());
		}
		return list;
	}

	@Override
	public List<UserLoginlogModel> queryUserLogModelByUserIdForPaging(
			QueryModel query) {
		List<UserLoginlogModel>  list=new ArrayList<UserLoginlogModel>();
		try{
			if((query!=null)&&(query.getCurPage()!=0)&&(query.getPageSize()!=0)&&(query.getUserId()!=0))
			{
				 Map map=new HashMap();
				 map.put("curpage", query.getStartnum());
				 map.put("pagesize", query.getPageSize());
				list =userLogDao.getAllUserLoginLog(map);
			}
			
		}catch(Exception e)
		{
			log.debug(e.toString());
		}
		return list;
	}

	@Override
	public int  queryCountUserLogModelByUserId(
			QueryModel query) {
	     int count=0;
	     try{
	    	 if((query!=null)&&(query.getUserId()!=0))
	    	 {
	    		 count=userLogDao.queryCountUserLogByUserId(query.getUserId());
	    	 }
	     }catch(Exception e)
	     {
	    	 log.debug(e.toString());
	     }
		return count;
	}

	@Override
	public int queryCountUserLogModel(QueryModel query) {
		int count=0;
		try{
			count=userLogDao.findLoginLogCount();
		}catch(Exception e)
		{
			log.debug(e.toString());
		}
		
		return count;
	}
  
}
