package com.njyb.gbdbase.service.admincenter.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.admincenter.ISystemLogDao;
import com.njyb.gbdbase.model.admincenter.UserSystemLogModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.ISearchLogService;

/**
 * 用户系统日志查询服务类
 * @author chenhu
 * 2015年3月27日
 */
@Service
public class SearchLogService implements ISearchLogService {
    @Autowired
    private ISystemLogDao systemLogDao;
    
    private static final Logger log=Logger.getLogger(SearchLogService.class);
	@Override
	public List<UserSystemLogModel> querySystemLogListForPaging(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		List<UserSystemLogModel> list=new ArrayList<UserSystemLogModel>();
		if((query!=null)&&(query.getCurPage()!=0)&&(query.getPageSize()!=0))
		{
			list=systemLogDao.querySystemLogsForPaging(query);
		}
		return list;
	}


	@Override
	public List<UserSystemLogModel> querySystemLogListByUserIdForPaging(
			QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		List<UserSystemLogModel> list=new ArrayList<UserSystemLogModel>();
		if((query!=null)&&(query.getCurPage()!=0)&&(query.getPageSize()!=0)&&(query.getUserId()!=0))
		{
			list=systemLogDao.querySystemLogsByUserIdForPaging(query);
		}
		return list;
	}


	@Override
	public int querySysLogCountByUserId(QueryModel query) {
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		int count=0;
		try{
			count=systemLogDao.querySysLogCountByUserId(query);
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return count;
	}


	@Override
	public List<UserSystemLogModel> querySystemLogByLogId(QueryModel query) {
		// TODO Auto-generated method stub
		return null;
	}

}
