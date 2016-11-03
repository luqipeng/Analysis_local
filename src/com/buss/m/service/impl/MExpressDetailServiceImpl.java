package com.buss.m.service.impl;
import com.buss.m.service.MExpressDetailServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.m.entity.MExpressDetailEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("mExpressDetailService")
@Transactional
public class MExpressDetailServiceImpl extends CommonServiceImpl implements MExpressDetailServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((MExpressDetailEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((MExpressDetailEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((MExpressDetailEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(MExpressDetailEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(MExpressDetailEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(MExpressDetailEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,MExpressDetailEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
// 		sql  = sql.replace("#{express_id}",String.valueOf(t.getExpressId()));
// 		sql  = sql.replace("#{express_name}",String.valueOf(t.getExpressName()));
// 		sql  = sql.replace("#{country_id}",String.valueOf(t.getCountryId()));
// 		sql  = sql.replace("#{country_name}",String.valueOf(t.getCountryName()));
 		sql  = sql.replace("#{delivery_charge}",String.valueOf(t.getDeliveryCharge()));
 		sql  = sql.replace("#{pay_charge}",String.valueOf(t.getPayCharge()));
 		sql  = sql.replace("#{first_weight}",String.valueOf(t.getFirstWeight()));
 		sql  = sql.replace("#{continue_weight}",String.valueOf(t.getContinueWeight()));
		sql  = sql.replace("#{added_weight}",String.valueOf(t.getFirstWeight()));
		sql  = sql.replace("#{billing_weight}",String.valueOf(t.getContinueWeight()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}