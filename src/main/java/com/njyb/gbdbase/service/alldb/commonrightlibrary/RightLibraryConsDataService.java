package com.njyb.gbdbase.service.alldb.commonrightlibrary;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.alldb.commonrightlibrary.SearchRightLibraryModel;

/**
 * 权库数据处理,将各个国家model处理ALLDBModel
 * @author WangBo
 * 2015年4月18日
 * RightLibraryConstructionDataToModel.java
 */
public class RightLibraryConsDataService {
	
	private static final Logger log = Logger.getLogger(RightLibraryConsDataService.class);  

	// 权库配置文件
	@Resource
	private SearchRightLibraryModel searchRightLibraryModel;

	// 懒加载
	private static RightLibraryConsDataService rightLibraryConstructionDataToModel = new RightLibraryConsDataService();
	
	/**
	 * 权库构造model<br>
	 * 单例
	 * @return
	 */
	public static RightLibraryConsDataService getRightLibraryConstructionDataToModel(){
		return rightLibraryConstructionDataToModel;
	}
	
	/**
	 * 预留处理
	 */
	private RightLibraryConsDataService() {
	}
	
	/**
	 * 获取个个国家的model,将每个国家的model转成ALlDBModel
	 * @param request : 请求
	 * @param key : 构造条件 key
	 * @param value : 构造条件value 
	 * @param sortkeymap : 排序字段
	 * @param reportName : 报告类型
	 * @param isflag : {预留}字段
	 * @param countrys : 国家
	 */
	public List<AllDBModel> ConstructionALLDBModelByParamModel(HttpServletRequest request,String[] key,Map<String,String[]> valuemap,String sortkey,String reportName,boolean isflag,String []countrys){
		log.info("开始处理 RightLibraryConstructionDataToModel,将个个国家的Model转成ALLDBModel");
		List<AllDBModel> list = Lists.newArrayList();
		// 调用洪浩 的 查询方法,返回个个国家的model
		log.info("结束处理 RightLibraryConstructionDataToModel,将个个国家的Model转成ALLDBModel");
		return list;
	}
}