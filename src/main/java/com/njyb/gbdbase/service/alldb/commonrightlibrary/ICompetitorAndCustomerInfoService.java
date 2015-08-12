package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.ComAndCusInfoResultModel;

/**
 * 竞争对手<br>
 * 客户信息<br>
 * 服务接口<br>
 * @author WangBo
 * 2015年4月7日
 * CompetitorService.java
 */
public interface ICompetitorAndCustomerInfoService {
	
	/**
	 * 竞争对手<br>
	 * 客户信息<br>
	 * 共用查询方法
	 * @param paramMap : Map
	 * @return : List<CompetitorAndCustomerInfoResultModel>
	 */
	List<ComAndCusInfoResultModel> queryCompetitorResult(Map<String,Object> paramMap);
	
	/**
	 * 竞争对手<br>
	 * 客户信息<br>
	 * 共用删除方法
	 * @param competitorAndCustomerInfoResultModel
	 * @return
	 */
	int deleteCompetitorAndCustomerInfo(ComAndCusInfoResultModel competitorAndCustomerInfoResultModel);
	
	/**
	 * 竞争对手<br>
	 * 客户信息<br>
	 * 共用添加方法
	 * @param competitorAndCustomerInfoResultModel
	 * @return 1 : 成功 2 : 失败
	 */
	int addCompetitorAndCustomerInfo(ComAndCusInfoResultModel competitorAndCustomerInfoResultModel);
	
	/**
	 * 竞争对手<br>
	 * 客户信息<br>
	 * 共用修改方法
	 * @param competitorAndCustomerInfoResultModel
	 * @return
	 */
	int updateCompetitorAndCustomerInfo(ComAndCusInfoResultModel competitorAndCustomerInfoResultModel);
}