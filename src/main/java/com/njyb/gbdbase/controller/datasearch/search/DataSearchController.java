package com.njyb.gbdbase.controller.datasearch.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.AllCountryInsertPropertiesUtil;
import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.TradeCacheUtil;
import com.njyb.gbdbas.util.TradeResultHandleUtil;
import com.njyb.gbdbas.util.TradeResultPageUtil;
import com.njyb.gbdbas.util.export.FileUtil;
import com.njyb.gbdbas.util.pagetemplate.DataCompareUtil;
import com.njyb.gbdbas.util.pagetemplate.DepthDiggingUtil;
import com.njyb.gbdbas.util.pagetemplate.LoadReportSummaryFieldUtil;
import com.njyb.gbdbas.util.pagetemplate.MutiHscodeUtil;
import com.njyb.gbdbas.util.pagetemplate.QueryConditionUtil;
import com.njyb.gbdbas.util.pagetemplate.ReportSummaryFieldUtil;
import com.njyb.gbdbas.util.pagetemplate.ResultFieldUtil;
import com.njyb.gbdbas.util.pagetemplate.SelectFieldUtil;
import com.njyb.gbdbas.util.pagetemplate.TabGenerateUtil;
import com.njyb.gbdbas.util.pagetemplate.ThbReportUtil;
import com.njyb.gbdbas.util.pagetemplate.ViewDetailUtil;
import com.njyb.gbdbas.util.sort.DataGridSortUtil;
import com.njyb.gbdbase.model.datasearch.common.ConditionFieldModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.MapModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.model.datasearch.common.ResultFieldModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.IMultiHscodeMarkService;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;
import com.njyb.gbdbase.service.common.engines.IReportEngineService;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
import com.njyb.gbdbase.service.contrastreport.contrast.component.CountryAllManagerComponent;
import com.njyb.gbdbase.service.datasearch.CommonSearchService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;
import com.njyb.gbdbase.service.personalcenter.favorites.IBrowsingHistoryService;

/**
 * 贸易情报模块控制器
 * @author honghao
 * 2015-04-02
 * @version 标准版
 */
@Controller
public class DataSearchController {
	@Resource
	private IReportEngineService reportEngineService;
	@Resource
	private IMultiHscodeMarkService multiHscodeMarkService;
	@Resource
	private  ResultFieldModel resultFieldModel;
	@Resource
	private  ConditionFieldModel conditionFieldModel;
	@Resource
	private  ReportPropertiesModel reportPropertiesModel;
	@Autowired
	private IDataSearchService dataSearchService;
	@Autowired
	private  ISearchEngineService searcherEngineService;
	@Autowired
	private CommonSearchService commonSearchService;
	// 浏览记录
	@Autowired
	private IBrowsingHistoryService browsingHistoryService;
	//获取所有原产国字段
	@Autowired
	private AllCountryInsertPropertiesUtil allField; 
	//获取章华才公共方法
	@Autowired
	private CountryAllManagerComponent allManagerComponet;
	//明细
	@Autowired
	private IReportDetailService detailService;
	
	//存放缓存中的集合
	private List<DataReportSumModel> cacheList = new ArrayList<DataReportSumModel>();
	
	/**
	 * 动态生成各国的查询条件
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @return string 返回的状态码
	 */
	@RequestMapping(value = "/generateQueryCondition ")
	public String generateQueryCondition(HttpServletRequest request,HttpServletResponse response,String country) throws Exception{
		PrintWriter out = response.getWriter();
		String countryName = InitCountryCENameUtil.queryCountryEnName(country);
		String countryEnName=InitCountryCENameUtil.queryCountryEnglishName(country);	//get the correct english name
		//将当前进入的国家英文名存放进session中
		request.getSession().setAttribute("country",countryName);
		//将当前进入的国家中文名存放进session中
		request.getSession().setAttribute("countryZhName",country);
		// 获取map对象
		Map map = conditionFieldModel.getConditionFieldMap();
		//根据国家生成对应的jsp搜索条件
		String htmlData = QueryConditionUtil.fmtHtml(countryName, map,countryEnName);
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		out.write(json.toString());
		return null;
	}
	
	/**
	 * 动态生成各国家搜索结果字段列的数据表格
	 * @param request
	 * @param response
	 * @param selectFieldId 选择列表中选择的字段id
	 * @param selectFieldName 选择列表中选择的字段名称
	 * @param selectFieldName
	 * @throws Exception 
	 * @return string 返回的状态码
	 */
	@RequestMapping(value = "/generateResultField")
	public String generateResultField(HttpServletRequest request,HttpServletResponse response,String selectFieldId) throws Exception{
		String selectFieldName = request.getParameter("selectFieldName");
		selectFieldName = URLDecoder.decode(selectFieldName,"UTF-8");  
		//country有值代表是收藏夹传过来的，country没值代表是其他模块传过来的
		String country=request.getParameter("country");
		//判断是否从添加收藏传过来的
		String intoType = request.getParameter("intoType");
		PrintWriter out = response.getWriter();
		//获取当前的国家
		if(country == null ||"".equals(country.trim()))
		{
			country = (String) request.getSession().getAttribute("country");
		}
		else
		{
			country = URLDecoder.decode(country,"UTF-8");  
			request.getSession().setAttribute("country",country);
		}
		if(intoType != null && !"".equals(intoType))
		{
			intoType = URLDecoder.decode(intoType,"UTF-8");  
		}
		else
		{
			intoType="";
		}
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//根据国家生成对应国家需要展示的列
		String htmlData = ResultFieldUtil.fmtHtml(country, map,selectFieldId,selectFieldName,intoType);
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		out.write(json.toString());
		return null;
	}
	
	
	/**
	 * 动态生成各国家搜索结果中选择字段对话框内容
	 * @param request
	 * @param response
	 * @param selectFieldId 选择列表中选择的字段id
	 * @param selectFieldName 选择列表中选择的字段名称
	 * @throws Exception 
	 * @return string 返回的状态码
	 */
	@RequestMapping(value = "/generateSelectField ")
	public String generateSelectField(HttpServletRequest request,HttpServletResponse response,String selectFieldId) throws Exception{
		String selectFieldName = request.getParameter("selectFieldName");
		selectFieldName = URLDecoder.decode(selectFieldName,"UTF-8");  
		PrintWriter out = response.getWriter();
		//获取当前的国家
		String country = (String) request.getSession().getAttribute("country");
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//根据国家生成对应国家需要展示的列
		String htmlData = SelectFieldUtil.fmtHtml(country, map,selectFieldId,selectFieldName);
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		out.write(json.toString());
		return null;
	}
	
	/**
	 * 动态生成各国家搜索结果选项卡
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @return string 返回的状态码
	 */
	@RequestMapping(value = "/generateTab ")
	public String generateTab(HttpServletRequest request,HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		//获取当前的国家
		String country = (String) request.getSession().getAttribute("country");
		// 获取map对象
		Map map = reportPropertiesModel.getReportFieldMap();
		//根据国家生成对应国家需要展示的列
		String htmlData = TabGenerateUtil.fmtHtml(country, map);
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		out.write(json.toString());
		return null;
	}
	
	
	
	/**
	 * 根据查询条件查询国家数据，并前台列表展示
	 * @param request
	 * @param response
	 * @param queryKey js端构造的查询条件名称以逗号分隔
	 * @param queryValue js端构造的查询条件值以逗号分隔
	 * @throws Exception 
	 * @return string
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getResultFieldList ")
	public<T> List<T> getResultFieldList(HttpServletRequest request,HttpServletResponse response,String queryKey,String queryValue) throws Exception{
		//获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		//处理日期YYYY-MM
		queryValue = allManagerComponet.formartDateByCountry(queryValue,country);
		//过了查询条件中的特殊字符
		queryValue = TradeResultHandleUtil.processSpecialChara(request,queryKey,queryValue);
		//将查询条件存放进map中，收藏夹那边会用到.
		MapModel.getMap().put("queryKey", queryKey);
		MapModel.getMap().put("queryValue", queryValue);
		//实例化分页工具
		PageBeanUtil beanUtil = commonSearchService.getPageBeanUtil(request,"10");
		//查询出lucene中数据库主键id 
		List<Integer> idList = searcherEngineService.getListKey(new SearchCommonParamModel(queryKey.split(";"), queryValue.split(";"), country, ParamEnumUtil.search.toString(), request, beanUtil));
		//根据具体国家以及数据库id查询具体国家的详细数据
		List<T> listAll =  dataSearchService.getDataById(country, idList);
		/*处理特殊字段的过滤*/
		List<T>companylist=new LinkedList<T>();
		List<T>placelist=new LinkedList<T>();
		List<T>countrylist=new LinkedList<T>();
		//经营单位名称
		String[]fs=queryKey.split(";");
		String[]vs=queryValue.split(";");
		int i=0;
		for ( i = 0; i < vs.length; i++) {
			/*if (fs[i].equals("company_name")) {
				String cvalue=vs[i];
				for(T t:listAll){
					if (cvalue.equals(BeanUtils.getProperty(t, "companyName"))) {
						companylist.add(t);
					}
				}
				listAll=companylist;
			}*/
//			if (fs[i].equals("product_place")) {
//				String cvalue=vs[i];
//				for(T t:listAll){
//					if (cvalue.equals(BeanUtils.getProperty(t, "productPlace"))) {
//						companylist.add(t);
//					}
//				}
//				listAll=placelist;
//			}
			
		}
		
		
		
		/*if (fieldsModel.getEnterprisename()!=null && !fieldsModel.getEnterprisename().equals("")) {
			for(ChinaEighModel c:list){
					if (c.getCompanyname().contains(fieldsModel.getEnterprisename())) {
						companylist.add(c);
					}
				list=companylist;
			}
		}
		//产销国
		if (fieldsModel.getTradecountry()!=null && !fieldsModel.getTradecountry().equals("")) {
			for(ChinaEighModel c:list){
					if (c.getProductsalcountry().contains(fieldsModel.getTradecountry())) {
						countrylist.add(c);
					}
				list=countrylist;
			}
		}
		//收发货地
		if (fieldsModel.getProductplace()!=null && !fieldsModel.getProductplace().equals("")) {
			for(ChinaEighModel c:list){
					if (c.getProductplace().contains(fieldsModel.getProductplace())) {
						placelist.add(c);
					}
				list=placelist;
			}
		}*/
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//根据客户端请求排序
		new DataGridSortUtil().executeSearchSort(request,listAll,map,DataSearchConstantUtil.SEARCH);
		//处理double类型的精度,保留小数点后两位
		TradeResultHandleUtil.processPrecision(request,listAll,map,DataSearchConstantUtil.SEARCH,2);
		//将查询结果放入缓存中
		Cache ehCache = CreateEncache.getEacheInstance().getCache("dataSearchCache");
		ehCache.acquireWriteLockOnKey("resultList");
		ehCache.put(new Element("resultList",listAll));
		ehCache.releaseWriteLockOnKey("resultList");  
		//json展示给前台
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", beanUtil.getPageCount());
		//查询必要条件列表红色展示
	    jsonObject.put("rows",TradeResultHandleUtil.fieldConRedOnResult(queryKey,queryValue,listAll) );
		response.getWriter().println(jsonObject.toString());
		return null;
	}
	
	/**
	 * 根据id获取数据库详细数据并展示在查看详情对话框中
	 * @param request
	 * @param response
	 * @param id 用户选择的列的数据库id
	 * @throws Exception 
	 * @return string
	 */
	@RequestMapping(value = "/getRowData")
	public<T> String getRowData(HttpServletRequest request,HttpServletResponse response,int id) throws Exception{
		  PrintWriter out = response.getWriter();
		  String country = "";
		  // myFavorites 不为空则为收藏夹功能  WangBo 283line 2015/6/09
		  if (!Strings.isNullOrEmpty(request.getParameter("myFavorites"))) {
			  country = request.getParameter("country");
		  } else {
			  //获取当前的国家名称
			  country = (String) request.getSession().getAttribute("country");
		  }
		 List<Integer> idList = Lists.newArrayList(id);
		 //根据具体国家以及数据库id查询具体国家的详细数据
		 T model =  (T) dataSearchService.getDataById(country, idList).get(0);
		 //将查询结果放入缓存中
		 Cache ehCache = CreateEncache.getEacheInstance().getCache("dataSearchCache");
		 ehCache.acquireWriteLockOnKey("detailData");
		 ehCache.put(new Element("detailData",model));
		 ehCache.releaseWriteLockOnKey("detailData");  
		 // 获取map对象
		 Map map = resultFieldModel.getResultFieldMap();
		 // 动态生成国家查看详情的页面
		 String htmlData = ViewDetailUtil.fmtHtml(map,model, request);
		 //对没有hscode而只有提单号的国家进行处理
		 //json展示给前台
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("htmlData",htmlData);
		 jsonObject.put("hscode", dataSearchService.solveCountryHscode(model, country));
		 jsonObject.put("country_cn", country);		//英文名称
		 jsonObject.put("country", InitCountryCENameUtil.queryCountryCnName(country));
		 out.write(jsonObject.toString());
		 return null;
	}
	
	/**
	 * 收藏夹对话框中显示用户的查询条件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getQueryCondition")
	public JSONObject getQueryCondition(HttpServletRequest request,HttpServletResponse response) {
	     try 
	     {
	    	 response.setContentType("text/plain;charset=utf-8");
	    	 String zhQueryParam = TradeCacheUtil.getQueryParam(MapModel.getMap().get("queryKey").toString(), MapModel.getMap().get("queryValue").toString());
			 response.getWriter().write(zhQueryParam);
		  }catch (Exception e){
		  }
		  return null;	
	 }
	
	/**
	 * 根据选中的列展示查询列表结果
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSelectFieldList")
	public <T> JSONObject getSelectFieldList(HttpServletRequest request,HttpServletResponse response,String queryKey,String queryValue) throws Exception{
		
		//获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		//实例化分页工具
		PageBeanUtil beanUtil = commonSearchService.getPageBeanUtil(request,"10");
		//处理日期YYYY-MM
		queryValue = allManagerComponet.formartDateByCountry(queryValue,country);
		
		// 处理日期格式 ,调用 全库共用国家构造接口 ,重写 洪浩版本 WangBo 2015/6/12  356line
		
		//查询出lucene中数据库主键id 
		List<Integer> idList = searcherEngineService.getListKey(new SearchCommonParamModel(queryKey.split(";"), queryValue.split(";"), country, ParamEnumUtil.search.toString(), request, beanUtil));
		//根据具体国家以及数据库id查询具体国家的详细数据
		List<T> listAll =  dataSearchService.getDataById(country, idList);
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//降序排列
		new DataGridSortUtil().executeSearchSort(request,listAll,map,DataSearchConstantUtil.SEARCH);
		//处理double类型的精度,保留小数点后两位
		TradeResultHandleUtil.processPrecision(request,listAll,map,DataSearchConstantUtil.SEARCH,2);
		//json展示给前台
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("total", beanUtil.getPageCount());
		 jsonObject.put("rows",TradeResultHandleUtil.fieldConRedOnResult(queryKey, queryValue,listAll) );
		 response.getWriter().println(jsonObject.toString());
		 return null;
	 }
	
	/**
	 * 查看详情翻译id获取并写入TXT文本
	 * @author XL
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/translation", method = RequestMethod.GET)
	public void downloadSummaryData(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		//获取需要翻译的id
		Set<String> ids = dataSearchService.querySearchDetailFields(request);
		//写入txt文本
		FileUtil.writeText(ids, request.getSession().getServletContext().getRealPath("/")+"view/datasearch/language/id.txt");
	}
	
	/**
	 * 显示所有国家原产国字段到下拉框中
	 * @param request
	 * @param response
	 * @return 
	 * @author 章华才
	 * @throws IOException 
	 */
	@RequestMapping(value = "/showAllCountryField")
	public String allCountrySelectField(HttpServletRequest request,HttpServletResponse response,String countryName) throws IOException{
		String name = new String(countryName.getBytes("iso-8859-1"), "utf-8");    
		String country = InitCountryCENameUtil.queryCountryEnName(name);
		if(country == null){
			country = InitCountryCENameUtil.queryCountryEnName(countryName);
		}
		PrintWriter writer = response.getWriter();
		JSONArray jsonArray = allField.getArray(country, null);
		if(jsonArray == null)
		{
			writer.print("<html><head><script>alert('No Origin Country Data!');</scirpt></head><body></body></html>");
		}
		else
		{
			writer.print(jsonArray);
		}
		writer.close();
		return null;
	}
	
	/**
	 * 动态生成各国报表汇总字段
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @return string 返回的状态码
	 */
	@RequestMapping(value = "/generateSummaryField ")
	public String generateSummaryField(HttpServletRequest request,HttpServletResponse response,String type) throws Exception{
		PrintWriter out = response.getWriter();
		//获取当前的国家
		String country = (String) request.getSession().getAttribute("country");
		// 获取map对象
		Map reportFieldMap = reportPropertiesModel.getReportFieldMap();
		// 获取map对象
		Map resultFieldMap = resultFieldModel.getResultFieldMap();
		
		//根据国家生成对应国家报表汇总的列
		String htmlData = "";
		if(DataSearchConstantUtil.CP_TRADE.equals(type))
		{
		     htmlData = ReportSummaryFieldUtil.fmtCpTradeHtml(country, reportFieldMap, resultFieldMap);
		}else if(DataSearchConstantUtil.CP_PORT.equals(type))
		{
			 htmlData = ReportSummaryFieldUtil.fmtCpPortHtml(country, reportFieldMap, resultFieldMap);
		}else if(DataSearchConstantUtil.CP_COUNTRY.equals(type))
		{
			htmlData = ReportSummaryFieldUtil.fmtCpCountryHtml(country, reportFieldMap, resultFieldMap);
		}
		JSONObject json = new JSONObject();
		
		//获取需要生成数据表格的table的id和图标所在div的id
		Map map = ReportSummaryFieldUtil.getTableAndChartId(type,country, reportFieldMap);
		json.put("tableId", (String)map.get("tableId")); 
		json.put("chartDivId", (String)map.get("chartDivId")); 
	
		json.put("htmlData", htmlData); 
		out.write(json.toString());
		return null;
	}
	
	/**
	 * 所有国家报表汇总方法
	 * @param request
	 * @param response
	 * @param tableId
	 * @param type
	 * @param loadType 加载类型 1代表懒加载只加载前10条数据，2代表全部加载
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getReportSummaryList ")
	public<T> List<T> getReportSummaryList(HttpServletRequest request,HttpServletResponse response,String tableId,String type,int loadType) throws Exception{
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//获取某一报告选项卡下某一个数据表格的报表汇总集合
		List<DataReportSumModel> originListAll = dataSearchService.getReportList(request,tableId,type,MapModel.getMap().get("queryKey").toString(),MapModel.getMap().get("queryValue").toString(),loadType);
		//计算比率,并处理汇总集合排序字段的精度
		TradeResultHandleUtil.processPrecision(request,TradeResultPageUtil.getPercentage(request,originListAll,originListAll,map),map,DataSearchConstantUtil.REPORT,2);
		//根据客户端请求排序
		new DataGridSortUtil().executeSearchSort(request,originListAll,map,DataSearchConstantUtil.REPORT);
		
		//备份原始集合
		List<DataReportSumModel> listAll = new LinkedList<DataReportSumModel>();
		listAll = TradeResultHandleUtil.getNewList(originListAll, listAll);
		
		//实例化分页工具
		PageBeanUtil beanUtil = commonSearchService.getPageBeanUtil(request,"10");
		//获取分页集合
		List<DataReportSumModel> pageList = new LinkedList<DataReportSumModel>();
		int startIndex = commonSearchService.getStartIndex(beanUtil);
		int endIndex = commonSearchService.getEndIndex(beanUtil, listAll, startIndex);
		for(int i=startIndex;i<endIndex;i++)
		{
			pageList.add(listAll.get(i));
		}
		  //每个数据表格的列名
		String columField = map.get(((String)request.getSession().getAttribute("country")+"_"+tableId.replaceAll("_table", "")+DataSearchConstantUtil.FIELDID)).toString().split(",")[1];
		//处理当前页集合
		pageList = dataSearchService.getHyperlinkList(request,dataSearchService.setHideColumnValue(request,pageList,columField,tableId,type),columField,map,tableId.replaceAll("_table", ""));
		//当前页汇总
		List<DataReportSumModel> currentPageFooter = TradeResultPageUtil.currentPageSummary(request, columField, pageList, map);
		//处理当前页汇总的精度
		TradeResultHandleUtil.processPrecision(request,currentPageFooter,map,DataSearchConstantUtil.REPORT,1);
		//饼图和柱状图的map总集合
	    Map chartMap = dataSearchService.getPieAndMixChartData(request,originListAll,columField,map,tableId.replaceAll("_table", ""),DataSearchConstantUtil.DATA_SUMMARY,request.getParameter("sort")==null?null:request.getParameter("sort"));
	    //change to english
	    //  String pieChartFieldZhName =  (String) map.get((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MIXCHART_FIELDZHNAME);
	    String pieChartFieldEnName =  (String) map.get((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MIXCHART_FIELDENNAME);
		  
	    //json展示给前台
		JSONObject jsonObject = new JSONObject();
		if(listAll !=null)
		{
			jsonObject.put("total", listAll.size());
		}
	    jsonObject.put("rows",pageList == null ? new LinkedList():pageList);
	    jsonObject.put("footer",currentPageFooter);
	    jsonObject.put("mixChart",chartMap.get("mixChart"));
		jsonObject.put("pieChart", chartMap.get("pieChart"));
		jsonObject.put("reportType", tableId.replaceAll("_table", ""));
		//Change the map to be english
		//jsonObject.put("reportZhName", InitCountryCENameUtil.getReportTypeZhName(tableId.replaceAll("_table", "")));
		jsonObject.put("reportZhName", InitCountryCENameUtil.getReportTypeEnName(tableId.replaceAll("_table", "")));
		//change to enName
		jsonObject.put("pieChartFieldZhName",pieChartFieldEnName.split(",")[0]);
		response.getWriter().println(jsonObject.toString());
		return null;
	}
	
	/**
	 * 获取数据对比下拉框填充绑定的值
	 * @param request
	 * @param response
	 * @return 
	 * @author 章华才
	 */
	@RequestMapping(value = "getSelectValue")
	public String getSelectValue(HttpServletRequest request,HttpServletResponse response,String hideColumn){
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			cacheList = dataSearchService.getCacheValue(request, hideColumn);
			JSONObject json = new JSONObject();
			json.put("selectValue", cacheList);
			writer.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询报表明细
	 * @param request
	 * @param response
	 * @param param 参数
	 * @return
	 * @throws Exception 
	 * @author 章华才
	 */
	@RequestMapping(value = "queryDataSearchDetail")
	public String queryDetail(HttpServletRequest request,HttpServletResponse response,String columnValue,String columnKey,String sort,String order) throws Exception{
		//从map中获取国家
		String countryName = (String) request.getSession().getAttribute("country");
		List<DataReportSumModel> list = detailService.buildReportSumList(columnValue, columnKey, countryName);
		JSONObject jsonObject = new JSONObject();
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request, "10");
		beanUtil.setPageCount(list.size()==0?0:list.size());
		jsonObject.put("total", beanUtil.getPageCount());
		jsonObject.put("rows", allManagerComponet.orderListDescOrAsc(allManagerComponet.pageList(list, beanUtil), order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,countryName):sort, "double"));
		response.getWriter().write(jsonObject.toString());
		return null;
	}
	
	/**
	 * 动态生成各国家深度挖取页面数据表格中的字段列
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/generateDepthDiggingField ")
	public String generateDepthDiggingField(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//通过"洪"分隔 例如 importer洪aaaaaa洪qs
		String value = request.getParameter("value");
		value = URLDecoder.decode(value,"UTF-8");  
		PrintWriter out = response.getWriter();
		//获取当前的国家
		String country = (String) request.getSession().getAttribute("country");
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		String [] valueArray = value.split("罟");
		//根据国家生成对应国家需要展示的列
		String htmlData = DepthDiggingUtil.fmtHtml(country, map,InitCountryCENameUtil.queryFieldZhName(valueArray[0]),valueArray[1],valueArray[2]);
		String allReportType  = (String) map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.DEPTHDIGGING_REPORTTYPE));
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		json.put("allReportType", allReportType.replaceAll(valueArray[2]+",", "")); 
		out.write(json.toString());
		return null;
	}
	
	/**
	 * 所有国家深度挖取方法
	 * @param request
	 * @param response
	 * @param value 通过"洪"分隔 例如 importer洪aaaaaa洪jks
	 * @param module 模块类型
	 * @return
	 * @throws Exception1
	 */
	@RequestMapping(value = "/generateDepthDiggingData")
	public String generateDepthDiggingData(HttpServletRequest request,HttpServletResponse response,String value,String reportType) throws Exception
	{
		List<DataReportSumModel> originListAll = Lists.newArrayList();
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		PrintWriter out = response.getWriter();
		//获取某一报告选项卡下某一个数据表格的报表汇总集合
		originListAll = dataSearchService.getDeepReportList(request,value.split("罟")[1],value.split("罟")[2],"drill&"+reportType);
		//计算比率,并处理汇总集合排序字段的精度
		TradeResultHandleUtil.processPrecision(request,TradeResultPageUtil.getPercentage(request,originListAll,originListAll,map),map,DataSearchConstantUtil.REPORT,2);
		//根据客户端请求排序
		new DataGridSortUtil().executeSearchSort(request,originListAll,map,DataSearchConstantUtil.REPORT);
		//实例化分页工具
		PageBeanUtil beanUtil = commonSearchService.getPageBeanUtil(request,"10");
		//获取分页集合
		List<DataReportSumModel> pageList = new LinkedList<DataReportSumModel>();
		int startIndex = commonSearchService.getStartIndex(beanUtil);
		int endIndex = commonSearchService.getEndIndex(beanUtil, originListAll, startIndex);
		for(int i=startIndex;i<endIndex;i++)
		{
			pageList.add(originListAll.get(i));
		}
		
		//每个数据表格的列名
		String columField = map.get(((String)request.getSession().getAttribute("country")+"_"+reportType+DataSearchConstantUtil.FIELDID)).toString().split(",")[1];
		//饼图和柱状图的map总集合 pageList  == 10 饼图显示前10条
	    Map chartMap = dataSearchService.getPieAndMixChartData(request,pageList,columField,map,reportType,DataSearchConstantUtil.DEPTH_DIGGING,request.getParameter("sort")==null?null:request.getParameter("sort"));
	    String pieChartFieldZhName =  (String) map.get((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MIXCHART_FIELDZHNAME);
	    
	    //json展示给前台
  		JSONObject jsonObject = new JSONObject();
//  		if(originListAll != null && !originListAll.isEmpty())
//  		{
//  		}
  		jsonObject.put("total", originListAll.size());
  	    jsonObject.put("rows",pageList == null ? Lists.newArrayList() :pageList);
  	    jsonObject.put("mixChart",chartMap.get("mixChart"));
  		jsonObject.put("pieChart", chartMap.get("pieChart"));
  		jsonObject.put("reportType",reportType);
  		jsonObject.put("reportZhName", InitCountryCENameUtil.getReportTypeZhName(reportType));
  		jsonObject.put("pieChartFieldZhName",pieChartFieldZhName.split(",")[0]);
  		response.getWriter().println(jsonObject.toString());
  		return null;
	}
	
	
	/**
	 * 动态生成各国家数据对比页面数据表格中的字段列
	 * @param request
	 * @param response
	 * @param reportType 报告类型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/generateDataCompareField")
	public String generateDataCompareField(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//通过"洪"分隔 例如:cp_trade洪qs洪aaaaaaa洪date
		String hideColumn = request.getParameter("hideColumn");
		hideColumn = URLDecoder.decode(hideColumn,"UTF-8");  
		PrintWriter out = response.getWriter();
		//获取当前的国家
		String country = (String) request.getSession().getAttribute("country");
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//根据国家生成对应国家需要展示的列
		String htmlData = DataCompareUtil.fmtHtml(country, map,hideColumn.split("罟")[1]);
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		out.write(json.toString());
		return null;
	}
	
	/**
	 * 所有国家报表数据对比方法
	 * @param request
	 * @param response
	 * @param hideColumn 通过"洪"分隔 例如 cp_trade洪qs洪aaaaaaa洪date
	 * @param selectedValue 选中待对比的数据通过"洪"分隔 例如 aaaaaaa洪bbbbbb洪cccccccc....
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/generateDataCompareData")
	public String generateDataCompareData(HttpServletRequest request,HttpServletResponse response,String hideColumn,String selectedValue) throws Exception
	{
		//获取待对比的数据集合
		List<DataReportSumModel> originListAll = dataSearchService.getDataCompareReportList(request,hideColumn,selectedValue);
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//计算比率,并处理汇总集合排序字段的精度
		TradeResultHandleUtil.processPrecision(request,TradeResultPageUtil.getPercentage(request,originListAll,originListAll,map),map,DataSearchConstantUtil.REPORT,2);
		//根据客户端请求排序
		new DataGridSortUtil().executeSearchSort(request,originListAll,map,DataSearchConstantUtil.REPORT);
		
		//备份原始集合
		List<DataReportSumModel> listAll = new LinkedList<DataReportSumModel>();
		listAll = TradeResultHandleUtil.getNewList(originListAll, listAll);
		
		//实例化分页工具
		PageBeanUtil beanUtil = commonSearchService.getPageBeanUtil(request,"10");
		//获取分页集合
		List<DataReportSumModel> pageList = new LinkedList<DataReportSumModel>();
		int startIndex = commonSearchService.getStartIndex(beanUtil);
		int endIndex = commonSearchService.getEndIndex(beanUtil, listAll, startIndex);
		for(int i=startIndex;i<endIndex;i++)
		{
			pageList.add(listAll.get(i));
		}
		
		//每个数据表格的列名
		String columField = map.get(((String)request.getSession().getAttribute("country")+"_"+hideColumn.split("罟")[1]+DataSearchConstantUtil.FIELDID)).toString().split(",")[1];
		//处理当前页集合
		pageList = dataSearchService.getHyperlinkList(request,dataSearchService.setHideColumnValue(request,pageList,columField,hideColumn.split("罟")[1],hideColumn.split("罟")[0]),columField,map,hideColumn.split("罟")[1]);
		//当前页汇总
		List<DataReportSumModel> currentPageFooter = TradeResultPageUtil.currentPageSummary(request, columField, pageList, map);
		//处理当前页汇总的精度
		TradeResultHandleUtil.processPrecision(request,currentPageFooter,map,DataSearchConstantUtil.REPORT,1);
		
		//饼图和柱状图的map总集合
	    Map chartMap = dataSearchService.getPieAndMixChartData(request,originListAll,columField,map,hideColumn.split("罟")[1],DataSearchConstantUtil.DATA_COMPARE,request.getParameter("sort")==null?null:request.getParameter("sort"));
	   //From ZH  -> EN
	    String pieChartFieldZhName =  (String) map.get((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MIXCHART_FIELDENNAME);
	    //json展示给前台
  		JSONObject jsonObject = new JSONObject();
  		if(listAll != null)
  		{
  			jsonObject.put("total", listAll.size());
  		}
  	    jsonObject.put("rows",pageList == null ? new LinkedList():pageList);
  	    jsonObject.put("footer",currentPageFooter);
  	    jsonObject.put("mixChart",chartMap.get("mixChart"));
  		jsonObject.put("pieChart", chartMap.get("pieChart"));
  		jsonObject.put("reportType",hideColumn.split("罟")[1]);
  		jsonObject.put("reportZhName", "Data Comparison ");
  		jsonObject.put("pieChartFieldZhName",pieChartFieldZhName.split(",")[0]);
  		response.getWriter().println(jsonObject.toString());
  		return null;
	}
	
	/**
	 * 动态生成各国报表汇总字段
	 * @param request
	 * @param response
	 * @param reportType 当前报告类型 如 jks cks
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadSummaryField")
	public String loadSummaryField(HttpServletRequest request,HttpServletResponse response,String reportType) throws Exception{
		PrintWriter out = response.getWriter();
		//获取当前的国家
		String country = (String) request.getSession().getAttribute("country");
		//获取map对象
		Map resultFieldMap = resultFieldModel.getResultFieldMap();
		//根据国家生成对应国家报表汇总的列
		String htmlData = LoadReportSummaryFieldUtil.fmtLoadDataHtml(country, reportType, resultFieldMap);
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		json.put("tableId", reportType + "_LoadDataTable"); 
		out.write(json.toString());
		return null;
	}
	
	/**
	 * 动态生成所有国家同环比页面html代码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/generateTHBReportfield")
	public String generateTHBReportfield(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PrintWriter out = response.getWriter();
		//获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		String htmlData = ThbReportUtil.fmtHtml(request,country, map);
		//同环比报告类型
		String thbReport = map.get(country+DataSearchConstantUtil.THB_SHOWTYPE).toString();
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		json.put("thbReport", thbReport); 
		out.write(json.toString());
		return null;
	}
	
	/**
	 * 所有国家月度同比，月度环比，年度同比总方法
	 * @param request
	 * @param response
	 * @param value 通过"洪"分隔 例如 cp_trade洪qs洪aaaaaaa洪date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/generateTHBReportData")
	public String generateTHBReportData(HttpServletRequest request,HttpServletResponse response,String singleReport,String reportType,String value) throws Exception{
		PrintWriter out = response.getWriter();
		List list = dataSearchService.getThbReportList (request,singleReport,reportType,value);
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//处理当前页汇总的精度
		TradeResultHandleUtil.processPrecision(request,list,map,singleReport,2);
		//饼图和柱状图的map总集合
		Map chartMap = new HashMap();
		//根据客户端请求排序
		new DataGridSortUtil().executeSearchSort(request,list,map,DataSearchConstantUtil.THB);
		
		//混搭图需要展示的字段
		String[] mixChartFieldsId =null;
		
		//月度环比
		if(DataSearchConstantUtil.MOM.equals(singleReport))
		{
			chartMap = dataSearchService.getPieAndMixChartData(request,list,DataSearchConstantUtil.DATE_LUCENE,map,reportType,DataSearchConstantUtil.MOM_REPORT,request.getParameter("sort")==null?null:request.getParameter("sort"));
			mixChartFieldsId = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MOM_MIXCHART_FIELDID)).toString().split(",");
		}
		//月度同比
		else if(DataSearchConstantUtil.MYOY.equals(singleReport))
		{
			chartMap = dataSearchService.getPieAndMixChartData(request,list,DataSearchConstantUtil.DATE_LUCENE,map,reportType,DataSearchConstantUtil.MYOY_REPORT,request.getParameter("sort")==null?null:request.getParameter("sort"));
			mixChartFieldsId = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MYOY_MIXCHART_FIELDID)).toString().split(",");
		}
		//年度同比
		else if(DataSearchConstantUtil.YOY.equals(singleReport))
		{
			chartMap = dataSearchService.getPieAndMixChartData(request,list,DataSearchConstantUtil.DATE_LUCENE,map,reportType,DataSearchConstantUtil.YOY_REPORT,request.getParameter("sort")==null?null:request.getParameter("sort"));
			//混搭图需要展示的字段
			mixChartFieldsId = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.YOY_MIXCHART_FIELDID)).toString().split(",");
		}
		//change from zh - > en
		 String pieChartFieldZhName =  (String) map.get((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MIXCHART_FIELDENNAME);
		
		JSONObject jsonObject = new JSONObject();
		if(list != null)
  		{
			//判断是否显示同环比
			boolean showTHB = false;
			for(String key :mixChartFieldsId)
			{
				double sum = 0;
				for(int i = 0;i<list.size();i++)
				{
					sum += Double.parseDouble(BeanUtils.getProperty(list.get(i), key));
				}
				if(sum > 0)
				{
					showTHB = true;
					break;
				}
				else
				{
					showTHB = false;
				}
			}
			jsonObject.put("showTHB", showTHB);
  			jsonObject.put("total", list.size());
  		}
		jsonObject.put("rows",list == null ? new LinkedList():list);
		jsonObject.put("mixChart",chartMap.get("mixChart"));
		jsonObject.put("pieChart", chartMap.get("pieChart"));
		jsonObject.put("showType",singleReport);
		//language issue
		//jsonObject.put("showTypeZhName",InitCountryCENameUtil.getReportTypeZhName(singleReport));
		jsonObject.put("showTypeZhName",InitCountryCENameUtil.getReportTypeEnName(singleReport));
		jsonObject.put("pieChartFieldZhName",pieChartFieldZhName.split(",")[0]);
		response.getWriter().println(jsonObject.toString());
		return null;
	}
	
	/**
	 *  动态生成所有国家多海关编码备注页面html代码
	 * @param request
	 * @param response
	 * @param hideColumn 例如 cp_trade洪qs洪aaaaaaa洪date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/generateMutihscodefield")
	public String generateMutihscodefield(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//通过"洪"分隔 例如:cp_trade洪qs洪aaaaaaa洪date
		String hideColumn = request.getParameter("hideColumn");
		hideColumn = URLDecoder.decode(hideColumn,"UTF-8");  
		PrintWriter out = response.getWriter();
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		String htmlData = MutiHscodeUtil.fmtHtml(country, map, hideColumn.split("罟")[1]);
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData); 
		out.write(json.toString());
		return null;
	}
	
	
	/**
	 * 获取多海关编码备注数据
	 * @param request
	 * @param response
	 * @param hideColumn 例如 cp_trade洪qs洪aaaaaaa洪date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMultiHscodeRemarkData")
	public String getMultiHscodeRemarkData(HttpServletRequest request,HttpServletResponse response,String hideColumn) throws Exception
	{
		PrintWriter out = response.getWriter();
		//获取当前的国家名称
		String country = (String) request.getSession().getAttribute("country");
		// 获取map对象
		Map map = resultFieldModel.getResultFieldMap();
		//获取多海关编码集合
		List<DataReportSumModel> mutiHscodeList = multiHscodeMarkService.getMultiHscodeMarkInfo(hideColumn.split("罟")[2],country,hideColumn.split("罟")[1]);
		//根据客户端请求排序
		new DataGridSortUtil().executeSearchSort(request,mutiHscodeList,map,DataSearchConstantUtil.REPORT);
		//处理当前页汇总的精度
		TradeResultHandleUtil.processPrecision(request,mutiHscodeList,map,DataSearchConstantUtil.REPORT,1);
		//前台展示给用户
		JSONObject jsonObject = new JSONObject();
		if(mutiHscodeList != null)
  		{
			jsonObject.put("total", mutiHscodeList.size());
  		}
		jsonObject.put("rows",mutiHscodeList == null ? new LinkedList():mutiHscodeList);
		out.write(jsonObject.toString());
		return null;
	}
}
