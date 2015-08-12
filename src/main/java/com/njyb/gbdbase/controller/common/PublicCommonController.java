package com.njyb.gbdbase.controller.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.ECacheContrastUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;

/**
 * 共用Controller
 * 
 * @author WangBo
 *
 */
@Controller
public class PublicCommonController {

	// jsonMap
	private Map<String, Object> jsonParamMap;

	/**
	 * 获取
	 * @return
	 */
	public Map<String, Object> getJsonParamMap() {
		return jsonParamMap;
	}

	public void setJsonParamMap(Map<String, Object> jsonParamMap) {
		this.jsonParamMap = jsonParamMap;
	}


	/**
	 * 获取查询的bean
	 * 
	 * @param request
	 * @return
	 */
	public PageBeanUtil getPageBean(HttpServletRequest request) {
		int pageIndex = Integer
				.valueOf((null == request.getParameter("page")) ? "1"
						: (request.getParameter("page")));
		int pageSize = Integer
				.valueOf((null == request.getParameter("rows")) ? "10"
						: (request.getParameter("rows")));
		PageBeanUtil beanUtil = new PageBeanUtil();
		beanUtil.setPageIndex(pageIndex);
		beanUtil.setPageSize(pageSize);
		return beanUtil;
	}

	/**
	 * 处理过的 查询条件Bean <br>
	 * mysql 分页
	 * @param request
	 * @return
	 */
	public PageBeanUtil getPageBeanToWorked(HttpServletRequest request) {
		int pageIndex = Integer
				.valueOf((null == request.getParameter("page")) ? "1"
						: (request.getParameter("page")));
		int pageSize = Integer
				.valueOf((null == request.getParameter("rows")) ? "10"
						: (request.getParameter("rows")));
		PageBeanUtil beanUtil = new PageBeanUtil();
		beanUtil.setPageIndex((pageIndex - 1) * pageSize);
		beanUtil.setPageSize(pageSize);
		return beanUtil;
	}
	
	/**
	 * 分页方法  java专用
	 * @param request
	 * @return
	 */
	public PageBeanUtil getPageBeanToRight(HttpServletRequest request) {
		int pageIndex = Integer
				.valueOf((null == request.getParameter("page")) ? "1"
						: (request.getParameter("page")));
		int pageSize = Integer
				.valueOf((null == request.getParameter("rows")) ? "10"
						: (request.getParameter("rows")));
		PageBeanUtil beanUtil = new PageBeanUtil();
		beanUtil.setPageIndex((pageIndex - 1) * pageSize);
		beanUtil.setPageSize(pageSize + beanUtil.getPageIndex());
		return beanUtil;
	}

	/**
	 * 获取封装好的json对象
	 * 
	 * @param total
	 * @param list
	 * @return
	 */
	public JSONObject getJsonObject(int total, List<?> list) {
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", list);
		return json;
	}

	/**
	 * 获取新的Map
	 * 
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getNewMap() {
		return Maps.newHashMap();
	}

	/**
	 * 获取session : 用户的Id
	 * 
	 * @param request
	 * @return
	 */
	public int getSessionOnUserId(HttpServletRequest request) {
		UserModel userModel = (UserModel) request.getSession().getAttribute(
				"user");
		return userModel.getUserId();
	}

	/**
	 * 构造查询的列的字段
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String[] findByFieldKey(HttpServletRequest request) {
		List<String> fieldKey = (List<String>) request.getAttribute("fieldKey");
		String[] key = new String[fieldKey.size()];
		key = (String[]) fieldKey.toArray(key);
		return key;
	}

	/**
	 * 构造查询字段列的值
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String[] findByFieldValue(HttpServletRequest request) {
		List<String> fieldValue = (List<String>) request
				.getAttribute("fieldValue");
		String[] value = new String[fieldValue.size()];
		value = (String[]) fieldValue.toArray(value);
		return value;
	}
	
	/**
	 * java 分页查询
	 * @param pageBean
	 * @param list
	 * @return
	 */
	public <T> List<T> getSubList(PageBeanUtil pageBean,List<T> list) {
		List<T> returnList = Lists.newArrayList();
		if (null != list && !list.isEmpty()) {
			int fromIndex = pageBean.getPageIndex();
			int toIndex = pageBean.getPageSize();
			if (toIndex > list.size()) {
				toIndex = list.size();
			}
			returnList = list.subList(fromIndex, toIndex);
		}
		return returnList;
	}
	
	/**
	 * 获取缓存中的pageBean
	 * @param elementKey : 缓存Bean
	 * @return PageBeanUtil
	 */
	public void getCacheOfTotal(String elementKey,PageBeanUtil pageBean){
		int totalPage = 0;
		if (!Strings.isNullOrEmpty(elementKey)) {
			totalPage = Integer.valueOf(ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE, elementKey).toString());
			if (totalPage > 0) {
				pageBean.setPageCount(totalPage);
			}
		}
	}
}