package com.buss.compare.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: commodity_analysis
 * @author onlineGenerator
 * @date 2016-02-04 11:04:36
 * @version V1.0   
 *
 */
public class CommodityAnalysisCompareEntity implements java.io.Serializable {
	
	private SkuProductEntity sku;
	
	private int id;
	
	private String searchExposureCur;//当前曝光量
	private String searchExposurePri;//上期曝光量
	private String searchExposureRate;//比率
	private String searchExposureDif;//差值
	
	
	private String viewNumCur;//当前浏览量
	private String viewNumPri;//上期浏览量
	private String viewNumRate;//比率
	private String viewNumDif;//差值
	
	private String buyerRateCur;//当前转化率
	private String buyerRatePri;//上期转化率
	private String buyerRateRate;//比率
	private String buyerRateDif;//差值
	
	
	private String searchHitsCur;//当前点击率
	private String searchHitsPri;//上期点击率
	private String searchHitsRate;//比率
	private String searchHitsDif;//差值
	
	private String addShoppingCartPersonCur;//当前加购数
	private String addShoppingCartPersonPri;//上期加购数
	private String addShoppingCartPersonRate;//比率
	private String addShoppingCartPersonDif;//差值
	
	
	private String collectionPersonCur;//当前收藏数
	private String collectionPersonPri;//上期收藏数
	private String collectionPersonRate;//比率
	private String collectionPersonDif;//差值
	
	private String payOrdersCur;//当前支付订单数
	private String payOrdersPri;//上期支付订单数
	private String payOrdersRate;//比率
	private String payOrdersDif;//差值
	
	
	private String payMoneyCur;//当前支付金额
	private String payMoneyPri;//上期支付金额
	private String payMoneyRate;//比率
	private String payMoneyDif;//差值
	
	private String refundOrdersCur;//当前退款订单数
	private String refundOrdersPri;//上期退款订单数
	private String refundOrdersRate;//比率
	private String refundOrdersDif;//差值
	
	
	private String refundAmountCur;//当前退款金额
	private String refundAmountPri;//上期退款金额
	private String refundAmountRate;//比率
	private String refundAmountDif;//差值
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SkuProductEntity getSku() {
		return sku;
	}

	public void setSku(SkuProductEntity sku) {
		this.sku = sku;
	}

	public String getSearchExposureCur() {
		return searchExposureCur;
	}

	public void setSearchExposureCur(String searchExposureCur) {
		this.searchExposureCur = searchExposureCur;
	}

	public String getSearchExposurePri() {
		return searchExposurePri;
	}

	public void setSearchExposurePri(String searchExposurePri) {
		this.searchExposurePri = searchExposurePri;
	}

	public String getSearchExposureRate() {
		return searchExposureRate;
	}

	public void setSearchExposureRate(String searchExposureRate) {
		this.searchExposureRate = searchExposureRate;
	}

	public String getSearchExposureDif() {
		return searchExposureDif;
	}

	public void setSearchExposureDif(String searchExposureDif) {
		this.searchExposureDif = searchExposureDif;
	}

	public String getViewNumCur() {
		return viewNumCur;
	}

	public void setViewNumCur(String viewNumCur) {
		this.viewNumCur = viewNumCur;
	}

	public String getViewNumPri() {
		return viewNumPri;
	}

	public void setViewNumPri(String viewNumPri) {
		this.viewNumPri = viewNumPri;
	}

	public String getViewNumRate() {
		return viewNumRate;
	}

	public void setViewNumRate(String viewNumRate) {
		this.viewNumRate = viewNumRate;
	}

	public String getViewNumDif() {
		return viewNumDif;
	}

	public void setViewNumDif(String viewNumDif) {
		this.viewNumDif = viewNumDif;
	}

	public String getBuyerRateCur() {
		return buyerRateCur;
	}

	public void setBuyerRateCur(String buyerRateCur) {
		this.buyerRateCur = buyerRateCur;
	}

	public String getBuyerRatePri() {
		return buyerRatePri;
	}

	public void setBuyerRatePri(String buyerRatePri) {
		this.buyerRatePri = buyerRatePri;
	}

	public String getBuyerRateRate() {
		return buyerRateRate;
	}

	public void setBuyerRateRate(String buyerRateRate) {
		this.buyerRateRate = buyerRateRate;
	}

	public String getBuyerRateDif() {
		return buyerRateDif;
	}

	public void setBuyerRateDif(String buyerRateDif) {
		this.buyerRateDif = buyerRateDif;
	}

	public String getSearchHitsCur() {
		return searchHitsCur;
	}

	public void setSearchHitsCur(String searchHitsCur) {
		this.searchHitsCur = searchHitsCur;
	}

	public String getSearchHitsPri() {
		return searchHitsPri;
	}

	public void setSearchHitsPri(String searchHitsPri) {
		this.searchHitsPri = searchHitsPri;
	}

	public String getSearchHitsRate() {
		return searchHitsRate;
	}

	public void setSearchHitsRate(String searchHitsRate) {
		this.searchHitsRate = searchHitsRate;
	}

	public String getSearchHitsDif() {
		return searchHitsDif;
	}

	public void setSearchHitsDif(String searchHitsDif) {
		this.searchHitsDif = searchHitsDif;
	}

	public String getAddShoppingCartPersonCur() {
		return addShoppingCartPersonCur;
	}

	public void setAddShoppingCartPersonCur(String addShoppingCartPersonCur) {
		this.addShoppingCartPersonCur = addShoppingCartPersonCur;
	}

	public String getAddShoppingCartPersonPri() {
		return addShoppingCartPersonPri;
	}

	public void setAddShoppingCartPersonPri(String addShoppingCartPersonPri) {
		this.addShoppingCartPersonPri = addShoppingCartPersonPri;
	}

	public String getAddShoppingCartPersonRate() {
		return addShoppingCartPersonRate;
	}

	public void setAddShoppingCartPersonRate(String addShoppingCartPersonRate) {
		this.addShoppingCartPersonRate = addShoppingCartPersonRate;
	}

	public String getAddShoppingCartPersonDif() {
		return addShoppingCartPersonDif;
	}

	public void setAddShoppingCartPersonDif(String addShoppingCartPersonDif) {
		this.addShoppingCartPersonDif = addShoppingCartPersonDif;
	}

	public String getCollectionPersonCur() {
		return collectionPersonCur;
	}

	public void setCollectionPersonCur(String collectionPersonCur) {
		this.collectionPersonCur = collectionPersonCur;
	}

	public String getCollectionPersonPri() {
		return collectionPersonPri;
	}

	public void setCollectionPersonPri(String collectionPersonPri) {
		this.collectionPersonPri = collectionPersonPri;
	}

	public String getCollectionPersonRate() {
		return collectionPersonRate;
	}

	public void setCollectionPersonRate(String collectionPersonRate) {
		this.collectionPersonRate = collectionPersonRate;
	}

	public String getCollectionPersonDif() {
		return collectionPersonDif;
	}

	public void setCollectionPersonDif(String collectionPersonDif) {
		this.collectionPersonDif = collectionPersonDif;
	}

	public String getPayOrdersCur() {
		return payOrdersCur;
	}

	public void setPayOrdersCur(String payOrdersCur) {
		this.payOrdersCur = payOrdersCur;
	}

	public String getPayOrdersPri() {
		return payOrdersPri;
	}

	public void setPayOrdersPri(String payOrdersPri) {
		this.payOrdersPri = payOrdersPri;
	}

	public String getPayOrdersRate() {
		return payOrdersRate;
	}

	public void setPayOrdersRate(String payOrdersRate) {
		this.payOrdersRate = payOrdersRate;
	}

	public String getPayOrdersDif() {
		return payOrdersDif;
	}

	public void setPayOrdersDif(String payOrdersDif) {
		this.payOrdersDif = payOrdersDif;
	}

	public String getPayMoneyCur() {
		return payMoneyCur;
	}

	public void setPayMoneyCur(String payMoneyCur) {
		this.payMoneyCur = payMoneyCur;
	}

	public String getPayMoneyPri() {
		return payMoneyPri;
	}

	public void setPayMoneyPri(String payMoneyPri) {
		this.payMoneyPri = payMoneyPri;
	}

	public String getPayMoneyRate() {
		return payMoneyRate;
	}

	public void setPayMoneyRate(String payMoneyRate) {
		this.payMoneyRate = payMoneyRate;
	}

	public String getPayMoneyDif() {
		return payMoneyDif;
	}

	public void setPayMoneyDif(String payMoneyDif) {
		this.payMoneyDif = payMoneyDif;
	}

	public String getRefundOrdersCur() {
		return refundOrdersCur;
	}

	public void setRefundOrdersCur(String refundOrdersCur) {
		this.refundOrdersCur = refundOrdersCur;
	}

	public String getRefundOrdersPri() {
		return refundOrdersPri;
	}

	public void setRefundOrdersPri(String refundOrdersPri) {
		this.refundOrdersPri = refundOrdersPri;
	}

	public String getRefundOrdersRate() {
		return refundOrdersRate;
	}

	public void setRefundOrdersRate(String refundOrdersRate) {
		this.refundOrdersRate = refundOrdersRate;
	}

	public String getRefundOrdersDif() {
		return refundOrdersDif;
	}

	public void setRefundOrdersDif(String refundOrdersDif) {
		this.refundOrdersDif = refundOrdersDif;
	}

	public String getRefundAmountCur() {
		return refundAmountCur;
	}

	public void setRefundAmountCur(String refundAmountCur) {
		this.refundAmountCur = refundAmountCur;
	}

	public String getRefundAmountPri() {
		return refundAmountPri;
	}

	public void setRefundAmountPri(String refundAmountPri) {
		this.refundAmountPri = refundAmountPri;
	}

	public String getRefundAmountRate() {
		return refundAmountRate;
	}

	public void setRefundAmountRate(String refundAmountRate) {
		this.refundAmountRate = refundAmountRate;
	}

	public String getRefundAmountDif() {
		return refundAmountDif;
	}

	public void setRefundAmountDif(String refundAmountDif) {
		this.refundAmountDif = refundAmountDif;
	}

	
	
}
