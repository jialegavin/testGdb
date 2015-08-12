package com.njyb.gbdbase.service.common.componet.report.componet.chagemap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Component;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.context.CacheStateContext;
import com.njyb.gbdbase.service.common.componet.report.componet.father.AbstractReportCmp;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;
/**
 * 封装map组件 并且存放到缓存中
 * @author 贾红平
 *
 */
@SuppressWarnings("unused")
@Component
public class ListChangeMapFormat extends AbstractReportCmp implements
		IListChangeMapFormat {

	/**
	 * 获取map 存放到缓存中
	 */
	@Override
	public Map<String, List<DataReportSumModel>> changeListForMap(
			List<DataReportSumModel> ds, String type, @SuppressWarnings("rawtypes") Map map, boolean isShowAll) {
			Map<String, List<DataReportSumModel>>rmp=null;
			if(type.startsWith("drill")){
				rmp=super.getDataReportSumMap(ds, type.split("&")[1], map, isShowAll);
			}
			else{
				rmp=super.getDataReportSumMap(ds, type, map, isShowAll);
			}
			/*把本次查询结果缓存起来*/
			CacheStateContext.createStateInstance(getStateType(map, type)).handleCacheState(type, rmp);
			return rmp;
	}
	/*报告类型获取对应状态处理的标志*/
	private static String getStateType(@SuppressWarnings("rawtypes") Map map,String type){
		/*后期通过配置文件判断*/
		if (type.equals("jkscompare_first")||type.equals("jkscompare_second")
				||type.equals("ckscompare_first")||type.equals("ckscompare_second")
				||type.equals("companycompare_first")||type.equals("companycompare_second")) {	
			return "addoff";
		}
		else if (type.startsWith("drill")) {
			return "drill";
		}
		else{
			return "sumary";
		}
	}
}
