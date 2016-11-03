package com.buss.caiji.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 产品表
 * @author onlineGenerator
 * @date 2015-08-06 20:28:19
 * @version V1.0   
 *
 */
@Entity
@Table(name = "product", schema = "")
@SuppressWarnings("serial")
public class ProductEntity implements java.io.Serializable {

	/**产品ID*/
	@Excel(name="产品ID")
	private String productId;
	/**产品名称*/
	@Excel(name="产品名称")
	private String productName;
	/**产品链接地址*/
	@Excel(name="产品链接地址")
	private String productUrl;
	/**图片*/
	@Excel(name="图片")
	private String productImg;
	/**价格*/
	@Excel(name="价格")
	private String productPrice;
	/**最大价格*/
	@Excel(name="最大价格")
	private String maxPrice;
	/**描述*/
	@Excel(name="描述")
	private String productDsr;
	/**productFB*/
	@Excel(name="productFB")
	private String productFaceback;
	/**分类序号*/
	/*@Excel(name="分类序号")
	private Integer categoryOrders;*/
	/**产品今日销量*/
	@Excel(name="产品总销量")
	private String productOrdersAll;
	/**产品昨日销量*/
	@Excel(name="产品昨日销量")
	private String productOrdersYesterday;
	/**产品今日销量*/
	@Excel(name="产品今日销量")
	private String productOrdersToday;
	/**店铺ID*/
	@Excel(name="店铺ID")
	private String storeId;
	/**店铺名称*/
	@Excel(name="店铺名称")
	private String storeName;
	/**storeFB
	@Excel(name="storeFB")
	private String storeFaceback;
	*//**storeFBper*//*
	@Excel(name="storeFBper")
	private String storeFacebackper;*/
	/**分类ID*/
	@Excel(name="分类ID")
	private String categoryId;
	/**折扣*//*
	@Excel(name="折扣")
	private String discount;*/
	/**日期*/
	@Excel(name="日期")
	private Date date;
	/**序号*//*
	@Excel(name="序号")
	private Integer allIndex;*/
	/**日期*/
	@Excel(name="日期")
	private Date lastDate;
	/**是否順序*/
	@Excel(name="順序")
	private Integer isSeries;
	 
	/**是否重复*/
	@Excel(name="順序")
	private Integer isRepeat;

	/**1天销量*/
	/*@Excel(name="1天销量")
	private Integer productOrders1;*/

	/**3天销量*/
	@Excel(name="3天销量")
	private Integer productOrders3;

	/**7天销量*/
	@Excel(name="7天销量")
	private Integer productOrders7;

	
/*	*//**周销量*//*
	@Excel(name="周销量")
	private Integer weekOrders;
	*//**价格*//*
	@Excel(name="今日销售额")
	private String amount;
	*//**价格*//*
	@Excel(name="周销售额")
	private String weekAmount;*/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品ID
	 */
	@Id
	@Column(name ="PRODUCT_ID",nullable=true,length=12)
	public String getProductId(){
		return this.productId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品ID
	 */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品名称
	 */
	@Column(name ="PRODUCT_NAME",nullable=true,length=200)
	public String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品名称
	 */
	public void setProductName(String productName){
		this.productName = productName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品链接地址
	 */
	@Column(name ="PRODUCT_URL",nullable=true,length=200)
	public String getProductUrl(){
		return this.productUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品链接地址
	 */
	public void setProductUrl(String productUrl){
		this.productUrl = productUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */
	@Column(name ="PRODUCT_IMG",nullable=true,length=400)
	public String getProductImg(){
		return this.productImg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setProductImg(String productImg){
		this.productImg = productImg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  价格
	 */
	@Column(name ="PRODUCT_PRICE",nullable=true,length=20)
	public String getProductPrice(){
		return this.productPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  价格
	 */
	public void setProductPrice(String productPrice){
		this.productPrice = productPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="PRODUCT_DSR",nullable=true,length=20)
	public String getProductDsr(){
		return this.productDsr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setProductDsr(String productDsr){
		this.productDsr = productDsr;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  productFB
	 */
	@Column(name ="PRODUCT_FACEBACK",nullable=true,length=200)
	public String getProductFaceback(){
		return this.productFaceback;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  productFB
	 */
	public void setProductFaceback(String productFaceback){
		this.productFaceback = productFaceback;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  分类序号
	 */
	/*@Column(name ="CATEGORY_ORDERS",nullable=true,length=10)
	public Integer getCategoryOrders(){
		return this.categoryOrders;
	}

	*//**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分类序号
	 *//*
	public void setCategoryOrders(Integer categoryOrders){
		this.categoryOrders = categoryOrders;
	}*/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品总销量
	 */
	@Column(name ="PRODUCT_ORDERS_ALL",nullable=true,length=10)
	public String getProductOrdersAll(){
		return this.productOrdersAll;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品总销量
	 */
	public void setProductOrdersAll(String productOrdersAll){
		this.productOrdersAll = productOrdersAll;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品昨日销量
	 */
	@Column(name ="PRODUCT_ORDERS_YESTERDAY",nullable=true,length=10)
	public String getProductOrdersYesterday() {
		return productOrdersYesterday;
	}

	public void setProductOrdersYesterday(String productOrdersYesterday) {
		this.productOrdersYesterday = productOrdersYesterday;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品今日销量
	 */
	@Column(name ="PRODUCT_ORDERS_TODAY",nullable=true,length=10)
	public String getProductOrdersToday(){
		return this.productOrdersToday;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品今日销量
	 */
	public void setProductOrdersToday(String productOrdersToday){
		this.productOrdersToday = productOrdersToday;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  店铺ID
	 */
	@Column(name ="STORE_ID",nullable=true,length=11)
	public String getStoreId(){
		return this.storeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺ID
	 */
	public void setStoreId(String storeId){
		this.storeId = storeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  店铺名称
	 */
	@Column(name ="STORE_NAME",nullable=true,length=200)
	public String getStoreName(){
		return this.storeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺名称
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  storeFB
	 */
	/*@Column(name ="STORE_FACEBACK",nullable=true,length=200)
	public String getStoreFaceback(){
		return this.storeFaceback;
	}

	*//**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  storeFB
	 *//*
	public void setStoreFaceback(String storeFaceback){
		this.storeFaceback = storeFaceback;
	}
	*//**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  storeFBper
	 *//*
	@Column(name ="STORE_FACEBACKPER",nullable=true,length=10)
	public String getStoreFacebackper(){
		return this.storeFacebackper;
	}

	*//**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  storeFBper
	 *//*
	public void setStoreFacebackper(String storeFacebackper){
		this.storeFacebackper = storeFacebackper;
	}*/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分类ID
	 */
	@Id
	@Column(name ="CATEGORY_ID",nullable=true,length=11)
	public String getCategoryId(){
		return this.categoryId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分类ID
	 */
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  折扣
	 */
	/*@Column(name ="DISCOUNT",nullable=true,length=10)
	public String getDiscount(){
		return this.discount;
	}

	*//**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  折扣
	 *//*
	public void setDiscount(String discount){
		this.discount = discount;
	}*/
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日期
	 */
	@Column(name ="DATE",nullable=true)
	public Date getDate(){
		return this.date;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  日期
	 */
	public void setDate(Date date){
		this.date = date;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  序号
	 */
	/*@Column(name ="ALL_INDEX",nullable=true,length=10)
	public Integer getAllIndex(){
		return this.allIndex;
	}

	*//**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  序号
	 *//*
	public void setAllIndex(Integer allIndex){
		this.allIndex = allIndex;
	}*/

	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日期
	 */
	@Column(name ="LAST_DATE",nullable=true)
	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  顺序
	 */
	@Column(name ="IS_SERIES",nullable=true,length=10)
	public Integer getIsSeries(){
		return this.isSeries;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  顺序
	 */
	public void setIsSeries(Integer isSeries){
		this.isSeries = isSeries;
	}

	@Column(name ="IS_REPEAT",nullable=true,length=2)
	public Integer getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}
	
	
	/*@Column(name ="WEEK_ORDERS",nullable=true,length=11)
	public Integer getWeekOrders() {
		return weekOrders;
	}

	public void setWeekOrders(Integer weekOrders) {
		this.weekOrders = weekOrders;
	}
	@Column(name ="AMOUNT",nullable=true,length=12)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Column(name ="WEEK_AMOUNT",nullable=true,length=12)
	public String getWeekAmount() {
		return weekAmount;
	}

	public void setWeekAmount(String weekAmount) {
		this.weekAmount = weekAmount;
	}*/
	/*@Column(name ="PRODUCT_ORDERS1",nullable=true,length=10)
	public Integer getProductOrders1() {
		return productOrders1;
	}

	public void setProductOrders1(Integer productOrders1) {
		this.productOrders1 = productOrders1;
	}*/

	@Column(name ="PRODUCT_ORDERS3",nullable=true,length=10)
	public Integer getProductOrders3() {
		return productOrders3;
	}

	public void setProductOrders3(Integer productOrders3) {
		this.productOrders3 = productOrders3;
	}

	@Column(name ="PRODUCT_ORDERS7",nullable=true,length=10)
	public Integer getProductOrders7() {
		return productOrders7;
	}

	public void setProductOrders7(Integer productOrders7) {
		this.productOrders7 = productOrders7;
	}

	@Column(name ="MAX_PRICE",nullable=true,length=20)
	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
}
