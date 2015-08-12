package com.njyb.gbdbase.service.contrastreport.contrast.component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.IReportCompareService;

/**
 * 所有公共国家的数据汇总
 * @author 章华才
 * @param <T>
 */
@Component
public class CommomSumDataComponent{
	
	@Resource
	private IReportCompareService reportService;
	
	@Resource 
	private CountryAllManagerComponent allManagerComponent;
	
	private static Map<String, List<DataReportSumModel>> map = new HashMap<String, List<DataReportSumModel>>();
	
	@SuppressWarnings("all")
	public List<DataReportSumModel> getCommonSumData(HttpServletRequest request,String [] fieldKey,String [] fieldValue
			             ,String countryName,String sortKey,String type,String isAdd,String isFirst){

		//判断查询条件是否一致
		if(allManagerComponent.getFields(fieldKey, fieldValue, countryName,isFirst,type))
		{
			if(isFirst.equals("first"))
			{
				//获取缓存数据
				return (List<DataReportSumModel>) allManagerComponent.getCacheList("addCacheKey", "addCacheMap");
			}
			else
			{
				//获取缓存数据
				return (List<DataReportSumModel>) allManagerComponent.getCacheList("outCacheKey", "outCacheMap");
			}
		}
		//设置缓存字段
		allManagerComponent.setCacheField(fieldKey, fieldValue, countryName,isFirst,type);
		//通过lucenc查询相应国家的原始数据
		List<DataReportSumModel> oldList = reportService.builderSingleList(new ReportCommonParamModel(fieldKey,fieldValue,countryName,"report",request,allManagerComponent.getImportOrExportType(countryName,isFirst,type)), true);
		//保存第一段时间查询的结果集
		if(isFirst.equals("first"))
		{
			//将数据放入到缓存中
			allManagerComponent.createCacheSetList(oldList, "addCacheKey", "addCacheMap");
		}
		//保存第二段时间查询的结果集
		else
		{
			//将数据放入到缓存中
			allManagerComponent.createCacheSetList(oldList, "outCacheKey", "outCacheMap");
		}
		return oldList;
	}
}
