package com.njyb.gbdbase.dao.personalcenter.alipay;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.personalcenter.alipay.AliPalyFilingModel;

/**
 * 支付处理Dao
 * @author WangBo
 * 2015年5月14日
 * IPayHandleDao.java
 */
@MyBatisReposity
public interface IPayHandleDao {
	
	/**
	 * 保存支付备案
	 * @param aliPalyFilingModel
	 * @return
	 */
	public int addAliPalyFiling(AliPalyFilingModel aliPalyFilingModel);
}