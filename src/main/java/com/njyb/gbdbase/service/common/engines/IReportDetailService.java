package com.njyb.gbdbase.service.common.engines;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
/**
 * 报表业务接口
 * @author 贾红平
 *
 */
public interface IReportDetailService {
	/**
	 * 报表汇总对应的详情接口
	 * @param  value:要查看明细字段的值
	 * @return
	 */
	public abstract  List<DataReportSumModel> builderSingleList(String value,String type,String country);
	/**
	 * 汇总对应的明细
	 * @param value
	 * @param type 报告类型
	 * @return
	 */
	public abstract  List<DataReportSumModel> buildReportSumList(String value,String type,String country);
}
