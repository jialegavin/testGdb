package com.njyb.gbdbase.controller.alldb.projectanalyze;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Strings;
import com.njyb.gbdbas.util.sort.DataGridSortUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.model.datasearch.common.ResultFieldModel;
import com.njyb.gbdbase.service.alldb.competitor.IMarketAnalysisReportService;
import com.njyb.gbdbase.service.alldb.projectAnalyze.IWithSequentialService;

/**
 * 同环比
 * @author WangBo
 * 2015年5月15日
 * WithSequentialController.java
 */
@Controller
@RequestMapping("/WithSequential")
public class WithSequentialController extends PublicCommonController {

	// 市场分析报表
	@Autowired
	private IMarketAnalysisReportService marketAnalysisReportService;
	
	@Autowired
	private IWithSequentialService withSequentialService;
	
	@Resource
	private  ResultFieldModel resultFieldModel;
	
	/**
	 * 查询同环比
	 * @param request
	 * @param response
	 * @param librarySearchModel
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IOException
	 */
	@RequestMapping(value="/queryWithSequential")
	public void queryWithSequential(HttpServletRequest request,HttpServletResponse response,
			RightLibrarySearchModel librarySearchModel) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String,Object> paramMap = marketAnalysisReportService.setMarketAnalysisReportFields(request, librarySearchModel);
		paramMap.put("request", request);
		paramMap.put("queryModel", librarySearchModel);
		Map<String, List<Object>> resukltMap = withSequentialService.queryWithSequentiallMap(paramMap);
		String key = BeanUtils.getProperty(librarySearchModel, "widthType");
		List<Object> resultList = (List<Object>) resukltMap.get(key);
		// 获取map对象
		@SuppressWarnings({ "unchecked", "static-access" })
		Map<String,Object> map = resultFieldModel.getResultFieldMap();
		if (!Strings.isNullOrEmpty(librarySearchModel.getSort())){			//排序
			new DataGridSortUtil().executeSearchSort(request,resultList,map,librarySearchModel.getSort());
		}
		int total = resultList.size() == 0 ? 0 : resultList.size();
		JSONObject json = this.getJsonObject(total, resultList);
		response.getWriter().println(json.toString());
	}
}