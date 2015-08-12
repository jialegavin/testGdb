package com.njyb.gbdbase.service.common.engine.chain;

import java.util.ArrayList;
import java.util.List;

import com.njyb.gbdbase.service.common.engine.filter.AbstractStrHandleFilter;
/**
 * 把之前的单个过滤器连接成一条链子
 * @author 贾红平
 *
 */
public class HandleStrFilterChain {
	private List<AbstractStrHandleFilter> filterList = new ArrayList<AbstractStrHandleFilter>();
	/**
	 * 循环添加过滤器
	 * @param filter
	 * @return
	 */
	private HandleStrFilterChain addFilter(List<AbstractStrHandleFilter> filters) {
		for(AbstractStrHandleFilter filter:filters){
			filterList.add(filter);
		}
		return this;
	}
	
	/**
	 * 获取最终处理之后的字符串
	 * @param str
	 * @return
	 */
	public String handleStr(String str,List<AbstractStrHandleFilter> filters) {
		//循环添加处理的过滤器
		addFilter(filters);
		//如果实际项目中过滤器较多的话 这里的性能就需要考虑了
		for (AbstractStrHandleFilter filter : filterList) {
			str = filter.handleStr(str);
		}
		return str;
	}
}
