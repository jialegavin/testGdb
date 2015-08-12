package com.njyb.gbdbase.service.common.componet.report.build;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.i18n.Exception;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.njyb.gbdbas.util.ArrayUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.service.common.componet.report.ReportSumaryCmp;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;

/**
 * 生成报表所需的数据格式
 * @author 贾红平
 *
 */
@Component
@SuppressWarnings("all")
public class BuildReportData extends ReportSumaryCmp  implements IBuildReportData{
	@Autowired 
	private ReportPropertiesModel reportPropertiesModel;
	private String country;
	private String typename;
	private HttpServletRequest request;
	private String[] fields;
	private String[] values;
	private Map<String, String>map;
	private String moduleName;
	/**
	 * 初始化参数
	 * @param model
	 */
	private void initReportParam(ReportCommonParamModel  model){
		country=model.getCountry();
		typename=model.getReportType();
		request=model.getRequest();
		fields=model.getFeilds();
		values=model.getValues();
		map=reportPropertiesModel.getReportFieldMap();
		moduleName=model.getModule();
	}
	/**
	 * 生成map集合的数据格式
	 */
	@Override
	public Map<String,  List<DataReportSumModel>> builderMapList(ReportCommonParamModel  commonParamModel,boolean isShowAll){
		// 全库请求不需要request
		Object new_type = null;
		HttpServletRequest request = RequestMap.getRequestMap().getRequest();
		if (null != request) {
			new_type = request.getAttribute("rep_type");
		}
		initReportParam(commonParamModel);
		Map<String, List<DataReportSumModel>>mps=new HashMap<String, List<DataReportSumModel>>();
		List<DataReportSumModel>list=null;
		//多个报告 zg_jks
		if (typename.contains("_")&&!typename.startsWith("zg")&&!typename.equals("goods_desc")) {
			String types=map.get(country+"_"+typename).toString();
			if (null != new_type) {		// 获取报告类型
				String request_type = new_type.toString();
				list = super.getSumListModel(request, fields, values, country,request_type,map,moduleName,isShowAll);
				if (list!=null) {
					mps.put(request_type, list);
				}
			} else {
				//正常查询
				for(String type:types.split(",")){
					//每次保证请求一次 ：controller请求几次这里就请求几次
					list=super.getSumListModel(request, fields, values, country,type,map,moduleName,isShowAll);
					if (list!=null) {// qs jks cks
						mps.put(type, list);
					}
					else{
						System.out.println("根据您输入的条件没有查询到任何关于"+type+"的结果集");
					}
				}
			}
		}
		//单一报告
		else{
			list=super.getSumListModel(request, fields, values, country,typename,map,moduleName,isShowAll);
			if (list!=null) {
				mps.put(typename, list);
			}
			else{
				System.out.println("根据您输入的条件没有查询到任何关于"+typename+"的结果集");
			}
		}
		return mps;
	}
	/**
	 * 生成单一集合 
	 */
	@Override
	public List<DataReportSumModel> builderSingleList(
			ReportCommonParamModel reportCommonParamModel, boolean isShowAll) {
		initReportParam(reportCommonParamModel);
		List<DataReportSumModel>list=super.getSumListModel(request, fields, values, country,typename,map,moduleName,isShowAll);
		return list;
	}
}
