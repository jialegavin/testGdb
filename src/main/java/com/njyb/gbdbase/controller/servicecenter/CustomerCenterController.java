package com.njyb.gbdbase.controller.servicecenter;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.export.ExportDataUtil;
import com.njyb.gbdbas.util.export.ExportExcelUtil;
import com.njyb.gbdbase.model.contrastreport.customercenter.CustomerCenterModel;
import com.njyb.gbdbase.service.contrastreport.contrast.component.CountryAllManagerComponent;
import com.njyb.gbdbase.service.contrastreport.customercenter.ICustomerCenterService;

/**
 * 客服中心controller
 * @author 章华才
 * 2015-04-28
 */
@Controller
@RequestMapping(value="customer")
public class CustomerCenterController {

	@Autowired
	private ICustomerCenterService customerService;
	
	@Autowired
	private CountryAllManagerComponent allManagerComponet;
	/**
	 * 新增客服留言
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="addCustomer")
	public String addCustomer(HttpServletRequest request,HttpServletResponse response,CustomerCenterModel model){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String dtStr = sdf.format(now);
		model.setDate(dtStr);
		customerService.addCentent(model);
		return null;
	}
	
	
	/**
	 * 查询所有留言信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="queryCentent")
	public String queryCentent(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//实例化分页工具
		PageBeanUtil beanUtil = allManagerComponet.getPageBeanUtil(request,"10");
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		List<CustomerCenterModel> list = customerService.queryCentents();
		jsonObject.put("total", list.size());
		jsonObject.put("rows", allManagerComponet.pageList(list, beanUtil));
		writer.write(jsonObject.toString());
		return null;
	}
	
	
	/**
	 * 根据id导出留言信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="queryCententById")
	public void queryCententByid(HttpServletRequest request,HttpServletResponse response,String id) throws IOException{
		//实例化分页工具
		String[] ids = id.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for (String str : ids) {
			idList.add(Integer.parseInt(str));
		}
		List<CustomerCenterModel> list = customerService.queryCententByid(idList);
		List<String[]> data = ExportDataUtil.getList(list, new String[]{"name","tel","centent","date"});
		String [] field = new String[]{"姓名","电话","留言内容","日期"};
		ExportExcelUtil.exportExcel("用户留言信息表", data, field, new String[]{}, new String[]{}, request, response);
		System.out.println(list);
	}
	
	/**
	 * 删除留言信息根据Id
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteCententById")
	public String deleteCententByid(HttpServletRequest request,HttpServletResponse response,String id){
		String[] ids = id.split(",");
		List<Integer> ls = new ArrayList<Integer>();
		for (String str : ids) {
			ls.add(Integer.parseInt(str));
		}
		//执行删除
		customerService.deleteCentent(ls);
		return null;
	}
}
