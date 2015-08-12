package com.njyb.auth.service.impl.cmp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbase.model.admincenter.AuthorityModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.alldb.competitor.RightLibrarySearchModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;

/**
 * 全库对用户权限控制接口
 * @author WangBo
 * 2015年6月28日
 * IUserRightAllDBService.java
 */
@Service
public class UserRightAllDBService implements IUserRightAllDBService {

	/**
	 * 市场分析报告查询Model {共用}
	 */
	@Override
	public RightLibrarySearchModel ControlRightSearchModel(HttpServletRequest request,
			RightLibrarySearchModel rightSearchModel) {
//		List<AuthorityModel> anthorityList = (List<AuthorityModel>) request.getAttribute("authorityInfo");
//		// 如果用户为使用用户,时间限制2011-2012
//		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
//		if (userModel.getUserDesc().equals("试用客户")) {
//			// 时间限制2011-2012
//		} else if (userModel.getUserDesc().equals("正式客户")) {
//			
//		}
		return null;
	}
	
	/**
	 * 动态更新日期开始时间和结束时间
	 * @param rightSearchModel
	 * @param userModel
	 * @return
	 */
	public List<String> getValue(HttpServletRequest request,List<String> key,List<String> value,String country,String keys,int index) {
		@SuppressWarnings("unchecked")
		List<AuthorityModel> list = (List<AuthorityModel>) request.getSession().getAttribute("authorityInfo");
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		String [] searchDate = null;
		if (null != list && !list.isEmpty() || null != userModel) {
			if (keys.equals(RightLibraryConstant.DATE)) {
				for (int i =0; i < list.size(); i++) {
					String authBeingDate = getBuyDate(list.get(i).getStarttime());
					String authEndDate = getBuyDate(list.get(i).getEndtime());
					long userAuthBiegnDate = DataUtil.parseDate(authBeingDate, 3).getTime();	// 购买时间
					long userAuthEndDate = DataUtil.parseDate(authEndDate,3).getTime();			// 结束时间
					searchDate = value.get(index).split(",");
					long beginDate = DataUtil.parseDate(searchDate[0], 3).getTime();	// 查询开始时间
					long endDate = DataUtil.parseDate(searchDate[1], 3).getTime();		// 查询结束时间
					if (userAuthBiegnDate > beginDate) {					// 购买时间大于 查询时间
						searchDate[0] = list.get(i).getStarttime();			// 赋值为购买时间
					}
					if (endDate > userAuthEndDate) {
						searchDate[0] = list.get(i).getEndtime();			// 赋值为购买时间
					}
				}
				System.out.println(searchDate[0]+","+searchDate[1]);
				value.set(index, searchDate[0]+","+searchDate[1]);
			}
		}
		return value;
	}
	
	/**
	 * 时间格式统一转换接口
	 * @param beginDate
	 * @return
	 */
	private String getBuyDate(String beginDate) {
		String result = "";
		if (!Strings.isNullOrEmpty(beginDate)) {
			String [] dateArray = beginDate.split("-");
			result = dateArray.length == 2 ? beginDate + "-01" : beginDate;
		}
		return result;
	}
}