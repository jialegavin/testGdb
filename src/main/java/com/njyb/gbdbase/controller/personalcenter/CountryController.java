package com.njyb.gbdbase.controller.personalcenter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.service.common.intercontinental.IntercontinentalUtil;

/**
 * 国家控制器
 * 
 * @author WangBo 2015年4月8日 CountryController.java
 */
@Controller
@RequestMapping(value = "/country")
public class CountryController extends PublicCommonController {

	@RequestMapping(value = "getCountry")
	public void getCountry(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONArray array = new JSONArray();
		String lg = request.getSession().getAttribute("language").toString();
		if (lg.equals("message_zh_CN")) {
			String countryList = IntercontinentalUtil.getCountryList().get(
					"countrylist");
			array.add(countryList);
			array.add(countryList);
		} else if (lg.equals("message_en_US")) {
			String countryListen = IntercontinentalUtil.getCountryList().get(
					"en_countryList");
			array.add(countryListen);
		}
		response.setContentType("text/plain;charset=utf-8");
		response.getWriter().write(array.toString());
	}

	/**
	 * 获取全球所有国家
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllCountry")
	public Object getAllCountry(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer sb = new StringBuffer();
		String str = "、";
		String fzlist = IntercontinentalUtil.createMap().get("非洲");
		sb.append(fzlist);
		String njzlist = IntercontinentalUtil.createMap().get("南极洲");
		if (!"".equals(njzlist)) {
			sb.append(str + njzlist);
		}
		String yzlist = IntercontinentalUtil.createMap().get("亚洲");
		sb.append(str + yzlist);
		String ozlist = IntercontinentalUtil.createMap().get("欧洲");
		sb.append(str + ozlist);
		String bmzlist = IntercontinentalUtil.createMap().get("北美洲");
		sb.append(str + bmzlist);
		String dyzlist = IntercontinentalUtil.createMap().get("大洋洲");
		sb.append(str + dyzlist);
		String nmzlist = IntercontinentalUtil.createMap().get("南美洲");
		sb.append(str + nmzlist);
		JSONArray array = new JSONArray();
		array.add(sb.toString());
		response.setContentType("text/plain;charset=utf-8");
		response.getWriter().write(array.toString());
		return null;
	}
}
