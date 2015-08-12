package com.njyb.gbdbase.model.usermanagement;

import java.util.List;

/**
 * echart混合数据（折线图、柱形图、x轴坐标的数据）
 * @author 章华才
 * 2015年4月21日
 */
public class MixDataModel {
	/**
	 * 数据类型
	 * line:折线图
	 * bar:柱形图
	 * x:轴坐标
	 */
    private String type;
    /**
     * 该图形的名称
     */
    private String name;
    
    /**
     * y轴的位置 
     *  0：左y轴  1：是右y轴  
     *  默认 0
     */
    private int   yIndex;
	
    /**
     * 该图形的数据
     */
    private Object[] data;
    /**
     * 是否显示
     * 默认显示
     */
    private int init;
    
    public MixDataModel() {
		super();
	}
	public MixDataModel(String type, String name, int yIndex, Object[] data,int init) {
		super();
		this.type = type;
		this.name = name;
		this.yIndex = yIndex;
		this.data = data;
		this.init = init;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getyIndex() {
		return yIndex;
	}
	public void setyIndex(int yIndex) {
		this.yIndex = yIndex;
	}
	public Object[] getData() {
		return data;
	}
	public void setData(Object[] data) {
		this.data = data;
	}
	public int getInit() {
		return init;
	}
	public void setInit(int init) {
		this.init = init;
	}
}
