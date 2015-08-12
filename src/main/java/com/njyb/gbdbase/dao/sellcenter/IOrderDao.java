package com.njyb.gbdbase.dao.sellcenter;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.sellcenter.UserOrderModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;

/**
 * 订单DAO层
 * @author chenhu
 * 2015年5月7日
 */
@MyBatisReposity
public interface IOrderDao {
    /**
     * 新增订单
     * @param order
     */
	void addNewOrder(UserOrderModel order);
     /**
      * 根据订单号查询订单
      * @param userId
      * @param orderNo
      * @return
      */
	UserOrderModel queryOrderInfoByNo(String orderNo);
	/**
	 * 修改订单
	 * @param order
	 */
	void upOrderInfo(UserOrderModel order);
	/**
	 * 根据用户ID查询订单列表
	 * @param userId
	 * @return
	 */
	List<Map<String, String>> queryEffectOrdersByUserId(QueryModel query);
	/**
	 * 查询有效的订单号数量
	 * @param userId
	 * @return
	 */
	int querySumEffectOrders(int userId);
	/**
	 *  根据订单Id删除订单
	 * @param orderId
	 */
	void deleteOrderByNo(String orderNo);
	/**
	 * 根据订单号，分页模糊查询订单
	 * @param query
	 * @return
	 */
	List<Map<String, String>> queryOrderByBlurNo(QueryModel query);
	/**
	 * 根据订单号，模糊查询订单的数量
	 * @param query
	 * @return
	 */
	int querySumOrderByBlurNo(QueryModel query);
}
