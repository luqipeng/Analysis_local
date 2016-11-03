package com.buss.caiji.service.impl;
import com.buss.caiji.service.RuProductServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.caiji.entity.RuProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("ruProductService")
@Transactional
public class RuProductServiceImpl extends CommonServiceImpl implements RuProductServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((RuProductEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((RuProductEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((RuProductEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(RuProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(RuProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(RuProductEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,RuProductEntity t){
 		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{activity_id}",String.valueOf(t.getActivityId()));
 		sql  = sql.replace("#{url}",String.valueOf(t.getUrl()));
 		sql  = sql.replace("#{img}",String.valueOf(t.getImg()));
 		sql  = sql.replace("#{discount}",String.valueOf(t.getDiscount()));
 		sql  = sql.replace("#{count}",String.valueOf(t.getCount()));
 		sql  = sql.replace("#{activity_price}",String.valueOf(t.getActivityPrice()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{end_time}",String.valueOf(t.getEndTime()));
 		sql  = sql.replace("#{last_date}",String.valueOf(t.getLastDate()));
 		sql  = sql.replace("#{is_soldout}",String.valueOf(t.getIsSoldout()));
 		sql  = sql.replace("#{category_gr}",String.valueOf(t.getCategoryGr()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}