package com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.context.CommonCache;

/**
 * 报表深度挖取缓存状态处理
 * 
 * @author jiahp
 *
 */
public class DrillReportCacheState extends CommonCache implements IHandleCacheState {
	@SuppressWarnings("static-access")
	public void handleCacheState(String type,Map<String, List<DataReportSumModel>> rmp) {
		try {
			drillMap.put(type.split("&")[1], rmp);
			cache = CreateEncache.getEacheInstance().getCache("drillReportMap");
			cache.acquireWriteLockOnKey("drillReport");
			element = new Element("drillReport", drillMap);
			cache.put(element);
		} finally {
			cache.releaseWriteLockOnKey("drillReport");
		}
	}
}
