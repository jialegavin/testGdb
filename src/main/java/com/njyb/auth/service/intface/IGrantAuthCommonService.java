package com.njyb.auth.service.intface;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;

/**
 * 授权公用接口
 * @author XL
 * @date 2015-06-26
 */
public interface IGrantAuthCommonService {

	/**
	 * 添加权限
	 * @param paramMap
	 */
	void addRight(Map<String, Object> paramMap);
	
	/**
	 * 组装用户授权的集合
	 * @param paramMap
	 * @return
	 */
	Map<String,Object> commonUserGrantAuth(Map<String, String> paramMap);

	/**
	 * 授权成功后用户角色处理及发送邮件
	 * @param paramMap
	 */
	void grantAuthBeforeOper(HttpServletRequest request,Map<String, Object> paramMap);
	
	/**
	 * 获取国家授权信息
	 * @param auth_type
	 * @param request
	 * @return
	 */
	List<ConditionRightModel> getCountryDefaultInfo(String auth_type, HttpServletRequest request);
}
