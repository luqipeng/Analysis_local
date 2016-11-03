package com.buss.caiji.po;

import java.util.List;

import com.buss.caiji.po.Series;

public class ProductPO {

	private List<Series> series;
	private List<String> categories;
	

	public List<Series> getSeries() {
		return series;
	}


	public void setSeries(List<Series> series) {
		this.series = series;
	}


	public List<String> getCategories() {
		return categories;
	}


	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


}
