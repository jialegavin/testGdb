package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory;

import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl.SameDateNotContainHscodeList;
/**
 * 日期相同且不包含海关编码
 * @author 贾红平
 *
 */
public class SameDateNHscodeListFactory extends AbstractBuildListFactory {
	@Override
	public IBuildReportDataListByCondition createInstance() {
		return new SameDateNotContainHscodeList();
	}

}
