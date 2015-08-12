package com.njyb.auth.service.impl.cmp;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Lists;
import com.njyb.auth.dao.cmp.IOrderAuthDao;
import com.njyb.auth.service.CommonAuthService;
import com.njyb.gbdbase.model.admincenter.UserCountBuyerModel;
import com.njyb.gbdbase.model.admincenter.UserCountModel;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 按次用户操作Service
 * @author WangBo
 * 2015年6月24日
 * OrderCountCmp.java
 */
public class OrderCountCmp implements IOrderCountCmp {

	private static final Logger log = Logger.getLogger(CommonAuthService.class);
	
	// 用户权限 dao接口
	@Autowired
	private IOrderAuthDao orderCountDao;
	
	// 数据接口
	@Autowired
	private IDataSearchService dataSearchService;
	
	/**
	 * 次数更新
	 */
	@Override
	public int userCountBuUserId(Map<String, Object> paramsMap) {
		// 切换数据库
		int state = 0;
		if (null != paramsMap && !paramsMap.isEmpty()) {
			orderCountDao.userCountBuUserId(paramsMap);
			state = (int) paramsMap.get("state");
			String message = paramsMap.get("message").toString();
			System.out.println(message);
			log.info(message + "按次用户 更新次数成功!");
		}
		return state;
	}

	/**
	 * 根据条件获取按次用户的信息
	 */
	@Override
	public UserCountModel queryUserCountModel(Map<String, Object> paramMap) {
		UserCountModel userCountModel = null;
		try {
			// 切换数据库
			userCountModel = orderCountDao.userRightCount(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
		}
		return userCountModel;
	}
	
	/**
	 * 获得当前用户的购买的数据集
	 */
	@Override
	public <T> List<T> queryUserBuyerList(Map<String, Object> paramMap) {
		// 切换数据库
		List<T> reslutList = Lists.newArrayList();
		if (null != paramMap && !paramMap.isEmpty()) {
			//List<UserCountBuyerModel> userBuyerList = orderCountDao.queryCountIdList(paramMap);
			List<UserCountBuyerModel> userBuyerList =null;
			Map<String,List<Integer>> resultMap = getDataByParam(userBuyerList);
			for (Map.Entry<String, List<Integer>> entry: resultMap.entrySet()) {
				List<T> tempList = dataSearchService.getDataById(entry.getKey(), entry.getValue());
				reslutList.addAll(tempList);
			}
		}
		return reslutList;
	}
	
	/**
	 * 获取数据
	 * @param userBuyerList
	 * @return
	 */
	private Map<String,List<Integer>> getDataByParam(List<UserCountBuyerModel> userBuyerList){
		Map<String,List<Integer>> resultMap = null;
		List<Integer> intList = Lists.newArrayList();
		String countryEn = "";
		if (null != userBuyerList && !userBuyerList.isEmpty()) {
			for (UserCountBuyerModel countBuyModel : userBuyerList) {
				intList.add(countBuyModel.getCountryId());
				countryEn = countBuyModel.getCountryEn();
			}
		}
		resultMap = ImmutableBiMap.of(countryEn,intList);
 		return resultMap;
	}
	
	/**
	 * 添加按次客户次数表数据
	 */
	@Override
	public int addUserCount(Map<String, Object> paramMap) {
		int result = 0;
		try {
			double money = 0;
			String userId = "";
			if (paramMap.containsKey("money")) {
				money = Double.valueOf(paramMap.get("money").toString());
			}
			if (paramMap.containsKey("userId")) {
				userId = paramMap.get("userId").toString();
			}
			int countNum = (int) (money / CommonAuthService.LOOK_PRICE);
			Map<String,Object> addParamMap = ImmutableBiMap.of("userId",userId,"totalNum",countNum,"remaiNum",(Object)0);
			// 切换数据库
			result = orderCountDao.addUserCount(addParamMap);
		} catch (NumberFormatException e) {
			log.debug(e);
		}
		return result;
	}
}
