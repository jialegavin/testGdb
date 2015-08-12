package com.njyb.gbdbase.service.common.componet.report.componet.father;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbase.model.datasearch.common.ConditionModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.RatioModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.service.common.componet.report.strategy.context.StrategyContext;
import com.njyb.gbdbase.service.common.componet.report.sumary.IReportDataSummary;
import com.njyb.gbdbase.service.common.componet.report.sumary.ReportDataSummary;
import com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.AbstractCacheListCmp;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;
/**
 * 报表汇总公用的类
 * @author 贾红平
 * @version 标准版
 *
 */
@Component
@SuppressWarnings("all")
public abstract class AbstractReportCmp extends AbstractCacheListCmp{
	/**
	 * 利用handoop的Reducer思想 把Map结构的数据 汇总成单一集合 
	 * 此时的集合并不是完整的:只是给对象中的数值类型进行赋值
	 * @param mlp 转换之后的Map结构
	 * @param type 当前操作的报告类型
	 * @param country 当前查询国家的名称
	 * @param map  配置文件中的信息
	 * @return
	 */
	protected List<DataReportSumModel>getDataReportSumModels(Map<String, List<DataReportSumModel>>mlp,String type,String country,Map map){
		double weight=0.0;double money=0.0;double quantity=0.0;double size=0;double packages=0.0;int count=0;
		List<DataReportSumModel>ds=new LinkedList<DataReportSumModel>();
		Map<String, String>nmp=new HashMap<String, String>();
		for(Object o:mlp.keySet()){
			List<DataReportSumModel>ls=mlp.get(o);
			//通过策略获取一个需要汇总的具体对象部分属性:需要汇总的字段
			DataReportSumModel sum=StrategyContext.getOrderModel(ls, country, map,type);
			//赋值
			ReportHelpUtil.newInstanceReportUtil().setColumnValueByType(o.toString(), type, sum);
			//获取新增流失报告对应进出口商的时间
			if (type.equals(ParamEnumUtil.jkscompare_first.name())
					||type.equals(ParamEnumUtil.jkscompare_second.name())
					||type.equals(ParamEnumUtil.ckscompare_first.name())
					||type.equals(ParamEnumUtil.ckscompare_second.name())
					||type.equals(ParamEnumUtil.companycompare_first.name())
					||type.equals(ParamEnumUtil.companycompare_second.name())
					) {
				if (ls.size()>1) {
					nmp.put(o.toString(), ls.get(0).getDate()+","+ls.get(ls.size()-1).getDate());
				}
				if (ls.size()==1) {
					nmp.put(o.toString(), ls.get(0).getDate()+","+ ls.get(0).getDate());
				}
				//最后放入日期
				sum.setDate(nmp.get(o.toString()));
			}
			ds.add(sum);
		}
		
		return ds;
	}
	/**
	 * 利用hadoop的Mapper思想 先把数据进行转换:转换成一种能够快速计算能力模型的数据结构
	 * @param ds 第一次查询出来的集合模型对象
	 * @param type 报告类型
	 * @param map  配置文件中的信息
	 * @param isShowAll  是否要一次性汇总 true:全部汇总 false:汇总前10条
	 * @return
	 */
	protected Map<String, List<DataReportSumModel>>getDataReportSumMap(List<DataReportSumModel> ds,String type,Map map,boolean isShowAll){
		Map<String, List<DataReportSumModel>>mlp=new HashMap<String, List<DataReportSumModel>>();
		//获取默认显示的汇总记录条数
		int num=Integer.parseInt(map.get("default_size").toString());
		//存放数值类型的集合
		List<DataReportSumModel>lss=null;
		for(DataReportSumModel data:ds){
			try {
				String value=getValue(data, type);	
				//默认汇总前10条记录 
				if (isShowAll==false) {
					//但是交易趋势报告除外
					if (type.equals(ParamEnumUtil.qs.name())) {
						/*数据组装成map结构*/
						assembledMapData(mlp, data, value);
					}
					else{
						//取前10个汇总记录
						if (mlp.size()<num) {
							/*数据组装成map结构*/
							assembledMapData(mlp, data, value);
							continue;
						}
						//判断等于10的时候是否还要重复的key需要累加
						if(mlp.size()==num){
							if (mlp.containsKey(value)) {
								lss=mlp.get(value);
								lss.add(data);
								mlp.put(value, lss);
							}
						}
					}
				}
				//汇总所有数据
 				if (isShowAll==true) {
 					/*数据组装成map结构*/
					assembledMapData(mlp, data, value);
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mlp;
	}
	/*
	 *数据组装成map结构
	 */
	private void assembledMapData(Map<String, List<DataReportSumModel>> mlp,
			DataReportSumModel data, String value) {
		List<DataReportSumModel> lss;
		if (mlp.get(value)==null) {
			lss=new LinkedList<DataReportSumModel>();
			lss.add(data);
			mlp.put(value, lss);
		}
		else{
			lss=mlp.get(value);
			lss.add(data);
			mlp.put(value, lss);
		}
	}
	/**
	 * 根据报告类型获取对应属性的值:此地方处于数据量在不同的等级的时候 使用反射会影响查询性能
	 * 所以没有使用反射:数据量很小的时候可以考虑使用反射,如果要考虑使用反射需要定制使用反射时候的策略
	 * @param data
	 * @param type
	 * @return
	 */
	private String getValue(DataReportSumModel data,String type){
		String value=null;
		switch (ParamEnumUtil.getEnum(type)) {
			case jks:
				value=data.getImporter();
				break;
			case goodsDesc:
				value=data.getGoodsDesc();
				break;
			case hsCode:
				value=data.getHscode();
				break;
			case jkscompare_first:
				value=data.getImporter();
				break;
			case companycompare_first:
				value=data.getCompany_name();
				break;
			case jkscompare_second:
				value=data.getImporter();
				break;
			case companycompare_second:
				value=data.getCompany_name();
				break;
			case zg_jks:
				value=data.getCompany_name();
				break;
			case zg_sfhd:
				value=data.getProduct_place();
				break;
			case zg_port:
				value=data.getPort();
				break;
			case cks:
				value=data.getExporter();
				break;
			case ckscompare_first:
				value=data.getExporter();
				break;
			case ckscompare_second:
				value=data.getExporter();
				break;
			case qyg:
				value=data.getStart_port();
				break;
			case ddg:
				value=data.getEnd_port();
				break;
			case ycg:
				value=data.getOrigin_country();
				break;
			case mdg:
				value=data.getDest_country();
				break;
			case hgcs:
				value=data.getCustoms();
				break;
			case qs:
				value=data.getDate();
				break;
			case tzr:
				value=data.getNotifier();
				break;
			case zzs:
				value=data.getManufacture();
				break;
			case multi_hscode:
				value=data.getHscode();
				break;
			}
			return value;
	}
	/*第一段时间的原始集合*/
	protected List<DataReportSumModel>sumaryFirstList=null;
	/*第二段时间的集合:同环比*/
	protected List<DataReportSumModel>sumarySecondList=null;
	/*第一段时间的原始集合转换为map*/
	protected Map<String, List<DataReportSumModel>> firstMap=null;
	/*第二段时间的集合转换为map*/
	protected Map<String, List<DataReportSumModel>> secondMap=null;
	/*查询条件*/
	protected  String[]fs;
	/*查询条件对应的值*/
	protected String[]vs;
	/*查询开始时间*/
	protected String start;
	/*查询结束时间*/
	protected String end;
	/*第二段检索的开始时间*/
	protected String secondStart=null;
	/*第二段检索的结束时间*/
	protected String secondEnd=null;
	/*注入报表配置信息的文件*/
	@Autowired
	protected ReportPropertiesModel propertiesModel;
	/**
	 * 计算月度同环比
	 * @param firstList 第一段时间的数据集合
	 * @param secondList 第二段时间的数据集合
	 * @return
	 */
	protected synchronized List<RatioModel> getRatioList(List<DataReportSumModel>firstList,List<DataReportSumModel>secondList,String oper){
		List<RatioModel>ratioList=new LinkedList<RatioModel>();
		/*由于共性字段比较多 可以使用冗余字段*/
		String[]dataField={"tradeMoney","tradeWeight","tradeQuantity","tradePackage","tradeSize","tradeCount"};
		String[]ratioModelField={"money","weight","quantity","packages","size","count"};
		String[]ratioField={"moneyRatio","weightRatio","quantityRatio","packagesRatio","sizeRatio","countRatio"};	
		/*笛卡尔乘积 后面尽量考虑使用其它方法代替*/
		for(DataReportSumModel old:firstList){
			int firstYear=Integer.parseInt(old.getDate().substring(0,4));
			int fristMonth=Integer.parseInt(old.getDate().substring(4,6));
			boolean falg = true;
			for(DataReportSumModel nw:secondList){
				int secondYear=Integer.parseInt(nw.getDate().substring(0,4));
				int secondMonth=Integer.parseInt(nw.getDate().substring(4,6));
				/*执行比率计算*/
				falg = executeRatioCalculate(oper, ratioList, dataField,
						ratioModelField, ratioField, old, firstYear,
						fristMonth, falg, nw, secondYear, secondMonth);
			}
			//不能进行月度同环比计算的数据
			if (falg==true) {
				/*比率字段赋值*/
				executeRatioFieldSetValue(ratioList, dataField,
						ratioModelField, ratioField, old);
			}
		}
		return ratioList;
	}
	/*
	 *给比率中字段赋值
	 */
	private void executeRatioFieldSetValue(List<RatioModel> ratioList,
			String[] dataField, String[] ratioModelField, String[] ratioField,
			DataReportSumModel old) {
		RatioModel rm=new RatioModel();
		//设置日期
		rm.setDate(old.getDate());
		for (int i = 0; i < dataField.length; i++) {
			try {
				BeanUtils.setProperty(rm, ratioModelField[i], Double.parseDouble(BeanUtils.getProperty(old, dataField[i])));
				BeanUtils.setProperty(rm, ratioField[i],this.ratioParse(Double.parseDouble(BeanUtils.getProperty(old, dataField[i])),0));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ratioList.add(rm);
	}
	/*
	 * 执行比率计算
	 */
	private boolean executeRatioCalculate(String oper,
			List<RatioModel> ratioList, String[] dataField,
			String[] ratioModelField, String[] ratioField,
			DataReportSumModel old, int firstYear, int fristMonth,
			boolean falg, DataReportSumModel nw, int secondYear, int secondMonth) {
			// 月度环比
			if (oper.equals("mom")) {
				// 满足月度环比条件 // 2013=2012+1 11+1=12
				if (((firstYear == secondYear && fristMonth == secondMonth + 1) || ((firstYear == secondYear + 1)
						&& secondMonth + 1 == 13 && fristMonth + 1 == 2))) {
					falg = computeRatioModel(ratioList, dataField, ratioModelField,
							ratioField, old, nw);
				}
			}
			// 月度同比或者年度同比
			if (oper.equals("myoy") || oper.equals("yoy")) {
				// 满足月度同比条件
				if ((firstYear - 1) == secondYear && fristMonth == secondMonth) {
					// 进行月度同比计算
					falg = computeRatioModel(ratioList, dataField, ratioModelField,
							ratioField, old, nw);
				}
			}
		return falg;
	}
	/*
	 * 计算比率以及对应的值
	 *
	 */
	private boolean computeRatioModel(List<RatioModel> ratioList,
			String[] dataField, String[] ratioModelField, String[] ratioField,
			DataReportSumModel old, DataReportSumModel nw) {
		boolean falg;
		falg=false;
		RatioModel rm=new RatioModel();
		//设置日期
		rm.setDate(old.getDate());
		for (int i = 0; i < dataField.length; i++) {
			try {
				BeanUtils.setProperty(rm, ratioModelField[i], Double.parseDouble(BeanUtils.getProperty(old, dataField[i])));
				BeanUtils.setProperty(rm, ratioField[i],this.ratioParse(Double.parseDouble(BeanUtils.getProperty(old, dataField[i])),Double.parseDouble(BeanUtils.getProperty(nw,dataField[i]))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ratioList.add(rm);
		return falg;
	}

	/**
	 * 获取同环比的比率
	 * @param oldValue
	 * @param newValue
	 * @return
	 */
	private double ratioParse(double oldValue,double newValue){
		if(oldValue == 0.0){
			return 0;
		}
		if (newValue==0.0) {
			return 0;
		}
		if (oldValue>0.0 && newValue>0.0) {
			double result  = ((oldValue-newValue)/newValue)*100;
			return Double.parseDouble(new DecimalFormat("#0.00").format(result));
		}
		return 0.0;
		
	}
	/**
	 * 根据用户查询功能不同 返回不同的报告类型
	 * @param oper
	 * @return
	 */
	protected static String getReportType(String currentType,String oper){
		if (oper.equals(ParamEnumUtil.mom.name())||oper.equals(ParamEnumUtil.myoy.name())
				||oper.equals(ParamEnumUtil.mqoq.name())){
			return ParamEnumUtil.qs.name();
		}
		if (oper.equals(ParamEnumUtil.yoy.name())) {
			return currentType;
		}
		return null;
	}
	/**
	 * 根据国家返回要格式化的日期格式
	 * @param model
	 * @return
	 */
	protected  static String dateFormatType(ReportCommonParamModel model,@SuppressWarnings("rawtypes") Map map){
		if (map.get("date_my").toString().contains(model.getCountry())) {
			return "yyyy-MM";
		}
		else{
			return "yyyy-MM-dd";
		}
	}
	
	/**
	 * 更具报告类型动态过滤条件
	 * @param reportType 报告类型
	 * @param currentValue 当前对象的值
	 * @param list 原始集合
	 * @return
	 */
	protected static List<DataReportSumModel>getFilterListByReportType(String currentValue,String reportType,List<DataReportSumModel>list,@SuppressWarnings("rawtypes") Map map){
		List<DataReportSumModel>nlist=new LinkedList<DataReportSumModel>();
		try {
			if (null != list && !list.isEmpty()) {
				String name=map.get(reportType).toString();
				for(DataReportSumModel data:list){
					String value=BeanUtils.getProperty(data, name);
					if (value.equals(currentValue)) {
						nlist.add(data);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nlist;
	}
	/**
	 * 执行参数的初始化赋值 针对月度同环比
	 * @param model
	 */
	protected  void initParam(ReportCommonParamModel model){
		//原始查询条件
 		fs = model.getFeilds();
		vs = model.getValues();
		// 字符串进行拆分 起始日期和结束日期
		start = model.getStartDate();
		end = model.getEndDate();
	}
	/**
	 * 
	 * @param model
	 * @param value
	 * @param reportType
	 * @param oper
	 * @return
	 */
	protected <T> Map<String, List<T>>getMonthTrendMap(ReportCommonParamModel model,String value, String reportType, String oper){
		/*组装缓存key*/
		String oldCountry=ConditionModel.getLastTimeCountry();
		String[]oldFields=ConditionModel.getLastTimeFields().split(";");
		String[]oldValues=ConditionModel.getLastTimeValues().split(";");
		String key=oldCountry+";"+ReportHelpUtil.queryKeyOrValue(oldFields, oldValues); 
		// 首先从缓存中获取第一次查询没有汇总的集合
		sumaryFirstList = (List<DataReportSumModel>) CreateEncache.getEacheInstance().getCache("reportSumList").get(key).getObjectValue();
		// 代表当前是非纯月份的月度同环比
		if (value != null) {
			// 根据报告类型对查询出来的集合进行过滤
			sumaryFirstList = getFilterListByReportType(value, reportType,sumaryFirstList, propertiesModel.getReportFieldMap());
		}
		// 获取报告类型
		String type = getReportType(reportType, oper);
		// 合并成map数据结构
		firstMap =getDataReportSumMap(sumaryFirstList, type,propertiesModel.getReportFieldMap(), true);
		// map结构数据求和
		List<DataReportSumModel> firstList =getDataReportSumModels(firstMap, type, model.getCountry(),propertiesModel.getReportFieldMap());
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].equals("date")) {
				vs[i] = secondStart + "," + secondEnd;
			}
		}
		// 获取环比时间段的数据集合
		sumarySecondList =super.getSearchSumListModel(fs, vs,model.getCountry(), propertiesModel.getReportFieldMap());
		//再根据报告类型进行过滤
		sumarySecondList = getFilterListByReportType(value, reportType,sumarySecondList, propertiesModel.getReportFieldMap());
		// 合并map
		secondMap =getDataReportSumMap(sumarySecondList, type,propertiesModel.getReportFieldMap(), true);
		// map求和
		List<DataReportSumModel> secondList =getDataReportSumModels(secondMap, type, model.getCountry(),propertiesModel.getReportFieldMap());
		// 重新设置时间为原始检索时间
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].equals("date")) {
				vs[i] = start + "," + end;
			}
		}
		
		//结果存放到map
		Map map=new HashMap();
		map.put("first", firstList);
		map.put("second", secondList);
		return map;
	}
}
