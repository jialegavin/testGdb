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
 * 创建子工厂的工厂
 * @author 贾红平
 *
 */
public class CreateFactory {
	/**
	 * 选择子类实现的对应工厂
	 * @param falg 创建参数化工厂方法的参数
	 * @return
	 */
	public static AbstractBuildListFactory createListFactory(String falg) {
		{
			AbstractBuildListFactory factory = null;
			if (falg.equals(ParamEnumUtil.same.name())) {
				factory = new SameFieldsAndValuesListFactory();
			} else if (falg.equals(ParamEnumUtil.containhscode.name())) {
				factory = new SameDateContainHscodeListFactory();
			} else if (falg.equals(ParamEnumUtil.nocontainhscode.name())) {
				factory = new SameDateNHscodeListFactory();
			} else {
				factory = new RepeatSearchDataListFactory();
			}
			return factory;
		}
	}
}
