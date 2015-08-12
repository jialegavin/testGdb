package com.njyb.gbdbase.controller.alldb.projectanalyze;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Strings;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.sort.DataGridSortUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.alldb.projectAnalyze.BuyerModel;
import com.njyb.gbdbase.model.alldb.projectAnalyze.ProjectAnalyzeModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ResultFieldModel;
import com.njyb.gbdbase.service.alldb.competitor.IMarketAnalysisReportService;
import com.njyb.gbdbase.service.alldb.projectAnalyze.IProjectAnalyzeService;

/**
 * 产品标签 {权库分析}
 * 
 * @author WangBo 2015年4月20日 ProjectanalyController.java
 */
@RequestMapping(value = "/projectAnalyze")
@Controller
public class ProjectAnalyzeController extends PublicCommonController {
	
	// 日志
	private static final Logger log = Logger.getLogger(ProjectAnalyzeController.class);
	
	// 权库分析
	@Autowired
	private IProjectAnalyzeService projectAnalyzeService;

	// 市场分析报表
	@Autowired
	private IMarketAnalysisReportService marketAnalysisReportService;

	/**
	 * 添加 产品标签
	 * 
	 * @param request
	 * @param response
	 * @param projectAnalyzeModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/addProjectAnalyze")
	public void addProjectAnalyze(HttpServletRequest request,
			HttpServletResponse response,
			ProjectAnalyzeModel projectAnalyzeModel){
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		projectAnalyzeModel.setUserId(userModel.getUserId());
		int result = projectAnalyzeService
				.addProjectAnalyze(projectAnalyzeModel);
		Map<String, Object> paramMap = this.getNewMap();
		paramMap.put("result", result);
		this.setJsonParamMap(paramMap);
		queryProjectAnalyzeByParam(request, response, projectAnalyzeModel);
	}

	/**
	 * 根据条件查询 产品标签
	 * 
	 * @param request
	 * @param response
	 * @param projectAnalyzeModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryProjectAnalyzeByParam")
	public void queryProjectAnalyzeByParam(HttpServletRequest request,
			HttpServletResponse response,
			ProjectAnalyzeModel projectAnalyzeModel){
		PageBeanUtil pageBean = this.getPageBeanToWorked(request);
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		Map<String, Object> paramMap = this.getNewMap();
		paramMap.put("queryModel", projectAnalyzeModel);
		paramMap.put("userId", userModel.getUserId());
		paramMap.put("index", pageBean.getPageIndex());
		paramMap.put("size", pageBean.getPageSize());
		List<ProjectAnalyzeModel> projectAnalyzeList = projectAnalyzeService
				.queryProjectAnalyzeModelByParam(paramMap);
		int total = projectAnalyzeList.size() == 0 ? 0 : projectAnalyzeList
				.get(0).getTotal();
		JSONObject json = this.getJsonObject(total, projectAnalyzeList);
		Map<String, Object> jsonMap = this.getJsonParamMap();
		if (null != jsonMap) {
			json.putAll(jsonMap);
			this.setJsonParamMap(null);
		}
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}

	/**
	 * 删除 产品标签
	 * 
	 * @param request
	 * @param response
	 * @param projectAnalyzeModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteProjectAnalyze")
	public void deleteProjectAnalyze(HttpServletRequest request,
			HttpServletResponse response,
			ProjectAnalyzeModel projectAnalyzeModel) throws IOException {
		int reuslt = projectAnalyzeService
				.deleteProjectAnalyze(projectAnalyzeModel);
		Map<String, Object> paramMap = this.getNewMap();
		paramMap.put("result", reuslt);
		this.setJsonParamMap(paramMap);
		queryProjectAnalyzeByParam(request, response, projectAnalyzeModel);
	}

	/**
	 * 编辑产品
	 * 
	 * @param request
	 * @param response
	 * @param projectAnalyzeModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateProjectAnalyzeByParam")
	public void updateProjectAnalyzeByParam(HttpServletRequest request,
			HttpServletResponse response,
			ProjectAnalyzeModel projectAnalyzeModel) throws IOException {
		int reuslt = projectAnalyzeService
				.updateProjectAnalyze(projectAnalyzeModel);
		Map<String, Object> paramMap = this.getNewMap();
		paramMap.put("result", reuslt);
		this.setJsonParamMap(paramMap);
		queryProjectAnalyzeByParam(request, response, projectAnalyzeModel);
	}

	/**
	 * 产品标签<br>
	 * 市场分析
	 * @param request
	 * @param response
	 * @param rightLibrarySearchModel : 条件对象
	 * @throws IOException 
	 */
	@RequestMapping(value="/queryProjectMarketData")
	public void queryProjectMarketData(HttpServletRequest request,
			HttpServletResponse response,RightLibrarySearchModel rightLibrarySearchModel) throws IOException {
		PageBeanUtil pageBean = this.getPageBeanToRight(request);
		Map<String,Object> paramMap = marketAnalysisReportService.setMarketAnalysisReportFields(request, rightLibrarySearchModel);
		paramMap.put("request", request);
		paramMap.put("queryModel", rightLibrarySearchModel);
		List<DataReportSumModel> allDBList = projectAnalyzeService.queryMarketAnalysisData(paramMap);
		// 获取map对象
		Map map = ResultFieldModel.getResultFieldMap();
		if (!Strings.isNullOrEmpty(rightLibrarySearchModel.getSort())){			//排序
			new DataGridSortUtil().executeSearchSort(request,allDBList,map,rightLibrarySearchModel.getSort());
		}
		List<DataReportSumModel> allDBListNew = this.getSubList(pageBean, allDBList);
		JSONObject json = this.getJsonObject(allDBList.size(), allDBListNew);
		response.getWriter().println(json.toString());
	}
	
	/**
	 * 产品标签<br>
	 * 交易记录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/queryProjectTradeData")
	public void queryProjectTradeData(HttpServletRequest request,
			HttpServletResponse response,RightLibrarySearchModel rightLibrarySearchModel) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PageBeanUtil pageBean = this.getPageBean(request);
		Map<String,Object> paramMap = marketAnalysisReportService.setMarketAnalysisReportFields(request, rightLibrarySearchModel);
		paramMap.put("request", request);
		paramMap.put("queryModel", rightLibrarySearchModel);
		List<AllDBModel> allDBList = projectAnalyzeService.queryTradingRecordData(paramMap,pageBean);
		Map<String,Object> map = ResultFieldModel.getResultFieldMap();
		if (!Strings.isNullOrEmpty(rightLibrarySearchModel.getSort())){
			new DataGridSortUtil().executeSearchSort(request,allDBList,map,rightLibrarySearchModel.getSort());
		}
		JSONObject json = this.getJsonObject(pageBean.getPageCount(), allDBList);
		response.getWriter().println(json.toString());
	}
	
	/**
	 * 买家资源库
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryBuyerResource")
	public void queryBuyerResource(HttpServletRequest request,
			HttpServletResponse response,RightLibrarySearchModel rightLibrarySearchModel){
		PageBeanUtil pageBean = this.getPageBean(request);
		// 构造条件
		Map<String,Object> paramMap = marketAnalysisReportService.setMarketAnalysisReportFields(request, rightLibrarySearchModel);
		paramMap.put("request", request);
		paramMap.put("pageBean", pageBean);
		paramMap.put("queryModel", rightLibrarySearchModel);
		// 获取数据
		List<BuyerModel> buyerList = projectAnalyzeService.queryBuyerList(paramMap);
		Map<String,Object> map = ResultFieldModel.getResultFieldMap();	// 获取map对象
		if (!Strings.isNullOrEmpty(rightLibrarySearchModel.getSort())){
			new DataGridSortUtil().executeSearchSort(request,buyerList,map,rightLibrarySearchModel.getSort());
		}
		PageBeanUtil pageBeans = this.getPageBeanToRight(request);
		List<BuyerModel> newCountryList = this.getSubList(pageBeans, buyerList);
		JSONObject json = this.getJsonObject(buyerList.size(), newCountryList);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * 市场标签<br>
	 * 竞争对手
	 * @param request
	 * @param response
	 * @param rightLibrarySearchModel
	 * @throws IOException 
	 */
	@RequestMapping(value="/queryCompetitor")
	public void queryCompetitor(HttpServletRequest request,
			HttpServletResponse response,RightLibrarySearchModel rightLibrarySearchModel) throws IOException {
		// 买家资源库 不需要处理分页
		PageBeanUtil pageBeans = this.getPageBean(request);
		// 构造条件
		Map<String,Object> paramMap = marketAnalysisReportService.setMarketAnalysisReportFields(request, rightLibrarySearchModel);
		paramMap.put("request", request);
		paramMap.put("pageBean", pageBeans);
		paramMap.put("queryModel", rightLibrarySearchModel);
		List<DataReportSumModel> resultList = projectAnalyzeService.queryCompetitorByParam(paramMap);
		Map<String,Object> map = ResultFieldModel.getResultFieldMap();	// 获取map对象
		if (!Strings.isNullOrEmpty(rightLibrarySearchModel.getSort())){
			new DataGridSortUtil().executeSearchSort(request,resultList,map,rightLibrarySearchModel.getSort());
		}
		PageBeanUtil pageBean = this.getPageBeanToRight(request);
		List<DataReportSumModel> subDataReportList = this.getSubList(pageBean, resultList);
		// TODO 分页
		JSONObject json = this.getJsonObject(resultList.size() == 0 ? 0 : resultList.size(),subDataReportList);
		response.getWriter().println(json.toString());
	}
}
