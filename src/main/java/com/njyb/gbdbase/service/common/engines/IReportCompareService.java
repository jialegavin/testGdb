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
public interface IReportCompareService {
	/**
	 * 生成map集合的数据格式
	 * @param request 封装请求参数对象
	 * @param fields  查询字段
	 * @param values  查询字段的值
	 * @param country 查询国家
	 * @param typename 报告类型分类
	 * @param isShowAll:false代表默认只显示前10条汇总记录  true:显示全部汇总记录
	 * @return
	 */
	public abstract  List<DataReportSumModel> builderSingleList(
			ReportCommonParamModel  searchCommonParamModel,boolean isShowAll);


}
