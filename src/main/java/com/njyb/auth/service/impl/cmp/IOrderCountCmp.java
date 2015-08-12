package com.njyb.auth.service.impl.cmp;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.admincenter.UserCountModel;

/**
 * 根据用户订购次数进行授权提供业务组件需要用到的方法
 * @author jiahp
 *
 */
public interface IOrderCountCmp {
	
	/**
	 * 次数更新
	 * @param paramsMap
	 * @return
	 */
	public int userCountBuUserId(Map<String,Object> paramsMap);
	
	/**
	 * 根据条件获取按次用户的信息
	 * @param paramMap : 1. userId : 用户id  2. type : 用户权限 {按次用户}
	 * @return
	 */
	public UserCountModel queryUserCountModel(Map<String,Object> paramMap);
	
	/**
	 * 获得当前用户的购买的数据集
	 * @param paramMap : 1. userId : 用户Id 2. type : 用户角色  3. country_en : 英文国家名称  
	 * @return
	 */
	public <T> List<T> queryUserBuyerList(Map<String,Object> paramMap);
	
	/**
	 * 添加按次客户次数表数据
	 * @param paramMap 1. userId : 用户id  2. money : 金额
	 * @return 1. success 2. error
	 */
	public int addUserCount(Map<String,Object> paramMap);
}
