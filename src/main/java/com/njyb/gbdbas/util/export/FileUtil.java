package com.njyb.gbdbas.util.export;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 操作文件工具类
 * @author XL
 * @date 2015-03-25
 * @version 标准版
 */
public class FileUtil {

	// 日志
	private static final Logger log = Logger.getLogger(FileUtil.class);

	// 生成zip压缩文件存放的路径
	private static String zipPath = "exportExcel/zip/";

	/**
	 * 下载压缩文件
	 * @param zipFileName 文件名称
	 * @param srcfile 需要打包的文件集合
	 * @param request
	 * @param response
	 * @return
	 */
	public static HttpServletResponse downLoadZipFile(String zipFileName, List<File> srcfile, HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			// 文件保存目录路径
			String savePath = request.getSession().getServletContext().getRealPath("/") + zipPath;
			savePath += "/" + zipFileName + ".zip";
			// 创建一个临时压缩文件，把文件流全部注入到这个文件中
			File zipfile = new File(savePath);
			// 如果没有此文件就创建一个
			if (!zipfile.exists())
			{
				zipfile.createNewFile();
			}
			response.reset();
			// 创建文件输出流
			FileOutputStream os = new FileOutputStream(zipfile);
			// 创建zip文件夹
			ZipOutputStream out = new ZipOutputStream(os);
			// 压缩文件
			zipFiles(srcfile, out);
			out.close();
			os.close();
			return downloadFile(zipfile, response);
		} 
		catch (IOException e) 
		{
			log.debug("FileUtil zipFiles exception:" + e.getMessage());
		}
		return response;
	}
	
	/**
	 * 下载导出服务器的文件
	 * 
	 * @param filePath 下载路径
	 * @param filename 文件名
	 * @param response
	 * @return
	 */
	public static boolean downloadFile(String filePath, String filename,HttpServletResponse response) 
	{
		// 输入流
		InputStream fis = null;
		// 输出流
		OutputStream toClient = null;
		File file = new File(filePath);
		//判断是否为文件
		if (file.exists()) 
		{
			try 
			{
				// 以流的形式下载文件
				fis = new BufferedInputStream(new FileInputStream(filePath));
				// available()返回的实际可读字节数,也就是总大小
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				response.reset();
				response.addHeader("Content-Disposition","attachment;filename=" + new String(filename.getBytes(), "iso8859-1"));
				// 设置返回的文件类型
				response.addHeader("Content-Length", "" + file.length());
				toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");
				// 输出数据
				toClient.write(buffer);
				return true;
			} 
			catch (Exception e) 
			{
				log.debug(e.getMessage());
			} 
			finally 
			{
				try 
				{
					if (null != fis) 
					{
						fis.close();
					}
					if (null != toClient) 
					{
						toClient.flush();
						toClient.close();
					}
				} 
				catch (IOException e) 
				{
					log.debug(e.getMessage());
				}
			}
		} 
		else 
		{
			return false;
		}
		return false;
	}
	
	/**
	 * 下载导出服务器的文件
	 * @param file 下载的文件
	 * @param response
	 * @return
	 */
	 public static HttpServletResponse downloadFile(File file,HttpServletResponse response)
	 {
		 
		 InputStream fis = null;
		 OutputStream toClient = null;
		 
		 //判断是否为文件
		 if (file.exists()) 
		 {
			 try
		        {
			        // 以流的形式下载文件
			        fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			        // available()返回的实际可读字节数,也就是总大小
			        byte[] buffer = new byte[fis.available()];
			        fis.read(buffer);
			        // 清空response
			        response.reset();
			        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
			        // 设置返回的文件类型
			        response.addHeader("Content-Length", "" + file.length());
			        toClient = new BufferedOutputStream(response.getOutputStream());
			        response.setContentType("application/octet-stream");
			        // 输出数据
					toClient.write(buffer);
		        } 
		        catch (IOException e) 
		        {
		        	log.debug(e.getMessage());
		        }
		        finally
		        {
		             try 
		             {
	            	    if (null != fis) 
						{
							fis.close();
						}
						if (null != toClient) 
						{
							toClient.flush();
							toClient.close();
						}
		            	//删除出服务器上的压缩文件
		                deleteFile(file.getPath());
		             } 
		             catch (Exception e) 
		             {
		            	log.debug(e.getMessage());
		             }
		        }
		 }
		 return response;
	 }

	/**
	 * 把全部文件打成压缩包
	 * 
	 * @param files
	 *            想要打包的所有文件
	 * @param outputStream
	 *            以 ZIP文件格式写入文件实现输出流过滤器
	 */
	public static void zipFiles(List<File> files, ZipOutputStream outputStream) 
	{
		// 获取文件个数
		int size = files.size();
		for (int i = 0; i < size; i++)
		{
			// 获取文件
			File file = (File) files.get(i);
			// 对文件进行打包
			zipFile(file, outputStream);
		}
	}

	/**
	 * 根据输入的文件与输出流对文件进行打包
	 * 
	 * @param inputFile
	 *            文件
	 * @param ouputStream
	 *            以 ZIP文件格式写入文件实现输出流过滤器
	 */
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) 
	{
		try 
		{
			// 判断文件是否存在
			if (inputFile.exists()) 
			{
				// 判断是否是文件
				if (inputFile.isFile()) 
				{
					// 创建文件输入流
					FileInputStream in = new FileInputStream(inputFile);
					// 创建具有指定缓冲区大小的 BufferedInputStream 并保存其参数，即输入流 in
					BufferedInputStream bins = new BufferedInputStream(in, 512);
					// 使用指定名称创建新的 ZIP条目
					ZipEntry entry = new ZipEntry(inputFile.getName());
					// 开始写入新的 ZIP文件条目并将流定位到条目数据的开始处。
					ouputStream.putNextEntry(entry);
					// 向压缩文件中输出数据
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) 
					{
						// 将字节数组写入当前 ZIP条目数据
						ouputStream.write(buffer, 0, nNumber);
					}
					// 关闭创建的流对象
					bins.close();
					// 关闭输入流
					in.close();
				} 
				else 
				{
					try 
					{
						// 返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++)
						{
							zipFile(files[i], ouputStream);
						}
					} 
					catch (Exception e) 
					{
						log.debug(e.getMessage());
					}
				}
			}
		} 
		catch (Exception e) 
		{
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * 删除单个文件
	 * 
	 * @param sPath 被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) 
	{
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) 
		{
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 将字符串数组往txt文件内写入
	 * @param ids 所有查看详情需要翻译的id
	 * @param fileUrl 创建txt文件路径
	 */
	public static void writeText(Set<String> ids,String fileUrl){
		//创建文件
		File txt = new File(fileUrl);
		OutputStream out = null;
		try {
			//判断文件是否存在
			if(!txt.exists())
			{
				//创建文件
				txt.createNewFile();
			}
			//创建文件输出流
			out = new FileOutputStream(txt);
			//循环忘文件内写入
			for(String id :ids)
			{
				out.write(id.getBytes());
				//换行写入
				out.write('\r');
				out.write('\n');
			}
		} 
		catch (IOException e) 
		{
			log.debug(e.getMessage());
		}
		finally
		{
			try 
			{
				//关闭输出流
				out.close();
			} 
			catch (IOException e) 
			{
				log.debug(e.getMessage());
			}
		}
	}

}
