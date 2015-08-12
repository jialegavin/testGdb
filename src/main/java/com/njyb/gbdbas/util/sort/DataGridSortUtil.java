package com.njyb.gbdbas.util.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbase.model.datasearch.common.ResultFieldModel;

/**
 * easyUI数据表格排序方法工具类
 * @author honghao
 * 2015-04-27
 *
 */
public class DataGridSortUtil{
	@Resource
	private  ResultFieldModel resultFieldModel;
	
	/**
	 * easyUI数据表格排序方法
	 * @param request
	 * @param list
	 * @param defaultName
	 */
	 public <T> void executeSearchSort(HttpServletRequest request,List<T> list,Map map,String type) {
			Enumeration e=request.getParameterNames();
			Map mp=new HashMap();
			String name=null;
			//获取当前国家的排序字段
			String sortKey="";
			if(DataSearchConstantUtil.SEARCH.equals(type))
			{
				sortKey = map.get(((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.DEFAULTSORTKEY)).toString();
			}else if(DataSearchConstantUtil.REPORT.equals(type))
			{
				sortKey = map.get((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.PERCENTAGE_SORT_FIELD).toString();
			}else if(DataSearchConstantUtil.THB.equals(type))
			{
				sortKey = map.get((String)request.getSession().getAttribute("country")+DataSearchConstantUtil.THB_SORT_FIELD).toString();
			}
			//获取所有的客户端传过来的参数 按特定条件进行封装
			while(e.hasMoreElements()){
				name=e.nextElement().toString();
				if (name.equals("sort"))
				{
					mp.put(name, request.getParameter(name));
				}
				if (name.equals("order")) {
					mp.put(name, request.getParameter(name));
				}
			}
			//判断用户是否选择了字段进行排序
			if (mp!=null && mp.size() !=0) {
				for (Object o:mp.keySet()) {
					if (o.equals("sort")) {
						//降序
						if (mp.get("order").equals("desc")) 
						{
				 			executeSort(list, new OrderDescCompartor(mp.get("sort").toString()));
						}
						//升序
						else
						{
				 			executeSort(list, new OrderAscCompartor(mp.get("sort").toString()));
						}
					}
	 			}
			}
			//如果没有选择 用默认字段进行排序
			else{
	 			executeSort(list, new OrderDescCompartor(sortKey));
			}
		}
	 
	  /**
	   * 根据指定字段--对集合进行降序排序
	   * @param request
	   * @param list
	   * @param defaultName
	   */
	   @SuppressWarnings("unchecked")
	   public static <T> void executeSort(List<T>ls,Comparator comp){
		   if(ls != null)
		   {
			   Collections.sort(ls,comp);
		   }
	   }
}