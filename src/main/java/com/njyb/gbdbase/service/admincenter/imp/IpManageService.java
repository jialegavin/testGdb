package com.njyb.gbdbase.service.admincenter.imp;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.njyb.gbdbas.util.IPAddressUtil;
import com.njyb.gbdbase.dao.admincenter.IUserDao;
import com.njyb.gbdbase.dao.admincenter.IUserIPDao;
import com.njyb.gbdbase.model.admincenter.UserIPModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
import com.njyb.gbdbase.service.admincenter.IIpManageService;

/**
 * 用户IP管理服务
 * @author chenhu
 * 2015年3月30日
 */
public class IpManageService implements IIpManageService {
	@Autowired
	private IUserDao iUserDao;
	@Autowired
	private IUserIPDao iUserIPDao;
	@Override
	public List<UserIPModel> queryUserIPsForPaging(QueryModel query) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int queryCountUserIPs(QueryModel query) {
		// TODO Auto-generated method stub
		return 0;
	}
	  
}
