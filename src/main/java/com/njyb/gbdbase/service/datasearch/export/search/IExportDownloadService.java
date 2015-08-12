package com.njyb.gbdbase.service.datasearch.export.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.njyb.gbdbase.model.datasearch.export.UserDownloadRightModel;

/**
 * @Description 导出EXCEL与PDF权限业务接口层
 * @author XL
 * @date 2015-03-09
 * @version 标准版
 */
public interface IExportDownloadService {
	
	/**
	 * 添加用户下载权限
	 * @param userDownloadRightModel 用户下载权限实体类
	 */
	void insertDownloadRight(UserDownloadRightModel userDownloadRightModel);
	
	/**
	 * 根据用户id查询用户下载数量权限
	 * 在注册时候已经录入用户下载数量权限，在导出excel/pdf之前只需查询即可
	 * @param userId 用户id
	 * @return
	 */
	UserDownloadRightModel queryDownLoadRight(Integer userId);

	/**
	 * 修改用户下载EXCEL/PDF数量记录
	 * @param downloadRightModel 用户下载权限实体类
	 */
	void updateDownloadTypeRecord(UserDownloadRightModel downloadRightModel);
	
	/**
	 * 按月清空下载权限记录
	 */
	void clearDownloadRecord();
	
	/**
	 * 根据用户id修改用户下载权限总记录数
	 * @param downloadRightModel 用户下载权限实体类
	 */
	void updateDownloadTotalNum(UserDownloadRightModel downloadRightModel);
	
	/**
	 * 导出excel/pdf方法
	 * @param type 导出类型值1：excel 2:pdf
	 * @param request
	 * @param response
	 */
	void exportFiles(int type, HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 导出excel/pdf前先判断用户是否满足需求
	 * @param jsonObject json数组
	 * @param type 导出类型值1：excel 2:pdf
	 * @param response
	 * @param request
	 * @return String
	 */
	 <T> String commonExportService(JSONObject jsonObject, int type, HttpServletResponse response, HttpServletRequest request);
	
	/**
	 * 根据用户选择的id获取数据
	 * @param exportList 需要导出的数据集合
	 * @param country 国家名称
	 * @param request
	 * @return List<String[]>
	 */
	<T> List<String[]> getCountryDatas(List<T> exportList, String country, HttpServletRequest request);
}
