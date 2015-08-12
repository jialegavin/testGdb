package com.njyb.auth.util;

import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njyb.auth.dao.cmp.IOrderAuthDao;
import com.njyb.auth.service.CommonAuthService;
import com.njyb.gbdbase.model.admincenter.UserCountModel;
import com.njyb.gbdbase.model.login.CommonLogoModel;
import com.njyb.gbdbase.model.personalcenter.email.MailModel;
import com.njyb.gbdbase.service.personalcenter.email.EmailService;

/**
 * 按次用户  taskJob 类
 * @author WangBo
 * 2015年6月24日
 * UserCountTaskUtil.java
 */
public class UserCountTaskUtil {
	
	private static final Logger log = Logger.getLogger(UserCountTaskUtil.class);  
	
	// 按次用户dao层
	@Autowired
	private IOrderAuthDao orderCountDao;
	
	// 邮件 服务接口
	@Autowired
	private EmailService emailService;
	
	/**
	 * 在某个时间段发送邮件
	 * 1. 模版 单列
	 * 2. 
	 */
	public void sendAllUserEmail(){
		List<UserCountModel> userCountResult = this.queryUserCountList();
		if (null != userCountResult && !userCountResult.isEmpty()) {
			for (UserCountModel userCountModel : userCountResult) {
				//公用信息(组织model)
				CommonLogoModel c = this.getCommonLogoModel(userCountModel);
				// TODO 模版
				MailModel mailModel = getMailModel(userCountModel,c);
				Map<String,Object> paramMap = getEmailMap(userCountModel,c);
				log.info("send Email :" + mailModel.getToAddreress());
				System.out.println("send Email :" + mailModel.getToAddreress());
				emailService.sendAttachMail(mailModel,paramMap , "massSendEmail.html",c);
			}
		}
	}
	
	/**
	 * 组织 model
	 * @param userCountModel
	 * @return
	 */
	private CommonLogoModel getCommonLogoModel(UserCountModel userCountModel) {
		CommonLogoModel logoModel = new CommonLogoModel();
		if (userCountModel.getDoMain().contains("trade-easy")) {
			logoModel.setDomain(userCountModel.getDoMain());	
			logoModel.setComanyName("南通趣易信息技术有限公司");
			logoModel.setTel("0513-85333684");
			logoModel.setCompanyEmail("TradeEasy@163.com");
			logoModel.setEmailPwd("TradeEasy123");
		} else if (userCountModel.getDoMain().contains("ybdb")) {
			logoModel.setDomain(userCountModel.getDoMain());	
			logoModel.setComanyName("南京英蓓信息技术有限公司");
			logoModel.setTel("025-66684219");
			logoModel.setCompanyEmail("nanjinginfobase@vip.163.com");
			logoModel.setEmailPwd("infobase");
		}
		return logoModel;
	}
	
	/**
	 * 获取内容对象
	 * @param userCountModel
	 * @return
	 */
	private Map<String,Object> getEmailMap(UserCountModel userCountModel,CommonLogoModel c) {
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userCountModel.getLoginName());		// 登录名称
		paramMap.put("totalNum", userCountModel.getTotalNum());			// 总次数
		paramMap.put("remainNum", userCountModel.getRemainNum());		// 使用次数
		paramMap.put("residueNum", userCountModel.getResidueNum());		// 剩余次数
		paramMap.put("consumptionMoney", userCountModel.getConsumptionMoney());		//消费金额 
		paramMap.put("residueMoney", userCountModel.getResidueMoney());		// 剩余金额
		paramMap.put("context_logoimg", c.getComanyName().equals("南京英蓓信息技术有限公司")?"yb_logo.png":"logo.png");
		paramMap.put("context_phoneimg", c.getComanyName().equals("南京英蓓信息技术有限公司")?"yb_emailPhone.png":"emailPhone.png");
		paramMap.put("context_mainbg", c.getComanyName().equals("南京英蓓信息技术有限公司")?"yb_emailMid.png":"emailMid.png");
		paramMap.put("context_bottombg", c.getComanyName().equals("南京英蓓信息技术有限公司")?"yb_emailBottom.png":"emailBottom.png");
		paramMap.put("updateUrl", c.getDomain());
		paramMap.put("companyTitle", c.getComanyName().equals("南京英蓓信息技术有限公司")?"英蓓":"趣易");
		paramMap.put("onlineTel", c.getComanyName().equals("南京英蓓信息技术有限公司")?c.getTel():"400-1816-008");
		paramMap.put("domain", c.getDomain());
		return paramMap;
	}
	
	/**
	 * 获取邮件对象
	 * @param userCountModel
	 * @return
	 */
	private MailModel getMailModel(UserCountModel userCountModel,CommonLogoModel c){
		MailModel mailModel = new MailModel();
		mailModel.setSubject("国际商业智能化数据信息平台");
		mailModel.setToAddreress(userCountModel.getEmail());
		mailModel.setFromAddress(c.getCompanyEmail());
		return mailModel;
	}
	
	/**
	 * 获取 按次的 所有用户
	 * @return List<UserCountModel>
	 */
	private List<UserCountModel> queryUserCountList(){
		List<UserCountModel> userCountList = Lists.newArrayList();
		try {//
			Map<String,Object> paramMap = Maps.newHashMap();
			paramMap.put("look_price", CommonAuthService.LOOK_PRICE);
			paramMap.put("type", CommonAuthService.COUNT_USER);
			userCountList = orderCountDao.queryUserConsuming(paramMap);
		} catch (Exception e) {
			System.out.println(e);
			log.debug(e);
			log.info(e);
		}
		return userCountList;
	}
}
