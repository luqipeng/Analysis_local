package com.buss.caiji.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;

import com.buss.compare.entity.CommodityAnalysisEntity;
import com.buss.compare.entity.CommodityManageEntity;
import com.buss.compare.entity.SkuProductEntity;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 店铺产品
 * @author onlineGenerator
 * @date 2015-08-06 20:31:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "store_product", schema = "")
@SuppressWarnings("serial")
public class StoreProductEntity implements java.io.Serializable {

	private SkuProductEntity skuProduct;
	private CommodityManageEntity commodityManageEntity;
	private CommodityAnalysisEntity commodityAnalysisEntity;

	/**产品ID*/
	@Excel(name="产品ID")
	private String productId;
	/**产品名称*/
	@Excel(name="产品名称")
	private String productName;
	/**产品地址*/
	@Excel(name="产品地址")
	private String productUrl;
	/**图片*/
	@Excel(name="图片")
	private String productImg;
	/**价格*/
	@Excel(name="价格")
	private String productPrice;
	@Excel(name="价格")
	private String minPrice;
	/**描述*/
	@Excel(name="描述")
	private String productDsr;
	/**productFB*/
	@Excel(name="productFB")
	private String productFaceback;
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
	/**折扣*/
	@Excel(name="折扣")
	private String discount;
	/**日期*/
	@Excel(name="日期")
	private Date date;

	private String sellerAdminSeq;

	private String firstTime;

	/**上架或下架
	 * 通过每日采集来判断是否上架还是下架
	 * **/
	private String type;

	private String sku;

	/**
	 * 銷量
	 */
//	private String orders;


	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	public CommodityManageEntity getCommodityManageEntity() {
		return commodityManageEntity;
	}

	public void setCommodityManageEntity(CommodityManageEntity commodityManageEntity) {
		this.commodityManageEntity = commodityManageEntity;
	}

	/*@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	public SkuProductEntity getSkuProduct() {
		return skuProduct;
	}

	public void setSkuProduct(SkuProductEntity skuProduct) {
		this.skuProduct = skuProduct;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	public CommodityAnalysisEntity getCommodityAnalysisEntity() {
		return commodityAnalysisEntity;
	}

	public void setCommodityAnalysisEntity(CommodityAnalysisEntity commodityAnalysisEntity) {
		this.commodityAnalysisEntity = commodityAnalysisEntity;
	}*/

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品ID
	 */
	@Id
	@Column(name ="PRODUCT_ID",nullable=true,length=12,insertable= false,updatable=false)
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
	 *@return: java.lang.String  产品地址
	 */
	@Column(name ="PRODUCT_URL",nullable=true,length=200)
	public String getProductUrl(){
		return this.productUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品地址
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


	@Column(name ="MIN_PRICE",nullable=true,length=20)
	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	@Column(name ="seller_admin_seq",nullable=true,length=11)
	public String getSellerAdminSeq() {
		return sellerAdminSeq;
	}

	public void setSellerAdminSeq(String sellerAdminSeq) {
		this.sellerAdminSeq = sellerAdminSeq;
	}

	@Column(name ="first_time",nullable=true)
	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	@Column(name ="type",nullable=true,length=2)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name ="sku",nullable=true,length=50)
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}
