package com.njyb.gbdbase.service.alldb.buyerresource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.document.Document;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.njyb.gbdbas.util.ECacheContrastUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.alldb.projectAnalyze.BuyerModel;
import com.njyb.gbdbase.service.alldb.commonrightlibrary.RightLibraryConstant;
import com.njyb.gbdbase.service.common.componet.impl.CommonLuceneComponent;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;

/**
 * 买家资源库
 * 
 * @author WangBo 2015年4月28日 BuyerResourceService.java
 */
@Service
public class BuyerResourceService extends CommonLuceneComponent implements
		IBuyerResourceService {

	/**
	 * 查询买家资源库
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BuyerModel> queryBuyerModelList(HttpServletRequest request,
			String[] fields, String[] values, String countryName,
			String module, PageBeanUtil page) {
		List<BuyerModel> buyerModelList = Lists.newLinkedList();
		List<Document> luceneList = null;
		if (null == ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE,LuceneConstant.DATA_RESOUCE)) {
			luceneList = this.getDocList(request, fields, values,LuceneConstant.DATA_RESOUCE, module, page);
			ECacheContrastUtil.addCacheByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE,LuceneConstant.DATA_RESOUCE, luceneList);
		} else {
			luceneList = (List<Document>) ECacheContrastUtil.getCacheResultByParams(RightLibraryConstant.RIGHT_SEARCH_CACHE,LuceneConstant.DATA_RESOUCE);
		}
		for (Document doc : luceneList) {
			buyerModelList.add(getBuyerModel(doc));
		}
		return buyerModelList;
	}

	/**
	 * 获取数据
	 * 
	 * @param doc
	 * @return
	 */
	private BuyerModel getBuyerModel(Document doc) {
		BuyerModel buyerModel = new BuyerModel();
		// 国家
		buyerModel.setCountry(doc.get("country"));
		// 公司名称
		buyerModel.setCompanyName(doc.get("importer"));
		// 产品描述
		buyerModel.setGoodsDescription(doc.get("goods_desc"));
		// 海关编码
		buyerModel.setHscode(doc.get("hscode"));
		return buyerModel;
	}
	
	/**
	 * 根据国家集合筛选国家
	 */
	@Override
	public List<BuyerModel> queryBuyerModelByCountry(String countryName,List<BuyerModel> buyerList) {
		List<BuyerModel> buyerCountryList = Lists.newArrayList();
		if (Strings.isNullOrEmpty(countryName)) {
			return buyerList;
		}
		for (BuyerModel buyerModel : buyerList) {
			if (buyerModel.getCountry().equals(countryName)) {
				buyerCountryList.add(buyerModel);
			}
		}
		return buyerCountryList;
	}

}
