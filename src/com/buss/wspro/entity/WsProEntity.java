package com.buss.wspro.entity;

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
 * @Description: m_ws_pro
 * @author onlineGenerator
 * @date 2016-07-13 20:11:24
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_ws_pro", schema = "")
@SuppressWarnings("serial")
public class WsProEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**工作组id*/
	@Excel(name="工作组id")
	private Integer wid;
	/**产品id*/
	@Excel(name="产品id")
	private String pid;
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

}
