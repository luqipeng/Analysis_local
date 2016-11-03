package com.buss.attention.entity;

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
 * @Description: attention_product
 * @author onlineGenerator
 * @date 2016-08-02 17:28:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "attention_product", schema = "")
@SuppressWarnings("serial")
public class AttentionProductEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**产品名称*/
	@Excel(name="产品名称")
	private String pid;
	/**wid*/
	@Excel(name="wid")
	private String wid;
	/**name*/
	@Excel(name="name")
	private String name;
	/**总单数*/
	@Excel(name="总单数")
	private Integer count;
	/**1天销量*/
	@Excel(name="1天销量")
	private Integer day1;
	/**3天销量*/
	@Excel(name="3天销量")
	private Integer day3;
	/**7天销量*/
	@Excel(name="7天销量")
	private Integer day7;
	/**客户等级占比*/
	@Excel(name="客户等级占比")
	private String customerlever;
	/**国家占比*/
	@Excel(name="国家占比")
	private String countrys;

	private String img;

	private String price;

	private String orders;

	private String maxPrice;

	private String minPrice;

	private String storeName;

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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品名称
	 */
	@Column(name ="PID",nullable=true,length=20)
	public String getPid(){
		return this.pid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品名称
	 */
	public void setPid(String pid){
		this.pid = pid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  wid
	 */
	@Column(name ="WID",nullable=true,length=10)
	public String getWid(){
		return this.wid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  wid
	 */
	public void setWid(String wid){
		this.wid = wid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  name
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总单数
	 */
	@Column(name ="COUNT",nullable=true,length=10)
	public Integer getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总单数
	 */
	public void setCount(Integer count){
		this.count = count;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  1天销量
	 */
	@Column(name ="DAY1",nullable=true,length=10)
	public Integer getDay1(){
		return this.day1;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  1天销量
	 */
	public void setDay1(Integer day1){
		this.day1 = day1;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  3天销量
	 */
	@Column(name ="DAY3",nullable=true,length=10)
	public Integer getDay3(){
		return this.day3;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  3天销量
	 */
	public void setDay3(Integer day3){
		this.day3 = day3;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  7天销量
	 */
	@Column(name ="DAY7",nullable=true,length=10)
	public Integer getDay7(){
		return this.day7;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  7天销量
	 */
	public void setDay7(Integer day7){
		this.day7 = day7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户等级占比
	 */
	@Column(name ="CUSTOMERLEVER",nullable=true,length=50)
	public String getCustomerlever(){
		return this.customerlever;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户等级占比
	 */
	public void setCustomerlever(String customerlever){
		this.customerlever = customerlever;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家占比
	 */
	@Column(name ="COUNTRYS",nullable=true,length=5000)
	public String getCountrys(){
		return this.countrys;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家占比
	 */
	public void setCountrys(String countrys){
		this.countrys = countrys;
	}

	@Column(name ="IMG",nullable=true,length=200)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name ="PRICE",nullable=true,length=20)
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name ="ORDERS",nullable=true,length=1000)
	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}


	@Column(name ="MAX_PRICE",nullable=true,length=50)
	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Column(name ="MIN_PRICE",nullable=true,length=50)
	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}


	@Column(name ="STORE_NAME",nullable=true,length=100)
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name ="STORE_ID",nullable=true,length=20)
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
