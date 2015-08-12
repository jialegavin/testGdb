package com.njyb.gbdbase.controller.personalcenter.alipay;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbas.util.alipay.AlipaySubmit;
import com.njyb.gbdbase.model.personalcenter.alipay.AliPalyFilingModel;
import com.njyb.gbdbase.service.personalcenter.alipay.IPayhandleService;
import com.njyb.gbdbase.service.personalcenter.alipay.IUserAlipay;

/**
 * 支付宝接口<br>
 * 数据入口<br>
 * @author WangBo
 * 2015年3月30日
 * UserPlayController.java
 */
@Controller
@RequestMapping("/alipay")
public class UserPlayController {
	//支付参数容器
	private Map<String,String> mapConfig;
	
	@Autowired
	private IUserAlipay userAlipay;		//支付接口
	
	@Autowired
	private IPayhandleService payhandleService;		//支付后处理

	/**
	 * 用户支付接口
	 * @param response
	 * @param request 
	 * @param out_trade_no ：支付订单号 (如果传过来的id为INTEGER类型，可以 xx.toString()) 强转即可
	 *            但要保证id的真实性{不重复}
	 * @param total_fee ： 支付价格 {总价格}
	 * @return "aliplyErrer" : "alipayapi"
	 */
	@RequestMapping("/submitPlay")
	public String submitPlay(HttpServletResponse response,HttpServletRequest request,String out_trade_no,double total_fee){
		userAlipay.initAlipayParam(request);	//初始化配置文件
		mapConfig = userAlipay.userToPaly(out_trade_no, total_fee);	//获取参数配置map 
		String sHtmlText = AlipaySubmit.buildRequest(mapConfig,"get","确认");	//建立请求，以表单HTML形式构造（默认）
		request.setAttribute("sHtmlText", sHtmlText);
		return null == mapConfig ? "personalcenter/alipay/aliplyErrer" : "personalcenter/alipay/alipayapi";
	}
	
	/**
	 * 接受支付宝的返回条件,修改用户权限,邮件提醒,保存备案
	 * @param response
	 * @param request
	 * @param alipalyFiling
	 * @return 返回系统主页
	 * @throws IOException 
	 */
	@RequestMapping("/playForm")
	public String verifyPalyState(HttpServletResponse response,HttpServletRequest request,
			AliPalyFilingModel alipalyFiling) throws IOException{
		int result = payhandleService.handlePayByModel(alipalyFiling);
		response.sendRedirect("www.ybdb.net");
		return null;
	}
}