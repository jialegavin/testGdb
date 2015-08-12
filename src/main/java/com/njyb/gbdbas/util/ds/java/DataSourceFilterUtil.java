package com.njyb.gbdbas.util.ds.java;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
 * 拦截客户的所有请求
 * 设置数据源类型
 * @author 贾红平
 *
 */
public class DataSourceFilterUtil implements Filter {
	@Override
	public void destroy() {

	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse reponse,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req=(HttpServletRequest)request;
			ThreadContextUtil.setDName(req.getParameter("dsname"));
			chain.doFilter(request, reponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
