package com.buss.caiji.service.impl;
import com.buss.caiji.service.ProductServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.caiji.entity.ProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("productService")
@Transactional
public class ProductServiceImpl extends CommonServiceImpl implements ProductServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ProductEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((ProductEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((ProductEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ProductEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ProductEntity t){
 		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{product_name}",String.valueOf(t.getProductName()));
 		sql  = sql.replace("#{product_url}",String.valueOf(t.getProductUrl()));
 		sql  = sql.replace("#{product_img}",String.valueOf(t.getProductImg()));
 		sql  = sql.replace("#{product_price}",String.valueOf(t.getProductPrice()));
		sql  = sql.replace("#{max_price}",String.valueOf(t.getMaxPrice()));
 		sql  = sql.replace("#{product_dsr}",String.valueOf(t.getProductDsr()));
 		sql  = sql.replace("#{product_faceback}",String.valueOf(t.getProductFaceback()));
// 		sql  = sql.replace("#{category_orders}",String.valueOf(t.getCategoryOrders()));
 		sql  = sql.replace("#{product_orders_today}",String.valueOf(t.getProductOrdersToday()));
 		sql  = sql.replace("#{product_orders_yesterday}",String.valueOf(t.getProductOrdersYesterday()));
 		sql  = sql.replace("#{product_orders_all}",String.valueOf(t.getProductOrdersAll()));
 		sql  = sql.replace("#{store_id}",String.valueOf(t.getStoreId()));
 		sql  = sql.replace("#{store_name}",String.valueOf(t.getStoreName()));
// 		sql  = sql.replace("#{store_faceback}",String.valueOf(t.getStoreFaceback()));
// 		sql  = sql.replace("#{store_facebackper}",String.valueOf(t.getStoreFacebackper()));
 		sql  = sql.replace("#{category_id}",String.valueOf(t.getCategoryId()));
// 		sql  = sql.replace("#{discount}",String.valueOf(t.getDiscount()));
 		sql  = sql.replace("#{date}",String.valueOf(t.getDate()));
// 		sql  = sql.replace("#{all_index}",String.valueOf(t.getAllIndex()));
 		sql  = sql.replace("#{last_date}",String.valueOf(t.getLastDate()));
 		sql  = sql.replace("#{is_repeat}",String.valueOf(t.getIsRepeat()));
 		/*sql  = sql.replace("#{week_orders}",String.valueOf(t.getWeekOrders()));
 		sql  = sql.replace("#{amount}",String.valueOf(t.getAmount()));
 		sql  = sql.replace("#{week_amount}",String.valueOf(t.getWeekAmount()));*/
		sql  = sql.replace("#{product_orders3}",String.valueOf(t.getProductOrders3()));
		sql  = sql.replace("#{product_orders7}",String.valueOf(t.getProductOrders7()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}