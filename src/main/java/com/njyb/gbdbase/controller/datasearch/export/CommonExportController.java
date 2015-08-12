package com.njyb.gbdbase.controller.datasearch.export;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbas.util.export.ExportPDFUtil;
import com.njyb.gbdbas.util.pagetemplate.ExportViewDetailPdfUtil;
import com.njyb.gbdbase.model.datasearch.common.ExportResultFieldModel;
import com.njyb.gbdbase.model.datasearch.common.ResultFieldModel;
import com.njyb.gbdbase.service.datasearch.export.report.IExportDataService;
import com.njyb.gbdbase.service.datasearch.export.search.IExportDownloadService;


/**
 * @Description 搜索报表模块导出PDF及excel控制层
 * @author XL
 * @date 2015-03-10
 * @version 标准版
 */
@Controller
@RequestMapping("/export")
public class CommonExportController{
	
	@Autowired
	private IExportDownloadService exportDownloadService;
	@Autowired
	private IExportDataService exportDataService;
	@Autowired
	private ResultFieldModel resultFieldModel;
	@Autowired
	private ExportResultFieldModel exportResultFieldModel;
	
	/**
	 * 导出excel或PDF前先判断用户是否满足需求
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadService", method = RequestMethod.POST)
	public @ResponseBody <T> String downloadService(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		JSONObject jsonObject = new JSONObject();
		// 获取下载类型
		int type = Integer.parseInt(request.getParameter("type"));
		// 切换数据源
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		return exportDownloadService.commonExportService(jsonObject, type, response, request);
	}

	/**
	 * 导出Excel或者PDF
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/downloadData")
	public void downloadData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 获取下载类型
		String type = request.getParameter("type");		
		int exportType = Integer.parseInt(type);
		// 导出EXCEL/PDF
		exportDownloadService.exportFiles(exportType,request, response);
	}
	
	/**
	 * 每月1号0点0分30秒触发任务
	 * 自动将所有的EXCEL/PDF下载数据的条数清零
	 */ 
	@Scheduled(cron="30 0 0 1 * ?")
	public void clearExcelNum(){
		exportDownloadService.clearDownloadRecord();
	}
	
	/**
	 * 导出报表汇总excel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadReport", method = RequestMethod.GET)
	public void downloadReport(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//获取国家
		String country = (String) request.getSession().getAttribute("country");
		//调用导出
		exportDataService.exportSummaryReportByCountry(country, map, request, response);
	}
	
	/**
	 * 生成查看详情pdf模板
	 * @param request
	 * @param response
	 * @throws Exception
	 * @auther honghao
	 */
	@RequestMapping(value = "/generatePdfTemplate", method = RequestMethod.GET)
	public <T> void generatePdfTemplate(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		 //将查询结果放入缓存中
		 Cache ehCache = CreateEncache.getEacheInstance().getCache("dataSearchCache");
		 ehCache.acquireReadLockOnKey("detailData");
		 Element element = ehCache.get("detailData");
		 ehCache.releaseReadLockOnKey("detailData");
		 T model = (T) element.getValue();
		 String htmlData =ExportViewDetailPdfUtil.fmtHtml(model, request);
		 //文件流将html页面代码写入页面中
		 FileWriter fw = null;
		 String rootPath = request.getSession().getServletContext().getRealPath("/");
		 File f = new File(rootPath+DataSearchConstantUtil.PDF_TEMPALATEPATH);
		 try {
			    if(!f.exists()){
			     f.createNewFile();
			    }
			    fw = new FileWriter(f);
			    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"utf-8"));
			    out.write(htmlData, 0, htmlData.length()-1);
			    out.close();
		    } catch (Exception e) {
			   e.printStackTrace();
		    }
	}
	
	/**
	 * 将查看详情页面导出为pdf格式
	 * @param request
	 * @param response
	 * @throws Exception
	 * @auther honghao
	 */
	@RequestMapping(value = "/exportPdf", method = RequestMethod.GET)
	public void exportPdf(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		// 导出PDF
		ExportPDFUtil.exportPdf(DataSearchConstantUtil.PDF_TEMPALATEPATH, DataSearchConstantUtil.TEMPALTE_NAME, request, response);
	}
	 
	/**
	 * 导出报表汇总明细数据
	 * @param request
	 * @param response
	 * @author XL
	 */
	@RequestMapping(value="/exportSummaryDetail")
	public <T> void exportSummaryDetail(HttpServletRequest request,HttpServletResponse response)
	{
		//获取当前的报告类型
		String reportType = request.getParameter("columnKey");
		//当前需要查看明细的值
		String comparValue = request.getParameter("columnValue");
		//获取国家
		String country = request.getParameter("country");
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//导出汇总明细
		exportDataService.exportSummaryDetail(reportType, country, comparValue, map, request, response);
	}
}
