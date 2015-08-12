package com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.factory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.datasearch.common.KeyValueModel;
import com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.ITopDocs;
import com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.impl.ListTopDocs;
import com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.impl.MapTopDocs;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
/**
 * 通过工厂方法创建一个具体的对象topdocs
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public  class TopDocsFactory {
	public static List<Integer>list=null;
	public static Map mp=new HashMap();
	/**
	 * 返回具体实例
	 * @param module 模块名称
	 * @return
	 */
	public static ITopDocs createTopDocsInstance(String module){
		ITopDocs top=null;
		if (module.equals("search")||module.equals("buyer")) {
			top=new ListTopDocs();
		}
		else{
			top=new MapTopDocs();
		}
		return top;
	}
	/**
	 * 获取具体的返回结果类型
	 * @param searcher 部件
	 * @param query 部件
	 * @param filter 部件
	 * @param module 模块名称简称 具体解释如下
	 * search 产品检索
	 * report 报表检索
	 * drill  报表深度挖取
	 * detailinfo  报表汇总数据对应的详情信息
	 * alldb 全库报表分析
	 * buyers 买家资源库
	 * @param page  分页模型
	 * @param keyId  获取对应的主键
	 * @return
	 */
	public static Map getObject(IndexSearcher searcher,Query query,Filter filter,String module,PageBeanUtil page, String keyId){
		list=new LinkedList<Integer>();
		try {
			TopDocs td=createTopDocsInstance(module).getTopDocs(searcher, query, filter, module, page);
			if (module.equals(ParamEnumUtil.search.toString())) {
				page.setPageCount(td.totalHits);
				for (ScoreDoc sd : td.scoreDocs) {
					// 获取查询文档对象
					Document doc = searcher.doc(sd.doc);
					// 获取文档对象的key的值
					list.add(Integer.parseInt(doc.get(keyId)));
				}
				mp.put("search_list", list);
			}
			else if (module.equals(ParamEnumUtil.buyer.name().toString())) {
				page.setPageCount(td.totalHits);
				mp.put("td", td);
				mp.put("searcher", searcher);
			}
			else if (module.equals(ParamEnumUtil.detail.toString())||module.equals(ParamEnumUtil.drill.toString())) {
				for (ScoreDoc sd : td.scoreDocs) {
					// 获取查询文档对象
					Document doc = searcher.doc(sd.doc);
					// 获取文档对象的key的值
					list.add(Integer.parseInt(doc.get(keyId)));
				}
				mp.put("detial_list", list);
			}
			else{
				mp.put("td", td);
				mp.put("searcher", searcher);
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mp;
	}
	
	 
}
