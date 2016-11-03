package com.buss.caiji.controller;
import com.alibaba.fastjson.JSONObject;
import com.buss.caiji.common.Cache;
import com.buss.caiji.entity.CaijiCategoryEntity;
import com.buss.caiji.entity.ProductEntity;
import com.buss.caiji.entity.ProductLogEntity;
import com.buss.caiji.entity.ProductPO;
import com.buss.caiji.method.ConnectionParam;
import com.buss.caiji.method.ConnectionPool;
import com.buss.caiji.method.GroupMethod;
import com.buss.caiji.po.Emtoy;
import com.buss.caiji.po.MyChart;
import com.buss.caiji.po.Serie;
import com.buss.caiji.po.Series;
import com.buss.caiji.service.CaijiCategoryServiceI;
import com.buss.caiji.service.ProductLogServiceI;
import com.buss.caiji.service.ProductServiceI;
import com.buss.caiji.util.Constant;
import com.buss.caiji.util.Tools;
import com.buss.tag.entity.TagEntity;
import com.buss.tag.entity.UserTagProEntity;
import com.google.gson.JsonArray;
import com.mysql.jdbc.PreparedStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.context.annotation.Scope;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.Highchart;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: 产品表
 * @author onlineGenerator
 * @date 2015-08-06 20:28:19
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/productController")
public class ProductController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductServiceI productService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CaijiCategoryServiceI caijiCategoryService;
	@Autowired
	private ProductLogServiceI productLogServiceI;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	static String img = null;
	
	static String p_id = null;

	static String p_name = null;

	static String p_url = null;

	static String p_img = null;
	
	static String p_price = null;
	
	static String p_dsr = null;//评价
	
	static String p_faceback = null;
	
	static String p_orders = null;
	
	static String s_id = null;
	
	static String s_name = null;
	
	static String s_url = null;
	
	static String s_faceback = null;
	
	static String s_facebackper = null;
	
	static String discount = null;
	
	static PreparedStatement ps = null;
	
	static PreparedStatement ps_pro = null;
	
	static Statement stmt = null;
	
	static Connection conn = null;
	
	static int count = 0;
	
	static int c_orders = 0;
	
	static ConnectionPool connectionPool = null;
	
	//是否需要修改或者增加一个字段，从1开始一直到最后一个产品ID.现有的按页排名仍然不变。通过这个新增ID来比较大小
	static int all_index = 1;
	
	static String prefix = "";
	
	static String prefix_all = "";
	
	//分类：按分类插入数据表
	static StringBuffer suffix;
	
	//总的：所有的产品
	static StringBuffer suffix_all;
	
	
	/**
	 * 开始采集
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */

	@RequestMapping(params = "run")
	public void run() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		logger.info("开始采集");
		long start = System.currentTimeMillis();
		ConnectionParam param = new ConnectionParam();
		param.setDriver(Constant.driver);
		param.setPassword(Constant.password);
		param.setUser(Constant.user);
		param.setUrl(Constant.url_service);
		connectionPool = new ConnectionPool(param);
		try {
			connectionPool.createPool();
		} catch (Exception e) {
			logger.error(e);
		}
		conn = connectionPool.getConnection();
		conn.setAutoCommit(false);
		String sql_pro = "insert into product_base(product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,product_orders,category_orders,store_id,store_name,store_faceback,store_facebackper," +
				"category_id,category_index,category_pageno,date,discount,all_index) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//每一次采集总序号都初始化为1.
		all_index = 0;
		List<CaijiCategoryEntity> categoryEntities = caijiCategoryService.loadAll(CaijiCategoryEntity.class);
		for(CaijiCategoryEntity caijiCategoryEntity:categoryEntities){
			System.out.println(caijiCategoryEntity.getId());
			if(caijiCategoryEntity.getPid() == 0){
				continue;
			}
			prefix_all = "insert into product_base(product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,product_orders,category_orders,store_id,store_name,store_faceback,store_facebackper," +
					"category_id,category_index,category_pageno,date,discount,all_index) VALUES ";
			suffix_all = new StringBuffer();
			ps_pro = (PreparedStatement) conn.prepareStatement(sql_pro,Statement.RETURN_GENERATED_KEYS);
			if(caijiCategoryEntity.getCatchPage() != null){
				//每一次采集分类序号都初始化为0.
				c_orders = 0;
				for(int i=1;i<=caijiCategoryEntity.getCatchPage();i++){
					if(i!=1){
						doCatch(caijiCategoryEntity.getUrl().replace(".html", "/"+i+".html"),caijiCategoryEntity.getCId(),caijiCategoryEntity.getCName(),i);
					}else{
						doCatch(caijiCategoryEntity.getUrl(),caijiCategoryEntity.getCId(),caijiCategoryEntity.getCName(),i);
					}
				}
				if(suffix_all.length()>1){
					sql_pro = prefix_all + suffix_all.substring(0, suffix_all.length() - 1) + "ON DUPLICATE KEY UPDATE product_name=VALUES(product_name), product_img=VALUES(product_img),product_price=VALUES(product_price),product_dsr=VALUES(product_dsr)" +
							",product_faceback=VALUES(product_faceback),product_orders=VALUES(product_orders),category_orders=VALUES(category_orders) "+
							",discount=VALUES(discount),all_index=VALUES(all_index)";
					ps_pro.addBatch(sql_pro);
					// 执行操作
					ps_pro.executeBatch();
					// 提交事务
					conn.commit();
					// 清空上一次添加的数据
					System.out.println("执行ps");
				}
					
			}
		}
		logger.info("采集结束");
		ps_pro.close();
		conn.close();
		long end = System.currentTimeMillis();
		System.out.println(end-start+"毫秒");
		logger.info(end-start+"毫秒");
	}
	
	
	private static void doCatch(String urlstr, Integer category_id,
			String category_name,int page) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		try {
            // 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径
            URL url = new URL(urlstr);
            // InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
            InputStreamReader isr = new InputStreamReader(url.openStream(),
                    "gb2312"); // 统一使用utf-8 编码模式
            // 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
            BufferedReader br = new BufferedReader(isr);
            String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容


         // 定义3个正则 用于匹配我们需要的数据
            String all = "(\\d{1,2}\\.\\d{1,2}\\.\\d{4})";
            String href = "<a\\s*.*)(href\\s*=\\s*[\"']*([^>\\s'\"]*)[\"']*)";
            String title = "<a[^>]*>([^<]*)</a>";
            String imgRegular= "src=\"([^\"]*)\"";
            String priceRegular= "<span[^>]*>([^<]*)</span>";
            String dsrRegular = "title=\"([^\"]*)\"";
            String orderRegular= "<em[^>]*>([^<]*)</em>";
            // 创建一个GroupMethod类的对象 gMethod 方便后期调用其类里的 regularGroup方法
            GroupMethod gMethod = new GroupMethod();
            boolean flag = false;
            // 开始读取数据 如果读到的数据不为空 则往里面读
            String sql = "";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            boolean start = false;
            int index = 1;
//            System.out.println("开始");
            while ((strRead = br.readLine()) != null) {
            	//从ul开始解析html
            	if(strRead.contains("<ul")){
            		start = true;
            		index = 1;
            	}
            	if(start){
            		//判断是否含有class = product  取url和id,name
            		if(strRead.contains("<a  class=\"product")||strRead.contains("<a  class=\" product")||strRead.contains("itemprop=\"name\"")){
            			String pattern= "href=\"([^\"]*)\"";
            			Pattern pKey = Pattern.compile(pattern, 2 | Pattern.DOTALL); 
            			Matcher mKey = pKey.matcher(strRead); 
            			if(mKey.find()){ 
            				//截取 url 中   ‘http://www.aliexpress.com/item/’ 这部分
            				p_url = mKey.group(1);
            				if(p_url != null && p_url.length() > 0){
                				if(p_url.split("/").length>5){
                					if(p_url.split("/").length==5){
                						System.out.println(""+p_url);
                					}
                					p_id = p_url.split("/")[5].replace(".html", "");
                				}else{
                					System.out.println("320行：" + p_url );
                				}
                				
                			}
                			p_name = gMethod.regularGroup(title, strRead);
                			p_name = p_name.replace("'", "");
                			if(p_url != null){
                				p_url = p_url.replace("http://www.aliexpress.com/item/", "");
                			}
            			}
            		}
            		//判断是否含有class = picCore  取图片地址
            		if(strRead.contains("picCore pic-Core-v")||strRead.contains("picCore")){
            			img = gMethod.regularGroup(imgRegular, strRead);
            		}
            		//判断是否含有class = value    取价格
            		if(strRead.contains("<span class=\"value")){
            			p_price = gMethod.regularGroup(priceRegular, strRead);
            			p_price = p_price.replace("US $", "");
            		}
            		//判断是否含有class = rate    取折扣
            		if(strRead.contains("<span class=\"rate")){
            			discount = gMethod.regularGroup(priceRegular, strRead).replace("|", "").replace("NULL", "");
            		}
            		
            		//判断是否含有class=star star-s  取评价
            		if(strRead.contains("<span class=\"star star-s")){
            			p_dsr = gMethod.regularGroup(dsrRegular, strRead).substring(13, 16);
            		}
            		//判断是否含有title= Feedback    取销量faceback
            		if(strRead.contains("title=\"Feedback")||strRead.contains("class=\"rate-num\"")){
            			p_faceback = gMethod.regularGroup(title, strRead).replace("Feedback", "").replace("(", "").replace(")", "");
            			if(p_faceback.contains("|")){
            				p_faceback = p_faceback.split("|")[0];
            			}
            		}
            		//判断是否含有<em title="Total Orders">    取排序orders
            		if(strRead.contains("<em title=\"Total Orders\">")){
            			p_orders = gMethod.regularGroup(orderRegular, strRead).trim().replace("Order  (", "").replace("Orders  (", "").replace("Order (", "").replace("Orders (", "").replace(")", "");
            		}
            		//判断是否含有class="store"    取store相关的值
            		if(strRead.contains("class=\"store\"")){
            			String pattern= "href=\"([^\"]*)\"";
            			String storeName= "title=\"([^\"]*)\"";
            			Pattern pKey = Pattern.compile(pattern, 2 | Pattern.DOTALL); 
            			Matcher mKey = pKey.matcher(strRead); 
            			if(mKey.find()){ 
            				s_url = mKey.group(1);
            				if(s_url!=null&&s_url.length()>0){
                				if(s_url.split("/").length==5){
                					s_id = s_url.split("/")[4].replace(".html", "");
                				}else if(s_url.split("/").length>5){
                					s_id = s_url.split("/")[5].split("-")[0];
                				}
                			}
                			s_name = gMethod.regularGroup(storeName, strRead);
                			s_name = s_name.replace("'", "\'");
            			}
            		}
            		//判断是否含有class="score-dot"   取store_faceback   store_facebackper相关的值
            		if(strRead.contains("class=\"score-dot\"")){
            			String feedbackscore= "feedbackscore=\"([^\"]*)\"";
            			String sellerpositivefeedbackpercentage= "sellerpositivefeedbackpercentage=\"([^\"]*)\"";
            			
            			s_faceback = gMethod.regularGroup(feedbackscore, strRead);
            			s_facebackper = gMethod.regularGroup(sellerpositivefeedbackpercentage, strRead);
            		}
            		
            		if(strRead.contains("</li>")){
            			if(p_id!=null&&!p_orders.equals("0")){
            				p_id = p_id.split("\\?")[0];
            				if(p_id.length()>12){
            					p_id = p_id.substring(0, 11);
            				}
            				all_index++;
            				c_orders++;
            				doAddBatch(p_id,p_name,p_url,img,p_price,p_dsr,p_faceback,p_orders,c_orders,s_id,s_name,
            						s_faceback,s_facebackper,category_id,index,page,df.format(new Date()),discount,all_index);
            				p_name = null;
            				p_url = null;
            				img = null;
            				p_price = null;
            				p_dsr = null;
            				p_faceback = null;
            				p_id = null;
            				p_orders = "0";
            				s_id = null;
            				s_name = null;
            				s_faceback = null;
            				s_facebackper = null;
            				discount = null;
            				if(++count % 10000 == 0) {/*
            					Runnable r = new Runnable(){  
            						public void run(){  
            							 try {
	            								System.out.println("执行ps");
	            								ps_pro.executeBatch();
	            								ps_pro.clearBatch();
	            							} catch (SQLException e) {
	            								// TODO Auto-generated catch block
	            								e.printStackTrace();
	            							}
            							}  
            						}; 
            						Thread t = new Thread(r);  
            						t.start(); 
            				*/}
            			}
            		}
            		
            		if(strRead.contains("</li>")){
            			index++;
            			if(index>=37){
            				index = 1;
            				start = false;
            				return;
            			}
            		}
            	}
            }
            // 当读完数据后 记得关闭 BufferReader
            br.close();
        } catch (IOException e) {
            // 如果出错 抛出异常
        	logger.error(e.getMessage());
        	logger.error("320行："+urlstr);
            e.printStackTrace();
            System.out.println("451报错url:"+urlstr);
        }
		
	}
	
	private static void doAddBatch(String p_id2,
			String p_name2, String p_url2, String img2, String p_price2,
			String p_dsr2, String p_faceback2, String p_orders2, int c_orders2,
			String s_id2, String s_name2, String s_faceback2,
			String s_facebackper2, Integer category_id, int index, int page,
			String format, String discount2, int all_index2) {
		if(p_dsr2 == null) p_dsr2 = "0";
		if(p_faceback2 == null) p_faceback2 = "0";
		if(discount2 == null) discount2 = "";
		
		suffix_all.append("(" + p_id2 + ", '"+p_name2+"', '" +p_url2+ "', '" +img2+ "', '" +p_price2+ "', '" +p_dsr2+ "', '" +p_faceback2+ "'" +
				", " +p_orders2+ ", " +c_orders2+ ", " +s_id2+ ", '" +s_name2+ "', '" +s_faceback2+ "', '" +s_facebackper2+ "'" +
				", " +category_id+ ", " +index+ ", " +page+ ", '" +format+ "', '" +discount2+ "', " +all_index2+ "),");
	}

	private static void doAddBatch(PreparedStatement ps2, String p_id2,
			String p_name2, String p_url2, String img2, String p_price2,
			String p_dsr2, String p_faceback2, String p_orders2, int c_orders2,
			String s_id2, String s_name2, String s_faceback2,
			String s_facebackper2, Integer category_id, int index, int page,
			String format, String discount2, int all_index2) throws SQLException {
			ps2.setString(1, p_id);
			ps2.setString(2, p_name);
			ps2.setString(3, p_url);
			ps2.setString(4, img);
			ps2.setString(5, p_price);
			ps2.setString(6, p_dsr);
			ps2.setString(7, p_faceback);
			ps2.setString(8, p_orders);
			ps2.setInt(9, c_orders);
			ps2.setString(10, s_id);
			ps2.setString(11, s_name);
			ps2.setString(12, s_faceback);
			ps2.setString(13, s_facebackper);
			ps2.setInt(14, category_id);
			ps2.setInt(15, index);
			ps2.setInt(16, page);
			ps2.setString(17, format);
			ps2.setString(18, discount);
			ps2.setInt(19, all_index);
			ps2.addBatch();
		
	}


	/**
	 * 产品表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "product")
	public ModelAndView product(HttpServletRequest request) {
		return new ModelAndView("com/buss/caiji/productList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.productService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除产品表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ProductEntity product, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		/*product = systemService.getEntity(ProductEntity.class, product.getId());
		message = "产品表删除成功";
		try{
			productService.delete(product);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "产品表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);*/
		return j;
	}
	
	/**
	 * 批量删除产品表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "产品表删除成功";
		try{
			for(String id:ids.split(",")){
				ProductEntity product = systemService.getEntity(ProductEntity.class, 
				Integer.parseInt(id)
				);
				productService.delete(product);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "产品表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加产品表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ProductEntity product, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "产品表添加成功";
		try{
			productService.save(product);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "产品表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新产品表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ProductEntity product, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "产品表更新成功";
		/*ProductEntity t = productService.get(ProductEntity.class, product.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(product, t);
			productService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "产品表更新失败";
			throw new BusinessException(e.getMessage());
		}*/
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 产品表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ProductEntity product, HttpServletRequest req) {
		/*if (StringUtil.isNotEmpty(product.getId())) {
			product = productService.getEntity(ProductEntity.class, product.getId());
			req.setAttribute("productPage", product);
		}*/
		return new ModelAndView("com/buss/caiji/product-add");
	}
	/**
	 * 产品表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ProductEntity product, HttpServletRequest req) {
		/*if (StringUtil.isNotEmpty(product.getId())) {
			product = productService.getEntity(ProductEntity.class, product.getId());
			req.setAttribute("productPage", product);
		}*/
		return new ModelAndView("com/buss/caiji/product-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","productController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ProductEntity product,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		List<ProductEntity> products = this.productService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"产品表");
		modelMap.put(NormalExcelConstants.CLASS,ProductEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("产品表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,products);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ProductEntity product,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "产品表");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,ProductEntity.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA,null);
		return TemplateExcelConstants.JEECG_TEMPLATE_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<ProductEntity> listProductEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ProductEntity.class,params);
				for (ProductEntity product : listProductEntitys) {
					productService.save(product);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	/**
	 * 统计集合页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "statisticTabs")
	public ModelAndView statisticTabs(HttpServletRequest request) {
		request.setAttribute("reportType", request.getParameter("reportType"));
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("productId", request.getParameter("productId"));
		return new ModelAndView("com/buss/caiji/statistics/statisticTabs");
	}
	/**
	 * 用户浏览器使用统计图
	 * 
	 * @return
	 */
	@RequestMapping(params = "productBroswer")
	public ModelAndView productBroswer(String reportType, HttpServletRequest request) {
		request.setAttribute("reportType", reportType);
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("productId", request.getParameter("productId"));
		if("price".equals(reportType)){
			return new ModelAndView("com/buss/caiji/statistics/productPriceBroswerLine");
		}else if("line".equals(reportType)) {
			return new ModelAndView("com/buss/caiji/statistics/productBroswerLine");
		}
		return new ModelAndView("com/buss/caiji/statistics/productIndexBroswerLine");
	}
	
	
	/**
	 * 用户浏览器使用统计图
	 * 
	 * @return
	 */
	@RequestMapping(params = "reportProduct")
	public ModelAndView reportProduct(String reportType, HttpServletRequest request) {
		request.setAttribute("reportType", reportType);
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("productId", request.getParameter("productId"));

		return new ModelAndView("report/reportProduct");
	}
	
	
	/**
	 * 报表数据生成
	 * 
	 * @return
	 */
	@RequestMapping(params = "getBroswerBar")
	@ResponseBody
	public List<Highchart> getBroswerBar(HttpServletRequest request,String reportType, HttpServletResponse response) {
		List<Highchart> list = new ArrayList<Highchart>();
		Highchart hc = new Highchart();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT broswer ,count(broswer) FROM TSLog group by broswer");
		List userBroswerList = systemService.findByQueryString(sb.toString());
		Long count = systemService.getCountForJdbc("SELECT COUNT(1) FROM T_S_Log WHERE 1=1");
		List lt = new ArrayList();
		MyChart myChart = new MyChart();
		getLine(request, reportType, response, myChart);
		hc = new Highchart();
//		hc.setName(MutiLangUtil.getMutiLangInstance().getLang(USER_BROWSER_ANALYSIS));
		hc.setType(reportType);
		Map<String, Object> map;
		int i = 0;
		for(String category:myChart.getCategoriesLocal()){
			map = new HashMap<String, Object>();
			double[] obj = myChart.getSeries().get(0).getData();
			map.put("name", category);
			map.put("y", obj[i]);
			map.put("percentage", obj[i]);
			i++;
//			Long groupCount = (Long) obj[1];
//			Double  percentage = 0.0;
//			if (count != null && count.intValue() != 0) {
//				percentage = new Double(groupCount)/count;
//			}
			
			lt.add(map);
		}
		/*if (userBroswerList.size() > 0) {
			for (Object object : userBroswerList) {
				map = new HashMap<String, Object>();
				Object[] obj = (Object[]) object;
				map.put("name", obj[0]);
				map.put("y", obj[1]);
				Long groupCount = (Long) obj[1];
				Double  percentage = 0.0;
				if (count != null && count.intValue() != 0) {
					percentage = new Double(groupCount)/count;
				}
				map.put("percentage", percentage*100);
				lt.add(map);
			}
		}*/
		hc.setData(lt);
//		hc.setData(myChart.getSeries());
		list.add(hc);
		return list;
	}
	
	// 线状图  
	@RequestMapping(params = "getLine")
	@ResponseBody
    private MyChart getLine(HttpServletRequest request,String reportType ,HttpServletResponse response,MyChart myChart) {  
		String productId = request.getParameter("productId");
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.MONTH, -2); 
		if(productId!=null){
			productId = productId.replace("'", "");
		}
//		String productIds = request.getParameter("productIds");
		myChart.setType(reportType);
        List<Serie> series = new ArrayList<Serie>();  
        double line[];  
        Serie serie = null;
        Connection con = null;
        
        
        String[] productList = productId.split(";");

        Statement st = null;
        ResultSet rs;
        try{ 
        	con = DriverManager.getConnection(Constant.url_local , Constant.user , Constant.password ) ;   
        	st = con.createStatement();
        }catch(SQLException se){   
		   	 System.out.println("数据库连接失败！");   
		   	 se.printStackTrace() ;   
	    }   
        
        if(categoryId == null){
        	String sql = "select * from product_analysis where p_id='"+productId+"'";
			try {
				rs = st.executeQuery(sql);
				while(rs.next()){
					categoryId = rs.getInt("c_id");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<CaijiCategoryEntity> caijiCategoryEntities = getPath(categoryId);
			 try {
				rs = st.executeQuery(sql);
				while(rs.next()){
//					categoryId = rs.getInt("c_id");
					for(CaijiCategoryEntity c:caijiCategoryEntities){
						if(c.getCId().equals(rs.getInt("c_id"))){
							categoryId = rs.getInt("c_id");
						}
					}
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		}else if(reportType.contains("all")){
			 List<CaijiCategoryEntity> caijiCategoryEntities = getPath(categoryId);
			 String sql = "select * from product_analysis where p_id='"+productId+"'";
			 try {
				rs = st.executeQuery(sql);
				while(rs.next()){
//					categoryId = rs.getInt("c_id");
					for(CaijiCategoryEntity c:caijiCategoryEntities){
						if(c.getCId().equals(rs.getInt("c_id"))){
							categoryId = rs.getInt("c_id");
						}
					}
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		}
       
        
        List<ProductLogEntity> logList = productLogServiceI.loadAll(ProductLogEntity.class);
        String categories[] = null;
        List<String> list = new ArrayList<String>();
//		for(int i = 0; i < logList.size(); i++){
//			if(logList.get(i).getTableName()!=null){
//				categories[i] = logList.get(i).getTableName().replace("product_", "").substring(5,10);
//			}
//		}

		try {
		    for(int o=0; o < productList.length; o++){
		    	List<ProductEntity> pList = new ArrayList<ProductEntity>();
		    	productId = productList[o];
				String sql = "select * from product_analysis where p_id='"+productId+"' and c_id = '"+ categoryId+"'";
				rs = st.executeQuery(sql);
				ProductEntity product = new ProductEntity();
				while(rs.next()){
					String[] pas = rs.getString("content").split(";");
					int j = 0;
					String date = "";
					
					for(int i=0;i<pas.length;i++){
						String[] pa = pas[i].replace("[", "").replace("]", "").split(",");
						try {
							if(calendar.getTime().after(fd.parse(pa[4]))){
								continue;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
						date = pa[4].substring(5,10);
						if(!list.contains(date)){
							list.add(date);
							j++;
							product = new ProductEntity();
							product.setProductName(rs.getString("p_name"));
							product.setProductPrice(pa[0]);
							product.setProductOrdersAll(pa[1]);
							product.setProductOrdersToday(pa[2]);
//							product.setCategoryOrders(Integer.parseInt(pa[3]));
							pList.add(product);
						}
						
					}
					categories = new String[list.size()];
					for(int i=0;i<list.size();i++){
						categories[i] = list.get(i);
					}
					/*for(ProductLogEntity log:logList){
						boolean flag = false;//记录是否有采集
						for(int i=0;i<pas.length;i++){
							String[] pa = pas[i].replace("[", "").replace("]", "").split(",");
							if(log.getTableName().contains(pa[4].trim().replace("-", "_"))){
								flag = true;
								product = new ProductEntity();
		//						product.setDate(pa[4]);
								product.setProductName(rs.getString("p_name"));
								product.setProductPrice(pa[0]);
								product.setProductOrdersAll(pa[1]);
								product.setProductOrdersToday(pa[2]);
								product.setCategoryOrders(Integer.parseInt(pa[3]));
								pList.add(product);
							}
						}
						if(!flag){
							if(pList.size()>0){
								pList.add(pList.get(pList.size()-1));
							}else{
								pList.add(product);
							}
							
						}
					}*/
				}
				
				rs.close();
				if(reportType.contains("index")){
					line = new double[categories.length];
					for (int i = 0; i < categories.length; i++) {  
	    				/*if(pList.size()>0 && pList.get(i) != null && pList.get(i).getCategoryOrders()!=null){
	    					line[i] = Double.parseDouble(pList.get(i).getCategoryOrders().toString());
	    				}*/
	    	        }
					series.add(new Serie("排名", line, "0", "line"));
				}else if(pList.size()>0){
					//总销量
		            line = new double[categories.length];
					for (int i = 0; i < categories.length; i++) {  
	    				if(pList.size()>0 && pList.get(i) != null && pList.get(i).getProductPrice()!=null){
	    					 line[i] = Double.parseDouble(pList.get(i).getProductOrdersAll());
	    				}
	    	        }
		            series.add(new Serie("总销量", line, "0", "line"));
					//今日销量
		            line = new double[categories.length];
					for (int i = 0; i < categories.length; i++) {  
	    				if(pList.size()>0 && pList.get(i) != null && pList.get(i).getProductPrice()!=null){
	    					 line[i] = Double.parseDouble(pList.get(i).getProductOrdersToday());
	    				}
	    	        }
		            series.add(new Serie("今日销量", line, "1", "line"));
					//price
					line = new double[categories.length];
					for (int i = 0; i < categories.length; i++) {  
	    				if(pList.size()>0 && pList.get(i) != null && pList.get(i).getProductPrice()!=null){
	    					 line[i] = Double.parseDouble(pList.get(i).getProductPrice().split("-")[0].replace("US $", ""));
	    				}
	    	        }
		            series.add(new Serie("价格", line, "0", "column"));
				}
	        }
	        st.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage());
		}
        
		myChart.setCategoriesLocal(categories);
        myChart.setSeries(series);  
        return myChart;  
    }
	
	
	public List<CaijiCategoryEntity> getPath(Integer categoryId){
		List<CaijiCategoryEntity> list = new ArrayList<CaijiCategoryEntity>();
		//当前访问最后一级
		if(categoryId != null){
			CaijiCategoryEntity categoryEntity = Cache.getCategoryList().get(categoryId);
//			list.add(categoryEntity);
			
			addChildCategory(categoryEntity.getId(), list);
		}
		return list;
	}
	
	
	public void addChildCategory(Integer pid,List<CaijiCategoryEntity> list){
		Map map = Cache.getCategoryList();
	    Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			CaijiCategoryEntity caijiCategoryEntity = (CaijiCategoryEntity)entry.getValue();
			if(caijiCategoryEntity.getPid().equals(pid)){
				list.add(caijiCategoryEntity);
				addChildCategory(caijiCategoryEntity.getId(), list);
			}
		}
		
	}
	
	
	// 取得时间段  
    private String[] getCombCate(MyChart myChart) {  
        String[] newCates;  
        String[] cates = myChart.getCategories();  
        switch (myChart.getTypetime()) {  
        case 3:  
            newCates = Tools.getYearList(cates[0], cates[1]);  
            break;  
        case 2:  
            newCates = Tools.getMonthList(cates[0], cates[1]);  
            break;  
        case 1:  
            newCates = Tools.getDayList(cates[0], cates[1]);  
            break;  
        default:  
            newCates = cates;  
            break;  
        }  
        return newCates;  
    }  
    
    
	/**
	 * 报表数据生成
	 * 
	 * @return
	 */
	@RequestMapping(params = "getBroswerBar1")
	@ResponseBody
	public List<Highchart> getBroswerBar1(HttpServletRequest request,String reportType, HttpServletResponse response) {
		List<Highchart> list = new ArrayList<Highchart>();
		Highchart hc = new Highchart();
		DataGrid dataGrid = new DataGrid();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT broswer ,count(broswer) FROM TSLog group by broswer");
		List userBroswerList = systemService.findByQueryString(sb.toString());
		Long count = systemService.getCountForJdbc("SELECT COUNT(1) FROM T_S_Log WHERE 1=1");
		ProductEntity productEntity = new ProductEntity();
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, productEntity, request.getParameterMap());
		try{
			cq.eq("productId", "32227858652");
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.productService.getDataGridReturn(cq, true);
		List<ProductEntity> pList = dataGrid.getResults();
		List lt = new ArrayList();
		hc = new Highchart();
		hc.setName("产品销量");
		hc.setType(reportType);
		Map<String, Object> map = null;
		if (userBroswerList.size() > 0) {
			for (ProductEntity object : pList) {
				map = new HashMap<String, Object>();
//				Object[] obj = (Object[]) object;
				map.put("name", object.getProductName());
				map.put("y", object.getProductOrdersToday());
//				Long groupCount = (Long) obj[1];
//				Double  percentage = 0.0;
//				if (count != null && count.intValue() != 0) {
//					percentage = new Double(groupCount)/count;
//				}
//				map.put("percentage", percentage*100);
				lt.add(map);
			}
		}
		hc.setData(lt);
		list.add(hc);
		
		return list;
	}
	
	public static ProductEntity getProductById(String productId){
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
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from product where product_id = " + productId);
			while(rs.next()){
				product.setProductFaceback(rs.getString("product_faceback"));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
       
		return product;
	}
	
	
	public CaijiCategoryEntity getCategoryByCategoryId(Integer categoryId){
		 Connection con = null;
		 ResultSet rs;
		 CaijiCategoryEntity category = new CaijiCategoryEntity();
         try{   
        	 con = DriverManager.getConnection(Constant.url_local , Constant.user , Constant.password ) ;   
         }catch(SQLException se){   
	        System.out.println("数据库连接失败！");   
	        se.printStackTrace() ;   
         }  
         try {
 			stmt = con.createStatement();
 			rs = stmt.executeQuery("select * from caiji_category where c_id = " + categoryId);
 			while(rs.next()){
 				category.setCId(rs.getInt("c_id"));
 	 			category.setCName(rs.getString("c_name"));
 	 			category.setPid(rs.getInt("pid"));
 	 			category.setId(rs.getInt("id"));
 			}
 			con.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
		return category;
	}
}
