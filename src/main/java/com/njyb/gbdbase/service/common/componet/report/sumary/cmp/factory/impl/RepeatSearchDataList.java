package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.AbstractCacheListCmp;

/**
 * 各种情况导致 需要重新检索数据
 * @author 贾红平
 *
 */
public class RepeatSearchDataList extends AbstractCacheListCmp implements IBuildReportDataListByCondition{
	@Override
	public List<DataReportSumModel> getFilterListModel(String[] fields, String[] values, String country,@SuppressWarnings("rawtypes") Map map,String oper) {
			//初始化数据 并且放到缓存中
			return initListAndSaveCache(fields, values, country, map,"");
	}
}
