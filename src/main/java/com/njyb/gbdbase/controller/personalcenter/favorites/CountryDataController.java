package com.njyb.gbdbase.controller.personalcenter.favorites;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
/**
 * 浏览记录 和 我的收藏 <br>
 * 点击查看详情<br>
 * 共用的Controller<br>
 * @author WangBo
 * 2015年4月3日
 * CountryDataController.java
 */
@Controller
@RequestMapping("CountryData")
public class CountryDataController extends PublicCommonController {
	
	@RequestMapping(value="queryCountryData")
	public void queryCountryDataByParam(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String queryValue = request.getParameter("queryValue");
		PageBeanUtil pageBeanUtil = this.getPageBean(request);
		JSONObject json = this.getJsonObject(0, Lists.newArrayList());
		response.getWriter().println(json.toString());
	}
}