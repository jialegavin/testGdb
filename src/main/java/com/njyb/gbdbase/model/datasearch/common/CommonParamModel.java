package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.PageBeanUtil;
/**
 * 封装查询的共性参数
 * @author 贾红平
 *
 */
public class CommonParamModel implements Serializable {
	/**
	 * 保证序列化版本一致性
	 */
	private static final long serialVersionUID = 1L;
	//用户输入的查询参数
	private String[]feilds;
	//用户输入的查询参数的值
	private String[]values;
	//用户输入的英文简称
	private String country;
	//当前模块的简称 参考ParamEnumUtil
	private String module;
	//封装请求的request对象
	private HttpServletRequest request;
	 
	public CommonParamModel(String[] feilds, String[] values, String country,
			String module, HttpServletRequest request) {
		super();
		this.feilds = feilds;
		this.values = values;
		this.country = country;
		this.module = module;
		this.request = request;
	}
	public String[] getFeilds() {
		return feilds;
	}
	public void setFeilds(String[] feilds) {
		this.feilds = feilds;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	 
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
