package com.njyb.gbdbase.controller.sellcenter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.ToJsonUtil;
import com.njyb.gbdbase.controller.common.PublicCommonController;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.sellcenter.ReceiverInfoModel;
import com.njyb.gbdbase.model.sellcenter.UserOrderDetModel;
import com.njyb.gbdbase.model.sellcenter.UserOrderModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.model.usermanagement.ViewModel;
import com.njyb.gbdbase.service.sellcenter.IOrderDetService;
import com.njyb.gbdbase.service.sellcenter.IOrderService;
import com.njyb.gbdbase.service.sellcenter.IReceiverInfoSerice;
import com.njyb.gbdbase.service.sellcenter.ISellCenterService;

/**
 * 定制中心
 * @author chenhu
 * 2015年5月5日
 */
@Controller
@RequestMapping("/sellCenter")
public class SellCenterController extends PublicCommonController  {
	/**
	 * 引入日志
	 */
	private static final Logger log=Logger.getLogger(SellCenterController.class);
	@Autowired
	private ISellCenterService sellCenterService;
	@Autowired
	private IReceiverInfoSerice  receiverInfoService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderDetService orderDetService;
    /**
     * 根据视图名称查询视图
     * @param request
     * @param response
     * @param viewName 视图名称
     */
	@RequestMapping(value="/queryViewByName")
    public 	void queryViewByName(HttpServletRequest request,HttpServletResponse response,String viewName){
		//TODO
		String lang="ch";
		 List<Map<String,String>>  country=sellCenterService.queryViewByName(viewName,lang);
    	 String json=ToJsonUtil.list2json(country);
    	 try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 
    }
	/**
	 * 添加新的订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/addNewOrder")
	public void  addNewOrder(HttpServletRequest request,HttpServletResponse response)
    {
			//获取seesion里的用户
			UserModel user=(UserModel)request.getSession().getAttribute("user");
			JSONObject json=JSONObject.fromObject(request.getParameter("data"));
			//获取前台的用户权限列表
            List<Map<String,Object>> orderDets=(List<Map<String, Object>>) json.getJSONArray("dets");
            //新增一个订单并添加订单明细
            String orderNo=sellCenterService.addNewOder(orderDets,user);
            request.setAttribute("orderNo", orderNo);
            //添加完跳转到订单详情页面
            JSONObject json1=new JSONObject();
	       	 json1.put("orderNo", orderNo);
	       	 try {
	   			response.getWriter().print(json1.toString());
	   		} catch (IOException e) {
	   			e.printStackTrace();
	   		}
    }
	/**
	 * 根据登录用户获取用户的地址信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryReceiverInfo")
    public void queryReceiverInfo(HttpServletRequest request,HttpServletResponse response){
    	//获取当前登录用户
    	UserModel user=(UserModel)request.getSession().getAttribute("user");
    	ReceiverInfoModel recInfo=receiverInfoService.queryUserInfoById(user.getUserId());
    	 JSONObject json=new JSONObject();
    	 json.put("recInfo", recInfo);
    	 try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	/**
	 * 新增或修改用户默认地址信息
	 * @param request
	 * @param response
	 * @param info
	 * @param flag
	 */
	@RequestMapping("/operAddrInfo")
	public void operAddrInfo(HttpServletRequest request,HttpServletResponse response,ReceiverInfoModel info){
		try{
			//获取登陆信息
			UserModel user=(UserModel)request.getSession().getAttribute("user");
			boolean result=receiverInfoService.operAddrInfo(info,user);
			JSONObject json=new JSONObject();
			//true 成功  false失败
			json.put("flag", result);
			response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	/**
	 * 根据订单编号获取单个订单信息
	 * @param request
	 * @param response
	 * @param orderNo 订单号
	 */
	@RequestMapping("/queryOrderInfoByNo")
	public void queryOrderInfoByNo(HttpServletRequest request,HttpServletResponse response,String orderNo){
		try{
			UserOrderModel order=orderService.queryOrderInfoByNo(orderNo);
			JSONObject json=new JSONObject();
			json.put("order", order);
			response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	/**
	 * 用户提交订单
	 * @param request
	 * @param response
	 * @param order
	 */
	@RequestMapping("/upOrderInfo")
	public void upOrderInfo(HttpServletRequest request,HttpServletResponse response,UserOrderModel order){
		try{
			//提交订单设置为已提交未支付状态
			order.setOrderStatus(IConstantUtil.ORDER_STATUS1);
			orderService.upOrderInfo(order);
		}catch(Exception e){
			log.debug(e.toString());
			e.printStackTrace();
			
		}
	}
	/**
	 * 根据当前登录的用户加载订单列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/queryOrders")
	public void queryOrders(HttpServletRequest request,HttpServletResponse response){
		QueryModel query=new QueryModel();
		PageBeanUtil pageUtil=this.getPageBean(request);
		//获取分页数据
		query.setCurPage(pageUtil.getPageIndex());
		query.setPageSize(pageUtil.getPageSize());
		UserModel user=(UserModel)request.getSession().getAttribute("user");
		//根据用户ID获取分页信息
		List<Map<String,String>> orders=orderService.queryEffectOrdersByUserId(user,query);
		//根据用户ID查询总数
		int count=orderService.querySumEffectOrders(user);
		 JSONObject json=this.getJsonObject(count, orders);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据订单Id删除订单
	 * @param request
	 * @param response
	 * @param orderId
	 */
	@RequestMapping("/deleteOrderByNo")
	public void deleteOrderByNo(HttpServletRequest request,HttpServletResponse response,String orderNo ){
		boolean flag=false;
		flag=orderService.deleteOrderByNo(orderNo);
		if(flag){
			flag=orderDetService.deleteOrderDetByNo(orderNo);
		}
      	 JSONObject json=new JSONObject();
      	json.put("flag", flag);
      	try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据订单号查询订单明细
	 * @param request
	 * @param response
	 * @param orderNo
	 */
	@RequestMapping("/queryOrderDetByNo")
	public void queryOrderDetByNo(HttpServletRequest request,HttpServletResponse response,String orderNo){
		QueryModel query=new QueryModel();
		PageBeanUtil pageUtil=this.getPageBean(request);
		//获取分页数据
		query.setCurPage(pageUtil.getPageIndex());
		query.setPageSize(pageUtil.getPageSize());
		query.setOrderNo(orderNo);
		//根据用户ID获取分页信息
		List<UserOrderDetModel> dets=orderDetService.queryOrderDetByNo(orderNo);
		//根据用户ID查询总数
		int count=orderDetService.querySumOrderDets(orderNo);
		 JSONObject json=this.getJsonObject(count, dets);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据 关联code、类型、和语言
	 * 查询国家级联菜单
	 * @param request
	 * @param response
	 * @param type（类型）
	 * @param real（关联）
	 */
	@RequestMapping("/queryCascdeMenu")
	public void queryCascdeMenu(HttpServletRequest request,HttpServletResponse response,String type,String rela){
		//TODO
		String lang="ch";
		List<Map<String,String>> viewList=sellCenterService.queryCascdeMenu(lang,type,rela);
		 //将国家列表转换成json格式传给前台
		String json=ToJsonUtil.list2json(viewList);
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	/**
	 * 根据 关联code、类型、和语言
	 * 查询国家级联菜单
	 * @param request
	 * @param response
	 * @param type（类型）
	 * @param real（关联）
	 */
	@RequestMapping("/queryCountryMenu")
	public void queryCountryMenu(HttpServletRequest request,HttpServletResponse response,String type,String code){
		//TODO
		String lang="ch";
		List<Map<String,String>> viewList=sellCenterService.queryCountryMenu(lang,type,code);
		 //将国家列表转换成json格式传给前台
		String json=ToJsonUtil.list2json(viewList);
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	/**
	 * 根据订单号模糊查询订单的详细信息
	 * @param request
	 * @param response
	 * @param orderNo 订单号
	 */
	@RequestMapping("/queryOrderByBlurNo")
	public void queryOrderByBlurNo(HttpServletRequest request,HttpServletResponse response,String orderNo){
		QueryModel query=new QueryModel();
		PageBeanUtil pageUtil=this.getPageBean(request);
		//获取分页数据
		query.setCurPage(pageUtil.getPageIndex());
		query.setPageSize(pageUtil.getPageSize());
		query.setOrderNo(orderNo);
		UserModel user=(UserModel)request.getSession().getAttribute("user");
		//如果用户不存在跳转到登录页面
		if(user!=null&&user.getUserId()!=0){
			query.setUserId(user.getUserId());
		}
		else{
			  try {
				response.getWriter().println("<script type='text/javascript'>"
						   + "location.href='/gbdbas/view/login/infobase/login.jsp'</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//根据用户ID获取分页信息
		List<Map<String,String>> orders=orderService.queryOrderByBlurNo(query);
		//根据用户ID查询总数
		int count=orderService.querySumOrderByBlurNo(query);
		 JSONObject json=this.getJsonObject(count, orders);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
