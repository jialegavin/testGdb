package com.njyb.gbdbase.service.common.componet.report;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.KeyValueModel;
import com.njyb.gbdbase.service.common.componet.report.componet.chagemap.IListChangeMapFormat;
import com.njyb.gbdbase.service.common.componet.report.componet.father.AbstractReportCmp;
import com.njyb.gbdbase.service.common.componet.report.sumary.IReportDataSummary;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;

/**
 * 报表汇总公用的类
 * 
 * @author 贾红平
 *
 */
@Component
public class ReportSumaryCmp extends AbstractReportCmp{
	//转换map组件
	@Autowired
	private IListChangeMapFormat changeMapFormat;
	//获取集合对象组件
	@Autowired
	private IReportDataSummary dataSummary;
	private static boolean isQuery = true;
	/**
	 * 获取汇总之和的集合模型对象
	 * 
	 * @param request
	 *            封装请求参数
	 * @param fields
	 *            查询字段
	 * @param values
	 *            查询字段的值
	 * @param country
	 *            查询的国家
	 * @param type
	 *            报告分类标识:参考ParanEnumUtil类
	 * @param map
	 *            reportConfigField存储的所有信息
	 * @param module
	 *            当前模块名称
	 * @return
	 */
	public List<DataReportSumModel> getSumListModel(HttpServletRequest request,
			String[] fields, String[] values, String country, String type,
			@SuppressWarnings("rawtypes") Map map, String module,
			boolean isShowAll) {
		
		
		// 缓存对象
		List<DataReportSumModel>  dataList = dataSummary.getSumListModel(fields,values, country, map);//69700;
		
		
//		System.out.println("每次去查询最底层数据：" + dataList.size());
		List<DataReportSumModel> list=null;
		if (dataList!=null && dataList.size()>0) {
			//把list集合转换成map集合
			Map<String, List<DataReportSumModel>> mlp = changeMapFormat.changeListForMap(dataList, type, map, isShowAll);
			//把map集合再重新转换成list汇总的集合
			list=super.getDataReportSumModels(mlp, type, country, map);		
		}
		return list;
	}
	
}
