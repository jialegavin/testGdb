package com.njyb.gbdbase.service.common.engine.filter.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.njyb.gbdbase.service.common.engine.filter.AbstractStrHandleFilter;
/**
 * 处理字符串的格式是否正确 或者包含非法字符
 * @author 贾红平
 *
 */
@SuppressWarnings("all")
public class HandleFormatterFilter extends AbstractStrHandleFilter {
	@Override
	public String handleStr(String param) {
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(param);
		return m.replaceAll("").trim();
	}

}
