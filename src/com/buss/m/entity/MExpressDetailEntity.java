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
import org.jeecgframework.poi.excel.annotation.ExcelEntity;

/**   
 * @Title: Entity
 * @Description: m_express_detail
 * @author onlineGenerator
 * @date 2016-05-16 11:35:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_express_detail", schema = "")
@SuppressWarnings("serial")
public class MExpressDetailEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**expressId*/
	@Excel(name="expressId")
	private Integer expressId;
	/**物流方案*/
	@Excel(name="物流方案")
	private String expressName;
	/**countryId*/
	@Excel(name="countryId")
	private Integer countryId;
	/**国家名称*/
	@Excel(name="国家名称")
	private String countryName;
	/**配送服务费*/
	@Excel(name="配送服务费\n" +
			"元（RMB）/KG\n" +
			"*每1g计重，限重2KG")
	private Double deliveryCharge;
	/**挂号服务费*/
	@Excel(name="挂号服务费")
	private Double payCharge;
	/**2KG首重*/
	@Excel(name="2KG首重\n" +
			"元（RMB）/包裹")
	private Double firstWeight;
	/**续重(每500G)*/
	@Excel(name="续重(每500G)\n" +
			"元（RMB）/500G")
	private Double continueWeight;

	/**续重*/
	@Excel(name="续重")
	private Double addedWeight;
	/**计费重量*/
	@Excel(name="计费重量")
	private Double billingWeight;


	/*@ExcelEntity(name = "国家列表")
	private MCountryEntity country;*/
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  expressId
	 */
	@Column(name ="EXPRESS_ID",nullable=true,length=10)
	public Integer getExpressId(){
		return this.expressId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  expressId
	 */
	public void setExpressId(Integer expressId){
		this.expressId = expressId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流方案
	 */
	@Column(name ="EXPRESS_NAME",nullable=true,length=50)
	public String getExpressName(){
		return this.expressName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流方案
	 */
	public void setExpressName(String expressName){
		this.expressName = expressName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  countryId
	 */
	@Column(name ="COUNTRY_ID",nullable=true,length=10)
	public Integer getCountryId(){
		return this.countryId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  countryId
	 */
	public void setCountryId(Integer countryId){
		this.countryId = countryId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家名称
	 */
	@Column(name ="COUNTRY_NAME",nullable=true,length=50)
	public String getCountryName(){
		return this.countryName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家名称
	 */
	public void setCountryName(String countryName){
		this.countryName = countryName;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  配送服务费
	 */
	@Column(name ="DELIVERY_CHARGE",nullable=true,scale=2,length=10)
	public Double getDeliveryCharge(){
		return this.deliveryCharge;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  配送服务费
	 */
	public void setDeliveryCharge(Double deliveryCharge){
		this.deliveryCharge = deliveryCharge;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  挂号服务费
	 */
	@Column(name ="PAY_CHARGE",nullable=true,scale=2,length=10)
	public Double getPayCharge(){
		return this.payCharge;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  挂号服务费
	 */
	public void setPayCharge(Double payCharge){
		this.payCharge = payCharge;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  2KG首重
	 */
	@Column(name ="FIRST_WEIGHT",nullable=true,scale=2,length=10)
	public Double getFirstWeight(){
		return this.firstWeight;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  2KG首重
	 */
	public void setFirstWeight(Double firstWeight){
		this.firstWeight = firstWeight;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  续重(每500G)
	 */
	@Column(name ="CONTINUE_WEIGHT",nullable=true,scale=2,length=10)
	public Double getContinueWeight(){
		return this.continueWeight;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  续重(每500G)
	 */
	public void setContinueWeight(Double continueWeight){
		this.continueWeight = continueWeight;
	}


/*	public MCountryEntity getCountry() {
		return country;
	}

	public void setCountry(MCountryEntity country) {
		this.country = country;
	}*/

	@Column(name ="ADDED_WEIGHT",nullable=true,scale=2,length=10)
	public Double getAddedWeight() {
		return addedWeight;
	}

	public void setAddedWeight(Double addedWeight) {
		this.addedWeight = addedWeight;
	}

	@Column(name ="BILLING_WEIGHT",nullable=true,scale=2,length=10)
	public Double getBillingWeight() {
		return billingWeight;
	}

	public void setBillingWeight(Double billingWeight) {
		this.billingWeight = billingWeight;
	}
}
