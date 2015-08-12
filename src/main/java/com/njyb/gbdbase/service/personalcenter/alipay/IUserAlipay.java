package com.njyb.gbdbase.service.personalcenter.alipay;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付宝接口
 * @author WangBo
 * 2015年3月31日
 * IUserAlipay.java
 */
public interface IUserAlipay {
	
	/**
	 * 用户点击支付，必须要有以下参数
	 * 
	 * @param out_trade_no
	 *            ：支付订单号 (如果传过来的id为INTEGER类型，可以 xx.toString()) 强转即可
	 *            但要保证id的真实性{不重复}
	 * @param total_fee
	 *            ： 支付价格
	 * @result : Map<String,String> map
	 * 
	 *         log4j 打入错误日志，空参数，造成支付失败。
	 * @return 如果返回值为null 产品ID 有被人修改嫌疑
	 * ----------------------------------------------------------------------
	 * 根据当前项目情况,只有 金额 和 ID
	 */
	public Map<String, String> userToPaly(String out_trade_no, double total_fee);

	/**
	 * 初始化配置文件
	 * @param request
	 */
	public void initAlipayParam(HttpServletRequest request);
}