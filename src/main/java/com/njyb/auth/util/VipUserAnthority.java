package com.njyb.auth.util;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.njyb.gbdbase.model.admincenter.AuthorityModel;
import com.njyb.gbdbase.model.admincenter.UserModel;
/**
 * 收费用户/正式用户
 * @author 章华才
 */
public class VipUserAnthority extends MainAuthoriy{

	private boolean isTrue = false;
	
	/**
	 * 判断海关编码是否一致
	 * @param model
	 * @param jsonObject
	 * @return
	 */
	public boolean isHscodeEquire(AuthorityModel model,JSONObject jsonObject,String module,String country,UserModel user){
		if(model.getByhscode().contains(hscode) || hscode.contains(model.getByhscode())){
			//是否查看历史数据
			if(model.getOpenHistoryData() == 0){
				if(module.equals("2")){
					if(AuthorityDateUtil.isDateEquals(endDate, model.getEndtime()) == true){
						jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
						return false;
					}
				}
				//如果日期相对的话
				else if(AuthorityDateUtil.DateEquals(startDate, endDate, model.getStarttime(),model.getEndtime(),country)==true){
					jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
					return false;
				}
			}
			//查看历史记录
			else{
				//如果等于true的话时间超出
				if(AuthorityDateUtil.isDateEquals(user.getEndTime(),endDate, model.getEndtime()) == true){
					jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
					return false;
				}
			}
		}else{
			jsonObject.put("error", "对不起,你查询的编码不在你的权限范围内！");
			return false;
		}
		return true;
	}
	
	
	/**
	 * 判断产品描述是否一致
	 * @param model
	 * @param jsonObject
	 * @return
	 */
	public boolean isDescEquire(AuthorityModel model,JSONObject jsonObject,String module,String country){
		if(model.getByproduct_desc().equals(desc)){
			//是否查看历史数据
			if(model.getOpenHistoryData() == 0){
				if(module.equals("2")){
					if(AuthorityDateUtil.isDateEquals(endDate, model.getEndtime()) == true){
						jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
						return false;
					}
				}
				//如果日期相对的话
				else if(AuthorityDateUtil.DateEquals(startDate, endDate, model.getStarttime(),model.getEndtime(),country)==false){
					jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
					return false;
				}
			}
			//查看历史记录
			else{
				//如果等于true的话时间超出
				if(AuthorityDateUtil.isDateEquals(endDate, model.getEndtime()) == true){
					jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
					return false;
				}
			}
		}else{
			jsonObject.put("error", "对不起,你查询的产品描述不在你的权限范围内！");
			return false;
		}
		return true;
	}
	
	/**
	 * 控制时间
	 * @param model
	 * @param jsonObject
	 * @return
	 */
	public boolean isDateOut(AuthorityModel model,JSONObject jsonObject,String module,String country){
		//是否查看历史数据
		if(model.getOpenHistoryData() == 0){
			if(module.equals("2")){
				if(AuthorityDateUtil.isDateEquals(endDate, model.getEndtime()) == true){
					jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
					return false;
				}
			}
			//如果日期相对的话
			else if(AuthorityDateUtil.DateEquals(startDate, endDate, model.getStarttime(),model.getEndtime(),country)==true){
				jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
				return false;
			}
		}
		//查看历史记录
		else{
			//如果等于true的话时间超出
			if(AuthorityDateUtil.isDateEquals(endDate, model.getEndtime()) == true){
				jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 购买中国的
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @return
	 */
	public boolean byChinaMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<AuthorityModel> list,String module,Map map){
		for(int i=0;i<list.size();i++){
			 	AuthorityModel model = list.get(i);
				//用户选择中国
				if(country.equals("中国")){
					//等于中国
					if(country.equals(model.getBycountry())){
						//判断国家进出口
						if(model.getIexporttype()!=null && !"".equals(model.getIexporttype())){
							String type = null;
							//将进口/出口转化为I/E
							if(model.getIexporttype().equals("进口")){
								type = "I";
							}else if(model.getIexporttype().equals("出口")){
								type = "E";
							}
							if(type.equals(imexType)){
								isTrue = true;
								//判断海关编码是否相同
								if(model.getByhscode()!=null && !"".equals(model.getByhscode())){
									return isHscodeEquire(model, jsonObject,module,country,(UserModel)request.getSession().getAttribute("user"));
								}
								//购买的产品描述
								else if(model.getByproduct_desc()!=null && !"".equals(model.getByproduct_desc())){
									return isDescEquire(model, jsonObject,module,country);
								}
								//购买整个国家
								else{
									return isDateOut(model, jsonObject,module,country);
								}
							}
						}
					}
				}
				//用户选择赠送国外数据
				else if(!country.equals("中国")){
					isTrue = true;
					//是否赠送国外数据下载
					request.getSession().setAttribute("isGive", "No");
					UserModel user = (UserModel) request.getSession().getAttribute("user");
					//如果使用3个月已到
					if(AuthorityDateUtil.isDateEquals(AuthorityDateUtil.dateAddThree(user.getBeginTime(),country),country)){
						jsonObject.put("error", "对不起,你赠送的国外数据期限已到！");
						return false;
					}else{
						//判断是否购买整个国家
						if(model.getByhscode() != null && !"".equals(model.getByhscode()) || model.getByproduct_desc() != null  && !"".equals(model.getByproduct_desc())){
							//输入的海关编码不是购买的海关编码
							if(model.getByhscode()!=null && !"".equals(model.getByhscode())){
								//判断是否存在有海关编码的国家
								String countryname = (String) map.get("noHscode");
								if(!countryname.contains(country)){
									//编码
									if(hscode != null){
										//如果等于true的话时间超出
										if(model.getByhscode().contains(hscode) || hscode.contains(model.getByhscode())){
											if(AuthorityDateUtil.isDateEquals(user.getEndTime(),endDate,country)){
												jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
												return false;
											}
										}else{
											jsonObject.put("error", "对不起,你查询的编码不在你的权限范围内！");
											return false;
										}
									}else{
										jsonObject.put("error", "对不起,海关编码必填！");
										return false;
									}
								}else{
									//判断数据检索时间是否超出
									if(AuthorityDateUtil.isDateEquals(user.getEndTime(),endDate,country)){
										jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
										return false;
									}
								}
							}
							//产品描述
							if(model.getByproduct_desc()!=null && !"".equals(model.getByproduct_desc())){
								if(desc != null){
									if(!model.getByproduct_desc().equals(desc)){
										jsonObject.put("error", "对不起,你查询的产品描述不在你的权限范围内！");
										return false;
									}else{
										if(AuthorityDateUtil.isDateEquals(user.getEndTime(),endDate,country)){
											jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
											return false;
										}
									}
								}else{
									jsonObject.put("error", "对不起,产品描述必填！");
									return false;
								}
							}
						}
						//整个国家
						else{
							if(AuthorityDateUtil.isDateEquals(user.getEndTime(),endDate,country)){
								jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
								return false;
							}
						}
					}
				}
		}
		 if (isTrue == false) {
			jsonObject.put("error", "对不起,你购买的国家不在权限范围内！");
			return false;
		}
		return true;
	}
	
	/**
	 * 购买国外
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @return
	 */
	public boolean byCountryOutMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<AuthorityModel> list,String module,Map map){
		for (int i = 0; i < list.size(); i++) {
			AuthorityModel model = list.get(i);
			//判断国家是否一致
			if(country.equals(model.getBycountry()+model.getIexporttype())){
				isTrue = true;
				//判断是否购买整个国家
				if(model.getByhscode() != null && !"".equals(model.getByhscode()) || model.getByproduct_desc() != null  && !"".equals(model.getByproduct_desc())){
					
					//输入的海关编码不是购买的海关编码
					if(model.getByhscode()!=null && !"".equals(model.getByhscode())){
						//编码
						if(hscode != null){
							//如果等于true的话时间超出
							if(model.getByhscode().contains(hscode) || hscode.contains(model.getByhscode())){
								if(AuthorityDateUtil.isDateEquals(model.getEndtime(),endDate,country)){
									jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
									return false;
								}
							}else{
								jsonObject.put("error", "对不起,你查询的编码不在你的权限范围内！");
								return false;
							}
						}else{
							jsonObject.put("error", "对不起,海关编码必填!");
							return false;
						}
					}
					//产品描述
					if(model.getByproduct_desc()!=null && !"".equals(model.getByproduct_desc())){
						if(desc != null){
							if(!model.getByproduct_desc().equals(desc)){
								jsonObject.put("error", "对不起,你查询的产品描述不在你的权限范围内！");
								return false;
							}else{
								if(AuthorityDateUtil.isDateEquals(model.getEndtime(),endDate,country)){
									jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
									return false;
								}
							}
						}else{
							jsonObject.put("error", "对不起,产品描述必填！");
							return false;
						}
					}
				}
				//整个国家
				else{
					if(AuthorityDateUtil.isDateEquals(model.getEndtime(),endDate,country)){
						jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
						return false;
					}
				}
			}
		}
		
		if (isTrue == false){
			jsonObject.put("error", "对不起,你购买的国家不在权限范围内！");
			return false;
		}
		return true;
	}
	
	/**
	 * 购买中国加国外
	 * @param request
	 * @param queryValue
	 * @param queryKey
	 * @param country
	 * @return
	 */
	public boolean byChinaAndCountryOutMethod(HttpServletRequest request,String queryValue,String queryKey,String country,JSONObject jsonObject,List<AuthorityModel> list,String module,Map map){
		for(int i=0;i<list.size();i++){
		 	AuthorityModel model = list.get(i);
			//用户选择中国
			if(country.equals("中国")){
				//等于中国
				if(country.equals(model.getBycountry())){
					//判断国家进出口
					if(model.getIexporttype()!=null && !"".equals(model.getIexporttype())){
						String type = null;
						//将进口/出口转化为I/E
						if(model.getIexporttype().equals("进口")){
							type = "I";
						}else if(model.getIexporttype().equals("出口")){
							type = "E";
						}
						if(type.equals(imexType)){
							isTrue = true;
							//判断海关编码是否相同
							if(model.getByhscode()!=null && !"".equals(model.getByhscode())){
								return isHscodeEquire(model, jsonObject,module,country,(UserModel)request.getSession().getAttribute("user"));
							}
							//购买的产品描述
							else if(model.getByproduct_desc()!=null && !"".equals(model.getByproduct_desc())){
								return isDescEquire(model, jsonObject,module,country);
							}
							//购买整个国家
							else{
								return isDateOut(model, jsonObject,module,country);
							}
						}
					}
				}
			}
			// 中国家国外
			else if(!country.equals("中国")){
				//判断国家是否一致
				if(country.equals(model.getBycountry()+model.getIexporttype())){
					isTrue = true;
					//判断是否购买整个国家
					if(model.getByhscode() != null || !"".equals(model.getByhscode()) && model.getByproduct_desc() != null  || !"".equals(model.getByproduct_desc())){
						//输入的海关编码不是购买的海关编码
						if(model.getByhscode()!=null && !"".equals(model.getByhscode())){
							//编码
							if(hscode != null){
								//如果等于true的话时间超出
								if(model.getByhscode().contains(hscode) || hscode.contains(model.getByhscode())){
									if(AuthorityDateUtil.isDateEquals(model.getEndtime(),endDate,country)){
										jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
										return false;
									}
								}else{
									jsonObject.put("error", "对不起,你查询的编码不在你的权限范围内！");
									return false;
								}
							}else{
								jsonObject.put("error", "对不起,海关编码必填！");
								return false;
							}
						}
						//产品描述
						if(model.getByproduct_desc()!=null && !"".equals(model.getByproduct_desc())){
							if(desc != null){
								if(!model.getByproduct_desc().equals(desc)){
									jsonObject.put("error", "对不起,你查询的产品描述不在你的权限范围内！");
									return false;
								}else{
									if(AuthorityDateUtil.isDateEquals(model.getEndtime(),endDate,country)){
										jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
										return false;
									}
								}
							}else{
								jsonObject.put("error", "对不起,产品描述必填！");
								return false;
							}
						}
					}
					//整个国家
					else{
						if(AuthorityDateUtil.isDateEquals(model.getEndtime(),endDate,country)){
							jsonObject.put("error", "对不起,你查询的时间不在你的权限范围内！");
							return false;
						}
					}
				}
			}
		}
		
		if (isTrue == false){
			jsonObject.put("error", "对不起,你查询的国家不在你的权限范围内！");
			return false;
		}
		return true;
	}
}
