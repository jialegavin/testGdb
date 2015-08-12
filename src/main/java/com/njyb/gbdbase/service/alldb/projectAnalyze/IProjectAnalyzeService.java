package com.njyb.gbdbase.service.alldb.projectAnalyze;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.alldb.projectAnalyze.BuyerModel;
import com.njyb.gbdbase.model.alldb.projectAnalyze.ProjectAnalyzeModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.ICommonMarketAnalysisService;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.ICommonTradingRecordService;


/**
 * 权库分析{产品分析}
 * @author WangBo
 * 2015年4月20日
 * IProjectAnalyzeService.java
 */
public interface IProjectAnalyzeService extends ICommonMarketAnalysisService<DataReportSumModel>,ICommonTradingRecordService<AllDBModel>{
	
	/**
	 * 添加产品标签
	 * @return
	 */
	public int addProjectAnalyze(ProjectAnalyzeModel projectAnalyzeModel);
	
	/**
	 * 删除产品标签
	 * @return
	 */
	public int deleteProjectAnalyze(ProjectAnalyzeModel projectAnalyzeModel);
	
	/**
	 * 根据条件查询产品标签
	 * @param paramMap
	 * @return
	 */
	public List<ProjectAnalyzeModel> queryProjectAnalyzeModelByParam(Map<String,Object> paramMap);
	
	/**
	 * 修改产品标签
	 * @param projectAnalyzeModel : 对象
	 * @return 
	 */
	public int updateProjectAnalyze(ProjectAnalyzeModel projectAnalyzeModel);
	
	/**
	 * 买家资源库
	 * @param paramMap
	 * @return
	 */
	public List<BuyerModel> queryBuyerList(Map<String,Object> paramMap);
	
	/**
	 * 竞争对手
	 * @param paramMap
	 * @return
	 */
	public List<DataReportSumModel> queryCompetitorByParam(Map<String,Object> paramMap);
}
