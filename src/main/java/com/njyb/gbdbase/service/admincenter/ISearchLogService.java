package com.njyb.gbdbase.service.admincenter;

import java.util.List;

import com.njyb.gbdbase.model.admincenter.UserSystemLogModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;

/**
 * 查询日志管理
 * @author hu
 */
public interface ISearchLogService {
	/**
	 * 分页查询用户查询日志列表
	 * @param query
	 * @return
	 */
    List<UserSystemLogModel>   querySystemLogListForPaging(QueryModel query);
    /**
     * 根据用户ID分页查询用户系统日志列表
     * @param query
     * @return
     */
    List<UserSystemLogModel>   querySystemLogListByUserIdForPaging(QueryModel query);
    /**
     * 根据用户ID分页查询用户系统日志列表
     * @param query
     * @return
     */
    List<UserSystemLogModel>   querySystemLogByLogId(QueryModel query);
    /**
     * 根据用户ID获取用的查询日志数量
     * @param query
     * @return
     */
    int querySysLogCountByUserId(QueryModel query);
    
}
