package com.njyb.auth.service.authoritycontrol.component;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.google.common.base.Strings;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;
import com.njyb.gbdbase.service.contrastreport.contrast.component.CountryAllManagerComponent;

/**
 * 海关编码+产品描述+购买整个国家组件类
 * @author 章华才
 *
 */
@Component
public class AuthorityComponentUtil extends MainAuthoriy{

	/**
	 * 判断海关编码是否一致
	 * @param model
	 * @param jsonObject
	 * @return
	 */
	public static boolean isHscodeEquire(ConditionRightModel m,JSONObject jsonObject,String module,String country,UserModel user,List<ConditionRightModel> list){
		boolean bool = false;
		CountryAllManagerComponent allManagerComponent = new CountryAllManagerComponent();
			for (int i = 0; i < list.size(); i++) {
				ConditionRightModel model = list.get(i);
				imexType = Strings.isNullOrEmpty(imexType) ? "" : imexType.trim();
				if(model.getByHsCode()!=null && !"".equals(model.getByHsCode())){
					if((model.getByHsCode().trim().contains(hscode.trim()) || hscode.trim().contains(model.getByHsCode().trim())) && model.getByCountry().trim().equals(country) && model.getiExportType().trim().equals(imexType)){
						bool = true;
							boolean b = false;
							//相同的海关编码不同的时间
							for (ConditionRightModel c : list) {
								if(c.getByCountry().equals("中国")){
									if((c.getByHsCode().contains(hscode) || hscode.contains(c.getByHsCode())) && c.getiExportType().equals(imexType)){
										//是否开放历史记录
										if(c.isOpenHistoryData()){
											if(AuthorityDateUtil.DateEqualsChina3(startDate, endDate, c.getHistoryStartTime(),c.getHistoryEndTime(),c.getStartTime(),c.getEndTime(),country)==false){
												b = true;
											}else{
												b = false;
												//加上同步锁
												synchronized (AuthorityComponentUtil.class) {
													AuthorityFieldModel.getAuthorityFieldMap().put("datestr", allManagerComponent.filterNotDate(allManagerComponent.dateList(listDate(list,"db")), allManagerComponent.dateList(listDate(list,"query"))));
//													AuthorityFieldModel.getAuthorityFieldMap().put("nowDate", c.getStartTime() + "," + c.getEndTime());
//													AuthorityFieldModel.getAuthorityFieldMap().put("historyDate", c.getHistoryStartTime() + "," + c.getHistoryEndTime());
//													AuthorityFieldModel.getAuthorityFieldMap().put("queryDate", AuthorityDateUtil.getDateStr(startDate) + "," + AuthorityDateUtil.getDateStr(endDate));	
												}
												return true;
											}
										}
										//不开放历史记录
										else{
											if(AuthorityDateUtil.DateEqualsChina(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
												b = true;
											}else{
												b = false;
												synchronized (AuthorityComponentUtil.class) {
													AuthorityFieldModel.getAuthorityFieldMap().put("datestr", allManagerComponent.filterNotDate(allManagerComponent.dateList(listDate(list,"db")), allManagerComponent.dateList(listDate(list,"query"))));
													return true;
												}
											}
											
										}
										
									}
								}
							}
							if(b){
								jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
								return false;
							}else{
								return true;
							}
					}
				}
			}
			if(bool){
				 return true;
			}else{
				jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
				return false;
		   }
	}
	
	/**
	 * 控制购买整个国家
	 * @param model
	 * @param jsonObject
	 * @return
	 */
	public static boolean isDateOut(ConditionRightModel model,JSONObject jsonObject,String module,String country,UserModel user,List<ConditionRightModel> list){
		CountryAllManagerComponent allManagerComponent = new CountryAllManagerComponent();
		boolean bool = false;
		for (int i = 0; i < list.size(); i++) {
			if(country.equals(model.getByCountry()) && model.getiExportType().equals(imexType)){
				bool = true;
					boolean b = false;
					//相同的产品描述不同的时间
					for (ConditionRightModel c : list) {
						if(c.getByCountry().equals("中国")){
							if(country.equals(model.getByCountry()) && model.getiExportType().equals(imexType)){
								//是否开放历史记录
								if(c.isOpenHistoryData()){
									if(AuthorityDateUtil.DateEqualsChina3(startDate, endDate, c.getHistoryStartTime(),c.getHistoryEndTime(),c.getStartTime(),c.getEndTime(),country)==false){
										b = true;
									}else{
										b = false;
										synchronized (AuthorityComponentUtil.class) {
											AuthorityFieldModel.getAuthorityFieldMap().put("datestr", allManagerComponent.filterNotDate(allManagerComponent.dateList(listDate(list,"db")), allManagerComponent.dateList(listDate(list,"query"))));
//											AuthorityFieldModel.getAuthorityFieldMap().put("nowDate", c.getStartTime() + "," + c.getEndTime());
//											AuthorityFieldModel.getAuthorityFieldMap().put("historyDate", c.getHistoryStartTime() + "," + c.getHistoryEndTime());
//											AuthorityFieldModel.getAuthorityFieldMap().put("queryDate", AuthorityDateUtil.getDateStr(startDate) + "," + AuthorityDateUtil.getDateStr(endDate));
										}
										return true;
									}
								}
								//不开放历史记录
								else{
									if(AuthorityDateUtil.DateEqualsChina(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
										b = true;
									}else{
										b = false;
										//加上同步块
										synchronized (AuthorityComponentUtil.class) {
											AuthorityFieldModel.getAuthorityFieldMap().put("datestr", allManagerComponent.filterNotDate(allManagerComponent.dateList(listDate(list,"db")), allManagerComponent.dateList(listDate(list,"query"))));
											return true;
										}
									}
									
								}
							}
						}
					}
					if(b){
						jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
						return false;
					}else{
						return true;
					}
			}
		}
		
		if(bool){
			return true;
		}else{
			jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
			return false;
		}
		
	}
	
	
	/**
	 * 赠送免费国家
	 * @param model
	 * @param country
	 * @param map
	 * @param user
	 * @param jsonObject
	 * @return
	 */
	public static boolean giveTreeCountry(ConditionRightModel m,String country,Map map,UserModel user,JSONObject jsonObject,List<ConditionRightModel> list){
		boolean bool = false;
		for (int i = 0; i < list.size(); i++) {
			ConditionRightModel model = list.get(i);
			if(model.getByCountry().equals("中国")){
				//判断是否存在有海关编码的国家
				String countryname = (String) map.get("noHscode");
				//有海关编码的国家
				if(!countryname.contains(country)){
					if(hscode!=null){
						if (model.getByHsCode().contains(hscode) || hscode.contains(model.getByHsCode())) {
							bool = true;
							if(AuthorityDateUtil.DateEqualsOut(startDate,endDate,user.getBeginTime(),AuthorityDateUtil.dateAddThree(user.getBeginTime(),""),country) == false){
								jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
								return false;
							}
							//在正常权限范围内，时间大于库里面的查询的时间，给他一个默认库里面的查询时间
							else{
								//加同步块
								synchronized (AuthorityComponentUtil.class) {
									if(AuthorityDateUtil.isDateSize(endDate, AuthorityDateUtil.dateAddThree(user.getBeginTime(),""), country)){
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", startDate + "," + AuthorityDateUtil.dateAddThree(user.getBeginTime(),""));
										return true;
									}else{
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
										return true;
									}	
								}
							}
						}
					}else{
						jsonObject.put("error", CommonConstantUtil.PROMPT_ISNULL_HSCODE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
						return false;
					}
					
				}
				//没有产品描述的国家
				else{
					//判断数据检索时间是否超出
					if(AuthorityDateUtil.DateEqualsOut(startDate,endDate,user.getBeginTime(),AuthorityDateUtil.dateAddThree(user.getBeginTime(),""),country) == false){
						jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
						return false;
					}
//						//在正常权限范围内，时间大于库里面的查询的时间，给他一个默认库里面的查询时间
					else{
						//加上同步快
						synchronized (AuthorityComponentUtil.class) {
							if(AuthorityDateUtil.isDateSize(endDate, AuthorityDateUtil.dateAddThree(user.getBeginTime(),""), country)){
								AuthorityFieldModel.getAuthorityFieldMap().put("newTime", startDate + "," + AuthorityDateUtil.dateAddThree(user.getBeginTime(),""));
								return true;
							}else{
								AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
								return true;
							}
						}
					}
				}
			}
		}
	    if(bool){
		  return true;
	    }else{
		  jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
		  return false;
	   }
	}
	
	
	/**
	 * 购买国外的国家
	 * @param model
	 * @param country
	 * @param jsonObject
	 * @return
	 */
	public static boolean byCountryOut(ConditionRightModel m,String country,JSONObject jsonObject,List<ConditionRightModel> list){
		boolean bool = false;
		for (int i = 0; i < list.size(); i++) {
		ConditionRightModel model = list.get(i);
		//判断是否购买整个国家
		if(model.getByHsCode() != null && !"".equals(model.getByHsCode()) || model.getByProductDesc() != null  && !"".equals(model.getByProductDesc())){
			//输入的海关编码不是购买的海关编码
			if(model.getByHsCode()!=null && !"".equals(model.getByHsCode()) && country.equals(model.getByCountry()+model.getiExportType())){
				//编码
				if(hscode != null){
					if(imexType!=null){
						//如果等于true的话时间超出
						if((model.getByHsCode().contains(hscode) || hscode.contains(model.getByHsCode())) && country.equals(model.getByCountry()) && model.getiExportType().equals(imexType)){
							bool = true;
							if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, model.getStartTime(),model.getEndTime(),country) == false){
								boolean b = false;
								//相同的产品描述不同的时间
								for (ConditionRightModel c : list) {
									if(c.getByHsCode().contains(hscode) || hscode.contains(c.getByHsCode())){
										if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
											b = true;
										}else{
											b = false;
										}
									}
								}
								if(b){
									jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
									return false;
								}else{
									return true;
								}
							}
							//在正常权限范围内，时间大于库里面的查询的时间，给他一个默认库里面的查询时间
							else{
								synchronized (AuthorityComponentUtil.class) {
								
									if(AuthorityDateUtil.isDateSize(endDate,model.getEndTime(), country)){
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", startDate + "," + model.getEndTime());
										return true;
									}else{
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
										return true;
									}
								}
							}
							
						}
					}else{
						//如果等于true的话时间超出
						if((model.getByHsCode().contains(hscode) || hscode.contains(model.getByHsCode())) && country.equals(model.getByCountry()+model.getiExportType())){
							bool = true;
							if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, model.getStartTime(),model.getEndTime(),country) == false){
								boolean b = false;
								//相同的产品描述不同的时间
								for (ConditionRightModel c : list) {
									if(c.getByHsCode()!=null && !"".equals(c.getByHsCode())){
										if(imexType!=null){
											if(c.getByHsCode().contains(hscode) || hscode.contains(c.getByHsCode()) && country.equals(c.getByCountry()+c.getiExportType())){
												if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
													b = true;
												}else{
													b = false;
												}
											}
										}else{
											if(c.getByHsCode().contains(hscode) || hscode.contains(c.getByHsCode()) && country.equals(c.getByCountry()) && imexType.equals(c.getiExportType())){
												if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
													b = true;
												}else{
													b = false;
												}
											}
										}
									}
								}
								if(b){
									jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
									return false;
								}else{
									return true;
								}
							}
							//在正常权限范围内，时间大于库里面的查询的时间，给他一个默认库里面的查询时间
							else{
								synchronized (AuthorityComponentUtil.class) {
									if(AuthorityDateUtil.isDateSize(endDate,model.getEndTime(), country)){
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", startDate + "," + model.getEndTime());
										return true;
									}else{
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
										return true;
									}	
								}
							}
							
						}
					}
				}else
				{
					//必须输入海关编码
					jsonObject.put("error", CommonConstantUtil.PROMPT_ISNULL_HSCODE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
					return false;
				}
			}
			//产品描述
			if(model.getByProductDesc()!=null && !"".equals(model.getByProductDesc()) && country.equals(model.getByCountry()+model.getiExportType())){
				if(desc != null){
					if(imexType!=null){
						if(model.getByProductDesc().equals(desc) && country.equals(model.getByCountry()) && imexType.equals(model.getiExportType())){
							bool = true;
							if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, model.getStartTime(),model.getEndTime(),country) == false){
								boolean b = false;
								//相同的产品描述不同的时间
								for (ConditionRightModel c : list) {
									if(c.getByProductDesc()!=null){
										if(imexType!=null){
											if(c.getByProductDesc().contains(desc) && country.equals(c.getByCountry()) && imexType.equals(c.getiExportType())){
												if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
													b = true;
												}else{
													b = false;
												}
											}
										}else{
											if(c.getByProductDesc().contains(desc) && country.equals(c.getiExportType()+c.getByCountry())){
												if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
													b = true;
												}else{
													b = false;
												}
											}
										}
									}
									
								}
								if(b){
									jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
									return false;
								}else{
									return true;
								}
							}
							//在正常权限范围内，时间大于库里面的查询的时间，给他一个默认库里面的查询时间
							else{
								synchronized (AuthorityComponentUtil.class) {
									if(AuthorityDateUtil.isDateSize(endDate, model.getEndTime(), country)){
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", startDate + "," + model.getEndTime());
										return true;
									}else{
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
										return true;
									}	
								}
							}
						}
					}else{
						if(model.getByProductDesc().equals(desc) && country.equals(model.getByCountry()+model.getiExportType())){
							bool = true;
							if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, model.getStartTime(),model.getEndTime(),country) == false){
								boolean b = false;
								//相同的产品描述不同的时间
								for (ConditionRightModel c : list) {
									if(c.getByProductDesc()!=null){
										if(imexType!=null){
											if(c.getByProductDesc().contains(desc) && country.equals(c.getByCountry()) && imexType.equals(c.getiExportType())){
												if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
													b = true;
												}else{
													b = false;
												}
											}
										}else{
											if(c.getByProductDesc().equals(desc) && country.equals(c.getByCountry()+c.getiExportType())){
												if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
													b = true;
												}else{
													b = false;
												}
											}
										}
									}
								}
								if(b){
									jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
									return false;
								}else{
									return true;
								}
							}
							//在正常权限范围内，时间大于库里面的查询的时间，给他一个默认库里面的查询时间
							else{
								synchronized (AuthorityComponentUtil.class) {
									if(AuthorityDateUtil.isDateSize(endDate, model.getEndTime(), country)){
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", startDate + "," + model.getEndTime());
										return true;
									}else{
										AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
										return true;
									}	
								}
							}
						}
					}
					
				}else
				{
					//必须输入海关编码
					jsonObject.put("error", CommonConstantUtil.PROMPT_ISNULL_DESC+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
					return false;
				}
			}
		}
		//整个国家
		else{
			if(imexType!=null){
				if(country.equals(model.getByCountry()) && imexType.equals(model.getiExportType())){
					bool = true;
					if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, model.getStartTime(),model.getEndTime(),country) == false){
						boolean b = false;
						//相同的产品描述不同的时间
						for (ConditionRightModel c : list) {
							if(c.getByCountry().equals(country) && c.getiExportType().equals(imexType)){
								if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
									b = true;
								}else{
									b = false;
								}
							}
						}
						if(b){
							jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
							return false;
						}else{
							return true;
						}
					}
					//在正常权限范围内，时间大于库里面的查询的时间，给他一个默认库里面的查询时间
					else{
						synchronized (AuthorityComponentUtil.class) {
							if(AuthorityDateUtil.isDateSize(endDate, model.getEndTime(), country)){
								AuthorityFieldModel.getAuthorityFieldMap().put("newTime",startDate + "," + model.getEndTime());
								return true;
							}else{
								AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
								return true;
							}	
						}
					}
				}
			}else{
				if(country.equals(model.getByCountry()+model.getiExportType())){
					bool = true;
					if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, model.getStartTime(),model.getEndTime(),country) == false){
						boolean b = false;
						//相同的产品描述不同的时间
						for (ConditionRightModel c : list) {
							if(country.equals(c.getByCountry()+c.getiExportType())){
								if(AuthorityDateUtil.DateEqualsOut(startDate, endDate, c.getStartTime(),c.getEndTime(),country)==false){
									b = true;
								}else{
									b = false;
								}
							}
						}
						if(b){
							jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
							return false;
						}else{
							return true;
						}
					}
					//在正常权限范围内，时间大于库里面的查询的时间，给他一个默认库里面的查询时间
					else{
						synchronized (AuthorityComponentUtil.class) {
							if(AuthorityDateUtil.isDateSize(endDate, model.getEndTime(), country)){
								AuthorityFieldModel.getAuthorityFieldMap().put("newTime",startDate + "," + model.getEndTime());
								return true;
							}else{
								AuthorityFieldModel.getAuthorityFieldMap().put("newTime", null);
								return true;
							}	
						}
					}
				}
			}
		}
	  }
	  if(bool){
		  return true;
	  }else{
		  jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
		  return false;
	  }
	}
	
	
	/**
	 * 判断中国购买的哪个产品（hscode/desc）
	 * @param model
	 * @param jsonObject
	 * @param module
	 * @param country
	 * @param request
	 * @return
	 */
	public static boolean chinaHscodeOrDesc(ConditionRightModel m,JSONObject jsonObject,String module,String country,HttpServletRequest request,Map map,List<ConditionRightModel> list){
	   boolean bool = false;
	  for(int i=0; i < list.size(); i++){
		  ConditionRightModel model = list.get(i);
		if(model.getiExportType().equals(imexType) && model.getByCountry().equals("中国")){
			bool = true;
			//是否可下载数据
			map.put("give", "Yes");
			//判断海关编码是否相同
			if(model.getByHsCode()!=null && !"".equals(model.getByHsCode())){
				return AuthorityComponentUtil.isHscodeEquire(model, jsonObject,module,country,(UserModel)request.getSession().getAttribute("user"),list);
			}
//			//购买的产品描述
//			else if(model.getByProductDesc()!=null && !"".equals(model.getByProductDesc())){
//				return AuthorityComponentUtil.isDescEquire(model, jsonObject,module,country,(UserModel)request.getSession().getAttribute("user"),list);
//			}
			//购买整个国家
			else{
				return AuthorityComponentUtil.isDateOut(model, jsonObject,module,country,(UserModel)request.getSession().getAttribute("user"),list);
			}
		 }
	   }
	   
	  if(bool){
		  return false;
	  }else{
			jsonObject.put("error", CommonConstantUtil.PROMPT_DATE+AuthorityFieldModel.getAuthorityFieldMap().get("phone"));
			return false;
		}
	}
	
	
	
	/**
	 * 判断正式用户是否能下载数据
	 * @param list
	 * @return
	 */
	public static boolean judgeIsDownload(List<ConditionRightModel> list,String country){
	
		for (ConditionRightModel c : list) {
			if(c.getByCountry().equals("中国") || c.getByCountry().equals("韩国")){
				if(c.getiExportType().equals(imexType)){
					return true;
				}
			}
			
			else{
				if(country.equals(c.getByCountry()+c.getiExportType())){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 添加时间集合
	 * @param models
	 * @return
	 */
	public static List<String> listDate(List<ConditionRightModel> models,String status){
		
		List<String> list = new ArrayList<String>();
		if(status.equals("db")){
			for (ConditionRightModel c : models) {
				imexType = imexType.trim();
				if((c.getByHsCode().trim().contains(hscode) || hscode.trim().contains(c.getByHsCode())) && c.getByCountry().trim().equals("中国") 
						&& c.getiExportType().trim().equals(imexType)){
					//是否开放历史记录
					if(c.isOpenHistoryData()){
						list.add(c.getStartTime()+","+c.getEndTime());
						list.add(c.getHistoryStartTime()+","+c.getHistoryEndTime());
					}else{
						list.add(c.getStartTime()+","+c.getEndTime());
					}
					
				}
			}
		}
		//页面查询时间
		else{
			list.add(AuthorityDateUtil.getDateStr(startDate)+","+AuthorityDateUtil.getDateStr(endDate));
		}
		return list;
	}
}
