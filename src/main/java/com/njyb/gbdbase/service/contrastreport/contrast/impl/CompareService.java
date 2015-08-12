package com.njyb.gbdbase.service.contrastreport.contrast.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.contrastreport.querybean.CommonSearchModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.contrastreport.contrast.ICompareService;
import com.njyb.gbdbase.service.contrastreport.contrast.component.CommomSumDataComponent;
import com.njyb.gbdbase.service.contrastreport.contrast.component.CountryAllManagerComponent;

/**
 * 对比报表service业务层
 * 用于所有国家的新增进出口商、流失进出口商、保持进出口商
 * @author 章华才
 */
@Service
public class CompareService implements ICompareService{

	//公共国家使用工具类
	@Resource
	private CountryAllManagerComponent allManagerComponet;
	
	@Resource
	private CommomSumDataComponent sumDataComponent;
	
	
	/**
	 * 新增进出口商
	 * @param request 
	 * @param field	             查询lucenc用到的字段名称
	 * @param values    查询字段的值
	 * @param model		查询用到的查询bean
	 * @param sortKey	排序字段
	 * @return list<T>
	 */
	@Override
	@SuppressWarnings("all")
	public List<DataReportSumModel> addImportAndExport(HttpServletRequest request,
			String[] field, String[] values, CommonSearchModel model,
			String sortKey,String countryName,String isAdd,String imexType) {
		List<DataReportSumModel> firstlists = new ArrayList<DataReportSumModel>();
		List<DataReportSumModel> secondLists = new ArrayList<DataReportSumModel>();
		//进行对比过后的新集合
		List<DataReportSumModel> newList = new ArrayList<DataReportSumModel>();
		//处理日期格式
		allManagerComponet.isValidateByCountry(model, countryName, "1");
		//设置第一段查询时间
		model.setFirstDate(model.getBeginDate()+","+model.getEndDate());
		//设置第一次查询条件字段
		Map map = allManagerComponet.setParameters(request,countryName,model);
		if(map == null)
		{
			return null;
		}
		String [] fieldKey = allManagerComponet.findByFieldKey(map);
		String [] fieldValue = allManagerComponet.findByFieldValue(map);
		
		//返回第一次时间查询的汇总集合
		firstlists = (List<DataReportSumModel>) sumDataComponent.getCommonSumData(request, fieldKey, fieldValue, countryName, sortKey, imexType, isAdd, "first");
		//处理日期格式
		allManagerComponet.isValidateByCountry(model, countryName, "2");
		//设置第二段查询时间
		model.setFirstDate(model.getBeginAddDate()+","+model.getEndAddDate());
		//设置第二次查询条件字段
		map = allManagerComponet.setParameters(request,countryName,model);
		fieldKey = allManagerComponet.findByFieldKey(map);
		fieldValue = allManagerComponet.findByFieldValue(map);
		//返回第二次时间查询的汇总集合
		secondLists = (List<DataReportSumModel>) sumDataComponent.getCommonSumData(request, fieldKey, fieldValue, countryName, sortKey, imexType, isAdd, "second");
		if(isAdd.equals("add"))
		{
			//新增对比过后处理的集合
			newList = allManagerComponet.addImportAndExport(firstlists, secondLists,allManagerComponet.judgeByCountryName(imexType),countryName);
		}
		else if(isAdd.equals("out")){
			//流失对比过后的集合
			newList = allManagerComponet.outImportAndExport(firstlists, secondLists,allManagerComponet.judgeByCountryName(imexType));
		}
		List<DataReportSumModel> lss = null;
		try {
			lss =allManagerComponet.getList(newList, isAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lss;
	}

	/**
	 * 保持进出口商
	 * @param request 
	 * @param field	             查询lucenc用到的字段名称
	 * @param values    查询字段的值
	 * @param model		查询用到的查询bean
	 * @param sortKey	排序字段
	 * @return list<T>
	 */
	@Override
	@SuppressWarnings("all")
	public Map<String,DataReportSumModel> keepImporterAndExporter(
		HttpServletRequest request, String[] field, String[] values,
		CommonSearchModel model, String sortKey,String countryName,String isAdd,String imexType) {
		List<DataReportSumModel> firstlists = new ArrayList<DataReportSumModel>();
		List<DataReportSumModel> secondLists = new ArrayList<DataReportSumModel>();
		//进行对比过后的新集合
		Map<String,DataReportSumModel> newList =  new HashMap<String, DataReportSumModel>();
		//处理日期格式
		allManagerComponet.isValidateByCountry(model, countryName, "1");
		//设置第一段查询时间
		model.setFirstDate(model.getBeginDate()+","+model.getEndDate());
		//设置第一次查询条件字段
		Map map = allManagerComponet.setParameters(request,countryName,model);
		if(map == null)
		{
			return null;
		}
		String [] fieldKey = allManagerComponet.findByFieldKey(map);
		String [] fieldValue = allManagerComponet.findByFieldValue(map);
		
		//返回第一次时间查询的汇总集合
		firstlists = (List<DataReportSumModel>) sumDataComponent.getCommonSumData(request, fieldKey, fieldValue, countryName, sortKey, imexType, isAdd, "first");
		
		//处理日期格式
		allManagerComponet.isValidateByCountry(model, countryName, "2");
		//设置第二段查询时间
		model.setFirstDate(model.getBeginAddDate()+","+model.getEndAddDate());
		//设置第二次查询条件字段
		map = allManagerComponet.setParameters(request,countryName,model);
		fieldKey = allManagerComponet.findByFieldKey(map);
		fieldValue = allManagerComponet.findByFieldValue(map);
//		//返回第二次时间查询的汇总集合
		secondLists = (List<DataReportSumModel>) sumDataComponent.getCommonSumData(request, fieldKey, fieldValue, countryName, sortKey, imexType, isAdd, "second");
		newList = allManagerComponet.KeepImportAndExport(firstlists, secondLists, allManagerComponet.judgeByCountryName(imexType));
		return newList;
	} 

	
	/**
	 * 流失进出口商
	 * @param request 
	 * @param field	             查询lucenc用到的字段名称
	 * @param values    查询字段的值
	 * @param model		查询用到的查询bean
	 * @param sortKey	排序字段
	 * @return list<T>
	 */
	@Override
	@SuppressWarnings("all")
	public <T> List<DataReportSumModel> outflowImportAndExport(HttpServletRequest request,
			String[] field, String[] values, CommonSearchModel model,
			String sortKey,String countryName,String isAdd,String imexType) {
		
//		//第一段日期查询出来的集合
//		List<T> firstlists = new ArrayList<T>();
//		//第二段日期查询出来的集合
//		List<T> secondLists = new ArrayList<T>();
//		//进行对比过后的新集合
//		List<T> newList = new ArrayList<T>();
//		
//		//处理日期格式
//		allManagerComponet.isValidateByCountry(model, countryName, "1");
//		//设置第一段查询时间
//		model.setFirstDate(model.getBeginDate()+","+model.getEndDate());
//		//设置第一次查询条件字段
//		Map map = allManagerComponet.setParameters(request,countryName,model);
//		if(map == null)
//		{
//			return null;
//		}
//		String [] fieldKey = allManagerComponet.findByFieldKey(map);
//		String [] fieldValue = allManagerComponet.findByFieldValue(map);
////		//返回第一次时间查询的汇总集合
////		firstlists = sumDataComponet.getCommonSumData(request, fieldKey, fieldValue, countryName, sortKey,allManagerComponet.judgeByCountryName(imexType),isAdd,"1");
//		
//		
//		//处理日期格式
//		allManagerComponet.isValidateByCountry(model, countryName, "2");
//		//设置第二段查询时间
//		model.setFirstDate(model.getBeginAddDate()+","+model.getEndAddDate());
//		//设置第二次查询条件字段
//		map = allManagerComponet.setParameters(request,countryName,model);
//		fieldKey = allManagerComponet.findByFieldKey(map);
//		fieldValue = allManagerComponet.findByFieldValue(map);
////		//返回第二次时间查询的汇总集合
////		secondLists = sumDataComponet.getCommonSumData(request, fieldKey, fieldValue, countryName, sortKey,allManagerComponet.judgeByCountryName(imexType),isAdd,"2");
//		
//		//新增对比过后处理的集合
//		newList = allManagerComponet.outImportAndExport(firstlists, secondLists,allManagerComponet.judgeByCountryName(imexType));
//		//排序集合
////		newList = allManagerComponet.orderListDescOrAsc(newList, "desc", "grossWeight", "double");
		return null;
		
	}
}
