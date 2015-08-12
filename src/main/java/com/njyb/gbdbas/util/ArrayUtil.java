package com.njyb.gbdbas.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

/**
 * 
 * byte数组工具类
 * 
 * @author honghao
 * @version [版本号, 2013-08-07]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ArrayUtil {
	private ArrayUtil() {
	}

	/**
	 * 去除数组最后面的0,如果没有则直接返回原数组
	 * 
	 * @param bt
	 * @param length
	 * @return byte[]
	 * @see [类、类#方法、类#成员]
	 */
	public static byte[] rTrimByteArray(byte[] bt) {
		int length = bt.length;
		int index = getLastZeroIndex(bt, length);
		// 如果没有就直接返回
		if (-1 == index) {
			return bt;
		}
		byte[] newByte = new byte[index];
		System.arraycopy(bt, 0, newByte, 0, index);
		return newByte;
	}

	/**
	 * 获取数组中0的最后位置,如果没有则返回-1
	 * 
	 * @param bt
	 * @param length
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	private static int getLastZeroIndex(byte[] bt, int length) {
		for (int i = length - 1; i > 0; i--) {
			if (0 != bt[i]) {
				return i + 1;
			}
		}
		return -1;
	}

	/**
	 * 是否为空数组
	 * 
	 * @param objs
	 * @return boolean
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isEmply(Object[] objs) {
		if (null == objs || 0 == objs.length) {
			return true;
		}
		return false;
	}

	/**
	 * 获取数组长度
	 * 
	 * @param objs
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	public static int getSize(Object[] objs) {
		if (ArrayUtil.isEmply(objs)) {
			return 0;
		}
		return objs.length;
	}

	/**
	 * 去除数组某一下标，数组长度减1 honghao
	 * 
	 * @param String
	 *            []
	 * @return String[]
	 * @see [类、类#方法、类#成员]
	 */
	public static String[] getArrayByRemoveIndex(String[] arraysrc, int i) {
		List list = new ArrayList();
		for (int j = 0; j < arraysrc.length; j++) {
			if (j == i) {
				continue;
			}
			list.add(arraysrc[j]);
		}
		String[] s = new String[list.size()];
		String[] arraydec = new String[arraysrc.length - 1];
		arraydec = (String[]) list.toArray(arraydec);
		return arraydec;
	}

	/**
	 * 判断两个字符串数组是否相同 honghao
	 * 
	 * @param String
	 *            []
	 * @return String[]
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean judgeEqual(String[] bt1, String[] bt2) {
		if (bt1.length != bt2.length) {
			return false;
		}
		for (int i = 0; i < bt1.length; i++) {
			if (!bt1[i].equals(bt2[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串 转Map {线程安全}
	 * 
	 * @author WangBo
	 * @param reportName
	 *            : 数据源
	 * @return paramList
	 */
	public static Map<String, String> getReportNameList(String reportName) {
		Map<String, String> paramList = null;
		if (!Strings.isNullOrEmpty(reportName)) {
			paramList = Splitter.on(",").withKeyValueSeparator("=")
					.split(reportName);
		}
		return paramList;
	}

	/**
	 * list转数组
	 * @author WangBo
	 * @param listValue
	 *            : list集合
	 * @return
	 */
	public static String[] getStringArray(List<String> listValue) {
		String[] resultArray = new String[0];
		if (null != listValue && !listValue.isEmpty()) {
			resultArray = new String[listValue.size()];
			resultArray = listValue.toArray(resultArray);
		}
		return resultArray;
	}
	
	/**
	 * list转object数组
	 * @author WangBo
	 * @param <T>
	 * @param listValue
	 *            : list集合
	 * @return
	 */
	public static <T> Object[] getObjArray(List<T> listValue) {
		Object[] resultArray = new String[0];
		if (null != listValue && !listValue.isEmpty()) {
			resultArray = new Object[listValue.size()];
			resultArray = listValue.toArray(resultArray);
		}
		return resultArray;
	}

}