package com.buss.caiji.po;

import java.util.List;

import com.buss.caiji.entity.ProductEntity;
import com.buss.caiji.entity.ProductFront;
import com.buss.tag.entity.TagEntity;

public class TagProductPO {

	private TagEntity tag;
	private List<ProductFront> products;
	private MainTabPo mtp;
	
	public TagEntity getTag() {
		return tag;
	}
	public void setTag(TagEntity tag) {
		this.tag = tag;
	}
	public List<ProductFront> getProducts() {
		return products;
	}
	public void setProducts(List<ProductFront> products) {
		this.products = products;
	}
	public MainTabPo getMtp() {
		return mtp;
	}
	public void setMtp(MainTabPo mtp) {
		this.mtp = mtp;
	}
	
	
}
