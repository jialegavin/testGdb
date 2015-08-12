package com.njyb.gbdbase.service.datasearch.export.report.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.export.ExportDataUtil;
import com.njyb.gbdbas.util.export.ExportPDFUtil;
import com.njyb.gbdbas.util.export.ExportReportUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;
import com.njyb.gbdbase.service.datasearch.export.report.IExportDataService;

/**
 * 报表中的导出业务接口层实现层
 * @author XL
 * @date 2015-04-30
 * @version 标准版
 */
@Service
public class ExportDataService implements IExportDataService{
	//log记录日志
	private static final Logger log = Logger.getLogger(ExportDataService.class);
	@Autowired
	private IReportDetailService reportDetailService;
	@Autowired
	private IDataSearchService dataSearchService;
	@Autowired
	private ExportReportUtil exportReportUtil;

	@Override
	public void exportSummaryDetail(String reportType, String country, String comparValue, Map map, HttpServletRequest request, HttpServletResponse response) {
		//将中文国家名称进行处理
		String countryName = InitCountryCENameUtil.queryCountryEnName(country);
		//获取当前报告类型的汇总明细
		List<DataReportSumModel> detailList = reportDetailService.buildReportSumList(comparValue, reportType, countryName);
		//需要显示的列中文名称
		String[] fieldName = map.get(countryName+DataSearchConstantUtil.DETAIL_FIELDZHNAME).toString().split(",");
		//需要反射的字段
		String[] fields = map.get(countryName+DataSearchConstantUtil.DETAIL_FIELDID).toString().split(",");
		//转换集合为字符串类型的集合
		List<String[]> data = ExportDataUtil.getList(detailList, fields);
		//设置pdf页面大小
		Document document = new Document(PageSize.A2, 50, 50, 50, 50);
		//导出pdf
		ExportPDFUtil.downloadPDF(document, IConstantUtil.FLEXWIDTH, "汇总明细", data, fieldName, request, response);
	}

	@Override
	public <T> void exportSummaryReportByCountry(String country, Map map,
			HttpServletRequest request, HttpServletResponse response) {
		// 根据国家名称获取报告类型,如：(qs,jks)
		String[] reportTypeField = map.get(country + DataSearchConstantUtil.REPORT_TYPE).toString().split(",");
		// 判断获取的报告类型字段数组是否为空
		if(reportTypeField.length != 0)
		{
			//导出excel
			exportReportUtil.exportExcel(reportTypeField, country, request, response);
		}
		
	}

	@Override
	public <T> T statistics(Class<DataReportSumModel> c, List<T> summaryList,String[] commonField, String[] commonSummaryRatio,HttpServletRequest request) 
	{
		T t = null;
		try 
		{
			t = (T) c.newInstance();
			for(int i =0;i<commonField.length;i++)
			{
				//计算单个数值类型的字段的总和
				double sum = 0;
				for (T m : summaryList)
				{
					sum+=Double.parseDouble(BeanUtils.getProperty(m ,commonField[i]));
				}
				//总计
				BeanUtils.setProperty(t, commonField[i], sum);
			}
			//计算总比率
			for(int i = 0 ; i < commonSummaryRatio.length; i++)
			{
				BeanUtils.setProperty(t, commonSummaryRatio[i],100.00);
			}
		} 
		catch (InstantiationException | IllegalAccessException e) 
		{
			log.debug(e.getMessage());
		} 
		catch (NumberFormatException |InvocationTargetException | NoSuchMethodException e) 
		{
			log.debug(e.getMessage());
		} 
		return t;
	}

	@Override
	public <T> void getDataPercent(List<T> summaryList, String[] fields,String[] ratioFields) throws Exception
	{
		//所有数值类型的字段的总和组成的数组
		double [] fieldsSummary = new double[fields.length];
		for(int i =0;i<fields.length;i++)
		{
			//计算单个数值类型的字段的总和
			double sum = 0;
			for (T t : summaryList) 
			{
				sum+=Double.parseDouble(BeanUtils.getProperty(t ,fields[i]));
			}
			fieldsSummary[i] = sum;
		}
		
		//计算单个比率
		for(int i =0;i<fields.length;i++)
		{
			for (T t : summaryList) 
			{
				if(fieldsSummary[i] != 0){
					BeanUtils.setProperty( t,ratioFields[i],(Double.parseDouble(BeanUtils.getProperty(t ,fields[i]))/fieldsSummary[i]*100));
				}
				else
				{
					BeanUtils.setProperty( t,ratioFields[i],0.00);
				}
			}
		}
	}

	@Override
	public String[] getNewArray(String[] srcArry, String addStr) {
		//判断字符串是否为空
		if(null != addStr || !"".equals(addStr))
		{
			String[] newArray= new String[srcArry.length+1]; 
			//重新组装数组
			newArray[0] = srcArry[0];
			newArray[1] = addStr;
			System.arraycopy(srcArry, 1, newArray, 2, srcArry.length-1);
			return newArray;
		}
		return srcArry;
	}
}
