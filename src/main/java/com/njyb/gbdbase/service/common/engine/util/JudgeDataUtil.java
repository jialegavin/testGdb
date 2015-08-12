package com.njyb.gbdbase.service.common.engine.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断是否是数值类型
 * @author 贾红平
 *
 */
public class JudgeDataUtil {
	/**
	 * 判断当前字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
