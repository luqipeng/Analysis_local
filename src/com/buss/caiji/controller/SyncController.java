package com.buss.caiji.controller;

import java.io.File;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import com.buss.attention.entity.AttentionProductEntity;
import com.buss.attention.entity.MChangeEntity;
import com.buss.attention.entity.MProductEntity;
import com.buss.attention.service.AttentionProductServiceI;
import com.buss.attention.service.MChangeServiceI;
import com.buss.attention.service.MProductServiceI;
import com.buss.caiji.common.Cache;
import com.buss.caiji.entity.*;
import com.buss.caiji.service.*;
import com.buss.compare.entity.CommodityManageEntity;
import com.buss.report.entity.MReportEntity;
import com.buss.report.service.MReportServiceI;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buss.caiji.method.ConnectionParam;
import com.buss.caiji.method.ConnectionPool;
import com.buss.caiji.util.Constant;
import com.buss.caiji.util.Tools;

@Scope("prototype")
@Controller
@RequestMapping("/syncController")
public class SyncController extends BaseController{

	@Autowired
	private ProductServiceI productService;
	@Autowired
	private ProductLogServiceI productLogServiceI;
	@Autowired
	private ProductAnalysisServiceI productAnalysisServiceI;

	@Autowired
	private MProductServiceI mProductServiceI;
	@Autowired
	private AttentionProductServiceI attentionProductServiceI;
	@Autowired
	private MReportServiceI mReportService;
	@Autowired
	private MChangeServiceI mChangeService;
	private String message;

	@Autowired
	private StoreServiceI storeService;

	private boolean isCanCloseSql = true;
	private long total = 0;
	private static Date today;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	List<String> productIds = new ArrayList<String>();

	@RequestMapping(params = "sync")
	public void sync(){
		try {
			System.out.println("开始同步");
			syncRU();
			syncCareProduct();
			syncStore();

			//更新下架产品到变更表
			syncStoreDown();

			Cache.loadProductList();
			SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
			String tableName = "product_"+df.format(new Date());
			today = new Date();
			syncData(0, tableName, "product_base");
			//执行分析统计
			try {
//				analysisData(tableName);
//				analysisWeekOrdersData("product_analysis");
				repairProductToReal();
				//doStatistics();

				//添加今日销量到product_daily表,同时统计3 7 天销量到product表。
				addToDaily();

				//同步店铺信息---这个本地进行采集同步
				syncStoreInfo();

			} catch (Exception e) {
				e.printStackTrace();
			}
			Cache.clearProductList();
			System.out.println("同步结束");
		} catch (Exception e) {
			logger.error(e);
		}
	}


	/**
	 * 新增时type 默认设置为1 表示当前最新的， 每日更新下架后把下架的产品type改成0，表示已经下架，防止重复执行下架操作。
	 */
	private void syncStoreDown() {
		//本地数据库
		ConnectionParam param_local = new ConnectionParam();
		param_local.setDriver(Constant.driver);
		param_local.setPassword(Constant.password);
		param_local.setUser(Constant.user);
		param_local.setUrl(Constant.url_local);
		connectionPool_local = new ConnectionPool(param_local);
		try {
			connectionPool_local.createPool();
			Connection conn_local = connectionPool_local.getConnection();
			Statement prepareStmt = conn_local.createStatement();
			String sql = "insert into store_product_change(p_id,p_name,time,type,old,now,store_id) values (?,?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement)conn_local.prepareStatement(sql);

			Statement sm = conn_local.createStatement();
			Calendar timeStart = Calendar.getInstance();
			timeStart.add(Calendar.DAY_OF_MONTH, -1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String beginTime = sdf.format(timeStart.getTime());
			ResultSet downRs = sm.executeQuery("select * from store_product where date <= '" + beginTime + "' and type = 1");
			while (downRs.next()) {
				ps.setString(1, downRs.getString("product_id"));
				ps.setString(2, downRs.getString("product_name"));
				ps.setString(3, downRs.getString("date"));
				ps.setString(4, "下架");
				ps.setString(5, "");
				ps.setString(6, "");
				ps.setString(7, downRs.getString("store_id"));
				ps.addBatch();
			}
			sm.execute("update store_product set type = 0 where date <= '" + beginTime + "'");
			ps.executeBatch();
			ps.close();
			conn_local.close();
			conn_local.commit();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 更新店铺信息
	 */
	public void syncStoreInfo(){
		List<StoreEntity> list = storeService.loadAll(StoreEntity.class);
		for(StoreEntity store:list){
			//设置sellerAdminSeq
			Tools.setSellerAdminSeq(store);
			//采集 产品数和收藏量
			Tools.setProductNum(store);
			//采集店铺创建时间和三个月的好评数
			Tools.setCreateTime(store);
			storeService.updateEntitie(store);
		}
	}

	/**
	 * 店铺产品同步
	 */
	@RequestMapping(params = "syncStore")
	public void syncStore(){
		try {
			System.out.println("开始同步店铺产品");
			SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String tableName = "product_"+df.format(new Date());
			today = new Date();
			Cache.loadStoreProductList();
			Cache.loadCommodityManageList();

			// 服务器数据库
			ConnectionParam param = new ConnectionParam();
			param.setDriver(Constant.driver);
			param.setPassword(Constant.password);
			param.setUser(Constant.user);
			param.setUrl(Constant.url_service);
			connectionPool = new ConnectionPool(param);

			//本地数据库
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_local);
			connectionPool_local = new ConnectionPool(param_local);

			connectionPool_local.createPool();
			connectionPool.createPool();

			Connection conn_local = connectionPool_local.getConnection();
			conn_local.setAutoCommit(false);

			Connection conn = connectionPool.getConnection();
			Statement prepareStmt = conn.createStatement();
			ResultSet rs = prepareStmt.executeQuery("select * from store_product");
			Map<String,StoreProductEntity> storeProductList = Cache.getStoreProductList();
			Map<String, List<CommodityManageEntity>> commodityManageList = Cache.getCommodityManageList();

			String sql = "insert into store_product(product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,product_orders,min_price,store_id,store_name," +
					"seller_admin_seq,date,discount,first_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql2 = "insert into store_product_change(p_id,p_name,time,type,old,now,store_id) values (?,?,?,?,?,?,?)";
			String sql3 = "insert into commodity_manage(product_id,store_name) values (?,?)";
			PreparedStatement ps = (PreparedStatement)conn_local.prepareStatement(sql);
			PreparedStatement ps2 = (PreparedStatement)conn_local.prepareStatement(sql2);
			PreparedStatement ps3 = (PreparedStatement)conn_local.prepareStatement(sql3);
			PreparedStatement ps_update = conn_local.prepareStatement("UPDATE store_product SET " +
					" product_name=?, product_url=?, product_img=?, product_price=?, product_dsr=?" +
					", product_faceback=?, product_orders=?, min_price=?, store_id=?, store_name=? "+
					",seller_admin_seq=?,date=?,discount=?  where product_id = ?;");
			/*PreparedStatement ps2_update = conn_local.prepareStatement("UPDATE store_product_change SET " +
					" p_name=?, time=?, type=?, old=?, now=?,store_id=? where p_id = ?;");*/
			while(rs.next()){
				if(commodityManageList==null || !commodityManageList.containsKey(rs.getString("product_id"))){
					ps3.setString(1,rs.getString("product_id"));
					ps3.setString(2,rs.getString("store_name"));
					commodityManageList.put(rs.getString("product_id"),null);
					ps3.addBatch();
				}
				if(storeProductList!=null && storeProductList.containsKey(rs.getString("product_id"))){
					StoreProductEntity storeProduct = storeProductList.get(rs.getString("product_id"));
					if(storeProduct != null){
						//判断变更类型
						/*if(storeProduct.getProductName()==null||!storeProduct.getProductName().equals(rs.getString("product_name"))){
							ps2.setString(1, rs.getString("product_id"));
							ps2.setString(2, rs.getString("product_name"));
							ps2.setString(3, rs.getString("date"));
							ps2.setString(4, "产品名称");
							ps2.setString(5, storeProduct.getProductName());
							ps2.setString(6, rs.getString("product_name"));
							ps2.setString(7, rs.getString("store_id"));
							ps2.addBatch();
						}*/

						if(storeProduct.getProductPrice()==null||!storeProduct.getProductPrice().equals(rs.getString("product_price"))){
							ps2.setString(1, rs.getString("product_id"));
							ps2.setString(2, rs.getString("product_name"));
							ps2.setString(3, rs.getString("date"));
							ps2.setString(4, "价格");
							ps2.setString(5, storeProduct.getProductPrice());
							ps2.setString(6, rs.getString("product_price"));
							ps2.setString(7, rs.getString("store_id"));
							ps2.addBatch();
						}

						if(storeProduct.getProductImg()==null||!storeProduct.getProductImg().equals(rs.getString("product_img"))){
							ps2.setString(1, rs.getString("product_id"));
							ps2.setString(2, rs.getString("product_name"));
							ps2.setString(3, rs.getString("date"));
							ps2.setString(4, "主图");
							ps2.setString(5, storeProduct.getProductImg());
							ps2.setString(6, rs.getString("product_img"));
							ps2.setString(7, rs.getString("store_id"));
							ps2.addBatch();
						}

						//是否是新出单
						if(storeProduct.getProductOrders()==null||(storeProduct.getProductOrders().equals("0") && !rs.getString("product_orders").equals("0"))){
							ps2.setString(1, rs.getString("product_id"));
							ps2.setString(2, rs.getString("product_name"));
							ps2.setString(3, rs.getString("date"));
							ps2.setString(4, "新出单");
							ps2.setString(5, "");
							ps2.setString(6, "");
							ps2.setString(7, rs.getString("store_id"));
							ps2.addBatch();
						}

						if("0".equals(storeProduct.getType())){
							ps2.setString(1, rs.getString("product_id"));
							ps2.setString(2, rs.getString("product_name"));
							ps2.setString(3, rs.getString("date"));
							ps2.setString(4, "上架");
							ps2.setString(5, "");
							ps2.setString(6, "");
							ps2.setString(7, rs.getString("store_id"));
							ps2.addBatch();
						}

						//执行更新操作

						ps_update.setString(14, rs.getString("product_id"));
						ps_update.setString(1, rs.getString("product_name"));
						ps_update.setString(2, rs.getString("product_url"));
						ps_update.setString(3, rs.getString("product_img"));
						ps_update.setString(4, rs.getString("product_price"));
						ps_update.setDouble(5, rs.getDouble("product_dsr"));
						ps_update.setDouble(6, rs.getDouble("product_faceback"));
						ps_update.setLong(7, rs.getLong("product_orders"));
						ps_update.setString(8, rs.getString("min_price"));
						ps_update.setString(9, rs.getString("store_id"));
						ps_update.setString(10, rs.getString("store_name"));
						ps_update.setString(11, rs.getString("seller_admin_seq"));
						ps_update.setString(12, rs.getString("date"));
						ps_update.setString(13, rs.getString("discount"));
						ps_update.addBatch();
					}

					//st.execute("update store_product set product_name = '"+rs.getString("product_name")+"'  where product_id = '"+rs.getString("product_id")+"'");
				}else{
					//执行新增操作
					ps.setString(1, rs.getString("product_id"));
					ps.setString(2, rs.getString("product_name"));
					ps.setString(3, rs.getString("product_url"));
					ps.setString(4, rs.getString("product_img"));
					ps.setString(5, rs.getString("product_price"));
					ps.setDouble(6, rs.getDouble("product_dsr"));
					ps.setDouble(7, rs.getDouble("product_faceback"));
					ps.setLong(8, rs.getLong("product_orders"));
					ps.setString(9, rs.getString("min_price"));
					ps.setString(10, rs.getString("store_id"));
					ps.setString(11, rs.getString("store_name"));
					ps.setString(12, rs.getString("seller_admin_seq"));
					ps.setString(13, rs.getString("date"));//更新时间
					ps.setString(14, rs.getString("discount"));
					ps.setString(15, df2.format(new Date()));
					ps.addBatch();
					storeProductList.put(rs.getString("product_id"), null);
					//p_id,p_name,time,type,old,now,store_id
					ps2.setString(1, rs.getString("product_id"));
					ps2.setString(2, rs.getString("product_name"));
					ps2.setString(3, rs.getString("date"));
					ps2.setString(4, "新建产品");
					ps2.setString(5, "");
					ps2.setString(6, "于 "+df1.format(new Date())+" 初次获取数据");
					ps2.setString(7, rs.getString("store_id"));
					ps2.addBatch();

					//标记上架到变动表
					ps2.setString(1, rs.getString("product_id"));
					ps2.setString(2, rs.getString("product_name"));
					ps2.setString(3, rs.getString("date"));
					ps2.setString(4, "上架");
					ps2.setString(5, "");
					ps2.setString(6, "");
					ps2.setString(7, rs.getString("store_id"));
					ps2.addBatch();
				}
			}
			ps.executeBatch();
			ps.close();
			ps3.executeBatch();
			ps3.close();

			ps2.executeBatch();
			ps2.close();
			ps_update.executeBatch();
			ps_update.close();
			conn_local.commit();
			//标记下架到变动表
			/*Statement sm = conn_local.createStatement();
			Calendar timeStart = Calendar.getInstance();
			timeStart.add(Calendar.DAY_OF_MONTH, -1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String beginTime = sdf.format(timeStart.getTime());
			ResultSet downRs = sm.executeQuery("select * from store_product where date <= '" +beginTime + "' and type = 1");
			while (downRs.next()){
				ps2.setString(1, downRs.getString("product_id"));
				ps2.setString(2, downRs.getString("product_name"));
				ps2.setString(3, downRs.getString("date"));
				ps2.setString(4, "下架");
				ps2.setString(5, "");
				ps2.setString(6, "");
				ps2.setString(7, rs.getString("store_id"));
				ps2.addBatch();
			}
			sm.executeQuery("update store_product set type = 0 where date <= '" +beginTime + "'");
			ps2.executeBatch();
			ps2.close();
			ps_update.executeBatch();
			ps_update.close();
			conn_local.commit();*/
			conn_local.close();
			conn.close();
			Cache.clearStoreProductList();
			Cache.clearCommodityManageList();
			System.out.println("同步店铺产品结束");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}


	public void addToDaily(){
		ConnectionParam param_local = new ConnectionParam();
		param_local.setDriver(Constant.driver);
		param_local.setPassword(Constant.password);
		param_local.setUser(Constant.user);
		param_local.setUrl(Constant.url_local);
		connectionPool = new ConnectionPool(param_local);

		try{
			connectionPool.createPool();

			Connection conn = connectionPool.getConnection();
			Statement prepareStmt = conn.createStatement();

			Calendar timeStart = Calendar.getInstance();
			timeStart.add(Calendar.DAY_OF_MONTH, -10);
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			String beginTime = sfd.format(timeStart.getTime());
			prepareStmt.execute("delete from product_daily where date < '"+beginTime+"'");
			//prepareStmt.execute("Truncate Table product_daily");
			SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
			Calendar calendar = Calendar.getInstance();
			//calendar.add(Calendar.DAY_OF_MONTH, -1);
			String sql = "insert into product_daily (product_id,product_orders_today,date) select product_id,product_orders_today,date from product_";
			prepareStmt.execute(sql + fd.format(calendar.getTime()));
			deleteProductDaily(prepareStmt, fd.format(calendar.getTime()));

			Calendar calendar1 = Calendar.getInstance();
			calendar1.add(Calendar.DAY_OF_MONTH, -3);
			//统计3天销量
			String sql3 = "update product as p inner join (select product_id, sum(product_orders_today) as last3_count from product_daily where date >= '"+fd.format(calendar1.getTime())+"' group by product_id) as d on p.product_id = d.product_id set p.product_orders3 = d.last3_count;";
			calendar1.add(Calendar.DAY_OF_MONTH, -4);
			prepareStmt.execute(sql3);
			System.out.println(sql3);
			//统计7天销量
			String sql7 = "update product as p inner join (select product_id, sum(product_orders_today) as last7_count from product_daily where date >= '"+fd.format(calendar1.getTime())+"' group by product_id) as d on p.product_id = d.product_id set p.product_orders7 = d.last7_count;";
			System.out.println(sql7);
			prepareStmt.execute(sql7);
			connectionPool.closeConnectionPool();
		}catch(Exception e){

		}
	}

	/**
	 * 更新m_report
	 *
	 * @param ids
	 * @return
	 */

	public void doStatistics() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
		Calendar calendar = Calendar.getInstance();

		DecimalFormat ddf = new DecimalFormat("0.00");
		try {
			// 本地数据库
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_local);
			ConnectionPool connectionPool = new ConnectionPool(param_local);
			try {
				connectionPool.createPool();
			} catch (Exception e) {
				logger.error(e);
			}
			Connection conn = connectionPool.getConnection();
			Statement st = conn.createStatement();

			//只保留一周数据
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			st.execute("delete from m_report where date < '" + df.format(calendar.getTime()) + "'");

			Calendar calendar1 = Calendar.getInstance();
			calendar1.add(Calendar.DAY_OF_MONTH, -1);
			String tableName = "product_"+df.format(calendar1.getTime());
			ResultSet rs = st.executeQuery("SELECT ROUND(sum(product_price*product_orders_today),2),category_id,sum(product_orders_today) FROM `" + tableName + "` where product_orders_today >0 GROUP BY category_id;  ");
			MReportEntity mReport;
			CaijiCategoryEntity categoryEntity = null;
			while(rs.next()){
				categoryEntity = caijiCategoryService.getEntity(CaijiCategoryEntity.class,rs.getString(2));
				mReport = new MReportEntity();
				mReport.setCId(rs.getString(2));
				mReport.setcName(categoryEntity.getCName());
				mReport.setDate(calendar1.getTime());
				mReport.setTotalCount(rs.getInt(3));
				mReport.setTotalMoney(ddf.format(rs.getDouble(1)) + "");
				mReport.setUnitPrice(ddf.format(rs.getDouble(1) / rs.getInt(3)) + "");
				mReportService.save(mReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	/*
	 * 下载同步数据
	 */
	@RequestMapping(params = "download")
	public void download(){
		try {
			//执行下载
			Tools.download();
			//解压
			Tools.unZipFiles(new File("d:/product_base.zip"), "d:/sql");
			//执行脚本
			Tools.execute("d:/sql/product_base.sql");

			syncRU();

			System.out.println("脚本同步完毕");
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/*
	 * 下载同步数据
	 */
	@RequestMapping(params = "downloadAndRun")
	@ResponseBody
	public AjaxJson downloadAndRun(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		try {
			//执行下载
			System.out.println("脚本同步开始");
			//Tools.download();
			//解压
			//Tools.unZipFiles(new File("d:/product_base.zip"), "d:/sql");
			//执行脚本
			Tools.execute("d:/sql/product_base.sql");

			System.out.println("脚本同步完毕");
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setMsg("脚本同步完毕");
		return j;
	}


	/**
	 * 销量统计初始化
	 */
	@RequestMapping(params = "initOrdersStatistics")
	public void initOrdersStatistics(){
		try {
			Long start = System.currentTimeMillis();
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_local);
			SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd");
			Calendar calendar = Calendar.getInstance();
			connectionPool = new ConnectionPool(param_local);

			connectionPool.createPool();

			Connection conn = connectionPool.getConnection();
			Statement prepareStmt = conn.createStatement();
			//清空表
			prepareStmt.execute("Truncate Table product_daily");
			//插入最近一周的当日销量到product_daily表里面
			String sql = "insert into product_daily (product_id,product_orders_today,date) select product_id,product_orders_today,date from product_";
			for(int i=0;i<7;i++){
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				prepareStmt.execute(sql + fd.format(calendar.getTime()));
				System.out.println(sql + fd.format(calendar.getTime()));

				//删除重复数据（product_id+date）
				deleteProductDaily(prepareStmt,fd.format(calendar.getTime()));
			}
			Calendar calendar1 = Calendar.getInstance();
			calendar1.add(Calendar.DAY_OF_MONTH, -3);
			//统计3天销量
			String sql3 = "update `product` as `p` inner join (select product_id, sum(product_orders_today) as last3_count from `product_daily` where `date` >= '"+fd.format(calendar1.getTime())+"' group by `product_id`) as `d` on `p`.`product_id` = `d`.`product_id` set p.product_orders3 = d.last3_count;";
			calendar1.add(Calendar.DAY_OF_MONTH, -4);
			prepareStmt.execute(sql3);
			System.out.println(sql3);
			//统计7天销量
			String sql7 = "update `product` as `p` inner join (select product_id, sum(product_orders_today) as last7_count from `product_daily` where `date` >= '"+fd.format(calendar1.getTime())+"' group by `product_id`) as `d` on `p`.`product_id` = `d`.`product_id` set p.product_orders7 = d.last7_count;";
			System.out.println(sql7);
			prepareStmt.execute(sql7);
			Long end = System.currentTimeMillis();
			System.out.println("耗时"+(end-start));
			connectionPool.closeConnectionPool();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 去重
	 * @param prepareStmt
	 * @param date
	 */
	public void deleteProductDaily(Statement prepareStmt,String date){
		try{
			ResultSet rs = prepareStmt.executeQuery("SELECT product_id,count(product_id) as count FROM `product_daily` where date = '"+date+"' GROUP BY product_id HAVING count>1;");
			rs.last(); //移到最后一行
			int rowCount = rs.getRow();
			if(rowCount>0){
				prepareStmt.execute("delete from product_daily  where id in (select id from (select  max(id) as id,count(product_id) as count from product_daily where date = '"+date+"' group by product_id having count >1 order by count desc) as tab )");
				deleteProductDaily(prepareStmt,date);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	/*
	 * 下载同步数据
	 */
	@RequestMapping(params = "deleteOver")
	public void deleteOver(){
		try {
			deleteOverProduct(null);

			System.out.println("数据清除成功！");
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void syncRU() throws SQLException{
		System.out.println("开始俄团同步");
		// 服务器数据库
		ConnectionParam param = new ConnectionParam();
		param.setDriver(Constant.driver);
		param.setPassword(Constant.password);
		param.setUser(Constant.user);
		param.setUrl(Constant.url_service);
		connectionPool = new ConnectionPool(param);

		// 本地数据库
		ConnectionParam param_local = new ConnectionParam();
		param_local.setDriver(Constant.driver);
		param_local.setPassword(Constant.password);
		param_local.setUser(Constant.user);
		param_local.setUrl(Constant.url_local);
		connectionPool_local = new ConnectionPool(param_local);
		try {
			connectionPool.createPool();
			connectionPool_local.createPool();
		} catch (Exception e) {
			logger.error(e);
		}
		conn_local = connectionPool_local.getConnection();
		Statement prepareStmt = conn_local.createStatement();
		prepareStmt.execute(" TRUNCATE TABLE ru_product " );


		conn = connectionPool.getConnection();
		conn.setAutoCommit(false);

		// 获得核心库连接
		Statement coreStmt = conn.createStatement();
		// 获得目标库连接
		conn_local.setAutoCommit(false);// 设置手动提交
		PreparedStatement targetPstmt = conn_local.prepareStatement("insert into ru_product(product_id,activity_id,url,img,discount,count,activity_price,price,end_time,last_date,is_soldout,category_gr) " +
				"values (?,?,?,?,?,?,?,?,?,?,?,?)");
		ResultSet coreRs = coreStmt.executeQuery("select * from ru_product");
		log.info(Thread.currentThread().getName()+"'s Query SQL:select * from ru_product:");
		while (coreRs.next()) {
			//装配pm 数据库插入组装
			targetPstmt.setString(1, coreRs.getString("product_id"));  // product_id
			targetPstmt.setString(2, coreRs.getString("activity_id"));  //activity_id
			targetPstmt.setString(3, coreRs.getString("url"));  //url
			targetPstmt.setString(4, coreRs.getString("img"));  //url
			targetPstmt.setString(5, coreRs.getString("discount"));  //url
			targetPstmt.setString(6, coreRs.getString("count"));  //url
			targetPstmt.setString(7, coreRs.getString("activity_price"));  //url
			targetPstmt.setString(8, coreRs.getString("price"));  //url
			targetPstmt.setString(9, coreRs.getString("end_time"));  //url
			targetPstmt.setString(10, coreRs.getString("last_date"));  //url
			targetPstmt.setString(11, coreRs.getString("is_soldout"));  //url
			targetPstmt.setString(12, coreRs.getString("category_gr"));  //category_gr
			targetPstmt.addBatch();
		}
		// 提交剩余的批处理
		targetPstmt.executeBatch();
		targetPstmt.clearBatch();
		coreStmt.close();
		conn_local.commit();

		conn.close();
		logger.info("核心库ru_product表数据同步完成");
		currentSynCount.set(0);
		conn_local.close();
		connectionPool_local.closeConnectionPool();
		connectionPool_local = null;
	}


	//删除俄团超期数据
	@RequestMapping(params = "deleteOverRUProduct")
	@ResponseBody
	public AjaxJson deleteOverRUProduct(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd");
		try {
			// 本地数据库
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_local);
			ConnectionPool connectionPool = new ConnectionPool(param_local);
			try {
				connectionPool.createPool();
			} catch (Exception e) {
				logger.error(e);
			}
			Connection conn = connectionPool.getConnection();
			Statement prepareStmt = conn.createStatement();
			//执行删除ru_product表超过一个月的垃圾数据
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			prepareStmt.execute("delete from ru_product where last_date < '" + f.format(calendar.getTime()) +"'");

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		message = "删除成功";
		j.setMsg(message);
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-start));
		return j;
	}


	//删除超期数据
	@RequestMapping(params = "deleteOverProduct")
	@ResponseBody
	public AjaxJson deleteOverProduct(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd");
		try {
			// 本地数据库
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_local);
			ConnectionPool connectionPool = new ConnectionPool(param_local);
			try {
				connectionPool.createPool();
			} catch (Exception e) {
				logger.error(e);
			}
			Connection conn = connectionPool.getConnection();
			Statement prepareStmt = conn.createStatement();
			//执行删除product表超过8天的垃圾数据
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -8);
			prepareStmt.execute("delete from product_analysis where p_id in (select product_id from product where last_date < '" + f.format(calendar.getTime()) + "')");
			prepareStmt.execute("delete from product where last_date < '" + f.format(calendar.getTime()) +"'");
			//删除每日采集表
			ResultSet rs = prepareStmt.executeQuery("select * from product_log where table_name = 'product_" + fd.format(calendar.getTime()) +"'");
			if(rs.next()){
				int id = rs.getInt("id");
				ResultSet dropRS = prepareStmt.executeQuery("select * from product_log where id < " + id);
				while(dropRS.next()){
					prepareStmt.execute("DROP TABLE " + dropRS.getString("table_name"));
				}
				prepareStmt.execute("delete from product_log where id < " + id);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		message = "删除成功";
		j.setMsg(message);
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-start));
		return j;
	}


	@RequestMapping(params = "syncProduct")
	@ResponseBody
	public AjaxJson syncProduct(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();
		Cache.loadProductList();
		//建表  product_YYYY_MM_DD
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
		String tableName = "product_"+df.format(new Date());
		try {
			System.out.println("开始同步");
			syncData(0, tableName, "product_base");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		message = "产品同步成功";
		j.setMsg(message);
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start));

		//执行分析统计
		try {
//			analysisData(tableName);
			//analysisWeekOrdersData("product_analysis");

			//更新product表，留下一条repeat为1，产品信息改成一致。
			repairProductToReal();
			//doStatistics();

			//添加今日销量到product_daily表,同时统计3 7 天销量到product表。
			addToDaily();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cache.clearProductList();
		return j;
	}


	/**
	 * 同步关注产品
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "syncCareProduct")
	@ResponseBody
	public AjaxJson syncCareProduct(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();
		System.out.println("开始同步");

		try {
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_service);
			connectionPool_local = new ConnectionPool(param_local);
			connectionPool_local.createPool();
			conn_local = connectionPool_local.getConnection();
			Statement st = conn_local.createStatement();
			List<MProductEntity> list = mProductServiceI.loadAll(MProductEntity.class);
			for(MProductEntity mProduct:list){
				ResultSet rs = st.executeQuery("select * from Attention_Product where pid = "+ mProduct.getPid());
				//判断价格、图片、标题是否发生变化
				//type变化类别：1：价格；2：图片；3：标题；4：直通车
				while(rs.next()){
					boolean isChange = false;
					if((mProduct.getPrice()!=null ) && (rs.getString("price")!=null || rs.getString("price") != "null")){
						if(!mProduct.getPrice().trim().equals(rs.getString("price").trim())){
							MChangeEntity mChange = new MChangeEntity();
							mChange.setPid(mProduct.getPid());
							mChange.setType(1);
							mChange.setOld(mProduct.getPrice());
							mChange.setNow(rs.getString("price"));
							mChange.setDate(new Date());
							isChange = true;
							mChangeService.save(mChange);
						}

					}
					if((mProduct.getImg()!=null) && (rs.getString("img")!=null || rs.getString("img") != "null")){
						String old = mProduct.getImg().substring(20,mProduct.getImg().length()-1);
						String now = rs.getString("img").substring(20,rs.getString("img").length()-1);
						if(!old.equals(now)){
						//if(!mProduct.getImg().equals(rs.getString("img"))){
							MChangeEntity mChange = new MChangeEntity();
							mChange.setPid(mProduct.getPid());
							mChange.setType(2);
							mChange.setOld(mProduct.getImg());
							mChange.setNow(rs.getString("img"));
							mChange.setDate(new Date());
							isChange = true;
							mChangeService.save(mChange);
						}

					}
					if((mProduct.getName()!=null) && (rs.getString("name") != null || rs.getString("name") != "null")){
						if(!mProduct.getName().trim().equals(rs.getString("name").trim())){
							MChangeEntity mChange = new MChangeEntity();
							mChange.setPid(mProduct.getPid());
							mChange.setType(3);
							mChange.setOld(mProduct.getName());
							mChange.setNow(rs.getString("name"));
							mChange.setDate(new Date());
							isChange = true;
							mChangeService.save(mChange);
						}

					}
					//直通车

					if(rs.next()){
						mProduct.setDay1(Integer.parseInt(rs.getString("day1")));
						mProduct.setDay3(Integer.parseInt(rs.getString("day3")));
						mProduct.setDay7(Integer.parseInt(rs.getString("day7")));
						mProduct.setCount(Integer.parseInt(rs.getString("count")));
						mProduct.setCustomerlever(rs.getString("customerlever"));
						mProduct.setCountrys(rs.getString("countrys"));
						mProduct.setOrders(rs.getString("orders"));
						mProduct.setMaxPrice(rs.getString("max_price"));
						mProduct.setMinPrice(rs.getString("min_price"));
						mProduct.setSname(rs.getString("store_name"));
						if(isChange){
							mProduct.setLastDate(new Date());//变更时间
							mProduct.setViewed(0);
							mProduct.setName(rs.getString("name"));
							mProduct.setImg(rs.getString("img"));
							mProduct.setPrice(rs.getString("price"));
						}
						mProductServiceI.saveOrUpdate(mProduct);
					}
				}

			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		message = "关注产品同步成功";
		j.setMsg(message);
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start));
		return j;
	}

	public void syncCareProduct(){
		long start = System.currentTimeMillis();
		System.out.println("开始关注产品同步");
		try {
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_service);
			connectionPool_local = new ConnectionPool(param_local);
			connectionPool_local.createPool();
			conn_local = connectionPool_local.getConnection();
			Statement st = conn_local.createStatement();
			List<MProductEntity> list = mProductServiceI.loadAll(MProductEntity.class);
			for(MProductEntity mProduct:list){
				ResultSet rs = st.executeQuery("select * from Attention_Product where pid = "+ mProduct.getPid());
				if(rs.next()){
					boolean isChange = false;
					if((mProduct.getPrice()!=null ) && (rs.getString("price")!=null || rs.getString("price") != "null")){
						if(!mProduct.getPrice().trim().equals(rs.getString("price").trim())){
							MChangeEntity mChange = new MChangeEntity();
							mChange.setPid(mProduct.getPid());
							mChange.setType(1);
							mChange.setOld(mProduct.getPrice());
							mChange.setNow(rs.getString("price"));
							mChange.setDate(new Date());
							isChange = true;
							mChangeService.save(mChange);
						}

					}
					if((mProduct.getImg()!=null) && (rs.getString("img")!=null || rs.getString("img") != "null")){
						String old = mProduct.getImg().substring(20,mProduct.getImg().length()-1);
						String now = rs.getString("img").substring(20,rs.getString("img").length()-1);
						if(!old.equals(now)){
							//if(!mProduct.getImg().equals(rs.getString("img"))){
							MChangeEntity mChange = new MChangeEntity();
							mChange.setPid(mProduct.getPid());
							mChange.setType(2);
							mChange.setOld(mProduct.getImg());
							mChange.setNow(rs.getString("img"));
							mChange.setDate(new Date());
							isChange = true;
							mChangeService.save(mChange);
						}

					}
					if((mProduct.getName()!=null) && (rs.getString("name") != null || rs.getString("name") != "null")){
						if(!mProduct.getName().trim().equals(rs.getString("name").trim())){
							MChangeEntity mChange = new MChangeEntity();
							mChange.setPid(mProduct.getPid());
							mChange.setType(3);
							mChange.setOld(mProduct.getName());
							mChange.setNow(rs.getString("name"));
							mChange.setDate(new Date());
							isChange = true;
							mChangeService.save(mChange);
						}

					}
					//直通车

					if(rs.next()){
						mProduct.setDay1(Integer.parseInt(rs.getString("day1")));
						mProduct.setDay3(Integer.parseInt(rs.getString("day3")));
						mProduct.setDay7(Integer.parseInt(rs.getString("day7")));
						mProduct.setCount(Integer.parseInt(rs.getString("count")));
						mProduct.setCustomerlever(rs.getString("customerlever"));
						mProduct.setCountrys(rs.getString("countrys"));
						mProduct.setOrders(rs.getString("orders"));
						mProduct.setMaxPrice(rs.getString("max_price"));
						mProduct.setMinPrice(rs.getString("min_price"));
						mProduct.setSname(rs.getString("store_name"));
						if(isChange){
							mProduct.setLastDate(new Date());//变更时间
							mProduct.setViewed(0);
							mProduct.setName(rs.getString("name"));
							mProduct.setImg(rs.getString("img"));
							mProduct.setPrice(rs.getString("price"));
						}
						mProductServiceI.saveOrUpdate(mProduct);
					}
				}

			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}


	@RequestMapping(params = "repairProduct")
	@ResponseBody
	public AjaxJson repairProduct(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();
		message = "数据修改成功";

		//执行分析统计
		try {
			repairProductToReal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setMsg(message);
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-start));
		return j;
	}

	private void repairProductToReal() throws Exception{
		ConnectionParam param_local = new ConnectionParam();
		param_local.setDriver(Constant.driver);
		param_local.setPassword(Constant.password);
		param_local.setUser(Constant.user);
		param_local.setUrl(Constant.url_local);
		connectionPool_local = new ConnectionPool(param_local);
		connectionPool_local.createPool();
		conn_local = connectionPool_local.getConnection();
		Statement st = conn_local.createStatement();
		System.out.println("修正数据开始");
		st.execute("update product set is_repeat = 0");
		st.execute("update product p,(select product_id,max(category_id) cid from product group by product_id) a set p.is_repeat = 1 where p.product_id = a.product_id and p.category_id = a.cid");
		System.out.println("修正数据结束");
		connectionPool_local.closeConnectionPool();
	}


	@RequestMapping(params = "analysisWeekOrder")
	@ResponseBody
	public AjaxJson analysisWeekOrder(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();
		message = "产品周销量统计成功";

		//执行分析统计
		try {
			analysisWeekOrdersData("product_analysis");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setMsg(message);
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start));
		return j;
	}


	@RequestMapping(params = "initAnalysis")
	@ResponseBody
	public AjaxJson initAnalysis(){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();

		Connection con = null;
		Statement st = null;
		try{
			con = DriverManager.getConnection(Constant.url_local , Constant.user , Constant.password ) ;
			st = con.createStatement();
			st.execute("TRUNCATE TABLE product_analysis");
			con.close();
		}catch(SQLException se){
			System.out.println("数据库连接失败！");
			se.printStackTrace() ;
		}
		List<ProductLogEntity> logList = productLogServiceI.loadAll(ProductLogEntity.class);
		for(ProductLogEntity log:logList){
			try {
				analysisData(log.getTableName());
				/*rs = st.executeQuery("select * from " + log.getTableName());
				while(rs.next()){
					
					List<ProductAnalysisEntity> productAnalysisEntities = productAnalysisServiceI.findHql("from ProductAnalysisEntity where P_ID=? and C_ID=?", rs.getString("product_id"),rs.getString("category_id"));
					ProductAnalysisEntity t = null;
					if(productAnalysisEntities.size()>0){
						t = productAnalysisEntities.get(0);
					}
					date = sdf.format(rs.getDate("date"));
					if(t == null){
						ProductAnalysisEntity productAnalysisEntity = new ProductAnalysisEntity();
						productAnalysisEntity.setPId(rs.getString("product_id"));
						productAnalysisEntity.setPName(rs.getString("product_name"));
						productAnalysisEntity.setCId(rs.getString("category_id"));
						content = "["+rs.getString("product_price")+","+rs.getString("product_orders_all")+","+rs.getString("product_orders_today")+","+rs.getString("category_orders")+","+date+"]";
						productAnalysisEntity.setContent(content);
						productAnalysisServiceI.save(productAnalysisEntity);
					}else{
						content = t.getContent()+";["+rs.getString("product_price")+","+rs.getString("product_orders_all")+","+rs.getString("product_orders_today")+","+rs.getString("category_orders")+","+date+"]";
						t.setContent(content);
						productAnalysisServiceI.updateEntitie(t);
					}
				}
        	*/
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		message = "初始化成功";
		j.setMsg(message);
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-start));
		return j;
	}


	public void doAnalysis(){
		List<ProductEntity> productEntities = productService.loadAll(ProductEntity.class);
		String content = "";
		String date = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for(ProductEntity product:productEntities){
			ProductAnalysisEntity t = (ProductAnalysisEntity) productAnalysisServiceI.findHql("from ProductAnalysisEntity where P_ID=? and C_ID=?", product.getProductId(),product.getCategoryId()).get(0);
			date = sdf.format(product.getDate());
			if(t == null){
				ProductAnalysisEntity productAnalysisEntity = new ProductAnalysisEntity();
				productAnalysisEntity.setPId(product.getProductId());
				productAnalysisEntity.setPName(product.getProductName());
				productAnalysisEntity.setCId(product.getCategoryId());
				//content = "["+product.getProductPrice()+","+product.getProductOrdersAll()+","+product.getProductOrdersToday()+","+product.getCategoryOrders()+","+date+"]";
				productAnalysisEntity.setContent(content);
				productAnalysisServiceI.save(productAnalysisEntity);
			}else{
				//content = t.getContent()+";["+product.getProductPrice()+","+product.getProductOrdersAll()+","+product.getProductOrdersToday()+","+product.getCategoryOrders()+","+date+"]";
				t.setContent(content);
				productAnalysisServiceI.updateEntitie(t);
			}
		}
	}


	@RequestMapping(params = "createTemp")
	@ResponseBody
	public AjaxJson createTemp(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();

		List<ProductEntity> productEntities = productService.loadAll(ProductEntity.class);
		String content = "";
		String date = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for(ProductEntity product:productEntities){
			ProductAnalysisEntity t = (ProductAnalysisEntity) productAnalysisServiceI.findHql("from ProductAnalysisEntity where P_ID=? and C_ID=?", product.getProductId(),product.getCategoryId()).get(0);
			date = sdf.format(product.getDate());
			if(t == null){
				ProductAnalysisEntity productAnalysisEntity = new ProductAnalysisEntity();
				productAnalysisEntity.setPId(product.getProductId());
				productAnalysisEntity.setPName(product.getProductName());
				productAnalysisEntity.setCId(product.getCategoryId());
				//content = "["+product.getProductPrice()+","+product.getProductOrdersAll()+","+product.getProductOrdersToday()+","+product.getCategoryOrders()+","+date+"]";
				productAnalysisEntity.setContent(content);
				productAnalysisServiceI.save(productAnalysisEntity);
			}else{
				//content = t.getContent()+";["+product.getProductPrice()+","+product.getProductOrdersAll()+","+product.getProductOrdersToday()+","+product.getCategoryOrders()+","+date+"]";
				t.setContent(content);
				productAnalysisServiceI.save(t);
			}

		}


		//建表  product_YYYY_MM_DD
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
		try {
			System.out.println("开始创建临时表");
			List<ProductLogEntity> logList = productLogServiceI.loadAll(ProductLogEntity.class);
			String sql = "create table temp select p.product_id,p.category_id,p.product_name,p.product_price,p.product_orders_all,p.product_orders_today,p.category_orders,p.date,";
			
			for(ProductLogEntity log:logList){
				sql += log.getTableName()+".product_price as "+log.getTableName()+"_product_price," +
					   log.getTableName()+".product_orders_all as "+log.getTableName()+"_product_orders_all," +
					   log.getTableName()+".product_orders_today as "+log.getTableName()+"_product_orders_today," +
					   log.getTableName()+".category_orders as "+log.getTableName()+"_category_orders," +
					   log.getTableName()+".date as "+log.getTableName()+"_date";
			}
			sql += " from product as p";
			for(ProductLogEntity log:logList){
				sql += " left join "+log.getTableName()+" on p.product_id = "+log.getTableName()+".product_id" +
						" and p.category_id = "+log.getTableName()+".category_id";
			}
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_local);
			connectionPool_local = new ConnectionPool(param_local);
			try {
				connectionPool_local.createPool();
			} catch (Exception e) {
				logger.error(e);
			}
			conn_local = connectionPool_local.getConnection();
			Statement prepareStmt = conn_local.createStatement();
			prepareStmt.execute("DROP TABLE IF EXISTS temp;");
			prepareStmt.execute(sql);
			conn_local.close();
			connectionPool_local.closeConnectionPool();
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}*/
		message = "创建临时表成功";
		j.setMsg(message);
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-start));
		return j;
	}


	@RequestMapping(params = "syncCategory")
	@ResponseBody
	public AjaxJson syncCategory(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		try {
			System.out.println("开始同步类目");
			syncCategoryData(1, "caiji_category", "caiji_category");
		} catch (Exception e) {
			e.printStackTrace();
		}
		message = "类目同步成功";
		j.setMsg(message);
		return j;
	}

	@Autowired
	private CaijiCategoryServiceI caijiCategoryService;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SyncController.class);

	private Logger log = Logger.getLogger(getClass());

	private AtomicLong currentSynCount = new AtomicLong(0L); // 当前已同步的条数

	private int syncThreadNum;  // 同步的线程数
	static Connection conn = null;
	static ConnectionPool connectionPool = null;

	static Connection conn_local = null;
	static ConnectionPool connectionPool_local = null;

	public void syncData(int businessType,String targetTBName,String tmpTBName) throws Exception {
		this.setSyncThreadNum(5);
		log.info("开始同步核心库 "+tmpTBName+" 表数据");
		// 服务器数据库
		ConnectionParam param = new ConnectionParam();
		param.setDriver(Constant.driver);
		param.setPassword(Constant.password);
		param.setUser(Constant.user);
		param.setUrl(Constant.url_service);
		connectionPool = new ConnectionPool(param);

		// 本地数据库
		ConnectionParam param_local = new ConnectionParam();
		param_local.setDriver(Constant.driver);
		param_local.setPassword(Constant.password);
		param_local.setUser(Constant.user);
		param_local.setUrl(Constant.url_local);
		connectionPool_local = new ConnectionPool(param_local);
		try {
			connectionPool.createPool();
			connectionPool_local.createPool();
		} catch (Exception e) {
			logger.error(e);
		}
		conn_local = connectionPool_local.getConnection();
		Statement prepareStmt = conn_local.createStatement();
		prepareStmt.execute("CREATE TABLE IF NOT EXISTS " + targetTBName + " LIKE product_model ");
		prepareStmt.execute(" TRUNCATE TABLE " + targetTBName);


		conn = connectionPool.getConnection();
		conn.setAutoCommit(false);
		Statement coreStmt = conn.createStatement();
		// 为每个线程分配结果集
		ResultSet coreRs = coreStmt.executeQuery("SELECT count(*) FROM " + tmpTBName);
		coreRs.next();
		// 总共处理的数量
		long totalNum = coreRs.getLong(1);
		// 每个线程处理的数量
		long ownerRecordNum =(long) Math.ceil((totalNum / syncThreadNum));
		log.info("共需要同步的数据量："+totalNum);
		log.info("同步线程数量："+syncThreadNum);
		log.info("每个线程可处理的数量："+ownerRecordNum);

		prepareStmt.execute("Insert into product_log(table_name,count) values('"+targetTBName+"',"+totalNum+") ON DUPLICATE KEY UPDATE count=VALUES(count)");
		productIds = new ArrayList<String>();
		// 开启五个线程向目标库同步数据
		for(int i=0; i < syncThreadNum; i ++){
			StringBuilder sqlBuilder = new StringBuilder();
			if(i == 0){
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum)
						.append( " And ")
						.append((i * ownerRecordNum + ownerRecordNum));
			}else if(i == syncThreadNum-1){
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum + 1)
						.append( " And ")
						.append(totalNum);
			}else{
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum + 1)
						.append( " And ")
						.append((i * ownerRecordNum + ownerRecordNum));
			}

			Thread workThread = new Thread(
					new WorkerHandler(sqlBuilder.toString(), businessType,tmpTBName,targetTBName));
			workThread.setName("SyncThread-"+i);
			workThread.start();
		}
		while (currentSynCount.get() < totalNum);
		// 休眠一会儿让数据库有机会commit剩余的批处理(只针对JUnit单元测试,因为单元测试完成后会关闭虚拟器，使线程里的代码没有机会作提交操作);
		Thread.sleep(1000 * 10);

		conn.commit();
		conn_local.commit();
		conn.close();
		logger.info("核心库"+tmpTBName+"表数据同步完成，共同步了" + currentSynCount.get() + "条数据");
		currentSynCount.set(0);
		conn_local.close();
		connectionPool_local.closeConnectionPool();
		connectionPool_local.createPool();
		connectionPool_local = null;
	}


	public void syncCategoryData(int businessType,String targetTBName,String tmpTBName) throws Exception {
		this.setSyncThreadNum(5);
		log.info("开始同步核心库 "+tmpTBName+" 表数据");
		// 服务器数据库
		ConnectionParam param = new ConnectionParam();
		param.setDriver(Constant.driver);
		param.setPassword(Constant.password);
		param.setUser(Constant.user);
		param.setUrl(Constant.url_service);
		connectionPool = new ConnectionPool(param);

		// 本地数据库
		ConnectionParam param_local = new ConnectionParam();
		param_local.setDriver(Constant.driver);
		param_local.setPassword(Constant.password);
		param_local.setUser(Constant.user);
		param_local.setUrl(Constant.url_local);
		connectionPool_local = new ConnectionPool(param_local);
		try {
			connectionPool.createPool();
			connectionPool_local.createPool();
		} catch (Exception e) {
			logger.error(e);
		}
		conn_local = connectionPool_local.getConnection();
		Statement prepareStmt = conn_local.createStatement();
		prepareStmt.execute(" TRUNCATE TABLE " + targetTBName);

		conn = connectionPool.getConnection();
		conn.setAutoCommit(false);
		Statement coreStmt = conn.createStatement();
		// 为每个线程分配结果集
		ResultSet coreRs = coreStmt.executeQuery("SELECT count(*) FROM " + tmpTBName);
		coreRs.next();
		// 总共处理的数量
		long totalNum = coreRs.getLong(1);
		// 每个线程处理的数量
		long ownerRecordNum =(long) Math.ceil((totalNum / syncThreadNum));
		log.info("共需要同步的数据量："+totalNum);
		log.info("同步线程数量："+syncThreadNum);
		log.info("每个线程可处理的数量："+ownerRecordNum);

		// 开启五个线程向目标库同步数据
		for(int i=0; i < syncThreadNum; i ++){
			StringBuilder sqlBuilder = new StringBuilder();
			if(i == 0){
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum)
						.append( " And ")
						.append((i * ownerRecordNum + ownerRecordNum));
			}else if(i == syncThreadNum-1){
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum + 1)
						.append( " And ")
						.append(totalNum);
			}else{
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum + 1)
						.append( " And ")
						.append((i * ownerRecordNum + ownerRecordNum));
			}

			Thread workThread = new Thread(
					new WorkerHandler(sqlBuilder.toString(), businessType,tmpTBName,targetTBName));
			workThread.setName("SyncThread-"+i);
			workThread.start();
		}
		while (currentSynCount.get() < totalNum);
		// 休眠一会儿让数据库有机会commit剩余的批处理(只针对JUnit单元测试,因为单元测试完成后会关闭虚拟器，使线程里的代码没有机会作提交操作);
		Thread.sleep(1000 * 10);

		conn.close();
		logger.info("核心库"+tmpTBName+"表数据同步完成，共同步了" + currentSynCount.get() + "条数据");
		currentSynCount.set(0);
		conn_local.close();
		connectionPool_local.closeConnectionPool();
		connectionPool_local.createPool();
		connectionPool_local = null;
	}


	public void analysisData(String tmpTBName) throws Exception {
		this.setSyncThreadNum(5);
		log.info("开始统计 "+tmpTBName+"  表数据");
		// 本地数据库
		ConnectionParam param_local = new ConnectionParam();
		param_local.setDriver(Constant.driver);
		param_local.setPassword(Constant.password);
		param_local.setUser(Constant.user);
		param_local.setUrl(Constant.url_local);
		connectionPool_local = new ConnectionPool(param_local);
		try {
			connectionPool_local.createPool();
		} catch (Exception e) {
			logger.error(e);
		}
		conn_local = connectionPool_local.getConnection();
		Statement prepareStmt = conn_local.createStatement();


		// 为每个线程分配结果集
		ResultSet coreRs = prepareStmt.executeQuery("SELECT count(*) FROM " + tmpTBName);
		coreRs.next();
		// 总共处理的数量
		long totalNum = coreRs.getLong(1);
		// 每个线程处理的数量
		long ownerRecordNum =(long) Math.ceil((totalNum / syncThreadNum));
		log.info("共需要同步的数据量："+totalNum);
		log.info("同步线程数量："+syncThreadNum);
		log.info("每个线程可处理的数量："+ownerRecordNum);

		for(int i=0; i < syncThreadNum; i ++){
			StringBuilder sqlBuilder = new StringBuilder();
			if(i == 0){
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum)
						.append( " And ")
						.append((i * ownerRecordNum + ownerRecordNum));
			}else if(i == syncThreadNum-1){
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum + 1)
						.append( " And ")
						.append(totalNum);
			}else{
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" Where id between " ).append(i * ownerRecordNum + 1)
						.append( " And ")
						.append((i * ownerRecordNum + ownerRecordNum));
			}

			Thread workThread = new Thread(
					new AnalysisHandler(sqlBuilder.toString(), 0,tmpTBName,""));
			workThread.setName("SyncThread-"+i);
			workThread.start();
		}
		while (currentSynCount.get() < totalNum);
		// 休眠一会儿让数据库有机会commit剩余的批处理(只针对JUnit单元测试,因为单元测试完成后会关闭虚拟器，使线程里的代码没有机会作提交操作);
		Thread.sleep(1000 * 10);

		logger.info("核心库"+tmpTBName+"表数据统计完成，共处理了" + currentSynCount.get() + "条数据");
		currentSynCount.set(0);
		conn_local.close();
		connectionPool_local.closeConnectionPool();
		connectionPool_local = null;
	}


	//
	public void analysisWeekOrdersData(String tmpTBName) throws Exception {
		this.setSyncThreadNum(30);
		log.info("开始统计 "+tmpTBName+"  表数据");
		// 本地数据库
		ConnectionParam param_local = new ConnectionParam();
		param_local.setDriver(Constant.driver);
		param_local.setPassword(Constant.password);
		param_local.setUser(Constant.user);
		param_local.setUrl(Constant.url_local);
		connectionPool_local = new ConnectionPool(param_local);
		try {
			connectionPool_local.createPool();
		} catch (Exception e) {
			logger.error(e);
		}
		conn_local = connectionPool_local.getConnection();
		Statement prepareStmt = conn_local.createStatement();


		// 为每个线程分配结果集
		ResultSet coreRs = prepareStmt.executeQuery("SELECT count(*) FROM " + tmpTBName);
		coreRs.next();
		// 总共处理的数量
		long totalNum = coreRs.getLong(1);
		total = totalNum;
//        // 每个线程处理的数量
		long ownerRecordNum =(long) Math.ceil((totalNum / syncThreadNum));
		log.info("共需要同步的数据量："+totalNum);
		log.info("同步线程数量："+syncThreadNum);
		log.info("每个线程可处理的数量："+ownerRecordNum);

		for(int i=0; i < syncThreadNum; i ++){
			StringBuilder sqlBuilder = new StringBuilder();
			if(i == 0){
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" limit " ).append(i * ownerRecordNum)
						.append( " , ")
						.append((ownerRecordNum));
			}else if(i == syncThreadNum-1){
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" limit " ).append(i * ownerRecordNum + 1)
						.append( " , ")
						.append(ownerRecordNum);
			}else{
				sqlBuilder.append("Select * From ").append(tmpTBName)
						.append(" limit " ).append(i * ownerRecordNum + 1)
						.append( " , ")
						.append((ownerRecordNum));
			}

			Thread workThread = new Thread(
					new WeekOrdersHandler(sqlBuilder.toString(), 0,tmpTBName,""));
			workThread.setName("SyncThread-"+i);
			workThread.start();
		}
		while (currentSynCount.get() < totalNum || !isCanCloseSql);
		// 休眠一会儿让数据库有机会commit剩余的批处理(只针对JUnit单元测试,因为单元测试完成后会关闭虚拟器，使线程里的代码没有机会作提交操作);
		Thread.sleep(1000 * 10);

		logger.info("核心库"+tmpTBName+"表数据统计完成，共处理了" + currentSynCount.get() + "条数据");
		currentSynCount.set(0);
		conn_local.close();
		connectionPool_local.closeConnectionPool();
		connectionPool_local = null;
	}
      
   /* private void createCategoryTable() throws SQLException {
		// TODO Auto-generated method stub
		String tableName = "";
		Statement createSM = conn_local.createStatement();
		Statement level1SM = conn_local.createStatement();
		int i = 0;
		String level1_sql = "SELECT b.c_name,a.c_name FROM `caiji_category` as a,(SELECT id,c_name FROM `caiji_category` where pid = 0) as b where a.pid = b.id;";
		ResultSet level1 = level1SM.executeQuery(level1_sql); 
		
		while(level1.next()){
			tableName = Tools.getTableName(level1.getString(1) + "_" + level1.getString(2));
			if(tableName != ""){
				createSM.execute("CREATE TABLE IF NOT EXISTS " + tableName + " LIKE product_model ");
				i++;
			}
		}
		System.out.println("count:"+i);
	}*/


	public void setSyncThreadNum(int syncThreadNum) {
		this.syncThreadNum = syncThreadNum;
	}

	// 数据同步线程
	final class WorkerHandler implements Runnable {
		ResultSet coreRs;
		String queryStr;
		int businessType;
		String tmpTBName;
		String targetTBName;

		public WorkerHandler(String queryStr,int businessType,String tmpTBName,String targetTBName) {
			this.queryStr = queryStr;
			this.businessType = businessType;
			this.tmpTBName = tmpTBName;
			this.targetTBName = targetTBName;
		}
		@Override
		public void run() {
			try {
				// 开始同步
				switch(businessType){
					case 0:
						launchSyncData();
						break;
					case 1:
						launchSyncCategoryData();
						break;
					default:
						break;
				}


			} catch(Exception e){
				log.error(e);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		// 同步产品数据方法
		void launchSyncData() throws Exception{
			// 获得核心库连接
			Statement coreStmt = conn.createStatement();
			// 获得目标库连接
			conn_local.setAutoCommit(false);// 设置手动提交
			PreparedStatement targetPstmt = conn_local.prepareStatement("INSERT INTO "+ targetTBName +
					" (product_id,product_name,product_url,product_img,product_price,product_dsr,max_price," +
					"product_orders_all,product_orders_today,store_id,store_name," +
					"category_id,date,is_series) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement targetPstmt_main = conn_local.prepareStatement("INSERT INTO product " +
					"(product_id,product_name,product_url,product_img,product_price,product_dsr,max_price," +
					"product_orders_all,product_orders_today,store_id,store_name," +
					"category_id,date,is_series,last_date,product_orders_yesterday,is_repeat) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
					+ "ON DUPLICATE KEY UPDATE product_name=VALUES(product_name), product_img=VALUES(product_img),product_price=VALUES(product_price),product_dsr=VALUES(product_dsr)" +
					",max_price=VALUES(max_price),product_orders_all=VALUES(product_orders_all),product_orders_yesterday=VALUES(product_orders_yesterday),product_orders_today=VALUES(product_orders_today) "+
					",category_id=VALUES(category_id),last_date=VALUES(last_date),is_repeat=VALUES(is_repeat)");

			PreparedStatement targetPstmt_update = conn_local.prepareStatement("UPDATE product SET " +
					" product_name=?, product_img=?, product_price=?, product_dsr=?" +
					", max_price=?, product_orders_all=?, product_orders_yesterday=?, product_orders_today=? "+
					",is_repeat=0 where product_id = ?;");
			//更新销量表
//            PreparedStatement targetPstmt_orders = conn_local.prepareStatement("INSERT INTO product_orders " +
//            		"(product_id,product_orders_all,product_orders_today,date) VALUES (?,?,?,?) "
//    				+ "ON DUPLICATE KEY UPDATE product_orders_all=VALUES(product_orders_all), product_orders_today=VALUES(product_orders_today),date=VALUES(date)"); 

			ResultSet coreRs = coreStmt.executeQuery(queryStr);
			log.info(Thread.currentThread().getName() + "'s Query SQL::" + queryStr);
			System.out.println(queryStr);
			int batchCounter = 0; // 累加的批处理数量
			while (coreRs.next()) {
				currentSynCount.incrementAndGet();// 递增
				if(coreRs.getInt("product_orders") == 0){
					continue;
				}
				//装配pm 数据库插入组装
				settingPM(targetPstmt,coreRs,0);
				settingPMForUpdate(targetPstmt_update, coreRs, 1);
				settingPM(targetPstmt_main,coreRs,1);


				batchCounter++;
				if (batchCounter % 1000 == 0) { // 1千条数据一提交
					targetPstmt.executeBatch();
					targetPstmt.clearBatch();

					targetPstmt_update.executeBatch();
					targetPstmt_update.clearBatch();

					targetPstmt_main.executeBatch();
					targetPstmt_main.clearBatch();


					conn_local.commit();
				}
			}
			// 提交剩余的批处理
			targetPstmt.executeBatch();
			targetPstmt.clearBatch();
			targetPstmt_update.executeBatch();
			targetPstmt_update.clearBatch();
			targetPstmt_main.executeBatch();
			targetPstmt_main.clearBatch();


			conn_local.commit();
			coreStmt.close();
		}


		private void settingPM(PreparedStatement targetPstmt,
							   ResultSet coreRs, int type) throws SQLException, ParseException {
			List<ProductEntity> productList = Cache.getProductList().get(coreRs.getLong("product_id"));
			int yesterdayCount = 0;
			String yesterdaySell = "0";//昨日销量
			String last_date = null;
			int productOrdersToday = 0;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if(type == 1){
				targetPstmt.setInt(17, 1);
			}
			if(productList!=null && productList.size()>0){
				for(ProductEntity product:productList){
					if(Integer.parseInt(product.getProductOrdersAll()) > yesterdayCount){
						last_date = product.getLastDate().toString();
						//如果今天更新过，则直接用这个数据
						if(Tools.isToday(sdf.parse(last_date), today)){
							productOrdersToday = Integer.parseInt(product.getProductOrdersToday());
							yesterdaySell = product.getProductOrdersYesterday();
							//PRODUCT+CATEGORY再次有发生PRODUCT已经存在时，更新这个字段为0
//		    			 if(type == 1){//product表
//		    	        	 targetPstmt.setInt(22, 0);//is_repeat  重复
//		    	         }
							break;
						}else{
							yesterdayCount = Integer.parseInt(product.getProductOrdersAll());
							productOrdersToday = coreRs.getInt("product_orders") - yesterdayCount;
							yesterdaySell = product.getProductOrdersToday();

							switch(Tools.passTime(sdf.parse(last_date), today)){
								case 0:
									break;
								case 1:
									productOrdersToday = productOrdersToday/2;
									break;
								case 2:
									productOrdersToday = productOrdersToday/3;
									break;
								case 3:
									productOrdersToday = 0;
									break;
							}

						}
					}
				}
			}


			//如果是今日刚采集的，则跳过
//	    	 if(last_date != null){
//	    		 if(Tools.isToday(sdf.parse(last_date), new Date())){
//		    		 return;
//		    	 } 
//	    	 }
			if(type == 1 && productIds != null){
				if(productIds.contains(coreRs.getString("product_id"))){
					//targetPstmt.setInt(22, 0);
				}else{
					productIds.add(coreRs.getString("product_id"));
				}
			}

			// 总共处理的数量
			targetPstmt.setString(1, coreRs.getString("product_id"));  // product_id
			targetPstmt.setString(2, coreRs.getString("product_name"));  //product_name
			targetPstmt.setString(3, coreRs.getString("product_url"));  //product_url
			targetPstmt.setString(4, coreRs.getString("product_img"));	//product_img
			targetPstmt.setFloat(5, coreRs.getFloat("product_price"));	//product_price
			targetPstmt.setString(6, coreRs.getString("product_dsr"));	//product_dsr

			targetPstmt.setFloat(7, coreRs.getFloat("max_price"));
			targetPstmt.setInt(8, coreRs.getInt("product_orders"));//product_orders_all
			if(productOrdersToday<0){
				productOrdersToday = 0;
			}
			targetPstmt.setInt(9, productOrdersToday);

			targetPstmt.setString(10, coreRs.getString("store_id"));	//store_id
			targetPstmt.setString(11, coreRs.getString("store_name"));	//store_name
			targetPstmt.setString(12, coreRs.getString("category_id"));	//category_id
			targetPstmt.setString(13, coreRs.getString("date"));//date
			if(last_date == null){
				targetPstmt.setInt(14, 1);//is_series
			}else if(last_date != null && Tools.isYeaterday(sdf.parse(last_date), today) != 1){
				targetPstmt.setInt(14, 1);//is_series
			}else{
				targetPstmt.setInt(14, 0);//is_series
			}
			if(type == 1){//product表
				targetPstmt.setString(15, coreRs.getString("date"));//last_date
				targetPstmt.setString(16, yesterdaySell);//yesterdaySell  昨日销量
			}
			targetPstmt.addBatch();
		}


		private void settingPMForUpdate(PreparedStatement targetPstmt, ResultSet coreRs, int type)
				throws SQLException, ParseException
		{
			List<ProductEntity> productList  = Cache.getProductList().get(coreRs.getLong("product_id"));
			int yesterdayCount = 0;
			String yesterdaySell = "0";
			String last_date = null;
			int productOrdersToday = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(productList!=null && productList.size()>0){
				System.out.println("");
				for(ProductEntity product:productList){
					if (Integer.parseInt(product.getProductOrdersAll()) > yesterdayCount) {
						last_date = product.getLastDate().toString();

						if (Tools.isToday(sdf.parse(last_date), today)) {
							productOrdersToday = Integer.parseInt(product.getProductOrdersToday());
							yesterdaySell = product.getProductOrdersYesterday();
							break;
						}
						yesterdayCount = Integer.parseInt(product.getProductOrdersAll());
						productOrdersToday = coreRs.getInt("product_orders") - yesterdayCount;
						yesterdaySell = product.getProductOrdersYesterday();

						switch(Tools.passTime(sdf.parse(last_date), today)){
							case 0:
								break;
							case 1:
								productOrdersToday = productOrdersToday/2;
								break;
							case 2:
								productOrdersToday = productOrdersToday/3;
								break;
							case 3:
								productOrdersToday = 0;
								break;
						}

					}
				}
			}

			//targetPstmt.setString(12, coreRs.getString(2));
			targetPstmt.setString(1, coreRs.getString("product_name"));
			targetPstmt.setString(2, coreRs.getString("product_img"));
			targetPstmt.setFloat(3, coreRs.getFloat("product_price"));
			targetPstmt.setString(4, coreRs.getString("product_dsr"));
			targetPstmt.setFloat(5, coreRs.getFloat("max_price"));
			targetPstmt.setInt(6, coreRs.getInt("product_orders"));
			targetPstmt.setString(7, yesterdaySell);
			if(productOrdersToday<0){
				productOrdersToday = 0;
			}
			targetPstmt.setInt(8, productOrdersToday);
			targetPstmt.setString(9, coreRs.getString("product_id"));
			targetPstmt.addBatch();
		}


		// 同步类目数据方法
		void launchSyncCategoryData() throws Exception{
			// 获得核心库连接
			Statement coreStmt = conn.createStatement();
			// 获得目标库连接
			conn_local.setAutoCommit(false);// 设置手动提交
			PreparedStatement caijiPstmt = conn_local.prepareStatement("INSERT INTO " + targetTBName + "(id, c_id, c_name, pid, is_catch, url, catch_page) VALUES (?,?,?,?,?,?,?)");
			ResultSet coreRs = coreStmt.executeQuery(queryStr);
			log.info(Thread.currentThread().getName()+"'s Query SQL::"+queryStr);
			while (coreRs.next()) {
				caijiPstmt.setInt(1, coreRs.getInt(1)); //id
				caijiPstmt.setInt(2, coreRs.getInt(2));  // c_id
				caijiPstmt.setString(3, coreRs.getString(3));  //c_name
				caijiPstmt.setInt(4, coreRs.getInt(4));  //pid
				caijiPstmt.setInt(5, coreRs.getInt(5));	//is_catch
				caijiPstmt.setString(6, coreRs.getString(6));	//url
				caijiPstmt.setInt(7, coreRs.getInt(7));	//catch_page

				caijiPstmt.addBatch();
				currentSynCount.incrementAndGet();// 递增
			}
			// 提交剩余的批处理
			caijiPstmt.executeBatch();
			caijiPstmt.clearBatch();
			conn_local.commit();
		}
	}


	// 数据同步线程
	final class AnalysisHandler implements Runnable {
		ResultSet coreRs;
		String queryStr;
		int businessType;
		String tmpTBName;
		String targetTBName;
		public AnalysisHandler(String queryStr,int businessType,String tmpTBName,String targetTBName) {
			this.queryStr = queryStr;
			this.businessType = businessType;
			this.tmpTBName = tmpTBName;
			this.targetTBName = targetTBName;
		}
		@Override
		public void run() {
			try {
				// 开始分析
				analysing();
			} catch(Exception e){
				log.error(e);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		// 同步产品数据方法
		void analysing() throws Exception{
			// 获得核心库连接
			Statement coreStmt = conn_local.createStatement();
			// 获得目标库连接
			conn_local.setAutoCommit(false);// 设置手动提交
			PreparedStatement targetPstmt = conn_local.prepareStatement("INSERT INTO product_analysis " +
					"(p_id,c_id,p_name,content) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE content=CONCAT(content,';',VALUES(content))");
			ResultSet coreRs = coreStmt.executeQuery(queryStr);
			log.info(Thread.currentThread().getName()+"'s Query SQL::"+queryStr);
			int batchCounter = 0; // 累加的批处理数量
			String content = "";
			String date = "";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			while (coreRs.next()) {
				currentSynCount.incrementAndGet();// 递增
				date = sdf.format(coreRs.getDate("date"));
				content = "["+coreRs.getString("product_price").replace(",", "")+","+coreRs.getString("product_orders_all")+","+coreRs.getString("product_orders_today")+","+coreRs.getString("category_orders")+","+date+"]";

				//装配pm 数据库插入组装
				targetPstmt.setString(1, coreRs.getString("product_id"));  // product_id
				targetPstmt.setString(2, coreRs.getString("category_id"));  //category_id
				targetPstmt.setString(3, coreRs.getString("product_name"));  //product_name
				targetPstmt.setString(4, content);	//content
				targetPstmt.addBatch();
				batchCounter++;
				if (batchCounter % 10000 == 0) { // 1万条数据一提交
					targetPstmt.executeBatch();
					targetPstmt.clearBatch();
					conn_local.commit();
				}
			}
			// 提交剩余的批处理
			targetPstmt.executeBatch();
			targetPstmt.clearBatch();
			coreStmt.close();
			conn_local.commit();
		}
	}


	// 周销量统计线程
	final class WeekOrdersHandler implements Runnable {
		ResultSet coreRs;
		String queryStr;
		int businessType;
		String tmpTBName;
		String targetTBName;
		public WeekOrdersHandler(String queryStr,int businessType,String tmpTBName,String targetTBName) {
			this.queryStr = queryStr;
			this.businessType = businessType;
			this.tmpTBName = tmpTBName;
			this.targetTBName = targetTBName;
		}
		@Override
		public void run() {
			try {
				// 开始分析
				weekOrders();
			} catch(Exception e){
				log.error(e);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		// 产品周销量统计数据方法
		void weekOrders() throws Exception{
			isCanCloseSql = false;
			// 获得核心库连接
			Statement coreStmt = conn_local.createStatement();
			// 获得目标库连接
			conn_local.setAutoCommit(false);// 设置手动提交
			PreparedStatement targetPstmt = conn_local.prepareStatement("UPDATE product SET week_orders = ?,amount = product_price*product_orders_today,week_amount = week_orders*product_price where product_id = ?");
			ResultSet coreRs = coreStmt.executeQuery(queryStr);
			log.info(Thread.currentThread().getName()+"'s Query SQL::"+queryStr);
			int batchCounter = 0; // 累加的批处理数量
			String content = "";
			int weekOrders = 0;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			long lastWeek=System.currentTimeMillis()-8*24*60*60*1000;
			List<String> list = new ArrayList<String>();
			while (coreRs.next()) {
				list = new ArrayList<String>();
				weekOrders = 0;
				currentSynCount.incrementAndGet();// 递增
				content = coreRs.getString("content");
				String[] cs = content.split(";");
				String[] p = null;
				int j = 0;
				int currentOrders = 0;
				int weekFirstOrders = 0;
				boolean first = true;
				for(int i = cs.length-1; i > 0; i--){
					p = cs[i].replace("[", "").replace("]", "").split(",");
					try {
						if(!list.contains(p[p.length-1])){
							list.add(p[p.length-1]);
							if(sdf.parse(p[p.length-1]).getTime() > lastWeek){
								if(first){
									currentOrders = Integer.parseInt(p[p.length-4]);
									first = false;
								}else{
									weekFirstOrders = Integer.parseInt(p[p.length-4]);
								}
								j++;
							}else{
//								System.out.println("productId:"+coreRs.getString("p_id")+";content:"+cs[i]);
								break;
							}
						}

					} catch (Exception e) {
						System.out.println("productId:"+coreRs.getString("p_id")+";content:"+cs[i]);
						logger.error(e+"productId:"+coreRs.getString("p_id")+";content:"+cs[i]);
						e.printStackTrace();
					}
				}
				//D.	如果1.9号有采集到，一直到1.17号再次采集，中间时间为空的。则week_orders值为0，相当于第一次采集
				if(j == 1){
					weekOrders = 0;
//					System.out.println("1100行；productId:"+coreRs.getString("p_id"));
				}else{
					weekOrders = currentOrders - weekFirstOrders;
				}
				//装配pm 数据库插入组装
//				System.out.println("*********************"+coreRs.getString("p_id")+":"+weekOrders);
				targetPstmt.setInt(1, weekOrders);  //weekOrders
				targetPstmt.setString(2, coreRs.getString("p_id"));  // product_id

				targetPstmt.addBatch();
				batchCounter++;
				if (batchCounter % 10000 == 0) { // 1万条数据一提交
					targetPstmt.executeBatch();
					targetPstmt.clearBatch();
					conn_local.commit();
				}
			}
			// 提交剩余的批处理
			targetPstmt.executeBatch();
			targetPstmt.clearBatch();
			coreStmt.close();
			conn_local.commit();
			if(currentSynCount.get() == total){
				System.out.println("总数："+currentSynCount.get());
				isCanCloseSql = true;
			}
		}
	}

}
