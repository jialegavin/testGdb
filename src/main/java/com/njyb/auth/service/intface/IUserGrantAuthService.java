package com.njyb.auth.service.intface;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户授权统一接口
 * @author jiahp
 * 用户授权方式:
 * 1 根据海关编码进行授权:auth_hscode:spring ioc注入时候要用到的
 * 2 根据产品描述进行授权:auth_desc:spring ioc注入时候要用到的
 * 3 根据整个国家进行授权:auth_country:spring ioc注入时候要用到的
 * 4 根据订购次数进行授权:auth_count:spring ioc注入时候要用到的
 *
 */
public interface IUserGrantAuthService {
	/**
	 * 根据海关编码进行授权
	 */
	public abstract <T>T userGrantAuthByHscode(HttpServletRequest request,Map<String, String>paramMap);
	/**
	 * 根据产品描述进行授权
	 */
	public abstract <T>T userGrantAuthByDesc(HttpServletRequest request,Map<String, String>paramMap);
	/**
	 * 根据购买国家进行授权
	 */
	public abstract <T>T userGrantAuthByCountry(HttpServletRequest request,Map<String, String>paramMap);
	/**
	 * 根据订购次数进行授权
	 */
	public abstract <T>T userGrantAuthByCount(HttpServletRequest request,Map<String, String>paramMap);
}
