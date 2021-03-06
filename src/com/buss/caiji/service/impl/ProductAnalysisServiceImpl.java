package com.buss.caiji.service.impl;
import com.buss.caiji.service.ProductAnalysisServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.caiji.entity.ProductAnalysisEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("productAnalysisService")
@Transactional
public class ProductAnalysisServiceImpl extends CommonServiceImpl implements ProductAnalysisServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ProductAnalysisEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((ProductAnalysisEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((ProductAnalysisEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ProductAnalysisEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ProductAnalysisEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ProductAnalysisEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ProductAnalysisEntity t){
 		sql  = sql.replace("#{p_id}",String.valueOf(t.getPId()));
 		sql  = sql.replace("#{c_id}",String.valueOf(t.getCId()));
 		sql  = sql.replace("#{p_name}",String.valueOf(t.getPName()));
 		sql  = sql.replace("#{content}",String.valueOf(t.getContent()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}