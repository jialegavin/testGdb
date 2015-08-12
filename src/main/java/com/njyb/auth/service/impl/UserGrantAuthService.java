package com.njyb.auth.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.auth.service.intface.IGrantAuthCommonService;
import com.njyb.auth.service.intface.IGrantAuthService;
import com.njyb.auth.service.intface.IUserGrantAuthService;
import com.njyb.gbdbas.util.IConstantUtil;
/**
 * 执行用户所有具体的授权方式
 * @author jiahp
 *
 */
@Service
public class UserGrantAuthService implements IUserGrantAuthService {
	/*注入根据海关编码授权的实现类*/
	@Resource(name="auth_hscode")
	private IGrantAuthService hscodeService;
	/*注入根据产品描述授权的实现类*/
	@Resource(name="auth_desc")
	private IGrantAuthService descService;
	/*注入根据购买国家授权的实现类*/
	@Resource(name="auth_country")
	private IGrantAuthService countryService;
	/*注入根据订购次数授权的实现类*/
	@Resource(name="auth_count")
	private IGrantAuthService countService;
	@Autowired
	private IGrantAuthCommonService grantAuthCommonService;
	
	/**
	 * 根据海关编码进行具体授权
	 */
	@Override
	public <T> T userGrantAuthByHscode(HttpServletRequest request,
			Map<String, String> paramMap) {
		Map<String, Object> map = grantAuthCommonService.commonUserGrantAuth(paramMap);
		//授权方式
		map.put("auth_type", IConstantUtil.AUTH_HSCODE);
		//添加授权
		hscodeService.executeGrantAuth(request, map);
		//授权成功后对用户角色处理及发送邮件
		grantAuthCommonService.grantAuthBeforeOper(request,map);
		return null;
	}
	
	/**
	 * 根据用户订购的产品描述进行具体的权限
	 */
	@Override
	public <T> T userGrantAuthByDesc(HttpServletRequest request,
			Map<String, String> paramMap) {
		Map<String, Object> map = grantAuthCommonService.commonUserGrantAuth(paramMap);
		//授权方式
		map.put("auth_type", IConstantUtil.AUTH_DESC);
		//添加授权
		descService.executeGrantAuth(request, map);
		//授权成功后对用户角色处理及发送邮件
		grantAuthCommonService.grantAuthBeforeOper(request,map);
		return null;
	}
	
	/**
	 * 根据用户订购的国家进行具体的授权
	 */
	@Override
	public <T> T userGrantAuthByCountry(HttpServletRequest request,
			Map<String, String> paramMap) {
		Map<String, Object> map = grantAuthCommonService.commonUserGrantAuth(paramMap);
		map.put("auth_type", IConstantUtil.AUTH_COUNTRY);
		countryService.executeGrantAuth(request, map);
		//授权成功后对用户角色处理及发送邮件
		grantAuthCommonService.grantAuthBeforeOper(request,map);
		return null;
	}
	
	/**
	 * 根据用户订购的次数进行具体授权
	 */
	@Override
	public <T> T userGrantAuthByCount(HttpServletRequest request,
			Map<String, String> paramMap) {
		Map<String, Object> map = grantAuthCommonService.commonUserGrantAuth(paramMap);
		double totalMoney = Double.parseDouble(paramMap.get("buyMoney"));
		map.put("totalMoney", totalMoney);
		//授权方式
		map.put("auth_type", IConstantUtil.AUTH_COUNT);
		countService.executeGrantAuth(request, map);
		//授权成功后对用户角色处理及发送邮件
		grantAuthCommonService.grantAuthBeforeOper(request,map);
		return null;
	}

}
