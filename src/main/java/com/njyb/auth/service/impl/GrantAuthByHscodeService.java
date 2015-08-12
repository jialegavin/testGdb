package com.njyb.auth.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.auth.service.CommonAuthService;
import com.njyb.auth.service.intface.IGrantAuthCommonService;
import com.njyb.auth.service.intface.IGrantAuthService;
/**
 * 根据海关编码进行授权
 * @author jiahp
 * 注意:这里service的注入不能使用默认的注入方式 ,需要手动指定一个名称,否则spring会抛出异常的
 */
@Service("auth_hscode")
public class GrantAuthByHscodeService extends CommonAuthService implements IGrantAuthService {
	@Autowired
	private IGrantAuthCommonService grantAuthCommonService;
	
	@Override
	public <T> T executeGrantAuth(HttpServletRequest request,
			Map<String, Object> paramMap) {
		String rightDesc = "单一权限";
		paramMap.put("rightDesc", rightDesc);
		grantAuthCommonService.addRight(paramMap);
		return null;
	}

}
