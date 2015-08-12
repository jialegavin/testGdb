package com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.impl;


import org.apache.log4j.Logger;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.pagetemplate.TabGenerateUtil;
import com.njyb.gbdbase.model.datasearch.common.SearchPropertiesModel;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 生成返回map类型的topdocs
 * 
 * @author jiahp
 *
 */
public class MapTopDocs implements com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.ITopDocs {
	//log记录日志
//	private static final Logger log = Logger.getLogger(MapTopDocs.class);
	@Autowired
	private SearchPropertiesModel propertiesMap;
	/**
	 * 生成返回map类型的topdocs
	 */
	@Override
	public TopDocs getTopDocs(IndexSearcher searcher, Query query,
			Filter filter, String module, PageBeanUtil page) {
		//默认显示所有
		int num = Integer.MAX_VALUE;
		//如果是报表分析和全库分析 从配置文件获取要显示的记录数
		if (module.equals(ParamEnumUtil.report.toString())
				|| module.equals(ParamEnumUtil.alldb.toString())) {
			num = Integer.parseInt(propertiesMap.getPropertiesMap().get(module)
					.toString());
		}
		TopDocs td = null;
		try {
			 
			//新增进出口商模块的时候 对日期进行升序排序
			if (module.equals(ParamEnumUtil.jkscompare_first.name()) || module.equals(ParamEnumUtil.companycompare_first.name()) || module.equals(ParamEnumUtil.ckscompare_first.name())
					|| module.equals(ParamEnumUtil.jkscompare_second) || module.equals(ParamEnumUtil.companycompare_second.name()) || module.equals(ParamEnumUtil.ckscompare_second.name())){				 
				Sort sort=new Sort(new SortField("date",Type.LONG,true));
				td = searcher.search(query, filter, num,sort);
			}
			//其它报告不需要进行排序
			else{
				td = searcher.search(query, filter, num);
				return td;
			}
		} catch (Exception e) {
			System.out.println("输入错误打印信息：" + e.getMessage());
		}
		return null;
	}

}
