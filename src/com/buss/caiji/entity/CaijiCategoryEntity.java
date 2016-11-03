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
 * @Description: 类目表
 * @author onlineGenerator
 * @date 2015-08-06 19:04:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "caiji_category", schema = "")
@SuppressWarnings("serial")
public class CaijiCategoryEntity implements java.io.Serializable {
	/**主键*/
	private Integer id;
	/**类目ID*/
	@Excel(name="类目ID")
	private Integer cId;
	/**类目名称*/
	@Excel(name="类目名称")
	private String cName;
	/**父级ID*/
	@Excel(name="父级ID")
	private Integer pid;
	/**是否采集*/
	@Excel(name="是否采集")
	private Integer isCatch;
	/**链接地址*/
	@Excel(name="链接地址")
	private String url;
	/**采集页数*/
	@Excel(name="采集页数")
	private Integer catchPage;

	/**类目总销量数*/
	@Excel(name="类目总销量数")
	private Integer count;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=10)
	public Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setId(Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  类目ID
	 */
	@Column(name ="C_ID",nullable=true,length=10)
	public Integer getCId(){
		return this.cId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  类目ID
	 */
	public void setCId(Integer cId){
		this.cId = cId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类目名称
	 */
	@Column(name ="C_NAME",nullable=true,length=500)
	public String getCName(){
		return this.cName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类目名称
	 */
	public void setCName(String cName){
		this.cName = cName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  父级ID
	 */
	@Column(name ="PID",nullable=true,length=10)
	public Integer getPid(){
		return this.pid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  父级ID
	 */
	public void setPid(Integer pid){
		this.pid = pid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否采集
	 */
	@Column(name ="IS_CATCH",nullable=true,length=10)
	public Integer getIsCatch(){
		return this.isCatch;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否采集
	 */
	public void setIsCatch(Integer isCatch){
		this.isCatch = isCatch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  链接地址
	 */
	@Column(name ="URL",nullable=true,length=500)
	public String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  链接地址
	 */
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  采集页数
	 */
	@Column(name ="CATCH_PAGE",nullable=true,length=10)
	public Integer getCatchPage(){
		return this.catchPage;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  采集页数
	 */
	public void setCatchPage(Integer catchPage){
		this.catchPage = catchPage;
	}

	@Column(name ="COUNT",nullable=true,length=10)
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
