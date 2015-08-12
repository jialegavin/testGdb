package com.njyb.gbdbase.dao.alldb.competitor;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.ComAndCusInfoResultModel;

/**
 * 竞争对手<br>
 * 客户信息<br>
 * Dao层
 * @author WangBo
 * 2015年4月7日
 * ICompetitorDao.java
 */
@MyBatisReposity
public interface ICompetitorAndCustomerInfoDao {
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
	 * @param paramMap
	 * @return
	 */
	int deleteCompetitorAndCustomerInfo(Map<String,Object> paramMap);
	
	/**
	 * 竞争对手<br>
	 * 添加方法
	 * @param competitorAndCustomerInfoResultModel
	 * @return 1 : 成功 2 : 失败
	 */
	int addCompetitor(ComAndCusInfoResultModel competitorAndCustomerInfoResultModel);
	
	/**
	 * 客户信息<br>
	 * 添加方法
	 * @param competitorAndCustomerInfoResultModel
	 * @return
	 */
	int addCustomerInfo(ComAndCusInfoResultModel competitorAndCustomerInfoResultModel);
	
	/**
	 * 竞争对手<br>
	 * 修改方法
	 * @param competitorAndCustomerInfoResultModel
	 * @return
	 */
	int updateCompetitor(ComAndCusInfoResultModel competitorAndCustomerInfoResultModel);
	
	/**
	 * 客户信息<br>
	 * 修改方法
	 * @param competitorAndCustomerInfoResultModel
	 * @return
	 */
	int updateCustomerInfo(ComAndCusInfoResultModel competitorAndCustomerInfoResultModel);
}
