package com.buss.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buss.attention.entity.AttentionProductEntity;
import com.buss.attention.entity.MChangeEntity;
import com.buss.attention.entity.MProductEntity;
import com.buss.attention.service.AttentionProductServiceI;
import com.buss.attention.service.MChangeServiceI;
import com.buss.attention.service.MProductServiceI;
import com.buss.caiji.entity.*;
import com.buss.caiji.method.GroupMethod;
import com.buss.caiji.service.*;
import com.buss.compare.controller.CommodityAnalysisController;
import com.buss.compare.controller.CommodityManageController;
import com.buss.compare.entity.CommodityAnalysisEntity;
import com.buss.compare.entity.CommodityManageEntity;
import com.buss.compare.entity.SkuProductEntity;
import com.buss.compare.entity.StoreProductChangeEntity;
import com.buss.compare.service.CommodityAnalysisServiceI;
import com.buss.compare.service.CommodityManageServiceI;
import com.buss.compare.service.SkuProductServiceI;
import com.buss.compare.service.StoreProductChangeServiceI;
import com.buss.group.entity.MGroupEntity;
import com.buss.group.service.MGroupServiceI;
import com.buss.keyword.entity.MKeywordEntity;
import com.buss.keyword.service.MKeywordServiceI;
import com.buss.ws.entity.MWorkspaceEntity;
import com.buss.ws.entity.UserMWorkspaceEntity;
import com.buss.ws.service.MWorkspaceServiceI;
import com.buss.wspro.entity.WsProEntity;
import com.buss.wspro.service.WsProServiceI;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.Highchart;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.buss.caiji.common.Cache;
import com.buss.caiji.controller.ProductController;
import com.buss.caiji.method.ConnectionParam;
import com.buss.caiji.method.ConnectionPool;
import com.buss.caiji.po.MainTabPo;
import com.buss.caiji.po.RUDatePO;
import com.buss.caiji.po.TagProductPO;
import com.buss.caiji.util.Constant;
import com.buss.caiji.util.Tools;
import com.buss.tag.entity.FirstTbEntity;
import com.buss.tag.entity.TagEntity;
import com.buss.tag.entity.UserTagProEntity;
import com.buss.tag.service.FirstTbServiceI;
import com.buss.tag.service.TagServiceI;
import com.buss.tag.service.UserTagProServiceI;

@Scope("prototype")
@Controller
@RequestMapping("/frontController")
public class FrontController extends BaseController {


	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FrontController.class);

	@Autowired
	private ProductServiceI productService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CaijiCategoryServiceI caijiCategoryService;
	@Autowired
	private UserTagProServiceI userTagProService;
	@Autowired
	private TagServiceI tagService;
	@Autowired
	private FirstTbServiceI firstTbServiceI;
	@Autowired
	private RuProductServiceI ruProductServiceI;
	@Autowired
	private CommodityAnalysisServiceI commodityAnalysisService;

	@Autowired
	private MGroupServiceI mGroupService;
	@Autowired
	private StoreProductChangeServiceI storeProductChangeService;

	@Autowired
	private CommodityManageServiceI commodityManageService;
	private String message;

	@Autowired
	private MWorkspaceServiceI mWorkspaceService;

	@Autowired
	private MKeywordServiceI mKeywordService;

	@Autowired
	private WsProServiceI wsProService;

	@Autowired
	private MProductServiceI mProductService;

	@Autowired
	private AttentionProductServiceI attentionProductService;
	@Autowired
	private MChangeServiceI mChangeService;
	@Autowired
	private StoreServiceI storeServiceI;

	@Autowired
	private StoreProductServiceI storeProductService;
	@Autowired
	private SkuProductServiceI skuProductService;

	static Statement stmt = null;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 首页 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "index")
	public ModelAndView index(HttpServletRequest request) {
		return new ModelAndView("front/index");
	}


	/**
	 * 首页 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		request.setAttribute("categoryId", request.getParameter("categoryId"));
		request.setAttribute("tagId", request.getParameter("tagId"));
		request.setAttribute("condition", request.getParameter("condition"));
		request.setAttribute("search", request.getParameter("search"));
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		return new ModelAndView("front/list");
	}


	/**
	 * 首页 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list_tag")
	public ModelAndView list_tag(HttpServletRequest request) {
		request.setAttribute("tagId", request.getParameter("tagId"));
		int tagId = Integer.parseInt(request.getParameter("tagId"));
		TagEntity tag = tagService.getEntity(TagEntity.class, tagId);
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("tag", tag);
		return new ModelAndView("front/list_tag");
	}


	/**
	 * 俄团 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "ru")
	public ModelAndView ru(HttpServletRequest request) {
		String date = request.getParameter("date");
		if(date != null && !("").equals(date)){
			request.setAttribute("date", date);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		return new ModelAndView("front/list_ru");
	}

	/**
	 * 工作组 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "ws")
	public ModelAndView ws(HttpServletRequest request) {
		String date = request.getParameter("date");
		String keyword = request.getParameter("keyword");
		if(date != null && !("").equals(date)){
			request.setAttribute("date", date);
		}
		if(keyword != null && !("").equals(keyword)){
			request.setAttribute("keyword", keyword);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		return new ModelAndView("front/list_workspace");
	}


	/**
	 * 店铺 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "store")
	public ModelAndView store(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		return new ModelAndView("front/list_store");
	}



	/**
	 * 团购 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "group")
	public ModelAndView group(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		return new ModelAndView("front/list_group");
	}


	/**
	 * 店铺列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "storeList")
	public void storeList(StoreEntity store,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());

		CriteriaQuery cq = new CriteriaQuery(StoreEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, store, request.getParameterMap());
		try{
			if(!"admin".equals(user.getUserName())){
				cq.eq("userId",user.getId());
			}
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.storeServiceI.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * 团购统计列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "groupList")
	public void groupList(MGroupEntity group,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());

		CriteriaQuery cq = new CriteriaQuery(MGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, group, request.getParameterMap());
		try{
			if(!user.getUserName().equals("admin")){
				cq.eq("userId",user.getId());
			}
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * 产品组 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "pd")
	public ModelAndView pd(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(id != null && !("").equals(id)){
			request.setAttribute("id", id);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		MWorkspaceEntity mWorkspace = mWorkspaceService.get(MWorkspaceEntity.class, Integer.parseInt(id));
		Long count = wsProService.getCountForJdbc("select count(*) from m_ws_pro where wid = " + id);
		request.setAttribute("mWorkspace", mWorkspace);
		request.setAttribute("count", count);
		return new ModelAndView("front/list_product");
	}

	/**
	 * 每日销量排行榜  页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "day")
	public ModelAndView day(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		return new ModelAndView("front/list_day");
	}


	/**
	 * 周销量排行榜  页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "week")
	public ModelAndView week(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		return new ModelAndView("front/list_week");
	}


	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "list_ru")
	public void list_ru(RuProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RuProductEntity.class, dataGrid);
		cq.setPageSize(36);
		String endTime = request.getParameter("endTime");
		String endTimes = request.getParameter("endTimes");
		String lastDate = request.getParameter("time");
		String condition = request.getParameter("condition");
		String orders = request.getParameter("paixu");
		String categorys = request.getParameter("categorys");
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		SortDirection od ;
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		try{
			if(lastDate != null && !"".equals(lastDate) && "now".equals(lastDate)){

				//默认为采集的最后一天
				long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
				long zero=yesterday/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
				cq.ge("lastDate", new Timestamp(zero));
			}
			if(endTime != null && !"".equals(endTime)){
				cq.eq("endTime", dfs.parse(endTime));
			}
			if(endTimes != null && !"".equals(endTimes)){
				cq.eq("endTime", dfs.parse(endTimes));
			}
//			if(lastDate == null || "".equals(lastDate)){
//				//默认为采集的最后一天
//		        long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
//				long zero=yesterday/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
//				cq.ge("lastDate", new Timestamp(zero));
//			}
			if(orders != null && !"".equals(orders)){
				if("up".equals(orders)){
					od =  SortDirection.asc;
				}else{
					od =  SortDirection.desc;
				}
				Map<String,Object> map = new HashMap<String,Object>();
				if(condition != null && !"".equals(condition) && condition.equals("price")){
					map.put("activityPrice",  od);
				}else{
					map.put("count",  od);
				}
				cq.setOrder(map);
			}else{
				//进入页面默认按销量降序
				//所有产品按活动结束时间先后，降序排列 2016-1-9
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("endTime",  SortDirection.asc);
				cq.setOrder(map);
			}
			if(condition != null && !"".equals(condition)){
				if(condition.equals("categoryGr")){
					cq.eq("categoryGr", categorys);
				}else{
					if(condition.equals("activityPrice")){
						if(begin != null && !"".equals(begin)){
							cq.ge("activityPrice", Double.parseDouble(begin));
						}
						if(end != null && !"".equals(end)){
							cq.le("activityPrice", Double.parseDouble(end));
						}
					}else{
						if(begin != null && !"".equals(begin)){
							cq.ge(condition, Integer.parseInt(begin));
						}
						if(end != null && !"".equals(end)){
							cq.le(condition, Integer.parseInt(end));
						}
					}

				}
			}


			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ruProductServiceI.getDataGridReturn(cq, true);
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

	@RequestMapping(params = "datagrid")
	public void datagrid(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		try{
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.productService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	@RequestMapping(params = "category")
	public void category(CaijiCategoryEntity category,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		CriteriaQuery cq = new CriteriaQuery(CaijiCategoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, category, request.getParameterMap());
		try{
			cq.eq("isCatch", 1);
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.caijiCategoryService.getDataGridReturn(cq, true);
		response.getWriter().print(JSONArray.fromObject(dataGrid.getResults()));
		response.getWriter().flush();
		response.getWriter().close();
	}


	@RequestMapping(params = "path")
	public void path(CaijiCategoryEntity category,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		String categoryId = request.getParameter("categoryId");
		if(categoryId != null){
			List<CaijiCategoryEntity> list = getPath(categoryId);
			response.getWriter().print(JSONArray.fromObject(list));
		}else{
			String tagId = request.getParameter("tagId");
			response.getWriter().print(tagService.get(TagEntity.class, Integer.parseInt(tagId)).getName());
		}
		response.getWriter().flush();
		response.getWriter().close();
	}

	public List<CaijiCategoryEntity> getPath(String categoryId){
		List<CaijiCategoryEntity> list = new ArrayList<CaijiCategoryEntity>();
		//当前访问最后一级
		if(categoryId != null && ""!=categoryId){
			CaijiCategoryEntity categoryEntity = Cache.getCategoryList().get(Integer.parseInt(categoryId));
			if(categoryEntity == null){
				return list;
			}
			list.add(categoryEntity);

			addParentCategory(categoryEntity.getPid(), list);
		}

		return list;
	}


	public void addParentCategory(Integer pid,List<CaijiCategoryEntity> list){
		Map map = Cache.getCategoryList();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			CaijiCategoryEntity caijiCategoryEntity = (CaijiCategoryEntity)entry.getValue();
			if(caijiCategoryEntity.getId().equals(pid)){
				list.add(caijiCategoryEntity);
				if(caijiCategoryEntity.getPid() != 0){
					addParentCategory(caijiCategoryEntity.getPid(), list);
					break;
				}
			}
		}
	}

	public List<CaijiCategoryEntity> getPath1(String categoryId){
		List<CaijiCategoryEntity> list = new ArrayList<CaijiCategoryEntity>();
		ConnectionParam param = new ConnectionParam();
		param.setDriver(Constant.driver);
		param.setPassword(Constant.password);
		param.setUser(Constant.user);
		param.setUrl(Constant.url_local);
		ConnectionPool connectionPool = new ConnectionPool(param);
		try {
			connectionPool.createPool();
		} catch (Exception e) {
			logger.error(e);
		}
		Connection conn;
		ResultSet rs;
		try {
			conn = connectionPool.getConnection();
			Statement sm = conn.createStatement();
			sm.execute("select * from caiji_category where c_id=" + categoryId);
			rs = (ResultSet) sm.getResultSet();
			while(rs.next()){
				CaijiCategoryEntity categoryEntity = new CaijiCategoryEntity();
				categoryEntity.setCId(rs.getInt(2));
				categoryEntity.setCName(rs.getString(3));
				list.add(categoryEntity);
				Statement sm1 = conn.createStatement();
				sm1.execute("select * from caiji_category where  id=" + rs.getInt(4));
				ResultSet rs1 = (ResultSet) sm1.getResultSet();
				while(rs1.next()){
					categoryEntity = new CaijiCategoryEntity();
					categoryEntity.setCId(rs1.getInt(2));
					categoryEntity.setCName(rs1.getString(3));
					list.add(categoryEntity);
					Statement sm2 = conn.createStatement();
					sm2.execute("select * from caiji_category where  id=" + rs1.getInt(4));
					ResultSet rs2 = (ResultSet) sm2.getResultSet();
					while(rs2.next()){
						categoryEntity = new CaijiCategoryEntity();
						categoryEntity.setCId(rs2.getInt(2));
						categoryEntity.setCName(rs2.getString(3));
						list.add(categoryEntity);
						Statement sm3 = conn.createStatement();
						sm3.execute("select * from caiji_category where  id=" + rs2.getInt(4));
						ResultSet rs3 = (ResultSet) sm3.getResultSet();
						while(rs3.next()){
							categoryEntity = new CaijiCategoryEntity();
							categoryEntity.setCId(rs3.getInt(2));
							categoryEntity.setCName(rs3.getString(3));
							list.add(categoryEntity);
//							Statement sm3 = conn.createStatement();
//							sm3.execute("select * from caiji_category where  id=" + rs2.getInt(4));
//							ResultSet rs3 = (ResultSet) sm2.getResultSet();

						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	@RequestMapping(params = "product")
	public void product(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		cq.setPageSize(36);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		try{
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.productService.getDataGridReturn(cq, true);
		response.getWriter().print(JSONArray.fromObject(dataGrid.getResults()));
		response.getWriter().flush();
		response.getWriter().close();
	}


	/**
	 * 查看关注产品详细
	 *  这边改变m_product表里面viewed为已查看
	 * @param product
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "proDetail")
	public ModelAndView proDetail(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {

		String pid = request.getParameter("pid");
		String wid = request.getParameter("wid");
		request.setAttribute("pid", pid);
		request.setAttribute("wid", wid);
		List<MProductEntity> mProductEntities = mProductService.findByQueryString(" from MProductEntity where pid = "+pid);
		MProductEntity mProduct = new MProductEntity();
		if(mProductEntities.size()>0){
			mProduct = mProductEntities.get(0);
			//改成已查看
			mProduct.setViewed(1);
			mProductService.saveOrUpdate(mProduct);
		}else{
			MProductEntity mProductEntity = new MProductEntity();
			mProductEntity.setPid(pid);
			mProductService.save(mProductEntity);
		}
		//发生变化
		List<MChangeEntity> mChange = mChangeService.findByQueryString("from MChangeEntity where pid = "+pid+" order by date desc");
		if(mProduct.getName()!=null){
			mProduct.setUrl(mProduct.getName().replace(" ", "-") + "/" + mProduct.getPid() + ".html");
		}

		if(mProduct.getFirstDate()!=null){
			mProduct.setDays( (int) ((new Date().getTime()-mProduct.getFirstDate().getTime())/86400000));
		}
		if(mProduct.getName() == null){
			mChange = null;
		}

		request.setAttribute("mProduct", mProduct);
		request.setAttribute("mChange", mChange);
		return new ModelAndView("front/pro_detail");
	}


	/**
	 * 查看店铺详细
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "storeDetail")
	public ModelAndView storeDetail(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {

		String id = request.getParameter("id");
		StoreEntity store = storeServiceI.getEntity(StoreEntity.class, Integer.parseInt(id));
		Calendar timeStart = Calendar.getInstance();
		timeStart.add(Calendar.DAY_OF_MONTH, -7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(timeStart.getTime());
		CriteriaQuery cq = new CriteriaQuery(CommodityAnalysisEntity.class, dataGrid);
		cq.setPageSize(10);
		cq.add(Restrictions.sqlRestriction(" c_Id in   (select product_id from store_product where store_id = '" + store.getStoreId() + "') and time = (SELECT max(time) FROM commodity_analysis)"));
		cq.addOrder("placingOrders", SortDirection.desc);

		cq.add();
		this.commodityAnalysisService.getDataGridReturn(cq, true);
		List<CommodityAnalysisEntity> analysisEntities = dataGrid.getResults();
		List<CommodityAnalysisEntity> analysisRs = new ArrayList<CommodityAnalysisEntity>();
		for(CommodityAnalysisEntity analysisEntity:analysisEntities){
			SkuProductEntity sku = skuProductService.get(SkuProductEntity.class,analysisEntity.getC_Id());
			analysisEntity.getStoreProduct().setSku(sku.getSku());
			analysisEntity.getStoreProduct().setProductName(sku.getProductName());
			analysisRs.add(analysisEntity);
		}
		dataGrid.setResults(analysisRs);

		List<StoreProductChangeEntity> changes = storeProductChangeService.findHql("from StoreProductChangeEntity where storeId='" + store.getStoreId() + "'   and time > '" + beginTime + "'");
		/*List<CommodityAnalysisEntity> commodityAnalysisEntites = commodityAnalysisService.findByQueryString(" from CommodityAnalysisEntity where c_Id in   (select productId from SkuProductEntity where storeName = (select nickName from StoreEntity where storeId = '" + store.getStoreId() + "' )) group by placingOrders order by placingOrders desc limit 10");*/

		//最近两周上下架产品
		timeStart.add(Calendar.DAY_OF_MONTH, -7);
		beginTime = sdf.format(timeStart.getTime());

		List<Object[]> list= systemService.findListbySql("SELECT sum(pay_money),sum(visitors_num),time from commodity_analysis where c_id in (select product_id FROM store_product where store_id = '"+store.getStoreId()+"') GROUP BY time");

//		List<CommodityAnalysisEntity> analysisList = commodityAnalysisService.findHql(" from CommodityAnalysisEntity where time > "+beginTime+" order by time asc");
		Map<String, Object> map;
		List lt = new ArrayList();
		for(Object[] object:list){
			map = new HashMap<String, Object>();
			map.put("date", object[2].toString());
			map.put("orders", object[0]);
			map.put("person", object[1]);
			lt.add(map);
		}
		request.setAttribute("chartdivDataProvider",JSONArray.fromObject(lt));

		request.setAttribute("store", store);
		request.setAttribute("commodityAnalysisEntites", dataGrid.getResults());
		setLastThirtyDaysOrdersZero(dataGrid, request, "lastZero",store);
		setLastThirtyDaysOrdersZero(dataGrid,request,"lastZeroToThirty",store);
		setLastThirtyDaysOrdersZero(dataGrid,request,"lastThirtyTo100",store);
		setLastThirtyDaysOrdersZero(dataGrid,request,"last100To300",store);
		setLastThirtyDaysOrdersZero(dataGrid,request,"last300",store);
		setLastThirtyDaysOrdersZero(dataGrid,request,"newOrders",store);
		setStoreProductChanges(dataGrid,request,store);
		setStoreProductDown(dataGrid, request,store);
		setStoreProductUp(dataGrid, request,store);
		return new ModelAndView("front/store_detail");
	}


	/**
	 * 最近30天销量为0
	 * @param dataGrid
	 * @param request
	 */
	private void setLastThirtyDaysOrdersZero(DataGrid dataGrid,HttpServletRequest request,String condition,StoreEntity store){
		CriteriaQuery cq = new CriteriaQuery(StoreProductEntity.class, dataGrid);

		//cq.setPageSize(10);
		Calendar timeStart = Calendar.getInstance();
		timeStart.add(Calendar.DAY_OF_MONTH, -30);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(timeStart.getTime());

		Calendar time7 = Calendar.getInstance();
		time7.add(Calendar.DAY_OF_MONTH, -7);
		String time = sdf.format(time7.getTime());

		switch (condition){
			case "lastZero":
				cq.add(Restrictions.sqlRestriction(" this_.product_id in(select DISTINCT(b.c_id) from commodity_analysis b,(SELECT sum(pay_orders) a,c_id from commodity_analysis t where time > '"+beginTime+"' GROUP BY c_id ORDER BY a desc) as c where b.c_id = c.c_id and  c.a = 0)"));
				break;
			case "lastZeroToThirty":
				cq.add(Restrictions.sqlRestriction(" this_.product_id in(select DISTINCT(b.c_id) from commodity_analysis b,(SELECT sum(pay_orders) a,c_id from commodity_analysis t where time > '"+beginTime+"' GROUP BY c_id ORDER BY a desc) as c where b.c_id = c.c_id and  c.a BETWEEN 1 and 30)"));
				break;
			case "lastThirtyTo100":
				cq.add(Restrictions.sqlRestriction(" this_.product_id in(select DISTINCT(b.c_id) from commodity_analysis b,(SELECT sum(pay_orders) a,c_id from commodity_analysis t where time > '"+beginTime+"' GROUP BY c_id ORDER BY a desc) as c where b.c_id = c.c_id and  c.a BETWEEN 31 and 100)"));
				break;
			case "last100To300":
				cq.add(Restrictions.sqlRestriction(" this_.product_id in(select DISTINCT(b.c_id) from commodity_analysis b,(SELECT sum(pay_orders) a,c_id from commodity_analysis t where time > '"+beginTime+"' GROUP BY c_id ORDER BY a desc) as c where b.c_id = c.c_id and  c.a BETWEEN 101 and 300)"));
				break;
			case "last300":
				cq.add(Restrictions.sqlRestriction(" this_.product_id in(select DISTINCT(b.c_id) from commodity_analysis b,(SELECT sum(pay_orders) a,c_id from commodity_analysis t where time > '"+beginTime+"' GROUP BY c_id ORDER BY a desc) as c where b.c_id = c.c_id and  c.a > 300)"));
				break;
			case "newOrders":
				cq.add(Restrictions.sqlRestriction(" this_.product_id in(select p_id from store_product_change where type = '新出单' and time > '"+time+"')"));
				break;
		}
		cq.eq("storeId", store.getStoreId());
		cq.add();
		this.storeProductService.getDataGridReturn(cq, true);
		List<StoreProductEntity> list = dataGrid.getResults();
		List<StoreProductEntity> result = new ArrayList<StoreProductEntity>();
		if(!"newOrders".equals(condition)){
			if(list.size()>0){
				for(StoreProductEntity storeProduct:list){
					List<BigDecimal> rs = commodityAnalysisService.findListbySql("select sum(pay_Orders) from Commodity_Analysis where c_Id = '" + storeProduct.getProductId()+"' and time > '"+beginTime+"'");
					if(rs.size()>0&&rs.get(0)!=null){
						storeProduct.setProductOrders(rs.get(0).toString());
					}
					result.add(storeProduct);
				}
			}
		}else{
			result = list;
		}


		request.setAttribute(condition, result);
		request.setAttribute(condition+"Total", dataGrid.getTotal());
	}


	private void setStoreProductChanges(DataGrid dataGrid,HttpServletRequest request,StoreEntity store){
		CriteriaQuery cq = new CriteriaQuery(StoreProductChangeEntity.class, dataGrid);
		//cq.setPageSize(10);
		Calendar timeStart = Calendar.getInstance();
		timeStart.add(Calendar.DAY_OF_MONTH, -14);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(timeStart.getTime());
		cq.add(Restrictions.sqlRestriction("store_id='" + store.getStoreId() + "'   and time > '" + beginTime + "'"));
		cq.addOrder("id", SortDirection.desc);
		cq.add();
		this.storeProductChangeService.getDataGridReturn(cq, true);

		List<StoreProductChangeEntity> list = dataGrid.getResults();
		List<StoreProductChangeEntity> result = new ArrayList<StoreProductChangeEntity>();
		for(StoreProductChangeEntity storeProductChangeEntity:list){
			StoreProductEntity storeProductEntity = this.storeProductService.get(StoreProductEntity.class,storeProductChangeEntity.getpId());
			storeProductChangeEntity.setpName(storeProductEntity.getProductName());
			result.add(storeProductChangeEntity);
		}
		dataGrid.setResults(result);
		request.setAttribute("changes", dataGrid.getResults());
		request.setAttribute("changesTotal", dataGrid.getTotal());
	}



	private void setStoreProductUp(DataGrid dataGrid,HttpServletRequest request,StoreEntity store){
		CriteriaQuery cq = new CriteriaQuery(StoreProductEntity.class, dataGrid);
		//cq.setPageSize(10);
		Calendar timeStart = Calendar.getInstance();
		timeStart.add(Calendar.DAY_OF_MONTH, -14);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(timeStart.getTime());
		cq.add(Restrictions.sqlRestriction(" this_.product_id in ( select p_id from store_product_change where type = '上架' and time > '" + beginTime + "' and store_id = '"+store.getStoreId()+"')"));
		cq.add();
		this.storeProductService.getDataGridReturn(cq, true);
		request.setAttribute("storeProductUp", dataGrid.getResults());
		request.setAttribute("storeProductUpTotal", dataGrid.getTotal());
	}


	private void setStoreProductDown(DataGrid dataGrid,HttpServletRequest request,StoreEntity store){
		CriteriaQuery cq = new CriteriaQuery(StoreProductEntity.class, dataGrid);
		//cq.setPageSize(10);
		Calendar timeStart = Calendar.getInstance();
		timeStart.add(Calendar.DAY_OF_MONTH, -14);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(timeStart.getTime());
		cq.add(Restrictions.sqlRestriction(" this_.product_id in ( select p_id from store_product_change where type = '下架' and time > '" + beginTime + "' and store_id = '"+store.getStoreId()+"')"));
		cq.add();
		this.storeProductService.getDataGridReturn(cq, true);

		request.setAttribute("storeProductDown", dataGrid.getResults());
		request.setAttribute("storeProductDownTotal", dataGrid.getTotal());
	}


	/**
	 * 查看单品详细
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "storeProDetail")
	public ModelAndView storeProDetail(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {

		String id = request.getParameter("id");
		String pid = request.getParameter("pid");
		StoreEntity store = storeServiceI.getEntity(StoreEntity.class, Integer.parseInt(id));

		//CommodityAnalysisEntity commodityAnalysisEntity = commodityAnalysisService.getEntity(CommodityAnalysisEntity.class,pid);
		StoreProductEntity storeProduct = storeProductService.get(StoreProductEntity.class,pid);
		List<CommodityManageEntity> commodityManageEntitys = commodityManageService.findHql("from CommodityManageEntity where productId=?",pid);
		if(commodityManageEntitys.size()>0){
			request.setAttribute("productInfo", commodityManageEntitys.get(0));
		}
		List<StoreProductEntity> storeProducts = storeProductService.findByProperty(StoreProductEntity.class,"productId",pid);
		if(storeProducts.size()>0){
			request.setAttribute("storeProduct", storeProducts.get(0));
		}
		request.setAttribute("store", store);
		request.setAttribute("product", storeProduct);
		Calendar timeStart = Calendar.getInstance();
		timeStart.add(Calendar.DAY_OF_MONTH, -90);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(timeStart.getTime());
		// 90天详情
		List<CommodityAnalysisEntity> analysisList = commodityAnalysisService.findHql(" from CommodityAnalysisEntity where c_Id = '"+pid+"' and time > "+beginTime+" order by time asc");
		Map<String, Object> map;
		List lt = new ArrayList();
		List lt1 = new ArrayList();
		List lt2 = new ArrayList();
		List lt3 = new ArrayList();
		List lt4 = new ArrayList();
		for(CommodityAnalysisEntity analysis:analysisList){
			map = new HashMap<String, Object>();
			map.put("date", analysis.getTime().toString());
			map.put("orders", analysis.getPayMoney());
			map.put("person", analysis.getOldBuyerVisitors());
			lt.add(map);

			map = new HashMap<String, Object>();
			map.put("date", analysis.getTime().toString());
			map.put("visitors",analysis.getVisitorsNum());
			map.put("exposure", analysis.getSearchExposure());
			lt1.add(map);

			map = new HashMap<String, Object>();
			map.put("date", analysis.getTime().toString());
			map.put("hits",analysis.getSearchHits().replace("%", ""));
			lt2.add(map);

			map = new HashMap<String, Object>();
			map.put("date", analysis.getTime().toString());
			map.put("pay",analysis.getPayOrders());
			map.put("shopCar", analysis.getAddShoppingCartPerson());
			map.put("orders", analysis.getPlacingOrders());
			map.put("collect", analysis.getCollectionPerson());
			lt3.add(map);

			map = new HashMap<String, Object>();
			map.put("date", analysis.getTime().toString());
			map.put("visitors",analysis.getOldBuyerVisitors());
			map.put("rate",Double.parseDouble(analysis.getOldBuyerRate().replace("%","")));
			lt4.add(map);
		}
		request.setAttribute("chartdivDataProvider",JSONArray.fromObject(lt));
		request.setAttribute("chartdiv1DataProvider",JSONArray.fromObject(lt1));
		request.setAttribute("chartdiv2DataProvider",JSONArray.fromObject(lt2));
		request.setAttribute("chartdiv3DataProvider",JSONArray.fromObject(lt3));
		request.setAttribute("chartdiv4DataProvider",JSONArray.fromObject(lt4));

		//changes
		List<StoreProductChangeEntity> changes = storeProductChangeService.findByProperty(StoreProductChangeEntity.class,"pId",pid);
		request.setAttribute("changes",changes);

		//俄团次数
		List<MGroupEntity> groups = mGroupService.findByProperty(MGroupEntity.class,"productId",pid);
		request.setAttribute("ruNum", groups.size());

		return new ModelAndView("front/store_pro_detail");
	}


	/**
	 * 变更列表
	 *
	 * @return
	 */
	@RequestMapping(params = "changeList")
	public void changeList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		String pid = request.getParameter("pid");
		List<MChangeEntity> list = mChangeService.findByQueryString("from MChangeEntity where pid = " + pid + " order by date desc");

		response.getWriter().print(JSONArray.fromObject(list));
		response.getWriter().flush();
		response.getWriter().close();
	}


	@RequestMapping(params = "attCountry")
	public void attCountry(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String pid = request.getParameter("pid");
		String wid = request.getParameter("wid");
		java.text.DecimalFormat   df = new   java.text.DecimalFormat("#.##");
		Long count = 0L;
		List<MProductEntity> mProductEntities = mProductService.findByQueryString(" from MProductEntity where pid = " + pid);
		MProductEntity mProduct = new MProductEntity();
		if(mProductEntities.size()>0){
			mProduct = mProductEntities.get(0);
			if(mProductEntities.get(0).getCount()!=null){
				count = mProductEntities.get(0).getCount().longValue();
			}
		}else{
			return;
		}

		if(mProduct.getCountrys() == null){
			return;
		}
		String[] countrys = mProduct.getCountrys().split(",");
		Map<String, Object> map;
		List lt = new ArrayList();
		for(String s:countrys){
			map = new HashMap<String, Object>();
			String[] obj = s.split(":");
			map.put("country", obj[0]);
			map.put("orders", obj[1]);
			Long groupCount = (Long) Long.parseLong(obj[1]);
			Double  percentage = 0.0;
			if (count != null && count.intValue() != 0) {
				percentage = new Double(groupCount)/count;
			}
			map.put("rate", df.format(percentage * 100) + "%");
			lt.add(map);
		}


		dataGrid.setTotal(1);
		dataGrid.setResults(lt);
		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * 客户级别生成
	 *
	 * @return
	 */
	@RequestMapping(params = "customerLevel")
	public void customerLevel(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		String pid = request.getParameter("pid");
		String wid = request.getParameter("wid");
		List<MProductEntity> mProductEntities = mProductService.findByQueryString(" from MProductEntity where pid = " + pid);
		MProductEntity mProduct = new MProductEntity();
		if(mProductEntities.size()>0){
			mProduct = mProductEntities.get(0);
		}

		Map<String, Object> map;
		if(mProduct.getCustomerlever() == null){
			return;
		}
		String[] cl = mProduct.getCustomerlever().split(";");
		List<String> list = new ArrayList<String>();
		String[] result = new String[5];
		for(int i=0;i<4;i++){
			String[] obj = cl[i].split(":");
			result[i] = "{\"name\":\""+obj[0]+"\",\"value\":\""+obj[1]+"\"}";
			list.add("{\"name\":\""+obj[0]+"\",\"value\":\""+obj[1]+"\"}");
		}

		response.getWriter().print(JSONArray.fromObject(list));
		response.getWriter().flush();
		response.getWriter().close();
	}





	/**
	 * 30天销量变化
	 *
	 * @return
	 */
	@RequestMapping(params = "orders30")
	public void orders30(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		String pid = request.getParameter("pid");
		String wid = request.getParameter("wid");
		List<MProductEntity> mProductEntities = mProductService.findHql("from MProductEntity where pid=?", pid);
		MProductEntity mProduct = new MProductEntity();
		if(mProductEntities.size()>0){
			mProduct = mProductEntities.get(0);
		}

		Map<String, Object> map;
		if(mProduct.getOrders() == null){
			return;
		}
		Calendar c = Calendar.getInstance();

		String[] orders = mProduct.getOrders().split(",");
		List<String> list = new ArrayList<String>();
		for(String l:orders){
			list.add("{\"date\":\""+new SimpleDateFormat("MM-dd").format(c.getTime())+"\",\"order\":\""+l+"\"}");
			c.add(Calendar.DAY_OF_MONTH, -1);
		}


		response.getWriter().print(JSONArray.fromObject(list));
		response.getWriter().flush();
		response.getWriter().close();
	}


	@RequestMapping(params = "index_tag")
	public void index_tag(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		List<TagProductPO> list = new ArrayList<TagProductPO>();

		List<FirstTbEntity> fList = getFirstTb(dataGrid);
		String url = "";
//		List<TagEntity> tagList = getTag(dataGrid);
		for(FirstTbEntity firstTb:fList){
			TagProductPO tagProductPO = new TagProductPO();

			MainTabPo mtp = new MainTabPo();
			mtp.setType(firstTb.getType());
			mtp.setId(firstTb.getTabId());
			List<ProductEntity> products = null;
			if(firstTb.getType() == 1){//标签
				if(getTagById(firstTb.getTabId()) == null) continue;
				mtp.setName(getTagById(firstTb.getTabId()).getName());
				products = getProducts(dataGrid, getProductIdsByTagId(dataGrid, firstTb.getTabId()));
				url = "frontController.do?list_tag&tagId="+firstTb.getTabId();
			}else{//类目
				mtp.setName(getCategoryByCategoryId(firstTb.getTabId()).getCName());
				products = getProductsByCategoryId(dataGrid, firstTb.getTabId());
				url = "frontController.do?list&categoryId="+firstTb.getTabId();
			}
			mtp.setUrl(url);
			tagProductPO.setMtp(mtp);
			List<ProductFront> productFronts = new ArrayList<ProductFront>();
			List<String> ids = new ArrayList<String>();
			if(products != null){
				int i = 0;
				for(ProductEntity product:products){
					if(i==6){
						break;
					}
					i++;
					ProductFront productFront = new ProductFront();
					BeanUtils.copyProperties(product, productFront);
//					setOrders(productFront);
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DAY_OF_MONTH, -1);
					//判断是否是最新采集的数据
					if(product.getLastDate().before(calendar.getTime())){
						productFront.setIsSeries(2);
					}
					if(product.getProductOrdersToday() != null)productFront.setTodayOrders(Integer.parseInt(product.getProductOrdersToday()));
					if(product.getProductOrdersYesterday() != null)productFront.setYesterdayOrders(Integer.parseInt(product.getProductOrdersYesterday()));
					if(ids.contains(product.getProductId())){
						continue;
					}
					ids.add(product.getProductId());
					productFronts.add(productFront);
				}
				tagProductPO.setProducts(productFronts);
			}

			list.add(tagProductPO);
		}


		response.getWriter().print(JSONArray.fromObject(list));
		response.getWriter().flush();
		response.getWriter().close();
	}


	private void setOrders(ProductFront productFront) {
		Integer categoryId = Integer.parseInt(productFront.getCategoryId());
		CaijiCategoryEntity category = this.getCategoryByCategoryId(categoryId);
		List<CaijiCategoryEntity> categoryEntities = caijiCategoryService.loadAll(CaijiCategoryEntity.class);
		String tableName = "";
		if(category.getPid() == 1){
			tableName = Tools.getTableName(category.getCName());
		}else if(category.getPid() != 0){
			if(Tools.getTableNameById(category.getPid(),categoryEntities) != null){
				tableName = Tools.getTableNameById(category.getPid(),categoryEntities);
			}
		}

		Connection con = null;
		//
		try{
			con = DriverManager.getConnection(Constant.url_local , Constant.user , Constant.password ) ;
		}catch(SQLException se){
			System.out.println("数据库连接失败！");
			se.printStackTrace() ;
		}
		ResultSet rs;
		List<ProductEntity> pList = new ArrayList<ProductEntity>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from "+tableName+" where product_id = " + productFront.getProductId() + " order by id desc limit 3");
			while(rs.next()){
				ProductEntity product = new ProductEntity();
				product.setProductOrdersToday(rs.getString("product_orders_today"));
				pList.add(product);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(pList.size()>0){
			productFront.setTodayOrders(Integer.parseInt(pList.get(0).getProductOrdersToday()));
		}
		/*if(pList.size()>3){
			productFront.setYesterdayOrders(Integer.parseInt(pList.get(1).getProductOrdersToday())-Integer.parseInt(pList.get(2).getProductOrdersToday()));
		}else{
			productFront.setYesterdayOrders(0);
		}*/

	}



	private List<RUDatePO> getRUDates(String date) {

		Connection con = null;
		//
		try{
			con = DriverManager.getConnection(Constant.url_local , Constant.user , Constant.password ) ;
		}catch(SQLException se){
			System.out.println("数据库连接失败！");
			se.printStackTrace() ;
		}
		ResultSet rs;
		List<RUDatePO> pList = new ArrayList<RUDatePO>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT end_time,COUNT(*) FROM `ru_product` where end_time > '"+new SimpleDateFormat("yyyy-MM-dd").format(c.getTime())+"' GROUP BY end_time;");
			while(rs.next()){
				RUDatePO po = new RUDatePO();
				po.setDate(rs.getString(1).substring(0, 10));
				po.setCount(rs.getInt(2));
				pList.add(po);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return pList;
	}

	/**
	 * 工作组列表
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @throws IOException
	 */
	@RequestMapping(params = "ws_list")
	public void ws_list(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		List<UserMWorkspaceEntity> list = new ArrayList<UserMWorkspaceEntity>();
		TSUser user = ResourceUtil.getSessionUserName();
		List<MWorkspaceEntity> mwList = mWorkspaceService.findByProperty(MWorkspaceEntity.class, "userId", user.getId());
		if(user.getUserName().equals("admin")){
			List<TSBaseUser> userList = this.systemService.findHql(" from TSBaseUser");
			for(TSBaseUser baseUser:userList){
				mwList = mWorkspaceService.findByProperty(MWorkspaceEntity.class, "userId", baseUser.getId());
				UserMWorkspaceEntity userMWorkspaceEntity = new UserMWorkspaceEntity();
				userMWorkspaceEntity.setUserName(baseUser.getUserName());
				userMWorkspaceEntity.setMwList(mwList);
				list.add(userMWorkspaceEntity);
			}
		}else{
			UserMWorkspaceEntity userMWorkspaceEntity = new UserMWorkspaceEntity();
			userMWorkspaceEntity.setUserName(user.getUserName());
			userMWorkspaceEntity.setMwList(mwList);
			list.add(userMWorkspaceEntity);
		}

		//共享的工作组
		List<MWorkspaceEntity> shareList  = mWorkspaceService.findByQueryString(" from MWorkspaceEntity where levelId in (5,6,7,8)");
		UserMWorkspaceEntity userMWorkspaceEntity = new UserMWorkspaceEntity();
		//userMWorkspaceEntity.setUserName(user.getUserName());
		userMWorkspaceEntity.setMwList(shareList);
		list.add(userMWorkspaceEntity);

		response.getWriter().print(JSONArray.fromObject(list));
		response.getWriter().flush();
		response.getWriter().close();
	}


	/**
	 * 工作组关键词列表
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @throws IOException
	 */
	@RequestMapping(params = "ws_key_list")
	public void ws_key_list(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		Integer wid = Integer.parseInt(request.getParameter("wid"));
		List<MKeywordEntity> list = mKeywordService.findByProperty(MKeywordEntity.class, "wid", wid);
		response.getWriter().print(JSONArray.fromObject(list));
		response.getWriter().flush();
		response.getWriter().close();
	}


	/**
	 * 产品组产品列表
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @throws IOException
	 */
	@RequestMapping(params = "wspo_list")
	public void wspo_list(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {

		CriteriaQuery cq = new CriteriaQuery(MProductEntity.class, dataGrid);
		//org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, null, request.getParameterMap());
		cq.setPageSize(100);

		String wid = request.getParameter("wid");
		String order = request.getParameter("orderName");
		String asc = request.getParameter("orderAD");

		String[] pids = getProductIdsByWorkspaceId(dataGrid, Integer.parseInt(wid));
		if(pids.length>0){
			cq.in("pid",getProductIdsByWorkspaceId(dataGrid,Integer.parseInt(wid)));
		}else{
			cq.eq("id",0);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put(order, asc);
		if("asc".equals(asc)){
			map.put(order,  SortDirection.asc);
		}else{
			map.put(order,  SortDirection.desc);
		}
		cq.setOrder(map);
		cq.add();
		this.mProductService.getDataGridReturn(cq, true);
		List<MProductEntity> mProductList = dataGrid.getResults();

		//TagUtil.datagrid(response,dataGrid);
		response.getWriter().print(JSONArray.fromObject(dataGrid));
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
			ids[i] = wsProEntities.get(i).getPid();
		}
		return ids;
	}

	@RequestMapping(params = "ru_date_list")
	public void ru_date_list(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		String date = request.getParameter("lastDate");
		long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
		long zero=yesterday/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
		date = new Timestamp(zero).toString();
		List<RUDatePO> ruList = getRUDates(date);
		response.getWriter().print(JSONArray.fromObject(ruList));
		response.getWriter().flush();
		response.getWriter().close();
	}

	@RequestMapping(params = "tag_list")
	public void tag_list(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		List<TagEntity> tagList = getTag(dataGrid, null);
		response.getWriter().print(JSONArray.fromObject(tagList));
		response.getWriter().flush();
		response.getWriter().close();
	}


	@RequestMapping(params = "tagLink")
	public void tagLink(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		String productId = request.getParameter("productId");
		List<String> ids = userTagProService.findByQueryString("select id from UserTagProEntity where productId=" + productId);
		List<TagEntity> tagList = getTag(dataGrid, ids.toArray());
		response.getWriter().print(JSONArray.fromObject(tagList));
		response.getWriter().flush();
		response.getWriter().close();
	}

	public void createCriteriaQuery(HttpServletRequest request, CriteriaQuery cq, DataGrid dataGrid, String orderBy){
		String storeId = request.getParameter("storeId");
		String searchTime = request.getParameter("searchTime");
		String categoryId = request.getParameter("categoryId");
		String productPriceBegin = request.getParameter("productPriceBegin");
		String productPriceEnd = request.getParameter("productPriceEnd");
		String productDsrBegin = request.getParameter("productDsrBegin");
		String productDsrEnd = request.getParameter("productDsrEnd");
		String productFacebackBegin = request.getParameter("productFacebackBegin");
		String productFacebackEnd = request.getParameter("productFacebackEnd");
		String productOrdersBegin = request.getParameter("productOrdersBegin");
		String productOrdersEnd = request.getParameter("productOrdersEnd");
		String productOrdersAllBegin = request.getParameter("productOrdersAllBegin");
		String productOrdersAllEnd = request.getParameter("productOrdersAllEnd");
		String categoryOrdersBegin = request.getParameter("categoryOrdersBegin");
		String categoryOrdersEnd = request.getParameter("categoryOrdersEnd");
		String categoryOrdersBy = request.getParameter("categoryOrdersBy");
		String tag = request.getParameter("tag");
		String orders = request.getParameter("orders");
		String productName = request.getParameter("productName");
		String productId = request.getParameter("productId");
		String productDsrZero = request.getParameter("productDsrZero");
		String productOrdersZero = request.getParameter("productOrdersZero");


		if(storeId != null && !"".equals(storeId)){
			cq.add(Restrictions.sqlRestriction(" store_id = "+storeId));
		}
		if(categoryId != null && !"".equals(categoryId)){
			cq.eq("categoryId", categoryId.replace("'", ""));
		}else{
			cq.add(Restrictions.sqlRestriction(" is_repeat = 1"));
		}
		if(productName != null && !"".equals(productName)){
			productName = productName.replace("'", "");
			cq.like("productName", "%" + productName + "%");
		}
		if(productId != null && !"".equals(productId)){
			cq.add(Restrictions.sqlRestriction(" product_id = " + productId));
		}
		if(productPriceBegin != null && !"".equals(productPriceBegin)) {
			cq.ge("maxPrice", productPriceBegin);
		}
		if(productPriceEnd != null && !"".equals(productPriceEnd)) {
			cq.le("maxPrice", productPriceEnd);
		}

		if(productFacebackBegin != null && !"".equals(productFacebackBegin)) {
			cq.ge("productFaceback", productFacebackBegin);
		}
		if(productFacebackEnd != null && !"".equals(productFacebackEnd)) {
			cq.le("productFaceback", productFacebackEnd);
		}

		if(productOrdersAllBegin != null && !"".equals(productOrdersAllBegin)) {
			cq.ge("productOrdersAll", productOrdersAllBegin);
		}
		if(productOrdersAllEnd != null && !"".equals(productOrdersAllEnd)) {
			cq.le("productOrdersAll", productOrdersAllEnd);
		}
		if(categoryOrdersBegin != null && !"".equals(categoryOrdersBegin)) {
			cq.ge("categoryOrders", Integer.parseInt(categoryOrdersBegin));
		}
		if(categoryOrdersEnd != null && !"".equals(categoryOrdersEnd)){
			cq.le("categoryOrders", Integer.parseInt(categoryOrdersEnd));
		}
		if(tag != null && !"".equals(tag)){
			cq.in("productId", getProductIdsByTagId(dataGrid, Integer.parseInt(tag)));
		}
		//评价得分为0
		if(productDsrZero != null && productDsrZero.equals("true")){
			if((productDsrBegin != null && !"".equals(productDsrBegin)) || (productDsrEnd != null && !"".equals(productDsrEnd))){
				if((productDsrBegin != null && !"".equals(productDsrBegin)) && (productDsrEnd != null && !"".equals(productDsrEnd))){
					cq.add(Restrictions.or(
							Restrictions.eq("productDsr", "0"),
							Restrictions.between("productDsr", productDsrBegin, productDsrEnd)
					));
				}else{
					if(productDsrEnd != null && !"".equals(productDsrEnd)){
						cq.add(Restrictions.or(
								Restrictions.eq("productDsr", "0"),
								Restrictions.le("productDsr", productDsrEnd)
						));
					}else{
						cq.add(Restrictions.or(
								Restrictions.eq("productDsr", "0"),
								Restrictions.ge("productDsr", productDsrBegin)
						));
					}
				}

			}else{

			}
		}else{
			if(productDsrBegin != null && !"".equals(productDsrBegin)){
				cq.ge("productDsr", productDsrBegin);
			}
			if(productDsrEnd != null && !"".equals(productDsrEnd)){
				cq.le("productDsr", productDsrEnd);
			}
		}

		//销量为0
		if(productOrdersZero != null && productOrdersZero.equals("true")){
			if((productOrdersBegin != null && !"".equals(productOrdersBegin)) || (productOrdersEnd != null && !"".equals(productOrdersEnd))){
				if((productOrdersBegin != null && !"".equals(productOrdersBegin)) && (productOrdersEnd != null && !"".equals(productOrdersEnd))){
					cq.add(Restrictions.or(
							Restrictions.eq(orderBy, "0"),
							Restrictions.between(orderBy, productOrdersBegin, productOrdersEnd)
					));
				}else{
					if(productOrdersBegin != null && !"".equals(productOrdersBegin)){
						cq.add(Restrictions.or(
								Restrictions.eq(orderBy, "0"),
								Restrictions.ge(orderBy, productOrdersBegin)
						));
					}
					if(productOrdersEnd != null && !"".equals(productOrdersEnd)){
						cq.add(Restrictions.or(
								Restrictions.eq(orderBy, "0"),
								Restrictions.le(orderBy, productOrdersEnd)
						));
					}
				}
			}else{

			}

		}else{
			if(Constant.ORDER_BY_DAY.equals(orderBy)){
				if(productOrdersBegin != null && !"".equals(productOrdersBegin)){
					cq.ge(orderBy, productOrdersBegin);
				}
				if(productOrdersEnd != null && !"".equals(productOrdersEnd)){
					cq.le(orderBy, productOrdersEnd);
				}
			}else{
				if(productOrdersBegin != null && !"".equals(productOrdersBegin)){
					cq.ge(orderBy, Integer.parseInt(productOrdersBegin));
				}
				if(productOrdersEnd != null && !"".equals(productOrdersEnd)){
					cq.le(orderBy, Integer.parseInt(productOrdersEnd));
				}
			}

		}
	}


	@RequestMapping(params = "list_category")
	public void list_category(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {

		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		//查询条件组装器
		cq.setPageSize(100);
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		try{
			//自定义追加查询条件
			createCriteriaQuery(request, cq, dataGrid, Constant.ORDER_BY_DAY);
			long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
			long zero=yesterday/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
			cq.ge("lastDate", new Timestamp(zero));

			//排序
			String orderIndex = request.getParameter("orderName");
			String orderAD = request.getParameter("orderAD");
			String orderName = "";
			if(orderIndex!=null && orderAD!=null){
				Map<String,Object> map = new HashMap<String,Object>();
				switch (orderIndex){
					case "0":
					case "1":
						orderName = "maxPrice";
						break;
					case "2":
						orderName = "productOrdersToday";
						break;
					case "3":
						orderName = "productOrders3";
						break;
					case "4":
						orderName = "productOrders7";
						break;
					case "5":
						orderName = "productOrdersAll";
						break;
					case "7":
						orderName = "productFaceback";
						break;
				}
				if(orderName != "") {
					map.put(orderName,  orderAD);
					if("asc".equals(orderAD)){
						map.put(orderName,  SortDirection.asc);
					}else{
						map.put(orderName,  SortDirection.desc);
					}
					cq.setOrder(map);
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		//cq.add(Restrictions.sqlRestriction(" is_repeat = 1"));
//		cq.add(Restrictions.sqlRestriction(" date in (select  min(a.date)  from product a group by a.product_id)"));
		//cq.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		cq.add();
		long start = System.currentTimeMillis();
		this.productService.getDataGridReturn(cq, true);
		System.out.println("产品查询耗时：" + (System.currentTimeMillis() - start));
		TagUtil.datagrid(response, dataGrid);


	}


	@RequestMapping(params = "list_tag_category")
	public void list_tag_category(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		//查询条件组装器
		cq.setPageSize(36);
		String tagId = request.getParameter("tagId");
		String[] ids = getProductIdsByTagId(dataGrid, Integer.parseInt(tagId));

		if(ids.length>0){
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < ids.length; i++){
				sb.append(ids[i]+",");
			}
			cq.add(Restrictions.sqlRestriction(" product_id in ("+sb.toString().substring(0, sb.length()-1)+")"));
		}

//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		try{
			//自定义追加查询条件
			createCriteriaQuery(request, cq, dataGrid, Constant.ORDER_BY_DAY);

			if(ids.length>0){
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < ids.length; i++){
					sb.append(ids[i]+",");
				}
				cq.add(Restrictions.or(
						Restrictions.eq("isRepeat", 1),
						Restrictions.sqlRestriction(" product_id in(select product_id from product where product_id in ("+sb.toString().substring(0, sb.length()-1)+") group by product_id having count(product_id)=1)")
				));
			}else{
				cq.eq("isRepeat", 1);
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		long start = System.currentTimeMillis();
		this.productService.getDataGridReturn(cq, true);
		List<ProductEntity> list = dataGrid.getResults();
		List<ProductEntity> results = new ArrayList<ProductEntity>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(ProductEntity productEntity:list){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			//判断是否是最新采集的数据
			if(productEntity.getLastDate().before(calendar.getTime())){
				productEntity.setIsSeries(2);
			}
			results.add(productEntity);
		}
		dataGrid.setResults(results);
		System.out.println("tag耗时：" + (System.currentTimeMillis() - start));
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		TagUtil.datagrid(response, dataGrid);

	}


	@RequestMapping(params = "list_day")
	public void list_day(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		//查询条件组装器
		cq.setPageSize(36);
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		try{
			//自定义追加查询条件
			createCriteriaQuery(request, cq, dataGrid, Constant.ORDER_BY_DAY);
			long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
			long zero=yesterday/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
			cq.ge("lastDate", new Timestamp(zero));
			cq.eq("isRepeat", 1);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		long start = System.currentTimeMillis();
		this.productService.getDataGridReturn(cq, true);
		System.out.println("list_day耗时：" + (System.currentTimeMillis() - start));
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("count", 100);
		TagUtil.datagrid(response, dataGrid);

	}



	@RequestMapping(params = "list_week")
	public void list_week(ProductEntity product,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws IOException {
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		//查询条件组装器
		cq.setPageSize(36);

//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, product, request.getParameterMap());
		try{
			//自定义追加查询条件
			createCriteriaQuery(request, cq, dataGrid, Constant.ORDER_BY_WEEK);
			cq.eq("isRepeat", 1);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		long start = System.currentTimeMillis();
		this.productService.getDataGridReturn(cq, true);
		System.out.println("list_week耗时：" + (System.currentTimeMillis() - start));
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("count", 100);
		TagUtil.datagrid(response, dataGrid);

	}


	public List<TagEntity> getTag(DataGrid dataGrid,Object[] keyvalue){
		CriteriaQuery cq = new CriteriaQuery(TagEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new TagEntity(), null);
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			cq.eq("userId", user.getId());
			if(keyvalue != null && keyvalue.length>0){
				cq.in("id", keyvalue);
			}
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		cq.setOrder(map);
		cq.add();
		this.tagService.getDataGridReturn(cq, true);
		return dataGrid.getResults();
	}

	public List<FirstTbEntity> getFirstTb(DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(FirstTbEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new FirstTbEntity(), null);
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			cq.eq("userId", user.getId());
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		cq.setOrder(map);
		cq.add();
		this.firstTbServiceI.getDataGridReturn(cq, true);
		return dataGrid.getResults();
	}


	public List<ProductEntity> getProducts(DataGrid dataGrid, String[] ids){
		if(ids.length == 0){
			return null;
		}
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		cq.setPageSize(10);
		if(ids.length>0){
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < ids.length; i++){
				sb. append(ids[i]+",");
			}
			cq.add(Restrictions.sqlRestriction(" product_id in ("+sb.toString().substring(0, sb.length()-1)+")"));
		}
		if(ids.length>0){
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < ids.length; i++){
				sb.append(ids[i]+",");
			}
			cq.add(Restrictions.or(
					Restrictions.eq("isRepeat", 1),
					Restrictions.sqlRestriction(" product_id in(select product_id from product where product_id in ("+sb.toString().substring(0, sb.length()-1)+") group by product_id having count(product_id)=1)")
			));
		}else{
			cq.eq("isRepeat", 1);
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new ProductEntity(), null);
		try{
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.productService.getDataGridReturn(cq, true);

		return dataGrid.getResults();
	}


	public List<ProductEntity> getProductsByCategoryId(DataGrid dataGrid, int categoryId){
		CriteriaQuery cq = new CriteriaQuery(ProductEntity.class, dataGrid);
		cq.setPageSize(5);
		cq.eq("categoryId", "'" + categoryId + "'");
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new ProductEntity(), null);
		try{
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.productService.getDataGridReturn(cq, true);

		return dataGrid.getResults();
	}

	public String[] getProductIdsByTagId(DataGrid dataGrid,Integer tagId){
		CriteriaQuery cq = new CriteriaQuery(UserTagProEntity.class, dataGrid);
		cq.eq("tagId", tagId);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new UserTagProEntity(), null);
		try{
			//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.userTagProService.getDataGridReturn(cq, true);
		List<UserTagProEntity> userTagProEntities = dataGrid.getResults();

		String[] ids = new String[userTagProEntities.size()];
		for(int i=0; i<userTagProEntities.size(); i++){
			ids[i] = "\'"+userTagProEntities.get(i).getProductId()+"\'";
		}
		return ids;
	}


	public CaijiCategoryEntity getCategoryByCategoryId(Integer categoryId){
		Connection con = null;
		ResultSet rs;
		CaijiCategoryEntity category = new CaijiCategoryEntity();
		try{
			con = DriverManager.getConnection(Constant.url_local , Constant.user , Constant.password ) ;
		}catch(SQLException se){
			System.out.println("数据库连接失败！");
			se.printStackTrace() ;
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from caiji_category where c_id = " + categoryId);
			while(rs.next()){
				category.setCId(rs.getInt("c_id"));
				category.setCName(rs.getString("c_name"));
				category.setPid(rs.getInt("pid"));
				category.setId(rs.getInt("id"));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}


	private TagEntity getTagById(int id){
		return tagService.getEntity(TagEntity.class, id);
	}




	/**
	 * 删除关键字
	 *
	 * @return
	 */
	@RequestMapping(params = "delKeyword")
	@ResponseBody
	public AjaxJson delKeyword(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		Integer id = Integer.parseInt(req.getParameter("id"));
		MKeywordEntity mKeyword = systemService.getEntity(MKeywordEntity.class, id);
		mKeywordService.delete(mKeyword);

		message = "删除成功";
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加关键字
	 *
	 * @return
	 */
	@RequestMapping(params = "addKeyword")
	@ResponseBody
	public AjaxJson addKeyword(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		Integer id = Integer.parseInt(req.getParameter("id"));
		String keyword = req.getParameter("keyword");
		try{
			keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
		}catch (UnsupportedEncodingException e){

		}

		TSUser user = ResourceUtil.getSessionUserName();
		MKeywordEntity mKeyword =   new MKeywordEntity();
		mKeyword.setKeyword(keyword);
		mKeyword.setWid(id);
		mKeyword.setUserId(user.getId());
		mKeywordService.save(mKeyword);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加工作组
	 *
	 * @return
	 */
	@RequestMapping(params = "addWorkspace")
	@ResponseBody
	public AjaxJson addWorkspace(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();

		String name = req.getParameter("name");
		String levelId = req.getParameter("levelId");
		try{
			name = java.net.URLDecoder.decode(name, "UTF-8");
		}catch (UnsupportedEncodingException e){

		}

		TSUser user = ResourceUtil.getSessionUserName();
		MWorkspaceEntity mWorkspace = new MWorkspaceEntity();
		mWorkspace.setWorkName(name);
		mWorkspace.setUserId(user.getId());
		mWorkspace.setLevelId(levelId);
		mWorkspaceService.save(mWorkspace);

		j.setMsg(message);
		return j;
	}


	/**
	 * 修改工作组名称
	 *
	 * @return
	 */
	@RequestMapping(params = "editWorkspace")
	@ResponseBody
	public AjaxJson editWorkspace(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();

		String name = req.getParameter("name");
		String wid = req.getParameter("wid");
		String levelId = req.getParameter("levelId");


		TSUser user = ResourceUtil.getSessionUserName();
		MWorkspaceEntity mWorkspace = mWorkspaceService.getEntity(MWorkspaceEntity.class, Integer.parseInt(wid));
		if(user.getId().equals(mWorkspace.getUserId())){
			if(!Tools.isNullOrEmpty(name)){
				try{
					name = java.net.URLDecoder.decode(name, "UTF-8");
				}catch (UnsupportedEncodingException e){

				}
				mWorkspace.setWorkName(name);
			}
			if(!Tools.isNullOrEmpty(levelId)){
				mWorkspace.setLevelId(levelId);
			}
			mWorkspaceService.save(mWorkspace);
			message="更新成功！";
			j.setSuccess(true);
		}else{
			message="更新失败，只有创建者才能修改！";
			j.setSuccess(false);
		}

		j.setMsg(message);
		return j;

	}

	/**
	 * 添加工作组产品
	 *
	 * @return
	 */
	@RequestMapping(params = "addProduct")
	@ResponseBody
	public AjaxJson addProduct(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		MProductEntity mProduct = new MProductEntity();
		String pid = request.getParameter("pid");
		String wid = request.getParameter("wid");
		String[] pids = pid.split(",");
		//批量添加
		if(pids.length>1){
			for(int i=0;i<pids.length;i++){
				pid = pids[i];
				List<WsProEntity> list = wsProService.findByQueryString("from WsProEntity where pid = " + pid + " and wid = " + wid);
				if(list.size()>0){
					//已经对该产品关注
					message = "已经对该产品"+pid+"关注,不能重复关注！";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}else if(pid == "" || pid == "''"){
					message = "产品ID不能为空！";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}else{
					List<MProductEntity> proList = mProductService.findByQueryString("from MProductEntity where pid = " + pid );
					if(proList.size()==0){
						mProduct = new MProductEntity();
						mProduct.setPid(pid);
						mProduct.setFirstDate(new Date());
						mProductService.save(mProduct);
					}
					WsProEntity wsProEntity = new WsProEntity();
					wsProEntity.setWid(Integer.parseInt(wid));
					wsProEntity.setPid(pid);
					wsProService.save(wsProEntity);

					syncWsPro(wsProEntity, true);

				}
			}
			j.setSuccess(true);
			j.setMsg(message);
			return j;
		}else{


			String name = request.getParameter("name");
			String img = request.getParameter("img");
			String sName = request.getParameter("sName");
			String price = request.getParameter("price");
			if(price!=null){
				price = price.trim();
			}
			List<WsProEntity> list = wsProService.findByQueryString("from WsProEntity where pid = " + pid + " and wid = " + wid);
			if(list.size()>0){
				//已经对该产品关注
				message = "已经对该产品"+pid+"关注,不能重复关注！";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			//如果是通过产品id添加的，这边需要去采集产品信息
			if(name == ""|| name==null){
				System.out.println("**********************************"+pid);
			}else{
				mProduct.setName(name);
				mProduct.setImg(img);
			}
			WsProEntity wsProEntity = new WsProEntity();
			wsProEntity.setWid(Integer.parseInt(wid));
			wsProEntity.setPid(pid);
			wsProService.save(wsProEntity);

			List<MProductEntity> proList = mProductService.findByQueryString("from MProductEntity where pid = " + pid );
			if(proList.size()==0){
				mProduct = new MProductEntity();
				mProduct.setPid(pid);
				mProduct.setSname(sName);
				mProduct.setPrice(price);
				mProduct.setFirstDate(new Date());
				mProductService.save(mProduct);
			}
			syncWsPro(wsProEntity, true);
			j.setMsg(message);
			return j;
		}
	}

	private static void doCatch(MProductEntity mProduct, String url) throws InterruptedException, IOException {
		URL serverUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) serverUrl
				.openConnection();
		conn.setRequestProperty("Cookie","11.227.7.27.1470909775656.996174.4; ali_beacon_id=11.227.7.27.1470909775656.996174.4; cna=3w4yEJlbElUCATEsOe/ZBDy0; xman_f=Lmv687wW3+STPgGCZizbYhfTwVFMfSpu6/c/5A/g4Szvf64+h3ohfM5EnjLaVSxpU4WoBYjDeYCZwG0sH9fig+gpgEruFqWF4D9KF51dx+g2x/DUzw5SCQ==; acs_usuc_t=acs_rt=3bfed8dc9a024d16900e437808eacb62; xman_t=Yh+64k0VI0vvmjmvkEwjxTw8NWhb8D0tIInDm0OBNVBI531m10JsTx9y3m6nFhqR; __utma=3375712.787323053.1470910075.1470916656.1471061190.3; __utmc=3375712; __utmz=3375712.1471061190.3.3.utmcsr=aliexpress.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _mle_tmp0=eNrz4A12DQ729PeL9%2FV3cfUx8KvOTLFScjIydzNzdTJ2cbFwNjUxNTI0dTYyNXKyNDQ3MzB0MzdT0kkusTI0MTc0MDM0NDMwNzXWSUxGE8itsDKojQIAleUXng%3D%3D; aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932703762672%091861300775%09997626966%0932698265016%0932660828343%091485182436%0932674803386%09997470494; JSESSIONID=083325C7F4D76FE7B4DF407A5BA5D0ED; xman_us_f=x_l=1&x_locale=en_US; intl_locale=en_US; aep_usuc_f=site=glo&region=IN&b_locale=en_US&c_tp=USD; intl_common_forever=+g2V9vFiAXqfarXc+MxdivavZ/V2toEnE9ArXK6kH8ZABLMME6Wg8g==; _ga=GA1.2.787323053.1470910075; _gat=1; ali_apache_track=; ali_apache_tracktmp=; l=ArKy4E63Qx92BSzQEK/Z-yeTgvKRGrbd; isg=AhoauWprGbvn05UVGXoo9cG5a8B_ZT72Zu6unSSWn614l7rRDNvuNeDpUdTx");
		conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		conn.setRequestMethod("GET");
		// 必须设置false，否则会自动redirect到Location的地址
		conn.setInstanceFollowRedirects(false);

		conn.addRequestProperty("Accept-Charset", "UTF-8;");
		conn.addRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
		//conn.addRequestProperty("Referer", "http://zuidaima.com/");
		conn.connect();
		String location = conn.getHeaderField("Location");
		System.out.println(location);
		serverUrl = new URL("http:"+location);
		conn = (HttpURLConnection) serverUrl.openConnection();
		conn.setRequestProperty("Cookie", "ali_apache_id=11.227.7.27.1470909775656.996174.4; ali_beacon_id=11.227.7.27.1470909775656.996174.4; cna=3w4yEJlbElUCATEsOe/ZBDy0; xman_f=Lmv687wW3+STPgGCZizbYhfTwVFMfSpu6/c/5A/g4Szvf64+h3ohfM5EnjLaVSxpU4WoBYjDeYCZwG0sH9fig+gpgEruFqWF4D9KF51dx+g2x/DUzw5SCQ==; __utma=3375712.787323053.1470910075.1470910241.1470916656.2; __utmz=3375712.1470916656.2.2.utmcsr=aliexpress.com|utmccn=(referral)|utmcmd=referral|utmcct=/; aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932698265016%0932703762672%091861300775%09997626966%09997470494; _ga=GA1.2.787323053.1470910075; acs_usuc_t=acs_rt=f026c597232a44169b19a2e6139598e7; xman_t=R7l4/FiF/8uPM7BxCSz/iYRr3ez3LSJdTPnY0BkCszqD1aEeD8dBU78LppRsnJIZ; JSESSIONID=5A057AD4A08A9251F2C103645DEAE749; ali_apache_track=; ali_apache_tracktmp=; xman_us_f=x_l=1&x_locale=en_US; aep_usuc_f=site=glo&region=IN&b_locale=en_US&c_tp=USD; intl_locale=en_US; intl_common_forever=J57DxG1KiTM3eHvgl1k4LVq+/CiqLmMIYqgy3DEGyakbqmoz8QXoqg==; l=AvDwKoL9ISHIvy7WJmW7lpQ6QLRC5NSD; isg=Ak9Pl3D3ROt3V0CWHPUFBtze3uMY9S5SS-kb3mFcNr7FMG8yaEQz5k0uBNd0");
		conn.setRequestMethod("GET");
		BufferedReader br = null;
		try {
			//conn.addRequestProperty("X-Forwarded-For", ip);
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
		} catch (Exception e) {
			conn.disconnect();
			System.out.println("请求失败");
			return;
		}
		String strRead = "";
		boolean start = false;
		String imgRegular= "src=\"([^\"]*)\"";
		String nameRegular= "title=\"([^\"]*)\"";
		GroupMethod gMethod = new GroupMethod();
		boolean go = true;
		while ((strRead = br.readLine()) != null && go) {
			if(strRead.contains("class=\"ui-image-viewer-thumb-frame")){
				start = true;
			}
			if(start){
				System.out.println(strRead);
				if(strRead.contains("<img")){
					String img = gMethod.regularGroup(imgRegular, strRead);
					String name =  gMethod.regularGroup(nameRegular, strRead);
					mProduct.setName(name);
					mProduct.setImg(img);
					System.out.println(img);
					System.out.println(name);
					go = false;
					return;
				}

			}
		}
	}


	/**
	 * 删除工作组
	 *
	 * @return
	 */
	@RequestMapping(params = "removeWs")
	@ResponseBody
	public AjaxJson removeWs(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();

		String id = req.getParameter("id");
		MWorkspaceEntity mWorkspace = mWorkspaceService.getEntity(MWorkspaceEntity.class, Integer.parseInt(id));

		if(user.getId().equals(mWorkspace.getUserId())){
			mWorkspaceService.deleteEntityById(MWorkspaceEntity.class, Integer.parseInt(id));
			WsProEntity wsPro = new WsProEntity();
			wsPro.setWid(Integer.parseInt(id));
			systemService.executeSql("delete from m_product where  pid in(select pid from m_ws_pro where wid = '" + id + "')");
			systemService.executeSql("delete from m_ws_pro where  wid='" + id + "'");
			syncWsPro(wsPro, false);
			j.setSuccess(true);
		}else{
			message = "删除失败，只有创建者才能删除！";
			j.setSuccess(false);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 删除工作组产品
	 *
	 * @return
	 */
	@RequestMapping(params = "delProduct")
	@ResponseBody
	public AjaxJson delProduct(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();

		String pid = req.getParameter("pid");
		String wid = req.getParameter("wid");

		WsProEntity wsProEntity = new WsProEntity();
		wsProEntity.setWid(Integer.parseInt(wid));
		wsProEntity.setPid(pid);
		systemService.executeSql("delete from m_ws_pro where pid='"+pid+"' and wid='"+wid+"'");
		//如果删除的话，有可能将别人添加的也一并删除了
		//systemService.executeSql("delete from m_product where pid='"+pid+"'");
		//systemService.executeSql("delete from m_change where pid='"+pid+"'");
		syncWsPro(wsProEntity, false);
		j.setMsg(message);
		return j;
	}


	/**
	 * 更新工作组产品
	 *
	 * @return
	 */
	@RequestMapping(params = "updateMProduct")
	@ResponseBody
	public AjaxJson updateMProduct(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String json = req.getParameter("json");
		JSONArray jsonArray = JSONArray.fromObject(json);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));
			AttentionProductEntity attProduct = (AttentionProductEntity) JSONObject.toBean(jsonObject, AttentionProductEntity.class);
			List<MProductEntity> list = mProductService.findHql("from MProductEntity where pid=?",attProduct.getPid());
			MProductEntity mProduct = new MProductEntity();
			if(list!=null && list.size() > 0) {
				mProduct = list.get(0);
			}

			//boolean isChange = changeMethod(mProduct, attProduct);

			mProduct.setImg(attProduct.getImg());
			mProduct.setName(attProduct.getName());
			mProduct.setPrice(attProduct.getPrice());
			mProduct.setDay1(attProduct.getDay1());
			mProduct.setDay3(attProduct.getDay3());
			mProduct.setDay7(attProduct.getDay7());
			mProduct.setCountrys(attProduct.getCountrys());
			mProduct.setCustomerlever(attProduct.getCustomerlever());
			mProduct.setCount(attProduct.getCount());
			mProduct.setOrders(attProduct.getOrders());
			mProduct.setMaxPrice(attProduct.getMaxPrice());
			mProduct.setMinPrice(attProduct.getMinPrice());
			mProduct.setSname(attProduct.getStoreName());
			mProduct.setLastDate(new Date());//变更时间
			/*if(isChange){
				mProduct.setLastDate(new Date());//变更时间
				mProduct.setViewed(0);
			}*/

			mProductService.saveOrUpdate(mProduct);
		}

		j.setMsg(message);
		return j;
	}


	/**
	 * 更新工作组产品
	 *
	 * @return
	 */
	@RequestMapping(params = "updateMProducts")
	@ResponseBody
	public AjaxJson updateMProducts(HttpServletRequest req) {
		AjaxJson aj = new AjaxJson();
		String json = req.getParameter("json");
		JSONArray jsonArray = JSONArray.fromObject(json);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));
			AttentionProductEntity attProduct = (AttentionProductEntity) JSONObject.toBean(jsonObject, AttentionProductEntity.class);
			List<MProductEntity> list = mProductService.findHql("from MProductEntity where pid=?",attProduct.getPid());
			MProductEntity mProduct = new MProductEntity();
			if(list!=null && list.size() > 0) {
				mProduct = list.get(0);
			}

			boolean isChange = changeMethod(mProduct, attProduct);

			//处理30天销量、day1、day3、day7、国家占比、客户占比
			String orders = "";
			int day1 = 0,day3 = 0,day7 = 0;
			if(mProduct!=null){
				String ors = mProduct.getOrders();
				orders = attProduct.getOrders() + "," + ors.substring(2);
				String os[] = orders.split(",");
				day1 = Integer.parseInt(os[0]);
				day3 = Integer.parseInt(os[0])+ Integer.parseInt(os[1])+ Integer.parseInt(os[2]);
				day7 = Integer.parseInt(os[0])+ Integer.parseInt(os[1])+ Integer.parseInt(os[2])+ Integer.parseInt(os[3])+ Integer.parseInt(os[4])+ Integer.parseInt(os[5])+ Integer.parseInt(os[6]);

			}


			/*mProduct.setImg(attProduct.getImg());
			mProduct.setName(attProduct.getName());*/
			//mProduct.setPrice(attProduct.getPrice());
			mProduct.setDay1(day1);
			mProduct.setDay3(day3);
			mProduct.setDay7(day7);
			//由于与每日同步冲突，这边不记录
			mProduct.setOrders(orders);
			mProduct.setSname(attProduct.getStoreName());
			if(isChange){
				mProduct.setLastDate(new Date());//变更时间
				mProduct.setViewed(0);
			}

			mProductService.saveOrUpdate(mProduct);
		}

		aj.setMsg(message);
		return aj;
	}


	/**
	 * 关注产品变更公共方法
	 * @param wsPro
	 * @param add
	 */
	private boolean changeMethod(MProductEntity mProduct, AttentionProductEntity attProduct){
		boolean isChange = false;
		if((mProduct.getPrice()!=null ) && (attProduct.getPrice()!=null || attProduct.getPrice() != "null")){
			if(!mProduct.getPrice().trim().equals(attProduct.getPrice().trim())){
				MChangeEntity mChange = new MChangeEntity();
				mChange.setPid(mProduct.getPid());
				mChange.setType(1);
				mChange.setOld(mProduct.getPrice());
				mChange.setNow(attProduct.getPrice());
				mChange.setDate(new Date());
				isChange = true;
				mChangeService.save(mChange);
			}

		}
		if((mProduct.getImg()!=null) && (attProduct.getImg()!=null || attProduct.getImg() != "null")){
			String old = mProduct.getImg().substring(20,mProduct.getImg().length()-1);
			String now = attProduct.getImg().substring(20,attProduct.getImg().length()-1);
			if(!old.equals(now)){
				MChangeEntity mChange = new MChangeEntity();
				mChange.setPid(mProduct.getPid());
				mChange.setType(2);
				mChange.setOld(mProduct.getImg());
				mChange.setNow(attProduct.getImg());
				mChange.setDate(new Date());
				isChange = true;
				mChangeService.save(mChange);
			}

		}
		if((mProduct.getName()!=null) && (attProduct.getName() != null || attProduct.getName() != "null")) {
			if (!mProduct.getName().trim().equals(attProduct.getName().trim())){
				MChangeEntity mChange = new MChangeEntity();
				mChange.setPid(mProduct.getPid());
				mChange.setType(3);
				mChange.setOld(mProduct.getName());
				mChange.setNow(attProduct.getName());
				mChange.setDate(new Date());
				isChange = true;
				mChangeService.save(mChange);
			}
		}
		return isChange;
	}

	private void syncWsPro(WsProEntity wsPro,boolean add){
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
			if(add){//true:添加 ; false:删除
				stmt.execute("INSERT INTO m_ws_pro(wid, pid) VALUES ('"+wsPro.getWid()+"', '"+wsPro.getPid()+"')");
			}else{
				if(wsPro.getPid() == null){
					stmt.execute("delete from m_ws_pro where wid='" + wsPro.getWid() + "'");
				}else{
					stmt.execute("delete from m_ws_pro where wid='" + wsPro.getWid() + "' and pid =  '" + wsPro.getPid()+"'");
				}

			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**************************************个人单品 统计图********************************************/
	@RequestMapping(params = "show")
	public void show(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)throws IOException{
		String pid = request.getParameter("pid");
		List<CommodityAnalysisEntity> analysisList = commodityAnalysisService.findHql(" from CommodityAnalysisEntity where c_Id = '"+pid+"' and time>20160701 order by time asc");
		Map<String, Object> map;
		List lt = new ArrayList();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String todayStr = format.format(newTime);
		for(CommodityAnalysisEntity analysis:analysisList){
			map = new HashMap<String, Object>();
			map.put("date", analysis.getTime().substring(0,4)+"-"+analysis.getTime().substring(4,6)+"-"+analysis.getTime().substring(6,8));
			map.put("orders",analysis.getPayMoney());
			map.put("person", analysis.getOldBuyerVisitors());
			lt.add(map);
		}

		response.getWriter().print(JSONArray.fromObject(lt));
		response.getWriter().flush();
		response.getWriter().close();
	}
}
