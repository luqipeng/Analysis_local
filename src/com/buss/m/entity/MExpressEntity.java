package com.buss.m.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**   
 * @Title: Entity
 * @Description: m_express
 * @author onlineGenerator
 * @date 2016-05-16 09:50:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_express", schema = "")
//@SuppressWarnings("serial")
@ExcelTarget("mExpressEntity")
public class MExpressEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**物流方案*/
	@Excel(name="物流方案")
	private String name;
	/**运送范围*/
	@Excel(name="运送范围")
	private String scope;
	/**重量限制*/
	@Excel(name="重量限制")
	private String limitWeight;
	/**订单金额限制*/
	@Excel(name="订单金额限制")
	private String limitAmount;
	/**是否接受带电*/
	@Excel(name="是否接受带电")
	private String isAcceptEle;
	/**物流跟踪*/
	@Excel(name="物流追踪")
	private String logisticsTracking;
	/**揽收范围*/
	@Excel(name="揽收范围")
	private String takingExpress;
	/**时效*/
	@Excel(name="物流时效承诺")
	private String aging;
	/**赔付上限*/
	@Excel(name="赔付上限\n" +
			"（人民币）")
	private String payoffCostLimit;

	@Excel(name="起重")
	private int lifting;

	@Excel(name="物流类型")
	private String expressType;

	@Excel(name="折扣")
	private String rate;

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
	 *@return: java.lang.String  物流方案
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流方案
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运送范围
	 */
	@Column(name ="SCOPE",nullable=true,length=50)
	public String getScope(){
		return this.scope;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运送范围
	 */
	public void setScope(String scope){
		this.scope = scope;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  重量限制
	 */
	@Column(name ="LIMIT_WEIGHT",nullable=true,length=50)
	public String getLimitWeight(){
		return this.limitWeight;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  重量限制
	 */
	public void setLimitWeight(String limitWeight){
		this.limitWeight = limitWeight;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单金额限制
	 */
	@Column(name ="LIMIT_AMOUNT",nullable=true,length=50)
	public String getLimitAmount(){
		return this.limitAmount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单金额限制
	 */
	public void setLimitAmount(String limitAmount){
		this.limitAmount = limitAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否接受带电
	 */
	@Column(name ="IS_ACCEPT_ELE",nullable=true,length=50)
	public String getIsAcceptEle(){
		return this.isAcceptEle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否接受带电
	 */
	public void setIsAcceptEle(String isAcceptEle){
		this.isAcceptEle = isAcceptEle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流跟踪
	 */
	@Column(name ="LOGISTICS_TRACKING",nullable=true,length=50)
	public String getLogisticsTracking(){
		return this.logisticsTracking;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流跟踪
	 */
	public void setLogisticsTracking(String logisticsTracking){
		this.logisticsTracking = logisticsTracking;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  揽收范围
	 */
	@Column(name ="TAKING_EXPRESS",nullable=true,length=250)
	public String getTakingExpress(){
		return this.takingExpress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  揽收范围
	 */
	public void setTakingExpress(String takingExpress){
		this.takingExpress = takingExpress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  时效
	 */
	@Column(name ="AGING",nullable=true,length=50)
	public String getAging(){
		return this.aging;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  时效
	 */
	public void setAging(String aging){
		this.aging = aging;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  赔付上限
	 */
	@Column(name ="PAYOFF_COST_LIMIT",nullable=true,length=50)
	public String getPayoffCostLimit(){
		return this.payoffCostLimit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  赔付上限
	 */
	public void setPayoffCostLimit(String payoffCostLimit){
		this.payoffCostLimit = payoffCostLimit;
	}

	@Column(name ="LIFTING",nullable=true,length=10)
	public int getLifting() {
		return lifting;
	}

	public void setLifting(int lifting) {
		this.lifting = lifting;
	}

	@Column(name ="EXPRESS_TYPE",nullable=true,length=20)
	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	@Column(name ="RATE",nullable=true,length=4)
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
}
