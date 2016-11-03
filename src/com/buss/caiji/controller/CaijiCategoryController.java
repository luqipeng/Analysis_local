package com.buss.caiji.controller;
import com.buss.caiji.entity.CaijiCategoryEntity;
import com.buss.caiji.method.ConnectionParam;
import com.buss.caiji.method.ConnectionPool;
import com.buss.caiji.method.GroupMethod;
import com.buss.caiji.service.CaijiCategoryServiceI;
import com.buss.caiji.util.Constant;

import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

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
 * @Description: 类目表
 * @author onlineGenerator
 * @date 2015-08-06 19:04:51
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/caijiCategoryController")
public class CaijiCategoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CaijiCategoryController.class);

	@Autowired
	private CaijiCategoryServiceI caijiCategoryService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		long start = System.currentTimeMillis();
		doCatch();
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-start));
	}
	
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
		doCatch();
		logger.info("采集结束");
	}
	
	static String c_id = null;
	
	static String c_name = null;

	static String c_url = null;
	
	static String p_id = null;
	
	static ConnectionPool connectionPool = null;
	
	static Connection conn = null;
	
	static  Statement stmt_root = null;
	
	static  Statement stmt = null;
	
    public static void doCatch() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // 首先用一个字符串 来装载网页链接
        String strUrl = "";
        try {
        	ConnectionParam param = new ConnectionParam();
    		param.setDriver(Constant.driver);
    		param.setPassword(Constant.password);
    		param.setUser(Constant.user);
    		param.setUrl(Constant.url_service);
    		connectionPool = new ConnectionPool(param);
    		try {
    			connectionPool.createPool();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		conn = connectionPool.getConnection();
    		stmt_root = conn.createStatement();
    		stmt = conn.createStatement();
            String sql = "select * from caiji_category where pid = 0 and is_catch = 1";
            ResultSet rs = (ResultSet)stmt_root.executeQuery(sql);
			while(rs.next()){
				//当前的分类id
				c_id = rs.getString(2);
				p_id = rs.getString(1);
				strUrl = rs.getString(6);
				
				 // 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径
	            // 更多可以看看 http://wenku.baidu.com/view/8186caf4f61fb7360b4c6547.html
	            URL url = new URL(strUrl);
	            // InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
	            // 更多可以看看 http://blog.sina.com.cn/s/blog_44a05959010004il.html
	            InputStreamReader isr = new InputStreamReader(url.openStream(),
	                    "gb2312"); // 统一使用utf-8 编码模式
	            // 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
	            BufferedReader br = new BufferedReader(isr);
	            String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容


	         // 定义3个正则 用于匹配我们需要的数据
	            String hrefPattern= "href=\"([^\"]*)\"";
	            String title = "<a[^>]*>([^<]*)</a>";
	            //创建一个GroupMethod类的对象 gMethod 方便后期调用其类里的 regularGroup方法
	            GroupMethod gMethod = new GroupMethod();
	            // 开始读取数据 如果读到的数据不为空 则往里面读
	            
	            
	            boolean start = false;
	            boolean startSecond = false;
	            boolean startThird = false;
	            int pid = Integer.parseInt(p_id),pid2 = 1,pid3 = 1;
	            while ((strRead = br.readLine()) != null) {
	            	if(strRead.contains("class=\"bc-list-title bc-title bc-nowrap-ellipsis\"")){
	            		start = true;
	            		String firstRegular= "<h2[^>]*>([^<]*)</h2>";
	            		c_name = gMethod.regularGroup(firstRegular, strRead);
	            		//判断分类id是否已经存在，存在则不添加子分类，修改上级分类的采集页数
	            		sql = "insert into caiji_category (c_id,c_name,url,pid) select '"+c_id+"','"+c_name+"','"+strUrl+"','0' FROM DUAL WHERE NOT EXISTS(SELECT * FROM caiji_category WHERE c_id = '"+c_id+"');";
	            		stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
	            		ResultSet rs1 = (ResultSet) stmt.getGeneratedKeys();
	        			if(rs1.next()){  
	        				pid2 = rs1.getInt(1);
	    			    } 
	            	}
	            	if(strRead.contains("class=\"bc-pagination\"")){
	            		start = false;
//	            		System.out.println(strRead);
//	            		System.out.println("退出");
//	            		return;
	            		continue;
	            	}
	            	if(start){
	            		//二级菜单开始
	            		if(strRead.contains("class=\"bc-cate-name bc-nowrap-ellipsis\"")){
	            			startSecond = true; 
	            		}
	            		if(startSecond&&strRead.contains("<a")){
	            			c_name = gMethod.regularGroup(title, strRead);
	            			c_url = gMethod.regularGroup(hrefPattern, strRead);
	            			c_id = c_url.split("/")[4];
	            			pid2 = addSecond(c_id,c_url,c_name,pid);
	            			startSecond = false;
	            		}
	            		//三级菜单开始
	            		if(strRead.contains("class=\"bc-nowrap-ellipsis\"")){
	            			startThird = true;
	            		}
	            		if(startThird&&strRead.contains("<a")){
	            			System.out.println(strRead);
	            			c_name = gMethod.regularGroup(title, strRead);
	            			c_url = gMethod.regularGroup(hrefPattern, strRead);
	            			c_id = c_url.split("/")[4];
	            			pid3 = addThird(c_id,c_url,c_name,pid2);
	            			startThird = false;
	            		}
	            	}
	            }
	            if(sql.length()>1){
	            	stmt.execute(sql);
	            }
	            // 当读完数据后 记得关闭 BufferReader
	            br.close();
		    } 
            
        	
           
        } catch (IOException e) {
            // 如果出错 抛出异常
        	System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    

	private static int addSecond(String c_id, String c_url, String c_name,int pid) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        //判断分类id是否已经存在，存在则不添加子分类，修改上级分类的采集页数
        String existSql = "select * from caiji_category where c_id = " + c_id;
        ResultSet exist = (ResultSet) stmt.executeQuery(existSql);
        if(exist.next()){
        	String updateSql = "update caiji_category set catch_page = 20 where c_id = " + c_id;
        	System.out.println(updateSql);
        	stmt.executeUpdate(updateSql,Statement.RETURN_GENERATED_KEYS);
        }else{
        	String sql = "insert into caiji_category (c_id,c_name,url,pid,catch_page) select '"+c_id+"','"+c_name+"','"+c_url+"','"+pid+"',10 FROM DUAL WHERE NOT EXISTS(SELECT * FROM caiji_category WHERE c_id = '"+c_id+"');";
    		System.out.println(sql);
    		stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
    		ResultSet rs = (ResultSet) stmt.getGeneratedKeys(); 
			if(rs.next()){  
				pid = rs.getInt(1);
		    } 
        }
        return pid;
	}
	
	private static int addThird(String c_id, String c_url, String c_name,int pid) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UnsupportedEncodingException, IOException {
        String existSql = "select * from caiji_category where c_id = " + c_id;
        ResultSet exist = (ResultSet) stmt.executeQuery(existSql);
        String sql = null;
        if(exist.next()){
        	String updateSql = "update caiji_category set catch_page = 20 where c_id = " + c_id;
        	stmt.executeUpdate(updateSql,Statement.RETURN_GENERATED_KEYS);
        }else{
        	sql = "insert into caiji_category (c_id,c_name,url,pid,catch_page) select '"+c_id+"','"+c_name+"','"+c_url+"','"+pid+"',10 FROM DUAL WHERE NOT EXISTS(SELECT * FROM caiji_category WHERE c_id = '"+c_id+"');";
    		System.out.println(sql);
    		stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
    		ResultSet rs = (ResultSet) stmt.getGeneratedKeys(); 
			if(rs.next()){
				pid = rs.getInt(1);
		    } 
        }
		
		GroupMethod gMethod = new GroupMethod();
		URL url = new URL(c_url);
        InputStreamReader isr = new InputStreamReader(url.openStream(),
                "gb2312"); // 统一使用utf-8 编码模式
        BufferedReader br = new BufferedReader(isr);
        String hrefPattern= "href=\"([^\"]*)\"";
        String title = "<a[^>]*>([^<]*)</a>";
        String strRead = "";
        boolean start = false;
        while ((strRead = br.readLine()) != null) {
        	if(strRead.contains("class=\"sn-parent-title\"")){
        		start = true;
        	}
        	if(strRead.contains("</dl>")){
        		start = false;
        	}
        	if(start){
        		if(strRead.contains("<a")){
        			c_name = gMethod.regularGroup(title, strRead);
        			c_url = gMethod.regularGroup(hrefPattern, strRead);
        			c_id = c_url.split("/")[4];
        			if(c_name!=null&&c_name!=""){
        				sql = "insert into caiji_category (c_id,c_name,url,pid,catch_page) select '"+c_id+"','"+c_name+"','"+c_url+"','"+pid+"',10 FROM DUAL WHERE NOT EXISTS(SELECT * FROM caiji_category WHERE c_id = '"+c_id+"');";
            			System.out.println(sql);
        				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
        			}
        			
        		}
        	}
        }
		return pid;
	}


	/**
	 * 类目表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "caijiCategory")
	public ModelAndView caijiCategory(HttpServletRequest request) {
		return new ModelAndView("com/buss/caiji/caijiCategoryList");
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
	public void datagrid(CaijiCategoryEntity caijiCategory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CaijiCategoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, caijiCategory, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.caijiCategoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除类目表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CaijiCategoryEntity caijiCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		caijiCategory = systemService.getEntity(CaijiCategoryEntity.class, caijiCategory.getId());
		message = "类目表删除成功";
		try{
			caijiCategoryService.delete(caijiCategory);
			//是否开发一个删除部分类目的功能，能够同步删除月份产品汇总表，新增产品汇总表等，相对应类目的数据，以减轻数据量的大小
			//TODO
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "类目表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除类目表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "类目表删除成功";
		try{
			for(String id:ids.split(",")){
				CaijiCategoryEntity caijiCategory = systemService.getEntity(CaijiCategoryEntity.class, 
				Integer.parseInt(id)
				);
				caijiCategoryService.delete(caijiCategory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "类目表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加类目表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CaijiCategoryEntity caijiCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "类目表添加成功";
		try{
			caijiCategoryService.save(caijiCategory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "类目表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新类目表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CaijiCategoryEntity caijiCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "类目表更新成功";
		CaijiCategoryEntity t = caijiCategoryService.get(CaijiCategoryEntity.class, caijiCategory.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(caijiCategory, t);
			caijiCategoryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "类目表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 类目表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CaijiCategoryEntity caijiCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(caijiCategory.getId())) {
			caijiCategory = caijiCategoryService.getEntity(CaijiCategoryEntity.class, caijiCategory.getId());
			req.setAttribute("caijiCategoryPage", caijiCategory);
		}
		return new ModelAndView("com/buss/caiji/caijiCategory-add");
	}
	/**
	 * 类目表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CaijiCategoryEntity caijiCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(caijiCategory.getId())) {
			caijiCategory = caijiCategoryService.getEntity(CaijiCategoryEntity.class, caijiCategory.getId());
			req.setAttribute("caijiCategoryPage", caijiCategory);
		}
		return new ModelAndView("com/buss/caiji/caijiCategory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","caijiCategoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CaijiCategoryEntity caijiCategory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CaijiCategoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, caijiCategory, request.getParameterMap());
		List<CaijiCategoryEntity> caijiCategorys = this.caijiCategoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"类目表");
		modelMap.put(NormalExcelConstants.CLASS,CaijiCategoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("类目表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,caijiCategorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CaijiCategoryEntity caijiCategory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "类目表");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,CaijiCategoryEntity.class);
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
				List<CaijiCategoryEntity> listCaijiCategoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CaijiCategoryEntity.class,params);
				for (CaijiCategoryEntity caijiCategory : listCaijiCategoryEntitys) {
					caijiCategoryService.save(caijiCategory);
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
