package com.njyb.gbdbase.service.personalcenter.favorites;


import java.util.List;
import java.util.Map;

/**
 * 收藏夹 共用接口
 * @author WangBo
 * 2015年4月2日
 * @param <T>
 * CommonFavoritesService.java
 */
public interface ICommonFavoritesService<T> {

	/**
	 * 保存浏览记录和我的收藏
	 * @param abstractFavoritesModelt : 共用抽象类
	 * @return 1 : 成功 2 : 失败
	 */
	int addFavorites(T t);
	
	/**
	 * 删除收藏夹 {我的收藏} 和 我的浏览记录
	 * @param abstractFavoritesModel : 共用抽象类
	 * @return 1 : 成功 0 : 失败
	 */
	int deleteFavorites(T t);
	
	/**
	 * 查询收藏夹数据<br>
	 * 包括 : 我的收藏夹 && 历史收藏夹
	 * userId 用户id
	 * @return List<T>
	 */
	List<T> queryFavoritesResult(int userId);
	
	/**
	 * 根据参数查询我的收藏<br>
	 * 我的交易记录
	 * @param paramsMap
	 * @return
	 */
	List<T> queryFavoritesByParams(Map<String,Object> paramsMap);
}
