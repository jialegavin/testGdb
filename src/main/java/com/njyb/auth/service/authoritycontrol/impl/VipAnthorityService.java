package com.njyb.auth.service.authoritycontrol.impl;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.njyb.auth.service.authoritycontrol.IAuthorityControlService;
import com.njyb.auth.service.authoritycontrol.component.AuthorityComponentUtil;
import com.njyb.auth.service.authoritycontrol.component.AuthorityDateUtil;
import com.njyb.auth.service.authoritycontrol.component.CommonConstantUtil;
import com.njyb.auth.service.authoritycontrol.component.MainAuthoriy;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
/**
 * 收费用户/正式用户
 * @author 章华才
 */
public class VipAnthorityService extends MainAuthoriy implements IAuthorityControlService{

	private boolean isTrue = false;
	
	
	/**
	 * 购买中国的
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @return
	 */
	@Override
	public boolean byChinaMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<ConditionRightModel> list,String module,Map map){
		for(int i=0;i<list.size();i++){
			ConditionRightModel model = list.get(i);
				//用户选择中国
				if(country.equals("中国")){
					//等于中国
					if(country.equals(model.getByCountry())){
						isTrue = true;
						//判断国家进出口
						if(model.getiExportType()!=null && !"".equals(model.getiExportType())){
							//判断中国购买的哪个产品（hscode/desc）
							return AuthorityComponentUtil.chinaHscodeOrDesc(model,jsonObject,module,country,request,map,list);
						}
					}
				}
				//用户选择赠送国外数据
				else if(!country.equals("中国")){
					isTrue = true;
					//加上同步锁
					synchronized (this) {
						map.put("give", "no");	
					}
					UserModel user = (UserModel) request.getSession().getAttribute("user");
					//如果使用3个月已到
					if(AuthorityDateUtil.isDateEquals(AuthorityDateUtil.dateAddThree(user.getBeginTime(),country),country)){
						jsonObject.put("error", CommonConstantUtil.PROMPT_GIVEDATA+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
						return false;
					}else{
						//赠送国外数据
						return AuthorityComponentUtil.giveTreeCountry(model, country, map, user, jsonObject,list);
					}
				}
		}
		//如果为false 表示没有查询到国家权限
		 if (isTrue == false) {
			jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
			return false;
		}
		return true;
	}
	
	/**
	 * 购买国外
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @return
	 */
	@Override
	public boolean byCountryOutMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<ConditionRightModel> list,String module,Map map){
	  for (int i = 0; i < list.size(); i++) {
		  ConditionRightModel model = list.get(i);
		  if(imexType!=null){
				//判断国家是否一致
				if(country.equals(model.getByCountry()) && model.getiExportType().equals(imexType)){
					isTrue=true;
					//购买国外的国家
					return AuthorityComponentUtil.byCountryOut(model, country, jsonObject,list);
				}
			}else{
				if(country.equals(model.getByCountry()+model.getiExportType())){
					isTrue=true;
					//购买国外的国家
					return AuthorityComponentUtil.byCountryOut(model, country, jsonObject,list);
				}
			}
		}
		//如果为false 表示没有查询到国家权限
		if (isTrue == false){
			jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
			return false;
		}
		return true;
	}
	
	/**
	 * 购买中国加国外
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @return
	 */
	@Override
	public boolean byChinaAndCountryOutMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<ConditionRightModel> list,String module,Map map){
		for(int i=0;i<list.size();i++){
			ConditionRightModel model = list.get(i);
			//用户选择中国
			if(country.equals("中国")){
				//等于中国
				if(country.equals(model.getByCountry())){
					isTrue = true;
					//判断国家进出口
					if(model.getiExportType()!=null && !"".equals(model.getiExportType())){
						//判断中国购买的哪个产品（hscode/desc）
						return AuthorityComponentUtil.chinaHscodeOrDesc(model,jsonObject,module,country,request,map,list);
					}
				}
			}
			// 中国加国外 （除中国之外的国外数据 ：包含赠送免费的国家+购买的国家）
			else if(!country.equals("中国")){
				if(imexType!=null){
					//判断国家是否一致
					if(country.equals(model.getByCountry()) && imexType.equals(model.getiExportType())){
						isTrue = true;
						//购买国外的国家
						return AuthorityComponentUtil.byCountryOut(model, country, jsonObject,list);
					}
				}
				//判断国家是否一致
				else if(country.equals(model.getByCountry()+model.getiExportType())){
					isTrue = true;
					//购买国外的国家
					return AuthorityComponentUtil.byCountryOut(model, country, jsonObject,list);
				}
					
				
			}
		}
		//如果为false 表示没有查询到国家权限
		if (isTrue == false){
			//购买国外数据后仍然可以享受国外免费开放的数据（条件是在免费开放的国外数据有效范围内）
			//是否可下载数据
			map.put("give", "no");
			UserModel user = (UserModel) request.getSession().getAttribute("user");
			//判断国外免费赠送国家的有效时间有没有到期3个月
			if(AuthorityDateUtil.isDateEquals(AuthorityDateUtil.dateAddThree(user.getBeginTime(),country),country)){
				jsonObject.put("error", CommonConstantUtil.PROMPT_GIVEDATA+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
				return false;
			}else{
				//赠送国外数据
				return AuthorityComponentUtil.giveTreeCountry(null, country, map, user, jsonObject,list);
			}
		}
		return true;
	}
	
	/**
	 * 试用用户
	 */
	@Override
	public boolean authoriyController(HttpServletRequest request,
			String queryValue, String queryKey, String country,
			JSONObject jsonObject) {
		return false;
	}
}
