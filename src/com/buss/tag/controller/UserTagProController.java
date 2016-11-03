package com.buss.tag.controller;
import com.buss.tag.entity.TagEntity;
import com.buss.tag.entity.UserTagProEntity;
import com.buss.tag.service.TagServiceI;
import com.buss.tag.service.UserTagProServiceI;
import java.util.List;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import java.io.Serializable;

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
 * @Description: user_tag_pro
 * @author onlineGenerator
 * @date 2015-09-23 20:02:14
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/userTagProController")
public class UserTagProController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserTagProController.class);

	@Autowired
	private UserTagProServiceI userTagProService;
	@Autowired
	private TagServiceI tagService;
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
	 * user_tag_pro列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "userTagPro")
	public ModelAndView userTagPro(HttpServletRequest request) {
		return new ModelAndView("com/buss/tag/userTagProList");
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
	public void datagrid(UserTagProEntity userTagPro,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(UserTagProEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, userTagPro, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.userTagProService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 删除user_tag_pro
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDelete")
	@ResponseBody
	public AjaxJson doDelete(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String tagId = request.getParameter("tagId");
		String productId = request.getParameter("productId");
		List<UserTagProEntity> userTagPros = userTagProService.findByQueryString("from UserTagProEntity where tagId="+tagId +" and productId="+productId);
//		UserTagProEntity userTagPro = systemService.getEntity(UserTagProEntity.class, Integer.parseInt(tagId));
		message = "user_tag_pro删除成功";
		try{
			if(userTagPros.size()>0){
				userTagProService.delete(userTagPros.get(0));
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "user_tag_pro删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 删除user_tag_pro
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(UserTagProEntity userTagPro, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		userTagPro = systemService.getEntity(UserTagProEntity.class, userTagPro.getId());
		message = "user_tag_pro删除成功";
		try{
			userTagProService.delete(userTagPro);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "user_tag_pro删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除user_tag_pro
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "user_tag_pro删除成功";
		try{
			for(String id:ids.split(",")){
				UserTagProEntity userTagPro = systemService.getEntity(UserTagProEntity.class, 
				Integer.parseInt(id)
				);
				userTagProService.delete(userTagPro);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "user_tag_pro删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加user_tag_pro
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(UserTagProEntity userTagPro, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "user_tag_pro添加成功";
		String tagName = request.getParameter("tagName");
		
		if(tagName != null && tagName != ""){
			TSUser user = ResourceUtil.getSessionUserName();
			TagEntity tag = new TagEntity();
			tag.setName(tagName);
			tag.setUserId(user.getId());
			Integer tagId = (Integer) tagService.save(tag);
			userTagPro.setTagId(tagId);
		}
		try{
			List <UserTagProEntity> list = userTagProService.findByQueryString("from UserTagProEntity where productId = "+userTagPro.getProductId()+" and tagId="+ userTagPro.getTagId());
			if(list.size()>0){
				message = "不能重复添加！";
			}else{
				userTagProService.save(userTagPro);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			message = "user_tag_pro添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新user_tag_pro
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(UserTagProEntity userTagPro, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "user_tag_pro更新成功";
		UserTagProEntity t = userTagProService.get(UserTagProEntity.class, userTagPro.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(userTagPro, t);
			userTagProService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "user_tag_pro更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * user_tag_pro新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(UserTagProEntity userTagPro, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(userTagPro.getId())) {
			userTagPro = userTagProService.getEntity(UserTagProEntity.class, userTagPro.getId());
			req.setAttribute("userTagProPage", userTagPro);
		}
		req.setAttribute("userTagProPage", userTagPro);
		return new ModelAndView("com/buss/tag/userTagPro-add");
	}
	/**
	 * user_tag_pro编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(UserTagProEntity userTagPro, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(userTagPro.getId())) {
			userTagPro = userTagProService.getEntity(UserTagProEntity.class, userTagPro.getId());
			req.setAttribute("userTagProPage", userTagPro);
		}
		List <UserTagProEntity> list = userTagProService.findByQueryString("from UserTagProEntity where productId = "+userTagPro.getProductId()+" and tagId="+ userTagPro.getTagId());
		if(list.size()>0){
			userTagPro = list.get(0);
		}
		req.setAttribute("userTagProPage", userTagPro);
		return new ModelAndView("com/buss/tag/userTagPro-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","userTagProController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(UserTagProEntity userTagPro,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(UserTagProEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, userTagPro, request.getParameterMap());
		List<UserTagProEntity> userTagPros = this.userTagProService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"user_tag_pro");
		modelMap.put(NormalExcelConstants.CLASS,UserTagProEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("user_tag_pro列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,userTagPros);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(UserTagProEntity userTagPro,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "user_tag_pro");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,UserTagProEntity.class);
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
				List<UserTagProEntity> listUserTagProEntitys = ExcelImportUtil.importExcel(file.getInputStream(),UserTagProEntity.class,params);
				for (UserTagProEntity userTagPro : listUserTagProEntitys) {
					userTagProService.save(userTagPro);
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
