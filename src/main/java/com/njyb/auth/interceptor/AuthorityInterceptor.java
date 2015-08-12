package com.njyb.auth.interceptor;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.njyb.auth.service.authoritycontrol.component.MainAuthoriy;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.contrastreport.querybean.CommonSearchModel;
import com.njyb.gbdbase.service.contrastreport.contrast.component.CountryAllManagerComponent;


/**
 * 权限控制拦截器
 * @author 章华才
 */
public class AuthorityInterceptor implements HandlerInterceptor{

	@Autowired
	private AuthorityFieldModel authorityFieldModel;
	
	@Resource
	private CountryAllManagerComponent allManagerComponet;
	
	//日志记录
	private Logger log = Logger.getLogger(AuthorityInterceptor.class);
	
	 /** 
	  * 在执行controll之前拦截之模块控制
	  * 
      * 在业务处理器处理请求之前被调用 
      * 如果返回false 
      *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
      *  
      * 如果返回true 
      *    执行下一个拦截器,直到所有的拦截器都执行完毕 
      *    再执行被拦截的Controller 
      *    然后进入拦截器链, 
      *    从最后一个拦截器往回执行所有的postHandle() 
      *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
      */  
	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
		log.info("权限控制拦截");
		JSONObject jsonObject = new JSONObject();
		//获取当前请求的controller路径
		String controllerUrl = request.getRequestURI();
		boolean istrue = true;
		//贸易情报
		if(controllerUrl.contains("/gbdbas/getResultFieldList")){
			
			//拦截第二次请求 过滤查询条件
		    istrue =  MainAuthoriy.allAuthority(request, request.getParameter("queryValue"),  request.getParameter("queryKey"),  (String)request.getSession().getAttribute("countryZhName"), jsonObject, "1", authorityFieldModel.getAuthorityFieldMap());
		}
		//对比分析
		else if(controllerUrl.contains("/contrastre/contrastreAddOutController"))
		{
			//封装权限查询条件
			String[] query = allManagerComponet.queryKeyOrValue(request, new CommonSearchModel(), request.getParameter("countryName"));
			//拦截第二次请求 过滤查询条件
		    istrue =  MainAuthoriy.allAuthority(request, query[1],  query[0],  request.getParameter("countryName"), jsonObject, "1", authorityFieldModel.getAuthorityFieldMap());
		}
		if(istrue == false){
			jsonObject.put("total",0);
		    jsonObject.put("rows","");
			response.getWriter().write(jsonObject.toString());
			return false;
		}
		return true;
	}
	/**
	 * 在执行完controller之后执行
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作  
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	
	 /** 
	  * 拦截器退出前执行
	  * 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
