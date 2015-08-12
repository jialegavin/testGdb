package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory;

import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl.RepeatSearchDataList;
/**
 * 重新检索数据工厂
 * @author 贾红平
 *
 */
public class RepeatSearchDataListFactory extends AbstractBuildListFactory{
	@Override
	public IBuildReportDataListByCondition createInstance() {
		return new RepeatSearchDataList();
	}

}
