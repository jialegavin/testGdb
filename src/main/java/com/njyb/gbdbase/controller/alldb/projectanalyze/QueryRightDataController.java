package com.njyb.gbdbase.controller.alldb.projectanalyze;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.njyb.gbdbas.util.ECacheContrastUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.pagetemplate.ViewDetailUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.alldb.projectAnalyze.ProgressOfDownData;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 权库 {点击详情}<br>
 * 获取相关数据
 * 
 * @author WangBo 2015年4月28日 SearchRightDataController.java
 */
@Controller
@RequestMapping(value = "/rightLibraryData")
public class QueryRightDataController extends PublicCommonController {

	/*使用日志*/
	private static final Logger log = Logger.getLogger(QueryRightDataController.class);
	
	@Autowired
	private IDataSearchService dataSearchService;

	/**
	 * 获取相关数据
	 * 
	 * @param request
	 * @param response
	 * @param id
	 *            : id
	 * @param countryName
	 *            : 国家
	 * @throws IOException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getRowData")
	public <T> void getSearchModel(HttpServletRequest request,
			HttpServletResponse response, int id, String countryName)
			throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 获取当前的国家名称
		List<Integer> idList = Lists.newArrayList();
		idList.add(id);// 根据具体国家以及数据库id查询具体国家的详细数据
		countryName = InitCountryCENameUtil.queryCountryEnName(countryName);
		T model = null;
		// 读取缓存,如果有返回,如果没有读取数据库
		if (null != ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, countryName)) {
			model =  (T) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, countryName);
		} else {
			// 将查询结果放入缓存中
			model = (T) dataSearchService.getDataById(countryName, idList).get(0);
			ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, countryName, model);
		}
		// 动态生成国家查看详情的页面
		String htmlData = ViewDetailUtil.fmtHtmlByCountry(model, request,countryName);
		// json展示给前台
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("htmlData", htmlData);
		try {
			// 如果当前国家没有hscode,复制为N/A
			jsonObject.put("hscode", BeanUtils.getProperty(model, "hscode"));
		} catch (Exception e) {
			log.debug(e);
		} finally {
			jsonObject.put("hscode", "N/A");
		}
		jsonObject.put("country", InitCountryCENameUtil.queryCountryCnName(countryName));
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 权库进度条  {每2秒请求1次}
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/queryDataSchedule")
	public void queryDataSchedule(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String country = ProgressOfDownData.getProgressOfDownData().getCountry();
		PrintWriter out = response.getWriter();
		JSONObject object = new JSONObject();
		object.put("key", country);
		out.print(object.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 贸易情报的进度条 {每2秒请求1次}
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/queryDataSeachPro")
	public void queryDataSeachPro(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int resultIndex = ProgressOfDownData.getProgressOfDownData().getProIndex();
		PrintWriter out = response.getWriter();
		JSONObject object = new JSONObject();
		object.put("key", resultIndex);
		out.print(object.toString());
		out.flush();
		out.close();
		
	}
}