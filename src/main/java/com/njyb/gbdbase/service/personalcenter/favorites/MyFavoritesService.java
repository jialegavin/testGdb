package com.njyb.gbdbase.service.personalcenter.favorites;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.dao.personalcenter.favorites.IBrowsingHistoryAndMyFavoritesDao;
import com.njyb.gbdbase.model.personalcenter.favorites.HistoryAndFavoritesModel;

/**
 * 我的收藏Service
 * @author WangBo
 * 2015年4月1日
 * MyFavoritesService.java
 */
@Service
public class MyFavoritesService implements IMyFavoritesService {

	/*使用日志*/
	private static final Logger log = Logger.getLogger(MyFavoritesService.class);
	
	//我的收藏夹DAO
	@Autowired
	private IBrowsingHistoryAndMyFavoritesDao favoritesDao;
	
	/**
	 * 查询收藏夹数据
	 * 包括 : 我的收藏夹 && 历史收藏夹
	 */
	@Override
	public List<HistoryAndFavoritesModel> queryFavoritesResult(int userId) {
		List<HistoryAndFavoritesModel> myFavoritesList = Lists.newArrayList();
		// 切换数据源
		
		myFavoritesList = favoritesDao.queryMyFavoritesByParamModel(userId);
		return myFavoritesList;
	}

	/**
	 * 添加我的收藏
	 */
	@Override
	public int addFavorites(HistoryAndFavoritesModel t) {
		//切换数据库
		
		int result = 0;
		if (null != t) {
			result = favoritesDao.addMyFavorites(t);
		}
		return result;
	}

	/**
	 * 删除我的收藏
	 */
	@Override
	public int deleteFavorites(HistoryAndFavoritesModel t) {
		//切换数据库
		
		int result = 0;
		if (null != t) {
			// TODO 注意: 这里是储存过程 pro
			result = favoritesDao.deleteMyFavorites(null);
		}
		return result;
	}
	
	/**
	 * 根据参数查询我的收藏
	 * 我的交易记录
	 */
	@Override
	public List<HistoryAndFavoritesModel> queryFavoritesByParams(
			Map<String, Object> paramsMap) {
		//切换数据库
		
		List<HistoryAndFavoritesModel> resultList = Lists.newArrayList();
		if (null != paramsMap && !paramsMap.isEmpty()) {
			resultList = favoritesDao.queryMyFavoritesByParams(paramsMap);
		}
		return resultList;
	}

	/**
	 * 删除收藏夹ID
	 */
	@Override
	public int delFavorites(String idSubList) {
		Map<String,Object> paramMap = Maps.newHashMap();
		int result = 0; 
		//切换数据库
		if (!Strings.isNullOrEmpty(idSubList)) {
			
			paramMap.put("idList", idSubList);
			log.info("调用producte,参数为: " + idSubList);
			try {
				favoritesDao.deleteMyFavorites(paramMap);
			} catch (Exception e) {
				log.info(e);
				log.debug(e);
			}
			if (paramMap.containsKey("flag")) {
				result = (int) paramMap.get("flag");
			}
		}
		return result;	
	}
}