package com.njyb.auth.service.intface;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权接口
 * @author jiahp
 *
 */
public interface IGrantAuthService {
	/**
	 * 执行具体的授权
	 * @param paramMap :封装客户端需要的参数
	 * @param request:封装客户端所有相关请求的参数的信息
	 * @return
	 */
	public abstract <T> T executeGrantAuth(HttpServletRequest request,Map<String, Object>paramMap);
}
