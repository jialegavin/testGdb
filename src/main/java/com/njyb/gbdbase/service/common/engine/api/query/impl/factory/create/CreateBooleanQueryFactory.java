package com.njyb.gbdbase.service.common.engine.api.query.impl.factory.create;

import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.AbstractBooleanQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.impl.ChinaBooleanQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.impl.OtherForeignBooleanQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.impl.UkBooleanQuery;
import com.njyb.gbdbase.service.common.engine.api.query.impl.factory.impl.UsaBooleanQuery;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;

/**
 * 创建查询对象的静态工厂 根据国家的名称
 * 
 * @author 贾红平
 *
 */
public class CreateBooleanQueryFactory {
	/**
	 * 返回一个具体的查询query
	 * @param country 国家名称的简称
	 * @return
	 */
	public static AbstractBooleanQuery createBooleanQuery(String country) {
		AbstractBooleanQuery query = null;
		switch (ParamEnumUtil.getEnum(country)) {
		case china:
			query = new ChinaBooleanQuery();
			break;
		case usa:
			query = new UsaBooleanQuery();
			break;
		case uk:
			query = new UkBooleanQuery();
			break;
		case other:
			query = new OtherForeignBooleanQuery();
			break;
		default:
			break;

		}
		return query;
	}
}
