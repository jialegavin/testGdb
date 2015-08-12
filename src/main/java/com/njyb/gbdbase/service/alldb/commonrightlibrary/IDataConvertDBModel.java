package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.List;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 多个国家转AllDBModel
 * @author WangBo
 * 2015年5月5日
 * IDataConvertDBModel.java
 */
public interface IDataConvertDBModel {

	/**
	 * 多个国家转AllDBModel
	 * @param country : 国家
	 * @param idList : idList
	 * @return List<AllDBModel>
	 */
	List<AllDBModel> convertAllDBModel(String country,List<Integer> idList);
	
	/**
	 * 市场分析功能<br>
	 * 根据国家和报告类型对数据进行整合 
	 * @param country : 国家
	 * @param reportType : 报告类型
	 * @return
	 */
	List<DataReportSumModel> convertDataByCountryAndReportType(List<DataReportSumModel> dataReportData,String [] country,String reportType);
}