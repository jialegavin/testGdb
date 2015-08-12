package com.njyb.gbdbase.service.personalcenter.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.auth.dao.cmp.IUserConsumingRecordsDao;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.model.admincenter.UserConsumingRecordsModel;

/**
 * 用户账户管理业务接口层实现层
 * @author XL
 * @date 2015-07-02
 */
@Service
public class UserAccountManagerService implements IUserAccountManagerService {

	@Autowired
	private IUserConsumingRecordsDao userConsumingRecordsDao;
	
	@Override
	public List<UserConsumingRecordsModel> getRecordsList(int curpage,
			int pagesize, String sql) {
		 // 切换数据源
		 
		 Map map=new HashMap();
		 int start=(curpage-1)*pagesize;
		 int end=pagesize;
		 map.put("curpage", start);
		 map.put("pagesize", end);
		 map.put("sqlparam", sql);
		 List<UserConsumingRecordsModel> list = userConsumingRecordsDao.getRecordsList(map);
		return list;
	}

}
