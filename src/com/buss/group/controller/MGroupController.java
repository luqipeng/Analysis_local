package com.buss.group.controller;
import com.buss.caiji.entity.StoreEntity;
import com.buss.caiji.service.StoreServiceI;
import com.buss.compare.entity.SkuProductEntity;
import com.buss.compare.service.SkuProductServiceI;
import com.buss.group.entity.MGroupEntity;
import com.buss.group.service.MGroupServiceI;
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
 * @Description: m_group
 * @author onlineGenerator
 * @date 2016-09-26 14:09:27
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/mGroupController")
public class MGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MGroupController.class);

	@Autowired
	private MGroupServiceI mGroupService;
	@Autowired
	private StoreServiceI storeServiceI;
	@Autowired
	private SkuProductServiceI skuProductService;
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
	 * m_group列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mGroup")
	public ModelAndView mGroup(HttpServletRequest request) {
		return new ModelAndView("com/buss/group/mGroupList");
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
	public void datagrid(MGroupEntity mGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mGroup, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除m_group
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MGroupEntity mGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		mGroup = systemService.getEntity(MGroupEntity.class, mGroup.getId());
		message = "m_group删除成功";
		try{
			mGroupService.delete(mGroup);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_group删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除m_group
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "m_group删除成功";
		try{
			for(String id:ids.split(",")){
				MGroupEntity mGroup = systemService.getEntity(MGroupEntity.class, 
				Integer.parseInt(id)
				);
				mGroupService.delete(mGroup);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "m_group删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加m_group
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MGroupEntity mGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_group添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			mGroup.setUserId(user.getId());

			//判断产品ID是否存在
			List<SkuProductEntity> skuList = skuProductService.findByProperty(SkuProductEntity.class,"productId",mGroup.getProductId());
			if(skuList.size()==0){
				j.setMsg("产品ID不存在");
				j.setSuccess(false);
				return j;
			}else{
				mGroup.setProductName(skuList.get(0).getProductName());
			}
			mGroupService.save(mGroup);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_group添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新m_group
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MGroupEntity mGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_group更新成功";
		MGroupEntity t = mGroupService.get(MGroupEntity.class, mGroup.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(mGroup, t);
			//判断产品ID是否存在
			List<SkuProductEntity> skuList = skuProductService.findByProperty(SkuProductEntity.class,"productId",mGroup.getProductId());
			if(skuList.size()==0){
				j.setMsg("产品ID不存在");
				j.setSuccess(false);
				return j;
			}else{
				t.setProductName(skuList.get(0).getProductName());
			}
			mGroupService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "m_group更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * m_group新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MGroupEntity mGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mGroup.getId())) {
			mGroup = mGroupService.getEntity(MGroupEntity.class, mGroup.getId());
			req.setAttribute("mGroupPage", mGroup);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		if(user.getUserName().equals("admin")){
			req.setAttribute("disabled", true);
		}else{
			req.setAttribute("disabled", false);
		}
		List<StoreEntity> storeList = storeServiceI.loadAll(StoreEntity.class);
		req.setAttribute("storeList", storeList);
		return new ModelAndView("com/buss/group/mGroup-add");
	}
	/**
	 * m_group编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MGroupEntity mGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mGroup.getId())) {
			mGroup = mGroupService.getEntity(MGroupEntity.class, mGroup.getId());
			req.setAttribute("mGroupPage", mGroup);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		if(user.getUserName().equals("admin")){
			req.setAttribute("disabled", true);
		}else{
			req.setAttribute("disabled", false);
		}
		List<StoreEntity> storeList = storeServiceI.loadAll(StoreEntity.class);
		req.setAttribute("storeList", storeList);
		return new ModelAndView("com/buss/group/mGroup-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","mGroupController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MGroupEntity mGroup,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MGroupEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mGroup, request.getParameterMap());
		List<MGroupEntity> mGroups = this.mGroupService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"m_group");
		modelMap.put(NormalExcelConstants.CLASS,MGroupEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("m_group列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,mGroups);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MGroupEntity mGroup,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "m_group");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,MGroupEntity.class);
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
				List<MGroupEntity> listMGroupEntitys = ExcelImportUtil.importExcel(file.getInputStream(),MGroupEntity.class,params);
				for (MGroupEntity mGroup : listMGroupEntitys) {
					mGroupService.save(mGroup);
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
