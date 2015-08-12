package com.njyb.gbdbase.service.common.engine.filter;
/**
 * 定义一个抽象字符串处理过滤器
 * @author 贾红平
 *
 */
public abstract class AbstractStrHandleFilter {
	/**
	 * 指定返回类型为泛型 便于使用通用的处理结果
	 * @param param
	 * @return 任意类型
	 */
	public abstract <T> T handleStr(String param);

}
