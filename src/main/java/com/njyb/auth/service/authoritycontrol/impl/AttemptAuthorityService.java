package com.njyb.auth.service.authoritycontrol.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.njyb.auth.service.authoritycontrol.IAuthorityControlService;
import com.njyb.auth.service.authoritycontrol.component.AuthorityDateUtil;
import com.njyb.auth.service.authoritycontrol.component.CommonConstantUtil;
import com.njyb.auth.service.authoritycontrol.component.MainAuthoriy;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;

/**
 * 试用用户
 * @author 章华才
 */
public class AttemptAuthorityService extends MainAuthoriy implements IAuthorityControlService{

	
	
	/**
	 * 试用用户权限控制
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @param jsonObject
	 * @return
	 */
	public boolean authoriyController(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject){
		UserModel model = (UserModel) request.getSession().getAttribute("user");
		//判断试用用户，试用时间有没有到期
		System.out.println(AuthorityDateUtil.dateAddThree(model.getRegistertime(), ""));
		if(AuthorityDateUtil.isDateEquals(AuthorityDateUtil.dateAddThree(model.getRegistertime(), ""),null)){
			jsonObject.put("error", CommonConstantUtil.PROMPT_TRYDATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
			return false;
		}else{
			//特殊用户
			if(AuthorityFieldModel.getAuthorityFieldMap().get("special_user").toString().contains(model.getLoginName())){
				//不开放中国
				if(AuthorityFieldModel.getAuthorityFieldMap().get("special_user").toString().contains(country)){
					jsonObject.put("error", CommonConstantUtil.PROMPT_AUTHORITY+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
					return false;
				}else{
					return true;
				}
			}
			//一般用户
			else{
				//试用用户检索时间
				String startTime = AuthorityFieldModel.getAuthorityFieldMap().get("tyrUserDate").toString().split(",")[0];
				String endTime =  AuthorityFieldModel.getAuthorityFieldMap().get("tyrUserDate").toString().split(",")[1];
				//处理日期格式
				startDate = AuthorityDateUtil.fromatDate(startDate,country);
				endDate = AuthorityDateUtil.fromatDate(endDate,country);
				//判断日期格式  yyyy-mm-dd/yyyy-mm
				if(AuthorityDateUtil.validate(startDate)){
					if(DataUtil.parseDate(startDate, 3).getTime() >= DataUtil.parseDate(startTime, 3).getTime()
							&& DataUtil.parseDate(startDate, 3).getTime()<=DataUtil.parseDate(endTime, 3).getTime()
							&& DataUtil.parseDate(endDate, 3).getTime()>=DataUtil.parseDate(startTime, 3).getTime()
							&& DataUtil.parseDate(endDate, 3).getTime()<= DataUtil.parseDate(endTime, 3).getTime()){
					}else{
						jsonObject.put("error", CommonConstantUtil.PROMPT_TRYQUERYDATA+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
						return false;
					}
				}else{
					if(DataUtil.parseDate(startDate, 17).getTime()>=DataUtil.parseDate(startTime, 3).getTime()
							&& DataUtil.parseDate(startDate, 17).getTime()<=DataUtil.parseDate(endTime, 3).getTime()
							&& DataUtil.parseDate(endDate, 17).getTime()>=DataUtil.parseDate(startTime, 3).getTime()
							&& DataUtil.parseDate(endDate, 17).getTime()<= DataUtil.parseDate(endTime, 3).getTime()){
					}else{
						jsonObject.put("error", CommonConstantUtil.PROMPT_TRYQUERYDATA+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
						return false;
					}
				}
			}
		}
		return true;
	}

	
	public boolean specialUser(){
		return false;
	}
	
	@Override
	public boolean byChinaMethod(HttpServletRequest request, String queryValue,
			String queryKey, String country, JSONObject jsonObject,
			List<ConditionRightModel> list, String module, Map map) {
		return false;
	}

	@Override
	public boolean byCountryOutMethod(HttpServletRequest request,
			String queryValue, String queryKey, String country,
			JSONObject jsonObject, List<ConditionRightModel> list, String module,
			Map map) {
		return false;
	}

	@Override
	public boolean byChinaAndCountryOutMethod(HttpServletRequest request,
			String queryValue, String queryKey, String country,
			JSONObject jsonObject, List<ConditionRightModel> list, String module,
			Map map) {
		return false;
	}
	
	public boolean isUserTry(UserModel model,String country,String endTime){
		if(country.equals("中国")){
			if(AuthorityDateUtil.isDateEquals(endTime, endDate, country)){
				AuthorityFieldModel.getAuthorityFieldMap().put("newTime", endTime);
				AuthorityFieldModel.getAuthorityFieldMap().put("july100", "july100");
				return true;
			}
		}else{
			AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
		}
		return true;
	}
}
