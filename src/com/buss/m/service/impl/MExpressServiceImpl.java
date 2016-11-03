package com.buss.m.service.impl;
import com.buss.m.service.MExpressServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.m.entity.MExpressEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("mExpressService")
@Transactional
public class MExpressServiceImpl extends CommonServiceImpl implements MExpressServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((MExpressEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((MExpressEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((MExpressEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(MExpressEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(MExpressEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(MExpressEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,MExpressEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{scope}",String.valueOf(t.getScope()));
 		sql  = sql.replace("#{limit_weight}",String.valueOf(t.getLimitWeight()));
 		sql  = sql.replace("#{limit_amount}",String.valueOf(t.getLimitAmount()));
 		sql  = sql.replace("#{is_accept_ele}",String.valueOf(t.getIsAcceptEle()));
 		sql  = sql.replace("#{logistics_tracking}",String.valueOf(t.getLogisticsTracking()));
 		sql  = sql.replace("#{taking_express}",String.valueOf(t.getTakingExpress()));
 		sql  = sql.replace("#{aging}",String.valueOf(t.getAging()));
 		sql  = sql.replace("#{payoff_cost_limit}",String.valueOf(t.getPayoffCostLimit()));
		sql  = sql.replace("#{lifting}",String.valueOf(t.getLifting()));
		sql  = sql.replace("#{express_type}",String.valueOf(t.getExpressType()));
		sql  = sql.replace("#{rate}",String.valueOf(t.getRate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}