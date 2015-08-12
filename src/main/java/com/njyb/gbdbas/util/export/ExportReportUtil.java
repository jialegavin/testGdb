package com.njyb.gbdbas.util.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.service.datasearch.export.report.factory.CreateSheetOperate;

/**
 * 导出excel表报工具类
 * @author XL
 * @date 2015-04-02
 * @version 标准版
 */
@Component
public class ExportReportUtil {
	
	@Autowired
	private CreateSheetOperate createSheetOperate;
	
	//log记录日志
	private static final Logger log = Logger.getLogger(ExportReportUtil.class);
	
	// 工作表索引
	public static int sheetNum = 0;
	
	public <T> void exportExcel(String[]reportTypeField,String country,HttpServletRequest request, HttpServletResponse response) 
	{
		WritableWorkbook wb = null;
		OutputStream os = null;
		try 
		{
			// 可打开页面导出对话框
			response.reset();
			os = response.getOutputStream();
			response.addHeader("Content-Disposition", "attachment;filename=" + new String((country + ".xls").getBytes(), "iso8859-1"));
			response.setContentType("application/octet-stream");
			// 创建可写入的Excel工作簿
			wb = Workbook.createWorkbook(os);
			// 汇总创建sheet
			//设置sheetNum从及开始(添加明细需要的工作表索引)
			sheetNum = reportTypeField.length;
			createSheetOperate.createSheet(wb,country,reportTypeField,request);
			// 写入到文件
			wb.write();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			log.debug(e.getMessage());
		} 
		finally 
		{
			if (wb != null) 
			{
				try 
				{
					wb.close();
				}
				catch (Exception e) 
				{
					log.debug(e.getMessage());
				}
			}
			if(os != null)
			{
				try 
				{
					//关闭输出流
					os.close();
				}
				catch (IOException e) 
				{
					log.debug(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * 标题样式
	 * @return
	 */
	public static WritableCellFormat getTitleCellFormat() 
	{ 
		WritableCellFormat titleFormat = null;
		try 
		{
			// 标题样式
			WritableFont titleFont = new WritableFont(WritableFont.createFont("微软雅黑"), 24,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
			// 添加单元格样式
			titleFormat = new WritableCellFormat(NumberFormats.TEXT);
			// 设置字体样式
			titleFormat.setFont(titleFont);
			// 设置标题居中样式
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFormat.setAlignment(Alignment.CENTRE);
			// 设置背景颜色
			titleFormat.setBackground(Colour.DARK_TEAL);
		} 
		catch (WriteException e) 
		{
			log.debug(e.getMessage());
		}
        return titleFormat;  
    }  
	
	/**
	 * 列名样式
	 * @return
	 */
	public static WritableCellFormat getColumnNameFormat()
	{ 
		WritableCellFormat columnNameFormat = null;
		try 
		{
			// 设置列名样式
			// 通过函数WritableFont（）设置字体样式
			// 第一个参数表示所选字体（字体为Arial）
			// 第二个参数表示字体大小
			// 第三个参数表示粗体样式，有BOLD和NO_BOLD两种样式
			// 第四个参数表示是否斜体,此处true表示为斜体
			// 第五个参数表示下划线样式
			// 第六个参数表示颜色样式，此处为黑色
			WritableFont columnNamefont = new WritableFont(WritableFont.createFont("微软雅黑"), 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
			// 实例化文字格式化
			columnNameFormat = new WritableCellFormat(columnNamefont);
			// 对齐方式
			columnNameFormat.setAlignment(Alignment.CENTRE);
			columnNameFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			columnNameFormat.setBackground(Colour.DARK_TEAL);
		} 
		catch (WriteException e)
		{
			log.debug(e.getMessage());
		}
		return columnNameFormat;
	}
	
	/**
	 * 排序字段样式
	 * @return
	 */
	public static WritableCellFormat getSortColumnNameFormat() 
	{ 
		WritableCellFormat sortColumnNameFormat = null;
		try 
		{
			WritableFont sortColumnNamefont = new WritableFont(
					WritableFont.createFont("微软雅黑"), 10, WritableFont.NO_BOLD, 
					false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
			// 实例化文字格式化
			sortColumnNameFormat = new WritableCellFormat(sortColumnNamefont);
			// 对齐方式
			sortColumnNameFormat.setAlignment(Alignment.CENTRE);
			sortColumnNameFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			sortColumnNameFormat.setBackground(Colour.DARK_TEAL);
		} 
		catch (WriteException e) 
		{
			log.debug(e.getMessage());
		}
		return sortColumnNameFormat;
	}
	
	/**
	 * 正文样式
	 * @return
	 */
	public static WritableCellFormat getFontFormat()
	{ 
		WritableCellFormat fontFormat = null;
		try 
		{
			// 正文字体样式
			WritableFont font = new WritableFont(WritableFont.createFont("微软雅黑"), 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			// 实例化文字格式化
			fontFormat = new WritableCellFormat(font);
			fontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			fontFormat.setAlignment(Alignment.CENTRE);
		} 
		catch (WriteException e) 
		{
			log.debug(e.getMessage());
		}
		return fontFormat;
	}
	
	/**
	 * 格式化数字
	 * @return
	 */
	public static WritableCellFormat getNumberFormat() 
	{ 
		// 格式化数字
	    NumberFormat nf = new NumberFormat("#,##0.00");
	    WritableCellFormat numberFormat = new WritableCellFormat(nf);
		return numberFormat;
	}
	
	/**
	 * 添加链接到某个工作表
	 * 
	 * @param col
	 *            链接所在列
	 * @param row
	 *            链接所在行
	 * @param sheet
	 *            要将链接添加到哪一个工作表
	 * @param destSheet
	 *            要连接到哪个工作表
	 * @param linkName
	 *            链接名称
	 */
	public static void addHyperlink(int col, int row, WritableSheet sheet, WritableSheet destSheet, String linkName)
	{
		try
		{
			WritableHyperlink whl = new WritableHyperlink(col, row, linkName, destSheet, 0, 0);
			sheet.addHyperlink(whl);
		} 
		catch (RowsExceededException e)
		{
			log.debug(e.getMessage());
		} 
		catch (WriteException e)
		{
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * 报表明细
	 * 
	 * @param <T>
	 * @param sheet
	 *            工作表
	 * @param detailList
	 *            明细集合
	 * @param readOnly
	 *            读取状态
	 * @param linkSheet
	 *            返回到链接的目标工作表
	 * @param fields
	 *            每列中文名称
	 * @param fieldNames
	 *            属性名称
	 * @param tableName
	 *            表名
	 * @throws Exception
	 */
	public static <T> void createReportDetail(WritableSheet sheet, List<T> detailList,
			boolean readOnly, WritableSheet linkSheet, String[] fields,
			String[] fieldNames, String tableName) throws Exception
	{
		//获取标题样式
		WritableCellFormat titleFormat = ExportReportUtil.getTitleCellFormat();
		
		//获取列名样式
		WritableCellFormat columnNameFormat = ExportReportUtil.getColumnNameFormat();
		
		//获取数据样式
		WritableCellFormat numberFormat = ExportReportUtil.getNumberFormat();
		
		//获取字体样式
		WritableCellFormat fontFormat = ExportReportUtil.getFontFormat();
		
		//取得sheet的设置信息
		SheetSettings sheetSet = sheet.getSettings();
		
		//去掉网格线
		sheetSet.setShowGridLines(true);
		
		//冻结窗口
		sheetSet.setVerticalFreeze(3);

		// 设置单元格为只读状态
		sheet.getSettings().setProtected(readOnly);
		
		// 合并单元格
		sheet.mergeCells(0, 0, fields.length - 1, 0);
		
		// 设置行的高度
		sheet.setRowView(0, 450);
		
		//设置字体样式
		WritableFont font = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		WritableCellFormat returnFontFormat = new WritableCellFormat(font);
		returnFontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		returnFontFormat.setAlignment(Alignment.LEFT);
		
		// 设置行的高度
		// 第一个参数表示第几行
		// 第二个参数表示行高
		sheet.setRowView(0, 600);
		// 添加标题(第一列第一行，到第二列第一行)
		// 合并单元格
		sheet.mergeCells(0, 0, fields.length - 1, 0);
		sheet.addCell(new Label(0, 0, tableName, titleFormat));
		
		sheet.setRowView(1, 450);
		//添加返回文字
		sheet.addCell(new Label(0, 1, "[Return]", returnFontFormat));
		addHyperlink(0, 1, sheet, linkSheet, "[Return]");


		// 添加列名
		for (int i = 0; i < fields.length; i++) 
		{
			// 设置列宽
			// 第一参数为某列，第二个参数为列宽
			sheet.setColumnView(i, 20);
			sheet.addCell(new Label(i, 2, fields[i], columnNameFormat));
		}
		int r = 3;
		// 循环插入数据
		if (detailList.size() != 0) 
		{
			for (T t : detailList) 
			{
				// 正文字体样式
				WritableFont contextFont = new WritableFont(
						WritableFont.ARIAL, 10,WritableFont.NO_BOLD, false, 
						UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				fontFormat = new WritableCellFormat(contextFont);
				fontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				fontFormat.setAlignment(Alignment.CENTRE);
				
				// 正文字体样式
				WritableFont numberContextFont = new WritableFont(
						WritableFont.ARIAL, 10,WritableFont.NO_BOLD, false, 
						UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				numberFormat = new WritableCellFormat(numberContextFont);
				numberFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				numberFormat.setAlignment(Alignment.CENTRE);
				if(r%2==0)
				{
					fontFormat.setBackground(Colour.ICE_BLUE);
					numberFormat.setBackground(Colour.ICE_BLUE);
				}
				
				for (int i = 0; i < fieldNames.length; i++) 
				{
					// 获取属性类型
					String type = PropertyUtils.getPropertyType(t,fieldNames[i]).getName();
					if (type.equals("java.lang.String")) 
					{
						Label contextSub = new Label(i, r, BeanUtils.getProperty(t,fieldNames[i]), fontFormat);
						sheet.addCell(contextSub);
					}
					else
					{
						String value = BeanUtils.getProperty(t, fieldNames[i]);
						if("".equals(value)||null==value)
						{
							Label contextSub = new Label(i, r, BeanUtils.getProperty(t,fieldNames[i]), fontFormat);
							sheet.addCell(contextSub);
						}
						else
						{
						jxl.write.Number number = new jxl.write.Number(i, r, Double.parseDouble(value),numberFormat); 
						sheet.addCell(number);
						}
					}
				}
				r++;
			}
		}
	}
	
	/**
	 * 处理工作表名过长
	 * @param sheetName 明细工作表名称
	 * @return
	 */
	public static String getSheetName(String sheetName)
	{
		if(sheetName != null && sheetName.length()>14)
		{
			sheetName=sheetName.substring(0, 14)+"...";
		}
		return sheetName;
	}
}
