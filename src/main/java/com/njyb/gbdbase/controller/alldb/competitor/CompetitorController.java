package com.njyb.gbdbase.controller.alldb.competitor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.sort.DataGridSortUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.ComAndCusInfoResultModel;
import com.njyb.gbdbase.model.alldb.competitor.QueryCompetitorQueryModel;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ResultFieldModel;
import com.njyb.gbdbase.service.alldb.competitor.ICompetitorService;
import com.njyb.gbdbase.service.alldb.competitor.IMarketAnalysisReportService;

/**	
 * 竞争对手模块<br>
 * Controller
 * @author WangBo 2015年4月7日 CompetitorController.java
 */
@Controller
@RequestMapping("/competitor")
public class CompetitorController extends PublicCommonController {

	// 竞争对手服务接口
	@Autowired
	private ICompetitorService competitorService;
	
	@Autowired
	private IMarketAnalysisReportService marketAnalysisReportService;
	
	@Resource
	private  ResultFieldModel resultFieldModel;

	/**
	 * 根据条件查询竞争对手结果
	 * 
	 * @param request
	 * @param response
	 * @param queryCompetitorQueryModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryCompetitor")
	public void queryCompetitorByParam(HttpServletRequest request,
			HttpServletResponse response,
			QueryCompetitorQueryModel queryCompetitorQueryModel)
			throws IOException {
		PageBeanUtil pageBean = this.getPageBeanToWorked(request);
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageUtil", pageBean);
		paramMap.put("userId", userModel.getUserId());
		paramMap.put("queryModel", queryCompetitorQueryModel);
		List<ComAndCusInfoResultModel> resultList = competitorService
				.queryCompetitorResult(paramMap);
		int total = resultList.size() == 0 ? 0 : resultList.get(0).getTotal();
		JSONObject json = this.getJsonObject(total, resultList);
		Map<String, Object> paramMaps = this.getJsonParamMap();
		if (null != paramMaps) {
			json.putAll(paramMaps);
			this.setJsonParamMap(null);	//赋值为空,下次使用
		}
		json.put("flag", 1);	//注意: flag 用于区分页面的 竞争对手 和 客户信息   1 : 竞争对手  2 : 客户信息
		response.getWriter().println(json.toString());
	}

	/**
	 * 删除竞争对手
	 * 
	 * @param request
	 * @param response
	 * @param queryCompetitorQueryModel
	 * @param competitorAndCustomerInfoResultModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/delCompany")
	public void deleteCompetitorByParam(
			HttpServletRequest request,
			HttpServletResponse response,
			QueryCompetitorQueryModel queryCompetitorQueryModel,
			ComAndCusInfoResultModel competitorAndCustomerInfoResultModel)
			throws IOException {
		int result = competitorService
				.deleteCompetitorAndCustomerInfo(competitorAndCustomerInfoResultModel);
		Map<String, Object> paramMap = this.getNewMap();
		paramMap.put("result", result);
		JSONObject json = new JSONObject();
		json.putAll(paramMap);
		response.getWriter().println(json.toString());
	}

	/**
	 * 添加竞争对手
	 * 
	 * @param request
	 * @param response
	 * @param competitorandCustomerInfoResultModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/addCompetitorInfo")
	public void addCompetitorInfo(
			HttpServletRequest request,
			HttpServletResponse response,
			ComAndCusInfoResultModel competitorandCustomerInfoResultModel)
			throws IOException {
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		competitorandCustomerInfoResultModel.setUserId(userModel.getUserId());
		competitorandCustomerInfoResultModel
				.setUserType(IConstantUtil.COMPETITOR);
		int result = competitorService
				.addCompetitorAndCustomerInfo(competitorandCustomerInfoResultModel);
		JSONObject json = new JSONObject();
		json.put("result", result);
		response.getWriter().println(json.toString());
	}

	/**
	 * 修改竞争对手
	 * 
	 * @param request
	 * @param response
	 * @param competitorandCustomerInfoResultModel
	 * @throws IOException
	 */
	@RequestMapping(value = "updateCompetitorInfo")
	public void updateCompetitorInfo(
			HttpServletRequest request,
			HttpServletResponse response,
			ComAndCusInfoResultModel competitorandCustomerInfoResultModel)
			throws IOException {
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		competitorandCustomerInfoResultModel.setUserId(userModel.getUserId());
		int result = competitorService
				.updateCompetitorAndCustomerInfo(competitorandCustomerInfoResultModel);
		JSONObject json = new JSONObject();
		json.put("result", result);
		response.getWriter().println(json.toString());
	}
	
	/**
	 * 市场分析
	 * @param request
	 * @param response
	 * @param searchAllDBCommonModel : 市场分析
	 * @throws IOException 
	 */
	@RequestMapping(value="/queryCompetitorMarketData")
	public void queryCompetitorMarketData(HttpServletRequest request,
			HttpServletResponse response,RightLibrarySearchModel marketAnalysisReportSearchModel) throws IOException{
		PageBeanUtil pageBeanUtil = this.getPageBeanToRight(request);
		response.setCharacterEncoding("UTF-8");
		Map<String,Object> paramMap = marketAnalysisReportService.setMarketAnalysisReportFields(request, marketAnalysisReportSearchModel);
		paramMap.put("request", request);
		paramMap.put("queryModel", marketAnalysisReportSearchModel);
		List<DataReportSumModel> allDBList = competitorService.queryMarketAnalysisData(paramMap);
		List<DataReportSumModel> allDBLIsts = this.getSubList(pageBeanUtil, allDBList);
		Map<String,Object> map = resultFieldModel.getResultFieldMap();	// 获取map对象
		if (!Strings.isNullOrEmpty(marketAnalysisReportSearchModel.getSort())){
			new DataGridSortUtil().executeSearchSort(request,allDBLIsts,map,marketAnalysisReportSearchModel.getSort());
		}
		int total = allDBList.size() == 0 ? 0 : allDBList.size();
		JSONObject json = this.getJsonObject(total, allDBLIsts);
		response.getWriter().println(json.toString());
	}
	
	/**
	 * 交易记录
	 * @param request
	 * @param response
	 * @param searchAllDBCommonModel : 市场分析
	 * @throws IOException 
	 */
	@RequestMapping(value="/queryCompetitorTradingData")
	public void queryCompetitorTradingData(HttpServletRequest request,
			HttpServletResponse response,RightLibrarySearchModel marketAnalysisReportSearchModel) throws IOException{
		PageBeanUtil pageBeanUtil = this.getPageBean(request);
		Map<String,Object> paramMap = marketAnalysisReportService.setMarketAnalysisReportFields(request, marketAnalysisReportSearchModel);
		paramMap.put("request", request);
		paramMap.put("queryModel", marketAnalysisReportSearchModel);
		List<AllDBModel> allDBList = competitorService.queryTradingRecordData(paramMap,pageBeanUtil);
		Map<String,Object> map = resultFieldModel.getResultFieldMap();	// map对象
		if (!Strings.isNullOrEmpty(marketAnalysisReportSearchModel.getSort())){
			new DataGridSortUtil().executeSearchSort(request,allDBList,map,marketAnalysisReportSearchModel.getSort());
		}
		PageBeanUtil pageBean = this.getPageBeanToRight(request);
		List<AllDBModel> tempAllDBList = this.getSubList(pageBean, allDBList);		// 得到分页数据
		JSONObject json = this.getJsonObject(allDBList.size(), tempAllDBList);
		response.getWriter().println(json.toString());
	}
}