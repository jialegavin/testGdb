package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl.SameDateContainHscodeList;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl.SameDateNotContainHscodeList;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl.SameFieldsAndValuesList;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
/**
 * 选择子类工厂
 * @author 贾红平
 *
 */
public abstract class AbstractBuildListFactory {
	/**
	 * 选择子类实现
	 * @param falg
	 * @return
	 */
	public abstract IBuildReportDataListByCondition createInstance();
	
	public List<DataReportSumModel> getFilterListModel(String[] fields,
			String[] values, String country, Map map,String oper){
		return createInstance().getFilterListModel(fields, values, country, map,oper);
	}
}
