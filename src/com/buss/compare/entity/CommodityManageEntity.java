package com.buss.compare.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: commodity_manage
 * @author onlineGenerator
 * @date 2016-09-17 18:36:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "commodity_manage", schema = "")
@SuppressWarnings("serial")
public class CommodityManageEntity implements java.io.Serializable {

	/**产品ID*/
	private String productId;
	/**编号*/
	@Excel(name="编号")
	private String code;
	/**商品Logo*/
	@Excel(name="商品Logo")
	private String logo;
	/**平台商品标识*/
	@Excel(name="平台商品标识")
	private String platformLogo;
	/**平台商品名称*/
	@Excel(name="平台商品名称")
	private String platformName;
	/**店铺名*/
	@Excel(name="店铺名")
	private String storeName;
	/**已确认*/
	@Excel(name="已确认")
	private String isCheck;
	/**平台来源*/
	@Excel(name="平台来源")
	private String platform;
	/**关联产品名称*/
	@Excel(name="关联产品名称")
	private String productName;
	/**关联产品SKU*/
	@Excel(name="关联产品SKU")
	private String sku;


/*	private CommodityAnalysisEntity commodityAnalysisEntity;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	public CommodityAnalysisEntity getCommodityAnalysisEntity() {
		return commodityAnalysisEntity;
	}

	public void setCommodityAnalysisEntity(CommodityAnalysisEntity commodityAnalysisEntity) {
		this.commodityAnalysisEntity = commodityAnalysisEntity;
	}*/

	@Id
	@Column(name ="product_id",nullable=true,length=20)
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */
	@Column(name ="CODE",nullable=true,length=20)
	public String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品Logo
	 */
	@Column(name ="LOGO",nullable=true,length=200)
	public String getLogo(){
		return this.logo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品Logo
	 */
	public void setLogo(String logo){
		this.logo = logo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  平台商品标识
	 */
	@Column(name ="PLATFORM_LOGO",nullable=true,length=200)
	public String getPlatformLogo(){
		return this.platformLogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  平台商品标识
	 */
	public void setPlatformLogo(String platformLogo){
		this.platformLogo = platformLogo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  平台商品名称
	 */
	@Column(name ="PLATFORM_NAME",nullable=true,length=200)
	public String getPlatformName(){
		return this.platformName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  平台商品名称
	 */
	public void setPlatformName(String platformName){
		this.platformName = platformName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  店铺名
	 */
	@Column(name ="STORE_NAME",nullable=true,length=100)
	public String getStoreName(){
		return this.storeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺名
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  已确认
	 */
	@Column(name ="IS_CHECK",nullable=true,length=2)
	public String getIsCheck(){
		return this.isCheck;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  已确认
	 */
	public void setIsCheck(String isCheck){
		this.isCheck = isCheck;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  平台来源
	 */
	@Column(name ="PLATFORM",nullable=true,length=20)
	public String getPlatform(){
		return this.platform;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  平台来源
	 */
	public void setPlatform(String platform){
		this.platform = platform;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关联产品名称
	 */
	@Column(name ="PRODUCT_NAME",nullable=true,length=200)
	public String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关联产品名称
	 */
	public void setProductName(String productName){
		this.productName = productName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关联产品SKU
	 */
	@Column(name ="SKU",nullable=true,length=100)
	public String getSku(){
		return this.sku;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关联产品SKU
	 */
	public void setSku(String sku){
		this.sku = sku;
	}


}
