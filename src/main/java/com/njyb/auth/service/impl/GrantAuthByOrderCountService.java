package com.njyb.auth.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.auth.dao.cmp.IUserConsumingRecordsDao;
import com.njyb.auth.service.CommonAuthService;
import com.njyb.auth.service.impl.cmp.IOrderCountCmp;
import com.njyb.auth.service.intface.IGrantAuthService;
import com.njyb.gbdbase.model.admincenter.UserConsumingRecordsModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
/**
 * 根据订购的次数进行授权 需要注意以下几个方面
 * 1 查询条件不做任何限制
 * 2 查询是按此收费,用户点击一次 累加一次,当本次查询完毕的时候,进行次数累加 费用累加
 * 3 重复查看不收费
 * 4 用户可以下载自己已经查看过的数据,没有查看过的数据不能下载  (需求更改,不需要下载!)
 * 5 晚上某个时间发送一份邮件,内容包含看了多少次,还剩多少次，花了多少钱,还剩多少钱
 * @author jiahp
 * 注意:这里service的注入不能使用默认的注入方式 ,需要手动指定一个名称,否则spring会抛出异常的
 */
@Service("auth_count")
public class GrantAuthByOrderCountService extends CommonAuthService implements IGrantAuthService {

	@Autowired
	private IOrderCountCmp orderCountCmp;
	
	@Override
	public <T> T executeGrantAuth(HttpServletRequest request,
			Map<String, Object> paramMap) {
		//获取用户
		UserModel userModel = (UserModel) paramMap.get("userModle");
		double totalMoney = (double) paramMap.get("totalMoney");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userModel.getUserId());
		map.put("money",totalMoney);
		orderCountCmp.addUserCount(map);
		return null;
	}

}
