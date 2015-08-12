package com.njyb.gbdbas.util.export;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.njyb.gbdbas.util.IConstantUtil;

/**
 * @Description 导出Excel工具类
 * @author XL
 * @date 2015-03-07
 * @version 标准版
 */
public class ExportExcelUtil {
	
	//log记录日志
	private static final Logger log = Logger.getLogger(ExportExcelUtil.class);
	
	/**
	 * 导出EXCEL
	 * 当数据过多时，可分多个sheet页显示数据
	 * 
	 * @param tableName
	 *            :表名
	 * @param data
	 *            :数据集合
	 * @param fields
	 *            :属性名集合
	 * @param linkImportFields
	 *            :需要添加百度链接的各个国家进口商字段名称数组
	 * @param linkExportFields
	 *            :需要添加百度链接的各个国家出口商字段名称数组
	 * @param request
	 * @param response
	 */
	public static void exportExcel(String tableName, List<String[]> data, String[] fields, String[] linkImportFields, String[] linkExportFields, HttpServletRequest request, HttpServletResponse response) 
	{
		
		WritableWorkbook wb;
		
		OutputStream os = null;
		
		// 获取进口商列
		int importerCellNum=0;
		
		// 获取出口商列
		int exporterCellNum=0;
		
		// 当前sheet索引
		int sheetNum = 0;
		
		// 当前记录索引
		int recNum = 0;
		
		// 替换所有空格
		tableName = tableName.replaceAll("\\s*", "");
		
		// 工作表名称
		String workSheet = tableName + sheetNum;
		
		try 
		{
			// start 可打开页面导出对话框
			response.reset();
			os = response.getOutputStream();
			response.addHeader("Content-Disposition", "attachment;filename=" + new String((tableName + ".xls").getBytes(), "iso8859-1"));
			response.setContentType("application/octet-stream");
			// end
			
			// 创建工作区
			wb = Workbook.createWorkbook(os);
			
			while (true) 
			{
				// 记录行
				int rowNum = 0;
				
				// 创建工作表
				WritableSheet sheet = wb.createSheet(workSheet, sheetNum);
				
				sheetNum++;
				
				// 列名样式
				// 通过函数WritableFont（）设置字体样式
				// 第一个参数表示所选字体
				// 第二个参数表示字体大小
				// 第三个参数表示粗体样式，有BOLD和NORMAL两种样式
				// 第四个参数表示是否斜体,此处true表示为斜体
				// 第五个参数表示下划线样式
				// 第六个参数表示颜色样式，此处为Red
				WritableFont columnNamefont = new WritableFont(WritableFont.ARIAL, 15, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
				
				// 实例化文字格式化
				WritableCellFormat columnNameFormat = new WritableCellFormat(columnNamefont);
				
				// 对齐方式
				columnNameFormat.setAlignment(Alignment.CENTRE);
				columnNameFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				
				// 设置行的高度
				sheet.setRowView(1, 400);
				
				// 标题样式
				WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 20, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
				WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
				
				// 设置标题居中样式
				titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				titleFormat.setAlignment(Alignment.CENTRE);
				
				// 合并单元格(从第一列第一行，到第二列第一行)
				sheet.mergeCells(0, 0, fields.length - 1, 0);
				
				sheet.addCell(new Label(0, rowNum,tableName,titleFormat));
				
				rowNum++;
				
				if (null != fields) 
				{
					// 读取列名
					for (int i = 0; i < fields.length; i++) 
					{
						// 设置列宽
						sheet.setColumnView(i, 20);
						
						// 添加字段名添加到列中
						sheet.addCell(new Label(i, rowNum, fields[i],columnNameFormat));
						
						// 判断字段名中是否包含进口商，如果包含则添加百度链接
						if(linkImportFields.length != 0)
						{
							for(int j = 0 ; j <linkImportFields.length; j++)
							{
								if(fields[i].equals(linkImportFields[j]))
								{
									importerCellNum = i;
									break;
								}
							}
						}
						
						// 判断字段名中是否包含出口商，如果包含则添加百度链接
						if(linkExportFields.length != 0)
						{
							for(int j = 0 ; j <linkExportFields.length; j++)
							{
								if(fields[i].equals(linkExportFields[j]))
								{
									exporterCellNum=i;
									break;
								}
							}
						}
					}
					
					rowNum++;
					
					// 字体样式
					WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
					WritableCellFormat fontFormat = new WritableCellFormat(font);
					
					// 对齐方式
					fontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
					fontFormat.setAlignment(Alignment.CENTRE);
					
					// 判断集合是否为空
					if (!data.isEmpty()) 
					{
						
						// SHEET数据行
						int sheetRecNum = 0;
						
						// 加入数据
						// data先查单条数据拼接成字符串数组，在放入集合
						for (int i = recNum; i < data.size(); i++, recNum++,rowNum++, sheetRecNum++) 
						{
							if (null != data.get(i) && 0 != data.get(i).length) 
							{
								// 单个SHEET数据行超过SHEET应达到数据限额，则停止写入
								if (sheetRecNum >= IConstantUtil.SHEETSIZE)
								{
									break;
								}
								
								//写入数据并为指定字段添加超链接
								for (int j = 0; j < data.get(i).length; j++) 
								{
									 if(j == importerCellNum && j != 0)
									 {
										 // 生产进口商的超链接
							             sheet.addHyperlink(new WritableHyperlink(j,rowNum,0,0,new URL("http://www.baidu.com/s?ie=utf-8&wd="+data.get(i)[j]+"&cl=3"),data.get(i)[j]));
							         }
									 else if(j == exporterCellNum && j != 0)
									 {
							        	 // 生产出口商的超链接
								         sheet.addHyperlink(new WritableHyperlink(j,rowNum,0,0,new URL("http://www.baidu.com/s?ie=utf-8&wd="+data.get(i)[j]+"&cl=3"),data.get(i)[j]));	
							         }
									 else
									 {
							             // 将生成的单元格添加到工作表中。
									     sheet.addCell(new Label(j, rowNum, data.get(i)[j],fontFormat));
									 }
								}
							}	
						}
					}
					
					// 数据写完，退出
					if (recNum >= data.size())
						break;
				}
				
				// 重新命名表空间名
				workSheet = tableName + sheetNum;
				
				//创建工作空间
				sheet = wb.createSheet(workSheet, sheetNum);
			}
			
			// 写入到文件
			wb.write();
			wb.close();
		} 
		catch (Exception e) 
		{
			log.debug(e.getMessage());
		}
		finally
		{
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
	 * 获取列名
	 * 
	 * @param fields
	 *            使用MyBatis中的Map读取的列名
	 * @return
	 */
	public static String[] getFields(List<Map<String, String>> fields) 
	{
		if (null != fields && 0 != fields.size())
		{
			String[] tableFields = new String[fields.size()-1];
			for (int i = 0; i < fields.size()-1; i++) 
			{
				tableFields[i] = fields.get(i+1).get("COLUMN_NAME");
			}
			return tableFields;
		}
		return null;
	}

	/**
	 * 限制用户下载条数
	 * 
	 * @param exportSize
	 *            下载条数
	 * @param tableName 
	 * 			   表名
	 * @param data
	 *           数据集合
	 * @param fields
	 *           字段名称集合
	 * @param request
	 * @param response
	 */
	public static void createExcel(int exportSize, String tableName,List<String[]> data, String[] fields, String[] linkImportFields, String[] linkExportFields, HttpServletRequest request,HttpServletResponse response) 
	{
		if(exportSize>0)
		{
			List<String[]> list = new ArrayList<String[]>();
			if (data.size() > exportSize) 
			{
				for (int i = 0; i < exportSize; i++) 
				{
					list.add(data.get(i));
				}
				exportExcel(tableName, list, fields, linkImportFields, linkExportFields, request, response);
			} 
			else 
			{
				exportExcel(tableName, data, fields, linkImportFields, linkExportFields, request, response);
			}
		}
		else
		{
			exportExcel(tableName, data, fields, linkImportFields, linkExportFields, request, response);
		}
	}

	/**
	 * 针对全库的导出
	 * @param language 语言
	 * @param countrysData 数据结果集
	 * @param linkImportFields 需要添加百度链接的各个国家进口商字段名称数组
	 * @param linkExportFields 需要添加百度链接的各个国家出口商字段名称数组
	 * @param hscodeFields 需要高亮显示的hscode
	 * @param goodsdespFields 需要高亮显示的产品描述
	 * @param request
	 * @param response
	 */
	public static void exportAllDataExcel(String language,Map<String, Map<String,Object>> countrysData, 
			String[] linkImportFields, String[] linkExportFields, String[] hscodeFields, String[] goodsdespFields,
			HttpServletRequest request,HttpServletResponse response) 
	{
		if(countrysData.size()!=0)
		{
			//记录添加国家的个数
			int countryNum = 0;
			
			// 当前sheet索引
			int sheetNum = 0;
			
			// 当前记录索引
			int recNum = 0;
			
			OutputStream os = null;
			
			try 
			{
				WritableWorkbook wb;
				
				// 可打开页面导出对话框
				response.reset();
				os = response.getOutputStream();
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String((language.equals("message_zh_CN")?"全库数据.xls":"alldata.xls").getBytes(), "iso8859-1"));
				response.setContentType("application/octet-stream");
				
				// 创建工作区
				wb = Workbook.createWorkbook(os);
				
				// 标题样式
				WritableFont titleFont = new WritableFont(WritableFont.ARIAL,20, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
				WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
				
				// 设置标题居中样式
				titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				titleFormat.setAlignment(Alignment.CENTRE);
				
				// 列名样式
				// 通过函数WritableFont（）设置字体样式
				// 第一个参数表示所选字体
				// 第二个参数表示字体大小
				// 第三个参数表示粗体样式，有BOLD和NORMAL两种样式
				// 第四个参数表示是否斜体,此处true表示为斜体
				// 第五个参数表示下划线样式
				// 第六个参数表示颜色样式，此处为Red
				WritableFont columnNamefont = new WritableFont(WritableFont.ARIAL, 15, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
				
				// 实例化文字格式化
				WritableCellFormat columnNameFormat = new WritableCellFormat(columnNamefont);
				
				// 对齐方式
				columnNameFormat.setAlignment(Alignment.CENTRE);
				columnNameFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				
				// 字体样式
				WritableFont font = new WritableFont(WritableFont.ARIAL,12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat fontFormat = new WritableCellFormat(font);
				
				// 对齐方式
				fontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				fontFormat.setAlignment(Alignment.CENTRE);
				
				//标记样式
				WritableFont blueFont = new WritableFont(WritableFont.ARIAL,12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
				WritableCellFormat blueFontFormat = new WritableCellFormat(blueFont);
				
				// 对齐方式
				blueFontFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				blueFontFormat.setAlignment(Alignment.CENTRE);
				
				for(String key:countrysData.keySet())
				{
					Map<String,Object> countrydata = countrysData.get(key);
					
					//工作表名称
					String sheetName=(String) countrydata.get("sheetName");
					
					//获取数据集
					List<String[]> data = (List<String[]>) countrydata.get("dataList");
					
					//获取excel表的列名
					String[] fields = (String[]) countrydata.get("columnName");
					
					// 工作表名称(工作表不能重名，所有加sheet索引进行区分)
					//标题名称
					String workSheet = sheetName;
					
					if(language.equals("message_zh_CN"))
					{
						workSheet = sheetName;
					}
					else if(language.equals("message_en_US"))
					{
						workSheet = key;
					}
					
					// 创建工作表(sheet)
					WritableSheet sheet = wb.createSheet(workSheet, sheetNum);
					
					//记录超过六万条数据重新命名index
					int dataIndex = 0;
					
					while (true) 
					{
						//获取进口商列
						int importerCellNum=0;
						
						//获取出口商列
						int exporterCellNum=0;
						
						//产品描述
						int proDescCellNum=0;
						
						//海关编码
						int hscodeCellNum=0;
						
						// 记录行
						int rowNum = 0;
						
						//新增sheet索引
						sheetNum++;
						
						// 设置行的高度
						sheet.setRowView(1, 400);
						
						// 读取标题(第一列第一行，到第二列第一行)
						// 合并单元格
						sheet.mergeCells(0, 0, fields.length - 1, 0);
						
						//标题名称
						if(language.equals("message_zh_CN"))
						{
							sheet.addCell(new Label(0, rowNum,sheetName + "数据",titleFormat));
						}
						else if(language.equals("message_en_US"))
						{
							sheet.addCell(new Label(0, rowNum,key + "data",titleFormat));
						}
						
						rowNum++;
						
						// 读取列名
						if (null != fields)
						{
							for (int i = 0; i < fields.length; i++) 
							{
								//设置列宽
								sheet.setColumnView(i, 20);
								
								sheet.addCell(new Label(i, rowNum, fields[i],columnNameFormat));
								
								// 判断字段名中是否包含进口商，如果包含则添加百度链接
								if(linkImportFields.length != 0)
								{
									for(int j = 0 ; j <linkImportFields.length; j++)
									{
										if(fields[i].equals(linkImportFields[j]))
										{
											importerCellNum = i;
											break;
										}
									}
								}
								
								// 判断字段名中是否包含出口商，如果包含则添加百度链接
								if(linkExportFields.length != 0)
								{
									for(int j = 0 ; j <linkExportFields.length; j++)
									{
										if(fields[i].equals(linkExportFields[j]))
										{
											exporterCellNum=i;
											break;
										}
									}
								}
								
								// 判断字段名中是否包含hscode，如果包含则高亮显示
								if(hscodeFields.length != 0)
								{
									for(int j = 0 ; j <hscodeFields.length; j++)
									{
										if(fields[i].equals(hscodeFields[j]))
										{
											hscodeCellNum=i;
											break;
										}
									}
								}
								
								// 判断字段名中是否包含产品描述，如果包含则高亮显示
								if(goodsdespFields.length != 0)
								{
									for(int j = 0 ; j <goodsdespFields.length; j++)
									{
										if(fields[i].equals(goodsdespFields[j]))
										{
											proDescCellNum=i;
											break;
										}
									}
								}
							}
							
							rowNum++;
							
							if (!data.isEmpty()) 
							{
								//单个SHEET数据行记录
								int sheetRecNum = 0;
								
								// 加入数据
								// data先查单条数据拼接成字符串数组，在放入集合
								for (int i = recNum; i < data.size(); i++,recNum++, rowNum++, sheetRecNum++) 
								{
									if (null != data.get(i) && 0 != data.get(i).length) 
									{
										// 单个SHEET数据行超过SHEET应达到数据限额，则停止写入
										if (sheetRecNum >= IConstantUtil.SHEETSIZE)
										{
											break;
										}
										
										for (int j = 0; j < data.get(i).length; j++) 
										{
											if(j==importerCellNum&&j!=0)
											{
												//生产进口商的超链接
												sheet.addHyperlink(new WritableHyperlink(j,rowNum,0,0,new URL("http://www.baidu.com/s?ie=utf-8&wd="+data.get(i)[j]+"&cl=3"),data.get(i)[j]));
											}else if(j==exporterCellNum&&j!=0)
											{
												//生产出口商的超链接
												sheet.addHyperlink(new WritableHyperlink(j,rowNum,0,0,new URL("http://www.baidu.com/s?ie=utf-8&wd="+data.get(i)[j]+"&cl=3"),data.get(i)[j]));	
											}else if(j==hscodeCellNum&&j!=0){
												
												sheet.addCell(new Label(j, rowNum, data.get(i)[j],blueFontFormat));
											
											}else if(j==proDescCellNum&&j!=0){
												
												sheet.addCell(new Label(j, rowNum, data.get(i)[j],blueFontFormat));
											}
											else
											{
												// 将生成的单元格添加到工作表中。
												sheet.addCell(new Label(j, rowNum, data.get(i)[j],fontFormat));
											}
											
										}
									}	
								}
							}
							
							// 数据写完，退出
							if (recNum >= data.size())
							{
								recNum=0;
								break;
							}
						}
						
						dataIndex++;
						
						// 重新命名表空间名
						workSheet = sheetName + dataIndex;
						
						sheet = wb.createSheet(workSheet, sheetNum);
					}
					
					countryNum++;
					
					//判断国家是否都已经结束写入
					if(countryNum>=countrysData.size())
					{
						break;
					}
				}
				// 写入到文件
				wb.write();
				wb.close();
			} 
			catch (Exception e)
			{
				log.debug(e.getMessage());
		    }
			finally
			{
				if(os != null)
				{
					try {
						os.close();
					} catch (IOException e) {
						log.debug(e.getMessage());
					}
				}
			}
		}
	}
}
