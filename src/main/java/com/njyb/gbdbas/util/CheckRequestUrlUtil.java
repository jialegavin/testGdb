package com.njyb.gbdbas.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;
import com.njyb.auth.util.GetDoMainName;
import com.njyb.gbdbase.model.admincenter.UserModel;

/**
 * 判断当前用户登录的域名是否和上一次域名相同
 * @author WangBo
 * 2015年7月17日
 * CheckRequestUrl.java
 */
public class CheckRequestUrlUtil implements Serializable {
	
	private static Logger log = Logger.getLogger(CheckRequestUrlUtil.class); 

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * 判断当前用户的域名和请求的域名是否一致
	 * @param request : 请求
	 * @return true : 相同  false : 不相同
	 */
	public static boolean isRequestUrlEq(HttpServletRequest request) {
		boolean result = false;
		try {
			if (null != request) {
				UserModel model = (UserModel) request.getSession().getAttribute("user");
				if (null == model) {
					return true;
				}
				String newUrl = Strings.isNullOrEmpty(GetDoMainName.getDoMain(request)) ? "" : GetDoMainName.getDoMain(request);
				String nowUrl = Strings.isNullOrEmpty(model.getDomain()) ? "" : model.getDomain();
				if (newUrl.endsWith(nowUrl)) {
					result = true;
				}
			} else {
				log.debug("HttpServletRequest is null! Check input param!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
		}
		return result;
	}

}
