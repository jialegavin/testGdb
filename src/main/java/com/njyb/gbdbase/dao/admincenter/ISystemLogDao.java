package com.njyb.gbdbase.dao.admincenter;

import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.admincenter.UserSystemLogModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
/**
 * 查询系统日志列表
 * @author chenhu
 * 2015年3月27日
 */
@MyBatisReposity
public interface ISystemLogDao {
    /**
     *  分页查询系统日志列表
     * @param curPage
     * @param pageSize
     * @return
     */
	List<UserSystemLogModel> querySystemLogsForPaging(QueryModel query);
    /**
     * 根据日志Id查询系统日志的具体内容
     * @param logId
     * @return
     */
	List<UserSystemLogModel>  querySystemLogByLogId(QueryModel query);
	/**
	 * 根据用的Id分页查询用户的日志列表
	 * @param curPage
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	List<UserSystemLogModel> querySystemLogsByUserIdForPaging(QueryModel query);
	/**
	 * 根据用户ID查询用户日志总数
	 * @param query
	 * @return
	 */
	int querySysLogCountByUserId(QueryModel query);
	

}
