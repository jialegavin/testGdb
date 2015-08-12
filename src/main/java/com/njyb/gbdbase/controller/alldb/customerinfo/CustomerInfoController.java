package com.njyb.gbdbase.controller.alldb.customerinfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.sort.DataGridSortUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.ComAndCusInfoResultModel;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.alldb.customer.QueryCustomerModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ResultFieldModel;
import com.njyb.gbdbase.service.alldb.competitor.IMarketAnalysisReportService;
import com.njyb.gbdbase.service.alldb.customer.ICustomerService;

/**
 * 客户信息<br>
 * 
 * @author WangBo 2015年4月8日 CustomerInfoController.java
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerInfoController extends PublicCommonController {

	@Autowired
	private ICustomerService customerService;

	// 市场分析报表
	@Autowired
	private IMarketAnalysisReportService marketAnalysisReportService;

	/**
	 * 查询客户信息
	 * 
	 * @param request
	 * @param response
	 * @param queryCustomerModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryCustomer")
	public void queryCustomerInfoByParam(HttpServletRequest request,
			HttpServletResponse response, QueryCustomerModel queryCustomerModel)
			throws IOException {
		PageBeanUtil pageBean = this.getPageBeanToWorked(request);
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageBean", pageBean);
		paramMap.put("userId", userModel.getUserId());
		paramMap.put("queryModel", queryCustomerModel);
		List<ComAndCusInfoResultModel> reslutList = customerService
				.queryCompetitorResult(paramMap);
		int total = reslutList.size() == 0 ? 0 : reslutList.get(0).getTotal();
		JSONObject json = this.getJsonObject(total, reslutList);
		Map<String, Object> paramMaps = this.getJsonParamMap();
		if (null != paramMaps) {
			json.putAll(paramMaps);
			this.setJsonParamMap(null);
		}
		json.put("flag", 2); // 注意: flag 用于区分页面的 竞争对手 和 客户信息 1 : 竞争对手 2 : 客户信息
		response.getWriter().println(json.toString());
	}

	/**
	 * 添加客户信息
	 * 
	 * @param request
	 * @param response
	 * @param competitorandCustomerInfoResultModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/addCustomerInfo")
	public void addCustomerInfo(HttpServletRequest request,
			HttpServletResponse response,
			ComAndCusInfoResultModel competitorandCustomerInfoResultModel,
			QueryCustomerModel queryCustomerModel) throws IOException {
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		competitorandCustomerInfoResultModel.setUserId(userModel.getUserId());
		int result = customerService
				.addCompetitorAndCustomerInfo(competitorandCustomerInfoResultModel);
		Map<String, Object> paramMap = this.getNewMap();
		paramMap.put("result", result);
		this.setJsonParamMap(paramMap);
		queryCustomerInfoByParam(request, response, queryCustomerModel);
	}

	/**
	 * 删除客户信息
	 * 
	 * @param request
	 * @param response
	 * @param competitorandCustomerInfoResultModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/delCustomer")
	public void deleteCustomerInfo(HttpServletRequest request,
			HttpServletResponse response,
			ComAndCusInfoResultModel competitorandCustomerInfoResultModel,
			QueryCustomerModel queryCustomerModel) throws IOException {
		int result = customerService
				.deleteCompetitorAndCustomerInfo(competitorandCustomerInfoResultModel);
		Map<String, Object> paramMap = this.getNewMap();
		paramMap.put("result", result);
		this.setJsonParamMap(paramMap);
		queryCustomerInfoByParam(request, response, queryCustomerModel);
	}

	/**
	 * 修改客户信息
	 * 
	 * @param request
	 * @param response
	 * @param competitorandCustomerInfoResultModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateCustomer")
	public void updateCustomerInfo(HttpServletRequest request,
			HttpServletResponse response,
			ComAndCusInfoResultModel comAndCusInfoResultModel)
			throws IOException {
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		comAndCusInfoResultModel.setUserId(userModel.getUserId());
		int result = customerService
				.updateCompetitorAndCustomerInfo(comAndCusInfoResultModel);
		Map<String, Object> paramMap = this.getNewMap();
		paramMap.put("result", result);
		this.setJsonParamMap(paramMap);
		queryCustomerInfoByParam(request, response, null);
	}

	/**
	 * 我的客户<br>
	 * 市场分析
	 * 
	 * @param request
	 * @param response
	 * @param rightLibrarySearchModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryCustomerMarketData")
	public void queryCustomerMarketData(HttpServletRequest request,
			HttpServletResponse response,
			RightLibrarySearchModel rightLibrarySearchModel) throws IOException {
		PageBeanUtil pageBeanUtil = this.getPageBeanToRight(request);
		Map<String, Object> paramMap = marketAnalysisReportService
				.setMarketAnalysisReportFields(request, rightLibrarySearchModel);
		paramMap.put("request", request);
		paramMap.put("queryModel", rightLibrarySearchModel);
		List<DataReportSumModel> list = customerService
				.queryMarketAnalysisData(paramMap);
		Map<String, Object> map = ResultFieldModel.getResultFieldMap(); // 获取map对象
		if (!Strings.isNullOrEmpty(rightLibrarySearchModel.getSort())) {
			new DataGridSortUtil().executeSearchSort(request, list, map,
					rightLibrarySearchModel.getSort());
		}
		List<DataReportSumModel> newList = this.getSubList(pageBeanUtil, list);
		int total = list.size() == 0 ? 0 : list.size();
		JSONObject json = this.getJsonObject(total, newList);
		response.getWriter().println(json.toString());
	}

	/**
	 * 交易记录
	 * 
	 * @param request
	 * @param response
	 * @param rightLibrarySearchModel
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryCustomerTradingData")
	public void queryCustomerTradingData(HttpServletRequest request,
			HttpServletResponse response,
			RightLibrarySearchModel rightLibrarySearchModel) throws IOException {
		PageBeanUtil pageBeanUtil = this.getPageBean(request);
		Map<String, Object> paramMap = marketAnalysisReportService
				.setMarketAnalysisReportFields(request, rightLibrarySearchModel);
		paramMap.put("request", request);
		paramMap.put("queryModel", rightLibrarySearchModel);
		List<AllDBModel> list = customerService.queryTradingRecordData(
				paramMap, pageBeanUtil);
		Map<String, Object> map = ResultFieldModel.getResultFieldMap(); // 获取Map对象
		if (!Strings.isNullOrEmpty(rightLibrarySearchModel.getSort())) {
			new DataGridSortUtil().executeSearchSort(request, list, map,
					rightLibrarySearchModel.getSort());
		}
		PageBeanUtil pageBean = this.getPageBeanToRight(request);
		List<AllDBModel> tempAllDBList = this.getSubList(pageBean, list);		// 得到分页数据
		JSONObject json = this.getJsonObject(list.size(),tempAllDBList);
		response.getWriter().println(json.toString());
	}
}