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

/**   
 * @Title: Entity
 * @Description: m_recharge
 * @author onlineGenerator
 * @date 2016-05-13 09:55:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_recharge", schema = "")
@SuppressWarnings("serial")
public class MRechargeEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**渠道名称*/
	@Excel(name="渠道名称")
	private String name;
	/**目的地*/
	@Excel(name="目的地")
	private String country;
	/**结算重量*/
	@Excel(name="结算重量")
	private String weight;
	/**金额*/
	@Excel(name="金额")
	private Double amount;
	/**燃油附加*/
	@Excel(name="燃油附加")
	private Double baf;
	/**币种*/
	@Excel(name="币种")
	private String currency;
	/**预计递送时间*/
	@Excel(name="预计递送时间")
	private String predictTime;
	/**可走物品*/
	@Excel(name="可走物品")
	private String canGoods;
	/**报价备注*/
	@Excel(name="报价备注")
	private String remark;

	/**包裹个数**/
	private Integer packageCount;
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
	 *@return: java.lang.String  渠道名称
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道名称
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  目的地
	 */
	@Column(name ="COUNTRY",nullable=true,length=10)
	public String getCountry(){
		return this.country;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  目的地
	 */
	public void setCountry(String country){
		this.country = country;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  结算重量
	 */
	@Column(name ="WEIGHT",nullable=true,scale=2,length=10)
	public String getWeight(){
		return this.weight;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  结算重量
	 */
	public void setWeight(String weight){
		this.weight = weight;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  金额
	 */
	@Column(name ="AMOUNT",nullable=true,scale=2,length=10)
	public Double getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  金额
	 */
	public void setAmount(Double amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  燃油附加
	 */
	@Column(name ="BAF",nullable=true,scale=2,length=10)
	public Double getBaf(){
		return this.baf;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  燃油附加
	 */
	public void setBaf(Double baf){
		this.baf = baf;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种
	 */
	@Column(name ="CURRENCY",nullable=true,length=10)
	public String getCurrency(){
		return this.currency;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种
	 */
	public void setCurrency(String currency){
		this.currency = currency;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预计递送时间
	 */
	@Column(name ="PREDICT_TIME",nullable=true)
	public String getPredictTime(){
		return this.predictTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预计递送时间
	 */
	public void setPredictTime(String predictTime){
		this.predictTime = predictTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  可走物品
	 */
	@Column(name ="CAN_GOODS",nullable=true,length=250)
	public String getCanGoods(){
		return this.canGoods;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  可走物品
	 */
	public void setCanGoods(String canGoods){
		this.canGoods = canGoods;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报价备注
	 */
	@Column(name ="REMARK",nullable=true,length=250)
	public String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报价备注
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	public Integer getPackageCount() {
		return packageCount;
	}

	public void setPackageCount(Integer packageCount) {
		this.packageCount = packageCount;
	}
}
