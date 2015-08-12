package com.njyb.gbdbase.service.common.componet.report.componet.chagemap;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 数据格式转换成map机构
 * @author 贾红平
 *
 */
public interface IListChangeMapFormat {
	/**
	 * 作为一个桥接组件 封装一下map
	 * @param ds
	 * @param type
	 * @param map
	 * @param isShowAll
	 * @return
	 */
	Map<String, List<DataReportSumModel>>changeListForMap(List<DataReportSumModel> ds,String type,Map map,boolean isShowAll);
	
}
