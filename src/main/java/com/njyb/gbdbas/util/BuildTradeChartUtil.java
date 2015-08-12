package com.njyb.gbdbas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.njyb.gbdbase.model.usermanagement.MixDataModel;
import com.njyb.gbdbase.model.usermanagement.PieDataModel;

/**
 * 生成贸易情报各种结果对应的图表工具类
 * @author 贾红平
 *
 */
public class BuildTradeChartUtil {
	 /**
     * 生成混搭图(折线图和柱状图),饼图需要的数据
     * @param request
     * @param listAll
     * @param columField
     * @param map
     * @param reportType
     * @param functionType 1代表报表汇总 2代表深度挖取 3代表数据对比模块 4 月度同比，月度环比模块 5年度同比模块
     * @return
     * @throws Exception
     * have change all from ZHNAME to ENNAME;
     */
      public static <T>  Map  buildPieAndMixChartData (HttpServletRequest request,List <T> listAll,String columField,Map map,String reportType,int functionType,String DynamicSortValue) throws Exception
      {
    	  
    	  //存放混搭图集合
  	      List<MixDataModel> mixChartList = new ArrayList<MixDataModel>();
  	      //存放饼图集合
  	      List<PieDataModel> pieChartList = new ArrayList<PieDataModel>();
  	      //存放饼图和柱状图的map总集合
  	      Map chartMap = new HashMap();
    	  //混搭图需要展示的字段
    	  String [] mixChartFieldsId = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MIXCHART_FIELDID)).toString().split(",");
    	  //混搭图需要展示的字段
    	  String [] mixChartFieldsName = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MIXCHART_FIELDENNAME)).toString().split(",");
    	  //饼状图需要展示的字段
    	  String  pieChartField = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.PIECHART_FIELDID)).toString();
    	  //需要展示混搭图的报告类型
    	  String  mixChartReportType = "";
    	  //需要展示饼状图的报告类型
    	  String  pieChartReportType = "";
    	  //数据汇总模块
    	  if(functionType == DataSearchConstantUtil.DATA_SUMMARY)
    	  {
    		  mixChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MIXCHART_REPORTTYPE)).toString();
    		  pieChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.PIECHART_REPORTTYPE)).toString();
    	  }
    	  //深度挖取模块
    	  else if(functionType == DataSearchConstantUtil.DEPTH_DIGGING)
    	  {
    		  mixChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.DEPTHDIGGING_MIXCHART_REPORTTYPE)).toString();
    		  pieChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.DEPTHDIGGING_PIECHART_REPORTTYPE)).toString();
    	  }
    	  //数据对比模块
    	  else if(functionType == DataSearchConstantUtil.DATA_COMPARE)
    	  {
    		  mixChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.DATACOMPARE_MIXCHART_REPORTTYPE)).toString();
    		  pieChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.DATACOMPARE_PIECHART_REPORTTYPE)).toString();
    	  }  
    	  //月度环比模块
    	  else if(functionType == DataSearchConstantUtil.MOM_REPORT)
    	  {
    		  mixChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.THB_MIXCHART_REPORTTYPE)).toString();
    		  pieChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.THB_PIECHART_REPORTTYPE)).toString();
    		  //混搭图需要展示的字段
        	  mixChartFieldsId = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MOM_MIXCHART_FIELDID)).toString().split(",");
        	  //混搭图需要展示的字段
        	  mixChartFieldsName = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MOM_MIXCHART_FIELDENNAME)).toString().split(",");
    	  }
    	  //月度同比模块
    	  else if(functionType == DataSearchConstantUtil.MYOY_REPORT)
    	  {
    		  mixChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.THB_MIXCHART_REPORTTYPE)).toString();
    		  pieChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.THB_PIECHART_REPORTTYPE)).toString();
    		  //混搭图需要展示的字段
        	  mixChartFieldsId = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MYOY_MIXCHART_FIELDID)).toString().split(",");
        	  //混搭图需要展示的字段
        	  mixChartFieldsName = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.MYOY_MIXCHART_FIELDENNAME)).toString().split(",");
    	  }
    	  //年度同比模块
    	  else if(functionType == DataSearchConstantUtil.YOY_REPORT)
    	  {
    		  mixChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.THB_MIXCHART_REPORTTYPE)).toString();
    		  pieChartReportType = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.THB_PIECHART_REPORTTYPE)).toString();
    		  //混搭图需要展示的字段
        	  mixChartFieldsId = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.YOY_MIXCHART_FIELDID)).toString().split(",");
        	  //混搭图需要展示的字段--change to english
        	  mixChartFieldsName = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.YOY_MIXCHART_FIELDENNAME)).toString().split(",");
    	  }
    	  if(listAll != null && listAll.size()>0)
    	  {
    		  //判断当前报告类型是否需要混搭图展示
    		  if(mixChartReportType.contains(reportType))
    		  {
    			  //绑定混搭图x轴数据
    			  Object[] xData = new Object[listAll.size()];
    			  for(int i=0;i<listAll.size();i++)
    			  {
    				  xData[i] = BeanUtils.getProperty(listAll.get(i), columField);
    			  }
    			  MixDataModel xDataModel = new MixDataModel("x","月份",0,xData,0);
    			  mixChartList.add(xDataModel);
    			  
    			  //绑定柱状图的数据
    			  for(int i=0;i<mixChartFieldsId.length;i++)
    			  {
    				  Object[] lineData = new Object[listAll.size()];
    				  for(int j=0;j<listAll.size();j++)
    				  {
    					  lineData[j] = BeanUtils.getProperty(listAll.get(j), mixChartFieldsId[i]);
    				  }
    				  MixDataModel lineDataModel =null;
    				  //月度同比月度环比折线图展示
					  if(functionType == DataSearchConstantUtil.MOM_REPORT 
							  || functionType == DataSearchConstantUtil.MYOY_REPORT )
					  {
						  lineDataModel = new MixDataModel("line",mixChartFieldsName[i],0,lineData,0);
					  }else if(functionType == DataSearchConstantUtil.YOY_REPORT )
					  {
						  lineDataModel = new MixDataModel("bar",mixChartFieldsName[i],0,lineData,0);
					  }
					  else
					  {
						  //判断排序字段是否为空
						  if(DynamicSortValue != null){
							  //判断哪些排序字段需要切换echart图片显示
							  if(DynamicSortValue.equals("tradeMoney") || DynamicSortValue.equals("tradeWeight") || 
									  
								  DynamicSortValue.equals("tradeQuantity") || DynamicSortValue.equals("tradePackage") || 
								   
								  DynamicSortValue.equals("tradeSize") ||  DynamicSortValue.equals("tradeCount")){
								  
								  pieChartField = DynamicSortValue;
							  
							  }
						  }
						  //默认显示最重要的排序字段0:显示;1不显示
	    				  if(mixChartFieldsId[i].equals(pieChartField))
	    				  {
	    					  lineDataModel = new MixDataModel("bar",mixChartFieldsName[i],0,lineData,0);
	    				  }else
	    				  {
	    					  lineDataModel = new MixDataModel("bar",mixChartFieldsName[i],0,lineData,1);
	    				  }
					  }
    				  mixChartList.add(lineDataModel);
    			  }
    			  chartMap.put("mixChart", mixChartList);
    		  }
    		  
    		  //判断当前报告类型是否需要饼图展示
    		  if(pieChartReportType.contains(reportType))
    		  {
    			  //绑定饼状图数据
    			  for(int i=0;i<listAll.size();i++)
    			  {
    				  PieDataModel pieDataModel = new PieDataModel(BeanUtils.getProperty(listAll.get(i), columField),BeanUtils.getProperty(listAll.get(i), pieChartField)) ;
    				  pieChartList.add(pieDataModel);
    			  }
    			  chartMap.put("pieChart", pieChartList);
    		  }
    	  }
    	  return chartMap;
      }
      
}
