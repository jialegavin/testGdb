package com.njyb.gbdbase.controller.personalcenter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.njyb.gbdbase.model.personalcenter.CountryUpdateTimeModel;
import com.njyb.gbdbase.service.personalcenter.IHomePageService;

/**
 * 个人中心模块首页控制器
 * @author honghao  业务需求变更,重写 wangB
 * 2015-05-18
 * @version 标准版
 */
@Controller
public class HomePageController {
	@Autowired
	private IHomePageService homePageService;
	
	/**
	 * 查询各国家最新数据更新时间
	 */
	@RequestMapping(value="/queryCountryUpdateTime")
	public String queryCountryUpdateTime(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//查询所有国家的最新更新时间
		List<CountryUpdateTimeModel> countryDateList = homePageService.getCountryUpdateTimeModel();
		StringBuffer htmlData = new StringBuffer();
		for(CountryUpdateTimeModel model: countryDateList) {
			htmlData.append("■"+model.getChCountryName()+"("+model.getEhCountryName()+")数据已经更新至"+model.getStartTime() + "--" + model.getEndTime() +"&nbsp;");
		}
		//前台显示
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData.toString()); 
		out.write(json.toString());
		return null;
	}
	
	/**
	 * 个个国家的数据更新时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/queryDBDateMessage")
	public void queryDBDateMessage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<CountryUpdateTimeModel> countryDateList = homePageService.getCountryUpdateTimeModel();
		Map<String,Object> resultMap = Maps.newHashMap();
		// JSON 格式 : key {国家: {data 数据}}
		for (CountryUpdateTimeModel updateTime : countryDateList) {
			resultMap.put(updateTime.getChCountryName(), updateTime);
		}
		JSONObject json = new JSONObject();
		json.putAll(resultMap);
		response.getWriter().println(json.toString());
	}
}
