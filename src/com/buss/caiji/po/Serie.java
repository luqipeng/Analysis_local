package com.buss.caiji.po;

public class Serie {  
    // 系列名称  
    public String name;  
    // 封装线状图或柱状图数据  
    public double data[];  
    public String typechart;
    public String type; 
    
    public Serie(String name, double[] data, String typechart,String type) {
		super();
		this.name = name;
		this.data = data;
		this.typechart = typechart;
		this.type = type;
	}
	public void setName(String name) {  
        this.name = name;  
    }  
    public double[] getData() {  
        return data;  
    }  
    public void setData(double[] data) {  
        this.data = data;  
    }  
    
    public String getTypechart() {
		return typechart;
	}
	public void setTypechart(String typechart) {
		this.typechart = typechart;
	}
	public String getName() {  
        return name;  
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}  
