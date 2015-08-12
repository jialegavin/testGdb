package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.ConditionModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.service.common.componet.impl.CommonLuceneComponent;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.build.OrderFieldSetValueBuilder;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.build.SearchConditionSetValueBuilder;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;
/**
 * 查询集合并且保存到缓存中..
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public abstract class AbstractCacheListCmp extends CommonLuceneComponent{
	/*创建一个缓存对象*/
	protected Cache  cache=CreateEncache.getEacheInstance().getCache("reportSumList");
	/*声明一个缓存中的元素*/
	protected Element element;
	/*声明一个集合*/
	protected List<DataReportSumModel> list=null;
	/*引入查询字段赋值*/
	@Autowired
	private SearchConditionSetValueBuilder conditionSetValueBuilder;
	/*引入查询汇总字段赋值*/
	@Autowired
	private OrderFieldSetValueBuilder orderFieldSetValueBuilder;
	/**
	 * 获取查询集合
	 * 1 先判断用户是否是第一次检索 如果是第一次检索数据 那么直接检索数据 之后保存到缓存中
	 * 2 如果用户不是第一次检索数据  那么先试着从缓存中获取...如果缓存的数据失效..重新查询数据..并保存到缓存中
	 * @param fields 查询字段的名称
	 * @param values 查询字段的值
	 * @param country 查询的国家
	 * @param map 存入map里面的配置文件
	 * @param str 提示信息
	 * @return
	 */
	protected List<DataReportSumModel> initListAndSaveCache(String[] fields,String[] values, String country,Map map,String str) {
		print(str);
		//1 第一步判断两次查询的国家是否一致 不一致 直接重新检索数据 然后保存到缓存中
		//2 如果国家一致的话..再判断查询条件和值是否一致
		String oldCountry=ConditionModel.getLastTimeCountry();
		String[]oldFields=ConditionModel.getLastTimeFields().split(";");
		String[]oldValues=ConditionModel.getLastTimeValues().split(";");
		boolean falg=ReportHelpUtil.newInstanceReportUtil().isInitSearch(oldCountry,oldFields,oldValues,country, fields, values);
		/*存放到缓存中元素的key*/
//		String key=country+fields+values;
		String key=country + ";"+ReportHelpUtil.queryKeyOrValue(fields, values);
		/*满足重新检索*/
		if (falg==false) {
			executeSearchAndSave(fields, values, country, map, key);
		}
		/*满足直接从缓存中获取数据*/
		if (falg==true) {
			/*处理直接从缓存中获取数据*/
			handleGetDataFromCache(fields, values, country, map, key);
		}
		return list;
	}
	
	/*
	 * 处理直接从缓存中获取数据
	 */
	private void handleGetDataFromCache(String[] fields, String[] values,
			String country, Map map, String key) {
		element=cache.get(key);
		/*用户第一次检索数据*/
		if (element==null) {
			executeSearchAndSave(fields, values, country, map, key);
		}
		/*用户不是第一次检索数据 数据有可能已经存放到缓存中*/
		if (element!=null) {
			list=(List<DataReportSumModel>) cache.get(key).getObjectValue();
			if (list==null) {
				executeSearchAndSave(fields, values, country, map, key);
			}
		}
	}
	
	/*
	 * 执行查询并且把结果缓存起来
	 */
	private void executeSearchAndSave(String[] fields, String[] values,
			String country, Map map, String key) {
		/*查询数据*/
		list=getSearchSumListModel(fields, values, country, map);
		try {
			/*缓存上锁*/
			cache.acquireWriteLockOnKey(key);
			/*创建缓存中存放的元素*/
			element=new Element(key, list);
			/*元素存放到缓存中*/
			cache.put(element);
			/*存放一个冗余缓存 用来做明细*/
			cache.put(new Element(key, list));
		} catch (Exception e) {
			 throw new RuntimeException("异常信息:"+e.getMessage());
		}
		finally{
			/*缓存释放锁*/
			cache.releaseWriteLockOnKey(key);  
		}
	}
	
	/*
	 * 从缓存中直接获取上一次查询的结果
	 */
	protected List<DataReportSumModel>getListFromCache(){
	/*	ReportCommonParamModel cm=ConditionModel.getReportModel();
		String country=cm.getCountry();
		String[] fs=cm.getFeilds();
		String[] vs=cm.getValues();*/
		
		String country=ConditionModel.getLastTimeCountry();
		String[]fs=ConditionModel.getLastTimeFields().split(";");
		String[]vs=ConditionModel.getLastTimeValues().split(";");
		String key=country+ ";"+ ReportHelpUtil.queryKeyOrValue(fs, vs);
		List<DataReportSumModel>list=(List<DataReportSumModel>) cache.get(key).getObjectValue();
		if (list!=null) {
			return list;
		}
		else{
			return null;
		}
	}
	
	/*
	 * 打印提示信息
	 */
	private void print(String str){
		System.out.println(str);
	}
	
	/*
	 * 根据条件检索索引文件 然后组装成java对象
	 */
	protected List<DataReportSumModel> getSearchSumListModel(String[] fields, String[] values, String country,@SuppressWarnings("rawtypes") Map map) {
		/*根据条件 获取到返回的索引文档集合*/
		List<Document> doclist = super.getDocList(null, fields, values,country, ParamEnumUtil.report.name().toString(), null);
		/*获取当前国家的汇总排序字段*/
		String[] orders = map.get(country + "_order").toString().split(",");
		List<DataReportSumModel> dataList =null;
		try {
			if (doclist.size()>0) {
				dataList=new LinkedList<DataReportSumModel>();
				for (Document doc : doclist) {
					DataReportSumModel data = new DataReportSumModel();
					/*查询条件字段赋值 这里使用new实例化对象 后期需要修改*/
					DataReportSumModel fdata=new SearchConditionSetValueBuilder().setSearchConditionValue(map, doc, country, data);
					/*查询排序字段赋值 这里使用new实例化对象 后期需要修改*/
					DataReportSumModel odata=new OrderFieldSetValueBuilder().setOrderFieldValue(orders, map, doc, country, fdata);
					/*所有查询 最终结果都要添加日期 并且要把所有的日期格式处理成yyyy-mm这种形式*/
					odata.setDate(ReportHelpUtil.handleDateStr(doc.get("date")));
					dataList.add(odata);
				}
			}
		} catch (Exception e) {
			System.out.println("输入错误打印信息：" + e.getMessage());
		}
		return dataList;
	}
}
