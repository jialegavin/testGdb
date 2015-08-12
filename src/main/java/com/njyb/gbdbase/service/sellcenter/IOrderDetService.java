package com.njyb.gbdbase.service.sellcenter;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.sellcenter.UserOrderDetModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;

/**
 * 订单明细
 * @author chenhu
 * 2015年5月7日
 */
public interface IOrderDetService {
   /**
    * 根据购物车里的订单信息
    * 和订单号添加订单明细
    * @param orderDets
    * @param orderNo
    */
	void addNewOrderDets(List<Map<String, Object>> orderDets, String orderNo);
   /**
    * 删除订单明细
    * @param orderId
    * @return
    */
   boolean deleteOrderDetByNo(String orderNo);
   /**
    * 根据订单编号查询订单明细列表
    * @param orderNo
    * @param query
    * @return
    */
   List<UserOrderDetModel> queryOrderDetByNo(String orderNo);
	/**
	 * 根据订单编号查询订单明细的数量
	 * @param orderNo
	 * @return
	 */
	int querySumOrderDets(String orderNo);

}
