package com.njyb.gbdbase.dao.contrastreport.customercenter;

import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.contrastreport.customercenter.CustomerCenterModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;


/**
 * 客服中心Dao
 * @author 章华才
 *
 */
@MyBatisReposity
public interface ICustomerCenterDao {
	
	/**
	 * 插入用户留言信息
	 * @param model
	 */
	void insertCentent(CustomerCenterModel model);
	
	/**
	 * 查询所有留言用户
	 */
	List<CustomerCenterModel> queryCentents();
	
	/**
	 * 删除指定的留言信息
	 * @param ids id编号
	 */
	void deleteCentent(SqlModel model); 
	
	/**
	 * 根据id查询
	 * @param model
	 * @return
	 */
	List<CustomerCenterModel> queryCententByid(SqlModel model);
	
}
