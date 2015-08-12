package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaExportModel;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaImportModel;
import com.njyb.gbdbase.model.datasearch.brazil.BrazilImportModel;
import com.njyb.gbdbase.model.datasearch.chile.ChileExportModel;
import com.njyb.gbdbase.model.datasearch.chile.ChileImportModel;
import com.njyb.gbdbase.model.datasearch.chinaEight.ChinaEightModel;
import com.njyb.gbdbase.model.datasearch.colombia.ColombiaExportModel;
import com.njyb.gbdbase.model.datasearch.colombia.ColombiaImportModel;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.costarica.CostaricaExportModel;
import com.njyb.gbdbase.model.datasearch.costarica.CostaricaImportModel;
import com.njyb.gbdbase.model.datasearch.ecuador.EcuadorExportModel;
import com.njyb.gbdbase.model.datasearch.ecuador.EcuadorImportModel;
import com.njyb.gbdbase.model.datasearch.guatemala.GuatemalaExportModel;
import com.njyb.gbdbase.model.datasearch.guatemala.GuatemalaImportModel;
import com.njyb.gbdbase.model.datasearch.honduras.HondurasImportModel;
import com.njyb.gbdbase.model.datasearch.india.IndiaImportModel;
import com.njyb.gbdbase.model.datasearch.korea.KoreaModel;
import com.njyb.gbdbase.model.datasearch.mexico.MexicoImportModel;
import com.njyb.gbdbase.model.datasearch.nicaragua.NicaraguaExportModel;
import com.njyb.gbdbase.model.datasearch.nicaragua.NicaraguaImportModel;
import com.njyb.gbdbase.model.datasearch.pakistan.PakistanImportModel;
import com.njyb.gbdbase.model.datasearch.panama.PanamaExportModel;
import com.njyb.gbdbase.model.datasearch.panama.PanamaImportModel;
import com.njyb.gbdbase.model.datasearch.paraguay.ParaguayExportModel;
import com.njyb.gbdbase.model.datasearch.paraguay.ParaguayImportModel;
import com.njyb.gbdbase.model.datasearch.peru.PeruExportModel;
import com.njyb.gbdbase.model.datasearch.peru.PeruImportModel;
import com.njyb.gbdbase.model.datasearch.salvatore.SalvatoreExportModel;
import com.njyb.gbdbase.model.datasearch.salvatore.SalvatoreImportModel;
import com.njyb.gbdbase.model.datasearch.uk.UkImportModel;
import com.njyb.gbdbase.model.datasearch.uruguay.UruguayExportModel;
import com.njyb.gbdbase.model.datasearch.uruguay.UruguayImportModel;
import com.njyb.gbdbase.model.datasearch.venezuela.VenezuelaExportModel;
import com.njyb.gbdbase.model.datasearch.venezuela.VenezuelaImportModel;
import com.njyb.gbdbase.model.datasearch.vietnam.VietnamExportModel;
import com.njyb.gbdbase.model.datasearch.vietnam.VietnamImportModel;
import com.njyb.gbdbase.service.alldb.convertdata.ArgentinaEConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.ArgentinaIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.BrazilIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.ChileEConventModel;
import com.njyb.gbdbase.service.alldb.convertdata.ChileIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.ChinaEightConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.ColombiaEConvoertModel;
import com.njyb.gbdbase.service.alldb.convertdata.ColombiaIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.CostaricaEConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.CostaricaIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.EcuadorEConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.EcuadorIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.GuatemalaEConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.GuatemalaIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.HondurasIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.IndiaIConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.KoreaConvertModel;
import com.njyb.gbdbase.service.alldb.convertdata.MexicoIConvertModel;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;

/**
 * 多个国家转AllDBModel
 * @author WangBo
 * 2015年5月5日
 * DataConvertDBModel.java
 */
@Service
public class DataConvertDBModel implements IDataConvertDBModel {

	// 公用搜索业务接口层
	@Autowired
	private IDataSearchService dataSearchService;
	
	/**
	 * 国家转AllDBModel<br>
	 * 数据量太大,禁用反射
	 */
	@Override
	public List<AllDBModel> convertAllDBModel(String country,
			List<Integer> idList) {
		List<AllDBModel> dbResultList = Lists.newArrayList();
		List<Object> dataList = dataSearchService.getDataById(country, idList);		//从数据中查询数据
		for (Object object : dataList) {
			AllDBModel allDBModel = new AllDBModel();
			String cnCountry = InitCountryCENameUtil.queryCountryCnName(country);
			switch (country) {
			case LuceneConstant.CHINA_EIGHT_STRING:		//中国
				if (object instanceof ChinaEightModel){
					dbResultList.add(ChinaEightConvertModel.getConvertModel((ChinaEightModel) object,cnCountry));
					break;
				}
			case LuceneConstant.ARGENTINA_IMPORT_STRING:	//阿根廷进口
				if (object instanceof ArgentinaImportModel){
					dbResultList.add(ArgentinaIConvertModel.getConvertModel((ArgentinaImportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.ARGENTINA_EXPORT_STRING:	//阿根廷出口
				if (object instanceof ArgentinaExportModel){
					dbResultList.add(ArgentinaEConvertModel.getArgentinaEConvertModel((ArgentinaExportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.BRAZIL_IMPORT_STRING:		//巴西进口
				if (object instanceof BrazilImportModel){
					dbResultList.add(BrazilIConvertModel.getBrazilIConvertModel((BrazilImportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.CHILE_EXPORT_STRING:   		//智力出口
				if (object instanceof ChileExportModel){
					dbResultList.add(ChileEConventModel.getChileEConventModel((ChileExportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.CHILE_IMPORT_STRING:		//智力进口
				if (object instanceof ChileImportModel){
					dbResultList.add(ChileIConvertModel.getChileIConvertModel((ChileImportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.COLOM_EXPORT_STRING:		//克罗姆出口
				if (object instanceof ColombiaExportModel){
					dbResultList.add(ColombiaEConvoertModel.getColombiaEConvoertModel((ColombiaExportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.COLOM_IMPORT_STRING:		//克罗姆进口
				if (object instanceof ColombiaImportModel){
					dbResultList.add(ColombiaIConvertModel.getColombiaEConvoertModel((ColombiaImportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.COSTARICA_EXPORT_STRING:	//哥斯达黎加出口
				if (object instanceof CostaricaExportModel){
					dbResultList.add(CostaricaEConvertModel.getColombiaEConvoertModel((CostaricaExportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.COSTARICA_IMPORT_STRING:	//哥斯达黎加进口
				if (object instanceof CostaricaImportModel){
					dbResultList.add(CostaricaIConvertModel.getCostaricaIConvertModel((CostaricaImportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.ECUADOR_EXPORT_STRING:		//厄瓜多尔出口
				if (object instanceof EcuadorExportModel){
					dbResultList.add(EcuadorEConvertModel.getEcuadorEConvertModel((EcuadorExportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.ECUADOR_IMPORT_STRING:		//厄瓜多尔进口
				if (object instanceof EcuadorImportModel){
					dbResultList.add(EcuadorIConvertModel.getEcuadorIConvertModel((EcuadorImportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.GUATEMALA_EXPORT_STRING:	//危地马拉出口
				if (object instanceof GuatemalaExportModel){
					dbResultList.add(GuatemalaEConvertModel.getGuatemalaEConvertModel((GuatemalaExportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.GUATEMALA_IMPORT_STRING:		//危地马拉进口
				if (object instanceof GuatemalaImportModel){
					dbResultList.add(GuatemalaIConvertModel.getGuatemalaIConvertModel((GuatemalaImportModel) object, cnCountry));
					break;
				}
			// TODO 没有 洪都拉斯 出口
			case LuceneConstant.HONDURAS_IMPORT_STRING:		//洪都拉斯进口
				if (object instanceof HondurasImportModel){
					dbResultList.add(HondurasIConvertModel.getHondurasIConvertModel((HondurasImportModel) object, cnCountry));
					break;
				}
			// TODO 没有印度出口
			case LuceneConstant.INDIA_IMPORT_STRING:		//印度进口
				if (object instanceof IndiaImportModel){
					dbResultList.add(IndiaIConvertModel.getIConvertModel((IndiaImportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.KOREA_STRING:		//韩国
				if (object instanceof KoreaModel){
					dbResultList.add(KoreaConvertModel.getKoreaConvertModel((KoreaModel) object, cnCountry));
					break;
				}
			case LuceneConstant.MEXICON_IMPORT_STRING:		//墨西哥进口
				if (object instanceof MexicoImportModel){
					dbResultList.add(MexicoIConvertModel.getMexicoIConvertModel((MexicoImportModel) object, cnCountry));
					break;
				}
			case LuceneConstant.NICARAGUA_EXPORT_STRING:		//尼加拉瓜出口
				if (object instanceof NicaraguaExportModel){
					NicaraguaExportModel nicaraguaExportModel = (NicaraguaExportModel) object;
					allDBModel.setId(nicaraguaExportModel.getNicaraguaExportId());
					allDBModel.setQuantity(nicaraguaExportModel.getQuantity());
					allDBModel.setWeight(0);
					allDBModel.setDate(nicaraguaExportModel.getDate());
					allDBModel.setEndport(nicaraguaExportModel.getEndPort());
					allDBModel.setOrgincountry(nicaraguaExportModel.getOriginCountry());
					allDBModel.setCountNum(nicaraguaExportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(nicaraguaExportModel.getGoodsDesc());
					allDBModel.setExporter(nicaraguaExportModel.getExporter());
					allDBModel.setHscode("");
					allDBModel.setImporter(nicaraguaExportModel.getImporter());
					allDBModel.setTotalprice(0);
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.NICARAGUA_IMPORT_STRING:		//尼加拉瓜进口
				if (object instanceof NicaraguaImportModel){
					NicaraguaImportModel nicaraguaImportModel = (NicaraguaImportModel) object;
					allDBModel.setQuantity(nicaraguaImportModel.getQuantity());
					allDBModel.setId(nicaraguaImportModel.getNicaraguaImportId());
					allDBModel.setWeight(0);
					allDBModel.setDate(nicaraguaImportModel.getDate());
					allDBModel.setEndport(nicaraguaImportModel.getEndPort());
					allDBModel.setStartport(nicaraguaImportModel.getStartPort());
					allDBModel.setOrgincountry(nicaraguaImportModel.getOriginCountry());
					allDBModel.setCountNum(nicaraguaImportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(nicaraguaImportModel.getGoodsDesc());
					allDBModel.setExporter(nicaraguaImportModel.getExporter());
					allDBModel.setHscode("");
					allDBModel.setImporter(nicaraguaImportModel.getImporter());
					allDBModel.setTotalprice(0);
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.PAKISTAN_IMPORT_STRING:		//巴基斯坦进口
				if (object instanceof PakistanImportModel){
					PakistanImportModel pakistanImportModel = (PakistanImportModel) object;
					allDBModel.setId(pakistanImportModel.getPakistanImportId());
					allDBModel.setWeight(pakistanImportModel.getGrossWeight());
					allDBModel.setDate(pakistanImportModel.getDate());
					allDBModel.setEndport(pakistanImportModel.getEndPort());
					allDBModel.setStartport(pakistanImportModel.getStartPort());
					allDBModel.setOrgincountry(pakistanImportModel.getOriginCountry());
					allDBModel.setCountNum(0);
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(pakistanImportModel.getGoodsDesc());
					allDBModel.setExporter(pakistanImportModel.getExporter());
					allDBModel.setHscode("");
					allDBModel.setImporter(pakistanImportModel.getImporter());
					allDBModel.setTotalprice(0);
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
				break;
				}
			case LuceneConstant.PANAMA_EXPORT_STRING:		//巴拿马出口
				if (object instanceof PanamaExportModel){
					PanamaExportModel panamaExportModel = (PanamaExportModel) object;
					allDBModel.setQuantity(panamaExportModel.getQuantity());
					allDBModel.setId(panamaExportModel.getPanamaExportId());
					allDBModel.setWeight(panamaExportModel.getGrossWeight());
					allDBModel.setDate(panamaExportModel.getDate());
					allDBModel.setEndport("");
					allDBModel.setStartport(panamaExportModel.getStartPort());
					allDBModel.setOrgincountry("");
					allDBModel.setCountNum(panamaExportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(panamaExportModel.getGoodsDesc());
					allDBModel.setExporter(panamaExportModel.getExporter());
					allDBModel.setIexport("");
					allDBModel.setHscode(panamaExportModel.getHscode());
					allDBModel.setImporter("");
					allDBModel.setTotalprice(panamaExportModel.getFobValue());
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.PANAMA_IMPORT_STRING:		//巴拿马进口
				if (object instanceof PanamaImportModel){
					PanamaImportModel panamaImportModel = (PanamaImportModel) object;
					allDBModel.setQuantity(panamaImportModel.getQuantity());
					allDBModel.setId(panamaImportModel.getPanamaImportId());
					allDBModel.setWeight(panamaImportModel.getGrossWeight());
					allDBModel.setDate(panamaImportModel.getDate());
					allDBModel.setEndport(panamaImportModel.getEndPort());
					allDBModel.setStartport("");
					allDBModel.setOrgincountry(panamaImportModel.getOriginCountry());
					allDBModel.setCountNum(panamaImportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(panamaImportModel.getGoodsDesc());
					allDBModel.setIexport(panamaImportModel.getTransType());
					allDBModel.setExporter("");
					allDBModel.setHscode(panamaImportModel.getHscode());
					allDBModel.setImporter(panamaImportModel.getImporter());
					allDBModel.setTotalprice(panamaImportModel.getFobValue());
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.PARAGUAY_EXPORT_STRING:		//巴拉圭出口
				if (object instanceof ParaguayExportModel){
					ParaguayExportModel paraguayExportModel = (ParaguayExportModel) object;
					allDBModel.setQuantity(paraguayExportModel.getQuantity());
					allDBModel.setId(paraguayExportModel.getParaguayExportId());
					allDBModel.setWeight(paraguayExportModel.getGrossWeight());
					allDBModel.setDate(paraguayExportModel.getDate());
					allDBModel.setEndport("");
					allDBModel.setStartport("");
					allDBModel.setOrgincountry("");
					allDBModel.setCountNum(paraguayExportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(paraguayExportModel.getGoodsDesc());
					allDBModel.setIexport(paraguayExportModel.getTransType());
					allDBModel.setExporter(paraguayExportModel.getExporter());
					allDBModel.setHscode(paraguayExportModel.getHscode());
					allDBModel.setImporter(paraguayExportModel.getImporter());
					allDBModel.setTotalprice(paraguayExportModel.getFobValue());
					allDBModel.setUnitprice(paraguayExportModel.getFobUnit());
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.PARAGUAY_IMPORT_STRING:		//巴拉圭进口
				if (object instanceof ParaguayImportModel){
					ParaguayImportModel paraguayImportModel = (ParaguayImportModel) object;
					allDBModel.setQuantity(paraguayImportModel.getQuantity());
					allDBModel.setId(paraguayImportModel.getParaguayImportId());
					allDBModel.setWeight(paraguayImportModel.getGrossWeight());
					allDBModel.setDate(paraguayImportModel.getDate());
					allDBModel.setEndport("");
					allDBModel.setStartport("");
					allDBModel.setOrgincountry(paraguayImportModel.getOriginCountry());
					allDBModel.setCountNum(paraguayImportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(paraguayImportModel.getGoodsDesc());
					allDBModel.setIexport(paraguayImportModel.getTransType());
					allDBModel.setExporter(paraguayImportModel.getExporter());
					allDBModel.setHscode(paraguayImportModel.getHscode());
					allDBModel.setImporter(paraguayImportModel.getImporter());
					allDBModel.setTotalprice(paraguayImportModel.getFobValue());
					allDBModel.setUnitprice(paraguayImportModel.getFobUnit());
					dbResultList.add(allDBModel);
				}
			case LuceneConstant.PERU_EXPORT_STRING:		//秘鲁出口
				if (object instanceof PeruExportModel){
					PeruExportModel peruExportModel = (PeruExportModel) object;
					allDBModel.setQuantity(peruExportModel.getQuantity());
					allDBModel.setId(peruExportModel.getPeruExportId());
					allDBModel.setWeight(peruExportModel.getGrossWeight());
					allDBModel.setDate(peruExportModel.getDate());
					allDBModel.setEndport(peruExportModel.getEndPort());
					allDBModel.setStartport("");
					allDBModel.setOrgincountry("");
					allDBModel.setCountNum(peruExportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(peruExportModel.getGoodsDesc());
					allDBModel.setIexport(peruExportModel.getTransType());
					allDBModel.setExporter(peruExportModel.getExporter());
					allDBModel.setHscode(peruExportModel.getHscode());
					allDBModel.setImporter("");
					allDBModel.setTotalprice(peruExportModel.getFobValue());
					allDBModel.setUnitprice(peruExportModel.getFobUnit());
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.PERU_IMPORT_STRING:		//秘鲁进口
				if (object instanceof PeruImportModel){
					PeruImportModel peruImportModel = (PeruImportModel) object;
					allDBModel.setQuantity(peruImportModel.getQuantity());
					allDBModel.setId(peruImportModel.getPeruImportId());
					allDBModel.setWeight(peruImportModel.getGrossWeight());
					allDBModel.setDate(peruImportModel.getDate());
					allDBModel.setEndport("");
					allDBModel.setStartport(peruImportModel.getStartPort());
					allDBModel.setOrgincountry(peruImportModel.getOriginCountry());
					allDBModel.setCountNum(peruImportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(peruImportModel.getGoodsDesc());
					allDBModel.setIexport(peruImportModel.getTransType());
					allDBModel.setExporter(peruImportModel.getExporter());
					allDBModel.setHscode(peruImportModel.getHscode());
					allDBModel.setImporter(peruImportModel.getImporter());
					allDBModel.setTotalprice(peruImportModel.getFobValue());
					allDBModel.setUnitprice(peruImportModel.getCifUnit());
					dbResultList.add(allDBModel);
					break;
				}
//			case LuceneConstant.PORTUGARL_STRING:		//葡萄牙
//				// TODO 葡萄牙
//				break;
			case LuceneConstant.RUSSIAN_EXPORT_STRING:	//俄罗斯出口
				// TODO 俄罗斯出口
				break;
			case LuceneConstant.RUSSIAN_IMPORT_STRING:	//俄罗斯进口
				// TODO 俄罗斯进口
				break;
			case LuceneConstant.SALVATORE_IMPROT_STRING:	//萨尔瓦多进口
				if (object instanceof SalvatoreImportModel){
					SalvatoreImportModel salvatoreImportModel = (SalvatoreImportModel) object;
					allDBModel.setId(salvatoreImportModel.getSalvatoreImportId());
					allDBModel.setQuantity(salvatoreImportModel.getQuantity());
					allDBModel.setWeight(0);
					allDBModel.setDate(salvatoreImportModel.getDate());
					allDBModel.setEndport(salvatoreImportModel.getEndPort());
					allDBModel.setStartport(salvatoreImportModel.getStartPort());
					allDBModel.setOrgincountry(salvatoreImportModel.getOriginCountry());
					allDBModel.setCountNum(salvatoreImportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(salvatoreImportModel.getGoodsDesc());
					allDBModel.setIexport("");
					allDBModel.setExporter(salvatoreImportModel.getExporter());
					allDBModel.setHscode("");
					allDBModel.setImporter(salvatoreImportModel.getImporter());
					allDBModel.setTotalprice(0);
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.SALVATORE_EXPORT_STRING:	//萨尔瓦多出口
				if (object instanceof SalvatoreExportModel){
					SalvatoreExportModel salvatoreExportModel = (SalvatoreExportModel) object;
					allDBModel.setQuantity(salvatoreExportModel.getQuantity());
					allDBModel.setId(salvatoreExportModel.getSalvatoreExportId());
					allDBModel.setWeight(0);
					allDBModel.setDate(salvatoreExportModel.getDate());
					allDBModel.setEndport(salvatoreExportModel.getEndPort());
					allDBModel.setStartport(salvatoreExportModel.getStartPort());
					allDBModel.setOrgincountry(salvatoreExportModel.getOriginCountry());
					allDBModel.setCountNum(salvatoreExportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(salvatoreExportModel.getGoodsDesc());
					allDBModel.setIexport("");
					allDBModel.setExporter(salvatoreExportModel.getExporter());
					allDBModel.setHscode("");
					allDBModel.setImporter(salvatoreExportModel.getImporter());
					allDBModel.setTotalprice(0);
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
//			case LuceneConstant.SPAIN_IMPORT_STRING:	//西班牙出口
//				// TODO 西班牙出口
//				break;
			case LuceneConstant.UK_IMPORT_STRING:		//英国进口
				if (object instanceof UkImportModel){
					UkImportModel ukImportModel = (UkImportModel) object;
					allDBModel.setId(ukImportModel.getUkImportId());
					allDBModel.setWeight(0);
					allDBModel.setDate(ukImportModel.getDate());
					allDBModel.setEndport(ukImportModel.getCompanyName());
					allDBModel.setStartport("");
					allDBModel.setOrgincountry("");
					allDBModel.setCountNum(0);
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(ukImportModel.getGoodsDesc());
					allDBModel.setIexport("");
					allDBModel.setExporter("");
					allDBModel.setHscode("");
					allDBModel.setImporter(ukImportModel.getCompanyName());
					allDBModel.setTotalprice(0);
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.UKRAINE_IMPORT_STRING:		//乌克兰进口
				// TODO 乌克兰进口
				if (object instanceof UruguayImportModel){
					UruguayImportModel uruguayImportModel = (UruguayImportModel) object;
					allDBModel.setQuantity(uruguayImportModel.getQuantity());
					allDBModel.setId(uruguayImportModel.getUruguayImportId());
					allDBModel.setWeight(uruguayImportModel.getNetWeight());
					allDBModel.setDate(uruguayImportModel.getDate());
					allDBModel.setEndport("");
					allDBModel.setStartport("");
					allDBModel.setOrgincountry(uruguayImportModel.getOriginCountry());
					allDBModel.setCountNum(uruguayImportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(uruguayImportModel.getGoodsDesc());
					allDBModel.setIexport(uruguayImportModel.getTransType());
					allDBModel.setExporter("");
					allDBModel.setHscode(uruguayImportModel.getHscode());
					allDBModel.setImporter(uruguayImportModel.getImporter());
					allDBModel.setTotalprice(uruguayImportModel.getCifValue());
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.URUGUAY_EXPORT_STRING:		//乌拉圭出口
				// TODO 乌拉圭出口
				if (object instanceof UruguayExportModel){
					UruguayExportModel uruguayExportModel = (UruguayExportModel) object;
					allDBModel.setQuantity(uruguayExportModel.getQuantity());
					allDBModel.setId(uruguayExportModel.getUruguayExportId());
					allDBModel.setWeight(uruguayExportModel.getGrossWeight());
					allDBModel.setDate(uruguayExportModel.getDate());
					allDBModel.setEndport("");
					allDBModel.setStartport("");
					allDBModel.setOrgincountry("");
					allDBModel.setCountNum(uruguayExportModel.getQuantity());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(uruguayExportModel.getGoodsDesc());
					allDBModel.setIexport(uruguayExportModel.getTransType());
					allDBModel.setExporter(uruguayExportModel.getExporter());
					allDBModel.setHscode(uruguayExportModel.getHscode());
					allDBModel.setImporter("");
					allDBModel.setTotalprice(uruguayExportModel.getFobValue());
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.USA_IMPORT_STRING:			//美国出口
				// TODO 美国出口
				break;
			case LuceneConstant.VENEZUELAID_EXPORT_STRING:	//委内瑞拉出口
				if (object instanceof VenezuelaExportModel){
					VenezuelaExportModel venezuelaExportModel = (VenezuelaExportModel) object;
					allDBModel.setId(venezuelaExportModel.getVenezuelaExportId());
					allDBModel.setWeight(venezuelaExportModel.getGrossWeight());
					allDBModel.setDate(venezuelaExportModel.getDate());
					allDBModel.setEndport(venezuelaExportModel.getEndPort());
					allDBModel.setStartport("");
					allDBModel.setOrgincountry("");
					allDBModel.setCountNum(0);
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(venezuelaExportModel.getGoodsDesc());
					allDBModel.setIexport(venezuelaExportModel.getTransType());
					allDBModel.setExporter(venezuelaExportModel.getExporter());
					allDBModel.setHscode(venezuelaExportModel.getHscode());
					allDBModel.setImporter("");
					allDBModel.setTotalprice(venezuelaExportModel.getFobValue());
					allDBModel.setUnitprice(venezuelaExportModel.getBoFob());
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.VENEZUELAID_IMPORT_STRING:	//委内瑞拉进口
				if (object instanceof VenezuelaImportModel){
					VenezuelaImportModel venezuelaImportModel = (VenezuelaImportModel) object;
					allDBModel.setId(venezuelaImportModel.getVenezuelaImportId());
					allDBModel.setWeight(venezuelaImportModel.getGrossWeight());
					allDBModel.setDate(venezuelaImportModel.getDate());
					allDBModel.setEndport("");
					allDBModel.setStartport(venezuelaImportModel.getStartPort());
					allDBModel.setOrgincountry(venezuelaImportModel.getOriginCountry());
					allDBModel.setCountNum(0);
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(venezuelaImportModel.getGoodsDesc());
					allDBModel.setIexport(venezuelaImportModel.getTransType());
					allDBModel.setExporter("");
					allDBModel.setHscode(venezuelaImportModel.getHscode());
					allDBModel.setImporter(venezuelaImportModel.getImporter());
					allDBModel.setTotalprice(venezuelaImportModel.getFobValue());
					allDBModel.setUnitprice(venezuelaImportModel.getBoFob());
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.VIETNAM_EXPORT_STRING:		//越南出口
				if (object instanceof VietnamExportModel){
					VietnamExportModel vietnamExportModel = (VietnamExportModel) object;
					allDBModel.setQuantity(vietnamExportModel.getQuantity());
					allDBModel.setId(vietnamExportModel.getVietnamExportId());
					allDBModel.setWeight(0);
					allDBModel.setDate(vietnamExportModel.getDate());
					allDBModel.setEndport("");
					allDBModel.setStartport(vietnamExportModel.getStartPort());
					allDBModel.setOrgincountry(vietnamExportModel.getOriginCountry());
					allDBModel.setCountNum(vietnamExportModel.getNum());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(vietnamExportModel.getGoodsDesc());
					allDBModel.setIexport("");
					allDBModel.setExporter("");
					allDBModel.setHscode(vietnamExportModel.getHscode());
					allDBModel.setImporter(vietnamExportModel.getImporter());
					allDBModel.setTotalprice(vietnamExportModel.getFobValue());
					allDBModel.setUnitprice(0);
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.VIETNAM_IMPORT_STRING:	//越南进口
				if (object instanceof VietnamImportModel){
					VietnamImportModel vietnamImportModel = (VietnamImportModel) object;
					allDBModel.setQuantity(vietnamImportModel.getQuantity());
					allDBModel.setId(vietnamImportModel.getVietnamImportId());
					allDBModel.setWeight(0);
					allDBModel.setDate(vietnamImportModel.getDate());
					allDBModel.setEndport(vietnamImportModel.getEndPort());
					allDBModel.setStartport("");
					allDBModel.setOrgincountry(vietnamImportModel.getOriginCountry());
					allDBModel.setCountNum(vietnamImportModel.getNum());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setGoodsdescription(vietnamImportModel.getGoodsDesc());
					allDBModel.setIexport("");
					allDBModel.setExporter(vietnamImportModel.getExporter());
					allDBModel.setHscode(vietnamImportModel.getHscode());
					allDBModel.setImporter(vietnamImportModel.getImporter());
					allDBModel.setTotalprice(vietnamImportModel.getCifValue());
					allDBModel.setUnitprice(vietnamImportModel.getCifUnit());
					dbResultList.add(allDBModel);
					break;
				}
			case LuceneConstant.URUGUAY_IMPORT_STRING :		//乌拉圭进口
				if (object instanceof UruguayImportModel){
					UruguayImportModel uruguayImportModel = (UruguayImportModel) object;
					allDBModel.setQuantity(uruguayImportModel.getQuantity());
					allDBModel.setId(uruguayImportModel.getUruguayImportId());
					allDBModel.setCountNum(uruguayImportModel.getNum());
					allDBModel.setCountry(InitCountryCENameUtil.queryCountryCnName(cnCountry));
					allDBModel.setDate(uruguayImportModel.getDate());
//					allDBModel.setExporter(uruguayImportModel.get);
					allDBModel.setImporter(uruguayImportModel.getImporter());
					allDBModel.setGoodsdescription(uruguayImportModel.getGoodsDesc());
					allDBModel.setHscode(uruguayImportModel.getHscode());
					allDBModel.setStartport(uruguayImportModel.getImporter());
					allDBModel.setOrgincountry(uruguayImportModel.getOriginCountry());
					allDBModel.setTotalprice(uruguayImportModel.getCifValue());
					allDBModel.setIexport("");
					allDBModel.setUnitprice(uruguayImportModel.getQuantity());
					dbResultList.add(allDBModel);
					break;
				}
			default:
				break;
			}
		}
		return dbResultList;
	}
	
	/**
	 * 市场分析功能 <br>
	 * 根据国家和报告类型对数据进行整合 <br>
	 * 禁用反射
	 */
	@Override
	public List<DataReportSumModel> convertDataByCountryAndReportType(List<DataReportSumModel> dataReportData,
			String [] country,String reportType) {
		List<String> newCountryArray = Arrays.asList(country);
		String [] countryArray = {"中国","韩国","英国"};		//配置国家
		for (String countryName : countryArray) {
			if (newCountryArray.contains(countryName)) {
				for (Iterator<DataReportSumModel> iterator = dataReportData.iterator(); iterator.hasNext();) {
					DataReportSumModel dataReportSumModel = iterator.next();
					dataReportSumModel.setImporter(dataReportSumModel.getCompany_name());
					dataReportSumModel.setExporter(dataReportSumModel.getCompany_name());
				}
			}
		}
		return dataReportData;
	}
}