package com.njyb.gbdbase.dao.personalcenter.favorites;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.personalcenter.favorites.HistoryAndFavoritesModel;

/**
 * 收藏夹 && 浏览器记录 <br>
 * 共用Dao层
 * @author WangBo
 * 2015年4月2日
 * IFavoritesDao.java
 */
@MyBatisReposity
public interface IBrowsingHistoryAndMyFavoritesDao {
	
	/**
	 * 查询历史收藏夹 用户id,国家名称,开始index,结束index
	 * @param userId : 用户id
	 * @return List<FavoritesModel>
	 */
	List<HistoryAndFavoritesModel> queryBrowsingHistoryByParamModel(int userId);
	
	/**
	 * 保存收藏夹记录
	 * @param favoritesModel : 收藏夹Bean
	 * @return 是否保存成功
	 */
	int addBrowsingHistory(HistoryAndFavoritesModel favoritesModel);
	
	/**
	 * 根据查询model查询我的收藏
	 * @param userId : 用户id
	 * @return List<FavoritesModel>
	 */
	List<HistoryAndFavoritesModel> queryMyFavoritesByParamModel(int userId);
	
	/**
	 * 添加我的收藏
	 * @param favoritesModel :　我的收藏
	 * @return 1 : 成功 2 : 失败
	 */
	int addMyFavorites(HistoryAndFavoritesModel favoritesModel);
	
	/**
	 * 删除我的收藏
	 * @param favoritesModel : 我的收藏
	 * @return 1 : 成功 2 : 失败
	 */
	int deleteMyFavorites(Map<String,Object> paramMap);
	
	/**
	 * 根据查询model查询我的收藏
	 * @param userId : 用户id
	 * @return List<FavoritesModel>
	 */
	List<HistoryAndFavoritesModel> queryMyFavoritesByParams(Map<String,Object> paramsMap);
}
