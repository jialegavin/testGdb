package com.njyb.auth.service.authoritycontrol;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.njyb.gbdbase.model.admincenter.AuthorityModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;

/**
 * 权限控制业务访问层
 * @author 章华才
 */
public interface IAuthorityControlService {

	
	/**
	 * 购买中国+赠送国外数据
	 * @param request
	 * @param queryValue  查询条件value
	 * @param queryKey    查询key
	 * @param country     国家
	 * @param jsonObject  
	 * @param list		权限list
	 * @param module    模块
	 * @param map       配置map xml文件
	 * @return boolean
	 */
	public abstract boolean byChinaMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<ConditionRightModel> list,String module,Map map);
	
	
	
	/**
	 * 购买国外数据
	 * @param request
	 * @param queryValue  查询条件value
	 * @param queryKey    查询key
	 * @param country     国家
	 * @param jsonObject  
	 * @param list		权限list
	 * @param module    模块
	 * @param map       配置map xml文件
	 * @return boolean
	 */
	public abstract boolean byCountryOutMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<ConditionRightModel> list,String module,Map map);
	
	
	/**
	 * 购买中国+国外数据
	 * @param request
	 * @param queryValue  查询条件value
	 * @param queryKey    查询key
	 * @param country     国家
	 * @param jsonObject  
	 * @param list		权限list
	 * @param module    模块
	 * @param map       配置map xml文件
	 * @return boolean
	 */
	public abstract boolean byChinaAndCountryOutMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<ConditionRightModel> list,String module,Map map);


	
	/**
	 * 试用用户
	 * @param request
	 * @param queryValue 查询条件值
	 * @param queryKey   查询条件key
	 * @param country    国家
	 * @param jsonObject
	 * @return boolean
	 */
	public abstract boolean authoriyController(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject);

}
