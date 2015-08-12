package com.njyb.gbdbase.service.common.engine.builder.componet.state;
import org.apache.lucene.search.IndexSearcher;

/**
 * 根据返回的国家 获取不同的indexsearcher
 * @author jiahp
 *
 */
public interface ISearcherState {
	/**
	 * 获取具体的indexserarcher
	 * @param country 国家名称
	 * @param indexpath 索引路径
	 * @param file 索引文件目录名称
	 * @param fields 查询字段
	 * @param values 查询字段的值
	 * @param map报错索引在配置文件里面的信息
	 * @return
	 */
	public IndexSearcher handleIndexSearcher(String country,String indexpath,String[] fields, String[] values);
}
