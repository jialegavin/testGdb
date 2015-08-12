package com.njyb.gbdbase.service.personalcenter.alipay;

import com.njyb.gbdbase.model.personalcenter.alipay.AliPalyFilingModel;

/**
 * 支付完成后<br>
 * 更改权限<br>
 * 发送邮件<br>
 * 保存备案,
 * @author WangBo
 * 2015年5月14日
 * IPayhandleService.java
 */
public interface IPayhandleService {
	
	/**
	 * 同步支付<br>
	 * 根据支付条件处理<br>
	 * 更改权限<br>
	 * 发送邮件<br>
	 * 保存备案,
	 * @param aliPalyFilingModel : 备案
	 * @return int
	 */
	public int handlePayByModel(AliPalyFilingModel aliPalyFilingModel);
	
	/**
	 * 异步支付<br>
	 * 根据支付条件处理<br>
	 * 更改权限<br>
	 * 发送邮件<br>
	 * 保存备案,
	 * @param aliPalyFilingModel
	 * @return
	 */
	public int handleNotifyPaly(AliPalyFilingModel aliPalyFilingModel,String stateType);

}
