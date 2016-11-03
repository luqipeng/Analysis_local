package com.buss.ws.controller;
import com.buss.attention.entity.MProductEntity;
import com.buss.caiji.method.GroupMethod;
import com.buss.caiji.po.ProductSearchPo;
import com.buss.ws.entity.MWorkspaceEntity;
import com.buss.ws.service.MWorkspaceServiceI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buss.wspro.entity.WsProEntity;
import com.buss.wspro.service.WsProServiceI;
import net.sf.json.JSONArray;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: m_workspace
 * @author onlineGenerator
 * @date 2016-07-12 16:27:54
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/mWorkspaceController")
public class MWorkspaceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MWorkspaceController.class);

	@Autowired
	private MWorkspaceServiceI mWorkspaceService;
	@Autowired
	private WsProServiceI wsProService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	static String img = null;

	static String p_id = null;

	static String p_name = null;

	static String p_url = null;

	static String p_img = null;

	static String p_price = null;

	static String p_dsr = null;//评价

	static String p_faceback = null;

	static String p_orders = null;

	static String s_id = null;

	static String s_name = null;

	static String s_url = null;

	/**
	 * m_workspace列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mWorkspace")
	public ModelAndView mWorkspace(HttpServletRequest request) {
		return new ModelAndView("com/buss/ws/mWorkspaceList");
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
	public void datagrid(MWorkspaceEntity mWorkspace,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MWorkspaceEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mWorkspace, request.getParameterMap());
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			cq.eq("userId",user.getId());
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mWorkspaceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "buildHtml")
	public void buildHtml(MWorkspaceEntity mWorkspace,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)  throws IOException{
		CriteriaQuery cq = new CriteriaQuery(MWorkspaceEntity.class, dataGrid);

		List<ProductSearchPo> list = new ArrayList<ProductSearchPo>();
		ProductSearchPo po = null;
		String keyword = request.getParameter("keyword");
		String page = request.getParameter("page");
		System.out.println(keyword);
		keyword = keyword.replace(" ","+");
		//判断是否存在数据库
		TSUser user = ResourceUtil.getSessionUserName();
		List<MWorkspaceEntity> listWS = mWorkspaceService.findByQueryString("from MWorkspaceEntity where user_id = '" + user.getId() + "'");
		String ids = "";
		for(int i=0;i<listWS.size();i++){
			ids += listWS.get(i).getId()+",";
		}
		List<WsProEntity> wsList = systemService.findByQueryString("from WsProEntity where wid in ("+ids.substring(0,ids.length()-1)+")");
		List<String> pids = new ArrayList<String>();
		for(WsProEntity m:wsList){
			pids.add(m.getPid());
		}
		//pids = getProductIdsByWorkspaceId();
		String urlstr = "http://www.aliexpress.com/wholesale?catId=0&initiative_id=AS_20160713010236&SearchText="+keyword+"&page="+page;
		// 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径
		try {
			URL url = new URL(urlstr);
			// InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
			InputStreamReader isr = new InputStreamReader(url.openStream(),
					"gb2312"); // 统一使用utf-8 编码模式
			// 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
			BufferedReader br = new BufferedReader(isr);
			String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容
			boolean start = false;
			int index = 1;
			// 定义3个正则 用于匹配我们需要的数据
			String all = "(\\d{1,2}\\.\\d{1,2}\\.\\d{4})";
			String href = "<a\\s*.*)(href\\s*=\\s*[\"']*([^>\\s'\"]*)[\"']*)";
			String title = "<a[^>]*>([^<]*)</a>";
			String imgRegular= "src=\"([^\"]*)\"";
			String priceRegular= "<span[^>]*>([^<]*)</span>";
			String dsrRegular = "title=\"([^\"]*)\"";
			String alt = "alt=\"([^\"]*)\"";
			String orderRegular= "<em[^>]*>([^<]*)</em>";
			// 创建一个GroupMethod类的对象 gMethod 方便后期调用其类里的 regularGroup方法
			GroupMethod gMethod = new GroupMethod();
			boolean flag = true;
			while(flag&&(strRead=br.readLine())!=null){
				if(strRead.contains("<ul")){
					start = true;
				}
				if(start){
					//判断是否含有class = product  取url和id,name
					if(strRead.contains("class=\"has-sku-image\"")||strRead.contains("<a  class=\" product")||strRead.contains("itemprop=\"name\"")||strRead.contains("class=\"history-item product ")){
						String pattern= "href=\"([^\"]*)\"";
						Pattern pKey = Pattern.compile(pattern, 2 | Pattern.DOTALL);
						Matcher mKey = pKey.matcher(strRead);
						if(mKey.find()){
							//截取 url 中   ‘http://www.aliexpress.com/item/’ 这部分
							p_url = mKey.group(1);
							if(p_url != null && p_url.length() > 0){
								if(p_url.split("/").length>5){
									if(p_url.split("/").length==5){
										System.out.println(""+p_url);
									}
									p_id = p_url.split("/")[5].replace(".html", "");
									p_id = p_id.substring(0,p_id.lastIndexOf("?"));
								}else{
									System.out.println("320行：" + p_url );
								}

							}
							p_name = gMethod.regularGroup(dsrRegular, strRead);
							p_name = p_name.replaceAll("'", "");
							if(p_url != null){
								p_url = p_url.replace("http://www.aliexpress.com/item/", "");
								p_url = p_url.replace("//www.aliexpress.com/item/", "");
							}
						}
					}
					//判断是否含有class = picCore  取图片地址
					if(strRead.contains("picCore pic-Core-v")||strRead.contains("picCore")){
						img = gMethod.regularGroup(imgRegular, strRead);
					}
					//判断是否含有class = value    取价格
					if(strRead.contains("<span class=\"value")){
						p_price = gMethod.regularGroup(priceRegular, strRead);
						p_price = p_price.replace("US $", "");
					}

					//判断是否含有class=star star-s  取评价
					if(strRead.contains("<span class=\"star star-s")){
						p_dsr = gMethod.regularGroup(dsrRegular, strRead).substring(13, 16);
					}
					//判断是否含有title= Feedback    取销量faceback
					if(strRead.contains("title=\"Feedback")||strRead.contains("class=\"rate-num\"")){
						p_faceback = gMethod.regularGroup(title, strRead).replace("Feedback", "").replace("(", "").replace(")", "");
						if(p_faceback.contains("|")){
							p_faceback = p_faceback.split("|")[0];
						}
					}
					//判断是否含有<em title="Total Orders">    取排序orders
					if(strRead.contains("<em title=\"Total Orders\">")){
						p_orders = gMethod.regularGroup(orderRegular, strRead).trim().replace("Order  (", "").replace("Orders  (", "").replace("Order (", "").replace("Orders (", "").replace(")", "");
					}
					//判断是否含有class="store"    取store相关的值
					if(strRead.contains("class=\"store ")){
						String pattern= "href=\"([^\"]*)\"";
						String storeName= "title=\"([^\"]*)\"";
						Pattern pKey = Pattern.compile(pattern, 2 | Pattern.DOTALL);
						Matcher mKey = pKey.matcher(strRead);
						if(mKey.find()){
							s_url = mKey.group(1);
							if(s_url!=null&&s_url.length()>0){
								if(s_url.split("/").length==5){
									s_id = s_url.split("/")[4].replace(".html", "");
								}else if(s_url.split("/").length>5){
									s_id = s_url.split("/")[5].split("-")[0];
								}
							}
							s_name = gMethod.regularGroup(storeName, strRead);
							s_name = s_name.replace("'", "\'");
						}
					}

					if(strRead.contains("</li>")){

						if(p_id!=null){
							p_id = p_id.split("\\?")[0];
							if(p_id.length()>12){
								p_id = p_id.substring(0, 11);
							}

							//List<MProductEntity> wsList = systemService.findByQueryString("from MProductEntity where wid in ("+ids.substring(0,ids.length()-1)+") and pid = '"+p_id+"'");
							po = new ProductSearchPo();
							po.setProductId(p_id);
							po.setProductImg(img);
							if(p_name!=null && p_name.contains("&#39;")){
								p_name = p_name.replace("&#39;","");
							}
							po.setProductUrl(p_url);
							po.setProductName(p_name);
							po.setProductPrice(p_price);
							po.setProductOrdersAll(p_orders);
							po.setStoreName(s_name);
							if(pids.contains(p_id)){
								po.setExist("1");
								//po.setWid(wsList.get(0).getWid().toString());
							}
							/*if(wsList!=null&&wsList.size()>0){
								po.setExist("1");
								po.setWid(wsList.get(0).getWid().toString());
							}*/
							list.add(po);
//							doAddBatch(p_id,p_name,p_url,img,p_price,p_dsr,p_faceback,p_orders,c_orders,s_id,s_name,
//									s_faceback,s_facebackper,category_id,index,page,df.format(new Date()),discount,all_index);
							p_name = null;
							p_url = null;
							img = null;
							p_price = null;
							p_dsr = null;
							p_faceback = null;
							p_id = null;
							p_orders = "0";
							s_id = null;
							s_name = null;
						}
					}

					if(strRead.contains("id=\"pagination-bottom\"")){
						start = false;
						flag = false;
					}
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}


		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mWorkspace, request.getParameterMap());
		try{
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
//		cq.add();
//		this.mWorkspaceService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);


		response.getWriter().print(JSONArray.fromObject(list));
		response.getWriter().flush();
		response.getWriter().close();
	}


	public String[] getProductIdsByWorkspaceId(DataGrid dataGrid,Integer wId){
		CriteriaQuery cq = new CriteriaQuery(WsProEntity.class, dataGrid);
		cq.eq("wid", wId);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new WsProEntity(), null);
		try{
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.wsProService.getDataGridReturn(cq, true);
		List<WsProEntity> wsProEntities = dataGrid.getResults();

		String[] ids = new String[wsProEntities.size()];
		for(int i=0; i<wsProEntities.size(); i++){
			ids[i] = "\'"+wsProEntities.get(i).getPid()+"\'";
		}
		return ids;
	}

	/**
	 * 删除m_workspace
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MWorkspaceEntity mWorkspace, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		mWorkspace = systemService.getEntity(MWorkspaceEntity.class, mWorkspace.getId());
		message = "m_workspace删除成功";
		try{
			mWorkspaceService.delete(mWorkspace);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_workspace删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	
	/**
	 * 批量删除m_workspace
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "m_workspace删除成功";
		try{
			for(String id:ids.split(",")){
				MWorkspaceEntity mWorkspace = systemService.getEntity(MWorkspaceEntity.class, 
				Integer.parseInt(id)
				);
				mWorkspaceService.delete(mWorkspace);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "m_workspace删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加m_workspace
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MWorkspaceEntity mWorkspace, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_workspace添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			mWorkspace.setUserId(user.getId());
			mWorkspaceService.save(mWorkspace);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "m_workspace添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新m_workspace
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MWorkspaceEntity mWorkspace, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "m_workspace更新成功";
		MWorkspaceEntity t = mWorkspaceService.get(MWorkspaceEntity.class, mWorkspace.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(mWorkspace, t);
			mWorkspaceService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "m_workspace更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * m_workspace新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MWorkspaceEntity mWorkspace, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mWorkspace.getId())) {
			mWorkspace = mWorkspaceService.getEntity(MWorkspaceEntity.class, mWorkspace.getId());
			req.setAttribute("mWorkspacePage", mWorkspace);
		}
		return new ModelAndView("com/buss/ws/mWorkspace-add");
	}
	/**
	 * m_workspace编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MWorkspaceEntity mWorkspace, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mWorkspace.getId())) {
			mWorkspace = mWorkspaceService.getEntity(MWorkspaceEntity.class, mWorkspace.getId());
			req.setAttribute("mWorkspacePage", mWorkspace);
		}
		return new ModelAndView("com/buss/ws/mWorkspace-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","mWorkspaceController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MWorkspaceEntity mWorkspace,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MWorkspaceEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, mWorkspace, request.getParameterMap());
		List<MWorkspaceEntity> mWorkspaces = this.mWorkspaceService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"m_workspace");
		modelMap.put(NormalExcelConstants.CLASS,MWorkspaceEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("m_workspace列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,mWorkspaces);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MWorkspaceEntity mWorkspace,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "m_workspace");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,MWorkspaceEntity.class);
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
				List<MWorkspaceEntity> listMWorkspaceEntitys = ExcelImportUtil.importExcel(file.getInputStream(),MWorkspaceEntity.class,params);
				for (MWorkspaceEntity mWorkspace : listMWorkspaceEntitys) {
					mWorkspaceService.save(mWorkspace);
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
