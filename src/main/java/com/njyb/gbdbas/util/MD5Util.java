package com.njyb.gbdbas.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import sun.misc.BASE64Encoder;

/**
 * 一些常用的加密方法--base64和md5
 * 
 * @author jiahp
 * 
 */
public class MD5Util {
	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	// 构造函数私有化
	private MD5Util() {
	}

	// 内部类进行封装
	public static class InnerUtil {
		private static MD5Util mu = new MD5Util();
	}

	// 获取工具类--单例
	public static MD5Util getInstance() {
		return InnerUtil.mu;
	}

	// 字符串使用Md5加密
	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 再使用base64加密字符串
				BASE64Encoder base64en = new BASE64Encoder();
				byte[] results = base64en.encode(
						md.digest(originString.getBytes())).getBytes();
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 返回字符数组
	 * 
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 返回二进制字符
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 加密字符串
	 * 
	 * @param inputString
	 * @return
	 */
	public static String generateEncrypte(String inputString) {
		return encodeByMD5(inputString);
	}

	/**
	 * 判断字符串是否相等
	 * 
	 * @param password
	 * @param inputString
	 * @return
	 */
	public static boolean validatePassword(String password, String inputString) {
		if (password.equals(encodeByMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成4为随机数
	 * 
	 * @return
	 */
	public static String generateSuJshu() {
		String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		List list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(5, 9);
		return result;
	}

	public static void main(String[] args) {
	
		System.out.println(encodeByMD5("123456"));
	}
}
