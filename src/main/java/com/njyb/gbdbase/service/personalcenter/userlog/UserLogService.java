package com.njyb.gbdbase.service.personalcenter.userlog;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.login.IUserLoginDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.login.UserLoginlogModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.usermanagement.IUserSonBaseOperService;
/**
 * 用户日志Service
 * @author WangBo
 * 2015年3月26日
 * UserLogService.java
 */
@Service
public class UserLogService implements IUserLogService {
	
	//用户日志DAO层
	@Autowired
	private IUserLoginDao userLogDao;
	
	//用户Service
	@Autowired
	private IUserSonBaseOperService userSonBaseOperService;
	
	/**
	 * 根据用户Id查询用户日志
	 */
	@Override
	public List<UserLoginlogModel> queryUserLogModelByUserModel(UserModel userModel) {
		// 非空判断
		List<UserLoginlogModel> userLogList = Lists.newArrayList();
		if (null != userModel) {
			//切换数据库
			DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
			userLogList = userLogDao.queryUserLogResultByUserIdAndPage(userModel.getUserId());
		}
		return userLogList;
	}
	
	/**
	 * 根据名称查询UserModel
	 */
	@Override
	public UserModel queryUserModelByLoginName(String loginName) {
		QueryModel queryModel = new QueryModel();
		queryModel.setUpdateSql(loginName);
		UserModel userModel = userSonBaseOperService.queryUserByLoginName(queryModel);
		return userModel;
	}

	/**
	 * 用户日志
	 */
	@Override
	public int queryUserLogCountByParam(UserModel userModel) {
		int result = 0;
		Map<String,Object> paramMap = Maps.newHashMap();
		if (null != userModel) {
			paramMap.put("userId", userModel.getUserId());
			result = userLogDao.queryUserLogCountByParam(paramMap);
		}
		return result;
	}

	/**
	 * 根据UserLoginlogModel 查询出相关数据
	 */
	@Override
	public List<UserLoginlogModel> queryLogResultByLoginModel(
			Map<String, Object> paramMap) {
		List<UserLoginlogModel> resultList = Lists.newArrayList();
		if (null != paramMap) {
			resultList = userLogDao.getLogByLoginName(paramMap);
		}
		return resultList;
	}
	
	/**
	 * 数据分页
	 * @param request
	 * @return page
	 * @auther honghao
	 */
	public PageBeanUtil getPageBeanUtil(HttpServletRequest request,String num){
		int pageIndex = Integer.valueOf((null == request.getParameter("page"))?"1":(request.getParameter("page")));
		int pageSize = Integer.valueOf((null == request.getParameter("rows"))?(num):(request.getParameter("rows")));
		PageBeanUtil beanUtil = new PageBeanUtil();
		beanUtil.setPageIndex(pageIndex);
		beanUtil.setPageSize(pageSize);
		return beanUtil;
	}
	
	/**
	 * 分页起始位置id
	 * @param beanUtil
	 * @return
	 *	@auther honghao
	 */
	public int getStartIndex(PageBeanUtil beanUtil)
	{
		int start = (beanUtil.getPageIndex()-1)*beanUtil.getPageSize();
		return start;
	}
	
	/**
	 * 分页结束位置id
	 * @param beanUtil
	 * @param list
	 * @param start
	 * @return
	 *	@auther honghao
	 */
	public int getEndIndex(PageBeanUtil beanUtil,List<?> list,int start) {
		int end = beanUtil.getPageIndex()*beanUtil.getPageSize();
        if(list == null || list.size() == 0)
        {
        	end = 0;
        }else if(list.size()>=start && list.size()<=end)
        {
        	end = list.size();
        }
        return end;
	}
}