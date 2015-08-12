package com.njyb.auth.dao.cmp;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.admincenter.UserConsumingRecordsModel;

/**
 * 用户消费金额记录实体类
 * @author XL
 * @date 2015-06-25
 *
 */
@MyBatisReposity
public interface IUserConsumingRecordsDao {

	/**
	 * 添加消费金额记录
	 * @param userConsumingRecordsModel
	 */
	void addConsumingRecords(UserConsumingRecordsModel userConsumingRecordsModel);
	
	/**
	 * 根据用户名查询用户消费金额
	 * @param map
	 * @return List<UserConsumingRecordsModel>
	 */
	List<UserConsumingRecordsModel> getRecordsList(Map map);
}
