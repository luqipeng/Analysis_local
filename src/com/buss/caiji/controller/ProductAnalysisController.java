package com.buss.caiji.controller;
import com.buss.caiji.entity.ProductAnalysisEntity;
import com.buss.caiji.service.ProductAnalysisServiceI;
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
 * @Description: product_analysis
 * @author onlineGenerator
 * @date 2015-12-09 11:18:21
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/productAnalysisController")
public class ProductAnalysisController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProductAnalysisController.class);

	@Autowired
	private ProductAnalysisServiceI productAnalysisService;
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
	 * product_analysis列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "productAnalysis")
	public ModelAndView productAnalysis(HttpServletRequest request) {
		return new ModelAndView("com/buss/com.buss/productAnalysisList");
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
	public void datagrid(ProductAnalysisEntity productAnalysis,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProductAnalysisEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, productAnalysis, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.productAnalysisService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除product_analysis
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ProductAnalysisEntity productAnalysis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		/*productAnalysis = systemService.getEntity(ProductAnalysisEntity.class, productAnalysis.getId());
		message = "product_analysis删除成功";
		try{
			productAnalysisService.delete(productAnalysis);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "product_analysis删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);*/
		return j;
	}
	
	/**
	 * 批量删除product_analysis
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "product_analysis删除成功";
		try{
			for(String id:ids.split(",")){
				ProductAnalysisEntity productAnalysis = systemService.getEntity(ProductAnalysisEntity.class, 
				id
				);
				productAnalysisService.delete(productAnalysis);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "product_analysis删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加product_analysis
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ProductAnalysisEntity productAnalysis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "product_analysis添加成功";
		try{
			productAnalysisService.save(productAnalysis);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "product_analysis添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新product_analysis
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ProductAnalysisEntity productAnalysis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "product_analysis更新成功";
		ProductAnalysisEntity t = (ProductAnalysisEntity) productAnalysisService.findHql("from ProductAnalysisEntity where P_ID=? and C_ID=?", productAnalysis.getPId(),productAnalysis.getCId()).get(0);
		try {
			MyBeanUtils.copyBeanNotNull2Bean(productAnalysis, t);
			productAnalysisService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "product_analysis更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * product_analysis新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ProductAnalysisEntity productAnalysis, HttpServletRequest req) {
		/*if (StringUtil.isNotEmpty(productAnalysis.getId())) {
			productAnalysis = productAnalysisService.getEntity(ProductAnalysisEntity.class, productAnalysis.getId());
			req.setAttribute("productAnalysisPage", productAnalysis);
		}*/
		return new ModelAndView("com/buss/com.buss/productAnalysis-add");
	}
	/**
	 * product_analysis编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ProductAnalysisEntity productAnalysis, HttpServletRequest req) {
		/*if (StringUtil.isNotEmpty(productAnalysis.getId())) {
			productAnalysis = productAnalysisService.getEntity(ProductAnalysisEntity.class, productAnalysis.getId());
			req.setAttribute("productAnalysisPage", productAnalysis);
		}*/
		return new ModelAndView("com/buss/com.buss/productAnalysis-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","productAnalysisController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ProductAnalysisEntity productAnalysis,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ProductAnalysisEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, productAnalysis, request.getParameterMap());
		List<ProductAnalysisEntity> productAnalysiss = this.productAnalysisService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"product_analysis");
		modelMap.put(NormalExcelConstants.CLASS,ProductAnalysisEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("product_analysis列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,productAnalysiss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ProductAnalysisEntity productAnalysis,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "product_analysis");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,ProductAnalysisEntity.class);
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
				List<ProductAnalysisEntity> listProductAnalysisEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ProductAnalysisEntity.class,params);
				for (ProductAnalysisEntity productAnalysis : listProductAnalysisEntitys) {
					productAnalysisService.save(productAnalysis);
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
