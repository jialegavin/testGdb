package com.njyb.gbdbase.service.sellcenter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.ViewModel;

/**
 * 定制中心业务层
 * @author chenhu
 * 2015年5月5日
 */
public interface ISellCenterService {
    /**
     * 根据视图名称查询视图
     * @return
     */
	 List<Map<String,String>> queryViewByName(String type,String lang);
    /**
     * 添加新订单
     * @param orderDets
     * @param uid
     */
	String addNewOder(List<Map<String, Object>> orderDets, UserModel user);
	/**
	 * 根据订单号授权
	 * @param orderNo
	 * @return
	 */
	boolean conferRight(String orderNo);
	/**
	 * 根据订单号获取用户
	 * @param orderNo
	 * @return
	 */
	UserModel queryUserByOrderNo(String orderNo);
	/**
	 * 根据语言、编码、关联 查询级联菜单
	 * @param lang
	 * @param type
	 * @param real
	 * @return
	 */
	List<Map<String, String>> queryCascdeMenu(String lang, String type,
			String real);
	/**
	 *  根据语言、编码、code 查询级联菜单
	 * @param lang
	 * @param type
	 * @param code
	 * @return
	 */
	List<Map<String, String>> queryCountryMenu(String lang, String type,
			String code);
}
