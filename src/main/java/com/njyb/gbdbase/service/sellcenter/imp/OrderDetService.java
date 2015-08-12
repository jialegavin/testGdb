package com.njyb.gbdbase.service.sellcenter.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.dao.sellcenter.IOrderDetDao;
import com.njyb.gbdbase.model.sellcenter.UserOrderDetModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.sellcenter.IOrderDetService;
/**
 * 订单明细
 * @author chenhu
 * 2015年5月7日
 */
@Service
public class OrderDetService implements IOrderDetService {
    @Autowired
	private IOrderDetDao orderDetDao;
	private static Logger log=Logger.getLogger(OrderDetService.class);
	@Override
	public void addNewOrderDets(List<Map<String, Object>> orderDets,String orderNo) {
		try{
			if((orderDets!=null)&&(orderDets.size()>0)&&!("".equals(orderNo))){
               //循环插入订单明细表中
				for(Map<String,Object> map:orderDets){
					UserOrderDetModel orderDet=new UserOrderDetModel();
					orderDet.setByType(map.get("byType").toString());
					orderDet.setCountry(map.get("country").toString());
					orderDet.setHscode(map.get("hscode").toString());
					orderDet.setIoType(map.get("ioType").toString());
					orderDet.setOrderNo(orderNo);
					orderDet.setPrice((double)map.get("price"));
					orderDet.setProdesc(map.get("desc").toString());
					orderDet.setTimes(map.get("times").toString());
					orderDet.setTlimit(map.get("tlimit").toString());
					orderDetDao.addNewOrderDet(orderDet);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
       
	}
	@Override
	public boolean deleteOrderDetByNo(String orderNo) {
		try{
			if(!("".equals(orderNo))){
				orderDetDao.deleteOrderDetByNo(orderNo);
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
		return false;
	}
	@Override
	public List<UserOrderDetModel> queryOrderDetByNo(String  orderNo) {
		List<UserOrderDetModel> list =new ArrayList<UserOrderDetModel>();
		try{
			 if(!("".equals(orderNo))){
				 list=orderDetDao.queryOrderDetByNo(orderNo);
				 return list;
			 }
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
			}
		return list;
	}
	@Override
	public int querySumOrderDets(String orderNo) {
		try{
			 if(!("".equals(orderNo))){
				 int num=orderDetDao.querySumDetByNo(orderNo);
				 return num;
			 }
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
			}
		return 0;
	}

}
