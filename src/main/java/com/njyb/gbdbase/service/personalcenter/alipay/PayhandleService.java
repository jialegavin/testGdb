package com.njyb.gbdbase.service.personalcenter.alipay;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbase.dao.personalcenter.alipay.IPayHandleDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.personalcenter.alipay.AliPalyFilingModel;
import com.njyb.gbdbase.model.personalcenter.email.MailModel;
import com.njyb.gbdbase.service.personalcenter.email.IEmailService;
import com.njyb.gbdbase.service.sellcenter.IOrderService;
import com.njyb.gbdbase.service.sellcenter.ISellCenterService;
/**
 * 支付完成后
 * 更改权限
 * 发送邮件
 * 保存备案,
 * @author WangBo
 * 2015年5月14日
 * PayhandleService.java
 */
@Service
public class PayhandleService implements IPayhandleService {
	
	//log记录日志
	private static final Logger log = Logger.getLogger(PayhandleService.class);

	//订单service层
	@Autowired
	private IOrderService orderService;
	
	// 邮件
	@Autowired
	private IEmailService emailService;
	
	//订单状态更改接口
	@Autowired
	private ISellCenterService sellCenterService;
	
	//支付处理Dao
	@Autowired
	private IPayHandleDao payHandleDao;
	
	/**
	 * 根据支付条件处理 1更改权限 2发送邮件 3保存备案<br>
	 * 1.未支付  2.支付成功  3. 支付失败  4. 支付超时
	 */
	@Override
	public int handlePayByModel(AliPalyFilingModel aliPalyFilingModel) {
		try {
			if (null != aliPalyFilingModel) {
				//请在这里加上商户的业务逻辑程序代码
				if(aliPalyFilingModel.isVerify_result()){			//验证成功
					//请在这里加上商户的业务逻辑程序代码
					//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					UserModel userModel = sellCenterService.queryUserByOrderNo(aliPalyFilingModel.getOut_trade_no());
					MailModel mailModel = getEmailModel(userModel);		//邮件model
					Map<String,Object> emailParamMap = Maps.newHashMap();
					emailParamMap.put("userName", userModel.getUserName());
					emailParamMap.put("buyTime", DataUtil.parseDate(new Date(), 7));			//购买时间
					emailParamMap.put("orderNum", aliPalyFilingModel.getOut_trade_no());
					aliPalyFilingModel.setAliPalyTime(new Date().toString());
					//订单号
					//支付成功
					if(aliPalyFilingModel.getTrade_status().equals("TRADE_FINISHED") || aliPalyFilingModel.getTrade_status().equals("TRADE_SUCCESS")){
						//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
						// 传入订单号 ,更改订单状态
						boolean state = sellCenterService.conferRight(aliPalyFilingModel.getOut_trade_no());
						if (state) { 		// 传入订单号,获取当前购买的model
							// TODO 给当前购买用户 发送邮件,提醒购买成功
							log.info("用户Id: " + userModel.getUserId() + " 用户名: " + userModel.getUserName() + "状态: 支付成功!");
							emailParamMap.put("stateText", "已付款生效!");
							// 保存支付备案		1 : 支付成功
							aliPalyFilingModel.setTrade_status("1");
							payHandleDao.addAliPalyFiling(aliPalyFilingModel);
						}
					// 支付失败
			 		} else {
			 			log.info("用户Id: " + userModel.getUserId() + " 用户名: " + userModel.getUserName() + "状态: 支付失败!");
			 			emailParamMap.put("stateText", "未付款!");
			 			// 因   支付失败  和  支付超时 造成的统称为 支付失败
			 			// 发送邮件提醒 支付失败,请重新支付
			 			aliPalyFilingModel.setTrade_status("2");		// 2 支付失败
						payHandleDao.addAliPalyFiling(aliPalyFilingModel);
			 		}
					emailService.sendAttachMail(mailModel,emailParamMap,"payToEemind.html");		//发送邮件
			 	}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug(e.toString());
		}
		return 0;
	}
	
	/**
	 * 异步支付<br>
	 * 根据支付条件处理<br>
	 * 更改权限<br>
	 * 发送邮件<br>
	 * 保存备案,
	 * @param aliPalyFilingModel
	 * @return
	 */
	@Override
	public int handleNotifyPaly(AliPalyFilingModel aliPalyFilingModel,String stateType) {
		try {
			if (null != aliPalyFilingModel) {
				UserModel userModel = sellCenterService.queryUserByOrderNo(aliPalyFilingModel.getOut_trade_no());
				MailModel mailModel = getEmailModel(userModel);		//邮件model
				Map<String,Object> emailParamMap = Maps.newHashMap();
				emailParamMap.put("userName", userModel.getUserName());
				emailParamMap.put("buyTime", DataUtil.parseDate(new Date(), 7));			//购买时间
				emailParamMap.put("orderNum", aliPalyFilingModel.getOut_trade_no());
				aliPalyFilingModel.setAliPalyTime(new Date().toString());
				//订单号
				//支付成功
				if(aliPalyFilingModel.getTrade_status().equals(stateType)){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					// 传入订单号 ,更改订单状态
					boolean state = sellCenterService.conferRight(aliPalyFilingModel.getOut_trade_no());
					if (state) { 		// 传入订单号,获取当前购买的model
						// TODO 给当前购买用户 发送邮件,提醒购买成功
						log.info("用户Id: " + userModel.getUserId() + " 用户名: " + userModel.getUserName() + "状态: 支付成功!");
						emailParamMap.put("stateText", "已付款生效!");
						// 保存支付备案		1 : 支付成功
						aliPalyFilingModel.setTrade_status("1");
						payHandleDao.addAliPalyFiling(aliPalyFilingModel);
					}
				// 支付失败
		 		} else {
		 			log.info("用户Id: " + userModel.getUserId() + " 用户名: " + userModel.getUserName() + "状态: 支付失败!");
		 			emailParamMap.put("stateText", "未付款!");
		 			// 因   支付失败  和  支付超时 造成的统称为 支付失败
		 			// 发送邮件提醒 支付失败,请重新支付
		 			aliPalyFilingModel.setTrade_status("2");		// 2 支付失败
					payHandleDao.addAliPalyFiling(aliPalyFilingModel);
		 		}
				emailService.sendAttachMail(mailModel,emailParamMap,"payToEemind.html");		//发送邮件
			}
		} catch (Exception e) {
			log.debug(e.toString());
		}
		return 0;
	}
	
	/**
	 * 邮箱Model生成
	 * @param userModel
	 * @return
	 */
	private MailModel getEmailModel(UserModel userModel){
		MailModel mailModel = new MailModel();
		mailModel.setSubject("亲爱的"+userModel.getUserName()+"，您在英蓓/趣易购买的产品已成功付款");
		mailModel.setToAddreress(userModel.getEmail());
		return mailModel;
	}
}