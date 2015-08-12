package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl;

import java.util.List;
import java.util.Map;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.AbstractCacheListCmp;

/**
 * 查询字段以及值两次都是完全一致
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public class SameFieldsAndValuesList extends AbstractCacheListCmp implements IBuildReportDataListByCondition{
	/**
	 * 先去缓存中去 获取不到再重新查询
	 * 查询检索数据,然后把检索到的数据保存到缓存中
	 */
	public List<DataReportSumModel> getFilterListModel(String[] fields, String[] values, String country,@SuppressWarnings("rawtypes") Map map,String oper) {
		//直接缓存中获取
		element= cache.get(country+fields+values);
		//缓存中有值
		if (element!=null) {
			return (List<DataReportSumModel>) element.getObjectValue();
		}
		//缓存中没有值
		else{
			//初始化数据 并且放到缓存中
			return initListAndSaveCache(fields,values,country,map,"第一次检索数据,正在帮您汇总数据中...");
		}
	}

}
