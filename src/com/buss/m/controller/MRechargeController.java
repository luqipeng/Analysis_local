package com.buss.m.controller;
import com.buss.m.entity.*;
import com.buss.m.service.MCountryServiceI;
import com.buss.m.service.MExpressDetailServiceI;
import com.buss.m.service.MExpressServiceI;
import com.buss.m.service.MRechargeServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.web.system.pojo.base.TSFunction;
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
 * @Description: m_recharge
 * @author onlineGenerator
 * @date 2016-05-13 09:55:58
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/mRechargeController")
public class MRechargeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MRechargeController.class);

	@Autowired
	private MExpressDetailServiceI mExpressDetailService;
	@Autowired
	private MRechargeServiceI mRechargeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private MExpressServiceI mExpressService;
	@Autowired
	private MCountryServiceI mCountryService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * m_recharge列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mRecharge")
	public ModelAndView mRecharge(HttpServletRequest request) {
		return new ModelAndView("com/buss/m/mRechargeList");
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
	public void datagrid(MExpressDetailEntity mExpressDetail,MExpressEntity mExpress,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MExpressDetailEntity.class, dataGrid);
		String cnName = request.getParameter("cnName");
		String expressType = request.getParameter("expressType");
		int weight  = Integer.parseInt(request.getParameter("weight"));
//		mRecharge.setCountry(country);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mExpressDetail, request.getParameterMap());
		try{
		//自定义追加查询条件
			if(cnName != null && !"".equals(cnName)){
				List<MCountryEntity> ts = mCountryService.findByProperty(MCountryEntity.class,"cnName",cnName);
				if(!ts.isEmpty()){
					int countryId = ts.get(0).getId();
					cq.eq("countryId",countryId);
				}
			}
			if(expressType != null && !"".equals(expressType)){
				cq.add(Restrictions.sqlRestriction(" express_id in (select id from m_express where express_type = '" + expressType + "')"));
			}
//			if(weight != null && !"".equals(weight)){
//				cq.eq("weight", new Double(weight));
//			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mRechargeService.getDataGridReturn(cq, true);
		dataGrid.setField("id,name,country,weight,amount,baf,currency,predictTime,canGoods,remark,packageCount");
//		dataGrid.setField("name,country,weight,amount,baf,currency,predictTime,canGoods,remark");
		List<MExpressDetailEntity> list = dataGrid.getResults();
		List<MRechargeEntity> poList = new ArrayList<MRechargeEntity>();
		for(MExpressDetailEntity entity:list){
			MRechargeEntity po = new MRechargeEntity();
			po.setName(entity.getExpressName());
			po.setCountry(entity.getCountryName());
			MCountryEntity country = mCountryService.get(MCountryEntity.class, entity.getCountryId());
			MExpressEntity express = mExpressService.get(MExpressEntity.class, entity.getExpressId());

			//判断物流类型
			if(expressType!=null && expressType!="" && !expressType.equals(express.getExpressType())){
				continue;
			}
			//判断是否超重
			String limitWeight = express.getLimitWeight();
			if(limitWeight.contains("国")){
				//TODO 复杂的判断
				limitWeight = "2";
			}else if(limitWeight.contains("KG")){
				limitWeight = limitWeight.replace("KG","");
			}
			int lw = Integer.parseInt(limitWeight)*1000;

			if(weight<=lw){
				//包裹数量默认一个
				po.setPackageCount(1);
				po.setAmount(weight * entity.getDeliveryCharge() / 1000 + entity.getPayCharge());
			}else{
				continue;
//				int num = (int) Math.ceil(weight/lw);
//				int w = weight-lw*num;
//				po.setPackageCount(num+1);
//				po.setAmount((lw * entity.getDeliveryCharge() / 1000 + entity.getPayCharge())*num + (w*entity.getDeliveryCharge()/1000 + entity.getPayCharge() ));
			}

			po.setId(entity.getId());

			po.setPredictTime(express.getAging());
			po.setWeight(weight+"克");//结算重量 = 输入重量
//
			po.setCanGoods(express.getIsAcceptEle());

//			po.setCountry(mCountryService.get(MCountryEntity.class,entity.getCountryId()));
//			po.setExpress(mExpressService.get(MExpressEntity.class, entity.getExpressId()));
			poList.add(po);
		}
		dataGrid.setResults(poList);
		TagUtil.datagrid(response, dataGrid);
	}

	//备份
	/*public void datagrid(MRechargeEntity mRecharge,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MRechargeEntity.class, dataGrid);
		String country = request.getParameter("enName");
		String weight = request.getParameter("weight");
//		mRecharge.setCountry(country);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mRecharge, request.getParameterMap());
		try{
			//自定义追加查询条件
			if(country != null && !"".equals(country)){
				cq.like("country", "%"+country+"%");
			}
			if(weight != null && !"".equals(weight)){
				cq.eq("weight", new Double(weight));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mRechargeService.getDataGridReturn(cq, true);
		dataGrid.setField("name,country,weight,amount,baf,currency,predictTime,canGoods,remark");
		TagUtil.datagrid(response, dataGrid);
	}*/

	/**
	 * AJAX 示例下拉框
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getRecharge")
	@ResponseBody
	public AjaxJson getRecharge(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String enName = StringUtil.getEncodePra(req.getParameter("enName"));
		String result = "";
		CriteriaQuery cq = new CriteriaQuery(MRechargeEntity.class);
		cq.eq("MRechargeEntity.enName", enName);
		cq.add();
		List<MRechargeEntity> recharges = systemService.getListByCriteriaQuery(cq ,false);
		if (recharges.size() > 0) {
			for (MRechargeEntity recharge : recharges) {
				result += "<tr><td>"+recharge.getName()+"</td><td>"+recharge.getCountry()+"</td><td>"+recharge.getWeight()+"</td><td>" +
						recharge.getWeight()+"</td><td>"+recharge.getAmount()+"</td><td>"+recharge.getBaf()+"</td><td>"+recharge.getCurrency()+"</td></tr>";
			}
		} else {
			result += "没有子项目!";
		}
		/*List<TSFunction> functions = systemService.getListByCriteriaQuery(cq, false);
		if (functions.size() > 0) {
			for (TSFunction function : functions) {
				floor += "<input type=\"checkbox\"  name=\"floornum\" id=\"floornum\" value=\"" + function.getId() + "\">" + function.getFunctionName() + "&nbsp;&nbsp;";
			}
		} else {
			floor += "没有子项目!";
		}*/

		j.setMsg(result);
		return j;
	}

	/**
	 * 删除m_recharge
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MRechargeEntity mRecharge, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		mRecharge = systemService.getEntity(MRechargeEntity.class, mRecharge.getId());
		message = "m_recharge删除成功";
		try{
			mRechargeService.delete(mRecharge);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_recharge删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除m_recharge
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "m_recharge删除成功";
		try{
			for(String id:ids.split(",")){
				MRechargeEntity mRecharge = systemService.getEntity(MRechargeEntity.class, 
				Integer.parseInt(id)
				);
				mRechargeService.delete(mRecharge);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "m_recharge删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加m_recharge
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MRechargeEntity mRecharge, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_recharge添加成功";
		try{
			mRechargeService.save(mRecharge);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_recharge添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新m_recharge
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MRechargeEntity mRecharge, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_recharge更新成功";
		MRechargeEntity t = mRechargeService.get(MRechargeEntity.class, mRecharge.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(mRecharge, t);
			mRechargeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "m_recharge更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * m_recharge新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MRechargeEntity mRecharge, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mRecharge.getId())) {
			mRecharge = mRechargeService.getEntity(MRechargeEntity.class, mRecharge.getId());
			req.setAttribute("mRechargePage", mRecharge);
		}
		return new ModelAndView("com/buss/m/mRecharge-add");
	}
	/**
	 * m_recharge编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MExpressDetailEntity entity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(entity.getId())) {
			entity = mExpressDetailService.getEntity(MExpressDetailEntity.class, entity.getId());



			MRechargeEntity po = new MRechargeEntity();
			po.setName(entity.getExpressName());
			po.setCountry(entity.getCountryName());
			MCountryEntity country = mCountryService.get(MCountryEntity.class, entity.getCountryId());
			MExpressEntity express = mExpressService.get(MExpressEntity.class, entity.getExpressId());
			po.setId(entity.getId());
			po.setAmount(entity.getDeliveryCharge());
			po.setPredictTime(express.getAging());
//
			po.setCanGoods(express.getTakingExpress());

//			po.setCountry(mCountryService.get(MCountryEntity.class,entity.getCountryId()));
//			po.setExpress(mExpressService.get(MExpressEntity.class, entity.getExpressId()));

			req.setAttribute("mRechargePage", po);
		}
		return new ModelAndView("com/buss/m/mRecharge-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","mRechargeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MRechargeEntity mRecharge,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MRechargeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mRecharge, request.getParameterMap());
		List<MRechargeEntity> mRecharges = this.mRechargeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"m_recharge");
		modelMap.put(NormalExcelConstants.CLASS,MRechargeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("m_recharge列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,mRecharges);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MRechargeEntity mRecharge,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "m_recharge");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,MRechargeEntity.class);
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
				List<MRechargeEntity> listMRechargeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),MRechargeEntity.class,params);
				for (MRechargeEntity mRecharge : listMRechargeEntitys) {
					mRechargeService.save(mRecharge);
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
