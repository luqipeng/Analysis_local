package com.buss.wspro.controller;
import com.buss.attention.entity.MProductEntity;
import com.buss.attention.service.MProductServiceI;
import com.buss.caiji.entity.CaijiCategoryEntity;
import com.buss.caiji.util.Constant;
import com.buss.ws.entity.MWorkspaceEntity;
import com.buss.ws.service.MWorkspaceServiceI;
import com.buss.wspro.entity.WsProEntity;
import com.buss.wspro.service.WsProServiceI;

import java.sql.*;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.web.system.pojo.base.TSUser;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: m_ws_pro
 * @author onlineGenerator
 * @date 2016-07-13 20:11:24
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/wsProController")
public class WsProController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WsProController.class);

	@Autowired
	private WsProServiceI wsProService;
	@Autowired
	private MProductServiceI mProductService;
	@Autowired
	private MWorkspaceServiceI mWorkspaceService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * m_ws_pro列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "wsPro")
	public ModelAndView wsPro(HttpServletRequest request) {
		return new ModelAndView("com/buss/wspro/wsProList");
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
	public void datagrid(WsProEntity wsPro,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WsProEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wsPro, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user = ResourceUtil.getSessionUserName();
			List<MWorkspaceEntity> list = mWorkspaceService.findByQueryString("from MWorkspaceEntity where user_id = '" + user.getId() + "'");
			Integer ids[] = new Integer[list.size()];
			for(int i=0;i<list.size();i++){
				ids[i] = list.get(i).getId();
			}
			cq.in("wid",ids);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.wsProService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除m_ws_pro
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WsProEntity wsPro, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		wsPro = systemService.getEntity(WsProEntity.class, wsPro.getId());
		message = "m_ws_pro删除成功";
		try{
			wsProService.delete(wsPro);
			//同步删除服务器上
			syncWsPro(wsPro, false);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_ws_pro删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除m_ws_pro
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "m_ws_pro删除成功";
		try{
			for(String id:ids.split(",")){
				WsProEntity wsPro = systemService.getEntity(WsProEntity.class, 
				Integer.parseInt(id)
				);
				wsProService.delete(wsPro);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "m_ws_pro删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加m_ws_pro
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WsProEntity wsPro, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_ws_pro添加成功";
		try{
			MProductEntity mProduct = new MProductEntity();
			String name = request.getParameter("name");
			String img = request.getParameter("img");
			mProduct.setPid(wsPro.getPid());
			mProduct.setName(name);
			mProduct.setImg(img);
			wsProService.save(wsPro);
			mProductService.save(mProduct);
			//同步添加到服务器上
			syncWsPro(wsPro, true);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_ws_pro添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	private void syncWsPro(WsProEntity wsPro,boolean add){
		Connection con = null;
		ResultSet rs;
		Statement stmt;
		CaijiCategoryEntity category = new CaijiCategoryEntity();
		try{
			con = DriverManager.getConnection(Constant.url_service_product, Constant.user, Constant.service_password) ;
		}catch(SQLException se){
			System.out.println("数据库连接失败！");
			se.printStackTrace() ;
		}
		try {
			stmt = con.createStatement();
			if(add){//true:添加 ; false:删除
				stmt.execute("INSERT INTO m_ws_pro(wid, pid) VALUES ('"+wsPro.getWid()+"', '"+wsPro.getPid()+"')");
			}else{
				stmt.execute("delete from m_ws_pro where wid='"+wsPro.getWid()+"' and pid =  '"+wsPro.getPid()+"'");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 更新m_ws_pro
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WsProEntity wsPro, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_ws_pro更新成功";
		WsProEntity t = wsProService.get(WsProEntity.class, wsPro.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(wsPro, t);
			wsProService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "m_ws_pro更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * m_ws_pro新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WsProEntity wsPro, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wsPro.getId())) {
			wsPro = wsProService.getEntity(WsProEntity.class, wsPro.getId());
			req.setAttribute("wsProPage", wsPro);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		List<MWorkspaceEntity> wsList = systemService.findByQueryString("from MWorkspaceEntity where user_id='" + user.getId() + "'");
		req.setAttribute("wsList", wsList);
		return new ModelAndView("com/buss/wspro/wsPro-add");
	}

	/**
	 * m_ws_pro新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goJoin")
	public ModelAndView goJoin(WsProEntity wsPro, HttpServletRequest req) {
		String productId = req.getParameter("productId");
		String name = req.getParameter("name");
		String img = req.getParameter("img");
		if (StringUtil.isNotEmpty(wsPro.getId())) {
			wsPro = wsProService.getEntity(WsProEntity.class, wsPro.getId());
			req.setAttribute("wsProPage", wsPro);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		List<MWorkspaceEntity> wsList = systemService.findByQueryString("from MWorkspaceEntity where user_id='" + user.getId() + "'");
		req.setAttribute("wsList", wsList);
		req.setAttribute("productId", productId);
		req.setAttribute("name", name);
		req.setAttribute("img", img);
		return new ModelAndView("com/buss/wspro/wsPro-join");
	}


	/**
	 * 删除m_ws_pro
	 *
	 * @return
	 */
	@RequestMapping(params = "cancleJoin")
	@ResponseBody
	public AjaxJson cancleJoin(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String productId = req.getParameter("productId");
		TSUser user = ResourceUtil.getSessionUserName();
		List<MWorkspaceEntity> listWS = systemService.findByQueryString("from MWorkspaceEntity where user_id='" + user.getId() + "'");
		String ids = "";
		for(int i=0;i<listWS.size();i++){
			ids += listWS.get(i).getId()+",";
		}
		List<WsProEntity> wsList = systemService.findByQueryString("from WsProEntity where wid in ("+ids.substring(0,ids.length()-1)+") and pid = '"+productId+"'");

		try{
			for(WsProEntity wsPro:wsList) {
				wsProService.delete(wsPro);
//				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "m_ws_pro删除失败";
			throw new BusinessException(e.getMessage());
		}
		message = "m_ws_pro删除成功";
		j.setMsg(message);
		return j;
	}

	/**
	 * m_ws_pro编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WsProEntity wsPro, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wsPro.getId())) {
			wsPro = wsProService.getEntity(WsProEntity.class, wsPro.getId());
			req.setAttribute("wsProPage", wsPro);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		List<MWorkspaceEntity> wsList = systemService.findByQueryString("from MWorkspaceEntity where user_id='" + user.getId() + "'");
		req.setAttribute("wsList", wsList);
		return new ModelAndView("com/buss/wspro/wsPro-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","wsProController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(WsProEntity wsPro,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(WsProEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wsPro, request.getParameterMap());
		List<WsProEntity> wsPros = this.wsProService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"m_ws_pro");
		modelMap.put(NormalExcelConstants.CLASS,WsProEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("m_ws_pro列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,wsPros);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(WsProEntity wsPro,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "m_ws_pro");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,WsProEntity.class);
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
				List<WsProEntity> listWsProEntitys = ExcelImportUtil.importExcel(file.getInputStream(),WsProEntity.class,params);
				for (WsProEntity wsPro : listWsProEntitys) {
					wsProService.save(wsPro);
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
