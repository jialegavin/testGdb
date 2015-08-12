package com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.context;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.AddOffReportCacheState;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.DrillReportCacheState;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.IHandleCacheState;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.SumaryReportCacheState;
/**
 * 缓存状态上下文f
 * @author jiahp
 *
 */
public class CacheStateContext {
	private static IHandleCacheState state=null;
	/*通过报表类型 选择具体的状态处理*/
	public static IHandleCacheState createStateInstance(String type){
		if (type.equals("addoff")) {
			state=new AddOffReportCacheState();
		}
		if (type.equals("drill")) {
			state=new DrillReportCacheState();
		}
		if (type.equals("sumary")) {
			state=new SumaryReportCacheState();
		}
		return state;
	}
	/*执行具体的缓存状态处理*/
	@SuppressWarnings("unused")
	private static  void handleCacheState(String state,String type,Map<String, List<DataReportSumModel>>rmp){
		createStateInstance(state).handleCacheState(type, rmp);
	}
}
