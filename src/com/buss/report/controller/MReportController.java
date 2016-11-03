package com.buss.report.controller;
import com.buss.caiji.method.ConnectionParam;
import com.buss.caiji.method.ConnectionPool;
import com.buss.caiji.util.Constant;
import com.buss.report.entity.MReportEntity;
import com.buss.report.service.MReportServiceI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
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
 * @Description: m_report
 * @author onlineGenerator
 * @date 2016-07-02 11:55:58
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/mReportController")
public class MReportController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MReportController.class);

	@Autowired
	private MReportServiceI mReportService;
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
	 * m_report列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mReport")
	public ModelAndView mReport(HttpServletRequest request) {
		return new ModelAndView("com/buss/report/mReportList");
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
	public void datagrid(MReportEntity mReport,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MReportEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mReport, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mReportService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除m_report
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MReportEntity mReport, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		mReport = systemService.getEntity(MReportEntity.class, mReport.getId());
		message = "m_report删除成功";
		try{
			mReportService.delete(mReport);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_report删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除m_report
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "m_report删除成功";
		try{
			for(String id:ids.split(",")){
				MReportEntity mReport = systemService.getEntity(MReportEntity.class, 
				Integer.parseInt(id)
				);
				mReportService.delete(mReport);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "m_report删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加m_report
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MReportEntity mReport, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_report添加成功";
		try{
			mReportService.save(mReport);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_report添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/*
	 * 统计销售
	 */
	@RequestMapping(params = "statistics")
	public void statistics(){
		try {
			doStatistics(null);
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新m_report
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doStatistics")
	@ResponseBody
	public AjaxJson doStatistics(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "销售统计成功";
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
		Calendar calendar = Calendar.getInstance();

		DecimalFormat ddf = new DecimalFormat("0.00");
		try {
			// 本地数据库
			ConnectionParam param_local = new ConnectionParam();
			param_local.setDriver(Constant.driver);
			param_local.setPassword(Constant.password);
			param_local.setUser(Constant.user);
			param_local.setUrl(Constant.url_local);
			ConnectionPool connectionPool = new ConnectionPool(param_local);
			try {
				connectionPool.createPool();
			} catch (Exception e) {
				logger.error(e);
			}
			Connection conn = connectionPool.getConnection();
			Statement st = conn.createStatement();

			//只保留一周数据
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			st.execute("delete from m_report where date < '" + df.format(calendar.getTime()) + "'");

			Calendar calendar1 = Calendar.getInstance();
			calendar1.add(Calendar.DAY_OF_MONTH, -1);
			String tableName = "product_"+df.format(calendar1.getTime());
			ResultSet rs = st.executeQuery("SELECT ROUND(sum(product_price*product_orders_today),2),category_id,sum(product_orders_today) FROM `" + tableName + "` where product_orders_today >0 GROUP BY category_id;  ");
			MReportEntity mReport;
			while(rs.next()){
				mReport = new MReportEntity();
				mReport.setCId(rs.getString(2));
				mReport.setDate(calendar1.getTime());
				mReport.setTotalCount(rs.getInt(3));
				mReport.setTotalMoney(ddf.format(rs.getDouble(1)) + "");
				mReport.setUnitPrice(ddf.format(rs.getDouble(1) / rs.getInt(3)) + "");
				mReportService.save(mReport);
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "销售统计失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新m_report
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MReportEntity mReport, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_report更新成功";
		MReportEntity t = mReportService.get(MReportEntity.class, mReport.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(mReport, t);
			mReportService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "m_report更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * m_report新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MReportEntity mReport, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mReport.getId())) {
			mReport = mReportService.getEntity(MReportEntity.class, mReport.getId());
			req.setAttribute("mReportPage", mReport);
		}
		return new ModelAndView("com/buss/report/mReport-add");
	}
	/**
	 * m_report编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MReportEntity mReport, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mReport.getId())) {
			mReport = mReportService.getEntity(MReportEntity.class, mReport.getId());
			req.setAttribute("mReportPage", mReport);
		}
		return new ModelAndView("com/buss/report/mReport-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","mReportController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MReportEntity mReport,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MReportEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mReport, request.getParameterMap());
		List<MReportEntity> mReports = this.mReportService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"m_report");
		modelMap.put(NormalExcelConstants.CLASS,MReportEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("m_report列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,mReports);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MReportEntity mReport,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "m_report");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,MReportEntity.class);
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
				List<MReportEntity> listMReportEntitys = ExcelImportUtil.importExcel(file.getInputStream(),MReportEntity.class,params);
				for (MReportEntity mReport : listMReportEntitys) {
					mReportService.save(mReport);
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
