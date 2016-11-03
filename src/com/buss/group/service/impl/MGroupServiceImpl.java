package com.buss.group.service.impl;
import com.buss.group.service.MGroupServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.group.entity.MGroupEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("mGroupService")
@Transactional
public class MGroupServiceImpl extends CommonServiceImpl implements MGroupServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((MGroupEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((MGroupEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((MGroupEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(MGroupEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(MGroupEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(MGroupEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,MGroupEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{store_name}",String.valueOf(t.getStoreName()));
 		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{url}",String.valueOf(t.getUrl()));
 		sql  = sql.replace("#{activity_name}",String.valueOf(t.getActivityName()));
 		sql  = sql.replace("#{time_start}",String.valueOf(t.getTimeStart()));
 		sql  = sql.replace("#{time_end}",String.valueOf(t.getTimeEnd()));
 		sql  = sql.replace("#{history_low}",String.valueOf(t.getHistoryLow()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{num}",String.valueOf(t.getNum()));
 		sql  = sql.replace("#{estimated}",String.valueOf(t.getEstimated()));
 		sql  = sql.replace("#{actual}",String.valueOf(t.getActual()));
		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
		sql  = sql.replace("#{product_name}",String.valueOf(t.getProductName()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}