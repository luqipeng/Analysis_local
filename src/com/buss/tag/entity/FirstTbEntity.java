package com.buss.tag.entity;

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
 * @Description: first_tb
 * @author onlineGenerator
 * @date 2015-10-25 13:29:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "first_tb", schema = "")
@SuppressWarnings("serial")
public class FirstTbEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**0:类目；1：标签*/
	@Excel(name="0:类目；1：标签")
	private Integer type;
	/**lId*/
	@Excel(name="TabId")
	private Integer TabId;
	
	/**UserId*/
	@Excel(name="UserId")
	private String UserId;
	
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
	 *@return: java.lang.Integer  0:类目；1：标签
	 */
	@Column(name ="TYPE",nullable=true,length=10)
	public Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  0:类目；1：标签
	 */
	public void setType(Integer type){
		this.type = type;
	}
	
	@Column(name ="TAB_ID",nullable=true,length=10)
	public Integer getTabId() {
		return TabId;
	}

	public void setTabId(Integer tabId) {
		TabId = tabId;
	}

	@Column(name ="USER_ID",nullable=true,length=32)
	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}
	

}
