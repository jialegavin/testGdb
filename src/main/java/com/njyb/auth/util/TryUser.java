package com.njyb.auth.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbase.model.admincenter.AuthorityModel;
import com.njyb.gbdbase.model.admincenter.UserModel;

/**
 * 试用用户
 * @author 章华才
 */
public class TryUser extends MainAuthoriy{

	
	
	/**
	 * 试用用户权限控制
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @param jsonObject
	 * @return
	 */
	public boolean authoriyController(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject){
		UserModel model = (UserModel) request.getSession().getAttribute("user");
		//判断试用用户，试用时间有没有到期
		if(AuthorityDateUtil.isDateEquals(model.getEndTime(),null)){
			jsonObject.put("error", "对不起,你试用的期限已到!");
			return false;
		}else{
			String startTime = "2012-01-01";
			String endTime = "2013-12-31";
			startDate = AuthorityDateUtil.fromatDate(startDate,country);
			endDate = AuthorityDateUtil.fromatDate(endDate,country);
			//判断日期格式  yyyy-mm-dd/yyyy-mm
			if(AuthorityDateUtil.validate(startDate)){
				if(DataUtil.parseDate(startDate, 3).getTime() >= DataUtil.parseDate(startTime, 3).getTime()
						&& DataUtil.parseDate(startDate, 3).getTime()<=DataUtil.parseDate(endTime, 3).getTime()
						&& DataUtil.parseDate(endDate, 3).getTime()>=DataUtil.parseDate(startTime, 3).getTime()
						&& DataUtil.parseDate(endDate, 3).getTime()<= DataUtil.parseDate(endTime, 3).getTime()){
				}else{
					jsonObject.put("error", "你试用数据检索日期只能在2012-01-01-2013-12-31 之间");
					return false;
				}
			}else{
				if(DataUtil.parseDate(startDate, 17).getTime()>=DataUtil.parseDate(startTime, 3).getTime()
						&& DataUtil.parseDate(startDate, 17).getTime()<=DataUtil.parseDate(endTime, 3).getTime()
						&& DataUtil.parseDate(endDate, 17).getTime()>=DataUtil.parseDate(startTime, 3).getTime()
						&& DataUtil.parseDate(endDate, 17).getTime()<= DataUtil.parseDate(endTime, 3).getTime()){
				}else{
					jsonObject.put("error", "你试用数据检索日期只能在2012-01-01-2013-12-31 之间");
					return false;
				}
			}
			
		}
		return true;
	}
	
	
}
