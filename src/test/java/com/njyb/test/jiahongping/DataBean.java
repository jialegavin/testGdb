package com.njyb.test.jiahongping;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataBean implements Serializable {
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	private int id;
	private  String importer;
	private String  exporter;
	private int weight=0;
	private int quantity=0;
	private int count=1;
	public DataBean(String importer, int weight, int quantity, int count) {
		super();
		this.importer = importer;
		this.weight = weight;
		this.quantity = quantity;
		this.count = count;
	}

	public DataBean(int weight, int quantity, int count) {
		super();
		this.weight = weight;
		this.quantity = quantity;
		this.count = count;
	}
	
	public String getImporter() {
		return importer;
	}
	public void setImporter(String importer) {
		this.importer = importer;
	}
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public static void main(String[] args) {
		//初始化数据
		List<DataBean>ls=new LinkedList<DataBean>();
		//新增进口商 时间是第一批交易 时间就是按照升序排序 jiahp 201001  
		
		//流失进口商 时间是最后一批交易 时间就是按照降序排序
		ls.add(new DataBean("jhp",201008, 21, 1));
		ls.add(new DataBean("jhp",201001, 21, 1));
		ls.add(new DataBean("jhp", 201002, 22, 1));
		ls.add(new DataBean("jhp", 201006, 22, 1));
		
		ls.add(new DataBean("yy", 201011, 25, 1));
		ls.add(new DataBean("yy", 201012, 26, 1));
		ls.add(new DataBean("yy", 201010, 25, 1));
		ls.add(new DataBean("yy", 201009, 26, 1));
		ls.add(new DataBean("hh", 20103, 27, 1));
		ls.add(new DataBean("hh", 201003, 28, 1));
		ls.add(new DataBean("xl", 201004, 29, 1));
		ls.add(new DataBean("zhc", 201009, 22, 1));
		//存放数值类型的集合
		List<DataBean>lss=null;
		//存放重复数值类型的集合
		List<DataBean>alllist=new LinkedList<DataBean>();
		Map<String, List<DataBean>>mlp=new HashMap<String, List<DataBean>>();
		//保存对应的进口商对应的时间
		Map map=new HashMap();
		for(DataBean data:ls){
			String name=data.getImporter();
			if (mlp.get(name)==null) {
				lss=new LinkedList<DataBean>();
				lss.add(data);
				mlp.put(name, lss);
			}
			else{
				lss=mlp.get(name);
				lss.add(data);
				mlp.put(name, lss);
			}
		}
		//开始累加
		int sumweight = 0;int sumquantity=0;int sumcount=0;
		String name=null;
		for(Object o:mlp.keySet()){
			name=o.toString();
			List<DataBean>ds=mlp.get(o);
			sumweight=0;sumquantity=0;sumcount=0;
			for(DataBean bean:ds){
				sumquantity=sumquantity+bean.getQuantity();
				sumweight=sumweight+bean.getWeight();
				sumcount=sumcount+bean.getCount();
			}
			
			System.out.println(name+":"+sumweight+":"+sumquantity+":"+sumcount);
		}
		
		
	}
}
