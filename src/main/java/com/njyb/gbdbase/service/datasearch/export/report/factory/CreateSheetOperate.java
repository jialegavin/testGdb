package com.njyb.gbdbase.service.datasearch.export.report.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbase.model.alldb.projectAnalyze.ProgressOfDownData;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ExportResultFieldModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;
import com.njyb.gbdbase.service.datasearch.export.report.IExportDataService;

/**
 * 根据条件创建不同的工作表
 * @author XL
 * @date 2015-04-02
 * @version 标准版
 */
@Component
public class CreateSheetOperate {

	@Resource
	private IDataSearchService dataSearchService;
	@Autowired
	private IExportDataService exportDataService;
	@Resource
	private ReportPropertiesModel reportPropertiesModel;
	@Resource
	private ExportResultFieldModel exportResultFieldModel;
	@Autowired
	private ICreateSheet createSheet;
	
	/**
	 * 创建工作表
	 * @param wb 工作簿
	 * @param country 国家
	 * @param reportTypeField 报告类型
	 * @param request
	 * @throws Exception 
	 */
	public void createSheet(WritableWorkbook wb, String country, String[] reportTypeField, HttpServletRequest request) throws Exception 
	{
		// 获取map对象
		Map reportFieldMap = reportPropertiesModel.getReportFieldMap();
		for(int i = 0;i <reportTypeField.length;i++)
		{
			// 用于进度条使用的参数  WangBo  2015/6/10  57line
			// TODO 线程不安全   {遗留bug}
			request.setAttribute("totalPro", reportTypeField.length);
			request.setAttribute("singleTemp", i);
			ProgressOfDownData.getProgressOfDownData().getDataSearchPro(request);
			
			// 汇总集合
			List<DataReportSumModel>  firstSummaryList = dataSearchService.getDataSummaryCacheValue(request, reportFieldMap, country, reportTypeField[i]);
			//获取导出报表excel所需的字段集合
			Map fieldMap = exportResultFieldModel.getExportResultFieldMap();
			//获取需要计算比率的字段
			String[] percentageFields = fieldMap.get(country + DataSearchConstantUtil.PERCENTAGE).toString().split(";");
			String[] commonField = percentageFields[0].split(",");
			String[] commonSummaryRatio = percentageFields[1].split(",");
			exportDataService.getDataPercent(firstSummaryList, commonField, commonSummaryRatio);
			Map map = new HashMap();
			//将汇总的集合放入map内
			map.put("summaryList", firstSummaryList);
			map.put("country", country);
			map.put("reportType",reportTypeField[i]);
			// 根据报告类型获取第二层钻取类型
//			String drillFiled = fieldMap.get(country+"_"+reportTypeField[i]+DataSearchConstantUtil.DRILLREPORTTYPE).toString();
			String drillFiled = null;
			//判断钻取字段是否为空
			if(null==drillFiled||"".equals(drillFiled))
			{
				// 如果获取钻取字段为空，则调用创建一层汇总报表的工作表方法
				createSheet.createNoDrillSheet(wb, map, false, i, reportTypeField.length, request);
			}
			else
			{
				// 如果获取钻取字段不为空，则调用创建带钻取汇总报表的工作表方法
				createSheet.createDrillSheet(wb, map, false, i, reportTypeField.length, request);
			}
		}
		// 用于进度条使用的参数  WangBo  2015/6/10  57line
		// TODO 线程不安全   {遗留bug}
		request.setAttribute("singleTemp", -1);
		ProgressOfDownData.getProgressOfDownData().getDataSearchPro(request);
	}
}
