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
 * @Description: m_change
 * @author onlineGenerator
 * @date 2016-08-13 10:07:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_change", schema = "")
@SuppressWarnings("serial")
public class MChangeEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**产品id*/
	@Excel(name="产品id")
	private String pid;
	/**旧的内容*/
	@Excel(name="旧的内容")
	private String old;
	/**now*/
	@Excel(name="now")
	private String now;
	/**变化类别：1：价格；2：图片；3：标题；4：直通车*/
	@Excel(name="变化类别：1：价格；2：图片；3：标题；4：直通车")
	private Integer type;
	/**date*/
	@Excel(name="date")
	private Date date;
	
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
	 *@return: java.lang.String  旧的内容
	 */
	@Column(name ="OLD",nullable=true,length=200)
	public String getOld(){
		return this.old;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  旧的内容
	 */
	public void setOld(String old){
		this.old = old;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  now
	 */
	@Column(name ="NOW",nullable=true,length=200)
	public String getNow(){
		return this.now;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  now
	 */
	public void setNow(String now){
		this.now = now;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  变化类别：1：价格；2：图片；3：标题；4：直通车
	 */
	@Column(name ="TYPE",nullable=true,length=10)
	public Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  变化类别：1：价格；2：图片；3：标题；4：直通车
	 */
	public void setType(Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  date
	 */
	@Column(name ="DATE",nullable=true)
	public Date getDate(){
		return this.date;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  date
	 */
	public void setDate(Date date){
		this.date = date;
	}
}
