package com.njyb.gbdbase.service.common.engine.builder.componet.state.context;

import org.apache.lucene.search.IndexSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engine.builder.componet.state.ISearcherState;
import com.njyb.gbdbase.service.common.engine.builder.componet.state.impl.AllCountryState;
import com.njyb.gbdbase.service.common.engine.builder.componet.state.impl.SplitCountrySearchState;

/**
 * 封装不同的状态对象 返回不同行为 上下文
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public class IndexSearchStateContext {	 
	//处理不同的状态执行不同的行为
	public static IndexSearcher requestIndexSearcher(String countryName,String indexpath,String[] fields, String[] values,SearchPropertiesModel propertiesMap){
		return getISearcherState(countryName,propertiesMap).handleIndexSearcher(countryName,indexpath, fields, values);
	}
	/**
	 * 根据国家名称 获取对应状态实例
	 * @param countryName
	 * @return
	 */
	private static ISearcherState getISearcherState(String countryName,SearchPropertiesModel propertiesMap){
		//注入状态接口
		ISearcherState state=null;
		//针对美国
		if (isSplit(countryName,propertiesMap)) {
			state=new SplitCountrySearchState();
		}
		else{
			state=new AllCountryState();
		}
		return state;
	}
	/**
	 * 判断什么时候该切分索引类型
	 * @param country
	 * @return
	 */
	private static boolean isSplit(String country,SearchPropertiesModel propertiesMap){
		String str=propertiesMap.getPropertiesMap().get("yyyy-mm").toString();
		return str.contains(country);
	}
}
