package com.njyb.gbdbase.dao.sellcenter;

import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.sellcenter.ReceiverInfoModel;

/**
 * 定制中心用户信息DAO层
 * @author chenhu
 * 2015年5月7日
 */
@MyBatisReposity
public interface IReceiverInfoDao {
    /**
     * 根据用户ID查询定制中心的用户信息
     * @param userId
     * @return
     */
	List<ReceiverInfoModel> queryUserInfoById(int userId);
    /**
     * 新增用户地址信息
     * @param info
     */
	void addNewInfo(ReceiverInfoModel info);
    /**
     * 修改用户地址信息
     * @param info
     */
	void updateInfo(ReceiverInfoModel info);

}
