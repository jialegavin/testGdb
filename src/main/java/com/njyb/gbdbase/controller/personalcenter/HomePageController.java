package com.njyb.gbdbase.controller.personalcenter;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.njyb.gbdbase.model.personalcenter.CountryUpdateTimeModel;
import com.njyb.gbdbase.service.personalcenter.IHomePageService;

/**
 * 个人中心模块首页控制器
 * @author honghao
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
		for(CountryUpdateTimeModel model: countryDateList)
		{
			htmlData.append("■"+model.getCountryName()+"数据已经更新至"+model.getUpdateTime()+"&nbsp;");
		}
		//前台显示
		JSONObject json = new JSONObject();
		json.put("htmlData", htmlData.toString()); 
		out.write(json.toString());
		return null;
	}
}
