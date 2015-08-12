package com.njyb.gbdbase.controller.personalcenter.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.njyb.gbdbase.model.admincenter.UserConsumingRecordsModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.service.personalcenter.account.IUserAccountManagerService;

/**
 * 用户账户管理
 * @author XL
 * @date 2015-07-02
 *
 */
@Controller
@RequestMapping(value="/userAccount")
public class UserAccountManagerController {
	
	@Autowired
	private IUserAccountManagerService userAccountManagerService;

	@RequestMapping(value = "/searchMyAccount", method = RequestMethod.POST)
	public void searchMyAccount(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int curpage = Integer.parseInt((request.getParameter("page") == null || request.getParameter("page") == "0") ? "1" : request.getParameter("page"));
		int pagesize = Integer.parseInt((request.getParameter("rows") == null || request.getParameter("rows") == "0") ? "10" : request.getParameter("rows"));
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//获取用户
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		String sql = " and USERID =" + userModel.getUserId();
		List<UserConsumingRecordsModel> list = userAccountManagerService.getRecordsList(curpage, pagesize, sql);
		JSONObject json = new JSONObject();
		json.put("total", list.size());
		json.put("rows", list);
		out.print(json.toString());
	}
}
