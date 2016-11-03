package com.buss.keyword.entity;

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
 * @Description: m_keyword
 * @author onlineGenerator
 * @date 2016-07-13 20:11:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_keyword", schema = "")
@SuppressWarnings("serial")
public class MKeywordEntity implements java.io.Serializable {
	/**主键*/
	private Integer id;
	/**用户id*/
	@Excel(name="用户id")
	private String userId;
	/**关键字*/
	@Excel(name="关键字")
	private String keyword;
	/**工作组id*/
	@Excel(name="工作组id")
	private Integer wid;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户id
	 */
	@Column(name ="USER_ID",nullable=true,length=50)
	public String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户id
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关键字
	 */
	@Column(name ="KEYWORD",nullable=true,length=50)
	public String getKeyword(){
		return this.keyword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关键字
	 */
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  工作组id
	 */
	@Column(name ="WID",nullable=true,length=10)
	public Integer getWid(){
		return this.wid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  工作组id
	 */
	public void setWid(Integer wid){
		this.wid = wid;
	}
}
