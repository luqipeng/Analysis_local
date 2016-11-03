package com.buss.caiji.po;

public class Serie_pie {  
    // 系列名称  
    public String name;  
    // 封装饼状图数据  
    public Object data[][];  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public Object[][] getData() {  
        return data;  
    }  
    public void setData(Object[][] data) {  
        this.data = data;  
    }  
}  
