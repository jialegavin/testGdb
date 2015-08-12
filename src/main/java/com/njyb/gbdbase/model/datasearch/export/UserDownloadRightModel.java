package com.njyb.gbdbase.model.datasearch.export;

import java.io.Serializable;

/**
 * 用户下载类
 * 记录用户
 * @author XL
 * @date 2015-03-17
 * @version 标准版
 */

public class UserDownloadRightModel implements Serializable {
	
	/**
	 * 主键
	 */
	private Integer rid;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 总下载数量
	 */
	private Integer totalNum;

	/**
	 * Excel下载条数
	 */
	private Integer excelNum;

	/**
	 * PDF下载条数
	 */
	private Integer pdfNum;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getExcelNum() {
		return excelNum;
	}

	public void setExcelNum(Integer excelNum) {
		this.excelNum = excelNum;
	}

	public Integer getPdfNum() {
		return pdfNum;
	}

	public void setPdfNum(Integer pdfNum) {
		this.pdfNum = pdfNum;
	}

	public UserDownloadRightModel() {
		super();
	}

	public UserDownloadRightModel(Integer userId, Integer excelNum,
			Integer pdfNum) {
		super();
		this.userId = userId;
		this.excelNum = excelNum;
		this.pdfNum = pdfNum;
	}

	
}
