package com.njyb.auth.service.authoritycontrol.component;

import java.io.Serializable;

import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.admincenter.UserModel;

import net.sf.json.JSONObject;

/**
 * 常量
 * @author 章华才
 */
public class CommonConstantUtil implements Serializable{

	
	
	/**
	 * 对不起,你试用数据检索日期只能在2012-01-2013-12 之间!详情请致电
	 * 对不起,你试用的期限已到!详情请致电
	 * 对不起,你查询的国家不在您购买的权限范围内!详情请致电
	 * 对不起,产品描述必填!详情请致电
	 * 对不起,海关编码必填!详情请致电
	 * 查询条件不在您的权限范围内!请修改查询条件，或详情致电
	 * 对不起,你没有访问权限!详情请致电
	 * 对不起,你赠送的国外数据期限已到!详情请致电
	 * 提示没有下载权限
	 */
	public static String PROMPT_DOWNLOAD = "你没有可下载权限!详情请致电<br/>";
	
	
	/**
	 * 提示没有访问权限
	 */
	public static String PROMPT_AUTHORITY = "你没有访问权限!详情请致电:<br>";
	
	
	/**
	 * 提示赠送国外数据3个数据期限已到期
	 */
	public static String PROMPT_GIVEDATA = "你赠送的国外数据期限已到!详情请致电:";
	
	
	
	/**
	 * 提示数据检索日期
	 */
	public static String PROMPT_DATE = "查询条件不在您的权限范围内!请修改查询条件，或详情致电:";
	
	/**
	 * 提示海关编码不在权限范围内
	 */
	public static String PROMPT_HSCODE = "你查询的编码不在你的权限范围内!详情请致电:";
	
	
	/**
	 * 提示海关编码必填
	 */
	public static String PROMPT_ISNULL_HSCODE = "海关编码必填!详情请致电:<br>"; 
	
	
	/**
	 * 提示产品描述不在范围之内
	 */
	public static String PROMPT_DESC = "你查询的产品描述不在你的权限范围内!详情请致电:";
	
	
	/**
	 * 提示产品描述必填
	 */
	public static String PROMPT_ISNULL_DESC = "产品描述必填!详情请致电:";
	
	
	/**
	 * 提示国家不在权限范围内
	 */
	public static String PROMPT_COUNTRY = "你查询的国家不在您购买的权限范围内!详情请致电:";
	
	
	
	/**
	 * 提示试用用户账号期限已到
	 */
	public static String PROMPT_TRYDATE = "你试用的期限已到!详情请致电:<br>";
	
	
	/**
	 * 提示试用用户检索日期
	 */
	public static String PROMPT_TRYQUERYDATA  = "你试用数据检索日期只能在2012-01-2013-12 之间!详情请致电:";
	

	/**
	 * 封装json提示
	 * @param jsonObject
	 */
	public static void jsonObject(UserModel model,JSONObject jsonObject){
		//特殊用户处理
		if(!AuthorityFieldModel.getAuthorityFieldMap().get("special_user").toString().contains(model.getLoginName())){
			jsonObject.put("error", CommonConstantUtil.PROMPT_AUTHORITY+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
		}
		jsonObject.put("total", 0);
		jsonObject.put("rows", "");
	}
}
