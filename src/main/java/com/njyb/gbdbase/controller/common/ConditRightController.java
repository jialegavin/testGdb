package com.njyb.gbdbase.controller.common;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.auth.service.authoritycontrol.component.AuthorityComponentUtil;
import com.njyb.auth.service.authoritycontrol.component.CommonConstantUtil;
import com.njyb.auth.service.authoritycontrol.component.MainAuthoriy;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
/**
 * 子模块控制
 * @author 章华才
 */
@Controller
public class ConditRightController {

	@Autowired
	private AuthorityFieldModel authorityFieldModel;
	
	
	/**
	 * 控制模块访问
	 * @param request
	 * @param response
	 * @param url
	 * @return null
	 * @throws Exception 
	 */
	@RequestMapping(value = "/ConditRightController")
	public String conditRightModule(HttpServletRequest request,HttpServletResponse response,String url,String country) throws Exception{
		JSONObject jsonObject = new JSONObject();
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		//获取当前请求的controller路径
		String urlPath = (String) authorityFieldModel.getAuthorityFieldMap().get(user.getUserDesc());
		//针对对比分析控制按次用户不能访问
		if(user.getUserDesc().equals("按次用户")){
			if(urlPath.contains(url)){
				//提示
				CommonConstantUtil.jsonObject(user,jsonObject);
			}
		}else{
			if(urlPath.contains(url)){
				//提示
				CommonConstantUtil.jsonObject(user,jsonObject);
			}else if(urlPath.equals("superUserDownload")){
				//超级用户可以下载全部
			}else{
				
				System.out.println("能否下载:"+authorityFieldModel.getAuthorityFieldMap().get("give"));
//				String give = null;
//				//加上同步锁
//				synchronized (this) {
//					give =  (String) AuthorityFieldModel.getAuthorityFieldMap().get("give");
//				}
				//针对赠送的国家没有下载权限
//				if(give!=null){
					if(!AuthorityComponentUtil.judgeIsDownload((List<ConditionRightModel>)request.getSession().getAttribute("authorityInfo"), country)){
						String notDownLoad = (String) authorityFieldModel.getAuthorityFieldMap().get("notGiveDownLoad");
						if(notDownLoad.contains(url)){
							//提示
							CommonConstantUtil.jsonObject(user,jsonObject);
						}
					}
//				}
				
			}
		}
		jsonObject.put("user_desc", user.getUserDesc());
		response.getWriter().write(jsonObject.toString());
		return null;
	}
	
	
	
	
	/**
	 * 控制模块访问
	 * @param request
	 * @param response
	 * @param url
	 * @return null
	 * @throws Exception 
	 */
	@RequestMapping(value = "/tryUserCountry")
	public String tryUserCountry(HttpServletRequest request,HttpServletResponse response,String url,String country) throws Exception{
		JSONObject jsonObject = new JSONObject();
		//拼接json数据
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"中国\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"阿根廷\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"巴拉圭\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"智利\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"厄瓜多尔\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"委内瑞拉\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"巴拿马\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"乌拉圭\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"哥伦比亚\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"秘鲁\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"玻利维亚\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"巴西\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"尼加拉瓜\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"萨尔瓦多\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"洪都拉斯\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"墨西哥\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"美国\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"危地马拉\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"哥斯达黎加\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"俄罗斯\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"乌克兰\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"英国\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"印度\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"越南\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"巴基斯坦\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"},");
		sb.append("{\"byHsCode\":\"★★★★★★\",\"byProductDesc\":\"★★★★★★\",\"byCountry\":\"韩国\",\"startTime\":\"2012-01\",\"endTime\":\"2013-12\"}");
		sb.append("]");
		jsonObject.put("total", 26);
		jsonObject.put("rows", sb.toString());
		response.getWriter().write(jsonObject.toString());
		return null;
	}
}
