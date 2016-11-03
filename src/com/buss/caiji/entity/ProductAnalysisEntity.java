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
 * @Description: product_analysis
 * @author onlineGenerator
 * @date 2015-12-09 11:18:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "product_analysis", schema = "")
@SuppressWarnings("serial")
public class ProductAnalysisEntity implements java.io.Serializable {
	/**pId*/
	@Excel(name="pId")
	private String pId;
	/**cId*/
	@Excel(name="cId")
	private String cId;
	/**pName*/
	@Excel(name="pName")
	private String pName;
	/**content*/
	@Excel(name="content")
	private String content;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  pId
	 */
	@Id
	@Column(name ="P_ID",nullable=false,length=10)
	public String getPId(){
		return this.pId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  pId
	 */
	public void setPId(String pId){
		this.pId = pId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  cId
	 */
	@Id
	@Column(name ="C_ID",nullable=false,length=10)
	public String getCId(){
		return this.cId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  cId
	 */
	public void setCId(String cId){
		this.cId = cId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  pName
	 */
	@Column(name ="P_NAME",nullable=true,length=200)
	public String getPName(){
		return this.pName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  pName
	 */
	public void setPName(String pName){
		this.pName = pName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  content
	 */
	@Column(name ="CONTENT",nullable=true,length=65535)
	public String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  content
	 */
	public void setContent(String content){
		this.content = content;
	}
}
