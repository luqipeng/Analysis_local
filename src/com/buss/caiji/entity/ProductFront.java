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
public class ProductFront implements java.io.Serializable {
	/**id*/
	private Integer id;
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
	/**描述*/
	@Excel(name="描述")
	private String productDsr;
	/**productFB*/
	@Excel(name="productFB")
	private String productFaceback;
	/**分类序号*/
	@Excel(name="分类序号")
	private Integer categoryOrders;
	/**产品序号*/
	@Excel(name="产品序号")
	private String productOrders;
	/**店铺ID*/
	@Excel(name="店铺ID")
	private String storeId;
	/**店铺名称*/
	@Excel(name="店铺名称")
	private String storeName;
	/**storeFB*/
	@Excel(name="storeFB")
	private String storeFaceback;
	/**storeFBper*/
	@Excel(name="storeFBper")
	private String storeFacebackper;
	/**分类ID*/
	@Excel(name="分类ID")
	private String categoryId;
	/**分类序号*/
	@Excel(name="分类序号")
	private Integer categoryIndex;
	/**分类分页号*/
	@Excel(name="分类分页号")
	private Integer categoryPageno;
	/**折扣*/
	@Excel(name="折扣")
	private String discount;
	/**日期*/
	@Excel(name="日期")
	private Date date;
	/**序号*/
	@Excel(name="序号")
	private Integer allIndex;
	
	private Integer todayOrders;
	
	private Integer yesterdayOrders;
	
	private Integer isSeries;
	
	private Date lastDate;
	 
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=10)
	public Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品ID
	 */
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
	@Column(name ="CATEGORY_ORDERS",nullable=true,length=10)
	public Integer getCategoryOrders(){
		return this.categoryOrders;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分类序号
	 */
	public void setCategoryOrders(Integer categoryOrders){
		this.categoryOrders = categoryOrders;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品序号
	 */
	@Column(name ="PRODUCT_ORDERS",nullable=true,length=10)
	public String getProductOrders(){
		return this.productOrders;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品序号
	 */
	public void setProductOrders(String productOrders){
		this.productOrders = productOrders;
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
	@Column(name ="STORE_FACEBACK",nullable=true,length=200)
	public String getStoreFaceback(){
		return this.storeFaceback;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  storeFB
	 */
	public void setStoreFaceback(String storeFaceback){
		this.storeFaceback = storeFaceback;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  storeFBper
	 */
	@Column(name ="STORE_FACEBACKPER",nullable=true,length=10)
	public String getStoreFacebackper(){
		return this.storeFacebackper;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  storeFBper
	 */
	public void setStoreFacebackper(String storeFacebackper){
		this.storeFacebackper = storeFacebackper;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分类ID
	 */
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  分类序号
	 */
	@Column(name ="CATEGORY_INDEX",nullable=true,length=10)
	public Integer getCategoryIndex(){
		return this.categoryIndex;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分类序号
	 */
	public void setCategoryIndex(Integer categoryIndex){
		this.categoryIndex = categoryIndex;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  分类分页号
	 */
	@Column(name ="CATEGORY_PAGENO",nullable=true,length=10)
	public Integer getCategoryPageno(){
		return this.categoryPageno;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分类分页号
	 */
	public void setCategoryPageno(Integer categoryPageno){
		this.categoryPageno = categoryPageno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  折扣
	 */
	@Column(name ="DISCOUNT",nullable=true,length=10)
	public String getDiscount(){
		return this.discount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  折扣
	 */
	public void setDiscount(String discount){
		this.discount = discount;
	}
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
	@Column(name ="ALL_INDEX",nullable=true,length=10)
	public Integer getAllIndex(){
		return this.allIndex;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  序号
	 */
	public void setAllIndex(Integer allIndex){
		this.allIndex = allIndex;
	}

	public Integer getTodayOrders() {
		return todayOrders;
	}

	public void setTodayOrders(Integer todayOrders) {
		this.todayOrders = todayOrders;
	}

	public Integer getYesterdayOrders() {
		return yesterdayOrders;
	}

	public void setYesterdayOrders(Integer yesterdayOrders) {
		this.yesterdayOrders = yesterdayOrders;
	}

	public Integer getIsSeries() {
		return isSeries;
	}

	public void setIsSeries(Integer isSeries) {
		this.isSeries = isSeries;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	
	
}
