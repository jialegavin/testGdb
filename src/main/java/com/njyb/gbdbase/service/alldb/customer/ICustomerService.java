package com.njyb.gbdbase.service.alldb.customer;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.ICommonMarketAnalysisService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.ICommonTradingRecordService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.ICompetitorAndCustomerInfoService;
/**
 * 客户信息<br>
 * @author WangBo
 * 2015年4月8日
 * ICustomerService.java
 */
public interface ICustomerService extends ICompetitorAndCustomerInfoService, ICommonMarketAnalysisService<DataReportSumModel>,ICommonTradingRecordService<AllDBModel>{
	
}