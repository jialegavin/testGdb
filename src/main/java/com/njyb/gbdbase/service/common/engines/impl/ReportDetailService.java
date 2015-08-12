package com.njyb.gbdbase.service.common.engines.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.ConditionModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.KeyValueModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.AbstractCacheListCmp;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;
import com.njyb.gbdbase.service.common.engines.IReportDetailService;

/**
 * 明细接口实现业务类 提供两种方式 1 直接从第一查询报表中汇总的map去取 2 从第一次没有汇总之前的集合中去取 最好解决方案
 * 1和2两种方案可以结合在一起
 * 
 * @author 贾红平
 *
 */
@Service
@SuppressWarnings("all")
public class ReportDetailService extends AbstractCacheListCmp implements IReportDetailService {
	@Autowired
	private ReportPropertiesModel rpm;

	/**
	 * 报表汇总明细查看方案1:从第一次汇总的缓存的map中直接获取
	 */
	@Override
	public List<DataReportSumModel> builderSingleList(String value,
			String type, String country) {
		/*
		 * type:first/second:summary detail:addoffReport
		 * type:drill&jks,jks:drill detail:drillReport
		 */
		Map<String, String> fmap = getCacheMap(type);
		Cache cache = CreateEncache.getEacheInstance().getCache(
				fmap.get("value"));
		Element element = cache.get(fmap.get("key"));
		Map<String, List<DataReportSumModel>> rmp = null;
		if (element != null) {
			rmp = (Map<String, List<DataReportSumModel>>) element
					.getObjectValue();
		}
		if (type.startsWith("drill")) {
			type = type.split("&")[1];
		}
		Map<String, List<DataReportSumModel>> map = (Map<String, List<DataReportSumModel>>) rmp
				.get(type);
		List<DataReportSumModel> list = map.get(value);
		/* 如果从第一次缓存中没有查询出来数据 再去从原始没有汇总的集合中再重新过滤 */
		if (list == null) {
			return buildReportSumList(value, type, country);
		} else {
			return list;
		}
	}

	/* 拿到新增和钻取相应的缓存map(public-private ,add static) */
	private static Map<String, String> getCacheMap(String type) {
		Map<String, String> map = new HashMap<String, String>();
		/* 针对新增流失报告分析模块 创建缓存 */
		if (type.contains("first") || type.contains("second")) {
			map.put("key", "addoffReport");
			map.put("value", "addoffReportMap");
		}
		/* 针对所有报告的深度挖取模块 创建缓存 */
		else if (type.startsWith("drill")) {
			map.put("key", "drillReport");
			map.put("value", "drillReportMap");
		}
		/* 针对第一次查询汇总的时候 创建缓存 */
		else {
			map.put("key", "reportMap");
			map.put("value", "reportSumMap");
		}
		return map;
	}

	/**
	 * 报表汇总明细的方案2:从第一次集合中过滤获取的
	 */
	@Override
	public List<DataReportSumModel> buildReportSumList(String value,String type, String country) {
		/* 用来接收从缓存中获取到的数据集合 */
		List<DataReportSumModel> list = null;
		/* 用来获取过滤后的新的集合 */
		List<DataReportSumModel> newList = new LinkedList<DataReportSumModel>();
		/* 创建缓存 */
		Cache cache = CreateEncache.getEacheInstance().getCache("reportSumList");
		/*获取本次查询的相关条件和对应的值*/
		String oldCountry=ConditionModel.getLastTimeCountry();
		String[]oldFields=ConditionModel.getLastTimeFields().split(";");
		String[]oldValues=ConditionModel.getLastTimeValues().split(";");
		/*组成缓存的key*/
		String key=country+";"+ReportHelpUtil.queryKeyOrValue(oldFields, oldValues);
		
		
		String[]fs=KeyValueModel.getLastQueryKey().split(";");
		String[]vs=KeyValueModel.getLastQueryValue().split(";");
		list=super.getSearchSumListModel(fs, vs, country, rpm.getReportFieldMap());
		/* 获取缓存中的元素 */
		Element element = cache.get(key);
		if (element != null) {
/*			list = (List<DataReportSumModel>) element.getObjectValue();
*/			/* 缓存中的集合不为空 */
			if (list != null) {
				for (DataReportSumModel data : list) {
					try {
						/* 根据报告类型通过反射获取到报告类型对应的检索字段名称 */
//						String cname = rpm.getReportFieldMap().get(reportType(country,type))
//								.toString();
						String cname = rpm.getReportFieldMap().get(type)
								.toString();
						/* 获取字段的值 */
						String val = BeanUtils.getProperty(data, cname);
						/* 报告类型不是交易趋势的时候 */
						if (!type.equals(ParamEnumUtil.qs.name())) {
							if (value.equals(val)) {
								newList.add(data);
							}
						}
						/* 报告类型是交易趋势的时候 */
						if (type.equals(ParamEnumUtil.qs.name())) {
							/*处理交易类型作为交易趋势时候的明细*/
							handleReportTypeForQs(value, country, newList,
									data, val);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return newList;
	}
	
	
	/*
	 * 处理报告类型作为交易趋势时候的明细
	 */
	private void handleReportTypeForQs(String value, String country,
			List<DataReportSumModel> newList, DataReportSumModel data,
			String val) {
		/* 处理国家的日期格式是YYYY-MM这种形式的数据 */
		if (rpm.getReportFieldMap().get("date_my")
				.toString().contains(country)) {
			if (value.equals(val)) {
				newList.add(data);
			}
		}
		/* 处理国家的日期格式是YYYY-MM-DD这种形式的数据 */
		else {
			String nval = val.replaceAll("-", "")
					.substring(0, 6);
			if (nval.equals(value)) {
				newList.add(data);
			}
		}
	}

}
