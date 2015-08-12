package com.njyb.gbdbase.controller.datasearch.export;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.njyb.auth.service.authoritycontrol.component.AuthorityDateUtil;
import com.njyb.auth.service.authoritycontrol.component.CommonConstantUtil;
import com.njyb.auth.service.authoritycontrol.component.MainAuthoriy;
import com.njyb.auth.service.impl.cmp.IOrderCountCmp;
import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbas.util.export.ExportPDFUtil;
import com.njyb.gbdbas.util.pagetemplate.ExportViewDetailPdfUtil;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
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
	@Resource
	private AuthorityFieldModel authorityFieldModel;
	@Autowired
	private IOrderCountCmp orderCount;
	
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
		
		/**
		 * 权限控制
		 */
		// TODO 获取用户session 的 UserModel, 从js端带过来 英文国家名称, 和 CommonAuthService.COUNT_USER
		// TODO UserModel.getUserDesc 角色权限, 判断是否是 按次用户,如果是, 调用 dataSearchService.queryUserBuyerList  获得用户所购买的国家数据集合,将其生成execl和PDF
		// TODO 如果不是, 将走 原来的方法
		JSONObject jsonObject = new JSONObject();
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		//控制按次用户是否查看了数据是否可以下载
		authorityFieldModel.getAuthorityFieldMap().put("userId", user.getUserId());
		authorityFieldModel.getAuthorityFieldMap().put("type", user.getUserDesc());
		authorityFieldModel.getAuthorityFieldMap().put("country_en", request.getSession().getAttribute("country"));
		if(user.getUserDesc().equals("按次用户")){
			List list = orderCount.queryUserBuyerList(authorityFieldModel.getAuthorityFieldMap());
			if(list == null || list.size()<1){
				jsonObject.put("error", CommonConstantUtil.PROMPT_AUTHORITY+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
				jsonObject.put("total", 0);
				jsonObject.put("rows", "");
				response.getWriter().write(jsonObject.toString());
				return null;
			}
		}
//		if(MainAuthoriy.isVisitModule(request, response, ((UserModel)request.getSession().getAttribute("user")).getUserDesc(), authorityFieldModel.getAuthorityFieldMap(), jsonObject)){
			// 获取下载类型
			int type = Integer.parseInt(request.getParameter("type"));
			// 切换数据源
			return exportDownloadService.commonExportService(jsonObject, type, response, request);
//		}
//		response.getWriter().write(jsonObject.toString());
//		return null;
		
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
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value = "/downloadReport", method = RequestMethod.GET)
	public String downloadReport(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{	
		/**
		 * 添加模块控制
		 */
		JSONObject jsonObject = new JSONObject();
//		if(MainAuthoriy.isVisitModule(request, response, ((UserModel)request.getSession().getAttribute("user")).getUserDesc(), authorityFieldModel.getAuthorityFieldMap(), jsonObject)){
			// 获取map对象
			Map map = resultFieldModel.getResultFieldMap();
			//获取国家
			String country = (String) request.getSession().getAttribute("country");
			//调用导出
			exportDataService.exportSummaryReportByCountry(country, map, request, response);
//		}
		return null;
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
		 /**
		  * 权限控制
		  */
		JSONObject jsonObject = new JSONObject();
//		if(MainAuthoriy.isVisitModule(request, response, ((UserModel)request.getSession().getAttribute("user")).getUserDesc(), authorityFieldModel.getAuthorityFieldMap(), jsonObject)){
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
//		}
		response.getWriter().write(jsonObject.toString());
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
		/**
		 * 权限控制
		 */
		JSONObject jsonObject = new JSONObject();
//		if(MainAuthoriy.isVisitModule(request, response, ((UserModel)request.getSession().getAttribute("user")).getUserDesc(), authorityFieldModel.getAuthorityFieldMap(), jsonObject)){
			if(request.getParameter("country")!=null){
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
			}else{
				try {
					response.getWriter().write(jsonObject.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
//		}else{
//			try {
//				response.getWriter().write(jsonObject.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	/**
	 * 打印详情
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/printDetial")
	public void printDetial(HttpServletRequest request,HttpServletResponse response){
//		/**
//		 * 权限控制
//		 */
//		JSONObject jsonObject = new JSONObject();
//		if(MainAuthoriy.isVisitModule(request, response, ((UserModel)request.getSession().getAttribute("user")).getUserDesc(), authorityFieldModel.getAuthorityFieldMap(), jsonObject)){
//			jsonObject.put("success", "yes");
//		}
//		try {
//			response.getWriter().write(jsonObject.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
