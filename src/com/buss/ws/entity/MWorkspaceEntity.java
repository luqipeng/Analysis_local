package com.buss.ws.entity;

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
 * @Description: m_workspace
 * @author onlineGenerator
 * @date 2016-07-12 16:27:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_workspace", schema = "")
@SuppressWarnings("serial")
public class MWorkspaceEntity implements java.io.Serializable {
	/**主键id*/
	private Integer id;
	/**用户id*/
	@Excel(name="用户id")
	private String userId;
	/**工作组名称*/
	@Excel(name="工作组名称")
	private String workName;
	/**级别*/
	@Excel(name="级别")
	private String levelId;
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=10)
	public Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键id
	 */
	public void setId(Integer id){
		this.id = id;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户id
	 */
	@Column(name ="User_ID",nullable=true,length=50)
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
	 *@return: java.lang.String  工作组名称
	 */
	@Column(name ="WORK_NAME",nullable=true,length=20)
	public String getWorkName(){
		return this.workName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工作组名称
	 */
	public void setWorkName(String workName){
		this.workName = workName;
	}
	@Column(name ="LEVEL_ID",nullable=true,length=11)
	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
}
