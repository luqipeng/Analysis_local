package com.buss.m.controller;
import com.buss.m.entity.MCountryEntity;
import com.buss.m.entity.MExpressDetailEntity;
import com.buss.m.entity.MExpressEntity;
import com.buss.m.service.MCountryServiceI;
import com.buss.m.service.MExpressDetailServiceI;

import java.io.FileInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buss.m.service.MExpressServiceI;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
 * @Description: m_express_detail
 * @author onlineGenerator
 * @date 2016-05-16 11:35:32
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/mExpressDetailController")
public class MExpressDetailController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MExpressDetailController.class);

	@Autowired
	private MExpressDetailServiceI mExpressDetailService;
	@Autowired
	private MExpressServiceI mExpressService;
	@Autowired
	private MCountryServiceI mCountryService;
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
	 * m_express_detail列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mExpressDetail")
	public ModelAndView mExpressDetail(HttpServletRequest request) {
		return new ModelAndView("com/buss/m/mExpressDetailList");
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
	public void datagrid(MExpressDetailEntity mExpressDetail,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MExpressDetailEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mExpressDetail, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mExpressDetailService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除m_express_detail
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MExpressDetailEntity mExpressDetail, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		mExpressDetail = systemService.getEntity(MExpressDetailEntity.class, mExpressDetail.getId());
		message = "m_express_detail删除成功";
		try{
			mExpressDetailService.delete(mExpressDetail);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_express_detail删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除m_express_detail
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "m_express_detail删除成功";
		try{
			for(String id:ids.split(",")){
				MExpressDetailEntity mExpressDetail = systemService.getEntity(MExpressDetailEntity.class, 
				Integer.parseInt(id)
				);
				mExpressDetailService.delete(mExpressDetail);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "m_express_detail删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加m_express_detail
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MExpressDetailEntity mExpressDetail, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_express_detail添加成功";
		try{
			mExpressDetailService.save(mExpressDetail);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_express_detail添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新m_express_detail
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MExpressDetailEntity mExpressDetail, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_express_detail更新成功";
		MExpressDetailEntity t = mExpressDetailService.get(MExpressDetailEntity.class, mExpressDetail.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(mExpressDetail, t);
			mExpressDetailService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "m_express_detail更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * m_express_detail新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MExpressDetailEntity mExpressDetail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mExpressDetail.getId())) {
			mExpressDetail = mExpressDetailService.getEntity(MExpressDetailEntity.class, mExpressDetail.getId());
			req.setAttribute("mExpressDetailPage", mExpressDetail);
		}
		return new ModelAndView("com/buss/m/mExpressDetail-add");
	}
	/**
	 * m_express_detail编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MExpressDetailEntity mExpressDetail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mExpressDetail.getId())) {
			mExpressDetail = mExpressDetailService.getEntity(MExpressDetailEntity.class, mExpressDetail.getId());
			req.setAttribute("mExpressDetailPage", mExpressDetail);
		}
		return new ModelAndView("com/buss/m/mExpressDetail-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","mExpressDetailController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MExpressDetailEntity mExpressDetail,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MExpressDetailEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mExpressDetail, request.getParameterMap());
		List<MExpressDetailEntity> mExpressDetails = this.mExpressDetailService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"m_express_detail");
		modelMap.put(NormalExcelConstants.CLASS,MExpressDetailEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("m_express_detail列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,mExpressDetails);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MExpressDetailEntity mExpressDetail,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "m_express_detail");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,MExpressDetailEntity.class);
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
			/*ImportParams params = new ImportParams();
			params.setTitleRows(7);
			params.setHeadRows(3);
			params.setSheetNum(3);
			params.setNeedSave(true);*/



			try {
				//开始
				// 将传入的File构造为FileInputStream;
				// // 得到工作表
				HSSFWorkbook book = new HSSFWorkbook(file.getInputStream());
				// // 得到第一页
				List<MExpressEntity> mExpressList = this.mExpressService.loadAll(MExpressEntity.class);
				String KEYWORD = "";
				for(MExpressEntity mExpress:mExpressList){
					String expressName = mExpress.getName().split("\n")[0];
					int expressId = mExpress.getId();
					switch (expressId){
						case 1:KEYWORD = "续重(每500G)\n" +
								"元（RMB）/500G";
						break;
						case 2:KEYWORD = "挂号服务费\n" +
								"元（RMB）/包裹";
							break;
						//new excel
						case 14:KEYWORD = "操作费";
							break;
						case 15:
						case 16:
							continue;
						case 17:KEYWORD = "挂号费(RMB/票）";
							break;
						case 18:KEYWORD = "挂号费(RMB/票)";
							break;
						case 19:KEYWORD = "挂号费（RMB/票）";
							break;
						default:KEYWORD = "挂号服务费\n" +
								"元（RMB）/包裹";
							break;
					}
					int index = book.getSheetIndex(expressName);
					if(index<0){
						continue;
					}
					HSSFSheet sheet = book.getSheetAt(index);

					Iterator<Row> row = sheet.rowIterator();
					/**
					 * 标题解析
					 */
					// 得到第一行，也就是标题行
					Row title = row.next();
					// 得到第一行的所有列
					Iterator<Cell> cellTitle = title.cellIterator();
					// 将标题的文字内容放入到一个map中。
					Map titlemap = new HashMap();
					// 从标题第一列开始
					/*int i = 0;
					// 循环标题所有的列
					while (cellTitle.hasNext()) {
						Cell cell = cellTitle.next();
						String value = cell.getStringCellValue();
						// 还是把表头trim一下
						value = value.trim();
						titlemap.put(i, value);
						i = i + 1;
					}*/
					boolean begin = false;
					MExpressDetailEntity mExpressDetail;
					while (row.hasNext()) {
						// 标题下的第一行
						Row rown = row.next();
						// 行的所有列
						Iterator<Cell> cellbody = rown.cellIterator();
						// 行的所有列
						int k = 0;
						//特殊处理 E邮宝标准价 E包裹 E特快
						if(expressId==14&&cellbody.hasNext()&&begin){
							mExpressDetail = new MExpressDetailEntity();

							String enName = "";
							String enShort = "";
							String cnName = "";
							String firstWeight = "";
							String deliveryCharge = "";
							//挂号费
							String payCharge = "";
							//操作费
							String editCharge = "";
							//续重价格（1-200克）
							String continueWeight = "";
							//续重价格（201-2000克）
							String continueWeight2 = "";
							//限重
							String limitWeight = "";
							//国家
							if(cellbody.hasNext()){
								cnName = cellbody.next().toString();
							}else{
								begin = false;
								break;
							}
							if("".equals(cnName)||"温馨提示".equals(cnName)){
								begin = false;
								break;
							}
							//起价重量
							if(cellbody.hasNext()){
								firstWeight = cellbody.next().toString();
							}
							//续重价格（1-200克）
							if(cellbody.hasNext()){
								continueWeight = cellbody.next().toString();
							}
							//续重价格（201-2000克）
							if(cellbody.hasNext()){
								continueWeight2 = cellbody.next().toString();
							}
							//限重
							if(cellbody.hasNext()){
								limitWeight = cellbody.next().toString();
							}
							//挂号费
							if(cellbody.hasNext()){
								payCharge = cellbody.next().toString();
							}
							//操作费
							if(cellbody.hasNext()){
								editCharge = cellbody.next().toString();
							}
							mExpressDetail.setExpressId(expressId);
							mExpressDetail.setExpressName(expressName);
							List<MCountryEntity> ts = mCountryService.findByProperty(MCountryEntity.class,"cnName",cnName);
							if(ts.size()>0){
								MCountryEntity t = ts.get(0);
								if(t!=null){
									mExpressDetail.setCountryId(t.getId());
									mExpressDetail.setCountryName(t.getCnName());
								}
							}else{
								MCountryEntity t = new MCountryEntity();
								t.setCnName(cnName);
								t.setEnName(enName);
								t.setEnShort(enShort);
								int id = (Integer)mCountryService.save(t);
								mExpressDetail.setCountryId(id);
								mExpressDetail.setCountryName(cnName);
							}

							if(!"-".equals(payCharge)&&!"".equals(payCharge))mExpressDetail.setPayCharge((Double) Double.parseDouble(payCharge));
//							if(!"-".equals(firstWeight)&&!"".equals(firstWeight))mExpressDetail.setFirstWeight((Double) Double.parseDouble(firstWeight));
							if(!"-".equals(continueWeight)&&!"".equals(continueWeight))mExpressDetail.setContinueWeight((Double) Double.parseDouble(continueWeight));
							mExpressDetailService.save(mExpressDetail);
						}else if(expressId>=17&&cellbody.hasNext()&&begin){
							mExpressDetail = new MExpressDetailEntity();

							String fenqu = "";//分区
							String enName = "";
							String enShort = "";
							String cnName = "";
							String deliveryCharge = "";
							String payCharge = "";
							String firstWeight = "";
							String continueWeight = "";
							if(cellbody.hasNext()){
								fenqu = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								cnName = cellbody.next().toString();
							}else{
								begin = false;
								break;
							}
							if("".equals(cnName)){
								begin = false;
								break;
							}
							if(cellbody.hasNext()){
								enName = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								enShort = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								deliveryCharge = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								payCharge = cellbody.next().toString();
							}
							/*if(cellbody.hasNext()){
								firstWeight = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								continueWeight = cellbody.next().toString();
							}*/
							mExpressDetail.setExpressId(expressId);
							mExpressDetail.setExpressName(expressName);
							List<MCountryEntity> ts = mCountryService.findByProperty(MCountryEntity.class,"cnName",cnName);
							if(ts.size()>0){
								MCountryEntity t = ts.get(0);
								if(t!=null){
									mExpressDetail.setCountryId(t.getId());
									mExpressDetail.setCountryName(t.getCnName());
								}
							}else{
								MCountryEntity t = new MCountryEntity();
								t.setCnName(cnName);
								t.setEnName(enName);
								t.setEnShort(enShort);
								int id = (Integer)mCountryService.save(t);
								mExpressDetail.setCountryId(id);
								mExpressDetail.setCountryName(cnName);
							}
//							MCountryEntity t = (MCountryEntity)mCountryService.findByProperty(MCountryEntity.class,"enShort",enShort).get(0);

							if(!"-".equals(deliveryCharge)&&!"".equals(deliveryCharge))mExpressDetail.setDeliveryCharge((Double) Double.parseDouble(deliveryCharge));
							if(!"-".equals(payCharge)&&!"".equals(payCharge))mExpressDetail.setPayCharge((Double) Double.parseDouble(payCharge));
							/*if(!"-".equals(firstWeight)&&!"".equals(firstWeight))mExpressDetail.setFirstWeight((Double) Double.parseDouble(firstWeight));
							if(!"-".equals(continueWeight)&&!"".equals(continueWeight))mExpressDetail.setContinueWeight((Double) Double.parseDouble(continueWeight));*/
							mExpressDetailService.save(mExpressDetail);
						}else if (cellbody.hasNext()&&begin) {
							mExpressDetail = new MExpressDetailEntity();

							String enName = "";
							String enShort = "";
							String cnName = "";
							String deliveryCharge = "";
							String payCharge = "";
							String firstWeight = "";
							String continueWeight = "";
							if(cellbody.hasNext()){
								enName = cellbody.next().toString();
							}else{
								begin = false;
								break;
							}
							if("".equals(enName)){
								begin = false;
								break;
							}
							if(cellbody.hasNext()){
								enShort = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								cnName = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								deliveryCharge = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								payCharge = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								firstWeight = cellbody.next().toString();
							}
							if(cellbody.hasNext()){
								continueWeight = cellbody.next().toString();
							}
							mExpressDetail.setExpressId(expressId);
							mExpressDetail.setExpressName(expressName);
							List<MCountryEntity> ts = mCountryService.findByProperty(MCountryEntity.class,"enShort",enShort);
							if(ts.size()>0){
								MCountryEntity t = ts.get(0);
								if(t!=null){
									mExpressDetail.setCountryId(t.getId());
									mExpressDetail.setCountryName(t.getCnName());
								}
							}else{
								MCountryEntity t = new MCountryEntity();
								t.setCnName(cnName);
								t.setEnName(enName);
								t.setEnShort(enShort);
								int id = (Integer)mCountryService.save(t);
								mExpressDetail.setCountryId(id);
								mExpressDetail.setCountryName(cnName);
							}
//							MCountryEntity t = (MCountryEntity)mCountryService.findByProperty(MCountryEntity.class,"enShort",enShort).get(0);

							if(!"-".equals(deliveryCharge)&&!"".equals(deliveryCharge))mExpressDetail.setDeliveryCharge((Double)Double.parseDouble(deliveryCharge));
							if(!"-".equals(payCharge)&&!"".equals(payCharge))mExpressDetail.setPayCharge((Double) Double.parseDouble(payCharge));
							if(!"-".equals(firstWeight)&&!"".equals(firstWeight))mExpressDetail.setFirstWeight((Double) Double.parseDouble(firstWeight));
							if(!"-".equals(continueWeight)&&!"".equals(continueWeight))mExpressDetail.setContinueWeight((Double) Double.parseDouble(continueWeight));
							mExpressDetailService.save(mExpressDetail);
						}
						// 遍历一行的列 取开始取值的位置
						while (cellbody.hasNext()&&!begin) {
							Cell cell = cellbody.next();

							String keyWord = cell.toString();
							if(KEYWORD.equals(keyWord)){
								begin = true;
							}
							// 这里得到此列的对应的标题
							String titleString = (String) titlemap.get(k);
							k++;
						}
					}
				}


				//结束

//				List<MExpressDetailEntity> listMExpressDetailEntitys = ExcelImportUtil.importExcel(file.getInputStream(),MExpressDetailEntity.class,params);
//				for (MExpressDetailEntity mExpressDetail : listMExpressDetailEntitys) {
//					mExpressDetailService.save(mExpressDetail);
//				}
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
