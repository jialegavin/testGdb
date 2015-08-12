package com.njyb.gbdbase.service.common.componet.report.sumary.cmp;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
/**
 * 生成报表之前的模型:通过各种情况的可能 进行二次过滤 避免重复检索数据
 * @author 贾红平
 *
 */
public interface IBuildReportDataListByCondition {
	/**
	 * 过滤数据核心接口
	 */
	public List<DataReportSumModel> getFilterListModel(String[] fields,String[] values, String country, Map map,String oper) ;
}
