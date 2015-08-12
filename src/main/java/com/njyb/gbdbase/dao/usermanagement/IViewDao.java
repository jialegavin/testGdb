package com.njyb.gbdbase.dao.usermanagement;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.usermanagement.ViewModel;

/**
 * 试图查询
 * @author chenhu
 * 2015年4月13日
 */
@MyBatisReposity
public interface IViewDao {
	/**
	 * 根据类型和语言查询试图列表
	 * @param type
	 * @param language
	 * @return
	 */
   List<Map<String,String>> queryViewByTL(ViewModel view);
   /**
    * 根据语言，类型，关联号查询试图
    * @param type
    * @param language
    * @param real
    * @return
    */
	List<Map<String,String>> queryViewByTLR(ViewModel view);
	/**
	 * 根据序列名称查询自增序列
	 * @param seqName
	 * @return
	 */
	void querySequenceByName(Map<String,Object> map);
	/**
	 * 根据语言、编码、code 查询级联菜单
	 * @param view
	 * @return
	 */
	List<Map<String, String>> queryViewByTLC(ViewModel view);
	
}
