package com.njyb.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Request;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.auth.service.CommonAuthService;
import com.njyb.auth.service.intface.IGrantAuthCommonService;
import com.njyb.auth.util.GetWebInfoUtil;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.LoadPropertiesUtil;
import com.njyb.gbdbas.util.MD5Util;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.njyb.gbdbase.dao.usermanagement.IRightDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.login.CommonLogoModel;
import com.njyb.gbdbase.model.personalcenter.email.MailModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.login.ILoginService;
import com.njyb.gbdbase.service.personalcenter.email.IEmailService;

/**
 * 授权公用接口实现层
 * @author XL
 * @date 2015-06-26
 */
@Service
public class GrantAuthCommonService implements IGrantAuthCommonService {
	@Autowired
	private IRightDao rightDao; 
	@Autowired
	private ILoginService loginService;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IEmailService iEamilService;

	@Override
	public void addRight(Map<String, Object> paramMap) {
		//切换数据库
		//获取权限集合
		List<ConditionRightModel> list = (List<ConditionRightModel>) paramMap.get("list");
		//获取用户
		UserModel userModle = (UserModel) paramMap.get("userModle");
		//获取权限标识
		String rightDesc = (String) paramMap.get("rightDesc");
		for(ConditionRightModel c:list){
			if(!"".equals(c.getByHsCode()) && null != c.getByHsCode()){
				if(c.getByHsCode().contains(",")){
					String [] hscodes = c.getByHsCode().split(",");
					for(String h : hscodes){
						ConditionRightModel con = new ConditionRightModel();
						con.setRid(c.getRid());
						con.setByHsCode(h);
						con.setByProductDesc(c.getByProductDesc());
						con.setByCountry(c.getByCountry());
						con.setStartTime(c.getStartTime());
						con.setEndTime(c.getEndTime());
						con.setiExportType(c.getiExportType());
						con.setOpenHistoryData(c.isOpenHistoryData());
						con.setHistoryStartTime(c.getHistoryStartTime());
						con.setHistoryEndTime(c.getHistoryEndTime());
						con.setTreeStartTime(c.getTreeStartTime());
						con.setTreeEndTime(c.getTreeEndTime());
						con.setUserId(userModle.getUserId());
						con.setRightType(rightDesc);
						rightDao.addUserRight(con);
					}
				}else{
					c.setUserId(userModle.getUserId());
					c.setRightType(rightDesc);
					rightDao.addUserRight(c);
				}
			} else {
				c.setUserId(userModle.getUserId());
				c.setRightType(rightDesc);
				rightDao.addUserRight(c);
			}
			
		}
	}

	@Override
	public Map<String, Object> commonUserGrantAuth(Map<String, String> paramMap) {
		//用户选择的权限json数组
		String jsonArray = paramMap.get("jsonArray");
		//用户登录名称
		String loginName = paramMap.get("loginName");
		Map<String,Object> map = new HashMap<String, Object>();
		if(!"".equals(jsonArray) && null != jsonArray){
			//转换json数组
			List<ConditionRightModel> list = JSONArray.toList(JSONArray.fromObject(jsonArray), new ConditionRightModel(), new JsonConfig());
			map.put("list", list);
		}
		//根据loginName获取整个用户的信息
		//查询用户
		if(!"".equals(loginName) && null != loginName){
			UserModel userModle=loginService.queryUserByName(loginName);
			map.put("userModle", userModle);
		}
		return map;
	}
	
	@Override
	public void grantAuthBeforeOper(HttpServletRequest request,Map<String, Object> paramMap){
		//切换数据库
		//获取用户
		UserModel userModle = (UserModel) paramMap.get("userModle");
		//获取授权方式
		String auth_type = (String) paramMap.get("auth_type");
		//用户角色
		String userDesc = null;
		if(auth_type.equals(IConstantUtil.AUTH_COUNTRY)||
				auth_type.equals(IConstantUtil.AUTH_HSCODE)||
				auth_type.equals(IConstantUtil.AUTH_DESC)){
			userDesc = CommonAuthService.VIP_USER;
		}else if(auth_type.equals(IConstantUtil.AUTH_COUNT)){
			userDesc = CommonAuthService.COUNT_USER;
		}
		//获取公用信息
		CommonLogoModel c = (CommonLogoModel) request.getSession().getAttribute("commonInfo");
		if(null ==userModle.getUserDesc() ||userModle.getUserDesc().equals("")){
			QueryModel  query=new QueryModel();
			query.setUpdateSql(" loginPassword = '" + MD5Util.encodeByMD5(userModle.getLoginPassword()) + "',userdesc = '" + userDesc+"',ISACTIVATED=1"+"\n");
			query.setWhereSql(" userid="+userModle.getUserId()+"\n");
			//修改用户描述，修改用户密码MD5加密
			userDao.updateUserBySql(query);
			//发送邮件
			//发送邮件
			Map<String,Object> emailParamMap = new HashMap<String, Object>();
			emailParamMap.put("message", "您已授权成功！");
			emailParamMap.put("content_uname", "您的用户名为："+userModle.getLoginName());
			emailParamMap.put("content_upwd", "您的初始密码为："+userModle.getLoginPassword());
			emailParamMap.put("content_tip", "为了保障您账号的安全性，请您登录平台及时修改密码。");
			emailParamMap.put("companyTitle", c.getComanyName().equals("南京英蓓信息技术有限公司")?"英蓓":"趣易");
			emailParamMap.put("onlineTel",c.getComanyName().equals("南京英蓓信息技术有限公司")?c.getTel():c.getTel()+"/400-1816-008");
			emailParamMap.put("domain", c.getDomain());
			emailParamMap.put("fax", c.getFax());
			emailParamMap.put("address", c.getAddress());
			sendEmail(userModle, emailParamMap, request);
		}else if(userModle.getUserDesc().equals(IConstantUtil.TRIAL_USER)){
			//修改用户描述
			 QueryModel query=new QueryModel();
			 query.setUserDesc(userDesc);
			 query.setUserId(userModle.getUserId());
			//发送授权成功邮件，不需要用户修改密码
			userDao.updateUserDescById(query);
			//发送邮件
			Map<String,Object> emailParamMap = new HashMap<String, Object>();
			emailParamMap.put("message", "您已授权成功！");
			emailParamMap.put("companyTitle", c.getComanyName().equals("南京英蓓信息技术有限公司")?"英蓓":"趣易");
			emailParamMap.put("onlineTel", c.getComanyName().equals("南京英蓓信息技术有限公司")?c.getTel():c.getTel()+"/400-1816-008");
			emailParamMap.put("domain", c.getDomain());
			emailParamMap.put("fax", c.getFax());
			emailParamMap.put("address", c.getAddress());
			sendEmail(userModle, emailParamMap, request);
		}
	}
	
	/**
	 * 发送邮件
	 * @param userModle
	 * @param emailParamMap
	 */
	private void sendEmail(UserModel userModle,Map<String,Object> emailParamMap,HttpServletRequest request){
		CommonLogoModel c = GetWebInfoUtil.getCommonWebInfo(request, userModle.getDomain());
		// 生成模板
		emailParamMap.put("companyName", c.getComanyName());
		emailParamMap.put("sendTime", DataUtil.parseDate(new Date(),7));
		MailModel mailModel = new MailModel();
		mailModel.setSubject("用户授权");
		mailModel.setToAddreress(userModle.getEmail());		
		mailModel.setFromAddress(c.getCompanyEmail());
		//发送邮件
		iEamilService.sendAttachMail(mailModel, emailParamMap, "sendRightTipTemplate.html",c);
	}

	@Override
	public List<ConditionRightModel> getCountryDefaultInfo(String auth_type, HttpServletRequest request) {
		//查询所有国家的最新更新时间配置文件
		new LoadPropertiesUtil().init(request,IConstantUtil.COUNTRYUPDATETIME);
		//根据授权方式展示不同的国家
		String[] countrys = LoadPropertiesUtil.getValue(auth_type+"_country").trim().split(",");
		//授权所需的一些基本国家信息
		List<ConditionRightModel> conditionRightList = new LinkedList<ConditionRightModel>();
		for(String c:countrys){
			ConditionRightModel conditionRightModel = new ConditionRightModel();
			//贸易类型
			String iexportType = null;
			//国家
			String country = null;
			if(c.contains("进口")){
				country = c.substring(0,c.indexOf('进'));
				iexportType = c.substring(c.indexOf("进"), c.length());
			}else if(c.contains("出口")){
				country = c.substring(0,c.indexOf('出'));
				iexportType = c.substring(c.indexOf("出"), c.length());
			}
			conditionRightModel.setiExportType(iexportType);
			conditionRightModel.setByCountry(country);
			String[] dataTime = LoadPropertiesUtil.getValue(c).trim().split(":");
			//插入开始日期
			conditionRightModel.setStartTime(dataTime[0]);
			conditionRightList.add(conditionRightModel);
		}
		return conditionRightList;
	}}
