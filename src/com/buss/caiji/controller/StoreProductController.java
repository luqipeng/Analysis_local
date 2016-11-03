package com.buss.caiji.controller;
import com.buss.caiji.entity.StoreProductEntity;
import com.buss.caiji.method.ConnectionParam;
import com.buss.caiji.method.ConnectionPool;
import com.buss.caiji.method.GroupMethod;
import com.buss.caiji.service.StoreProductServiceI;
import com.buss.caiji.util.Constant;
import com.mysql.jdbc.PreparedStatement;

import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
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
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: 店铺产品
 * @author onlineGenerator
 * @date 2015-08-06 20:31:28
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/storeProductController")
public class StoreProductController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StoreProductController.class);

	@Autowired
	private StoreProductServiceI storeProductService;
	@Autowired
	private SystemService systemService;
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
	
	static Statement stmt = null;
	
	static Connection conn = null;
	
	static int count = 0;
	
	static int c_orders = 0;
	
	static ConnectionPool connectionPool = null;
	
	//是否需要修改或者增加一个字段，从1开始一直到最后一个产品ID.现有的按页排名仍然不变。通过这个新增ID来比较大小
	static int all_index = 1;
	
	@RequestMapping(params = "run")
	public void run() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		logger.info("开始采集");
		doCatch();
		logger.info("采集结束");
	}
	
	public static void doCatch() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		long start = System.currentTimeMillis();
		logger.info("start CatchData");
		ConnectionParam param = new ConnectionParam();
		param.setDriver(Constant.driver);
		param.setPassword(Constant.password);
		param.setUser(Constant.user);
		param.setUrl(Constant.url_service);
		connectionPool = new ConnectionPool(param);
		try {
			connectionPool.createPool();
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn = connectionPool.getConnection();
		String sql = "insert into store_product(product_id,product_name,product_url,product_img,product_price,product_dsr,product_faceback,product_orders,category_orders,store_id,store_name,store_faceback,store_facebackper," +
	            					"category_id,category_index,category_pageno,date,discount,all_index) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps = (PreparedStatement) conn.prepareStatement(sql);
	    Statement stmt1 = conn.createStatement();
	    stmt1.execute("select * from store where is_catch =0");
		ResultSet rs = (ResultSet) stmt1.getResultSet();

		all_index = 1;
		while(rs.next()){
			s_url = rs.getString("store_url");
			s_id = rs.getString("store_id");
			s_name = rs.getString("store_name");
			c_orders = 0;
			logger.info("开始抓取"+s_url);
			doCatch(s_url,s_id,s_name);
			logger.info("结束抓取"+s_url);
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start+"毫秒");
		ps.executeBatch();
		ps.close();
		conn.close();
	 }
 
private static void doCatch(String urlstr, String s_id,
		String s_name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	try {
		System.out.println(urlstr);
        // 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径
        URL url = new URL(urlstr);
        // InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
        InputStreamReader isr = new InputStreamReader(url.openStream(),
                "utf-8"); // 统一使用utf-8 编码模式
        // 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
        BufferedReader br = new BufferedReader(isr);
        String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容


     // 定义3个正则 用于匹配我们需要的数据
        String all = "(\\d{1,2}\\.\\d{1,2}\\.\\d{4})";
        String href = "<a\\s*.*)(href\\s*=\\s*[\"']*([^>\\s'\"]*)[\"']*)";
        String title = "<a[^>]*>([^<]*)</a>";
        String imgRegular= "src=\"([^\"]*)\"";
        String priceRegular= "<b[^>]*>([^<]*)</b>";
        String dsrRegular = "title=\"([^\"]*)\"";
        String orderRegular= "<span[^>]*>([^<]*)</span>";
        // 创建一个GroupMethod类的对象 gMethod 方便后期调用其类里的 regularGroup方法
        GroupMethod gMethod = new GroupMethod();
        boolean flag = false;
        // 开始读取数据 如果读到的数据不为空 则往里面读
        String sql = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        boolean begin = false;
        boolean start = false;
        int index = 1;
        
        while ((strRead = br.readLine()) != null) {
        	//从ul开始解析html
        	if(strRead.contains("<div class=\"layout row-c2-s7\"")){
        		System.out.println("开始");
        		begin = true;
        		index = 1;
        	}
        	if(begin){
        		if(strRead.contains("data-title=\"商品推荐")){
        			start = true;
        		}
        	}
        	if(start){
        		//判断是否含有class = product  取url和id,name
        		if(strRead.contains("class=\"pic-rind")){
        			String pattern= "href=\"([^\"]*)\"";
        			p_url = gMethod.regularGroup(pattern, strRead);
    				//截取 url 中   ‘http://www.aliexpress.com/item/’ 这部分
    				if(p_url!=null&&p_url.length()>0){
        				if(p_url.split("/").length>6){
        					p_id = p_url.split("/")[6].replace(".html", "").split("_")[1];
        					p_name = p_url.split("/")[5].replace("-", " ");
                			p_name = p_name.replace("'", "");
        				}
        				p_url = p_url.replace("http://www.aliexpress.com/store/product/", "");
        			}
            			
        		}
        		//判断是否含有class = picCore  取图片地址
        		if(strRead.contains("pic-core lazy-load")){
        			img = gMethod.regularGroup(imgRegular, strRead);
        		}
        		//判断是否含有class = value    取价格
        		if(strRead.contains("class=\"cost")){
        			p_price = gMethod.regularGroup(priceRegular, strRead);
        		}else if(strRead.contains("class=\"notranslate")){
        			p_price = gMethod.regularGroup(priceRegular, strRead);
        		}
        		//判断是否含有class = rate    取折扣
        		if(strRead.contains("class=\"rate")){
        			discount = gMethod.regularGroup(priceRegular, strRead).replace("|", "").replace("NULL", "");
        		}
        		
        		//判断是否含有class=star star-s  取评价
        		if(strRead.contains("class=\"star star-s")){
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
        		if(strRead.contains("span class=\"recent-order\">")){
        			p_orders = gMethod.regularGroup(orderRegular, strRead).trim().replace("Order  (", "").replace("Orders  (", "").replace("Order (", "").replace("Orders(", "").replace(")", "");
        		}
        		//判断是否含有class="store"    取store相关的值
        		/*if(strRead.contains("class=\"store\"")){
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
        		}*/
        		//判断是否含有class="score-dot"   取store_faceback   store_facebackper相关的值
        		/*if(strRead.contains("class=\"score-dot\"")){
        			String feedbackscore= "feedbackscore=\"([^\"]*)\"";
        			String sellerpositivefeedbackpercentage= "sellerpositivefeedbackpercentage=\"([^\"]*)\"";
        			
        			s_faceback = gMethod.regularGroup(feedbackscore, strRead);
        			s_facebackper = gMethod.regularGroup(sellerpositivefeedbackpercentage, strRead);
        		}*/
        		
        		if(strRead.contains("</li>")){
        			System.out.println(p_id);
        			if(p_id!=null){
        				ps.setString(1, p_id);
        				ps.setString(2, p_name);
        				ps.setString(3, p_url);
        				ps.setString(4, img);
        				ps.setString(5, p_price);
        				ps.setString(6, p_dsr);
        				ps.setString(7, p_faceback);
        				ps.setString(8, p_orders);
        				ps.setInt(9, ++c_orders);
        				ps.setString(10, s_id);
        				ps.setString(11, s_name);
        				ps.setString(12, s_faceback);
        				ps.setString(13, s_facebackper);
        				ps.setString(14, s_id);
        				ps.setInt(15, index);
        				ps.setInt(16, 1);
        				ps.setString(17, df.format(new Date()));
						ps.setString(18, discount);
						ps.setInt(19, all_index++);
        				ps.addBatch();
        				p_name = null;
        				p_url = null;
        				img = null;
        				p_price = null;
        				p_dsr = null;
        				p_faceback = null;
        				p_id = null;
        				p_orders = "0";
        				s_faceback = null;
        				s_facebackper = null;
        				discount = null;
        				if(++count % 10000 == 0) {
        					Runnable r = new Runnable(){  
        					public void run(){  
    							 try {
									ps.executeBatch();
									ps.clearBatch();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
        						}  
    						}; 
    						Thread t = new Thread(r);  
    						t.start();  
        			        System.out.println("执行ps");
        			    }
        			}
        		}
        		
        		if(strRead.contains("<div class=\"col-sub\"")){
    				System.out.println("结束"+strRead);
    				index = 1;
    				start = false;
    				return;
        		}
        	}
        }
        // 当读完数据后 记得关闭 BufferReader
        br.close();
    } catch (IOException e) {
        // 如果出错 抛出异常
    	logger.error(e.getMessage());
        e.printStackTrace();
    }
	
}
	

	/**
	 * 店铺产品列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "storeProduct")
	public ModelAndView storeProduct(HttpServletRequest request) {
		return new ModelAndView("com/buss/caiji/storeProductList");
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
	public void datagrid(StoreProductEntity storeProduct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(StoreProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, storeProduct, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.storeProductService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除店铺产品
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(StoreProductEntity storeProduct, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		storeProduct = systemService.getEntity(StoreProductEntity.class, storeProduct.getProductId());
		message = "店铺产品删除成功";
		try{
			storeProductService.delete(storeProduct);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺产品删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除店铺产品
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "店铺产品删除成功";
		try{
			for(String id:ids.split(",")){
				StoreProductEntity storeProduct = systemService.getEntity(StoreProductEntity.class, 
				Integer.parseInt(id)
				);
				storeProductService.delete(storeProduct);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺产品删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加店铺产品
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(StoreProductEntity storeProduct, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "店铺产品添加成功";
		try{
			storeProductService.save(storeProduct);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺产品添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新店铺产品
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(StoreProductEntity storeProduct, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "店铺产品更新成功";
		StoreProductEntity t = storeProductService.get(StoreProductEntity.class, storeProduct.getProductId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(storeProduct, t);
			storeProductService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "店铺产品更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 店铺产品新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(StoreProductEntity storeProduct, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(storeProduct.getProductId())) {
			storeProduct = storeProductService.getEntity(StoreProductEntity.class, storeProduct.getProductId());
			req.setAttribute("storeProductPage", storeProduct);
		}
		return new ModelAndView("com/buss/caiji/storeProduct-add");
	}
	/**
	 * 店铺产品编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(StoreProductEntity storeProduct, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(storeProduct.getProductId())) {
			storeProduct = storeProductService.getEntity(StoreProductEntity.class, storeProduct.getProductId());
			req.setAttribute("storeProductPage", storeProduct);
		}
		return new ModelAndView("com/buss/caiji/storeProduct-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","storeProductController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(StoreProductEntity storeProduct,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(StoreProductEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, storeProduct, request.getParameterMap());
		List<StoreProductEntity> storeProducts = this.storeProductService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"店铺产品");
		modelMap.put(NormalExcelConstants.CLASS,StoreProductEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("店铺产品列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,storeProducts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(StoreProductEntity storeProduct,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "店铺产品");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,StoreProductEntity.class);
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
				List<StoreProductEntity> listStoreProductEntitys = ExcelImportUtil.importExcel(file.getInputStream(),StoreProductEntity.class,params);
				for (StoreProductEntity storeProduct : listStoreProductEntitys) {
					storeProductService.save(storeProduct);
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
}
