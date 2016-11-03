package com.buss.group.entity;

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
 * @Description: m_group
 * @author onlineGenerator
 * @date 2016-09-26 14:09:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_group", schema = "")
@SuppressWarnings("serial")
public class MGroupEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**店铺名称*/
	@Excel(name="店铺名称")
	private String storeName;
	/**产品ID*/
	@Excel(name="产品ID")
	private String productId;
	/**店铺产品链接*/
	@Excel(name="店铺产品链接")
	private String url;
	/**活动名称*/
	@Excel(name="活动名称")
	private String activityName;
	/**活动开始*/
	@Excel(name="活动开始")
	private Date timeStart;
	/**活动结束*/
	@Excel(name="活动结束")
	private Date timeEnd;
	/**历史最低*/
	@Excel(name="历史最低")
	private Double historyLow;
	/**报名价格*/
	@Excel(name="报名价格")
	private Double price;
	/**报名数量*/
	@Excel(name="报名数量")
	private Integer num;
	/**采购预估*/
	@Excel(name="采购预估")
	private Integer estimated;
	/**实际采购*/
	@Excel(name="实际采购")
	private String actual;

	private String userId;

	private String productName;

	
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
	 *@return: java.lang.String  店铺名称
	 */
	@Column(name ="STORE_NAME",nullable=true,length=50)
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
	 *@return: java.lang.String  产品ID
	 */
	@Column(name ="PRODUCT_ID",nullable=true,length=20)
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
	 *@return: java.lang.String  店铺产品链接
	 */
	@Column(name ="URL",nullable=true,length=200)
	public String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺产品链接
	 */
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动名称
	 */
	@Column(name ="ACTIVITY_NAME",nullable=true,length=200)
	public String getActivityName(){
		return this.activityName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setActivityName(String activityName){
		this.activityName = activityName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  活动开始
	 */
	@Column(name ="TIME_START",nullable=true)
	public Date getTimeStart(){
		return this.timeStart;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  活动开始
	 */
	public void setTimeStart(Date timeStart){
		this.timeStart = timeStart;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  活动结束
	 */
	@Column(name ="TIME_END",nullable=true)
	public Date getTimeEnd(){
		return this.timeEnd;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  活动结束
	 */
	public void setTimeEnd(Date timeEnd){
		this.timeEnd = timeEnd;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  历史最低
	 */
	@Column(name ="HISTORY_LOW",nullable=true,scale=2,length=10)
	public Double getHistoryLow(){
		return this.historyLow;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  历史最低
	 */
	public void setHistoryLow(Double historyLow){
		this.historyLow = historyLow;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  报名价格
	 */
	@Column(name ="PRICE",nullable=true,scale=2,length=10)
	public Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  报名价格
	 */
	public void setPrice(Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  报名数量
	 */
	@Column(name ="NUM",nullable=true,length=10)
	public Integer getNum(){
		return this.num;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  报名数量
	 */
	public void setNum(Integer num){
		this.num = num;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  采购预估
	 */
	@Column(name ="ESTIMATED",nullable=true,length=10)
	public Integer getEstimated(){
		return this.estimated;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  采购预估
	 */
	public void setEstimated(Integer estimated){
		this.estimated = estimated;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际采购
	 */
	@Column(name ="ACTUAL",nullable=true,length=50)
	public String getActual(){
		return this.actual;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际采购
	 */
	public void setActual(String actual){
		this.actual = actual;
	}

	@Column(name ="user_id",nullable=true,length=50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name ="product_name",nullable=true,length=200)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
