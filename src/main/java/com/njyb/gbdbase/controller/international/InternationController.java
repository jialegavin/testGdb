package com.njyb.gbdbase.controller.international;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 国际化控制层controller类接口
 * @author honghao
 * 2015年3月4号
 */
@Controller
@RequestMapping("/language")
public class InternationController{

	  @RequestMapping("/changelanguage")
	     public void changeLocale(String language,HttpServletRequest request,HttpServletResponse response)
	    {
	        request.getSession().setAttribute("language", language);
	    }
}
