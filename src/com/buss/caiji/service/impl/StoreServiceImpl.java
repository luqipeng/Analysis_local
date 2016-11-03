package com.buss.caiji.service.impl;
import com.buss.caiji.service.StoreServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.caiji.entity.StoreEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("storeService")
@Transactional
public class StoreServiceImpl extends CommonServiceImpl implements StoreServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((StoreEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((StoreEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((StoreEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(StoreEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(StoreEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(StoreEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,StoreEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{store_id}",String.valueOf(t.getStoreId()));
 		sql  = sql.replace("#{store_url}",String.valueOf(t.getStoreUrl()));
 		sql  = sql.replace("#{store_name}",String.valueOf(t.getStoreName()));
 		sql  = sql.replace("#{store_faceback}",String.valueOf(t.getStoreFaceback()));
 		sql  = sql.replace("#{store_facebackper}",String.valueOf(t.getStoreFacebackper()));
 		sql  = sql.replace("#{is_catch}",String.valueOf(t.getIsCatch()));
		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
		sql  = sql.replace("#{seller_admin_seq}",String.valueOf(t.getSellerAdminSeq()));
		sql  = sql.replace("#{seller_company_id}",String.valueOf(t.getSellerCompanyId()));
		sql  = sql.replace("#{total_num}",String.valueOf(t.getTotalNum()));
		sql  = sql.replace("#{collect_num}",String.valueOf(t.getCollectNum()));
		sql  = sql.replace("#{level}",String.valueOf(t.getLevel()));
		sql  = sql.replace("#{nick_name}",String.valueOf(t.getNickName()));
		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
		sql  = sql.replace("#{three_feedback}",String.valueOf(t.getThreeFeedback()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}