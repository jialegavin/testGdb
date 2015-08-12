package com.njyb.gbdbase.controller.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbas.util.translate.LanguageUtil;

/**
 * 将中文翻译成各国家的语言
 * @author 洪皓
 * 2015-04-09
 * @version 标准版
 */
@Controller
@RequestMapping("/translate")
public class TranslateController {
	private LanguageUtil languageUtil = new LanguageUtil();
	
	@RequestMapping(value="translateCountry")
	public String translateCountryValue(HttpServletRequest request,HttpServletResponse response,
			String countryValue) throws Exception{
		
		response.setContentType("text/plain;charset=utf-8");
		String country = (String) request.getSession().getAttribute("country");
		
		if("russian_import".equals(country) || "russian_export".equals(country))
		{
			//俄语
			response.getWriter().write(languageUtil.getStrValueByRussian(countryValue));
		}else if("usa_import".equals(country) || "pakistan_import".equals(country) || "korea".equals(country)
				         ||"uk_import".equals(country) || "mexicon_import".equals(country) || "const_import".equals(country)
				         ||"const_export".equals(country) ||"india_import".equals(country)||"india_export".equals(country)
				         ||"panama_import".equals(country) ||"panama_export".equals(country) ||"wdml_import".equals(country)
				         ||"njlg_import".equals(country) ||"sewd_import".equals(country)||"wdml_export".equals(country)
				         ||"njlg_export".equals(country)||"sewd_export".equals(country))
		{
			//英文
			response.getWriter().write(languageUtil.getStrValueByChina(countryValue));
		}else if("wkl_import".equals(country)){
			//乌克兰
			response.getWriter().write(languageUtil.getStrValueByUkrainian(countryValue));
		}else if("vnrl_import".equals(country)||"chile_export".equals(country)||"paraguay_import".equals(country)
				          ||"honduras_import".equals(country)||"ecuador_import".equals(country)||"spain".equals(country)
				          ||"peru_import".equals(country)||"uruguay_import".equals(country)||"argentina_import".equals(country)
				          ||"argentina_export".equals(country)||"uruguay_export".equals(country)||"paraguay_export".equals(country)
				          ||"colom_import".equals(country)||"colom_export".equals(country)||"peru_export".equals(country)
				          ||"ecuador_export".equals(country)||"chile_import".equals(country)||"vnrl_export".equals(country)
				          ||"honduras_export".equals(country)
				){
			//西班牙
			response.getWriter().write(languageUtil.getStrValueBySpain(countryValue));
		}else if("brazil_import".equals(country))
		{
			//葡萄牙
			response.getWriter().write(languageUtil.getStrValueByPortuguese(countryValue));
		}else if("yn_import".equals(country) || "yn_export".equals(country))
		{
			//越南语
			response.getWriter().write(languageUtil.getStrValueByVietnam(countryValue));
		}
		return null;
	}
}
