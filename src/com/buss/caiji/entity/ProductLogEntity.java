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
 * @Description: product_log
 * @author onlineGenerator
 * @date 2015-11-20 17:17:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "product_log", schema = "")
@SuppressWarnings("serial")
public class ProductLogEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**tableName*/
	@Excel(name="tableName")
	private String tableName;
	/**总数*/
	@Excel(name="总数")
	private Integer count;
	
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
	 *@return: java.lang.String  tableName
	 */
	@Column(name ="TABLE_NAME",nullable=true,length=50)
	public String getTableName(){
		return this.tableName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  tableName
	 */
	public void setTableName(String tableName){
		this.tableName = tableName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总数
	 */
	@Column(name ="COUNT",nullable=true,length=10)
	public Integer getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总数
	 */
	public void setCount(Integer count){
		this.count = count;
	}
}
