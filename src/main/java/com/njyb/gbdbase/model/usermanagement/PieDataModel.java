package com.njyb.gbdbase.model.usermanagement;
/**
 * 饼状图模型
 * @author 章华才
 * 2015年4月21日
 */
public class PieDataModel {
   /**
    * 名称
    */
	private String name;
	/**
	 * 值
	 */
	private Object value;
	public PieDataModel(){};
	public PieDataModel(String name,Object d){
		this.name = name;
		this.value = d;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
