package com.njyb.gbdbase.service.common.componet.report.build;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
/**
 * 生成客户端需要的数据格式的结构
 * @author 贾红平
 *
 */
public interface IBuildReportData {
	/**
	 * 返回Map结构的数据格式:Map<报告类型,集合对象>
	 * @param reportCommonParamModel 查询模型
	 * @param isShowAll 是否汇总全部数据
	 * @return
	 */
	public abstract Map<String, List<DataReportSumModel>> builderMapList(ReportCommonParamModel reportCommonParamModel, boolean isShowAll);

	/**
	 * 生成单一的集合对象
	 * @param reportCommonParamModel 查询模型
	 * @param isShowAll 是否汇总全部数据
	 * @return
	 */
	public abstract List<DataReportSumModel> builderSingleList(ReportCommonParamModel reportCommonParamModel, boolean isShowAll);


}