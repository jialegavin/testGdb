package com.njyb.gbdbase.service.sellcenter.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.njyb.gbdbase.dao.authcenter.ICountRightDao;
import com.njyb.gbdbase.dao.usermanagement.IRightDao;
import com.njyb.gbdbase.dao.usermanagement.IViewDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.authcenter.CountRightModel;
import com.njyb.gbdbase.model.sellcenter.ReceiverInfoModel;
import com.njyb.gbdbase.model.sellcenter.UserOrderDetModel;
import com.njyb.gbdbase.model.sellcenter.UserOrderModel;
import com.njyb.gbdbase.model.usermanagement.ViewModel;
import com.njyb.gbdbase.service.sellcenter.IOrderDetService;
import com.njyb.gbdbase.service.sellcenter.IOrderService;
import com.njyb.gbdbase.service.sellcenter.IReceiverInfoSerice;
import com.njyb.gbdbase.service.sellcenter.ISellCenterService;
/**
 *定制中心业务层具体实现
 * @author chenhu
 * 2015年5月5日
 */
@Service
public class SellCenterServiceImp implements ISellCenterService {
    @Autowired
	private IViewDao viewDao;
    @Autowired
    private IReceiverInfoSerice userInfoService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetService orderDetService;
    @Autowired
    private IRightDao rightDao;
    @Autowired
    private ICountRightDao countDao;
    @Autowired
    private IUserDao userDao;
	private static final Logger log=Logger.getLogger(SellCenterServiceImp.class);
	@Override
	public List<Map<String,String>> queryViewByName(String type,String lang) {
	   List<Map<String,String>> list=new ArrayList<Map<String,String>>(); 
		try{
			if(!("".equals(type))&&!("".equals(lang)))
			{
				ViewModel view=new ViewModel();
				view.setType(type);
				view.setLanguage(lang);
				 list=viewDao.queryViewByTL(view);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
		return list;
	}
	@Override
	public String addNewOder(List<Map<String, Object>> orderDets, UserModel user) {
		try{
			 //首先根据用户ID查询出用户的定制信息
			if(user!=null&&user.getUserId()!=0){
				 //查询订单默认用户地址
				ReceiverInfoModel userInfo=userInfoService.queryUserInfoById(user.getUserId());
				 //添加订单信息
			      String  orderNo=orderService.addNewOrder(orderDets,userInfo,user);
			     //添加订单明细
			      orderDetService.addNewOrderDets(orderDets,orderNo);
			      //返回订单号
			      return orderNo;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
		return null;
	}
	@Override
	public boolean conferRight(String orderNo) {
		try{
			//1、修改订单支付状态
			  UserOrderModel order=orderService.queryOrderInfoByNo(orderNo);
			  if(order!=null){
				  order.setOrderStatus(IConstantUtil.ORDER_STATUS2);
				  orderService.upOrderInfo(order);
			  }
			//2、根据订单号获取订单明细
			  List<UserOrderDetModel> dets=orderDetService.queryOrderDetByNo(orderNo);
			//3、根据订单明细类型授权
			    if(dets!=null&&dets.size()>0){
			    	for(UserOrderDetModel det : dets){
                        //权限分为两大类 1.次数、2条件
			    		Map<String,Object> map=new HashMap<String,Object>();
			    		if("按次数定制".equals(det.getByType())){
			    			//修改用户的使用次数1、查询用户的使用次数 2、修改用户的使用次数
			    			UserModel user=new UserModel();
			    			user.setUserId(order.getUserId());
			    			CountRightModel countRight=countDao.queryCountRightModelById(user);
			    			if(countRight!=null){
			    				//如果不为空则更新
			    				int totalNum=countRight.getTotalNum()+Integer.parseInt(det.getTimes());
			    				int remainNum=countRight.getRemainnum()+Integer.parseInt(det.getTimes());
			    				countRight.setTotalNum(totalNum);
			    				countRight.setRemainnum(remainNum);
			    				countDao.updateCountRight(countRight);
			    			}else{
			    				//如果为空则添加
			    				countRight=new CountRightModel();
			    				countRight.setRemainnum(Integer.parseInt(det.getTimes()));
			    				countRight.setTotalNum(Integer.parseInt(det.getTimes()));
			    				countRight.setUserId(order.getUserId());
			    				countDao.addNewCountRight(countRight);
			    			}
			    		}else
			    		{
			    			//获取时间范围
			    			String time=det.getTlimit();
			    			String[] times= time.split("/");
			    			 map.put("userId", order.getUserId());
			    			 map.put("startTime", times[0]);
			    			 map.put("endTime", times[1]);
			    			 map.put("iExportType", det.getIoType());
			    			 map.put("byCountry", det.getCountry());
			    			 //根据定制类型的不同，向表中插入不同的数据
			    			 if("按HSCODE定制".equals(det.getByType())){
				    			  map.put("byHsCode", det.getHscode());
				    			  map.put("byProductDesc", "");
				    			  map.put("rightType", "2");
				    		  }else if("按产品描述定制".equals(det.getByType())){
				    			  map.put("byHsCode", "");
				    			  map.put("byProductDesc",det.getProdesc() );
				    			  map.put("rightType", "2");
				    		  }else if("按国家定制".equals(det.getByType())){
				    			  map.put("byHsCode", "");
				    			  map.put("byProductDesc","" );
				    			  map.put("rightType", "1");
				    		  }
			    			 rightDao.addRight(map);
			    		}
			    	}
			    }
			    return true;
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
		return false;
	}
	@Override
	public UserModel queryUserByOrderNo(String orderNo) {
		try{
			//根据订单号查询订单
			  UserOrderModel order=orderService.queryOrderInfoByNo(orderNo);
				  if(order!=null&&order.getUserId()!=0){
					  //跟订单里的userId查询该订单对应的用户
					  UserModel user=userDao.queryUserByID(order.getUserId());
					  return user;
				  }
		}catch(Exception e){
				  e.printStackTrace();
					log.debug(e.toString());
			  }
		return null;
	}
	@Override
	public List<Map<String, String>> queryCascdeMenu(String lang, String type,
			String real) {
		List<Map<String, String>> list=new ArrayList<Map<String, String>>();
		try{
			ViewModel view=new ViewModel();
			view.setType(type);
			view.setLanguage(lang);
			view.setRela(real);
			list=this.viewDao.queryViewByTLR(view);
		}catch(Exception e){
			  e.printStackTrace();
				log.debug(e.toString());
		}
		return list;
	}
	@Override
	public List<Map<String, String>> queryCountryMenu(String lang, String type,
			String code) {
		List<Map<String, String>> list=new ArrayList<Map<String, String>>();
		try{
			ViewModel view=new ViewModel();
			view.setType(type);
			view.setLanguage(lang);
			view.setCode(code);
			list=this.viewDao.queryViewByTLC(view);
		}catch(Exception e){
			  e.printStackTrace();
				log.debug(e.toString());
		}
		return list;
	}
}
