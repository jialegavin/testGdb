package com.njyb.gbdbas.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页的公用的bean
 * @author jcy
 * 
 */
public class PageBeanUtil {
	//当前的页数
	private int pageIndex = 1;
	//每页显示的条目数
	private int pageSize = 10;
	//总条目数
	private int pageCount = 0;
	//所有的查询条件
	private Map<String, Object> map = new HashMap<String, Object>();
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public PageBeanUtil() {
		super();
	}
	public PageBeanUtil(int pageSize) {
		super();
		this.pageSize = pageSize;
	}
	
	
	
}
