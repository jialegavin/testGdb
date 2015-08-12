package com.njyb.gbdbase.service.personalcenter.account;

import java.util.List;

import com.njyb.gbdbase.model.admincenter.UserConsumingRecordsModel;

/**
 * 用户账户管理业务接口层
 * @author XL
 * @date 2015-07-02
 *
 */
public interface IUserAccountManagerService {
	
	/**
	 * 分页查询用户账户消费记录
	 * @param curpage
	 * @param pagesize
	 * @param sql
	 * @return
	 */
	List<UserConsumingRecordsModel> getRecordsList(int curpage, int pagesize,String sql);

}
