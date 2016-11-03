package com.buss.keyword.controller;
import com.buss.keyword.entity.MKeywordEntity;
import com.buss.keyword.service.MKeywordServiceI;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buss.ws.entity.MWorkspaceEntity;
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
 * @Description: m_keyword
 * @author onlineGenerator
 * @date 2016-07-13 20:11:37
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/mKeywordController")
public class MKeywordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MKeywordController.class);

	@Autowired
	private MKeywordServiceI mKeywordService;
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
	 * m_keyword列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mKeyword")
	public ModelAndView mKeyword(HttpServletRequest request) {

		String workspace = "";
		List<MWorkspaceEntity> wsList = systemService.getList(MWorkspaceEntity.class);
		for (MWorkspaceEntity ws : wsList) {
			if (workspace.length() > 0) {
				workspace += ",";
			}
			workspace += ws.getWorkName() + "_" + ws.getId();
		}
		request.setAttribute("workspaceReplace", workspace);
		return new ModelAndView("com/buss/keyword/mKeywordList");
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
	public void datagrid(MKeywordEntity mKeyword,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MKeywordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mKeyword, request.getParameterMap());
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			cq.eq("userId",user.getId());
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mKeywordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除m_keyword
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MKeywordEntity mKeyword, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		mKeyword = systemService.getEntity(MKeywordEntity.class, mKeyword.getId());
		message = "m_keyword删除成功";
		try{
			mKeywordService.delete(mKeyword);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_keyword删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除m_keyword
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "m_keyword删除成功";
		try{
			for(String id:ids.split(",")){
				MKeywordEntity mKeyword = systemService.getEntity(MKeywordEntity.class, 
				Integer.parseInt(id)
				);
				mKeywordService.delete(mKeyword);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "m_keyword删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加m_keyword
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MKeywordEntity mKeyword, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_keyword添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			mKeyword.setUserId(user.getId());
			mKeywordService.save(mKeyword);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_keyword添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新m_keyword
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MKeywordEntity mKeyword, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_keyword更新成功";
		MKeywordEntity t = mKeywordService.get(MKeywordEntity.class, mKeyword.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(mKeyword, t);
			mKeywordService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "m_keyword更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * m_keyword新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MKeywordEntity mKeyword, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mKeyword.getId())) {
			mKeyword = mKeywordService.getEntity(MKeywordEntity.class, mKeyword.getId());
			req.setAttribute("mKeywordPage", mKeyword);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		List<MWorkspaceEntity> wsList = systemService.findByQueryString("from MWorkspaceEntity where user_id='"+user.getId()+"'");
		req.setAttribute("wsList", wsList);
		return new ModelAndView("com/buss/keyword/mKeyword-add");
	}
	/**
	 * m_keyword编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MKeywordEntity mKeyword, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mKeyword.getId())) {
			mKeyword = mKeywordService.getEntity(MKeywordEntity.class, mKeyword.getId());
			req.setAttribute("mKeywordPage", mKeyword);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		List<MWorkspaceEntity> wsList = systemService.findByQueryString("from MWorkspaceEntity where user_id='" + user.getId() + "'");
		req.setAttribute("wsList", wsList);
		return new ModelAndView("com/buss/keyword/mKeyword-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","mKeywordController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MKeywordEntity mKeyword,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MKeywordEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mKeyword, request.getParameterMap());
		List<MKeywordEntity> mKeywords = this.mKeywordService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"m_keyword");
		modelMap.put(NormalExcelConstants.CLASS,MKeywordEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("m_keyword列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,mKeywords);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MKeywordEntity mKeyword,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "m_keyword");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,MKeywordEntity.class);
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
				List<MKeywordEntity> listMKeywordEntitys = ExcelImportUtil.importExcel(file.getInputStream(),MKeywordEntity.class,params);
				for (MKeywordEntity mKeyword : listMKeywordEntitys) {
					mKeywordService.save(mKeyword);
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
