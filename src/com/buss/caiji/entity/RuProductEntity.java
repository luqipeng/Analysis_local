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
 * @Description: ru_product
 * @author onlineGenerator
 * @date 2016-01-04 21:22:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ru_product", schema = "")
@SuppressWarnings("serial")
public class RuProductEntity implements java.io.Serializable {
	/**productId*/
	@Excel(name="productId")
	private String productId;
	/**activityId*/
	@Excel(name="activityId")
	private String activityId;
	/**url*/
	@Excel(name="url")
	private String url;
	/**img*/
	@Excel(name="img")
	private String img;
	/**折扣*/
	@Excel(name="折扣")
	private String discount;
	/**count*/
	@Excel(name="count")
	private Integer count;
	/**activityPrice*/
	@Excel(name="activityPrice")
	private Double activityPrice;
	/**price*/
	@Excel(name="price")
	private Double price;
	/**endTime*/
	@Excel(name="endTime")
	private Date endTime;
	/**lastDate*/
	@Excel(name="lastDate")
	private Date lastDate;
	/**是否售罄 0：否，1：是*/
	@Excel(name="是否售罄 0：否，1：是")
	private Integer isSoldout;
	/**categoryGr*/
	@Excel(name="categoryGr")
	private String categoryGr;
	
	/**unit*/
	@Excel(name="unit")
	private String unit;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  productId
	 */
	@Id
	@Column(name ="PRODUCT_ID",nullable=false,length=20)
	public String getProductId(){
		return this.productId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  productId
	 */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  activityId
	 */
	@Column(name ="ACTIVITY_ID",nullable=true,length=20)
	public String getActivityId(){
		return this.activityId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  activityId
	 */
	public void setActivityId(String activityId){
		this.activityId = activityId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  url
	 */
	@Column(name ="URL",nullable=true,length=200)
	public String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  url
	 */
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  img
	 */
	@Column(name ="IMG",nullable=true,length=200)
	public String getImg(){
		return this.img;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  img
	 */
	public void setImg(String img){
		this.img = img;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  折扣
	 */
	@Column(name ="DISCOUNT",nullable=true,length=20)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  count
	 */
	@Column(name ="COUNT",nullable=true,length=10)
	public Integer getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  count
	 */
	public void setCount(Integer count){
		this.count = count;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  activityPrice
	 */
	@Column(name ="ACTIVITY_PRICE",nullable=true,scale=2,length=10)
	public Double getActivityPrice(){
		return this.activityPrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  activityPrice
	 */
	public void setActivityPrice(Double activityPrice){
		this.activityPrice = activityPrice;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  price
	 */
	@Column(name ="PRICE",nullable=true,scale=2,length=10)
	public Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  price
	 */
	public void setPrice(Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  endTime
	 */
	@Column(name ="END_TIME",nullable=false)
	public Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  endTime
	 */
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  lastDate
	 */
	@Column(name ="LAST_DATE",nullable=true)
	public Date getLastDate(){
		return this.lastDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  lastDate
	 */
	public void setLastDate(Date lastDate){
		this.lastDate = lastDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否售罄 0：否，1：是
	 */
	@Column(name ="IS_SOLDOUT",nullable=true,length=10)
	public Integer getIsSoldout(){
		return this.isSoldout;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否售罄 0：否，1：是
	 */
	public void setIsSoldout(Integer isSoldout){
		this.isSoldout = isSoldout;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  categoryGr
	 */
	@Column(name ="CATEGORY_GR",nullable=true,length=20)
	public String getCategoryGr(){
		return this.categoryGr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  categoryGr
	 */
	public void setCategoryGr(String categoryGr){
		this.categoryGr = categoryGr;
	}

	@Column(name ="UNIT",nullable=true,length=20)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
