package com.njyb.gbdbase.service.sellcenter;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.sellcenter.ReceiverInfoModel;
import com.njyb.gbdbase.model.sellcenter.UserOrderModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;

/**
 * 订单service层
 * @author chenhu
 * 2015年5月7日
 */
public interface IOrderService {
   /**
    * 添加新的订单
    * @param orderDets
    * @param uid
    */
    String addNewOrder(List<Map<String, Object>> orderDets, ReceiverInfoModel userInfo,UserModel user);
   /**
    * 根据订单号查询订单
    * @param orderNo
    * @return
    */
   UserOrderModel queryOrderInfoByNo(String orderNo);
   /**
    * 修改订单信息
    * @param order
    */
   void upOrderInfo(UserOrderModel order);
	/**
	 * 根据用户ID查询订单列表
	 * @param user
	 * @return
	 */
	List<Map<String, String>> queryEffectOrdersByUserId(UserModel user,QueryModel query);
	/**
	 * 查询有效的订单号数量
	 * @param user
	 * @return
	 */
	int querySumEffectOrders(UserModel user);
	/**
	 * 根据订单Id删除订单
	 * @param orderId
	 * @return
	 */
	boolean deleteOrderByNo(String orderNo);
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
