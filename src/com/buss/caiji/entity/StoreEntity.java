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
 * @Description: 店铺
 * @author onlineGenerator
 * @date 2015-08-07 14:14:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "store", schema = "")
@SuppressWarnings("serial")
public class StoreEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**店铺ID*/
	@Excel(name="店铺ID")
	private String storeId;
	/**链接地址*/
	@Excel(name="链接地址")
	private String storeUrl;
	/**店铺名称*/
	@Excel(name="店铺名称")
	private String storeName;
	/**storeFB*/
	@Excel(name="storeFB")
	private String storeFaceback;
	/**storeFBper*/
	@Excel(name="storeFBper")
	private String storeFacebackper;
	/**是否采集*/
	@Excel(name="是否采集")
	private Integer isCatch;
	/**sellerAdminSeq*/
	@Excel(name="sellerAdminSeq")
	private String sellerAdminSeq;
	/**userId*/
	@Excel(name="userId")
	private String userId;
	@Excel(name="totalNum")
	private Integer totalNum;
	@Excel(name="collectNum")
	private Integer collectNum;
	@Excel(name="level")
	private String level;
	@Excel(name="nickName")
	private String nickName;
	@Excel(name="sellerCompanyId")
	private String sellerCompanyId;

	/**
	 * 采集店铺信息用
	 */
//	private String productId;

	private String createTime;

	private String threeFeedback;
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
	 *@return: java.lang.String  店铺ID
	 */
	@Column(name ="STORE_ID",nullable=true,length=20)
	public String getStoreId(){
		return this.storeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺ID
	 */
	public void setStoreId(String storeId){
		this.storeId = storeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  链接地址
	 */
	@Column(name ="STORE_URL",nullable=true,length=200)
	public String getStoreUrl(){
		return this.storeUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  链接地址
	 */
	public void setStoreUrl(String storeUrl){
		this.storeUrl = storeUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  店铺名称
	 */
	@Column(name ="STORE_NAME",nullable=true,length=200)
	public String getStoreName(){
		return this.storeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺名称
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  storeFB
	 */
	@Column(name ="STORE_FACEBACK",nullable=true,length=20)
	public String getStoreFaceback(){
		return this.storeFaceback;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  storeFB
	 */
	public void setStoreFaceback(String storeFaceback){
		this.storeFaceback = storeFaceback;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  storeFBper
	 */
	@Column(name ="STORE_FACEBACKPER",nullable=true,length=20)
	public String getStoreFacebackper(){
		return this.storeFacebackper;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  storeFBper
	 */
	public void setStoreFacebackper(String storeFacebackper){
		this.storeFacebackper = storeFacebackper;
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
	@Column(name ="SELLER_ADMIN_SEQ",nullable=true,length=20)
	public String getSellerAdminSeq() {
		return sellerAdminSeq;
	}

	public void setSellerAdminSeq(String sellerAdminSeq) {
		this.sellerAdminSeq = sellerAdminSeq;
	}
	@Column(name ="USER_ID",nullable=true,length=50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name ="TOTAL_NUM",nullable=true,length=10)
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	@Column(name ="COLLECT_NUM",nullable=true,length=10)
	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}
	@Column(name ="LEVEL",nullable=true,length=10)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	@Column(name ="NICK_NAME",nullable=true,length=50)
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/*public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}*/

	@Column(name ="seller_company_id",nullable=true,length=20)
	public String getSellerCompanyId() {
		return sellerCompanyId;
	}

	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}

	@Column(name ="create_time",nullable=true)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name ="three_feedback",nullable=true,length=10)
	public String getThreeFeedback() {
		return threeFeedback;
	}

	public void setThreeFeedback(String threeFeedback) {
		this.threeFeedback = threeFeedback;
	}
}
