package com.njyb.gbdbase.service.admincenter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.admincenter.UserIPModel;
import com.njyb.gbdbase.model.usermanagement.QueryModel;
/**
 * 用户IP管理服务
 * @author chenhu
 * 2015年3月20日
 */
@Service
public interface IIpManageService {
	 /**
	  * 分页查询用户登陆的IP地址
	  * @param query
	  * @return
	  */
	  List<UserIPModel> queryUserIPsForPaging(QueryModel query);
      /**
       * 
       * @param query
       * @return
       */
	  int  queryCountUserIPs(QueryModel query);
}
