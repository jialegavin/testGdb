package com.njyb.service.paypal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.paypal.UserChargeModel;
import com.njyb.gbdbase.service.personalcenter.password.UserPasswordManageService;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
@Service
public class PayService implements IPayService{
	
	// 日志
		private static final Logger log = Logger
				.getLogger(UserPasswordManageService.class);
	
	
	@Autowired
	private IUserDao iUserDao;
	/**
	 * used for user charge 
	 * The time of user will be increased by model monthes, e.g. model=3, the time will increase 3 monthes.
	 * @param username
	 * @param model
	 * 
	 */
	@Override
	public boolean updateUserCharge(Payment payment, String loginName,int model)throws Exception{
		
	//	String beginTime="2015-04-23";
	//	String endTime="2015-06-23";
		String userDesc="金卡用户";
		int time=model;
		
		String endT=iUserDao.getEndTime(loginName);
		System.out.print(loginName+"\n");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");//写入时间
		Date date=new Date();
		Calendar beginTimeForC=Calendar.getInstance();
		String timeForNow=sf.format(date);
		beginTimeForC.setTime(sf.parse(timeForNow));
		String beginTime=sf.format(beginTimeForC.getTime());
		if(endT==null){
			beginTimeForC.add(Calendar.MONTH,time);
			String endTime=sf.format(beginTimeForC.getTime());
			iUserDao.updateUserTime( loginName,beginTime , endTime);
		}else
		{
			Calendar fromDatabase=Calendar.getInstance();
			fromDatabase.setTime(sf.parse(endT));
			if(beginTimeForC.after(fromDatabase)){
				beginTimeForC.add(Calendar.MONTH,time);
				String endTime=sf.format(beginTimeForC.getTime());
				iUserDao.updateUserTime( loginName,beginTime , endTime);
			}else{
				fromDatabase.add(Calendar.MONTH, time);
				String endTime=sf.format(fromDatabase.getTime());
				iUserDao.addUserTime( loginName, endTime);
			}
		}
		
	//	iUserDao.updateIsactivated("gavin");
		return false;
		
	}
	
	
	
	/**
	 * THe function to record User charge. The transaction information can be got from payment, and model can determine the time user bought. 
	 */
	@Override
	public boolean recordUserCharge(Payment payment,String loginName,int model) {
		UserChargeModel ucharge=new UserChargeModel();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");//写入时间
		Date date=new Date();
		String nowTime=sf.format(date);
		ucharge.setUsername(loginName);
		String id=payment.getId();
		String service=null;
		if(model==1) service="1m";
		else{
			if(model==3) service="3m";
			else{
				if(model==6) service="6m";
				else{
					if(model==9) service="9m";
					else 					service="error";

				}
					
					
					
			}
		}
		ucharge.setUsermode(service);
		ucharge.setDate(nowTime);
		ucharge.setPaymentID(id);
		iUserDao.saveUserCharge(ucharge);
		return true;
	}
	
	@Override
	public int checkTransactionModel(Payment payment) {
		// TODO Auto-generated method stub
		int model=0;
		Iterator<Transaction> iter=payment.getTransactions().iterator();
		while(iter.hasNext()){
		Transaction t1=iter.next();
	
		if(t1.getDescription().equalsIgnoreCase("TransactionsForInforvellor")){
			Amount am=t1.getAmount();
			String totle=am.getTotal();
			System.out.print(totle);
			if(totle.equals("359.00")) model=1;
			else{
				if(totle.equals("959.00")) model=3;
				else{
					if(totle.equals("1759.00")) model=6;
					else{
						if(totle.equals("3159")) model=12;
					}
				}
			}
		}
		break;
		}
		return model;
	}
}
