package com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
/**
 * 设计一个缓存状态父类F
 * @author jiahp
 *
 */
public class CommonCache {
	/*存储所有报告对应的map*/
	protected static Map<String, Map<String, List<DataReportSumModel>>>sumMap=new HashMap<String, Map<String,List<DataReportSumModel>>>();
	/*存储新增流失报告对应的map*/
	protected static Map<String, Map<String, List<DataReportSumModel>>>addoffMap=new HashMap<String, Map<String,List<DataReportSumModel>>>();
	/*存储所有报告钻取对应的map*/
	protected static Map<String, Map<String, List<DataReportSumModel>>>drillMap=new HashMap<String, Map<String,List<DataReportSumModel>>>();
	/*缓存*/
	protected Cache cache=null;
	/*缓存中的元素*/
	protected Element element=null;
}
