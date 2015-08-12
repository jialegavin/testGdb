package com.njyb.gbdbase.service.contrastreport.customercenter;

import java.util.List;

import com.njyb.gbdbase.model.contrastreport.customercenter.CustomerCenterModel;

/**
 * 用户留言service
 * @author 章华才
 */
public interface ICustomerCenterService {
	/**
	 * 插入用户留言信息
	 * @param model
	 */
	void addCentent(CustomerCenterModel model);
	
	/**
	 * 查询所有留言用户
	 */
	List<CustomerCenterModel> queryCentents();
	
	/**
	 * 删除指定的留言信息
	 * @param ids id编号
	 */
	void deleteCentent(List<Integer> ids); 
	
	/**
	 * 根据id查询
	 * @param ids
	 * @return
	 */
	List<CustomerCenterModel> queryCententByid(List<Integer> ids);
}
