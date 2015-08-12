package com.njyb.gbdbase.service.common.engine.filter.impl;

import com.njyb.gbdbase.service.common.engine.filter.AbstractStrHandleFilter;
/**
 * 处理字符串的 空 /空格
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public class HandleNullFilter extends AbstractStrHandleFilter {
	@Override
	public String handleStr(String param) {
		String str=param.trim();
		if (str==null || str.equals(" ")) {
			str="N/A";
		}
		return str;
	}

}
