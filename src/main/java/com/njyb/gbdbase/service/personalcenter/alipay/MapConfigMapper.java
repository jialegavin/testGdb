package com.njyb.gbdbase.service.personalcenter.alipay;

import java.util.HashMap;
import java.util.Map;

import com.njyb.gbdbase.model.personalcenter.alipay.AliPalyEntity;


/**
 * 支付宝封装容器<br>
 * 不可动!!
 * @author WangBo
 * 2015年3月30日
 * MapConfigMapper.java
 */
public class MapConfigMapper {

	private Map<String, String> sParaTemp = new HashMap<String,String>();

	/**
	 * 封装容器
	 * @param play
	 */
	public void setPlayMap(AliPalyEntity play) {
		//把请求参数打包成MAP
		sParaTemp.put("service", "create_direct_pay_by_user");
	    sParaTemp.put("partner", AlipayConfig.partner);
	    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", play.getPayment_type());
		sParaTemp.put("notify_url", play.getNotify_url());
		sParaTemp.put("return_url", play.getReturn_url());
		sParaTemp.put("seller_email", play.getSeller_email());
		sParaTemp.put("out_trade_no", play.getOut_trade_no());
		sParaTemp.put("subject", play.getSubject());
		sParaTemp.put("total_fee", play.getTotal_fee());
		sParaTemp.put("body", play.getBody());
		sParaTemp.put("show_url", play.getShow_url());
		sParaTemp.put("anti_phishing_key", play.getAnti_phishing_key());
		sParaTemp.put("exter_invoke_ip", play.getExter_invoke_ip());
	}
	
	/**
	 * get Map 支付容器
	 * @return sParaTemp
	 */
	public Map<String, String> getSParaTemp(){
		return sParaTemp;
	}
}