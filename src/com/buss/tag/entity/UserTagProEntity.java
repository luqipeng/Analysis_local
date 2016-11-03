package com.buss.tag.entity;

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
 * @Description: user_tag_pro
 * @author onlineGenerator
 * @date 2015-09-23 20:02:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "user_tag_pro", schema = "")
@SuppressWarnings("serial")
public class UserTagProEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**tagId*/
	@Excel(name="tagId")
	private Integer tagId;
	/**productId*/
	@Excel(name="productId")
	private String productId;
	
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
	 *@return: java.lang.Integer  tagId
	 */
	@Column(name ="TAG_ID",nullable=false,length=10)
	public Integer getTagId(){
		return this.tagId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  tagId
	 */
	public void setTagId(Integer tagId){
		this.tagId = tagId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  productId
	 */
	@Column(name ="PRODUCT_ID",nullable=false,length=40)
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
}
