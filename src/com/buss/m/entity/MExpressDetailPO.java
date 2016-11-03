package com.buss.m.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: m_express_detail
 * @author onlineGenerator
 * @date 2016-05-16 11:35:32
 * @version V1.0   
 *
 */

public class MExpressDetailPO implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**expressId*/
	@Excel(name="expressId")
	private Integer expressId;
	/**物流方案*/
	@Excel(name="物流方案")
	private String expressName;
	/**countryId*/
	@Excel(name="countryId")
	private Integer countryId;
	/**国家名称*/
	@Excel(name="国家名称")
	private String countryName;
	/**配送服务费*/
	@Excel(name="配送服务费\n" +
			"元（RMB）/KG\n" +
			"*每1g计重，限重2KG")
	private Double deliveryCharge;
	/**挂号服务费*/
	@Excel(name="挂号服务费")
	private Double payCharge;
	/**2KG首重*/
	@Excel(name="2KG首重\n" +
			"元（RMB）/包裹")
	private Double firstWeight;
	/**续重(每500G)*/
	@Excel(name="续重(每500G)\n" +
			"元（RMB）/500G")
	private Double continueWeight;

	private MCountryEntity country;
	private MExpressEntity express;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExpressId() {
		return expressId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Double getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(Double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public Double getPayCharge() {
		return payCharge;
	}

	public void setPayCharge(Double payCharge) {
		this.payCharge = payCharge;
	}

	public Double getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(Double firstWeight) {
		this.firstWeight = firstWeight;
	}

	public Double getContinueWeight() {
		return continueWeight;
	}

	public void setContinueWeight(Double continueWeight) {
		this.continueWeight = continueWeight;
	}

	public MCountryEntity getCountry() {
		return country;
	}

	public void setCountry(MCountryEntity country) {
		this.country = country;
	}

	public MExpressEntity getExpress() {
		return express;
	}

	public void setExpress(MExpressEntity express) {
		this.express = express;
	}
}
