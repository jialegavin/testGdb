package com.njyb.gbdbase.service.common.engine.api.query.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engine.api.query.ISearchQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.facade.BooleanQueryFactoryFacade;
import com.njyb.gbdbase.service.common.engine.util.LuceneAnaylazeyUtil;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 根据用户输入多个查询条件 组合一个booleaquery
 * 
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public class MultiConditionQuery implements ISearchQuery {
	@Autowired
	private SearchPropertiesModel propertiesMap;
	private Map mp = null;

	@Override
	public Query getQuery(String[] filterField, String[] values,String country,HttpServletRequest request) {
		mp = propertiesMap.getPropertiesMap();
		// 获取需要过滤的数值类型的字符串
		String filterStr =null;
		if (LuceneAnaylazeyUtil.falg(country)) {
			//根据特殊的国家把海关编码从构建的query对象中移除掉
			filterStr=ParamEnumUtil.hscode.toString()+","+mp.get("commonFilterStr").toString();
		}
		else{
			filterStr = mp.get("commonFilterStr").toString();
		}
		String[] fs = filterStr.split(",");
		// 保存查询字段
		List<String> s1 = new ArrayList<String>();
		// 保存查询字段对应的值
		List<String> v1 = new ArrayList<String>();
		// 把字符串转换成集合...
		for (int i = 0; i < filterField.length; i++) {
			s1.add(filterField[i]);
			v1.add(values[i]);
		}
		//从集合中移除需要过滤的数值类型字符串
		if (!country.equals(ParamEnumUtil.data_resource.toString())) {
			for (int i = 0; i < fs.length; i++) {
				for (int j = 0; j < s1.size(); j++) {
					if (fs[i].toLowerCase().equals(s1.get(j))) {
						s1.remove(j);
						v1.remove(j);
					}
				}
			}
		}
		//调用工厂创建具体返回查询的对象
		return BooleanQueryFactoryFacade.getBooleanQuery(s1,v1,country,request);
	}
	
}
