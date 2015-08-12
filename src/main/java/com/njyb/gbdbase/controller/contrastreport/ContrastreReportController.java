package com.njyb.gbdbase.controller.contrastreport;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.export.ExportDataUtil;
import com.njyb.gbdbas.util.export.ExportExcelUtil;
import com.njyb.gbdbase.model.contrastreport.querybean.CommonSearchModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;
import com.njyb.gbdbase.service.common.engines.IReportDrillService;
import com.njyb.gbdbase.service.contrastreport.contrast.ICompareService;
import com.njyb.gbdbase.service.contrastreport.contrast.component.CountryAllManagerComponent;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;
/** 
 * 新增进出口商控制层
 * @author 章华才
 * 2015-03-27
 */
@RequestMapping(value="contrastre")
@Controller
public class ContrastreReportController{
	
	//注入业务层
	@Autowired
	private ICompareService service;
	
	//处理中文国家转化为英文国家的业务接口类
	@Autowired
	private IDataSearchService dataSearche;
	
	//注入深度挖取业务接口
	@Autowired
	private IReportDrillService drillService;
	
	//注入报表明细业务接口
	@Autowired
	private IReportDetailService detailService;
	
	@Resource
	private CountryAllManagerComponent allManagerComponet;
	
	//存放新增流失后的集合
//	private List<DataReportSumModel> lists = null;
	/**
	 * 执行新增/流失controller
	 * @param request   
	 * @param response
	 * @param bean   公共查询model
	 * @param sortKey 排序字段
	 * @param countryName 国家名称
	 * @param imexType  进出/口商
	 * @param addOrOut  是否是新增/流失
	 * @param sort easyui传送过来的排序字段名称  cifValue/netWeight....
	 * @param order easyui传送过来的排序类型 desc/asc
	 * @return null
	 * @author 章华才
	 */
	@RequestMapping(value="contrastreAddOutController")
	public String addContrastre(HttpServletRequest request,HttpServletResponse response,
			                     CommonSearchModel bean,String sortKey,String countryName,String imexType,String addOrOut,String sort,String order){
		
		System.out.println("comein...");
		JSONObject jsonObject = new JSONObject();
		//将中文的国家转化为英文
		countryName = InitCountryCENameUtil.queryCountryEnName(countryName);
//		if(!countryName.equals("china") && !countryName.equals("korea"))
//		{
//			//重置国家value
//			countryName = countryName + "_" + imexType;
//		}
		//将国家放入到map
		SearchPropertiesModel.getPropertiesMap().put("countryName", countryName);
		//实例化分页工具
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request,"10");
		List<DataReportSumModel> lists=service.addImportAndExport(request, null, null, bean, sortKey,countryName,addOrOut,imexType);
		//将集合数据放入到缓存中
		allManagerComponet.createCacheSetList(lists,"reportListAll","reportContrastreCache");
		//分页
		beanUtil.setPageCount(lists == null? 0:lists.size());
		Map<String, DataReportSumModel> map = allManagerComponet.getCount(lists,addOrOut,imexType);
		jsonObject.put("total", beanUtil.getPageCount());
		jsonObject.put("rows", lists == null||lists.size() ==0?"":allManagerComponet.orderListDescOrAsc(allManagerComponet.pageList(lists, beanUtil), order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,countryName):sort, "double"));
		jsonObject.put("barChart",map.get("barChart"));
		jsonObject.put("pieChart", map.get("pieChart"));
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 保持新增/流失进出/口商
	 * @param request
	 * @param response
	 * @param bean  公共查询model
	 * @param sortKey 排序字段
	 * @param countryName 国家名称
	 * @param imexType  进出/口商类型
	 * @param addOrOut  是否是新增/流失
	 * @param rows     每页显示多少条数据
	 * @param i        表示哪一段时间的数据
	 * @param sort easyui传送过来的排序字段名称 cifValue/fobValue
	 * @param order easyui 传送过来的排序类型 desc/asc
	 * @return null
	 * @author 章华才
	 */
	@RequestMapping(value="contrastreKeepController")
	public String KeepContrastre1(HttpServletRequest request,HttpServletResponse response,
			                     CommonSearchModel bean,String sortKey,String countryName,String imexType,String addOrOut,Integer rows,
			                     String i,String sort,String order){
		
		JSONObject jsonObject = new JSONObject();
		//将中文的国家转化为英文
		countryName = InitCountryCENameUtil.queryCountryEnName(countryName);
//		if(!countryName.equals("china") && !countryName.equals("korea"))
//		{
//			//重置国家value
//			countryName = countryName + "_" + imexType;
//		}
		//实例化分页工具
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request,"20");
		Map<String,DataReportSumModel>  map = service.keepImporterAndExporter(request, null, null, bean, sortKey, countryName, addOrOut,imexType);
		List<DataReportSumModel> list1 = null;
		if(map != null)
		{
			if(i.equals("1"))
			{
				list1 = (List<DataReportSumModel>) map.get("data1");
				jsonObject.put("date", bean.getBeginDate() + " 至   " +bean.getEndDate());
			}
			else if(i.equals("2"))
			{
				list1 = (List<DataReportSumModel>) map.get("data2");
				jsonObject.put("date", bean.getBeginAddDate() + " 至  " +bean.getEndAddDate());
			}
		}
		//分页
		beanUtil.setPageCount(list1 == null? 0:list1.size());
		jsonObject.put("total", beanUtil.getPageCount());
		jsonObject.put("rows", list1==null||list1.size()==0?"":allManagerComponet.pageList(list1, beanUtil));
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 导出对比报表excel
	 * @param request
	 * @param response
	 * @param paman
	 * @param countryName
	 * @param imexType
	 * @return
	 */
	@RequestMapping(value = "exportExcel")
	public String exportExcle(HttpServletRequest request,HttpServletResponse response,String paman,String countryName,String imexType){
		//将中文国家转化为英文
		String country = null;
		try {
			//URLDecoder.decode解码
			country = InitCountryCENameUtil.queryCountryEnName(URLDecoder.decode(countryName,"UTF-8"));  
			paman = URLDecoder.decode(paman,"UTF-8");  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		List<DataReportSumModel> ls = new ArrayList<DataReportSumModel>();
		//获取map集合
		Map<String, DataReportSumModel> map = allManagerComponet.getDataReportModelMap(allManagerComponet.getCacheList("reportListAll","reportContrastreCache"),(String) SearchPropertiesModel.getPropertiesMap().get("countryName"),null,imexType);
		String[] str = paman.split(",");
		//根据选中的进出口商去map当中找，找到后加入到新的集合当中
		for (String s : str) {
			ls.add(map.get(s));
		}
		//执行导出execl方法
		List<String[]> data = ExportDataUtil.getList(ls, allManagerComponet.getFieldsByCountryName(request,country,imexType).get("field"));
		String [] field = allManagerComponet.getFieldsByCountryName(request,country,imexType).get("strName");
		ExportExcelUtil.exportExcel("报表对比信息", data, field, new String[]{}, new String[]{}, request, response);
		return null;
	}
	
	/**
	 * 获取对比下拉框的值
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "selectValues")
	public String getSelectValue(HttpServletRequest request,HttpServletResponse response){
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			JSONObject json = new JSONObject();
			//从缓存中拿去数据
			json.put("selectValue", allManagerComponet.getCacheList("reportListAll","reportContrastreCache"));
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 进出口商对比报表
	 * @param request
	 * @param response
	 * @param val 要对比的进出口商
	 * @param arr 选中的对比数组
	 * @return
	 * @throws Exception 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "contrastByImportOrExport")
	public String duiBiInfo(HttpServletRequest request,HttpServletResponse response,String val,String order,String sort,String imexType) throws IllegalAccessException, InvocationTargetException, Exception{
		//从map中获取国家
		String countryName = (String) SearchPropertiesModel.getPropertiesMap().get("countryName");
		PrintWriter writer = null;
		try {
			 writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取选中数据的数组
		String [] arr = request.getParameterValues("arr[]");
		String [] str = new String[arr.length+1];
		for (int i = 0; i < arr.length; i++) {
			str[i] = arr[i];
		}
		str[arr.length] = val;
		//拿到map集合
		Map<String, DataReportSumModel> map = allManagerComponet.getDataReportModelMap(allManagerComponet.getCacheList("reportListAll","reportContrastreCache"),countryName,null,imexType);
		//新集合
		List<DataReportSumModel> ls = new ArrayList<DataReportSumModel>();
		for (String s : str) {
			ls.add(map.get(s));
		}
		//获取图表展示集合
		Map<String, DataReportSumModel> m = allManagerComponet.getLineInfo(request,ls, countryName,null,imexType,request.getParameter("sort")==null?null:request.getParameter("sort"));
		JSONObject jsonObject = new JSONObject();
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request,"10");
		beanUtil.setPageCount(ls.size()==0?0:ls.size());
		jsonObject.put("total", beanUtil.getPageCount());
		jsonObject.put("rows", ls==null?"":allManagerComponet.orderListDescOrAsc(allManagerComponet.pageList(ls, beanUtil), order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,countryName):sort, "double"));
		jsonObject.put("showChart",m.get("showChart"));
		jsonObject.put("showPie", m.get("showPie"));
		jsonObject.put("ch_name", m.get("ch_name"));
		writer.write(jsonObject.toString());
		writer.close();
		return null;
	}
	
	/**
	 * 报表明细controller
	 * @param request
	 * @param response
	 * @param ie 进出口商/公司名称
	 * @return null
	 * @throws Exception 
	 */
	@RequestMapping(value = "reportDetailInfo")
	public String reportDetail(HttpServletRequest request,HttpServletResponse response,String ie,String order,String sort,String isAdd) throws Exception{
		String countryName = (String) SearchPropertiesModel.getPropertiesMap().get("countryName");
		/*贾红平 向接口中添加一个参数*/
		List<DataReportSumModel> ls = detailService.builderSingleList(ie,isAdd,countryName);
		JSONObject jsonObject = new JSONObject();
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request, "10");
		beanUtil.setPageCount(ls.size()==0?0:ls.size());
		jsonObject.put("total", beanUtil.getPageCount());
		jsonObject.put("rows", allManagerComponet.orderListDescOrAsc(allManagerComponet.pageList(ls, beanUtil), order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,(String)SearchPropertiesModel.getPropertiesMap().get("countryName")):sort, "double"));
		response.getWriter().write(jsonObject.toString());
		response.getWriter().close();
		return null;
	}
	
	/**
	 * 从配置文件中获取明细需要绑定的参数字段和名称
	 * @param request
	 * @param response
	 * @param countryName 国家名称
	 * @return
	 */
	@RequestMapping(value = "showDetailField")
	public String showDetailField(HttpServletRequest request,HttpServletResponse response,String countryName){
		JSONObject jsonObject = new JSONObject();
		//将中文国家转化为英文国家
		String chCountryName =  InitCountryCENameUtil.queryCountryEnName(countryName);
		String countryNameNow = null;
		if(chCountryName == null || "".equals(chCountryName)){
			countryNameNow = countryName;
		}else
		{
			countryNameNow = chCountryName;
		}
		jsonObject.put("name", allManagerComponet.detailShowField(countryNameNow).get("name"));
		jsonObject.put("id", allManagerComponet.detailShowField(countryNameNow).get("Id"));
		try {
			response.getWriter().write(jsonObject.toString());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 从配置文件中获取深度挖取要绑定的参数字段和名称
	 * @param request
	 * @param response
	 * @param countryName
	 * @return
	 */
	@RequestMapping(value = "getDrillField")
	public String showDrillField(HttpServletRequest request,HttpServletResponse response,String countryName,String imexType){
		JSONObject jsonObject = new JSONObject();
		//初始化配置文件
		new LoadPropertiesUtil().init(request, "contrastreCenter");
		//将中文的国家名称换成英文
		countryName = InitCountryCENameUtil.queryCountryEnName(countryName);
//		String [] country = countryName.split("_");
//		if(country.length != 1){
//			countryName = country[0] + "_" + imexType;
//		}
		String [] drillType = LoadPropertiesUtil.getProp(countryName + "_type_en");
		String [] drillTypeName = LoadPropertiesUtil.getProp(countryName + "_type_ch");
		
		String [] sumField = LoadPropertiesUtil.getProp(countryName + "_sum_field");
		String [] sumName = LoadPropertiesUtil.getProp(countryName + "_sum_name");
		
		jsonObject.put("drillType", drillType);
		jsonObject.put("drillTypeName", drillTypeName);
		jsonObject.put("sumField", sumField);
		jsonObject.put("sumName", sumName);
		try {
			response.getWriter().write(jsonObject.toString());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 判断国家是否存在进口和出口
	 * @param request
	 * @param response
	 * @param countryName
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "judgeCountryIsHaveImportOrExport")
	public String judgeCountryIsHaveImportOrExport(HttpServletRequest request,HttpServletResponse response,String countryName) throws Exception{
		String country = InitCountryCENameUtil.queryCountryEnName(countryName);
		//初始化配置文件
		new LoadPropertiesUtil().init(request, "contrastreCenter");
		//获取该国家是否存在进口和出口
		String [] importOrExport = LoadPropertiesUtil.getProp(country);
		//获取所有不存在海关编码的国家
		String presenceHscode = LoadPropertiesUtil.getValue("query_contry_hscode");
		//获取是否存在产品描述
		String goodsDescStr = LoadPropertiesUtil.getValue("query_contry_goodsDesc");
		boolean isHscode = true;
		boolean isGoodsDesc = true;
		//如果进入if所以该国家不存在海关编码查询字段
		String [] contry_hscode = presenceHscode.split(",");
		String [] goodsDescs = goodsDescStr.split(",");
		for (String str : contry_hscode) {
			if(country.contains(str)){
				isHscode = false;
			}
		}
		for (String str : goodsDescs) {
			if(country.contains(str)){
				isGoodsDesc = false;
			}
		}
		
		
		StringBuffer sb = new StringBuffer();
		if(importOrExport != null){
			for (String s : importOrExport) {
				if(s.contains("import")){
					sb.append("<option value='"+s+"'>采购商</option>");
				}else if(s.contains("export")){
					sb.append("<option value='"+s+"'>供应商</option>");
				}
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ie", sb.toString());
		jsonObject.put("hscode", isHscode);
		jsonObject.put("goodsDesc", isGoodsDesc);
		response.getWriter().write(jsonObject.toString());
		return null;
	}
	
	
	/**
	 * 对比中心深度挖取(原产国汇总)
	 * @param request
	 * @param response
	 * @param value  具体某个值
	 * @param type   报表类型
	 * @param countryName  国家
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "country")
	public String reportCenterDrillByOrigin(HttpServletRequest request,HttpServletResponse response,String value,String country,String sort,String order,String isMany,String isAdd,String type,String imexType) throws Exception{
		JSONObject jsonObject = new JSONObject();
		//将中文国家名称换成英文名称
		country = InitCountryCENameUtil.queryCountryEnName(country);
		List<DataReportSumModel> originList = drillService.drillReportSumList(value, isAdd, country,"drill&"+type);
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request, "10");
		//显示前十条
		if(isMany == null)
		{
			originList = allManagerComponet.defaultShowTen(originList);
			jsonObject.put("total", originList.size());
			jsonObject.put("rows", allManagerComponet.orderListDescOrAsc(originList, order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,country):sort, "double"));
		}
		//显示更多
		else
		{
			beanUtil.setPageCount(originList.size()==0?0:originList.size());
			jsonObject.put("total", beanUtil.getPageCount());
			jsonObject.put("rows", allManagerComponet.orderListDescOrAsc(allManagerComponet.pageList(originList, beanUtil), order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,country):sort, "double"));
		}
		//获取图表展示集合
		Map<String, DataReportSumModel> m = allManagerComponet.getLineInfo(request,originList, country,type,imexType,sort==null?null:sort);
		jsonObject.put("showChart",m.get("showChart"));
		jsonObject.put("showPie", m.get("showPie"));
		response.getWriter().write(jsonObject.toString());
		return null;
	}
	
	/**
	 * 对比中心深度挖取(产品描述汇总)
	 * @param request
	 * @param response
	 * @param value  具体某个值
	 * @param type   报表类型
	 * @param countryName  国家
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "goodsDesc")
	public String reportCenterDrillByGoodsDesc(HttpServletRequest request,HttpServletResponse response,String value,String country,String sort,String order,String isMany,String isAdd,String type,String imexType) throws Exception{
		JSONObject jsonObject = new JSONObject();
		//将中文国家名称换成英文名称
		country = InitCountryCENameUtil.queryCountryEnName(country);
		List<DataReportSumModel> originList = drillService.drillReportSumList(value, isAdd, country,"drill&"+type);
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request, "10");
		//显示前十条
		if(isMany == null)
		{
			originList = allManagerComponet.defaultShowTen(originList);
			jsonObject.put("total", originList.size());
			jsonObject.put("rows", allManagerComponet.orderListDescOrAsc(originList, order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,country):sort, "double"));
		}
		//显示更多
		else
		{
			beanUtil.setPageCount(originList.size()==0?0:originList.size());
			jsonObject.put("total", beanUtil.getPageCount());
			jsonObject.put("rows", allManagerComponet.orderListDescOrAsc(allManagerComponet.pageList(originList, beanUtil), order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,country):sort, "double"));
		}
		//获取图表展示集合
		Map<String, DataReportSumModel> m = allManagerComponet.getLineInfo(request,originList, country,type,imexType,sort==null?null:sort);
		jsonObject.put("showChart",m.get("showChart"));
		jsonObject.put("showPie", m.get("showPie"));
		response.getWriter().write(jsonObject.toString());
		return null;
	}
	
	/**
	 * 对比中心深度挖取(进出口商/公司名称汇总)
	 * @param request
	 * @param response
	 * @param value  具体某个值
	 * @param type   报表类型
	 * @param countryName  国家
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "trande")
	public String reportCenterDrillByImportOrExportOrCompanyName(HttpServletRequest request,HttpServletResponse response,String value,String type,String country,String sort,String order,String isAdd,String isMany,String imexType) throws Exception{
		JSONObject jsonObject = new JSONObject();
		//将中文国家名称换成英文名称
		country = InitCountryCENameUtil.queryCountryEnName(country);
		List<DataReportSumModel> originList = drillService.drillReportSumList(value,isAdd , country,"drill&"+type);
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request, "10");
		//显示前十条
		if(isMany == null)
		{
			originList = allManagerComponet.defaultShowTen(originList);
			jsonObject.put("total", originList.size());
			jsonObject.put("rows", allManagerComponet.orderListDescOrAsc(originList, order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,country):sort, "double"));
		}
		//显示更多
		else
		{
			beanUtil.setPageCount(originList.size()==0?0:originList.size());
			jsonObject.put("total", beanUtil.getPageCount());
			jsonObject.put("rows", allManagerComponet.orderListDescOrAsc(allManagerComponet.pageList(originList, beanUtil), order==null?"desc":order, sort==null?allManagerComponet.getSortName(request,country):sort, "double"));
		}
		//获取图表展示集合
		Map<String, DataReportSumModel> m = allManagerComponet.getLineInfo(request,originList, country,type,imexType,sort==null?null:sort);
		jsonObject.put("showChart",m.get("showChart"));
		jsonObject.put("showPie", m.get("showPie"));
		response.getWriter().write(jsonObject.toString());
		return null;
	}
	
	/**
	 * 对比中心深度挖取(交易趋势（日期）汇总)
	 * @param request
	 * @param response
	 * @param value  具体某个值
	 * @param type   报表类型
	 * @param countryName  国家
	 * @return
	 */
	@RequestMapping(value = "reportCenterDrillByDate")
	public String reportCenterDrillByDate(HttpServletRequest request,HttpServletResponse response,String value,String type,String countryName){
		return null;
	}
	
	
	/**
	 * 获取绑定datagrid的参数字段
	 * @param request
	 * @param response
	 * @param country
	 * @return
	 */
	@RequestMapping(value = "getDatagridField")
	public String getDatagridField(HttpServletRequest request,HttpServletResponse response,String country,String ieType){
		//初始化配置文件
		new LoadPropertiesUtil().init(request, "contrastreCenter");
		//将中文国家转化为英文china_id
		String countryName = InitCountryCENameUtil.queryCountryEnName(country);
		String [] country_fieldId = null;
		String [] country_fieldName = null;
		if(countryName.equals("china") || countryName.equals("korea")){
			country_fieldId = LoadPropertiesUtil.getProp(countryName +"_id");
			country_fieldName = LoadPropertiesUtil.getProp(countryName +"_name");
		}else{
			country_fieldId = LoadPropertiesUtil.getProp(countryName.split("_")[0] + "_" + ieType +"_id");
			country_fieldName = LoadPropertiesUtil.getProp(countryName.split("_")[0] + "_" + ieType  +"_name");
		}
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("field_id", country_fieldId);
		jsonObject.put("field_name", country_fieldName);
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
