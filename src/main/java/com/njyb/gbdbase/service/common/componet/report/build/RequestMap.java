package com.njyb.gbdbase.service.common.componet.report.build;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
/**
 * Request 容器
 * @author nb
 *
 */
public class RequestMap implements Serializable {
	
	private static RequestMap requestMap = null;
	
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	public static RequestMap getRequestMap() {
		if (null != requestMap) {
			return requestMap;
		}
		return requestMap = new RequestMap();
	}
	
	private RequestMap() {
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
