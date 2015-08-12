package com.njyb.gbdbase.service.common.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.ConditionModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
/**
 * 多个子类都可能用到的方法
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public abstract class AbstractEngineService {
	private List<String> s1;
	private List<String> v1;
	private String[] filterField;
	private String[] values;
	private String[] fs;
	private List<String> v2=null;
	/**
	 * 返回检索查询模型对象
	 * @param model
	 * @param map
	 * @return
	 */
	protected SearchCommonParamModel filterStrByCountry(
			SearchCommonParamModel model, @SuppressWarnings("rawtypes") Map map) {
		String country = model.getCountry();
		if (country.equals(LuceneConstant.CHINA_EIGHT_STRING)) {
			filterField = model.getFeilds();
			values = model.getValues();
			//过滤字符串
			executeFilterStr(map);
			//重新赋值
			model.setFeilds(s1.toArray(new String[s1.size()]));
			model.setValues(v1.toArray(new String[v1.size()]));
			
			return model;
		} 
		else {
			return model;
		}
	}
	
	
	
	/**
	 * 返回报表查询模型对象
	 * @param model
	 * @param map
	 * @return
	 */
	protected ReportCommonParamModel filterStrByCountry(
			ReportCommonParamModel model, Map map) {
		/*保存整个查询参数对象*/
		ConditionModel.setReportModel(model);
		String country = model.getCountry();
		if (country.equals(LuceneConstant.CHINA_EIGHT_STRING)) {
			filterField = model.getFeilds();
			values = model.getValues();
			//过滤字符串
			executeFilterStr(map);
			// 重新赋值
			model.setFeilds(s1.toArray(new String[s1.size()]));
			model.setValues(v1.toArray(new String[v1.size()]));
			return model;
		} 
		else {
			return model;
		}
	}
	/**
	 * 针对中国进行部分字段的特殊处理
	 * @param map
	 */
	private void executeFilterStr(Map map) {
		s1 = new ArrayList<String>();
		v1 = new ArrayList<String>();
		// 需要排除的字段
		fs = map.get("exclude_field").toString().split(",");
		// 把字符串转换成集合...
		for (int i = 0; i < filterField.length; i++) {
			s1.add(filterField[i]);
			//去除特殊标点符号
			v1.add(values[i]);
		}
		// 从集合中移除需要过滤的数值类型字符串
		for (int i = 0; i < fs.length; i++) {
			for (int j = 0; j < s1.size(); j++) {
				if (fs[i].toLowerCase().equals(s1.get(j))) {
					s1.remove(j);
					v1.remove(j);
				}
			}
		}
	}
	
}
