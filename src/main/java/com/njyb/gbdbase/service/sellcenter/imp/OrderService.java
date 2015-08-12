package com.njyb.gbdbase.service.sellcenter.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.alipay.UtilDate;
import com.njyb.gbdbase.dao.sellcenter.IOrderDao;
import com.njyb.gbdbase.dao.usermanagement.IViewDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.sellcenter.ReceiverInfoModel;
import com.njyb.gbdbase.model.sellcenter.UserOrderModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.sellcenter.IOrderService;
/**
 * 订单
 * @author chenhu
 * 2015年5月7日
 */
@Service
public class OrderService implements IOrderService {
	@Autowired
     private IViewDao viewDao;
	@Autowired
	private IOrderDao  orderDao;
	private static final Logger log=Logger.getLogger(OrderService.class);
	@Override
	public String addNewOrder(List<Map<String, Object>> orderDets,
			ReceiverInfoModel userInfo,UserModel user) {
		  //获取一个新的订单号
	      String newOrderNo=getNewOrderNo();
	      UserOrderModel order=new UserOrderModel();
	      order.setOrderNo(newOrderNo);
	      //如果用户个人信息不为空，则订单添加个人信息
	      if(userInfo!=null){
	    	  order.setRid(userInfo.getRid());
	      }
	      order.setUserId(user.getUserId());
	      //设置海关数据名称   暂时默认
	      order.setOrderName(IConstantUtil.ORDER_NAME);
	      double sum=0.0;//获取总价
	      if(orderDets!=null&&orderDets.size()>0){
	    	  order.setOrderNum(orderDets.size());
	    	  //循环获取价格
	    	  for(Map<String,Object> map :orderDets){
	    		  sum+=(double)map.get("price");
	    	  }
	      }
	      order.setOrderSummary(sum);
	      //设置初始化订单状态
	      order.setOrderStatus(IConstantUtil.ORDER_STATUS0);
	      orderDao.addNewOrder(order);
	      return newOrderNo;
	}
	@Override
	public UserOrderModel queryOrderInfoByNo(String orderNo){
		UserOrderModel orderModel=new UserOrderModel();
		try{
			//判断用户是否为空
			if(orderNo!=null&&!("".equals(orderNo))){
				orderModel=orderDao.queryOrderInfoByNo(orderNo);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
		return orderModel;
	}
    /**
     * 获取一个新的订单No 10 15 05 1001
     * 生成规则
     * 10:默认
     * 15:年的后两位
     * 05:月份
     * 1001:自增张序列
     * @return
     */
	private String getNewOrderNo(){
		StringBuffer sb=new StringBuffer();
		sb.append("10");
		//获取当前年的最后两位  
		String date=UtilDate.getDate().substring(2, 6);
		sb.append(date);
		//获取当前月
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("seqName", "orderno");
		map.put("result", "");
		viewDao.querySequenceByName(map);
		sb.append(map.get("result"));
		return sb.toString();
	}
	@Override
	public void upOrderInfo(UserOrderModel order) {
		   try{
			   if(order!=null){
                  //修改order的状态		
				   orderDao.upOrderInfo(order);
			   }
		   }catch(Exception e){
			   e.printStackTrace();
			   log.debug(e.toString());
		   }
	}
	@Override
	public List<Map<String, String>> queryEffectOrdersByUserId(UserModel user,QueryModel query) {
		try{
			List<Map<String, String>> list =new ArrayList<Map<String, String>>();
			 if(user!=null){
				 query.setUserId(user.getUserId());
				 list=orderDao.queryEffectOrdersByUserId(query);
				 return list;
			 }
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
			}
		
		return null;
	}
	@Override
	public int querySumEffectOrders(UserModel user) {
		 try{
			 int count=0;
			 if(user!=null){
				 count=orderDao.querySumEffectOrders(user.getUserId());
				 return count;
			 }
		 }catch(Exception e){
			 
		 }
		return 0;
	}
	@Override
	public boolean deleteOrderByNo(String orderNo) {
		try{
			if(!("".equals(orderNo))){
				orderDao.deleteOrderByNo(orderNo);
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
		return false;
	}
	@Override
	public List<Map<String, String>> queryOrderByBlurNo(QueryModel query) {
		try{
			List<Map<String, String>> list =new ArrayList<Map<String, String>>();
			query.setWhereSql(" uo.orderNo like '%"+query.getOrderNo()+"%'");
				 list=orderDao.queryOrderByBlurNo(query);
				 return list;
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
		return null;
	}
	@Override
	public int querySumOrderByBlurNo(QueryModel query) {
		try{
			 int count=0;
			 if(query!=null){
				 query.setWhereSql(" uo.orderNo like '%"+query.getOrderNo()+"%'");
				 count=orderDao.querySumOrderByBlurNo(query);
				 return count;
			 }
		 }catch(Exception e){
			 e.printStackTrace();
			log.debug(e.toString());
		 }
		return 0;
	}
}
