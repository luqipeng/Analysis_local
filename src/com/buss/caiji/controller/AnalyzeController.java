package com.buss.caiji.controller;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buss.caiji.controller.SyncController.WorkerHandler;
import com.buss.caiji.entity.CaijiCategoryEntity;
import com.buss.caiji.entity.ProductEntity;
import com.buss.caiji.method.ConnectionParam;
import com.buss.caiji.method.ConnectionPool;
import com.buss.caiji.service.CaijiCategoryServiceI;
import com.buss.caiji.service.ProductServiceI;
import com.buss.caiji.util.Constant;
import com.buss.caiji.util.Tools;

@Scope("prototype")
@Controller
@RequestMapping("/analyzeController")
public class AnalyzeController extends BaseController{

	private StringBuilder suffix;
	private StringBuilder suffix_all;  
	String sql,prefix,prefix_all,tableName;
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AnalyzeController.class);
	
	@Autowired
	private CaijiCategoryServiceI caijiCategoryService;
	@Autowired
	private ProductServiceI productService;
	
	private String message;
	private int syncThreadNum;  // 同步的线程数
	private AtomicLong currentSynCount = new AtomicLong(0L); // 当前已同步的条数
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getSyncThreadNum() {
		return syncThreadNum;
	}

	public void setSyncThreadNum(int syncThreadNum) {
		this.syncThreadNum = syncThreadNum;
	}

	static Connection conn_local = null;
    static ConnectionPool connectionPool_local = null;
	
	/*
	 * 分析数据
	 */
	@RequestMapping(params = "analysis")
	public void analysis(HttpServletRequest request){
		try {
			System.out.println("开始分析");
			doAnalysis(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/*
	 * 下载同步数据
	 */
	@RequestMapping(params = "doSql")
	public void doSql(HttpServletRequest request){
		try {
			//执行下载
			System.out.println("脚本同步开始");
			Tools.download();
			//解压
			Tools.unZipFiles(new File("d:/product_base.zip"), "d:/sql");
			//执行脚本
			Tools.execute("d:/sql/product_base.sql");

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
	@RequestMapping(params = "download")
	public void download(HttpServletRequest request){
		try {
			//执行下载
			Tools.download();
			//解压
			Tools.unZipFiles(new File("d:/product_base.zip"), "d:/sql");
			//执行脚本
			Tools.execute("d:/sql/product_base.sql");
			
			System.out.println("脚本同步完毕");
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 分析数据
	 */
	@RequestMapping(params = "analysisProduct")
	@ResponseBody
	public AjaxJson analysisProduct(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long start = System.currentTimeMillis();
		try {
			System.out.println("开始分析");
			doAnalysis(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		message = "产品分析完成,共耗时"+ (end-start);
		j.setMsg(message);
		return j;
	}

	
	private void doAnalysis(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String tmpTBName = "product_base";
		setSyncThreadNum(2);
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
		Statement anayStmt = conn_local.createStatement();
		Statement proStmt = conn_local.createStatement();
		
		String level1_sql = "SELECT b.c_name,a.c_name,a.c_id FROM caiji_category as a,(SELECT id,c_name FROM caiji_category where pid = 0) as b where a.pid = b.id";
//				"SELECT * FROM caiji_category where pid in (SELECT id FROM caiji_category where pid = 0);";
		ResultSet level1 = anayStmt.executeQuery(level1_sql); 
		String id = "";
		String tableName = "";
		while(level1.next()){
			
			id = level1.getString(3);
			System.out.println(id);
			String ids = getChildrenList(id);
			if(ids.length() > 0){
				tableName = Tools.getTableName(level1.getString(1) + "_" + level1.getString(2));
				String pro_sql = "SELECT * FROM product_base where category_id in (" +ids + ")";
				analysis(tableName, pro_sql);
				/*Thread workThread = new Thread(  
	                     new WorkerHandler(pro_sql, 0, tableName));  
	             workThread.setName("SyncThread-"+id);  
	             workThread.start(); */
			}
		}
		
		ResultSet coreRs = anayStmt.executeQuery("SELECT count(*) FROM " + tmpTBName);  
        coreRs.next();
        // 总共处理的数量
        long totalNum = coreRs.getLong(1); 
        while (currentSynCount.get() < totalNum); 
         
        currentSynCount.set(0);
	    conn_local.close();
         
	}
	
	private String getChildrenList(String id) throws SQLException {
		Statement anayStmt = conn_local.createStatement();
		String level1_all = "select c_id from caiji_category where pid in (select id from caiji_category where pid in (select id from caiji_category where pid in (select id from caiji_category where pid = "+id+")) or pid in (select id from caiji_category where pid = "+id+")) " +
				"or pid in (select id from caiji_category where pid in (select id from caiji_category where pid = "+id+")) " +
				"or pid in (select id from caiji_category where pid = "+id+")"+
				"or c_id in ("+id+") and is_catch = 1";
		
		ResultSet childs = anayStmt.executeQuery(level1_all);
		String childsId = "";
		while(childs.next()){
			childsId += childs.getString(1) + ",";
		}
		if(childsId.length()>1){
			childsId = childsId.substring(0, childsId.length()-1);
		}
		anayStmt.close();
		return childsId;
	}
	
	
	// 同步产品数据方法
    void analysis(String targetTBName,String queryStr) throws Exception{  
    	
    	suffix = new StringBuilder();
		suffix_all = new StringBuilder();  
        // 获得核心库连接
        Statement coreStmt = conn_local.createStatement();
        conn_local.setAutoCommit(false);// 设置手动提交
        PreparedStatement targetPstmt = conn_local.prepareStatement("INSERT INTO product (product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,category_orders,product_orders,store_id,store_name,store_faceback,store_facebackper," +
			"category_id,category_index,category_pageno,discount,date,all_index,is_series,last_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
        PreparedStatement categoryPstmt = conn_local.prepareStatement("INSERT INTO "+targetTBName+" (product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,category_orders,product_orders,store_id,store_name,store_faceback,store_facebackper," +
				"category_id,category_index,category_pageno,date,discount,all_index,is_series) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ResultSet coreRs = coreStmt.executeQuery(queryStr); 
        logger.info(Thread.currentThread().getName()+"'s Query SQL::"+queryStr);  
        int batchCounter = 0; // 累加的批处理数量
       
        //添加 到各类目表
        
		prefix_all = "insert into product(product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,product_orders_all,product_orders,category_orders,store_id,store_name,store_faceback,store_facebackper," +
				"category_id,category_index,category_pageno,date,discount,all_index,is_series,last_date) VALUES ";
		prefix = "insert into "+targetTBName+"(product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,category_orders,product_orders,store_id,store_name,store_faceback,store_facebackper," +
				"category_id,category_index,category_pageno,date,discount,all_index,is_series) VALUES ";
		suffix = new StringBuilder();
		suffix_all = new StringBuilder();
        while (coreRs.next()) {
        	doAddBatch(coreRs.getString(2), coreRs.getString(3), coreRs.getString(4), coreRs.getString(5), coreRs.getString(6),
        			coreRs.getString(7), coreRs.getString(8), coreRs.getString(9), coreRs.getInt(10), coreRs.getString(11), coreRs.getString(12), 
        			coreRs.getString(13), coreRs.getString(14), coreRs.getInt(15), coreRs.getInt(16), coreRs.getInt(17), 
        			coreRs.getString(19), coreRs.getString(18), coreRs.getInt(20));
        	
            batchCounter++;  
            currentSynCount.incrementAndGet();// 递增
            if (batchCounter % 5000 == 0) { // 1万条数据一提交
            	if(suffix.length()>1){
					sql = prefix + suffix.substring(0, suffix.length() - 1);
					categoryPstmt.addBatch(sql);
					if(suffix_all.length()>1){
						sql = prefix_all + suffix_all.substring(0, suffix_all.length() - 1) + "ON DUPLICATE KEY UPDATE product_name=VALUES(product_name), product_img=VALUES(product_img),product_price=VALUES(product_price),product_dsr=VALUES(product_dsr)" +
								",product_faceback=VALUES(product_faceback),product_orders_all=VALUES(product_orders_all),product_orders=VALUES(product_orders),category_orders=VALUES(category_orders) "+
								",discount=VALUES(discount),all_index=VALUES(all_index),last_date=VALUES(date),is_series=0";
						targetPstmt.addBatch(sql);
					}
				}
            	suffix = new StringBuilder();
    			suffix_all = new StringBuilder();
                targetPstmt.executeBatch();  
                targetPstmt.clearBatch();  
                categoryPstmt.executeBatch();
                categoryPstmt.clearBatch();
                conn_local.commit();  
            }  
        }  
        if(suffix.length()>1){
			sql = prefix + suffix.substring(0, suffix.length() - 1);
			categoryPstmt.addBatch(sql);
			if(suffix_all.length()>1){
				sql = prefix_all + suffix_all.substring(0, suffix_all.length() - 1) + "ON DUPLICATE KEY UPDATE product_name=VALUES(product_name), product_img=VALUES(product_img),product_price=VALUES(product_price),product_dsr=VALUES(product_dsr)" +
						",product_faceback=VALUES(product_faceback),product_orders_all=VALUES(product_orders_all),product_orders=VALUES(product_orders),category_orders=VALUES(category_orders) "+
						",discount=VALUES(discount),all_index=VALUES(all_index),last_date=VALUES(date),is_series=0";
				targetPstmt.addBatch(sql);
			}
			// 提交剩余的批处理
            targetPstmt.executeBatch();  
            targetPstmt.clearBatch();  
            categoryPstmt.executeBatch();
            categoryPstmt.clearBatch();
            conn_local.commit();
		}
        coreStmt.close();
    } 
    
    
    void doAddBatch(String p_id2,
			String p_name2, String p_url2, String img2, String p_price2,
			String p_dsr2, String p_faceback2, String p_orders2, int c_orders2,
			String s_id2, String s_name2, String s_faceback2,
			String s_facebackper2, int category_id, int index, int page,
			String format, String discount2, int all_index2) throws ParseException {
		if(p_dsr2 == null) p_dsr2 = "";
		if(p_faceback2 == null) p_faceback2 = "";
		if(discount2 == null) discount2 = "";

		
		int is_series = 0;
		//今日销量
		Integer p_orders_today = 0;
		if(p_orders2 != null && !"".equals(p_orders2) && p_id2 != null && !"".equals(p_id2)){
			//获取前一天的销量，计算出今日销量
			ProductEntity product = getProductById(p_id2);
			if(product != null && product.getProductOrdersAll()!=null && !"".equals(product.getProductOrdersAll())){
				Integer newPF = Integer.parseInt(p_orders2);//今日总销量
				Integer oldPF = Integer.parseInt(product.getProductOrdersAll());//最近一天的总销量
				p_orders_today = newPF - oldPF;
				//判断昨日是否采集到该产品
				if(product.getLastDate() != null){
					if(Tools.isYeaterday(product.getLastDate(), new Date()) != 0){
    					is_series = 1;
    				}
				}else if(product.getDate() != null){
					if(Tools.isYeaterday(product.getDate(), new Date()) != 0){
    					is_series = 1;
    				}
				}
			}
		}
		suffix_all.append("(" + p_id2 + ", '"+p_name2+"', '" +p_url2+ "', '" +img2+ "', '" +p_price2+ "', '" +p_dsr2+ "', '" +p_faceback2+ "'" +
				", " +p_orders2+ ", " +p_orders_today+", " +c_orders2+ ", " +s_id2+ ", '" +s_name2+ "', '" +s_faceback2+ "', '" +s_facebackper2+ "'" +
				", " +category_id+ ", " +index+ ", " +page+ ", '" +format+ "', '" +discount2+ "', " +all_index2+ " ,"+ is_series +",'" +format+ "'),");
		suffix.append("(" + p_id2 + ", '"+p_name2+"', '" +p_url2+ "', '" +img2+ "', '" +p_price2+ "', '" +p_dsr2+ "', '" +p_faceback2+ "'" +
				", " +p_orders_today+ ", " +c_orders2+ ", " +s_id2+ ", '" +s_name2+ "', '" +s_faceback2+ "', '" +s_facebackper2+ "'" +
						", " +category_id+ ", " +index+ ", " +page+ ", '" +format+ "', '" +discount2+ "', " +all_index2+ " ,"+ is_series +" ),");
		
		
	}
    
    public ProductEntity getProductById(String productId){
		 Connection con = null;
		 ResultSet rs;
		 ProductEntity product = new ProductEntity();
       try{   
      	 	con = DriverManager.getConnection(Constant.url_local , Constant.user , Constant.password ) ;   
       }catch(SQLException se){   
	        System.out.println("数据库连接失败！");   
	        	se.printStackTrace() ;   
       }  
       try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select * from product where product_id = " + productId);
			while(rs.next()){
//				product.setProductFaceback(rs.getString("product_faceback"));
				product.setProductOrdersAll(rs.getString("product_orders_all"));
			}
			con.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
      
		return product;
	}
	

	// 数据同步线程
    final class WorkerHandler implements Runnable {  
        ResultSet coreRs;  
        String queryStr;  
        int businessType;  
        String targetTBName;
		private StringBuilder suffix;
		private StringBuilder suffix_all;  
		String sql,prefix,prefix_all,tableName;
        public WorkerHandler(String queryStr,int businessType,String targetTBName) {  
            this.queryStr = queryStr;  
            this.businessType = businessType;  
            this.targetTBName = targetTBName;  
        }  
        @Override  
        public void run() {  
            try {  
                // 开始分析
            	analysis();
            } catch(Exception e){  
                logger.error(e);  
                e.printStackTrace();  
            }  
        }  
        // 同步产品数据方法
        void analysis() throws Exception{  
            // 获得核心库连接
            Statement coreStmt = conn_local.createStatement();
            conn_local.setAutoCommit(false);// 设置手动提交
            PreparedStatement targetPstmt = conn_local.prepareStatement("INSERT INTO product (product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,category_orders,product_orders,store_id,store_name,store_faceback,store_facebackper," +
				"category_id,category_index,category_pageno,discount,date,all_index,is_series) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
            PreparedStatement categoryPstmt = conn_local.prepareStatement("INSERT INTO "+targetTBName+" (product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,category_orders,product_orders,store_id,store_name,store_faceback,store_facebackper," +
    				"category_id,category_index,category_pageno,discount,date,all_index,is_series) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ResultSet coreRs = coreStmt.executeQuery(queryStr); 
            logger.info(Thread.currentThread().getName()+"'s Query SQL::"+queryStr);  
            int batchCounter = 0; // 累加的批处理数量
           
            //添加 到各类目表
            
			prefix_all = "insert into product(product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,product_orders_all,product_orders,category_orders,store_id,store_name,store_faceback,store_facebackper," +
					"category_id,category_index,category_pageno,date,discount,all_index,is_series) VALUES ";
			prefix = "insert into "+targetTBName+"(product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,product_orders,category_orders,store_id,store_name,store_faceback,store_facebackper," +
					"category_id,category_index,category_pageno,date,discount,all_index,is_series) VALUES ";
			suffix = new StringBuilder();
			suffix_all = new StringBuilder();
            while (coreRs.next()) {
            	doAddBatch(coreRs.getString(2), coreRs.getString(3), coreRs.getString(4), coreRs.getString(5), coreRs.getString(6),
            			coreRs.getString(7), coreRs.getString(8), coreRs.getString(9), coreRs.getInt(10), coreRs.getString(11), coreRs.getString(12), 
            			coreRs.getString(13), coreRs.getString(14), coreRs.getInt(15), coreRs.getInt(16), coreRs.getInt(17), 
            			coreRs.getString(18), coreRs.getString(19), coreRs.getInt(20));
            	
                batchCounter++;  
                currentSynCount.incrementAndGet();// 递增
                if (batchCounter % 5000 == 0) { // 1万条数据一提交
                	if(suffix.length()>1){
    					sql = prefix + suffix.substring(0, suffix.length() - 1);
    					categoryPstmt.addBatch(sql);
    					if(suffix_all.length()>1){
    						sql = prefix_all + suffix_all.substring(0, suffix_all.length() - 1) + "ON DUPLICATE KEY UPDATE product_name=VALUES(product_name), product_img=VALUES(product_img),product_price=VALUES(product_price),product_dsr=VALUES(product_dsr)" +
    								",product_faceback=VALUES(product_faceback),product_orders_all=VALUES(product_orders_all),product_orders=VALUES(product_orders),category_orders=VALUES(category_orders) "+
    								",discount=VALUES(discount),all_index=VALUES(all_index),last_date=VALUES(date),is_series=0";
    						targetPstmt.addBatch(sql);
    					}
    				}
                	suffix = new StringBuilder();
        			suffix_all = new StringBuilder();
                    targetPstmt.executeBatch();  
                    targetPstmt.clearBatch();  
                    categoryPstmt.executeBatch();
                    categoryPstmt.clearBatch();
                    conn_local.commit();  
                }  
            }  
            if(suffix.length()>1){
				sql = prefix + suffix.substring(0, suffix.length() - 1);
				categoryPstmt.addBatch(sql);
				if(suffix_all.length()>1){
					sql = prefix_all + suffix_all.substring(0, suffix_all.length() - 1) + "ON DUPLICATE KEY UPDATE product_name=VALUES(product_name), product_img=VALUES(product_img),product_price=VALUES(product_price),product_dsr=VALUES(product_dsr)" +
							",product_faceback=VALUES(product_faceback),product_orders_all=VALUES(product_orders_all),product_orders=VALUES(product_orders),category_orders=VALUES(category_orders) "+
							",discount=VALUES(discount),all_index=VALUES(all_index),last_date=VALUES(date),is_series=0";
					targetPstmt.addBatch(sql);
				}
				// 提交剩余的批处理
	            targetPstmt.executeBatch();  
	            targetPstmt.clearBatch();  
	            categoryPstmt.executeBatch();
	            categoryPstmt.clearBatch();
	            conn_local.commit();
			}
        } 
        
        
        void doAddBatch(String p_id2,
    			String p_name2, String p_url2, String img2, String p_price2,
    			String p_dsr2, String p_faceback2, String p_orders2, int c_orders2,
    			String s_id2, String s_name2, String s_faceback2,
    			String s_facebackper2, int category_id, int index, int page,
    			String format, String discount2, int all_index2) throws ParseException {
    		if(p_dsr2 == null) p_dsr2 = "";
    		if(p_faceback2 == null) p_faceback2 = "";
    		if(discount2 == null) discount2 = "";

    		
    		int is_series = 0;
    		//今日销量
    		Integer p_orders_today = 0;
    		if(p_orders2 != null && !"".equals(p_orders2) && p_id2 != null && !"".equals(p_id2)){
    			//获取前一天的销量，计算出今日销量
    			ProductEntity product = getProductById(p_id2);
    			if(product != null && product.getProductOrdersAll()!=null && !"".equals(product.getProductOrdersAll())){
    				Integer newPF = Integer.parseInt(p_orders2);//今日总销量
    				Integer oldPF = Integer.parseInt(product.getProductOrdersAll());//最近一天的总销量
    				p_orders_today = newPF - oldPF;
    				//判断昨日是否采集到该产品
    				if(product.getLastDate() != null){
    					if(Tools.isYeaterday(product.getLastDate(), new Date()) != 0){
        					is_series = 1;
        				}
    				}else{
    					if(Tools.isYeaterday(product.getDate(), new Date()) != 0){
        					is_series = 1;
        				}
    				}
    			}
    		}
    		suffix_all.append("(" + p_id2 + ", '"+p_name2+"', '" +p_url2+ "', '" +img2+ "', '" +p_price2+ "', '" +p_dsr2+ "', '" +p_faceback2+ "'" +
    				", " +p_orders2+ ", " +p_orders_today+", " +c_orders2+ ", " +s_id2+ ", '" +s_name2+ "', '" +s_faceback2+ "', '" +s_facebackper2+ "'" +
    				", " +category_id+ ", " +index+ ", " +page+ ", '" +format+ "', '" +discount2+ "', " +all_index2+ " ,"+ is_series +"),");
    		suffix.append("(" + p_id2 + ", '"+p_name2+"', '" +p_url2+ "', '" +img2+ "', '" +p_price2+ "', '" +p_dsr2+ "', '" +p_faceback2+ "'" +
    				", " +p_orders_today+ ", " +c_orders2+ ", " +s_id2+ ", '" +s_name2+ "', '" +s_faceback2+ "', '" +s_facebackper2+ "'" +
    						", " +category_id+ ", " +index+ ", " +page+ ", '" +format+ "', '" +discount2+ "', " +all_index2+ " ,"+ is_series +" ),");
    		
    		
    	}
        
        public ProductEntity getProductById(String productId){
   		 Connection con = null;
   		 ResultSet rs;
   		 ProductEntity product = new ProductEntity();
           try{   
          	 	con = DriverManager.getConnection(Constant.url_local , Constant.user , Constant.password ) ;   
           }catch(SQLException se){   
   	        System.out.println("数据库连接失败！");   
   	        	se.printStackTrace() ;   
           }  
           try {
   			Statement stmt = con.createStatement();
   			rs = stmt.executeQuery("select * from product where product_id = " + productId);
   			while(rs.next()){
//   				product.setProductFaceback(rs.getString("product_faceback"));
   				product.setProductOrdersAll(rs.getString("product_orders_all"));
   			}
   			con.close();
   		} catch (SQLException e) {
   			logger.error(e);
   			e.printStackTrace();
   		}
          
   		return product;
   	}
    }  
	
}
