package com.njyb.gbdbase.service.datasearch.export.report.factory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.SheetSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.export.DateComparator;
import com.njyb.gbdbas.util.export.ExportDataUtil;
import com.njyb.gbdbas.util.export.ExportReportUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ExportResultFieldModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;
import com.njyb.gbdbase.service.common.engines.IReportDrillService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;
import com.njyb.gbdbase.service.datasearch.export.report.IExportDataService;

/**
 * 创建工作表接口实现层
 * @author XL
 * @date 2015-04-02
 * @version 标准版
 *
 */
@Service
public class CreateSheet implements ICreateSheet{

	@Autowired
	private IReportDrillService reportDrillService;
	@Autowired
	private IDataSearchService dataSearchService;
	@Autowired
	private IReportDetailService reportDetailService;
	@Autowired
	private IExportDataService exportDataService;
	@Autowired
	private ExportResultFieldModel exportResultFieldModel;
	@Autowired
	private ReportPropertiesModel rpm;
	
	/**
	 * 不带钻取的汇总
	 */
	@Override
	public <T> void createNoDrillSheet(WritableWorkbook wb, Map map,
			boolean readOnly, int sheetIndex, int sumSheetNum,
			HttpServletRequest request) throws Exception 
	{
		//从map获取国家
		String country = (String) map.get("country");
		
		//从map获取报告类型
		String reportType = (String) map.get("reportType");
		
		//从map获取汇总数据
		List<T> summaryList = (List<T>) map.get("summaryList");
		
		//获取标题样式
		WritableCellFormat titleFormat = ExportReportUtil.getTitleCellFormat();
		
		//获取排序字段样式
		WritableCellFormat sortColumnNameFormat = ExportReportUtil.getSortColumnNameFormat();
		
		//获取列名样式
		WritableCellFormat columnNameFormat = ExportReportUtil.getColumnNameFormat();
		
		//获取字体样式
		WritableCellFormat fontFormat = ExportReportUtil.getFontFormat();
		
		//判断汇总集合是否为0
		if (summaryList.size() != 0)
		{
			//获取工作表名称
			String sheetName = InitCountryCENameUtil.getReportTypeZhName(reportType);
			
			//获取导出报表excel所需的字段集合
			Map fieldMap = exportResultFieldModel.getExportResultFieldMap();
			
			// 获取列名
			String[] fields = fieldMap.get(country + "_"+ reportType + DataSearchConstantUtil.FIELDNAME).toString().split(",");
			
			// 获取属性字段
			String[] propertyFields = fieldMap.get(country + "_"+ reportType + DataSearchConstantUtil.FIELDID).toString().split(",");
			
			//获取排序字段
			String sortField = fieldMap.get(country + DataSearchConstantUtil.SORT_FIELD).toString();
			
			//获取明细中文列名
//			String[] detailColumnName = fieldMap.get(country + DataSearchConstantUtil.DETAIL_FIELDZHNAME).toString().split(",");
			
			//获取明细属性字段
//			String[] detailField = fieldMap.get(country + DataSearchConstantUtil.DETAIL_FIELDID).toString().split(",");
			
			// 创建工作表
			// 两个参数分别是工作表名字和插入位置，这个位置从0开始
			WritableSheet sheet = wb.createSheet(sheetName, sheetIndex);
			SheetSettings sheetSet = sheet.getSettings();
			
			//去掉网格线
			sheetSet.setShowGridLines(true);
			
			// 设置单元格为只读状态
			sheetSet.setProtected(readOnly);
			
			//冻结窗口
			sheetSet.setVerticalFreeze(0);
			
			// 合并单元格
			sheet.mergeCells(0, 0, fields.length - 1, 0);
			
			// 设置行的高度
			sheet.setRowView(0, 600);
			
			// 添加标题(第一列第一行，到第二列第一行)
			sheet.addCell(new Label(0,0,sheetName,titleFormat));
			
			// 设置行的高度
			// 第一个参数表示第几行
			// 第二个参数表示行高
			sheet.setRowView(1, 450);
			
			// 合并单元格
			sheet.mergeCells(0, 1, fields.length - 1, 1);
			sheet.addCell(new Label(0, 1,""));
			
			// 添加列名
			for (int i = 0; i < fields.length; i++)
			{
				// 设置列宽
				// 第一参数为某列，第二个参数为列宽
				sheet.setColumnView(i, 20);
				if(propertyFields[i].equals(sortField)){
					// 设置列名样式
					sheet.addCell(new Label(i, 2, fields[i]+"↓", sortColumnNameFormat));
				}
				else
				{
					// 设置列名样式
					sheet.addCell(new Label(i, 2, fields[i], columnNameFormat));
				}
			}
			
			// 排序
			ExportDataUtil.executeSort(summaryList, sortField);
			
			int r = 3;
			
			// 循环插入数据
			for (T t : summaryList)
			{
				do 
				{
					ExportReportUtil.sheetNum++;				
				} while (ExportReportUtil.sheetNum<=sumSheetNum);
				
				// 正文字体样式
				WritableFont font = new WritableFont(
						WritableFont.ARIAL, 10,WritableFont.NO_BOLD, false, 
						UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				fontFormat = new WritableCellFormat(font);
				fontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				fontFormat.setAlignment(Alignment.CENTRE);
				if(r%2==0)
				{
					fontFormat.setBackground(Colour.ICE_BLUE);
				}
				//获取当前报告类型字段如：date
//				String reportField=rpm.getReportFieldMap().get(reportType).toString();
				//报告类型字段获取信息
//				String reportSummaryName = BeanUtils.getProperty(t,reportField);
				
				// 创建第一层报告的明细工作表
//				WritableSheet detailSheet = wb.createSheet(ExportReportUtil.getSheetName(reportSummaryName)+ "明细",ExportReportUtil.sheetNum);

				for(int i = 0; i<propertyFields.length; i++)
				{
					// 获取属性类型
					String type = PropertyUtils.getPropertyType(t,propertyFields[i]).getName();
					Label context = null;
					if(type.equals("java.lang.String"))
					{
						context = new Label(i, r, BeanUtils.getProperty(t,propertyFields[i]), fontFormat);
					}
					else
					{
						context = new Label(i, r,ExportDataUtil.fmtMicrometer(BeanUtils.getProperty(t,propertyFields[i])), fontFormat);
					}
					sheet.addCell(context);
				}
				
				// 添加第一层报告类型的超链接
//				ExportReportUtil.addHyperlink(0, r, sheet, detailSheet, reportSummaryName);
				
				r++;
				//获取当前报告类型的汇总明细
//				List<DataReportSumModel> detailList = reportDetailService.buildReportSumList(reportSummaryName, reportType, country);
				// 第一层报告的明细工作表内写值
//				ExportReportUtil.createReportDetail(detailSheet,detailList,readOnly, sheet,detailColumnName,detailField,sheetName+"明细");
			}
			String[] statisticsFields = fieldMap.get(country + DataSearchConstantUtil.STATISTICS).toString().split(";");
			String[] commonField = statisticsFields[0].split(",");
			String[] commonSummaryRatio = statisticsFields[1].split(",");
			// 添加汇总
			T sum = exportDataService.statistics(DataReportSumModel.class, summaryList, commonField, commonSummaryRatio, request);
			sheet.addCell(new Label(0, r, "总计", columnNameFormat));
			for(int i = 1; i<propertyFields.length; i++)
			{
				// 获取属性类型
				String type = PropertyUtils.getPropertyType(sum,propertyFields[i]).getName();
				Label context = null;
				if(type.equals("java.lang.String"))
				{
					context = new Label(i, r, BeanUtils.getProperty(sum,propertyFields[i]), columnNameFormat);
				}
				else
				{
					context = new Label(i, r, ExportDataUtil.fmtMicrometer(BeanUtils.getProperty(sum,propertyFields[i])), columnNameFormat);
				}
				sheet.addCell(context);
			}
		}
	}

	/**
	 * 带钻取的汇总
	 */
	@Override
	public <T> void createDrillSheet(WritableWorkbook wb, Map map,boolean readOnly, int sheetIndex, int sumSheetNum,HttpServletRequest request) throws Exception 
	{
		//从map获取国家
		String country = (String) map.get("country");
		
		//从map获取报告类型
		String reportType = (String) map.get("reportType");
		
		//从map获取汇总数据
		List<T> summaryList = (List<T>) map.get("summaryList");
		
		//获取标题样式
		WritableCellFormat titleFormat = ExportReportUtil.getTitleCellFormat();
		
		//获取排序字段样式
		WritableCellFormat sortColumnNameFormat = ExportReportUtil.getSortColumnNameFormat();
		
		//获取列名样式
		WritableCellFormat columnNameFormat = ExportReportUtil.getColumnNameFormat();
		
		//获取数据样式
		WritableCellFormat numberFormat = ExportReportUtil.getNumberFormat();
		
		//获取字体样式
		WritableCellFormat fontFormat = ExportReportUtil.getFontFormat();
		
		// 判断汇总报告集合是否有值
		if (summaryList.size() != 0) 
		{
			//获取工作表名称
			String sheetName = InitCountryCENameUtil.getReportTypeZhName(reportType);
			
			//获取导出报表excel所需的字段集合
			Map fieldMap = exportResultFieldModel.getExportResultFieldMap();
			
			// 根据报告类型获取第二层钻取报告类型
			String drillReportType = fieldMap.get(country + "_" + reportType + DataSearchConstantUtil.DRILLREPORTTYPE).toString();
			
			// 根据报告类型获取第二层钻取中文名称
			String drillColumnName = fieldMap.get(country + "_" + reportType + DataSearchConstantUtil.DRILLCOLUMNNAME).toString();
			
			//获取当前报告类型字段如：date
			String drillReportField = rpm.getReportFieldMap().get(drillReportType).toString();
			
			// 获取列名(缺少第二层钻取列名)
			String[] oldFields = fieldMap.get(country + "_"+ reportType + DataSearchConstantUtil.FIELDNAME).toString().split(",");
			// 重新组装列名
			String[] fields = exportDataService.getNewArray(oldFields, drillColumnName);
			
			// 获取属性字段(缺少第二层钻取属性名称)
			String[] oldPropertyFields = fieldMap.get(country + "_"+  reportType + DataSearchConstantUtil.FIELDID).toString().split(",");
			
			// 重新组装属性字段
			String[] propertyFields = exportDataService.getNewArray(oldPropertyFields, drillReportField);
			
			//获取排序字段
			String sortField = fieldMap.get(country + DataSearchConstantUtil.SORT_FIELD).toString();
			
			//获取明细中文列名
			String[] detailColumnName = fieldMap.get(country + DataSearchConstantUtil.DETAIL_FIELDZHNAME).toString().split(",");
			
			//获取明细属性字段
			String[] detailField = fieldMap.get(country + DataSearchConstantUtil.DETAIL_FIELDID).toString().split(",");
			
			// 创建工作表
			// 两个参数分别是工作表名字和插入位置，这个位置从0开始
			WritableSheet sheet = wb.createSheet(sheetName, sheetIndex);
			SheetSettings sheetSet = sheet.getSettings();
			
			//去掉网格线
			sheetSet.setShowGridLines(true);
			
			// 设置单元格为只读状态
			sheetSet.setProtected(readOnly);
			
			//冻结窗口
			sheetSet.setVerticalFreeze(0);
			
			// 合并单元格
			sheet.mergeCells(0, 0, fields.length - 1, 0);
			
			// 设置行的高度
			// 第一个参数表示第几行
			// 第二个参数表示行高
			sheet.setRowView(0, 600);
			
			sheet.addCell(new Label(0,0,sheetName,titleFormat));
			
			// 设置行的高度
			sheet.setRowView(1, 450);
			
			// 添加标题(第一列第一行，到第二列第一行)
			// 合并单元格
			sheet.mergeCells(0, 1, fields.length - 1, 1);
			sheet.addCell(new Label(0, 1, ""));
			
			// 添加列名
			for (int i = 0; i < fields.length; i++) 
			{
				// 设置列宽
				// 第一参数为某列，第二个参数为列宽
				sheet.setColumnView(i, 20);
				// 设置列名样式
				sheet.addCell(new Label(i, 2, fields[i], columnNameFormat));
			}

			int r = 3;
			//获取当前报告类型字段如：date
			String reportField=rpm.getReportFieldMap().get(reportType).toString();
			if(reportField.equals("date"))
			{
				// 对日期进行排序
				Collections.sort(summaryList, new DateComparator());
			}
			else
			{
				// 排序
				ExportDataUtil.executeSort(summaryList, sortField);
			}
			
			// 循环插入数据
			for (T t : summaryList) {
				
				do 
				{
					ExportReportUtil.sheetNum++;				
				} while (ExportReportUtil.sheetNum <= sumSheetNum);
				
				// 正文字体样式
				WritableFont font = new WritableFont(
						WritableFont.ARIAL, 10,WritableFont.NO_BOLD, false, 
						UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				fontFormat = new WritableCellFormat(font);
				fontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				fontFormat.setAlignment(Alignment.CENTRE);
				if(r%2==0){
					fontFormat.setBackground(Colour.ICE_BLUE);
				}
				
				//工作表明细名称
//				String sheetDetailName = BeanUtils.getProperty(t,reportField);
				
				// 创建明细工作表
//				WritableSheet sheetDetail = wb.createSheet(ExportReportUtil.getSheetName(sheetDetailName)+ "明细",ExportReportUtil.sheetNum);

				for(int i = 0; i<propertyFields.length; i++)
				{
					// 获取属性类型
					String type = PropertyUtils.getPropertyType(t,propertyFields[i]).getName();
					Label context = null;
					if(type.equals("java.lang.String"))
					{
						context = new Label(i, r, BeanUtils.getProperty(t,propertyFields[i]), fontFormat);
					}
					else
					{
						context = new Label(i, r, ExportDataUtil.fmtMicrometer(BeanUtils.getProperty(t,propertyFields[i])), fontFormat);
					}
					if(i == 1)
					{
						context = new Label(1, r, "", fontFormat);
					}
					sheet.addCell(context);
				}
				// 添加第一层报告类型超链接
//				ExportReportUtil.addHyperlink(0, r, sheet, sheetDetail, BeanUtils.getProperty(t,reportField));
				
				r++;

				// 第一层明细
//				List<DataReportSumModel> firstReportSameList = reportDetailService.buildReportSumList(BeanUtils.getProperty(t,reportField), reportType, country);
				
				// 获取第二层报告类型汇总集合
				List<DataReportSumModel> secondSummaryList = reportDrillService.drillReportSumList(BeanUtils.getProperty(t,reportField), reportType, country, "drill&"+drillReportType);

				//获取需要计算比率的字段
				String[] percentageFields = fieldMap.get(country + DataSearchConstantUtil.PERCENTAGE).toString().split(";");
				String[] commonField = percentageFields[0].split(",");
				String[] commonSummaryRatio = percentageFields[1].split(",");
				exportDataService.getDataPercent(secondSummaryList, commonField, commonSummaryRatio);
				
				// 根据排序字段进行排序
				ExportDataUtil.executeSort(secondSummaryList,sortField);
//				ExportDataUtil .executeSort(firstReportSameList,sortField);

				// 写入第一层报告类型的明细
//				ExportReportUtil.createReportDetail(sheetDetail,firstReportSameList,readOnly, sheet,detailColumnName,detailField,sheetName+"明细");//获取明显列，描述
				
				// 判断第二层汇总集合是否有值
				if (secondSummaryList.size() != 0) 
				{
					for (int i = 0; i < secondSummaryList.size(); i++) 
					{
						fontFormat = new WritableCellFormat(font);
						fontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						fontFormat.setAlignment(Alignment.CENTRE);
						if(r%2==0)
						{
							fontFormat.setBackground(Colour.ICE_BLUE);
						}
						for(int j = 0; j<propertyFields.length; j++)
						{
							Label contextSub = null;
							if(j == 0)
							{
								contextSub = new Label(j, r, "", fontFormat);
							}
							else
							{
								// 获取属性类型
								String type = PropertyUtils.getPropertyType(secondSummaryList.get(i),propertyFields[j]).getName();
								if(type.equals("java.lang.String"))
								{
									contextSub = new Label(j, r, BeanUtils.getProperty(secondSummaryList.get(i),propertyFields[j]), fontFormat);
								}
								else
								{
									contextSub = new Label(j, r,ExportDataUtil.fmtMicrometer(BeanUtils.getProperty(secondSummaryList.get(i),propertyFields[j])), fontFormat);
								}
							}
							sheet.addCell(contextSub);
						}
						
						do 
						{
							ExportReportUtil.sheetNum++;				
						} while (ExportReportUtil.sheetNum <= sumSheetNum);
						
						// 创建第二层报告类型的明细工作表
//						WritableSheet productSalCountryDetail = wb.createSheet(sheetDetailName
//								+BeanUtils.getProperty(secondSummaryList.get(i), drillReportField)
//								+sheetName, ExportReportUtil.sheetNum);

						// 添加第二层报告类型的值超链接
//						ExportReportUtil.addHyperlink(1, r, sheet, productSalCountryDetail, BeanUtils.getProperty(secondSummaryList.get(i), drillReportField));
						r++;
						
						// 第二层明细
//						List<DataReportSumModel> secondDetailList = reportDetailService.buildReportSumList(BeanUtils.getProperty(secondSummaryList.get(i), drillReportField), drillReportType, country);
						// 第二层报告类型明细工作表写值
//						ExportReportUtil.createReportDetail(productSalCountryDetail,secondDetailList,readOnly, sheet,detailColumnName,detailField,sheetName+"明细");//获取明细列，描述
					}
				}
			}
			//获取统计字段
			String[] statisticsFields = fieldMap.get(country + DataSearchConstantUtil.STATISTICS).toString().split(";");
			String[] commonField = statisticsFields[0].split(",");
			String[] commonSummaryRatio = statisticsFields[1].split(",");
			// 添加汇总
			T sum = exportDataService.statistics(DataReportSumModel.class, summaryList, commonField, commonSummaryRatio, request);
			sheet.addCell(new Label(0, r, "总计", columnNameFormat));
			for(int i = 1; i<propertyFields.length; i++)
			{
				if(i == 1)
				{
					sheet.addCell(new Label(i, r, "", columnNameFormat));
					continue;
				}
				// 获取属性类型
				String type = PropertyUtils.getPropertyType(sum,propertyFields[i]).getName();
				Label context = null;
				if(type.equals("java.lang.String"))
				{
					context = new Label(i, r, BeanUtils.getProperty(sum,propertyFields[i]), columnNameFormat);
				}
				else
				{
					context = new Label(i, r, ExportDataUtil.fmtMicrometer(BeanUtils.getProperty(sum,propertyFields[i])), columnNameFormat);
				}
				sheet.addCell(context);
			}
		}
	}

}
