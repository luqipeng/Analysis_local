package com.buss.caiji.po;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * Created by LuQP on 2016/7/13.
 */
public class ProductSearchPo {

    /**��ƷID*/
    private String productId;
    /**��Ʒ����*/
    private String productName;
    /**��Ʒ���ӵ�ַ*/
    private String productUrl;
    /**ͼƬ*/
    private String productImg;
    /**�۸�*/
    private String productPrice;
    /**����*/
    private String productDsr;
    /**productFB*/
    private String productFaceback;
    /**�������*/
    private Integer categoryOrders;
    private String productOrdersAll;
    /**��Ʒ��������*/
    private String productOrdersYesterday;
    /**��Ʒ��������*/
    private String productOrdersToday;
    /**����ID*/
    private String storeId;
    /**��������*/
    private String storeName;
    /**storeFB*/
    private String storeFaceback;
    /**storeFBper*/
    private String storeFacebackper;
    /**����ID*/
    private String categoryId;
    /**�Ƿ����*/
    private String exist;
    private String wid;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDsr() {
        return productDsr;
    }

    public void setProductDsr(String productDsr) {
        this.productDsr = productDsr;
    }

    public String getProductFaceback() {
        return productFaceback;
    }

    public void setProductFaceback(String productFaceback) {
        this.productFaceback = productFaceback;
    }

    public Integer getCategoryOrders() {
        return categoryOrders;
    }

    public void setCategoryOrders(Integer categoryOrders) {
        this.categoryOrders = categoryOrders;
    }

    public String getProductOrdersAll() {
        return productOrdersAll;
    }

    public void setProductOrdersAll(String productOrdersAll) {
        this.productOrdersAll = productOrdersAll;
    }

    public String getProductOrdersYesterday() {
        return productOrdersYesterday;
    }

    public void setProductOrdersYesterday(String productOrdersYesterday) {
        this.productOrdersYesterday = productOrdersYesterday;
    }

    public String getProductOrdersToday() {
        return productOrdersToday;
    }

    public void setProductOrdersToday(String productOrdersToday) {
        this.productOrdersToday = productOrdersToday;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreFaceback() {
        return storeFaceback;
    }

    public void setStoreFaceback(String storeFaceback) {
        this.storeFaceback = storeFaceback;
    }

    public String getStoreFacebackper() {
        return storeFacebackper;
    }

    public void setStoreFacebackper(String storeFacebackper) {
        this.storeFacebackper = storeFacebackper;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }
}
