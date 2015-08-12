package com.njyb.auth.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.njyb.gbdbase.model.admincenter.AuthorityModel;
import com.njyb.gbdbase.model.admincenter.UserModel;

/**
 * 主入口
 * @author 章华才
 */
public class MainAuthoriy {

	protected static String hscode = null;
	protected static String desc = null;
	protected static String startDate = null;
	protected static String endDate = null;
	protected static String imexType = null;
	
	/**
	 * 判断是否能访问子模块
	 * @return
	 */
	public static boolean isVisitModule(HttpServletRequest request,HttpServletResponse response,String role,Map map,JSONObject jsonObject){
		//获取当前请求的controller路径
		String controllerUrl = request.getRequestURI();
		String url = (String) map.get(role);
		if(url.contains(controllerUrl)){
			jsonObject.put("error", "对不起，你没有权限能访问！");
			return false;
		}else{
			//针对赠送的国家没有下载权限
			if(request.getSession().getAttribute("isGive")!=null){
				if(request.getSession().getAttribute("isGive").equals("No")){
					String notDownLoad = (String) map.get("notGiveDownLoad");
					if(notDownLoad.contains(controllerUrl)){
						jsonObject.put("error", "对不起，你没有下载权限！");
						return false;
					}
				}
			}
			return true;
		}
	}
	
	/**
	 * 对比中心除外总的方法
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @param jsonObject
	 * @return
	 */
	public static boolean allAuthority(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,String module,Map map){
		//初始化参数
		init(queryKey, queryValue);
		List<AuthorityModel> list = (List<AuthorityModel>) request.getSession().getAttribute("authorityInfo");
		UserModel model = (UserModel) request.getSession().getAttribute("user");
		if(model.getUserDesc().equals("正式用户")){
			
			for (int i = 0; i < list.size(); i++) {
				//中国+国外
				if(list.size() > 1 && list.get(i).getBycountry().equals("中国")){
					return new VipUserAnthority().byChinaAndCountryOutMethod(request, queryValue, queryKey, country, jsonObject,list,module,map);
				}
				//只购买中国
				else if(list.size() == 1 && list.get(i).getBycountry().equals("中国")){
					return new VipUserAnthority().byChinaMethod(request, queryValue, queryKey, country, jsonObject,list,module,map);
				}
				//只购买国外
				else if(list.size() >= 1 && !list.get(i).getBycountry().equals("中国")){
					return new VipUserAnthority().byCountryOutMethod(request, queryValue, queryKey, country, jsonObject, list,module,map);
				}
			}
			
			
		}else if(model.getUserDesc().equals("试用用户")){
			
			return new TryUser().authoriyController(request, queryValue, queryKey, country, jsonObject);
			
		}else if(model.getUserDesc().equals("按次用户")){
			
//			return new NoteCountUser();
			
		}else if(model.getUserDesc().equals("超级用户")){
			return true;
		}
		return true;
	}
	
	
	
	/**
	 * 初始化查询参数
	 * @param queryKey
	 * @param queryValue
	 */
	public static void init(String queryKey,String queryValue){
		hscode = null;
		desc = null;
		startDate = null;
		endDate = null;
		imexType = null;
		String [] key = queryKey.split(";");
		String [] value = queryValue.split(";");
		for (int i = 0; i < key.length; i++) {
			if(key[i].equals("hscode")){
				hscode = value[i];
			}
			if(key[i].equals("goods_desc")){
				desc = value[i];
			}
			if(key[i].equals("date")){
				String [] date = value[i].split(",");
				startDate = date[0];
				endDate = date[1];
			}
			if(key[i].equals("trade_type")){
				imexType = value[i];
			}
		}
	}
	
	
}
