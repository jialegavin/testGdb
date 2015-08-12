package com.njyb.gbdbase.service.contrastreport.contrast;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.contrastreport.querybean.CommonSearchModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
/**
 * 所有对比service
 * @author 章华才
 */
public interface ICompareService{

	/**
	 * 新增进出口商
	 * @param request 
	 * @param field	             查询lucenc用到的字段名称
	 * @param values    查询字段的值
	 * @param model		查询用到的查询bean
	 * @param sortKey	排序字段
	 * @return list<T>
	 */
	public <T> List<DataReportSumModel> addImportAndExport(HttpServletRequest request,String [] field,String [] values,
			                         CommonSearchModel model,String sortKey,String countryName,String isAdd,String imexType);
	
	
	/**
	 * 保持进出口商
	 * @param request 
	 * @param field	             查询lucenc用到的字段名称
	 * @param values    查询字段的值
	 * @param model		查询用到的查询bean
	 * @param sortKey	排序字段
	 * @return list<T>
	 */
	public <T> Map<String,DataReportSumModel> keepImporterAndExporter(HttpServletRequest request,String [] field,String [] values,
            						CommonSearchModel model,String sortKey,String countryName,String isAdd,String imexType);
	
	
	
	/**
	 * 流失进出口商
	 * @param request 
	 * @param field	             查询lucenc用到的字段名称
	 * @param values    查询字段的值
	 * @param model		查询用到的查询bean
	 * @param sortKey	排序字段
	 * @return list<T>
	 */
	public <T> List<DataReportSumModel> outflowImportAndExport(HttpServletRequest request,String [] field,String [] values,
						CommonSearchModel model,String sortKey,String countryName,String isAdd,String imexType);
}
