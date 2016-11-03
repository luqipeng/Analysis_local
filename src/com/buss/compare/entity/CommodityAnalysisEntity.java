package com.buss.compare.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;

import com.buss.caiji.entity.StoreProductEntity;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: commodity_analysis
 * @author onlineGenerator
 * @date 2016-02-04 11:04:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "commodity_analysis", schema = "")
@SuppressWarnings("serial")
public class CommodityAnalysisEntity implements java.io.Serializable {
	


	private SkuProductEntity sku;

	private StoreProductEntity storeProduct;

	private Integer id;
	/**cId*/
	@Excel(name="商品ID")
	private String c_Id;
	/**cTitle*/
	@Excel(name="商品标题")
	private String cTitle;
	/**oldPayBuyer*/
	@Excel(name="老买家支付买家数")
	private String oldPayBuyer;
	/**oldOrderBuyer*/
	@Excel(name="老买家下单买家数")
	private String oldOrderBuyer;
	/**oldPayMoney*/
	@Excel(name="老买家支付金额")
	private String oldPayMoney;
	/**oldPayBuyerOrders*/
	@Excel(name="老买家支付订单数")
	private String oldPayBuyerOrders;
	/**oldBuyerOrders*/
	@Excel(name="老买家下单订单数")
	private String oldBuyerOrders;
	/**oldBuyerRate*/
	@Excel(name="老买家浏览-下单转化率")
	private String oldBuyerRate;
	/**oldBuyerVisitors*/
	@Excel(name="老买家商品页访客数")
	private String oldBuyerVisitors;
	/**searchExposure*/
	@Excel(name="搜索曝光量")
	private String searchExposure;
	/**viewNum*/
	@Excel(name="商品页浏览量")
	private String viewNum;
	/**visitorsNum*/
	@Excel(name="商品页访客数")
	private String visitorsNum;
	/**buyerRate*/
	@Excel(name="浏览-下单转化率")
	private String buyerRate;
	/**searchHits*/
	@Excel(name="搜索点击率")
	private String searchHits;
	/**avgStayTime*/
	@Excel(name="平均停留时长")
	private String avgStayTime;
	/**addShoppingCartCount*/
	@Excel(name="添加购物车次数")
	private String addShoppingCartCount;
	/**addShoppingCartPerson*/
	@Excel(name="加购物车人数")
	private String addShoppingCartPerson;
	/**collectionNum*/
	@Excel(name="添加收藏次数")
	private String collectionNum;
	/**collectionPerson*/
	@Excel(name="加收藏夹人数")
	private String collectionPerson;
	/**orderBuyer*/
	@Excel(name="下单买家数")
	private String orderBuyer;
	/**placingOrders*/
	@Excel(name="下单订单数")
	private String placingOrders;
	/**payBuyer*/
	@Excel(name="支付买家数")
	private String payBuyer;
	/**payOrders*/
	@Excel(name="支付订单数")
	private String payOrders;
	/**payMoney*/
	@Excel(name="支付金额")
	private String payMoney;
	/**riskControlOrders*/
	@Excel(name="风控订单数")
	private String riskControlOrders;
	/**riskControlMoney*/
	@Excel(name="风控金额")
	private String riskControlMoney;
	/**refundAmount*/
	@Excel(name="退款金额")
	private String refundAmount;
	/**refundOrders*/
	@Excel(name="退款订单数")
	private String refundOrders;
	
	private String time;
	
	
	/*@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "C_ID")*/
	/*public SkuProductEntity getSku() {
		return sku;
	}

	public void setSku(SkuProductEntity sku) {
		this.sku = sku;
	}*/

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "C_ID")
	public StoreProductEntity getStoreProduct() {
		return storeProduct;
	}

	public void setStoreProduct(StoreProductEntity storeProduct) {
		this.storeProduct = storeProduct;
	}

	@Id
	@Column(name ="id",nullable=true,length=20)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name ="C_ID",nullable=true,length=255,insertable= false,updatable=false)
	public String getC_Id() {
		return c_Id;
	}

	public void setC_Id(String c_Id) {
		this.c_Id = c_Id;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  cTitle
	 */
	@Column(name ="C_TITLE",nullable=true,length=255)
	public String getCTitle(){
		return this.cTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  cTitle
	 */
	public void setCTitle(String cTitle){
		this.cTitle = cTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  oldPayBuyer
	 */
	@Column(name ="OLD_PAY_BUYER",nullable=true,length=255)
	public String getOldPayBuyer(){
		return this.oldPayBuyer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  oldPayBuyer
	 */
	public void setOldPayBuyer(String oldPayBuyer){
		this.oldPayBuyer = oldPayBuyer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  oldOrderBuyer
	 */
	@Column(name ="OLD_ORDER_BUYER",nullable=true,length=255)
	public String getOldOrderBuyer(){
		return this.oldOrderBuyer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  oldOrderBuyer
	 */
	public void setOldOrderBuyer(String oldOrderBuyer){
		this.oldOrderBuyer = oldOrderBuyer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  oldPayMoney
	 */
	@Column(name ="OLD_PAY_MONEY",nullable=true,length=255)
	public String getOldPayMoney(){
		return this.oldPayMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  oldPayMoney
	 */
	public void setOldPayMoney(String oldPayMoney){
		this.oldPayMoney = oldPayMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  oldPayBuyerOrders
	 */
	@Column(name ="OLD_PAY_BUYER_ORDERS",nullable=true,length=255)
	public String getOldPayBuyerOrders(){
		return this.oldPayBuyerOrders;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  oldPayBuyerOrders
	 */
	public void setOldPayBuyerOrders(String oldPayBuyerOrders){
		this.oldPayBuyerOrders = oldPayBuyerOrders;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  oldBuyerOrders
	 */
	@Column(name ="OLD_BUYER_ORDERS",nullable=true,length=255)
	public String getOldBuyerOrders(){
		return this.oldBuyerOrders;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  oldBuyerOrders
	 */
	public void setOldBuyerOrders(String oldBuyerOrders){
		this.oldBuyerOrders = oldBuyerOrders;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  oldBuyerRate
	 */
	@Column(name ="OLD_BUYER_RATE",nullable=true,length=255)
	public String getOldBuyerRate(){
		return this.oldBuyerRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  oldBuyerRate
	 */
	public void setOldBuyerRate(String oldBuyerRate){
		this.oldBuyerRate = oldBuyerRate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  oldBuyerVisitors
	 */
	@Column(name ="OLD_BUYER_VISITORS",nullable=true,length=255)
	public String getOldBuyerVisitors(){
		return this.oldBuyerVisitors;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  oldBuyerVisitors
	 */
	public void setOldBuyerVisitors(String oldBuyerVisitors){
		this.oldBuyerVisitors = oldBuyerVisitors;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  searchExposure
	 */
	@Column(name ="SEARCH_EXPOSURE",nullable=true,length=255)
	public String getSearchExposure(){
		return this.searchExposure;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  searchExposure
	 */
	public void setSearchExposure(String searchExposure){
		this.searchExposure = searchExposure;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  viewNum
	 */
	@Column(name ="VIEW_NUM",nullable=true,length=255)
	public String getViewNum(){
		return this.viewNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  viewNum
	 */
	public void setViewNum(String viewNum){
		this.viewNum = viewNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  visitorsNum
	 */
	@Column(name ="VISITORS_NUM",nullable=true,length=255)
	public String getVisitorsNum(){
		return this.visitorsNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  visitorsNum
	 */
	public void setVisitorsNum(String visitorsNum){
		this.visitorsNum = visitorsNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  buyerRate
	 */
	@Column(name ="BUYER_RATE",nullable=true,length=255)
	public String getBuyerRate(){
		return this.buyerRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  buyerRate
	 */
	public void setBuyerRate(String buyerRate){
		this.buyerRate = buyerRate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  searchHits
	 */
	@Column(name ="SEARCH_HITS",nullable=true,length=255)
	public String getSearchHits(){
		return this.searchHits;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  searchHits
	 */
	public void setSearchHits(String searchHits){
		this.searchHits = searchHits;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  avgStayTime
	 */
	@Column(name ="AVG_STAY_TIME",nullable=true,length=255)
	public String getAvgStayTime(){
		return this.avgStayTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  avgStayTime
	 */
	public void setAvgStayTime(String avgStayTime){
		this.avgStayTime = avgStayTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  addShoppingCartCount
	 */
	@Column(name ="ADD_SHOPPING_CART_COUNT",nullable=true,length=255)
	public String getAddShoppingCartCount(){
		return this.addShoppingCartCount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  addShoppingCartCount
	 */
	public void setAddShoppingCartCount(String addShoppingCartCount){
		this.addShoppingCartCount = addShoppingCartCount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  addShoppingCartPerson
	 */
	@Column(name ="ADD_SHOPPING_CART_PERSON",nullable=true,length=255)
	public String getAddShoppingCartPerson(){
		return this.addShoppingCartPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  addShoppingCartPerson
	 */
	public void setAddShoppingCartPerson(String addShoppingCartPerson){
		this.addShoppingCartPerson = addShoppingCartPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  collectionNum
	 */
	@Column(name ="COLLECTION_NUM",nullable=true,length=255)
	public String getCollectionNum(){
		return this.collectionNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  collectionNum
	 */
	public void setCollectionNum(String collectionNum){
		this.collectionNum = collectionNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  collectionPerson
	 */
	@Column(name ="COLLECTION_PERSON",nullable=true,length=255)
	public String getCollectionPerson(){
		return this.collectionPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  collectionPerson
	 */
	public void setCollectionPerson(String collectionPerson){
		this.collectionPerson = collectionPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  orderBuyer
	 */
	@Column(name ="ORDER_BUYER",nullable=true,length=255)
	public String getOrderBuyer(){
		return this.orderBuyer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  orderBuyer
	 */
	public void setOrderBuyer(String orderBuyer){
		this.orderBuyer = orderBuyer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  placingOrders
	 */
	@Column(name ="PLACING_ORDERS",nullable=true,length=255)
	public String getPlacingOrders(){
		return this.placingOrders;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  placingOrders
	 */
	public void setPlacingOrders(String placingOrders){
		this.placingOrders = placingOrders;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  payBuyer
	 */
	@Column(name ="PAY_BUYER",nullable=true,length=255)
	public String getPayBuyer(){
		return this.payBuyer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  payBuyer
	 */
	public void setPayBuyer(String payBuyer){
		this.payBuyer = payBuyer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  payOrders
	 */
	@Column(name ="PAY_ORDERS",nullable=true,length=255)
	public String getPayOrders(){
		return this.payOrders;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  payOrders
	 */
	public void setPayOrders(String payOrders){
		this.payOrders = payOrders;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  payMoney
	 */
	@Column(name ="PAY_MONEY",nullable=true,length=255)
	public String getPayMoney(){
		return this.payMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  payMoney
	 */
	public void setPayMoney(String payMoney){
		this.payMoney = payMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  riskControlOrders
	 */
	@Column(name ="RISK_CONTROL_ORDERS",nullable=true,length=255)
	public String getRiskControlOrders(){
		return this.riskControlOrders;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  riskControlOrders
	 */
	public void setRiskControlOrders(String riskControlOrders){
		this.riskControlOrders = riskControlOrders;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  riskControlMoney
	 */
	@Column(name ="RISK_CONTROL_MONEY",nullable=true,length=255)
	public String getRiskControlMoney(){
		return this.riskControlMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  riskControlMoney
	 */
	public void setRiskControlMoney(String riskControlMoney){
		this.riskControlMoney = riskControlMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  refundAmount
	 */
	@Column(name ="REFUND_AMOUNT",nullable=true,length=255)
	public String getRefundAmount(){
		return this.refundAmount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  refundAmount
	 */
	public void setRefundAmount(String refundAmount){
		this.refundAmount = refundAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  refundOrders
	 */
	@Column(name ="REFUND_ORDERS",nullable=true,length=255)
	public String getRefundOrders(){
		return this.refundOrders;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  refundOrders
	 */
	public void setRefundOrders(String refundOrders){
		this.refundOrders = refundOrders;
	}

	public String getTime() {
		return time;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  refundOrders
	 */
	@Column(name ="TIME",nullable=true,length=50)
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
