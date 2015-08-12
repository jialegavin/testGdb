package com.njyb.gbdbase.service.personalcenter.alipay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbase.model.personalcenter.alipay.AliPalyEntity;

/**
 * 支付容错率检测类
 * @author WangBo
 * 2015年3月30日
 * UserAlipay.java
 */
@Service
public class UserAlipay implements IUserAlipay{
	private Logger logger = Logger.getLogger(UserAlipay.class);
	
	private String seller_email;		// 此账号是南京英蓓账号,如果更换了账号,请在这里修改
	
	private AliPalyEntity play = new AliPalyEntity();
	
	/**
	 * 初始化配置文件
	 */
	public void initAlipayParam(HttpServletRequest request){
		new LoadPropertiesUtil().init(request, IConstantUtil.ALIPAYFIELD);
		seller_email = LoadPropertiesUtil.getValue("seller_email");
		// 服务器异步通知页面路径
		play.setNotify_url(LoadPropertiesUtil.getValue("notify_url"));
		//页面跳转同步通知页面路径
		play.setReturn_url(LoadPropertiesUtil.getValue("return_url"));
		//支付配置文件
		AlipayConfig.key = LoadPropertiesUtil.getValue("key");				//配置key,不要动!!!
		AlipayConfig.partner = LoadPropertiesUtil.getValue("partner");
		AlipayConfig.log_path = LoadPropertiesUtil.getValue("log_path");
		AlipayConfig.input_charset = LoadPropertiesUtil.getValue("input_charset");
	}
	
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
	public Map<String, String> userToPaly(String out_trade_no, double total_fee) {
		Map<String, String> map = null;
		// 如果都为true
		if (checkObj(out_trade_no) && checkObj(total_fee)) {
			// 业务逻辑代码
			// 将支付的属性封装成对象
			// 验证ID 有效性
			if (checkStringId(out_trade_no))
				play.setOut_trade_no(out_trade_no);
			//1. 根据订单号更改状态 {注意: 如果订单号存在,更改状态,如果不存在,插入一次,更改状态}
			//2. 获取支付人的user
			else {
				// 不通过效验
				logger.info("ID号出现异常");
				logger.debug("ID号出现异常");
				return null;
			}
			play.setSeller_email(seller_email);
			// 再次判断金额是否有效
			if (checkDoubleNum(total_fee))
				play.setTotal_fee(Double.toString(total_fee));
			else {
				// 金额小于0,则不让支付
				logger.debug("金额为0,出现了异常!!! ");
				logger.info("金额为0,出现了异常!!! ");
				return null;
			}
			play.setBody("data");
			play.setSubject("data alipay");
			// 设置支付方式
			play.setPayment_type("1");
			// 放入容器中
			MapConfigMapper mapConfigMapper = new MapConfigMapper();
			logger.info("支付信息:用户id" + play.getOut_trade_no() + "金额:"
					+ play.getTotal_fee());
			logger.debug("支付信息:用户id" + play.getOut_trade_no() + "金额:"
					+ play.getTotal_fee());
			mapConfigMapper.setPlayMap(play);
			map = mapConfigMapper.getSParaTemp();
		} else
			logger.debug("打入错误日志，空参数，造成支付失败!!!!!!!");
		return map;
	}

	/**
	 * 对传入参数 非空验证
	 * 
	 * @return true ： 有效 false ： 无效
	 */
	private boolean checkObj(Object obj) {
		boolean result = false;
		if (obj != null)
			result = true;
		return result;
	}

	/**
	 * 判断金额有效性 如果金额 < 0 终止交易
	 * 
	 * @param total_fee
	 *            : 金额
	 * @return true || false
	 */
	private boolean checkDoubleNum(double total_fee) {
		boolean result = true;
		if (total_fee < 0) {
			logger.info("金额数目发生了异常 当前时间" + new Date());
			result = false;
		}
		return result;
	}

	/**
	 * 对产品id 进行验证,
	 * @param out_trade_no : 产品ID
	 * @return
	 */
	private boolean checkStringId(String out_trade_no) {
		boolean result = false;
		// 判断订单号长度是否合法
		if (out_trade_no.length() == 10) {
			// 验证订单合法性
			if (out_trade_no.indexOf("10") != -1) {
				String dateStr = DataUtil.parseDate(new Date(), 17);
				String [] resultDate = dateStr.split("-");
				String year = resultDate[0].substring(2,resultDate[0].length());	//获取当前年
				String month = resultDate[1];				//获取当期月
				if (out_trade_no.indexOf(year) != -1 && out_trade_no.indexOf(month) != -1) {
					logger.info("ID第二次效验通过");
					result = true;
				} else {
					logger.info("订单ID有被人为修改的嫌疑!!!!!");
					logger.debug("严重异常!!!!");
					result = false;
				}
			}
		}
		return result;
	}

	/**
	 * 获取时间格式化转换器
	 * 
	 * @return SimpleDateFormat : 转换器
	 */
	private SimpleDateFormat getSimpleDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * ID字符效验器
	 * 
	 * @param dateResult
	 *            : 时间格式的字符串
	 * @return false || true
	 */
	private boolean checkDateMarger(String dateResult) {
		boolean result = false;
		// 进行封装时间格式的字符串
		String year = dateResult.substring(0, 4);
		String month = dateResult.substring(4, 6);
		String day = dateResult.substring(6, 8);
		String newString = year + "-" + month + "-" + day;
		try {
			// 如果ID有真实性,则可通过,否则ID失效
			String date = this.getSimpleDateFormat().parse(newString)
					.toString();
			logger.info("ID通过效验!!!!" + date);
			result = true;
		} catch (ParseException e) {
			logger.debug("时间转换错误,有异常");
			result = false;
		}
		return result;
	}
}