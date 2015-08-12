package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory;

import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl.SameFieldsAndValuesList;
/**
 * 条件和值都完全一致的工厂
 * @author 贾红平
 *
 */
public class SameFieldsAndValuesListFactory extends AbstractBuildListFactory{
	@Override
	public IBuildReportDataListByCondition createInstance() {
		return new SameFieldsAndValuesList();
	}

}
