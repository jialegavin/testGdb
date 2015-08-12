package com.njyb.gbdbase.service.sellcenter.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.dao.sellcenter.IReceiverInfoDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.sellcenter.ReceiverInfoModel;
import com.njyb.gbdbase.service.sellcenter.IReceiverInfoSerice;
/**
 * 收货人信息
 * @author chenhu
 * 2015年5月7日
 */
@Service
public class ReceiverInfoSerice implements IReceiverInfoSerice {
    
	@Autowired
	private IReceiverInfoDao userInfoDao; 
	//日志
	private static final Logger log=Logger.getLogger(ReceiverInfoModel.class);
	@Override
	public ReceiverInfoModel queryUserInfoById(int userId) {
		ReceiverInfoModel receive=new ReceiverInfoModel();
		if(userId!=0){
			//获取用户的默认信息
			List<ReceiverInfoModel> userInfo=userInfoDao.queryUserInfoById(userId);
			if(userInfo==null||userInfo.size()==0){
				return null;
			}
			receive= userInfo.get(0);
		}
		return receive;
	}
	@Override
	public boolean operAddrInfo(ReceiverInfoModel info,UserModel user) {
	    boolean	result =false;
		try{
			//判断地址信息非空 
			if(info!=null&&user!=null){
				//设置用户ID
				info.setUserId(user.getUserId());
				//设置默认状态
				info.setStatus(true);
				//判断操作标志   true 修改 false 新增
				if(info.getOperFlag()){
					userInfoDao.updateInfo(info);
				}else{
					userInfoDao.addNewInfo(info);
				}
				result=true;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.toString());
		}
		
		return result;
	}

}
