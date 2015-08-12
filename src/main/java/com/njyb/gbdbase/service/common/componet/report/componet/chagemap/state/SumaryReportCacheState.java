package com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.Element;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.context.CommonCache;

/**
 * 所有报告缓存的状态处理
 * @author user
 *
 */
public class SumaryReportCacheState extends CommonCache implements IHandleCacheState {
	@SuppressWarnings("static-access")
	public void handleCacheState(String type,Map<String, List<DataReportSumModel>> rmp) {
		try {
			sumMap.put(type, rmp);
			cache = CreateEncache.getEacheInstance().getCache("reportSumMap");
			cache.acquireWriteLockOnKey("reportMap");
			element = new Element("reportMap", sumMap);
			cache.put(element);
		} finally {
			cache.releaseWriteLockOnKey("reportMap");
		}
	}
}
