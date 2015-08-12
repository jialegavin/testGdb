package com.njyb.gbdbase.model.usermanagement;
/**
 * 系统编码表
 * @author chenhu
 * 2015年4月13日
 */
public class ViewModel {
  //编码ID
  private int codeId;
  //编码
  private String code;
  //值
  private String value;
  //类型
  private String type;
  //关联编码
  private String rela;
  //语言
  private String language;
public int getCodeId() {
	return codeId;
}
public void setCodeId(int codeId) {
	this.codeId = codeId;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getRela() {
	return rela;
}
public void setRela(String rela) {
	this.rela = rela;
}
}