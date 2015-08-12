package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanQuery;

import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.create.CreateBooleanQueryFactory;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;

/**
 * 封装通过简单工厂获取对象 然后通过对象的方法 返回一个具体的查询对象
 * @author 贾红平
 *
 */
public final class BooleanQueryFactoryFacade {
	/**
	 * 针对不同国家的状态创建返回的query对象
	 * @param fs 查询参数的数组
	 * @param vs 查询参数的值
	 * @param country 查询国家的英文简称
	 * @param request  封装请求的参数对象
	 * @return
	 */
	public static BooleanQuery getBooleanQuery(List<String>fs,List<String>vs,String country,HttpServletRequest request){
		return CreateBooleanQueryFactory.createBooleanQuery(getQueryType(country)).getBooleanQuery(fs, vs,country,request);
	}
	
	/**
	 * 根据用户传递过来的国家区分特殊的国家群体
	 * @param country
	 * @return
	 */
	private static String getQueryType(String country){
		//美国
		if (country.equals(LuceneConstant.USA_IMPORT_STRING)) {
			return "usa";
		}
		//英国
		if (country.equals(LuceneConstant.UK_IMPORT_STRING)) {
			return "uk";
		}
		//中国
		if (country.equals(LuceneConstant.CHINA_EIGHT_STRING)) {
			return "china";
		}
		else{
			return "other";
		}
	}
}
