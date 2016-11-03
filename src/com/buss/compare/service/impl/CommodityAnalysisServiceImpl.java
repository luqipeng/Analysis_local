package com.buss.compare.service.impl;
import com.buss.compare.service.CommodityAnalysisServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.compare.entity.CommodityAnalysisEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("commodityAnalysisService")
@Transactional
public class CommodityAnalysisServiceImpl extends CommonServiceImpl implements CommodityAnalysisServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CommodityAnalysisEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CommodityAnalysisEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CommodityAnalysisEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CommodityAnalysisEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CommodityAnalysisEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CommodityAnalysisEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CommodityAnalysisEntity t){
 		sql  = sql.replace("#{c_id}",String.valueOf(t.getC_Id()));
 		sql  = sql.replace("#{c_title}",String.valueOf(t.getCTitle()));
 		sql  = sql.replace("#{old_pay_buyer}",String.valueOf(t.getOldPayBuyer()));
 		sql  = sql.replace("#{old_order_buyer}",String.valueOf(t.getOldOrderBuyer()));
 		sql  = sql.replace("#{old_pay_money}",String.valueOf(t.getOldPayMoney()));
 		sql  = sql.replace("#{old_pay_buyer_orders}",String.valueOf(t.getOldPayBuyerOrders()));
 		sql  = sql.replace("#{old_buyer_orders}",String.valueOf(t.getOldBuyerOrders()));
 		sql  = sql.replace("#{old_buyer_rate}",String.valueOf(t.getOldBuyerRate()));
 		sql  = sql.replace("#{old_buyer_visitors}",String.valueOf(t.getOldBuyerVisitors()));
 		sql  = sql.replace("#{search_exposure}",String.valueOf(t.getSearchExposure()));
 		sql  = sql.replace("#{view_num}",String.valueOf(t.getViewNum()));
 		sql  = sql.replace("#{visitors_num}",String.valueOf(t.getVisitorsNum()));
 		sql  = sql.replace("#{buyer_rate}",String.valueOf(t.getBuyerRate()));
 		sql  = sql.replace("#{search_hits}",String.valueOf(t.getSearchHits()));
 		sql  = sql.replace("#{avg_stay_time}",String.valueOf(t.getAvgStayTime()));
 		sql  = sql.replace("#{add_shopping_cart_count}",String.valueOf(t.getAddShoppingCartCount()));
 		sql  = sql.replace("#{add_shopping_cart_person}",String.valueOf(t.getAddShoppingCartPerson()));
 		sql  = sql.replace("#{collection_num}",String.valueOf(t.getCollectionNum()));
 		sql  = sql.replace("#{collection_person}",String.valueOf(t.getCollectionPerson()));
 		sql  = sql.replace("#{order_buyer}",String.valueOf(t.getOrderBuyer()));
 		sql  = sql.replace("#{placing_orders}",String.valueOf(t.getPlacingOrders()));
 		sql  = sql.replace("#{pay_buyer}",String.valueOf(t.getPayBuyer()));
 		sql  = sql.replace("#{pay_orders}",String.valueOf(t.getPayOrders()));
 		sql  = sql.replace("#{pay_money}",String.valueOf(t.getPayMoney()));
 		sql  = sql.replace("#{risk_control_orders}",String.valueOf(t.getRiskControlOrders()));
 		sql  = sql.replace("#{risk_control_money}",String.valueOf(t.getRiskControlMoney()));
 		sql  = sql.replace("#{refund_amount}",String.valueOf(t.getRefundAmount()));
 		sql  = sql.replace("#{refund_orders}",String.valueOf(t.getRefundOrders()));
 		sql  = sql.replace("#{time}",String.valueOf(t.getTime()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}