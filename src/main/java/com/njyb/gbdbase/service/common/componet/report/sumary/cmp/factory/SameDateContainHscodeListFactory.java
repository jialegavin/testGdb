package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory;

import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.IBuildReportDataListByCondition;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.factory.impl.SameDateContainHscodeList;
/**
 * 日期相同且包含海关编码工厂
 * @author 贾红平
 *
 */
public class SameDateContainHscodeListFactory extends AbstractBuildListFactory{
	@Override
	public IBuildReportDataListByCondition createInstance() {
		return new SameDateContainHscodeList();
	}

}
