package com.njyb.gbdbas.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 贸易情报数据汇总分页处理的工具类
 * @author 贾红平
 *
 */
public class TradeResultPageUtil {
	 /**
	   * 计算报表汇总比率
	   * @param request
	   * @param listAll
	   * @param pageList
	   * @param map
	   * @return
	   * @throws Exception
	   */
	    public static List<DataReportSumModel> getPercentage(HttpServletRequest request,List <DataReportSumModel> listAll,List <DataReportSumModel> pageList,Map map)throws Exception
	    {
	    	//当前国家名
	    	String country = request.getSession().getAttribute("country").toString();
	    	//报表需要计算比率的字段
	    	String field = map.get(country+DataSearchConstantUtil.PERCENTAGE_SORT_FIELD).toString();
	    	//报表比率显示字段
	    	String percentField = map.get(country+DataSearchConstantUtil.PERCENTFIELD).toString();
	    	Double sumFieldValue = 0.0;
	    	if(listAll != null)
	    	{
		    	for(DataReportSumModel model : listAll)
		    	{
		    		sumFieldValue += BeanUtils.getProperty(model, field) == null ? 0.0 : Double.valueOf(BeanUtils.getProperty(model, field));
		    	}
		    	
		    	for(DataReportSumModel model : pageList)
		    	{
		    		Double fieldValue = BeanUtils.getProperty(model, field) == null ? 0.0 : Double.valueOf(BeanUtils.getProperty(model, field));
		    		BeanUtils.setProperty(model,percentField, fieldValue/sumFieldValue*100);
		    	}
	    	}
	    	return pageList;
	    }
	    
	    /**
	     * 计算当前页面汇总
	     * @param request
	     * @param columField
	     * @param pageList
	     * @param map
	     * @return
	     * @throws Exception
	     */
	    @SuppressWarnings("all")
		public static List<DataReportSumModel> currentPageSummary(HttpServletRequest request,String columField,List <DataReportSumModel> pageList,Map map)throws Exception
	      {
	    	  String [] precisionFields = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.REPORT_FORMATPRECISION)).toString().split(",");
	    	  DataReportSumModel dataReportSumModel = new DataReportSumModel();
	    	  
	    	  for(String field : precisionFields)
	    	  {
	    		  //贸易次数是整形的需要特殊对待
	    		  int sumTradeCountValue =0;
	    		  //其他double类型字段(金额重量数量)
				  Double sumOtherFieldValue = 0.0;
				  for(DataReportSumModel model : pageList)
				  {
					  //针对英国没有金额，数量，重量的国家做判断
					  if(!field.isEmpty()){
						  sumOtherFieldValue += BeanUtils.getProperty(model, field) == null ? 0.0 : Double.valueOf(BeanUtils.getProperty(model, field));
					  }
					  sumTradeCountValue += Integer.valueOf(BeanUtils.getProperty(model, "tradeCount"));
				  }
				  //针对英国没有金额，数量，重量的国家做判断
				  if(!field.isEmpty()){
					  BeanUtils.setProperty(dataReportSumModel, field,sumOtherFieldValue);
				  }
				  BeanUtils.setProperty(dataReportSumModel, "tradeCount",sumTradeCountValue);
	    	  }
	    	
	    	  BeanUtils.setProperty(dataReportSumModel, columField,"Sub Total");
	    	  BeanUtils.setProperty(dataReportSumModel, "hideColumn","Sub Total");
	    	  List<DataReportSumModel> footer = new LinkedList<DataReportSumModel>();
	    	  footer.add(dataReportSumModel);
	    	  return footer;
	      }
}
