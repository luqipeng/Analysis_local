package com.buss.m.entity;

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
 * @Description: m_country
 * @author onlineGenerator
 * @date 2016-05-13 09:29:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_country", schema = "")
@SuppressWarnings("serial")
public class MCountryEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**目的地编码*/
	@Excel(name="目的地编码")
	private String code;
	/**英文名称*/
	@Excel(name="英文名称")
	private String enName;
	/**英文简称*/
	@Excel(name="英文简称")
	private String enShort;
	/**中文名称*/
	@Excel(name="中文名称")
	private String cnName;
	/**扫描字符串*/
	@Excel(name="扫描字符串")
	private String scanStr;
	
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
	 *@return: java.lang.String  目的地编码
	 */
	@Column(name ="CODE",nullable=true,length=50)
	public String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  目的地编码
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文名称
	 */
	@Column(name ="EN_NAME",nullable=true,length=50)
	public String getEnName(){
		return this.enName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文名称
	 */
	public void setEnName(String enName){
		this.enName = enName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文简称
	 */
	@Column(name ="EN_SHORT",nullable=true,length=50)
	public String getEnShort(){
		return this.enShort;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文简称
	 */
	public void setEnShort(String enShort){
		this.enShort = enShort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中文名称
	 */
	@Column(name ="CN_NAME",nullable=true,length=50)
	public String getCnName(){
		return this.cnName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中文名称
	 */
	public void setCnName(String cnName){
		this.cnName = cnName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扫描字符串
	 */
	@Column(name ="SCAN_STR",nullable=true,length=50)
	public String getScanStr(){
		return this.scanStr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扫描字符串
	 */
	public void setScanStr(String scanStr){
		this.scanStr = scanStr;
	}
}
