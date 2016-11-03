package com.buss.compare.entity;

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
 * @Description: store_product_change
 * @author onlineGenerator
 * @date 2016-09-20 10:49:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "store_product_change", schema = "")
@SuppressWarnings("serial")
public class StoreProductChangeEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**日期*/
	@Excel(name="日期")
	private Date time;
	/**类型*/
	@Excel(name="类型")
	private String type;
	/**变更前*/
	@Excel(name="变更前")
	private String old;
	/**变更后*/
	@Excel(name="变更后")
	private String now;

	private String pId;


	private String pName;


	private String storeId;
	
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日期
	 */
	@Column(name ="TIME",nullable=true)
	public Date getTime(){
		return this.time;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  日期
	 */
	public void setTime(Date time){
		this.time = time;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	@Column(name ="TYPE",nullable=true,length=20)
	public String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  变更前
	 */
	@Column(name ="OLD",nullable=true,length=200)
	public String getOld(){
		return this.old;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  变更前
	 */
	public void setOld(String old){
		this.old = old;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  变更后
	 */
	@Column(name ="NOW",nullable=true,length=200)
	public String getNow(){
		return this.now;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  变更后
	 */
	public void setNow(String now){
		this.now = now;
	}

	@Column(name ="P_ID",nullable=true,length=20)
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	@Column(name ="P_NAME",nullable=true,length=200)
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	@Column(name ="STORE_ID",nullable=true,length=20)
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
