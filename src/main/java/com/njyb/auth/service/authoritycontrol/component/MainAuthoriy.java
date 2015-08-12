package com.njyb.auth.service.authoritycontrol.component;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.njyb.auth.service.authoritycontrol.impl.AttemptAuthorityService;
import com.njyb.auth.service.authoritycontrol.impl.VipAnthorityService;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.login.CommonLogoModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
/**
 * 主入口
 * @author 章华才
 */
public class MainAuthoriy {

	//查询条件
	protected static String hscode = null;
	protected static String desc = null;
	protected static String startDate = null;
	protected static String endDate = null;
	protected static String imexType = null;
	
	/**
	 * 总的方法
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @param jsonObject
	 * @return
	 */
	public static boolean allAuthority(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,String module,Map map){
		synchronized (MainAuthoriy.class) {
			AuthorityFieldModel.getAuthorityFieldMap().put("newTime",null);
			AuthorityFieldModel.getAuthorityFieldMap().put("datestr",null);
		}
		//初始化条件查询参数
		init(request,queryKey, queryValue);
		List<ConditionRightModel> list = (List<ConditionRightModel>) request.getSession().getAttribute("authorityInfo");
		UserModel model = (UserModel) request.getSession().getAttribute("user");
		if(model.getUserDesc().equals("正式用户")){
			String statu = null;
			String statu2 = null;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getByCountry().equals("中国")){
					if(statu2!=null){
						statu2 = statu2 + "1";
					}else{
						statu = "1";
					}
				}else{
					if(statu!=null){
						statu = statu + "2";
					}else{
						statu2 = "2";
					}
					
				}
			}
			//只购买中国
			if(statu!=null && statu.equals("1")){
				return new VipAnthorityService().byChinaMethod(request, queryValue, queryKey, country, jsonObject,list,module,map);
			}
			//只购买国外
			else if(statu2!=null && statu2.equals("2")){
				return new VipAnthorityService().byCountryOutMethod(request, queryValue, queryKey, country, jsonObject, list,module,map);
				
			}
			//购买中国加国外
			else if(statu2!=null || statu!=null && (statu.startsWith("12") || statu2.startsWith("21"))){
				return new VipAnthorityService().byChinaAndCountryOutMethod(request, queryValue, queryKey, country, jsonObject,list,module,map);
			}
		}
		//试用用户
		else if(model.getUserDesc().equals("试用用户")){
			return new AttemptAuthorityService().authoriyController(request, queryValue, queryKey, country, jsonObject);
			
		}
		//按次用户
		else if(model.getUserDesc().equals("按次用户")){
		}
		//超级用户（权限最大的用户）
		else if(model.getUserDesc().equals("超级用户")){
			return true;
		}
		return true;
	}
	
	
	/**
	 * 初始化查询参数
	 * @param queryKey
	 * @param queryValue
	 */
	public static void init(HttpServletRequest request,String queryKey,String queryValue){
		synchronized (MainAuthoriy.class) {
			AuthorityFieldModel.getAuthorityFieldMap().put("phone", ((CommonLogoModel)request.getSession().getAttribute("commonInfo")).getTel());
		}
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
				
				if(imexType!=null){
					if(imexType.equals("I")){
						imexType = "进口";
					}else if(imexType.equals("E")){
						imexType = "出口";
					}
				}
			}
		}
	}
}
