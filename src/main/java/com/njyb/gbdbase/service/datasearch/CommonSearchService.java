package com.njyb.gbdbase.service.datasearch;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.dao.datasearch.country.IArgentinaExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IArgentinaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IBoliviaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IBrazilImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IChileExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IChileImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IChinaEightDao;
import com.njyb.gbdbase.dao.datasearch.country.IColombiaExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IColombiaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.ICostaricaExportDao;
import com.njyb.gbdbase.dao.datasearch.country.ICostaricaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IEcuadorExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IEcuadorImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IGuatemalaExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IGuatemalaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IHondurasImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IIndiaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IKoreaDao;
import com.njyb.gbdbase.dao.datasearch.country.IMexicoImportDao;
import com.njyb.gbdbase.dao.datasearch.country.INicaraguaExportDao;
import com.njyb.gbdbase.dao.datasearch.country.INicaraguaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IPakistanImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IPanamaExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IPanamaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IParaguayExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IParaguayImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IPeruExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IPeruImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IRussiaExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IRussiaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.ISalvatoreExportDao;
import com.njyb.gbdbase.dao.datasearch.country.ISalvatoreImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IUkImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IUkraineImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IUruguayExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IUruguayImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IUsaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IVenezuelaExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IVenezuelaImportDao;
import com.njyb.gbdbase.dao.datasearch.country.IVietnamExportDao;
import com.njyb.gbdbase.dao.datasearch.country.IVietnamImportDao;
import com.njyb.gbdbase.service.common.engines.IMonthYearTrendService;
import com.njyb.gbdbase.service.common.engines.IReportDrillService;
import com.njyb.gbdbase.service.common.engines.IReportEngineService;
/**
 * 一些共性的方法可以放在这个类里面去实现 让子类直接复用
 * @author 贾红平
 *
 */
@Component
public class CommonSearchService {
	/*注入报表查询引擎组件*/
	@Resource
	protected IReportEngineService reportEngineService;
	/*注入报表查询钻取组件*/
	@Resource
	protected IReportDrillService reportDrillService;
	/*注入报表同环比分析组件*/
	@Resource
	protected IMonthYearTrendService monthYearTrendService;
	/*注入阿根廷出口数据查询组件*/
	@Autowired
	protected IArgentinaExportDao IArgentinaExportDao;
	/*注入阿根廷进口数据查询组件*/
	@Autowired
	protected IArgentinaImportDao IArgentinaImportDao;
	/*注入巴西进口数据查询组件*/
	@Autowired
	protected IBrazilImportDao IBrazilImportDao;
	/*注入智利出口数据查询组件*/
	@Autowired
	protected IChileExportDao IChileExportDao;
	/*注入智利进口数据查询组件*/
	@Autowired
	protected IChileImportDao IChileImportDao;
	/*注入中国数据查询组件*/
	@Autowired
	protected IChinaEightDao IChinaEightDao;
	/*注入哥伦比亚出口数据查询组件*/
	@Autowired
	protected IColombiaExportDao IColombiaExportDao;
	/*注入哥伦比亚进口数据查询组件*/
	@Autowired
	protected IColombiaImportDao IColombiaImportDao;
	/*注入哥斯达尼加出口数据查询组件*/
	@Autowired
	protected ICostaricaExportDao ICostaricaExportDao;
	/*注入哥斯达尼加进口数据查询组件*/
	@Autowired
	protected ICostaricaImportDao ICostaricaImportDao;
	/*注入厄瓜多尔出口数据查询组件*/
	@Autowired
	protected IEcuadorExportDao IEcuadorExportDao;
	/*注入厄瓜多尔进口数据查询组件*/
	@Autowired
	protected IEcuadorImportDao IEcuadorImportDao;
	/*注入危地马拉出口数据查询组件*/
	@Autowired
	protected IGuatemalaExportDao IGuatemalaExportDao;
	/*注入危地马拉进口数据查询组件*/
	@Autowired
	protected IGuatemalaImportDao IGuatemalaImportDao;
	/*注入洪都拉斯进口数据查询组件*/
	@Autowired
	protected IHondurasImportDao IHondurasImportDao;
	/*注入印度进口数据查询组件*/
	@Autowired
	protected IIndiaImportDao IIndiaImportDao;
	/*注入韩国数据查询组件*/
	@Autowired
	protected IKoreaDao IKoreaDao;
	/*注入墨西哥进口数据查询组件*/
	@Autowired
	protected IMexicoImportDao IMexicoImportDao;
	/*注入尼加拉瓜出口数据查询组件*/
	@Autowired
	protected INicaraguaExportDao INicaraguaExportDao;
	/*注入尼加拉瓜进口数据查询组件*/
	@Autowired
	protected INicaraguaImportDao INicaraguaImportDao;
	/*注巴基斯坦进口数据查询组件*/
	@Autowired
	protected IPakistanImportDao IPakistanImportDao;
	/*注入巴拿马出口数据查询组件*/
	@Autowired
	protected IPanamaExportDao IPanamaExportDao;
	/*注入巴拿马进口数据查询组件*/
	@Autowired
	protected IPanamaImportDao IPanamaImportDao;
	/*注入巴拉圭出口数据查询组件*/
	@Autowired
	protected IParaguayExportDao IParaguayExportDao;
	/*注入巴拉圭进口数据查询组件*/
	@Autowired
	protected IParaguayImportDao IParaguayImportDao;
	/*注入秘鲁出口数据查询组件*/
	@Autowired
	protected IPeruExportDao IPeruExportDao;
	/*注入秘鲁进口数据查询组件*/
	@Autowired
	protected IPeruImportDao IPeruImportDao;
	/*注入俄罗斯出口数据查询组件*/
	@Autowired
	protected IRussiaExportDao russiaExportDao;
	/*注入俄罗斯进口数据查询组件*/
	@Autowired
	protected IRussiaImportDao russiaImportDao;
	/*注入萨尔瓦多出口数据查询组件*/
	@Autowired
	protected ISalvatoreExportDao ISalvatoreExportDao;
	/*注入萨尔瓦多进口数据查询组件*/
	@Autowired
	protected ISalvatoreImportDao ISalvatoreImportDao;
	/*注入英国数据查询组件*/
	@Autowired
	protected IUkImportDao IUkImportDao;
	/*注入乌克兰进口数据查询组件*/
	@Autowired
	protected IUkraineImportDao ukraineImportDao;
	/*注入乌拉圭出口数据查询组件*/
	@Autowired
	protected IUruguayExportDao IUruguayExportDao;
	/*注入乌拉圭进口数据查询组件*/
	@Autowired
	protected IUruguayImportDao IUruguayImportDao;
	/*注入美国数据查询组件*/
	@Autowired
	protected IUsaImportDao IUsaImportDao;
	/*注入委内瑞拉出口数据查询组件*/
	@Autowired
	protected IVenezuelaExportDao IVenezuelaExportDao;
	/*注入委内瑞拉进口数据查询组件*/
	@Autowired
	protected IVenezuelaImportDao IVenezuelaImportDao;
	/*注入越南出口数据查询组件*/
	@Autowired
	protected IVietnamExportDao IVietnamExportDao;
	/*注入越南进口数据查询组件*/
	@Autowired
	protected IVietnamImportDao IVietnamImportDao;	
	@Autowired 
	protected IBoliviaImportDao boliviaImportDao;
	
	/*
	 * 数据分页
	 */
	public PageBeanUtil getPageBeanUtil(HttpServletRequest request,String num){
		int pageIndex = Integer.valueOf((null == request.getParameter("page"))?"1":(request.getParameter("page")));
		int pageSize = Integer.valueOf((null == request.getParameter("rows"))?(num):(request.getParameter("rows")));
		PageBeanUtil beanUtil = new PageBeanUtil();
		beanUtil.setPageIndex(pageIndex);
		beanUtil.setPageSize(pageSize);
		return beanUtil;
	}
	
	/*
	 * 起始页
	 */
	public int getStartIndex(PageBeanUtil beanUtil)
	{
		int start = (beanUtil.getPageIndex()-1)*beanUtil.getPageSize();
		return start;
	}
	/*
	 * 终止页
	 */
	public int getEndIndex(PageBeanUtil beanUtil,List<?> list,int start) {
		int end = beanUtil.getPageIndex()*beanUtil.getPageSize();
        if(list == null || list.size() == 0)
        {
        	end = 0;
        }else if(list.size()>=start && list.size()<=end)
        {
        	end = list.size();
        }
        return end;
	}
}
