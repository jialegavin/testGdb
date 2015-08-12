package com.njyb.gbdbas.util.translate;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import com.google.gson.Gson;

/**
 * 语言翻译工具类
 * @author 洪皓
 * 2015-04-09
 * @version 标准版
 */
public class LanguageTranslateUtil {
	protected static final String URL = "http://openapi.baidu.com/public/2.0/bmt/translate";
	protected static final String API_KEY = "SwAKb2QUp2OaY5RqxxDeIjPD";
	protected static final String ENCODING = "UTF-8";
	protected static final String AUTO = "auto"; // google自动判断语言来源系统
	protected static final String CHINA = "zh"; // 简体中文
	protected static final String ENGLISH = "en"; // 英语
	protected static final String JAPAN = "jp"; // 日语
	protected static final String RUSSIAN = "ru";// 俄语
	protected static final String SPAIN = "spa";// 西班牙语
	protected static final String VIETNAM = "auto";// 越南语
	protected static final String PORTUGAL = "pt";// 葡萄牙语

	/**
	 * 语言翻译方法
	 * @param text 翻译文字
	 * @param src_lang 原始语种
	 * @param target_lang 目标语种
	 * @return
	 * @throws Exception翻译异常
	 */
	public static String translate(final String text, final String src_lang,final String target_lang) throws Exception {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(URL);
		// from源语言语种
		// to目标语言语种
		// client_id 开发者在百度开发者中心注册得到的授权API key
		// q 待翻译内容该字段必须为UTF-8编码，并且以GET方式调用API时，需要进行urlencode编码
		method.setQueryString(new NameValuePair[] {	new NameValuePair("from", src_lang),new NameValuePair("to", target_lang),new NameValuePair("client_id", API_KEY),new NameValuePair("q", text) });
		client.executeMethod(method);
		String response = new String(method.getResponseBodyAsString().getBytes("ISO-8859-1"));
		method.releaseConnection();
		Gson gson = new Gson();
		BaiduTrans bt = gson.fromJson(response, BaiduTrans.class);
		String result = null;
		if (null !=bt.getTrans_result()&& bt.getTrans_result().size() == 1) 
		{
			for (TransResult tr : bt.getTrans_result()) {
				result = tr.getDst();
			}
		}
		return result;
	}
	/**
	 * 
	 * @param text
	 * @param target_lang
	 * @return
	 * @throws Exception
	 */
	public static String translate(final String text, final String target_lang)
			throws Exception {
		return translate(text, AUTO, target_lang);
	}

	/**
	 * 中文--俄语
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String cn2ru(final String text) throws Exception {
		return translate(text, CHINA, RUSSIAN);
	}

	/**
	 * 中文--英语
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String cn2en(final String text) throws Exception {
		return translate(text, CHINA, ENGLISH);
	}

	/**
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String jp2tw(final String text) throws Exception {
		return translate(text, JAPAN, CHINA);
	}

	/**
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String tw2jp(final String text) throws Exception {
		return translate(text, CHINA, JAPAN);
	}

	/**
	 * 中文--西班牙
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String cn2sp(final String text) throws Exception {
		return translate(text, CHINA, SPAIN);
	}

	/**
	 * 中文--葡萄牙
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String cn2po(final String text) throws Exception {
		return translate(text, CHINA, SPAIN);
	}
	/**
	 * 中文--越南语
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String cn2vi(final String text) throws Exception {
		return translate(text, CHINA, VIETNAM);
	}
	/**
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String ru2cn(final String text) throws Exception {
		return translate(text, RUSSIAN, CHINA);
	}

	/**
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String en2cn(final String text) throws Exception {
		return translate(text, ENGLISH, CHINA);
	}

}
