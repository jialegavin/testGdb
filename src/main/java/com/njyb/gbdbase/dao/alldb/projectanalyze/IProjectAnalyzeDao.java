package com.njyb.gbdbase.dao.alldb.projectanalyze;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.alldb.projectAnalyze.ProjectAnalyzeModel;

/**
 * 产品分析 {权库分析}
 * 
 * @author WangBo 2015年4月20日 IProjectAnalyzeDao.java
 */
@MyBatisReposity
public interface IProjectAnalyzeDao {

	/**
	 * 添加产品标签
	 * 
	 * @return
	 */
	public int addProjectAnalyze(ProjectAnalyzeModel projectAnalyzeModel);

	/**
	 * 删除产品标签
	 * @param paramMap :　id条件
	 * @return
	 */
	public int deleteProjectAnalyze(Map<String,Object> paramMap);

	/**
	 * 根据条件查询产品标签
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<ProjectAnalyzeModel> queryProjectAnalyzeModelByParam(
			Map<String, Object> paramMap);

	/**
	 * 修改产品标签
	 * 
	 * @param projectAnalyzeModel
	 *            : 对象
	 * @return
	 */
	public int updateProjectAnalyze(ProjectAnalyzeModel projectAnalyzeModel);
}
