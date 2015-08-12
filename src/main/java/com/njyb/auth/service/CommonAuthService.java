package com.njyb.auth.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.datasearch.chinaEight.ChinaEightModel;
import com.njyb.gbdbase.model.datasearch.korea.KoreaModel;
import com.njyb.gbdbase.model.datasearch.uk.UkImportModel;

/**
 * 所有具体授权对应的子类有可能需要用到的共同属性或者共同方法 都统一放在这个类来进行管理
 * 
 * @author jiahp
 *
 */
public class CommonAuthService {

	private static final Logger log = Logger.getLogger(CommonAuthService.class);

	/**
	 * 按次用户
	 */
	public static final String COUNT_USER = "按次用户";

	/**
	 * 正式用户
	 */
	public static final String VIP_USER = "正式用户";

	/**
	 * 每次查看的费用
	 */
	public static final int LOOK_PRICE = 10;

	/**
	 * 按次用户的核心字段过滤业务方法<br>
	 * 过滤集合中存在的特殊字段为★★★★★<br>
	 * 后期优化 不通过反射设置值<br>
	 * 
	 * @param paramList
	 * @return
	 */
	public static <T> List<T> formatListT(HttpServletRequest request,
			List<T> paramList) {
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		if (userModel.getUserDesc().equals(COUNT_USER)) {
			for (T t : paramList) {
				try {
					// 中国 英国 韩国
					if (t instanceof ChinaEightModel || t instanceof KoreaModel|| t instanceof UkImportModel) {
						BeanUtils.setProperty(t, "companyName", "★★★★★");
					} else {
						// 其他国家 进出口商
						BeanUtils.setProperty(t, "importer", "★★★★★");
						BeanUtils.setProperty(t, "exporter", "★★★★★");
					}
					BeanUtils.setProperty(t, "contact", "★★★★★");
					BeanUtils.setProperty(t, "tel", "★★★★★");
					BeanUtils.setProperty(t, "address", "★★★★★");
				} catch (IllegalAccessException e) {
					log.debug(e);
				} catch (InvocationTargetException e) {
					log.debug(e);
				}
			}
		}
		return paramList;
	}
}