package com.njyb.auth.dao.cmp;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.admincenter.UserCountBuyerModel;
import com.njyb.gbdbase.model.admincenter.UserCountModel;

@MyBatisReposity
public interface IOrderAuthDao {
	
	/**
	 * 次数更新
	 * @param paramsMap
	 * @return
	 */
	public Map<String,Object> userCountBuUserId(Map<String,Object> paramsMap);
	
	/**
	 * 获取 按次用户 的 信息
	 * @param paramsMap
	 * @return
	 */
	public List<UserCountModel> queryUserConsuming(Map<String,Object> paramsMap);
	
	/**
	 * 获取当前用户{按次用户}的信息
	 * @param paramMap
	 * @return
	 */
	public UserCountModel userRightCount(Map<String,Object> paramMap);
	
	/**
	 * 通过参数 获取 当前用户 购买的id数据
	 * @param paramMap :
	 * @return
	 */
	public List<UserCountBuyerModel> queryCountIdList(Map<String,Object> paramMap);
	
	/**
	 * 添加按次客户次数表数据
	 * @param paramMap 1. userId : 用户id  2. money : 金额
	 * @return 1. success 2. error
	 */
	public int addUserCount(Map<String,Object> paramMap);
}
