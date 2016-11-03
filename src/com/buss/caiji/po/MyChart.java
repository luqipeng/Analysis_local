package com.buss.caiji.po;

import java.util.List;
import java.util.Map;

public class MyChart {  
	  
	//類型
	private String type;
    /*  
     * 指定数据源  
     * 适用于数据统计来自多个数据源时 
     */  
    private int typedb;  
      
    /* 
     * 统计类型 
     * 0线状图，1柱状图，2单饼图，3内嵌饼图 
     */  
    private int typechart;  
      
    /* 
     * 统计时间类型 
     * 0自定义，1天，2月，3年 
     */  
    private int typetime;  
      
    // 封装Line,column传递到jsp页面的数据  
    public List<Serie> series;  
    public List<Serie_pie> series_pie;  
      
    /* 
     * 封装统计查询参数  
     */  
    private List<Emtoy> emtoys;  
    private List<Emtoy> smtoys;//如果是内嵌饼图，这个参数封装的是内嵌饼图的参数数据  
      
    /* 
     * 封装y轴类型时间类型 
     */  
    private String[] categories;//时间参数  
    private String[] categoriesLocal;//时间参数  
    private String categoriesStr;  
      
    /* 
     * 封装自定义的参数 
     */  
    private Map<String,Object> map;  
      
  
    public int getTypedb() {  
        return typedb;  
    }  
    public void setTypedb(int typedb) {  
        this.typedb = typedb;  
    }  
    public int getTypechart() {  
        return typechart;  
    }  
    public void setTypechart(int typechart) {  
        this.typechart = typechart;  
    }  
    public int getTypetime() {  
        return typetime;  
    }  
    public void setTypetime(int typetime) {  
        this.typetime = typetime;  
    }  
    public List<Serie> getSeries() {  
        return series;  
    }  
    public void setSeries(List<Serie> series) {  
        this.series = series;  
    }  
    public List<Serie_pie> getSeries_pie() {  
        return series_pie;  
    }  
    public void setSeries_pie(List<Serie_pie> series_pie) {  
        this.series_pie = series_pie;  
    }  
    public List<Emtoy> getEmtoys() {  
        return emtoys;  
    }  
    public void setEmtoys(List<Emtoy> emtoys) {  
        this.emtoys = emtoys;  
    }  
    public List<Emtoy> getSmtoys() {  
        return smtoys;  
    }  
    public void setSmtoys(List<Emtoy> smtoys) {  
        this.smtoys = smtoys;  
    }  
    public Map<String, Object> getMap() {  
        return map;  
    }  
    public void setMap(Map<String, Object> map) {  
        this.map = map;  
    }  
    public String[] getCategories() {  
        if(categoriesStr==null || categoriesStr.equals("")){  
            return null;  
        }  
        if(categoriesStr.indexOf("#")!=-1){  
            return categoriesStr.split("#");  
        }else{  
            return new String[]{categoriesStr};  
        }  
    }  
    public void setCategories(String[] categories) {  
        this.categories = categories;  
    }  
    public String[] getCategoriesLocal() {  
        return categoriesLocal;  
    }  
    public void setCategoriesLocal(String[] categoriesLocal) {  
        this.categoriesLocal = categoriesLocal;  
    }  
    public String getCategoriesStr() {  
        return categoriesStr;  
    }  
    public void setCategoriesStr(String categoriesStr) {  
        this.categoriesStr = categoriesStr;  
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}  
}  
