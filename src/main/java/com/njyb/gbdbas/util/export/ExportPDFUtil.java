package com.njyb.gbdbas.util.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.njyb.gbdbas.util.IConstantUtil;

/**
 * @Description 导出PDF类
 * @author XL
 * @date 2015-03-07
 * @version 标准版
 */
public class ExportPDFUtil {

	//记录日志
	private static final Logger log = Logger.getLogger(ExportPDFUtil.class);
	
	/**
	 * * 导出PDF
	 * 
	 * @param document
	 *            创建一个Document对象
	 * @param maxSize
	 *            创建table的最大宽度
	 * @param tableName
	 *            导出的文件名
	 * @param data
	 *            数据集合
	 * @param fields
	 *            所有列名
	 * @param request
	 * @param response
	 */
	public static void downloadPDF(Document document, int maxSize, String tableName, List<String[]> data, 
			String[] fields, HttpServletRequest request, HttpServletResponse response) 
	{
		// 替换所有表名的空格
		tableName = tableName.replaceAll("\\s*", "");
		OutputStream os = null;
		
		// 1.创建一个Document对象
		try 
		{
			// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档 写入到磁盘中。
			// 创建PdfWriter 对象第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
			os = response.getOutputStream();
			// 清空buffer,设置页面不缓存 
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String((tableName + ".pdf").getBytes(), "iso8859-1"));
			response.setContentType("application/octet-stream");
			// 3.将Document实例和文件输出流用PdfWriter类绑定在一起
			PdfWriter.getInstance(document, os);
			// 4.设置文字字体样式
			// 中文字符问题 需要多导入一个IText-asian.jar
			// BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			BaseFont bfChinese = BaseFont.createFont("/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// 标题字体
			Font bold_fontChinese = new Font(bfChinese, 14, Font.NORMAL);
			Font italic_fontChinese = new Font(bfChinese, 10, Font.UNDEFINED);
			// 5.打开文档
			document.open();
			if (null != fields && 0 != fields.length) 
			{
				// 标题
				Paragraph title = new Paragraph();
				// 设置页面格式
				// 设置段落前后的间距
				title.setSpacingBefore(8);
				title.setSpacingAfter(2);
				// 段落居中 参数也可以设置为1
				title.setAlignment(Element.ALIGN_CENTER);
				// 设置PDF标题
				// 标题放入文本块
				title.add(new Chunk(tableName, new Font(bfChinese, 25, Font.BOLD)));
				// 在文档中添加标题
				document.add(title);
				// 换行
				document.add(new Chunk("\n"));
				// 6.创建table
				PdfPTable table = new ExportPDFUtil().createTable(maxSize, fields.length);
				// 7.设置背景颜色
				BaseColor backgroundColor = new BaseColor(213, 141, 69);
				// 设置每一页都有表头
				// 设置了头一行为表格头
				table.setHeaderRows(1);
				// 8.输出列名
				for (String field : fields) 
				{
					table.addCell(new ExportPDFUtil().createCell(field, bold_fontChinese, Element.ALIGN_CENTER, backgroundColor));
				}
				// 9.输出数据
				for (int i = 0; i < data.size(); i++) 
				{
					//判断是否有数据
					if (null != data.get(i) && 0 != data.get(i).length) 
					{
						for (int j = 0; j < data.get(i).length; j++) 
						{
							//添加每行每列的数据
							table.addCell(new ExportPDFUtil().createCell(data.get(i)[j], italic_fontChinese, Element.ALIGN_CENTER));
						}
					}
				}
				// 10.绘制TABLE
				document.add(table);
				// 11.关闭文档
				document.close();
			}
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
					// 关闭文件流
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
	 * 创建列
	 * 
	 * @param value
	 *            文本
	 * @param font
	 *            字体样式
	 * @param align
	 *            对齐方式
	 * @return
	 */
	public PdfPCell createCell(String value, com.itextpdf.text.Font font, int align) 
	{
		PdfPCell cell = new PdfPCell();
		// 文本垂直对齐方式
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// 文本水平对齐方式
		cell.setHorizontalAlignment(align);
		// 插入文本及设置字体样式
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 创建带背景的列
	 * 
	 * @param value
	 *            文本
	 * @param font
	 *            字体样式
	 * @param align
	 *            对齐方式
	 * @param backgroundColor
	 *            背景颜色
	 * @return
	 */
	public PdfPCell createCell(String value, com.itextpdf.text.Font font, int align, BaseColor backgroundColor) 
	{
		PdfPCell cell = new PdfPCell();
		// 设置背景颜色
		cell.setBackgroundColor(backgroundColor);
		// 文本垂直对齐方式
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// 文本水平对齐方式
		cell.setHorizontalAlignment(align);
		// 插入文本及设置字体样式
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 创建表
	 * 
	 * @param value 文本
	 * @param rowspan 合并的单元格数
	 * @param font 字体
	 * @param align 对齐方式
	 * @param backgroundColor 背景颜色
	 * @return
	 */
	public PdfPCell createCell(String value, int rowspan, com.itextpdf.text.Font font, int align, BaseColor backgroundColor) 
	{
		PdfPCell cell = new PdfPCell();
		// rowspan代表从当前单元格的位置开始，合并的单元格数
		cell.setRowspan(rowspan);
		cell.setPadding(3.0f);
		// 设置背景颜色
		cell.setBackgroundColor(backgroundColor);
		// 文本垂直对齐方式
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// 文本水平对齐方式
		cell.setHorizontalAlignment(align);
		// 插入文本及设置字体样式
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 创建列
	 * 
	 * @param value
	 *            文本
	 * @param font
	 *            文本字体样式
	 * @return
	 */
	public PdfPCell createCell(String value, com.itextpdf.text.Font font) 
	{
		// 定义一个表格单元 
		PdfPCell cell = new PdfPCell();
		// 设置对齐方式
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// 插入文本及设置字体样式
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 创建列
	 * 
	 * @param value
	 *            文本
	 * @param font
	 *            字体样式
	 * @param align
	 *            对齐方式
	 * @param colspan
	 *            跨列
	 * @return
	 */
	public PdfPCell createCell(String value, com.itextpdf.text.Font font, int align, int colspan) 
	{
		// 定义一个表格单元 
		PdfPCell cell = new PdfPCell();
		// 设置对齐方式
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		// 设置跨列的值
		cell.setColspan(colspan);
		// 插入文本及设置字体样式
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 创建列
	 * 
	 * @param value
	 *            文本
	 * @param font
	 *            字体样式
	 * @param align
	 *            对齐方式
	 * @param colspan
	 *            跨列
	 * @param boderFlag
	 *            是否设置边框 值为true：有边框，值为false:无边框
	 * @return
	 */
	public PdfPCell createCell(String value, com.itextpdf.text.Font font, int align, int colspan, boolean boderFlag) 
	{
		// 定义一个表格单元 
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// 设置水平对齐方式
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		// 插入文本及设置字体样式
		cell.setPhrase(new Phrase(value, font));
		// 某一单元(如:某行/列)里有效
		cell.setPadding(3.0f);
		// 值为true：有边框，值为false:无边框
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(8.0f);
		}
		return cell;
	}

	/**
	 * 创建表
	 * 
	 * @param colNumber
	 *            列数
	 * @return
	 */
	public PdfPTable createTable(int maxWidth, int colNumber) 
	{
		// 创建一个有colNumber列的表格
		PdfPTable table = new PdfPTable(colNumber);
		try 
		{
			// 1.设置表格的总宽度 table.setTotalWidth(maxWidth);
			// 2.设置表格的各列宽度 table.setTotalWidth(float[] columnWidth);
			table.setTotalWidth(maxWidth);
			// 使用以上两个函数，必须使用以下函数，将宽度锁定
			table.setLockedWidth(true);
			// 设置文本水平对齐方式
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			// 设置表格边框
			table.getDefaultCell().setBorder(1);
		}
		catch (Exception e) 
		{
			log.debug(e.getMessage());
		}
		return table;
	}

	/**
	 * 创建表
	 * 
	 * @param widths
	 *            设置表格的各列宽度
	 * @return
	 */
	public PdfPTable createTable(float[] widths) 
	{
		// 设置表格的各列宽度创建表格
		PdfPTable table = new PdfPTable(widths);
		try
		{
			// 1.设置表格的总宽度 table.setTotalWidth(maxWidth);
			table.setTotalWidth(IConstantUtil.MAXWIDTH);
			// 2.将宽度锁定
			table.setLockedWidth(true);
			// 3.设置文本对齐方式
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			// 4.设置表格边框
			table.getDefaultCell().setBorder(1);
		}
		catch (Exception e) 
		{
			log.debug(e.getMessage());
		}
		return table;
	}

	/**
	 * 绘制表
	 * 
	 * @param font 字体
	 * @return
	 */
	public PdfPTable createBlankTable(com.itextpdf.text.Font font)
	{
		// 创建1列的表格
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(0);
		table.addCell(createCell("", font));
		table.setSpacingAfter(20.0f);
		table.setSpacingBefore(20.0f);
		return table;
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
			String[] tableFields = new String[fields.size() - 1];
			for (int i = 0; i < fields.size() - 1; i++) 
			{
				tableFields[i] = fields.get(i + 1).get("COLUMN_NAME");
			}
			return tableFields;
		}
		return null;
	}

	/**
	 * 将HTML导出PDF
	 * 
	 * @param htmlUrl
	 *            HTML路径地址
	 * @param fileName
	 *            生成PDF文件名
	 * @param request
	 * @param response
	 */
	public static void exportPdf(String htmlUrl, Object fileName, HttpServletRequest request, HttpServletResponse response) 
	{
		OutputStream os = null;
		try 
		{
			// 获取HTML地址
			String inputFile = htmlUrl;
			if (null != htmlUrl && !"".equals(htmlUrl)) 
			{
				String rootPath = request.getSession().getServletContext().getRealPath("/");
				String url = new File(rootPath+inputFile).toURI().toURL().toString();
				// 创建输出流对象
				os = response.getOutputStream();
				// 清空buffer,设置页面不缓存 
				response.reset();
				// 设置pdf名称
				String param = fileName + ".pdf";
				// 替换空格
				if (param.contains(" ")) 
				{
					param = param.replace(" ", "-");
				}
				response.addHeader("Content-Disposition","attachment;filename=" + new String(param.getBytes(), "iso8859-1"));
				response.setContentType("application/octet-stream");
				// 实例ITextRenderer，加载html文档
				ITextRenderer renderer = new ITextRenderer();
				renderer.setDocument(url);
				// 解决中文支持问题
				ITextFontResolver fontResolver = renderer.getFontResolver();
				fontResolver.addFont("/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				// 解决图片的相对路径问题
				renderer.getSharedContext().setBaseURL(request.getSession().getServletContext().getRealPath("/static/img"));
				renderer.layout();
				//创建pdf
				renderer.createPDF(os);
				
			}
		} 
		catch (Exception e) 
		{
			log.debug(e.getMessage());
		}
		finally
		{
			if(os !=null)
			{
				try 
				{
					//关闭文件流
					os.close();
				} 
				catch (IOException e) 
				{
					log.debug(e.getMessage());
				}
			}
		}
	}

	private static ServletRequest getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 导出图形报表PDF
	 * @param document  创建一个Document对象
	 * @param url 报表图片路径
	 * @param maxSize 设置表格的总宽度
	 * @param tableName 标题名称
	 * @param textValue 文本
	 * @param data 数据
	 * @param fields 列名
	 * @param rowIndex 现在输出的条数，值为0：不限制
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void downloadReportPDF(Document document, String url, int maxSize, String tableName, String textValue,
			List<String[]> data, String[] fields, int rowIndex, HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		// 替换所有空格
		tableName = tableName.replaceAll("\\s*", "");
		String targetFile = null;
		FileOutputStream out = null;
		// 水印图片
		String image = "";
		float widths = 0f;
		float heights = 0f;
		// 1.创建一个Document对象
		// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		try 
		{
			targetFile = "C:\\" + tableName + ".pdf";
			// 创建文件实例
			File file = new File(targetFile);
			// 创建文件输出流
			out = new FileOutputStream(file);
			// 创建PdfWriter 对象第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
			PdfWriter.getInstance(document, out);
			// 4.设置文字字体样式
			// 中文字符问题 需要多导入一个IText-asian.jar
			// BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
			// "UniGB-UCS2-H", false);
			BaseFont bfChinese = BaseFont.createFont("/SIMYOU.TTF",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// 标题字体
			Font bold_fontChinese = new Font(bfChinese, 14, Font.NORMAL);
			Font italic_fontChinese = new Font(bfChinese, 10, Font.UNDEFINED);
			document.open();
			if (null != fields && 0 != fields.length) 
			{
				// 选择图片
				Image img = Image.getInstance(url);
				img.setAlignment(Image.ALIGN_CENTER);
				document.add(img);

				// 创建文本table
				PdfPTable texttable = new ExportPDFUtil().createTable(maxSize,1);
				// 设置表格上面空白宽度(代替换行)
				texttable.setSpacingBefore(20f);
				BaseColor textbackgroundColor = new BaseColor(214, 200, 75);
				texttable.addCell(new ExportPDFUtil().createCell(textValue, italic_fontChinese, Element.ALIGN_MIDDLE, textbackgroundColor));
				document.add(texttable);

				// 5.创建table
				PdfPTable table = new ExportPDFUtil().createTable(maxSize, fields.length);
				// 设置表格上面空白宽度 (代替换行)
				table.setSpacingBefore(20f);
				table.setHeaderRows(1);
				// 6.设置背景颜色
				BaseColor backgroundColor = new BaseColor(167, 220, 224);
				// 7.输出列名
				for (String field : fields) 
				{
					table.addCell(new ExportPDFUtil().createCell(field, bold_fontChinese, Element.ALIGN_CENTER, backgroundColor));
				}
				// 8.输出数据
				for (int i = 0; i < data.size(); i++) 
				{
					if (null != data.get(i) && 0 != data.get(i).length) 
					{
						for (int j = 0; j < data.get(i).length; j++) 
						{
							table.addCell(new ExportPDFUtil().createCell(data.get(i)[j], italic_fontChinese, Element.ALIGN_CENTER));
						}
						if (rowIndex != 0) 
						{
							if (i == rowIndex - 1) 
							{
								break;
							}
						}
					}
				}
				// 获取页面宽度
				widths = document.getPageSize().getWidth();
				// 获取页面高度
				heights = document.getPageSize().getHeight();
				// 9.绘制TABLE
				document.add(table);
				// 10.关闭文档
				document.close();
			}
		} 
		catch (Exception e) 
		{
			log.debug(e.getMessage());
		}
		finally 
		{
			if (out != null) 
			{
				try 
				{
					// 关闭输出文件流
					out.close();
				} 
				catch (IOException e1) 
				{
					log.debug(e1.getMessage());
				}
			}
		}

		// 获取页数
		try 
		{
			PdfReader reader = new PdfReader(targetFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("C:\\" + tableName + "2.pdf"));
			int total = reader.getNumberOfPages();
			for (int i = 1; i <= total; i++) 
			{
				PdfContentByte under = stamper.getUnderContent(i);
				// 设定半透明
				PdfGState gstate = new PdfGState();
				gstate.setFillOpacity(0.2f);
				gstate.setStrokeOpacity(0.2f);
				under.setGState(gstate);
				float height = 0.0f;
				// 水印图片
				if (null != image && !image.equals(""))
				{
					String path = request.getSession().getServletContext().getRealPath("");
					File file = new File(path + "\\" + image);
					if (file.exists()) 
					{
						height = new ExportPDFUtil().pdfWaterImage(under, widths, heights, image, request);
					}
				}
			}
			stamper.close();
		} 
		catch (Exception e) 
		{
			log.debug(e.getMessage());
		}
		// 手动下载
		FileUtil.downloadFile("C:\\" + tableName + "2.pdf", tableName + ".pdf", response);
		// 删除服务器文件
		FileUtil.deleteFile(targetFile);
		FileUtil.deleteFile("C:\\" + tableName + "2.pdf");
	}

	/**
	 * 添加水印图片
	 */
	public float pdfWaterImage(PdfContentByte under, float widths, float heights, String image, HttpServletRequest request)throws Exception 
	{
		// 获取绝对路径
		String path = request.getSession().getServletContext().getRealPath("");
		Image images = Image.getInstance(path + "\\" + image);
		// 获取图片宽度和高度
		float width = images.getWidth();
		float height = images.getHeight();
		height = 600 / width * height;
		// 图片位置
		images.setAbsolutePosition((widths - 600) / 2, (heights - height) / 2);
		// 将宽度设为100，高度按比例缩小
		if (width > 600) 
		{
			// 设定显示尺寸
			images.scaleAbsolute(600, height);
		}
		// 旋转
		images.setRotationDegrees(40);
		// 添加水印图片
		under.addImage(images);
		return height;
	}

}
