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
 * @Description: m_product
 * @author onlineGenerator
 * @date 2016-08-08 11:34:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_product", schema = "")
@SuppressWarnings("serial")
public class MProductEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**产品id*/
	@Excel(name="产品id")
	private String pid;

	/**产品名称*/
	@Excel(name="产品名称")
	private String name;
	/**图片*/
	@Excel(name="图片")
	private String img;
	/**url*/
	@Excel(name="url")
	private String url;
	/**昨天销量*/
	@Excel(name="昨天销量")
	private Integer day1;
	/**3天销量*/
	@Excel(name="3天销量")
	private Integer day3;
	/**7天销量*/
	@Excel(name="7天销量")
	private Integer day7;
	/**总销量*/
	@Excel(name="总销量")
	private Integer count;
	/**店铺名称*/
	@Excel(name="店铺名称")
	private String sname;
	/**价格*/
	@Excel(name="价格")
	private String price;
	/**客户级别*/
	@Excel(name="客户级别")
	private String customerlever;
	/**国家占比*/
	@Excel(name="国家占比")
	private String countrys;
	/**lastDate*/
	@Excel(name="lastDate")
	private Date lastDate;
	/**firstDate*/
	@Excel(name="firstDate")
	private Date firstDate;
	/**lastDate*/
	@Excel(name="viewed")
	private Integer viewed;

	private String orders;

	private Integer days;//采集天数

	/**最高价格*/
	@Excel(name="最高价格")
	private String maxPrice;
	/**最低价格*/
	@Excel(name="最低价格")
	private String minPrice;
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
	 *@return: java.lang.String  产品id
	 */
	@Column(name ="PID",nullable=true,length=20)
	public String getPid(){
		return this.pid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品id
	 */
	public void setPid(String pid){
		this.pid = pid;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品名称
	 */
	@Column(name ="NAME",nullable=true,length=200)
	public String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品名称
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */
	@Column(name ="IMG",nullable=true,length=400)
	public String getImg(){
		return this.img;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setImg(String img){
		this.img = img;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  url
	 */
	@Column(name ="URL",nullable=true,length=1000)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  昨天销量
	 */
	@Column(name ="DAY1",nullable=true,length=10)
	public Integer getDay1(){
		return this.day1;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  昨天销量
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总销量
	 */
	@Column(name ="COUNT",nullable=true,length=10)
	public Integer getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总销量
	 */
	public void setCount(Integer count){
		this.count = count;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  店铺名称
	 */
	@Column(name ="SNAME",nullable=true,length=200)
	public String getSname(){
		return this.sname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺名称
	 */
	public void setSname(String sname){
		this.sname = sname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  价格
	 */
	@Column(name ="PRICE",nullable=true,length=50)
	public String getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  价格
	 */
	public void setPrice(String price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户级别
	 */
	@Column(name ="CUSTOMERLEVER",nullable=true,length=100)
	public String getCustomerlever(){
		return this.customerlever;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户级别
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
	@Column(name ="VIEWED",nullable=true,length = 1)
	public Integer getViewed() {
		return viewed;
	}

	public void setViewed(Integer viewed) {
		this.viewed = viewed;
	}

	@Column(name ="ORDERS",nullable=true,length=1000)
	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	@Column(name ="FIRST_DATE",nullable=true)
	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	@Column(name ="DAYS",nullable=true,length=2)
	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
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
}
