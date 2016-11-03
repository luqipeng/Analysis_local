package com.buss.attention.service.impl;
import com.buss.attention.service.AttentionProductServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.attention.entity.AttentionProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("attentionProductService")
@Transactional
public class AttentionProductServiceImpl extends CommonServiceImpl implements AttentionProductServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((AttentionProductEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((AttentionProductEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((AttentionProductEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(AttentionProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(AttentionProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(AttentionProductEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,AttentionProductEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{pid}",String.valueOf(t.getPid()));
 		sql  = sql.replace("#{wid}",String.valueOf(t.getWid()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
		sql  = sql.replace("#{img}",String.valueOf(t.getImg()));
		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{count}",String.valueOf(t.getCount()));
 		sql  = sql.replace("#{day1}",String.valueOf(t.getDay1()));
 		sql  = sql.replace("#{day3}",String.valueOf(t.getDay3()));
 		sql  = sql.replace("#{day7}",String.valueOf(t.getDay7()));
 		sql  = sql.replace("#{customerlever}",String.valueOf(t.getCustomerlever()));
 		sql  = sql.replace("#{countrys}",String.valueOf(t.getCountrys()));
		sql  = sql.replace("#{orders}",String.valueOf(t.getOrders()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}