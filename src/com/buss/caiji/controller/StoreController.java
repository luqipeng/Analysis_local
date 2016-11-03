package com.buss.caiji.controller;
import com.buss.caiji.entity.CaijiCategoryEntity;
import com.buss.caiji.entity.FeedbackInfo;
import com.buss.caiji.entity.StoreBean;
import com.buss.caiji.entity.StoreEntity;
import com.buss.caiji.method.GroupMethod;
import com.buss.caiji.service.StoreServiceI;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buss.caiji.util.Constant;
import com.buss.caiji.util.JsonPluginsUtil;
import com.buss.caiji.util.Tools;
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
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Locale;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: 店铺
 * @author onlineGenerator
 * @date 2015-08-07 14:14:10
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/storeController")
public class StoreController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StoreController.class);

	@Autowired
	private StoreServiceI storeService;
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
	 * 店铺列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "store")
	public ModelAndView store(HttpServletRequest request) {
		return new ModelAndView("com/buss/caiji/storeList");
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
	public void datagrid(StoreEntity store,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(StoreEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, store, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.storeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除店铺
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(StoreEntity store, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		store = systemService.getEntity(StoreEntity.class, store.getId());
		message = "店铺删除成功";
		try{
			storeService.delete(store);
			syncStore(store,"delete");
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除店铺
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "店铺删除成功";
		try{
			for(String id:ids.split(",")){
				StoreEntity store = systemService.getEntity(StoreEntity.class, 
				Integer.parseInt(id)
				);
				storeService.delete(store);
				syncStore(store, "delete");
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加店铺
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(StoreEntity store, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "店铺添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			store.setUserId(user.getId());

			//设置sellerAdminSeq
			Tools.setSellerAdminSeq(store);

			//采集 产品数和收藏量
			Tools.setProductNum(store);
			//采集店铺创建时间和三个月的好评数
			Tools.setCreateTime(store);
			Serializable id = storeService.save(store);
			store.setId(Integer.parseInt(String.valueOf(id)));
			syncStore(store, "add");
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/*private void setCreateTime(StoreEntity store) {
		try {
			URL serverUrl = new URL("http://m.aliexpress.com/store/sellerInfo.htm?sellerAdminSeq="+store.getSellerAdminSeq());
			HttpURLConnection conn = (HttpURLConnection) serverUrl
					.openConnection();
			conn.setRequestProperty("Cookie", "ali_apache_id=11.227.7.27.1470909775656.996174.4; ali_beacon_id=11.227.7.27.1470909775656.996174.4; cna=3w4yEJlbElUCATEsOe/ZBDy0; xman_f=Lmv687wW3+STPgGCZizbYhfTwVFMfSpu6/c/5A/g4Szvf64+h3ohfM5EnjLaVSxpU4WoBYjDeYCZwG0sH9fig+gpgEruFqWF4D9KF51dx+g2x/DUzw5SCQ==; __utma=3375712.787323053.1470910075.1470910241.1470916656.2; __utmz=3375712.1470916656.2.2.utmcsr=aliexpress.com|utmccn=(referral)|utmcmd=referral|utmcct=/; aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932698265016%0932703762672%091861300775%09997626966%09997470494; _ga=GA1.2.787323053.1470910075; acs_usuc_t=acs_rt=f026c597232a44169b19a2e6139598e7; xman_t=R7l4/FiF/8uPM7BxCSz/iYRr3ez3LSJdTPnY0BkCszqD1aEeD8dBU78LppRsnJIZ; JSESSIONID=5A057AD4A08A9251F2C103645DEAE749; ali_apache_track=; ali_apache_tracktmp=; xman_us_f=x_l=1&x_locale=en_US; aep_usuc_f=site=glo&region=IN&b_locale=en_US&c_tp=USD; intl_locale=en_US; intl_common_forever=J57DxG1KiTM3eHvgl1k4LVq+/CiqLmMIYqgy3DEGyakbqmoz8QXoqg==; l=AvDwKoL9ISHIvy7WJmW7lpQ6QLRC5NSD; isg=Ak9Pl3D3ROt3V0CWHPUFBtze3uMY9S5SS-kb3mFcNr7FMG8yaEQz5k0uBNd0");
			conn.setRequestMethod("GET");
			conn.addRequestProperty("X-Forwarded-For", "");
			BufferedReader br = null;
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String strRead = "";
			String createTime = "";
			String storeName = "";
			String threeMonthFeedback = "";
			String timeRegular= "<div[^>]*>([^<]*)</div>";
			String feedbackRegular= "<td[^>]*>([^<]*)</td>";
			boolean start = false;
			GroupMethod gMethod = new GroupMethod();
			while ((strRead = br.readLine()) != null) {
				if(strRead.contains("ms-seller-name")){
					storeName = gMethod.regularGroup(timeRegular,strRead);
					store.setStoreName(storeName);
				}
				if(strRead.contains("ms-seller-since")){
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMM yyyy", Locale.US);
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					createTime = gMethod.regularGroup(timeRegular,strRead);
					createTime = createTime.replace("Fournisseur Depuis ", "").replace("Supplier Since&nbsp; ","");
					createTime = sdf2.format(sdf1.parse(createTime));
					store.setCreateTime(createTime);
					System.out.println(createTime);
				}
				if(start){
					if (strRead.contains("<tbody>")) {
						br.readLine();
						br.readLine();
						strRead = br.readLine();
						threeMonthFeedback = gMethod.regularGroup(feedbackRegular,strRead);
						System.out.println(threeMonthFeedback);
						store.setThreeFeedback(threeMonthFeedback);
						start = false;
						return;
					}
				}
				if (strRead.contains("feedback-history")) {
					start = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	private void setProductNum(StoreEntity store) throws IOException,InterruptedIOException{
		String urlstr = "https://m.aliexpress.com/product/ajax/sellerStatisticsInfo.do?sellerAdminSeq="+store.getSellerAdminSeq()+"&sellerCompanyId="+store.getSellerCompanyId();
		HttpURLConnection conn = (HttpURLConnection) new URL(urlstr)
				.openConnection();
		BufferedReader br = null;
		try {
			conn.addRequestProperty("X-Forwarded-For", "");
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
		} catch (Exception e) {

		}
		String jsonString = "";
		String strRead = "";
		while ((strRead = br.readLine()) != null) {
			jsonString += strRead;
		}
		StoreBean storeBean = (StoreBean) JsonPluginsUtil
				.jsonToBean(jsonString, StoreBean.class);
		store.setCollectNum(storeBean.getWishlistCount());//收藏量
		store.setTotalNum(storeBean.getItemsCount());//产品数
		FeedbackInfo feedbackInfo = storeBean.getFeedbackInfo();
		store.setLevel(feedbackInfo.getSellerLevel());//店铺等级
		store.setStoreFacebackper(feedbackInfo.getSellerPositiveRate());//好评率
		store.setStoreFaceback(feedbackInfo.getSellerScore()+"");//好评分

	}

	private void setSellerAdminSeq(StoreEntity store) {

		try {
			URL serverUrl = new URL("http://m.aliexpress.com/store/storeHome.htm?storeNo="+store.getStoreId());
			HttpURLConnection conn = (HttpURLConnection) serverUrl
					.openConnection();
			conn.setRequestProperty("Cookie", "ali_apache_id=11.227.7.27.1470909775656.996174.4; ali_beacon_id=11.227.7.27.1470909775656.996174.4; cna=3w4yEJlbElUCATEsOe/ZBDy0; xman_f=Lmv687wW3+STPgGCZizbYhfTwVFMfSpu6/c/5A/g4Szvf64+h3ohfM5EnjLaVSxpU4WoBYjDeYCZwG0sH9fig+gpgEruFqWF4D9KF51dx+g2x/DUzw5SCQ==; __utma=3375712.787323053.1470910075.1470910241.1470916656.2; __utmz=3375712.1470916656.2.2.utmcsr=aliexpress.com|utmccn=(referral)|utmcmd=referral|utmcct=/; aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932698265016%0932703762672%091861300775%09997626966%09997470494; _ga=GA1.2.787323053.1470910075; acs_usuc_t=acs_rt=f026c597232a44169b19a2e6139598e7; xman_t=R7l4/FiF/8uPM7BxCSz/iYRr3ez3LSJdTPnY0BkCszqD1aEeD8dBU78LppRsnJIZ; JSESSIONID=5A057AD4A08A9251F2C103645DEAE749; ali_apache_track=; ali_apache_tracktmp=; xman_us_f=x_l=1&x_locale=en_US; aep_usuc_f=site=glo&region=IN&b_locale=en_US&c_tp=USD; intl_locale=en_US; intl_common_forever=J57DxG1KiTM3eHvgl1k4LVq+/CiqLmMIYqgy3DEGyakbqmoz8QXoqg==; l=AvDwKoL9ISHIvy7WJmW7lpQ6QLRC5NSD; isg=Ak9Pl3D3ROt3V0CWHPUFBtze3uMY9S5SS-kb3mFcNr7FMG8yaEQz5k0uBNd0");
			conn.setRequestMethod("GET");
			conn.addRequestProperty("X-Forwarded-For", "");
			BufferedReader br = null;
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String strRead = "";
			String sellerAdminSeq = "";
			String sellerCompanyId = "";
			String regular= "value=\"([^\"]*)\"";
			boolean start = false;
			GroupMethod gMethod = new GroupMethod();
			while ((strRead = br.readLine()) != null) {
				if(strRead.contains("companyId")){
					start = true;
					sellerCompanyId = gMethod.regularGroup(regular,strRead);
					store.setSellerCompanyId(sellerCompanyId);
				}
				if (start && strRead.contains("var sellerAdminSeq=")) {
					sellerAdminSeq  = strRead.replace("var sellerAdminSeq=", "").replace(";", "");
					store.setSellerAdminSeq(sellerAdminSeq);
					start = false;
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}*/

	/**
	 * 更新店铺
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(StoreEntity store, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "店铺更新成功";
		StoreEntity t = storeService.get(StoreEntity.class, store.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(store, t);
			storeService.saveOrUpdate(t);
			syncStore(t,"update");
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "店铺更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	private void syncStore(StoreEntity store,String type){
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
			if("add".equals(type)){//true:添加 ; false:删除
				stmt.execute("INSERT INTO store(id,store_id,store_url,store_name,store_faceback,store_facebackper,is_catch,user_id,seller_admin_seq) VALUES ("+store.getId()+",'"+store.getStoreId()+"', '"+store.getStoreUrl()+"'" +
						",'"+store.getStoreName()+"','"+store.getStoreFaceback()+"','"+store.getStoreFacebackper()+"',"+store.getIsCatch()+",'"+store.getUserId()+"','"+store.getSellerAdminSeq()+"')");
			}else if("delete".equals(type)){
				stmt.execute("delete from store where id='" + store.getId() + "'");
			}else if("update".equals(type)){
				stmt.execute("update store set store_id='"+store.getStoreId()+"',store_url='"+store.getStoreUrl()+"',store_name='"+store.getStoreName()+"',is_catch='"+store.getIsCatch()+"',seller_admin_seq='"+store.getSellerAdminSeq()+"'  where id='" + store.getId() + "'");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 店铺新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(StoreEntity store, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = storeService.getEntity(StoreEntity.class, store.getId());
			req.setAttribute("storePage", store);
		}
		return new ModelAndView("com/buss/caiji/store-add");
	}
	/**
	 * 店铺编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(StoreEntity store, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = storeService.getEntity(StoreEntity.class, store.getId());
			req.setAttribute("storePage", store);
		}
		return new ModelAndView("com/buss/caiji/store-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","storeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(StoreEntity store,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(StoreEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, store, request.getParameterMap());
		List<StoreEntity> stores = this.storeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"店铺");
		modelMap.put(NormalExcelConstants.CLASS,StoreEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("店铺列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,stores);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(StoreEntity store,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "店铺");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,StoreEntity.class);
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
				List<StoreEntity> listStoreEntitys = ExcelImportUtil.importExcel(file.getInputStream(),StoreEntity.class,params);
				for (StoreEntity store : listStoreEntitys) {
					storeService.save(store);
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
