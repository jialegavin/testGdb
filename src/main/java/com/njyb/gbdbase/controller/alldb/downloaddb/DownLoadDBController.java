package com.njyb.gbdbase.controller.alldb.downloaddb;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Strings;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.export.ExportExcelUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.service.alldb.competitor.IMarketAnalysisReportService;
import com.njyb.gbdbase.service.alldb.downloaddb.IDownLoadDBService;
/**
 * 下载全库
 * @author WangBo
 * 2015年5月6日
 * DownLoadDBController.java
 */
@RequestMapping(value="/downloadDB")
@Controller
public class DownLoadDBController extends PublicCommonController {

	// 全库Service
	@Autowired
	private IMarketAnalysisReportService marketAnalysisReportService;
	
	// 下载全库
	@Autowired
	private IDownLoadDBService downLoadDBService;
	
	/**
	 * 下载全库
	 * @param request
	 * @param response
	 * @param rightLibrarySearchModel
	 * @throws IOException 
	 */
	@RequestMapping(value="/downLoadDBByParams")
	public String downLoadDBByParams(HttpServletRequest request,HttpServletResponse response,
			RightLibrarySearchModel rightLibrarySearchModel) throws IOException{
		rightLibrarySearchModel.setCountrySelect(java.net.URLDecoder.decode(rightLibrarySearchModel.getCountrySelect(), "UTF-8"));
		rightLibrarySearchModel.setGoodsdescription(java.net.URLDecoder.decode(rightLibrarySearchModel.getGoodsdescription(),"UTF-8"));
		if (Strings.isNullOrEmpty(rightLibrarySearchModel.getCountrySelect())) {		//如果国家为空,则跳回页面
			return "alldb/downloadDBData/downLoadAllData";
		}	
		PageBeanUtil pageBean = this.getPageBean(request);
		Map<String,Object> paramMap = marketAnalysisReportService.setMarketAnalysisReportFields(request, rightLibrarySearchModel);
		paramMap.put("queryModel", rightLibrarySearchModel);
		paramMap.put("pageBean", pageBean);
		paramMap.put("request", request);
		Map<String,Map<String,Object>> countryDate = downLoadDBService.downLoadDBByParams(paramMap);
		try {
			ExportExcelUtil.exportAllDataExcel("message_zh_CN", countryDate, new String[]{},  new String[]{},  new String[]{"海关编码"},  new String[]{"产品描述"}, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "alldb/downloadDBData/downLoadAllData";
	}
}