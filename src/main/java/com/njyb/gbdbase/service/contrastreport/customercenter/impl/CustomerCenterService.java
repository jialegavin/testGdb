package com.njyb.gbdbase.service.contrastreport.customercenter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbase.dao.contrastreport.customercenter.ICustomerCenterDao;
import com.njyb.gbdbase.model.contrastreport.customercenter.CustomerCenterModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.service.contrastreport.customercenter.ICustomerCenterService;

/**
 * 用户留言service实现类
 * @author 章华才
 *
 */
@Service
public class CustomerCenterService implements ICustomerCenterService{

	@Autowired
	private ICustomerCenterDao customerDao;
	
	@Override
	public void addCentent(CustomerCenterModel model) {
		customerDao.insertCentent(model);
	}

	@Override
	public List<CustomerCenterModel> queryCentents() {
		return customerDao.queryCentents();
	}

	@Override
	public void deleteCentent(List<Integer> ids) {
		SqlModel model = new SqlModel();
		model.setSql(getMyBatiesSql(ids));
		customerDao.deleteCentent(model);
	}

	@Override
	public List<CustomerCenterModel> queryCententByid(List<Integer> ids) {
		SqlModel model = new SqlModel();
		model.setSql(getMyBatiesSql(ids));
		List<CustomerCenterModel> list = customerDao.queryCententByid(model);
		return list;
	}

	
	/**
	 * 拼接每个国家的搜索模块sql
	 * @param idList
	 * @auther honghao
	 * @return
	 */
	public String getMyBatiesSql(List<Integer> idList) {
		if (idList ==null || idList.size() == 0) 
		{
			idList.add(-1);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (Integer i : idList) {
			sb.append(i + ",");
		}
		return sb.substring(0, sb.lastIndexOf(",")) + ")";
	}
}
