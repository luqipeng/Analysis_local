package com.buss.caiji.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.buss.caiji.entity.ProductEntity;
import com.buss.caiji.entity.StoreProductEntity;
import com.buss.compare.entity.CommodityManageEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

import com.buss.caiji.entity.CaijiCategoryEntity;
import com.buss.caiji.service.CaijiCategoryServiceI;
import com.buss.caiji.util.Constant;

public class Cache {

	private static Logger logger = Logger.getLogger(Cache.class);
	
	
	static Statement stmt = null;
	@Getter
	private static Map<Integer, CaijiCategoryEntity> categoryList = new HashMap<Integer, CaijiCategoryEntity>();

	@Getter
	private static Map<Long, List<ProductEntity>> productList = new HashMap<Long, List<ProductEntity>>();


	private static Map<String, StoreProductEntity> storeProductList = new HashMap<String, StoreProductEntity>();

	private static Map<String, List<CommodityManageEntity>> commodityManageList = new HashMap<String, List<CommodityManageEntity>>();

	@Getter
	private static int count = 0;

	public static void initialize() {
		logger.info("Begin initialize master data...");
		categoryList.clear();
		loadCategoryList();
		logger.info("End initialize master data...");

	}

	private static void loadCategoryList() {

		Connection con = null;
		ResultSet rs;
		try {
			con = DriverManager.getConnection(Constant.url_local, Constant.user,
					Constant.password);
		} catch (SQLException se) {
			logger.error(se);
			se.printStackTrace();
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from caiji_category where is_catch = 1");
			while(rs.next()){
				CaijiCategoryEntity caijiCategoryEntity = new CaijiCategoryEntity();
				caijiCategoryEntity.setId(rs.getInt("id"));
				caijiCategoryEntity.setCId(rs.getInt("c_id"));
				caijiCategoryEntity.setCName(rs.getString("c_name"));
				caijiCategoryEntity.setPid(rs.getInt("pid"));
				caijiCategoryEntity.setCount(rs.getInt("count"));
				categoryList.put(rs.getInt("c_id"), caijiCategoryEntity);
				count++;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("init success!");
	}

	public static void loadProductList() {
		productList = new HashMap<Long, List<ProductEntity>>();
		Connection con = null;
		ResultSet rs;
		try {
			con = DriverManager.getConnection(Constant.url_local, Constant.user,
					Constant.password);
		} catch (SQLException se) {
			logger.error(se);
			se.printStackTrace();
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT product_id,product_orders_all,product_orders_today,last_date,product_orders_yesterday FROM product");
			while(rs.next()){
				List<ProductEntity> pl = new ArrayList<ProductEntity>();
				ProductEntity product = new ProductEntity();
				product.setProductOrdersAll(rs.getString("product_orders_all"));
				product.setProductOrdersToday(rs.getString("product_orders_today"));
				product.setLastDate(rs.getDate("last_date"));
				product.setProductOrdersYesterday(rs.getString("product_orders_yesterday"));
				if(productList.get(rs.getLong("product_id"))!=null && productList.get(rs.getLong("product_id")).size()>0) {
					productList.get(rs.getLong("product_id")).add(product);
				}else{
					pl.add(product);
					productList.put(rs.getLong("product_id"), pl);
				}
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("init product success!" + productList.size());
	}

	public static void clearProductList() {
		productList.clear();
	}

	public static void loadStoreProductList() {
		storeProductList = new HashMap<String, StoreProductEntity>();
		Connection con = null;
		ResultSet rs;
		try {
			con = DriverManager.getConnection(Constant.url_local, Constant.user,
					Constant.password);
		} catch (SQLException se) {
			logger.error(se);
			se.printStackTrace();
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT product_id,product_name,product_price,product_img,type FROM store_product");
			while(rs.next()){
				StoreProductEntity product = new StoreProductEntity();
				product.setProductId(rs.getString("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductImg(rs.getString("product_img"));
				product.setProductPrice(rs.getString("product_price"));
				product.setType(rs.getString("type"));//是否上架，1表示上架，0表示下架
				storeProductList.put(rs.getString("product_id"), product);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("init storeProduct success!" + storeProductList.size());
	}

	public static void clearStoreProductList() {
		storeProductList.clear();
	}


	public static void loadCommodityManageList() {
		commodityManageList = new HashMap<String, List<CommodityManageEntity>>();
		Connection con = null;
		ResultSet rs;
		try {
			con = DriverManager.getConnection(Constant.url_local, Constant.user,
					Constant.password);
		} catch (SQLException se) {
			logger.error(se);
			se.printStackTrace();
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT product_id,store_name FROM commodity_manage");
			while(rs.next()){
				List<CommodityManageEntity> pl = new ArrayList<CommodityManageEntity>();
				CommodityManageEntity commodityManageEntity = new CommodityManageEntity();
				commodityManageEntity.setProductId(rs.getString("product_id"));
				commodityManageEntity.setStoreName(rs.getString("store_name"));
				if(commodityManageList.get(rs.getString("product_id"))!=null && commodityManageList.get(rs.getLong("product_id")).size()>0) {
					commodityManageList.get(rs.getString("product_id")).add(commodityManageEntity);
				}else{
					pl.add(commodityManageEntity);
					commodityManageList.put(rs.getString("product_id"), pl);
				}
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("init commondityManageList success!" + commodityManageList.size());
	}

	public static void clearCommodityManageList() {
		commodityManageList.clear();
	}

	public static void setCategoryList(
			Map<Integer, CaijiCategoryEntity> categoryList) {
		Cache.categoryList = categoryList;
	}

	public static void setCount(int count) {
		Cache.count = count;
	}

	public static Map<Integer, CaijiCategoryEntity> getCategoryList() {
		return categoryList;
	}

	public static int getCount() {
		return count;
	}

	public static Map<Long, List<ProductEntity>> getProductList() {
		return productList;
	}

	public static void setProductList(Map<Long, List<ProductEntity>> productList) {
		Cache.productList = productList;
	}


	public static Map<String, StoreProductEntity> getStoreProductList() {
		return storeProductList;
	}

	public static void setStoreProductList(Map<String, StoreProductEntity> storeProductList) {
		Cache.storeProductList = storeProductList;
	}

	public static Map<String, List<CommodityManageEntity>> getCommodityManageList() {
		return commodityManageList;
	}

	public static void setCommodityManageList(Map<String, List<CommodityManageEntity>> commodityManageList) {
		Cache.commodityManageList = commodityManageList;
	}
}
