package com.njyb.auth.util;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbase.model.login.CommonLogoModel;

/**
 * 获取网站公共信息工具类
 * @author xl
 *
 */
public class GetWebInfoUtil {

	/**
	 * 获取网站公共信息
	 * @param request
	 * @param domain 用户信息注册时的域名（可为null）
	 * @return
	 */
	public static CommonLogoModel getCommonWebInfo(HttpServletRequest request,String domain){
 		//获取路径
 		String path = request.getContextPath();
 		//初始化配置文件
 		new LoadPropertiesUtil().init(request,IConstantUtil.WEBINFO);
 		//判断是进入的哪个网站
 		String webName = null;
 		//如果用户信息内的域名为null，则根据域名判断获取公用信息
 		if(null == domain){
 			//当前域名
 			String url=GetDoMainName.getDoMain(request);
			if (url.endsWith("www.ybdb.net") || url.endsWith("ybdb.net")){
				webName = IConstantUtil.WEBINFO_INFOBASE;
			}else if (url.endsWith("online.trade-easy.com.cn") || url.endsWith("trade-easy.com.cn") || url.contains("trade-easy")  || url.endsWith("http://localhost:8080")) {
				webName = IConstantUtil.WEBINFO_TRADE_EASY;
			}
 		} else {
 			if (domain.endsWith("www.ybdb.net") || domain.endsWith("ybdb.net")){
				webName = IConstantUtil.WEBINFO_INFOBASE;
			}else if (domain.endsWith("online.trade-easy.com.cn") || domain.endsWith("trade-easy.com.cn") || domain.contains("trade-easy") || domain.endsWith("http://localhost:8080")) {
				webName = IConstantUtil.WEBINFO_TRADE_EASY;
			}
 		}
		//初始化模型
		CommonLogoModel c = new CommonLogoModel();
		c.setComanyName(LoadPropertiesUtil.getValue(webName + "_comanyName"));
		c.setDomain(LoadPropertiesUtil.getValue(webName + "_domain"));
		c.setTel(LoadPropertiesUtil.getValue(webName + "_tel"));
		c.setCompanyEmail(LoadPropertiesUtil.getValue(webName+ "_comanyEmail"));
		c.setLogoUrl(path+LoadPropertiesUtil.getValue(webName + "_logoUrl"));
		c.setIntoLogoUrl(path+LoadPropertiesUtil.getValue(webName + "_intoLogoUrl"));
		c.setForwardJsp(LoadPropertiesUtil.getValue(webName + "_forwardJsp"));
		c.setRecordNumber(LoadPropertiesUtil.getValue(webName + "_recordNumber"));
		c.setCustomImgUrl(path+LoadPropertiesUtil.getValue(webName + "_customImgUrl"));
		c.setEmailPwd(LoadPropertiesUtil.getValue(webName + "_emailPwd" ));
		c.setFax(LoadPropertiesUtil.getValue(webName + "_fax" ));
		c.setAddress(LoadPropertiesUtil.getValue(webName + "_address" ));
		return c;
	}
}
