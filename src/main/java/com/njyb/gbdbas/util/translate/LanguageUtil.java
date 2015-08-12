package com.njyb.gbdbas.util.translate;
import com.njyb.gbdbas.util.translate.LanguageTranslateUtil;

/**
 * 语言翻译工具类
 * @author 洪皓
 * 2015-04-09
 * @version 标准版
 */
public class LanguageUtil {
	/**
	 * 简体中文--翻译成英文
	 * 
	 * @param param
	 * @return
	 */
	public String getStrValueByChina(String param) throws Exception {
		return LanguageTranslateUtil.cn2en(param);
	}

	/**
	 * 中文-俄语
	 * 
	 * @param param
	 * @return
	 */
	public String getStrValueByRussian(String param) throws Exception {
		return LanguageTranslateUtil.cn2ru(param);
	}

	/**
	 * 中文-乌克兰
	 * 
	 * @param param
	 * @return
	 */
	public String getStrValueByUkrainian(String param) throws Exception {
		return LanguageTranslateUtil.cn2ru(param);
	}

	/**
	 * 中文--西班牙
	 * 
	 * @param param
	 * @return
	 */
	public String getStrValueBySpain(String param) throws Exception {
		return LanguageTranslateUtil.cn2sp(param);
	}

	/**
	 * 中文-葡萄牙
	 * 
	 * @param param
	 * @return
	 */
	public String getStrValueByPortuguese(String param) throws Exception {
		return LanguageTranslateUtil.cn2po(param);
	}
	/**
	 * 中文-葡萄牙
	 * 
	 * @param param
	 * @return
	 */
	public String getStrValueByVietnam(String param) throws Exception {
		return LanguageTranslateUtil.cn2vi(param);
	}
}
