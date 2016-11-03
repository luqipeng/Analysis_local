package com.buss.caiji.service.impl;
import com.buss.caiji.service.StoreProductServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.caiji.entity.StoreProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("storeProductService")
@Transactional
public class StoreProductServiceImpl extends CommonServiceImpl implements StoreProductServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((StoreProductEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((StoreProductEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((StoreProductEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(StoreProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(StoreProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(StoreProductEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,StoreProductEntity t){
 		//sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{product_name}",String.valueOf(t.getProductName()));
 		sql  = sql.replace("#{product_url}",String.valueOf(t.getProductUrl()));
 		sql  = sql.replace("#{product_img}",String.valueOf(t.getProductImg()));
 		sql  = sql.replace("#{product_price}",String.valueOf(t.getProductPrice()));
		sql  = sql.replace("#{min_price}",String.valueOf(t.getMinPrice()));
 		sql  = sql.replace("#{product_dsr}",String.valueOf(t.getProductDsr()));
 		sql  = sql.replace("#{product_faceback}",String.valueOf(t.getProductFaceback()));
 		sql  = sql.replace("#{product_orders}",String.valueOf(t.getProductOrders()));
 		sql  = sql.replace("#{store_id}",String.valueOf(t.getStoreId()));
 		sql  = sql.replace("#{store_name}",String.valueOf(t.getStoreName()));
 		sql  = sql.replace("#{store_faceback}",String.valueOf(t.getStoreFaceback()));
 		sql  = sql.replace("#{store_facebackper}",String.valueOf(t.getStoreFacebackper()));
 		sql  = sql.replace("#{discount}",String.valueOf(t.getDiscount()));
 		sql  = sql.replace("#{date}",String.valueOf(t.getDate()));
		sql  = sql.replace("#{seller_admin_seq}",String.valueOf(t.getSellerAdminSeq()));
		sql  = sql.replace("#{first_time}",String.valueOf(t.getFirstTime()));
		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
		sql  = sql.replace("#{sku}",String.valueOf(t.getSku()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}