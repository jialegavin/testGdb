package com.njyb.gbdbase.service.common.engine.util;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;

/**
 * 使用lucene原声的api组合成一个能针对具体查询的query(精确或者模糊)
 * 
 * @author 贾红平
 *
 */
public class LuceneAnaylazeyUtil {
	/**
	 * 支持字段精确查询
	 * 
	 * @param keyWord
	 * @param keyValue
	 * @param countryname
	 * @return
	 */
	public static BooleanQuery getTokenInfoByParam(String keyWord,
			String keyValue, String countryname, HttpServletRequest request) {
		BooleanQuery bq = new BooleanQuery();
		TokenStream stream = null;
		try {
			// 传过来的参数是数值类型
			if (JudgeDataUtil.isNumeric(keyValue.toString())) {
				dataBooleanQuery(keyWord, keyValue, countryname, bq);
			}
			// 查询多个海关编码
			else if (keyWord.equals("hscode")) {
				if (keyValue.split(",").length>1) {
					multiHscodeBooleanQuery(keyWord, keyValue, countryname, bq);
				}
			}
			// 非数值类型
			else if (!JudgeDataUtil.isNumeric(keyValue)) {
				noNumberTypeQuery(keyWord, keyValue, countryname, bq, request);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bq;
	}

	/**
	 * 针对美国
	 * 
	 * @param keyWord
	 * @param keyValue
	 * @param countryname
	 * @param bq
	 * @throws IOException
	 */
	private static void noNumberTypeQuery(String keyWord, String keyValue,
			String countryname, BooleanQuery bq, HttpServletRequest request)
			throws IOException {
		TokenStream stream;
		// 通过不同的分词器讲词语分成不同的单元词
		stream = CountryAnaylazerUtil.getAnalyzerByCountry(countryname)
				.tokenStream("content", new StringReader(keyValue));
		// 存储每一个语汇单元的信息（分词单元信息）
		CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
		// 重置
		stream.reset();
		for (; stream.incrementToken();) {
			if (countryname.equals(LuceneConstant.USA_IMPORT_STRING)) {
				//查询美国的国家的时候 是否添加额外条件 如 排除进口商是物流或者是非空项
				//String con = request.getParameter("state");
				String con = "imp_other";
				
				// 排空值
				// 排物流
				// 排空值 也排物流
				if (con.equals(ParamEnumUtil.imp_not_wl.toString())
						|| con.equals(ParamEnumUtil.imp_not_null.toString())) {
					if (keyWord.equals("IMPORTER".toLowerCase())) {
						bq.add(new TermQuery(new Term(keyWord, cta.toString())),
								Occur.SHOULD);
					} else {
						bq.add(new TermQuery(new Term(keyWord, cta.toString())),
								Occur.MUST);
					}
				}
				else {
					bq.add(new TermQuery(new Term(keyWord, cta.toString())),
							Occur.MUST);
				}
				
			}
			//排除美国之外的国家
			else {
				bq.add(new TermQuery(new Term(keyWord, cta.toString())),
						Occur.MUST);
			}
		}
		if (stream != null) {
			stream.close();
		}
	}

	/**
	 * 针对多个海关编码
	 * 
	 * @param keyWord
	 * @param keyValue
	 * @param countryname
	 * @param bq
	 * @throws ParseException
	 */
	private static void multiHscodeBooleanQuery(String keyWord,
			String keyValue, String countryname, BooleanQuery bq)
			throws ParseException {
		String[] vs = keyValue.contains(",") ? keyValue.split(",") : keyValue
				.split("，");
		Query query = null;
		QueryParser p = new QueryParser(Version.LUCENE_48, keyWord,
				CountryAnaylazerUtil.getAnalyzerByCountry(countryname));
		for (int i = 0; i < vs.length; i++) {
			query = p.parse(vs[i].toString() + "*");
			bq.add(query, Occur.SHOULD);
		}
	}

	/**
	 * 针对单个海关编码
	 * 
	 * @param keyWord
	 * @param keyValue
	 * @param countryname
	 * @param bq
	 * @throws ParseException
	 */
	private static void dataBooleanQuery(String keyWord, String keyValue,
			String countryname, BooleanQuery bq) throws ParseException {
		QueryParser p = new QueryParser(Version.LUCENE_48, keyWord,
				CountryAnaylazerUtil.getAnalyzerByCountry(countryname));
		Query query = p.parse(keyValue.toString() + "*");
		bq.add(query, Occur.MUST);
	}

	/**
	 * 针对模糊查询
	 * 
	 * @param keyWord
	 * @param keyValue
	 * @param coutryname
	 * @return
	 */
	public static Query getParseQuery(String keyWord, String keyValue,
			String coutryname) {
		// 根据国家名称获取对应的分词器
		Analyzer analyzer = CountryAnaylazerUtil
				.getAnalyzerByCountry(coutryname);
		QueryParser qp = null;
		try {
			qp = new QueryParser(Version.LUCENE_48, keyWord, analyzer);
			qp.setAllowLeadingWildcard(true);
			Query query = qp.parse(keyValue + "*");
			return query;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 根据国家决定是否显示把海关编码字段加入到过滤范围字段中
	 * @param country
	 * @return
	 */
	public static boolean falg(String country){
		if (country.equals(LuceneConstant.CHINA_EIGHT_STRING)
				||country.equals(LuceneConstant.KOREA_STRING)) {
			return true;
		}
		return false;
	}
}
