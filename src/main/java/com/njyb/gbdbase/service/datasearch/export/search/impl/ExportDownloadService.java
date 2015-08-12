package com.njyb.gbdbase.service.datasearch.export.search.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.njyb.auth.service.impl.cmp.IOrderCountCmp;
import com.njyb.gbdbas.cache.CreateEncache;
import com.njyb.gbdbas.util.IConstantUtil;
import com.njyb.gbdbas.util.InitCountryCENameUtil;
import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbas.util.export.ExportDataUtil;
import com.njyb.gbdbas.util.export.ExportExcelUtil;
import com.njyb.gbdbas.util.export.ExportPDFUtil;
import com.njyb.gbdbas.util.export.QueryReportFieldByCountryUtil;
import com.njyb.gbdbase.dao.datasearch.export.IUserDownloadRightDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.datasearch.common.MapModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.model.datasearch.export.UserDownloadRightModel;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
import com.njyb.gbdbase.service.datasearch.CommonSearchService;
import com.njyb.gbdbase.service.datasearch.IDataSearchService;
import com.njyb.gbdbase.service.datasearch.export.search.IExportDownloadService;

/**
 * @Description 导出EXCEL与PDF权限业务接口实现层
 * @author XL
 * @date 2015-03-18
 * @version 标准版
 */
@Service
public class ExportDownloadService extends CommonSearchService implements IExportDownloadService  {

	@Autowired
	private IUserDownloadRightDao userDownloadRightDao;
	@Autowired
	private IDataSearchService dataSearchService;
	@Autowired
	private  ISearchEngineService searcherEngineService;
	@Autowired
	private IOrderCountCmp orderCountCmp;
	
	// 存放数据集合
	private List<String[]> dataList = new ArrayList<String[]>();
	// 用户下载类实体对象
	private UserDownloadRightModel downloadModel;
	
	@Override
	public void insertDownloadRight(UserDownloadRightModel userDownloadRightModel) 
	{
		userDownloadRightDao.insertDownloadRecord(userDownloadRightModel);
	}
	
	@Override
	public UserDownloadRightModel queryDownLoadRight(Integer userId) 
	{
		UserDownloadRightModel model = userDownloadRightDao.queryUserDownloadRight(userId);
		return model;
	}

	@Override
	public void updateDownloadTypeRecord(UserDownloadRightModel downloadRightModel) 
	{
		String sql = "UPDATE USER_DOWNLOAD_RIGHT SET EXCELNUM = EXCELNUM + "+downloadRightModel.getExcelNum()+",PDFNUM = PDFNUM + "+downloadRightModel.getPdfNum()+" WHERE USERID = "+downloadRightModel.getUserId();
		userDownloadRightDao.updateDownloadRecord(sql);
	}
	
	@Override
	public void clearDownloadRecord()
	{
		String sql = "UPDATE USER_DOWNLOAD_RIGHT SET EXCELNUM = 0,PDFNUM = 0";
		userDownloadRightDao.updateDownloadRecord(sql);
	}
	
	@Override
	public void updateDownloadTotalNum(UserDownloadRightModel downloadRightModel) 
	{
		String sql = "UPDATE USER_DOWNLOAD_RIGHT SET TOTALNUM = " + downloadRightModel.getTotalNum() + " WHERE USERID = " + downloadRightModel.getUserId();
		userDownloadRightDao.updateDownloadRecord(sql);
	}

	@Override
	public void exportFiles(int type, HttpServletRequest request,HttpServletResponse response) 
	{
		// 获取用户当前进入的国家
		String countryName = (String) request.getSession().getAttribute("country");
		// 根据国家名称获取标题名称(获取国家中文名称)
/*		String title = InitCountryCENameUtil.queryCountryCnName(countryName) + "数据";
*/		String title = InitCountryCENameUtil.queryCountryCnName(countryName) + "海关数据";
		// 获取备注名称
		String[] comm = QueryReportFieldByCountryUtil.queryCommonRemarksByCountry(request, countryName);
		// 根据用户的选择，调用不同的导出方法
		if (IConstantUtil.EXCELTYPE == type) 
		{
			String[] linkImportFields = new String[]{"公司名称","companyName","进口商","importer"};
			String[] linkExportFields = new String[]{"出口商","exporter"};
			// 导出excel
			ExportExcelUtil.exportExcel(title, dataList, comm, linkImportFields, linkExportFields, request, response);
			if (null != downloadModel) 
			{
				// 修改导出记录
				updateDownloadTypeRecord(downloadModel);
			}
		}
		else if (IConstantUtil.PDFTYPE == type) 
		{
			// 创建一个Document对象
			// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
			// 设置pdf纸张大小
			Document document = new Document(PageSize.A0, 50, 50, 50, 50);
			// 导出pdf
			ExportPDFUtil.downloadPDF(document, IConstantUtil.MAXWIDTH, title, dataList, comm, request, response);
			if (null != downloadModel) 
			{
				// 修改导出记录
				updateDownloadTypeRecord(downloadModel);
			}
		}
	}

	@Override
	public <T> String commonExportService(JSONObject jsonObject, int type, HttpServletResponse response, HttpServletRequest request)
	{
		// 获取用户
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		//获取用户当前进入的国家
		String country = (String) request.getSession().getAttribute("country");
		//当前页集合
  		List<T> exportList = new ArrayList<T>();
	  	//读取查询结果缓存
	  	Cache ehCache = CreateEncache.getEacheInstance().getCache("dataSearchCache");
	  	//判断缓存内集合是否存在
	  	if(ehCache.get("resultList") != null)
  		{
  			//从缓存中拿数据
	  		ehCache.acquireReadLockOnKey("resultList");
			Element element = ehCache.get("resultList");
			ehCache.releaseReadLockOnKey("resultList");
			exportList = (List<T>) element.getValue();
  		}
  		else
  		{
  			//获取查询条件
  			String queryKey = (String) MapModel.getMap().get("queryKey");
  			String queryValue = (String) MapModel.getMap().get("queryValue");
  			//实例化分页工具
  			PageBeanUtil beanUtil = super.getPageBeanUtil(request,"25");
  			//查询出lucene中数据库主键id 
  			List<Integer> idList =  searcherEngineService.getListKey(new SearchCommonParamModel(queryKey.split(";"), queryValue.split(";"), country, ParamEnumUtil.search.toString(), request, beanUtil));
  			//根据具体国家以及数据库id查询具体国家的详细数据
  			exportList = dataSearchService.getDataById(country, idList);
  		}
		//判断按次用户是否查看下载数据
  		if(user.getUserDesc().equals("按次用户")){
  			Map map = new HashMap();
//  			 userId : 用户Id 2. type : 用户角色  3. country_en 
  			map.put("userId", user.getUserId());
  			map.put("type", user.getUserDesc());
  			map.put("country_en", country);
  			List list = orderCountCmp.queryUserBuyerList(map);
  			if(list.size()>0){
  				exportList = list;
  			}
  		}
		// 判断需要导出的集合是否有值
		if (exportList.size() != 0) 
		{
			// 切换数据源
			
//			// 查询用户下载的记录(适用于任何用户)
//			UserDownloadRightModel rightModel= queryDownLoadRight(user.getUserId());
//			// 判断用户是否有下载权限
//			// 如果总下载量等于0的话表示没有下载权限，给出提示
//			if(rightModel.getTotalNum()<=0)
//			{
//				jsonObject.put("loadDataFlag", true);
//				jsonObject.put("message", "对不起，您没有导出权限！");
//			}
//			else
//			{
//				jsonObject.put("loadDataFlag", false);
//				// 计算用户已经导出excel与pdf总量
//				int exportNum = rightModel.getExcelNum()+rightModel.getPdfNum();
//				// 判断用户导出excel量与pdf量是否已经超过总下载量，给出提示
//				if(exportNum >= rightModel.getTotalNum())
//				{
//					jsonObject.put("loadNumFlag", true);
//					jsonObject.put("message", "对不起，您本月下载量已达到本月总下载量"+rightModel.getTotalNum()+"条！");
//				}
//				else
//				{
//					jsonObject.put("loadNumFlag", false);
//					// 判断用户本次选择的数据加上已经导出的数据总量是否已经超过总下载量，并给出提示
//					if((exportNum+exportList.size()) >= rightModel.getTotalNum())
//					{
//						jsonObject.put("isLog", true);
//						jsonObject.put("message", "对不起，您本次累积下载量已超过本月下载总量"+rightModel.getTotalNum()+"条！");
//					}
//					else
//					{
//						jsonObject.put("isLog", false);
//						// 提示用户可下载量
//						jsonObject.put("dowonlodMessage", "(每月最多可下载" + rightModel.getTotalNum() + "条 ！还可以下载:" + (rightModel.getTotalNum() - exportNum) + "条)");
//						// 根据国家获取转换后的数据集合
						dataList = getCountryDatas(exportList, country, request);
//						// 用户导出类型
						// type值1：excel 值2：pdf
						if(IConstantUtil.EXCELTYPE == type)
						{
							downloadModel = new UserDownloadRightModel(user.getUserId(), dataList.size(), 0);
						}
						else
						{
							downloadModel = new UserDownloadRightModel(user.getUserId(), 0 ,dataList.size());
						}
//					}
//				}
//			}
			return jsonObject.toString();
		}
		return null;
	}

	@Override
	public <T> List<String[]> getCountryDatas(List<T> exportList, String country, HttpServletRequest request) 
	{
		//获取实体类属性
		String[] fields = QueryReportFieldByCountryUtil.queryCommonPropertyNameByCountry(request, country);
		List<String[]> strList = ExportDataUtil.getList(exportList, fields);
		return strList;
	}
	
}
