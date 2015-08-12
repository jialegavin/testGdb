package com.njyb.gbdbase.service.alldb.competitor;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.ICommonMarketAnalysisService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.ICommonTradingRecordService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.ICompetitorAndCustomerInfoService;
/**
 * 竞争对手服务接口
 * @author WangBo
 * 2015年4月7日
 * CompetitorService.java
 */
public interface ICompetitorService extends ICompetitorAndCustomerInfoService,ICommonMarketAnalysisService<DataReportSumModel>,ICommonTradingRecordService<AllDBModel> {
}