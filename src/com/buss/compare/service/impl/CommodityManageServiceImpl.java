package com.buss.compare.service.impl;
import com.buss.compare.service.CommodityManageServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.buss.compare.entity.CommodityManageEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("commodityManageService")
@Transactional
public class CommodityManageServiceImpl extends CommonServiceImpl implements CommodityManageServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CommodityManageEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CommodityManageEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CommodityManageEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CommodityManageEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CommodityManageEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CommodityManageEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CommodityManageEntity t){
 		//sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{code}",String.valueOf(t.getCode()));
 		sql  = sql.replace("#{logo}",String.valueOf(t.getLogo()));
 		sql  = sql.replace("#{platform_logo}",String.valueOf(t.getPlatformLogo()));
 		sql  = sql.replace("#{platform_name}",String.valueOf(t.getPlatformName()));
 		sql  = sql.replace("#{store_name}",String.valueOf(t.getStoreName()));
 		sql  = sql.replace("#{is_check}",String.valueOf(t.getIsCheck()));
 		sql  = sql.replace("#{platform}",String.valueOf(t.getPlatform()));
 		sql  = sql.replace("#{product_name}",String.valueOf(t.getProductName()));
		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{sku}",String.valueOf(t.getSku()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}