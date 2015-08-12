package com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.context.CommonCache;

/**
 * 新增流失报告缓存状态处理
 * @author jiahp
 *
 */
public class AddOffReportCacheState extends CommonCache implements IHandleCacheState {
	/*存储新增流失报告对应的map*/
	protected static Map<String, Map<String, List<DataReportSumModel>>>addoffMap=new HashMap<String, Map<String,List<DataReportSumModel>>>();
	/* (non-Javadoc)
	 * @see com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.IHandleCacheState#handleCacheState(java.lang.String, java.util.Map)
	 */
	@Override
	@SuppressWarnings("static-access")
	public void handleCacheState(String type,Map<String, List<DataReportSumModel>>rmp){
		try{
			String[]str=type.split("_");
			addoffMap.put(str[1], rmp);
			element=new Element("addoffReport", addoffMap);
			cache=CreateEncache.getEacheInstance().getCache("addoffReportMap");
			cache.acquireWriteLockOnKey("addoffReport");
			cache.put(element);
		}
		finally{
			cache.releaseWriteLockOnKey("addoffReport");
		}
	}
}
