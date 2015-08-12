package com.njyb.gbdbase.service.authcenter.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.AuthCenterConstantUtil;
import com.njyb.gbdbas.util.DataSearchConstantUtil;
import com.njyb.gbdbase.dao.admincenter.IAuthorityDao;
import com.njyb.gbdbase.dao.authcenter.ICountRightDao;
import com.njyb.gbdbase.dao.authcenter.IUserRightDao;
import com.njyb.gbdbase.dao.usermanagement.IRightDao;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.authcenter.CountRightModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.service.authcenter.IPermissionService;
/**
 * 用户权限验证服务
 * @author chenhu
 * 2015年4月27日
 */
@Service
public class PermissionService implements IPermissionService {
	
	// 引入日志
	private static final Logger log=Logger.getLogger(PermissionService.class);
	@Autowired
	private IRightDao rightDao;
	@Autowired
	private ICountRightDao countRightDao;
	@Autowired
	private IUserRightDao userRightDao;
	
	@Autowired
	private IAuthorityDao authorityDao;
	/**
	 * 备注：该方法暂时未考虑国际化
	 */
	@Override
	public String checkHaveSearchRight(UserModel user,
			ConditionRightModel conditionModel) {
		String flag=DataSearchConstantUtil.NO_USER;
		try{
			if(user!=null&&!("".equals(user.getUserDesc()))){
				log.info("判断用户是否具有该查询条件的权限-输入：用户ID="+user.getUserId()+" 权限范围=（国家："+conditionModel.getByCountry()
						+"、起始时间："+conditionModel.getStartTime()+"、结束时间:"+conditionModel.getEndTime()+""
								+ "、进出口类型:"+conditionModel.getiExportType()+"、hsCode:"+conditionModel.getByHsCode()+"、desc:"+conditionModel.getByProductDesc());
				if(("按次用户").equals(user.getUserDesc()))
				{
					//如果该用户是按次用户直接返回
					flag=DataSearchConstantUtil.COUNT_USER;
				}
				else
				{
					ConditionRightModel condition=diffContry(conditionModel);
					condition.setUserId(user.getUserId());
					//首先查询该用户是否具有该权限
					int num=rightDao.checkHaveSearchRight(condition);
					if(num>0)
					{
						flag=DataSearchConstantUtil.CONDITION_TRUE_USER;
					}
					else
					{
						flag=DataSearchConstantUtil.CONDITION_FALSE_USER;
					}
				}
			
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		log.info("判断用户是否具有该查询条件的权限-返回=flag:"+flag);
		return flag;
	}
	@Override
	public String checkHaveDetailRight(UserModel user) {
		String result=DataSearchConstantUtil.NO_USER;
		try{
			if(user!=null&&!("".equals(user.getUserDesc()))){
				log.info("判断该用户是否具有查看明细的权限-输入：用户ID="+user.getUserId());
				if("按次用户".equals(user.getUserDesc())){
					//首先查看该用户是否还有查看的次数
					CountRightModel countRight=countRightDao.queryCountRightModelById(user);
					if(countRight.getRemainnum()>0)
					{
						//返回成功
						result=DataSearchConstantUtil.COUNT_USER;
						//剩余次数减1
						int remainNum=countRight.getRemainnum()-1;
						countRight.setRemainnum(remainNum);
						countRightDao.updateCountRight(countRight);
					}
					else
					{
						//返回次数以用完
						result=DataSearchConstantUtil.COUNT_USER_OVER;
					}
					
				}
				else
				{
					//如果是正式用户具有查看明细的权限
					result=DataSearchConstantUtil.CONDITION_TRUE_USER;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			log.debug(e.toString());
		}
		return result;
	}
	/**
	 * 重新组装查询条件 如：阿根廷出口 组装成 阿根廷+出口
	 * @param conditionModel
	 * @return
	 */
	private ConditionRightModel diffContry(ConditionRightModel conditionModel){
		ConditionRightModel condition=new ConditionRightModel();
		  if(conditionModel!=null)
		  {
			  condition.setByHsCode(conditionModel.getByHsCode());
			  condition.setByProductDesc(conditionModel.getByProductDesc());
			  condition.setEndTime(conditionModel.getEndTime());
			  condition.setStartTime(conditionModel.getStartTime());
			  //判断进出口是否为空
			  if((conditionModel.getiExportType()!=null)&&!("".equals(conditionModel.getiExportType()))){
				  condition.setByCountry(conditionModel.getByCountry());
				  condition.setiExportType(conditionModel.getiExportType());
			  }else{
				 //重新组装
				  String county=conditionModel.getByCountry();
				  condition.setiExportType(county.substring(county.length()-2, county.length()));
				  condition.setByCountry(county.substring(0,county.length()-2));
			  }
		  }
		return condition;
	}
	
	/**
	 * 根据用户Id查询用户权限
	 */
	@Override
	public List<ConditionRightModel> queryUserRight(UserModel userModel) {
		//切换数据库
		
		List<ConditionRightModel> userRightList = null;
		userRightList = userRightDao.queryUserRightResultByUserIdAndPage(userModel.getUserId());
		return null == userRightList ? new ArrayList<ConditionRightModel>() : userRightList;
	}

	/**
	 * 添加用户权限 用于在定制时调用此方法,添加用户的权限
	 */
	@Override
	public int insertUserRight(ConditionRightModel userRightModel) {
		int result = 0;
		if (null != userRightModel) {
			result = userRightDao.insertUserRigth(userRightModel);
		}
		return result;
	}
	
	/**
	 * 判断用户是否有该模块权限
	 * @param module
	 * @return String 返回判断结果
	 */
	public String getRightByUser(HttpServletRequest request,Map map,String module) throws Exception
	{
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		//用户权限
		String userRight = (String) map.get(module);
		if(userRight.contains(userModel.getUserDesc()))
		{
			return AuthCenterConstantUtil.HAVE_PERMISSION;
		}else
		{
			return AuthCenterConstantUtil.NO_PERMISSION;
		}
	}
	
	@Override
	public List<ConditionRightModel> getConditionRight(int userId) {
		List<ConditionRightModel> list = null;
		try {
			list = authorityDao.queryJurisdictInfo(userId);
		} catch (Exception e) {
			log.debug(e);
		}
		return list;
	}
}
