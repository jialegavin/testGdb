package com.njyb.gbdbase.service.common.engine.builder.impl.factorymethod.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public static Map getObject(HttpServletRequest request,IndexSearcher searcher,Query query,Filter filter,String module,PageBeanUtil page, String keyId){
		list=new LinkedList<Integer>();
		try {
			TopDocs td=createTopDocsInstance(module).getTopDocs(searcher, query, filter, module, page);
			if (td!=null) {
				System.out.println("当前从lucene返回文档记录数:"+td.totalHits);
				if (module.equals(ParamEnumUtil.search.toString())) {
					/*处理查询模块*/
					handleSearchMoudle(request,searcher, page, keyId, td);
				}
				else if (module.equals(ParamEnumUtil.buyer.name().toString())) {
					/*处理买家资源库模块*/
					handleBuyerMoudle(searcher, page, td);
				}
				else if (module.equals(ParamEnumUtil.detail.toString())||module.equals(ParamEnumUtil.drill.toString())) {
					/*处理查看详情或者钻取模块*/
					handleDetailOrDrillMoudle(searcher, keyId, td);
				}
				else{
					/*处理其它模块*/
					handleOtherMoudle(searcher, td);
				}
			}
			else{
				return null;
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mp;
	}
	
	/*
	 * 处理其它模块
	 */
	private static void handleOtherMoudle(IndexSearcher searcher, TopDocs td) {
		mp.put("td", td);
		mp.put("searcher", searcher);
	}
	
	
	/*
	 * 处理查看详情或者钻取模块
	 */
	private static void handleDetailOrDrillMoudle(IndexSearcher searcher,
			String keyId, TopDocs td) throws IOException {
		for (ScoreDoc sd : td.scoreDocs) {
			// 获取查询文档对象
			Document doc = searcher.doc(sd.doc);
			// 获取文档对象的key的值
			list.add(Integer.parseInt(doc.get(keyId)));
		}
		mp.put("detial_list", list);
	}
	
	/*
	 * 处理买家模块
	 */
	private static void handleBuyerMoudle(IndexSearcher searcher,
			PageBeanUtil page, TopDocs td) {
		page.setPageCount(td.totalHits);
		handleOtherMoudle(searcher, td);
	}
	
	/*
	 * 处理检索模块
	 */
	private static void handleSearchMoudle(HttpServletRequest request,IndexSearcher searcher,
			PageBeanUtil page, String keyId, TopDocs td) throws IOException {
		
//		StringBuilder sb = new StringBuilder();
//		String datestr = (String)request.getAttribute("datestr");
//		if(datestr!=null){
//			String[] dt = datestr.split(",");
//			for (String s : dt) {
//				sb.append(s.split("-")[0]+s.split("-")[1]+",");
//			}
//			datestr = sb.toString();
//		}
		page.setPageCount(td.totalHits);
		for (ScoreDoc sd : td.scoreDocs) {
			// 获取查询文档对象
			Document doc = searcher.doc(sd.doc);
			//排除不需要的月份
			String date = doc.get("date");
//			if(datestr!=null){
//				if(!datestr.contains(date)){
//					// 获取文档对象的key的值
//					list.add(Integer.parseInt(doc.get(keyId)));
//				}
//			}else{
				list.add(Integer.parseInt(doc.get(keyId)));
//			}
		}
//		page.setPageCount(list.size());
//		//设置当前页
//		//获得分页大小
//		int pagesize=page.getPageSize();
//		//获得分页数据在list集合中的索引
//		int firstIndex=(page.getPageIndex()-1)*pagesize;
//		int toIndex=page.getPageIndex()*pagesize;
//		if(toIndex>list.size()){
//			toIndex=list.size();
//		}
//		if(firstIndex>toIndex){
//			firstIndex=toIndex-25;
//		}
//		//截取数据集合，获得分页数据
//		List courseList=list.subList(firstIndex, toIndex);
//		mp.put("search_list", courseList);
		mp.put("search_list", list);
	}
}
