package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.PageBeanUtil;
/**
 * 封装检索需要用到的参数
 * @author 贾红平
 *
 */
public class SearchCommonParamModel extends CommonParamModel {
	//封装分页对象
	private PageBeanUtil page;

	public SearchCommonParamModel(String[] feilds, String[] values,
			String country, String module, HttpServletRequest request,
			PageBeanUtil page) {
		super(feilds, values, country, module, request);
		this.page = page;
	}

	public PageBeanUtil getPage() {
		return page;
	}

	public void setPage(PageBeanUtil page) {
		this.page = page;
	}
	
	 
	 
}
