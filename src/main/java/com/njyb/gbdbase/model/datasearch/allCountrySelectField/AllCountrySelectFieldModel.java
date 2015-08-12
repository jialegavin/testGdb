package com.njyb.gbdbase.model.datasearch.allCountrySelectField;

/**
 * 所有国家选择下拉框字段
 * @author 章华才
 */
public class AllCountrySelectFieldModel {

	/**
	 * 字段id
	 */
	private Integer id;
	/**
	 * 字段值
	 */
	private String fieldValue;
	/**
	 * 字段名称
	 */
	private String fieldName;
	/**
	 * 国家名称
	 */
	private String countryName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
