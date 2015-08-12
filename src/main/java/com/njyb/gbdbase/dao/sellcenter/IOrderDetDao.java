package com.njyb.gbdbase.dao.sellcenter;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.sellcenter.UserOrderDetModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;

/**
 * 订单明细DAO层
 * @author chenhu
 * 2015年5月7日
 */
@MyBatisReposity
public interface IOrderDetDao {
    /**
     * 添加用户订单明细
     * @param orderDet
     */
	void addNewOrderDet(UserOrderDetModel orderDet);
    /**
     * 删除订单明细
     * @param id
     */
	void deleteOrderDetByNo(String orderNo);
	/**
	 * 根据订单号查询订单明细
	 * @param query
	 * @return
	 */
	List<UserOrderDetModel> queryOrderDetByNo(String orderNo);
	/**
	 * 根据订单号查询订单明细数量
	 * @param orderNo
	 * @return
	 */
	int querySumDetByNo(String orderNo);
}
