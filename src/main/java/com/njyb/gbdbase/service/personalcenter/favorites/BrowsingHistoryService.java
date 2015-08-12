package com.njyb.gbdbase.service.personalcenter.favorites;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbas.util.SysContentUtil;
import com.njyb.gbdbas.util.TradeCacheUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.personalcenter.favorites.IBrowsingHistoryAndMyFavoritesDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.datasearch.common.MapModel;
import com.njyb.gbdbase.model.personalcenter.favorites.HistoryAndFavoritesModel;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 浏览记录Service业务接口实现类
 * 
 * @author WangBo 2015年4月3日 BrowsingHistoryService.java
 */
@Service
public class BrowsingHistoryService implements IBrowsingHistoryService {

	/**
	 * 浏览记录&&我的收藏<br>
	 * 共用 DAO层
	 */
	@Autowired
	private IBrowsingHistoryAndMyFavoritesDao favoritesDao;
	
	/**
	 * 公用搜索业务接口层
	 */
	@Autowired
	private IDataSearchService dataSearchService;

	/**
	 * 查询浏览记录<br>
	 */
	@Override
	public List<HistoryAndFavoritesModel> queryFavoritesResult(int userId) {
		List<HistoryAndFavoritesModel> favoritesList = Lists.newArrayList();
		//切换数据库
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		favoritesList = favoritesDao.queryBrowsingHistoryByParamModel(userId);
		return favoritesList;
	}

	/**
	 * 添加浏览记录
	 */
	@Override
	public int addFavorites(HistoryAndFavoritesModel t) {
		HttpServletRequest request = SysContentUtil.getRequest();
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		String country = (String) request.getSession().getAttribute("country");
		int reuslt = 0;
		if (null != t) {
			TradeCacheUtil.getQueryParam(t.getQueryKey(), t.getQueryValue());
			String queryKey = MapModel.map.get("zhQueryParam").toString();	//用户看的条件
			String queryValue = MapModel.map.get("enQueryParam").toString();	//系统条件
			t.setUserId(userModel.getUserId());
			t.setCountry(country);
			t.setQueryKey(queryKey);
			t.setQueryValue(queryValue);
			t.setQuerytime(DataUtil.parseDate(new Date(), 0));
			//切换数据库
			DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
			reuslt = favoritesDao.addBrowsingHistory(t);
		}
		return reuslt;
	}

	/**
	 * 删除浏览记录
	 * 
	 * @see 预留
	 */
	@Override
	public int deleteFavorites(HistoryAndFavoritesModel t) {
		// TODO 预留
		return 0;
	}
	
	@Override
	public List<HistoryAndFavoritesModel> queryFavoritesByParams(
			Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}
}