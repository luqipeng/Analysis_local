package com.buss.report.entity;

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
 * @Description: m_report
 * @author onlineGenerator
 * @date 2016-07-02 11:55:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_report", schema = "")
@SuppressWarnings("serial")
public class MReportEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**cId*/
	@Excel(name="cId")
	private java.lang.String cId;
	/**cName*/
	@Excel(name="cName")
	private java.lang.String cName;
	/**date*/
	@Excel(name="date")
	private java.util.Date date;
	/**totalCount*/
	@Excel(name="totalCount")
	private java.lang.Integer totalCount;
	/**totalMoney*/
	@Excel(name="totalMoney")
	private java.lang.String totalMoney;
	/**unitPrice*/
	@Excel(name="unitPrice")
	private java.lang.String unitPrice;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  cId
	 */
	@Column(name ="C_ID",nullable=true,length=255)
	public java.lang.String getCId(){
		return this.cId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  cId
	 */
	public void setCId(java.lang.String cId){
		this.cId = cId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  date
	 */
	@Column(name ="DATE",nullable=true)
	public java.util.Date getDate(){
		return this.date;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  date
	 */
	public void setDate(java.util.Date date){
		this.date = date;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  totalCount
	 */
	@Column(name ="TOTAL_COUNT",nullable=true,length=10)
	public java.lang.Integer getTotalCount(){
		return this.totalCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  totalCount
	 */
	public void setTotalCount(java.lang.Integer totalCount){
		this.totalCount = totalCount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  totalMoney
	 */
	@Column(name ="TOTAL_MONEY",nullable=true,length=20)
	public java.lang.String getTotalMoney(){
		return this.totalMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  totalMoney
	 */
	public void setTotalMoney(java.lang.String totalMoney){
		this.totalMoney = totalMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  unitPrice
	 */
	@Column(name ="UNIT_PRICE",nullable=true,length=20)
	public java.lang.String getUnitPrice(){
		return this.unitPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  unitPrice
	 */
	public void setUnitPrice(java.lang.String unitPrice){
		this.unitPrice = unitPrice;
	}

	@Column(name ="C_NAME",nullable=true,length=20)
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
}
