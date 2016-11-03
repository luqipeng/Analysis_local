package com.buss.compare.controller;
import com.buss.caiji.entity.StoreProductEntity;
import com.buss.caiji.service.StoreProductServiceI;
import com.buss.compare.entity.CommodityManageEntity;
import com.buss.compare.entity.SkuProductEntity;
import com.buss.compare.service.CommodityManageServiceI;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buss.compare.service.SkuProductServiceI;
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
 * @Description: commodity_manage
 * @author onlineGenerator
 * @date 2016-09-17 18:36:59
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/commodityManageController")
public class CommodityManageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CommodityManageController.class);

	@Autowired
	private CommodityManageServiceI commodityManageService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SkuProductServiceI skuProductService;
	@Autowired
	private StoreProductServiceI storeProductService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * commodity_manage列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "commodityManage")
	public ModelAndView commodityManage(HttpServletRequest request) {
		return new ModelAndView("com/buss/compare/commodityManageList");
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
	public void datagrid(CommodityManageEntity commodityManage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommodityManageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityManage, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.commodityManageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除commodity_manage
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CommodityManageEntity commodityManage, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		commodityManage = systemService.getEntity(CommodityManageEntity.class, commodityManage.getProductId());
		message = "commodity_manage删除成功";
		try{
			commodityManageService.delete(commodityManage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_manage删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除commodity_manage
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "commodity_manage删除成功";
		try{
			for(String id:ids.split(",")){
				CommodityManageEntity commodityManage = systemService.getEntity(CommodityManageEntity.class, 
				Integer.parseInt(id)
				);
				commodityManageService.delete(commodityManage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_manage删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加commodity_manage
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CommodityManageEntity commodityManage, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "commodity_manage添加成功";
		try{
			commodityManageService.save(commodityManage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_manage添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新commodity_manage
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CommodityManageEntity commodityManage, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "commodity_manage更新成功";
		CommodityManageEntity t = commodityManageService.get(CommodityManageEntity.class, commodityManage.getProductId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(commodityManage, t);
			commodityManageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "commodity_manage更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * commodity_manage新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CommodityManageEntity commodityManage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commodityManage.getProductId())) {
			commodityManage = commodityManageService.getEntity(CommodityManageEntity.class, commodityManage.getProductId());
			req.setAttribute("commodityManagePage", commodityManage);
		}
		return new ModelAndView("com/buss/compare/commodityManage-add");
	}
	/**
	 * commodity_manage编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CommodityManageEntity commodityManage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commodityManage.getProductId())) {
			commodityManage = commodityManageService.getEntity(CommodityManageEntity.class, commodityManage.getProductId());
			req.setAttribute("commodityManagePage", commodityManage);
		}
		return new ModelAndView("com/buss/compare/commodityManage-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","commodityManageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CommodityManageEntity commodityManage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CommodityManageEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityManage, request.getParameterMap());
		List<CommodityManageEntity> commodityManages = this.commodityManageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"commodity_manage");
		modelMap.put(NormalExcelConstants.CLASS,CommodityManageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("commodity_manage列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,commodityManages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CommodityManageEntity commodityManage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "commodity_manage");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,CommodityManageEntity.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA,null);
		return TemplateExcelConstants.JEECG_TEMPLATE_EXCEL_VIEW;
	}


	/**
	 * 产品关联表导入
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setHeadRows(1);
			params.setNeedSave(true);
			String productid = "";
			try {
				List<CommodityManageEntity> listCommodityManageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CommodityManageEntity.class,params);
				for (CommodityManageEntity commodityManage : listCommodityManageEntitys) {
					if(!commodityManage.getPlatformLogo().equals("") && commodityManage.getPlatformLogo().split("-").length>0){
						productid = commodityManage.getPlatformLogo().split("-")[0];
						commodityManage.setProductId(productid);
					}
					//更新SKU表
					List<SkuProductEntity> list = skuProductService.findHql("from SkuProductEntity where productId='" + productid + "'");
					if (list.size()==0) {
						SkuProductEntity sku = new SkuProductEntity();
						sku.setProductId(productid);
						sku.setProductName(commodityManage.getProductName());
						sku.setSku(commodityManage.getSku());
						sku.setStoreName(commodityManage.getStoreName());
						skuProductService.save(sku);
					}else{
						SkuProductEntity sku = list.get(0);
						sku.setProductId(productid);
						sku.setProductName(commodityManage.getProductName());
						sku.setSku(commodityManage.getSku());
						sku.setStoreName(commodityManage.getStoreName());
						skuProductService.updateEntitie(sku);
					}
					commodityManage.setCode(commodityManage.getCode().replace(".0",""));
					List<CommodityManageEntity> cList = commodityManageService.findHql("from CommodityManageEntity where productId='" + productid + "'");
					if(cList.size()==0){
						commodityManageService.save(commodityManage);
					}
					//更新主表 store_product
					List<StoreProductEntity> storeList = storeProductService.findHql("from StoreProductEntity where productId='" + productid + "'");
					if(storeList.size()>0){
						//更新
						StoreProductEntity storeProductEntity = storeList.get(0);
						storeProductEntity.setProductName(commodityManage.getProductName());
						storeProductEntity.setSku(commodityManage.getSku());
						storeProductService.updateEntitie(storeProductEntity);
					}else{
						//新增
						StoreProductEntity storeProductEntity = new StoreProductEntity();
						storeProductEntity.setProductId(productid);
						storeProductEntity.setProductName(commodityManage.getProductName());
						storeProductEntity.setSku(commodityManage.getSku());
						storeProductEntity.setFirstTime(df.format(new Date()));
						storeProductService.save(storeProductEntity);
					}
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
