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
 * @Description: sku_product
 * @author onlineGenerator
 * @date 2016-02-04 15:04:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "sku_product", schema = "")
@SuppressWarnings("serial")
public class SkuProductEntity implements java.io.Serializable {
	/**productId*/
	@Excel(name="productId")
	private String productId;
	/**sku*/
	@Excel(name="sku")
	private String sku;
	/**productName*/
	@Excel(name="productName")
	private String productName;
	/**storeName*/
	@Excel(name="storeName")
	private String storeName;

	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  productId
	 */
	@Id
	@Column(name ="PRODUCT_ID",nullable=false,length=255)
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
	 *@return: java.lang.String  sku
	 */
	@Column(name ="SKU",nullable=true,length=255)
	public String getSku(){
		return this.sku;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  sku
	 */
	public void setSku(String sku){
		this.sku = sku;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  productName
	 */
	@Column(name ="PRODUCT_NAME",nullable=true,length=255)
	public String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  productName
	 */
	public void setProductName(String productName){
		this.productName = productName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  storeName
	 */
	@Column(name ="STORE_NAME",nullable=true,length=255)
	public String getStoreName(){
		return this.storeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  storeName
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
}
