package com.njyb.gbdbase.service.personalcenter.favorites;

import com.njyb.gbdbase.model.personalcenter.favorites.HistoryAndFavoritesModel;

/**
 * 我的收藏
 * @author WangBo
 * 2015年4月1日
 * IMyFavoritesService.java
 */
public interface IMyFavoritesService extends ICommonFavoritesService<HistoryAndFavoritesModel>{
	
	/**
	 * 根据idSubList 字符串 删除数据库表中的ID
	 * @param idSubList : 字符串
	 * @return 0 : success 1 : error
	 */
	int delFavorites(String idSubList);
}