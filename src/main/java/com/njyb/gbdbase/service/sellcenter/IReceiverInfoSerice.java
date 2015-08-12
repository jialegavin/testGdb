package com.njyb.gbdbase.service.sellcenter;

import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.sellcenter.ReceiverInfoModel;

/**
 * 用户信息服务层
 * @author chenhu
 * 2015年5月7日
 */
public interface IReceiverInfoSerice {
    /**
     * 获取用户的默认地址信息
     * @param uid
     * @return
     */
	ReceiverInfoModel queryUserInfoById(int uid);
    /**
     * 新增或修改用户信息
     * @param info
     * @param flag
     * @return
     */
	boolean operAddrInfo(ReceiverInfoModel info,UserModel user);
}
